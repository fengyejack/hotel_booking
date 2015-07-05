<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Please enter your PIN</title>
</head>
<body>
<center>
<form action='ServletControl' method = 'POST'>
<h3 style="font-family: verdana; color: red;"> Please enter your PIN here:</h3>
<input type = "text" name = "price" size = "20"><br><br>
<input type='hidden' name='control' value='validate'>
<input type='submit' value='submit'></form><br>
</center>
</body>
</html>