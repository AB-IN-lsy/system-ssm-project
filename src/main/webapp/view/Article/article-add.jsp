<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<rapid:override name="frame-content">
	<blockquote class="layui-elem-quote">
		<span class="layui-breadcrumb" lay-separator="/"> <a
			href="/admin">首页</a> <a href="/admin/article">文章列表</a> <a><cite>添加文章</cite></a>
		</span>
	</blockquote>

	<form class="layui-form" method="post" id="myForm" 	action="article/add">

		<div class="layui-form-item">
			<label class="layui-form-label">标题 <span
				style="color: #FF5722;">*</span></label>
			<div class="layui-input-block">
				<input type="text" name="articleTitle" lay-verify="title"
					id="title" autocomplete="off" placeholder="请输入标题"
					class="layui-input">
			</div>
		</div>
		
					

		<div class="layui-form-item layui-form-text">
			<label class="layui-form-label">内容 <span
				style="color: #FF5722;">*</span></label>
			<div class="layui-input-block">
				<textarea class="layui-textarea layui-hide" name="articleContent" lay-verify="content" id="content"></textarea>			
			</div>

		</div>
		


		<div class="layui-form-item">
			<label class="layui-form-label">分类 <span
				style="color: #FF5722;">*</span>
			</label>
			<div class="layui-input-inline">
				<select name="articleParentCategoryId" 	id="articleParentCategoryId" lay-filter="articleParentCategoryId" required>
					<option value="" >一级分类</option>
					<c:forEach var="c" items="${categoryList }">
						<c:if test="${c.categoryPid==0 }">
							<option value="${c.categoryId }">${c.categoryName }</option>
						</c:if>
					</c:forEach>
				</select>
			</div>
			<div class="layui-input-inline">
				<select name="articleChildCategoryId" id="articleChildCategoryId">
					<option value="" selected>二级分类</option>
				</select>
			</div>
		</div>

		<div class="layui-form-item" pane="">
			<label class="layui-form-label">标签</label>
			<div class="layui-input-block">
				<c:forEach var="t" items="${tagList }">
					<input type="checkbox" name="articleTagIds" lay-skin="primary" title="${t.tagName }" value="${t.tagId }"> 
				</c:forEach>
					
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">状态</label>
			<div class="layui-input-block">
				<input type="radio" name="articleStatus" value="1" title="发布"
					checked> <input type="radio" name="articleStatus"
					value="0" title="草稿">
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-input-block">
				<button class="layui-btn" lay-submit="" lay-filter="demo1">立即提交</button>
				<button type="reset" class="layui-btn layui-btn-primary"
					onclick="getCateIds()">重置</button>
			</div>
		</div>
		<blockquote class="layui-elem-quote layui-quote-nm">
			温馨提示：<br> 1、文章内容的数据表字段类型为MEDIUMTEXT,每篇文章内容请不要超过500万字 <br>
			2、写文章之前，请确保标签和分类存在，否则可以先新建；请勿刷新页面，博客不会自动保存 <br> 3、插入代码前，可以点击
			<a href="http://liuyanzhao.com/code-highlight.html"
				target="_blank">代码高亮</a>,将代码转成HTML格式
		</blockquote>
	</form>
