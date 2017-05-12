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
<form id="messageImageForm" method="post" action="${ctx }/user/keyword/save">
	<div data-options="region:'center',cache:false,border:false">
		<table class="tableForm">
			<tr >
				<td width="25%" class="titleTd " >关键词：</td>
				<td >
				<input class="easyui-validatebox" style="width:200px" name="keyword" id="keyword" 
				data-options="required:true,validType:'maxlength[100]'" onblur="checkKeyword()" value="${keyword.keyword }"/>
				<br><span style="color: #FA413F; padding-top: 10px; display: block;">多个关键词用“，”隔开</span>
				</td>
			</tr>
			<tr>
				<td class="titleTd " >回复图片：</td>
				<td  >
					<input type="hidden" id="accessoryIds" name="accessoryIds"/>
					<div style="padding-right:10px;float:left;">
						<img id="photoImg" style="max-width: 200px;max-height: 150px;" 
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
						  padding-top: 9px;"> * 单图文建议图片尺寸（200x50）</span>
					</div>
				</td>
			</tr>										
		</table>
	</div>
	<div data-options="region:'south',cache:false,border:false" class="windowBtnDiv" >
		<div class="windowBtn">
			<input type="hidden" name="wechatId" value="${wechatId }" />
			<input type="hidden" name="id" value="${keyword.id }" />
			<input type="hidden" name="messageImageId" value="${messageImage.id }" />
			<input type="hidden" name="serviceType" value="Text" />
			<input type="hidden" name="messageType" value="Image" />
			<input type="button" class="btnSubmit" name="submitBtn" onclick="checkKeyword('messageImageForm')">
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
	initUploadButton("uploadButton", "photoImg", "accessoryIds",'<%=ConfigUtil.MODULE_WECHAT_IMAGE %>', 'image', 3*1024*1024, 200, 50,true);
});
function submitHandler(obj){
	if(obj.success){
		parent.reloadList();
		closeFrame();
	}
}

function checkKeyword(formId){
	var keyword = $('#keyword').val();
	var isValid = $('#keyword').validatebox('isValid');
	if (isValid) {
		$.post('${ctx}/user/keyword/checkKeyword',{module:module,keywordId:keywordId,keyword:keyword,wechatId:wechatId},function(data){
			if(!data.success){
				layer.alert(data.checkKeyword);
			} else {
				if(formId){
					formSubmit(formId);
				}
			}
		},'json');
	}
}
</script>
</body>
</html>
