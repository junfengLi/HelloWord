<!DOCTYPE html>
<%@page import="com.web.commons.util.ConfigUtil"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title></title>
</head>
<body>
	<div id="AdvanceDiv" class="easyui-layout" data-options="fit:true">
		<form id="addForm" method="post" action="${ctx}/user/wechatSave"
			style="width: 100%; height: 450px;">
			<div data-options="cache:false,region:'center',border:false">
				<div id="particles">
					<div class="intro">
						<div class="manage-main">
							<div class="manage-top">
								<c:if test="${empty wechat }">平台其他功能需在完善信息后才能使用！</c:if>
								<c:if test="${!empty wechat}">微信信息管理</c:if>
								<h2>请如实填写您的账户信息</h2>
							</div>
							<div class="manage-content">
								<div class="manage-con-right">
									<div class="m-hr"></div>
										<div class="input-box">
											<div class="con-t">
												<span>*</span>微信原始ID：
											</div>
											<c:if test="${empty wechat }">
												<div class="input-con">
													<input type="text" id="wechatId" name="wechatId" class="easyui-validatebox manage-text"
													 data-options="required:true,validType:'maxlength[100]'" />
												</div>
												<div class="shuom">例：gh_xae5a4sd1f1</div>
											</c:if>
											<c:if test="${!empty wechat}">
												<div class="input-con">
													${wechat.wechatId }
													<input type="hidden" name="wechatId" value="${wechat.wechatId }" />
												</div>
											</c:if>
										</div>
										<div class="input-box">
											<div class="con-t">
												<span>*</span>微信名称：
											</div>
											<div class="input-con">
												<input type="text" id="wechatName" name="wechatName" value="${wechat.wechatName }"
												 class="easyui-validatebox manage-text" 
												 data-options="required:true,validType:'maxlength[100]'" />
											</div>
											<div class="shuom">例：杭州生活公众号</div>
										</div>
										<div class="input-box">
											<div class="con-t">
												<span>*</span>微信AppId：
											</div>
											<div class="input-con">
												<input type="text" id="appId" name="appId"  onblur="checkAppId()" value="${wechat.appId }"
												 class="easyui-validatebox manage-text"  data-options="required:true,validType:'maxlength[100]'" />
											</div>
										</div>
										<div class="input-box">
											<div class="con-t">
												<span>*</span>微信AppSecred：
											</div>
											<div class="input-con">
												<input type="text" id="appSecred" name="appSecred" onblur="checkAppId()" value="${wechat.appSecred }"
												 class="easyui-validatebox manage-text"  data-options="required:true,validType:'maxlength[100]'" />
											</div>
										</div>
										<div class="input-box">
											<div class="con-t">
												<span>*</span>公众号类型：
											</div>
											<div class="input-con">
												<input type="hidden" id="wechat_type" name="wechatType" value="${wechat.wechatType }" class="manage-text" />
												<div id="wechat_type_text">${wechatTypeDesc }</div>
											</div>
										</div>
										<div class="input-box input-box-image">
											<div class="con-t">
												<span>*</span>公众号头像：
											</div>
											<div class="input-con">
												<input type="hidden" id="accessoryIds" name="accessoryIds"/>
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
													/></div>
													<div style=" width:150px;float:left; height:25px;text-align: center;    margin-top: 15px;">
													<input type="button" id="uploadButton" value="上传照片" />
												</div>
											</div>
											<div id="checkTelMessage" class="cleck-mesg"></div>
											<div class="shuom">图片尺寸（200x200）</div>
										</div>

										<div class="input-box">
											<div class="con-t">
												<span>*</span>初始粉丝数：
											</div>
											<div class="input-con">
												<input type="text" id="wechatFans" name="wechatFans" value="${wechat.wechatFans }" class="easyui-validatebox manage-text" />
											</div>
											<div class="shuom" id="msemail">请填写微信公众号当前粉丝数</div>
										</div>

										<div class="input-box">
											<div class="con-t">
												<span>*</span>微信号：
											</div>
											<div class="input-con">
												<input type="text" id="wechatNumber" name="wechatNumber" value="${wechat.wechatNumber }" class="easyui-validatebox manage-text" />
											</div>
											<div class="shuom"></div>
										</div>

										<input type="hidden" name="id" value="${wechat.id }" />
										<input type="hidden" name="userId" value="${userId }" />
										<div class="submit_Line"></div>
										<input type="button" class="btnSubmit" onclick="formSubmit('addForm')">
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>
	<script type="text/javascript">
	$(function(){

	});
	function checkAppId(){
		var appId = $("#appId").val();
		var appSecred = $("#appSecred").val();
		if(appId != '' && appSecred != ''){
			$.post('${ctx}/user/wechatCheckAppId',{appId:appId,appSecred:appSecred},function(data){
				if(data.success){
					$("#wechat_type").val(data.wechatType);
					$("#wechat_type_text").html(data.desc);
				} else {
					$("#appId").val('');
					$("#appSecred").val('');
					layer.alert('appId或appSecred参数错误');
				}
			},"json");
		}
	}
	function submitHandler(obj){
		window.location.href='${ctx}/user/index/forward';
	}
	</script>
</body>
</html>
