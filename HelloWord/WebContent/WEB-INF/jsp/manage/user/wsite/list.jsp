<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Cache-Condivol" content="no-store" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<title></title>
</head>
<body>
<div class="wsite-demo-center">
	<c:forEach items="${easyUINodes }" var="node">
	<div class="wsite-demo-box<c:if test="${wechat.indexDemo == node.id or wechat.listDemo == node.id or wechat.contentDemo == node.id }"> on</c:if>">
		<label>
		<img src="${ctx }/static/images/wsite/demo/${node.attributes.demoType }/${node.id }.png" />
		<span class="input-text">
		<input type="radio" name="${node.attributes.demoType }wsiteDemo"
		<c:if test="${wechat.indexDemo == node.id or wechat.listDemo == node.id or wechat.contentDemo == node.id }"> checked="checked" </c:if>
		 value="${node.id }" />${node.text }</span>
		</label>
	</div>
	</c:forEach>
	<%-- <c:forEach items="${easyUINodes }" var="node">
	<div class="wsite-demo-box">
		<label>
		<img src="${ctx }/static/images/wsite/demo/${node.attributes.demoType }/${node.id }.png" />
		<span class="input-text"><input type="radio" name="${node.attributes.demoType }wsiteDemo" value="${node.id }" />${node.text }</span>
		</label>
	</div>
	</c:forEach>
	<c:forEach items="${easyUINodes }" var="node">
	<div class="wsite-demo-box">
		<label>
		<img src="${ctx }/static/images/wsite/demo/${node.attributes.demoType }/${node.id }.png" />
		<span class="input-text"><input type="radio" name="${node.attributes.demoType }wsiteDemo" value="${node.id }" />${node.text }</span>
		</label>
	</div>
	</c:forEach>
	<c:forEach items="${easyUINodes }" var="node">
	<div class="wsite-demo-box">
		<label>
		<img src="${ctx }/static/images/wsite/demo/${node.attributes.demoType }/${node.id }.png" />
		<span class="input-text"><input type="radio" name="${node.attributes.demoType }wsiteDemo" value="${node.id }" />${node.text }</span>
		</label>
	</div>
	</c:forEach>
	<c:forEach items="${easyUINodes }" var="node">
	<div class="wsite-demo-box">
		<label>
		<img src="${ctx }/static/images/wsite/demo/${node.attributes.demoType }/${node.id }.png" />
		<span class="input-text"><input type="radio" name="${node.attributes.demoType }wsiteDemo" value="${node.id }" />${node.text }</span>
		</label>
	</div>
	</c:forEach>
	<c:forEach items="${easyUINodes }" var="node">
	<div class="wsite-demo-box">
		<label>
		<img src="${ctx }/static/images/wsite/demo/${node.attributes.demoType }/${node.id }.png" />
		<span class="input-text"><input type="radio" name="${node.attributes.demoType }wsiteDemo" value="${node.id }" />${node.text }</span>
		</label>
	</div>
	</c:forEach> --%>
</div>
<script type="text/javascript">
$(function(){
	var wechatId = '${wechatId}';
	var demoType = '${demoType}';
	$(".wsite-demo-box input").click(function(){
		var demo = $(this).val();
		$(this).parent().parent().parent().addClass('on');
		$(this).parent().parent().parent().siblings().removeClass('on');
		var loadIndex = layer.load();
		$.post('${ctx}/user/wsite/setWsiteDemo',{demo:demo, demoType:demoType, wechatId:wechatId},function(data){
			layer.close(loadIndex);
			if (data.success){
				layer.msg('保存成功');
			}
			
		},'json');		
	});
});
</script>
</body>
</html>
