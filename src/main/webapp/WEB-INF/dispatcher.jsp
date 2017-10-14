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
    <fmt:message key="message.phone" var="phone"/>
    <fmt:message key="message.back.to.dispatcher.page" var="backToDispatcher"/>
    <fmt:message key="navigation.rus" var="navigationRU"/>
    <fmt:message key="navigation.eng" var="navigationENG"/>
</fmt:bundle>
<html>
<head>
<t:gHead>
    <title>Dispatcher</title>
</t:gHead>
</head>
<body>
<form action="/Controller/changeLocale" method="post">
    <select onchange="submit()" name="locale">
        <option
                <c:if test="${locale == 'ru'}">selected</c:if> value="ru">
            ${navigationRU}
        </option>

        <option
                <c:if test="${locale == 'en'}">selected</c:if> value="en">
            ${navigationENG}
        </option>
    </select>
</form>
<table border="1" cellpadding="7" cellspacing="0">
    <tr>
        <td colspan="2" bgcolor="#D3EDF6" align="center">
    <c:forEach var="entity" items="${orders}">
       <form action="/Controller/acceptOrder" method="post">
        <p><input type="hidden" name="acceptOrder" value="${entity.getId()}">
            <input type="submit" value="${accept}">
        </p></form>
    <form action="/Controller/rejectOrder" method="post">
        <p><input type="hidden" name="rejectOrder" value="${entity.getId()}">
            <input type="submit" value="${reject}">
        </p></form>
        <div>
            ${orderId}
                ${entity.getId()}
        </div>
        <div>
                ${phone}
                ${entity.getPhone()}
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
        </c:forEach></td></tr>
</table>
<p><a href="/Controller/welcome">${backToWelcome}</a></p>
<p><a href="/Controller/dispatcher">${backToDispatcher}</a></p>
</body>
</html>

