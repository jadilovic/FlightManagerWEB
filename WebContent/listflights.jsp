<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>List Flights</title>
</head>
<body>
<h3>List Flights Page</h3>
<h5>List of flights</h5>
<sql:setDataSource var="ds" dataSource="jdbc/flights" />
<sql:query dataSource="${ds}" var="results" sql="SELECT * FROM flight" ></sql:query>
<c:forEach var="flights" items="${results.rows}" >
	<p>Flight id: ${flights.id}, Flight name: ${flights.flight_name}, 
	Origin: ${flights.origin}, Destination: ${flights.destination}, 
	Airport: ${flights.airport}, Airline: ${flights.airline}</p>
</c:forEach>
<form action="/FlightManager/Servlet" method="get">
	<input type="hidden" name="option" value="home" />
	<p><input type="submit" value="HOME" /></p>
</form>
</body>
</html>