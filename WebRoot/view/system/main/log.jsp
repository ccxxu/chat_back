<%request.setAttribute("ctx", request.getContextPath()); %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	    <meta charset="utf-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	    <!--360浏览器优先以webkit内核解析-->
	    <link rel="shortcut icon" href="favicon.ico">
	    <link href="${ctx}/css/bootstrap.min.css" rel="stylesheet"/>
	    <link href="${ctx}/css/font-awesome.min.css" rel="stylesheet"/>
	    <link href="${ctx}/css/main/animate.min.css" rel="stylesheet"/>
	    <link href="${ctx}/css/main/style.min862f.css" rel="stylesheet"/>
	    <link href="${ctx}/css/chart/style.css" rel="stylesheet"/>
	    <script src="${ctx}/js/chart/Chart.js"></script>
	    <script src="${ctx}/js/jquery.min.js"></script>
	    <script src="${ctx}/js/bootstrap.min.js"></script>
	    <script src="${ctx}/js/layer.min.js"></script>
	    <script type="text/javascript" src="${ctx}/js/chart/jquery.dcjqaccordion.2.7.js"></script>
	    <script src="${ctx}/js/chart/jquery.scrollTo.min.js"></script>
  		<script src="${ctx}/js/chart/jquery.nicescroll.js" type="text/javascript"></script>
	    <script src="${ctx}/js/chart/common-scripts.js"></script>
	</head>
	<body>
		<div class="ibox float-e-margins">
                    <div class="ibox-content no-padding">
                        <div class="panel-body" style="padding:0px;">
                            <div class="panel-group" id="version">
                            <div class="panel panel-default">
								<div class="panel-heading">
								   <h5 class="panel-title">
									   <a data-toggle="collapse" data-parent="#version" href="#v41">v4.1.0</a><code class="pull-right">2019.10.22</code>
								   </h5>
								</div>
								<div id="v41" class="panel-collapse collapse in">
									<div class="panel-body">
									   <ol>
									        <li>支持多表格实例操作</li>
									        <li>浮动提示方法tooltip支持弹窗</li>
											<li>代码生成&字典数据支持模糊条件查询</li>
											<li>增加页签全屏方法</li>
											<li>增加清除表单验证错误信息方法</li>
											<li>支持iframe局部刷新页面</li>
											<li>支持在线切换主题</li>
											<li>修改图片预览设置的高宽参数颠倒问题</li>
											<li>操作日志新增解锁账户功能</li>
											<li>管理员用户&角色不允许操作</li>
											<li>去掉jsoup包调用自定义转义工具</li>
											<li>添加时间轴示例</li>
											<li>修复翻页记住选择时获取指定列值的问题</li>
											<li>代码生成sql脚本添加导出按钮</li>
											<li>添加表格父子视图示例</li>
											<li>添加表格行内编辑示例</li>
											<li>升级fastjson到最新版1.2.60 阻止漏洞攻击</li>
											<li>升级echarts到最新版4.2.1</li>
											<li>操作日志新增返回参数</li>
											<li>支持mybatis通配符扫描任意多个包</li>
											<li>权限验证多种情况处理</li>
											<li>修复树形类型的代码生成的部分必要属性无法显示</li>
											<li>修复非表格插件情况下重置出现异常</li>
											<li>修复富文本编辑器有序列表冲突</li>
											<li>代码生成表前缀配置支持多个</li>
											<li>修复自动去除表前缀配置无效问题</li>
											<li>菜单列表按钮数据可见不显示（权限标识控制）</li>
											<li>修复设置会话超时时间无效问题</li>
											<li>新增本地资源通用下载方法</li>
											<li>操作日志记录新增请求方式</li>
											<li>代码生成单选按钮属性重名修复</li>
											<li>优化select2下拉框宽度不会随浏览器改变</li>
											<li>修复代码生成树表异常</li>
											<li>其他细节优化</li>
										</ol>
									</div>
								</div>
							 </div>
                            <div class="panel panel-default">
								<div class="panel-heading">
								   <h5 class="panel-title">
									   <a data-toggle="collapse" data-parent="#version" href="#v40">v4.0.0</a><code class="pull-right">2019.08.08</code>
								   </h5>
								</div>
								<div id="v40" class="panel-collapse collapse">
									<div class="panel-body">
									   <ol>
									        <li>代码生成支持预览、编辑，保存方案</li>
									        <li>新增防止表单重复提交注解</li>
											<li>新增后端校验（和前端保持一致）</li>
											<li>新增同一个用户最大会话数控制</li>
											<li>Excel导出子对象支持多个字段</li>
											<li>定时任务支持静态调用和多参数</li>
											<li>定时任务增加分组条件查询</li>
											<li>字典类型增加任务分组数据</li>
											<li>新增表格是否首次加载数据</li>
											<li>新增parentTab选项卡可在同一页签打开</li>
											<li>多数据源支持类注解（允许继承父类的注解）</li>
											<li>部门及以下数据权限（调整为以下及所有子节点）</li>
											<li>新增角色数据权限配（仅本人数据权限）</li>
											<li>修改菜单权限显示问题</li>
											<li>上传文件修改路径及返回名称</li>
											<li>添加报表插件及示例</li>
											<li>添加首页统计模板</li>
											<li>添加表格拖拽示例</li>
											<li>添加卡片列表示例</li>
											<li>添加富文本编辑器示例</li>
											<li>添加表格动态增删改查示例</li>
											<li>添加用户页面岗位选择框提示</li>
											<li>点击菜单操作添加背景高亮显示</li>
											<li>表格树新增showSearch是否显示检索信息</li>
											<li>解决表格列设置sortName无效问题</li>
											<li>表格图片预览支持自定义设置宽高</li>
											<li>添加表格列浮动提示（单击文本复制）</li>
											<li>PC端收起菜单后支持浮动显示</li>
											<li>详细操作样式调整</li>
											<li>修改用户更新描述空串不更新问题</li>
											<li>导入修改为模板渲染</li>
											<li>修改菜单及部门排序规则</li>
											<li>角色导出数据范围表达式翻译</li>
											<li>添加summernote富文本字体大小</li>
											<li>优化表格底部下边框防重叠&汇总像素问题</li>
											<li>树表格支持属性多层级访问</li>
											<li>修复IE浏览器用户管理界面右侧留白问题</li>
											<li>重置按钮刷新表格</li>
											<li>重置密码更新用户缓存</li>
											<li>优化验证码属性参数</li>
											<li>支持数据监控配置用户名和密码</li>
											<li>文件上传修改按钮背景及加载动画</li>
											<li>支持配置一级菜单href跳转</li>
											<li>侧边栏添加一套浅色主题</li>
											<li>树表格添加回调函数（校验异常状态）</li>
											<li>用户个人中心适配手机端显示</li>
											<li>Excel支持设置导出类型&更换样式</li>
											<li>检查属性改变修改为克隆方式（防止热部署强转异常）</li>
											<li>其他细节优化</li>
										</ol>
									</div>
								</div>
							 </div>
                            <div class="panel panel-default">
								<div class="panel-heading">
								   <h5 class="panel-title">
									   <a data-toggle="collapse" data-parent="#version" href="#v34">v3.4.0</a><code class="pull-right">2019.06.03</code>
								   </h5>
								</div>
								<div id="v34" class="panel-collapse collapse">
									<div class="panel-body">
									   <ol>
									        <li>新增实例演示菜单及demo</li>
											<li>新增页签右键操作</li>
											<li>菜单管理新增打开方式</li>
											<li>新增点击某行触发的事件</li>
											<li>新增双击某行触发的事件</li>
											<li>新增单击某格触发的事件</li>
											<li>新增双击某格触发的事件</li>
											<li>新增是否启用显示细节视图</li>
											<li>支持上传任意格式文件</li>
											<li>修复角色权限注解失效问题</li>
											<li>左侧的菜单栏宽度调整</li>
											<li>新增响应完成后自定义回调函数</li>
											<li>支持前端及其他模块直接获取用户信息</li>
											<li>升级swagger到最新版2.9.2</li>
											<li>升级jquery.slimscroll到最新版1.3.8</li>
											<li>升级select2到最新版4.0.7</li>
											<li>新增角色配置本部门数据权限</li>
											<li>新增角色配置本部门及以下数据权限</li>
											<li>优化底部操作防止跳到页面顶端</li>
											<li>修改冻结列选框无效及样式问题</li>
											<li>修复部门四层级修改祖级无效问题</li>
											<li>更换开关切换按钮样式</li>
											<li>新增select2-bootstrap美化下拉框</li>
											<li>添加表格内图片预览方法</li>
											<li>修复权限校验失败跳转页面路径错误</li>
											<li>国际化资源文件调整</li>
											<li>通知公告布局调整</li>
											<li>删除页签操作功能</li>
											<li>表格树新增查询指定列值</li>
											<li>更改系统接口扫描方式及完善测试案例</li>
											<li>表格列浮动提示及字典回显默认去背景</li>
											<li>修复启用翻页记住前面的选择check没选中问题</li>
											<li>去除监控页面底部的广告</li>
											<li>日期控件功问题修复及data功能增强</li>
											<li>新增角色权限可见性（前端直接调用）</li>
											<li>新增获取当前登录用户方法（前端及子模块调用）</li>
											<li>修复热部署重启导致菜单丢失问题</li>
											<li>优化业务校验失败普通请求跳转页面</li>
											<li>操作日志新增状态条件查询</li>
											<li>操作类型支持多选条件查询</li>
											<li>通知公告防止滚动触底回弹优化</li>
											<li>其他细节优化</li>
										</ol>
									</div>
								</div>
							 </div>
                             <div class="panel panel-default">
								<div class="panel-heading">
								   <h5 class="panel-title">
									   <a data-toggle="collapse" data-parent="#version" href="#v33">v3.3.0</a><code class="pull-right">2019.04.01</code>
								   </h5>
								</div>
								<div id="v33" class="panel-collapse collapse">
									<div class="panel-body">
									   <ol>
											<li>新增线程池统一管理</li>
											<li>新增支持左右冻结列</li>
											<li>新增表格字符超长浮动提示</li>
											<li>升级datepicker拓展并汉化</li>
											<li>升级druid到最新版本v1.1.14</li>
											<li>修复个人头像为图片服务器跨域问题</li>
											<li>修改上传文件按日期存储</li>
											<li>新增表格客户端分页选项</li>
											<li>新增表格的高度参数</li>
											<li>新增表格销毁方法</li>
											<li>新增表格下拉按钮切换方法</li>
											<li>新增表格分页跳转到指定页码</li>
											<li>新增表格启用点击选中行参数</li>
											<li>修复表格数据重新加载未触发部分按钮禁用</li>
											<li>使用jsonview展示操作日志参数</li>
											<li>新增方法（addTab、editTab）</li>
											<li>修改用户管理界面为Tab打开方式</li>
											<li>表单验证代码优化</li>
											<li>修复@Excel注解 prompt 属性使用报错</li>
											<li>修复combo属性Excel兼容性问题</li>
											<li>新增@Excel导入导出支持父类字段</li>
											<li>修复关闭最后选项卡无法激活滚动问题</li>
											<li>增加日期控件显示类型及回显格式扩展选项</li>
											<li>修复定时任务执行失败后入库状态为成功状态</li>
											<li>支持定时任务并发开关控制</li>
											<li>优化权限校验失败普通请求跳转页面</li>
											<li>捕获线程池执行任务抛出的异常</li>
											<li>修复IE浏览器导出功能报错</li>
											<li>新增角色管理分配用户功能</li>
											<li>新增表格翻页记住前面的选择</li>
											<li>调整用户个人中心页面</li>
											<li>修复界面存在的一些安全问题</li>
											<li>其他细节优化</li>
										</ol>
									</div>
								</div>
							</div>
                            <div class="panel panel-default">
								<div class="panel-heading">
								   <h5 class="panel-title">
									   <a data-toggle="collapse" data-parent="#version" href="#v32">v3.2.0</a><code class="pull-right">2019.01.18</code>
								   </h5>
								</div>
								<div id="v32" class="panel-collapse collapse">
									<div class="panel-body">
									   <ol>
											<li>部门修改时不允许选择最后节点</li>
											<li>修复部门菜单排序字段无效</li>
											<li>修复光驱磁盘导致服务监控异常</li>
											<li>登录界面去除check插件</li>
											<li>验证码文本字符间距修正</li>
											<li>升级SpringBoot到最新版本2.1.1</li>
											<li>升级MYSQL驱动</li>
											<li>修正登录必填项位置偏移</li>
											<li>Session会话检查优化</li>
											<li>Excel注解支持多级获取</li>
											<li>新增序列号生成方法</li>
											<li>修复WAR部署tomcat退出线程异常</li>
											<li>全屏操作增加默认确认/关闭</li>
											<li>修复个人信息可能导致漏洞</li>
											<li>字典数据根据下拉选择新增类型</li>
											<li>升级Summernote到最新版本v0.8.11</li>
											<li>新增用户数据导入</li>
											<li>首页主题样式更换</li>
											<li>layer扩展主题更换</li>
											<li>用户管理移动端默认隐藏左侧布局</li>
											<li>详细信息弹出层显示在顶层</li>
											<li>表格支持切换状态（用户/角色/定时任务）</li>
											<li>Druid数据源支持配置继承</li>
											<li>修正部分iPhone手机端表格适配问题</li>
											<li>新增防止重复提交表单方法</li>
											<li>新增表格数据统计汇总方法</li>
											<li>支持富文本上传图片文件</li>
										</ol>
									</div>
								</div>
							</div>
                            <div class="panel panel-default">
								<div class="panel-heading">
								   <h5 class="panel-title">
									   <a data-toggle="collapse" data-parent="#version" href="#v31">v3.1.0</a><code class="pull-right">2018.12.03</code>
								   </h5>
								</div>
								<div id="v31" class="panel-collapse collapse">
									<div class="panel-body">
									   <ol>
											<li>新增内网不获取IP地址</li>
											<li>新增cron表达式有效校验</li>
											<li>定时任务新增详细信息</li>
											<li>定时任务默认策略修改（不触发立即执行）</li>
											<li>定时任务显示下一个执行周期</li>
											<li>支持前端任意日期格式处理</li>
											<li>上传头像删除多余提交按钮</li>
											<li>表格增加行间隔色配置项</li>
											<li>表格增加转义HTML字符串配置项</li>
											<li>表格增加显示/隐藏指定列</li>
											<li>代码生成优化</li>
											<li>操作日志参数格式化显示</li>
											<li>页签新增新增全屏显示</li>
											<li>新增一键打包部署</li>
											<li>Excel注解新增多个参数</li>
											<li>新增提交静默更新表格方法</li>
											<li>新增服务监控菜单</li>
										</ol>
									</div>
								</div>
							</div>
							<div class="panel panel-default">
								<div class="panel-heading">
								   <h5 class="panel-title">
									   <a data-toggle="collapse" data-parent="#version" href="#v30">v3.0.0</a><code class="pull-right">2018.10.08</code>
								   </h5>
								</div>
								<div id="v30" class="panel-collapse collapse">
									<div class="panel-body">
									   <ol>
											<li>升级poi到最新版3.17</li>
											<li>导出修改临时目录绝对路径</li>
											<li>升级laydate到最新版5.0.9</li>
											<li>升级SpringBoot到最新版本2.0.5</li>
											<li>优化开始/结束时间校验限制</li>
											<li>重置密码参数表中获取默认值</li>
											<li>修复头像修改显示问题</li>
											<li>新增数据权限过滤注解</li>
											<li>新增表格检索折叠按钮</li>
											<li>新增清空（登录、操作、调度）日志</li>
											<li>固定按钮位置（提交/关闭）</li>
											<li>部门/菜单支持（展开/折叠）</li>
											<li>部分细节调整优化</li>
											<li>项目采用分模块</li>
										</ol>
									</div>
								</div>
							</div>
							<div class="panel panel-default">
								<div class="panel-heading">
								   <h5 class="panel-title">
									   <a data-toggle="collapse" data-parent="#version" href="#v24">v2.4.0</a><code class="pull-right">2018.09.03</code>
								   </h5>
								</div>
								<div id="v24" class="panel-collapse collapse">
									<div class="panel-body">
									   <ol>
											<li>支持部门多级查询</li>
											<li>修复菜单状态查询无效</li>
											<li>支持IP地址开关</li>
											<li>支持XSS开关</li>
											<li>记录日志异步处理</li>
											<li>字典回显样式更改为下拉框</li>
											<li>菜单类型必填校验</li>
											<li>修复在线用户排序报错</li>
											<li>增加重置按钮</li>
											<li>支持注解导入数据</li>
											<li>支持弹层外区域关闭</li>
											<li>备注更换为文本区域</li>
											<li>新增角色逻辑删除</li>
											<li>新增部门逻辑删除</li>
											<li>支持部门数据权限</li>
											<li>管理员默认拥有所有授权</li>
											<li>字典数据采用分页</li>
											<li>部分细节调整优化</li>
										</ol>
									</div>
								</div>
							</div>
                            <div class="panel panel-default">
									<div class="panel-heading">
									   <h5 class="panel-title">
										   <a data-toggle="collapse" data-parent="#version" href="#v23">v2.3.0</a><code class="pull-right">2018.08.06</code>
									   </h5>
									</div>
									<div id="v23" class="panel-collapse collapse">
										<div class="panel-body">
										   <ol>
										        <li>支持表格不分页开关控制</li>
										        <li>修改字典类型同步修改字典数据</li>
										        <li>代码生成新增修改后缀处理</li>
										        <li>代码生成新增实体toString</li>
										        <li>代码生成非字符串去除!=''</li>
												<li>导出数据前加载遮罩层</li>
												<li>部门删除校验条件修改</li>
												<li>搜索查询下载优化</li>
												<li>手机打开弹出层自适应</li>
												<li>角色岗位禁用显示置灰</li>
												<li>角色禁用不显示菜单</li>
												<li>新增导出权限</li>
												<li>角色权限唯一校验</li>
												<li>岗位名称编码唯一校验</li>
                                                <li>TreeTable优化</li>
                                                <li>支持多数据源</li>
												<li>其他细节优化</li>
											</ol>
										</div>
									</div>
								</div>
                                <div class="panel panel-default">
									<div class="panel-heading">
									   <h5 class="panel-title">
										   <a data-toggle="collapse" data-parent="#version" href="#v22">v2.2.0</a><code class="pull-right">2018.07.23</code>
									   </h5>
									</div>
									<div id="v22" class="panel-collapse collapse">
										<div class="panel-body">
										   <ol>
										        <li>修复批量生成代码异常问题</li>
										        <li>修复定时器保存失败问题</li>
										        <li>修复热部署转换问题</li>
												<li>支持查询菜单管理，部门管理</li>
												<li>大多数功能支持时间查询</li>
												<li>自定义导出注解自动匹配column</li>
												<li>新增任务执行策略</li>
												<li>操作详细动态显示类型</li>
												<li>支持动态回显字典数据</li>
												<li>后台代码优化调整</li>
												<li>其他细节优化</li>
											</ol>
										</div>
									</div>
								</div>
                                <div class="panel panel-default">
									<div class="panel-heading">
									   <h5 class="panel-title">
										   <a data-toggle="collapse" data-parent="#version" href="#v21">v2.1.0</a><code class="pull-right">2018.07.10</code>
									   </h5>
									</div>
									<div id="v21" class="panel-collapse collapse">
										<div class="panel-body">
										   <ol>
										        <li>新增登陆超时提醒</li>
										        <li>修复定时器热部署转换问题</li>
										        <li>修复登录验证码校验无效问题</li>
												<li>定时任务新增立即执行一次</li>
												<li>存在字典数据不允许删除字典</li>
												<li>字典数据支持按名称查询</li>
												<li>代码生成增加日志注解&表格优化</li>
												<li>修复用户逻辑删除后能登录问题</li>
												<li>表格支持多字段动态排序</li>
												<li>支持三级菜单显示</li>
												<li>新增ry.sh启动程序脚本</li>
												<li>其他细节优化</li>
											</ol>
										</div>
									</div>
								</div>
                            	<div class="panel panel-default">
									<div class="panel-heading">
									   <h5 class="panel-title">
										   <a data-toggle="collapse" data-parent="#version" href="#v20">v2.0.0</a><code class="pull-right">2018.07.02</code>
									   </h5>
									</div>
									<div id="v20" class="panel-collapse collapse">
										<div class="panel-body">
										   <ol>
										        <li>升级SpringBoot到最新版本2.0.3</li>
										        <li>新增公告管理</li>
												<li>表单校验示提体验优化</li>
												<li>前端通用方法封装调整</li>
												<li>前端去除js文件，合并到html</li>
												<li>操作加载遮罩层</li>
												<li>支持全屏模式操作</li>
												<li>支持注解导出数据</li>
												<li>系统支持多查询&下载</li>
												<li>系统样式调整</li>
											</ol>
										</div>
									</div>
								</div>
                                <div class="panel panel-default">
									<div class="panel-heading">
									   <h5 class="panel-title">
										   <a data-toggle="collapse" data-parent="#version" href="#v16">v1.1.6</a><code class="pull-right">2018.06.04</code>
									   </h5>
									</div>
									<div id="v16" class="panel-collapse collapse">
										<div class="panel-body">
										   <ol>
												<li>新增用户列表部门列</li>
												<li>新增登录地点</li>
												<li>新增swagger</li>
												<li>修复排序数字校验</li>
												<li>优化头像上传文件类型限定为图片</li>
												<li>新增XSS过滤</li>
												<li>新增热部署提高开发效率</li>
												<li>修复treegrid居中无效</li>
												<li>角色多条件查询</li>
											</ol>
										</div>
									</div>
								</div>
                            	<div class="panel panel-default">
									<div class="panel-heading">
									   <h5 class="panel-title">
										   <a data-toggle="collapse" data-parent="#version" href="#v15">v1.1.5</a><code class="pull-right">2018.05.28</code>
									   </h5>
									</div>
									<div id="v15" class="panel-collapse collapse">
										<div class="panel-body">
										   <ol>
												<li>优化登录失败刷新验证码</li>
												<li>新增用户登陆地址时间</li>
												<li>修复ajax超时退出问题</li>
												<li>新增html调用数据字典</li>
												<li>调整系统部分样式</li>
												<li>新增用户逻辑删除</li>
												<li>新增管理员不允许删除修改</li>
												<li>升级bootstrapTable到最新版本1.12.1</li>
												<li>升级layer到最新版本3.1.1</li>
											</ol>
										</div>
									</div>
								</div>
							    <div class="panel panel-default">
									<div class="panel-heading">
									   <h5 class="panel-title">
										   <a data-toggle="collapse" data-parent="#version" href="#v14">v1.1.4</a><code class="pull-right">2018.05.20</code>
									   </h5>
									</div>
									<div id="v14" class="panel-collapse collapse">
										<div class="panel-body">
										   <ol>
												<li>新增参数管理</li>
												<li>修复头像上传bug</li>
												<li>手机邮箱唯一校验</li>
												<li>支持手机邮箱登录</li>
												<li>代码生成优化</li>
												<li>支持模糊查询</li>
												<li>支持切换主题皮肤</li>
												<li>修改权限即时生效</li>
												<li>修复页签Tab关闭问题</li>
											</ol>
										</div>
									</div>
								</div>
								<div class="panel panel-default">
									<div class="panel-heading">
									   <h5 class="panel-title">
										   <a data-toggle="collapse" data-parent="#version" href="#v13">v1.1.3</a><code class="pull-right">2018.05.14</code>
									   </h5>
									</div>
									<div id="v13" class="panel-collapse collapse">
										<div class="panel-body">
										   <ol>
												<li>新增验证码（数组计算、字符验证）</li>
												<li>新增cookie记住我</li>
												<li>新增头像上传</li>
												<li>用户名密码长度限制</li>
												<li>通用字段提取</li>
												<li>支持自定义条件查询</li>
												<li>部门名称必填、时间格式调整</li>
												<li>其他细节优化</li>
											</ol>
										</div>
									</div>
								</div>
								<div class="panel panel-default">
									<div class="panel-heading">
                                        <h5 class="panel-title">
											<a data-toggle="collapse" data-parent="#version" href="#v12">v1.1.2</a><code class="pull-right">2018.05.07</code>
										</h5>
                                    </div>
                                    <div id="v12" class="panel-collapse collapse">
                                        <div class="panel-body">
                                            <ol>
                                            	<li>新增个人信息修改</li>
												<li>菜单存在子菜单不允许删除</li>
												<li>菜单分配角色不允许删除</li>
												<li>角色分配人员不允许删除</li>
												<li>岗位使用后不允许删除</li>
												<li>保证用户的数据完整性加入事物</li>
												<li>新增环境使用手册、数据建模</li>
												<li>Thymeleaf升级到3.0</li>
												<li>支持非ROOT部署</li>
                                            </ol>
                                        </div>
                                    </div>
                                </div>
                                <div class="panel panel-default">
									<div class="panel-heading">
                                        <h5 class="panel-title">
											<a data-toggle="collapse" data-parent="#version" href="#v11">v1.1.1</a><code class="pull-right">2018.04.23</code>
										</h5>
                                    </div>
                                    <div id="v11" class="panel-collapse collapse">
                                        <div class="panel-body">
                                            <ol>
                                            	<li>新增表单构建器</li>
												<li>代码生成优化</li>
												<li>支持新增主部门</li>
												<li>支持选择上级部门、上级菜单</li>
												<li>新增字典管理单条删除</li>
												<li>优化一些其他细节</li>
                                            </ol>
                                        </div>
                                    </div>
                                </div>
                                <div class="panel panel-default">
									<div class="panel-heading">
                                        <h5 class="panel-title">
											<a data-toggle="collapse" data-parent="#version" href="#v10">v1.1.0</a><code class="pull-right">2018.04.20</code>
										</h5>
                                    </div>
                                    <div id="v10" class="panel-collapse collapse">
                                        <div class="panel-body">
                                            <ol>
                                            	<li>支持密码盐</li>
												<li>支持新增主目录</li>
												<li>支持批量生成代码</li>
												<li>支持表格导出(csv、txt、doc、excel)</li>
												<li>自动适应宽高模式窗体</li>
												<li>重复校验(角色名、菜单名、部门名)</li>
												<li>优化一些其他细节</li>
                                            </ol>
                                        </div>
                                    </div>
                                </div>
                                <div class="panel panel-default">
									<div class="panel-heading">
                                        <h5 class="panel-title">
											<a data-toggle="collapse" data-parent="#version" href="#v09">v1.0.9</a><code class="pull-right">2018.04.14</code>
										</h5>
                                    </div>
                                    <div id="v09" class="panel-collapse collapse">
                                        <div class="panel-body">
                                            <ol>
                                            	<li>新增代码生成(生成包括 java、html、js、xml、sql)</li>
												<li>新增按钮权限控制隐藏</li>
                                            </ol>
                                        </div>
                                    </div>
                                </div>
								<div class="panel panel-default">
									<div class="panel-heading">
                                        <h5 class="panel-title">
											<a data-toggle="collapse" data-parent="#version" href="#v08">v1.0.8</a><code class="pull-right">2018.04.08</code>
										</h5>
                                    </div>
                                    <div id="v08" class="panel-collapse collapse">
                                        <div class="panel-body">
                                            <ol>
                                            	<li>新增定时任务(新增、修改、删除、查询、启动/暂停)</li>
												<li>新增调度日志(查询、删除)</li>
                                            </ol>
                                        </div>
                                    </div>
                                </div>
                            	<div class="panel panel-default">
									<div class="panel-heading">
                                        <h5 class="panel-title">
											<a data-toggle="collapse" data-parent="#version" href="#v07">v1.0.7</a><code class="pull-right">2018.04.04</code>
										</h5>
                                    </div>
                                    <div id="v07" class="panel-collapse collapse">
                                        <div class="panel-body">
                                            <ol>
                                            	<li>新增岗位管理(新增、修改、删除、查询)</li>
												<li>优化用户管理，菜单管理部分细节</li>
                                            </ol>
                                        </div>
                                    </div>
                                </div>
								<div class="panel panel-default">
									<div class="panel-heading">
                                        <h5 class="panel-title">
											<a data-toggle="collapse" data-parent="#version" href="#v06">v1.0.6</a><code class="pull-right">2018.03.15</code>
										</h5>
                                    </div>
                                    <div id="v06" class="panel-collapse collapse">
                                        <div class="panel-body">
                                            <ol>
                                            	<li>新增字典管理(新增、删除、修改、查询、数据选择)</li>
												<li>新增用户密码重置</li>
												<li>优化一些其他细节</li>
                                            </ol>
                                        </div>
                                    </div>
                                </div>
								<div class="panel panel-default">
									<div class="panel-heading">
                                        <h5 class="panel-title">
											<a data-toggle="collapse" data-parent="#version" href="#v05">v1.0.5</a><code class="pull-right">2018.03.12</code>
										</h5>
                                    </div>
                                    <div id="v05" class="panel-collapse collapse">
                                        <div class="panel-body">
                                            <ol>
                                            	<li>新增菜单管理(新增、删除、修改、查询、图标选择)</li>
												<li>部门管理优化(添加责任人、联系电话、邮箱、修改者)</li>
                                            </ol>
                                        </div>
                                    </div>
                                </div>
								<div class="panel panel-default">
									<div class="panel-heading">
                                        <h5 class="panel-title">
											<a data-toggle="collapse" data-parent="#version" href="#v04">v1.0.4</a><code class="pull-right">2018.03.11</code>
										</h5>
                                    </div>
                                    <div id="v04" class="panel-collapse collapse">
                                        <div class="panel-body">
                                            <ol>
                                            	<li>新增角色管理(新增、删除、修改、查询、菜单选择)</li>
                                            </ol>
                                        </div>
                                    </div>
                                </div>
								<div class="panel panel-default">
									<div class="panel-heading">
                                        <h5 class="panel-title">
											<a data-toggle="collapse" data-parent="#version" href="#v03">v1.0.3</a><code class="pull-right">2018.03.08</code>
										</h5>
                                    </div>
                                    <div id="v03" class="panel-collapse collapse">
                                        <div class="panel-body">
                                            <ol>
                                            	<li>新增用户管理(新增、删除、修改、查询、部门选择)</li>
                                            </ol>
                                        </div>
                                    </div>
                                </div>
                            	<div class="panel panel-default">
									<div class="panel-heading">
                                        <h5 class="panel-title">
											<a data-toggle="collapse" data-parent="#version" href="#v02">v1.0.2</a><code class="pull-right">2018.03.04</code>
										</h5>
                                    </div>
                                    <div id="v02" class="panel-collapse collapse">
                                        <div class="panel-body">
                                            <ol>
                                            	<li>新增部门管理 (新增、删除、修改、查询)</li>
                                            </ol>
                                        </div>
                                    </div>
                                </div>
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h5 class="panel-title">
											<a data-toggle="collapse" data-parent="#version" href="#v01">v1.0.1</a><code class="pull-right">2018.03.03</code>
										</h5>
                                    </div>
                                    <div id="v01" class="panel-collapse collapse">
                                        <div class="panel-body">
                                            <ol>
                                            	<li>新增在线用户 (批量强退、单条强退、查询)</li>
                                                <li>新增登录日志 (批量删除、查询)</li>
												<li>新增操作日志 (批量删除、查询、详细)</li>
												<li>新增数据监控 (监控DB池连接和SQL的执行)</li>
                                            </ol>
                                        </div>
                                    </div>
                                </div>
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h4 class="panel-title">
                                            <a data-toggle="collapse" data-parent="#version" href="#v00">v1.0.0</a><code class="pull-right">2018.03.01</code>
                                        </h4>
                                    </div>
                                    <div id="v00" class="panel-collapse collapse">
                                        <div class="panel-body">
                                            <ol>
                                                <li>后台管理系统正式发布。</li>
                                            </ol>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
	</body>
</html>