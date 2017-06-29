<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<fmt:setLocale value="${locale}"/>
<fmt:bundle basename="i18n.messages">
    <fmt:message key="message.accept" var="accept"/>
    <fmt:message key="message.back.to.welcome" var="backToWelcome"/>
    <fmt:message key="message.reject" var="reject"/>
    <fmt:message key="message.order.id" var="orderId"/>
    <fmt:message key="message.user.id" var="userId"/>
    <fmt:message key="message.street" var="street"/>
    <fmt:message key="message.number.of.house" var="numberOfHouse"/>
    <fmt:message key="message.number.of.apartment" var="numberOfApartment"/>
    <fmt:message key="message.time.format" var="time"/>
</fmt:bundle>
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
            <input type="submit" value="${accept}">
        </p></form>
    <form action="/Controller/rejectOrder" method="get">
        <p><input type="hidden" name="rejectOrder" value="${entity.getId()}">
            <input type="submit" value="${reject}">
        </p></form>
    <div>
        <div>
            ${orderId}
                ${entity.getId()}
        </div>
        <div>
            ${userId}
                ${entity.getFkUser()}
        </div>
        <div>
            ${street}
                ${entity.getStreet()}
        </div>
        <div>
            ${numberOfHouse}
                ${entity.getNumberOfHouse()}
        </div>
        <div>
            ${numberOfApartment}
                ${entity.getNumberOfApartment()}
        </div>
        <div>
            ${time}
                ${entity.getTime()}
        </div>
        </c:forEach>
</table>
<p><a href="/Controller/welcome">${backToWelcome}</a></p>
</body>
</html>

