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
	</head>
	<body class="white-bg">
		<div class="wrapper wrapper-content animated fadeInRight ibox-content">
			<form class="form-horizontal m" id="form-menu-add">
				<input id="fdid" name="fdid" type="hidden" value="${rm.fdid}"/>
				<input id="pid" name="pid" type="hidden" value="${rm.pid}"/>
				<input id="treeId" name="parentId" type="hidden" value="${rm.pid}" />
				<%-- 
				<div class="form-group">
					<label class="col-sm-3 control-label">上级菜单：</label>
					<div class="col-sm-8">
				    	<div class="input-group">
					    	<input class="form-control" type="text" onclick="selectMenuTree()" id="treeName" readonly="true" th:value="${menu.menuName}">
				        	<span class="input-group-addon"><i class="fa fa-search"></i></span>
				    	</div>
					</div>
				</div>
				 --%>
				<div class="form-group">
					<label class="col-sm-3 control-label is-required">菜单名称：</label>
					<div class="col-sm-8">
						<input class="form-control" type="text" name="name" id="name" value="${rm.name }" required>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label is-required">菜单类型：</label>
					<div class="col-sm-8">
						<label class="radio-box"> <input type="radio" name="showType" value="0" <c:if test="${rm.type=='0' }">checked</c:if> /> 目录 </label> 
						<label class="radio-box"> <input type="radio" name="showType" value="1" <c:if test="${rm.type=='1' }">checked</c:if> /> 菜单 </label> 
						<label class="radio-box"> <input type="radio" name="showType" value="2" <c:if test="${rm.type=='2' }">checked</c:if> /> 按钮 </label>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">请求地址：</label>
					<div class="col-sm-8">
						<input id="url" name="url" class="form-control" type="text" value="${rm.url }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">打开方式：</label>
					<div class="col-sm-8">
						<select id="target" name="target" class="form-control m-b">
		                    <option value="menuItem">页签</option>
		                    <option value="menuBlank">新窗口</option>
		                </select>
					</div>
				</div>
				<%-- 
				<div class="form-group">
					<label class="col-sm-3 control-label">权限标识：</label>
					<div class="col-sm-8">
						<input id="perms" name="perms" class="form-control" type="text">
						<span class="help-block m-b-none"><i class="fa fa-info-circle"></i> 控制器中定义的权限标识，如：@RequiresPermissions("")</span>
					</div>
				</div>
				--%>
				<div class="form-group">
					<label class="col-sm-3 control-label is-required">显示排序：</label>
					<div class="col-sm-8">
						<input class="form-control" type="text" name="orderNum" value="${rm.orderNum }" required>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">图标：</label>
					<div class="col-sm-8">
						<input id="icon" name="icon" class="form-control" type="text" value="${rm.icon }" placeholder="选择图标">
	                    <div class="ms-parent" style="width: 100%;">
	                        <div class="icon-drop animated flipInX" style="display: none;max-height:200px;overflow-y:auto">
	                            <jsp:include page="icon.jsp" ></jsp:include>
	                        </div>
	                    </div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">菜单状态：</label>
					<div class="col-sm-8">
						<label class="radio-box" >
							<input type="radio" name="visible" value="1" <c:if test="${rm.visible!='0' }">checked</c:if> />
							显示
						</label>
						<label class="radio-box" >
							<input type="radio" name="visible" value="0" <c:if test="${rm.visible=='0' }">checked</c:if> />
							隐藏
						</label>
					</div>
				</div>
			</form>
		</div>
	 	<script>
	        var prefix = "${ctx}/system/resource";
	        
	        $("#form-menu-add").validate({
	        	onkeyup: false,
	        	rules:{
	        		menuType:{
	        			required:true,
	        		},
	        		menuName:{
	        			remote: {
	                        url: prefix + "/checkMenuNameUnique",
	                        type: "post",
	                        dataType: "json",
	                        data: {
	                        	"parentId": function() {
			                		return $("input[name='parentId']").val();
			                    },
	                        	"menuName" : function() {
	                                return $.common.trim($("#menuName").val());
	                            }
	                        },
	                        dataFilter: function(data, type) {
	                        	return $.validate.unique(data);
	                        }
	                    }
	        		},
	        		orderNum:{
	        			digits:true
	        		},
	        	},
	        	messages: {
	                "menuName": {
	                    remote: "菜单已经存在"
	                }
	            },
	            focusCleanup: true
	        });
	        
	        function submitHandler() {
		        if ($.validate.form()) {
		            $.operate.save("${ctx}/system/resource/save", $('#form-menu-add').serialize());
		        }
		    }
	
	        $(function() {
	        	$("input[name='icon']").focus(function() {
	                $(".icon-drop").show();
	            });
	        	$("#form-menu-add").click(function(event) {
	        	    var obj = event.srcElement || event.target;
	        	    if (!$(obj).is("input[name='icon']")) {
	        	    	$(".icon-drop").hide();
	        	    }
	        	});
	        	$(".icon-drop").find(".ico-list i").on("click", function() {
	        		$('#icon').val($(this).attr('class'));
	            });
	        	$('input').on('ifChecked', function(event){  
	        		var menuType = $(event.target).val();
	        		if (menuType == "M") {
	                    $("#url").parents(".form-group").hide();
	                    $("#perms").parents(".form-group").hide();
	                    $("#icon").parents(".form-group").show();
	                    $("#target").parents(".form-group").hide();
	                    $("input[name='visible']").parents(".form-group").show();
	                } else if (menuType == "C") {
	                	$("#url").parents(".form-group").show();
	                    $("#perms").parents(".form-group").show();
	                    $("#icon").parents(".form-group").show();
	                    $("#target").parents(".form-group").show();
	                    $("input[name='visible']").parents(".form-group").show();
	                } else if (menuType == "F") {
	                	$("#url").parents(".form-group").hide();
	                    $("#perms").parents(".form-group").show();
	                    $("#icon").parents(".form-group").hide();
	                    $("#target").parents(".form-group").hide();
	                    $("input[name='visible']").parents(".form-group").hide();
	                }
	        	});  
	        });
	
	        /*菜单管理-新增-选择菜单树*/
	        function selectMenuTree() {
	        	var treeId = $("#treeId").val();
	        	var menuId = treeId > 0 ? treeId : 1;
	        	var url = prefix + "/selectMenuTree/" + menuId;
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
	   			$("#treeId").val(body.find('#treeId').val());
	   			$("#treeName").val(body.find('#treeName').val());
	   			layer.close(index);
			}
	    </script>
	</body>
</html>
