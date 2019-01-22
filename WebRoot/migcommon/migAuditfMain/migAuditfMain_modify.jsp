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
		<div style="padding:5px 0 10px 30px">
		    <form id="u_form" method="post" action="" style="margin:0;padding:0;">
		    	<table align="center">
		    		<tr>
		    			<td></td>
		    			<td><input class="easyui-validatebox" type="hidden" 
		    			id="faudit_id_U" name="faudit_id" value="${map.faudit_id }" data-options="required:true" readonly="readonly"></input></td>
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
		    			<td>Audit Item:</td>
		    			<td><input class="easyui-validatebox" type="text" 
		    			id="faudit_name_U" name="faudit_name" value="${map.faudit_name }" data-options="required:true"></input></td>
		    		</tr>
		    		<tr>
		    			<td>Source Table:</td>
		    			<td><input class="easyui-validatebox" type="text" 
		    			id="faudit_srctable_name_U" name="faudit_srctable_name" value="${map.faudit_srctable_name }" data-options="required:true"></input></td>
		    		</tr>
		    		<tr>
		    			<td>Target Table:</td>
		    			<td><input class="easyui-validatebox" type="text" 
		    			id="faudit_dsttable_name_U" name="faudit_dsttable_name" value="${map.faudit_dsttable_name }" data-options="required:true"></input></td>
		    		</tr>
		    		<tr>
		    			<td>Source Connection URL:</td>
		    			<td>
		    				<input id="faudit_srctable_conn_U" name="faudit_srctable_conn" type="text" value="${map.faudit_srctable_conn }" width="300px"></input>
		    			</td>
		    		</tr>
		    		<tr>
		    			<td>Target Connection URL:</td>
		    			<td>
		    				<input id="faudit_dsttable_conn_U" name="faudit_dsttable_conn" type="text" value="${map.faudit_dsttable_conn }" width="300px"></input>
		    			</td>
		    		</tr>
		    		<tr>
		    			<td>Description:</td>
		    			<td><textarea id="faudit_desc_U" name="faudit_desc"  style="height:30px;width:160px;font-size: 12px;">${map.faudit_desc }</textarea></td>
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
	update.jdata.condition.faudit_id = formData.faudit_id;
	update.jdata.operator.faudit_id = OPE.EQUAL;
	for (var field in formData) {
		update.jdata.data[field] = formData[field];
	}

	jQuery.post( getContextPath()+"/curd/migAuditfMainCURDmodify.action", 
		{
			tableName : tableName,
			jdata: encodeURI($.toJSON( update.jdata )) 
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
update.open_dialog_srcconn = function(){
	var mig_src_conn = $('#faudit_srctable_conn_U').combo( 'getValue' );	
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
	var mig_dst_conn = $('#faudit_dsttable_conn_U').combo( 'getValue' );	
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
	$('#faudit_srctable_conn_U').combogrid({
		url : getContextPath()+"/query/commonCBBQUERYdfind.action",
		panelWidth : 500,
		panelHeight : 200,
		idField : 'url',
		textField : 'url',
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
		columns : [ [  {
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
	$('#faudit_dsttable_conn_U').combogrid({
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
		columns : [ [  {
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
						$('#faudit_srctable_conn_U').combogrid( 'setValue', migsrcconn );
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
						$('#faudit_dsttable_conn_U').combogrid( 'setValue', migdstconn );
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