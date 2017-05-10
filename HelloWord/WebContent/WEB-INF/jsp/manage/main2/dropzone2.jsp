<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html lang="en">
<head>
<meta charset="utf-8" />
<title>Bootstrap控制台</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>

<body>
<div class="breadcrumbs" id="breadcrumbs">
	<script type="text/javascript">
		try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
	</script>
	
	<ul class="breadcrumb">
		<li>
			<i class="icon-home home-icon"></i>
			<a href="#">Home</a>
		</li>
	
		<li>
			<a href="#">UI Elements</a>
		</li>
		<li class="active">Buttons &amp; Icons</li>
	</ul><!-- .breadcrumb -->
	
	<div class="nav-search" id="nav-search">
		<form class="form-search">
			<span class="input-icon">
				<input type="text" placeholder="Search ..." class="nav-search-input" id="nav-search-input" autocomplete="off" />
				<i class="icon-search nav-search-icon"></i>
			</span>
		</form>
	</div><!-- #nav-search -->
</div>
<div class="page-content">
	<div class="row">
		<div class="col-xs-12">
			<div id="dropzone">
				<form action="${ctx }/formSubmit">
					<div class="fallback"></div>
				</form>
				<div class="myDropone"></div>
			</div><!-- PAGE CONTENT ENDS -->
			<div class="fileinput-button">000</div>
		</div><!-- /.col -->
	</div><!-- /.row -->
</div><!-- /.page-content -->
<script type="text/javascript">
jQuery(function($){	
	try {
		$(".myDropone").dropzone({
			url: "${ctx}/accessory/upload?module=test",
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
			    this.on("success", function(file) {
	                console.log("File " + file.name + "uploaded");
	            });
	            this.on("removedfile", function(file) {
	                console.log("File " + file.name + "removed");
	            });
			  },
			  dictDefaultMessage :'<span class="bigger-150 bolder"><i class="icon-caret-right red"></i> Drop files</span> to upload \<span class="smaller-80 grey">(or click)</span> <br /> \<i class="upload-icon icon-cloud-upload blue icon-3x"></i>',dictResponseError: 'Error while uploading file!',
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

