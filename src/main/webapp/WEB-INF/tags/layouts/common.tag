<%@ tag body-content="scriptless" %><%--스크립트 태그가 포함되지 않은 순수 HTML이나 텍스트만을 허용--%>
<%@ tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<%@ attribute name="header" fragment="true" %>
<%@ attribute name="footer" fragment="true" %>
<%@ attribute name="commonCss" fragment="true" %>
<%@ attribute name="commonJs" fragment="true" %>
<%@ attribute name="title" %>
<fmt:setBundle basename="messages.commons" />
<c:url var="cssUrl" value="/css/" />
<c:url var="jsUrl" value="/js/" />
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>
         <c:if test="${!empty title}">
         ${title} -
         </c:if>
         <fmt:message key="SITE_TITLE" />
        </title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
        <link rel="stylesheet" type="text/css" href="${cssUrl}style.css">

        <jsp:invoke fragment="commonCss" />
        <c:if test="${addCss != null}">
            <c:forEach var="cssFile" items="${addCss}">
                <link rel="stylesheet" type="text/css" href="${cssUrl}${cssFile}.css">
            </c:forEach>
        </c:if>

        <script src="${jsUrl}common.js"></script>
        <jsp:invoke fragment="commonJs" />
        <c:if test="${addScript != null}">
            <c:forEach var="jsFile" items="${addScript}">
                <script src="${jsUrl}${jsFile}.js"></script>
            </c:forEach>
        </c:if>
    </head>
    <body>
        <header>
            <jsp:invoke fragment="header" />
        </header>
        <main>
            <jsp:doBody />
        </main>
        <footer>
            <jsp:invoke fragment="footer" />
        </footer>
    </body>
    <iframe name="ifrmProcess" class="dn"></iframe>
</html>