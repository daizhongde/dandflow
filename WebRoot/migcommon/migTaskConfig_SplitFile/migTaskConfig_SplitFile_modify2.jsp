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
		                        id='domain_U2' name="domain" value="${map.domain }"
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

app.replaceidfield_gridrow = [];
app.splitfield_gridrow = [];

update.submitForm = function (){
	if( ! $('#u_form').form("validate") )
	{
		return;
	}

	var formData = $('#u_form').form( 'getData');
// 	if( formData["mig_dst_conn"].replace(/ /g, "")=="" || formData["mig_where"].replace(/ /g, "")=="" ){
// 		alert("ID Replacement Rule and Split Rule all are requird!");
// 		return;
// 	}
	
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

update.open_dialog_migdstconn = function(){
	var mig_dst_conn = $('#mig_dst_conn_U').val();
	if( mig_dst_conn.length>2 
			&& mig_dst_conn.indexOf(':') !=-1 
			&& mig_dst_conn.indexOf(';') !=-1){
		mig_dst_conn = mig_dst_conn.substring(0,mig_dst_conn.length-1);
				
		var temp = mig_dst_conn.split(/;/);
		
		app.replaceidfield_gridrow = [];
		for(var i=0, j=temp.length; i<j; i++ ){
			var row = {};
			row.fieldValue = temp[i].split(/:/)[0];
			row.tableiName = temp[i].split(/:/)[1];
			for(var m=0,n=app.cbbdata.length; m<n; m++ ){
				if(row.fieldValue == app.cbbdata[m].value){
					row.fieldName = app.cbbdata[m].label;
					break;
				}
			}
			app.replaceidfield_gridrow.push(row);
		}
		
		//loadlocaldata
		$('#idreplacerule_grid_U2').datagrid('loadData', app.replaceidfield_gridrow );
		
	}else{
		$('#idreplacerule_grid_U2').datagrid('loadData', [] );
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
				row.fieldValue = 0;
				row.fieldName = 0;
				row.fieldRule = arr[0];
			}else if(arr.length==2){
				row.fieldValue = arr[0];
				row.fieldRule = arr[1];
				for(var m=0,n=app.cbbdata.length; m<n; m++ ){
					if(row.fieldValue == app.cbbdata[m].value){
						row.fieldName = app.cbbdata[m].label;
						break;
					}
				}
			}else{
				alert("error!")
				return;
			}
			app.splitfield_gridrow.push(row);
		}
		
		//loadlocaldata
		$('#splitrule_grid_U2').datagrid('loadData', app.splitfield_gridrow );
	}else{
		$('#splitrule_grid_U2').datagrid('loadData', [] );
	}
	$('#dlg-migwhere_U2').dialog('open');
};

/** 替换表格编辑 */
	var idreplacerule_U2_editIndex = undefined;
	function idreplacerule_U2_endEditing() {
		if (idreplacerule_U2_editIndex == undefined) {
			return true
		}
		if ($('#idreplacerule_grid_U2').datagrid('validateRow', idreplacerule_U2_editIndex)) {
			var ed = $('#idreplacerule_grid_U2').datagrid('getEditor', {
				index : idreplacerule_U2_editIndex,
				field : 'fieldValue'
			});
			var fieldName = $(ed.target).combobox('getText');
			$('#idreplacerule_grid_U2').datagrid('getRows')[idreplacerule_U2_editIndex]['fieldName'] = fieldName;
			$('#idreplacerule_grid_U2').datagrid('endEdit', idreplacerule_U2_editIndex);
			idreplacerule_U2_editIndex = undefined;
			return true;
		} else {
			return false;
		}
	}
	function idreplacerule_U2_onClickRow(index) {
		if (idreplacerule_U2_editIndex != index) {
			if (idreplacerule_U2_endEditing()) {
				$('#idreplacerule_grid_U2').datagrid('selectRow', index).datagrid('beginEdit', index);
				idreplacerule_U2_editIndex = index;
			} else {
				$('#idreplacerule_grid_U2').datagrid('selectRow', idreplacerule_U2_editIndex);
			}
		}
	}
	
	function idreplacerule_U2_append() {
		if (idreplacerule_U2_endEditing()) {
			$('#idreplacerule_grid_U2').datagrid('appendRow', {fieldValue: '',fieldName: '', tableiName:''});
			idreplacerule_U2_editIndex = $('#idreplacerule_grid_U2').datagrid('getRows').length - 1;
			$('#idreplacerule_grid_U2').datagrid('selectRow', idreplacerule_U2_editIndex).datagrid('beginEdit',
					idreplacerule_U2_editIndex);
		}
	}
	function idreplacerule_U2_removeit() {
		if (idreplacerule_U2_editIndex == undefined) {return}
		$('#idreplacerule_grid_U2').datagrid('cancelEdit', idreplacerule_U2_editIndex).datagrid('deleteRow', idreplacerule_U2_editIndex)
		idreplacerule_U2_editIndex = undefined;
	}
	function idreplacerule_U2_accept() {
		if (idreplacerule_U2_endEditing()) {
			$('#idreplacerule_grid_U2').datagrid('acceptChanges');
		}
	}
	function idreplacerule_U2_reject() {
		$('#idreplacerule_grid_U2').datagrid('rejectChanges');
		idreplacerule_U2_editIndex = undefined;
	}
	function getChanges() {
		var rows = $('#idreplacerule_grid_U2').datagrid('getChanges');
		alert(rows.length + ' rows are changed!');
	}

