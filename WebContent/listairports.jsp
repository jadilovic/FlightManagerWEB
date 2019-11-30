<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>List Airports</title>
</head>
<body>
<h3>List Airports Page</h3>
<h5>Please click SUBMIT to list airports</h5>
<form action="/FlightManager/Servlet" method="post">
	<input type="hidden" name="message" value="" />
	<input type="hidden" name="option" value="${param.option}" />
	<p></p><input type="submit" value="SUBMIT" />
</form>
</body>
</html>