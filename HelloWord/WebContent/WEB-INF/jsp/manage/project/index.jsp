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
		<div class="row">
			<div class="col-xs-12">
				<h3 class="header smaller lighter orange">
					<span >当前时间：</span>
					<span id="time"></span>
				</h3>
				<div class="alert alert-block alert-success">
					<!-- <i class="icon-circle green"></i> -->
					<a href="#" class="menu-text" 
					 onclick="openFrame('模板配置','${ctx }/project/config/forward?id=${config.id }',900,600);">
						<span class="icon-cogs"> 模板配置 </span>
					</a>
					<a href="#" class="menu-text"  id="pageReload"
					 onclick="return LoadPage(this,'main-content');" data-url="${ctx }/project/index/load">
						<span class="menu-text"> <i class="icon-refresh"></i>刷新数据 </span>
					</a>
				</div>
				<!-- PAGE CONTENT BEGINS -->
				<div class="row">
					<div class="col-sm-5">
						<div class="row">
							<div class="widget-box">
								<div class="widget-header widget-header-flat">
									<h4><i class="icon-circle"></i>一周统计</h4>
									<div class="widget-toolbar">
										<input class="form-control input-small date-picker" id="id-date-picker-2" type="text" name="starttime" style="    margin-top: 2px;float: left; display: block;" />
										<span class="input-group-addon" style="float: left;height: 34px; margin-top: 2px;line-height: 23px; width:35px;"><i class="icon-calendar bigger-110"></i></span>
										<span class="input-group-addon clearDate"  style="float: left; margin-top: 2px;height: 34px;line-height: 23px;width:35px;"><i class="icon-remove"></i></span>
										<span style="cursor: pointer;width:30px;margin-left:5px;float:left; margin-top: 2px;" onclick="weekSearch();" >
										<i class="icon-search nav-search-icon bigger-160"></i>
										</span>
									</div>
								</div>
								<div class="widget-body">
									<div class="widget-main">
										<div class="row">
											<div class="col-xs-12" id="dayConfig" style="white-space: pre-line;">${config.dayconfig }</div>
										</div>
									</div>
								</div>
							</div>
						</div><!-- /span -->
						<div class="row">
							<div class="widget-box">
								<div class="widget-header widget-header-flat">
									<h4><i class="icon-circle"></i>周报</h4>
								</div>
								<div class="widget-body">
									<div class="widget-main">
										<div class="row">
											<div class="col-xs-12" id="weekConfig" style="white-space: pre-line;">${config.weekconfig }</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="widget-box">
								<div class="widget-header widget-header-flat">
									<h4><i class="icon-circle"></i>月报</h4>
									<div class="widget-toolbar">
										<input class="form-control input-small date-picker" id="id-date-picker-1" type="text" name="starttime" style="    margin-top: 2px;float: left; display: block;" />
										<span class="input-group-addon" style="float: left;height: 34px; margin-top: 2px;line-height: 23px; width:35px;"><i class="icon-calendar bigger-110"></i></span>
										<span class="input-group-addon clearDate"  style="float: left; margin-top: 2px;height: 34px;line-height: 23px;width:35px;"><i class="icon-remove"></i></span>
										<span style="cursor: pointer;width:30px;margin-left:5px;float:left; margin-top: 2px;" onclick="monthSearch();" >
										<i class="icon-search nav-search-icon bigger-160"></i>
										</span>
									</div>
								</div>
								<div class="widget-body">
									<div class="widget-main">
										<div class="row">
											<div class="col-xs-12" id="monthConfig" style="white-space: pre-line;">${config.monthconfig }</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div><!-- /span -->
					<div class="col-sm-7">
						<div class="page-header">
							<a href="#" class="menu-text" 
							 onclick="openFrame('添加采访记录','${ctx }/project/times/forward',500,400);">
								<span class="icon-cogs"> 添加采访记录 </span>
							</a>
						</div>
						<div class="widget-main">
						<table id="grid-table"></table>
						<div id="grid-pager"></div>
						</div>
					</div><!-- /span -->
				</div>
		</div><!-- /.col -->
	</div><!-- /.row -->
</div><!-- /.page-content -->

