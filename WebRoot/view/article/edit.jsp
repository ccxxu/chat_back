<%request.setAttribute("ctx", request.getContextPath()); %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
	    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
	    <link href="<%=request.getContextPath() %>/css/bootstrap.min.css" rel="stylesheet"/>
	    <link href="<%=request.getContextPath() %>/css/jquery.contextMenu.min.css" rel="stylesheet"/>
	    <link href="<%=request.getContextPath() %>/css/font-awesome.min.css" rel="stylesheet"/>
	    <link href="<%=request.getContextPath() %>/css/animate.css" rel="stylesheet"/>
	    <link href="<%=request.getContextPath() %>/css/style.css" rel="stylesheet"/>
	    <link href="<%=request.getContextPath() %>/css/skins.css" rel="stylesheet"/>
	    <link href="<%=request.getContextPath() %>/css/ccx/ccx-ui.css?v=4.1.0" rel="stylesheet"/>
	    <link href="<%=request.getContextPath() %>/css/date/bootstrap-datetimepicker.min.css" rel="stylesheet"/>
	    
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
		<script src="<%=request.getContextPath() %>/js/ccx/common.js?v=4.2.0"></script>
		<script src="<%=request.getContextPath() %>/js/ccx/index.js"></script>
		<script src="<%=request.getContextPath() %>/js/jquery.fullscreen.js"></script>
		
	    <script type="text/javascript" charset="utf-8" src="${ctx }/js/ueditor/ueditor.config.js?v=1.2"></script>
	    <script type="text/javascript" charset="utf-8" src="${ctx }/js/ueditor/ueditor.all.js"> </script>
	    <script type="text/javascript" charset="utf-8" src="${ctx }/js/ueditor/ueditor.all.min.js?v=1.1"> </script>
	    <script type="text/javascript" charset="utf-8" src="${ctx }/js/date/bootstrap-datetimepicker.min.js"></script>
	    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
	    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
	    <script type="text/javascript" charset="utf-8" src="${ctx }/js/ueditor/zh-cn.js"></script>
	
	    <style type="text/css">
	        div{
	            width:100%;
	        }
	        .article_left {
	        	position: absolute;
	        	width:100px;
	        }
	        .article_right {
	        	position: inherit;
	        	margin-left: 100px;
	        }
	        .laydate-theme-molv {
			    border: none;
			    margin-left:100px;
			    width:274px;
			}
			.laydate-footer-btns {
			    position: absolute;
			    right: 10px;
			    top: 10px;
			    left: 10px;
			}
	    </style>
	</head>
	<body>
		<div class="tab-content">
			<div class="tab-pane active" id="user_info">
				<form class="form-horizontal" id="form-article-add">
		            <!--隐藏ID-->
		            <input name="id" id="id" type="hidden" value="${am.id }">
		            <input id="mainColKey" name="mainColKey" type="hidden" value="${am.mainColKey }" />
		            <input id="slaveColKey" name="slaveColKey" type="hidden" value="${am.slaveColKey }" />
		            <input id="imageUrl_" name="imageUrl_" type="hidden" value="" />
		            <input id="ext" name="ext" type="hidden" value=""/>
		            <div class="form-group" style="margin-top: 15px;">
		                <label class="control-label article_left">文章标题：</label>
		                <div class="col-sm-11 article_right">
		                    <input type="text" class="form-control" id="title" name="title" value="${am.title }" placeholder="请输入文章标题">
		                </div>
		            </div>
		            <div class="form-group">
		                <label class="control-label article_left">二级标题：</label>
		                <div class="col-sm-11 article_right">
		                    <input type="text" class="form-control" id="shortTitle" name="shortTitle" value="${am.shortTitle }" placeholder="请输入二级标题">
		                </div>
		            </div>
		            <div class="form-group">
		                <label class="control-label article_left">子站标题：</label>
		                <div class="col-sm-11 article_right">
		                    <input type="text" class="form-control" id="subTitle" name="subTitle" value="${am.subTitle }" placeholder="请输入子站标题">
		                </div>
		            </div>
		            <div class="form-group">
		                <label class="control-label article_left">封面：</label>
		                <div class="col-sm-11 article_right" style="height:150px;">
		                    <img id="forntImage" style="position:absolute;width:220px;heigth:150px;" src="http://www.mzwh.com:8081${am.imageUrl }">
							<input style="position:inherit;width:150px;margin-left:240px;margin-top:20px;" name="imgFile" id="imgFile" type="file" accept="image/*"/>
							<input type="button" style="position:inherit;width:50px;margin-left:240px;margin-top:20px;" value="上传" onclick="upload()"/>
		                </div>
		            </div>
		            <div class="form-group">
		                <label class="control-label article_left">文章作者：</label>
		                <div class="col-sm-11 article_right">
		                    <input type="text" class="form-control" id="author" name="author" value="${am.author }" placeholder="请输入文章作者">
		                </div>
		            </div>
		            <div class="form-group">
		                <label class="control-label article_left">文章来源：</label>
		                <div class="col-sm-11 article_right">
		                    <input type="text" class="form-control" id="source" name="source" value="${am.source }" placeholder="请输入文章来源">
		                </div>
		            </div>
		            <div class="form-group">
		                <label class="control-label article_left">文章栏目1：</label>
		                <div class="col-sm-11 article_right">
		                    <input type="text" class="form-control" onclick="selectColTree(1)" id="mainColName" name="mainColName" readonly="true" value="${cm1.name }" placeholder="请输入文章栏目1">
		                </div>
		            </div>
		            <div class="form-group">
		                <label class="control-label article_left">文章栏目2：</label>
		                <div class="col-sm-11 article_right">
		                    <input type="text" class="form-control" onclick="selectColTree(2)" id="slaveColName" name="slaveColName" readonly="true" value="${cm2.name }" placeholder="请输入文章栏目2">
		                </div>
		            </div>
		            <div class="form-group">
		                <label class="control-label article_left">关键字：</label>
		                <div class="col-sm-11 article_right">
		                    <input type="text" class="form-control" name="keyword" value="${am.keyword }" placeholder="请输入关键字">
		                </div>
		            </div>
		            <div class="form-group">
		                <label class="control-label article_left">发布时间：</label>
		                <div class="col-sm-3 article_right">
		                    <input type="text" style="position:absolute;width:100px;" class="form-control time-input" id="pubDate01" data-format="yyyy-MM-dd" name="pubDate01" value="${fn:substring(am.pubDate, 0, 10)}" placeholder="2000-01-01">
		                    <input type="text" style="position:inherit;width:100px;margin-left:120px" class="form-control time-input" id="pubDate02" data-format="HH:mm" data-type="time" name="pubDate02" value="${fn:substring(am.pubDate, 11, 16)}" placeholder="00:00">
		                </div>
		            </div>
		            <div class="form-group">
		                <label class="control-label article_left">文章正文：</label>
		                <div class="col-sm-11 article_right">
		                    <script id="editor" type="text/plain" style="width:100%;height:500px;"></script>
		                </div>
		            </div>
		            <div class="form-group">
		                <label class="control-label article_left">测试功能：</label>
		                <div id="btns" class="col-sm-11 article_right">
						    <div>
						        <button onclick="getAllHtml()">获得整个html的内容</button>
						        <button onclick="getContent()">获得内容</button>
						        <button onclick="setContent()">写入内容</button>
						        <button onclick="setContent(true)">追加内容</button>
						        <button onclick="getContentTxt()">获得纯文本</button>
						        <button onclick="getPlainTxt()">获得带格式的纯文本</button>
						        <button onclick="hasContent()">判断是否有内容</button>
						        <button onclick="setFocus()">使编辑器获得焦点</button>
						        <button onmousedown="isFocus(event)">编辑器是否获得焦点</button>
						        <button onmousedown="setblur(event)" >编辑器失去焦点</button>
						        <button onclick="getText()">获得当前选中的文本</button>
						        <button onclick="insertHtml()">插入给定的内容</button>
						        <button id="enable" onclick="setEnabled()">可以编辑</button>
						        <button onclick="setDisabled()">不可编辑</button>
						        <button onclick=" UE.getEditor('editor').setHide()">隐藏编辑器</button>
						        <button onclick=" UE.getEditor('editor').setShow()">显示编辑器</button>
						        <button onclick=" UE.getEditor('editor').setHeight(300)">设置高度为300默认关闭了自动长高</button>
						        <button onclick="getLocalData()" >获取草稿箱内容</button>
						        <button onclick="clearLocalData()" >清空草稿箱</button>
						    </div>
						</div>
					</div>
				</form>
			</div>
		</div>
		<script type="text/javascript">
		    //实例化编辑器
		    //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
		    var ue = UE.getEditor('editor');
		    var val_type = 1;
		    ue.ready(function() {
				//设置编辑器的内容"
				ue.setContent("${uedtor01}");  //date为动态传入的数据
			});
		    
		    /*
		    $(function(){
		    	console.log("${uedtor01}");
		    	UE.getEditor('editor').setContent("${uedtor01}");
		    	//UE.getEditor('editor').execCommand("insertHtml", "${uedtor01}");
		    });
		    */
		
		    function isFocus(e){
		        alert(UE.getEditor('editor').isFocus());
		        UE.dom.domUtils.preventDefault(e)
		    }
		    function setblur(e){
		        UE.getEditor('editor').blur();
		        UE.dom.domUtils.preventDefault(e)
		    }
		    function insertHtml() {
		        var value = prompt('插入html代码', '');
		        UE.getEditor('editor').execCommand('insertHtml', value)
		    }
		    function createEditor() {
		        enableBtn();
		        UE.getEditor('editor');
		    }
		    function getAllHtml() {
		        alert(ue.getAllHtml());
		    }
		    function getContent() {
		        var arr = [];
		        arr.push("使用editor.getContent()方法可以获得编辑器的内容");
		        arr.push("内容为：");
		        arr.push(UE.getEditor('editor').getContent());
		        alert(arr.join("\n"));
		    }
		    function getPlainTxt() {
		        var arr = [];
		        arr.push("使用editor.getPlainTxt()方法可以获得编辑器的带格式的纯文本内容");
		        arr.push("内容为：");
		        arr.push(UE.getEditor('editor').getPlainTxt());
		        alert(arr.join('\n'))
		    }
		    function setContent(isAppendTo) {
		        var arr = [];
		        arr.push("使用editor.setContent('欢迎使用ueditor')方法可以设置编辑器的内容");
		        ue.setContent('欢迎使用ueditor', isAppendTo);
		        //alert(arr.join("\n"));
		    }
		    function setDisabled() {
		        UE.getEditor('editor').setDisabled('fullscreen');
		        disableBtn("enable");
		    }
		
		    function setEnabled() {
		        UE.getEditor('editor').setEnabled();
		        enableBtn();
		    }
		
		    function getText() {
		        //当你点击按钮时编辑区域已经失去了焦点，如果直接用getText将不会得到内容，所以要在选回来，然后取得内容
		        var range = UE.getEditor('editor').selection.getRange();
		        range.select();
		        var txt = UE.getEditor('editor').selection.getText();
		        alert(txt)
		    }
		
		    function getContentTxt() {
		        var arr = [];
		        arr.push("使用editor.getContentTxt()方法可以获得编辑器的纯文本内容");
		        arr.push("编辑器的纯文本内容为：");
		        arr.push(UE.getEditor('editor').getContentTxt());
		        alert(arr.join("\n"));
		    }
		    function hasContent() {
		        var arr = [];
		        arr.push("使用editor.hasContents()方法判断编辑器里是否有内容");
		        arr.push("判断结果为：");
		        arr.push(UE.getEditor('editor').hasContents());
		        alert(arr.join("\n"));
		    }
		    function setFocus() {
		        UE.getEditor('editor').focus();
		    }
		    function deleteEditor() {
		        disableBtn();
		        UE.getEditor('editor').destroy();
		    }
		    function disableBtn(str) {
		        var div = document.getElementById('btns');
		        var btns = UE.dom.domUtils.getElementsByTagName(div, "button");
		        for (var i = 0, btn; btn = btns[i++];) {
		            if (btn.id == str) {
		                UE.dom.domUtils.removeAttributes(btn, ["disabled"]);
		            } else {
		                btn.setAttribute("disabled", "true");
		            }
		        }
		    }
		    function enableBtn() {
		        var div = document.getElementById('btns');
		        var btns = UE.dom.domUtils.getElementsByTagName(div, "button");
		        for (var i = 0, btn; btn = btns[i++];) {
		            UE.dom.domUtils.removeAttributes(btn, ["disabled"]);
		        }
		    }
		
		    function getLocalData () {
		        alert(UE.getEditor('editor').execCommand( "getlocaldata" ));
		    }
		
		    function clearLocalData () {
		        UE.getEditor('editor').execCommand( "clearlocaldata" );
		        alert("已清空草稿箱")
		    }
		    
		    /*菜单管理-新增-选择菜单树*/
	        function selectColTree(val) {
	        	var treeId = $("#mainColKey").val();
	        	val_type = val;
	        	if (val_type == 2) {
	        		treeId = $("#slaveColKey").val();
	        	}
	        	var menuId = treeId > 0 ? treeId : 1;
	        	var url = "${ctx}/article/column/" + menuId;
				var options = {
					title: '栏目选择',
					width: "380",
					height: "400",
					url: url,
					callBack: doSubmit
				};
				$.modal.openOptions(options);
			}
		    
		    function submitHandler() {
		    	var myForm = document.getElementById("form-article-add");
		    	var formData = new FormData(myForm);
		    	formData.append("digest", UE.getEditor('editor').getContentTxt());
		    	formData.append("htmlContent", UE.getEditor('editor').getContent());
		    	//console.log(formData.get('htmlContent'));
		    	$.ajax({
		    	    url: '${ctx}/article/save',
		    	    type: 'POST',
		    	    data: formData,                    // 上传formdata封装的数据
		    	    dataType: 'JSON',
		    	    cache: false,                      // 不缓存
		    	    processData: false,                // jQuery不要去处理发送的数据
		    	    contentType: false,                // jQuery不要去设置Content-Type请求头
		    	    success:function (result) {           //成功回调
	    	    		$.modal.msgSuccess(result.msg);
		    	    }
		    	});
		    }
		    
		    function doSubmit(index, layero){
				var body = layer.getChildFrame('body', index);
				if (val_type == 1) {
	   				$("#mainColKey").val(body.find('#treeId').val());
	   				$("#mainColName").val(body.find('#treeName').val());
				} else if (val_type == 2) {
					$("#slaveColKey").val(body.find('#treeId').val());
	   				$("#slaveColName").val(body.find('#treeName').val());
				}
	   			layer.close(index);
			}
		    
		    function upload() {
            	var formData = new FormData();
            	formData.append("imgFile",$('#imgFile')[0].files[0]);
            	
                $.ajax({
                    type : "POST",
                    url : "<%=request.getContextPath()%>/article/image/upload",
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