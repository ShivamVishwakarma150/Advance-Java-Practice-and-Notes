// package com.shivam.jdbc;

// Write JDBC App who gives MAX sal from EMployee.
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectTest6 {

	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		try {
		
			// Load JDBC Driver class
				// Class.forName("Oracle.jdbc.driver.OracleDriver");
			
			// establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","Shivam12345");
			
			// create JDBC Statement obj
			if(con!=null) {
				st=con.createStatement();
			}
			
			// Prepare SQL query
				// select smpno,ename,job,sal from emp where sal=(select max(sal) from emp)
			String query="SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE SAL=(SELECT MAX(SAL) FROM EMP)";
			System.out.println(query);
			
			//send and execute query
			if(st!=null) {
				rs=st.executeQuery(query);
			}
			
			// process the ResultSet (0 or more records)
			if(rs!=null) {
				boolean flag=false;
				while(rs.next()) {
					flag=true;
					System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
				}//while
				if(flag==false) {
					System.out.println("Record not found");
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
		}//finally

	}// main

}//class
