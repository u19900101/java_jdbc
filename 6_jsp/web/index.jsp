<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: liupannnnnnnnnn
  Date: 2021/1/1
  Time: 9:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"
         pageEncoding="utf-8"
         language="java"
         autoFlush="true"
         buffer="8kb"
%>
<%@ page contentType="text/html;charset=UTF-8" errorPage="error500.jsp" %>

<html>
  <head>
    <title>Goodbye 2020</title>
  </head>
  <body>
  <h1> Hello 2021 </h1>
  <h2> 么么哒 </h2>

  <%--1.声明脚本--%>
  <%!
      private String name;
      static HashMap<String, Object> map = new HashMap<String, Object>();
  %>
  <%!
      public int sum(){
        return 12;
      }
  %>
  <%!
    static {
      map.put("key1", "value1");
      map.put("key2", "value2");
      map.put("key3", "value3");
    }
  %>
  <%--2.表达式脚本--%>
  <%= 12%>
  <%= 2020.01%>
  <%= "Hello 元旦"%>
  <%= map%><br/>
  <br/>
  <%= request.getParameter("username")%><br/><br/>


  <%--3.代码脚本--%>
  <%
    for (int i = 0; i < 10; i++) {
      System.out.println("num " + i);
      %>

  <%= "num " + i%><br/>
  <%
    }
  %>

  <%
    String username = request.getParameter("username");
    System.out.println(" 用户名的请求参数值是：" + username);
  %>
  </body>
</html>
