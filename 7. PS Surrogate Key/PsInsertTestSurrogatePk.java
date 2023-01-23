// package com.shivam.jdbc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PsInsertTestSurrogatePk {
private static final String STUDENT_INSERT_QUERY="INSERT INTO STUDENT VALUES(SNO_SEQL.NEXTVAL,?,?,?)";
	
	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		PreparedStatement ps =null;
		try {
			//read inputs
			sc=new Scanner(System.in);
			int count=0;
			if(sc!=null) {
				System.out.println("enter students count ::");
				count=sc.nextInt();
			}
			// register JDBC Driver (optional)
			//class.ForName("oracle.jdbc.Driver.OracleDrvier");
			
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","Shivam12345");
			
			// create PreparedStatement object having pre-compiled SQL query
			ps=con.prepareStatement(STUDENT_INSERT_QUERY);
			// read input values from enduser, set them to query param values and execute the precompiled 
			// SQL query for multiple times
			if(ps!=null && sc!=null) {
				for(int i=1;i<=count;i++) {
					
					// Read each student input values
					System.out.println("Enter "+i+"Student Details");
					System.out.println("Enter student name::");
					String name=sc.next();
					System.out.println("Enter student address::");
					String addrs=sc.next();
					System.out.println("Enter student avg::");
					float avg=sc.nextFloat();
					//set each student details pre-compiled SQL query params
					ps.setString(1,name);
					ps.setString(2, addrs);
					ps.setFloat(3,avg);
					
					// execute pre-compiled SQL query each time
					int result = ps.executeUpdate();
					
					//process execution result of pre-compiled query
					if(result==0)
						System.out.println(i+" student details not inserted");
					else 
						System.out.println(i+" student details are inserted");
					
				}//for
			}//if
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			//close JDBC objs
			
			try {
				if(ps!=null) {
					ps.close();
				}
			}catch(SQLException se){
				se.printStackTrace();
			}
			
			try {
				if(con!=null) {
					con.close();
				}
			}catch(SQLException se){
				se.printStackTrace();
			}
			
			try {
				if(sc!=null) {
					sc.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}//finally
	}//main

}//class
