package com.shivam.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetCapitalServlet extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//get PrintWriter
		PrintWriter pw=res.getWriter();
		//set response content type
		res.setContentType("text/html");
		//read form data
		int country = Integer.parseInt(req.getParameter("country"));
		//get Capital City name 
		String capitals[] = new String[] {"New Delhi","Islamabad","Wahington DC","Bejing"};
		String countries[]= new String[] {"India","Pakistan","USA","China"};
		pw.println("<h1 style='color:red;text-align:center'>Capital name of country "+countries[country]+" is :: "+capitals[country]+"</h1>");
		
		//add home hyperlink
		pw.println("<a href='page.html'>home</a>");
		
		//close stream
		pw.close();
		
		
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
	}
}
