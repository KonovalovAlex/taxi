<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}"/>
<fmt:bundle basename="i18n.messages">
    <fmt:message key="message.orders.were.canceled" var="OrdersWereCanceled"/>
    <fmt:message key="message.back.to.welcome" var="backToWelcome"/>
    <fmt:message key="message.back.to.client.page" var="backToClient"/>
</fmt:bundle>
<html>
<head>
    <t:gHead>
    <title>OrdersWereCanceled</title>
    </t:gHead>
</head>
<body>
${OrdersWereCanceled}
<p><a href="/Controller/welcome">${backToWelcome}</a></p>
<p><a href="/Controller/client">${backToClient}</a></p>
</body>
</html>
