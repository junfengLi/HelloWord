<%@page import="com.web.commons.util.ConfigUtil"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<jsp:include page="../../include/css-js.jsp" />
</head>
<body>
<div class="row">
	<form action="${ctx }/keyword/save" id="userForm" method="post">
	<table  class="tableForm">
		<tr>
			<td width="15%" class="titleTd">标题：</td>
			<td width="30%"><input type="text" id="title" name="title" placeholder="标题"
				value="${messageImg.title }" class="col-xs-12" autocomplete="off" /></td>
			<td width="15%" rowspan="5" class="titleTd">缩略图：</td>
			<td  width="30%" rowspan="5" style="    padding-bottom: 20px;">
			<div class="row" >
			<input type="hidden" name="accessoryIds" id="accessoryIds" />
				<div class="col-xs-10" >
					<div id="dropzone">
						<div class="fallback"></div>
						<div class="dropzone slt"><div class="fileinput-button icon-cloud-upload btn btn-info" style="float: right; width: 44px; height: 100px; white-space: inherit; margin-top: 132px;">上传照片</div></div>
					</div><!-- PAGE CONTENT ENDS -->
					
				</div><!-- /.col -->
			</div><!-- /.row -->
			</td>
		</tr>	
		<tr >
			<td class="titleTd">模板样式：</td>
			<td>
				<select class="form-control input-small" id="form-field-select-1" name="pid" style="width:120px;">
					<c:forEach items="${nodes }" var="node" >
						<option value="${node.id }" <c:if test="${node.id == button.pid}"> selected="selected"</c:if> >${node.text }</option>
					</c:forEach>
				</select>
			</td>
		</tr>	
		<tr>
		<td class="titleTd">网站排序：</td>
				<td>
				<input type="text" id="imgsort" name="imgsort" placeholder="排序"
					value="${messageImg.imgsort }<c:if test="${empty messageImg }">500</c:if>" class="col-xs-12" autocomplete="off" /></td>
		</tr>	
		<tr>
			<td class="titleTd">简介：</td>
			<td>
			<textarea id="form-field-11" name="description" class="autosize-transition form-control" placeholder="简介"
			 style="overflow: hidden; word-wrap: break-word; resize: horizontal; height: 70px;margin: 2px 0px;">${messageImg.description}</textarea>
			</td>
		</tr>	
		<tr>
			<td class="titleTd">外链地址：</td>
			<td><input type="text" id="title" name="seturl" placeholder="不填跳转站内链接"
				value="${messageImg.seturl }" class="col-xs-12" autocomplete="off" /></td>
		</tr>	
		<tr>
			<td  colspan="4" >
			<div class="row" >
			<input type="hidden" name="accessoryIdsz" id="accessoryIdsz" />
				<div class="col-xs-12" >
					<div id="dropzonez">
						<div class="fallback"></div>
						<div class="dropzone zutu"></div>
					</div><!-- PAGE CONTENT ENDS -->
					
				</div><!-- /.col -->
			</div><!-- /.row -->
			<div class="fileinput-buttonz icon-cloud-upload btn btn-info" 
			style="float: right;">上传导航组图</div>
			</td>
		</tr>	
		<tr><td style="height:60px;"><td></tr>
		
	</table>
	<div class="frame_close">
		<input type="hidden" name="menuuid" value="${menuUid }" />
		<input type="hidden" name="ismessage" value="0" />
		<input type="hidden" name="issite" value="1" />
		<input type="hidden" name="ismenu" value="1" />
		<input type="hidden" name="wechatid" value="${wechatId }" />
		<input type="hidden" name="id" value="${messageImg.id }" />
		<button class="btn btn-info" onclick="parent.closeFrame()" type="button"> 关闭</button>
		<input type="submit" class="btn btn-primary" value="提交" />
	</div>
	</form>
</div><!-- /.row -->
<jsp:include page="../../include/footer-js.jsp" />
<script type="text/javascript">
var layer;
layui.use(['layer', 'form'], function(){
	layer = layui.layer;
});
var hasAccessory = '${not empty accessory}';
function submitHandler(obj){
	if (obj.success) {
		parent.pageReload();
		parent.closeFrame();
	} else {
		layer.alert(obj.desc,{skin: 'layui-layer-lan',closeBtn: 0 });
	}
}
jQuery(function($) {
	 $('#userForm').validate({
		focusInvalid: false,
		submitHandler: function() {  
			// 点击提交后处理: 
			$("#content").val($("#editor1").html());
			formSubmit('userForm');
        },
		rules: {
			keyword: {
				required: true,
				maxlength: 20
			},
			title:{
				required: true,
				maxlength: 50
			},
			description:{
				maxlength:1000
			},
			seturl:{url:true}
		},
		errorPlacement: function(error, element) { //错误信息位置设置方法
			var id = element.attr("id");
			layer.tips(error.html(), '#' + id, {tips: [3, '#78BA32']});
		}
	});
	 
	 
	 Dropzone.autoDiscover = false;
		try {
		  $(".slt").dropzone({
			  url: "${ctx}/accessory/upload?module=wechatImg",
			//最大文件大小，单位是 MB  
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
			    this.on("success", function(file, data) {
			    	$('.fileinput-button').hide();
			    	$("#accessoryIds").val(data.id);
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
		
		try {
			  $(".zutu").dropzone({
				  url: "${ctx}/accessory/upload?module=wechatImg",
				//最大文件大小，单位是 MB  
			    maxFilesize: 5,  
			    //默认false。如果设为true，则会给文件添加上传取消和删除预览图片的链接  
			    addRemoveLinks: true,  
			    //指明允许上传的文件类型，格式是逗号分隔的 MIME type 或者扩展名。例如：image/*,application/pdf,.psd,.obj  
			    acceptedFiles: "image/*",  

			    thumbnailWidth: 80,
				thumbnailHeight: 80,
				parallelUploads: 20,
				autoQueue: false, // Make sure the files aren't queued until manually added
				clickable: ".fileinput-buttonz", // Define the element that should be used as click trigger to select files.
				dictDefaultMessage:'上传',
				dictFileTooBig: "图片最大5M",  
				dictInvalidFileType: "只能上传图片",  
				dictRemoveFile: "移除",  
				init: function() {
					if (hasAccessory == 'true') {
						var mockFile = {name: '${accessory.name}',size: '${accessory.filesize}'};
						this.emit('addedfile', mockFile);
				        this.emit('thumbnail', mockFile, '${accessory.url}');
				        this.emit('success', mockFile);
				        this.emit('processing', mockFile);
				        this.emit('complete', mockFile);
						this.options.maxFiles = this.options.maxFiles - 1;
						$('.fileinput-buttonz').hide();
					}
				    this.on("success", function(file, data) {
				    	$('.fileinput-buttonz').hide();
				    	$("#accessoryIdsz").val(data.id);
		                //console.log("File " + file.name + "uploaded");
		            });
		            this.on("removedfile", function(file) {
		            	$('.fileinput-buttonz').show();
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
</script>
</body>
</html>
