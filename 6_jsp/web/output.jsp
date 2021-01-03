<%--
  Created by IntelliJ IDEA.
  User: liupannnnnnnnnn
  Date: 2021/1/1
  Time: 17:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>output</title>
</head>
<body>


<% out.write("out1");%><br/>
<% out.flush();%><br/>
<% out.write("out2");%><br/>



<% response.getWriter().write("response1<br/><br/>");%><br/>
<% response.getWriter().write("response2<br/><br/>");%><br/>






</body>
</html>
