package com.shivam.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class VaccineElglibilityServlet extends HttpServlet {
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("VaccineElglibilityServlet.doPost(-,-) ");
		
		// get PrintWriter
		PrintWriter pw = res.getWriter();
		// set response content type
		res.setContentType("text/html");
		//read form data (req parameter values)
		String name = req.getParameter("pname");
		int age = Integer.parseInt(req.getParameter("page"));
		String addrs= req.getParameter("paddrs");

		//write blogic (request processing logic)
		if(age<18)
			pw.println("<h1 style='color:red;text-align:center'>Mr/Miss/Mirss."+name+" U r not eligible for Corona Vaccination</h1>");
		else
			pw.println("<h1 style='color:green;text-align:center'>Mr/Miss/Mirss. "+name+" U r eligible for Corona Vaccination</h1>");
		
		// add home hyperlink (graphical hyperlink)
		pw.println("<a href='corona_vaccine.html'><img src='images/home.png'></a>");
		
		// close stream
		pw.close();
	}//doGet(-,-)
	
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("VaccineElglibilityServlet.doGet(-,-)");
		// get printWriter
				PrintWriter pw = res.getWriter();
				//set response content type
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
				//close stream
				pw.close();
	}//doPost(-,-)
}//class
