<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
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
    My project - TAXI3 .There are two language English and Russian, three role admin,dispatcher and client.
    Client can register on site and create orders where he can specify address and time or cancel all previous orders.
    Dispatcher have a page where he can watch "waiting orders" and accept or reject orders.Admin can delete users.

    <p><a href="/Controller/registration"><fmt:message
            key="message.registration"/> </a></p>
    <p><a href="/Controller/login"><fmt:message
            key="message.login"/></a></p>
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

