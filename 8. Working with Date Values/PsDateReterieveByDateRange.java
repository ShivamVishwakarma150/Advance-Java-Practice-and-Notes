// package com.shivam.jdbc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class PsDateReterieveByDateRange {
	private static final String RETRIEVE_DATES_QUERY = "SELECT PID,PNAME,DOB,DOJ,DOM FROM PERSON_INFO_DATES WHERE DOB>=? AND DOB<=? ";
	public static void main(String[] args) {
		Scanner sc=null;
		PreparedStatement ps=null;
		Connection con=null;
		ResultSet rs=null;
		try {
				//read inputs
				sc=new Scanner(System.in);
				String sdob=null,edob=null;
				if(sc!=null) {
					System.out.println("Enter start range of DOB(dd-MM-yyyy) ");
					sdob=sc.next();
					System.out.println("Enter end range of DOB(dd-MM-yyyy) ");
					edob=sc.next();
				}
				
				//convert String date values java.sql.util class objs
				SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
				
				java.sql.Date ssqdob=new java.sql.Date(sdf.parse(sdob).getTime()); 
				java.sql.Date esqdob=new java.sql.Date( sdf.parse(edob).getTime());
				
				
				
				// Load JDBC Driver class
					Class.forName("com.mysql.cj.jdbc.Driver");
				// establish the connection
					con = DriverManager.getConnection("jdbc:mysql:///ntaj414","root","Shivam@123");
					
					if(con!=null)
					ps=con.prepareStatement(RETRIEVE_DATES_QUERY);
					
					// set values to query parameter
					if(ps!=null) {
						ps.setDate(1, ssqdob);
						ps.setDate(2, esqdob);
					}
					
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
					boolean flag=false;
					while(rs.next()) {
						int pid=rs.getInt(1);
						String name=rs.getString(2);
						java.sql.Date sqdob = rs.getDate(3);
						java.sql.Date sqdoj = rs.getDate(4);
						java.sql.Date sqdom = rs.getDate(5);
						
						//convert java.sql.Date class obj to Stirng date values
						SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
						String dob=sdf1.format(sqdob);
						String doj=sdf1.format(sqdoj);
						String dom=sdf1.format(sqdom);
						System.out.println(pid+" "+name+" "+dob+" "+doj+" "+dom);
						
						
						
					}//while
					if(flag==false) {
						System.out.println("No Records Found");
					}
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
