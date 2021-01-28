<%--
  Created by IntelliJ IDEA.
  User: liupannnnnnnnnn
  Date: 2021/1/10
  Time: 9:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- 引入各种JQuery依赖库--%>
<%@ include file="/pages/common/head.jsp"%>

<html>
<head>
    <title>Title</title>
    <script type="text/javascript">
        $(function () {
            $("#pn_input").bind("input propertychange",function(event){
                // console.log($("#pn_input").val())
                $("#pn_input2").val($("#pn_input").val());
            });
        });
    </script>
</head>
<body>
<h1> test </h1>

输入1： <input type="text" id="pn_input" style="width: auto"/><br/>
实时显示1的输入<input type="text" id="pn_input2"/>
</body>
</html>
