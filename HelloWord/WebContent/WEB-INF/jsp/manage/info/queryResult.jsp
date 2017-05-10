<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html lang="en">
<head>
<meta charset="utf-8" />
<title>Bootstrap控制台</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>

<body style="overflow-x:hidden;">
<div class="page-content">
	<div class="page-header">
		<ul class="breadcrumb">
			<li>
				<a href="#" onclick="openFrame('添加信息','${ctx }/info/add/forward',650,550);" ><i class="icon-legal home-icon">添加</i></a>
			</li>
		</ul><!-- .breadcrumb -->
		<div style="float:right;">
			<table  class="">
				<tr>
					<td style="width:90px; text-align: right;"> 进度选择：</td>
					<td >
					<select class="form-control input-small" id="form-field-select-1" name="speed" style="width:120px;">
						<c:forEach items="${nodes }" var="node" >
							<option value="${node.id }">${node.text }</option>
						</c:forEach>
					</select>
					</td>
					<td style="width:90px; text-align: right;"> 开始时间：</td>
					<td style="width:190px;">
					<input class="form-control input-small date-picker" id="id-date-picker-1" type="text" name="starttime" style="float: left; display: block;" />
					<span class="input-group-addon" style="float: left;height: 34px;line-height: 23px; width:35px;"><i class="icon-calendar bigger-110"></i></span>
					<span class="input-group-addon clearDate"  style="float: left;height: 34px;line-height: 23px;width:35px;"><i class="icon-remove"></i></span>
					</td>
					<td style="width:90px; text-align: right;"> 截止时间：</td>
					<td style="width:190px;">
					<input class="form-control input-small date-picker" id="id-date-picker-2" type="text" name="endtime" style="float: left; display: block; " />
					<span class="input-group-addon" style="float: left;height: 34px;line-height: 23px; width:35px;"><i class="icon-calendar bigger-110"></i></span>
					<span class="input-group-addon clearDate"  style="float: left;height: 34px;line-height: 23px;width:35px;"><i class="icon-remove"></i></span>
					</td>
					<td style="width:20px; cursor: pointer;" onclick="searchResult();"><i class="icon-search nav-search-icon bigger-160"></i></td>
				</tr>
			</table>
		</div>
	</div><!-- /.page-header -->

	<div class="row">
		<div class="col-xs-12">
			<!-- PAGE CONTENT BEGINS -->
			<table id="grid-table"></table>
			<div id="grid-pager"></div>
			<!-- PAGE CONTENT ENDS -->
		</div><!-- /.col -->
	</div><!-- /.row -->
</div><!-- /.page-content -->

<script type="text/javascript">
var layer = null;
var queryData = {};
$(function(){
	queryData.speed="F";
	$('.input-group-addon.clearDate').click(function(){
		$(this).prev().prev().val('');
	});
	$('.date-picker').datepicker({
		dateFormat: "yy-mm-dd"
		}); 
	$('#id-date-picker-1').datepicker({autoclose:true}).next().on(ace.click_event, function(){
		$(this).prev().focus();
	});
	$('#id-date-picker-2').datepicker({autoclose:true}).next().on(ace.click_event, function(){
		$(this).prev().focus();
	});
	queryData.userid='${userid}';
	queryData.page = 1;
	pageInit();
	layui.use(['layer'], function(){layer = layui.layer;});
});

$(".page-content").resize(function(){
	var grid_selector = "#grid-table";
	jQuery(grid_selector).setGridWidth($(".col-xs-12").width());
});

