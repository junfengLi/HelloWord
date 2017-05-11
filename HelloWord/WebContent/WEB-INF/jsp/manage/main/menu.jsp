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
</head>
<body>
			<ul class="nav nav-list">
				<li class="active">
					<a href="#" class="menu-text" onclick="return LoadPage(this,'main-content');" data-url="${ctx }/manage/index/load">
						<i class="icon-dashboard"></i>
						<span class="menu-text">控制台</span>
					</a>
				</li>
				<c:if test="${ empty wechat }">
				<li class="">
					<a href="#" class="dropdown-toggle">
						<i class="icon-edit"></i>
						<span class="menu-text"> 信息管理 </span>
						<b class="arrow icon-angle-down"></b>
					</a>
					<ul class="submenu">
						<li class="" >
							<a href="#" class="menu-text" onclick="return LoadPage(this,'main-content');" data-url="${ctx }/usermanage/edit/load">
								<i class="icon-double-angle-right"></i>
								<span class="menu-text">微信信息管理</span>
							</a>
						</li>
					</ul>
				</li>
				</c:if>
				<c:if test="${not empty wechat }">
				<li class="">
					<a href="#" class="dropdown-toggle">
						<i class="icon-edit"></i>
						<span class="menu-text"> 信息管理 </span>
						<b class="arrow icon-angle-down"></b>
					</a>
					<ul class="submenu">
						<li class="" >
							<a href="#" class="menu-text" onclick="return LoadPage(this,'main-content');" data-url="${ctx }/usermanage/show/load?wechatId=${wechat.wechatid }">
								<i class="icon-double-angle-right"></i>
								<span class="menu-text">微信信息管理</span>
							</a>
						</li>
							<li class="">
								<a href="javascript:void(0);" onclick="return LoadPage(this,'main-content');" data-url="${ctx}/menu/query/forward?wechatId=${wechatId }">
									<i class="icon-double-angle-right"></i>
									自定义菜单管理
								</a>
							</li>
						<c:if test="${wechat.wechattype != 'D_W'}">
						</c:if>
					</ul>
				</li>
				<li class="">
					<a href="#" class="dropdown-toggle">
						<i class="icon-cloud"></i>
						<span class="menu-text"> 自动回复管理 </span>
						<b class="arrow icon-angle-down"></b>
					</a>
					<ul class="submenu">
						<li class="" >
							<a href="#" class="menu-text" onclick="return LoadPage(this,'main-content');" data-url="${ctx}/user/keyword/query/forward?wechatId=${wechatId }">
								<i class="icon-double-angle-right"></i>
								<span class="menu-text">普通消息处理</span>
							</a>
						</li>
						<li class="" >
							<a href="#" class="menu-text" onclick="return LoadPage(this,'main-content');" data-url="${ctx}/user/keyword/special/forward?wechatId=${wechatId }">
								<i class="icon-double-angle-right"></i>
								<span class="menu-text">特殊消息处理</span>
							</a>
						</li>
					</ul>
				</li>
				<li class="">
					<a href="#" class="dropdown-toggle">
						<i class="icon-desktop"></i>
						<span class="menu-text"> 微站管理 </span>
						<b class="arrow icon-angle-down"></b>
					</a>
					<ul class="submenu">
						<li class="" >
							<a href="#" class="menu-text" onclick="return LoadPage(this,'main-content');" data-url="${ctx}/user/wsite/base/forward?wechatId=${wechatId }">
								<i class="icon-double-angle-right"></i>
								<span class="menu-text">微站基本信息管理</span>
							</a>
						</li>
						<li class="" >
							<a href="#" class="menu-text" onclick="return LoadPage(this,'main-content');" data-url="${ctx}/user/wsite/query/forward?wechatId=${wechatId }">
								<i class="icon-double-angle-right"></i>
								<span class="menu-text">模板管理</span>
							</a>
						</li>
						<li class="" >
							<a href="#" class="menu-text" onclick="return LoadPage(this,'main-content');" data-url="${ctx}/user/wsite/banner/forward?wechatId=${wechatId }">
								<i class="icon-double-angle-right"></i>
								<span class="menu-text">幻灯片图片</span>
							</a>
						</li>
						<li class="" >
							<a href="#" class="menu-text" onclick="return LoadPage(this,'main-content');" data-url="${ctx}/user/module/query/forward?wechatId=${wechatId }">
								<i class="icon-double-angle-right"></i>
								<span class="menu-text">模块列表</span>
							</a>
						</li>
					</ul>
				</li>
				</c:if>
				<shiro:hasPermission name="sys">
				<li class="">
					<a href="#" class="menu-text" onclick="return LoadPage(this,'main-content');" data-url="${ctx }/user/query/load">
						<i class="icon-user"></i>
						<span class="menu-text"> 用户管理</span>
					</a>
				</li>
				</shiro:hasPermission>
			</ul><!-- /.nav-list -->
			<div class="sidebar-collapse" id="sidebar-collapse">
				<i class="icon-double-angle-left" data-icon1="icon-double-angle-left" data-icon2="icon-double-angle-right"></i>
			</div>
			<script type="text/javascript">
	jQuery(function($) {
		var url = $(".nav-list").find("a.menu-text:first-child").attr("data-url");
		LoadMainPage(url, 'main-content');
		LoadMainPage('${ctx }/usermanage/show/load?wechatId=${wechat.wechatid }','main-content');
	});
</script>
</body>
</html>

