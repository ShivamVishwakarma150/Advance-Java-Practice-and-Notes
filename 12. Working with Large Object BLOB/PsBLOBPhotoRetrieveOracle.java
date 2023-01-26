// package com.shivam.jdbc1;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

// import org.apache.commons.io.IOUtils;


public class PsBLOBPhotoRetrieveOracle {
private static final String ARTIST_RETRIEVE_QUERY="SELECT AID,NAME,ADDRS,PHOTO FROM ARTIST_INFO WHERE AID=?";
	public static void main(String[] args) {
		try(Scanner sc=new Scanner(System.in)){
			int aid=0;
			if(sc!=null) {
				System.out.println("Enter Artist id::");
				aid=sc.nextInt();
				}
			
			// create connection,PreparedStatement objects
			try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","Shivam12345");
					PreparedStatement ps=con.prepareStatement(ARTIST_RETRIEVE_QUERY);)
					{
				// set query param
				if(ps!=null) {
					ps.setInt(1, aid);
				}
				
				//execute query
				try(ResultSet rs=ps.executeQuery()){
					// process the result
					if(rs!=null) {
						if(rs.next()) {
							aid=rs.getInt(1);
							String name=rs.getString(2);
							String addrs=rs.getString(3);
							System.out.println(aid+" "+name+""+addrs);
				try(InputStream is=rs.getBinaryStream(4);
					//create Output stream pointing to destination file
						OutputStream os=new FileOutputStream("retrieve_image.jpg");
						){
					//Copy BLOB col value to Destination file
					// IOUtils.copy(is,os);
					System.out.println("BLOB value is retrieve and stored in the file");
				}//try4
						}//if
						else {
							System.out.println("Records not found");
						}//else
					}//if
				}//try3 related rs
			}//try2 related ps,con
		}//try1 //sc
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}//main

}//class
