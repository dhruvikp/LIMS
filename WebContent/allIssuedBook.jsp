<%@page import="com.simplilearn.entity.Book"%>
<%@page import="java.util.List"%>
<%@page import="com.simplilearn.dao.BookDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<style>
table, th, td {
	border: 1px solid black;
}
</style>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<h2>Report by Issuer Name</h2>
	<%
		String userName = "Dhruvik";
		System.out.println(userName);
		List<Book> books = BookDAO.listBooksByIssuerName(userName);
		System.out.println(books.size());
		out.println("<h3>Issuer Name:" + userName + "</h3>");
		out.println("<table>");
		out.println("<tr><th>Book Id</th><th>Publish Date</th><th>Authers</th></tr>");

		if (books != null && books.size() > 0) {
			for (Book book : books) {
				out.println("<tr>");
				out.println("<td>" + book.getBookId() + "</td>");
				out.println("<td>" + book.getPublishDate() + "</td>");
				out.println("<td>" + book.getAuthorNames() + "</td>");
				out.println("</tr>");
			}

		} else {
			out.println("No Books Found");
		}
		out.println("</table>");
	%>
</body>
</html>