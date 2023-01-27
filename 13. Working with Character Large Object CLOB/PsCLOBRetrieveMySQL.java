// package com.shivam.jdbc1;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

// import org.apache.commons.io.IOUtils;


public class PsCLOBRetrieveMySQL {
private static final String JS_RETRIEVE_QUERY="SELECT JSID,JSNAME,JSADDRS,RESUME,PHOTO FROM JOBSEEKER_INFO WHERE JSID=?";
	public static void main(String[] args) {
		try(Scanner sc=new Scanner(System.in)){ //try1
			int jsid=0;
			if(sc!=null) {
				System.out.println("Enter Job id::");
				jsid=sc.nextInt();
				}
			
			// create connection,PreparedStatement objects
			try(Connection con=DriverManager.getConnection("jdbc:mysql:///ntaj414","root","Shivam@123");
					PreparedStatement ps=con.prepareStatement(JS_RETRIEVE_QUERY);) //try2
					{
				// set query param
				if(ps!=null) {
					ps.setInt(1, jsid);
				}
				
				//execute query
				try(ResultSet rs=ps.executeQuery()){ //try3
					// process the result
					if(rs!=null) {
						if(rs.next()) {
							jsid=rs.getInt(1);
							String name=rs.getString(2);
							String addrs=rs.getString(3);
							System.out.println(jsid+" "+name+""+addrs);
							// get Reader Stream pointing to CLOB value
				try(Reader reader=rs.getCharacterStream(4);
					//create Output stream pointing to destination file 
						InputStream is=rs.getBinaryStream(5);
						OutputStream os=new FileOutputStream("retrieve_image.jpg");
						Writer writer=new FileWriter("retrieve_resume.txt");
						){
					//Copy BLOB col value to Destination file
					// IOUtils.copy(is,os); // takes images writing
					// IOUtils.copy(reader, writer); // takes text writing
					System.out.println("CLOB,BLOB value is retrieve and stored in the file");
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
