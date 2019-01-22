<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'MyJsp.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<body>
	<form action="http://home.asiainfo.com/AIPRT/Home/GetUserListForChild" method="post">
		<table>
			<tr><td>
			<label>Mobile:</label><input name="Mobile" type="text" />
			</td></tr>
			<tr><td>
			<label>NT_Account:</label><input name="NT_Account" type="text" /> 
			</td></tr>
			<tr><td>
			<label>ORG_NAME:</label><input name="ORG_NAME" type="text" />
			</td></tr>
			<tr><td>
			<label>SubPhone:</label><input name="SubPhone" type="text" />
			</td></tr>
			<tr><td>
			<label>WORKING_LOCATION:</label><input name="WORKING_LOCATION" type="text" />
			</td></tr>
			<tr><td>
			<label>company:</label><input name="company" type="text" />
			</td></tr>
			<tr><td>
			<label>employeeNo:</label><input name="employeeNo" type="text" />
			</td></tr>
			<tr><td>
			<label>name:</label><input name="name" type="text" />
			</td></tr>
			<tr><td>
			<label>page:</label><input name="page" type="text" value="1"/>
			</td></tr>
			<tr><td>
			<label>sbu:</label><input name="sbu" type="text" /> 
			</td></tr>
			<tr><td>
			<label>size:</label><input name="size" type="text" value="15"/>
			</td></tr>
		</table>
		<button type="submit">提交</button>
	</form>
</body>
</html>
