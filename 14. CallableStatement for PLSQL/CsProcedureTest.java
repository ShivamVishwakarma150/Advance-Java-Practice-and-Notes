// package com.shivam.jdbc2;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

/*
 
create or replace procedure first_pro(x in number,y in number,z out number) as
begin
 z:=x+y;
end; 

*/

public class CsProcedureTest {
	private static final String CALL_PROCEDURE="{CALL FIRST_PRO(?,?,?)}";
	public static void main(String[] args) {
		//read inputs
		try(Scanner sc=new Scanner(System.in)){
			System.out.println("Enter first value::");
			int first = sc.nextInt();
			System.out.println("enter second value::");
			int second =sc.nextInt();
			// establish the connection 
			try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","Shivam12345");
			// create CallableStatements object having query calling PL/SQL procedures as the pre-compiled SQL query.
			CallableStatement cs=con.prepareCall(CALL_PROCEDURE);)
			{
				// register OUT params with JDBC data types
				if(cs!=null)
				cs.registerOutParameter(3,Types.INTEGER);
				
				//set values to IN params
				if(cs!=null) {
					cs.setInt(1, first);
					cs.setInt(2, second);
				}
				// execute/call the PL/SQL function
				if(cs!=null) cs.execute();
				
				// gather results from OUT param
				int result=0;
				if(cs!=null) {
					result=cs.getInt(3);
					System.out.println("Sum is :: "+result);
				}
			}//try2
		}//try1
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}

		
	}

}
