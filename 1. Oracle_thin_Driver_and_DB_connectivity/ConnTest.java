
import java.sql.*;
public class ConnTest {
    public static void main(String[] args) throws Exception {

        
        oracle.jdbc.driver.OracleDriver obj = new oracle.jdbc.driver.OracleDriver();

        DriverManager.registerDriver(obj);

        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","Shivam@12345");

        if(con == null) System.out.println("Connection is not established.");
        else System.out.println("Connection is established");
    }
}
