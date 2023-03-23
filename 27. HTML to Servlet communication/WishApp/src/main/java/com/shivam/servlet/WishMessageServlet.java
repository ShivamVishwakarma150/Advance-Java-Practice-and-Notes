package com.shivam.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WishMessageServlet extends HttpServlet {
	
	@Override
	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// get printWriter
		PrintWriter pw = res.getWriter();
		//set respose content type
		res.setContentType("text/html");
		// write b.logic to generate the wish message
		LocalDateTime ldt= LocalDateTime.now();// give current date and time 
		int hour  = ldt.getHour();
		pw.println("<h1 style='color:red;text-align:center'>"+ldt.toString()+"</h1>");
		if(hour<12)
			pw.println("<h1 style='color:orange;text-align:center'>Good Morning</h1>");
		else if(hour<16) {
			pw.println("<h1 style='color:green;text-align:center'>Good Afternoon</h1>");
		}
		else if(hour<20) {
			pw.println("<h1 style='color:pink;text-align:center'>Good Evening</h1>");
		}else {
			pw.println("<h1 style='color:red;text-align:center'>Good Night</h1>");
	
		}
		// add home hyperlink
					pw.println("<br> <a href='http://localhost:3030/WishApp/page.html'>Home</a>");
					//close stream
					pw.close();
			
	}
}
