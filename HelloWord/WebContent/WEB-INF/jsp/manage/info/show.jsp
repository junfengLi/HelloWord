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
<jsp:include page="../include/css-js.jsp" />
</head>
<body>
<div class="row" style="height:460px;">
	<div class="tabbable" >
		<ul class="nav nav-tabs" id="myTab">
			<li class="active">
				<a data-toggle="tab" href="#info">
					<i class="icon-group bigger-110"></i>
					客户信息
				</a>
			</li>
			<c:if test="${not empty speed}">
				<li>
					<a data-toggle="tab" href="#speed">
						<i class="icon-fighter-jet bigger-110"></i>
						采访进度
					</a>
				</li>
			</c:if>
			<c:if test="${not empty online}">
				<li>
					<a data-toggle="tab" href="#online">
						<i class="icon-cloud-upload bigger-110"></i>
						上线进度
					</a>
				</li>
			</c:if>
		</ul>
		<div class="tab-content">
			<div id="info" class="tab-pane in active">
				<table  class="tableForm">
					<tr>
						<td width="30%" class="titleTd">姓名：</td>
						<td  width="70%">${info.name }</td>
					</tr>
					<tr>
						<td class="titleTd">手机：</td>
						<td>${info.mobile }</td>
					</tr>
					<tr>
						<td class="titleTd">是否拒绝采访：</td>
						<td>
						<c:if test="${info.isrefuse == '0' }">否</c:if>
						<c:if test="${info.isrefuse == '1' }">是</c:if>
						</td>
					</tr>
					<tr>
						<td class="titleTd">备用手机：</td>
						<td>${info.mobile2 }</td>
					</tr>
					<tr>
						<td class="titleTd">邮箱：</td>
						<td>${info.email }</td>
					</tr>
					<tr>
						<td class="titleTd">备用邮箱：</td>
						<td>${info.email2 }</td>
					</tr>
					<tr>
						<td class="titleTd">项目名称：</td>
						<td>${info.projectname }</td>
					</tr>
					<tr>
						<td class="titleTd">项目链接：</td>
						<td><c:if test="${not empty info.projecthref }">
						<a href="${info.projecthref }" style="color: #428bca;" target="_blank">项目链接</a></c:if></td>
					</tr>
					<tr>
						<td class="titleTd">服务客服：</td>
						<td>${info.asker }</td>
					</tr>
					<tr>
						<td class="titleTd"> 服务到期时间：</td>
						<td>${servicetime }</td>
					</tr>
					<tr>
						<td class="titleTd">备注：</td>
						<td>${info.note }</td>
					</tr>
					<tr>
						<td class="titleTd">排序：</td>
						<td>${info.seq }</td>
					</tr>
				</table>
			</div>
			<div id="speed" class="tab-pane">
				<table  class="tableForm">
					<tr>
						<td width="30%" class="titleTd"> 编辑后台时间：</td>
						<td width="70%"> ${editbackgroundtime }</td>
					</tr>
					<tr>
						<td class="titleTd"> 推首时间：</td>
						<td>${pushheadtime }</td>
					</tr>
					<tr>
						<td class="titleTd"> 资讯轮播时间：</td>
						<td>${informationtime }</td>
					</tr>
					<tr>
						<td class="titleTd"> 项目集合时间：</td>
						<td>${itemsettime }</td>
					</tr>
					<tr>
						<td class="titleTd"> 首页Banner时间：</td>
						<td>${bannertime }</td>
					</tr>
					<tr>
						<td class="titleTd"> 文章链接：</td>
						<td><c:if test="${not empty online.infohref  }">
									<a href="${online.infohref  }" style="color: #428bca;" target="_blank">文章链接</a></c:if></td>
						<td>
					</tr>
				</table>
			</div>
			<div id="online" class="tab-pane">
				<table  class="tableForm">
					<tr>
						<td width="30%" class="titleTd"> 联系客户时间：</td>
						<td width="70%"> ${speed.asktime }</td>
					</tr>
					<tr>
						<td class="titleTd"> 发送采访提纲时间：</td>
						<td>${sendmenutime }</td>
					</tr>
					<tr>
						<td class="titleTd"> 采访时间：</td>
						<td>${interviewtime }</td>
					</tr>
					<tr>
						<td class="titleTd">采访类型：</td>
						<td> <c:if test="${speed.asktype == 'email' }">邮件采访</c:if>
						<c:if test="${speed.asktype == 'mobile' }">电话采访</c:if>
						</td>
					</tr>
					<tr>
						<td class="titleTd"> 成稿时间：</td>
						<td>${finshnewstime }</td>
					</tr>
					<tr>
						<td class="titleTd"> 客户确认上线时间：</td>
						<td>${onlinetime }</td>
					</tr>
					<tr>
						<td class="titleTd">客户打分：</td>
						<td>${speed.source }</td>
					</tr>
					<tr>
						<td class="titleTd"> 发送设计需求时间：</td>
						<td>${sendneedtime}</td>
					</tr>
					<tr>
						<td class="titleTd"> 发送反馈时间：</td>
						<td>${backtime}</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
	<div class="frame_close">
		<button class="btn btn-info" onclick="parent.closeFrame()" type="button"> 关闭</button>
	</div>
</div><!-- /.row -->
<jsp:include page="../include/footer-js.jsp" />
</body>
</html>

