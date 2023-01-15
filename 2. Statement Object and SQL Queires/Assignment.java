import java.sql.*;

public class Assignment {
    public static void main(String[] args) throws Exception {
         // Establish the connection ( Road )

         Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","Shivam12345");

         // create JDBC Statement object ( vehical )
         Statement st=con.createStatement();
 
         // Send and execute SQL Query in Db s/w and get JDBC ResultSet object
 
         ResultSet rs=st.executeQuery("SELECT * FROM DEPT");
 
         // Process the ResultSet object
 
         while(rs.next()!=false){
             System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3));
 
         }
 
         //close jdbc objs
 
         rs.close();
         st.close();
         con.close();
    }
}
