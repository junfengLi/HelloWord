<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html lang="en">
<head>
<meta charset="utf-8" />
</head>
<body>
<div class="page-content">
	<div class="row">
		<div class="col-xs-12">
			<!-- PAGE CONTENT BEGINS -->
			<div id="dropzone" >
				<form action="//dummy.html" >
					<div class="fallback">
					</div>
				</form>
				<div class="dropzone"></div>
			</div><!-- PAGE CONTENT ENDS -->
			<div class="fileinput-button">000</div>
		</div><!-- /.col -->
	</div><!-- /.row -->
</div><!-- /.page-content -->
<script type="text/javascript">
	jQuery(function($){
	Dropzone.autoDiscover = false;
	try {
		var myDropzone = new Dropzone(".dropzone", {
	  //$(".dropzone").dropzone({
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
			var mockFile = {name: "myiamge.jpg",size: 12345,type: 'image/jpeg'};
			this.addFile.call (this,mockFile);
			
			this.options.thumbnail.call (this,mockFile,"http://edms.kitesky.com/upload/image/20170422/52edf3c2aabf171315d968d9af814d0c.jpg"); 
			
			//添加数据源给mock图片
		    this.on("success", function(file) {
                console.log("File " + file.name + "uploaded");
            });
            this.on("removedfile", function(file) {
                console.log("File " + file.name + "removed");
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
	
	var mockFile = { name: "123.jpg", accepted:true };
	myDropzone.emit("addedfile", mockFile);
	myDropzone.emit("thumbnail", mockFile, "http://edms.kitesky.com/upload/image/20170422/52edf3c2aabf171315d968d9af814d0c.jpg");
	myDropzone.emit("complete", mockFile);
	myDropzone.options.maxFiles = myDropzone.options.maxFiles - 1;
	});
</script>
</body>
</html>