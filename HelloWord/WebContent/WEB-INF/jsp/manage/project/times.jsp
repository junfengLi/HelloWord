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
	<form action="${ctx }/project/times/save" id="userForm" method="post">
		<table  class="tableForm">
			<tr>
				<td width="25%" class="titleTd">记录时间：</td>
				<td  width="50%">
				<input class="form-control date-picker" id="dated" type="text" name="dated" value="${date }"
					style="float: left; display: block; width: 70%;" />
				<span class="input-group-addon" style="float: left;width: 15%;height: 34px;line-height: 23px;"><i class="icon-calendar bigger-110"></i></span>
				<span class="input-group-addon clearDate"  style="float: left;width: 15%;height: 34px;line-height: 23px;"><i class="icon-remove"></i></span>
				</td>
				<td width="15%"></td>
			</tr>
			<tr>
				<td class="titleTd">采访人次：</td>
				<td height="50px;">
				<c:choose>
				<c:when test="${ empty times}"><c:set var="timesval" value="1" /></c:when>
				<c:otherwise>
					<c:set var="timesval" value="${times.times }" />
				</c:otherwise>				
				</c:choose>
				<input type="text" class="input-mini" id="times" name="times"  />
				</td>
				<td></td>
			</tr>
			<tr>
				<td class="titleTd">备注：</td>
				<td>
				<textarea id="note" name="note" class="autosize-transition form-control"
				 style="overflow: hidden; word-wrap: break-word; resize: horizontal; height: 90px;margin: 2px 0px;">${times.note }</textarea>
				</td>
				<td></td>
			</tr>
		</table>
	<div class="frame_close">
		<input type="hidden" name="userid" value="${userid }" />
		<input type="hidden" name="id" value="${times.id }" />
		<input type="hidden" name="createtime" value="${times.createtime }" />
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
	$('#times').ace_spinner({value:${timesval },min:1,max:9999999999,step:1, on_sides: true, icon_up:'icon-plus smaller-75', icon_down:'icon-minus smaller-75', btn_up_class:'btn-success' , btn_down_class:'btn-danger'});


	 $('#userForm').validate({
		errorElement: 'div',
		errorClass: 'error-msg',
		focusInvalid: false,
		submitHandler: function() {  
			 formSubmit('userForm');
        },
		rules: {
			dated:{
				required:true
			},
			/* times:{
				number:true,
				rangelength:[1,10]
			}, */
			note: {
				maxlength: 1000
			}
		},
		messages: {
			dated:{
				required:'请选择时间'
			},
			/* times:{
				number:"只能输入数字",
				rangelength:"只能输入{0}到{1}位数字"
			}, */
			note:{
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

