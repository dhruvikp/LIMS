package com.simplilearn.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.simplilearn.entity.Student;
import com.simplilearn.util.HibernateUtil;

/**
 * Servlet implementation class StudentServlet
 */
@WebServlet("/student")
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StudentServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("<form method=post action=student>");
		out.println("<fieldset> <legend> Register Student </legend>");
		out.println("Student Name:<input type=text name=studentname id=studentname><br/>");
		out.println("Student Password:<input type=password name=studentpassword id=studentpassword><br/>");
		out.println("Student EmailId:<input type=text name=studentemailid id=studentemailid><br/><br/>");
		out.println("<input type=submit value=register />");
		out.println("</fieldset></form>");
		out.println("<hr style=\"width:50%;text-align:left;margin-left:0\">");

		out.println("<h2>All Student list</h2>");
		SessionFactory sf = HibernateUtil.buildSessionFactory();
		Session session = sf.openSession();

		List<Student> students = session.createQuery("select s from Student s where s.userType='student'").list();
		if (students != null && students.size() > 0) {

			out.println("<style>table, th, td { border: 1px solid black;}</style>");

			out.println("<table style=\"width:100%\">");
			out.println("<tr>");
			out.println("<th>Student ID</th>");
			out.println("<th>Student Name</th>");
			out.println("<th>Student EmailId</th>");
			out.println("<th>Action</th>");
			out.println("</tr>");

			for (Student student : students) {
				out.println("<tr>");
				out.println("<td>" + student.getStudentId() + "</td>");
				out.println("<td>" + student.getStudentName() + "</td>");
				out.println("<td>" + student.getStudentEmailId() + "</td>");
				out.println("<td>");
				out.println("<form method=post action=student> ");
				out.println(" <input type=hidden name=action value=delete />");
				out.println("<input type=hidden name=studentId value=" + student.getStudentId() + " />");
				out.println("<input type=submit value='Delete' />");
				out.println("</form>");
				out.println("</tr>");
			}
		} else {
			out.println("<p>No Students found, Please register user.</p>");
		}
		out.println("</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if ("delete".equals(request.getParameter("action"))) {
			doDelete(request, response);
			return;
		}
		
		String studentName = request.getParameter("studentname");
		String studentPwd = request.getParameter("studentpassword");
		String studentEmailId = request.getParameter("studentemailid");

		if (studentName == null || studentPwd == null || studentEmailId == null) {
			throw new IllegalArgumentException("Please fill all information");
		}

		Student student = new Student();
		student.setStudentName(studentName);
		student.setStudentEmailId(studentEmailId);
		student.setStudentPwd(studentPwd);
		student.setUserType("student");

		SessionFactory sf = HibernateUtil.buildSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();

		session.save(student);
		tx.commit();
		session.close();

		response.sendRedirect("student");
	}

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("Do Delete invoked");
		Integer studentId = Integer.valueOf(request.getParameter("studentId"));
		SessionFactory sf = HibernateUtil.buildSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		Student s = new Student();
		s.setStudentId(studentId);
		session.delete(s);
		tx.commit();
		session.close();
		try {
			response.sendRedirect("student");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
