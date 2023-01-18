import java.sql.*;

class MyJdbcApp2 {
   public static void main(String[] args) {
      Connection conn = null;
      try {
         Class.forName("oracle.jdbc.OracleDriver");
         System.out.println("Driver loaded successfully");
         conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "advjavabatch", "myscholars");
         System.out.println("Connected successfully to the database");
         Statement st = conn.createStatement();
         ResultSet rs = st.executeQuery("Select ename,sal from emp");
         int total = 0;
         int count = 0;
         while (rs.next()) {
            String name = rs.getString(1);
            int amt = rs.getInt(2);
            System.out.println(name + "\t" + amt);
            total += amt;
            count++;
         }
         System.out.println("Avg sal is " + (float) total / count);
      } catch (ClassNotFoundException cnf) {
         System.out.println("Cannot laod the driver class:" + cnf.getMessage());
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
      }
   }
}
