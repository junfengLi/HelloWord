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
<div id="queryDiv" class="easyui-layout" data-options="fit:true">
	<div class="easyui-tabs" style="overflow-y:auto;" data-options="region:'center',cache:false,border:false">
		<c:forEach items="${easyUINodes }" var="node">
			<div title="${node.text }" style="padding:20px;"
			 data-options="href:'${ctx}/user/wsite/list/forward?demoType=${node.id }&wechatId=${wechatId }'">   
					<%-- <div data-options="region:'center',cache:false,border:false">
						<div id="tab" class="easyui-layout" data-options="fit:true">
							<div class="query_tool" data-options="region:'north',height:60,cache:false,border:false">
								<div style="float:left;overflow:hidden;">
									<c:forEach items="${easyUINodes }" var="node">
										<a href="###" id="${node.id }" class="wsite-menu <c:if test="${node.id == demoType }">on</c:if>">
										<c:if test="${node.id == 'index' }"><i class="icon-dashboard"></i></c:if>
										<c:if test="${node.id == 'list' }"><i class="icon-list-ul"></i></c:if>
										<c:if test="${node.id == 'con' }"><i class="icon-file-text"></i></c:if>
										${node.text }</a>
									</c:forEach>
								</div>
							</div>
							<div style="overflow-y:auto;" data-options="region:'center',cache:false,
							href:'${ctx}/user/wsite/list/forward?demoType=${demoType }&wechatId=${wechatId }',border:false"></div>
						</div>
					</div> --%>
			</div>    
		</c:forEach>
	</div>
</div>   
<%-- 

<div id="queryDiv" class="easyui-layout" data-options="fit:true">
	<div data-options="region:'center',cache:false,border:false">
		<div id="queryContentDiv" class="easyui-layout" data-options="fit:true">
			<div class="query_tool" data-options="region:'north',height:60,cache:false,border:false">
				<div style="float:left;overflow:hidden;">
					<c:forEach items="${easyUINodes }" var="node">
						<a href="###" id="${node.id }" class="wsite-menu <c:if test="${node.id == demoType }">on</c:if>">
						<c:if test="${node.id == 'index' }"><i class="icon-dashboard"></i></c:if>
						<c:if test="${node.id == 'list' }"><i class="icon-list-ul"></i></c:if>
						<c:if test="${node.id == 'con' }"><i class="icon-file-text"></i></c:if>
						${node.text }</a>
					</c:forEach>
				</div>
			</div>
			<div style="overflow-y:auto;"data-options="region:'center',cache:false,
			href:'${ctx}/user/wsite/list/forward?demoType=${demoType }&wechatId=${wechatId }',border:false"></div>
		</div>
	</div>
</div>
 --%>
<script type="text/javascript">


</script>
</body>
</html>
