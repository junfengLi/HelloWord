<%@page import="com.web.commons.util.ConfigUtil"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<jsp:include page="../../include/css-js.jsp" />
</head>
<body>
	<div id="AdvanceDiv" class="easyui-layout" data-options="fit:true">
	<div data-options="region:'center',cache:false,border:false">
		<table class="tableFormShow">
			<c:if test="${messageImg.isMessage == '1' }">
			<tr >
				<td width="25%" class="titleTd " >关键词：</td>
				<td  colspan="3" >
				${keyword.keyword }
				</td>
			</tr>
			</c:if>
			<tr >
				<td class="titleTd" width="20%">标题：<font color="red">*</font></td>
				<td colspan="3" width="70%" >${messageImg.title }</td>
			</tr>		
			<tr>
			<td class="titleTd">封图：</td>
			<td colspan="3">
				<div style="padding-right:10px;float:left;">
					<img id="photoImg" width="200" height="80" 
					style="display: block;"
					<c:choose>
				 		<c:when test="${not empty accessory}">   
					    	src="${accessory.url}" 
					  	</c:when>
					  	<c:otherwise>   
					    	src="${ctx }/static/images/wechat/wechat_photo.png" 
					  	</c:otherwise> 
					</c:choose>
					/></div>
					
			</td>	
			</tr>
			<tr>
				<td class="titleTd">模板样式：</td>
				<td colspan="3">${demo }</td>
			</tr>
			<tr>
				<td class="titleTd">简介：</td>
				<td colspan="3">${messageImg.description }</td>
			</tr>
			<tr>
				<td class="titleTd">外链地址：</td>
				<td colspan="3">${messageImg.setUrl }</td>
			</tr>
			<tr >
				<td class="titleTd" width="25%">上次修改时间：</td>
				<td colspan="3" width="70%" >${updateTime }</td>
			</tr>	
		</table>
	</div>
	<div data-options="region:'south',cache:false,border:false" class="windowBtnDiv" >
		<div class="windowBtn">
			<input type="button" class="btnClose"  name="closeBtn" onclick="closeFrame();">
		</div>
	</div>
</div>
<script>
$(function(){
});
function cancleMenu(id){
	var loadIndex = layer.load();
	$.post("${ctx}/user/keyword/cancleMenu",{id:id},function(data){
		layer.close(loadIndex);
		if (data.success) {
			layer.msg("取消关联成功");
			$("#cancleMenu-td").html("已取消关联");
		} else {
			layer.alert('取消关联失败，请刷新后重试！');
		}
	},'json');
	layer.close(index);
}
</script>
</body>
</html>
