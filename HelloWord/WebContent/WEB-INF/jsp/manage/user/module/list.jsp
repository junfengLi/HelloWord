<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Cache-Condivol" content="no-store" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<title></title>
</head>
<body>
<table id="keywordList">
	<thead>
		<tr>
		 <th data-options="field:'ck',checkbox:true"></th>   
		<th data-options="field:'title',align:'left', width:300" >标题</th>
		<th data-options="field:'module',align:'center', width:50" >模块类型</th>
		<th data-options="field:'imgSort',align:'center', width:50" >排序</th>
		<th data-options="field:'creatTime',align:'center', width:100" >上次修改时间</th>
		<th data-options="field:'operate',width:100,align:'center',formatter
					:function(value, rowData){return operate(rowData);}">操作</th>
		</tr>
	</thead>
</table>
<script type="text/javascript">
var wechatId = '${wechatId}';
queryParams.menuUid = '${menuUid}';
function doSearch(){
	queryParams.wechatId = wechatId;
	queryParams.createTimeStart=$("input[name='createTimeStart']").val();
	queryParams.createTimeEnd=$("input[name='createTimeEnd']").val();
	queryParams.title=$("input[name='title']").val();
	queryParams.isMenu=$("input[name='isMenu']").val();
	$('#keywordList').datagrid('reload');
}
$(function(){
	queryParams.wechatId = wechatId;
	$('#keywordList').datagrid({
		fitColumns:true,
		fit:true,
		singleSelect:true,
		selectOnCheck:false,
		checkOnSelect:false,
		pageSize:10,
		idField:'id', 
		pagination: true,
		url:'${ctx}/user/module/listInfo',
		queryParams:queryParams,
		onLoadSuccess:function(data){
			$('#keywordList').datagrid('clearChecked');
        }
	});	 
});
function operate(rowData){
	var arr = [];
	arr.push("<a href='#' onclick='showKeyword(\""+rowData.id+"\",\""+rowData.isMenu+"\")'>查看</a>");
	arr.push("<a href='#' onclick='editKeyword(\""+rowData.id+"\",\""+rowData.menuUid+"\",\""+rowData.isMenu+"\")'>编辑</a>");
	if(rowData.isMessage != '1'){
		arr.push("<a href='#' onclick='deleteKeyword(\""+rowData.id+"\")'>删除</a>");
	}
	return arr.join(' | ');
}

function showKeyword(id,isMenu){
	if(isMenu == '1'){
		openFrame('查看信息','${ctx}/user/module/menu/Show/forward?wechatId='+wechatId+'&id=' + id,640,400);
	} else {
		openFrame('查看信息','${ctx}/user/module/img/Show/forward?wechatId='+wechatId+'&id=' + id,720,700);
	}
	
}
function editKeyword(id,menuUid,isMenu){
	if(isMenu == '1'){
		openFrame('编辑信息','${ctx}/user/module/menu/Add/forward?wechatId='+wechatId+'&id=' + id + '&menuUid=' + menuUid,600,400);
	} else {
		openFrame('编辑信息','${ctx}/user/module/img/Add/forward?wechatId='+wechatId+'&id=' + id + '&menuUid=' + menuUid,720,700);
	}
}
function deleteKeyword(id){
	var ids = "";
	if(id){
		ids = id;
	} else {
		ids = getCheckedIds('#keywordList');
	}
	layer.confirm('是否要删除信息?', {icon: 3, title:'确认信息'}, function(index){
		if(index){
			var loadIndex = layer.load();
			$.post("${ctx}/user/module/delete",{ids:ids},function(data){
				layer.close(loadIndex);
				if (data.success) {
					layer.msg("删除成功");
					reloadList();
				} else {
					layer.alert('删除失败，请刷新后重试！');
				}
			},'json');
			layer.close(index);
		}
	});
}
function reloadList(reloadTree){
	if(reloadTree){
		var node = $('#menuTree').tree('getSelected');
		if(node)
			$('#menuTree').tree('reload', node.target);
	}
	
	$('#keywordList').datagrid('reload');
}


</script>
</body>
</html>
