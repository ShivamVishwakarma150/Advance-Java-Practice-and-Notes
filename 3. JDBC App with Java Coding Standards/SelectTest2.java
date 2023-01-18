import java.util.Scanner;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectTest2 {
    public static void main(String[] args) throws SQLException {
       // read input from end user
       Scanner sc=null;
       String desg1=null,desg2=null,desg3=null;
       Connection con=null;
       Statement st=null;
       ResultSet rs=null;
       try{
        sc=new Scanner(System.in);
        if(sc!=null){
            System.out.println("Enter desg1");
            desg1=sc.next().toUpperCase();
            System.out.println("Enter desg2");
            desg2=sc.next().toUpperCase();
            System.out.println("Enter desg3");
            desg3=sc.next().toUpperCase();

        }

        desg1="'"+desg1+"'";
        desg2="'"+desg2+"'";
        desg3="'"+desg3+"'";

    con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","Shivam12345");

        if(con!=null){
            st=con.createStatement();
        }

        // prepare SQL Query

        String query = "SELECT EMPNO,ENAME,JOB,SAL,DEPTNO FROM EMP WHERE JOB IN ("+desg1+","+desg2+","+desg3+") ORDER BY JOB";

        // send and execute SQL Query in DB s/w
        if(st!=null)
            rs=st.executeQuery(query);

        if(rs!=null){
            System.out.println("The Employees details are");
            while(rs.next()){
                System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4)+" "+rs.getInt(5));

            }//while
        }//if
       }//try
       catch(SQLException se){ // to handle known exception
        System.out.println(se.toString()); // gives details info about the raised exception
       }
       catch(Exception e){ // To handle unknown exceptions
        e.printStackTrace();
       }
       finally {
        try {
            if(rs!=null) {
                rs.close();
            }
        }catch(SQLException se) {
            se.printStackTrace();
        }
        try {
            if(st!=null) {
                st.close();
            }
        }catch(SQLException se) {
            se.printStackTrace();
        }
        try {
            if(con!=null) {
                con.close();
            }
        }catch(SQLException se) {
            se.printStackTrace();
        }
        try {
            if(sc!=null) {
                sc.close();
            }
        }catch(Exception se) {
            se.printStackTrace();
        }
    }//finally


    }
}
