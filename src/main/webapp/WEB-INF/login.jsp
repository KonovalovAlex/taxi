<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <t:gHead>
        <title>error</title>
    </t:gHead>
</head>
<body>
<form action="/Controller/actionLogin" method="post">
    login:<br/>
    <input type="text" name="login" value=""/><br/>
    password:<br/>
    <input type="password" name="password" value=""/><br/>
    <input type="submit" value="войти"/><br/>
</form>
<p><a href="/Controller/welcome"><fmt:message key="message.back.to.welcome"/></a></p>
</body>
</html>


