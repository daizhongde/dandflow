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
		    			<td>Pending Split File:</td>
		    			<td>
		    				<input id="mig_src_U" name="mig_src" class="easyui-validatebox"
								data-options="required: true" type="text" style='width:99%;' value="${map.mig_src }" />
		    			</td>
		    		</tr>
		    		<tr>
		    			<td>Generate table I Rule:</td>
		    			<td>
		    				<textarea id="mig_dst_U" name="mig_dst" style="font-size: 12px;" 
		    				rows="1" cols="60" onclick="update.open_dialog_migdst()" readonly="readonly">${map.mig_dst }</textarea>
		    			</td>
		    		</tr>
		    		<tr>
		    			<td>ID Replacement Rule:</td>
		    			<td>
		    				<textarea id="mig_dst_conn_U" name="mig_dst_conn" style="font-size: 12px;" 
		    				rows="1" cols="60" onclick="update.open_dialog_migdstconn()" readonly="readonly">${map.mig_dst_conn }</textarea>
		    			</td>
	    			</tr>
		    		<tr>
		    			<td>File Split Rule:</td>
		    			<td>
		    				<textarea id="mig_where_U" name="mig_where" style="font-size: 12px;" 
		    				rows="2" cols="60" onclick="update.open_dialog_migwhere()" readonly="readonly" >${map.mig_where }</textarea>
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
	var mig_dst = $('#mig_dst_U').val();
	if( mig_dst != '' ){
// 		var dst ='tablei:00001|* field1;$0|00009+seq field9';
// 		var temp = dst.split(/:|\|\* |;\$0\||\+seq /);
// 		console.log(temp.length);
		var temp = mig_dst.split(/:|\|\* |;\$0\||\+seq /);
		if(temp.length==5){
			$('#tableiname_U').textbox("setValue", temp[0] );
			$('#oldid').textbox("setValue", temp[1]);
			$('#newid').textbox("setValue", temp[2]);
			$('#oldidfieldname').textbox("setValue", temp[3]);
			$('#newidfieldname').textbox("setValue", temp[4]);
		}
	}
	$('#dlg-migdst_U').dialog('open');
};
update.open_dialog_migdstconn = function(){
	var mig_dst_conn = $('#mig_dst_conn_U').val();
	console.log("mig_dst_conn:"+mig_dst_conn);
	if( mig_dst_conn.length>2 
			&& mig_dst_conn.indexOf(':') !=-1 
			&& mig_dst_conn.indexOf(';') !=-1){
		mig_dst_conn = mig_dst_conn.substring(0,mig_dst_conn.length-1);
		
		console.log("mig_dst_conn.length>2");
		
		var temp = mig_dst_conn.split(/;/);
		var tableiname = temp[0].split(/:/)[0];
		
		app.replacefield_gridrow = [];
		for(var i=0, j=temp.length; i<j; i++ ){
			var row = {};
			row.fieldname = temp[i].split(/:/)[1];
			app.replacefield_gridrow.push(row);
		}
		$('#tableiname_idreplace').textbox("setValue", tableiname );
		
		//loadlocaldata
		$('#replacefield_grid').datagrid('loadData', app.replacefield_gridrow );
		
	}
	$('#dlg-migds_conn_U').dialog('open');
};
update.open_dialog_migwhere = function(){
	var mig_where = $('#mig_where_U').val();
	if( mig_where != '' 
		&& mig_where.indexOf(':') !=-1 
		&& mig_where.indexOf('$') !=-1){
		mig_where = mig_where.substring(0,mig_where.length-1);
		
		var temp = mig_where.split(/:/);
				
		app.splitfield_gridrow = [];
		for(var i=0, j=temp.length; i<j; i++ ){
			var row = {};
			var arr = temp[i].split(/\|/);
			
			if(arr.length==1){
				row.fieldindex = 0;
				row.fieldrule = arr[0];
			}else if(arr.length==2){
				row.fieldindex = arr[0].replace("$","");
				row.fieldrule = arr[1];
			}else{
				alert("error!")
				return;
			}
			app.splitfield_gridrow.push(row);
		}
		
		//loadlocaldata
		$('#splitfiled_grid').datagrid('loadData', app.splitfield_gridrow );
	}
	$('#dlg-migwhere').dialog('open');
};

