// package com.shivam.jdbc;

// Make A jdbc application where update salary by (user given) percentage

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class UpdateTest1 {

	public static void main(String[] args) {
		
		Scanner sc=null;
		Connection con=null;
		Statement st=null;
		try {
			sc=new Scanner(System.in);
			float hike_percentage=0.0f;
			String desg1=null,desg2=null,desg3=null;
			
			if(sc!=null) {
				System.out.println("Enter employee Desg1:");
				desg1=sc.next().toUpperCase();
				System.out.println("Enter employee Desg2:");
				desg2=sc.next().toUpperCase(); 
				System.out.println("Enter employee Desg3:");
				desg3=sc.next().toUpperCase(); 
				
				
				System.out.println("Salary hike percentage::");
				hike_percentage=sc.nextInt();
			}
			// convert input values as required for sql queries
			desg1="'"+desg1+"'"; // gives 'CLERK'
			desg2="'"+desg2+"'";//gives 'MANAGER'
			desg3="'"+desg3+"'";// gives 'ANALYST'
			
			
			// establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","Shivam12345");

			// create JDBC Statement obj
			if(con!=null) {
				st=con.createStatement();
			}
			// prepare SQL query
			  // update emp set sal=sal+(sal*10/100) where job in ('CLERK','MANAGER','ANALYST');
			
			String query = "update emp set sal=sal+(sal*"+hike_percentage/100+") where job in  ("+desg1+","+desg2+","+desg3+")";
			System.out.println(query);
			
			// send and execute SQL query in Db s/w
			int count=0;
			if(st!=null)
				count=st.executeUpdate(query);
			
			//process the result
			if(count==0)
				System.out.println("No records found for updation");
			else 
				System.out.println(" No of records that are effected ::"+count);

		}//try
		catch(SQLException se){
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
	}//main
}//class
