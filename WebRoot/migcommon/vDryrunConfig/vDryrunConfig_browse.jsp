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
<body>
<div class="easyui-layout" data-options="fit:true">
	<div data-options="region:'center',border:false" title="" noheader="true">
		<div style="padding:10px 0 10px 60px">
	    	<table align="center">
	    		<tr>
	    			<td>ID:</td>
	    			<td>${map.mig_dryrun_id }</td>
	    		</tr>
	    		<tr>
	    			<td>Dryrun Name:</td>
	    			<td>${map.mig_dryrun_name }</td>
	    		</tr>
	    		<tr>
	    			<td>Description:</td>
	    			<td>${map.remark }</td>
	    		</tr>
	    	</table>
	    </div>
	</div>
<script type="text/javascript">
read.close = function (){
	$('#win').dialog('close');
}
</script>
	<div data-options="region:'south',border:false" style="text-align:right;padding:5px 5px 5px 0;background:#F4F4F4;">
		<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="javascript:void(0)" onclick="javascript:$('#win').dialog('close');" style="width:80px">Close</a>
	</div>
</div>
</body>
</html>