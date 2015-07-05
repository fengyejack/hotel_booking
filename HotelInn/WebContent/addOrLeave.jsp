<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="edu.unsw.comp9321.control.*, edu.unsw.comp9321.DTO.*, java.util.*, java.text.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Confirmation</title>
<style>
table, th, td
{
border-collapse:collapse;
border:1px solid black;
}
th, td
{
padding:5px;
}
</style>
<script>
function validateForm()
{
    if(document.frm.username.value=="")
    {
      alert("User Name should be left blank");
      document.frm.username.focus();
      return false;
    }
    else if(document.frm.pwd.value=="")
    {
      alert("Password should be left blank");
      document.frm.pwd.focus();
      return false;
    }
}
</script>
</head><br><br>
<body>
<% 
DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
SearchResultDTO selection = (SearchResultDTO)session.getAttribute("selection");
BookingDTO booking =(BookingDTO)session.getAttribute("booking");
%>
<center>
<h2>Update your booking</h2>

<form action = "ServletControl" method = 'POST'>
<table border = "1" style="width:1000px" align="center">
<tr>
<th>Hotel ID</th><th>Room Type</th><th>Number of Rooms</th><th>Unit Price</th><th>From</th><th>To</th><th>Extra Bed</th><th>TotalPrice<br></th>
</tr>
<tr>
<td align = center>
<%
	out.println(selection.getHotelID());
%>
</td>
<td align = center>
<%
	out.println(selection.getRoomType());
%>
</td>
<td align = center>
	<%
	out.println(selection.getQuantity());
	%>
</td>
<td align = center>
	<%
	out.println(selection.getPrice());
	%>
</td>
<td align = center>
	<%
	out.println(fmt.format(selection.getFromDate()));
	%>
</td>
<td align = center>
	<%
	out.println(fmt.format(selection.getToDate()));
	%>
</td>
<td align = center>
	<%
	out.println(selection.getExtraBed());
	%>
</td>
<td  align = center>
    <%
    out.println(Math.round((selection.getToDate().getTime() - selection.getFromDate().getTime()) / 86400000D) * (selection.getPrice() + 35 * selection.getExtraBed()));
    %>
</td>
<tr>
<td align = center>
<%
	out.println(booking.getHotelID());
%>
</td>
<td align = center>
<%
	out.println(booking.getType());
%>
</td>
<td align = center>
	<%
	out.println(booking.getQuantity());
	%>
</td>
<td align = center>
	<%
	out.println(Math.round(booking.getPrice() / ((selection.getToDate().getTime() - selection.getFromDate().getTime()) / 86400000D)) - 35 * booking.getExtraBed());
	%>
</td>
<td align = center>
	<%
	out.println(fmt.format(booking.getCheckin()));
	%>
</td>
<td align = center>
	<%
	out.println(fmt.format(booking.getCheckout()));
	%>
</td>
<td align = center>
	<%
	out.println(booking.getExtraBed());
	%>
</td>
<td  align = center>
    <%
    out.println(booking.getPrice());
    %>
</td>
</table>
<input type='submit' name ='action' value='Update my booking'>
<input type='hidden' name='control' value='add'>
</form>
<br><br>
<a href="end.jsp"> EXIT</a>
</center>
</body>
</html>