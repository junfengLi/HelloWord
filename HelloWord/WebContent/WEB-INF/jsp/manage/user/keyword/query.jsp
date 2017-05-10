<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
			<div class="query_tool" data-options="region:'north',cache:false,border:false,height:60">
			<div style="float:left;overflow:hidden;margin-left:15px;width:100%">
				<a href="###" class="easyui-linkbutton" 
					 onclick="openFrame('新增信息','${ctx}/user/keyword/text/Add/forward?wechatId=${wechatId }',400,300);">
					 <i class="icon-bold"></i><span style="padding:5px;">新增文本消息</span></a>
				<a href="###" class="easyui-linkbutton" 
					 onclick="openFrame('新增信息','${ctx}/user/keyword/img/Add/forward?wechatId=${wechatId }',720,700);">
					 <i class="icon-picture"></i><span style="padding:5px;">新增图文消息</span></a>
				<c:if test="${fn:contains(wechat.wechatType,'_R')}">
					<a href="###" class="easyui-linkbutton" 
						 onclick="openFrame('新增信息','${ctx}/user/keyword/image/Add/forward?wechatId=${wechatId }',420,400);">
						 <i class="icon-picture"></i><span style="padding:5px;">新增图片消息</span></a>
				</c:if>
				<a href="###" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-delete'"
						onclick="deleteKeyword()">批量删除</a>
			</div>	
			<table style="float:right;" id="query_form">
				<tr>
					<td>创建时间：
							<input id="createTimeStart" name="createTimeStart" class="easyui-datebox" style="width:90px"></input> -- 
							<input id="createTimeEnd" name="createTimeEnd" class="easyui-datebox" style="width:90px"></input>
					</td>
					<td><label>关键词：<input name="keyword" id="keyword"></label></td>
					<td>消息类型：
							<input name="messageType" id="messageType" type="text" class="easyui-combobox"
						data-options="editable:false,width:100,panelHeight:120,valueField:'id',textField:'text',
						data:[{id:'',text:'所有'},{id:'Text',text:'文本消息'},{id:'Img',text:'图文消息'}
						<c:if test="${fn:contains(wechat.wechatType,'_R')}">,{id:'Image',text:'图片消息'}</c:if>
						]">
					</td>
					<td><input id="query_btn" type="button" class="btnSearch"  onclick="doSearch()" ></td>
				</tr>
			</table>
			</div>
			<div style="overflow-y:auto;"data-options="region:'center',cache:false,
			href:'${ctx}/user/keyword/list/forward?wechatId=${wechatId }',border:false"></div>
		</div>
	</div>
</div>

<script type="text/javascript">

</script>
</body>
</html>
