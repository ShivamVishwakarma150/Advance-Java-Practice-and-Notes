package com.shivam.jdbc;
// Write a JDBC App to get count of records from Student Db Table
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectTest5 {

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
				// select count(*) from student
			String query="SELECT COUNT(*) FROM STUDENT";
			System.out.println(query);
			
			//send and execute query
			if(st!=null) {
				rs=st.executeQuery(query);
			}
			
			// process the ResultSet (0 or 1 record)
			if(rs!=null) {
				rs.next();
				int count=rs.getInt("COUNT(*)");
				System.out.println("records count in student DB Table::"+count);
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
