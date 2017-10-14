<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<fmt:setLocale value="${locale}"/>
<fmt:bundle basename="i18n.messages">
    <fmt:message key="message.back.to.welcome" var="backToWelcome"/>
    <fmt:message key="message.street" var="street"/>
    <fmt:message key="message.number.of.house" var="numberOfHouse"/>
    <fmt:message key="message.number.of.apartment" var="numberOfApartment"/>
    <fmt:message key="message.time.format" var="time"/>
    <fmt:message key="message.make.an.order" var="makeAnOrder"/>
    <fmt:message key="message.back.to.welcome" var="backToWelcome"/>
    <fmt:message key="message.back.to.client.page" var="backToClient"/>
    <fmt:message key="button.cancel.all" var="cancelAll"/>
    <fmt:message key="message.sort.of.pay" var="sortOfPay"/>
    <fmt:message key="message.orders.were.canceled" var="ordersWereCanceled"/>
    <fmt:message key="message.time.is.not.correct" var="timeIsNotCorrect"/>
    <fmt:message key="navigation.rus" var="navigationRU"/>
    <fmt:message key="navigation.eng" var="navigationENG"/>
</fmt:bundle>
<html>
<head>
    <t:gHead>
        <title>Client</title>
    </t:gHead>
</head>
<body>
${sortOfPay} = 555 tenge - town
<form action="/Controller/makeAnOrder" method="post">
    <p><input placeholder="${street}" name="street"></p>
    <p><input placeholder="${numberOfHouse}" name="number_of_house"></p>
    <p><input placeholder="${numberOfApartment}" name="number_of_apartment"></p>
    <p><input placeholder=" ${time} HH:mm" name="time"></p>
    <input type="submit" name="makeAnOrder" value="${makeAnOrder}">
</form>
<form action="/Controller/cancelTheOrders">
<input type="submit" name="cancelTheOrders" value="${cancelAll}">
</form>
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
<p><a href="/Controller/welcome">${backToWelcome}</a></p>
</body>
</html>
