<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title></title>
<jsp:include page="../../include/css-js.jsp" />
</head>
<body>
<div class="row">
	<form action="${ctx }/menu/save" id="userForm" method="post">
	<table  class="tableForm">
		<tr>
			<td width="25%" class="titleTd">菜单名称：</td>
			<td  width="50%">
			<input type="text" id="name" name="name" placeholder="菜单名称"
			  value="${button.name }" class="col-xs-10 col-sm-5" autocomplete="off" />
			</td>
			<td width="15%"></td>
		</tr>
		<tr>
			<td class="titleTd">属于（分组）：</td>
			<td >
				<select class="form-control input-small" id="form-field-select-1" name="pid" style="width:120px;">
					<c:forEach items="${nodes }" var="node" >
						<option value="${node.id }" <c:if test="${node.id == button.pid}"> selected="selected"</c:if> >${node.text }</option>
					</c:forEach>
				</select>
			</td>
			<td></td>
		</tr>
		<tr>
			<td class="titleTd">排序：</td>
			<td>
			<input type="text" id="seq" name="buttonsort" value="${button.buttonsort }<c:if test="${empty button }">500</c:if>" 
			 placeholder="排序" class="col-xs-4 col-sm-5"><span style="line-height: 20px; height: 20px; margin-top: 5px; margin-left: 10px;"" class="badge badge-danger">请输入3位数字如：000/999</span>
			</td>
			<td></td>
		</tr>
		<tr>
			<td class="titleTd">菜单类型：</td>
			<td >
				<div class="radio">
					<label>
						<input name="buttontype" type="radio" class="ace" value="click" checked="checked" />
						<span class="lbl"></span><span>&nbsp;&nbsp;事件类型&nbsp;&nbsp;</span>
					</label>
					<label>
						<input name="buttontype" type="radio" class="ace" value="view"
						 <c:if test="${button.buttontype=='view' }">checked="checked"</c:if> />
						<span class="lbl"></span><span>&nbsp;&nbsp;超链接类型&nbsp;&nbsp;</span>
					</label>
				</div>
			</td>
			<td></td>
		</tr>
		<tr class="tr_keyWord " <c:if test="${button.buttontype=='view' }">style="display:none;"</c:if>>
			<td class="titleTd">触发关键词：</td>
			<td >
			<input type="text" id="menukey" name="menukey" placeholder="触发关键词"
			  value="${button.menukey }" class="col-xs-10 col-sm-5" autocomplete="off" /></td>
			<td></td>
		</tr>
		<tr class="tr_setUrl" <c:if test="${button.buttontype=='click' or empty button}">style="display:none;"</c:if>>
			<td  class="titleTd " >跳转URL：</td>
			<td >
			<input type="text" id="url" name="url" placeholder="跳转URL"
			  value="${button.url }" class="col-xs-10 col-sm-5" autocomplete="off" /></td>
			<td></td>
		</tr>
	</table>
	<div class="frame_close">
		<input type="hidden" name="wechatid" value="${wechatId }" />
		<input type="hidden" name="id" value="${button.id }" />
		<button class="btn btn-info" onclick="parent.closeFrame()" type="button"> 关闭</button>
		<input type="submit" class="btn btn-primary" value="提交" />
	</div>
	</form>
</div><!-- /.row -->
<jsp:include page="../../include/footer-js.jsp" />
			
<script>

var layer;
layui.use(['layer', 'form'], function(){
	layer = layui.layer;
});


function submitHandler(obj){
	if (obj.success) {
		parent.reloadList();
		parent.closeFrame();
	} else {
		layer.alert(obj.desc,{skin: 'layui-layer-lan',closeBtn: 0 });
	}
}


$(function(){
	$("input[name='buttontype']").click(function(){
		var value = $(this).val();
		if(value == 'view'){
			$(".tr_setUrl").show();
			//$("#url").validatebox({required: true,validType:'url'}); 
			//$("#menuKey").validatebox({required: false});  
			$(".tr_keyWord").hide();
		} else {
			//$("#url").validatebox({required: false,validType:''}); 
			$(".tr_setUrl").hide();
			$(".tr_keyWord").show();
			//$("#menuKey").validatebox({required: true});  
		}
	});
	 $('#userForm').validate({
			errorElement: 'div',
			errorClass: 'error-msg',
			focusInvalid: false,
			submitHandler: function() {  
				 formSubmit('userForm');
	        },
			rules: {
				name: {
					required: true,
					maxlength: 7
				},
				menukey:{
					required: true,
					maxlength:10
				},
				/* url:{
					required: true,
					maxlength:500,
					url:true
				}, */
				buttonsort:{
					number:true,
					rangelength:[3,3]
				}
			},
			messages: {
				name:{
					required:"请输入菜单名称",
					maxlength: "最多输入20个字符"
				},
				menukey:{
					required:"请输入触发关键词",
					maxlength:"最多输入{0}个字符",
				},
				/* url:{
					required:"请输入链接地址",
					maxlength:"最多输入{0}个字符",
					url:"链接格式不正确"
				}, */
				buttonsort:{
					number:"只能输入数字",
					rangelength:"请输入一个{0}位数字"
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
