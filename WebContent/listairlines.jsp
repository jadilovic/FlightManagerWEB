<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>List Airlines</title>
</head>
<body>
<h3>List Airlines Page</h3>
<h5>List of airlines</h5>
<sql:setDataSource var="ds" dataSource="jdbc/flights" />
<sql:query var="results" dataSource="${ds}" sql="SELECT * FROM airline" ></sql:query>
<c:forEach var="airline" items="${results.rows}" >
	<p>${airline.name}</p>
</c:forEach>
<form action="/FlightManager/Servlet" method="get">
	<input type="hidden" name="option" value="home" />
	<p><input type="submit" value="HOME" /></p>
</form>
</body>
</html>