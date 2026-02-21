
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registration Page</title>
<style type="text/css">
.error {
	color: red;
	position:fixed;
	text-align: left;
	margin-left: 30px;
}
</style>
</head>
<body>
<h1 align="center"> Love Calculator</h1>

<form:form  action="registration-success" method="GET" modelAttribute="userReg">
<div align="center">
<label> Name : </label>
<form:input path="name"/>
<form:errors path="name" cssClass="error"/>
<br/>

<label> User Name : </label>
<form:input path="userName"/>
<form:errors path="userName" cssClass="error"/>
<br/>

<label> Password : </label>
<form:password path="password"/>
<br/>

<label> Country : </label>
<form:select path="countryName">
<form:option value="Ind" label="India"></form:option>
<form:option value="Aus" label="Australia"></form:option>
<form:option value="Pak" label="Pakistan"></form:option>
<form:option value="Den" label="Denmark"></form:option>
</form:select> 
<br/>

<label> Hobbies : </label>
Cricket :<form:checkbox path="hobbies" value="cricket"/>
Reading :<form:checkbox path="hobbies" value="reading"/>
Travel:<form:checkbox path="hobbies" value="travel"/>
Programming :<form:checkbox path="hobbies" value="programming"/>
<br/>

<label> Gender :</label>
Male<form:radiobutton path="gender" value="male"/>
Female<form:radiobutton path="gender" value="female"/>
<br/>

<label> Age :</label>
<form:input path="age"/>
<form:errors path="age" cssClass="error"/>
<br/>
</div>

<div align="center">
<h3>Communication</h3>

<label>Email</label>
<form:input path="communicationDTO.email"/>
<form:errors path="communicationDTO.email" cssClass="error"/>
<br/>

<label>Phone</label>
<form:input path="communicationDTO.phone"/>
<form:errors path="communicationDTO.phone" cssClass="error"/>

</div>
<div align="center">
<input type="submit" value="register">


</div>
</form:form>


</body>
</html>