<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid"%>

<rapid:override name="frame-header-style">
	<link rel="stylesheet" type="text/css"
		href="resources/css/index-page.css"></link>
</rapid:override>

<rapid:override name="frame-content">
	这是index.jsp中要重写的内容,文章列表,评论列表,草稿功能...
</rapid:override>

<%@ include file="framework.jsp"%>