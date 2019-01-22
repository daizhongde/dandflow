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
		<div style="padding:10px 0 10px 30px">
	    	<table align="center">
	    		<tr>
	    			<td>Audit Item:</td>
	    			<td>${map.faudit_name }</td>
	    		</tr>
	    		<tr>
	    			<td>Index:</td>
	    			<td>${map.faudit_sub_index }</td>
	    		</tr>
	    		<tr>
	    			<td>Source Field:</td>
	    			<td>${map.faudit_src_field }</td>
	    		</tr>
	    		<tr>
	    			<td>Target Field:</td>
	    			<td>${map.faudit_dst_field }</td>
	    		</tr>
	    		<tr>
	    			<td>isKey:</td>
	    			<td>${map.faudit_iskey }</td>
	    		</tr>
	    		<tr>
	    			<td>Fields Correlation:</td>
	    			<td>${map.faudit_opt }</td>
	    		</tr>
	    		<tr>
	    			<td>Create Time:</td>
	    			<td>${map.faudit_createtime }</td>
	    		</tr>
	    		<tr>
	    			<td>Last Modify Time:</td>
	    			<td>${map.faudit_modifytime }</td>
	    		</tr>
	    		<tr>
	    			<td>Status:</td>
	    			<td>${map.faudit_status }</td>
	    		</tr>
	    	</table>
	    </div>
    </div>
<script type="text/javascript">
read.close = function (){
	$('#win').window('close');
}
</script>
	<div data-options="region:'south',border:false" style="text-align:right;padding:5px 5px 5px 0;background:#F4F4F4;">
		<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="javascript:void(0)" onclick="javascript:$('#win').dialog('close');" style="width:80px">Close</a>
	</div>
</div>
</body>
</html>