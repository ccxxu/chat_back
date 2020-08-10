<%request.setAttribute("ctx", request.getContextPath()); %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix='security' uri='http://www.springframework.org/security/tags'%>
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
			<form class="form-horizontal m" id="form-user-resetPwd">
				<input name="id" type="hidden" value="<security:authentication property='principal.id'></security:authentication>" />
				<div class="form-group">
					<label class="col-sm-3 control-label">登录名称：</label>
					<div class="col-sm-8">
						<input class="form-control" type="text" readonly="true" name="loginName" value="<security:authentication property='principal.username'></security:authentication>"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">旧密码：</label>
					<div class="col-sm-8">
						<input class="form-control" type="password" name="oldPassword" id="oldPassword">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">新密码：</label>
					<div class="col-sm-8">
						<input class="form-control" type="password" name="newPassword" id="newPassword">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">再次确认：</label>
					<div class="col-sm-8">
						<input class="form-control" type="password" name="confirm" id="confirm">
					</div>
				</div>
			</form>
		</div>
		<script>
			$("#form-user-resetPwd").validate({
				rules:{
					oldPassword:{
						required:true,
						remote: {
		                    url: "${ctx}/system/user/checkPassword",
		                    type: "get",
		                    dataType: "json",
		                    data: {
		                        password: function() {
		                            return $("input[name='oldPassword']").val();
		                        }
		                    }
		                }
					},
					newPassword: {
		                required: true,
		                minlength: 5,
		    			maxlength: 20
		            },
		            confirm: {
		                required: true,
		                equalTo: "#newPassword"
		            }
				},
				messages: {
		            oldPassword: {
		                required: "请输入原密码",
		                remote: "原密码错误"
		            },
		            newPassword: {
		                required: "请输入新密码",
		                minlength: "密码不能小于6个字符",
		                maxlength: "密码不能大于20个字符"
		            },
		            confirm: {
		                required: "请再次输入新密码",
		                equalTo: "两次密码输入不一致"
		            }
	
		        },
		        focusCleanup: true
			});
			
			function callPwd(result) {
				$.modal.alert(result.msg);
			}
			
			function submitHandler() {
		        if ($.validate.form()) {
		        	$.operate.saveModal("${ctx}/system/user/resetPwd", $('#form-user-resetPwd').serialize());
		        }
		    }
		</script>
	</body>
</html>
