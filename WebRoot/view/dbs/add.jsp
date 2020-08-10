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
		<script type="text/javascript" charset="utf-8" src="${ctx }/js/ueditor/ueditor.config.js?v=1.2"></script>
	    <script type="text/javascript" charset="utf-8" src="${ctx }/js/ueditor/ueditor.all.js"> </script>
	    <script type="text/javascript" charset="utf-8" src="${ctx }/js/ueditor/zh-cn.js"></script>
		<style type="">
			.w-e-text-container{
			    height: 120px !important;/*!important是重点，因为原div是行内样式设置的高度300px*/
			}			
			.dbs_left {
	        	position: absolute;
	        	width:100px;
	        }
	        .dbs_right {
	        	position: inherit;
	        	margin-left: 100px;
	        }
	        .edui-editor {
	        	width:95%;
	        }
		</style>
        <script type="text/javascript">
	        var ue = UE.getEditor('editor', {
	        	toolbars: [
	   				    ['bold', 'italic', 'underline', '|', 'fontfamily', 'fontsize']
	   				],
	   			initialFrameWidth:'95%',
	   			maximumWords:500
	        });		    
		    
            function submitHandler() {
                if ($.validate.form()) {
                	$.modal.loading("正在保存中...");
                	setTimeout("add()", 2000);
                }
            }

            function add() {
            	var myForm = document.getElementById("form-dbs-add");
		    	var formData = new FormData(myForm);
		    	formData.append("content", UE.getEditor('editor').getContent());
		    	console.log(formData.get("content"));
                $.ajax({
                    
                    
                    url: '${ctx}/dbs/save',
		    	    type: 'POST',
		    	    data: formData,                    // 上传formdata封装的数据
		    	    dataType: 'JSON',
		    	    cache: false,                      // 不缓存
		    	    processData: false,                // jQuery不要去处理发送的数据
		    	    contentType: false,                // jQuery不要去设置Content-Type请求头
                    
                    error : function(request) {
                        $.modal.alertError("系统错误");
                    },
                    success : function(data) {
                        $.operate.successCallback(data);
                    }
                });
            }
        </script>
    </head>
    <body class="white-bg">
        <div class="wrapper wrapper-content animated fadeInRight ibox-content">
            <form class="form-horizontal m" id="form-dbs-add">
            	<input type="hidden" name="tb" value="${tb}"/>
                <div class="form-group">
                    <label class="dbs_left control-label">消息内容：</label>
                    <div class="col-sm-11 dbs_right">
	                    <script id="editor" type="text/plain" style="width:100%;height:120px;"></script>
	                </div>
                </div>
                <div class="form-group">
                    <label class="dbs_left control-label">显示次序：</label>
                    <div class="col-sm-11 dbs_right">
                        <input class="form-control" style="width:95%;" type="text" name="xuhao" id="xuhao" />
                    </div>
                </div>
            </form>
        </div>
    </body>
</html>