/** 替换表格编辑 */
var replacefield_editIndex = undefined;
function replacefield_endEditing() {
	if (replacefield_editIndex == undefined) 
	{
		return true
	}
	if ($('#replacefield_grid').datagrid('validateRow', replacefield_editIndex)) 
	{
		$('#replacefield_grid').datagrid('endEdit', replacefield_editIndex);
		replacefield_editIndex = undefined;
		return true;
	}
	else 
	{
		return false;
	}
};
function replacefield_onClickRow(index) {
	if (replacefield_editIndex != index) {
		if (replacefield_endEditing()) {
			$('#replacefield_grid').datagrid('selectRow', index).datagrid('beginEdit', index);
			replacefield_editIndex = index;
		} else {
			$('#replacefield_grid').datagrid('selectRow', replacefield_editIndex);
		}
	}
};

function replacefield_append() {
	if (replacefield_endEditing()) {
		$('#replacefield_grid').datagrid('appendRow', { fieldname: ''});
		replacefield_editIndex = $('#replacefield_grid').datagrid('getRows').length - 1;
		$('#replacefield_grid').datagrid('selectRow', replacefield_editIndex).datagrid('beginEdit',
				replacefield_editIndex);
	}
};
function replacefield_removeit() {
	if (replacefield_editIndex == undefined) {return}
	$('#replacefield_grid').datagrid('cancelEdit', replacefield_editIndex).datagrid('deleteRow', replacefield_editIndex)
	replacefield_editIndex = undefined;
};
function replacefield_accept() {
	if (replacefield_endEditing()) {
		$('#replacefield_grid').datagrid('acceptChanges');
	}
};
function replacefield_reject() {
	$('#replacefield_grid').datagrid('rejectChanges');
	replacefield_editIndex = undefined;
};

/** 拆分表格编辑 */
var split_editIndex = undefined;
function split_endEditing() {
	if (split_editIndex == undefined) 
	{
		return true
	}
	if ($('#splitfiled_grid').datagrid('validateRow', split_editIndex)) 
	{
		$('#splitfiled_grid').datagrid('endEdit', split_editIndex);
		split_editIndex = undefined;
		return true;
	}
	else 
	{
		return false;
	}
};
function split_onClickRow(index) {
	if (split_editIndex != index) {
		if (split_endEditing()) {
			$('#splitfiled_grid').datagrid('selectRow', index).datagrid('beginEdit', index);
			split_editIndex = index;
		} else {
			$('#splitfiled_grid').datagrid('selectRow', split_editIndex);
		}
	}
};

function split_append() {
	if (split_endEditing()) {
		$('#splitfiled_grid').datagrid('appendRow', { fieldindex: '',fieldrule:''});
		split_editIndex = $('#splitfiled_grid').datagrid('getRows').length - 1;
		$('#splitfiled_grid').datagrid('selectRow', split_editIndex).datagrid('beginEdit',
				split_editIndex);
	}
};
function split_removeit() {
	if (split_editIndex == undefined) {return}
	$('#splitfiled_grid').datagrid('cancelEdit', split_editIndex).datagrid('deleteRow', split_editIndex)
	split_editIndex = undefined;
};
function split_accept() {
	if (split_endEditing()) {
		$('#splitfiled_grid').datagrid('acceptChanges');
	}
};
function split_reject() {
	$('#splitfiled_grid').datagrid('rejectChanges');
	split_editIndex = undefined;
};
</script>
	<div data-options="region:'south',border:false" style="text-align:right;padding:5px 5px 5px 0;background:#F4F4F4;">
		<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" href="javascript:void(0)" onclick="javascript: update.submitForm()" style="width:80px">Submit</a>
		<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="javascript:void(0)" onclick="javascript:$('#win').dialog('close');" style="width:80px">Cancel</a>
	</div>
	
	<!-- 生成I表规则对话框   ########################### -->
	<div id="dlg-migdst_U" class="easyui-dialog"
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
						$('#mig_dst_U').val(migdst);
						$('#dlg-migdst_U').dialog('close');
					}
				},{
					text:'Cancel',
					handler:function(){
						$('#dlg-migdst_U').dialog('close');
					}
				}]
			">
		<form id="u_form-migdst" method="post" action="" style="margin:0;padding:0;">
	    	<table align="center" border="1">
