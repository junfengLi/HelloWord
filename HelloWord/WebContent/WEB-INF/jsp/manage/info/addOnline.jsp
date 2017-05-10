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
<div class="row">
	<form action="${ctx }/info/onlineSave" id="userForm" method="post">
	<table  class="tableForm">
		<tr>
			<td width="25%" class="titleTd"> 编辑后台时间：</td>
			<td width="50%"> 
			<input class="form-control date-picker" id="id-date-picker-1" type="text" name="editbackgroundtime-d" value="${editbackgroundtime }"
			style="float: left; display: block; width: 70%;" />
			<span class="input-group-addon" style="float: left;width: 15%;height: 34px;line-height: 23px;"><i class="icon-calendar bigger-110"></i></span>
			<span class="input-group-addon clearDate"  style="float: left;width: 15%;height: 34px;line-height: 23px;"><i class="icon-remove"></i></span>
			</td>
			<td width="15%"></td>
		</tr>
		<tr>
			<td class="titleTd"> 推首时间：</td>
			<td>
			<input class="form-control date-picker" id="id-date-picker-2" type="text" name="pushheadtime-d" value="${pushheadtime }"
			style="float: left; display: block; width: 70%;" />
			<span class="input-group-addon" style="float: left;width: 15%;height: 34px;line-height: 23px;"><i class="icon-calendar bigger-110"></i></span>
			<span class="input-group-addon clearDate"  style="float: left;width: 15%;height: 34px;line-height: 23px;"><i class="icon-remove"></i></span>
			</td>
			<td></td>
		</tr>
		<tr>
			<td class="titleTd"> 资讯轮播时间：</td>
			<td>
			<input class="form-control date-picker" id="id-date-picker-3" type="text" name="informationtime-d" value="${informationtime }"
			style="float: left; display: block; width: 70%;" />
			<span class="input-group-addon" style="float: left;width: 15%;height: 34px;line-height: 23px;"><i class="icon-calendar bigger-110"></i></span>
			<span class="input-group-addon clearDate"  style="float: left;width: 15%;height: 34px;line-height: 23px;"><i class="icon-remove"></i></span>
			</td>
			<td></td>
		</tr>
		<tr>
			<td class="titleTd"> 项目集合时间：</td>
			<td>
			<input class="form-control date-picker" id="id-date-picker-4" type="text" name="itemsettime-d" value="${itemsettime }"
			style="float: left; display: block; width: 70%;" />
			<span class="input-group-addon" style="float: left;width: 15%;height: 34px;line-height: 23px;"><i class="icon-calendar bigger-110"></i></span>
			<span class="input-group-addon clearDate"  style="float: left;width: 15%;height: 34px;line-height: 23px;"><i class="icon-remove"></i></span>
			</td>
			<td></td>
		</tr>
		<tr>
			<td class="titleTd"> 首页Banner时间：</td>
			<td>
			<input class="form-control date-picker" id="id-date-picker-5" type="text" name="bannertime-d" value="${bannertime }"
			style="float: left; display: block; width: 70%;" />
			<span class="input-group-addon" style="float: left;width: 15%;height: 34px;line-height: 23px;"><i class="icon-calendar bigger-110"></i></span>
			<span class="input-group-addon clearDate"  style="float: left;width: 15%;height: 34px;line-height: 23px;"><i class="icon-remove"></i></span>
			</td>
			<td></td>
		</tr>
		<tr>
			<td class="titleTd"> 文章链接：</td>
			<td>
			<input type="text" id="infohref" name="infohref" value="${online.infohref }" 
			 placeholder="文章链接" class="col-xs-12 col-sm-5">
			</td>
			<td></td>
		</tr>
	</table>
	<div class="frame_close">
		<input type="hidden" name="id" value="${online.id }" />
		<input type="hidden" name="infoid" value="${info.id }" />
		<input type="hidden" name="userid" value="${info.userid }" />
		<button class="btn btn-info" onclick="parent.closeFrame()" type="button"> 关闭</button>
		<input type="submit" class="btn btn-primary" value="提交" />
	</div>
	</form>
</div><!-- /.row -->
<jsp:include page="../include/footer-js.jsp" />
<script type="text/javascript">
var layer;
layui.use(['layer', 'form'], function(){
	layer = layui.layer;
});


