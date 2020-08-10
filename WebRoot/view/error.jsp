<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/jsp/common/importTag.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>error</title>
</head>
<body>
	<div style="width:252px;margin:50px auto">
		<img src="<%=request.getContextPath() %>/img/error.jpg" />
		<br/>
		<!-- 
		操作异常  ${ex.message }
		 -->
	</div>
</body>
</html>
