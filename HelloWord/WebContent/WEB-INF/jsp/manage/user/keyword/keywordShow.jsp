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
	<table class="tableForm">
		<tr >
			<td width="45%" class="titleTd " >请求类型：</td>
			<td >${serviceTypeText }</td>
		</tr>
		<tr >
			<td class="titleTd " >关联关键词：</td>
			<td >${keyword.messagetype }</td>
		</tr>
		<tr >
			<td class="titleTd">上次修改时间：</td>
			<td >${createTime }</td>
		</tr>	
		<c:forEach items="${maps }" var="map">
		<tr>
			<td class="titleTd " >回复类型：</td>
			<td >${map.messagetype }</td>
		</tr>
		<tr>
			<td class="titleTd " >回复内容：</td>
			<td >${map.content }
			<c:if test="${map.messagetype == 'Img' }">
			<span> [<a href="###" style="color:blue" onclick="showKeyword('${map.id }')">查看</a>] </span>
			</c:if>
			</td>
		</tr>
		</c:forEach>
	</table>
	<div class="frame_close">
		<button class="btn btn-info" onclick="parent.closeFrame()" type="button"> 关闭</button>
	</div>
</div><!-- /.row -->
<jsp:include page="../../include/footer-js.jsp" />
<script type="text/javascript">
var wechatId = '${wechatId}';
function showKeyword(id){
	parent.openFrame('查看信息','${ctx}/user/keyword/img/Show/forward?wechatId='+wechatId+'&id=' + id,700,700);
}
</script>
</body>
</html>
