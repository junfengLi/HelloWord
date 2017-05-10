<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!-- basic scripts -->
<!--[if !IE]> -->
<script type="text/javascript">
	window.jQuery || document.write("<script src='${ctx}/static/js/jquery/jquery-2.0.3.min.js'>"+"<"+"script>");
</script>

<!-- <![endif]-->
<!--[if IE]>
<script type="text/javascript">
 window.jQuery || document.write("<script src='${ctx}/static/js/jquery/jquery-1.10.2.min.js'>"+"<"+"script>");
</script>
<![endif]-->
<script type="text/javascript">
	if("ontouchend" in document) document.write("<script src='${ctx}/static/js/jquery/jquery.mobile.custom.min.js'>"+"<"+"script>");
</script>


<script src="${ctx}/static/bootstrap-3.3.0/js/bootstrap.min.js"></script>
<script src="${ctx}/static/bootstrap-3.3.0/js/typeahead-bs2.min.js"></script>
<script src="${ctx}/static/bootstrap-3.3.0/js/bootstrap-colorpicker.min.js"></script>
<script src="${ctx}/static/bootstrap-3.3.0/js/date-time/bootstrap-datepicker.min.js"></script>
<script src="${ctx}/static/bootstrap-3.3.0/js/date-time/bootstrap-timepicker.min.js"></script>
<script src="${ctx}/static/bootstrap-3.3.0/js/date-time/daterangepicker.min.js"></script>
<script src="${ctx}/static/bootstrap-3.3.0/js/date-time/moment.min.js"></script>
<script src="${ctx}/static/bootstrap-3.3.0/js/bootstrap-tag.min.js"></script>
<!-- page specific plugin scripts -->
<!--[if lte IE 8]>
  <script src="${ctx}/static/ace/js/excanvas.min.js"></script>
<![endif]-->
<%-- <script src="${ctx}/static/js/jquery/jquery-ui-1.10.3.custom.min.js"></script> --%>
<script src="${ctx}/static/js/jquery/jquery-ui-1.10.3.full.min.js"></script>
<script src="${ctx}/static/js/jquery/jquery.ui.touch-punch.min.js"></script>
<script src="${ctx}/static/js/jquery/jquery.slimscroll.min.js"></script>
<script src="${ctx}/static/js/jquery/jquery.easy-pie-chart.min.js"></script>
<script src="${ctx}/static/js/jquery/jquery.sparkline.min.js"></script>
<script src="${ctx}/static/ace/js/flot/jquery.flot.min.js"></script>
<script src="${ctx}/static/ace/js/flot/jquery.flot.pie.min.js"></script>
<script src="${ctx}/static/ace/js/flot/jquery.flot.resize.min.js"></script>

<!-- 各个插件 -->
<script src="${ctx}/static/js/jquery/jquery.nestable.min.js"></script>
<script src="${ctx}/static/ace/js/fuelux/fuelux.tree.min.js"></script>
<script src="${ctx}/static/ace/js/fuelux/fuelux.spinner.min.js"></script>

<script src="${ctx}/static/ace/js/dropzone.min.js"></script>

<script src="${ctx}/static/js/jqGrid/jquery.jqGrid.min.js"></script>
<script src="${ctx}/static/js/jqGrid/i18n/grid.locale-en.js"></script>

<script src="${ctx}/static/js/jqForm/chosen.jquery.min.js"></script>
<script src="${ctx}/static/js/jqForm/jquery.knob.min.js"></script>
<script src="${ctx}/static/js/jqForm/jquery.autosize.min.js"></script>
<script src="${ctx}/static/js/jqForm/jquery.inputlimiter.1.3.1.min.js"></script>
<script src="${ctx}/static/js/jqForm/jquery.maskedinput.min.js"></script>




<script src="${ctx}/static/js/jqGrid/jquery.dataTables.min.js"></script>
<script src="${ctx}/static/js/jqGrid/jquery.dataTables.bootstrap.js"></script>


<!-- ace scripts -->
<script src="${ctx}/static/ace/js/ace-elements.min.js"></script>
<script src="${ctx}/static/ace/js/ace.min.js"></script>


