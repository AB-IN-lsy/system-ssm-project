<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<rapid:override name="frame-content">
	<blockquote class="layui-elem-quote">
		<span class="layui-breadcrumb" lay-separator="/"> <a
			href="/admin">首页</a> <a><cite>文章列表</cite></a>
		</span>
	</blockquote>

	<div class="layui-tab layui-tab-card">
		<form id="articleForm" method="post">
			<input type="hidden" name="currentUrl" id="currentUrl" value="">
			<table class="layui-table">
				<colgroup>
					<col width="300">
					<col width="150">
					<col width="100">
					<col width="150">
					<col width="100">
					<col width="50">
				</colgroup>
				<thead>
					<tr>
						<th>标题</th>
						<th>所属分类</th>
						<th>状态</th>
						<th>发布时间</th>
						<th>操作</th>
						<th>id</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="a" items="${pageInfo.list}">
						<tr>
							<td><a href="/article/${a.articleId }" target="_blank">${a.articleTitle }</a></td>
							<td>
								<c:if test="${empty a.categoryList }">
									<a target="_blank">未分类</a> &nbsp;
									<a target="_blank">未分类</a> &nbsp;
								</c:if>
								<c:if test="${!empty a.categoryList }">
									<c:forEach var="c" items="${a.categoryList}">
											<a href="/category/${c.categoryId }" target="_blank">${c.categoryName }</a> &nbsp;
									</c:forEach>
								</c:if>
							</td>
							<td> <a href="/article?status=1"> 
								<c:if test="${a.articleStatus==1 }">
									<span style="color: #5FB878;">已发布</span>
								</c:if> 
								<c:if test="${a.articleStatus==0 }">
									<span style="color: red">草稿</span>
								</c:if>
								</a>
							</td>
							<td><fmt:formatDate value="${a.articleCreateTime}"
									pattern="yyyy-MM-dd HH:mm:ss" /></td>
							<td><a href="/article/edit/${a.articleId }"
								class="layui-btn layui-btn-mini">编辑</a> <a
								href="javascript:void(0)"
								onclick="deleteArticle(${a.articleId })"
								class="layui-btn layui-btn-danger layui-btn-mini">删除</a></td>
							<td>${a.articleId }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</form>
		<%@ include file="../page.jsp" %>
	</div>
</rapid:override>

<%@ include file="../framework.jsp"%>