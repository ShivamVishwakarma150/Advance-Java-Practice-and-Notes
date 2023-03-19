package com.shivam.servlet;

import javax.servlet.*; // servlet api
import java.io.*; // Iostreams api
import java.util.*; // Utility api

public class DateServlet extends GenericServlet
{
    public void service(ServletRequest req,ServletResponse res) throws ServletException,IOException{
        // set response content type
        res.setContentType("text/html ");
        // get printWriter stream obj from response obj
        PrintWriter pw = res.getWriter();

        // write request processing logic
        Date d = new Date(); // holds system date and time

        // write generated output to response obj using printWriter Stream
        pw.println("<h1>Date and time is ::"+d+"</h1>");

        //close the stream
        pw.close();

    }//service(-,-)
}//class