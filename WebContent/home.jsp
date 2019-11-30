<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home Page</title>
</head>
<body>
<h3>Welcome to Flight Manager Application</h3>
<h5>Please Choose From Options Offered</h5>

<form action="/FlightManager/Servlet" method="get" >
	<input type="submit" name="option" value="Create Airport" />
	<input type="submit" name="option" value="Create Airline" />
	<input type="submit" name="option" value="Create Flight" /><br/>
	<br/>
	<input type="submit" name="option" value="List Airports" />
	<input type="submit" name="option" value="List Airlines" />
	<input type="submit" name="option" value="List Flights" /><br/>
	<br/>
	<input type="submit" name="option" value="Find a Flight" />
	<input type="submit" name="option" value="Book a Seat" />
	<input type="submit" name="option" value="Exit Application" /><br/>
	<br/>
</form>

<c:if test="${param.message != null}">
<p><%= request.getAttribute("message") %></p>

</c:if>

</body>
</html>