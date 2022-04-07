<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Student Dashboard</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<h2>Search Book</h2>
	<form action="bookSearch.jsp" method="post">
		Book Name : <input type="text" name="bookName" /> <input
			type="submit" value="Search" />
	</form>
	<h2>All Reports</h2>
	<a href="allIssuedBook.jsp">All issued Books</a>
</body>
</html>