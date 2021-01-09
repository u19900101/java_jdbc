<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- 只负责请求转发--%>
<%System.out.println("进入了index.jsp中");%>
<jsp:forward page="/client/bookServlet?action=page"></jsp:forward>