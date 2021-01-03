<%--
  Created by IntelliJ IDEA.
  User: liupannnnnnnnnn
  Date: 2021/1/1
  Time: 22:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>EL</title>
</head>
<body>

<%--<%  pageContext.setAttribute("key","pageContext");%>--%>
<%--<%  request.setAttribute("key","request");%>--%>
<%--<%  session.setAttribute("key","session");%>--%>
<%--<%  application.setAttribute("key","application");%>--%>


General express is <%= request.getAttribute("key")%><br/>
EL express is ${key}<br/>
</body>
</html>
