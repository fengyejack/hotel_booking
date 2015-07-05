<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Too late to view your booking!</title>
</head>
<body>
<center>
<% session.invalidate(); %>
<h2 style="font-family: times; color: red;">TOO LATE TO VIEW YOUR BOOKING!</h2>
<h2 style="font-family: times; color: blue;">Please make sure you are visiting your booking 48 hours before your checkin date.</h2>
<a href="index.jsp">BACK TO HOMEPAGE</a>
</center>
</body>
</html>