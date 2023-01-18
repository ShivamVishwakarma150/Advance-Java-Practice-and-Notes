// Select4.java

// Write a JDBC APP that gives Department form DEPT table Based on given deptno

// package com.shivam.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SelectTest4 {

	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		try {
			// read inputs
			sc=new Scanner(System.in);
			int dno=0;
			if(sc!=null) {
				System.out.println("Enter dept Number ::");
				dno=sc.nextInt(); // gives 1
			}//if
			
			// Load JDBC Driver class
				// Class.forName("Oracle.jdbc.driver.OracleDriver");
			
			// establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","Shivam12345");
			
			// create JDBC Statement obj
			if(con!=null) {
				st=con.createStatement();
			}
			
			// Prepare SQL query
				// select * from dept where deptno=0
			String query="SELECT DEPTNO,DNAME,LOC FROM DEPT WHERE DEPTNO="+dno;
			System.out.println(query);
			
			//send and execute query
			if(st!=null) {
				rs=st.executeQuery(query);
			}
			
			// process the ResultSet (0 or 1 record)
			if(rs!=null) {
				if(rs.next())
					System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3));
				else
					System.out.println("No Record found");
			}//if
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(rs!=null) {
					rs.close();
				}
			}catch(SQLException se) {
				se.printStackTrace();
			}
			
			try {
				if(st!=null) {
					st.close();
				}
			}catch(SQLException se) {
				se.printStackTrace();
			}
			
			try {
				if(con!=null) {
					con.close();
				}
			}catch(SQLException se) {
				se.printStackTrace();
			}
			
			try {
				if(sc!=null) {
					sc.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}//finally

	}// main

}//class
