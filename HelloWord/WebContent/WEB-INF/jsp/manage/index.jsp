<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>开发者信息管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<jsp:include page="include/css-js.jsp" />
<jsp:include page="include/header-js.jsp" />
</head>
<body>
<div class="navbar navbar-default navbar-fixed-top" id="navbar">
	<script type="text/javascript">
		try{ace.settings.check('navbar' , 'fixed')}catch(e){}
	</script>

	<div class="navbar-container" id="navbar-container">
		<div class="navbar-header pull-left">
			<a href="#" class="navbar-brand">
				<small>
					<i class="icon-leaf"></i>
					开发者信息管理系统
				</small>
			</a><!-- /.brand -->
		</div><!-- /.navbar-header -->
		<div class="navbar-header pull-right" role="navigation">
			<ul class="nav ace-nav">
				<li class="light-blue">
					<a data-toggle="dropdown" href="#" class="dropdown-toggle">
						<img class="nav-user-photo" src="${ctx}/static/ace/avatars/user.jpg" />
						<span class="user-info">
							<small>欢迎光临</small>
							${user.realname }
						</span>

						<i class="icon-caret-down"></i>
					</a>

					<ul class="user-menu pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
						<li>
							<a href="#" onclick="openFrame('编辑信息','${ctx }/user/useredit/forward?id=${user.id }',400,320);" >
								<i class="icon-cog"></i>
								设置
							</a>
						</li>

						<li>
							<a href="#" onclick="openFrame('查看信息','${ctx }/user/usershow/forward?id=${user.id }',500,420);" >
								<i class="icon-user"></i>
								个人资料
							</a>
						</li>

						<li class="divider"></li>
						<li>
							<a href="${ctx }/logout">
								<i class="icon-off"></i>
								退出
							</a>
						</li>
					</ul>
				</li>
			</ul><!-- /.ace-nav -->
		</div><!-- /.navbar-header -->
	</div><!-- /.container -->
</div>

<div class="main-container">
	<script type="text/javascript">
		try{ace.settings.check('main-container' , 'fixed')}catch(e){}
	</script>

	<div class="main-container-inner"  id="main-container">
		<a class="menu-toggler" id="menu-toggler" href="#">
			<span class="menu-text"></span>
		</a>
		<div class="sidebar sidebar-fixed" id="sidebar">
			<script type="text/javascript">
				try{ace.settings.check('sidebar' , 'fixed')}catch(e){}
			</script>
			<ul class="nav nav-list">
				<li class='<c:if test="${addWechat=='' }">active</c:if>'>
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
				<li class="<c:if test="${addWechat=='1' }">active open</c:if>">
					<a href="#" class="dropdown-toggle">
						<i class="icon-edit"></i>
						<span class="menu-text"> 信息管理 </span>
						<b class="arrow icon-angle-down"></b>
					</a>
					<ul class="submenu">
						<li class="<c:if test="${addWechat=='1' }">active</c:if>" >
							<a href="#" class="menu-text" onclick="return LoadPage(this,'main-content');" data-url="${ctx }/usermanage/show/load?wechatId=${wechat.wechatid }">
								<i class="icon-double-angle-right"></i>
								<span class="menu-text">微信信息管理</span>
							</a>
						</li>
							<li class="">
								<a href="javascript:void(0);" onclick="return LoadPage(this,'main-content');" data-url="${ctx}/menu/query/forward?wechatId=${wechat.wechatid }">
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
							<a href="#" class="menu-text" onclick="return LoadPage(this,'main-content');" data-url="${ctx}/keyword/query/forward?wechatId=${wechat.wechatid }">
								<i class="icon-double-angle-right"></i>
								<span class="menu-text">普通消息处理</span>
							</a>
						</li>
						<li class="" >
							<a href="#" class="menu-text" onclick="return LoadPage(this,'main-content');" data-url="${ctx}/keyword/special/forward?wechatId=${wechat.wechatid }">
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
							<a href="#" class="menu-text" onclick="return LoadPage(this,'main-content');" data-url="${ctx}/user/wsite/base/forward?wechatId=${wechat.wechatid }">
								<i class="icon-double-angle-right"></i>
								<span class="menu-text">微站基本信息管理</span>
							</a>
						</li>
						<li class="" >
							<a href="#" class="menu-text" onclick="return LoadPage(this,'main-content');" data-url="${ctx}/user/wsite/query/forward?wechatId=${wechat.wechatid }">
								<i class="icon-double-angle-right"></i>
								<span class="menu-text">模板管理</span>
							</a>
						</li>
						<li class="" >
							<a href="#" class="menu-text" onclick="return LoadPage(this,'main-content');" data-url="${ctx}/user/wsite/banner/forward?wechatId=${wechat.wechatid }">
								<i class="icon-double-angle-right"></i>
								<span class="menu-text">幻灯片图片</span>
							</a>
						</li>
						<li class="" >
							<a href="#" class="menu-text" onclick="return LoadPage(this,'main-content');" data-url="${ctx}/user/module/query/forward?wechatId=${wechat.wechatid }">
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
				try{ace.settings.check('sidebar' , 'collapsed')}catch(e){}
			</script>
		</div>
		<div class="main-content" id="main-content">
		</div><!-- /.main-content -->
	</div><!-- /.main-container-inner -->
	<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
		<i class="icon-double-angle-up icon-only bigger-110"></i>
	</a>
</div><!-- /.main-container -->
<jsp:include page="include/footer-js.jsp" />
<script type="text/javascript">
	jQuery(function($) {
		var url = $(".nav-list .active").find("a.menu-text:first-child").attr("data-url");
		LoadMainPage(url, 'main-content');
		$('a.menu-text').click(function(){
			if (!$(this).parent().hasClass('active')){
				if (!$(this).parent().parent().parent().hasClass('active')) {
					$('.nav-list').find('.active').find('.submenu').hide();
					$('.nav-list').find('.open').removeClass('open');
				}
				$('.nav-list').find('.active').removeClass('active');
				$(this).parent().addClass('active');
				if ($(this).parent().parent().hasClass('submenu')){
					$(this).parent().parent().parent().addClass('active');
					$(this).parent().parent().parent().addClass('open');
				}
			}
		});	
	});
</script>
</body>
</html>

