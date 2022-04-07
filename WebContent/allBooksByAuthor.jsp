<%@page import="com.simplilearn.entity.Book"%>
<%@page import="java.util.Set"%>
<%@page import="com.simplilearn.entity.Author"%>
<%@page import="java.util.List"%>
<%@page import="com.simplilearn.dao.AuthorDAO"%>
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
	<%
		List<Author> authors = AuthorDAO.listAuthors();

		if (authors != null && authors.size() > 0) {
			for (Author author : authors) {
				out.println("<h3>Author:" + author.getAuthorName() + "</h3>");
				out.println("<table>");
				out.println("<tr><th>Book Id</th><th>Publish Date</th><th>Issued By</th></tr>");
				
				Set<Book> books = author.getBooks();
				for(Book book: books) {
					String issuedBy = book.getIssueBy();
					if(issuedBy== null) {
						issuedBy="";
					}
					out.println("<tr>");
					out.println("<td>"+book.getBookId()+"</td>");
					out.println("<td>"+book.getPublishDate()+"</td>");
					out.println("<td>"+issuedBy+"</td>");
					out.println("</tr>");
				}
				out.println("</table>");
			}

		} else {
			out.println("No Books Found");
		}
	%>
</body>
</html>