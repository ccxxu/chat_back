<%request.setAttribute("ctx", request.getContextPath()); %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <link href="<%=request.getContextPath() %>/icons/favicon.ico" rel="stylesheet"/>
	    <link href="<%=request.getContextPath() %>/css/bootstrap.min.css" rel="stylesheet"/>
	    <link href="<%=request.getContextPath() %>/css/jquery.contextMenu.min.css" rel="stylesheet"/>
	    <link href="<%=request.getContextPath() %>/css/font-awesome.min.css" rel="stylesheet"/>
	    <link href="<%=request.getContextPath() %>/css/animate.css" rel="stylesheet"/>
	    <link href="<%=request.getContextPath() %>/css/style.css" rel="stylesheet"/>
	    <link href="<%=request.getContextPath() %>/css/skins.css" rel="stylesheet"/>
	    <link href="<%=request.getContextPath() %>/css/ccx/ccx-ui.css?v=4.1.0" rel="stylesheet"/>
	    <!-- 全局js -->
		<script src="<%=request.getContextPath() %>/js/jquery.min.js"></script>
		<script src="<%=request.getContextPath() %>/js/bootstrap.min.js"></script>
		<script src="<%=request.getContextPath() %>/js/plugins/metisMenu/jquery.metisMenu.js"></script>
		<script src="<%=request.getContextPath() %>/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
		<script src="<%=request.getContextPath() %>/js/jquery.contextMenu.min.js"></script>
		<script src="<%=request.getContextPath() %>/js/jquery.blockUI.js"></script>
		<script src="<%=request.getContextPath() %>/js/layer.min.js"></script>
		
		<!-- bootstrap-table 表格插件 -->
		<script src="<%=request.getContextPath() %>/js/bootstrap-table/bootstrap-table.min.js?v=20191219"></script>
		<script src="<%=request.getContextPath() %>/js/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
		<script src="<%=request.getContextPath() %>/js/bootstrap-table/extensions/mobile/bootstrap-table-mobile.js"></script>
		<script src="<%=request.getContextPath() %>/js/bootstrap-table/extensions/toolbar/bootstrap-table-toolbar.min.js"></script>
		<script src="<%=request.getContextPath() %>/js/bootstrap-table/extensions/columns/bootstrap-table-fixed-columns.js"></script>
		<!-- jquery-validate 表单验证插件 -->
		<script src="<%=request.getContextPath() %>/js/validate/jquery.validate.min.js"></script>
		<script src="<%=request.getContextPath() %>/js/validate/messages_zh.min.js"></script>
		<script src="<%=request.getContextPath() %>/js/validate/jquery.validate.extend.js"></script>
		<!-- 遮罩层 -->
	    <script src="<%=request.getContextPath() %>/js/iCheck/icheck.min.js"></script>
		<script src="<%=request.getContextPath() %>/js/layui/layui.js"></script>
		
		<script src="<%=request.getContextPath() %>/js/ccx/ccx-ui.js?v=4.1.0"></script>
		<script src="<%=request.getContextPath() %>/js/ccx/common.js?v=4.1.0"></script>
		<script src="<%=request.getContextPath() %>/js/ccx/index.js"></script>
		<script src="<%=request.getContextPath() %>/js/jquery.fullscreen.js"></script>
	</head>
	<body class="white-bg">
		<div class="wrapper wrapper-content animated fadeInRight ibox-content">
			<form class="form-horizontal m" id="form-role-edit">
				<input id="id" name="id" type="hidden" value="${rm.id}"/>
				<div class="form-group">
					<label class="col-sm-3 control-label is-required">用户组名称：</label>
					<div class="col-sm-8">
						<input class="form-control" type="text" name="name" id="name" value="${rm.name }" required>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label is-required">用户组描述：</label>
					<div class="col-sm-8">
						<input class="form-control" type="text" name="descc" id="descc" value="${rm.descc }" required>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label is-required">显示排序：</label>
					<div class="col-sm-8">
						<input class="form-control" type="text" name="orderNum" value="${rm.orderNum }" required>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">是否可用：</label>
					<div class="col-sm-8">
					    <label class="radio-box" >
							<input type="radio" name="status" value="1" <c:if test="${rm.status != '0' }">checked</c:if> />
							是
						</label>
						<label class="radio-box" >
							<input type="radio" name="status" value="0" <c:if test="${rm.status == '0' }">checked</c:if> />
							否
						</label>
					</div>
				</div>
			</form>
		</div>
	 	<script>
	        	        
	        function submitHandler() {
		        if ($.validate.form()) {
		            $.operate.save("${ctx}/system/role/save", $('#form-role-edit').serialize());
		        }
		    }
	
	    </script>
	</body>
</html>
