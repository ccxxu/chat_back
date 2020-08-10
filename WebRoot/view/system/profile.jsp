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
		<script type="text/javascript">
		    /*用户管理-头像*/
		    function avatar() {
		        var url = '${ctx}/system/avatar';
		        $.modal.open("修改头像", url);
		    }
	    
		    /*用户信息-修改*/
		    function check00() {
			    $("#form-user-edit").validate({
					onkeyup: false,
					rules:{
						username:{
							required:true,
				            remote: {
				                url: "${ctx}/system/user/checkUsernameUnique",
				                type: "get",
				                dataType: "json",
				                data: {
				        			"username": function() {
				                        return $.common.trim($("#username").val());
				                    }
				                }
				            }
						}
					},
					messages: {
						username: {
			                required: "请输入登录名",
			                remote: "该登录名已存在",
			            }
				    },
				    focusCleanup: true
				});
		    }
		    
		    function check01() {
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
		    }
			
			function submitUserInfo() {
		        if ($.validate.form()) {
		        	$.operate.post("${ctx}/system/user/profile/save", $("#form-user-edit").serialize());
		        }
		    }
			
			function submitChangPassword () {
		        if ($.validate.form("form-user-resetPwd")) {
		        	$.operate.saveModal("${ctx}/system/user/resetPwd", $('#form-user-resetPwd').serialize());
		        }
		    }
		</script>
	</head>

	<body class="gray-bg" style="font: 14px Helvetica Neue, Helvetica, PingFang SC, 微软雅黑, Tahoma, Arial, sans-serif !important;">
    	<input id="userId" name="userId" type="hidden" value="" />
    	<section class="section-content">
		    <div class="row">
		        <div class="col-sm-3 pr5">
		            <div class="ibox float-e-margins">
		                <div class="ibox-title ibox-title-gray dashboard-header gray-bg">
		                    <h5>个人资料</h5>
		                </div>
		                <div class="ibox-content">
		                    <div class="text-center">
		                        <p><img class="img-circle img-lg" src="${ctx}<security:authentication property='principal.photoPath'></security:authentication>"></p>
		                        <p><a href="javascript:avatar()">修改头像</a></p>
		                    </div>
		                    <ul class="list-group list-group-striped">
		                        <li class="list-group-item"><i class="fa fa-user"></i>
		                            <b class="font-noraml">登录名称：</b>
		                            <p class="pull-right"><security:authentication property='principal.username'></security:authentication></p>
		                        </li>
		                        <li class="list-group-item"><i class="fa fa-phone"></i>
		                            <b  class="font-noraml">手机号码：</b>
		                            <p class="pull-right"><security:authentication property='principal.mobile'></security:authentication></p>
		                        </li>
		                        <li class="list-group-item"><i class="fa fa-group"></i>
		                            <b  class="font-noraml">所属单位：</b>
		                            <p class="pull-right" ><security:authentication property='principal.department'></security:authentication></p>
		                        </li>
		                        <li class="list-group-item"><i class="fa fa-envelope-o"></i>
		                            <b  class="font-noraml">邮箱地址：</b>
		                            <p class="pull-right" ><security:authentication property='principal.email'></security:authentication></p>
		                        </li>
		                        <li class="list-group-item"><i class="fa fa-calendar"></i>
		                            <b  class="font-noraml">入库时间：</b>
		                            <p class="pull-right" ><security:authentication property='principal.createDate'></security:authentication></p>
		                        </li>
		                    </ul>
                		</div>
            		</div>
        		</div>
        
		        <div class="col-sm-9 about">
		            <div class="ibox float-e-margins">
		                <div class="ibox-title ibox-title-gray dashboard-header">
		                    <h5>基本资料</h5>
		                </div>
		                <div class="ibox-content">
		                    <div class="nav-tabs-custom">
		                        <ul class="nav nav-tabs">
		                            <li class="active"><a href="#user_info" data-toggle="tab" aria-expanded="true">基本资料</a></li>
		                            <li><a href="#modify_password" data-toggle="tab" aria-expanded="false">修改密码</a></li>
		                        </ul>
		                        <div class="tab-content">
		                            <!--用户信息-->
		                            <div class="tab-pane active" id="user_info">
		                                <form class="form-horizontal" id="form-user-edit">
		                                    <!--隐藏ID-->
		                                    <input name="id" id="id" type="hidden" value="<security:authentication property='principal.id'></security:authentication>">
		                                    <div class="form-group">
		                                        <label class="col-sm-2 control-label">用户昵称：</label>
		                                        <div class="col-sm-10">
		                                            <input type="text" class="form-control" name="name" value="<security:authentication property='principal.name'></security:authentication>" placeholder="请输入用户昵称">
		                                        </div>
		                                    </div>
		                                    <div class="form-group">
		                                        <label class="col-sm-2 control-label">用户称谓：</label>
		                                        <div class="col-sm-10">
		                                            <input type="text" class="form-control" name="title" value="<security:authentication property='principal.duty'></security:authentication>" placeholder="请输入用户称谓">
		                                        </div>
		                                    </div>
		                                    <div class="form-group">
		                                        <label class="col-sm-2 control-label">登录名：</label>
		                                        <div class="col-sm-10">
		                                            <input type="text" class="form-control" id="username" onchange="check00()" name="username" value="<security:authentication property='principal.username'></security:authentication>" placeholder="请输入用户名称">
		                                        </div>
		                                    </div>
		                                    <div class="form-group">
		                                        <label class="col-sm-2 control-label">手机号码：</label>
		                                        <div class="col-sm-10">
		                                            <input type="text" class="form-control" name="mobile" maxlength="11" value="<security:authentication property='principal.mobile'></security:authentication>" placeholder="请输入手机号码">
		                                        </div>
		                                    </div>
		                                    <div class="form-group">
		                                        <label class="col-sm-2 control-label">邮箱：</label>
		                                        <div class="col-sm-10">
		                                            <input type="text" class="form-control" name="email" value="<security:authentication property='principal.email'></security:authentication>" placeholder="请输入邮箱">
		                                        </div>
		                                    </div>
		                                    <%-- 
		                                    <div class="form-group">
		                                        <label class="col-sm-2 control-label">性别：</label>
		                                        <div class="col-sm-10">
		                                            <div class="radio-box">
														<input type="radio" disabled id="radio1" checked name="sex" value="0">
														<label for="radio1">男</label>
													</div>
													<div class="radio-box">
														<input type="radio" disabled id="radio2" name="sex" value="1">
														<label for="radio2">女</label>
													</div>
		                                        </div>
		                                    </div>
		                                     --%>
		                                    <div class="form-group">
		                                        <div class="col-sm-offset-2 col-sm-10">
		                                            <button type="button" class="btn btn-sm btn-primary" onclick="submitUserInfo()"><i class="fa fa-check"></i>保 存</button>&nbsp;
		                                            <button type="button" class="btn btn-sm btn-danger" onclick="closeItem()"><i class="fa fa-reply-all"></i>关 闭 </button>
		                                        </div>
		                                    </div>
		                                </form>
		                            </div>
		                            
		                            <!--修改密码-->
		                            <div class="tab-pane" id="modify_password">
		                                <form class="form-horizontal" id="form-user-resetPwd">
		                                	<input name="id" id="id" type="hidden" value="<security:authentication property='principal.id'></security:authentication>">
		                                    <div class="form-group">
		                                        <label class="col-sm-2 control-label">旧密码：</label>
		                                        <div class="col-sm-10">
		                                            <input type="password" class="form-control" onkeyup="check01()" name="oldPassword" id="oldPassword" placeholder="请输入旧密码">
		                                        </div>
		                                    </div>
		                                    <div class="form-group">
		                                        <label class="col-sm-2 control-label">新密码：</label>
		                                        <div class="col-sm-10">
		                                            <input type="password" class="form-control" onkeyup="check01()" name="newPassword" id="newPassword" placeholder="请输入新密码">
		                                        </div>
		                                    </div>
		                                    <div class="form-group">
		                                        <label class="col-sm-2 control-label">确认密码：</label>
		                                        <div class="col-sm-10">
		                                            <input type="password" class="form-control" onkeyup="check01()" name="confirm" placeholder="请确认密码">
		                                        </div>
		                                    </div>
		                                    <div class="form-group">
		                                        <div class="col-sm-offset-2 col-sm-10">
		                                            <button type="button" class="btn btn-sm btn-primary" onclick="submitChangPassword()"><i class="fa fa-check"></i>保 存</button>&nbsp;
		                                            <button type="button" class="btn btn-sm btn-danger" onclick="closeItem()"><i class="fa fa-reply-all"></i>关 闭 </button>
		                                        </div>
		                                    </div>
		                                </form>
		                            </div>
		                        </div>
		                    </div>
		                </div>
            		</div>
        		</div>
        
    		</div>
		</section>
	</body>
</html>
