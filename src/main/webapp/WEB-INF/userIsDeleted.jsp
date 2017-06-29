<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<fmt:setLocale value="${locale}"/>
<fmt:bundle basename="i18n.messages">
    <fmt:message key="message.user.status.deleted" var="userStatusIsDeleted"/>
</fmt:bundle>
<html>
<head>
    <t:gHead>
        <title>userStatusWasChange</title>
    </t:gHead>
</head>
<body>
${userStatusIsDeleted}
<p><a href="/Controller/admin"></a></p>
<p><a href="/Controller/welcome"></a></p>
</body>
</html>
