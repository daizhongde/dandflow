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
		                        id='domain_B' name="entity" value="${map.entity }"
		                        data-options="valueField:'code', textField:'value',
		                        editable:false,
		                        required: true,
								data: domains" readonly="readonly"/>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>Sub Domain:</td>
	    			<td>${map.audit_name }</td>
	    		</tr>
	    		<tr>
	    			<td>Source Enum:</td>
	    			<td>${map.src_enum }</td>
	    		</tr>
	    		<tr>
	    			<td>Target Enum:</td>
	    			<td>${map.dst_enum }</td>
	    		</tr>
	    		<tr>
	    			<td>Description:</td>
	    			<td>${map.enum_desc }</td>
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