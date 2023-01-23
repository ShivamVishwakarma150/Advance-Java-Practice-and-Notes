import java.sql.*;
public class ResultsetMovement{
    public static void main(String[] args) {
        Connection con = null;
        try{
            Class.forName("oracle.jdbc.OracleDriver");

            System.out.println("Driver Successfully loaded");
            
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","advjavabatch","myscholars");

            System.out.println("Connection successfully opened!");

            Statement st=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);


            ResultSet rs= st.executeQuery("select * from allbooks");

            rs.absolute(7); 
            String bname=rs.getString("bookname");
            System.out.println(bname);



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