<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tld"%>
<!DOCTYPE html>
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
        </c:forEach>
</table>
</body>
</html>
