<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Thank you</title>
</head>
<body>
<center>
<h1 style="font-family: verdana; color: red;"> Thanks for your booking!</h1><br><br><br>
<% session.invalidate(); %>
<a href="index.jsp"> Back to HomePage</a>
</center>
</body>
</html>