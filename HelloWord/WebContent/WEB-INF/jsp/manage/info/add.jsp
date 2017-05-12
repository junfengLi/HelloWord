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
	<form action="${ctx }/info/save" id="userForm" method="post">
	<table  class="tableForm">
		<tr>
			<td width="25%" class="titleTd">姓名：</td>
			<td  width="50%">
			<input type="text" id="name" name="name" placeholder="姓名"
			  value="${info.name }" class="col-xs-12 col-sm-5" autocomplete="off" />
			</td>
			<td width="15%"></td>
		</tr>
		<tr>
			<td class="titleTd">手机：</td>
			<td>
			<input type="text" id="mobile" name="mobile" value="${info.mobile }"
			 placeholder="手机" class="col-xs-12 col-sm-5" autocomplete="off" />
			</td>
			<td></td>
		</tr>
		<tr>
			<td class="titleTd">是否拒绝采访：</td>
			<td>
				<div class="radio">
					<label>
						<input name="isrefuse" type="radio" class="ace" value="1" checked="checked" />
						<span class="lbl"></span><span>&nbsp;&nbsp;是&nbsp;&nbsp;</span>
					</label>
					<label>
						<input name="isrefuse" type="radio" class="ace" value="0"
						 <c:if test="${info.isrefuse != '1' }"> checked="checked" </c:if> />
						<span class="lbl"></span><span>&nbsp;&nbsp;否&nbsp;&nbsp;</span>
					</label>
				</div>
			</td>
			<td></td>
		</tr>
		<tr>
			<td class="titleTd">备用手机：</td>
			<td>
			<input type="text" id="mobile2" name="mobile2" value="${info.mobile2 }"
			 placeholder="备用手机" class="col-xs-12 col-sm-5" autocomplete="off" />
			</td>
			<td></td>
		</tr>
		<tr>
			<td class="titleTd">邮箱：</td>
			<td>
			<input type="text" id="email" name="email" value="${info.email }"
			 placeholder="邮箱" class="col-xs-12 col-sm-5">
			</td>
			<td></td>
		</tr>
		<tr>
			<td class="titleTd">备用邮箱：</td>
			<td>
			<input type="text" id="email2" name="email2" value="${info.email2 }"
			 placeholder="备用邮箱" class="col-xs-12 col-sm-5">
			</td>
			<td></td>
		</tr>
		<tr>
			<td class="titleTd">项目名称：</td>
			<td>
			<input type="text" id="projectname" name="projectname" value="${info.projectname }"
			 placeholder="项目名称" class="col-xs-12 col-sm-5">
			</td>
			<td></td>
		</tr>
		<tr>
			<td class="titleTd">项目链接：</td>
			<td>
			<input type="text" id="projecthref" name="projecthref" value="${info.projecthref }" 
			 placeholder="项目链接" class="col-xs-12 col-sm-5">
			</td>
			<td></td>
		</tr>
		<tr>
			<td class="titleTd">服务客服：</td>
			<td>
			<input type="text" id="asker" name="asker" value="${info.asker }" 
			 placeholder="服务客服" class="col-xs-12 col-sm-5">
			</td>
			<td></td>
		</tr>
		<tr>
			<td class="titleTd"> 服务到期时间：</td>
			<td>
			<input class="form-control date-picker" id="id-date-picker-1" type="text" name="servicetime-d" value="${servicetime }"
			style="float: left; display: block; width: 70%;" />
			<span class="input-group-addon" style="float: left;width: 15%;height: 34px;line-height: 23px;"><i class="icon-calendar bigger-110"></i></span>
			<span class="input-group-addon clearDate"  style="float: left;width: 15%;height: 34px;line-height: 23px;"><i class="icon-remove"></i></span>
			</td>
			<td></td>
		</tr>
		<tr>
			<td class="titleTd">备注：</td>
			<td>
			<textarea id="form-field-11" name="note" class="autosize-transition form-control"
			 style="overflow: hidden; word-wrap: break-word; resize: horizontal; height: 70px;margin: 2px 0px;">${info.note }</textarea>
			</td>
			<td></td>
		</tr>
		<tr>
			<td class="titleTd">排序：</td>
			<td>
			<input type="text" id="seq" name="seq" value="${info.seq }<c:if test="${empty info }">500</c:if>" 
			 placeholder="排序" class="col-xs-12 col-sm-5">
			</td>
			<td></td>
		</tr>
	</table>
	<div class="frame_close">
		<input type="hidden" name="id" value="${info.id }" />
		<input type="hidden" name="createtime" value="${info.createtime }" />
		<input type="hidden" name="userid" value="${userid }" />
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
			name: {
				required: true,
				maxlength: 20
			},
			mobile:{
				mobile: true
			},
			mobile2:{
				mobile: true
			},
			email:{
				maxlength:50,
				email:true
			},
			email2:{
				maxlength:50,
				email:true
			},
			projectname:{
				maxlength:100
			},
			projecthref:{
				maxlength:500,
				url:true
			},
			asker:{
				maxlength:50
			},
			note:{
				maxlength:1000
			},
			seq:{
				number:true,
				rangelength:[3,3]
			}
		},
		messages: {
			name:{
				required:"请输入姓名",
				maxlength: "最多输入20个字符"
			},
			mobile:{
				mobile: "手机格式不正确"
			},
			mobile2:{
				mobile: "手机格式不正确"
			},
			email:{
				maxlength:"最多输入{0}个字符",
				email:"邮箱格式不正确"
			},
			email2:{
				maxlength:"最多输入{0}个字符",
				email:"邮箱格式不正确"
			},
			projectname:{
				maxlength:"最多输入{0}个字符",
			},
			projecthref:{
				maxlength:"最多输入{0}个字符",
				url:"链接格式不正确"
			},
			asker:{
				maxlength:"最多输入{0}个字符",
			},
			note:{
				maxlength:"最多输入{0}个字符",
			},
			seq:{
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

