//package com.shivam.jdbc;

import java.io.Console;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class LoginApp1 {

	public static void main(String[] args) {
		Console cons=null; 
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		try {
			// read inputs
			cons=System.console(); // java.util.console does not work in Eclipse IDE
			String user=null,pass=null;
			if(cons!=null) {
				System.out.println("Enter Login username::");
				user=cons.readLine();// gives raja rao
				System.out.println("Enter Login Password::");
				pass=new String(cons.readPassword());// gives rao rao
			}//if
			//covert input values as required for the SQL query
			user="'"+user+"'";// gives 'raja rao'
			pass="'"+pass+"'";// gives 'rao rao'
			
			// Load JDBC driver class (optional)
//			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// establish the connection 
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","Shivam12345");
			
			// create JDBC statement object
			if(con!=null) {
				st=con.createStatement();
			}
			
			// preprare SQL query
				// select count(*) from IRCTC_TAB WHERE uname='raja' and pwd='rani';
			String query="SELECT COUNT(*) FROM IRCTC_TAB WHERE UNAME="+user+" AND PWD="+pass;
			System.out.println(query);
			
			//send and execute the query in DB
			if(st!=null) {
				rs=st.executeQuery(query);
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
				if(st!=null) {
					st.close();
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
			
		}

	}

}
