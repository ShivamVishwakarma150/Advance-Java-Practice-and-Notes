// HtmlServlet.java

package com.shivam.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class HtmlServlet extends HttpServlet
{
    protected void service(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{
        // set response content Type 
        res.setContentType("text/html");
        // get printWriter Stream
        PrintWriter pw = res.getWriter();
        // enable auto refresh on the web page
        res.setHeader("refresh","10");
        // write output content to browser
        pw.println("<table border='1' align='center'>");
        pw.println("<tr><th>Player Name </th> <th> Medal</th> <th> category </th> </tr>");
        pw.println("<tr> <td> Neeraj Chopra </td> <td> Gold </td> <td>Javaline throw </td> </tr>");
        pw.println("<tr> <td> Meera Bai Chanu </td> <td> Silver </td><td> Weight Lifting </td> </tr>");
        pw.println("<tr> <td> Ravi kumar Dahiya </td> <td> Silver </td><td> Wrestling </td> </tr>");
        pw.println("<tr> <td> P.v.Sindhu </td> <td> bronze </td><td> badminton </td> </tr>");
        pw.println("<tr> <td> Lovlina </td> <td> bronze </td> <td>boxing </td> </tr>");
        pw.println("<tr> <td> Bajarang </td> <td> bronze </td><td> Wrestling </td> </tr>");
        pw.println("</table>");

        pw.println("<br> <h1 style='color:red; text-align:center;'> Date and time is :: "+ new java.util.Date()+"</h1>");

        //close stream
        pw.close();


    }// service
}// class