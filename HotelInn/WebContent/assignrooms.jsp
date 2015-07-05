<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="edu.unsw.comp9321.control.*, edu.unsw.comp9321.DTO.*, java.util.*, java.text.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Insert title here</title>
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
ArrayList<RoomSpecificDTO> ResultList = (ArrayList<RoomSpecificDTO>) session.getAttribute("rooms");
%>
<center>
<h2>List all available rooms and bookings</h2>
<form action = "ServletControl" method = 'POST'>

<table border = "1" style="width:1000px" align="center">
<tr>
<th>RoomID</th><th>Room Number</th><th>Type of Room</th><th>Room Condition</th><th>HotelID</th><th>Select<br></th>
</tr>
<%
for(int i = 0; i < ResultList.size(); i++){
%>
<tr>
<td align = center>
<%
out.println(ResultList.get(i).getRoomID()); 
%>
</td>
	<td align = center>
<%
	out.println(ResultList.get(i).getNumber());
%>
</td>
	<td align = center>
	<%
	out.println(ResultList.get(i).getType());
	%>
	</td>
	<td align = center>
	<%
	out.println(ResultList.get(i).getCondition());
	%>
	</td>
	<td align = center>
	<%
	out.println(ResultList.get(i).getHotelID());
	%>
	</td>
	<td  align = center>
	<%
	int selectid =ResultList.get(i).getRoomID();
	%>
    <input type="radio" name="resultList" value=<%=i%> >
    </td>
<%
	}
%>
</table>
<br><br>
   <input type="hidden" name="control" value="decideroom"/>
	<input type="submit" value="Decide"/>

</form>


</body>
</html>