<script type="text/javascript">
var layer = null;
var queryData = {};
$(function(){
	queryData.userid='${userid}';
	layui.use(['layer'], function(){layer = layui.layer;});
	getTime();
	pageInit();
	$('.input-group-addon.clearDate').click(function(){
		$(this).prev().prev().val('');
	});
	$('#id-date-picker-2').datepicker({dateFormat: "yy-mm-dd",autoclose:true});
	$('#id-date-picker-1').datetimepicker({language: "zh-CN",format: "yyyy-mm",autoclose:true,
		startView: 3, //这里就设置了默认视图为年视图
        minView: 3, //设置最小视图为年视图
		});
});

function pageReload(){
	$("#pageReload").click();
}

function weekSearch(){
	var time = $('#id-date-picker-2').val();
	if (time != '') {
		var index2 = layer.load(2, {
			  shade: [0.1,'#ccc'] //0.1透明度的白色背景
			});
		$.post('${ctx }/project/weekSearch', {time:time}, function (data) {
			layer.close(index2);
			if(data.result){
				$("#dayConfig").html(data.day);
				$("#weekConfig").html(data.week);
			}else{
				layer.msg('参数错误');
			}
		});
	} else {
		layer.tips('请选择时间', '#id-date-picker-2', {tips: [3, '#78BA32']});
	}
}

function monthSearch(){
	var time = $('#id-date-picker-1').val();
	if (time != '') {
		var index2 = layer.load(2, {
			  shade: [0.1,'#ccc'] //0.1透明度的白色背景
			});
		$.post('${ctx }/project/monthSearch', {time:time}, function (data) {
			layer.close(index2);
			if(data.result){
				$("#monthConfig").html(data.month);
			}else{
				layer.msg('参数错误');
			}
		});
	} else {
		layer.tips('请选择时间', '#id-date-picker-1', {tips: [3, '#78BA32']});
	}
}

function getTime(){
    var date = new Date();
    var month = date.getMonth()+1;
    var day = date.getDate();
    var str = date.getFullYear()+"-"+( month < 10 ? "0" + month : month )+"-"+( day < 10 ? "0" + day : day );
    var hours =date.getHours();
    var minutes = date.getMinutes();
    var seconds = date.getSeconds();
    
    $('#time').html(str + " " + ( hours < 10 ? "0" + hours : hours ) + " : " + ( minutes < 10 ? "0" + minutes : minutes ) +" : "+ ( seconds < 10 ? "0" + seconds : seconds ));
    setTimeout("getTime()",1000);

}
function pageInit(){
	var grid_selector = "#grid-table";
	var pager_selector = "#grid-pager";
	
	jQuery(grid_selector).jqGrid({
	      url : '${ctx}/project/getPage',
	      datatype : "json",
	      postData : queryData,
	      mtype:'POST',
	      colModel : [ 
	                   {name : 'date',label: '时间', width : 100, sortable : false, align : 'center'}, 
	                   {name : 'times',label: '采访人次', width : 100, sortable : false, align : 'center'}, 
	                   {name: 'note', label: '备注', width: 150, sortable : false,align: 'center',formatter: 
	                	   function (cellvalue, options, rowObject) {if(cellvalue != '') return '<span class="hide">' + cellvalue + '</span><a href="###" class="blue" onclick="show(this);">点击查看备注信息</a>'; else return '暂无备注信息';}},
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
	      emptyrecords : "暂无数据",
	      caption : "用户管理",
	      height: 'auto',
	      loadComplete : function() {
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
	var html = [];
	html.push("<a href='#' class='blue' onclick='edit(\""+rowObject.id+"\")'>编辑</a>");
	html.push("<a href='#' class='blue' onclick='deletetimes(\""+rowObject.id+"\")'>删除</a>");
	return html.join("&nbsp;|&nbsp;");
}


function edit(id){
	openFrame('添加采访记录','${ctx }/project/times/forward?id=' + id,500,400);
}
function show(e){
	var html = $(e).prev().html();
	layer.open({
		  title: '备注信息',
		  content: '<span style="white-space: pre;">'+html+'</span>'
		});  
}

function deletetimes(id){
	layer.confirm('确认删除信息', {icon: 3, title:'确认信息'}, function(index){
		var index2 = layer.load(2, {
			  shade: [0.1,'#ccc'] //0.1透明度的白色背景
			});
		$.post('${ctx }/project/timesdelete', {id:id}, function (data) {
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

