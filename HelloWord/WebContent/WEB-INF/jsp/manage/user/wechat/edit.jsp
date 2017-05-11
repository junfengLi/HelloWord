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
<div class="page-content">
	<div class="row">
		<div class="col-xs-12">
			<div class="alert alert-block alert-success">
				<span><c:if test="${empty wechat }">平台其他功能需在完善信息后才能使用！</c:if>
				<c:if test="${!empty wechat}">微信信息管理</c:if>
				——请如实填写您的信息
				</span>
			</div>
		</div><!-- /.col -->
	</div><!-- /.row -->
	<form action="${ctx}/usermanage/wechatSave" id="userForm" method="post">
	<table  class="tableForm">
		<tr>
			<td width="25%" class="titleTd">微信原始ID：</td>
			<c:if test="${empty wechat }">
			<td  width="30%">
				<input type="text" id="wechatid" name="wechatid" placeholder="微信原始ID"
			  	 class="col-xs-12 col-sm-10" autocomplete="off" />
		  	</td>
		  	<td width="35%">【微信原始ID填写后不能修改】</td>
			</c:if>
			<c:if test="${!empty wechat}">
			<td  width="30%">
				${wechat.wechatid }【微信原始ID不能修改】
				<input type="hidden" name="wechatid" value="${wechat.wechatid }" />
			</td>
			<td width="35%"></td>
			</c:if>
		</tr>
		<tr>
			<td class="titleTd">微信名称：</td>
			<td>
			<input type="text" id="wechatname" name="wechatname" value="${wechat.wechatname }"
			 placeholder="微信名称：" class="col-xs-12 col-sm-10" autocomplete="off" />
			</td>
			<td>例：杭州生活公众号</td>
		</tr>
		<tr>
			<td class="titleTd">微信AppId：</td>
			<td>
			<input type="text" id="appid" name="appid" value="${wechat.appid }" onblur="checkAppId()"
			 placeholder="微信AppId：" class="col-xs-12 col-sm-10" autocomplete="off" />
			</td>
			<td></td>
		</tr>
		<tr>
			<td class="titleTd">微信AppSecred：</td>
			<td>
			<input type="text" id="appsecred" name="appsecred" value="${wechat.appsecred }" onblur="checkAppId()"
			 placeholder="微信AppSecred：" class="col-xs-12 col-sm-10" autocomplete="off" />
			</td>
			<td></td>
		</tr>
		<tr>
			<td class="titleTd">公众号类型：</td>
			<td>
			<input type="hidden" id="wechattype" name="wechattype" value="${wechat.wechattype }" />
			<div id="wechat_type_text">${wechatTypeDesc }</div>
			</td>
			<td></td>
		</tr>
		<tr>
			<td class="titleTd">公众号头像：</td>
			<td>
			<div class="row">
			<%-- <c:if test="${not empty accessory}"> 
			  <img id="photoImg" width="80" height="80" style="display: block;" src="${accessory.url}" />
			</c:if> --%>
				<div class="col-xs-12 col-sm-10" >
					<div id="dropzone">
						<div class="fallback"></div>
						<div class="dropzone"></div>
					</div><!-- PAGE CONTENT ENDS -->
				</div><!-- /.col -->
			</div><!-- /.row -->
			</td>
			<td><div class="fileinput-button icon-cloud-upload btn btn-info">上传照片</div></td>
		</tr>
		<tr>
			<td class="titleTd">初始粉丝数：</td>
			<td>
			<input type="text" id="wechatfans" name="wechatfans" value="${wechat.wechatfans }" 
			 placeholder="初始粉丝数：" class="col-xs-12 col-sm-10" autocomplete="off" />
			</td>
			<td>请填写微信公众号当前粉丝数</td>
		</tr>
		<tr>
			<td class="titleTd">微信号：</td>
			<td>
			<input type="text" id="wechatnumber" name="wechatnumber" value="${wechat.wechatnumber }" 
			 placeholder="微信号：" class="col-xs-12 col-sm-10" autocomplete="off" />
			</td>
			<td></td>
		</tr>
	</table>
	<div class="row">
		<div class="col-xs-6" ></div>
		<div class="col-xs-6" >
			<input type="hidden" name="id" value="${wechat.id }" />
			<input type="hidden" name="userid" value="${userId }" />
			<input type="submit" class="btn btn-primary" value="提交" />
		</div>
	</div>
	</form>
