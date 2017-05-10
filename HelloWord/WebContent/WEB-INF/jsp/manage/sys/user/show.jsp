<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/WEB-INF/tld/tool.tld" prefix="tool" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>Bootstrap控制台</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<jsp:include page="../../include/css-js.jsp" />
</head>
<body>
<div class="row">
	<table  class="tableForm">
		<tr>
			<td width="35%" class="titleTd">
			登录名：
			</td>
			<td  width="40%">${user.loginname }
		</tr>
		<tr>
			<td class="titleTd">真实姓名：</td>
			<td>${user.realname }</td>
		</tr>
		<tr>
			<td class="titleTd">手机：</td>
			<td>${user.mobile }</td>
		</tr>
		<tr>
			<td class="titleTd">邮箱：</td>
			<td>${user.email }</td>
		</tr>
		<tr>
			<td class="titleTd">上次登录IP：</td>
			<td>${user.loginip }</td>
		</tr>
		<tr>
			<td class="titleTd">上次登录地点：</td>
			<td>${user.loginplace }</td>
		</tr>
		<tr>
			<td class="titleTd">上次登录时间：</td>
			<td>${tool:getFormatDate(user.logintime)}</td>
		</tr>
		<tr>
			<td class="titleTd">创建时间：</td>
			<td>${tool:getFormatDate(user.createtime)}</td>
		</tr>
		<tr>
			<td class="titleTd">上次更新时间：</td>
			<td>${tool:getFormatDate(user.updatetime)}</td>
		</tr>
	</table>
	<div class="frame_close">
		<button class="btn btn-info" onclick="parent.closeFrame()" type="button"> 关闭</button>
	</div>
</div><!-- /.row -->
<jsp:include page="../../include/footer-js.jsp" />
<script type="text/javascript">
</script>
</body>
</html>