/** 拆分表格编辑 */
var splitU2_editIndex = undefined;
function splitU2_endEditing() {
	if (splitU2_editIndex == undefined) 
	{
		return true
	}
	if ($('#splitrule_grid_U2').datagrid('validateRow', splitU2_editIndex)) 
	{
		var ed = $('#splitrule_grid_U2').datagrid('getEditor', {
			index : splitU2_editIndex,
			field : 'fieldValue'
		});
		var fieldName = $(ed.target).combobox('getText');
		$('#splitrule_grid_U2').datagrid('getRows')[splitU2_editIndex]['fieldName'] = fieldName;
		$('#splitrule_grid_U2').datagrid('endEdit', splitU2_editIndex);
		splitU2_editIndex = undefined;
		return true;
	}
	else 
	{
		return false;
	}
};
function splitU2_onClickRow(index) {
	if (splitU2_editIndex != index) {
		if (splitU2_endEditing()) {
			$('#splitrule_grid_U2').datagrid('selectRow', index).datagrid('beginEdit', index);
			splitU2_editIndex = index;
		} else {
			$('#splitrule_grid_U2').datagrid('selectRow', splitU2_editIndex);
		}
	}
};

function splitU2_append() {
	if (splitU2_endEditing()) {
		$('#splitrule_grid_U2').datagrid('appendRow', { fieldValue: '',fieldName: '',fieldRule:''});
		splitU2_editIndex = $('#splitrule_grid_U2').datagrid('getRows').length - 1;
		$('#splitrule_grid_U2').datagrid('selectRow', splitU2_editIndex).datagrid('beginEdit',
				splitU2_editIndex);
	}
};
function splitU2_removeit() {
	if (splitU2_editIndex == undefined) {return}
	$('#splitrule_grid_U2').datagrid('cancelEdit', splitU2_editIndex).datagrid('deleteRow', splitU2_editIndex)
	splitU2_editIndex = undefined;
};
function splitU2_accept() {
	if (splitU2_endEditing()) {
		$('#splitrule_grid_U2').datagrid('acceptChanges');
	}
};
function splitU2_reject() {
	$('#splitrule_grid_U2').datagrid('rejectChanges');
	splitU2_editIndex = undefined;
};
</script>
	<div data-options="region:'south',border:false" style="text-align:right;padding:5px 5px 5px 0;background:#F4F4F4;">
		<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" href="javascript:void(0)" onclick="javascript: update.submitForm()" style="width:80px">Submit</a>
		<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="javascript:void(0)" onclick="javascript:$('#win').dialog('close');" style="width:80px">Cancel</a>
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

						var rows= $('#idreplacerule_grid_U2').datagrid('getRows');
						for(var i=0, j = rows.length; i<j; i++ ){
								if( isNvl( rows[i].fieldValue ) || isNvl( rows[i].tableiName ) ){
								alert('Field name can\'t be null!');
								return;
							}
							migdstconn += rows[i].fieldValue + ':' + rows[i].tableiName +';';
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
<!-- 			<div data-options="region:'north',split:false,border:false" style="height:50px;padding:5px 0 0 0px"> -->
<!-- 				<table id="addTable0" width="100%" cellpadding="2"> -->
<!-- 					<tr> -->
<!-- 						<td style='width:130px'>Table I Name</td> -->
<!-- 						<td><input id="tableiName_idreplace" class="easyui-textbox" -->
<!-- 							data-options="required: true" type="text" name="tableiName" -->
<!-- 							style='width:90%;' value="" /></td> -->
<!-- 					</tr> -->
<!-- 				</table> -->
<!-- 			</div> -->
			<div data-options="region:'center',border: false">
				<table id="idreplacerule_grid_U2" class="easyui-datagrid" 
					data-options="rownumbers:true,
					fit : true,
					onClickCell : idreplacerule_U2_onClickRow,
					singleSelect:true,
					showHeader: true, 
					showFooter: false, 
					selectOnCheck:true,
					checkOnSelect:false,
					idField: 'fieldValue',
					url:'',
					method:'post',
					toolbar: [ {
						text : 'New',
						disabled: false,
						iconCls : 'icon-add',
						handler : function() {
							idreplacerule_U2_append();
							return false;
						}
					}, '-', {
						text : 'Remove',
						disabled: false,
						iconCls : 'icon-remove',
						handler : function() {
							idreplacerule_U2_removeit();
							return false;
						}
					} , '-', {
						text : 'Save',
						disabled: false,
						iconCls : 'icon-save',
						handler : function() {
							idreplacerule_U2_accept();
							return false;
						}
					}, '-', {
						text : 'Cancel',
						disabled: false,
						iconCls : 'icon-undo',
						handler : function() {
							idreplacerule_U2_reject();
							return false;
						}
					}]">
					<thead>
						<tr>
<!-- 							<th data-options="field:'ck',checkbox:true"></th> -->
							<th data-options="field:'tableiName',width:200,editor : { type : 'text' }">Table I Name</th>
							<th data-options="field:'fieldValue',width:200,
							formatter : function( value, row ) { return row.fieldName; },
							editor : { type : 'combobox',
								options:{
									valueField: 'value',
									textField: 'label',
									editable : false,
									data: app.cbbdata.slice(1)
								} }">Replace Field</th>
						</tr>
					</thead>
				</table>
			</div>
			</form>
		</div>
	</div>
	
	<!-- 拆分规则对话框    ###########################-->
	<div id="dlg-migwhere_U2" class="easyui-dialog"
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
						var rows = $('#splitrule_grid_U2').datagrid('getRows');
						console.log( $.toJSON(rows) );
						for(var i=0, j = rows.length; i<j; i++ ){
							if( isNvl( rows[i].fieldValue ) || isNvl( rows[i].fieldRule ) ){
								console.log( $.toJSON(rows[i]) );
								alert('Neither Field index nor Field split rule can be null!');
								return;
							}
							if(rows[i].fieldValue == 0 ){
								migwhere += rows[i].fieldRule +':'
							}else{
								migwhere += rows[i].fieldValue +'|'+rows[i].fieldRule +':'
							}
						}
						$('#mig_where_U').val( migwhere );
						$('#dlg-migwhere_U2').dialog('close');
					}
				},{
					text:'Cancel',
					handler:function(){
						$('#dlg-migwhere_U2').dialog('close');
					}
				}]
			">
		<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'center',border: false">
				<table id="splitrule_grid_U2" class="easyui-datagrid" 
					data-options="rownumbers:true,
					fit : true,
					onClickCell : splitU2_onClickRow,
					singleSelect:true,
					showHeader: true, 
					showFooter: false, 
					selectOnCheck:true,
					checkOnSelect:false,
					idField: 'fieldValue',
					url:'',
					method:'post',
					toolbar: [ {
						text : 'New',
						disabled: false,
						iconCls : 'icon-add',
						handler : function() {
							splitU2_append();
							return false;
						}
					}, '-', {
						text : 'Remove',
						disabled: false,
						iconCls : 'icon-remove',
						handler : function() {
							splitU2_removeit();
							return false;
						}
					} , '-', {
						text : 'Save',
						disabled: false,
						iconCls : 'icon-save',
						handler : function() {
							splitU2_accept();
							return false;
						}
					}, '-', {
						text : 'Cancel',
						disabled: false,
						iconCls : 'icon-undo',
						handler : function() {
							splitU2_reject();
							return false;
						}
					}]">
					<thead>
						<tr>
<!-- 							<th data-options="field:'ck',checkbox:true"></th> -->
							<th data-options="field:'fieldValue',width:200,
							formatter : function( value, row ) { return row.fieldName; },
							editor : { type : 'combobox',
								options:{
									valueField: 'value',
									textField: 'label',
									editable : false,
									data: app.cbbdata
								} }">Split Field</th>
							<th data-options="field:'fieldRule',width:200,editor : { type : 'text' }">Rule</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
	</div>
	
</div>
</body>
</html>