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
<div class="page-content">
	<div class="page-header">
		<div class="alert alert-block alert-success">
			<a href="#" onclick="openFrame('新增文本消息','${ctx}/keyword/text/Add/forward?wechatId=${wechatId }',400,300);" ><i class="icon-legal home-icon">新增文本消息</i></a>
			<a href="#" onclick="openFrame('新增图文消息','${ctx}/keyword/img/Add/forward?wechatId=${wechatId }',800,700);" ><i class="icon-legal home-icon">新增图文消息</i></a>
			<%-- <c:if test="${fn:contains(wechat.wechattype,'_R')}">
				<a href="#" onclick="openFrame('添加信息','${ctx}/keyword/image/Add/forward?wechatId=${wechatId }',420,400);" ><i class="icon-legal home-icon">新增图片消息</i></a>
			</c:if> --%>
			<!-- <a href="###" class=""	onclick="deleteKeyword()"><i class="icon-legal home-icon">批量删除</i></a> -->
		</div>
		<div>
		<%-- <table style="float:right;" id="query_form">
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
			</table> --%>
		</div>
	</div><!-- /.page-header -->

	<div class="row">
		<div class="col-xs-12">
			<table id="grid-table"></table>
			<div id="grid-pager"></div>
		</div><!-- /.col -->
	</div><!-- /.row -->
</div><!-- /.page-content -->

<script type="text/javascript">
var layer = null;
var queryData = {};
var wechatId = '${wechatId}';
$(function(){
	queryData.wechatId = wechatId;
	queryData.page = 1;
	pageInit();
	layui.use(['layer'], function(){layer = layui.layer;});
});

$(".page-content").resize(function(){
	var grid_selector = "#grid-table";
	jQuery(grid_selector).setGridWidth($(".col-xs-12").width());
});

function searchResult(){
	var name=$("#name").val();
	queryData.name=name;
	var mobile=$("#mobile").val();
	queryData.mobile=mobile;
	pageReload();
}

function pageReload(){
	var grid_selector = "#grid-table";
	var pager_selector = "#grid-pager";
	jQuery(grid_selector).jqGrid("setGridParam", { postData: queryData });
	jQuery(grid_selector).trigger("reloadGrid");
}
function pageInit(){
	var grid_selector = "#grid-table";
	var pager_selector = "#grid-pager";
	
	jQuery(grid_selector).jqGrid({
	      url : '${ctx}/keyword/listInfo',
	      datatype : "json",
	      postData : queryData,
	      mtype:'POST',
	      colModel : [ 
	                   {name : 'keyword',label: '关键词', width : 100,sortable : false, align : 'center'}, 
	                   {name : 'messageType',label: '回复消息类型', width : 50,sortable : false, align : 'center'}, 
	                   {name : 'message',label: '回复消息内容', width : 100,sortable : false, align : 'center'}, 
	                   {name : 'createTime',label: '上次修改时间', width : 100,sortable : false, align : 'center'}, 
	                   {name: 'flag', label: '操作', width: 50, sortable : false,align: 'center',formatter: 
	                	   function (cellvalue, options, rowObject) {return operate(cellvalue, options, rowObject);}},
	                 ],
	      rowNum : 10,
	      rowList : [ 10, 20, 30 ],
	      pager : pager_selector,
	      sortname : 'id',
	      viewrecords : true,
	      autowidth: true,
	      sortorder : "desc",
	      rownumbers : true,
	      caption : "图文管理",
	      height: 'auto',
	      loadComplete : function() {
	    	  $(grid_selector).setGridParam().hideCol("isrefuse").trigger("reloadGrid");
	    	  $(grid_selector).setGridHeight($(window).height() - 290);  
				var w2 = parseInt($('.ui-jqgrid-labels>th:eq(0)').css('width'))-3;  
				$('.ui-jqgrid-lables>th:eq(0)').css('width',w2);  
				$('#grid-table tr').find("td:eq(0)").each(function(){  
					$(this).css('width',w2);  
				});
				var table = this;
				setTimeout(function(){
					updatePagerIcons(table);
				}, 0);
			},
			rowattr: function (rd) { //grid配置  
				if (rd.isrefuse == '1') {  
	                return {"style": "color:#b9b9b9;"};  
	            } else {
	            	 if (rd.backtime != '' && rd.backtime != undefined) {  
	            		 return {"style": "color:#00bf00;"};   
	 	            }  
	            	return {"style": "color:black;"};  
	            } 
	            return null;  
	        },
	        recordtext:"第 {0} - {1} 条，共 {2} 页",
	    });
}	

function updatePagerIcons(table) {
	var replacement = 
	{
		'ui-icon-seek-first' : 'icon-double-angle-left bigger-140',
		'ui-icon-seek-prev' : 'icon-angle-left bigger-140',
		'ui-icon-seek-next' : 'icon-angle-right bigger-140',
		'ui-icon-seek-end' : 'icon-double-angle-right bigger-140'
	};
	$('.ui-pg-table:not(.navtable) > tbody > tr > .ui-pg-button > .ui-icon').each(function(){
		var icon = $(this);
		var $class = $.trim(icon.attr('class').replace('ui-icon', ''));
		
		if($class in replacement) icon.attr('class', 'ui-icon '+replacement[$class]);
	})
}
function operate(cellvalue, options, rowObject){
	var arr = [];
	arr.push("<a href='#' onclick='showKeyword(\""+rowObject.id+"\",\""+rowObject.module+"\")'> 查看</a>");
	arr.push("<a href='#' onclick='editKeyword(\""+rowObject.id+"\",\""+rowObject.module+"\")'> 编辑</a>");
	arr.push("<a href='#' onclick='deleteKeyword(\""+rowObject.id+"\")'> 删除</a>");
	return arr.join(' | ');
}

function showKeyword(id,module){
	if(module == "Text") {
		openFrame('查看信息','${ctx}/keyword/text/Show/forward?wechatId='+wechatId+'&id=' + id,400,300);
	} else if(module == "Img") {
		openFrame('查看信息','${ctx}/keyword/img/Show/forward?wechatId='+wechatId+'&id=' + id,720,700);
	} else if(module == "Image") {
		openFrame('查看信息','${ctx}/keyword/image/Show/forward?wechatId='+wechatId+'&id=' + id,420,400);
	}
}
function editKeyword(id,module){
	if(module == "Text") {
		openFrame('编辑信息','${ctx}/keyword/text/Add/forward?wechatId='+wechatId+'&id=' + id,400,300);
	} else if(module == "Img") {
		openFrame('编辑信息','${ctx}/keyword/img/Add/forward?wechatId='+wechatId+'&id=' + id,720,700);
	} else if(module == "Image") {
		openFrame('编辑信息','${ctx}/keyword/image/Add/forward?wechatId='+wechatId+'&id=' + id,420,400);
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
			$.post("${ctx}/keyword/delete",{ids:ids},function(data){
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
</script>
</body>
</html>
