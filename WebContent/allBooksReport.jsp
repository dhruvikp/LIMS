<%@page import="java.util.List"%>
<%@page import="com.simplilearn.entity.Book"%>
<%@page import="com.simplilearn.dao.BookDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core"%>
<style>
table, th, td {
	border: 1px solid black;
}
</style>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<%
		List<Book> books = BookDAO.listBooks();
		request.setAttribute("books", books);
	%>
	<h2>All Books detailed information</h2>
	
	<table style="border: thick;">
		<tr>
			<th>Book Id</th>
			<th>Publish Date</th>
			<th>Authers</th>
			<th>Issued By</th>
		</tr>
		<core:forEach items="${books}" var="book">
			<tr>
				<td>${book.getBookId()}</td>
				<td>${book.getBookName()}</td>
				<td>${book.getAuthorNames()}</td>
				<td>${book.getIssueBy()}</td>
			</tr>
		</core:forEach>
	</table>

</body>
</html>