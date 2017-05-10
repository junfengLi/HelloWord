<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<link href="${ctx}/static/favicon.ico" rel="bookmark" type="image/x-icon" /> 
<link href="${ctx}/static/favicon.ico" rel="icon" type="image/x-icon" /> 
<link href="${ctx}/static/favicon.ico" rel="shortcut icon" type="image/x-icon" /> 

<!-- basic styles -->
<link href="${ctx }/static/bootstrap-3.3.0/css/bootstrap.min.css" rel="stylesheet" />
<link href="${ctx }/static/bootstrap-3.3.0/css/date-time/bootstrap-datetimepicker.min.css" rel="stylesheet" />

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
<link rel="stylesheet" href="${ctx }/static/css/base.css" />

<!-- ace settings handler -->
<script type="text/javascript" src="${ctx }/static/ace/js/ace-extra.min.js"></script>
<script type="text/javascript" src="${ctx}/static/js/jquery/jquery-2.0.3.min.js"></script>
<script type="text/javascript" src="${ctx }/static/layui/layui.js"></script>

<script type="text/javascript">
function formSubmit(formId,noCallBack){
	layui.use(['layer', 'form'], function(){
		var layer = layui.layer;
		var _form = $('#'+formId);
		var _url = _form.attr('action');
		var index = layer.load(2, {
			  shade: [0.1,'#ccc'] //0.1透明度的白色背景
			});
		$.post(_url, $(_form).serialize(), function (data) {
			layer.close(index);
			if(data.success){
				//noCallBack不传走回调，传了就不走回调
				if(!noCallBack){
					submitHandler(data); 
				}
			}else{
				layer.msg('提交失败');
			}
		});
	});
}
</script>