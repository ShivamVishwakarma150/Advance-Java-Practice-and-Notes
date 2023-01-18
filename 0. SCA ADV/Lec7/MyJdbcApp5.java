import java.sql.*;
import java.util.*;

public class MyJdbcApp5 {
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        Connection conn = null;

        try {
            Class.forName("oracle.jdbc.OracleDriver"); 

            System.out.println("Driver successfully loaded!");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "advjavabatch", "myscholars");
            System.out.println("Connection successfully opened!");
            PreparedStatement ps = conn.prepareStatement("Insert into allbooks values(?,?,?,?)");


            System.out.println("Enter bookid:");
            int bid = kb.nextInt();
            System.out.println("Enter bookname:");
            kb.nextLine();
            String bname = kb.nextLine();
            System.out.println("Enter subject:");
            String subject = kb.nextLine();
            System.out.println("Enter price:");
            double amt = kb.nextDouble();

            ps.setInt(1,bid);
            ps.setString(2,bname);
            ps.setString(3,subject);
            ps.setDouble(4, amt);

            // This method will run the same query which we have store in PreparedStatement object  
            int ans = ps.executeUpdate();
            System.out.println("Rec inserted:"+ans);

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
