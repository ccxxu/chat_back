<%request.setAttribute("ctx", request.getContextPath()); %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<meta charset="utf-8">
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
		<script src="<%=request.getContextPath() %>/js/ccx/index.js"></script>
		<script src="<%=request.getContextPath() %>/js/jquery.fullscreen.js"></script>
		
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
		<!-- jquery-validate 表单树插件 -->
		<script src="<%=request.getContextPath() %>/js/bootstrap-table/bootstrap-treetable.js"></script>
		<!-- 遮罩层 -->
	    <script src="<%=request.getContextPath() %>/js/iCheck/icheck.min.js"></script>
		<script src="<%=request.getContextPath() %>/js/layui/layui.js"></script>
	</head>
	<body class="gray-bg">
	    <div class="container-div">
			<div class="row">
	       		 <div class="col-sm-12 select-table table-striped">
		            <table id="bootstrap-table"></table>
		        </div>
		    </div>
		</div>
		
		<script type="text/javascript">
			var addFlag = "";
			var editFlag = "";
			var removeFlag = "";
			var datas = "";
			var prefix = "${ctx}/system/user/role/${userId}";
	
			$(function() {
				console.log("=="+prefix);
			    var options = {
			    	uniqueId: "id",
			        url: "${ctx}/system/user/role/${userId}",
			        createUrl: prefix + "/add/{id}",
			        updateUrl: prefix + "/edit/{id}",
			        removeUrl: prefix + "/remove/{id}",
			        modalName: "用户",
			        showSearch: false,
	                showRefresh: false,
	                showColumns: false,
	                showToggle: false,
			        columns: [{
			            checkbox: true,
			            width: '10%',
			            formatter: function(value, row, index) {
			            	var roles = "${roles}";
			            	if (roles.indexOf(":"+row.id+":")>-1) {
				            	return {
				                    disabled : true,//设置是否可用
				                    checked : true//设置选中
				                };
			            	} else {
			            		return value;
			            	}
	                    }
			        }, {
			            title: '角色名称',
			            field: 'name',
			            width: '90%',
			            align: 'center'
			        }]
			    };
			    $.table.init(options);
			});
			
	        function editResource(roleId) {
	        	var url = "${ctx}/system/resource/tree/" + roleId;
				var options = {
					title: '菜单选择',
					width: "380",
					url: url,
					callBack: doSubmit
				};
				$.modal.openOptions(options);
			}
			
			function doSubmit(index, layero){
				var body = layer.getChildFrame('body', index);
				$.operate.post("${ctx}/role/resource/save", { "roleId": body.find('#roleId').val(), "resourceIds": body.find('#resourceIds').val() }, function() {
					layer.close(index);
				});
			}
			
			function submitHandler() {
		        var idsA = $.table.selectColumns("id");
		        var ids = "";
		        for (ii=0; ii<idsA.length; ii++) {
		        	console.log(idsA[ii]);
		        	if (ii==0) {
		        		ids += idsA[ii];
		        		continue;
		        	}
		        	ids += ","+idsA[ii];
		        }
		        
		        $.operate.save("${ctx}/user/role/save", {userId: "${userId}", roleIds:ids});
		    }
		</script>
	</body>
</html>