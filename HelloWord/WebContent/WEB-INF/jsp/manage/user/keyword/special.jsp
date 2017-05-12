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
<div class="page-content">
	<div class="row">
		<div class="col-xs-12">
			<div class="keyword-center">
				<div  class="keyword-head">
				   		<div class="keyword-serviceType">请求类型</div>
				        <div class="keyword-messageType">关联关键词</div>
				        <div class="keyword-time">上次修改时间</div>
				        <div class="keyword-hander">操作</div>
			   </div>
				<c:forEach items="${serviceTypes }" var="thisServiceType">
					<c:set var="id" value="${thisServiceType.key }id" scope="page"/>
					<c:set var="serviceType" value="${thisServiceType.key }serviceType" scope="page"/>
					<c:set var="messageType" value="${thisServiceType.key }messageType" scope="page"/>
					<c:set var="module" value="${thisServiceType.key }module" scope="page"/>
					<c:set var="message" value="${thisServiceType.key }message" scope="page"/>
					<c:set var="creatTime" value="${thisServiceType.key }creatTime" scope="page"/>
					<div class="keyword-t">
				   	<div class="keyword-serviceType">${thisServiceType.desc }</div>
					<c:if test="${empty map[serviceType] }">
						<div class="keyword-messageType">
							<a href="###" class="easyui-linkbutton"
									 onclick="openFrame('新增信息','${ctx}/keyword/keyword/Add/forward?serviceType=${thisServiceType.key }&wechatId=${wechatId }',400,300);">
									 <i class="icon-bold"></i><span style="padding:5px;">关联关键词</span></a>
						</div>
						<div class="keyword-time"></div>
						<div class="keyword-hander">[还未设置]</div>
					</c:if>
					<c:if test="${not empty map[serviceType] }">
				   		<div class="keyword-messageType">${map[messageType] }</div>
			   			<%-- <div class="keyword-text">${map[message] }</div> --%>
						<div class="keyword-time">${map[creatTime] }</div>
					    <div class="keyword-hander">
						    <a href="###" onclick="showKeyword('${map[id] }','${thisServiceType.key }')">查看</a> | 
						    <a href="###" onclick="editKeyword('${map[id] }','${thisServiceType.key }')">更改关联</a> | 
						    <a href="###"  onclick="deleteKeyword('${map[id] }')">解除关联</a>
					    </div>
					</c:if>
					</div>
				</c:forEach>
			</div>
		</div><!-- /.col -->
	</div><!-- /.row -->
</div><!-- /.page-content -->

<script type="text/javascript">
var wechatId = '${wechatId}';
function showKeyword(id,serviceType){
	openFrame('查看信息','${ctx}/keyword/keyword/Show/forward?serviceType='+serviceType+'&wechatId='+wechatId+'&id=' + id,400,300);
}
function editKeyword(id,serviceType){
	openFrame('编辑信息','${ctx}/keyword/keyword/Add/forward?serviceType='+serviceType+'&wechatId='+wechatId+'&id=' + id,400,300);
}
function deleteKeyword(id){
	var ids = "";
	if(id){
		ids = id;
	} else {
		ids = getCheckedIds('#keywordList');
	}
	layer.confirm('是否要解除关联?', {icon: 3, title:'确认信息'}, function(index){
		if(index){
			var loadIndex = layer.load();
			$.post("${ctx}/keyword/delete",{ids:ids},function(data){
				layer.close(loadIndex);
				if (data.success) {
					layer.msg("解除关联成功");
					reloadList();
				} else {
					layer.alert('解除关联失败，请刷新后重试！');
				}
			},'json');
			layer.close(index);
		}
	});
}
function reloadList(){
	LoadMainPage('${ctx}/keyword/special/forward?wechatId=${wechatId }', 'main-content');
}
</script>
</body>
</html>
