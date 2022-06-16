
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
	<base href="<%=basePath%>">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	
    <title>404</title>
    <link rel="shortcut icon" href="resources/img/logo.png">
    <link type="text/css" rel='stylesheet' href="resources/page/404/css/404life.css">
    <script type="text/javascript" src='resources/page/404/js/404life.js'></script>
    <script src='https://unpkg.com/jquery@3.2.1/dist/jquery.min.js'></script>
    <script>
    window.console = window.console || function(t) {};
    </script>
    <script>
    if (document.location.search.match(/type=embed/gi)) {
        window.parent.postMessage("resize", "*");
    }
    </script>
</head>

<body translate="no" >
  <div class='terminal'>
  <h1 id='title'>404</h1>
  <div class='text' id='text_1'></div>
  <div class='text' id='text_2'></div>
  <div class='cursor'>_</div>
  <div class='c'>
    Copyright © 2022
    <span class='heart'>&hearts;</span>
    AB-IN All Rights Reserved
  </div>
</div>
</body>
</html>