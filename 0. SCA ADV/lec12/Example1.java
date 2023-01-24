import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.Connection;

 // Write an app that displays Name and Hiredate of every employee but make sure that an asterik appears infront of names of those employees who were hired on weekends

class Example1{
    public static void main(String[] args) {
        Connection con = null;
        try{
            Class.forName("oracle.jdbc.OracleDriver");

            System.out.println("Driver Successfully loaded");
            
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","advjavabatch","myscholars");

            System.out.println("Connection successfully opened!");

            Statement st=con.createStatement();

            ResultSet rs= st.executeQuery("select ename,hiredate from emp");

            while(rs.next()){
                String ename = rs.getString(1);
                Date hd=rs.getDate(2);
                SimpleDateFormat smp=new SimpleDateFormat("E");
                SimpleDateFormat smp1=new SimpleDateFormat("dd-MMM-yyyy");

                String date=smp.format(hd);
                if(date.equalsIgnoreCase("sun")||date.equalsIgnoreCase("sat"))
                {
                    System.out.println("*"+ename+" "+smp1.format(hd));
                }
                else
                {
                    System.out.println(ename+" "+smp1.format(hd));
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
