<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create Flight</title>
</head>
<body>
<h3>Create Flight Page</h3>
<h5>Please enter data to create an flight</h5>
<form action="/FlightManager/Servlet" method="post">
	<input type="hidden" name="message" value="" />
	<input type="hidden" name="option" value="${param.option}" />
	<p>Flight ID:</p><input type="text" name="flightId" /><br/>
	<p>Flight name:</p><input type="text" name="flightName" /><br/>
	<p>Origin city:</p><input type="text" name="originCity" /><br/>
	<p>Destination city:</p><input type="text" name="destinationCity" /><br/>
	<p>Airport name:</p><input type="text" name="airportName" /><br/>
	<p>Airline name:</p><input type="text" name="airlineName" /><br/>
	<p>Number of seats per row:</p><input type="text" name="seatsPerRow" /><br/>
	<p></p><input type="submit" value="SUBMIT" />
</form>
</body>
</html>