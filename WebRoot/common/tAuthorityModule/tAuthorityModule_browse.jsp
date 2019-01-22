<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>浏览模块 JDATA R:map</title>
<!-- 	<link rel="stylesheet" type="text/css" href="../../scripts/jquery-easyui/1.3.5/themes/default/easyui.css"/> -->
<!-- 	<link rel="stylesheet" type="text/css" href="../../scripts/jquery-easyui/1.3.5/themes/icon.css"/> -->
<!-- 	<script type="text/javascript" src="../../scripts/jquery-1.4.4.min.js" ></script> -->
<!-- 	<script type="text/javascript" src="../../scripts/jquery-easyui/1.3.5/jquery.easyui.min.js"></script> -->
<!-- 	<script type="text/javascript" src="../../scripts/json/jquery.json-2.2.min.js"></script> -->
</head>
<body rightmargin="0" bottommargin="0" leftmargin="0" topmargin="0" class="easyui-layout">
<div data-options="region:'center',border:false" title="模块main" style="overflow:hidden;" noheader="true">
		<div style="padding:10px 0 10px 60px">
	    	<table align="center">
	    		<tr>
	    			<td>模块名称:</td>
	    			<td>${map.name }</td>
	    		</tr>
	    		<tr>
	    			<td>模块路径:</td>
	    			<td>${map.path }</td>
	    		</tr>
	    		<tr>
	    			<td>叶子节点:</td>
	    			<td><span id="cmleaf_V" name="map.leaf" >${map.leaf }</span></td>
	    		</tr>
	    		<tr>
	    			<td>模块级别:</td>
	    			<td><span id="nmlevel_V" name="map.level" >${map.level }</span></td>
	    		</tr>
	    		<tr>
	    			<td>上级模块:</td>
	    			<td><span id="nmparent_V" name="map.parent" >${map['t2.name'] }</span></td>
	    		</tr>
	    		
	    		<tr>
	    			<td>模块次序:</td>
	    			<td>${map.order }</td>
	    		</tr>
	    		<tr>
	    			<td>模块说明:</td>
	    			<td>${map.note }</td>
	    		</tr>
	    	</table>
	    </div>
	    <div style="text-align:center;padding:5px">
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="read.close()">关闭</a>
	    </div>
<script type="text/javascript">
// var read = {};//define in main.html(eg: table_jeasyui.html)
read.close = function (){
	$('#win').window('close');
}
</script>
</div>
</body>
</html>