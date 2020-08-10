<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>403</title>
</head>
<body>
  <h2>没有权限使用该页面</h2>
  <h3><a href="<%=request.getContextPath() %>/j_spring_security_logout" target="_top">退出</a></h3>
</body>
</html>