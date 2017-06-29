<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<fmt:setLocale value="${locale}"/>
<fmt:bundle basename="i18n.messages">
    <fmt:message key="message.order.rejected" var="orderRejected"/>
</fmt:bundle>
<html>
<head>
    <t:gHead>
        <title>OrderRejected</title>
    </t:gHead>
</head>
<body>
${orderRejected}
<p><a href="/Controller/welcome">${backToDispatcher}</a></p>
</body>
</html>
