<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}"/>
<fmt:bundle basename="i18n.messages">
    <!DOCTYPE html>
    <html>
    <head>
        <title>Welcome</title>
    </head>
    <body>
    <p><a href="/Controller/registration"><fmt:message
            key="registration"/> </a></p>
    <p><a href="/Controller/login">Войти</a></p>
    <form action="/Controller/changeLocale" method="post">

        <select onchange="submit()" name="locale">
            <option
                    <c:if test="${locale == 'ru'}">selected</c:if> value="ru">
                <fmt:message key="navigation.rus"/>
            </option>
            <option
                    <c:if test="${locale == 'en'}">selected</c:if> value="en">
                <fmt:message key="navigation.eng"/>
            </option>
        </select>

    </form>
    </body>
    </html>
</fmt:bundle>

