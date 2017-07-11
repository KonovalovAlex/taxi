<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}"/>
<fmt:bundle basename="i18n.messages">
    <fmt:message key="message.user.status.deleted" var="userStatusIsDeleted"/>
    <fmt:message key="message.back.to.admin.page" var="backToAdmin"/>
    <fmt:message key="message.back.to.welcome" var="backToWelcome"/>
</fmt:bundle>
<html>
<head>
    <t:gHead>
        <title>userStatusWasChange</title>
    </t:gHead>
</head>
<body>
${userStatusIsDeleted}
<p><a href="/Controller/admin">${backToAdmin}</a></p>
<p><a href="/Controller/welcome">${backToWelcome}</a></p>
</body>
</html>
