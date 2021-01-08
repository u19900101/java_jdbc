<%--
  Created by IntelliJ IDEA.
  User: liupannnnnnnnnn
  Date: 2021/1/8
  Time: 22:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1> This is target Source!!!</h1>
<%
    System.out.println("target source get!");
    System.out.println(Thread.currentThread().getName());

%>
</body>
</html>
