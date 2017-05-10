<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
			<td class="titleTd">性别：</td>
			<td><c:if test="${user.sex == '0' }">女</c:if>
				 <c:if test="${user.sex == '1' }">男</c:if>
			 </td>
		</tr>
		<tr>
			<td class="titleTd">真实姓名：</td>
			<td>${user.name }</td>
		</tr>
		<tr>
			<td class="titleTd">手机：</td>
			<td>${user.mobile }</td>
		</tr>
		<tr>
			<td class="titleTd">地址：</td>
			<td>${user.address }</td>
		</tr>
		<%-- <tr>
			<td class="titleTd">排序：</td>
			<td>${user.seq }</td>
		</tr> --%>
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

