<%--
  Created by IntelliJ IDEA.
  User: liupannnnnnnnnn
  Date: 2021/1/1
  Time: 17:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>4大域对象演示</title>
</head>
<body>
<h1> Scope3 页面</h1>
request : <%= request.getAttribute("key")%><br/>
pageContext : <%= pageContext.getAttribute("key")%><br/>
session :<%= session.getAttribute("key")%><br/>
application :<%= application.getAttribute("key")%><br/>



</body>
</html>
