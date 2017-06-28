<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<html>
<head>
<t:gHead>
    <title>Dispatcher</title>
</t:gHead>
</head>
<body>
<table>
    <c:forEach var="entity" items="${orders}">
    <form action="/Controller/acceptOrder" method="get">
        <p><input type="hidden" name="acceptOrder" value="${entity.getId()}">
            <input type="submit" value="принять">
        </p></form>
    <form action="/Controller/rejectOrder" method="get">
        <p><input type="hidden" name="rejectOrder" value="${entity.getId()}">
            <input type="submit" value="отклонить">
        </p></form>
    <div>
        <div>
            order id
                ${entity.getId()}
        </div>
        <div>
            user id
                ${entity.getFkUser()}
        </div>
        <div>
            Street
                ${entity.getStreet()}
        </div>
        <div>
            Number Of House
                ${entity.getNumberOfHouse()}
        </div>
        <div>
            Number Of Apartment
                ${entity.getNumberOfApartment()}
        </div>
        <div>
            Time
                ${entity.getTime()}

        </div>
        </c:forEach>
</table>
<p><a href="/Controller/welcome">Вернуться на главную!</a></p>
</body>
</html>

