<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<jsp:include page="../css-js.jsp"></jsp:include>
</head>
<body>
	<div id="AdvanceDiv" class="easyui-layout" data-options="fit:true">
	<div data-options="region:'center',cache:false,border:false">
		<table class="tableFormShow">
			<tr >
				<td width="25%" class="titleTd " >请求类型：</td>
				<td >${serviceTypeText }</td>
			</tr>
			<tr >
				<td width="25%" class="titleTd " >关联关键词：</td>
				<td >${keyword.messageType }</td>
			</tr>
			<tr >
				<td class="titleTd" width="25%">上次修改时间：</td>
				<td colspan="3" width="70%" >${creatTime }</td>
			</tr>	
			<c:forEach items="${maps }" var="map">
			<tr>
				<td width="25%" class="titleTd " >回复类型：</td>
				<td >${map.messageType }</td>
			</tr>
			<tr>
				<td width="25%" class="titleTd " >回复内容：</td>
				<td >${map.content }
				<c:if test="${map.messageType == 'Img' }">
				<span> [<a href="###" style="color:blue" onclick="showKeyword('${map.id }')">查看</a>] </span>
				</c:if>
				</td>
			</tr>
			</c:forEach>
		</table>
	</div>
	<div data-options="region:'south',cache:false,border:false" class="windowBtnDiv" >
		<div class="windowBtn">
			<input type="button" class="btnClose"  name="closeBtn" onclick="closeFrame();">
		</div>
	</div>
</div>
<script type="text/javascript">
var wechatId = '${wechatId}';
function showKeyword(id){
	parent.openFrame('查看信息','${ctx}/user/keyword/img/Show/forward?wechatId='+wechatId+'&id=' + id,700,700);
}
</script>
</body>
</html>
