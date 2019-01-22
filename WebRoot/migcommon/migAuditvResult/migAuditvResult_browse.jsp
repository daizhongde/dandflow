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
	    	<table align="center" width="100%">
		    		<tr>
		    			<td width="100px">id:</td>
		    			<td>${map.id}</td>
		    			<td width="100px">audit id</td>
		    			<td>${map.audit_id }</td>
		    		</tr>
		    		<tr>
		    			<td>Audit Item:</td>
		    			<td>${map.audit_name }</input>
		    			</td>
		    			<td>Dryrun:</td>
		    			<td>${map.dryrun_name }</td>
		    		</tr>
		    		<tr>
		    			<td>Diff. Reasons:</td>
		    			<td colspan="3">${map.min_analysis }</td>
		    		</tr>
		    		<tr>
		    			<td>DMP NO.:</td>
		    			<td>${map.dmp_num }</td>
		    			<td>Environment:</td>
		    			<td>${map.env }</td>
		    		</tr>
		    		<tr>
		    			<td>Domain Name:</td>
		    			<td>${map.domain2 }</td>
		    			<td>Table Name:</td>
		    			<td>${map.table_name }</td>
		    		</tr>
		    		<tr>
		    			<td>Source Value:</td>
		    			<td>${map.src_value }</td>
		    			<td>Target Value:</td>
		    			<td>${map.dst_value }</td>
		    		</tr>
		    		<tr>
		    			<td>Difference Value:</td>
		    			<td colspan="3">${map.min_value }</td>
		    		</tr>
		    		<tr>
		    			<td>Unit:</td>
		    			<td>${map.audit_unit }</td>
		    			<td>Invalid Table:</td>
		    			<td>${map.invalid_data_table }</td>
		    		</tr>
		    		<tr>
		    			<td>Execute Result:</td>
		    			<td>${map.result }</td>
		    			<td>Auditor:</td>
		    			<td>${map.audit_author }</td>
		    		</tr>
		    		<tr>
		    			<td>Audit Time:</td>
		    			<td colspan="3">${map.hdate }</td>
		    		</tr>
		    		<tr>
		    			<td>Remark:</td>
		    			<td colspan="3">${map.remark }</td>
		    		</tr>
		    		<tr>
		    			<td>Success Flag:</td>
		    			<td>${map.success }</td>
		    			<td>Error Data Count:</td>
		    			<td>${map.invalid_data_cnt }</td>
		    		</tr>
		    		<tr>
		    			<td>Error Msg:</td>
		    			<td colspan="3">${map.err_msg }</td>
		    		</tr>
		    		<tr>
		    			<td>src_audit_sql:</td>
		    			<td colspan="3">${map.src_audit_sql }</td>
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