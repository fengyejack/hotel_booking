<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Booking Deleted</title>
</head>
<body>
<center><br><br>
<h2 style="font-family: times; color: red;">Your booking is deleted successfully!</h2><br>
<h4>
<% session.invalidate(); %>
<a href="index.jsp"> Back to HomePage</a>
</h4>
</center>
</body>
</html>