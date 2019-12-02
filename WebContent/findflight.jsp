<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Find a Flight</title>
</head>
<body>
<h3>Find a Flight Page</h3>
<h5>Please enter flight ID</h5>
<form action="${pageContext.request.contextPath}/Servlet">
	<input type="hidden" name="option" value="${param.option}" />
	Flight ID:<input type="text" name="flightId" value="" />
	<input type="submit" value="Find a Flight"/>
</form>

<c:if test='${param.flightId != null}'>
	<sql:setDataSource var="ds" dataSource="jdbc/flights" />
	<sql:query dataSource="${ds}" sql="SELECT * FROM flight WHERE id=?" var="results">
		<sql:param>${param.flightId}</sql:param>
	</sql:query>
	
		<c:if test="${results.rows[0] != null}">
			<c:set var="flights" scope="page" value="${results.rows[0]}" ></c:set>
				<p>Flight id: ${flights.id}, Flight name: ${flights.flight_name}, 
					Origin: ${flights.origin}, Destination: ${flights.destination}, 
					Airport: ${flights.airport}, Airline: ${flights.airline}</p>
			</c:if>

		<c:if test="${results.rows[0] == null}">
		<p>No available flights with the given flight ID ${param.flightId}</p>
		</c:if>
</c:if>

<form action="${pageContext.request.contextPath}/Servlet">
	<input type="hidden" name="option" value="home" />
	<p><input type="submit" value="HOME" /></p>
</form>
</body>
</html>