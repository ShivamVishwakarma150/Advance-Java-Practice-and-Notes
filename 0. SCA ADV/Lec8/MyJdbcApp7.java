import java.sql.*;
import java.util.*;

// WAP to insert multiple records into the table i.e. after inserting a record ask the user if he wants to continue. If the answer if yes then insert another rec otherwise stop. finally display how many records have been inserted in the table.

public class MyJdbcApp7{
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        Connection conn = null;

        try {
            Class.forName("oracle.jdbc.OracleDriver"); 

            System.out.println("Driver successfully loaded!");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "advjavabatch", "myscholars");
            System.out.println("Connection successfully opened!");
            PreparedStatement ps = conn.prepareStatement("Insert into allbooks values(?,?,?,?)");
            int  count=0;
            String choice;
            do{
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
            count+=ans;
            System.out.println("Any More(Yes/No)?");
            choice=kb.next();
            }while(choice.equalsIgnoreCase("yes"));
            System.out.println("Total rec inserted: "+count);

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
