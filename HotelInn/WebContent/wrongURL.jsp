<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Wrong URL/PIN</title>
</head>
<body>
<center>
<% session.invalidate(); %>
<h2 style="font-family: times; color: red;">WRONG URL/PIN!</h2>
<h2 style="font-family: times; color: blue;">Please make sure you are using the correct URL/PIN we sent to you via Email.</h2>
<a href="index.jsp">BACK TO HOMEPAGE</a>
</center>
</body>
</html>