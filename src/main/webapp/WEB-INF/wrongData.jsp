
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<fmt:setLocale value="${locale}"/>
<fmt:bundle basename="i18n.messages">
    <fmt:message key="message.wrong.data" var="wrongData"/>
    <fmt:message key="message.back.to.client.page" var="backToClient"/>
</fmt:bundle>
<html>
<head>
    <title>Title</title>
</head>
<body>
${wrongData}
<p><a href="/Controller/login">${backToClient}</a></p>
</body>
</html>
