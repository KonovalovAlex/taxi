<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<fmt:setLocale value="${locale}"/>
<fmt:bundle basename="i18n.messages">
    <fmt:message key="message.order.created.operator.will.send.you.sms" var="orderCreated"/>
    <fmt:message key="message.back.to.client.page" var="backToClient"/>
</fmt:bundle>
<html>

<body>
${orderCreated}
<p><a href="/Controller/client">${backToClient}</a></p>
</body>
</html>
