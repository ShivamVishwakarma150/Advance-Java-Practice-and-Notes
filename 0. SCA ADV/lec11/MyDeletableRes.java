

import java.sql.*;
import java.util.Scanner;
public class MyDeletableRes{
    public static void main(String[] args) {
        Connection con = null;
        try{
            Class.forName("oracle.jdbc.OracleDriver");

            System.out.println("Driver Successfully loaded");
            
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","advjavabatch","myscholars");

            System.out.println("Connection successfully opened!");

            Statement st=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);


            ResultSet rs= st.executeQuery("select bookid,bookname from allbooks");
            
            int count=0;
            Scanner sc=new Scanner(System.in);
            while(rs.next()){
                int bookid=rs.getInt(1);
                String bname=rs.getString(2);
                System.out.println(bookid+","+bname);
                String choice=sc.next();
                if(choice.equalsIgnoreCase("yes")){
                    rs.deleteRow();
                    ++count;
                }
            }
            if(count==0) System.out.println("No books were deleted");
            else 
            System.out.println(count+" books deleted");

            sc.close();
            
            

        }catch(ClassNotFoundException cnf){
            System.out.println("Sorry Cannot Load the driver");
            System.out.println(cnf.getMessage());
        }catch(SQLException sq){
            System.out.println("Sorry Problem with DB");
            System.out.println(sq.getMessage());
        }
        finally{
            if(con!=null){
                try {
                    con.close();
                    System.out.println("Connection Successfully Closed");
                } catch (SQLException e) {
                    System.out.println("Sorry Problem in closing the connection");
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}