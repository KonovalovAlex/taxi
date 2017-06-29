<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<fmt:setLocale value="${locale}"/>
<fmt:bundle basename="i18n.messages">
    <html>
    <head>
        <t:gHead>
            <title>Registration</title>
        </t:gHead>
    </head>
    <body>
    <fmt:bundle basename="i18n">
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
            Phone:<br/>
            <input type="text" name="phone" value=""/><br/>
            <input type="submit" name="doRegistration" value="sdfsdfsdf"/><br/>
        </form>
    </fmt:bundle>
    <c:forEach var="invalidFields" items="${getInvalidFiels}">
        <div>
                <%--order id--%>
                <%--${invalidFields.getInvalidFiels()}--%>
        </div>
    </c:forEach>
    <t:invalidFields>
        
    </t:invalidFields>
    <p><a href="/Controller/welcome"><fmt:message key="message.back.to.welcome"/></a></p>
    </body>
    </html>
</fmt:bundle>