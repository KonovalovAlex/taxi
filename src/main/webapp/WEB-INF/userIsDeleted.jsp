<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <t:gHead>
        <title>userIsDeleted</title>
    </t:gHead>
</head>
<body>
юзеру был присвоен статус удалён!
<p><a href="/Controller/admin"><fmt:message
        key="message.back.to.admin.page"/></a></p>
<p><a href="/Controller/welcome"><fmt:message
        key="message.back.to.welcome"/></a></p>
</body>
</html>
