import java.sql.*;
import java.util.*;
public class MyInsertableRes3{
    public static void main(String[] args) {
        Connection con = null;
        try{
            Class.forName("oracle.jdbc.OracleDriver");

            System.out.println("Driver Successfully loaded");
            
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","advjavabatch","myscholars");

            System.out.println("Connection successfully opened!");

            Statement st=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);


            ResultSet rs= st.executeQuery("select a.* from allbooks a");
            
            Scanner sc=new Scanner(System.in);
            System.out.println("Enter bookid: ");
            int bookid=sc.nextInt();
            System.out.println("Enter bookname:");
            sc.nextLine();
            String bname=sc.nextLine();
            System.out.println("Enter subject:");
            String subject=sc.nextLine();
            System.out.println("Enter bookprice:");
            double amt = sc.nextDouble();

            rs.moveToInsertRow();
            rs.updateInt(1,bookid);
            rs.updateString(2,bname);
            rs.updateString(3,subject);
            rs.updateDouble(4,amt);
            rs.insertRow();
            rs.moveToCurrentRow();
            System.out.println("Record Inserted");


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