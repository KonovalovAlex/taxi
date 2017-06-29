<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <t:gHead>
    <title>Deleted</title>
    </t:gHead>
<body>
вы были удалены.
<p><a href="/Controller/welcome"><fmt:message
        key="message.back.to.welcome"/></a></p>
<p><a href="/Controller/registration"><fmt:message
        key="message.registration"/></a></p>
</body>
</html>