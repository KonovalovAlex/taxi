<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<fmt:setLocale value="${locale}"/>
<fmt:bundle basename="i18n.messages">
    <fmt:message key="message.error.check.data" var="checkData"/>
</fmt:bundle>
<html>
<head>
    <t:gHead>
        <title>error</title>
    </t:gHead>
</head>
<body>
<table>
    <div>${checkData}</div>
    <c:forEach var="invalidFields" items="${valuesList}">
        <div>
            order id
                ${invalidFields.values()}
        </div>
    </c:forEach>
</table>
<p><a href="/Controller/welcome">${backToWelcome}</a></p>
</body>
</html>

