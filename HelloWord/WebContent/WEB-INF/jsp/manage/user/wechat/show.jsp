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
<div id="queryDiv" class="easyui-layout" data-options="fit:true">
	<div data-options="cache:false,region:'center',border:false">
	<div id="particles">
		<div class="intro">
			<div class="manage-main">
				<div class="manage-top">
					微信基本信息
					<h2><a href="###" onclick="refreshUrl('contentIndexDiv','${ctx}/user/wechatEdit/forward?wechatId=${wechat.wechatid }')">编辑</a></h2>
				</div>
				<div class="manage-content">
					<div class="manage-con-right">
						<div class="m-hr"></div>
							<div class="input-box">
								<div class="con-t">
									<span>*</span>微信原始ID：
								</div>
								<div class="input-con">
									${wechat.wechatid }
								</div>
							</div>
							<div class="input-box">
								<div class="con-t">
									<span>*</span>微信名称：
								</div>
								<div class="input-con">
									${wechat.wechatname }
								</div>
							</div>
							<div class="input-box">
								<div class="con-t">
									<span>*</span>微信AppId：
								</div>
								<div class="input-con">
									${wechat.appid }
								</div>
							</div>
							<div class="input-box">
								<div class="con-t">
									<span>*</span>微信appSecred：
								</div>
								<div class="input-con">
									${wechat.appsecred }
								</div>
							</div>
							<div class="input-box">
								<div class="con-t">
									<span>*</span>公众号类型：
								</div>
								<div class="input-con">
									<div id="wechat_type_text">${wechatTypeDesc }</div>
								</div>
							</div>
							<div class="input-box">
								<div class="con-t">
									<span>*</span>粉丝数：
								</div>
								<div class="input-con">
									${wechat.wechatfans }
								</div>
							</div>
							<div class="input-box">
								<div class="con-t">
									<span>*</span>微信号：
								</div>
								<div class="input-con">
									${wechat.wechatnumber }
								</div>
							</div>
							<div class="input-box input-box-image">
								<div class="con-t">
												<span>*</span>公众号头像：
								</div>
								<div class="input-con">
									<div style="padding-right:10px;float:left;">
										<img id="photoImg" width="80" height="80" 
										style="display: block;"
										<c:choose>
									 		<c:when test="${not empty accessory}">   
										    	src="${accessory.url}" 
										  	</c:when>
										  	<c:otherwise>   
										    	src="${ctx }/static/images/link/link.jpg" 
										  	</c:otherwise> 
										</c:choose>
										/>
									</div>
								</div>
							</div>
							<div class="input-box">
								<div class="con-t">
									<span>*</span>开发者接口地址：
								</div>
								<div class="input-con">${tool:getOpenUrl(wechat.wechatid)}</div>
							</div>
							<div class="input-box">
								<div class="con-t">
									<span>*</span>开发者token：
								</div>
								<div class="input-con">
									qbtest
								</div>
							</div>
							</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

</body>
</html>
