<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}"/>
<fmt:bundle basename="i18n.messages">
    <fmt:message key="message.wrong.data" var="wrongData"/>
    <fmt:message key="message.back.to.login" var="backToLogin"/>
</fmt:bundle>
<html>
<body>
${wrongData}
<p><a href="/Controller/login">${backToLogin}</a></p>
</body>
</html>
