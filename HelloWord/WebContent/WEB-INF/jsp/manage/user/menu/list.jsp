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
<div class="menu-center">
	<div  class="menu-head">
	   		<div class="menu-name">菜单名称</div>
	        <div class="menu-type">菜单类型</div>
	        <div class="menu-key">触发关键词/跳转URL</div>
	        <div class="menu-hander">操作</div>
   </div>
	<c:forEach items="${buttons }" var="button">
		<div class="menu-t">
		   	<div class="menu-name menu-left"><span class="menubutton-name">${button.name }</span></div>
			<div class="menu-type"><c:if test="${empty button.sub_button }">${button.type }</c:if></div>
			<div class="menu-key"><c:if test="${button.type == 'click' }">${button.key }</c:if>
				<c:if test="${button.type == 'view' }">${button.url }</c:if>	
		    </div>
		    <div class="menu-hander"><a href="###" onclick="editMenu('${button.id }')">编辑</a> | 
		    <a href="###"  onclick="deleteMenu('${button.id }')">删除</a></div>
		</div>
		<c:forEach items="${button.sub_button }" var="subButton">
			<div  class="menu-sub">
			   	<div class="menu-name menu-right"><span class="menu-z-line"></span><span class="menubutton-name">${subButton.name }</span></div>
				<div class="menu-type">${subButton.type }</div>
				<div class="menu-key"><c:if test="${subButton.type == 'click' }">${subButton.key }</c:if>
					<c:if test="${subButton.type == 'view' }">${subButton.url }</c:if>	
			    </div>
			    <div class="menu-hander"><a href="###" onclick="editMenu('${subButton.id }')">编辑</a> | 
			    <a href="###" onclick="deleteMenu('${subButton.id }')">删除</a></div>
			</div>
		</c:forEach>
	</c:forEach>
	<div class="creat-menu-box"><button type="button" onclick="creatMenu('${wechatId}')">生成菜单</button></div>
</div>
<script type="text/javascript">
function creatMenu(wechatId){
	layer.confirm('是否现在生成自定义菜单?', {icon: 3, title:'确认信息'}, function(index){
		if(index){
			var loadIndex = layer.load();
			$.post("${ctx}/user/menu/creatMenu",{wechatId:wechatId},function(data){
				layer.close(loadIndex);
				if (data.success) {
					layer.msg("操作成功");
				} else {
					layer.alert(data.desc);
				}
			},'json');
	  		layer.close(index);
		}
	});  
}

function editMenu(id){
	openFrame('新增信息','${ctx}/user/menu/edit/forward?id=' + id,600,400);
}
function deleteMenu(id){
	layer.confirm('是否要删除该条信息?', {icon: 3, title:'确认信息'}, function(index){
		if(index){
			$.post("${ctx}/user/menu/delete",{id:id},function(data){
				layer.close(index);
				if (data.success) {
					layer.msg("删除成功");
					reloadList();
				} else {
					layer.alert('删除失败，请刷新后重试！');
				}
			},'json');
		}
	});
}

</script>
</body>
</html>
