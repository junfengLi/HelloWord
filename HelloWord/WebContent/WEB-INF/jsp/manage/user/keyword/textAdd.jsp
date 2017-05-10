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
<form id="messageTextForm" method="post" action="${ctx }/user/keyword/save">
	<div data-options="region:'center',cache:false,border:false">
		<table class="tableForm">
			<tr >
				<td width="20%" class="titleTd " >关键词：</td>
				<td >
				<input class="easyui-validatebox" style="width:240px" name="keyword" id="keyword" 
				data-options="required:true,validType:'maxlength[100]'" onblur="checkKeyword()" value="${keyword.keyword }"/></td>
			</tr>
			<tr >
				<td style="line-height: 25px;color: #FA413F;text-align: right;padding-right: 50px;" colspan="2" >多个关键词用“，”隔开</td>
			</tr>
			<tr>
				<td width="20%" class="titleTd " >回复内容：</td>
				<td >
					<textarea style="width: 240px; height:100px"  class="easyui-validatebox" 
					 id="content" name="content"
								data-options="required:true,validType:'maxlength[500]',captions:'内容'">${messageText.content }</textarea>				
				</td>
			</tr>
		</table>
	</div>
	<div data-options="region:'south',cache:false,border:false" class="windowBtnDiv" >
		<div class="windowBtn">
			<input type="hidden" name="wechatId" value="${wechatId }" />
			<input type="hidden" name="id" value="${keyword.id }" />
			<input type="hidden" name="messageTextId" value="${messageText.id }" />
			<input type="hidden" name="messageType" value="Text" />
			<input type="hidden" name="serviceType" value="Text" />
			<input type="button" class="btnSubmit" name="submitBtn" onclick="checkKeyword('messageTextForm')">
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
