<%request.setAttribute("ctx", request.getContextPath()); %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
	    <style type="">
			
			body .password-wrapper .input {
			    border: none;
			    padding: 10px 15px;
			    font: small-caption;
			    font-size: 18px;
			    font-size: 1.8rem;
			    width: calc(100% - 50px);
			    color: #34495e;
			    outline: none;
			    line-height: 1.5;
			}
			body .icon-wrapper {
			    position: relative;
			    display: inline;
			    float: right;
			    width: 50px;
			    height: 50px;
			    background-color: #34495e;
			    transition: background-color 0.25s ease-out;
			    cursor: pointer;
			    margin-top:-50px;
			}
			body .icon-wrapper .ion-eye,
			body .icon-wrapper .ion-more {
			    font-size: 26px;
			    font-size: 2.6rem;
			    position: absolute;
			    top: 11px;
			    right: 12px;
			    color: #ccc;
			    transition: color 0.25s ease-out;
			}
			
			body .icon-wrapper .ion-eye {
				padding: 0px 8px 18px 20px;
    			background: url("${ctx}/images/icon-visible.png") no-repeat;
    			background-size: 100% 90%;
    			margin-top: 14px;
			}
			
			body .icon-wrapper .ion-more {
				padding: 0px 8px 18px 20px;
    			background: url("${ctx}/images/icon-invisible.png") no-repeat;
    			background-size: 100% 62%;
    			margin-top: 18px;
			}
			
			body  .icon-wrapper .ion-more {
			    right: 14px;
			}
			body  .icon-wrapper:hover {
			    transition: background-color 0.25s ease-out;
			    background-color: #2c3e50;
			}
			body  .icon-wrapper:hover .ion-eye,
			body  .icon-wrapper:hover .ion-more {
			    color: #3498db;
			    transition: color 0.25s ease-in;
			}
	    </style>
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
        <script type="text/javascript">

            function submitHandler() {
                if ($.validate.form()) {
                	$.modal.loading("正在保存中...");
                	setTimeout("edit()", 2000);
                }
            }

            function edit() {
                $.ajax({
                	cache : true,
                    type : "POST",
                    url : "${ctx}/tbs/save",
                    data : $("#form-role-edit").serialize(),
                    async : false,
                    error : function(request) {
                        $.modal.alertError("系统错误");
                    },
                    success : function(data) {
                        $.operate.successCallback(data);
                    }
                });
            }
            
            function switchPwd() {
		        $(".toggle-password").toggleClass(".ion-eye ion-more");
		        var input = $($(".toggle-password").attr("toggle"));
		        if (input.attr("type") == "password") {
		            input.attr("type", "text");
		        } else {
		            input.attr("type", "password");
		        }
            }
            
        </script>
    </head>
    <body class="white-bg">
        <div class="wrapper wrapper-content animated fadeInRight ibox-content">
            <form class="form-horizontal m" id="form-role-edit">
                <input type="hidden" name="_id" value="${model._id }"/>
                <input type="hidden" name="src" value="form"/>
                <div class="form-group">
                    <label class="col-sm-3 control-label">云课名称</label>
                    <div class="col-sm-8">
                        <input class="form-control" type="text" name="name" id="name" value="${model.name }" required>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">显示次序</label>
                    <div class="col-sm-8">
                        <input class="form-control" type="text" name="orderno" id="orderno" value="${model.orderno}" required>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">设置密码：</label>
                    <div class="col-sm-8" style="overflow: hidden;">
    					<input id="password-field" type="password" class="form-control" name="ipwd" value="${model.ipwd }">
					    <div class="icon-wrapper" onclick="switchPwd()">
					        <span toggle="#password-field" class="ion ion-eye field-icon toggle-password"></span>
					    </div>
                    </div>
                </div>
            </form>
        </div>
    </body>
</html>
