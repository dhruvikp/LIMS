<%@page import="com.simplilearn.dao.BookDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<%
		String bookId = request.getParameter("bookId");
		String userName = (String) session.getAttribute("username");
		BookDAO.issueBook(bookId, userName);
	%>
	Book successfully issued.
</body>
</html>