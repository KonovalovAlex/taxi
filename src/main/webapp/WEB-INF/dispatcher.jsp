<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Dispatcher</title>
</head>
<body>
<input type="submit" roleName="showWaitingOrders" value="показать ожидающие заказы"/><br/>
<c:forEach var="orders" items="${orders}"></c:forEach>
</body>
</html>
