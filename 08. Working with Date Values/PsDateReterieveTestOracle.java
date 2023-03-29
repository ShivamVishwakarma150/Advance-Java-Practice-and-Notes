// package com.shivam.jdbc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class PsDateReterieveTestOracle {
	private static final String RETRIEVE_DATES_QUERY = "SELECT PID,PNAME,DOB,DOJ,DOM FROM PERSON_INFO_DATES";
	public static void main(String[] args) {
		Scanner sc=null;
		PreparedStatement ps=null;
		Connection con=null;
		ResultSet rs=null;
		try {
			
				// Load JDBC Driver class
					Class.forName("oracle.jdbc.driver.OracleDriver");
				// establish the connection
					con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","Shivam12345");
					
					if(con!=null)
					ps=con.prepareStatement(RETRIEVE_DATES_QUERY);
					
					// execute Query
					if(ps!=null)
						rs=ps.executeQuery();
					
					// process the ResultSet obj
					/*if(rs!=null) {
						while(rs.next()) {
							System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4));
							
						}//while
					}//if */
					
				System.out.println("--------------------");
			
				//process the ResultSet obj
				if(rs!=null) {
					while(rs.next()) {
						int pid=rs.getInt(1);
						String name=rs.getString(2);
						java.sql.Date sqdob = rs.getDate(3);
						java.sql.Date sqdoj = rs.getDate(4);
						java.sql.Date sqdom = rs.getDate(5);
						
						//convert java.sql.Date class obj to Stirng date values
						SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
						String sdob=sdf.format(sqdob);
						String sdoj=sdf.format(sqdoj);
						String sdom=sdf.format(sqdom);
						System.out.println(pid+" "+name+" "+sdob+" "+sdoj+" "+sdom);
						
						
						
					}//while
				}//if
					
					
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			//close jdbc objs
			try {
				if(ps!=null) ps.close();
			}catch(SQLException se) {
				se.printStackTrace();
			}
			
			try {
				if(con!=null) con.close();
			}catch(SQLException se) {
				se.printStackTrace();
			}
			
			try {
				if(sc!=null) con.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}

	}

}
