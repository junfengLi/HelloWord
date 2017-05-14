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
<div class="page-content">
	<div class="row">
		<div class="col-xs-12">
			<div class="alert alert-block alert-success">
				<span>微网站Banner图片设置</span>
			</div>
		</div><!-- /.col -->
	</div><!-- /.row -->
	<div class="row">
	<input type="hidden" name="accessoryIds" id="accessoryIds" />
	<input type="hidden" name="deleteAccessoryNames" id="deleteAccessoryNames" />
	<input name="id" type="hidden" value="${site.id }"/>	
		<div class="col-xs-12 lead" >
			<div id="dropzone">
				<div class="fallback"></div>
				<div class="dropzone"></div>
			</div><!-- PAGE CONTENT ENDS -->
		</div><!-- /.col -->
	</div><!-- /.row -->
	<div class="btn btn-info" onclick="saveBanners()">保存</div>
	<div class="fileinput-button-text text-warning bigger-110 orange" style="display: none;"><i class="icon-warning-sign"></i>图片上传已达上限</div>
	<div class="fileinput-button icon-cloud-upload btn btn-info">上传照片</div>
</div>
	<script type="text/javascript">
	var wechatId = '${wechatId}';
	var layer;
	layui.use(['layer', 'form'], function(){
		layer = layui.layer;
	});
	var accessoryListJson = ${accessoryListJson};
	var fileSize = 0;
	$(function(){
		Dropzone.autoDiscover = false;
		try {
		  $(".dropzone").dropzone({
			  url: "${ctx}/accessory/upload?module=wechatImg",
			//最大文件大小，单位是 MB  
			maxFiles: 2,
		    maxFilesize: 5,  
		    //默认false。如果设为true，则会给文件添加上传取消和删除预览图片的链接  
		    addRemoveLinks: true,  
		    //指明允许上传的文件类型，格式是逗号分隔的 MIME type 或者扩展名。例如：image/*,application/pdf,.psd,.obj  
		    acceptedFiles: "image/*",  

		    thumbnailWidth: 80,
			thumbnailHeight: 80,
			parallelUploads: 20,
			autoQueue: false, // Make sure the files aren't queued until manually added
			clickable: ".fileinput-button", // Define the element that should be used as click trigger to select files.
			dictDefaultMessage:'上传',
		    dictFileTooBig: "图片最大5M",  
		    dictInvalidFileType: "只能上传图片",  
		    dictRemoveFile: "移除",  
			init: function() {
				var arrayJson = $.parseJSON(accessoryListJson);
				$.each(arrayJson, function(i, data){
					var mockFile = {name: data.name,size: data.filesize};
					this.emit('addedfile', mockFile);
			        this.emit('thumbnail', mockFile, '${accessory.url}');
			        this.emit('success', mockFile);
			        this.emit('processing', mockFile);
			        this.emit('complete', mockFile);
			        fileSize++;
			        if (fileSize>=this.options.maxFiles) {
			    		$('.fileinput-button').hide();
			    		$('.fileinput-button-text').show();
					}
				});
			    this.on("success", function(file, data) {
			    	var value = $("#accessoryIds").val();
			    	if (value == '') {
			    		$("#accessoryIds").val(data.id);
					} else {
						$("#accessoryIds").val(value + ";" + data.id);
					}
	            });
			    this.on("addedfile", function(file, data) {
			    	fileSize++;
			    	if (fileSize>=this.options.maxFiles) {
			    		$('.fileinput-button').hide();
			    		$('.fileinput-button-text').show();
					}
	            });
			    
	            this.on("removedfile", function(file) {
	            	var value = $("#deleteAccessoryNames").val();
			    	if (value == '') {
			    		$("#deleteAccessoryNames").val(file.name);
					} else {
						$("#deleteAccessoryNames").val(value + ";" + file.name);
					}
			    	fileSize--;
			    	if (fileSize<this.options.maxFiles) {
			    		$('.fileinput-button').show();
			    		$('.fileinput-button-text').hide();
					}
	            	
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
	function saveBanners(){
		var accessoryIds = $("input[name='accessoryIds']").val();
		var deleteAccessoryNames = $("input[name='deleteAccessoryNames']").val();
		var id = $("input[name='id']").val();
		var loadIndex = layer.load();
		$.post('${ctx}/user/wsite/saveWechatBanner',{accessoryIds:accessoryIds, id:id, deleteAccessoryNames:deleteAccessoryNames},function(data){
			layer.close(loadIndex);
			if (data.success){
				layer.msg('保存成功');
			}
		},'json');		
	}
	</script>
</body>
</html>
