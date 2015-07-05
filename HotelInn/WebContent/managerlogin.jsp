<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Manager Login</title>
</head>
<body>
<h2>Manager Login</h2>
<form action='ServletControl' method = 'POST'>
<legend><br>Username</legend>
<input type = "text" name = "username" size = "20">
<legend><br>Password</legend>
<input type = "text" name = "password" size = "20">
<br><br>
<input type='hidden' name='control' value='loginin'>
<input type='submit' value='Login in'></form><br>

</body>
</html>