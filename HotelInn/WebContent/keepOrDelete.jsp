<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Keep or Delete?</title>
</head>
<body>
<form action = "ServletControl" method = 'POST'>
<center><br><br>
<h3 style="font-family: times; color: red;">NO RESULT FOUND!</h3><br>
<h4>
You can either <a href="index.jsp"> EXIT</a> or 
<input type='submit' value='DELETE'> your booking.
<input type='hidden' name='control' value='delete'>
</h4>
</center>
</form>
</body>
</html>