<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Book a Seat</title>
</head>
<body>
<h3>Book a Seat Page</h3>
<h5>Please enter Flight ID to book a seat on a specific flight</h5>
<form action="${pageContext.request.contextPath}/Servlet">
	<input type="hidden" name="option" value="${param.option}" />
	Flight ID:<input type="text" name="flightId" value="" />
	<input type="submit" value="List Seats on a Flight"/>
</form>

<c:if test='${param.flightId != null}'>
	<sql:setDataSource var="ds" dataSource="jdbc/flights" />
	<sql:query dataSource="${ds}" sql="SELECT * FROM seats WHERE flightID=?" var="results">
		<sql:param>${param.flightId}</sql:param>
	</sql:query>
	<p>Seat ID	Row Letter	Seat Number	Available	Flight ID
<c:forEach var="seat" items="${results.rows}" >
	<p>${seat.seatID}, ${seat.rowLetter}, ${seat.seat_number}, ${seat.available}, ${seat.flightID}</p>
</c:forEach>
</c:if>

<c:if test='${param.seatId != null}'>
	<sql:setDataSource var="ds" dataSource="jdbc/flights" />
	<sql:update dataSource="${ds}" sql="UPDATE seats SET available=? WHERE seatID=?" var="results">
		<sql:param>0</sql:param>
		<sql:param>${param.seatId}</sql:param>
	</sql:update>
	<p>Seat ID: ${param.seatId} was booked</p>
</c:if>

<form action="${pageContext.request.contextPath}/Servlet">
	<input type="hidden" name="option" value="${param.option}" />
	Seat ID:<input type="text" name="seatId" value="" />
	<input type="submit" value="Book a Seats"/>
</form>

<form action="${pageContext.request.contextPath}/Servlet">
	<input type="hidden" name="option" value="home" />
	<p><input type="submit" value="HOME" /></p>
</form>
</body>
</html>