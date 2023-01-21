import java.sql.*;
import java.util.ArrayList;
public class MyScrollableRes1{
    public static void main(String[] args) {
        Connection con = null;
        try{
            Class.forName("oracle.jdbc.OracleDriver");

            System.out.println("Driver Successfully loaded");
            
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","advjavabatch","myscholars");

            System.out.println("Connection successfully opened!");

            Statement st=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);


            ResultSet rs= st.executeQuery("select subject,bookprice,bookname from allbooks");
            ArrayList<String> bookList=new ArrayList<>();
            while(rs.next()){
                String subject = rs.getString(1);
                if(subject.equalsIgnoreCase("JSE"))
                {
                    double amt=rs.getDouble(2);
                    amt=amt+amt*0.1;
                    rs.updateDouble(2, amt);
                    rs.updateRow();
                    String bname=rs.getString(3);
                    bookList.add(bname);

                }
            }

            if(bookList.size()==0)System.out.println("No Books of JSE Found :(");
            else {
                System.out.println(bookList.size()+" Books Updated");
                System.out.println("Book Names are:");
                for(String str:bookList){
                    System.out.println(str);
                }
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