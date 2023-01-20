import java.sql.*;

class MyJdbcApp8{
    public static void main(String[] args) {
        Connection con = null;
        try{
            Class.forName("oracle.jdbc.OracleDriver");

            System.out.println("Driver Successfully loaded");
            
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","advjavabatch","myscholars");

            System.out.println("Connection successfully opened!");

            // Read-Only and Forward-only (Non-Parameterized)
            // This line gives us Bidirectional, with read only features and insensitive to changes made by others.  
            Statement st=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);


            ResultSet rs= st.executeQuery("select bookname,bookprice from allbooks");

            while(rs.next()){
                String bname = rs.getString(1);
                Double amt = rs.getDouble(2);
                System.out.println(bname+"\t"+amt);
            }
            System.out.println("Forward Traversal Finished Now Traversing backward");
            // agar ham resultSet ko dono direction me traverse karwana chahate hai to us condition me hame resultSet ko scrollable result set banana pdega.
            // Till now we use only GetXXX() method of resultSet only.

            while(rs.previous()){// cursor backward movement nhi de pa rha hai
                String bname = rs.getString(1);
                Double amt = rs.getDouble(2);
                System.out.println(bname+"\t"+amt);
            }

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