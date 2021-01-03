<%--
  Created by IntelliJ IDEA.
  User: liupannnnnnnnnn
  Date: 2021/1/1
  Time: 19:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
errorPage="foot.jsp" %>
<html>
<head>
    <title>main</title>
</head>
<body>
<h1>main</h1>
<%-- 静态包含 --%>
<%@ include file="foot.jsp"%>

<%-- 动态包含 --%>
<jsp:include page="foot.jsp">
    <jsp:param name="username" value="root"></jsp:param>
</jsp:include>

<%-- 标签转发--%>
<jsp:forward page="/1_scope/scope.jsp"></jsp:forward>
</body>
</html>
