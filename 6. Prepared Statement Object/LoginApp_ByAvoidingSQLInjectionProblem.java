package com.shivam.jdbc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class LoginApp {
	private static final String LOGIN_QUERY="SELECT COUNT(*) FROM IRCTC_TAB WHERE UNAME=? AND PWD=?";
	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			// read inputs
			sc=new Scanner(System.in);
			String user=null,pass=null;
			if(sc!=null) {
				System.out.println("Enter Login username::");
				user=sc.nextLine();// gives raja rao
				System.out.println("Enter Login Password::");
				pass=sc.nextLine();// gives rao rao
			}//if
			
			
			// Load JDBC driver class (optional)
				//	Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// establish the connection 
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","Shivam12345");
			
			// create JDBC PreparedStatement object
			if(con!=null) {
				ps=con.prepareStatement(LOGIN_QUERY);
			}
			
			// set values to prarams of pre-compiled query
			if(ps!=null) {
				ps.setString(1, user);
				ps.setString(2, pass);
			}
			
			//send and execute the query in DB
			if(ps!=null) {
				rs=ps.executeQuery();
			}
			//process the resultSet object
			if(rs!=null) {
				rs.next();//moves the cursor to first record from BFR
				int count=rs.getInt(1);// get first col value of that first record
				if(count==0)
					System.out.println("Invalid Credentials");
				else
					System.out.println("Valid Credentials");
				
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
				if(rs!=null) {
					rs.close();
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
		}

	}

}
