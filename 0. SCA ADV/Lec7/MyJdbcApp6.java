import java.sql.*;
import java.util.*;

public class MyJdbcApp6 {
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        Connection conn = null;

        try {
            Class.forName("oracle.jdbc.OracleDriver"); 

            System.out.println("Driver successfully loaded!");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "advjavabatch", "myscholars");
            System.out.println("Connection successfully opened!");
            PreparedStatement ps = conn.prepareStatement("Update allbooks set bookprice=bookprice+? where subject=?");

            System.out.println("Enter subject:");
            String subject = kb.nextLine();
            System.out.println("Enter price:");
            double amt = kb.nextDouble();

            ps.setDouble(1, amt);
            ps.setString(2,subject);

            // This method will run the same query which we have store in PreparedStatement object  
            int ans = ps.executeUpdate();

            if(ans==0)
            System.out.println("Sorry! No books updated");
            else
            System.out.println(ans+" books updated");

        } catch (ClassNotFoundException cnf) {
            System.out.println("Cannot load the driver class:" + cnf.getMessage());
         } catch (SQLException ex) {
            System.out.println("DB Error:" + ex.getMessage());
         } finally {
            if (conn != null) {
               try {
                  conn.close();
                  System.out.println("Disconnected successfully to the database");
               } catch (SQLException sq) {
                  System.out.println("Error in closing the connection");
               }
            }
            kb.close();
         }
    }
}

