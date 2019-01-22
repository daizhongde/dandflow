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
		<div style="padding:10px 0 10px 5px">
	    	<table align="center">
	    		<tr>
	    			<td>ID:</td>
	    			<td>${map.mig_config_id }</td>
	    		</tr>
	    		<tr>
	    			<td>Config Type:</td>
	    			<td>${map.mig_config_type }</td>
	    		</tr>
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
	    			<td>Pending Storage File:</td>
	    			<td>${map.mig_src }</td>
	    		</tr>
	    		<tr>
	    			<td>Target Table Name:</td>
	    			<td>${map.mig_dst }</td>
	    		</tr>
	    		<tr>
	    			<td>Target DB Connection URL:</td>
	    			<td>${map.mig_dst_conn }</td>
	    		</tr>
	    		<tr>
	    			<td>Record Status:</td>
	    			<td>${map.mig_status }</td>
	    		</tr>
	    		<tr>
	    			<td>Description:</td>
	    			<td>${map.mig_desc }</td>
	    		</tr>
	    		<tr>
	    			<td>Author:</td>
	    			<td>${map.mig_author }</td>
	    		</tr>
	    		<tr>
	    			<td>Last Update Time:</td>
	    			<td>${map.mig_modifytime }</td>
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