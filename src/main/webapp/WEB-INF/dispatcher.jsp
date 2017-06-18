<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Dispatcher</title>
</head>
<body>
<form action="/Controller/showWaitingOrders">
<table>
<input type="submit" name="showWaitingOrders" value="показать ожидающие заказы"/><br/>
<c:forEach var="entity" items="${orders}">
    ${entity}
    <tr>
        <td>${entity}</td>
        <td>${entity}</td>
    </tr>
</c:forEach>
</table>
</form>
</body>
</html>
