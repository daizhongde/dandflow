<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>修改</title>
</head>
<body>
<div class="easyui-layout" data-options="fit:true">
	<div data-options="region:'center',border:false" title="" noheader="true">
		<div style="padding:5px 0 5px 5px">
		    <form id="u_form" method="post" action="" style="margin:0;padding:0;">
		    	<table align="center">
		    		<tr>
		    			<td>ID:</td>
		    			<td><input class="easyui-validatebox" type="text" 
		    			id="audit_id_U" name="audit_id" value="${map.audit_id }" data-options="required:true" readonly="readonly"></input></td>
		    		</tr>
		    		<tr>
		    			<td>Audit Item:</td>
		    			<td><input class="easyui-validatebox" type="text" 
		    			id="audit_name_U" name="audit_name" value="${map.audit_name }" data-options="required:true"></input></td>
		    		</tr>
		    		<tr>
		    			<td>Parameter Template:</td>
		    			<td><input class="easyui-validatebox" type="text" id="mig_sql_rep_U" name="mig_sql_rep" 
		    				value="${map.mig_sql_rep }" data-options="required:false"></input></td>
		    		</tr>
		    		<tr>
		    			<td>Audit SQL:</td>
		    			<td><textarea id="mig_sql_U" name="mig_sql"  style="height:150px;width:420px;font-size: 12px;"
		    			onclick="update.open_sqleditor()" readonly="readonly">${map.mig_sql }</textarea></td>
		    		</tr>
		    		<tr>
		    			<td>DB Connection URL:</td>
			    		<td>
		    				<input id="src_db_connect_U" name="sql_db" type="text" value="${map.sql_db }" width="300px" ></input>
		    			</td>
	    			</tr>
		    	</table>
		    </form>
	    </div>
    </div>
<script type="text/javascript">
var update = VIRTUE.update;

update.submitForm = function (){
	if( ! $('#u_form').form("validate") )
	{
		return;
	}
	if($('#u_form #mig_sql_U').val() == ""){
		alert("稽核SQL不能為空！");
		return;
	}
	
	var formData = $('#u_form').form( 'getData');
	update.init();
	update.jdata.condition.audit_id = formData.audit_id;
	update.jdata.operator.audit_id = OPE.EQUAL;
	for (var field in formData) {
		update.jdata.data[field] = formData[field];
	}
	
	jQuery.post( getContextPath()+"/curd/migAuditcConsistencyCURDmodify.action", 
		{
			tableName : tableName,
			jdata: $.toJSON( update.jdata )
		}, 
		function( oResponse, status ){
			alert(oResponse.msg);
			if(oResponse.success == true){
				$('#grid').datagrid('reload',{
					tableName : tableName,
					jdata: encodeURI($.toJSON( query.jdata ))
				});
				$('#win').window('close');
			}
	});
};
update.clearForm = function (){
	$('#u_form').form('clear');
};


update.SQLValue = "";
/** 打开sql编辑页面*/
update.open_sqleditor = function(){
	$("#dlg_audit_sql_U").dialog({
		title : "sql",
		href :  "sql_editor_U.html",
		iconCls : 'icon-edit',
		modal : true,
		closed : true,
		resizable: true,
		minimizable: true,
		maximizable: true,
		cache: false,
		width : 840,
		height : 380,
		buttons: [{
            text:'OK',
            iconCls:'icon-ok',
            handler:function(){
            	$("#mig_sql_U").val( update.editor.getValue()  );
            	$("#dlg_audit_sql_U").dialog('close');
            }
        },{
            text:'Cancel',
            iconCls:'icon-cancel',
            handler:function(){
            	$("#dlg_audit_sql_U").dialog('close');
            }
        }]
	});
	update.SQLValue = $("#mig_sql_U").val();
	$("#dlg_audit_sql_U").dialog('open');
};
$(function(){
	$('#src_db_connect_U').combogrid({
		url : getContextPath()+"/query/commonCBBQUERYdfind.action",
		panelWidth : 500,
		panelHeight : 200,
		idField : 'url',
		textField : 'name',
		editable : false,
		pagination : false,
		fitColumns : false,
		required : true,
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
		<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" href="javascript:void(0)" onclick="javascript: update.submitForm()" style="width:80px">Submit</a>
		<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="javascript:void(0)" onclick="javascript:$('#win').dialog('close');" style="width:80px">Cancel</a>
	</div>
	
	<div id="dlg_audit_sql_U"></div>
</div>
</body>
</html>