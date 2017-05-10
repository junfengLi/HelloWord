<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html lang="en"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<script type="text/javascript" src="${ctx }/static/js/jquery/jquery-1.11.0.min.js"></script>
<script type="text/javascript">
	var logout = '${logout}';
	if (logout != '') {
		top.location.href="${ctx}/loginForward";
	}
</script>
<script type='text/javascript' src='${ctx }/static/js/login/jquery.particleground.min.js'></script>
<link rel="stylesheet" href="${ctx }/static/css/login/login_bg.css" />
</head>
<body>
<c:if test="${empty logout}">
<div id="particles">
	<div class="intro">
		<div class="login_main">
			<div class="form_title">
			<div><a  class="login_logo" href="${ctx }/index" title="去首页"></a></div>
			<span>用户登陆</span>
			</div>
			<div class="login_form">
				<form action="${ctx }/login" method="POST" id="form_login">
					<span>登陆名：</span>
					<input type="text" name="username" id="username" /> 
					<span>密码：</span>
					<input type="password" name="password" id="password" /> 
					<span>验证码：</span>
					<input type="text" name="jcaptchaCode" id="jcaptchaCode" /> 
					<div style="width:100px; height:40px;">
						<img src="${ctx }/jcaptcha.jpg" class="imageCode" style="margin-left: 50px;" onclick="this.src='${ctx }/jcaptcha.jpg'" />
					</div>
					<button type="submit" class="button" id="login_submit">登 陆</button>
				</form>
				<c:if test="${not empty error}">
					<div style="    color: #f70000; height: 30px; margin-top: 8px; float: left; width: 103px;" id="err_msg">${error}</div>
				</c:if>
			</div>
		</div>
	</div>
</div>
</c:if>
</body>
</html>
