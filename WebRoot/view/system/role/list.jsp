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
			<div class="col-sm-12 search-collapse">
				<form id="menu-form">
					<div class="select-list">
						<ul>
							<li>
								菜单名称：<input type="text" name="menuName"/>
							</li>
							<li>
								菜单状态：<select name="visible" >
									<option value="">所有</option>
									<option th:each="dict : ${type}" th:text="${dict.dictLabel}" th:value="${dict.dictValue}"></option>
								</select>
							</li>
							<li>
								<a class="btn btn-primary btn-rounded btn-sm" onclick="$.treeTable.search()"><i class="fa fa-search"></i>&nbsp;搜索</a>
								<a class="btn btn-warning btn-rounded btn-sm" onclick="$.form.reset()"><i class="fa fa-refresh"></i>&nbsp;重置</a>
							</li>
						</ul>
					</div>
				</form>
			</div>
                
            <div class="btn-group-sm" id="toolbar" role="group">
		        <a class="btn btn-success" onclick="$.operate.add('', 800, 400)">
                    <i class="fa fa-plus"></i> 新增
                </a>
                <a class="btn btn-primary single disabled" onclick="$.operate.edit('', 800, 400)" >
		            <i class="fa fa-edit"></i> 修改
		        </a>
				<a class="btn btn-danger multiple disabled" onclick="$.operate.removeAll()">
		            <i class="fa fa-remove"></i> 删除
		        </a>
	        </div>
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
		var prefix = "${ctx}/system/role";

		$(function() {
			console.log("=="+prefix);
		    var options = {
		    	uniqueId: "id",
		        url: prefix + "/list",
		        createUrl: prefix + "/add/{id}",
		        updateUrl: prefix + "/edit/{id}",
		        removeUrl: prefix + "/remove/{id}",
		        modalName: "用户",
		        columns: [{
		            checkbox: true,
		            width: '5%'
		        }, {
		            title: '角色名称',
		            field: 'name',
		            width: '20%',
		            align: 'center'
		        }, {
		            field: 'descc',
		            title: '角色描述',
		            width: '20%',
		            align: 'center'
		        }, {
		            field: 'orderNum',
		            title: '显示次序',
		            width: '20%',
		            align: 'center'
		        }, {
		            field: 'status',
		            title: '是否可用',
		            width: '20%',
		            align: "center",
		            formatter: function(value, row, index) {
		            	if (row.status=='1') {
		            		return '<i class=\"fa fa-toggle-on text-info fa-2x\" onclick="disable(\'' + row.id + '\')"></i> ';
			    		} else {
			    			return '<i class=\"fa fa-toggle-off text-info fa-2x\" onclick="enable(\'' + row.id + '\')"></i> ';
			    		}
                    }
		        }, {
		            title: '操作',
		            width: '25%',
		            align: "center",
		            formatter: function(value, row, index) {
		                var actions = [];
		                actions.push('<a class="btn btn-success btn-xs ' + editFlag + '" href="javascript:void(0)" onclick="$.operate.edit(\'' + row.id + '\', 800, 400)"><i class="fa fa-edit"></i>编辑</a> ');
		                actions.push('<a class="btn btn-success btn-xs ' + addFlag + '" href="javascript:void(0)" onclick="editResource(\'' + row.id + '\')"><i class="fa fa-file"></i>分配资源</a> ');
		                actions.push('<a class="btn btn-danger btn-xs ' + removeFlag + '" href="javascript:void(0)" onclick="$.operate.remove(\'' + row.id + '\')"><i class="fa fa-trash"></i>删除</a>');
		                return actions.join('');
		            }
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
	</script>
</body>
</html>