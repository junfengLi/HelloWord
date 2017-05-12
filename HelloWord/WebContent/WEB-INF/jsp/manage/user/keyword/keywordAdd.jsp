<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<jsp:include page="../../include/css-js.jsp" />
</head>
<body>
<div class="row">
	<form action="${ctx }/keyword/save" id="userForm" method="post">
	<table  class="tableForm">
		<tr >
			<td width="30%" class="titleTd " ></td>
			<td >${serviceTypeText }</td>
		</tr>
		<tr >
			<td width="30%" class="titleTd " >关联关键词：</td>
			<td >
			<input type="text" id="messagetype" name="messagetype" placeholder="关联关键词"
			  value="${keyword.messagetype }" class="col-xs-10 col-sm-5" autocomplete="off" />
			</td>
		</tr>
	</table>
	<div class="frame_close">
	
		<input type="hidden" name="wechatid" value="${wechatId }" />
		<input type="hidden" name="id" value="${keyword.id }" />
		<input type="hidden" name="messageTextId" value="${messageText.id }" />
		<input type="hidden" name="servicetype" value="${serviceType }" />
		<input type="hidden" name="keyword" value="${serviceType }" />
		<button class="btn btn-info" onclick="parent.closeFrame()" type="button"> 关闭</button>
		<input type="submit" class="btn btn-primary" value="提交" />
	</div>
	</form>
</div><!-- /.row -->
<jsp:include page="../../include/footer-js.jsp" />
<script type="text/javascript">
var layer;
layui.use(['layer', 'form'], function(){
	layer = layui.layer;
});

function submitHandler(obj){
	if (obj.success) {
		parent.pageReload();
		parent.closeFrame();
	} else {
		layer.alert(obj.desc,{skin: 'layui-layer-lan',closeBtn: 0 });
	}
}
jQuery(function($) {
	 $('#userForm').validate({
		focusInvalid: false,
		submitHandler: function() {  
			 formSubmit('userForm');
        },
		rules: {
			messagetype: {
				required: true,
				maxlength: 20
			}
		},
		messages: {
			messagetype:{
				required:"请输入关联关键词",
				maxlength: "最多输入{0}个字符"
			}
		},
		errorPlacement: function(error, element) { //错误信息位置设置方法
			var id = element.attr("id");
			layer.tips(error.html(), '#' + id, {tips: [3, '#78BA32']});
		}
	});
});
</script>
</body>
</html>
