<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>任务设置excute bin(shell控件)</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	</head>
	<body>
	<div class="easyui-layout" fit="true">
	<form id="modifyTask_form" method="post" style="margin:0;padding:0;">
		<div data-options="region:'north'" style="height:50px">
			<table id="utaskTable0" width="100%" cellpadding="2">
				<tr>
					<td style='width:130px'></td>
					<td><input id="utaskId" class="easyui-validatebox"
						data-options="required: true" type="hidden" name="taskId"
						style='width:90%;' value="" /></td>
				</tr>
				<tr>
					<td style='width:130px'>Task Name</td>
					<td><input id="utaskName" class="easyui-validatebox"
						data-options="required: true" type="text" name="taskName"
						style='width:90%;' value="" /></td>
				</tr>
			</table>
		</div>
		<div data-options="region:'center',border:false" title=""
			style="overflow:hidden" noheader="true">
			<div class="easyui-tabs" fit="true" >
				<div title="Task specification" style="padding:10px">
					<table id="utaskTable1" width="100%" cellpadding="2">
						
						<tr>
							<td style='width:120px'>Author</td>
							<td><input id="utaskAuthor" class="easyui-validatebox"
								data-options="required : false" type="text" name="taskAuthor"
								style='width:90%;' value="" readonly="readonly" /></td>
						</tr>
						<tr>
							<td style='width:120px'>Remark</td>
							<td><textarea id="utaskRemark" rows="5" style='width:90%;'
									name="taskRemark">${pojo.taskRemark}</textarea></td>
						</tr>
						<tr>
							<td style='width:120px'>Last Update Time</td>
							<td><input id="utaskUpdate" class="easyui-validatebox"
								data-options="required : false" type="text" name="taskUpdate"
								style='width:90%;' value="" readonly="readonly" /></td>
						</tr>
					</table>
				</div>
				<div title="Variables" style="padding:10px">
					<table id="taskparam_grid"></table>
				</div>
				<div title="Parameters" style="padding:10px">
					<table id="comparaTable" width="100%" cellpadding="2">
<script type="text/javascript">
var taskpojo = ${pojo};
jQuery(document).ready(function() {
	$("#modifyTask_form").form("load", taskpojo);
});

var controlTPLS = ${json };

app.dryrunflag = false;
app.dbconnectionurl = false;

// app.SPLIT_FILE
// app.LOAD_DATA
// app.OUT_DATA_FILE
// app.OUT_DATA_BASE
// app.EXCUTE_SQL
// app.EXCUTE_BIN
// app.AUDIT  //whether exists legality audit business param, legality

// app.CONTROL = false;
// app.auditcflag = false;//quantity /consistence / 
// app.auditfflag = false;//quanlity / field

for( var i=0,j = controlTPLS.length; i<j; i++ ){
	var controlTPL = controlTPLS[i];
	var inputStr = "";
	
	/** according input type write html element **/

	switch(controlTPL.inputType ){
		case 1:
			inputStr = "<input class='easyui-validatebox' type='text' name='"+ controlTPL.paraId+"' style='width:90%;' value='"+ nvl(controlTPL.defValue) +"'/>";
			break;
		case 2:
// 			console.log("controlTPL.paraName:"+controlTPL.paraName+",controlTPL.controlId:"+controlTPL.controlId)
			inputStr += "<textarea id="+controlTPL.paraName+"' name='"+ controlTPL.paraId+"' style='height:240px;width:100%;font-size: 12px;'>"+ nvl(controlTPL.defValue) +"</textarea>";
			break;
		case 3:
			inputStr = "<input id="+controlTPL.paraName+"' class='easyui-combobox' "
				+"name='"+ controlTPL.paraId+"' value='"+ nvl(controlTPL.defValue) +"' data-options=\"valueField:'code',textField:'value',"
				+"editable:false,url:'../cbb/dicCBBgetDic2ByType.action?dicType="+controlTPL.paraName+"',method:'post',required:true\"/>";
			break;
		case 4:
			app.dbconnectionurl = true;
			inputStr = "<input id='dbconnectionurl' name='"+ controlTPL.paraId+"' value='"+ nvl(controlTPL.defValue) +"'  width='400px'/>";
			break;
		case 101:
			app.dryrunflag = true;
			inputStr = "<input id='fares_dryrun_id' name='"+ controlTPL.paraId+"' value='"+ nvl(controlTPL.defValue) +"'/>";
			break;
		default: 
			inputStr = "<input class='easyui-validatebox' type='text' name='"+ controlTPL.paraId+"' style='width:90%;' value='"+controlTPL.defValue+"'/>";
	}
	
	var row = "<tr>"
		+"<td style='width:120px'>"+ controlTPL.paraName+":</td>"
		+"<td>"+inputStr+"</td>"
		+"</tr>";
	
	$("#comparaTable").append(row);
}
</script>
					</table>
				</div>
			</div>
		</div>
	</form>
	
<div id="chooseconfig-dlg6"></div>

	</div>
<script type="text/javascript">

