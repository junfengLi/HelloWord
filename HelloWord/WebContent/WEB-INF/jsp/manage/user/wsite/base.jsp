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
<div class="page-content">
	<div class="row">
		<div class="col-xs-12">
			<div class="alert alert-block alert-success">
				<span>微站基本信息</span>
			</div>
		</div><!-- /.col -->
	</div><!-- /.row -->
	<div class="page-header">
		<h1>微站链接地址：<small>${common:getWsiteUrl(wechatId)}</small></h1>
	</div>
</div>
</body>
</html>
