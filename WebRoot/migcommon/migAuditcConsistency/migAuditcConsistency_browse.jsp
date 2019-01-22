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
	    			<td width="20%">ID:</td>
	    			<td width="80%">${map.audit_id }</td>
	    		</tr>
	    		<tr>
	    			<td>Audit Item:</td>
	    			<td>${map.audit_name }</td>
	    		</tr>
	    		<tr>
	    			<td>Parameter Template:</td>
	    			<td>${map.mig_sql_rep }</td>
	    		</tr>
	    		<tr>
	    			<td>Audit SQL:</td>
	    			<td>${map.mig_sql }</td>
	    		</tr>
	    		<tr>
	    			<td>DB Connection URL:</td>
		    		<td>
	    				<input id="src_db_connect_R" name="sql_db" type="text" value="${map.sql_db }" width="300px" ></input>
	    			</td>
    			</tr>
	    	</table>
	    </div>
    </div>
<script type="text/javascript">
read.close = function (){
	$('#win').window('close');
}
$(function(){
	$('#src_db_connect_R').combogrid({
		url : getContextPath()+"/query/commonCBBQUERYdfind.action",
		panelWidth : 500,
		panelHeight : 200,
		idField : 'url',
		textField : 'name',
		editable : false,
		pagination : false,
		fitColumns : false,
		required : true,
		readonly : true,
		rownumbers : true,
		mode : 'remote',
		delay : 500,
		sortName : 'url',
		sortOrder : 'asc',
		pageSize : 5,
		pageList : [ 5, 10 ],
		queryParams : {
			tableName : "mig_config_connection",
			columns: "url"
		},
		columns : [ [ {
			field : 'name',
			title : 'Name',
			width : 120,
			sortable : true
		},{
			field : 'url',
			title : 'Connection URL',
			width : 220,
			sortable : true
		}, {
			field : 'remark',
			title : 'Description',
			width : 220,
			sortable : false
		}, {
			field : 'ctime',
			title : 'Create Time',
			width : 130,
			sortable : true
		} ] ]
	});
});
</script>
	<div data-options="region:'south',border:false" style="text-align:right;padding:5px 5px 5px 0;background:#F4F4F4;">
		<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="javascript:void(0)" onclick="javascript:$('#win').dialog('close');" style="width:80px">Close</a>
	</div>
</div>
</body>
</html>