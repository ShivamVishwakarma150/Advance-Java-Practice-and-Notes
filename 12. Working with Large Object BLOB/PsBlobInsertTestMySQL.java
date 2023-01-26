// package com.shivam.jdbc1;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PsBlobInsertTestMySQL {
private static final String INSERT_ARTIST_QUERY="INSERT INTO ARTIST_INFO(NAME,ADDRS,PHOTO) VALUES(?,?,?)";
	public static void main(String[] args) {
		
		try(Scanner sc=new Scanner(System.in);){
			// read inputs
			String name=null,addrs=null,photoLocation=null;
			if(sc!=null) {
				System.out.println("Enter artist Name::");
				name=sc.next();
				System.out.println("Enter Artist addrss:::");
				addrs=sc.next();
				System.out.println("Enter Artist photo Location::");
				photoLocation=sc.next().replace("?","");
			}//if
			//create InputStram pointing to photo file
			try(InputStream is=new FileInputStream(photoLocation)){
				//establish the connection and prepared statement
				try(Connection con=DriverManager.getConnection("jdbc:mysql:///ntaj414","root","Shivam@123");
				PreparedStatement ps=con.prepareStatement(INSERT_ARTIST_QUERY);
						){
					//set values to query param
					if(ps!=null) {
						ps.setString(1,name);
						ps.setString(2, addrs);
						ps.setBinaryStream(3, is);
					}
					//execute the query
					int count=0;
					if(ps!=null) {
						count=ps.executeUpdate();
					}
					
					//process the result
					if(count==0)
						System.out.println("Record not inserted");
					else
						System.out.println("Record inserted");
					
				}//try3
				
			}//try2
		}//try1
		catch(SQLException se) {
			se.printStackTrace();
			System.out.println("Problem in record Insertion");
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}//main

}//class
