package view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Servlet1 extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		long time = System.currentTimeMillis();
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<h3>Servlet1:"+time+"</h3>");
		out.close();
	}
  //江慶庭
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);


public class Servlet1 extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		long time = System.currentTimeMillis();
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<h1>Hsiang-Yu, Kuo version 1</h1>");
		out.println("<h1>郭翔宇 版本1</h1>");
		out.println("<h3>Servlet1:" + time + "</h3>");
		out.println("<h2>Servlet2:" + time + "</h2>");
		out.close();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);

		System.out.println("trytrysee");
		System.out.println("123218787");
		//practice 3   

		System.out.println("Shuan");


	}
}
