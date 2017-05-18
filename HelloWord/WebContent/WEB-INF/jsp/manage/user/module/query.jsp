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
<div class="col-xs-2">
	<div class="widget-box">
		<div class="widget-header header-color-blue2">
			<h4 class="lighter smaller">列表模块</h4>
		</div>

		<div class="widget-body">
			<div class="widget-main padding-8">
				<div id="tree1" class="tree"></div>
			</div>
		</div>
	</div>
</div><!-- /.col -->
<div class="col-xs-10">
	<div class="page-header">
			<div class="alert alert-block alert-success">
				<a href="###" class="easyui-linkbutton" 
					 onclick="addMenuModule();">
					 <i class="icon-th-large"></i><span style="padding:5px;">新增模块列表</span></a>
				<a href="###" class="easyui-linkbutton" 
					 onclick="addImgModule();">
					 <i class="icon-picture"></i><span style="padding:5px;">新增模块内容</span></a>
				<a href="###" class="easyui-linkbutton" 
					 onclick="addFromMessage();">
					 <i class="icon-picture"></i><span style="padding:5px;">从图文消息新增模块内容</span></a>
			</div>
		</div><!-- /.page-header -->
		<div class="row">
			<div class="col-xs-12">
				<table id="grid-table"></table>
				<div id="grid-pager"></div>
			</div><!-- /.col -->
		</div><!-- /.row -->
	</div>
</div><!-- /.page-content -->
<%-- 
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
	</div> --%>

<script type="text/javascript">
var layer = null;
var queryData = {};
var wechatId = '${wechatId}';
var treeDataSource = null;
var remoteUrl = '${ctx}/module/selectTree';//动态树数据请求接口 
var DataSourceTree = function(options) {
	this._data 	= options.data;
	this._delay = options.delay;
}
var parent_id = '';
DataSourceTree.prototype.data = function(options, callback) {
	var self = this;
	var $data = null;
	if( !('text' in options || 'type' in options) ){
		$data = this._data;//the root tree
		parent_id = "";//load first level data
	} else if('type' in options && options['type'] == 'folder') {//it has children
		if('additionalParameters' in options && 'children' in options.additionalParameters) {
			parent_id = options.additionalParameters['id'];
		} else $data = {}//no data
		if(parent_id != null && parent_id != '') {//根据父节点id，请求子节点
			$.ajax({
				url: remoteUrl,
				data: {'wechatId':wechatId, 'id': parent_id},
				type: 'GET',
				dataType: 'json',
				success : function(data) {
					callback({ data: data.data });
				}
			});
		}
	}
	if($data != null)
		setTimeout(function(){callback({ data: $data });} , parseInt(Math.random() * 500) + 200);
};
var firstLoad = true;
$(function(){
   selectTree('');
	queryData.wechatId = wechatId;
	queryData.page = 1;
	pageInit();
	layui.use(['layer'], function(){layer = layui.layer;});
});
function selectTree(id){
	$.ajax({
	       url : remoteUrl ,//动态树数据请求接口 
	       data: {'wechatId':wechatId, 'id': id},
	       type : 'GET',
	       dataType : 'json',
	       cache : false,
	       success : function (data){
	    	   var treeDataSource = new DataSourceTree({
	    	        data : data.data
	    	    });
	    		//执行  ace_tree
	    	    $('#tree1').ace_tree({
	    			dataSource: treeDataSource ,
	    			loadingHTML:'<div class="tree-loading"><i class="icon-refresh icon-spin blue"></i></div>',
	    			'open-icon' : 'icon-minus',
	    			'close-icon' : 'icon-plus',
	    			'selectable' : true,
	    			
	    		}).on('loaded', function (evt, data) {
					if (firstLoad) {
						$('#tree1>.tree-folder>.tree-folder-header div').click();
					}	    			
    			}).on('extend', function (evt, data) {
    				if (data && data.additionalParameters) {
		    			var menuuid = data.additionalParameters['id'];
		    			queryData.menuUid = menuuid;
		    			searchResult();
					}
    			});
	       }
	   })
}
$(".page-content").resize(function(){
	var grid_selector = "#grid-table";
	jQuery(grid_selector).setGridWidth($(".col-xs-12").width());
});

function searchResult(){
	if (firstLoad) {
		firstLoad = false;
	} else {
		var name=$("#name").val();
		queryData.name=name;
		var mobile=$("#mobile").val();
		queryData.mobile=mobile;
		pageReload();
	}	
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
	      url : '${ctx}/module/listInfo',
	      datatype : "json",
	      postData : queryData,
	      mtype:'POST',
	      colModel : [ 
	                   {name : 'title',label: '标题', width : 100,sortable : false, align : 'center'}, 
	                   {name : 'module',label: '类型', width : 50,sortable : false, align : 'center'}, 
	                   {name : 'imgSort',label: '排序', width : 100,sortable : false, align : 'center'}, 
	                   //{name : 'isMessage',label: '是否作为图片消息', width : 50,sortable : false, align : 'center'}, 
	                   {name : 'updateTime',label: '上次修改时间', width : 100,sortable : false, align : 'center'}, 
	                   {name: 'flag', label: '操作', width: 100, sortable : false,align: 'center',formatter: 
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
		openFrame('查看信息','${ctx}/keyword/img/Show/forward?wechatId='+wechatId+'&id=' + id,800,700);
	} else if(module == "Image") {
		openFrame('查看信息','${ctx}/keyword/image/Show/forward?wechatId='+wechatId+'&id=' + id,420,400);
	}
}
function editKeyword(id,module){
	if(module == "Text") {
		openFrame('编辑信息','${ctx}/keyword/text/Add/forward?wechatId='+wechatId+'&id=' + id,400,300);
	} else if(module == "Img") {
		openFrame('编辑信息','${ctx}/keyword/img/Add/forward?wechatId='+wechatId+'&id=' + id,800,700);
	} else if(module == "Image") {
		openFrame('编辑信息','${ctx}/keyword/image/Add/forward?wechatId='+wechatId+'&id=' + id,420,400);
	}
}

function addMenuModule(){
	openFrame('新增信息','${ctx}/module/menu/Add/forward?wechatId=${wechatId }&menuUid=' + parent_id,700,600);
}
function addImgModule(){
	openFrame('新增信息','${ctx}/module/img/Add/forward?wechatId=${wechatId }&menuUid=' + parent_id,720,700);
}
function addFromMessage(){
	openFrame('新增信息','${ctx}/module/fromMessage?wechatId=${wechatId }&menuUid=' + parent_id,720,500);
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
					pageReload();
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
