<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<fmt:setLocale value="${locale}"/>
<fmt:bundle basename="i18n.messages">
    <fmt:message key="message.error.check.data" var="checkData"/>
    <fmt:message key="message.back.to.welcome" var="backToWelcome"/>
</fmt:bundle>
<html>
<head>
    <t:gHead>
        <title>error</title>
    </t:gHead>
</head>
<body>
<table>
    <div>${checkData}</div>
<p><a href="/Controller/welcome">${backToWelcome}</a></p>
</body>
</html>

