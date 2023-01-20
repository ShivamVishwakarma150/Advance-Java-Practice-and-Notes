package com.shivam.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class InsertTest {

	public static void main(String[] args) {
		Scanner sc=null;
		
		Connection con=null;
		Statement st=null;
		try {
			//read inputs
			sc=new Scanner(System.in);
			int no=0;
			String name=null,addrs=null;
			float avg=0.0f;
			if(sc!=null) {
				System.out.println("Enter student number ::");
				no=sc.nextInt();
				System.out.println("Enter student name::");
				name=sc.next();
				System.out.println("Enter student address::");
				addrs=sc.next();
				System.out.println("Enter student avg::");
				avg=sc.nextFloat(); //gives 57.77
			}
			
			//convert input values as required for the SQL Query
			name="'"+name+"'";
			addrs="'"+addrs+"'";
			
			// Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","Shivam12345");

			// create JDBC Statement obj
			if(con!=null) {
				st=con.createStatement();
			}
			// prepare SQL query
			// insert into student values(567,'ramesh','hyd',67.88);
						
			String query = " insert into student values  ("+no+","+name+","+addrs+","+avg+")";
			System.out.println(query);
			
			//send and execute query in DB s/w
			int count=0;
			if(st!=null) {
				count=st.executeUpdate(query);
			}
			
			//process the result
			
			if(count==0)
				System.out.println("Record not inserted");
			else
				System.out.println("Record inserted");
			
			
		}//try
		catch(SQLException se){
			if(se.getErrorCode()==1) {
				System.out.println("Dupicates cannot be inserted to PK Column");
			}
			if(se.getErrorCode()==1400) {
				System.out.println("NULL cannot be inserted in PK column");
			}
			if(se.getErrorCode()>=999 && se.getErrorCode()<=999)
			{
				System.out.println("Invalid col names or tables names or SQL keywords");
				se.printStackTrace();
			}
			else if(se.getErrorCode()==12899) {
				System.out.println("Do not insert more than col size data to sname,sadd cols");
				se.printStackTrace();
			}
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
		

	}

}
