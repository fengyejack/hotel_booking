<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="edu.unsw.comp9321.control.*, edu.unsw.comp9321.DTO.*, java.util.*, java.text.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search Result</title>
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
ArrayList<SearchResultDTO> ResultList = (ArrayList<SearchResultDTO>) session.getAttribute("resultList");
%>
<center>
<h2>Select your favorite option</h2>

<form action = "ServletControl" method = 'POST'>
<table border = "1" style="width:1000px" align="center">
<tr>
<th>Hotel ID</th><th>Room Type</th><th>Number of Rooms</th><th>Unit Price</th><th>From</th><th>To</th><th>Extra Bed</th><th>Select<br></th>
</tr>
<%
for(int i = 0; i < ResultList.size(); i++){
%>
<tr>
<td align = center>
<%
out.println(ResultList.get(i).getHotelID());
%>
</td>
	<td align = center>
<%
	out.println(ResultList.get(i).getRoomType());
%>
</td>
	<td align = center>
	<%
	out.println(ResultList.get(i).getQuantity());
	%>
	</td>
	<td align = center>
	<%
	out.println(ResultList.get(i).getPrice());
	%>
	</td>
	<td align = center>
	<%
	out.println(fmt.format(ResultList.get(i).getFromDate()));
	%>
	</td>
	<td align = center>
	<%
	out.println(fmt.format(ResultList.get(i).getToDate()));
	%>
	</td>
	<td align = center>
	<%
	if(ResultList.get(i).getRoomType() != "SINGLE"){
	%>
	<input type="radio" name="extraBed" value=<%=i%> >
	<%
	}
	%>
	</td>
    <td  align = center>
    <input type="radio" name="resultListIndex" value=<%=i%> >
    </td>
<%
	}
%>
</table>
<br><br>
<input type='submit' name ='action' value='Confirm'>
<input type='hidden' name='control' value='confirm'>
</form>
<br><br>
<a href="index.jsp"> Back to HomePage</a>
</center>
</body>
</html>