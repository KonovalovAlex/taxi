<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<fmt:setLocale value="${locale}"/>
<fmt:bundle basename="i18n.messages">
    <fmt:message key="message.back.to.welcome" var="backToWelcome"/>
    <fmt:message key="message.login" var="login"/>
    <fmt:message key="message.password" var="password"/>
    <fmt:message key="message.phone" var="phone"/>
    <fmt:message key="message.first.name" var="firstName"/>
    <fmt:message key="message.last.name" var="lastName"/>
    <fmt:message key="message.registration" var="registration"/>
    <fmt:message key="message.back.to.welcome" var="backToWelcome"/>
    <fmt:message key="message.email" var="email"/>
    <fmt:message key="message.role" var="role"/>
    <fmt:message key="message.status" var="status"/>
    <fmt:message key="button.delete" var="delete"/>
    <fmt:message key="message.user.id" var="userId"/>
    <fmt:message key="navigation.rus" var="navigationRU"/>
    <fmt:message key="navigation.eng" var="navigationENG"/>

</fmt:bundle>
<html>
<head>
<t:gHead>
    <title>Admin</title>
</t:gHead>
</head>
<body>
<table>
    <c:forEach var="entity" items="${users}">
    <form action="/Controller/deleteUser" method="post">
        <p><input type="hidden" name="id" value="${entity.getId()}">
            <input type="submit" value="${delete}">
        </p></form>
    <div>
        <div>
                ${userId} ${entity.getId()}
        </div>
        <div>
                ${login} ${entity.getLogin()}
        </div>
        <div>
                ${password} ${entity.getPassword()}
        </div>
        <div>
                ${firstName} ${entity.getFirstName()}
        </div>
        <div>
                ${lastName} ${entity.getLastName()}
        </div>
        <div>
                ${phone} ${entity.getPhone()}
        </div>
        <div>
                ${email} ${entity.getEmail()}
        </div>
        <div>
                ${role} ${entity.getRole()}
        </div>
        <div>
                ${status} ${entity.getActivityStatus()}
        </div>
        </c:forEach>
</table>
<form>

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