<!-- 	    		<tr bgcolor="#B3DFDA"> -->
				<tr>
	    			<td>Table I Name:</td>
	    			<td colspan='3'>
	    				<input class="easyui-textbox" type="text" id="tableiname_U" name="tableiname" data-options="required:true"></input>
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
	
	<!-- ID 替换规则对话框    ###########################-->
	<div id="dlg-migds_conn_U" class="easyui-dialog"
		title="ID Replacement Rule" style="width:550px;height:320px;"
			data-options="
				modal:true,closed:true,
				iconCls: 'icon-edit',
				buttons: [{
					text:'Ok',
					iconCls:'icon-ok',
					handler:function(){
						if( ! $('#u_migdstconn_form').form('validate') )
						{
							return;
						}
						
						//assemble
						var migdstconn='';
						var formData = $('#u_migdstconn_form').form( 'getData');
						var tableiname = formData['tableiname'];
						var rows= $('#replacefield_grid').datagrid('getRows');
						for(var i=0, j = rows.length; i<j; i++ ){
							if( isNvl( rows[i].fieldname ) ){
								alert('Field name can\'t be null!');
								return;
							}
							migdstconn += tableiname + ':' + rows[i].fieldname+';';
						}
						$('#mig_dst_conn_U').val( migdstconn );
						$('#dlg-migds_conn_U').dialog('close');
					}
				},{
					text:'Cancel',
					handler:function(){
						$('#dlg-migds_conn_U').dialog('close');
					}
				}]
			">
		<div class="easyui-layout" data-options="fit:true">
			<form id="u_migdstconn_form" method="post" style="margin:0;padding:0;">
			<div data-options="region:'north',split:false,border:false" style="height:50px;padding:5px 0 0 0px">
				<table id="addTable0" width="100%" cellpadding="2">
					<tr>
						<td style='width:130px'>Table I Name</td>
						<td><input id="tableiname_idreplace" class="easyui-textbox"
							data-options="required: true" type="text" name="tableiname"
							style='width:90%;' value="" /></td>
					</tr>
				</table>
			</div>
			<div data-options="region:'center',border: false">
				<table id="replacefield_grid" class="easyui-datagrid" 
					data-options="rownumbers:true,
					fit : true,
					onClickCell : replacefield_onClickRow,
					singleSelect:true,
					showHeader: true, 
					showFooter: false, 
					selectOnCheck:true,
					checkOnSelect:false,
					idField: 'fieldname',
					url:'',
					method:'post',
					toolbar: [ {
						text : 'New',
						disabled: false,
						iconCls : 'icon-add',
						handler : function() {
							replacefield_append();
							return false;
						}
					}, '-', {
						text : 'Remove',
						disabled: false,
						iconCls : 'icon-remove',
						handler : function() {
							replacefield_removeit();
							return false;
						}
					} , '-', {
						text : 'Save',
						disabled: false,
						iconCls : 'icon-save',
						handler : function() {
							replacefield_accept();
							return false;
						}
					}, '-', {
						text : 'Cancel',
						disabled: false,
						iconCls : 'icon-undo',
						handler : function() {
							replacefield_reject();
							return false;
						}
					}]">
					<thead>
						<tr>
