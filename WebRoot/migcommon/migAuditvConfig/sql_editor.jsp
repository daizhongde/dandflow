<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <base href="<%=basePath%>">
    
    <title>sql编辑</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
</head>
<body>
<div style="border-top: 1px solid black; border-bottom: 1px solid black;">
<textarea id="code" cols="120" rows="40"></textarea>
</div>
<script type="text/javascript">
add.editor = CodeMirror.fromTextArea('code', {
//     height: "400px",
    parserfile: "parsesql.js",
    stylesheet: getContextPath()+"/scripts/codemirror-5.10/sqlcolors.css",
    path: getContextPath()+"/scripts/codemirror-5.10/",
    lineNumbers: true,
    textWrapping: true
});

</script>
</body>
</html>
