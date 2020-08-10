<%request.setAttribute("ctx", request.getContextPath()); %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
		<script type="text/javascript">
		    function avatar() {
		        var url = '${ctx}/system/share';
		        $.modal.open("修改头像", url);
		    }
		    
		    function submitProp() {
		    	if ($.validate.form()) {
		    		var titleText = $("#titleText").val();
		    		var title = $("#title").val();
		    		var shareText = $("#shareText").val();
		    		var tip = $("#tip").val();
		    		var content = $("#content").val();
		    		$.operate.post("${ctx}/prop/save", {"titleText": titleText, "title": title, "shareText": shareText, "tip": tip, "content": content });
		    	}
		    }
		</script>
	</head>

	<body class="gray-bg" style="font: 14px Helvetica Neue, Helvetica, PingFang SC, 微软雅黑, Tahoma, Arial, sans-serif !important;">
    	<input id="userId" name="userId" type="hidden" value="" />
    	<section class="section-content">
    		<div class="row">
		        <div class="col-sm-3 pr5" style="width:800px;position: absolute;">
		            <div class="ibox float-e-margins">
		                <div class="ibox-title ibox-title-gray dashboard-header gray-bg">
		                    <h5>小程序属性</h5>
		                </div>
		                <div class="ibox-content">
		                    <div class="tab-content">
		                        <div class="tab-pane active" id="user_info" >
		                            <form class="form-horizontal" id="form-user-edit">
		                                <!--隐藏ID-->
		                                <input name="id" id="id" type="hidden">
		                                <div class="form-group">
		                                    <label class="col-sm-2 control-label" style="padding-top:40px;">分享图片：</label>
		                                    <div class="text-center">
						                        <a ><img class="img-lg" onclick="avatar()" title="点击修改图片" src="${model.shareUrl }"></a>
						                    </div>
		                                </div>
		                                <div class="form-group">
		                                    <label class="col-sm-2 control-label">负载配置：</label>
		                                    <div class="col-sm-10" style="padding:6px 20px 0px 20px;">
		                                        <input type="checkbox" style="margin-right:5px;" disabled <c:if test="${fn:contains(model.serverIP, '211.159.180.93') }">checked</c:if> name="titleText" value="0" />云服务
		                                        <input type="checkbox" style="margin-left:10px;margin-right:5px;" disabled <c:if test="${fn:contains(model.serverIP, 'msg.mzwh.club') }">checked</c:if> name="titleText" value="1" />镜像01
		                                        <input type="checkbox" style="margin-left:20px;margin-right:5px;" disabled <c:if test="${fn:contains(model.serverIP, 'ccx-1989') }">checked</c:if> name="titleText" value="2" />镜像02
		                                        <input type="checkbox" style="margin-left:20px;margin-right:5px;" disabled <c:if test="${fn:contains(model.serverIP, 'i-love-life') }">checked</c:if> name="titleText" value="3" />镜像03
		                                        <label style="color:red;margin-left:20px;margin-right:5px;">注：当前参数由系统自动设置</label>  
		                                    </div>
		                                </div>
		                                <%-- 
		                                <div class="form-group">
		                                    <label class="col-sm-2 control-label">云存储空间：</label>
		                                    <div class="col-sm-10" style="padding:10px 20px 0px 20px;">
		                                        <label>265.68M/5G</label>  
		                                    </div>
		                                </div>
		                                 --%>
		                                <div class="form-group">
		                                    <label class="col-sm-2 control-label">一级标题：</label>
		                                    <div class="col-sm-10">
		                                        <input type="text" class="form-control" id="titleText" name="titleText" value="${model.titleText }" placeholder="请输入一级标题">
		                                    </div>
		                                </div>
		                                <div class="form-group">
		                                    <label class="col-sm-2 control-label">二级标题：</label>
		                                    <div class="col-sm-10">
		                                        <input type="text" class="form-control" id="title" name="title" value="${model.title }" placeholder="请输入二级标题">
		                                    </div>
		                                </div>
		                                <div class="form-group">
		                                    <label class="col-sm-2 control-label">分享标语：</label>
		                                    <div class="col-sm-10">
		                                        <input type="text" class="form-control" id="shareText" name="shareText" value="${model.shareText }" placeholder="请输入分享标语">
		                                    </div>
		                                </div>
		                                <div class="form-group">
		                                    <label class="col-sm-2 control-label">听课须知：</label>
		                                    <div class="col-sm-10">
		                                        <input type="text" class="form-control" id="tip" name="tip" value="${model.tip }" placeholder="请输入听课须知">
		                                    </div>
		                                </div>
		                                <div class="form-group">
		                                    <label class="col-sm-2 control-label">须知内容：</label>
		                                    <div class="col-sm-10">
		                                        <textarea type="text" class="form-control" id="content" style="min-height:100px;" name="content"  placeholder="请输入须知内容">${content }</textarea>
		                                    </div>
		                                </div>
		                                <div class="form-group">
		                                    <div class="col-sm-offset-2 col-sm-10">
		                                        <button type="button" class="btn btn-sm btn-primary" onclick="submitProp()"><i class="fa fa-check"></i>保 存</button>&nbsp;
		                                        <button type="button" class="btn btn-sm btn-danger" onclick="closeItem()"><i class="fa fa-reply-all"></i>关 闭 </button>
		                                    </div>
		                                </div>
		                            </form>
		                        </div>
			                </div>
			            </div>
			        </div>
          		</div>
    		</div>
		</section>
	</body>
</html>
