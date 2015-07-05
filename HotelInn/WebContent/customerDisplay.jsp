<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="edu.unsw.comp9321.control.*, edu.unsw.comp9321.DTO.*, java.util.*, java.text.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Your Booking Info</title>
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
</head><br><br>
<body>
<% 
DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
ArrayList<BookingDTO> bookingList = (ArrayList<BookingDTO>)session.getAttribute("bookingList");
%>
<center>
<h2>Below is your booking information</h2>

<form action = "ServletControl" method = 'POST'>
<table border = "1" style="width:1000px" align="center">
<tr>
<th>Hotel ID</th><th>Room Type</th><th>Number of Rooms</th><th>Extra Bed</th><th>CheckIn</th><th>CheckOut</th><th>Total Price<br></th>
</tr>
<%
for(int i = 0; i < bookingList.size(); i++){
%>
<tr>
<td align = center>
<%
	out.println(bookingList.get(i).getHotelID());
%>
</td>
	<td align = center>
<%
	out.println(bookingList.get(i).getType());
%>
</td>
	<td align = center>
	<%
	out.println(bookingList.get(i).getQuantity());
	%>
	</td>
	<td align = center>
	<%
	out.println(bookingList.get(i).getExtraBed());
	%>
	</td>
	<td align = center>
	<%
	out.println(fmt.format(bookingList.get(i).getCheckin()));
	%>
	</td>
	<td align = center>
	<%
	out.println(fmt.format(bookingList.get(i).getCheckout()));
	%>
	</td>
	<td align = center>
	<%
	out.println(Math.round((bookingList.get(i).getCheckout().getTime() - bookingList.get(i).getCheckin().getTime()) / 86400000D) * (bookingList.get(i).getPrice() + 35 * bookingList.get(i).getExtraBed()));
	%>
	</td>
</tr>
<%
}
%>
</table>
<br><br>
<a href="welcome.jsp"> Add new booking</a>
<!-- <input type='hidden' name='control' value='add'> -->
</form>
<br><br>
</center>
</body>
</html>