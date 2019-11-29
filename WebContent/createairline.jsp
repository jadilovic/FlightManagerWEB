<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create Airline</title>
</head>
<body>
<h3>Create Airline Page</h3>
<h5>Please enter data to create an airline</h5>
<form action="/FlightManager/Servlet" method="post">
	<input type="hidden" name="message" value="" />
	<input type="hidden" name="option" value="${param.option}" />
	<p>Airline name:</p><input type="text" name="airlineName" /><br/>
	<p></p><input type="submit" value="SUBMIT" />
</form>
</body>
</html>