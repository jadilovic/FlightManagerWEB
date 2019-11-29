<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create Airport</title>
</head>
<body>
<h3>Create Airport Page</h3>
<h5>Please enter data to create an airport</h5>
<form action="/FlightManager/Servlet" method="post">
	<input type="hidden" name="message" value="" />
	<input type="hidden" name="option" value="${param.option}" />
	<p>Airport name:</p><input type="text" name="airportName" /><br/>
	<p>Airport city:</p><input type="text" name="airportCity" /><br/>
	<p></p><input type="submit" value="SUBMIT" />
</form>
</body>
</html>