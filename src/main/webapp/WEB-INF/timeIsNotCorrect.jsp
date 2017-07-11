<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}"/>
<fmt:bundle basename="i18n.messages">
    <fmt:message key="message.back.to.client.page" var="backToClient"/>
    <fmt:message key="message.time.is.not.correct" var="timeIsNotCorrect"/>
</fmt:bundle>
<html>
<body>
${timeIsNotCorrect}
<p><a href="/Controller/client">${backToClient}</a></p>
</body>
</html>
