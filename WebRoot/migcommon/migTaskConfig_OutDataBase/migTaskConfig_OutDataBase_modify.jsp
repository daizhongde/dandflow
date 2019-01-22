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
		<div style="padding:10px 0 10px 5px">
		    <form id="u_form" method="post" action="" style="margin:0;padding:0;">
		    	<table align="center">
		    		<tr>
		    			<td></td>
		    			<td><input type="hidden" name="mig_config_id" value="${map.mig_config_id }"></input></td>
		    		</tr>
		    		<tr>
		    			<td>Config Type:</td>
		    			<td>
		    				<input class="easyui-combobox" 
			                        id='mig_config_type_U' name="mig_config_type" 
			                        data-options="valueField:'code', textField:'value',
			                        value:${map.mig_config_type },
			                        editable:false,
		                        	readonly: true,
									data:configType,
			                        required:true"/>Record Status:<input class="easyui-combobox" 
			                        id='mig_status_U' name="mig_status" value="${map.mig_status }" 
			                        data-options="valueField:'code', textField:'value',
			                        editable:false,
									data:recordStatus,
			                        method:'post'"/>
		    			</td>
		    		</tr>
		    		<tr>
		    			<td>Domain:</td>
		    			<td>
		    				<input class="easyui-combobox" 
		                        id='domain_U' name="domain" value="${map.domain }"
		                        data-options="valueField:'code', textField:'value',
		                        editable:false,
		                        required: true,
								data: domains" />
		    			</td>
		    		</tr>
		    		<tr>
		    			<td>Source Table Name:</td>
		    			<td>
		    				<input id="mig_src_U" name="mig_src" class="easyui-validatebox"
								data-options="required: true" type="text" style='width:99%;' value="${map.mig_src }" />
		    			</td>
		    		</tr>
		    		<tr>
		    			<td>Source DB Connection URL:</td>
		    			<td>
		    				<input id="mig_src_conn_U" name="mig_src_conn" type="text" width="300px"></input>
		    			</td>
		    		</tr>
		    		<tr>
		    			<td>Filter Condition:</td>
		    			<td>
		    				<textarea id="mig_where_U" name="mig_where" style="font-size: 12px;" rows="1" cols="60">${map.mig_where }</textarea>
		    			</td>
		    		</tr>
		    		<tr>
		    			<td>Target Table Name:</td>
		    			<td>
		    				<input id="mig_dst_U" name="mig_dst" class="easyui-validatebox"
								data-options="required: true" type="text" style='width:99%;' value="${map.mig_dst }" />
		    			</td>
		    		</tr>
		    		<tr>
		    			<td>Target DB Connection URL:</td>
		    			<td>
		    				<input id="mig_dst_conn_U" name="mig_dst_conn" type="text" width="300px"></input>
		    			</td>
		    		</tr>
		    		<tr>
		    			<td>Description:</td>
		    			<td><textarea id="mig_desc_U" name="mig_desc" style="font-size: 12px;" rows="2" cols="60">${map.mig_desc }</textarea></td>
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

	var formData = $('#u_form').form( 'getData');
	update.init();
	update.jdata.condition.mig_config_id = formData.mig_config_id;
	update.jdata.operator.mig_config_id = OPE.EQUAL;
	for (var field in formData) {
		update.jdata.data[field] = formData[field];
	}
	jQuery.post( getContextPath()+"/curd/migTaskConfigCURDmodify.action", 
		{
			tableName : tableName,
			jdata: $.toJSON( update.jdata )
		}, 
		function( oResponse, status ){
			alert(oResponse.msg);
			if(oResponse.success == true){
				$('#grid').datagrid('reload',{
					tableName : tableName,
					jdata: $.toJSON( query.jdata )
				});
				$('#win').window('close');
			}
	});
};
update.clearForm = function (){
	$('#u_form').form('clear');
};
update.open_dialog_srcconn = function(){
	var mig_src_conn = $('#mig_src_conn_U').combo( 'getValue' );	
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
update.open_dialog_dstconn = function(){
	var mig_dst_conn = $('#mig_dst_conn_U').combo( 'getValue' );	
	if( mig_dst_conn != '' ){
		var temp = mig_dst_conn.split(/\|/);
		if(temp.length==5){
			$('#dbtype2_U').combobox("setValue", temp[0] );
			$('#ip2_U').textbox("setValue", temp[1]);
			$('#username2_U').textbox("setValue", temp[2]);
			$('#password2_U').textbox("setValue", temp[3]);
			$('#instance2_U').textbox("setValue", temp[4]);
		}
	}
	$('#dlg-migdstconn_U').dialog('open');
};

$(function(){
	$('#mig_src_conn_U').combogrid({
		url : getContextPath()+"/query/commonCBBQUERYdfind.action",
		panelWidth : 500,
		panelHeight : 200,
		idField : 'url',
		textField : 'url',
		value: '${map.mig_src_conn }',
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
		columns : [ [{
			field : 'name',
			title : 'Name',
			width : 120,
			sortable : true
		}, {
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
	$('#mig_dst_conn_U').combogrid({
		url : getContextPath()+"/query/commonCBBQUERYdfind.action",
		panelWidth : 500,
		panelHeight : 200,
		idField : 'url',
		textField : 'url',
		value: '${map.mig_dst_conn }',
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
		}, {
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
	
	<!-- 源数据库连接串   ########################### -->
	<div id="dlg-migsrcconn_U" class="easyui-dialog"
		title="DB Connection URL Config" style="width:550px;height:180px;"
			data-options="
				modal:true,closed:true,
				iconCls: 'icon-edit',
				buttons: [{
					text:'Ok',
					iconCls:'icon-ok',
					handler:function(){
						if( ! $('#form-migsrcconn_U').form('validate') )
						{
							return;
						}
						var migsrcconn = '';
						var formData = $('#form-migsrcconn_U').form( 'getData');
						migsrcconn = formData['dbtype']+'|'+formData['ip']+'|'+formData['username']+'|'
							+formData['password']+'|'+formData['instance'];
						$('#mig_src_conn_U').combogrid( 'setValue', migsrcconn );
						$('#dlg-migsrcconn_U').dialog('close');
					}
				},{
					text:'Cancel',
					handler:function(){
						$('#dlg-migsrcconn_U').dialog('close');
					}
				}]
			">
		<form id="form-migsrcconn_U" method="post" action="" style="margin:0;padding:0;">
	    	<table align="center" width="100%" border="1">
				<tr>
	    			<td>DB Type:</td>
	    			<td colspan='3'>
	    				<input class='easyui-combobox' id="dbtype_U"  name='dbtype' data-options="valueField:'code',textField:'value', 
							editable:false,url:'../../cbb/dicCBBgetDic2ByType.action?dicType=db-type',method:'post',required:true"/>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>IP:</td>
	    			<td>
	    				<input class="easyui-textbox" type="text" id="ip_U" name="ip" data-options="required:true"></input>
	    			</td>
	    			<td>Instance:</td>
	    			<td>
	    				<input class="easyui-textbox" type="text" id="instance_U" name="instance" data-options="required:true"></input>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>Username:</td>
	    			<td>
	    				<input class="easyui-textbox" type="text" id="username_U" name="username" data-options="required:true"></input>
	    			</td>
	    			<td>Password:</td>
	    			<td>
	    				<input class="easyui-textbox" type="text" id="password_U" name="password" data-options="required:true"></input>
	    			</td>
	    		</tr>
	    	</table>			
	    </form>
	</div>
	
	<!-- 目标数据库连接串   ########################### -->
	<div id="dlg-migdstconn_U" class="easyui-dialog"
		title="DB Connection URL Config" style="width:550px;height:180px;"
			data-options="
				modal:true,closed:true,
				iconCls: 'icon-edit',
				buttons: [{
					text:'Ok',
					iconCls:'icon-ok',
					handler:function(){
						if( ! $('#form-migdstconn_U').form('validate') )
						{
							return;
						}
						var migdstconn = '';
						var formData = $('#form-migdstconn_U').form( 'getData');
						migdstconn = formData['dbtype']+'|'+formData['ip']+'|'+formData['username']+'|'
							+formData['password']+'|'+formData['instance'];
						$('#mig_dst_conn_U').combogrid( 'setValue', migdstconn );
						$('#dlg-migdstconn_U').dialog('close');
					}
				},{
					text:'Cancel',
					handler:function(){
						$('#dlg-migdstconn_U').dialog('close');
					}
				}]
			">
		<form id="form-migdstconn_U" method="post" action="" style="margin:0;padding:0;">
	    	<table align="center" width="100%" border="1">
				<tr>
	    			<td>DB Type:</td>
	    			<td colspan='3'>
	    				<input class='easyui-combobox' id="dbtype2_U"  name='dbtype' data-options="valueField:'code',textField:'value', 
							editable:false,url:'../../cbb/dicCBBgetDic2ByType.action?dicType=db-type',method:'post',required:true"/>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>IP:</td>
	    			<td>
	    				<input class="easyui-textbox" type="text" id="ip2_U" name="ip" data-options="required:true"></input>
	    			</td>
	    			<td>Instance:</td>
	    			<td>
	    				<input class="easyui-textbox" type="text" id="instance2_U" name="instance" data-options="required:true"></input>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>Username:</td>
	    			<td>
	    				<input class="easyui-textbox" type="text" id="username2_U" name="username" data-options="required:true"></input>
	    			</td>
	    			<td>Password:</td>
	    			<td>
	    				<input class="easyui-textbox" type="text" id="password2_U" name="password" data-options="required:true"></input>
	    			</td>
	    		</tr>
	    	</table>			
	    </form>
	</div>
	
</div>
</body>
</html>