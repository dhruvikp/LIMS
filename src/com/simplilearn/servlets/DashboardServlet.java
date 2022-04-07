package com.simplilearn.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class DashboardServlet
 */
@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DashboardServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");

		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("<h1 align=\"center\">Library Management System</h1>");

		out.println("<p align=center><a href=logout> Logout </a></p>");
		out.println("<p> Hello, " + username + "</p>");
		
		out.println("<p>");
		out.println("<nav>");
		out.println("<a href=student>Manage Student</a><br/>");
		out.println("<a href=book>Manage Book</a><br/>");
		out.println("<h2>Reports</h2>");
		out.println("<a href=allBooksReport.jsp>All Books Report</a><br/>");
		out.println("<a href=allBooksReport.jsp>All Books Report</a><br/>");
		out.println("<a href=allBooksReport.jsp>All Books Report</a><br/>");
		
		out.println("</body></html>");
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
