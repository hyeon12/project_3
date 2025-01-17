<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="layout" tagdir="/WEB-INF/tags/layouts" %>
<c:url var="listlink" value="/board/list/${board.BId}" />
<c:url var="actionUrl" value="/board/save" />
<%-- 저장/수정하는 곳 --%>
<layout:main title="${board.BName}">

<section class="layout-width">
    <jsp:include page="_header.jsp" />

    <form name="frmSave" method="POST" action="${actionUrl}" target="ifrmProcess" autocomplete="off">
        <input type="hidden" name="mode" value="update">

        <jsp:include page="_form.jsp"/>

        <div class="button-group">
            <button type="reset">다시입력</button>
            <button type="submit">글수정</button>
        </div>
    </form>

</section>

</layout:main>