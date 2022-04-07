package com.simplilearn.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.simplilearn.entity.Author;
import com.simplilearn.entity.Book;
import com.simplilearn.util.HibernateUtil;

/**
 * Servlet implementation class BookServlet
 */
@WebServlet("/book")
public class BookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BookServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("<form method=post action=book>");
		out.println("<fieldset> <legend> Register Book </legend>");
		out.println("Book Name:<input type=text name=bookname id=bookname><br/>");
		out.println("Publication Date:<input type=text name=pubdate id=pubdate><br/>");
		out.println("Author Name:<input type=text name=authName id=authName><br/><br/>");
		out.println("<input type=submit value=Register Book />");
		out.println("</fieldset></form>");
		out.println("<hr style=\"width:50%;text-align:left;margin-left:0\">");

		out.println("<h2>All Book list</h2>");
		SessionFactory sf = HibernateUtil.buildSessionFactory();
		Session session = sf.openSession();

		List<Book> books = session.createQuery("from Book").list();
		if (books != null && books.size() > 0) {

			out.println("<style>table, th, td { border: 1px solid black;}</style>");

			out.println("<table style=\"width:100%\">");
			out.println("<tr>");
			out.println("<th>Book ID</th>");
			out.println("<th>Book Name</th>");
			out.println("<th>Publication Date</th>");
			out.println("<th>Author Name</th>");
			out.println("<th>Action</th>");
			out.println("</tr>");

			for (Book book : books) {
				out.println("<tr>");
				out.println("<td>" + book.getBookId() + "</td>");
				out.println("<td>" + book.getBookName() + "</td>");
				out.println("<td>" + book.getPublishDate() + "</td>");
				out.println("<td>" + book.getAuthorNames() + "</td>");

				out.println("<td>");
				out.println("<form method=post action=book> ");
				out.println(" <input type=hidden name=action value=delete />");
				out.println("<input type=hidden name=bookId value=" + book.getBookId() + " />");
				out.println("<input type=submit value='Delete' />");
				out.println("</form>");
				out.println("</tr>");
			}
		} else {
			out.println("<p>No Books found, Please register books.</p>");
		}
		out.println("</body></html>");
		session.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if ("delete".equals(request.getParameter("action"))) {
			doDelete(request, response);
			return;
		}

		String bookName = request.getParameter("bookname");
		String bookPubDate = request.getParameter("pubdate");
		String bookAuthorName = request.getParameter("authName");

		if (bookName == null || bookPubDate == null || bookAuthorName == null) {
			throw new IllegalArgumentException("Please fill all information");
		}

		Book book = populateBook(bookName, bookPubDate, bookAuthorName);
		Session session = HibernateUtil.buildSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		session.save(book);
		tx.commit();
		session.close();

		response.sendRedirect("book");
	}

	private Book populateBook(String bookName, String bookPubDate, String bookAuthorName) {
		Book book = new Book();
		book.setBookName(bookName);
		Date pubDate;
		try {
			pubDate = new SimpleDateFormat("dd/MM/yyyy").parse(bookPubDate);
			book.setPublishDate(pubDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String[] bookAuthors = bookAuthorName.split(",");

		Set<Author> authors = getAuthors(book, bookAuthors);
		book.setAuthors(authors);
		return book;
	}

	private Set<Author> getAuthors(Book book, String[] bookAuthors) {
		Session session1 = HibernateUtil.buildSessionFactory().openSession();
		Set<Author> authors = book.getAuthors();
		for (String bookAuthor : bookAuthors) {
			List<Author> authorsDB = session1.createQuery("from Author where authorName = '" + bookAuthor + "'")
					.list();
			Author autDB = null;

			if (authorsDB != null && authorsDB.size() > 0) {
				autDB = authorsDB.get(0);
			} else {
				autDB = new Author();
				autDB.setAuthorName(bookAuthor);
			}
			authors.add(autDB);
		}
		session1.close();
		return authors;
	}

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) {
		Integer bookId = Integer.valueOf(request.getParameter("bookId"));
		SessionFactory sf = HibernateUtil.buildSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();

		List<Book> books = session.createQuery("Select b from Book b where b.bookId=" + bookId).list();
		Book book = books.get(0);

		book.getAuthors().forEach(author -> {
			author.getBooks().remove(book);
		});
		session.delete(book);
		tx.commit();
		session.close();
		try {
			response.sendRedirect("book");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
