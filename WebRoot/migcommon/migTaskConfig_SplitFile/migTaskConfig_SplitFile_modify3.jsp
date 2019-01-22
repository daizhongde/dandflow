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
		                        id='domain_U3' name="domain" value="${map.domain }"
		                        data-options="valueField:'code', textField:'value',
		                        editable:false,
		                        required: true,
								data: domains" />
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
		$('#splitrule_grid_U3').datagrid('loadData', app.splitfield_gridrow );
	}else{
		$('#splitrule_grid_U3').datagrid('loadData', [] );
	}
	$('#dlg-migwhere_U3').dialog('open');
};

/** 拆分表格编辑 */
var split_editIndex = undefined;
function split_endEditing() {
	if (split_editIndex == undefined) 
	{
		return true
	}
	if ($('#splitrule_grid_U3').datagrid('validateRow', split_editIndex)) 
	{
		$('#splitrule_grid_U3').datagrid('endEdit', split_editIndex);
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
			$('#splitrule_grid_U3').datagrid('selectRow', index).datagrid('beginEdit', index);
			split_editIndex = index;
		} else {
			$('#splitrule_grid_U3').datagrid('selectRow', split_editIndex);
		}
	}
};

function split_append() {
	if (split_endEditing()) {
		$('#splitrule_grid_U3').datagrid('appendRow', { fieldindex: '',fieldrule:''});
		split_editIndex = $('#splitrule_grid_U3').datagrid('getRows').length - 1;
		$('#splitrule_grid_U3').datagrid('selectRow', split_editIndex).datagrid('beginEdit',
				split_editIndex);
	}
};
function split_removeit() {
	if (split_editIndex == undefined) {return}
	$('#splitrule_grid_U3').datagrid('cancelEdit', split_editIndex).datagrid('deleteRow', split_editIndex)
	split_editIndex = undefined;
};
function split_accept() {
	if (split_endEditing()) {
		$('#splitrule_grid_U3').datagrid('acceptChanges');
	}
};
function split_reject() {
	$('#splitrule_grid_U3').datagrid('rejectChanges');
	split_editIndex = undefined;
};
</script>
	<div data-options="region:'south',border:false" style="text-align:right;padding:5px 5px 5px 0;background:#F4F4F4;">
		<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" href="javascript:void(0)" onclick="javascript: update.submitForm()" style="width:80px">Submit</a>
		<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="javascript:void(0)" onclick="javascript:$('#win').dialog('close');" style="width:80px">Cancel</a>
	</div>
	
	<!-- 拆分规则对话框    ###########################-->
	<div id="dlg-migwhere_U3" class="easyui-dialog"
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
						var rows = $('#splitrule_grid_U3').datagrid('getRows');
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
						$('#dlg-migwhere_U3').dialog('close');
					}
				},{
					text:'Cancel',
					handler:function(){
						$('#dlg-migwhere_U3').dialog('close');
					}
				}]
			">
		<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'center',border: false">
				<table id="splitrule_grid_U3" class="easyui-datagrid" 
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