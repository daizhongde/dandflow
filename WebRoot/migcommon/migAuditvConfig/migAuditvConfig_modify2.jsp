<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>修改-树型界面修改功能</title>

</head>
<body>
<div class="easyui-layout" data-options="fit:true">
	<div data-options="region:'center',border:false" title="" noheader="true">
		<div style="padding:5px 0 5px 5px">
		    <form id="u_form" method="post" action="" style="margin:0;padding:0;">
		    	<table align="center" width="100%">
		    		<tr>
		    			<td></td>
		    			<td><input type="hidden" id="audit_id_U" name="audit_id" value="${map.audit_id }"></input></td>
		    			<td></td>
		    			<td></td>
		    		</tr>
		    		<tr>
		    			<td width="25%">Audit Item:</td>
		    			<td width="25%">
		    				<input class="easyui-validatebox" type="text" id="audit_name_U" name="audit_name" 
		    				value="${map.audit_name }" data-options="required:true"></input>
		    			</td>
		    			<td width="15%">Domain:</td>
		    			<td width="35%">
		    				<input class="easyui-combobox" 
		                        id='domain_U' name="domain" value="${map.domain }"
		                        data-options="valueField:'code', textField:'value',
		                        editable:false,
		                        required: true,
								data: domains" />
		    			</td>
		    		</tr>
		    		<tr>
		    			<td>Table Name:</td>
		    			<td>
		    				<input class="easyui-validatebox" type="text" id="table_name_U" name="table_name" 
		    				value="${map.table_name }" data-options="required:false"></input>
		    			</td>
		    			<td>Auditor:</td>
		    			<td><input class="easyui-validatebox" type="text" id="audit_author_U" name="audit_author" 
		    				value="${map.audit_author }" data-options="required:true"></input>
		    			</td>
		    		</tr>
<!-- 		    		<tr> -->
<!-- 		    			<td>DB Connection URL:</td> -->
<!-- 		    			<td colspan="3"> -->
<!-- 		    				<input id="src_db_connect_U" name="src_db_connect" type="text" value="${map.src_db_connect }"  width="300px" ></input> -->
<!-- 		    			</td> -->
<!-- 		    		</tr> -->
		    		<tr>
		    			<td>Parameter Template:</td>
		    			<td colspan="3">
		    				<textarea id="mig_sql_rep_U" name="mig_sql_rep" 
		    					style="height:50px;width:95%;font-size: 12px;">${map.mig_sql_rep }</textarea>
		    			</td>
		    		</tr>
		    		<tr>
		    			<td>Operator:</td>
		    			<td><input class="easyui-validatebox" type="text" id="operator_U" name="operator" 
		    				value="${map.operator }" data-options="required:true"></input>
		    			</td>
		    			<td>Expectation:</td>
		    			<td colspan="3">
		    				<input class="easyui-validatebox" id="audit_value_U" name="audit_value" 
		    					value="${map.audit_value }" data-options="required:true"/>
		    			</td>
		    		</tr>
		    		<tr>
		    			<td>Description:</td>
		    			<td colspan="3">
		    				<textarea id="remark_U" name="remark" 
		    					style="height:50px;width:95%;font-size: 12px;">${map.remark }</textarea>
		    			</td>
		    		</tr>
		    		<tr>
		    			<td>Source Audit SQL:</td>
		    			<td colspan="3">
		    				<textarea id="src_audit_sql_U" name="src_audit_sql"	style="height:100px;width:95%;font-size: 12px;"
		    					onclick="update.open_sqleditor()" readonly="readonly">${map.src_audit_sql }</textarea>
		    			</td>
		    		</tr>
		    		<tr>
		    			<td>Audit Flag:</td>
		    			<td>
		    				<input class="easyui-combobox" 
		                        id='audit_flag_U' name="audit_flag" value=""
		                        data-options="valueField:'id', textField:'name',
		                        editable:false,
		                        required: true,value:'${map.audit_flag }',
								data: [{id:'Y',name:'valid'},{id:'N',name:'invalid'}]" />
		    			</td>
		    			<td>Audit Level:</td>
		    			<td>
		    				<input class="easyui-combobox" 
		                        id='audit_level_U' name="audit_level" value=""
		                        data-options="valueField:'code', textField:'value',
		                        editable:false,
		                        required: true,value:'${map.audit_level }',
								data: AuditLevels" />
								<!-- [{id:'1',name:'high'},{id:'2',name:'middle'},{id:'3',name:'low'}]" /> -->
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
	if($('#u_form #src_audit_sql_U').val() == ""){
		alert("Source Audit SQL is required！");
		return;
	}
	
	var formData = $('#u_form').form( 'getData');
	update.init();
	update.jdata.condition.audit_id = formData.audit_id;
	update.jdata.operator.audit_id = OPE.EQUAL;
	for (var field in formData) {
		update.jdata.data[field] = formData[field];
	}
	
	jQuery.post( getContextPath()+"/curd/migAuditvConfigCURDmodify.action", 
		{
			jdata: $.toJSON( update.jdata )
		}, 
		function( oResponse, status ){
			alert(oResponse.msg);
			if(oResponse.success == true){
				
				$('#win').window('close');
// 				var t = $('#tt');
// 				var node = t.tree('getSelected');
// 				var parent = t.tree('getParent', node.target)
// 				t.tree('reload', parent.target);
			}
	});
};
update.clearForm = function (){
	$('#u_form').form('clear');
};

update.SQLValue = "";
/** 打开sql编辑页面*/
update.open_sqleditor = function(){
	update.SQLValue = $("#src_audit_sql_U").val();
// 	alert(update.SQLValue);
	$("#dlg_audit_sql_U").dialog({
		title : "sql",
		href :  getContextPath()+"/migcommon/migAuditvConfig/sql_editor_U.html",
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
            	$("#src_audit_sql_U").val( update.editor.getValue()  );
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
	$("#dlg_audit_sql_U").dialog('open');
	$("#dlg_audit_sql_U").dialog("refresh");
};

update.open_dialog_srcconn = function(){
	var mig_src_conn = $('#src_db_connect_U').combo( 'getValue' );	
	if( mig_src_conn != '' ){
		var temp = mig_src_conn.split(/\|/);
		if(temp.length==5){
			$('#dbtype_U').combobox("setValue", temp[0] );
			$('#ip_U').textbox("setValue", temp[1]);
			$('#username_U').textbox("setValue", temp[2]);
			$('#password_U').textbox("setValue", temp[3]);
			$('#instance_U').textbox("setValue", temp[4]);
		}
	}
	$('#dlg-migsrcconn_U').dialog('open');
};

$(function(){
	$('#src_db_connect_U').combogrid({
		url : getContextPath()+"/query/commonCBBQUERYdfind.action",
		panelWidth : 500,
		panelHeight : 200,
		idField : 'url',
		textField : 'name',
		editable:false,
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
		<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="javascript:void(0)" onclick="javascript:$('#win').window('close');" style="width:80px">Cancel</a>
	</div>
	
	<div id="dlg_audit_sql_U"></div>
	
</div>
</body>
</html>