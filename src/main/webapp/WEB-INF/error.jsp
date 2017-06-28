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
<table>
    <div>возникла ошибка.</div>
    <c:forEach var="invalidFieldsMap" items="${invalidFieldsMap}">
        <div>
            <%--invalid Field--%>
            <%--${invalidFieldsMap.values()}--%>
        </div>
    </c:forEach>
</table>
</body>
</html>
