<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${locale}"/>
<fmt:bundle basename="i18n.messages">
    <fmt:message key="message.login" var="login"/>
    <fmt:message key="message.password" var="password"/>
    <fmt:message key="message.back.to.welcome" var="backToWelcome"/>
</fmt:bundle>
<html>
<head>
    <t:gHead>
        <title>login</title>
    </t:gHead>
</head>
<body>
<form action="/Controller/actionLogin" method="post">
    ${login}:<br/>
    <input type="text" name="login" value=""/><br/>
    ${password}<br/>
    <input type="password" name="password" value=""/><br/>
    <input type="submit" value="${login}"/><br/>
</form>
<br>
<p><a href="/Controller/welcome">${backToWelcome}</a></p>
</body>
</html>


