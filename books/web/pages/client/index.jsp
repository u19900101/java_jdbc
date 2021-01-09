<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: liupannnnnnnnnn
  Date: 2021/1/6
  Time: 10:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<title>书城首页</title>
<%-- 静态包含 base标签、css样式、jQuery文件 --%>
<%@ include file="/pages/common/head.jsp"%>
</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif" >
    <span class="wel_word">网上书城</span>
    <div>
        <%-- 用户未登录 --%>
        <c:if test="${empty sessionScope.user}">
            <a href="pages/user/login.jsp">登录</a> |
            <a href="pages/user/register.jsp">注册</a> &nbsp;&nbsp;
        </c:if>
            <a href="pages/order/order.jsp">我的订单</a>
            <a href="pages/cart/cart.jsp">购物车</a>

        <%-- 用户已登录 --%>
            <c:if test="${not empty sessionScope.user}">
                <a href="index.jsp">返回</a>
                <a href="userServlet?action=logout">注销</a>&nbsp;&nbsp;
                <span>欢迎<span class="um_span">${sessionScope.user.username}</span>光临尚硅谷书城</span>
            <a href="pages/manage/manage.jsp">后台管理</a>
        </c:if>

    </div>
</div>

<div id="main">
    <div id="book">
        <div class="book_cond">
            <form action="client/bookServlet" method="post">
                价格：<input id="min" type="text" name="min" value="${requestScope.min}"> 元 -
                <input id="max" type="text" name="max" value="${requestScope.max}"> 元
                <input type="hidden" value="queryByPrice" name="action">
                <input type="submit" value="查询" id = "searchPriceBtn"/>
            </form>
        </div>
        <script type="text/javascript">
            $(function () {
                // 跳到指定的页码
                $("#searchPriceBtn").click(function () {
                    var min = $("#min").val();
                    var max = $("#max").val();
                    alert(min);
                    alert(max);
                    if(""==min){
                        min = 0;
                    }
                    if(""==max){
                        max = 1000000000;
                    }
                    if($.isNumeric(min)&&$.isNumeric(max)){
                        min = Number(min);
                        max = Number(max);
                        if(min>max){
                            var a = min;
                            min = max;
                            max = a;
                            $("#min").val(min);
                            $("#max").val(max);
                        }
                    }
                    else {
                        alert("数字输入错误,请从新输入");
                        if($.isNumeric(min)){
                            $("#min").val(Number(min));
                        }
                        if($.isNumeric(max)){
                            $("#max").val(Number(max));
                        }
                    }
                });
                $("button.cartBtn").click(function () {

                    var bookId = $(this).attr("bookId");
                    /**
                     * 在事件响应的function函数 中，有一个this对象，这个this对象，是当前正在响应事件的dom对象
                     * @type {jQuery}
                     */
                    // alert(bookId);
                    location.href = "client/cartServlet?action=addItem&id="+bookId;
                        /*此处不能使用属性进行取值，因为循环后相同属性的很多*/
                        // location.href = "client/cartServlet?action=addItem"+$("#cartBtn").val();

                    /*不登录也可以加入购物车*/
                    /*else {
                        alert("请先登录后再加入购物车");
                        location.href = "pages/user/login.jsp";
                    }*/
                });
            });
        </script>
        <div style="text-align: center">


            <c:if test="${empty sessionScope.cart}">
                <div>
                    <span style="color: red">当前购物车为空</span>
                </div>
            </c:if>

            <c:if test="${not empty sessionScope.cart}">
                <span>您的购物车中有${sessionScope.cart.totalCount}件商品</span>
                <div>
                    您刚刚将<span style="color: red">${sessionScope.lastAddBook}</span>加入到了购物车中
                </div>
            </c:if>

        </div>
        <c:forEach items="${requestScope.page.items}" var="book">
            <div class="b_list">
                <div class="img_div">
                    <img class="book_img" alt="" src="static/img/default.jpg" />
                </div>
                <div class="book_info">
                    <div class="book_name">
                        <span class="sp1">书名:</span>
                        <span class="sp2">${book.name}</span>
                    </div>
                    <div class="book_author">
                        <span class="sp1">作者:</span>
                        <span class="sp2">${book.author}</span>
                    </div>
                    <div class="book_price">
                        <span class="sp1">价格:</span>
                        <span class="sp2">${book.price}</span>
                    </div>
                    <div class="book_sales">
                        <span class="sp1">销量:</span>
                        <span class="sp2">${book.sales}</span>
                    </div>
                    <div class="book_amount">
                        <span class="sp1">库存:</span>
                        <span class="book_amount">${book.stock}</span>
                    </div>
                    <div class="book_add">
                        <button class="cartBtn" bookId="${book.id}" >加入购物车</button>
                    </div>
                </div>
            </div>
        </c:forEach>

    </div>
    <%@include file="/pages/common/page_nav.jsp"%>
    <%--分页条的开始--%>
    <%--<div id="page_nav">
        &lt;%&ndash;大于首页，才显示&ndash;%&gt;
        <c:if test="${requestScope.page.pageNo > 1}">
            <a href="${ requestScope.page.url }&pageNo=1">首页</a>
            <a href="${ requestScope.page.url }&pageNo=${requestScope.page.pageNo-1}">上一页</a>
        </c:if>
        &lt;%&ndash;页码输出的开始&ndash;%&gt;
        <c:choose>
            &lt;%&ndash;情况1：如果总页码小于等于5的情况，页码的范围是：1-总页码&ndash;%&gt;
            <c:when test="${ requestScope.page.pageTotal <= 5 }">
                <c:set var="begin" value="1"/>
                <c:set var="end" value="${requestScope.page.pageTotal}"/>
            </c:when>
            &lt;%&ndash;情况2：总页码大于5的情况&ndash;%&gt;
            <c:when test="${requestScope.page.pageTotal > 5}">
                <c:choose>
                    &lt;%&ndash;小情况1：当前页码为前面3个：1，2，3的情况，页码范围是：1-5.&ndash;%&gt;
                    <c:when test="${requestScope.page.pageNo <= 3}">
                        <c:set var="begin" value="1"/>
                        <c:set var="end" value="5"/>
                    </c:when>
                    &lt;%&ndash;小情况2：当前页码为最后3个，8，9，10，页码范围是：总页码减4 - 总页码&ndash;%&gt;
                    <c:when test="${requestScope.page.pageNo > requestScope.page.pageTotal-3}">
                        <c:set var="begin" value="${requestScope.page.pageTotal-4}"/>
                        <c:set var="end" value="${requestScope.page.pageTotal}"/>
                    </c:when>
                    &lt;%&ndash;小情况3：4，5，6，7，页码范围是：当前页码减2 - 当前页码加2&ndash;%&gt;
                    <c:otherwise>
                        <c:set var="begin" value="${requestScope.page.pageNo-2}"/>
                        <c:set var="end" value="${requestScope.page.pageNo+2}"/>
                    </c:otherwise>
                </c:choose>
            </c:when>
        </c:choose>

        <c:forEach begin="${begin}" end="${end}" var="i">
            <c:if test="${i == requestScope.page.pageNo}">
                【${i}】
            </c:if>
            <c:if test="${i != requestScope.page.pageNo}">
                <a href="${ requestScope.page.url }&pageNo=${i}">${i}</a>
            </c:if>
        </c:forEach>
        &lt;%&ndash;页码输出的结束&ndash;%&gt;


        &lt;%&ndash; 如果已经 是最后一页，则不显示下一页，末页 &ndash;%&gt;
        <c:if test="${requestScope.page.pageNo < requestScope.page.pageTotal}">
            <a href="${ requestScope.page.url }&pageNo=${requestScope.page.pageNo+1}">下一页</a>
            <a href="${ requestScope.page.url }&pageNo=${requestScope.page.pageTotal}">末页</a>
        </c:if>

        共${ requestScope.page.pageTotal }页，${ requestScope.page.pageTotalCount }条记录
        到第<input value="${param.pageNo}" name="pn" id="pn_input"/>页
        <input id="searchPageBtn" type="button" value="确定">

        <script type="text/javascript">

            $(function () {
                // 跳到指定的页码
                $("#searchPageBtn").click(function () {

                    var pageNo = $("#pn_input").val();
                    var pageTotal = ${requestScope.page.pageTotal};
                    // alert(Number(+5));
                    // javaScript语言中提供了一个location地址栏对象
                    // 它有一个属性叫href.它可以获取浏览器地址栏中的地址
                    // href属性可读，可写
                    if($.isNumeric(pageNo)){
                        //强转  排除 +5  这种bug
                        pageNo = Number(pageNo);
                        if(pageNo<pageTotal&&pageNo>0){
                            location.href = "${pageScope.basePath}${ requestScope.page.url }&pageNo=" + pageNo;
                        }else {
                            alert("页码输入错误,请从新输入");
                        }
                    }
                    else {
                        alert("页码输入错误,请从新输入");
                    }
                    $("#pn_input").val(${requestScope.page.pageNo});
                });
            });

        </script>

    </div>--%>
    <%--分页条的结束--%>

</div>

<%--静态包含页脚内容--%>
<%@include file="/pages/common/footer.jsp"%>

</body>
</html>
