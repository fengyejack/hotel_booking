<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="control">
<legend> Available rooms</legend>
<table>
   <tr>
   		<th>
   			ID
   		</th>
   		<th>
   			Checkbox
   		</th>
   	</tr>
   <c:forEach var="room" items="${rooms}">
   	<tr>
   		<td>
   			${room.ID}
   		</td>
   		<td> 
		<input type="checkbox" name="checkbox" value="${room.ID}" /> 
		</td> 
   	</tr>			
   	</c:forEach>
   </table>
      <input type="hidden" name="action" value="decideidleroom"/>
	<input type="submit" value="Decide idle room"/>
	</form>
	
</body>
</html>