<!-- 							<th data-options="field:'ck',checkbox:true"></th> -->
							<th data-options="field:'fieldname',width:200,editor : { type : 'text' }">Replace Field</th>
						</tr>
					</thead>
				</table>
			</div>
			</form>
		</div>
	</div>
	
	<!-- 拆分规则对话框    ###########################-->
	<div id="dlg-migwhere" class="easyui-dialog"
		title="Split File Rule" style="width:550px;height:320px;"
			data-options="
				modal:true,closed:true,
				iconCls: 'icon-edit',
				buttons: [{
					text:'Ok',
					iconCls:'icon-ok',
					handler:function(){
						//assemble
						var migwhere = '';
						var rows = $('#splitfiled_grid').datagrid('getRows');
						console.log( $.toJSON(rows) );
						for(var i=0, j = rows.length; i<j; i++ ){
							if( isNvl( rows[i].fieldindex ) || isNvl( rows[i].fieldrule ) ){
								console.log( $.toJSON(rows[i]) );
								alert('Neither Field index nor Field split rule can be null!');
								return;
							}
							if(rows[i].fieldindex == 0 ){
								migwhere += rows[i].fieldrule +':'
							}else{
								migwhere += '$'+rows[i].fieldindex +'|'+rows[i].fieldrule +':'
							}
						}
						$('#mig_where_U').val( migwhere );
						$('#dlg-migwhere').dialog('close');
					}
				},{
					text:'Cancel',
					handler:function(){
						$('#dlg-migwhere').dialog('close');
					}
				}]
			">
		<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'center',border: false">
				<table id="splitfiled_grid" class="easyui-datagrid" 
					data-options="rownumbers:true,
					fit : true,
					onClickCell : split_onClickRow,
					singleSelect:true,
					showHeader: true, 
					showFooter: false, 
					selectOnCheck:true,
					checkOnSelect:false,
					idField: 'fieldname',
					url:'',
					method:'post',
					toolbar: [ {
						text : 'New',
						disabled: false,
						iconCls : 'icon-add',
						handler : function() {
							split_append();
							return false;
						}
					}, '-', {
						text : 'Remove',
						disabled: false,
						iconCls : 'icon-remove',
						handler : function() {
							split_removeit();
							return false;
						}
					} , '-', {
						text : 'Save',
						disabled: false,
						iconCls : 'icon-save',
						handler : function() {
							split_accept();
							return false;
						}
					}, '-', {
						text : 'Cancel',
						disabled: false,
						iconCls : 'icon-undo',
						handler : function() {
							split_reject();
							return false;
						}
					}]">
					<thead>
						<tr>
<!-- 							<th data-options="field:'ck',checkbox:true"></th> -->
							<th data-options="field:'fieldindex',width:200,
							editor : { type : 'combobox',
								options:{
									valueField: 'label',
									textField: 'value',
									editable : false,
									data: [
									{ label: '0', value: '0' },
									{ label: '1', value: '1' },
									{ label: '2', value: '2' },
									{ label: '3', value: '3' },
									{ label: '4', value: '4' },
									{ label: '5', value: '5' },
									{ label: '6', value: '6' },
									{ label: '7', value: '7' },
									{ label: '8', value: '8' },
									{ label: '9', value: '9' },
									{ label: '10', value: '10' },
									{ label: '11', value: '11' },
									{ label: '12', value: '12' },
									{ label: '13', value: '13' },
									{ label: '14', value: '14' },
									{ label: '15', value: '15' },
									{ label: '16', value: '16' },
									{ label: '17', value: '17' },
									{ label: '18', value: '18' },
									{ label: '19', value: '19' },
									{ label: '20', value: '20' },
									{ label: '21', value: '21' },
									{ label: '22', value: '22' },
									{ label: '23', value: '23' },
									{ label: '24', value: '24' },
									{ label: '25', value: '25' },
									{ label: '26', value: '26' },
									{ label: '27', value: '27' },
									{ label: '28', value: '28' },
									{ label: '29', value: '29' },
									{ label: '30', value: '30' }]
								} }">Split Field</th>
							<th data-options="field:'fieldrule',width:200,editor : { type : 'text' }">Rule</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
	</div>
	
</div>
</body>
</html>