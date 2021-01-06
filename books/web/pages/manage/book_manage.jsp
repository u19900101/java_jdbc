<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>图书管理</title>
	<%-- 静态包含 base标签、css样式、jQuery文件 --%>
	<%@ include file="/pages/common/head.jsp"%>

	<script type="text/javascript">
		$(function () {
			// 给删除的a标签绑定单击事件，用于删除的确认提示操作
			$("a.deleteClass").click(function () {
				// 在事件的function函数中，有一个this对象。这个this对象，是当前正在响应事件的dom对象。
				/**
				 * confirm是确认提示框函数
				 * 参数是它的提示内容
				 * 它有两个按钮，一个确认，一个是取消。
				 * 返回true表示点击了，确认，返回false表示点击取消。
				 */
				return confirm("你确定要删除【" + $(this).parent().parent().find("td:first").text() + "】?");
				// return false// 阻止元素的默认行为===不提交请求
			});
		});
	</script>

</head>
<body>
	<div id="header">
			<img class="logo_img" alt="" src="../../static/img/logo.gif" >
			<span class="wel_word">图书管理系统</span>
		<%-- 静态包含 manager管理模块的菜单  --%>
		<%@include file="/pages/common/manage_menu.jsp"%>
	</div>
	<div id="main">
        <%--分页条的开始--%>
        <div id="page_nav">
            <%--大于首页，才显示--%>
            <c:if test="${requestScope.page.pageNo > 1}">
                <a href="${ requestScope.page.url }&pageNo=1">首页</a>
                <a href="${ requestScope.page.url }&pageNo=${requestScope.page.pageNo-1}">上一页</a>
            </c:if>
            <%--页码输出的开始--%>
            <c:choose>
                <%--情况1：如果总页码小于等于5的情况，页码的范围是：1-总页码--%>
                <c:when test="${ requestScope.page.pageTotal <= 5 }">
                    <c:set var="begin" value="1"/>
                    <c:set var="end" value="${requestScope.page.pageTotal}"/>
                </c:when>
                <%--情况2：总页码大于5的情况--%>
                <c:when test="${requestScope.page.pageTotal > 5}">
                    <c:choose>
                        <%--小情况1：当前页码为前面3个：1，2，3的情况，页码范围是：1-5.--%>
                        <c:when test="${requestScope.page.pageNo <= 3}">
                            <c:set var="begin" value="1"/>
                            <c:set var="end" value="5"/>
                        </c:when>
                        <%--小情况2：当前页码为最后3个，8，9，10，页码范围是：总页码减4 - 总页码--%>
                        <c:when test="${requestScope.page.pageNo > requestScope.page.pageTotal-3}">
                            <c:set var="begin" value="${requestScope.page.pageTotal-4}"/>
                            <c:set var="end" value="${requestScope.page.pageTotal}"/>
                        </c:when>
                        <%--小情况3：4，5，6，7，页码范围是：当前页码减2 - 当前页码加2--%>
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
            <%--页码输出的结束--%>


            <%-- 如果已经 是最后一页，则不显示下一页，末页 --%>
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

        </div>
        <%--分页条的结束--%>
		<%--<div id="page_nav">
			<c:if test="${requestScope.page.pageNo>1}">
				<a href="manage/bookServlet?action=page&pageNo=1">首页</a>
				<a href="manage/bookServlet?action=page&pageNo=${requestScope.page.pageNo-1}">上一页</a>
			</c:if>
				<a href="manage/bookServlet?action=page&pageNo=${requestScope.page.pageNo-1}">${requestScope.page.pageNo-1}</a>


			【${requestScope.page.pageNo}】

			<c:if test="${requestScope.page.pageNo<requestScope.page.pageTotal}">
				<a href="manage/bookServlet?action=page&pageNo=${requestScope.page.pageNo+1}">${requestScope.page.pageNo+1}</a>
				<a href="manage/bookServlet?action=page&pageNo=${requestScope.page.pageNo+1}">下一页</a>
				<a href="manage/bookServlet?action=page&pageNo=${requestScope.page.pageTotal}">末页</a>
			</c:if>
			共${ requestScope.page.pageTotal}页，${ requestScope.page.pageTotalCount}条记录 到第
			<input value="${param.pageNo}" name="pn" id="pn_input"/>页
			<input type="button" value="确定" id = "searchBtn">

			&lt;%&ndash; 写js代码 绑定跳转页面按钮&ndash;%&gt;
			<script type="text/javascript">
				$(function () {
					$("#searchBtn").click(function () {
						// alert(location.href);http://localhost:8080/6_books/manage/bookServlet?action=page&pageNo=6
						var pn = $("#pn_input").val();
						if(pn<=${requestScope.page.pageTotal}&&pn>0){
							location.href = "${pageScope.basePath}manage/bookServlet?action=page&pageNo="+pn;
						}else {
							alert("页面输入有误，请重新输入");
							$("#pn_input").val(${requestScope.page.pageNo});
						}
					})
				});
			</script>
		</div>--%>

		<table>
			<tr>
				<td>名称</td>
				<td>价格</td>
				<td>作者</td>
				<td>销量</td>
				<td>库存</td>
				<td colspan="2">操作</td>
			</tr>
			<c:forEach items="${ requestScope.page.items }" var="book">
				<tr>
					<td>${book.name}</td>
					<td>${book.price}</td>
					<td>${book.author}</td>
					<td>${book.sales}</td>
					<td>${book.stock}</td>
					<td><a href="manage/bookServlet?action=getBook&id=${book.id}&pageNo=${requestScope.page.pageNo}">修改</a></td>
					<td><a class="deleteClass" href="manage/bookServlet?action=delete&id=${book.id}&pageNo=${requestScope.page.pageNo}">删除</a></td>
				</tr>
			</c:forEach>
				<td><a href="pages/manage/book_edit.jsp?pageNo=${requestScope.page.pageNo}">添加图书</a></td>
			</tr>
		</table>
		<%--静态包含分页条--%>
		<%--<%@include file="/pages/common/page_nav.jsp"%>--%>

	</div>
	<%--静态包含页脚内容--%>
	<%@include file="/pages/common/footer.jsp"%>
</body>
</html>