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
				<td width="25%" class="titleTd " >关键词：</td>
				<td >${keyword.keyword }</td>
			</tr>
			<tr>
				<td width="25%" class="titleTd " >回复内容：</td>
				<td >${messageText.content }</td>
			</tr>
			<tr >
				<td class="titleTd" width="25%">上次修改时间：</td>
				<td colspan="3" width="70%" >${creatTime }</td>
			</tr>	
		</table>
	</div>
	<div data-options="region:'south',cache:false,border:false" class="windowBtnDiv" >
		<div class="windowBtn">
			<input type="button" class="btnClose"  name="closeBtn" onclick="closeFrame();">
		</div>
	</div>
</div>
</body>
</html>
