<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Cache-Control" content="no-store" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<title></title>
</head>
<body>
<div id="queryDiv" class="easyui-layout" data-options="fit:true">
	<div data-options="region:'center',cache:false,border:false">
		<div id="queryContentDiv" class="easyui-layout" data-options="fit:true">
			<div class="query_tool" data-options="region:'north',cache:false,border:false">
			<div style="float:left;overflow:hidden;">
				<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-create'"
				 onclick="openFrame('新增信息','${ctx}/user/menu/add/forward?wechatId=${wechatId }',600,400);">新增</a>
			</div>
			<div style="float:right;">
			</div>
			</div>
			<div style="overflow-y:auto;"data-options="region:'center',cache:false,
			href:'${ctx}/user/menu/list/forward?wechatId=${wechatId }',border:false"></div>
		</div>
	</div>
</div>

<script type="text/javascript">
function reloadList(){
	$('#queryContentDiv').layout('panel','center').panel('refresh','${ctx}/user/menu/list/forward?wechatId=${wechatId }');
}

</script>
</body>
</html>
