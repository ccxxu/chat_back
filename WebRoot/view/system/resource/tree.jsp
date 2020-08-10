<%request.setAttribute("ctx", request.getContextPath()); %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta name="keywords" content="">
		<meta name="description" content="">
		<link rel="shortcut icon" href="favicon.ico">
		<link href="${ctx }/css/bootstrap.min.css" rel="stylesheet"/>
		<link href="${ctx }/css/font-awesome.min.css" rel="stylesheet"/>
		<!-- bootstrap-table 表格插件样式 -->
		<link href="${ctx }/js/bootstrap-table/bootstrap-table.min.css" rel="stylesheet"/>
		<link href="${ctx }/css/animate.css" rel="stylesheet"/>
		<link href="${ctx }/css/style.css" rel="stylesheet"/>
		<link href="${ctx }/css/ccx/ccx-ui.css" rel="stylesheet"/>
		<link href="${ctx }/css/ztree/metro/zTreeStyle.css" rel="stylesheet"/>
		<style>
			body{height:auto;font-family: "Microsoft YaHei";}
			button{font-family: "SimSun","Helvetica Neue",Helvetica,Arial;}
		</style>
	</head>
	<body class="hold-transition box box-main">
		<input id="treeId"   name="treeId"    type="hidden" value="0"/>
		<input id="treeName" name="treeName"  type="hidden" value="0"/>
		<input id="roleId" name="roleId"  type="hidden" value="${roleId }"/>
		<input id="resourceIds" name="resourceIds"  type="hidden" value=""/>
		<div class="wrapper"><div class="treeShowHideButton" onclick="$.tree.toggleSearch();">
			<label id="btnShow" title="显示搜索" style="display:none;">︾</label>
			<label id="btnHide" title="隐藏搜索">︽</label>
		</div>
		<div class="treeSearchInput" id="search">
			<label for="keyword">关键字：</label><input type="text" class="empty" id="keyword" maxlength="50">
			<button class="btn" id="btn" onclick="$.tree.searchNode()"> 搜索 </button>
		</div>
		<div class="treeExpandCollapse">
			<a href="#" onclick="$.tree.expand()">展开</a> /
			<a href="#" onclick="$.tree.collapse()">折叠</a>
		</div>
		<div id="tree" class="ztree treeselect"></div>
		</div>
		<script src="${ctx }/js/jquery.min.js"></script>
		<script src="${ctx }/js/bootstrap.min.js"></script>
		<!-- bootstrap-table 表格插件 -->
		<script src="${ctx }/js/bootstrap-table/bootstrap-table.min.js?v=20191219"></script>
		<script src="${ctx }/js/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
		<script src="${ctx }/js/bootstrap-table/extensions/mobile/bootstrap-table-mobile.js"></script>
		<script src="${ctx }/js/bootstrap-table/extensions/toolbar/bootstrap-table-toolbar.min.js"></script>
		<script src="${ctx }/js/bootstrap-table/extensions/columns/bootstrap-table-fixed-columns.js"></script>
		<!-- jquery-validate 表单验证插件 -->
		<script src="${ctx }/js/validate/jquery.validate.min.js"></script>
		<script src="${ctx }/js/validate/messages_zh.min.js"></script>
		<script src="${ctx }/js/validate/jquery.validate.extend.js"></script>
		<!-- jquery-validate 表单树插件 -->
		<script src="${ctx }/js/bootstrap-table/bootstrap-treetable.js"></script>
		<!-- jquery-export 表格导出插件 -->
		<script src="${ctx }/js/bootstrap-table/extensions/export/bootstrap-table-export.js"></script>
		<script src="${ctx }/js/bootstrap-table/extensions/export/tableExport.js"></script>
		<!-- 遮罩层 -->
		<script src="${ctx }/js/jquery.blockUI.js"></script>
	    <script src="${ctx }/js/iCheck/icheck.min.js"></script>
		<script src="${ctx }/js/layer/layer.min.js"></script>
		<script src="${ctx }/js/layui/layui.js"></script>
		<script src="${ctx }/js/ccx/common.js?v=4.1.0"></script>
		<script src="${ctx }/js/ccx/ccx-ui.js?v=4.1.0"></script>
		<script src="${ctx }/js/ztree/jquery.ztree.all-3.5.js"></script>
		<script>
			$(function() {
				var url = "${ctx}/system/resource/treeData?roleId=${roleId}";
				var options = {
			        url: url,
			        expandLevel: 1,
			        onClick : zOnClick,
			        onCheck : zOnCheck,
			        check: {
    				    enable: true,             // 置 zTree 的节点上是否显示 checkbox / radio
    				    nocheckInherit: false,      // 设置子节点是否自动继承
    				},
			    };
				$.tree.init(options);
			});
			
			function zOnClick(event, treeId, treeNode) {
			    var treeId = treeNode.id;
			    var treeName = treeNode.name;
			    console.log(treeId+"=="+treeName);
			    $("#treeId").val(treeId);
			    $("#treeName").val(treeName);
			}
			
			function zOnCheck(event, treeId, treeNode) {
			    //console.log($.tree.getCheckedNodes());
			    $("#resourceIds").val($.tree.getCheckedNodes());
			}
		</script>
	</body>
</html>
