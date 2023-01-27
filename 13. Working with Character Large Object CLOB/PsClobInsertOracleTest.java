// package com.shivam.jdbc1;

import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PsClobInsertOracleTest {
private static final String INSERT_JOBSEEKER_QUERY="INSERT INTO JOBSEEKER_INFO VALUES(JSID_SEQ1.NEXTVAL,?,?,?)";
	public static void main(String[] args) {
		
		try(Scanner sc=new Scanner(System.in);){ //try1
			// read inputs
			String name=null,addrs=null,resumeLocation=null;
			if(sc!=null) {
				System.out.println("Enter JobSeeker Name::");
				name=sc.next();
				System.out.println("Enter JobSeeker addrss:::");
				addrs=sc.next();
				System.out.println("Enter Resume Location::");
				resumeLocation=sc.next().replace("?", "");
			}//if
			//create InputStram pointing to photo file
			try(Reader reader=new FileReader(resumeLocation)){ //try2
				//establish the connection and prepared statement
				try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","Shivam12345");
				PreparedStatement ps=con.prepareStatement(INSERT_JOBSEEKER_QUERY);
						){ //try3
					//set values to query param
					if(ps!=null) {
						ps.setString(1,name);
						ps.setString(2, addrs);
						ps.setCharacterStream(3, reader);
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
