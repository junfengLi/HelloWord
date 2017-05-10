<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!-- basic styles -->
<link href="${ctx }/static/bootstrap-3.3.0/css/bootstrap.min.css" rel="stylesheet" />
<link rel="stylesheet" href="${ctx }/static/ace/css/font-awesome.min.css" />
<!--[if IE 7]><link rel="stylesheet" href="${ctx }/static/ace/css/font-awesome-ie7.min.css" /><![endif]-->
<!-- ace styles -->
<link rel="stylesheet" href="${ctx }/static/css/jquery/jquery-ui-1.10.3.full.min.css" />

<link rel="stylesheet" href="${ctx }/static/css/jquery/ui.jqgrid.css" />
<link rel="stylesheet" href="${ctx }/static/ace/css/dropzone.css" />
<link rel="stylesheet" href="${ctx }/static/ace/css/ace.min.css" />
<link rel="stylesheet" href="${ctx }/static/ace/css/ace-rtl.min.css" />
<link rel="stylesheet" href="${ctx }/static/ace/css/ace-skins.min.css" />
<!--[if lte IE 8]><link rel="stylesheet" href="${ctx }/static/ace/css/ace-ie.min.css" /><![endif]-->
<link rel="stylesheet" href="${ctx }/static/layui/css/layui.css" />
<script type="text/javascript" src="${ctx }/static/layui/layui.js"></script>



<link rel="stylesheet" href="${ctx }/static/css/base.css" />
<script>
layui.use(['layer', 'form'], function(){
  var layer = layui.layer
  ,form = layui.form();
});
function closeFrame(){
	var index = parent.layer.getFrameIndex(window.name);
	parent.layer.close(index);
}
function openFrame(title,url,width,height){
	layui.use(['layer', 'form'], function(){
	  var layer = layui.layer;
	  layer.open({
			title:title,
			type: 2,
			shade: false,
			area : [width+'px' , height+'px'],
			maxmin: true,
			content: url,
			zIndex: layer.zIndex,
			success: function(layero){
				layer.setTop(layero);
			},btn: ['提交', '关闭'] //只是为了演示
		});  
	});
}
</script> 

<!-- ace settings handler -->
<script type="text/javascript" src="${ctx }/static/ace/js/ace-extra.min.js"></script>

<!--[if lt IE 9]><script src="${ctx }/static/ace/js/html5shiv.js"></script>
	<script src="${ctx }/static/ace/js/respond.min.js"></script><![endif]-->
	
<script type="text/javascript">
	function LoadPage(obj,id) {
		LoadMainPage($(obj).attr('data-url'),id)
		return false;
	}
	function LoadMainPage(url,id){
		
		$.get(url,{n:Math.random()},function(html){
			if (html) {
				$("#" + id).html("<P></P>");
				setTimeout(function() { $("#" + id).html(html); }, 10);
	     	}
		});
	}
</script>

