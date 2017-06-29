<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<fmt:setLocale value="${locale}"/>
<fmt:bundle basename="i18n.messages">
<html>
<head>
    <t:gHead>
        <title>OrderAccepted</title>
    </t:gHead>
</head>
<body>
<p><fmt:message key="message.order.accepted"/></p>
<p><a href="/Controller/dispatcher"><fmt:message
        key="message.back.to.dispatcher.page"/></a></p>
</body>
</html>
</fmt:bundle>