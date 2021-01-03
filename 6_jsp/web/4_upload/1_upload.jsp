<%--
  Created by IntelliJ IDEA.
  User: liupannnnnnnnnn
  Date: 2021/1/2
  Time: 20:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%-- http://localhost:8080/--%>
<form action="/jsp/uploadServlet" enctype="multipart/form-data" method="post">
    username<input type="text" name="username" value="username">
    <hr>
    photo<input name="photo" type="file" >
    <input type="submit">
</form>
</body>
</html>
