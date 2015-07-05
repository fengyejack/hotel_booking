<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="edu.unsw.comp9321.control.*, edu.unsw.comp9321.DTO.*, java.util.*, java.text.*"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<link rel="stylesheet" href="/resources/demos/style.css">
<script>
$(document).ready(function(){
	$( "#datepickercheckin" ).datepicker({
		dateFormat: "yy-mm-dd",
		showOn: "button",
		minDate: 0,
		onSelect: function(selected) {
	          $("#datepickercheckout").datepicker("option","minDate", selected);
		}
	});
	$( "#datepickercheckout" ).datepicker({
		dateFormat: "yy-mm-dd",
		showOn: "button",
		minDate: 0,
		onSelect: function(selected) {
	           $("#datepickercheckin").datepicker("option","maxDate", selected);
	        }
	});
});
</script>
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
</head>
<body>
<% 
ArrayList<OccupancyDTO> ResultList = (ArrayList<OccupancyDTO>) session.getAttribute("occupancy");
%>
<center>
<h2>All hotels occupancy</h2>
<form action='ServletControl' method = 'POST'>
<table border = "1" style="width:1000px" align="center">
<tr>
<th>hotel_ID</th><th>Occupied_number<br></th><th>Available_number</th>
</tr>
<%
for(int i = 0; i < ResultList.size(); i++){
%>
<tr>
<td align = center>
<%
out.println(ResultList.get(i).gethotelID()); 
%>
</td>
	<td align = center>
<%
	out.println(ResultList.get(i).getavailablenumber());
%>
</td>
	<td align = center>
	<%
	out.println(ResultList.get(i).getoccupiednumber());
	%>
	</td>
<%
	}
%>
</table>
</center>
<h3>Set a discount fare for selected hotel</h3>
<legend>Select city</legend>
<select name="city">
				<option value="1">Sydney</option>
				<option value="2">Brisbane</option>
				<option value="3">Melbourne</option>
				<option value="4">Adelaide</option>
				<option value="5">Hobart</option>
			</select><br>
<br><legend>Select room type</legend>
<select name="roomtype">
				<option value="SINGLE">SINGLE</option>
				<option value="TWIN">TWIN</option>
				<option value="QUEEN">QUEEN</option>
				<option value="EXECUTIVE">EXECUTIVE</option>
				<option value="SUITE">SUITE</option>
			</select><br>
<br><legend>From Date</legend>
<input type="text" id="datepickercheckin" name="fromdata"><br>
<br><legend>To Date</legend>
<input type="text" id="datepickercheckout" name ="todata"><br>
<br><legend>Discount </legend>
<input type = "text" name = "fare" size = "20"><br>
<br><br>
   <input type="hidden" name="control" value="discount"/>
	<input type="submit" value="Set discount"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="index.jsp"><b>EXIT</b> </a>
</form><br>
</body>
</html>