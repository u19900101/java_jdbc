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
<h1> Scope1 页面</h1>
<% pageContext.setAttribute("key","pageContext");%>
<% request.setAttribute("key","request");%>
<% session.setAttribute("key","session");%>
<% application.setAttribute("key","application");%>
<hr>
key is ${key}
<hr>
request : <%= request.getAttribute("key")%><br/>
pageContext : <%= pageContext.getAttribute("key")%><br/>
session :<%= session.getAttribute("key")%><br/>
application :<%= application.getAttribute("key")%><br/>
<hr>

${pageScope.key}<br/>
${requestScope.key}<br/>
${sessionScope.key}<br/>
${applicationScope.key}<br/>

<td><a href="pages/manage/book_edit.jsp&pageNo=${requestScope.page.pageNo}">添加图书</a></td>

<%--<% request.getRequestDispatcher("/1_scope/scope2.jsp").forward(request,response);%>--%>

</body>
</html>
