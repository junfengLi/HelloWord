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
<form id="menuForm" method="post" action="${ctx }/user/module/menuSave">
	<div data-options="region:'center',cache:false,border:false">
		<table class="tableForm">
			<tr >
				<td class="titleTd" width="25%">标题：<font color="red">*</font></td>
				<td colspan="3" width="75%" >
				<input class="easyui-validatebox" type="text" name="title" 
				style="width:300px;"  value="${messageImg.title }"
				data-options="required:true,tipPosition:'right',validType:'maxlength[100]'"></td>
			</tr>
			<tr>
				<td class="titleTd">网站排序：<font color="red">*</font></td>
				<td><input class=" easyui-validatebox" style="width:50px; float: left;"
					name="imgSort" id="description" value="${messageImg.imgSort }"
					data-options="required:true,validType:'numbers[3]'">
					<span style=" display: block; text-align: center;  color: red;"> 请输入三位排序码如：999 </span>
				</td>
			</tr>
			<tr>
				<td class="titleTd">模板样式：<font color="red">*</font></td>
				<td >
				<input class="easyui-combobox" 
							name="demo" 
							data-options="required:true,
									url:'${ctx}/user/wsite/demoTree?demoType=list',
									valueField:'id',
									textField:'text',
									editable:false,
									panelHeight:'auto',
									value:'${demo}'
							">
				</td>
			</tr>								
			<tr>
			<td class="titleTd">封图：</td>
			<td colspan="3">
				<input type="hidden" id="accessoryIds" name="accessoryIds"/>
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
					<div style=" width:150px;float:left; height:25px;text-align: center;    margin-top: 15px;">
					<input type="button" id="uploadButton" value="上传照片" />
					<span style="  width:150px;  display: block;  text-align: center;  color: red;
					  padding-top: 9px;"> * 建议图片尺寸（200x50）</span>
				</div>
			</td>	
			</tr>
			<tr>
				<td class="titleTd">简介：</td>
				<td colspan="3"><textarea style="width:300px;padding:5px;"
						rows="3" id="description" name="description"
						data-options="required:true,validType:'maxlength[500]'">${messageImg.description }</textarea>
				</td>
			</tr>
		</table>
	</div>
	<div data-options="region:'south',cache:false,border:false" class="windowBtnDiv" >
		<div class="windowBtn">
			<input type="hidden" name="wechatId" value="${wechatId }" />
			<input type="hidden" name="menuUid" value="${menuUid }" />
			<input type="hidden" name="id" value="${messageImg.id }" />
			<input type="hidden" name="isSite" value="1" />
			<input type="hidden" name="isMenu" value="1" />
			<input type="hidden" name="isMessage" value="0" />
			<input type="button" class="btnSubmit" name="submitBtn" onclick="formSubmit('menuForm')">
			<input type="button" class="btnClose"  name="closeBtn" onclick="closeFrame();">
		</div>
	</div>
</form>
</div>
<script>
var module = '${module}';
var wechatId = '${wechatId}';
var keywordId = '${keyword.id }';
$(function(){
	window.editor = KindEditor.create('#content',{height : '300px'});
	initUploadButton("uploadButton", "photoImg", "accessoryIds",'<%=ConfigUtil.MODULE_WECHAT_IMG %>', 'image', 3*1024*1024, 200, 50,true);
});
function submitHandler(obj){
	if(obj.success){
		parent.reloadList('reloadTree');
		closeFrame();
	}
}
</script>
</body>
</html>
