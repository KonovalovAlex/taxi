<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<fmt:setLocale value="${locale}"/>
<html>
<head>
    <t:gHead>
        <title>Registration</title>
    </t:gHead>
</head>
<body>
<form action="/Controller/doRegistration" method="post">
    login:<br/>
    <input type="text" name="login" value=""/><br/>
    password:<br/>
    <input type="password" name="password" value=""/><br/>
    firstName:<br/>
    <input type="text" name="firstName" value=""/><br/>
    lastName:<br/>
    <input type="text" name="lastName" value=""/><br/>
    Email:<br/>
    <input type="email" name="email" value=""/><br/>
    phone:<br/>
    <input type="text" name="phone" value=""/><br/>
    <input type="submit" name="doRegistration" value="${registration}"/><br/>
</form>
<c:forEach var="invalidFields" items="${getInvalidFiels}">
    <div>
        <%--order id--%>
            <%--${invalidFields.getInvalidFiels()}--%>
    </div>
</c:forEach>

<p><a href="/Controller/welcome">Вернуться обратно!</a></p>
</body>
</html>
