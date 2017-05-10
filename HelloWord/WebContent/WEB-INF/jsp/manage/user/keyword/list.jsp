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
		<th data-options="field:'keyword',align:'left', width:200" >关键词</th>
		<th data-options="field:'messageType',align:'center', width:50" >回复消息类型</th>
		<th data-options="field:'message',align:'left', width:350" >回复消息内容</th>
		<th data-options="field:'creatTime',align:'center', width:100" >上次修改时间</th>
		<th data-options="field:'operate',width:100,align:'center',formatter
					:function(value, rowData){return operate(rowData);}">操作</th>
		</tr>
	</thead>
</table>
<script type="text/javascript">
var wechatId = '${wechatId}';
var queryParams = {};
function doSearch(){
	queryParams.wechatId = wechatId;
	queryParams.createTimeStart=$("input[name='createTimeStart']").val();
	queryParams.createTimeEnd=$("input[name='createTimeEnd']").val();
	queryParams.messageType=$("input[name='messageType']").val();
	queryParams.keyword=$("input[name='keyword']").val();
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
		url:'${ctx}/user/keyword/listInfo',
		queryParams:queryParams,
		onLoadSuccess:function(data){
			$('#keywordList').datagrid('clearChecked');
        }
	});	 
});
function operate(rowData){
	var arr = [];
	arr.push("<a href='#' onclick='showKeyword(\""+rowData.id+"\",\""+rowData.module+"\")'> 查看</a>");
	arr.push("<a href='#' onclick='editKeyword(\""+rowData.id+"\",\""+rowData.module+"\")'> 编辑</a>");
	arr.push("<a href='#' onclick='deleteKeyword(\""+rowData.id+"\")'> 删除</a>");
	return arr.join(' | ');
}

function showKeyword(id,module){
	if(module == "Text") {
		openFrame('查看信息','${ctx}/user/keyword/text/Show/forward?wechatId='+wechatId+'&id=' + id,400,300);
	} else if(module == "Img") {
		openFrame('查看信息','${ctx}/user/keyword/img/Show/forward?wechatId='+wechatId+'&id=' + id,720,700);
	} else if(module == "Image") {
		openFrame('查看信息','${ctx}/user/keyword/image/Show/forward?wechatId='+wechatId+'&id=' + id,420,400);
	}
}
function editKeyword(id,module){
	if(module == "Text") {
		openFrame('编辑信息','${ctx}/user/keyword/text/Add/forward?wechatId='+wechatId+'&id=' + id,400,300);
	} else if(module == "Img") {
		openFrame('编辑信息','${ctx}/user/keyword/img/Add/forward?wechatId='+wechatId+'&id=' + id,720,700);
	} else if(module == "Image") {
		openFrame('编辑信息','${ctx}/user/keyword/image/Add/forward?wechatId='+wechatId+'&id=' + id,420,400);
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
			$.post("${ctx}/user/keyword/delete",{ids:ids},function(data){
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
function reloadList(){
	$('#keywordList').datagrid('reload');
}


</script>
</body>
</html>
