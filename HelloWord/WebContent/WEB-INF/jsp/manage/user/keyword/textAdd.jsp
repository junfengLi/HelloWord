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
		<tr>
			<td width="25%" class="titleTd">关键词：</td>
			<td  width="50%">
			<input type="text" id="keyword" name="keyword" placeholder="关键词"
			  value="${keyword.keyword }" class="col-xs-12 col-sm-5" autocomplete="off" />
			</td>
			<td width="15%"></td>
		</tr>
		<tr>
			<td class="titleTd">回复内容：</td>
			<td>
			<textarea id="form-field-11" name="content" class="autosize-transition form-control" placeholder="回复内容"
			 style="overflow: hidden; word-wrap: break-word; resize: horizontal; height: 70px;margin: 2px 0px;">${messageText.content }</textarea>
			</td>
			<td></td>
		</tr>
	</table>
	<div class="frame_close">
		<input type="hidden" name="wechatid" value="${wechatId }" />
		<input type="hidden" name="id" value="${keyword.id }" />
		<input type="hidden" name="messageTextId" value="${messageText.id }" />
		<input type="hidden" name="messagetype" value="Text" />
		<input type="hidden" name="servicetype" value="Text" />
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
			keyword: {
				required: true,
				maxlength: 20
			},
			content:{
				maxlength:1000
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