</rapid:override>

 <rapid:override name="frame-footer-script">
	<script>
		layui.use(
		[ 'form', 'layedit', 'laydate' ],
		
		function() {
			var form = layui.form, 
			    layer = layui.layer, 
			    layedit = layui.layedit, 
			    laydate = layui.laydate;

			//上传图片,必须放在 创建一个编辑器前面
			layedit.set({
				uploadImage : {
					url : '/admin/upload/img' //接口url
					,
					type : 'post' //默认post
				}
			});

			//创建一个编辑器
			var editIndex = layedit.build('content', {
				height : 350,
			});

			//自定义验证规则
			form.verify({
				title : function(value) {
					if (value.length < 5) {
						return '标题至少得5个字符啊';
					}
				},
				pass : [ /(.+){6,12}$/, '密码必须6到12位' ],
				content : function(value) {
					layedit.sync(editIndex);
				}
			});

			layedit.build('content', {
				tool : [ 'strong' //加粗
				, 'italic' //斜体
				, 'underline' //下划线
				, 'del' //删除线
				, '|' //分割线
				, 'left' //左对齐
				, 'center' //居中对齐
				, 'right' //右对齐
				, 'link' //超链接
				, 'unlink' //清除链接
				, 'face' //表情
				, 'image' //插入图片
				, 'code' ]
			});
			
		

			layui.use('code', function() { //加载code模块
				layui.code();
			});

			//二级联动
			form
				.on(
					"select(articleParentCategoryId)",
					function() {
						var optionstring = "";
						var articleParentCategoryId = $(
								"#articleParentCategoryId")
								.val();

						if (articleParentCategoryId == 0) {
							optionstring += "<option name='childCategory' value='1'>Java</option>";
						}

						if (articleParentCategoryId == 1) {
							optionstring += "<option name='childCategory' value='2'>Java基础</option>";
						}

						if (articleParentCategoryId == 1) {
							optionstring += "<option name='childCategory' value='3'>Core Java</option>";
						}

						if (articleParentCategoryId == 1) {
							optionstring += "<option name='childCategory' value='4'>多线程并发编程</option>";
						}

						if (articleParentCategoryId == 1) {
							optionstring += "<option name='childCategory' value='5'>Sockets和IO</option>";
						}

						if (articleParentCategoryId == 1) {
							optionstring += "<option name='childCategory' value='6'>设计模式和反射</option>";
						}

						if (articleParentCategoryId == 1) {
							optionstring += "<option name='childCategory' value='7'>JVM</option>";
						}

						if (articleParentCategoryId == 1) {
							optionstring += "<option name='childCategory' value='8'>JavaWeb</option>";
						}

						if (articleParentCategoryId == 1) {
							optionstring += "<option name='childCategory' value='9'>Java框架</option>";
						}

						if (articleParentCategoryId == 0) {
							optionstring += "<option name='childCategory' value='10'>计算机科学</option>";
						}

						if (articleParentCategoryId == 10) {
							optionstring += "<option name='childCategory' value='11'>数据结构和算法</option>";
						}

						if (articleParentCategoryId == 10) {
							optionstring += "<option name='childCategory' value='12'>操作系统</option>";
						}

						if (articleParentCategoryId == 10) {
							optionstring += "<option name='childCategory' value='13'>数据库</option>";
						}

						if (articleParentCategoryId == 10) {
							optionstring += "<option name='childCategory' value='14'>计算机网络</option>";
						}

						if (articleParentCategoryId == 0) {
							optionstring += "<option name='childCategory' value='15'>其他技术</option>";
						}

						if (articleParentCategoryId == 15) {
							optionstring += "<option name='childCategory' value='16'>消息服务</option>";
						}

						if (articleParentCategoryId == 15) {
							optionstring += "<option name='childCategory' value='17'>缓存服务</option>";
						}

						if (articleParentCategoryId == 100000000) {
							optionstring += "<option name='childCategory' value='19'>Hello</option>";
						}

						if (articleParentCategoryId == 15) {
							optionstring += "<option name='childCategory' value='100000003'>微服务</option>";
						}

						if (articleParentCategoryId == 15) {
							optionstring += "<option name='childCategory' value='100000004'>搜索引擎</option>";
						}

						if (articleParentCategoryId == 15) {
							optionstring += "<option name='childCategory' value='100000005'>权限框架</option>";
						}

						if (articleParentCategoryId == 15) {
							optionstring += "<option name='childCategory' value='100000006'>开发利器</option>";
						}

						$("#articleChildCategoryId")
								.html(
										"<option value=''selected>二级分类</option>"
												+ optionstring);
						form.render('select'); //这个很重要
					})

				});
	</script>
</rapid:override>


<%@ include file="../framework.jsp" %>