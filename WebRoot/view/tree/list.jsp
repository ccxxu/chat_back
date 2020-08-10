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
					说明：<br/>
					1.可以通过调整显示次序来进行前端微信小程序云课的排序，次序小的排在前面，次序大的排在后面<br/>
					2.设置成有效，当前云课会在微信小程序显示，设置成无效，当前云课在微信小程序对用户不可见<br/>
					3.设置密码，是否启用密码由用户自己做主<br/>
					4.当前列表数据来自与小程序绑定的云服务，由于云服务会发生间歇性的“抖动”，可能有时会调用接口失败，没有数据，可以过一会再尝试刷新
				</div>
				
				<div class="btn-group-sm" id="toolbar" role="group">
					<a class="btn btn-success" onclick="addCate('root')" >
		                <i class="fa fa-plus"></i> 新增分类
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
			var editFlag = "";
			var removeFlag = "";
			var prefix = "${ctx}/tree";
			
	
			$(function() {
			    var options = {
			    	code: "_id",
			    	parentCode: "pid",
			    	uniqueId: "_id",
			    	rootIdValue: "root",
			        url: prefix + "/list",
			        createUrl: prefix + "/add",
			        updateUrl: prefix + "/edit/{id}",
			        removeUrl: prefix + "/remove",
			        exportUrl: prefix + "/export",
			        sortName: "roleSort",
			        uniqueId: "_id",
			        expandColumn: 0,
			        modalName: "云课",
			        columns: [{
			            field: 'name',
			            title: '云课名称',
			            width: '20%'
			        }, {
			            field: 'orderno',
			            title: '显示次序',
			            width: '15%'
			        }, {
			            field: 'is_valid',
			            title: '是否有效',
			            width: '15%',
                        formatter: function (value, row, index) {
                            return statusTools(row);
                        }
			        }, {
			            field: 'ipwd',
			            title: '设置密码',
			            width: '15%',
			            formatter: function (value, row, index) {
			            	if (row.pid == 'root') {
			            		return '--';
			            	} else {
			            		return value;
			            	}
                        }
			        }, {
			            field: 'pwdFlg',
			            title: '启用密码',
			            width: '15%',
                        formatter: function (value, row, index) {
                        	if (row.pid == 'root') {
			            		return '--';
			            	} else {
                            	return pwdTools(row);
			            	}
                        }
			        }, {
			            title: '操作',
			            width: '15%',
			            formatter: function(value, row, index) {
			                var actions = [];
			                actions.push('<a class="btn btn-success btn-xs ' + editFlag + '" href="javascript:void(0)" onclick="onEdit02(\'' + row._id + '\')"><i class="fa fa-edit"></i>编辑</a> ');
			                if (row.pid == 'root') {
			                	actions.push('<a class="btn btn-success btn-xs ' + editFlag + '" href="javascript:void(0)" onclick="addCate(\'' + row._id + '\')"><i class="fa fa-plus"></i>新增云课</a> ');
			                }
			                if (row.isDel) {
			                	actions.push('<a class="btn btn-danger btn-xs ' + removeFlag + '" href="javascript:void(0)" onclick="doRemove(\'' + row._id + '\',\'' + row.tbname + '\')"><i class="fa fa-remove"></i>删除</a> ');
			                }
			                return actions.join('');
			            }
			        }]
			    };
			    $.treeTable.init(options);
			});
			
			function statusTools(row) {
			    if (row.is_valid) {
			    	return '<i class=\"fa fa-toggle-on text-info fa-2x\" onclick="disable(\'' + row._id + '\')"></i> ';
	    		} else {
	    			return '<i class=\"fa fa-toggle-off text-info fa-2x\" onclick="enable(\'' + row._id + '\')"></i> ';
	    		}
			}
			
			function disable(roleId) {
				$.modal.confirm("确认要设置成无效吗？", function() {
					$.operate.post(prefix + "/save?src=json", { "_id": roleId, "is_valid": false });
			    })
			}
	
			function enable(roleId) {
				$.modal.confirm("确认要设置成有效吗？", function() {
					$.operate.post(prefix + "/save?src=json", { "_id": roleId, "is_valid": true });
			    })
			}
			
			function pwdTools(row) {
			    if (row.pwdFlg) {
			    	return '<i class=\"fa fa-toggle-on text-info fa-2x\" onclick="disablePwd(\'' + row._id + '\')"></i> ';
	    		} else {
	    			return '<i class=\"fa fa-toggle-off text-info fa-2x\" onclick="enablePwd(\'' + row._id + '\')"></i> ';
	    		}
			}
			
			function disablePwd(id) {
				$.modal.confirm("确认要放弃密码吗？", function() {
					$.operate.post(prefix + "/save?src=json", { "_id": id, "pwdFlg": false });
			    })
			}
	
			function enablePwd(id) {
				$.modal.confirm("确认要启用密码吗？", function() {
					$.operate.post(prefix + "/save?src=json", { "_id": id, "pwdFlg": true });
			    })
			}
			
			function doRemove(id, tb) {
				$.modal.confirm("确认要删除当前云课吗？", function() {
					$.operate.post(prefix + "/remove", { "id": id, "tb": tb });
			    })
			}
			
			function addCate(p_id) {
				var url = "${ctx}/tree/add?pid="+p_id;
				$.modal.open("新增分类", url);
			}
			
			function onEdit02(id) {
				var url = "${ctx}/tree/edit/"+id;
				$.modal.open("修改云课", url, 800, 400);
			}
			
		</script>
	</body>
</html>