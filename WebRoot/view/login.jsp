<%request.setAttribute("ctx", request.getContextPath()); %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	    <meta charset="utf-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
	    <title>暗云数据管理平台</title>
	    <meta name="description" content="菉竹轩数据管理系统">
	    <link href="${ctx }/css/bootstrap.min.css" rel="stylesheet"/>
	    <link href="${ctx }/css/font-awesome.min.css" rel="stylesheet"/>
	    <link href="${ctx }/css/style.css" rel="stylesheet"/>
	    <link href="${ctx }/css/login.min.css" rel="stylesheet"/>
	    <link href="${ctx }/css/ccx/ccx-ui.css" rel="stylesheet"/>
	    <!--[if lt IE 9]>
	    <meta http-equiv="refresh" content="0;ie.html" />
	    <![endif]-->
	    <link rel="shortcut icon" href="${ctx }/icons/ccx_ico.png"/>
	    <style type="text/css">label.error { position:inherit;  }</style>
	    <!-- 全局js -->
		<script src="${ctx }/js/jquery.min.js"></script>
		<script src="${ctx }/js/bootstrap.min.js"></script>
		<!-- 验证插件 -->
		<script type="text/javascript"> 
			var ctx = "${ctx}"; 
			var captchaType = "math"; 
		</script>
		<script src="${ctx }/js/validate/jquery.validate.min.js"></script>
		<script src="${ctx }/js/validate/messages_zh.min.js""></script>
		<script src="${ctx }/js/layer.min.js"></script>
		<script src="${ctx }/js/jquery.blockUI.js" ></script>
		<script src="${ctx }/js/ccx/ccx-ui.js"></script>
		<script src="${ctx }/js/ccx/login.js?v=1"></script>
	    <script>
	        if(window.top!==window.self){window.top.location=window.location};
	    </script>
	</head>

	<body class="signin">
	    <div class="signinpanel">
	        <div class="row">
	            <div class="col-sm-7">
	                <div class="signin-info">
	                    <div class="logopanel m-b">
	                        <h1><img alt="[ 燕园夜雨 ]" width="121px" src="${ctx }/images/ccx.png" ></h1>
	                    </div>
	                    <div class="m-b"></div>
	                    <h4>欢迎使用 <strong>暗云数据综合管理平台</strong></h4>
	                    <ul class="m-b">
	                        <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 系统管理</li>
	                        <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 系统监控</li>
	                        <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 系统工具</li>
	                        <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 网站管理</li>
	                        <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 流量监控</li>
	                    </ul>
	                </div>
	            </div>
	            <div class="col-sm-5">
	                <form id="signupForm">
	                    <h4 class="no-margins">登录：</h4>
	                    <p class="m-t-md">所有幸福，都来自平凡的奋斗和坚持</p>
	                    <input type="text"     name="username" class="form-control uname"     placeholder="用户名" value=""    />
	                    <input type="password" name="password" class="form-control pword"     placeholder="密码"   value="" />
						<div class="row m-t">
							<div class="col-xs-6">
							    <input type="text" name="validateCode" class="form-control code" placeholder="验证码" maxlength="5" autocomplete="off">
							</div>
							<div class="col-xs-6">
								<a href="javascript:void(0);" title="点击更换验证码">
									<img src="${ctx }/js/captchaImage?type=math" class="imgcode" width="85%"/>
								</a>
							</div>
						</div>
						<%-- 
	                    <div class="checkbox-custom" th:classappend="${captchaEnabled==false} ? 'm-t'">
					        <input type="checkbox" id="rememberme" name="rememberme"> <label for="rememberme">记住我</label>
					    </div>
					     --%>
	                    <button class="btn btn-success btn-block" id="btnSubmit" data-loading="正在验证登录，请稍后...">登录</button>
	                </form>
	            </div>
	        </div>
	        <div class="signup-footer">
	            <div class="pull-left">
	                &copy; 2016-2020 All Rights Reserved. @燕园夜雨  &nbsp;&nbsp;   网站备案号：京ICP备20007135号-1 <br>
	            </div>
	        </div>
	    </div>
	</body>
</html>
