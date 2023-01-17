import java.util.Scanner;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectTest2 {
    public static void main(String[] args) {
       // read input from enduser
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

        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","Shivam12345");

        if(conn!=null){
            st=con.createStatement();

        }

        // prepare SQL Query

        String query = "SELECT EMPNO,ENAME,JOB,SAL,DEPTNO FROM EMP WHERE JOB IN ("+desg1+","+desg2+","+desg3+") ORDER BY JOB";

        



        

       } 
    }
}
