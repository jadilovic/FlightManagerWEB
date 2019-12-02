<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Exit Page</title>
</head>
<body>
<h3>Exit Application Page</h3>
<h5>Please select to return to the home page</h5>
<form action="${pageContext.request.contextPath}/Servlet" method="get">
	<input type="hidden" name="option" value="home" />
	<p><input type="submit" value="HOME" /></p>
</form>
</body>
</html>