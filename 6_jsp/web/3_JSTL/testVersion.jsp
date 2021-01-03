<%--
  Created by IntelliJ IDEA.
  User: liupannnnnnnnnn
  Date: 2021/1/2
  Time: 10:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
1. Server info: <%= application.getServerInfo() %><br>
2. Servlet version: <%= application.getMajorVersion() %>.<%= application.getMinorVersion() %><br>
3. JSP version: <%= JspFactory.getDefaultFactory().getEngineInfo().getSpecificationVersion() %><br>
4. Java version: <%= System.getProperty("java.version") %><br>
<%--
1. Server info: Apache Tomcat/9.0.8
2. Servlet version: 4.0
3. JSP version: 2.3
4. Java version: 1.8.0_171
--%>
</body>
</html>
