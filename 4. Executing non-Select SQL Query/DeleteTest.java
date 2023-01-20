// package com.shivam.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DeleteTest {

	public static void main(String[] args) {
		
		Scanner sc=null;
		Connection con=null;
		Statement st=null;
		try {
			// read inputs
			String city=null;
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter sutdent address(city name::");
				city=sc.next(); // gives hyd
			}
			city="'"+city+"'"; // gives 'hyd'
			// establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","Shivam12345");

			// create JDBC Statement obj
			if(con!=null) {
				st=con.createStatement();
			}
			// prepare SQL query
			  // delete from student where sadd='hyd'
			String query="DELETE FROM STUDENT WHERE SADD="+city;
			System.out.println(query);
			
			// send and execute SQL query in Db s/w
			int count=0;
			if(st!=null)
				count=st.executeUpdate(query);
			
			//process the result
			if(count==0)
				System.out.println("No records found to delete");
			else 
				System.out.println(" No of records that are effected ::"+count);

		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
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
	}//main
}//class