if(app.dryrunflag){
	$('#fares_dryrun_id').combogrid({
		url : getContextPath()+"/query/commonCBBQUERYdfind.action",
		queryParams : {
			tableName : "v_dryrun_config",
			columns: "mig_dryrun_name"
		},
		editable:false,
		panelWidth : 500,
		panelHeight : 200,
		idField : 'mig_dryrun_id',
		textField : 'mig_dryrun_name',
		pagination : true,
		fitColumns : false,
		required : true,
		rownumbers : true,
		mode : 'remote',
		delay : 500,
		sortName : 'mig_dryrun_name',
		sortOrder : 'asc',
		pageSize : 5,
		pageList : [ 5, 10 ],
		columns : [ [
			{ title : 'ID', field : 'mig_dryrun_id',width : 90, sortable: true }, 
			{ title : 'Dry Run名称', field : 'mig_dryrun_name', width : 120, sortable: true }, 
			{ title : '备注', field : 'remark',  width : 300 } 
		] ]
	});
};
if(app.dbconnectionurl){
	$('#dbconnectionurl').combogrid({
		url : getContextPath()+"/query/commonCBBQUERYdfind.action",
		panelWidth : 500,
		panelHeight : 200,
		idField : 'url',
		textField : 'name',
		pagination : false,
		fitColumns : false,
		required : true,
		rownumbers : true,
		editable: false,
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
};

/** 任务参数信息工具栏信息 */
app.taskparam_toolbar = [ {
	text : 'New',
	disabled: false,
	iconCls : 'icon-add',
	handler : function() {
		taskparam_append();
		return false;
	}
}, '-', {
	text : 'Remove',
	disabled: false,
	iconCls : 'icon-remove',
	handler : function() {
		taskparam_removeit();
		return false;
	}
} , '-', {
	text : 'Save',
	disabled: false,
	iconCls : 'icon-save',
	handler : function() {
		taskparam_accept();
		return false;
	}
}, '-', {
	text : 'Cancel',
	disabled: false,
	iconCls : 'icon-undo',
	handler : function() {
		taskparam_reject();
		return false;
	}
}];
app.taskparaloaded=false;
// 任务参数-表格
$('#taskparam_grid').datagrid({
	title : '',
	toolbar : app.taskparam_toolbar,
	width : 938,
	height : 720,
	nowrap : true,
	striped : true,
	fit : true,
	collapsible : true,
	url: getContextPath()+"/query/migJobParaJEasyUIQUERYdfind.action",
// 	idField : "nodeId",
	multiSort : true,
	singleSelect : true,
	onClickCell : taskparam_onClickRow,
	onLoadSuccess: function(){
		app.taskparaloaded=true;
	},
	columns : [ [ 
		{ title : 'ID', field : 'nodeId', hidden: true }, 
		{ title : 'Variable', field : 'para', editor : { type : 'text'}, width : 90 }, 
		{ title : 'Value', field : 'paraValue', editor : { type : 'text'}, width : 90 }, 
		{ title : 'Type', field : 'paraType', editor : { type : 'text'}, width : 90 }, 
		{ title : 'Chinese Name', field : 'paraName', editor : { type : 'text'}, width : 90 } 
	] ],
	pagination : false,
	rownumbers : false,
	queryParams : {
		jdata : encodeURI($.toJSON({ act: VIRTUE.act.QUERY, condition: {nodeId: taskpojo.taskId }, operator : {} })),
	}
});
		
/** 任务参数表格编辑 */
var taskparam_editIndex = undefined;
function taskparam_endEditing() {
	if (taskparam_editIndex == undefined) 
	{
		return true
	}
	if ($('#taskparam_grid').datagrid('validateRow', taskparam_editIndex)) 
	{
		$('#taskparam_grid').datagrid('endEdit', taskparam_editIndex);
		taskparam_editIndex = undefined;
		return true;
	}
	else 
	{
		return false;
	}
};
function taskparam_onClickRow(index) {
	if (taskparam_editIndex != index) {
		if (taskparam_endEditing()) {
			$('#taskparam_grid').datagrid('selectRow', index).datagrid('beginEdit', index);
			taskparam_editIndex = index;
		} else {
			$('#taskparam_grid').datagrid('selectRow', taskparam_editIndex);
		}
	}
};

function taskparam_append() {
	if (taskparam_endEditing()) {
		$('#taskparam_grid').datagrid('appendRow', { nodeId: taskpojo.taskId });
		taskparam_editIndex = $('#taskparam_grid').datagrid('getRows').length - 1;
		$('#taskparam_grid').datagrid('selectRow', taskparam_editIndex).datagrid('beginEdit',
				taskparam_editIndex);
	}
};
function taskparam_removeit() {
	if (taskparam_editIndex == undefined) {return}
	$('#taskparam_grid').datagrid('cancelEdit', taskparam_editIndex).datagrid('deleteRow', taskparam_editIndex)
	taskparam_editIndex = undefined;
};
function taskparam_accept() {
	if (taskparam_endEditing()) {
		$('#taskparam_grid').datagrid('acceptChanges');
	}
};
function taskparam_reject() {
	$('#taskparam_grid').datagrid('rejectChanges');
	taskparam_editIndex = undefined;
};

</script>
	</body>
</html>