package com.shivam.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LcTestServlet extends HttpServlet {
	static {
		System.out.println("LcTestServlet :: static block");
	}
	
	public LcTestServlet() {
		System.out.println("LcTestServlet :: 0-Param constructor");
		
	}
	
	@Override
	public void init() throws ServletException {
		System.out.println("LcTestServlet :: Init(ServletConfig cg)");
	}
	
	@Override
	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("LcTestServlet :: service(req,res");
		//get printWriter
		PrintWriter pw = res.getWriter();
		//set response content type
		res.setContentType("text/html");
		//write message to response object using PrintWriter
		pw.println("<h1 style='color:red;text-align:center'> Date and Time::"+new java.util.Date()+"</h1>");
		//close stream
		pw.close();
	}
	
	@Override
	public void destroy() {
		System.out.println("LcTestServlet :: destroy()");
	}
}//class
