<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Thank you for booking</title>
</head>
<body>
<h1 style="font-family: verdana; color: red;"> Thanks for your booking!</h1>
<h2>Your PIN number is : ${PIN}</h2>
<h2>URL to review booking details is : ${URL}</h2>
Please remember this PIN number and use it to review your booking details via this URL within 48 hours before your check in.<br>
Your can also find this details in your registered mail box.<br>
Enjoy your life!<br><br>
<% session.invalidate(); %>
<a href="index.jsp"> Back to HomePage</a>
</body>
</html>