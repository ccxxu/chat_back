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
				<input id="pid" name="pid" type="hidden" value="${pid}"/>
				<input id="imageUrl_" name="imageUrl_" type="hidden" value="" />
		        <input id="ext" name="ext" type="hidden" value=""/>
				<div class="form-group">
					<label class="col-sm-2 control-label is-required">栏目名称：</label>
					<div class="col-sm-10">
						<input class="form-control" type="text" name="name" id="name" value="${rm.name }" required>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label is-required">英文名称：</label>
					<div class="col-sm-10">
						<input class="form-control" type="text" name="nameEN" id="nameEN" value="${rm.nameEN }" required>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label is-required">栏目全称：</label>
					<div class="col-sm-10">
						<input class="form-control" type="text" name="fullName" id="fullName" value="${rm.fullName }" required>
					</div>
				</div>
				<div class="form-group">
	                <label class="col-sm-2 control-label is-required">封面：</label>
	                <div class="col-sm-10" style="height:150px;">
	                    <img id="forntImage" style="position:absolute;width:220px;heigth:150px;" src="http://www.mzwh.com:8081/article/img/excerpt.jpg">
						<input style="position:inherit;width:150px;margin-left:240px;margin-top:20px;" name="imgFile" id="imgFile" type="file" accept="image/*"/>
						<input type="button" style="position:inherit;width:50px;margin-left:240px;margin-top:20px;" value="上传" onclick="upload()"/>
	                </div>
	            </div>
				<div class="form-group">
					<label class="col-sm-2 control-label is-required">英文全称：</label>
					<div class="col-sm-10">
						<input class="form-control" type="text" name="fullNameEN" id="fullNameEN" value="${rm.fullNameEN }" required>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">发布地址：</label>
					<div class="col-sm-10">
						<input id="staticUrl" name="staticUrl" class="form-control" type="text" value="${rm.staticUrl }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">栏目跳转方式：</label>
					<div class="col-sm-10">
						<select id="category" name="category" class="form-control">
		                    <option value="column" <c:if test="${rm.category=='column' }">selected</c:if>>子栏目</option>
		                    <option value="article" <c:if test="${rm.category=='article' }">selected</c:if>>文章页面</option>
		                </select>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">打开方式：</label>
					<div class="col-sm-10">
						<select id="openType" name="openType" class="form-control">
		                    <option value="_self" <c:if test="${rm.openType=='_self' }">selected</c:if>>本窗口</option>
		                    <option value="_blank" <c:if test="${rm.openType=='_blank' }">selected</c:if>>新窗口</option>
		                    <option value="_parent" <c:if test="${rm.openType=='_parent' }">selected</c:if>>父窗口</option>
		                    <option value="_top" <c:if test="${rm.openType=='_top' }">selected</c:if>>顶窗口</option>
		                </select>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label is-required">显示排序：</label>
					<div class="col-sm-10">
						<input class="form-control" type="text" name="orderNum" value="${rm.orderNum }" required>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">栏目字段：</label>
					<div class="col-sm-10">
						<select id="colKey" name="colKey" class="form-control">
		                    <option value="mainColKey" <c:if test="${rm.colKey=='mainColKey' }">selected</c:if>>文章栏目1</option>
		                    <option value="slaveColKey" <c:if test="${rm.colKey=='slaveColKey' }">selected</c:if>>文章栏目2</option>
		                </select>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">栏目取值：</label>
					<div class="col-sm-10">
						<input class="form-control" type="text" name="colValue" value="${rm.colValue }" required>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">代码前缀：</label>
					<div class="col-sm-10">
						<textarea id="preCode" name="preCode" class="form-control" style="height:100px;" type="text" placeholder="编写代码前缀">${rm.preCode }</textarea>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">代码主体：</label>
					<div class="col-sm-10">
						<textarea id="bodyCode" name="bodyCode" class="form-control" style="height:100px;" type="text" placeholder="编写代码主体">${rm.bodyCode }</textarea>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">代码后缀：</label>
					<div class="col-sm-10">
						<textarea id="postCode" name="postCode" class="form-control" style="height:100px;" type="text" placeholder="编写代码后缀">${rm.postCode }</textarea>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">是否显示：</label>
					<div class="col-sm-10">
						<label class="radio-box" >
							<input type="radio" name="state" value="1" <c:if test="${rm.state!='0' }">checked</c:if> />
							显示
						</label>
						<label class="radio-box" >
							<input type="radio" name="state" value="0" <c:if test="${rm.state=='0' }">checked</c:if> />
							隐藏
						</label>
					</div>
				</div>
			</form>
		</div>
	 	<script>
	        
	        function submitHandler() {
		        if ($.validate.form()) {
		            $.operate.save("${ctx}/column/save", $('#form-menu-add').serialize());
		        }
		    }
	
	        function upload() {
            	var formData = new FormData();
            	formData.append("imgFile",$('#imgFile')[0].files[0]);
            	
                $.ajax({
                    type : "POST",
                    url : "<%=request.getContextPath()%>/column/image/upload",
                    data : formData,
                    async : false,
                    processData : false, // 使数据不做处理
                    contentType : false, // 不要设置Content-Type请求头
                    error : function(request) {
                        $.modal.alertError("系统错误");
                    },
                    success : function(data) {
                        $("#forntImage").attr("src", data.src);
                        $("#imageUrl_").val("1");
                        $("#ext").val(data.ext);
                    }
                });
            }
	    </script>
	</body>
</html>
