<%request.setAttribute("ctx", request.getContextPath()); %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	    <meta charset="utf-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	    <!--360浏览器优先以webkit内核解析-->
	    <link rel="shortcut icon" href="favicon.ico">
	    <link href="${ctx}/css/chart/style.css" rel="stylesheet"/>
	    <link href="${ctx}/css/bootstrap.min.css" rel="stylesheet"/>
	    <link href="${ctx}/css/font-awesome.min.css" rel="stylesheet"/>
	    <link href="${ctx}/css/main/animate.min.css" rel="stylesheet"/>
	    <link href="${ctx}/css/main/style.min862f.css" rel="stylesheet"/>
	    
	    <script src="${ctx}/js/chart/Chart.js"></script>
	    <script src="${ctx}/js/jquery.min.js"></script>
	    <script src="${ctx}/js/bootstrap.min.js"></script>
	    <script src="${ctx}/js/layer.min.js"></script>
	    <script type="text/javascript" src="${ctx}/js/chart/jquery.dcjqaccordion.2.7.js"></script>
	    <script src="${ctx}/js/chart/jquery.scrollTo.min.js"></script>
  		<script src="${ctx}/js/chart/jquery.nicescroll.js" type="text/javascript"></script>
	    <script src="${ctx}/js/chart/common-scripts.js"></script>
	    <script type="text/javascript">
	    	
		    $(function(){
		    	// custom bar chart
		    	if ($(".custom-bar-chart")) {
		    		console.log("1232132=");
		    		$(".bar").each(function() {
		    			console.log("qwe");
		    			var i = $(this).find(".value").html();
		    			$(this).find(".value").html("");
		    			$(this).find(".value").animate({
		    				height: i
		    			}, 2000);
		    		});
		    	}
		    });
	    </script>
	</head>
	<body class="gray-bg">
    	<%-- 
    	<div class="row  border-bottom white-bg dashboard-header">
       	 	<div class="main-chart" style="width:100%;padding: 20px;">
	            <!--CUSTOM CHART START -->
	            <div class="border-head">
	              	<h3>网站用户访问量</h3>
	            </div>
	            <div class="custom-bar-chart">
	              	<ul class="y-axis">
		                <li><span>10000</span></li>
		                <li><span>8000</span></li>
		                <li><span>6000</span></li>
		                <li><span>4000</span></li>
		                <li><span>2000</span></li>
		                <li><span>0</span></li>
	              	</ul>
	              	<c:forEach items="${visitlist }" var="item" varStatus="sta">
	              	<div class="bar" <c:if test="${sta.index==0 }">style="margin-left:4%;"</c:if>>
		                <div class="title">${item.month }</div>
		                <div class="value tooltips" data-original-title="8500" data-toggle="tooltip" data-placement="top">${item.cnt }%</div>
	              	</div>
	              	</c:forEach>
	            </div>
			</div>
    	</div>
    	<div class="wrapper wrapper-content" style="padding:0px 20px;">
	        <div class="row">
	            <div class="col-sm-4">
	                <div class="green-panel pn">
                  		<div class="green-header">
                    		<h5>CPU</h5>
                  		</div>
                  		<canvas id="CPU" height="120" width="120"></canvas>
                  		<script>
                    		var doughnutData = [{
		                        value: ${sysinfo.cpuCombine},
		                        color: "#2b2b2b"
                      		},
                      		{
                        		value: ${sysinfo.cpuIdle},
                        		color: "#fffffd"
                      		}];
                    		var myDoughnut = new Chart(document.getElementById("CPU").getContext("2d")).Doughnut(doughnutData);
                  		</script>
                  		<h3><fmt:formatNumber type="number" value="${sysinfo.cpuCombine*100 }" pattern="#.00"/>% USED</h3>
                	</div>
	            </div>
	            <div class="col-sm-4">
	                <div class="grey-panel pn donut-chart">
                  		<div class="grey-header">
                    		<h5>内存</h5>
                  		</div>
                  		<canvas id="memory" height="120" width="120"></canvas>
	                  	<script>
		                    var doughnutData = [{
		                        value: ${sysinfo.memCombine},
		                        color: "#FF6B6B"
		                      },
		                      {
		                        value: ${1-sysinfo.memCombine},
		                        color: "#fdfdfd"
		                      }
		                    ];
		                    var myDoughnut = new Chart(document.getElementById("memory").getContext("2d")).Doughnut(doughnutData);
	                  	</script>
                  		<div class="row">
	                    	<div class="col-sm-6 col-xs-6 goleft">
	                      		<p>Total:${sysinfo.memTotal }M<br/>Used:${sysinfo.memUsed }M</p>
	                    	</div>
	                    	<div class="col-sm-6 col-xs-6">
	                      		<h2><fmt:formatNumber type="number" value="${sysinfo.memCombine*100 }" pattern="#.00"/>%</h2>
	                    	</div>
	                  	</div>
                	</div>
	            </div>
	            <div class="col-sm-4">
	                <div class="grey-panel pn donut-chart">
                  		<div class="grey-header">
                    		<h5>天一i·生活平台</h5>
                  		</div>
                  		<canvas id="i_love_life" height="120" width="120"></canvas>
	                  	<script>
		                    var doughnutData = [{
		                        value: ${sysinfo.cloudCombine},
		                        color: "#FF6B6B"
		                      },
		                      {
		                        value: ${1-sysinfo.cloudCombine},
		                        color: "#fdfdfd"
		                      }
		                    ];
		                    var myDoughnut = new Chart(document.getElementById("i_love_life").getContext("2d")).Doughnut(doughnutData);
	                  	</script>
                  		<div class="row">
	                    	<div class="col-sm-6 col-xs-6 goleft">
	                      		<p>Total:${sysinfo.cloudTotal }<br/>Used:${sysinfo.cloudUsed }</p>
	                    	</div>
	                    	<div class="col-sm-6 col-xs-6">
	                      		<h2><fmt:formatNumber type="number" value="${sysinfo.cloudCombine*100 }" pattern="#.00"/>%</h2>
	                    	</div>
	                  	</div>
                	</div>
	            </div>
	            
	        </div>
	    </div>
	     --%>
	    <div class="wrapper wrapper-content" style="padding:0px 20px;">
	        <div class="row">
	            <div class="col-sm-4">
	                <div class="ibox float-e-margins">
	                    <div class="ibox-title">
	                        <h5>小程序管理平台</h5>
	                    </div>
	                    <div class="ibox-content">
	                        <p><i class="fa fa-send-o"></i> 天一i睡后收入小程序：<a href="#" target="_blank">ccx-i</a>
	                        </p>
	                        <p><i class="fa fa-send-o"></i> 商务微新闻公众号&小程序： <a href="#" target="_blank">ccx-mofcom</a>
	                        </p>
	                        <p><i class="fa fa-send-o"></i> 暗云小程序： <a href="#" target="_blank">ccx-iv</a>
	                        </p>
	                    </div>
	                    <div class="ibox-title">
	                        <h5>网站管理后台</h5>
	                    </div>
	                    <div class="ibox-content">
	                        <p><i class="fa fa-send-o"></i> 基础模块：<a href="#" target="_blank">nw-common</a>
	                        </p>
	                        <p><i class="fa fa-send-o"></i> 系统管理模块：<s>nw-dev</s>  <a href="#" target="_blank">nw-admin</a>
	                        </p>
	                        <p><i class="fa fa-send-o"></i> 注册中心模块：<a href="#">nw-server</a>
	                        </p>
	                        <p><i class="fa fa-send-o"></i> 授权登录模块：<a href="#">nw-oauth2</a>
	                        </p>
							<p><i class="fa fa-send-o"></i> 网关路由信息：<a href="#">nw-gateway</a>
							</p>
	                    </div>
	                </div>
	            </div>
	            <div class="col-sm-4">
	                <div class="ibox float-e-margins">
	                    <div class="ibox-title">
	                        <h5>暗云数据综合管理平台</h5>
	                    </div>
	                    <div class="ibox-content" style="padding:15px 20px 16px;">
	                        <p style="text-indent:2em;">
	                        	他山之石，可以攻玉。本座半路出家，没有美工工底，想做一款后台管理系统，于是集众多优秀开源项目，自成一家，2016年5月初步开发完成此系统。
	                        	后经多次改进，参考诸多网站模板，就成现在的后台数据管理系统，取名为“暗云”。暗云二字取自于当年流行的计算机病毒暗云I的变异版本暗云II,暗云III等系列木马病毒。
	                        	在对他们进行逆向分析的同时，也带来了新的编程思想。所有前端后台代码封装过后十分精简易上手，出错概率低。
	                        </p>
	                        <p style="text-indent:2em;">
	                        	暗云数据综合管理平台自从2016年上线以后，开始支持一直作为测试自我学习的平台。在持续不断改进的同时，不断融入新的技术。提供应用安全性，防止DDOS攻击，跨域攻击。
	                        	在2017年底陆续开始作为数据平台为生产系统服务。逐步提高代码可复用程度，可以以此为基础平台，进行二次开发。
	                        </p>
				            <p>
				                <b>当前版本：</b><span>v4.1.0</span>
				            </p>
			            	<br>
				            <p style="margin:0 0 9px;">
				                <a class="btn btn-success btn-outline" href="https://gitee.com/" target="_blank">
				                    <i class="fa fa-cloud"> </i> 访问码云
				                </a>
				                <a class="btn btn-white btn-bitbucket" href="https://github.com/" target="_blank">
				                    <i class="fa fa-home"></i> 访问主页
				                </a>
				            </p>
	                    </div>
	                </div>
	            </div>
	            <div class="col-sm-4">
	                <div class="ibox float-e-margins">
	                    <div class="ibox-title">
	                        <h5>网站链接</h5>
	                    </div>
	                    <div class="ibox-content" style="padding:15px 20px 18px;">
	                        <blockquote class="text-warning" style="font-size:14px">
							Get started with GitHub Enterprise(饭桶中心)
			                <br><a target="_blank" href="https://github.com/">https://github.com/</a><br>
							码云Gitee
			                <br><a target="_blank" href="https://gitee.com/">https://gitee.com/</a><br>
							Knowledge Software LTD
			                <br><a target="_blank" href="http://www.knosof.co.uk/">http://www.knosof.co.uk/</a><br>
							beaglebone-blue-and-black
			                <br><a target="_blank" href="https://www.linux.org/">https://www.linux.org/</a><br>
							微信官方文档
			                <br><a target="_blank" href="https://developers.weixin.qq.com/miniprogram/dev/framework/">https://developers.weixin.qq.com/miniprogram/dev/framework/</a><br>
			                CSDN-专业IT技术社区
			                <br><a target="_blank" href="https://www.csdn.net/">https://www.csdn.net/</a>
			                <h4 class="text-danger">乱七八糟</h4>
			            	</blockquote>
	                    </div>
	                </div>
	            </div>
	        </div>
	    </div>
	</body>
</html>
