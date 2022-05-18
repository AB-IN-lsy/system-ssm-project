<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath(); 
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
	  <head>
		<base href="<%=basePath%>">
		
		<title></title>
		
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">    
	
		<!--
		<link rel="stylesheet" type="text/css" href="styles.css">
		-->
	
	  </head>
	  
	  <body>
	  	<img src="resources/img/AB-IN.jpg">
		<h1>${msg}</h1>
	  </body>
</html>