// SelectTest.java


// package com.shivam.jdbc;

/* Java APP to get Employee Details based on given initial Characters Employee name */


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class SelectTest3 {

	public static void main(String[] args) {
		System.out.println("SelectTest3.main()");
		Scanner sc = null;
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		try {
			// read inputs
			sc=new Scanner(System.in);
			String initChars=null;
			if(sc!=null) {
				System.out.println("Enter Intital characters of employee name ::");
				initChars=sc.next();
			}
			// convert input values as required for the SQL query
			initChars = "'"+initChars+"%'"; // gives 's%'
			
			// register JDBC driver by loading JDBC driver class
				// Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","Shivam12345");
			
			// create statement object
			if(con!=null) {
				st=con.createStatement();
			}
			
			// prepare SQL query
			String query = "SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE ENAME LIKE "+initChars;
			System.out.println(query);
			// send and execute SQL query in DB s/w
			if(st!=null)
				rs=st.executeQuery(query);
			
			//process the ResultSet object
			if(rs!=null) {
 				// int count=0;
				boolean flag=false;
				while(rs.next()) {
					flag=true;
					System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
				}//while
				
				if(flag==false) {
					System.out.println("No records Found");
				}
			}//if
		}catch(SQLException se){
			if(se.getErrorCode()>=999 && se.getErrorCode()<=999)
			{
				System.out.println("Invalid col names or tables names or SQL keywords");
				se.printStackTrace();
			}
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
			}catch(Exception se) {
				se.printStackTrace();
			}
		}//finally
	}//main
}//class
