<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/WEB-INF/tld/common.tld" prefix="common" %>
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
<!-- 内部主要框架 -->
<div id="mainDiv" class="easyui-layout" data-options="fit:'true'">
	<!--left导航条-->
	<div class="leftNav" style="width:194px;"  data-options="region:'west',cache:false,border:false,collapsible:false">
		<div class="main-container" id="main-container">
			<div class="main-container-inner">
				<a class="menu-toggler" id="menu-toggler" href="#">
					<span class="menu-text"></span>
				</a>
				<div class="sidebar" id="sidebar">
					<ul class="nav nav-list">
						<c:if test="${!hasWechat }">
						<c:set var="defaultUrl" value="${ctx}/user/wechatAdd/forward" />
						<li class="open active">
							<a href="#" class="dropdown-toggle">
								<i class="icon-desktop"></i>
								<span class="menu-text"> 信息管理 </span>
								<b class="arrow icon-angle-left"></b>
							</a>
							<ul class="submenu">
								<li class="active">
									<a href="javascript:void(0);" onclick="leftJump(this,'${ctx}/user/wechatAdd/forward')">
										<i class="icon-double-angle-right"></i>
										微信信息管理
									</a>
								</li>
								<!-- <li>
									<a href="#" class="dropdown-toggle">
										<i class="icon-double-angle-right"></i>
										三级菜单
										<b class="arrow icon-angle-left"></b>
									</a>
									<ul class="submenu">
										<li>
											<a href="#" class="dropdown-toggle">
												<i class="icon-pencil"></i>
												第四级
												<b class="arrow icon-angle-left"></b>
											</a>
											<ul class="submenu">
												<li>
													<a href="#">
														<i class="icon-plus"></i>
														添加产品
													</a>
												</li>
											</ul>
										</li>
									</ul>
								</li> -->
							</ul>
						</li>
						</c:if>
						<c:if test="${hasWechat }">
						<c:set var="defaultUrl" value="${ctx}/user/wechatShow/forward?wechatId=${wechatId }" />
						<li class="open active">
							<a href="#" class="dropdown-toggle">
								<i class="icon-list"></i>
								<span class="menu-text"> 信息管理 </span>
								<b class="arrow icon-angle-left"></b>
							</a>
							<ul class="submenu">
								<li class="active">
									<a href="javascript:void(0);" onclick="leftJump(this,'${ctx}/user/wechatShow/forward?wechatId=${wechatId }')">
										<i class="icon-double-angle-right"></i>
										微信信息管理
									</a>
								</li>
								<c:if test="${wechat.wechatType != 'D_W'}">
									<li class="">
										<a href="javascript:void(0);" onclick="leftJump(this,'${ctx}/user/menu/query/forward?wechatId=${wechatId }')">
											<i class="icon-double-angle-right"></i>
											自定义菜单管理
										</a>
									</li>
								</c:if>
							</ul>
						</li>
						<li class="open">
							<a href="#" class="dropdown-toggle">
								<i class="icon-list"></i>
								<span class="menu-text"> 自动回复管理 </span>
								<b class="arrow icon-angle-left"></b>
							</a>
							<ul class="submenu">
								<li class="">
									<a href="javascript:void(0);" onclick="leftJump(this,'${ctx}/user/keyword/query/forward?wechatId=${wechatId }')">
										<i class="icon-double-angle-right"></i>
										普通消息处理
									</a>
								</li>
								<li class="">
									<a href="javascript:void(0);" onclick="leftJump(this,'${ctx}/user/keyword/special/forward?wechatId=${wechatId }')">
										<i class="icon-double-angle-right"></i>
										特殊消息处理
									</a>
								</li>
								<%-- <li class="">
									<a href="javascript:void(0);" onclick="leftJump(this,'${ctx}/user/wechatAdd/forward')">
										<i class="icon-double-angle-right"></i>
										客服消息回复
									</a>
								</li> --%>
							</ul>
						</li>
						<li class="open">
							<a href="#" class="dropdown-toggle">
								<i class="icon-list"></i>
								<span class="menu-text"> 微站管理 </span>
								<b class="arrow icon-angle-left"></b>
							</a>
							<ul class="submenu">
								<li class="">
									<a href="javascript:void(0);" onclick="leftJump(this,'${ctx}/user/wsite/base/forward?wechatId=${wechatId }')">
										<i class="icon-double-angle-right"></i>
										微站基本信息管理
									</a>
								</li>
								<li class="">
									<a href="javascript:void(0);" onclick="leftJump(this,'${ctx}/user/wsite/query/forward?wechatId=${wechatId }')">
										<i class="icon-double-angle-right"></i>
										模板管理
									</a>
								</li>
								<li class="">
									<a href="javascript:void(0);" onclick="leftJump(this,'${ctx}/user/wsite/banner/forward?wechatId=${wechatId }')">
										<i class="icon-double-angle-right"></i>
										幻灯片图片
									</a>
								</li>
								<li class="">
									<a href="javascript:void(0);" onclick="leftJump(this,'${ctx}/user/module/query/forward?wechatId=${wechatId }')">
										<i class="icon-double-angle-right"></i>
										模块列表
									</a>
								</li>
								<%-- <li class="">
									<a href="javascript:void(0);" onclick="leftJump(this,'${ctx}/user/wsite/query/forward?wechatId=${wechatId }')">
										<i class="icon-double-angle-right"></i>
										自定义导航
									</a>
								</li> --%>
							</ul>
						</li>
						</c:if>
					</ul><!-- /.nav-list -->
				</div>
			</div>
		</div>
	</div>
	<!--main 主要展现区域-->
	<div class="contentRegion" data-options="cache:'false',region:'center',border:0">
		<div id="contentIndexDiv" class="easyui-layout" data-options="fit:'true'">
			<!--main 主要展现区域-->			
			<div class="contentRegionCenter" 
			data-options="region:'center',cache:false,href:'${defaultUrl }',border:false"></div>
		</div>
	</div>
</div>
<script src="${ctx}/static/js/ace/ace.min.js"></script>
</body>
</html>
