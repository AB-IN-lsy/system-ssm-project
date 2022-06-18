<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
	       
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="zh-CN">
<head>
	<base href="<%=basePath%>">		
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>系统登录</title>
    <link rel="stylesheet" href="resources/plugin/font-awesome/css/font-awesome.min.css">
    <link rel="shortcut icon" href="resources/img/logo.png">
    <link rel='stylesheet' id='dashicons-css'  href='resources/plugin/login/dashicons.min.css' type='text/css' media='all' />
    <link rel='stylesheet' id='buttons-css'  href='resources/plugin/login/buttons.min.css' type='text/css' media='all' />
    <link rel='stylesheet' id='forms-css'  href='resources/plugin/login/forms.min.css' type='text/css' media='all' />
    <link rel='stylesheet' id='l10n-css'  href='resources/plugin/login/l10n.min.css' type='text/css' media='all' />
    <link rel='stylesheet' id='login-css'  href='resources/plugin/login/login.min.css' type='text/css' media='all' />
    <link rel='stylesheet'  href='resources/css/login.css' type='text/css' media='all' />
   
    <meta name='robots' content='noindex,follow' />
    <meta name="viewport" content="width=device-width" />

</head>

<body class="login login-action-login wp-core-ui  locale-zh-cn">
	<div id="login">
	    <br /> <br /> 
	    <%
	         String username = "";
	         String password = "";
	         //获取当前站点的所有Cookie
	         Cookie[] cookies = request.getCookies();
	         for (int i = 0; i < cookies.length; i++) {//对cookies中的数据进行遍历，找到用户名、密码的数据
	             if ("username".equals(cookies[i].getName())) {
	                    username = cookies[i].getValue();
	             } else if ("password".equals(cookies[i].getName())) {
	                 password = cookies[i].getValue();
	             }
	         }
     	%>
	    <form action="user/login"  method="post">
	        <p>
	            <label for="user_login">用户名或电子邮件地址<br />
	             <input type="text" name="userName" id="user_login" class="input" size="20" required/></label>
	        </p>
	        <p>
	            <label for="user_pass">密码<br />
	                <input type="password" name="userPass" id="user_pass" class="input" size="20" required/>
	            </label>
	        </p>
	        <p class="forgetmenot"><label for="rememberMe"><input name="rememberMe" type="checkbox" id="rememberme" value="1" checked /> 记住密码</label></p>
	        <p class="submit">
	            <input type="submit" name="wp-submit" id="submit-btn" class="button button-primary button-large" value="登录" />
	        </p>
	    </form>
	
	    <p id="backtoblog"><a href="#">&larr; 系统前台</a></p>
 
	</div>
	<div class="clear"></div>
	
	 <script>
	 	 var str="${msg}"; 
	 	 
	 	 if(str!=""){
	 		 alert(str);
	 	 }
	 </script>
</body>
</html>