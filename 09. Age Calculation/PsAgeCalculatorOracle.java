// package com.shivam.jdbc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class PsAgeCalculatorOracle {
	
	private static final String AGE_CALCULATOR="SELECT ROUND((SYSDATE-DOB)/365.25,2) FROM PERSON_INFO_DATES WHERE PID=?";

	public static void main(String[] args) {
		// read inputs
		Scanner sc=null;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			//read inputs
			sc=new Scanner(System.in);
			int pid=0;
			if(sc!=null) {
				System.out.println("Enter Person ID::");
				pid=sc.nextInt();
			}
			// Load JDBC Driver class
			// Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// Establish the connection 
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","Shivam12345");

			// create JDBC PreparedStatement obj having pre-compiled sql Query
			if(con!=null)
				ps=con.prepareStatement(AGE_CALCULATOR);
			
			// set values to query parameter
			if(ps!=null) {
				ps.setInt(1, pid);
			}
			//execute the Query
			if(ps!=null) {
				rs=ps.executeQuery();
			}
			
			//process the ResultSet
			if(rs!=null) {
				if(rs.next()) {
					float age=rs.getFloat(1);
					System.out.println("Person age is : "+age);
				}
				else {
					System.out.println("Person is not found");
				}
			}//if
			
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
				if(rs!=null) rs.close();
			}catch(SQLException se) {
				se.printStackTrace();
			}
			
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
				if(sc!=null) sc.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}

	}

}
