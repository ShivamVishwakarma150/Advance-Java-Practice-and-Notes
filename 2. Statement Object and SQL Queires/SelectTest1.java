import java.sql.*;
import java.util.*;

public class SelectTest1 {
    public static void main(String[] args)throws Exception {
        // read inputs
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter start range of Employee Salary::");
        float startSalary = sc.nextFloat();
        System.out.println("Enter end range of employee salary");
        float endSalary=sc.nextFloat();
        
        // register JDBC Driver s/w by load jdbc Driver class (optional)
            // Class.forName("oracle.jdbc.driver.OracleDriver");

        // establish the connection with Oracle Db s/w
        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","Shivam12345");


        //create Statement Object
        Statement st=con.createStatement();

        // Prepare SQL Query
        String query = "SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE SAL>="+startSalary+" AND SAL<="+endSalary;
        System.out.println(query);

        // send and execute SQL Query in Db s/w
        ResultSet rs=st.executeQuery(query);

        // Process the ResultSet Obj
        System.out.println("Emp details having salary range "+startSalary+" ....."+endSalary);

        while(rs.next()!=false){
            System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));

        }

        //close jdbc objs

        rs.close();
        st.close();
        con.close();
        sc.close();


        
        

    }
}
