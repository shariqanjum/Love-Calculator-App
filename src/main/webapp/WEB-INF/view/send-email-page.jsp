<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1> HI ${userInfo.userName}</h1>
<h2>Send Result to your Email</h2>
<form:form action="process-email" method="GET" modelAttribute="emailDTO">	
	<label>Enter your Email</label>
	<form:input path="userEmail"/>
	<input type="submit" value="send">
	

</form:form>



</body>
</html>