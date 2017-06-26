<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Registration</title>
</head>
<body>
<form action="/Controller/doRegistration" method="post">
    login:<br/>
    <input type="text" name="login" value=""/><br/>
    password:<br/>
    <input type="password" name="password" value=""/><br/>
    firstName:<br/>
    <input type="text" name="firstName" value=""/><br/>
    lastName:<br/>
    <input type="text" name="lastName" value=""/><br/>
    Email:<br/>
    <input type="email" name="email" value=""/><br/>
    phone:<br/>
    <input type="text" name="phone" value=""/><br/>
    <input type="submit" name="doRegistration" value="зарегистрироваться"/><br/>
</form>
<c:forEach var="invalidFields" items="${getInvalidFiels}">
    <div>
        order id
            ${invalidFields.getInvalidFiels()}
    </div>
</c:forEach>
<p><a href="/Controller/welcome">Вернуться обратно!</a></p>
</body>
</html>
