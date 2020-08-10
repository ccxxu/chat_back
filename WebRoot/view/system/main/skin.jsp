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
		<script src="<%=request.getContextPath() %>/js/ccx/ccx-ui.js?v=4.1.0"></script>
		<script src="<%=request.getContextPath() %>/js/ccx/common.js?v=4.1.0"></script>
    	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    	<meta name="renderer" content="webkit">
	    <title>主题选择</title>
	    <!--[if lt IE 9]>
	    <meta http-equiv="refresh" content="0;ie.html"/>
	    <![endif]-->
	    <style type="text/css">
			.list-unstyled{margin:10px;}
			.full-opacity-hover{opacity:1;filter:alpha(opacity=1);border:1px solid #fff}
			.full-opacity-hover:hover{border:1px solid #f00;}
		</style>
	</head>
	<body class="gray-bg">
		<ul class="list-unstyled clearfix">
			<li style="float:left; width: 33.33333%; padding: 5px;">
				<a href="javascript:" data-skin="skin-blue|theme-dark" style="display: block; box-shadow: 0 0 3px rgba(0,0,0,0.4)" class="clearfix full-opacity-hover">
					<span style="width: 20%; float: left; height: 13px; background: #367fa9"></span>
					<span style="width: 80%; float: left; height: 13px; background: #3c8dbc"></span>
					<span style="width: 20%; float: left; height: 30px; background: #2f4050"></span>
					<span style="width: 80%; float: left; height: 30px; background: #f4f5f7"></span>
				</a>
				<p class="text-center">蓝</p>
			</li>
	
			<li style="float:left; width: 33.33333%; padding: 5px;">
				<a href="javascript:" data-skin="skin-green|theme-dark" style="display: block; box-shadow: 0 0 3px rgba(0,0,0,0.4)" class="clearfix full-opacity-hover">
					<span style="width: 20%; float: left; height: 13px; background: #008d4c"></span>
					<span style="width: 80%; float: left; height: 13px; background: #00a65a"></span>
					<span style="width: 20%; float: left; height: 30px; background: #222d32"></span>
					<span style="width: 80%; float: left; height: 30px; background: #f4f5f7"></span>
				</a>
				<p class="text-center">绿</p>
			</li>
	
			<li style="float:left; width: 33.33333%; padding: 5px;">
				<a href="javascript:" data-skin="skin-purple|theme-dark" style="display: block; box-shadow: 0 0 3px rgba(0,0,0,0.4)" class="clearfix full-opacity-hover">
					<span style="width: 20%; float: left; height: 13px; background: #555299"></span>
					<span style="width: 80%; float: left; height: 13px; background: #605ca8"></span>
					<span style="width: 20%; float: left; height: 30px; background: #222d32"></span>
					<span style="width: 80%; float: left; height: 30px; background: #f4f5f7"></span>
				</a>
				<p class="text-center">紫</p>
			</li>
	
			<li style="float:left; width: 33.33333%; padding: 5px;">
				<a href="javascript:" data-skin="skin-red|theme-dark" style="display: block; box-shadow: 0 0 3px rgba(0,0,0,0.4)" class="clearfix full-opacity-hover">
					<span style="width: 20%; float: left; height: 13px; background: #dd4b39"></span>
					<span style="width: 80%; float: left; height: 13px; background: #d73925"></span>
					<span style="width: 20%; float: left; height: 30px; background: #222d32"></span>
					<span style="width: 80%; float: left; height: 30px; background: #f4f5f7"></span>
				</a>
				<p class="text-center">红</p>
			</li>
	
			<li style="float:left; width: 33.33333%; padding: 5px;">
				<a href="javascript:" data-skin="skin-yellow|theme-dark" style="display: block; box-shadow: 0 0 3px rgba(0,0,0,0.4)" class="clearfix full-opacity-hover">
					<span style="width: 20%; float: left; height: 13px; background: #f39c12"></span>
					<span style="width: 80%; float: left; height: 13px; background: #e08e0b"></span>
					<span style="width: 20%; float: left; height: 30px; background: #222d32"></span>
					<span style="width: 80%; float: left; height: 30px; background: #f4f5f7"></span>
				</a>
				<p class="text-center">黄</p>
			</li>
	
			<li style="float:left; width: 33.33333%; padding: 5px;">
				<a href="javascript:" data-skin="skin-blue|theme-light" style="display: block; box-shadow: 0 0 3px rgba(0,0,0,0.4)" class="clearfix full-opacity-hover">
					<span style="width: 20%; float: left; height: 13px; background: #367fa9"></span>
					<span style="width: 80%; float: left; height: 13px; background: #3c8dbc"></span>
					<span style="width: 20%; float: left; height: 30px; background: #f9fafc"></span>
					<span style="width: 80%; float: left; height: 30px; background: #f4f5f7"></span>
				</a>
				<p class="text-center">蓝灰</p>
			</li>
	
			<li style="float:left; width: 33.33333%; padding: 5px;">
				<a href="javascript:" data-skin="skin-green|theme-light" style="display: block; box-shadow: 0 0 3px rgba(0,0,0,0.4)" class="clearfix full-opacity-hover">
					<span style="width: 20%; float: left; height: 13px; background: #008d4c"></span>
					<span style="width: 80%; float: left; height: 13px; background: #00a65a"></span>
					<span style="width: 20%; float: left; height: 30px; background: #f9fafc"></span>
					<span style="width: 80%; float: left; height: 30px; background: #f4f5f7"></span>
				</a>
				<p class="text-center">绿灰</p>
			</li>
	
			<li style="float:left; width: 33.33333%; padding: 5px;">
				<a href="javascript:" data-skin="skin-purple|theme-light" style="display: block; box-shadow: 0 0 3px rgba(0,0,0,0.4)" class="clearfix full-opacity-hover">
					<span style="width: 20%; float: left; height: 13px; background: #555299"></span>
					<span style="width: 80%; float: left; height: 13px; background: #605ca8"></span>
					<span style="width: 20%; float: left; height: 30px; background: #f9fafc"></span>
					<span style="width: 80%; float: left; height: 30px; background: #f4f5f7"></span>
				</a>
				<p class="text-center">紫灰</p>
			</li>
			
			<li style="float:left; width: 33.33333%; padding: 5px;">
				<a href="javascript:" data-skin="skin-red|theme-light" style="display: block; box-shadow: 0 0 3px rgba(0,0,0,0.4)" class="clearfix full-opacity-hover">
					<span style="width: 20%; float: left; height: 13px; background: #dd4b39"></span>
					<span style="width: 80%; float: left; height: 13px; background: #d73925"></span>
					<span style="width: 20%; float: left; height: 30px; background: #f9fafc"></span>
					<span style="width: 80%; float: left; height: 30px; background: #f4f5f7"></span>
				</a>
				<p class="text-center">红灰</p>
			</li>
			
			<li style="float:left; width: 33.33333%; padding: 5px;">
				<a href="javascript:" data-skin="skin-yellow|theme-light" style="display: block; box-shadow: 0 0 3px rgba(0,0,0,0.4)" class="clearfix full-opacity-hover">
					<span style="width: 20%; float: left; height: 13px; background: #f39c12"></span>
					<span style="width: 80%; float: left; height: 13px; background: #e08e0b"></span>
					<span style="width: 20%; float: left; height: 30px; background: #f9fafc"></span>
					<span style="width: 80%; float: left; height: 30px; background: #f4f5f7"></span>
				</a>
				<p class="text-center">黄灰</p>
			</li>
			
			<li style="float:left; width: 33.33333%; padding: 5px;">
				<a href="javascript:" data-skin="skin-blue|theme-blue" style="display: block; box-shadow: 0 0 3px rgba(0,0,0,0.4)" class="clearfix full-opacity-hover">
					<span style="width: 20%; float: left; height: 13px; background: #367fa9"></span>
					<span style="width: 80%; float: left; height: 13px; background: #3c8dbc"></span>
					<span style="width: 20%; float: left; height: 30px; background: rgba(15,41,80,1)"></span>
					<span style="width: 80%; float: left; height: 30px; background: #f4f5f7"></span>
				</a>
				<p class="text-center">蓝浅（新）</p>
			</li>
			<li style="float:left; width: 33.33333%; padding: 5px;">
				<a href="javascript:" data-skin="skin-green|theme-blue" style="display: block; box-shadow: 0 0 3px rgba(0,0,0,0.4)" class="clearfix full-opacity-hover">
					<span style="width: 20%; float: left; height: 13px; background: #008d4c"></span>
					<span style="width: 80%; float: left; height: 13px; background: #00a65a"></span>
					<span style="width: 20%; float: left; height: 30px; background: rgba(15,41,80,1)"></span>
					<span style="width: 80%; float: left; height: 30px; background: #f4f5f7"></span>
				</a>
				<p class="text-center">绿浅（新）</p>
			</li>
		</ul>
	</body>
	<script type="text/javascript">
		//皮肤样式列表
		var skins = ["skin-blue", "skin-green", "skin-purple", "skin-red", "skin-yellow"];
		
		// 主题样式列表
		var themes = ["theme-dark", "theme-light", "theme-blue"];

		$("[data-skin]").on('click',
		function(e) {
		    var skin = $(this).data('skin');
		    $.each(skins, function(i) {
		        parent.$("body").removeClass(skins[i]);
		    });
		    $.each(themes, function(i) {
		        parent.$("body").removeClass(themes[i]);
		    });
		    parent.$("body").addClass(skin.split('|')[0]);
		    parent.$("body").addClass(skin.split('|')[1]);
		    storage.set('skin', skin);
		});
	</script>
</html>