</div>

	<script type="text/javascript">
	var layer;
	layui.use(['layer', 'form'], function(){
		layer = layui.layer;
	});
	var hasWechat = '${not empty wechat }';
	var hasAccessory = '${not empty accessory}';
	jQuery(function($){
		 $('#userForm').validate({
				errorElement: 'div',
				errorClass: 'error-msg',
				focusInvalid: false,
				submitHandler: function() {  
					 formSubmit('userForm');
		        },
				rules: {
					wechatid: {
						required: true,
						maxlength: 16
					},
					wechatname:{
						maxlength:50
					},
					appid:{
						required:true,
						maxlength:18
					},
					appsecred:{
						required:true,
						maxlength:32
					},
					wechatnumber:{
						maxlength:50
					},
					wechatfans:{
						number:true,
						rangelength:[0,10]
					}
				},
				messages: {
					wechatid:{
						required:"请输入微信原始ID",
						maxlength: "最多输入{0}个字符"
					},
					wechatname:{
						maxlength:"最多输入{0}个字符"
					},
					appid:{
						required:"请输入微信appid",
						maxlength:"最多输入{0}个字符"
					},
					appsecred:{
						required:"请输入微信appsecred",
						maxlength:"最多输入{0}个字符"
					},
					wechatnumber:{
						maxlength:"最多输入{0}个字符"
					},
					wechatfans:{
						number:"只能输入数字",
						rangelength:"最多只能输入{1}位数字"
					}
				},
				errorPlacement: function(error, element) { //错误信息位置设置方法
					var id = element.attr("id");
					layer.tips(error.html(), '#' + id, {tips: [3, '#78BA32']});
				}
			});
		
		
		
		
		Dropzone.autoDiscover = false;
		try {
		  $(".dropzone").dropzone({
			  url: "${ctx}/accessory/upload?module=test",
		    paramName: "file", // The name that will be used to transfer the file
		    thumbnailWidth: 80,
			thumbnailHeight: 80,
			parallelUploads: 20,
			autoQueue: false, // Make sure the files aren't queued until manually added
			clickable: ".fileinput-button", // Define the element that should be used as click trigger to select files.
			maxFiles: 1,
	        maxFilesize: 2,	// MB
			addRemoveLinks : true,
			dictDefaultMessage:'上传',
			init: function() {
				if (hasAccessory == 'true') {
					var mockFile = {name: '${accessory.name}',size: '${accessory.filesize}'};
					this.emit('addedfile', mockFile);
			        this.emit('thumbnail', mockFile, '${accessory.url}');
			        this.emit('success', mockFile);
			        this.emit('processing', mockFile);
			        this.emit('complete', mockFile);
					this.options.maxFiles = this.options.maxFiles - 1;
					$('.fileinput-button').hide();
				}
			    this.on("success", function(file) {
			    	$('.fileinput-button').hide();
	                //console.log("File " + file.name + "uploaded");
	            });
	            this.on("removedfile", function(file) {
	            	$('.fileinput-button').show();
	                //console.log("File " + file.name + "removed");
	            });
			  },
			dictDefaultMessage : '',//'<span class="bigger-150 bolder"><i class="icon-caret-right red"></i> Dropiles</span>to upload \<span class="smaller-80 grey">(or click)</span> <br /> \<i class="upload-icon icon-cloud-upload blue icon-3x"></i>',
			dictResponseError: 'Error while uploading file!',				
			//change the previewTemplate to use Bootstrap progress bars
			previewTemplate: "<div class=\"dz-preview dz-file-preview\">\n  <div class=\"dz-details\">\n    <div class=\"dz-filename\"><span data-dz-name></span></div>\n    <div class=\"dz-size\" data-dz-size></div>\n    <img data-dz-thumbnail />\n  </div>\n  <div class=\"progress progress-small progress-striped active\"><div class=\"progress-bar progress-bar-success\" data-dz-uploadprogress></div></div>\n  <div class=\"dz-success-mark\"><span></span></div>\n  <div class=\"dz-error-mark\"><span></span></div>\n  <div class=\"dz-error-message\"><span data-dz-errormessage></span></div>\n</div>"
		  });
		} catch(e) {
		  alert('Dropzone.js does not support older browsers!');
		}
		
		});
	function checkAppId(){
		var appId = $("#appid").val();
		var appSecred = $("#appsecred").val();
		if(appId != '' && appSecred != ''){
			$.post('${ctx}/usermanage/wechatCheckAppId',{appId:appId,appSecred:appSecred},function(data){
				if(data.success){
					$("#wechattype").val(data.wechattype);
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
		if (hasWechat == 'true') {
			LoadMainPage('${ctx }/usermanage/show/load?wechatId=${wechat.wechatid }','main-content');
		} else {
			window.location.href='${ctx}/manage?addWechat=1';
		}
	}
	</script>
</body>
</html>
