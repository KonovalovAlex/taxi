<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<form action="/Controller/actionLogin" method="post">
    login:<br/>
    <input type="text" roleName="login" value=""/><br/>
    password:<br/>
    <input type="password" roleName="password" value=""/><br/>
    <input type="submit" value="войти"/><br/>
</form>
<p><a href="/Controller/welcome">Вернуться обратно!</a></p>
</body>
</html>


