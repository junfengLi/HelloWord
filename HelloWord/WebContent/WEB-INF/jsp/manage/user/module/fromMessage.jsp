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
<jsp:include page="../../include/css-js.jsp" />de>
</head>
<body>
<div id="fromMessage" class="easyui-layout" data-options="fit:true">
	<div data-options="region:'center',cache:false,border:false">
		<div id="fromMessageDiv" class="easyui-layout" data-options="fit:true">
			<div class="query_tool" data-options="region:'north',cache:false,border:false">
				<table style="float:right;" id="query_form">
					<tr>
						<td>创建时间：
								<input id="createTimeStart" name="createTimeStart" class="easyui-datebox" style="width:90px"></input> -- 
								<input id="createTimeEnd" name="createTimeEnd" class="easyui-datebox" style="width:90px"></input>
						</td>
						<td><label>关键词：<input name="keyword" id="keyword"></label></td>
						<td><input id="query_btn" type="button" class="btnSearch"  onclick="doSearch()" ></td>
					</tr>
				</table>
			</div>
			<div style="overflow-y:auto;"data-options="region:'center',cache:false,border:false">
			<table id="messageImgList">
				<thead>
					<tr>
					 <th data-options="field:'ck',checkbox:true"></th>   
					<th data-options="field:'keyword',align:'left', width:200" >关键词</th>
					<th data-options="field:'message',align:'left', width:350" >回复消息内容</th>
					<th data-options="field:'creatTime',align:'center', width:100" >上次修改时间</th>
					<th data-options="field:'operate',width:100,align:'center',formatter
								:function(value, rowData){return operate(rowData);}">操作</th>
					</tr>
				</thead>
			</table>
			</div>
		</div>
	</div>
	<div data-options="region:'south',cache:false,border:false" class="windowBtnDiv" >
		<div class="windowBtn">
			<input type="button" class="btnClose"  name="closeBtn" onclick="closeFrame();">
		</div>
	</div>
</div>

<script type="text/javascript">
var wechatId = '${wechatId}';
var menuUid = '${menuUid}';

var queryParams = {messageType:'Img',isSite:'0'};
function doSearch(){
	queryParams.wechatId = wechatId;
	queryParams.createTimeStart=$("input[name='createTimeStart']").val();
	queryParams.createTimeEnd=$("input[name='createTimeEnd']").val();
	queryParams.keyword=$("input[name='keyword']").val();
	$('#messageImgList').datagrid('reload');
}
$(function(){
	queryParams.wechatId = wechatId;
	$('#messageImgList').datagrid({
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
			$('#messageImgList').datagrid('clearChecked');
        },
        onDblClickRow:function(rowIndex, rowData){
        	parent.editKeyword(rowData.messageId, menuUid);
        	closeFrame();
        }
	});	 
});
function operate(rowData){
	var arr = [];
	arr.push('<a href="#" onclick="parent.showKeyword(\''+rowData.id+'\')"> 查看</a>');
	return arr.join(' | ');
}

function reloadList(){
	$('#messageImgList').datagrid('reload');
}


</script>
</body>
</html>
