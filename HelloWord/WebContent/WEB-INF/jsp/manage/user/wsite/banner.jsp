<!DOCTYPE html>
<%@page import="com.web.commons.util.ConfigUtil"%>
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
	<div class="set_top">
		<p>微网站Banner图片设置</p>
	</div>
	<div id="artlist">
		<table id="addn">
			<tbody>
				<tr>
					<td class="rowTitle" rowspan="2"><strong>banner图片：</strong></td>
					<td>
						<input type="hidden" id="accessoryIds" name="accessoryIds"
						 value="${accessory.id }" />
						<div id="bannerImg">&nbsp;</div>
					</td>
				</tr>
				<tr>
					<td><div style="    margin-bottom: 10px; margin-left: 20px;float:left; height:25px;">
							<input type="button" id="uploadButton" value="上传照片" />	
						</div>
					</td>
				</tr>
		</table>
	</div>
	<div class="subButton" >
		<input name="id" type="hidden" value="${site.id }"/>	
		<input	type="button" class="btnSubmit"	onclick="saveBanners()">
	</div>
	<script type="text/javascript">
	var wechatId = '${wechatId}';
	$(function(){
		showAllAccessory("bannerImg", "${accessoryListJson}", "image", true, "accessoryIds", 104,35);
		initUploadButton("uploadButton", "bannerImg", "accessoryIds", '<%=ConfigUtil.MODULE_WECHAT_BANNER%>',"image",3*1024*1024, 104,35,false);
	});
	function saveBanners(){
		var accessoryIds = $("input[name='accessoryIds']").val();
		var loadIndex = layer.load();
		$.post('${ctx}/user/wsite/saveWechatBanner',{accessoryIds:accessoryIds, wechatId:wechatId},function(data){
			layer.close(loadIndex);
			if (data.success){
				layer.msg('保存成功');
			}
		},'json');		
	}
	</script>
</body>
</html>
