<%request.setAttribute("ctx", request.getContextPath()); %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
					3.当前列表数据来自与小程序绑定的云服务，由于云服务会发生间歇性的“抖动”，可能有时会调用接口失败，没有数据，可以过一会再尝试刷新<br/>
					4.当前数据管理与2.x小程序(未经过发布审核)同步，如果想在1.x小程序看到数据，需要在点击“发布到1.x小程序”按钮，触发后台脚本
				</div>
			 	
				<div class="btn-group-sm" id="toolbar">
					<a class="btn btn-success" onclick="$.operate.add()" >
		                <i class="fa fa-plus"></i> 添加文本
		            </a>
		            <a class="btn btn-success" onclick="upload('jpg')" >
			            <i class="fa fa-upload"></i> 上传图片
			        </a>
		            <a class="btn btn-success" onclick="upload('mp3')" >
			            <i class="fa fa-upload"></i> 上传语音
			        </a>
			        <a class="btn btn-success" onclick="upload02()" >
			            <i class="fa fa-upload"></i> 批量上传
			        </a>
			        <a class="btn btn-success" disabled >
			            <i class="fa fa-upload"></i> 上传视频
			        </a>
			        <a class="btn btn-success" disabled >
			            <i class="fa fa-mobile"></i> 发布到1.x小程序
			        </a>
		        </div>
		        
		        <div class="col-sm-12 select-table table-striped">
				    <table id="bootstrap-table"></table>
				</div>
			</div>
		</div>
		<script type="text/javascript">
			var editFlag = "";
			var removeFlag = "";
			var prefix = "${ctx}/dbs";
		
			$(function() {
			    var options = {
			        url: prefix + "/list?tb=${tb}",
			        createUrl: prefix + "/add?tb=${tb}",
			        updateUrl: prefix + "/edit/{id}",
			        removeUrl: prefix + "/remove?tb=${tb}",
			        uniqueId: "_id",
			        modalName: "消息内容",
			        columns: [{
			            checkbox: true
			        }, {
			            field: 'type',
			            title: '消息类型',
			            align: 'center',
			            width: '10%',
			            formatter: function(value, row, index) {
			                if (row.type=='mp3') {
			                	return "语音";
			                } else if (row.type=='jpg') {
			                	return "图片";
			                } else if (row.type=='mp4') {
			                	return "视频";
			                } else {
			                	return "文本";
			                }
			            }
			        }, {
			            field: 'name',
			            title: '消息内容',
			            width: '35%',
			            sortable: true,
			            formatter: function(value, row, index) {
			                if (row.type=='txt') {
			                	return row.content;
			                } else {
			                	return value;
			                }
			            }
			        }, {
			            field: '',
			            title: '是否有效',
			            align: 'center',
			            width: '10%',
			            formatter: function(value, row, index) {
			            	if (row.is_valid) {
			            		return '<i class=\"fa fa-toggle-on text-info fa-2x\" onclick="disable(\'' + row._id + '\')"></i> ';
				    		} else {
				    			return '<i class=\"fa fa-toggle-off text-info fa-2x\" onclick="enable(\'' + row._id + '\')"></i> ';
				    		}
			            }
			        }, {
			            field: 'totalTime',
			            title: '时长(秒)',
			            align: 'center',
			            sortable: true,
			            width: '10%',
			        }, {
			            field: 'xuhao',
			            title: '显示次序',
			            sortable: true,
			            width: '10%',
			        }, {
			            title: '操作',
			            align: 'center',
			            width: '20%',
			            formatter: function(value, row, index) {
			                var actions = [];
			                actions.push('<a class="btn btn-success btn-xs ' + editFlag + '" href="javascript:void(0)" onclick="doEdit(\'' + row._id + '\', \'1\')"><i class="fa fa-edit"></i>修改次序</a> ');
			                if (row.type=='txt') {
			                	actions.push('<a class="btn btn-primary btn-xs ' + editFlag + '" href="javascript:void(0)" onclick="doEdit(\'' + row._id + '\', \'2\')"><i class="fa fa-edit"></i>编辑</a> ');
			                } else if (row.type=='jpg' || row.type=='mp3' || row.type=='mp4') {
			                	actions.push('<a class="btn btn-primary btn-xs ' + editFlag + '" href="javascript:void(0)" onclick="doDown(\'' + row._id + '\')"><i class="fa fa-download"></i>下载</a> ');
			                }			                
			                actions.push('<a class="btn btn-danger btn-xs ' + removeFlag + '" href="javascript:void(0)" onclick="doRemove(\'' + row._id + '\',\'' + row.fileId + '\')"><i class="fa fa-remove"></i>删除</a> ');
			                return actions.join('');
			            }
			        }]
			    };
			    $.table.init(options);
			});
			
			function upload(type) {
				var url = "${ctx}/dbs/upload?tb=${tb}&type="+type;
				if ('mp3' == type) {
					$.modal.open("上传语音", url);
				} else if ('mp4' == type) {
					$.modal.open("上传视频", url);
				} else if ('jpg' == type) {
					$.modal.open("上传图片", url);
				}
			}
			
			function upload02() {
				var url = "${ctx}/dbs/upload02?tb=${tb}";
				$.modal.open("批量上传", url);
			}
			
			function doEdit(id, et) {
				var url = "${ctx}/dbs/edit?tb=${tb}&id="+id+"&et="+et;
				if('1' == et) {
					$.modal.open("修改显示次序", url);
				} else if('2' == et) {
					$.modal.open("修改消息内容", url);
				}
			}
			
			function doDown(id) {
				window.location.href = "${ctx}/dbs/download?id="+id;
				/*
				$.ajax({
                    cache : true,
                    type : "POST",
                    url : "${ctx}/dbs/download",
                    data : {id:id},
                    async : false,
                    error : function(request) {
                        $.modal.alertError("系统错误");
                    },
                    success : function(data) {
                        window.location.href = data.msg;
                    }
                });
				*/
			}
			
			function disable(roleId) {
				$.modal.confirm("确认要设置成无效吗，设置为无效之后，小程序将看不到此条消息？", function() {
					$.operate.post(prefix + "/save", { "_id": roleId, "is_valid": false, "tb": "${tb}" });
			    })
			}
	
			function enable(roleId) {
				$.modal.confirm("确认要设置成有效吗？", function() {
					$.operate.post(prefix + "/save", { "_id": roleId, "is_valid": true, "tb": "${tb}" });
			    })
			}
			
			function doRemove(id, fileId) {
				$.modal.confirm("确认要删除记录吗？", function() {
					$.operate.post(prefix + "/remove?tb=${tb}", { "id": id, "fileId": fileId });
			    })
			}
		</script>
	</body>
</html>