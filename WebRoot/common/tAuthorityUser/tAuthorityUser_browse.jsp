<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>浏览用户 JDATA R:map</title>
<!-- 	<link rel="stylesheet" type="text/css" href="../../scripts/jquery-easyui/1.3.5/themes/default/easyui.css"/> -->
<!-- 	<link rel="stylesheet" type="text/css" href="../../scripts/jquery-easyui/1.3.5/themes/icon.css"/> -->
<!-- 	<script type="text/javascript" src="../../scripts/jquery-1.4.4.min.js" ></script> -->
<!-- 	<script type="text/javascript" src="../../scripts/jquery-easyui/1.3.5/jquery.easyui.min.js"></script> -->
<!-- 	<script type="text/javascript" src="../../scripts/json/jquery.json-2.2.min.js"></script> -->
</head>
<body rightmargin="0" bottommargin="0" leftmargin="0" topmargin="0" class="easyui-layout">
<div data-options="region:'center',border:false" title="用户main" style="overflow:hidden;" noheader="true">
		<div style="padding:10px 0 10px 60px">
	    	<table align="center">
	    		<tr>
	    			<td>所属机构:</td>
	    			<td>${map.icode }-${map.iname }</td>
	    		</tr>
	    		<tr>
	    			<td>用户ID:</td>
	    			<td>${map.id }</td>
	    		</tr>
	    		<tr>
	    			<td>用户登陆名:</td>
	    			<td>${map.logname }</td>
	    		</tr>
	    		<tr>
	    			<td>用户姓名:</td>
	    			<td>${map.name }</td>
	    		</tr>
	    		<tr>
	    			<td>性别:</td>
	    			<td>${map.sex }</td>
	    		</tr>
	    		<tr>
	    			<td>员工号:</td>
	    			<td>${map.employee_number }</td>
	    		</tr>
	    		<tr>
	    			<td>上司ID:</td>
	    			<td>${map.supervisor_id }</td>
	    		</tr>
	    		<tr>
	    			<td>联系电话:</td>
	    			<td>${map.phone }</td>
	    		</tr>
	    		<tr>
	    			<td>办公电话:</td>
	    			<td>${map.tel }</td>
	    		</tr>
	    		<tr>
	    			<td>传真:</td>
	    			<td>${map.fax }</td>
	    		</tr>
	    		<tr>
	    			<td>电子邮箱:</td>
	    			<td>${map.email }</td>
	    		</tr>
	    		<tr>
	    			<td>QQ:</td>
	    			<td>${map.qq }</td>
	    		</tr>
	    		<tr>
	    			<td>通迅地址:</td>
	    			<td>${map.addr }</td>
	    		</tr>
				<tr>
	    			<td>备注:</td>
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
};
</script>
</div>
</body>
</html>