<html>
   	<%@page session="false"%>
    <%@include file="/apps/scf/global.jsp" %>
	<%@ page contentType="text/html; charset=utf-8" %>

    <head>
        <cq:include script="head.jsp"/>
        <cq:includeClientLib css="apps.biogen"/>
    </head>
    <body>
        <div id="base-container">
            <div id="base-page">
                <cq:include script="content.jsp"/>
            </div>
        </div>
    </body>
</html>