function submitHandler(obj){
	if (obj.result) {
		parent.pageReload();
		parent.closeFrame();
	} else {
		layer.alert(obj.desc,{skin: 'layui-layer-lan',closeBtn: 0 });
	}
}
jQuery(function($) {
	$('.input-group-addon.clearDate').click(function(){
		$(this).prev().prev().datepicker( 'setDate' , '');//.val('');
	});
	$('.date-picker').datepicker({dateFormat: "yy-mm-dd"}); 
	$('#id-date-picker-1').datepicker({autoclose:true}).next().on(ace.click_event, function(){
		$(this).prev().focus();
	});
	$('#id-date-picker-2').datepicker({autoclose:true}).next().on(ace.click_event, function(){
		$(this).prev().focus();
	});
	$('#id-date-picker-3').datepicker({autoclose:true}).next().on(ace.click_event, function(){
		$(this).prev().focus();
	});
	$('#id-date-picker-4').datepicker({autoclose:true}).next().on(ace.click_event, function(){
		$(this).prev().focus();
	});
	$('#id-date-picker-5').datepicker({autoclose:true}).next().on(ace.click_event, function(){
		$(this).prev().focus();
	});

	 $('#userForm').validate({
		errorElement: 'div',
		errorClass: 'error-msg',
		focusInvalid: false,
		submitHandler: function() {  
			 formSubmit('userForm');
        },
		rules: {
			infohref:{
				maxlength:500,
				url:true
			}
		},
		messages: {
			infohref:{
				maxlength:"最多输入{0}个字符",
				url:"链接格式不正确"
			}
		},
		errorPlacement: function(error, element) { //错误信息位置设置方法
			var id = element.attr("id");
			layer.tips(error.html(), '#' + id, {tips: [3, '#78BA32']});
			//error.appendTo( element.parent().next() ); //这里的element是录入数据的对象
		}
	});
		/* rules: {
			name: {
				required: true
			},
			phone: {
				required: true,
				phone: 'required'
			},
			url: {
				required: true,
				url: true
			},
			comment: {
				required: true
			},
			state: {
				required: true
			},
			platform: {
				required: true
			},
			subscription: {
				required: true
			},
			gender: 'required',
			agree: 'required'
		},

		messages: {
			email: {
				required: "Please provide a valid email.",
				email: "Please provide a valid email."
			},
			password: {
				required: "Please specify a password.",
				minlength: "Please specify a secure password."
			},
			subscription: "Please choose at least one option",
			gender: "Please choose gender",
			agree: "Please accept our policy"
		},

		invalidHandler: function (event, validator) { //display error alert on form submit   
			$('.alert-danger', $('.login-form')).show();
		},

		highlight: function (e) {
			$(e).closest('.form-group').removeClass('has-info').addClass('has-error');
		},

		success: function (e) {
			$(e).closest('.form-group').removeClass('has-error').addClass('has-info');
			$(e).remove();
		},

		errorPlacement: function (error, element) {
			if(element.is(':checkbox') || element.is(':radio')) {
				var controls = element.closest('div[class*="col-"]');
				if(controls.find(':checkbox,:radio').length > 1) controls.append(error);
				else error.insertAfter(element.nextAll('.lbl:eq(0)').eq(0));
			}
			else if(element.is('.select2')) {
				error.insertAfter(element.siblings('[class*="select2-container"]:eq(0)'));
			}
			else if(element.is('.chosen-select')) {
				error.insertAfter(element.siblings('[class*="chosen-container"]:eq(0)'));
			}
			else error.insertAfter(element.parent());
		},

		submitHandler: function (form) {
		},
		invalidHandler: function (form) {
		}
	}); */
	//form 提交回调函数
	
	/* $('#id-disable-check').on('click', function() {
		var inp = $('#form-input-readonly').get(0);
		if(inp.hasAttribute('disabled')) {
			inp.setAttribute('readonly' , 'true');
			inp.removeAttribute('disabled');
			inp.value="This text field is readonly!";
		}
		else {
			inp.setAttribute('disabled' , 'disabled');
			inp.removeAttribute('readonly');
			inp.value="This text field is disabled!";
		}
	});


	$(".chosen-select").chosen(); 
	$('#chosen-multiple-style').on('click', function(e){
		var target = $(e.target).find('input[type=radio]');
		var which = parseInt(target.val());
		if(which == 2) $('#form-field-select-4').addClass('tag-input-style');
		 else $('#form-field-select-4').removeClass('tag-input-style');
	});


	$('[data-rel=tooltip]').tooltip({container:'body'});
	$('[data-rel=popover]').popover({container:'body'});
	
	$('textarea[class*=autosize]').autosize({append: "\n"});
	$('textarea.limited').inputlimiter({
		remText: '%n character%s remaining...',
		limitText: 'max allowed : %n.'
	});

	$.mask.definitions['~']='[+-]';
	$('.input-mask-date').mask('99/99/9999');
	$('.input-mask-phone').mask('(999) 999-9999');
	$('.input-mask-eyescript').mask('~9.99 ~9.99 999');
	$(".input-mask-product").mask("a*-999-a999",{placeholder:" ",completed:function(){alert("You typed the following: "+this.val());}});



	$( "#input-size-slider" ).css('width','200px').slider({
		value:1,
		range: "min",
		min: 1,
		max: 8,
		step: 1,
		slide: function( event, ui ) {
			var sizing = ['', 'input-sm', 'input-lg', 'input-mini', 'input-small', 'input-medium', 'input-large', 'input-xlarge', 'input-xxlarge'];
			var val = parseInt(ui.value);
			$('#form-field-4').attr('class', sizing[val]).val('.'+sizing[val]);
		}
	});

	$( "#input-span-slider" ).slider({
		value:1,
		range: "min",
		min: 1,
		max: 12,
		step: 1,
		slide: function( event, ui ) {
			var val = parseInt(ui.value);
			$('#form-field-5').attr('class', 'col-xs-'+val).val('.col-xs-'+val);
		}
	});
	
	
	$( "#slider-range" ).css('height','200px').slider({
		orientation: "vertical",
		range: true,
		min: 0,
		max: 100,
		values: [ 17, 67 ],
		slide: function( event, ui ) {
			var val = ui.values[$(ui.handle).index()-1]+"";

			if(! ui.handle.firstChild ) {
				$(ui.handle).append("<div class='tooltip right in' style='display:none;left:16px;top:-6px;'><div class='tooltip-arrow'></div><div class='tooltip-inner'></div></div>");
			}
			$(ui.handle.firstChild).show().children().eq(1).text(val);
		}
	}).find('a').on('blur', function(){
		$(this.firstChild).hide();
	});
	
	$( "#slider-range-max" ).slider({
		range: "max",
		min: 1,
		max: 10,
		value: 2
	});
	
	$( "#eq > span" ).css({width:'90%', 'float':'left', margin:'15px'}).each(function() {
		// read initial values from markup and remove that
		var value = parseInt( $( this ).text(), 10 );
		$( this ).empty().slider({
			value: value,
			range: "min",
			animate: true
			
		});
	});

	
	$('#id-input-file-1 , #id-input-file-2').ace_file_input({
		no_file:'No File ...',
		btn_choose:'Choose',
		btn_change:'Change',
		droppable:false,
		onchange:null,
		thumbnail:false //| true | large
		//whitelist:'gif|png|jpg|jpeg'
		//blacklist:'exe|php'
		//onchange:''
		//
	});
	
	$('#id-input-file-3').ace_file_input({
		style:'well',
		btn_choose:'Drop files here or click to choose',
		btn_change:null,
		no_icon:'icon-cloud-upload',
		droppable:true,
		thumbnail:'small'//large | fit,
		preview_error : function(filename, error_code) {
			//name of the file that failed
			//error_code values
			//1 = 'FILE_LOAD_FAILED',
			//2 = 'IMAGE_LOAD_FAILED',
			//3 = 'THUMBNAIL_FAILED'
			//alert(error_code);
		}

	}).on('change', function(){
		//console.log($(this).data('ace_input_files'));
		//console.log($(this).data('ace_input_method'));
	});
	

	//dynamically change allowed formats by changing before_change callback function
	$('#id-file-format').removeAttr('checked').on('change', function() {
		var before_change
		var btn_choose
		var no_icon
		if(this.checked) {
			btn_choose = "Drop images here or click to choose";
			no_icon = "icon-picture";
			before_change = function(files, dropped) {
				var allowed_files = [];
				for(var i = 0 ; i < files.length; i++) {
					var file = files[i];
					if(typeof file === "string") {
						//IE8 and browsers that don't support File Object
						if(! (/\.(jpe?g|png|gif|bmp)$/i).test(file) ) return false;
					}
					else {
						var type = $.trim(file.type);
						if( ( type.length > 0 && ! (/^image\/(jpe?g|png|gif|bmp)$/i).test(type) )
								|| ( type.length == 0 && ! (/\.(jpe?g|png|gif|bmp)$/i).test(file.name) )//for android's default browser which gives an empty string for file.type
							) continue;//not an image so don't keep this file
					}
					
					allowed_files.push(file);
				}
				if(allowed_files.length == 0) return false;

				return allowed_files;
			}
		}
		else {
			btn_choose = "Drop files here or click to choose";
			no_icon = "icon-cloud-upload";
			before_change = function(files, dropped) {
				return files;
			}
		}
		var file_input = $('#id-input-file-3');
		file_input.ace_file_input('update_settings', {'before_change':before_change, 'btn_choose': btn_choose, 'no_icon':no_icon})
		file_input.ace_file_input('reset_input');
	});




	$('#spinner1').ace_spinner({value:0,min:0,max:200,step:10, btn_up_class:'btn-info' , btn_down_class:'btn-info'})
	.on('change', function(){
		//alert(this.value)
	});
	$('#spinner2').ace_spinner({value:0,min:0,max:10000,step:100, touch_spinner: true, icon_up:'icon-caret-up', icon_down:'icon-caret-down'});
	$('#spinner3').ace_spinner({value:0,min:-100,max:100,step:10, on_sides: true, icon_up:'icon-plus smaller-75', icon_down:'icon-minus smaller-75', btn_up_class:'btn-success' , btn_down_class:'btn-danger'});


	
	$('.date-picker').datepicker({autoclose:true}).next().on(ace.click_event, function(){
		$(this).prev().focus();
	});
	$('input[name=date-range-picker]').daterangepicker().prev().on(ace.click_event, function(){
		$(this).next().focus();
	});
	
	$('#timepicker1').timepicker({
		minuteStep: 1,
		showSeconds: true,
		showMeridian: false
	}).next().on(ace.click_event, function(){
		$(this).prev().focus();
	});
	
	$('#colorpicker1').colorpicker();
	$('#simple-colorpicker-1').ace_colorpicker();

	
	$(".knob").knob();
	
	
	//we could just set the data-provide="tag" of the element inside HTML, but IE8 fails!
	var tag_input = $('#form-field-tags');
	if(! ( /msie\s*(8|7|6)/.test(navigator.userAgent.toLowerCase())) ) 
	{
		tag_input.tag(
		  {
			placeholder:tag_input.attr('placeholder'),
			//enable typeahead by specifying the source array
			source: ace.variable_US_STATES,//defined in ace.js >> ace.enable_search_ahead
		  }
		);
	}
	else {
		//display a textarea for old IE, because it doesn't support this plugin or another one I tried!
		tag_input.after('<textarea id="'+tag_input.attr('id')+'" name="'+tag_input.attr('name')+'" rows="3">'+tag_input.val()+'</textarea>').remove();
		//$('#form-field-tags').autosize({append: "\n"});
	}
	
	
	

	/////////
	$('#modal-form input[type=file]').ace_file_input({
		style:'well',
		btn_choose:'Drop files here or click to choose',
		btn_change:null,
		no_icon:'icon-cloud-upload',
		droppable:true,
		thumbnail:'large'
	})
	
	//chosen plugin inside a modal will have a zero width because the select element is originally hidden
	//and its width cannot be determined.
	//so we set the width after modal is show
	$('#modal-form').on('shown.bs.modal', function () {
		$(this).find('.chosen-container').each(function(){
			$(this).find('a:first-child').css('width' , '210px');
			$(this).find('.chosen-drop').css('width' , '210px');
			$(this).find('.chosen-search input').css('width' , '200px');
		});
	}) */

});
</script>
</body>
</html>

