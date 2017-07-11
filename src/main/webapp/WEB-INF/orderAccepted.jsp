<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${locale}"/>
<fmt:bundle basename="i18n.messages">
    <fmt:message key="message.order.accepted" var="orderAccepted"/>
    <fmt:message key="message.back.to.dispatcher.page" var="backToDispatcher"/>

</fmt:bundle>
<html>
<head>
    <t:gHead>
        <title>OrderAccepted</title>
    </t:gHead>
</head>
<body>
${orderAccepted}
<p><a href="/Controller/welcome">${backToDispatcher}</a></p>
</body>
</html>
