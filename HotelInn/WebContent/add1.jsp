<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="edu.unsw.comp9321.control.*, edu.unsw.comp9321.DTO.*, java.util.*, java.text.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>New booking added successfully</title>
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
SearchResultDTO selection = (SearchResultDTO)session.getAttribute("selection");
ArrayList<BookingDTO> bookingList = (ArrayList<BookingDTO>)session.getAttribute("bookingList");
%>
<center>
<h2 style="font-family: times; color: red;">New booking added successfully!</h2><br><br>
<h3 style="font-family: times; color: blue;">You can still use previous URL and PIN to check your new booking infomation!</h3><br><br>
<table border = "1" style="width:1000px" align="center">
<tr>
<th>Hotel ID</th><th>Room Type</th><th>Number of Rooms</th><th>Unit Price</th><th>From</th><th>To</th><th>Extra Bed</th><th>TotalPrice<br></th>
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
	out.println(bookingList.get(i).getPrice());
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
	out.println(bookingList.get(i).getExtraBed());
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
<td align = center>
<%
    out.println(Math.round((selection.getToDate().getTime() - selection.getFromDate().getTime()) / 86400000D) * (selection.getPrice() + 35 * selection.getExtraBed()));
%>
</td>
</tr>
</table><br><br>
<br><br>
<a href="index.jsp"> Exit</a>
</center>
</body>
</html>