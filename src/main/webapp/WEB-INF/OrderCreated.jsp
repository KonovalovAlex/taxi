<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<fmt:setLocale value="${locale}"/>
<fmt:bundle basename="i18n.messages">
    <html>
    <head>
        <t:gHead>
            <title>OrderCreated</title>
        </t:gHead>
    </head>
    <body>
    <p><a href="/Controller/client"><fmt:message
            key="message.back.to.client.page"/></a></p>
    </body>
    </html>
</fmt:bundle>