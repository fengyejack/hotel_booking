<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>No Result Found</title>
</head>
<body>
<center>
<% session.invalidate(); %>
<h2 style="font-family: times; color: red;">NO RESULT FOUND!</h2>
<h2 style="font-family: times; color: blue;"> Please decrease the quantity or increase the price and try again.</h2>
<a href="index.jsp">BACK TO HOMEPAGE</a>
</center>
</body>
</html>