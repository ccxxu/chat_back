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
								栏目名称：<input type="text" name="menuName"/>
							</li>
							<li>
								栏目状态：<select name="visible" >
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
		        <a class="btn btn-success" onclick="doAdd(0)" shiro:hasPermission="system:menu:add">
                    <i class="fa fa-plus"></i> 新增
                </a>
                <a class="btn btn-info" id="expandAllBtn">
                    <i class="fa fa-exchange"></i> 展开/折叠
                </a>
	        </div>
       		 <div class="col-sm-12 select-table table-striped">
	            <table id="bootstrap-tree-table"></table>
	        </div>
	    </div>
	</div>
	
	<script type="text/javascript">
		var addFlag = "";
		var editFlag = "";
		var removeFlag = "";
		var datas = "";
		var prefix = "${ctx}/column";

		$(function() {
			console.log("=="+prefix);
		    var options = {
	    		code: "key",
		    	parentCode: "pid",
		    	uniqueId: "id",
		    	rootIdValue: "0",
		        url: prefix + "/list",
		        createUrl: prefix + "/add/{id}",
		        updateUrl: prefix + "/edit/{id}",
		        removeUrl: prefix + "/remove/{id}",
		        modalName: "栏目",
		        expandColumn: 0,
		        expandAll: false,
		        expandFirst: false,
		        columns: [{
		            title: '栏目名称',
		            field: 'name',
		            width: '10%'
		        }, {
		            field: 'hierarchical',
		            title: '标识符',
		            width: '10%',
		            align: "left"
		        }, {
		            field: 'key',
		            title: '主键',
		            width: '10%',
		            align: "left"
		        }, {
		            field: 'staticUrl',
		            title: '请求地址',
		            width: '15%',
		            align: "left"
		        }, {
		            title: '类型',
		            field: 'type',
		            width: '10%',
		            align: "left",
		            formatter: function(value, item, index) {
		                if (item.type == '0') {
		                    return '<span class="label label-success">目录</span>';
		                } else if (item.type == '1') {
		                    return '<span class="label label-primary">菜单</span>';
		                } else if (item.type == '2') {
		                    return '<span class="label label-warning">按钮</span>';
		                }
		            }
		        }, {
		            field: 'visible',
		            title: '可见',
		            width: '10%',
		            align: "left",
		            formatter: function(value, row, index) {
		            	if (row.visible=='1') {
		            		return '<i class=\"fa fa-toggle-on text-info fa-2x\" onclick="disable(\'' + row.id + '\')"></i> ';
			    		} else {
			    			return '<i class=\"fa fa-toggle-off text-info fa-2x\" onclick="enable(\'' + row.id + '\')"></i> ';
			    		}
                    }
		        }, {
		            field: 'remark',
		            title: '备注',
		            width: '15%',
		            align: "left"
		        }, {
		            title: '操作',
		            width: '20%',
		            align: "left",
		            formatter: function(value, row, index) {
		                var actions = [];
		                actions.push('<a class="btn btn-success btn-xs ' + editFlag + '" href="javascript:void(0)" onclick="doEdit(\'' + row.id + '\')"><i class="fa fa-edit"></i>编辑</a> ');
		                actions.push('<a class="btn btn-success btn-xs ' + editFlag + '" href="javascript:void(0)" onclick="doKey(\'' + row.id + '\')"><i class="fa fa-key"></i>主键</a> ');
		                actions.push('<a class="btn btn-info btn-xs ' + addFlag + '" href="javascript:void(0)" onclick="doAdd(\'' + row.key + '\')"><i class="fa fa-plus"></i>新增</a> ');
		                if (row.hasSub == '0' || row.hasSub == '' || row.hasSub == null) {
		                	actions.push('<a class="btn btn-danger btn-xs ' + removeFlag + '" href="javascript:void(0)" onclick="$.operate.remove(\'' + row.id + '\')"><i class="fa fa-trash"></i>删除</a> ');
		                }
		                actions.push('<a class="btn btn-success btn-xs " href="javascript:void(0)" onclick="doPublish(\'' + row.id + '\')"><i class="fa fa-plus-circle"></i>发布</a> ');
		                return actions.join('');
		            }
		        }]
		    };
		    $.treeTable.init(options);
		});
		
		function doAdd(pid) {
			var url = prefix+"/add/"+pid;
			$.modal.open("新增栏目", url, 800, 600);
		}
		
		function doEdit(id) {
			var url = prefix+"/edit/"+id;
			$.modal.open("修改栏目", url, 800, 600);
		}
		
		function doKey(id) {
			var url = prefix+"/key/"+id;
			$.modal.open("修改栏目主键", url, 800, 200);
		}
		
		function doPublish(id) {
			console.log("id="+id);
			$.ajax({
	    	    url: '${ctx}/column/publish',
	    	    type: 'POST',
	    	    data: {id: id},                    // 上传formdata封装的数据
	    	    dataType: 'JSON',
	    	    success:function (result) {           //成功回调
    	    		parent.$.modal.msgSuccess(result.msg);
	    	    }
	    	});
		}
	</script>
</body>
</html>