function searchResult(){
	var speed=$("#form-field-select-1").val();
	queryData.speed=speed;
	var starttime=$("#id-date-picker-1").val();
	queryData.starttime=starttime;
	var endtime=$("#id-date-picker-2").val();
	queryData.endtime=endtime;
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
	      url : '${ctx}/info/getPageForResult',
	      datatype : "json",
	      postData : queryData,
	      mtype:'POST',
	      colModel : [ 
	                   {name : 'name',label: '姓名', width : 55,sortable : false, align : 'center'}, 
	                   {name : 'mobile',label: '电话', width : 80, sortable : false, align : 'center'}, 
	                   {name : 'email',label: '邮箱', width : 80, sortable : false, align : 'center'}, 
	                   {name : 'projecthref',label: '项目链接', width : 50, sortable : false, align : 'center',formatter: 
	                	   function (cellvalue, options, rowObject) {return href(cellvalue,'项目链接');}}, 
	                   {name : 'sendmenutime',label: '发送采访提纲时间', width : 80, sortable : false, align : 'center'}, 
	                   {name : 'interviewtime',label: '采访时间', width : 80, sortable : false, align : 'center'}, 
	                   {name : 'finshnewstime',label: '成稿时间', width : 80, sortable : false, align : 'center'}, 
	                   {name : 'source',label: '打分', width : 100, sortable : false, align : 'center'}, 
	                   {name : 'backtime',label: '发送反馈时间', width : 80, sortable : false, align : 'center'}, 
	                   {name : 'isrefuse',label: '是否拒绝采访', width : 1, sortable : false, align : 'center'}, 
	                   {name : 'infohref',label: '文章链接', width : 60, sortable : false, align : 'center',formatter: 
	                	   function (cellvalue, options, rowObject) {return href(cellvalue,'文章链接');}}
	                  /*  {name: 'flag', label: '操作', width: 250, sortable : false,align: 'center',formatter: 
	                	   function (cellvalue, options, rowObject) {return operate(cellvalue, options, rowObject);}}, */
	                 ],
	      rowNum : 10,
	      rowList : [ 10, 20, 30 ],
	      pager : pager_selector,
	      sortname : 'id',
	      viewrecords : true,
	      autowidth: true,
	      sortorder : "desc",
	      rownumbers : true,
	      //multiselect: true,
	      //multiboxonly: true,
	      emptyrecords : "暂无数据",
	      caption : "用户管理",
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
	        recordtext:"第 {0} - {1} 条，共 {2} 条",
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
	//$("#grid-pager_right div").hide();
	$('.ui-pg-table:not(.navtable) > tbody > tr > .ui-pg-button > .ui-icon').each(function(){
		var icon = $(this);
		var $class = $.trim(icon.attr('class').replace('ui-icon', ''));
		
		if($class in replacement) icon.attr('class', 'ui-icon '+replacement[$class]);
	})
}
function href(href, text){
	if (href != undefined && href != '') {
		return "<a href='"+href+"' style='color: #428bca;' target='_blank'>"+text+"</a>";
	} else {
		return '';
	}
}


function operate(cellvalue, options, rowObject){
	var html = [];
	html.push("<a href='#' class='icon-eye-open' onclick='show(\""+rowObject.id+"\")'>查看</a>");
	html.push("<a href='#' class='icon-edit' onclick='edit(\""+rowObject.id+"\")'>编辑</a>");
	
	var speedDesc = '采访进度';//rowObject.haveSpeed == '0' ? '新增进度' : '编辑进度';
	html.push("<a href='#' onclick='addSpeed(\""+rowObject.id+"\",\""+speedDesc+"\")'>" +speedDesc+ "</a>");
	var onlineDesc = '上线进度';//rowObject.haveOnline == '0' ? '新增上线信息' : '上线进度';
	html.push("<a href='#' onclick='addOnline(\""+rowObject.id+"\",\""+onlineDesc+"\")'>" +onlineDesc+ "</a>");
	
	html.push("<a href='#' onclick='deleteinfo(\""+rowObject.id+"\")'>删除</a>");
	
	return html.join("&nbsp;|&nbsp;");
}
function show(id){
	openFrame('查看信息','${ctx }/info/show/forward?id=' + id,700,560);
}
function edit(id){
	openFrame('编辑信息','${ctx }/info/add/forward?id=' + id,650,550);
}
function addSpeed(id,title){
	openFrame(title + '信息','${ctx }/info/addSpeed/forward?id=' + id,600,500);
}
function addOnline(id,title){
	openFrame(title + '信息','${ctx }/info/addOnline/forward?id=' + id,500,400);
}
function deleteinfo(id){
	layer.confirm('确认删除客户信息（包括进度和上线信息）', {icon: 3, title:'确认信息'}, function(index){
		var index2 = layer.load(2, {
			  shade: [0.1,'#ccc'] //0.1透明度的白色背景
			});
		$.post('${ctx }/info/delete', {id:id}, function (data) {
			layer.close(index2);
			if(data.result){
				layer.msg('删除成功');
				pageReload();
			}else{
				layer.msg('删除失败');
			}
		});
	  layer.close(index);
	});
}
</script>
</body>
</html>

