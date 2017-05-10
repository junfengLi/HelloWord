<!DOCTYPE html>
<%@page import="com.web.commons.util.ConfigUtil"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title></title>
<jsp:include page="../css-js.jsp"></jsp:include>
</head>
<body>
<div id="AdvanceDiv" class="easyui-layout" data-options="fit:true">
<form id="addMenuForm" method="post" action="${ctx }/user/menu/save">
	<div data-options="region:'center',cache:false,border:false">
		<table class="tableForm">
			<tr>
				<td width="30%" class="titleTd " >菜单名称：</td>
				<td width="70%"><input class="easyui-validatebox" name="name" id="name" 
				data-options="required:true,validType:'maxlength[7]'" value="${button.name }"></td>
			</tr>
			<tr>
				<td width="20%" class="titleTd ">属于（分组）：</td>
				<td >
				<input id="pid" name="pid" class="easyui-combobox" data-options="   
						required:true, 
				        valueField: 'id',    
				        textField: 'text',    
				        url: '${ctx }/user/menu/selectCombo?wechatId=${wechatId }&id=${button.id }', 
				        editable:false,
				        value:'${button.pid}',
				        panelHeight:160" />   
				</td>
			</tr>
			<tr>
				<td class="titleTd ">排序：</td>
				<td >
					<input value="500" class="easyui-validatebox" id="buttonSort" name="buttonSort" 
					data-options="required:true,validType:'numbers[3]',tipPosition:'left'"
					style="width:40px;"  value="${button.buttonSort }" /><span style="color:red;"> 请输入3位数字如：000/999</span>
				</td>
			</tr>
			<tr>
				<td width="20%" class="titleTd " >菜单类型：</td>
				<td >
					<label><input type="radio" name="buttonType" value="click" checked="checked" />事件类型</label>
					<label><input type="radio" name="buttonType" value="view"  
					<c:if test="${button.buttonType=='view' }">checked="checked"</c:if> />超链接类型</label>
				</td>
			</tr>
			<tr class="tr_keyWord" <c:if test="${button.buttonType=='view' }">style="display:none;"</c:if>>
				<td width="20%" class="titleTd " >触发关键词：</td>
				<td >
				<input class="easyui-validatebox" style="width:150px" name="menuKey" id="menuKey" 
				<c:if test="${button.buttonType=='click' }">data-options="required:true"</c:if> value="${button.menuKey }"/></td>
			</tr>
			<tr class="tr_setUrl" <c:if test="${button.buttonType=='click' }">style="display:none;"</c:if>>
				<td width="20%" class="titleTd " >跳转URL：</td>
				<td >
				<input class="easyui-validatebox" style="width:150px" name="url" id="url" 
				data-options="<c:if test="${button.buttonType=='view' }">required:false,</c:if>validType:'url'" value="${button.url }"/></td>
			</tr>
			<%-- <tr class="tr_setUrl">
				<td width="20%" class="titleTd " >OAuth2.0授权：</td>
				<td >
					<label><input type="radio" name="isOAuth" value="0" checked="checked" />不授权</label>
					<label><input type="radio" name="isOAuth" value="1"
					 <c:if test="${button.isOAuth=='1' }"> checked="checked"</c:if>  />授权</label>
				</td>
			</tr> --%>
		</table>
	</div>
	<div data-options="region:'south',cache:false,border:false" class="windowBtnDiv" >
		<div class="windowBtn">
			<input type="hidden" name="wechatId" value="${wechatId }" />
			<input type="hidden" name="id" value="${button.id }" />
			<input type="button" class="btnSubmit" name="submitBtn" onclick="formSubmit ('addMenuForm')">
			<input type="button" class="btnClose"  name="closeBtn" onclick="closeFrame();">
		</div>
	</div>
</form>
</div>
<script>
function submitHandler(obj){
	if(obj.success){
		parent.reloadList();
		closeFrame();
	}
}
$(function(){
	$("input[name='buttonType']").click(function(){
		var value = $(this).val();
		if(value == 'view'){
			$(".tr_setUrl").show();
			$("#url").validatebox({required: true,validType:'url'}); 
			$("#menuKey").validatebox({required: false});  
			$(".tr_keyWord").hide();
		} else {
			$("#url").validatebox({required: false,validType:''}); 
			$(".tr_setUrl").hide();
			$(".tr_keyWord").show();
			$("#menuKey").validatebox({required: true});  
		}
	});
});
</script>
</body>
</html>
