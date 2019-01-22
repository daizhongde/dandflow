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
		<div style="padding:10px 0 10px 30px">
		    <form id="u_form" method="post" action="" style="margin:0;padding:0;">
		    	<table align="center">
		    		<tr>
		    			<td></td>
		    			<td><input type="hidden" id="id_U" name="id" value="${map.id }"></input></td>
		    		</tr>
		    		<tr>
		    			<td>Name:</td>
		    			<td><input class="easyui-validatebox" type="text" 
		    			id="name_U" name="name" value="${map.name }" data-options="required:true" style="width:300px"></input></td>
		    		</tr>
		    		<tr>
		    			<td>Connection URL:</td>
		    			<td>
		    				<input class="easyui-validatebox" type="text" id="url_U" name="url" value="${map.url }" 
		    				onclick="update.open_dialog_url()" data-options="required:true" style="width:300px"></input>
	    					<input type='button' value='Config' onclick="update.open_dialog_url()"/>
		    			</td>
		    		</tr>
		    		<tr>
		    			<td>Connection Description:</td>
		    			<td><textarea id="remark_U" name="remark"  style="height:60px;width:300px;font-size: 12px;">${map.remark }</textarea></td>
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
	if($('#u_form #remark_U').val() == ""){
		alert("Connection Description is required！");
		return;
	}
	
	var formData = $('#u_form').form( 'getData');
	update.init();
	update.jdata.condition.id = formData.id;
	update.jdata.operator.id = OPE.EQUAL;
	for (var field in formData) {
		update.jdata.data[field] = formData[field];
	}
	
	jQuery.post( getContextPath()+"/curd/commonCURDmodify.action", 
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
update.open_dialog_url = function(){
	var url = $('#url_U').val();	
	if( url != '' ){
		var temp = url.split(/\|/);
		if(temp.length==5){
			$('#dbtype_U').combobox("setValue", temp[0] );
			$('#ip_U').textbox("setValue", temp[1]);
			$('#username_U').textbox("setValue", temp[2]);
			$('#password_U').textbox("setValue", temp[3]);
			$('#instance_U').textbox("setValue", temp[4]);
			$('#port_U').textbox("setValue", temp[5]);
		}
	}
	$('#dlg-url_U').dialog('open');
};
</script>
	<div data-options="region:'south',border:false" style="text-align:right;padding:5px 5px 5px 0;background:#F4F4F4;">
		<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" href="javascript:void(0)" onclick="javascript: update.submitForm()" style="width:80px">Submit</a>
		<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="javascript:void(0)" onclick="javascript:$('#win').dialog('close');" style="width:80px">Cancel</a>
	</div>
	
	<!-- 目标数据库连接串   ########################### -->
	<div id="dlg-url_U" class="easyui-dialog"
		title="DB Connection URL Config" style="width:550px;height:180px;"
			data-options="
				modal:true,closed:true,
				iconCls: 'icon-edit',
				buttons: [{
					text:'Ok',
					iconCls:'icon-ok',
					handler:function(){
						if( ! $('#form-migdstconn').form('validate') )
						{
							return;
						}
						var migdstconn = '';
						var formData = $('#form-migdstconn').form( 'getData');
						migdstconn = formData['dbtype']+'|'+formData['ip']+'|'+formData['username']+'|'
							+formData['password']+'|'+formData['instance']+'|'+formData['port'];
						$('#url_U').val( migdstconn );
						$('#dlg-url_U').dialog('close');
					}
				},{
					text:'Cancel',
					handler:function(){
						$('#dlg-url_U').dialog('close');
					}
				}]
			">
		<form id="form-migdstconn" method="post" action="" style="margin:0;padding:0;">
	    	<table align="center" width="100%" border="1">
				<tr>
	    			<td>DB Type:</td>
	    			<td colspan='1'>
	    				<input class='easyui-combobox' id="dbtype_U"  name='dbtype' data-options="valueField:'code',textField:'value', 
							editable:false,url:'../../cbb/dicCBBgetDic2ByType.action?dicType=db-type',method:'post',required:true"/>
	    			</td>
	    			<td>Port:</td>
	    			<td>
	    				<input class="easyui-textbox" type="text" id="port_U" name="port" data-options="required:true"></input>
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
</div>
</body>
</html>