// package com.shivam.jdbc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class PsDateInsertTestMySQL {
	private static final String INSERT_DATE_QUERY = "INSERT INTO PERSON_INFO_DATES(PNAME,DOB,DOJ,DOM) VALUES(?,?,?,?)";
	public static void main(String[] args) {
		Scanner sc=null;
		PreparedStatement ps=null;
		Connection con=null;
		try {
			//read inputs
			sc=new Scanner(System.in);
			String name=null,sdob=null,sdoj=null,sdom=null;
			if(sc!=null) {
				System.out.println("Person Name::");
				name=sc.next();
				System.out.println("Person DOB(dd-MM-yyyy)::");
				sdob=sc.next();
				System.out.println("Person DOJ(yyyy-MM-dd)::");
				sdoj=sc.next();
				System.out.println("Person DOM(MMM-d-yyyy)::");
				sdom=sc.next();
				
			}
			// COnvert String date values to java.sql.Date classs obj
			// Convert String date value to java.Util.Date class obj
			SimpleDateFormat sdf1=new SimpleDateFormat("dd-MM-yyyy");
			java.util.Date udob=sdf1.parse(sdob);
			// converting java.util.Date class obj to java.sql.Date class obj
			long ms=udob.getTime();
			java.sql.Date sqdob=new java.sql.Date(ms);
			// for DOJ (yyyy-MM-dd Direct conversion)
			  // Converting String value to java.sql.Date class obj
			java.sql.Date sqdoj=java.sql.Date.valueOf(sdoj);
			
			// for DOM(MM-dd-yyyy)
			// Convert String date value to java.Util.Date class obj
			SimpleDateFormat sdf2=new SimpleDateFormat("MMM-dd-yyyy");
				java.util.Date udom=sdf2.parse(sdom);
						// converting java.util.Date class obj to java.sql.Date class obj
				ms=udom.getTime();
				java.sql.Date sqdom=new java.sql.Date(ms);
				
				// Load JDBC Driver class
					Class.forName("com.mysql.cj.jdbc.Driver");
					
				// establish the connection
					con = DriverManager.getConnection("jdbc:mysql:///NTAJ414","root","Shivam@123");
					
					if(con!=null)
					ps=con.prepareStatement(INSERT_DATE_QUERY);
					
					// create PreparedStatement obj
					if(con!=null) {
						ps.setString(1, name);
						ps.setDate(2, sqdob);
						ps.setDate(3, sqdoj);
						ps.setDate(4, sqdom);
					}
					
					// execute Query
					int count=0;
					if(ps!=null)
						count=ps.executeUpdate();
					// process the result
					if(count==0)
							System.out.println("Record not found");
					else
						System.out.println("Record Inserted");
					
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			//close jdbc objs
			try {
				if(ps!=null) ps.close();
			}catch(SQLException se) {
				se.printStackTrace();
			}
			
			try {
				if(con!=null) con.close();
			}catch(SQLException se) {
				se.printStackTrace();
			}
			
			try {
				if(sc!=null) con.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}

	}

}
