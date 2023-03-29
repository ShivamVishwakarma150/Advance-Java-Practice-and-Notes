
import java.sql.*;
public class ConnTest {
    public static void main(String[] args) throws Exception {

        // 1. Register JDBC driver s/w

        // // a. create JDBC Driver class object
        // oracle.jdbc.driver.OracleDriver obj = new oracle.jdbc.driver.OracleDriver();

        // // register JDBC driver class object with DriverManager service
        // DriverManager.registerDriver(obj);

        //2. Establish the connection
        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","Shivam@12345");

        if(con == null) System.out.println("Connection is not established.");
        else System.out.println("Connection is established");
    }
}
