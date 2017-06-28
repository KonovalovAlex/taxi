<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<html>
    <t:gHead>
        <title>Admin</title>
    </t:gHead>
<body>
<table>
    <c:forEach var="entity" items="${users}">
    <form action="/Controller/deleteUser" method="get">
        <p><input type="hidden" name="id" value="${entity.getId()}">
            <input type="submit" value="удалить">
        </p></form>
    <div>
        <div>
            user id ${entity.getId()}
        </div>
        <div>
            Login ${entity.getLogin()}
        </div>
        <div>
            password ${entity.getPassword()}
        </div>
        <div>
            Name ${entity.getFirstName()}
        </div>
        <div>
            LastName ${entity.getLastName()}
        </div>
        <div>
            Mobile ${entity.getPhone()}
        </div>
        <div>
            Email ${entity.getEmail()}
        </div>
        <div>
            Role ${entity.getRole()}
        </div>
        <div>
            Status ${entity.getActivityStatus()}
        </div>
        </c:forEach>
</table>
</body>
</html>
