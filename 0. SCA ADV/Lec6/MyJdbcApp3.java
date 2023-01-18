import java.sql.*;

class MyJdbcApp3 {
    public static void main(String[] args) {
        Connection conn = null;
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            System.out.println("Driver loaded successfully");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "advjavabatch", "myscholars");
            System.out.println("Connected successfullzy to the database");
            Statement st = conn.createStatement();
            String qry = "Insert into allbooks values(105,'Web Development',600,'Web Dev')";
            int res = st.executeUpdate(qry);
            System.out.println("Rec inserted:" + res);
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
