// package com.shivam.jdbc2;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

/*
 
CREATE OR REPLACE PROCEDURE P_GET_EMP_DETAILS_BY_ID 
(
  NO IN NUMBER 
, NAME OUT VARCHAR2 
, DESG OUT VARCHAR2 
, SALARY OUT VARCHAR2 
) AS 
BEGIN
    SELECT ENAME,JOB,SAL INTO NAME,DESG,SALARY FROM EMP WHERE EMPNO=NO;

END;

*/

public class CsProcedureTest2 {
	private static final String CALL_PROCEDURE="{CALL P_GET_EMP_DETAILS_BY_ID(?,?,?,?)}";
	public static void main(String[] args) {
		//read inputs
		try(Scanner sc=new Scanner(System.in)){
			System.out.println("Enter Employee Details::");
			
			System.out.println("Enter EmpNo::");
			int eno = sc.nextInt();
			// establish the connection 
			try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","Shivam12345");
			// create CallableStatements object having query calling PL/SQL procedures as the pre-compiled SQL query.
			CallableStatement cs=con.prepareCall(CALL_PROCEDURE);)
			{
				// register OUT params with JDBC data types
				if(cs!=null) {
					cs.registerOutParameter(2, Types.VARCHAR);
					cs.registerOutParameter(3, Types.VARCHAR);
					cs.registerOutParameter(4,Types.FLOAT );
				}
				
				//set values to IN params
				if(cs!=null) {
					cs.setInt(1,eno);
				}
				// execute/call the PL/SQL function
				if(cs!=null) cs.execute();
				
				// gather results from OUT param
				if(cs!=null) {
					String name=cs.getString(2);
					String desg=cs.getString(3);
					float salary=cs.getFloat(4);
					System.out.println("name:: "+name+"\naddrs::  "+desg+"\nsalary:: "+salary);
				}
			}//try2
		}//try1
		catch(SQLException se) {
			System.out.println("Requested data not available");
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}

		
	}

}
