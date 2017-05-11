<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/WEB-INF/tld/tool.tld" prefix="tool" %>
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
<div class="page-content">
	<div class="row">
		<div class="col-xs-12">
			<div class="alert alert-block alert-success">
				<span>微信基本信息</span>
				<a href="#" class="menu-text" onclick="return LoadPage(this,'main-content');" data-url="${ctx }/usermanage/edit/load?wechatId=${wechat.wechatid }">
					<span class="icon-cogs"> 编辑 </span>
				</a>
			</div>
		</div><!-- /.col -->
	</div><!-- /.row -->
	<table  class="tableForm">
		<tr>
			<td width="30%" class="titleTd">微信原始ID：</td>
			<td  width="60%">
				${wechat.wechatid }</td>
		</tr>
		<tr>
			<td class="titleTd">微信名称：</td>
			<td>${wechat.wechatname }</td>
		</tr>
		<tr>
			<td class="titleTd">微信AppId：</td>
			<td>${wechat.appid }</td>
		</tr>
		<tr>
			<td class="titleTd">微信AppSecred：</td>
			<td>${wechat.appsecred }</td>
		</tr>
		<tr>
			<td class="titleTd">公众号类型：</td>
			<td>${wechatTypeDesc }</td>
		</tr>
		<tr>
			<td class="titleTd">公众号头像：</td>
			<td>
			<div class="row">
			<c:if test="${not empty accessory}"> 
			  <img id="photoImg" width="80" height="80" style="display: block;" src="${accessory.url}" />
			</c:if>
			</div><!-- /.row -->
			</td>
		</tr>
		<tr>
			<td class="titleTd">初始粉丝数：</td>
			<td>${wechat.wechatfans }</td>
		</tr>
		<tr>
			<td class="titleTd">微信号：</td>
			<td>${wechat.wechatnumber }</td>
		</tr>
		<tr>
			<td class="titleTd">开发者接口地址：</td>
			<td>${tool:getOpenUrl(wechat.wechatid)}</td>
		</tr>
		<tr>
			<td class="titleTd">开发者token：</td>
			<td>qbtest</td>
		</tr>
	</table>
</div>
</body>
</html>
