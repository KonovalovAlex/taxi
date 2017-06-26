<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>error</title>
</head>
<body>
<table>
    <div>возникла ошибка.</div>
    <c:forEach var="invalidFieldsMap" items="${invalidFieldsMap}">
        <div>
            invalid Field
            ${invalidFieldsMap.values()}
        </div>
    </c:forEach>
</table>
</body>
</html>
