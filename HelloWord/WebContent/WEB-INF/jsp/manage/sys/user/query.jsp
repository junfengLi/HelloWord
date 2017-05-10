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
	<div class="page-header">
		<ul class="breadcrumb">
			<li>
				<a href="#" onclick="openFrame('百度','${ctx }/user/add/forward',600,400);" ><i class="icon-legal home-icon">添加</i></a>
			</li>
		</ul><!-- .breadcrumb -->
	</div><!-- /.page-header -->

	<div class="row">
		<div class="col-xs-12">
			<!-- PAGE CONTENT BEGINS -->
			<div class="row">
				<div class="col-xs-12">
					<div class="table-header">
						Results for "Latest Registered Domains"
					</div>
					<div class="table-responsive">
					<table id="example" class="display" cellspacing="0" width="100%">
				        <thead>
				            <tr>
				                <th>Name</th>
				                <th>Position</th>
				                <th>Office</th>
				                <th>Extn.</th>
				                <th>Start date</th>
				                <th>Salary</th>
				            </tr>
				        </thead>
				        <tfoot>
				            <tr>
				                <th>Name</th>
				                <th>Position</th>
				                <th>Office</th>
				                <th>Extn.</th>
				                <th>Start date</th>
				                <th>Salary</th>
				            </tr>
				        </tfoot>
				    </table>
						<table id="sample-table-2" class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th class="center">
										<label>
											<input type="checkbox" class="ace" />
											<span class="lbl"></span>
										</label>
									</th>
									<th>Domain</th>
									<th>Price</th>
									<th>Clicks</th>
									<th>Update</th>
									<th>Status</th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td class="center">
										<label>
											<input type="checkbox" class="ace" />
											<span class="lbl"></span>
										</label>
									</td>
									<td>
										<a href="#">app.com</a>
									</td>
									<td>$45</td>
									<td class="hidden-480">3,330</td>
									<td>Feb 12</td>
									<td class="hidden-480">
										<span class="label label-sm label-warning">Expiring</span>
									</td>
									<td>
										<a href="#">app.com</a>
									</td>
								</tr>
								<tr>
									<td class="center">
										<label>
											<input type="checkbox" class="ace" />
											<span class="lbl"></span>
										</label>
									</td>
									<td>
										<a href="#">app.com</a>
									</td>
									<td>$45</td>
									<td class="hidden-480">3,330</td>
									<td>Feb 12</td>
									<td class="hidden-480">
										<span class="label label-sm label-warning">Expiring</span>
									</td>
									<td>
										<a href="#">app.com</a>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div><!-- /.col -->
	</div><!-- /.row -->
</div><!-- /.page-content -->

<script type="text/javascript">
			jQuery(function($) {
				
				$('#example').DataTable( {
			        "processing": true,
			        "serverSide": true,
			        "ajax": '${ctx}/user/getPage'
			    } );
				
				$('table th input:checkbox').on('click' , function(){
					var that = this;
					$(this).closest('table').find('tr > td:first-child input:checkbox')
					.each(function(){
						this.checked = that.checked;
						$(this).closest('tr').toggleClass('selected');
					});
						
				});
			})
function operate(cellvalue, options, rowObject){
	var html = [];
	html.push("<a href='#' class='icon-eye-open' onclick='show(\""+rowObject.id+"\")'>查看</a>");
	html.push("<a href='#' class='icon-edit' onclick='edit(\""+rowObject.id+"\")'>编辑</a>");
	html.push("<a href='#' class='icon-undo' onclick='resetPassword(\""+rowObject.id+"\")'>重置密码</a>");
	if (rowObject.isdelete == '0') {
		html.push("<a href='#' class='icon-unlock' onclick='lock(\""+rowObject.id+"\",\"锁定\")'>锁定</a>");
	} else {
		html.push("<a href='#' class='icon-lock' onclick='lock(\""+rowObject.id+"\",\"解锁\")'>解锁</a>");
	}
	return html.join("&nbsp;|&nbsp;");
}
function show(id){
	openFrame('查看用户信息','${ctx }/user/show/forward',600,400);
}
function edit(id){
	openFrame('编辑用户信息','${ctx }/user/add/forward?id=' + id,600,400);
}
function resetPassword(id){
	$.post('${ctx}/user/resetPassword',{id:id},function(data){
		if (data.result) {
			layer.msg("重置成功");
		} else {
			layer.msg("重置失败");
		}
	});
}
function lock(id,open){
	$.post('${ctx}/user/lock',{id:id,open:open},function(data){
		if (data.result) {
			layer.msg(open + "成功");
			pageReload();
		} else {
			layer.msg(open + "失败");
		}
	});
}

</script>
</body>
</html>

