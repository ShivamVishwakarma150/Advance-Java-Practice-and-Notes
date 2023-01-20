// Update Test
// package com.shivam.jdbc;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class UpdateTest {

	public static void main(String[] args) {
		
		Scanner sc=null;
		Connection con=null;
		Statement st=null;
		try {
			// read inputs
			String newCity=null,newName=null;
			float newAvg=0.0f;
			int no=0;
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter new name for student::");
				newName=sc.nextLine();
				System.out.println("Enter new Address for Student ::");
				newCity=sc.nextLine(); // gives navi mumbai
				System.out.println("Enter new avg for student");
				newAvg = sc.nextFloat();
				
				System.out.println("Enter sno of student::");
				no=sc.nextInt();
			}
			// convert input values as required for sql queries
			newName = "'"+newName+"'"; // gives 'ani rao'
			newCity="'"+newCity+"'"; // gives ' navi mumbai'
			
			// establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","Shivam12345");

			// create JDBC Statement obj
			if(con!=null) {
				st=con.createStatement();
			}
			// prepare SQL query
			  // update student set sname='anil rao',sadd='delhi',avg=91.55 where sno=1003;
			
			String query="UPDATE STUDENT SET SNAME="+newName+",sadd="+newCity+",avg="+newAvg+" where sno="+no;
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
