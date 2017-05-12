<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<jsp:include page="../../include/css-js.jsp" />
</head>
<body>
<div class="row">
	<table  class="tableForm">
		<tr >
			<td width="45%" class="titleTd " >关键词：</td>
			<td >${keyword.keyword }</td>
		</tr>
		<tr>
			<td class="titleTd " >回复内容：</td>
			<td >${messageText.content }</td>
		</tr>
		<tr >
			<td class="titleTd" >上次修改时间：</td>
			<td >${creatTime }</td>
		</tr>	
	</table>
	<div class="frame_close">
		<button class="btn btn-info" onclick="parent.closeFrame()" type="button"> 关闭</button>
	</div>
</div><!-- /.row -->
<jsp:include page="../../include/footer-js.jsp" />
</body>
</html>
