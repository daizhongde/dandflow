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
			                        id='mig_config_type_U1' name="mig_config_type" 
			                        data-options="valueField:'code', textField:'value',
			                        value:${map.mig_config_type },
			                        editable:false,
		                        	readonly: true,
									data:configType,
			                        required:true"/>Record Status:<input class="easyui-combobox" 
			                        id='mig_status_U1' name="mig_status" value="${map.mig_status }" 
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
		                        id='domain_U1' name="domain" value="${map.domain }"
		                        data-options="valueField:'code', textField:'value',
		                        editable:false,
		                        required: true,
								data: domains" />
		    			</td>
		    		</tr>
		    		<tr>
		    			<td>Pending Split File:</td>
		    			<td>
		    				<input id="mig_src_U1" name="mig_src" class="easyui-validatebox"
								data-options="required: true" type="text" style='width:99%;' value="${map.mig_src }" />
		    			</td>
		    		</tr>
		    		<tr>
		    			<td>Generate table I Rule:</td>
		    			<td>
		    				<textarea id="mig_dst_U1" name="mig_dst" style="font-size: 12px;" 
		    				rows="1" cols="60" onclick="update.open_dialog_migdst()" readonly="readonly">${map.mig_dst }</textarea>
		    			</td>
		    		</tr>		    		
		    		<tr>
		    			<td>Description:</td>
		    			<td><textarea id="mig_desc_U1" name="mig_desc" style="font-size: 12px;" rows="2" cols="60">${map.mig_desc }</textarea></td>
		    		</tr>
		    	</table>
		    </form>
	    </div>
    </div>
<script type="text/javascript">
var update = VIRTUE.update;

app.replacefield_gridrow = [];
app.splitfield_gridrow = [];

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

update.open_dialog_migdst = function(){
	var mig_dst = $('#mig_dst_U1').val();
	if( mig_dst != '' ){
// 		var dst ='tablei:00001|* field1;$0|00009+seq field9';
// 		var temp = dst.split(/:|\|\* |;\$0\||\+seq /);
// 		console.log(temp.length);
		var temp = mig_dst.split(/:|\|\* |;\$0\||\+seq /);
		if(temp.length==5){
			$('#tableiname_U1').textbox("setValue", temp[0] );
			$('#oldid').textbox("setValue", temp[1]);
			$('#oldidfieldname').textbox("setValue", temp[2]);
			$('#newid').textbox("setValue", temp[3]);
			$('#newidfieldname').textbox("setValue", temp[4]);
		}
	}
	$('#dlg-migdst_U1').dialog('open');
};

</script>
	<div data-options="region:'south',border:false" style="text-align:right;padding:5px 5px 5px 0;background:#F4F4F4;">
		<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" href="javascript:void(0)" onclick="javascript: update.submitForm()" style="width:80px">Submit</a>
		<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="javascript:void(0)" onclick="javascript:$('#win').dialog('close');" style="width:80px">Cancel</a>
	</div>
	
	<!-- 生成I表规则对话框   ########################### -->
	<div id="dlg-migdst_U1" class="easyui-dialog"
		title="Generate table I Rule" style="width:550px;height:180px;"
			data-options="
				modal:true,closed:true,
				iconCls: 'icon-edit',
				buttons: [{
					text:'Ok',
					iconCls:'icon-ok',
					handler:function(){
						if( ! $('#u_form-migdst').form('validate') )
						{
							return;
						}
						var migdst='';
						var formData = $('#u_form-migdst').form( 'getData');
						migdst = formData['tableiname']+':'+formData['oldid']+'|* '+formData['oldidfieldname']+';$0|'
							+formData['newid']+'+seq '+formData['newidfieldname'];
						$('#mig_dst_U1').val(migdst);
						$('#dlg-migdst_U1').dialog('close');
					}
				},{
					text:'Cancel',
					handler:function(){
						$('#dlg-migdst_U1').dialog('close');
					}
				}]
			">
		<form id="u_form-migdst" method="post" action="" style="margin:0;padding:0;">
	    	<table align="center" border="1">
<!-- 	    		<tr bgcolor="#B3DFDA"> -->
				<tr>
	    			<td>Table I Name:</td>
	    			<td colspan='3'>
	    				<input class="easyui-textbox" type="text" id="tableiname_U1" name="tableiname" data-options="required:true"></input>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>Old ID:</td>
	    			<td>
	    				<input class="easyui-textbox" type="text" id="oldid" name="oldid" data-options="required:true"></input>
	    			</td>
	    			<td>New ID:</td>
	    			<td>
	    				<input class="easyui-textbox" type="text" id="newid" name="newid" data-options="required:true"></input>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>Old ID Field Name:</td>
	    			<td>
	    				<input class="easyui-textbox" type="text" id="oldidfieldname" name="oldidfieldname" data-options="required:true"></input>
	    			</td>
	    			<td>New ID Field Name:</td>
	    			<td>
	    				<input class="easyui-textbox" type="text" id="newidfieldname" name="newidfieldname" data-options="required:true"></input>
	    			</td>
	    		</tr>
	    	</table>			
	    </form>
	</div>

</div>
</body>
</html>