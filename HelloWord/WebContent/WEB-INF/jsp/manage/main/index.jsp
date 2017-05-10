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

<body style="overflow-x:hidden;">
<div class="page-content">
		<div class="row">
			<div class="col-xs-12">
				<h3 class="header smaller lighter orange">
					<span >当前时间：</span>
					<span id="time"></span>
				</h3>
		</div><!-- /.col -->
	</div><!-- /.row -->
</div><!-- /.page-content -->

<script type="text/javascript">
var layer = null;
$(function(){
	layui.use(['layer'], function(){layer = layui.layer;});
	getTime();
});

function getTime(){
    var date = new Date();
    var month = date.getMonth()+1;
    var day = date.getDate();
    var str = date.getFullYear()+"-"+( month < 10 ? "0" + month : month )+"-"+( day < 10 ? "0" + day : day );
    var hours =date.getHours();
    var minutes = date.getMinutes();
    var seconds = date.getSeconds();
    $('#time').html(str + " " + ( hours < 10 ? "0" + hours : hours ) + " : " + ( minutes < 10 ? "0" + minutes : minutes ) +" : "+ ( seconds < 10 ? "0" + seconds : seconds ));
    setTimeout("getTime()",1000);
}
</script>
</body>
</html>

