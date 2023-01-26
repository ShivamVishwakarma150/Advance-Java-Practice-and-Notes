// package Shivam.jdbc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class ImgInsert {

	public static void main(String[] args) {
		Connection con=null;
		try {
			 Class.forName("oracle.jdbc.OracleDriver");
			System.out.println("Driver loaded successfully");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","advjavabatch","myscholars");
			System.out.println("Connection successfully opened!");
			PreparedStatement ps =con.prepareStatement("insert into movies values(?,?)");
			Scanner sc=new Scanner(System.in);
			System.out.println("Enter path to the image: ");
			String str=sc.nextLine();
			File f=new File(str);
			FileInputStream fin=new FileInputStream(str);
			String fname=f.getName();
			int dotPos=fname.lastIndexOf(".");
			String movieName=fname.substring(0,dotPos);
			
			ps.setString(1, movieName);
			ps.setBinaryStream(2, fin,(int)f.length());
			
			int res=ps.executeUpdate();
			System.out.println("Record Inserted : "+res);
			sc.close();
			
		}//try
		catch(FileNotFoundException fnf) {
			System.out.println("Cannot open the file ");
			System.out.println(fnf.getMessage());
		}
		catch(ClassNotFoundException cnf) {
			System.out.println("Cannot lad the driver class:"+cnf);
			cnf.getMessage();
		}
		catch(SQLException ex) {
			System.out.println("Error in DB"+ex);
			ex.getMessage();
		}
		finally {
			if(con!=null) {
				try {
					con.close();
					System.out.println("Connection closed succesfully:");
				}
				catch(SQLException ex) {
					System.out.println("Error in CLosing the Connnection"+ex);
					ex.getMessage();
				}
			}
		}


	}

}
