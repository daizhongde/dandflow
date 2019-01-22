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
		<div style="padding:5px 0 5px 5px">
	    	<table align="center" width="100%">
	    		<tr>
	    			<td width="15%">ID:</td>
	    			<td>${map.id }</td>
	    			<td width="15%"></td>
	    			<td></td>
	    		</tr>
	    		<tr>
	    			<td>Domain:</td>
	    			<td>${map.entity }</td>
	    			<td>Sub Domain:</td>
	    			<td>${map.audit_item }</td>
	    		</tr>
	    		<tr>
	    			<td>Enum Description:</td>
	    			<td>${map.enum_desc }</td>
	    			<td>Flag:</td>
	    			<td>${map.split_flag }</td>
	    		</tr>
	    		<tr>
	    			<td>Source Enum:</td>
	    			<td>${map.src_enum }</td>
	    			<td>Target Enum:</td>
	    			<td>${map.dst_enum }</td>
	    		</tr>
	    		<tr>
	    			<td>Source Count:</td>
	    			<td>${map.dst_count }</td>
	    			<td>Target Count:</td>
	    			<td>${map.dst_count }</td>
	    		</tr>
	    		<tr>
	    			<td>Author:</td>
	    			<td>${map.audit_author }</td>
	    			<td></td>
	    			<td></td>
	    		</tr>
	    		<tr>
	    			<td>DryRunId:</td>
	    			<td>${map.fares_dryrun_id }</td>
	    			<td>DryRun:</td>
	    			<td>${map.dryrun_name }</td>
	    		</tr>
	    		<tr>
	    			<td>Difference Reasons:</td>
	    			<td colspan="3">${map.min_analysis }</td>
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