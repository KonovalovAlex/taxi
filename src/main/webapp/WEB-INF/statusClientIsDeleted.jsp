<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}"/>
<fmt:bundle basename="i18n.messages">
    <fmt:message key="message.you.were.deleted" var="youWereDeleted"/>
    <fmt:message key="message.back.to.welcome" var="backToWelcome"/>
    <fmt:message key="message.registration" var="backRegistr"/>
</fmt:bundle>
<html>
<head>
    <t:gHead>
    <title>Deleted</title>
    </t:gHead>
<body>
${youWereDeleted}
<p><a href="/Controller/welcome">${backToWelcome}</a></p>
<p><a href="/Controller/registration">${backRegistr}</a></p>
</body>
</html>
