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
    <input type="text" roleName="login" value=""/><br/>
    password:<br/>
    <input type="password" roleName="password" value=""/><br/>
    firstName:<br/>
    <input type="text" roleName="firstName" value=""/><br/>
    lastName:<br/>
    <input type="text" roleName="lastName" value=""/><br/>
    Email:<br/>
    <input type="email" roleName="email" value=""/><br/>
    phone:<br/>
    <input type="text" roleName="phone" value=""/><br/>
    <input type="submit" roleName="doRegistration" value="зарегистрироваться"/><br/>
</form>
<p><a href="/Controller/welcome">Вернуться обратно!</a></p>
</body>
</html>
