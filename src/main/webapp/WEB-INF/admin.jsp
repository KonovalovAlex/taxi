<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Admin</title>
</head>
<body>
<table>
    <c:forEach var="entity" items="${users}">
    <form action="/Controller/deleteUser" method="get">
        <p><input type="hidden" name="deleteUser" value="${entity.getId()}">
            <input type="submit" value="удалить">
        </p></form>
    <div>
        <div>
            user id
                ${entity.getId()}
        </div>
        <div>
            Login
                ${entity.getLogin()}
        </div>
        <div>
            password
                ${entity.getPassword()}
        </div>
        <div>
            Name
                ${entity.getFirstName()}
        </div>
        <div>
            LastName
                ${entity.getLastName()}
        </div>
        <div>
            Mobile
                ${entity.getPhone()}
        </div>
        <div>
            Email
                ${entity.getEmail()}

        </div>
        </c:forEach>
</table>
</body>
</html>
