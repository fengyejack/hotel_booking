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
ArrayList<BookingDTO> bookingList = (ArrayList<BookingDTO>)session.getAttribute("bookingList");
%>
<center>
<%
   if(bookingList == null){
%>
<h2>Input your details and submit your booking</h2>

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
<td align = center>
<%
    out.println(Math.round((selection.getToDate().getTime() - selection.getFromDate().getTime()) / 86400000D) * (selection.getPrice() + 35 * selection.getExtraBed()));
%>
</td>
</tr>
</table> 
<br><br><h3 style="font-family: times; color: red;">Please enter your payment details as below:</h3>
<h4 style="font-family: times; color: blue;">Please fill in all below blanks without these symbols: ":!?*()</h4>
<table width="40%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="30%">Surname</td>
    <td width="70%"><input type = "text" name = "surname" size = "20"></td>
  </tr>
  <tr>
    <td width="30%">Othername</td>
    <td width="70%"><input type = "text" name = "othername" size = "20"></td>
  </tr>
  <tr>
    <td width="30%">Username</td>
    <td width="70%"><input type = "text" name = "username" size = "20"></td>
  </tr>
  <tr>
    <td width="30%">Password</td>
    <td width="70%"><input type = "text" name = "password" size = "20"></td>
  </tr>
  <tr>
    <td width="30%">Email</td>
    <td width="70%"><input type = "text" name = "email" size = "30"></td>
  </tr>
  <tr>
    <td width="30%">Payment details</td>
    <td width="70%"><input type = "text" name = "payment" size = "30"></td>
  </tr>
  <tr>
    <td width="30%">Booking details</td>
    <td width="70%"><input type = "text" name = "booking" size = "30"></td>
  </tr>
</table><br><br>

<input type='submit' name ='action' value='Submit'>
<input type='hidden' name='control' value='book'>
</form>
<br><br>
<a href="index.jsp"> Back to HomePage</a>
<%
	}else{
%>
<h2>Update your booking</h2><br><br>
<form action = "ServletControl" method = 'POST'>
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
<td align = center><font color = "red"><b>
<%
	out.println(selection.getHotelID());
%>
</b></font></td>
	<td align = center><font color = "red"><b>
<%
	out.println(selection.getRoomType());
%>
</b></font></td>
<td align = center><font color = "red"><b>
<%
	out.println(selection.getQuantity());
%>
</b></font></td>
<td align = center><font color = "red"><b>
<%
	out.println(selection.getPrice());
%>
</b></font></td>
<td align = center><font color = "red"><b>
<%
	out.println(fmt.format(selection.getFromDate()));
%>
</b></font></td>
<td align = center><font color = "red"><b>
<%
	out.println(fmt.format(selection.getToDate()));
%>
</b></font></td>
<td align = center><font color = "red"><b>
<%
	out.println(selection.getExtraBed());
%>
</b></font></td>
<td align = center><font color = "red"><b>
<%
    out.println(Math.round((selection.getToDate().getTime() - selection.getFromDate().getTime()) / 86400000D) * (selection.getPrice() + 35 * selection.getExtraBed()));
%>
</b></font></td>
</tr>
</table><br><br>
<h3 style="font-family: times; color: red;">Do you wish to add new booking highlighted in red?</h3>
<input type='submit' name ='action' value='Add to your booking'>
<input type='hidden' name='control' value='add'>
</form>
<br><br>
<a href="index.jsp"> Exit</a>
<%
	}
%>
</center>
</body>
</html>