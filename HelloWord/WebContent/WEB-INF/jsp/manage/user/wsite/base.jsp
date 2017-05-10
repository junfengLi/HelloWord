<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/WEB-INF/tld/common.tld" prefix="common" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Cache-Control" content="no-store" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<title></title>
</head>
<body>
<div id="particles">
	<div class="intro">
		<div class="manage-main">
			<div class="manage-top">
				微站基本信息管理
			</div>
			<div class="manage-content">
				<div class="manage-con-right">
					<div class="m-hr"></div>
					<div class="input-box">
						<div class="con-t">
							<span>*</span>微站链接地址：
						</div>
						<div class="input-con">
							${common:getWsiteUrl(wechatId)}
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>
