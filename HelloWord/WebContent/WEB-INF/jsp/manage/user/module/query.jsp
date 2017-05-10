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
	<div  style="padding-right:6px;" data-options="width:200,region:'west',cache:false,border:false" >
		<ul id="menuTree"></ul>
	</div>
	<div data-options="region:'center',cache:false,border:false">
		<div id="queryContentDiv" class="easyui-layout" data-options="fit:true">
			<div class="query_tool" data-options="region:'north',height:60,cache:false,border:false">
			<div style="float:left;overflow:hidden;margin-left:15px;width: 100%;">
				<a href="###" class="easyui-linkbutton" 
					 onclick="addMenuModule();">
					 <i class="icon-th-large"></i><span style="padding:5px;">新增模块列表</span></a>
				<a href="###" class="easyui-linkbutton" 
					 onclick="addImgModule();">
					 <i class="icon-picture"></i><span style="padding:5px;">新增模块内容</span></a>
				<a href="###" class="easyui-linkbutton" 
					 onclick="addFromMessage();">
					 <i class="icon-picture"></i><span style="padding:5px;">从图文消息新增模块内容</span></a>
				<a href="###" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-delete'"
						onclick="deleteKeyword()">批量删除</a>
			</div>	
			<table style="float:right;" id="query_form">
				<tr>
					<td>创建时间：
							<input id="createTimeStart" name="createTimeStart" class="easyui-datebox" style="width:90px"></input> -- 
							<input id="createTimeEnd" name="createTimeEnd" class="easyui-datebox" style="width:90px"></input>
					</td>
					<td><label>标题：<input name="title" id="title"></label></td>
					<td>模块类型：
							<input name="isMenu" id="isMenu" type="text" class="easyui-combobox"
						data-options="editable:false,width:100,panelHeight:80,valueField:'id',textField:'text',
						data:[{id:'',text:'所有'},{id:'1',text:'模块列表'},{id:'0',text:'模块内容'}]">
					</td>
					</td>
					<td><input id="query_btn" type="button" class="btnSearch"  onclick="doSearch()" ></td>
				</tr>
			</table>
			</div>
			<div style="overflow-y:auto;"data-options="region:'center',cache:false,
			href:'${ctx}/user/module/list/forward?wechatId=${wechatId }',border:false"></div>
		</div>
	</div>
</div>

<script type="text/javascript">
var queryParams = {};
$(function () { 
	$('#menuTree').tree({  
	    lines:true,
		onBeforeExpand:function(node,param){   
			$('#menuTree').tree('options').url ='${ctx}/user/module/selectTree?wechatId=${wechatId }';
		},
		method:'get',
		data:[{ 
			id:'', 
			text:'根目录', 
			state:'closed'
		}], 
		onClick:function(node){   
			refreshRightDetail(node.id);
		}	
	});
	//展开第一级
	var node = $('#menuTree').tree('getRoot');
	$('#menuTree').tree('select', node.target);
	$('#menuTree').tree('expand', node.target);
});

function addMenuModule(){
	var nodeId = getSelectId();
	openFrame('新增信息','${ctx}/user/module/menu/Add/forward?wechatId=${wechatId }&menuUid=' + nodeId,600,400);
}
function addImgModule(){
	var nodeId = getSelectId();
	openFrame('新增信息','${ctx}/user/module/img/Add/forward?wechatId=${wechatId }&menuUid=' + nodeId,720,700);
}
function addFromMessage(){
	var nodeId = getSelectId();
	openFrame('新增信息','${ctx}/user/module/fromMessage?wechatId=${wechatId }&menuUid=' + nodeId,720,500);
}
//刷新右边展现信息
function refreshRightDetail(nodeId){
	if(!nodeId){
		nodeId = getSelectId();
	}
	var url = "${ctx}/user/module/list/forward?wechatId=${wechatId }&menuUid="+nodeId;
	refreshUrl('queryContentDiv',url);
}

function getSelectId(){
	var nodeId = "";
	var node = $('#menuTree').tree('getSelected');
	if(node != null)
		nodeId = node.id;
	return nodeId;
}

</script>
</body>
</html>
