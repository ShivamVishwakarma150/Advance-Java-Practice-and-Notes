package com.shivam.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
		String tage=req.getParameter("page");
		String addrs= req.getParameter("paddrs");
		String csvStatus = req.getParameter("vflag");
		int age=0;
		
		if(csvStatus.equalsIgnoreCase("no")) {
			// form validation logics (server side)
			System.out.println("Server side form validations");
			List<String> errorsList = new ArrayList<>();
			if(name==null || name.length()==0 || name.equalsIgnoreCase("")) // required rule on name
				errorsList.add("Person name is required");
			else if(addrs==null || addrs.length()==0||addrs.equalsIgnoreCase(""))//required rule on address
				errorsList.add("Person address is required");
			else if(addrs.length()<10)// min length rule in address
				errorsList.add("Person address must have min of 10 characters");
			
			
			if(tage==null || tage.length()==0 || tage.equalsIgnoreCase(""))// required rule on age
				errorsList.add("Person age is required");
			
			else {
				try {
					age=Integer.parseInt(tage);
					if(age<0 || age>125) {
						errorsList.add("Person age must be in the range on 1 to 125");// age range rule
						
					}
				}catch(NumberFormatException e) {
					errorsList.add("Person age must be numeric value"); // age must be numeric value
				}
			}//else
			
			if(errorsList.size()>0) {
				pw.println("<p style='color:tomato;text-align:center'><ul>");
				for(String errMsg:errorsList) {
					pw.println("<li style='color:red;font-weight:bold;font-size:20px'>"+errMsg+"</li>");
				}
				pw.println("</ul></p>");
				return; //blocks control going further
			}
		}//if
		
		else {
			age=Integer.parseInt(tage);
		}
		
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
