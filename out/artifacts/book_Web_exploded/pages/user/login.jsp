<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import ="java.net.URLDecoder" %>
<%@ page import="java.net.URLEncoder" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>尚硅谷会员登录页面</title>

	<%-- 静态包含 base标签、css样式、jQuery文件 --%>
	<%@ include file="/pages/common/head.jsp"%>
   <%-- <script type="text/javascript">
        /* 出于安全考虑 此功能还是算了吧，哈哈哈*/
        $(function () {
            $("#username").bind("input propertychange",function(event){
                var username = this.value;
                $.getJSON("userServlet?action=ajaxexistUsername","&username="+username,function (data) {
                    if(!data.existUsername){
                        $("span.errorMsg").text("用户名还未匹配上，请继续输入");
                    }else {
                        $("span.errorMsg").text("用户名已匹配上，请继续密码");
                    }
                });
            });
        });
    </script>--%>
</head>
<body>
		<div id="login_header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
		</div>
		
			<div class="login_banner">
			
				<div id="l_content">
					<span class="login_word">欢迎登录</span>
				</div>
				
				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1>尚硅谷会员</h1>
								<a href="pages/user/register.jsp">立即注册</a>
							</div>
							<div class="msg_cont">
								<b></b>
								<span class="errorMsg">
									${ empty requestScope.msg ? "请输入用户名和密码":requestScope.msg }
								</span>
							</div>
							<div class="form">
								<form action="userServlet" method="post">
									<input type="hidden" name="action" value="login" />
									<label>用户名称：</label>
									<input class="itxt" type="text" placeholder="请输入用户名"
										   autocomplete="off" tabindex="1" name="username"
										   value="${requestScope.username}" id="username"
                                    />
									<br />
									<br />
									<label>用户密码：</label>
									<input class="itxt" type="password" placeholder="请输入密码"
										   autocomplete="off" tabindex="1" name="password" />
									<br />
									<br />
									<input type="submit" value="登录" id="sub_btn" />
								</form>
							</div>
							
						</div>
					</div>
				</div>
			</div>

		<%--静态包含页脚内容--%>
		<%@include file="/pages/common/footer.jsp"%>


</body>
</html>