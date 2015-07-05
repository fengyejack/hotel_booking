<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="edu.unsw.comp9321.control.*, edu.unsw.comp9321.DTO.*, java.util.*, java.text.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Welcome to HotelInn</title>
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
</head>
<body>
<h2>Please select your favorite accommodation</h2>
<form action='ServletControl' method = 'POST'>
<fieldset style="width:500px">
<legend>Staying In</legend>
<select name="city">
				<option value="Sydney">Sydney</option>
				<option value="Brisbane">Brisbane</option>
				<option value="Melbourne">Melbourne</option>
				<option value="Adelaide">Adelaide</option>
				<option value="Hobart">Hobart</option>
			</select><br>
<legend>Check-in Date</legend>
<input type="text" id="datepickercheckin" name="checkin">
<legend><br>Check-out Date</legend>
<input type="text" id="datepickercheckout" name ="checkout">
<legend><br>Number of rooms </legend>
<input type = "text" name = "quantity" size = "20">
<legend><br>Maximum price per room per night </legend>
<input type = "text" name = "price" size = "20">
</fieldset>
<br><br>
<input type='hidden' name='control' value='search'>
<input type='submit' value='Search'>
</form><br>
</body>
</html>