<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>Bootstrap控制台</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<jsp:include page="../include/css-js.jsp" />
</head>
<body>
<div class="row">
	<form action="${ctx }/project/config/save" id="userForm" method="post">
		<table  class="tableForm">
		<tr>
		<td>
			 <div class="col-sm-4">
				<div class="widget-box">
					<div class="widget-header widget-header-flat">
						<h4><i class="icon-bookmark"></i>当日情况配置：</h4>
					</div>
					<div class="widget-body">
						<div class="widget-main">
							<div class="row">
								<div class="col-xs-12">
										<textarea id="dayconfig" name="dayconfig" class="autosize-transition form-control"
					 						style="    height: 130px;">${config.dayconfig }</textarea>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div><!-- /span -->
			<div class="col-sm-4">
				<div class="widget-box">
					<div class="widget-header widget-header-flat">
						<h4><i class="icon-bookmark"></i>周报配置：</h4>
					</div>
					<div class="widget-body">
						<div class="widget-main">
							<div class="row">
								<div class="col-xs-12">
										<textarea id="weekconfig" name="weekconfig" class="autosize-transition form-control"
					 						style="    height: 130px;">${config.weekconfig }</textarea>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div><!-- /span -->
			<div class="col-sm-4">
				<div class="widget-box">
					<div class="widget-header widget-header-flat">
						<h4><i class="icon-bookmark"></i>月报配置：</h4>
					</div>
					<div class="widget-body">
						<div class="widget-main">
							<div class="row">
								<div class="col-xs-12">
										<textarea id="monthconfig" name="monthconfig" class="autosize-transition form-control"
					 						style="    height: 130px;">${config.monthconfig }</textarea>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div><!-- /span -->
			<div class="col-sm-12">
				<div class="widget-box">
					<div class="widget-header widget-header-flat">
						<h4><i class="icon-bookmark"></i>提示：</h4>
					</div>
					<div class="widget-body">
						<div class="widget-main">
							<div class="row">
								<div class="col-xs-12">
										<c:forEach items="${nodes }" var="node" >
												<i class="icon-circle bigger-110 purple"></i>
												{[${node.id }]} = ${node.text };<br />
										</c:forEach>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div><!-- /span -->
	</td>
	</tr>
	</table>
	<div class="frame_close">
		<input type="hidden" name="userid" value="${config.userid }" />
		<input type="hidden" name="id" value="${config.id }" />
		<input type="hidden" name="createtime" value="${config.createtime }" />
		<button class="btn btn-info" onclick="parent.closeFrame()" type="button"> 关闭</button>
		<input type="submit" class="btn btn-primary" value="提交" />
	</div>
	</form>
</div><!-- /.row -->
<jsp:include page="../include/footer-js.jsp" />
<script type="text/javascript">
var layer;
layui.use(['layer', 'form'], function(){
	layer = layui.layer;
});


function submitHandler(obj){
	if (obj.result) {
		parent.pageReload();
		parent.closeFrame();
	} else {
		layer.alert(obj.desc,{skin: 'layui-layer-lan',closeBtn: 0 });
	}
}
jQuery(function($) {
	$('.input-group-addon.clearDate').click(function(){
		$(this).prev().prev().datepicker( 'setDate' , '');//.val('');
	});
	$('.date-picker').datepicker({dateFormat: "yy-mm-dd"}); 
	$('.date-picker').datepicker({autoclose:true}).next().on(ace.click_event, function(){
		$(this).prev().focus();
	});

	 $('#userForm').validate({
		errorElement: 'div',
		errorClass: 'error-msg',
		focusInvalid: false,
		submitHandler: function() {  
			 formSubmit('userForm');
        },
		rules: {
			dayconfig: {
				maxlength: 1000
			},
			weekconfig:{
				maxlength: 1000
			},
			monthconfig:{
				maxlength: 1000
			}
		},
		messages: {
			dayconfig:{
				maxlength:"最多输入{0}个字符",
			},
			weekconfig:{
				maxlength:"最多输入{0}个字符",
			},
			monthconfig:{
				maxlength:"最多输入{0}个字符",
			}
		},
		errorPlacement: function(error, element) { //错误信息位置设置方法
			var id = element.attr("id");
			layer.tips(error.html(), '#' + id, {tips: [3, '#78BA32']});
			//error.appendTo( element.parent().next() ); //这里的element是录入数据的对象
		}
	});

});
</script>
</body>
</html>

