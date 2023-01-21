import java.sql.*;
import java.util.ArrayList;

public class MyScrollableRes2{
    public static void main(String[] args) {
        Connection con = null;
        try{
            Class.forName("oracle.jdbc.OracleDriver");

            System.out.println("Driver Successfully loaded");
            
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","advjavabatch","myscholars");

            System.out.println("Connection successfully opened!");

            Statement st=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);


            ResultSet rs= st.executeQuery("select subject,bookprice,bookname from allbooks");
            ArrayList<Book> bookList=new ArrayList<>();

            while(rs.next()){
                String subject = rs.getString(1);
                if(subject.equalsIgnoreCase("JSE"))
                {
                    double oldamt = rs.getDouble(2);
                    double newamt = oldamt+oldamt*0.1;
                    rs.updateDouble(2, newamt);
                    rs.updateRow();
                    Book obj=new Book(rs.getString(3),oldamt,newamt);
                    bookList.add(obj);

                }
            }

            if(bookList.size()==0)System.out.println("No Books of JSE Found :(");
            else {
                System.out.println(bookList.size()+" Books Updated");
                System.out.println("Book details are:");
                for(Book bk:bookList){
                    System.out.println("Book Name : "+bk.bname+" New Price : "+bk.newp+" Old Price"+bk.oldp);
                    
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