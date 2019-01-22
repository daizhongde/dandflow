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
	    			<td>Domain:</td>
	    			<td>
						<input class="easyui-combobox" 
		                        id='domain_B' name="domain" value="${map.domain }"
		                        data-options="valueField:'code', textField:'value',
		                        editable:false,
		                        required: true,
								data: domains" readonly="readonly"/>
					</td>
	    		</tr>
	    		<tr>
	    			<td>Audit Item:</td>
	    			<td>${map.faudit_name }</td>
	    		</tr>
	    		<tr>
	    			<td>Source Table:</td>
	    			<td>${map.faudit_srctable_name }</td>
	    		</tr>
	    		<tr>
	    			<td>Target Table:</td>
	    			<td>${map.faudit_dsttable_name }</td>
	    		</tr>
	    		
	    		<tr>
	    			<td>Source Connection URL:</td>
	    			<td>${map.faudit_srctable_conn }</td>
	    		</tr>
	    		<tr>
	    			<td>Target Connection URL:</td>
	    			<td>${map.faudit_dsttable_conn }</td>
	    		</tr>
	    		<tr>
	    			<td>Description:</td>
	    			<td>${map.faudit_desc }</td>
	    		</tr>
	    		<tr>
	    			<td>Author:</td>
	    			<td>${map.author }</td>
	    		</tr>
	    		<tr>
	    			<td>Create Time:</td>
	    			<td>${map.faudit_createtime }</td>
	    		</tr>
	    		<tr>
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