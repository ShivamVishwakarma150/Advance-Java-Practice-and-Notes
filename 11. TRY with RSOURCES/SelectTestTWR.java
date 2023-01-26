// SelectTest.java


package com.shivam.jdbc1;

/* Java APP to get Employee Details based on given initial Characters Employee name */


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class SelectTestTWR {

	public static void main(String[] args) {
		try (
				//establish the connection
				Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","Shivam12345");
				// create statement object
				Statement st=con.createStatement();
				// send and execute SQL query in Db s/w
				ResultSet rs=st.executeQuery("SELECT SNO,SNAME,SADD,AVG FROM STUDENT"); )
		{
			
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
		}//TRY
		catch(SQLException se){
			if(se.getErrorCode()>=999 && se.getErrorCode()<=999)
			{
				System.out.println("Invalid col names or tables names or SQL keywords");
				se.printStackTrace();
			}
		}
	}//main
}//class
