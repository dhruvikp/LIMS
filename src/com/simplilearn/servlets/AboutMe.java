package com.simplilearn.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AboutMe
 */
@WebServlet("/aboutMe")
public class AboutMe extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AboutMe() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pattern = "MM-dd-yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String date = simpleDateFormat.format(new Date());

		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("<h1 align=\"center\">Library Management System</h1>");

		out.println("<h2 align=center>About Me</h2>");
		out.println("<p align=center>");
		out.print("Date:" + date);
		out.println("</p>");
		out.println(
				"<p>Children's Academy provides a range and variety of Curricular, Extra- Curricular, and Co-Curricular activities which help students to reach their goals. We prepare our students to face challenges through international standards and yet retain valuable lessons and ideologies from our culture. Our core philosophy is to recognize and harness every child’s true potential and provide them with the best\r\n"
						+ "opportunity in order to mold their talent into something great.</p>");
		out.println("</body></html>");

		out.println("<p>Legal | Privacy© 2022  Children's Academy school.All Rights Reserved.</p>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
