package com.shivam.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LinksServlet extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// get PrintWriter
		PrintWriter pw = res.getWriter();
		//set response content type
		res.setContentType("text/html");
		// read additional req param value (p1)
		String pval=req.getParameter("p1");
		
		//get All Available Locales
		Locale locales[]=Locale.getAvailableLocales();
		// Differentiate logics for hyperlinks
		if(pval.equalsIgnoreCase("link1")) {
			pw.println("<h1> All Languages are <h1>");
			for(Locale l:locales)
				pw.println(l.getDisplayLanguage()+",");
		}//if
		else if(pval.equalsIgnoreCase("link2")) {
			pw.println("<h1> All Countries are <h1>");
			for(Locale l:locales)
				pw.println(l.getDisplayCountry()+",");
		}
		else {
			pw.println("<h1> System Date and Time ::"+LocalDateTime.now()+"</h1>");
		}
		
		//add home hyperlink
		pw.println("<br> <a href='links.html'>Home</a>");
	}//doGet(-,-)
	
	@Override
	public  void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req,res);
	}//doPost(-,-)
}
