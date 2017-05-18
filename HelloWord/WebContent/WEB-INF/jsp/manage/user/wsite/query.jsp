<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Cache-Control" content="no-store" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<title></title>
</head>
<body>
<div class="row col-sm-12">
	<div class="tabbable" >
		<ul class="nav nav-tabs" id="myTab">
			<c:forEach items="${nodes }" var="node" varStatus="status" >
				<li class="<c:if test="${status.index == 0 }">active</c:if>">
					<a data-toggle="tab" href="#${node.id }">
						<i class="icon-group bigger-110"></i>
						${node.text }
					</a>
				</li>
			</c:forEach>
		</ul>
		<div class="tab-content">
			<div class="tab-pane in active" id="index" >
				<div class="row" >
					<div class="radio ">
					<c:forEach items="${index }" var="indexnode">
						<label class=" col-sm-2">
						<img src="${ctx }/static/images/wsite/demo/${indexnode.attributes.demoType }/${indexnode.id }.png" />
						<input type="radio" name="${indexnode.attributes.demoType }wsiteDemo" class="ace"
						<c:if test="${wechat.indexdemo == indexnode.id }"> checked="checked" </c:if>
						 value="${indexnode.id }" />
						<span class="lbl col-sm-12 center " style="height:40px; line-height: 40px; display:block;">&nbsp;&nbsp;${indexnode.text }&nbsp;&nbsp;</span>
						</label>
					</c:forEach>
					</div>
				</div>
			</div>
			<div class="tab-pane in" id="list" >
				<div class="row" >
					<div class="radio ">
					<c:forEach items="${list }" var="listnode">
						<label class=" col-sm-2">
						<img src="${ctx }/static/images/wsite/demo/${listnode.attributes.demoType }/${listnode.id }.png" />
						<input type="radio" name="${node.attributes.demoType }wsiteDemo" class="ace"
						<c:if test="${wechat.listdemo == listnode.id }"> checked="checked" </c:if>
						 value="${listnode.id }" />
						<span class="lbl col-sm-12 center " style="height:40px; line-height: 40px; display:block;">&nbsp;&nbsp;${listnode.text }&nbsp;&nbsp;</span>
						</label>
					</c:forEach>
					</div>
				</div>
			</div>
			<div class="tab-pane in" id="content" >
				<div class="row" >
					<div class="radio ">
					<c:forEach items="${content }" var="contentnode">
						<label class=" col-sm-2">
						<img src="${ctx }/static/images/wsite/demo/${contentnode.attributes.demoType }/${contentnode.id }.png" />
						<input type="radio" name="${contentnode.attributes.demoType }wsiteDemo" class="ace"
						<c:if test="${wechat.contentdemo == contentnode.id }"> checked="checked" </c:if>
						 value="${contentnode.id }" />
						<span class="lbl col-sm-12 center " style="height:40px; line-height: 40px; display:block;">&nbsp;&nbsp;${contentnode.text }&nbsp;&nbsp;</span>
						</label>
					</c:forEach>
					</div>
				</div>
			</div>
		</div> <!--- /.tab-content ---->
	</div>
</div><!-- /.row -->

<script type="text/javascript">
var wechatId = '${wechatId}';
$(function(){
	$(".radio input").click(function(){
		var demo = $(this).val();
		var loadIndex = layer.load();
		$.post('${ctx}/wsite/setWsiteDemo',{demo:demo, wechatId:wechatId},function(data){
			layer.close(loadIndex);
			if (data.success){
				layer.msg('保存成功');
			}
			
		},'json');		
	});
	$(".tab-content .tab-pane").each(function(index){
		$(this).load($(this).attr('data-url'));
    })
})
</script>
</body>
</html>
