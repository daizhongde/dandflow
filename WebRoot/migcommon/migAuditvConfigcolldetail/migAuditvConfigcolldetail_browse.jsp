<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>查看</title>
</head>
<body rightmargin="0" bottommargin="0" leftmargin="0" topmargin="0" class="easyui-layout">
<div data-options="region:'center',border:false" title="" style="overflow:hidden;" noheader="true">
	<div style="padding:10px 0 10px 60px">
    	<table align="center">
    		<tr>
    			<td>ID:</td>
    			<td>${map.main_id }</td>
    		</tr>
    		<tr>
    			<td>配置集合名称:</td>
    			<td>${map.name }</td>
    		</tr>
    		<tr>
    			<td>备注:</td>
    			<td>${map.remark }</td>
    		</tr>
    	</table>
    </div>
    <div style="text-align:center;padding:5px">
    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="read.close()">关闭</a>
    </div>
<script type="text/javascript">
read.close = function (){
	$('#win').window('close');
}
</script>
</div>
</body>
</html>