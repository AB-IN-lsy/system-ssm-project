<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
<base href="<%=basePath%>">
<meta charset="utf-8">
<link rel="shortcut icon" href="resources/img/logo.png">
<title>信息系统台模板</title>

<link rel="stylesheet" href="resources/plugin/layui/css/layui.css">
<link rel="stylesheet" href="resources/css/back.css">
<link rel="stylesheet"
	href="resources/plugin/font-awesome/css/font-awesome.min.css">

<!-- 留给别的页面去覆盖.让它们可以引入自已的css或 js -->
<rapid:block name="frame-header-style"></rapid:block>
<rapid:block name="frame-header-script"></rapid:block>

</head>
<body>
	<div class="layui-layout layui-layout-admin">
		<div class="layui-header">
			<div class="layui-logo">
				<a href="user/index" style="color: #009688;"> 博客后台 </a>
			</div>
			<!-- 头部区域（可配合layui已有的水平导航） -->
			<ul class="layui-nav layui-layout-left">
				<li class="layui-nav-item"><a href="/" target="_blank">前台</a></li>
				<li class="layui-nav-item"><a href="javascript:;">新建</a>
					<dl class="layui-nav-child">
						<dd>
							<a href="article/add">文章</a>
						</dd>
						<dd>
							<a href="page/add">页面</a>
						</dd>
						<dd>
							<a href="category/add">分类</a>
						</dd>
						<dd>
							<a href="notice/add">公告</a>
						</dd>
						<dd>
							<a href="link/add">链接</a>
						</dd>
					</dl></li>
			</ul>
			<ul class="layui-nav layui-layout-right">
				<li class="layui-nav-item">
				<a href="javascript:;"> 
					<img class="layui-nav-img" src="<%=basePath%>user/photo/${session_user.userId}">
					${session_user.userName} 
				</a>
					<dl class="layui-nav-child">
						<dd>
							<a href="user/profile">基本资料</a>
						</dd>
					</dl></li>
				<li class="layui-nav-item"><a href="user/logout" onclick="return confrim('确定要退出吗?')">退出</a></li>
			</ul>
		</div>

		<div class="layui-side layui-bg-black">
			<div class="layui-side-scroll">
				<!-- 左侧导航区域（可配合layui已有的垂直导航） -->
				<ul class="layui-nav layui-nav-tree" lay-filter="test">
					<li class="layui-nav-item layui-nav-itemed"><a class=""
						href="javascript:;">文章</a>
						<dl class="layui-nav-child">
							<dd>
								<a href="article">全部文章</a>
							</dd>
							<dd>
								<a href="article/add">写文章</a>
							</dd>
							<dd>
								<a href="category">全部分类</a>
							</dd>
							<dd>
								<a href="tag">全部标签</a>
							</dd>
						</dl></li>
					<li class="layui-nav-item"><a href="javascript:;">页面</a>
						<dl class="layui-nav-child">
							<dd>
								<a href="page">全部页面</a>
							</dd>
							<dd>
								<a href="page/add">添加页面</a>
							</dd>
						</dl></li>
					<li class="layui-nav-item"><a class="" href="javascript:;">
							链接 </a>
						<dl class="layui-nav-child">
							<dd>
								<a href="link">全部链接</a>
							</dd>
							<dd>
								<a href="link/add">添加链接</a>
							</dd>
						</dl></li>
					<li class="layui-nav-item"><a href="javascript:;">公告</a>
						<dl class="layui-nav-child">
							<dd>
								<a href="notice">全部公告</a>
							</dd>
							<dd>
								<a href="notice/add">添加公告</a>
							</dd>
						</dl></li>
					<li class="layui-nav-item"><a href="comment"> 评论 </a></li>
					<li class="layui-nav-item"><a class="" href="javascript:;">
							用户 </a>
						<dl class="layui-nav-child">
							<dd>
								<a href="user">全部用户</a>
							</dd>
							<dd>
								<a href="user/add">添加用户</a>
							</dd>
						</dl></li>
					<li class="layui-nav-item"><a href="javascript:;">设置</a>
						<dl class="layui-nav-child">
							<dd>
								<a href="menu">菜单</a>
							</dd>
							<dd>
								<a href="options">主要选项</a>
							</dd>
						</dl></li>
				</ul>
			</div>
		</div>

		<div class="layui-body">
			<div style="padding: 15px">
				<rapid:block name="frame-content">
					这是主体区内容,每个页面这里都各不相同
				</rapid:block>
			</div>
		</div>

		<div class="layui-footer">
			<!-- 底部固定区域 -->
			Copyright © 2022 AB-IN All Rights Reserved
			<a href="https://www.ab-in.cn/" target="_blank">个人网址</a> / 
			<a href="https://blog.ab-in.cn/" target="_blank">个人博客</a>
		</div>
	</div>

	<script src="resources/js/jquery.min.js"></script>
	<script src="resources/plugin/layui/layui.all.js"></script>
	<script src="resources/js/back.js"></script>

	<!-- 留给别的页面覆盖,让它们可以引入自已的js -->
	<rapid:block name="frame-footer-script"></rapid:block>

</body>
</html>