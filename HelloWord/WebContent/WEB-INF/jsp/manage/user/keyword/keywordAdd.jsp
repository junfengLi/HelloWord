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
<form id="messageKeywordForm" method="post" action="${ctx }/user/keyword/save">
	<div data-options="region:'center',cache:false,border:false">
		<table class="tableForm">
			<tr >
				<td width="30%" class="titleTd " ></td>
				<td >${serviceTypeText }</td>
			</tr>
			<tr >
				<td width="30%" class="titleTd " >关联关键词：</td>
				<td >
				<input class="easyui-validatebox" style="width:240px" name="messageType" id="messageType" 
				data-options="required:true,validType:'maxlength[100]'" onblur="checkKeyword()" value="${keyword.messageType }"/></td>
			</tr>
		</table>
	</div>
	<div data-options="region:'south',cache:false,border:false" class="windowBtnDiv" >
		<div class="windowBtn">
			<input type="hidden" name="wechatId" value="${wechatId }" />
			<input type="hidden" name="id" value="${keyword.id }" />
			<input type="hidden" name="messageTextId" value="${messageText.id }" />
			<input type="hidden" name="serviceType" value="${serviceType }" />
			<input type="hidden" name="keyword" value="${serviceType }" />
			<input type="button" class="btnSubmit" name="submitBtn" onclick="checkKeyword('messageKeywordForm')">
			<input type="button" class="btnClose"  name="closeBtn" onclick="closeFrame();">
		</div>
	</div>
</form>
</div>
<script>
var module = '${module}';
var wechatId = '${wechatId}';
var keywordId = '${keyword.id }';
function submitHandler(obj){
	if(obj.success){
		parent.reloadList();
		closeFrame();
	}
}

function checkKeyword(formId){
	var keyword = $('#messageType').val();
	var isValid = $('#messageType').validatebox('isValid');
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
