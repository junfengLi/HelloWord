<%@page import="com.web.commons.util.ConfigUtil"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<jsp:include page="../css-js.jsp"></jsp:include>
</head>
<body>
	<div id="AdvanceDiv" class="easyui-layout" data-options="fit:true">
<form id="messageImgForm" method="post" action="${ctx }/user/module/save">
	<div data-options="region:'center',cache:false,border:false">
		<table class="tableForm">
			<tr class="keyword-tr" <c:if test="${isMessage == '0'}"> style="display:none;" </c:if> >
				<td width="20%" class="titleTd " >关键词：</td>
				<td  colspan="3" >
				<input class="easyui-validatebox" style="width:340px" name="keyword" id="keyword" 
				data-options="validType:'maxlength[100]'" onblur="checkKeyword()" value="${keyword.keyword }"/>
				<span style="color: #FA413F;">多个关键词用“，”隔开</span>
				</td>
			</tr>
			<tr >
				<td class="titleTd" width="20%">是否作为图文消息：<font color="red">*</font></td>
				<td colspan="3" width="70%" >
					<input type="radio" name="isMessage" value="1" <c:if test="${isMessage != '0'}"> checked="checked" </c:if>/> 是 
					<input type="radio" name="isMessage" value="0" <c:if test="${isMessage == '0'}"> checked="checked" </c:if> /> 否
				</td>
			</tr>									
			<tr >
				<td class="titleTd" width="20%">标题：<font color="red">*</font></td>
				<td colspan="3" width="70%" >
				<input class="easyui-validatebox" type="text" name="title" 
				style="width:340px;"  value="${messageImg.title }"
				data-options="required:true,tipPosition:'right',validType:'maxlength[100]'"></td>
			</tr>
			<tr>
				<td class="titleTd">网站排序：<font color="red">*</font></td>
				<td><input class=" easyui-validatebox" style="width:50px; float: left;"
					name="imgSort" id="description" value="${messageImg.imgSort }"
					data-options="required:true,validType:'numbers[3]'">
					<span style=" display: block; text-align: center;  color: red;"> 请输入三位排序码如：999 </span>
				</td>
			</tr>
			<tr>
				<td class="titleTd">模板样式：<font color="red">*</font></td>
				<td >
				<input class="easyui-combobox" 
							name="demo" 
							data-options="required:true,
									url:'${ctx}/user/wsite/demoTree?demoType=content',
									valueField:'id',
									textField:'text',
									editable:false,
									panelHeight:'auto',
									value:'${wechat.contentDemo }'
							">
				</td>
			</tr>						
			<tr>
			<td class="titleTd">封图：</td>
			<td colspan="3">
				<input type="hidden" id="accessoryIds" name="accessoryIds"/>
				<div style="padding-right:10px;float:left;">
					<img id="photoImg" width="200" height="80" 
					style="display: block;"
					<c:choose>
				 		<c:when test="${not empty accessory}">   
					    	src="${accessory.url}" 
					  	</c:when>
					  	<c:otherwise>   
					    	src="${ctx }/static/images/wechat/wechat_photo.png" 
					  	</c:otherwise> 
					</c:choose>
					/></div>
					<div style=" width:150px;float:left; height:25px;text-align: center;    margin-top: 15px;">
					<input type="button" id="uploadButton" value="上传照片" />
					<span style="  width:150px;  display: block;  text-align: center;  color: red;
					  padding-top: 9px;"> * 单图文建议图片尺寸（200x50）</span>
				</div>
			</td>	
			</tr>
			<tr>
				<td class="titleTd">简介：</td>
				<td colspan="3"><textarea style="width:400px;padding:5px;"
						rows="3" id="description" name="description"
						data-options="required:true,validType:'maxlength[500]'">${messageImg.description }</textarea>
				</td>
			</tr>
			<tr>
				<td class="titleTd">外链地址：</td>
				<td colspan="3"><input class="easyui-validatebox"
					name="setUrl" id="setUrl" style="width:340px;"  value="${messageImg.setUrl }"
					data-options="validType:['url','maxlength[510]']">
				<span style="color:#FA413F;">不填跳转站内链接</span>
				</td>
			</tr>
			<tr>
				<td colspan="4" align="center"><textarea style="width: 670px;"
						rows="5" id="content" name="content"
						data-options="captions:'内容'">${messageImg.content }</textarea></td>
			</tr>
		</table>
	</div>
	<div data-options="region:'south',cache:false,border:false" class="windowBtnDiv" >
		<div class="windowBtn">
			<input type="hidden" name="wechatId" value="${wechatId }" />
			<input type="hidden" name="keywordId" value="${keyword.id }" />
			<input type="hidden" name="parentMenuUid" value="${menuUid }" />
			<input type="hidden" name="menuUid" value="${messageImg.menuUid }" />
			<input type="hidden" name="id" value="${messageImg.id }" />
			<input type="hidden" name="messageType" value="Img" />
			<input type="hidden" name="isMenu" value="0" />
			<input type="hidden" name="isSite" value="1" />
			<input type="hidden" name="serviceType" value="Text" />
			<input type="button" class="btnSubmit" name="submitBtn" onclick="checkKeyword('messageImgForm')">
			<input type="button" class="btnClose"  name="closeBtn" onclick="closeFrame();">
		</div>
	</div>
</form>
</div>
<script>
var module = '${module}';
var wechatId = '${wechatId}';
var keywordId = '${keyword.id }';
$(function(){
	$("input[name='isMessage']").click(function(){
		var val = $(this).val();
		if(val == '1'){
			$(".keyword-tr").show();
			$('#keyword').validatebox({required: true});
		} else {
			$(".keyword-tr").hide();
			$('#keyword').validatebox({required: false});
		}
		
	});
	
	window.editor = KindEditor.create('#content',{height : '300px'});
	initUploadButton("uploadButton", "photoImg", "accessoryIds",'<%=ConfigUtil.MODULE_WECHAT_IMG %>', 'image', 3*1024*1024, 200, 50,true);
});
function submitHandler(obj){
	if(obj.success){
		parent.reloadList();
		closeFrame();
	}
}

function checkKeyword(formId){
	var keyword = $('#keyword').val();
	var isValid = $('#keyword').validatebox('isValid');
	if (isValid) {
		$.post('${ctx}/user/keyword/checkKeyword',{module:module,keywordId:keywordId,keyword:keyword,wechatId:wechatId},function(data){
			if(!data.success){
				layer.alert(data.checkKeyword);
			} else {
				if(formId){
					editor.sync();
					formSubmit(formId);
				}
			}
		},'json');
	} else {
		//layer.msg('请正确输入关键词');
	}
}
</script>
</body>
</html>
