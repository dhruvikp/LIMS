package com.simplilearn.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.simplilearn.util.DBConnection;

/**
 * Servlet implementation class Servlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.getRequestDispatcher("login.html").include(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		try {
			DBConnection dbCon = new DBConnection();
			Connection con = dbCon.getConnection();
			PreparedStatement preparedStmt = con
					.prepareStatement("select * from user where user_name=? and password=?");
			preparedStmt.setString(1, userName);
			preparedStmt.setString(2, password);

			ResultSet rs = preparedStmt.executeQuery();

			if (rs.next()) {
				response.sendRedirect("dashboard");
				HttpSession session = request.getSession();
				session.setAttribute("username", userName);
			}else {
				response.sendRedirect("login");
			}
		} catch (Exception e) {
				e.printStackTrace();
		}
	}
}
