<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${locale}"/>
<fmt:bundle basename="i18n.messages">
    <fmt:message key="message.order.rejected" var="orderRejected"/>
    <fmt:message key="message.back.to.dispatcher.page" var="backToDispatcher"/>
</fmt:bundle>
<html>
<body>
${orderRejected}
<p><a href="/Controller/dispatcher">${backToDispatcher}</a></p>
</body>
</html>
