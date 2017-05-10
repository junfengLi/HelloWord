<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title></title>
<link rel="stylesheet" type="text/css" href="${ctx}/static/easyui/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/static/easyui/themes/icon.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/static/kindeditor/themes/default/default.css">
<link href="${ctx}/static/css/manage/index.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/static/css/manage/default.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/static/css/manage/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/static/js/base/jquery-1.9.0.js"></script>
<script type="text/javascript" src="${ctx}/static/js/base/index.js"></script>
<script type="text/javascript" src="${ctx}/static/js/base/main.js"></script>
<script type="text/javascript" src="${ctx}/static/layer-v2.1/layer/layer.js"></script>
<script type="text/javascript" src="${ctx}/static/easyui/jquery.easyui.js"></script>
<script type="text/javascript" src="${ctx}/static/easyui/easyui.form.js"></script>
<script type="text/javascript" src="${ctx}/static/easyui/easyui.lang.js"></script>
</head>
<body>
<div id="AdvanceDiv" class="easyui-layout" data-options="fit:true">
		<form id="addForm" method="post" action="${ctx}/user/userEdit"  style="width:100%;height:450px;">
		<div data-options="cache:false,region:'center',border:false"
			style="overflow: hidden">
				<table data-options="align:'center'" class="tableForm">
					<tr>
						<td width="10%" class="titleTd">登陆名:<font color="red">*</font></td>
						<td width="35%">${user.loginName}</td>
					</tr>
					<tr>
						<td class="titleTd">真实姓名：<font color="red">*</font></td>
						<td ><input class="easyui-validatebox"
							name="realName" id="realName" value="${user.realName }"
							data-options="required:true,validType:'maxlength[64]'"></td>
					</tr>
					<tr>
						<td class="titleTd">邮箱：<font color="red">*</font></td>
						<td ><input class="easyui-validatebox"
							name="email" id="email" value="${user.email }"
							data-options="required:true,validType:['maxlength[64]','email']"></td>
					</tr>
					<tr>
						<td class="titleTd">密码：<font color="red">*</font></td>
						<td ><input class="easyui-validatebox" type="password"
							name="password" id="password" value="${user.password }"
							data-options="required:true,validType:'maxlength[64]'"></td>
					</tr>
				</table>
				
		</div>
		<div class="windowBtnDiv" data-options="cache:false,region:'south',border:false">
			<div class="windowBtn"> 
				<input type="hidden" name="id" value="${user.id }" />
				<input	type="button" class="btnSubmit"	onclick="formSubmit('addForm')">
				<input type="button" class="btnClose" onclick="closeFrame()">

			</div>
		</div>
		</form>
	</div>
	<script>	
	function submitHandler(obj){
		if(obj.success){
			parent.setRealName(obj.realName);
			closeFrame();
		}
	}
	</script>
</body>
</html>
