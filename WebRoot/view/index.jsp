<%request.setAttribute("ctx", request.getContextPath()); %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix='security' uri='http://www.springframework.org/security/tags'%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	    <meta charset="utf-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	    <meta name="renderer" content="webkit">
	    <link rel="shortcut icon" href="${ctx }/icons/ccx_ico.png"/>
	    <title>暗云数据管理平台</title>
	    <!--[if lt IE 9]>
	    <meta http-equiv="refresh" content="0;ie.html"/>
	    <![endif]-->
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
		<script src="<%=request.getContextPath() %>/js/ccx/ccx-ui.js?v=4.1.0.1"></script>
		<script src="<%=request.getContextPath() %>/js/ccx/common.js?v=4.1.0"></script>
		<script src="<%=request.getContextPath() %>/js/ccx/index.js?v=1.2"></script>
		<script src="<%=request.getContextPath() %>/js/jquery.fullscreen.js"></script>
		<script type="text/javascript">
		
			// history（表示去掉地址的#）否则地址以"#"形式展示
			var mode = "history";
			// 排除非左侧菜单链接
			var excludesUrl = ["/system/user/profile"];
			
			/* 用户管理-重置密码 */
			function resetPwd() {
			    var url = '${ctx}/system/user/resetPwd';
			    $.modal.open("重置密码", url, '770', '380');
			}
			
			/* 切换主题 */
			function switchSkin() {
			    layer.open({
					type : 2,
					shadeClose : true,
					title : "切换主题",
					area : ["530px", "386px"],
					content : ["${ctx}/system/switchSkin", 'no']
				})
			}
			
			/** 刷新时访问路径页签 */
			function applyPath(url) {
				$('a[href$="' + decodeURI(url) + '"]').click();
			    if($.inArray(url, excludesUrl)){
			        $('a[href$="' + url + '"]').parent("li").addClass("selected").parents("li").addClass("active").end().parents("ul").addClass("in");
			    }
			}
			
			function syslog() {
				var url = '${ctx}/system/main/log';
			    $.modal.openWithOutButton("系统日志", url, '700', '600');
			}
			
			/*
			$(function() {
				if($.common.equals("history", mode) && window.performance.navigation.type == 1) {
					var url = storage.get('publicPath');
				    if ($.common.isNotEmpty(url)) {
				    	applyPath(url);
				    }
				} else {
					var hash = location.hash;
				    if ($.common.isNotEmpty(hash)) {
				        var url = hash.substring(1, hash.length);
				        applyPath(url);
				    }
				}
			});
			*/
		</script>
	</head>
	<body class="fixed-sidebar full-height-layout gray-bg" style="overflow: hidden">
		<div id="wrapper">

		    <!--左侧导航开始-->
		    <nav class="navbar-default navbar-static-side" role="navigation">
		        <div class="nav-close">
		            <i class="fa fa-times-circle"></i>
		        </div>
		        <div class="sidebar-collapse">
		            <ul class="nav" id="side-menu">
	                  	<li class="logo">
					    	<span class="logo-lg" >暗云数据管理平台</span>
	            	  	</li>
		            	<li>
		            		<div class="user-panel">
		            			<a class="menuItem" title="个人中心" href="${ctx}/system/profile">
		            				<div class="hide" text="个人中心"></div>
							        <div class="pull-left image">
				                    	<img src="${ctx}<security:authentication property='principal.photoPath'></security:authentication>" class="img-circle" style="width:45px;height:45px;" alt="User Image">
							        </div>
						        </a>
						        <div class="pull-left info">
						          <p><security:authentication property="principal.name"></security:authentication></p>
						          <a href="#"><i class="fa fa-circle text-success"></i> 在线</a>
						          <a href="${ctx }/j_spring_security_logout" style="padding-left:5px;"><i class="fa fa-sign-out text-danger"></i> 注销</a>
						        </div>
						    </div>
		            	</li>
                		<li>
		                    <a href="#"><i class="fa fa-home"></i> <span class="nav-label">主页</span> <span class="fa arrow"></span></a>
		                    <ul class="nav nav-second-level">
		                        <li><a class="menuItem" href="${ctx }/system/main">首页</a></li>
		                    </ul>
		                </li>
						<c:forEach items="${menus }" var="item">
						<li>
	              			<a href="#"><i class="fa fa-bars"></i> <span class="nav-label">${item.name }</span><span class="fa arrow"></span></a>
	                 		<ul class="nav nav-second-level collapse">
	                 			<c:forEach items="${item.children }" var="item0">
									<li><a class="menuItem" href="${ctx }${item0.url}">${item0.name }</a></li>
								</c:forEach>
							</ul>
						</li>
						</c:forEach>
		            </ul>
		        </div>
		    </nav>
		    <!--左侧导航结束-->
    
		    <!--右侧部分开始-->
		    <div id="page-wrapper" class="gray-bg dashbard-1">
		        <div class="row border-bottom">
		            <nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">
		                <div class="navbar-header">
		                    <a class="navbar-minimalize minimalize-styl-2" style="color:#FFF;" href="#" title="收起菜单">
		                    	<i class="fa fa-bars"></i>
		                    </a>
		                </div>
                		<ul class="nav navbar-top-links navbar-right welcome-message">
                			<%-- 
		                    <li><a title="视频教程" href="#" target="_blank"><i class="fa fa-video-camera"></i> 视频教程</a></li>
		                    <li><a title="开发文档" href="#" target="_blank"><i class="fa fa-question-circle"></i> 开发文档</a></li>
		                     --%>
		                    <li><a title="全屏显示" href="javascript:void(0)" onclick="syslog()"><i class="fa fa-file-word-o"></i> 系统日志</a></li>
	                		<li><a title="全屏显示" href="javascript:void(0)" id="fullScreen"><i class="fa fa-arrows-alt"></i> 全屏显示</a></li>
                    		<li class="dropdown user-menu">
								<a href="javascript:void(0)" class="dropdown-toggle" data-hover="dropdown">
									<img src="${ctx}<security:authentication property='principal.photoPath'></security:authentication>" class="user-image">
									<span class="hidden-xs"><security:authentication property='principal.name'></security:authentication></span>
								</a>
								<ul class="dropdown-menu">
									<li class="mt5">
										<a href="${ctx }/system/profile" class="menuItem">
										<i class="fa fa-user"></i> 个人中心</a>
									</li>
									<li>
										<a onclick="resetPwd()">
										<i class="fa fa-key"></i> 修改密码</a>
									</li>
									<li>
										<a onclick="switchSkin()">
										<i class="fa fa-dashboard"></i> 切换主题</a>
									</li>
									<li class="divider"></li>
									<li>
										<a href="${ctx }/j_spring_security_logout">
										<i class="fa fa-sign-out"></i> 退出登录</a>
									</li>
								</ul>
							</li>
		                </ul>
		            </nav>
		        </div>
		        <div class="row content-tabs">
		            <button class="roll-nav roll-left tabLeft">
		                <i class="fa fa-backward"></i>
		            </button>
		            <nav class="page-tabs menuTabs">
		                <div class="page-tabs-content">
		                    <a href="javascript:;" class="active menuTab" data-id="/system/main">首页</a>
		                </div>
		            </nav>
		            <button class="roll-nav roll-right tabRight">
		                <i class="fa fa-forward"></i>
		            </button>
		            <a href="javascript:void(0);" class="roll-nav roll-right tabReload"><i class="fa fa-refresh"></i> 刷新</a>
		        </div>
        
        		<a id="ax_close_max" class="ax_close_max" href="#" title="关闭全屏"> <i class="fa fa-times-circle-o"></i> </a>
                    
		        <div class="row mainContent" id="content-main">
		            <iframe class="ccx_iframe" name="iframe0" width="100%" height="100%" data-id="/system/main"
		                    src="${ctx }/system/main" frameborder="0" seamless></iframe>
		        </div>
		        <div class="footer">
		            <div class="pull-right">&copy; 2016-2020 All Rights Reserved. @燕园夜雨  &nbsp;&nbsp;   网站备案号：京ICP备20007135号-1  </div>
		        </div>
		    </div>
		    <!--右侧部分结束-->
		</div>

	</body>
</html>
