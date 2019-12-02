<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>List Airports</title>
</head>
<body>
<h3>List Airports Page</h3>
<h5>List of airports</h5>
<sql:setDataSource var="ds" dataSource="jdbc/flights" />
<sql:query dataSource="${ds}" sql="SELECT * FROM airport" var="results"></sql:query>
<c:forEach var="airport" items="${results.rows}" >
	<p>${airport.name}, ${airport.city}</p>
</c:forEach>
<form action="/FlightManager/Servlet" method="get">
	<input type="hidden" name="option" value="home" />
	<p></p><input type="submit" value="HOME" />
</form>
</body>
</html>