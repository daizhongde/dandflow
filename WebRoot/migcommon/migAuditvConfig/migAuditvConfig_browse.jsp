<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>查看</title>
<script type="text/javascript">

</script>
</head>
<body>
<div class="easyui-layout" data-options="fit:true">
	<div data-options="region:'center',border:false" title="" noheader="true">
		<div style="padding:5px 0 5px 5px">
	    	<table align="center" width="100%">
	    		<tr>
	    			<td width="25%">Audit Item:</td>
	    			<td width="25%">${map.audit_name }</td>
	    			<td width="15%">Domain:</td>
	    			<td width="35%">
						<input class="easyui-combobox" 
		                        id='domain_B' name="domain" value="${map.domain }"
		                        data-options="valueField:'code', textField:'value',
		                        editable:false,
		                        required: true,
								data: domains" readonly="readonly"/>
					</td>
	    		</tr>
	    		<tr>
	    			<td>Table Name:</td>
	    			<td>${map.table_name }</td>
	    			<td>Expectation:</td>
	    			<td>${map.audit_value }</td>
	    		</tr>
	    		<tr>
	    			<td>Audit Type:</td>
	    			<td>${map.audit_type }</td>
	    			<td>SQL Type:</td>
	    			<td>${map.sql_type }</td>
	    		</tr>
	    		<tr>
	    			<td>Target Audit SQL:</td>
	    			<td colspan="3">${map.dst_audit_sql }</td>
	    		</tr>
	    		<tr>
	    			<td>Invalid SQL:</td>
	    			<td colspan="3">${map.invalid_data_sql }</td>
	    		</tr>
	    		<tr>
	    			<td>Unit:</td>
	    			<td>${map.audit_unit }</td>
	    			<td>Auditor:</td>
	    			<td>${map.audit_author }</td>
	    		</tr>
	    		<tr>
	    			<td>DB Connecton URL:</td>
	    			<td>${map.src_db_connect }</td>
	    			<td>Target DB Connecton URL:</td>
	    			<td>${map.dst_db_connect }</td>
	    		</tr>
	    		<tr>
	    			<td>Parameter Template:</td>
	    			<td colspan="3">${map.mig_sql_rep }</td>
	    		</tr>
	    		<tr>
	    			<td>Operator:</td>
	    			<td>${map.operator }</td>
	    		
	    			<td>Invalid Flag:</td>
	    			<td>${map.audit_flag }</td>
	    		</tr>
	    		<tr>
	    			<td>Edition:</td>
	    			<td>${map.audit_code }</td>
	    			<td></td>
	    			<td></td>
	    		</tr>
	    		<tr>
	    			<td>Description:</td>
	    			<td colspan="3">${map.remark }</td>
	    		</tr>
	    		<tr>
	    			<td>Source Audit SQL:</td>
	    			<td colspan="3">${map.src_audit_sql }</td>
	    		</tr>
	    	</table>
	    </div>
	</div>
<script type="text/javascript">
// read.close = function (){
// 	$('#win').dialog('close');
// }
</script>
	<div data-options="region:'south',border:false" style="text-align:right;padding:5px 5px 5px 0;background:#F4F4F4;">
		<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="javascript:void(0)" onclick="javascript:$('#win').dialog('close');" style="width:80px">Close</a>
	</div>
</div>
</body>
</html>