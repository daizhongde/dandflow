<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="person.daizhongde.authority.constant.SessionConstants"%>
<%@page import="person.daizhongde.authority.hibernate.pojo.TAuthorityUser"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>任务excute bin(shell控件)</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	</head>
	<body>
	<div class="easyui-layout" fit="true">
	<form id="browseTask_form" method="post" style="margin:0;padding:0;">
		<div data-options="region:'north'" style="height:50px">
			<table id="utaskTable0" width="100%" cellpadding="2">
				<tr>
					<td style='width:130px'></td>
					<td><input id="uprocessId" class="easyui-validatebox"
						data-options="required: true" type="hidden" name="processId"
						style='width:90%;' value="" /></td>
				</tr>
				<tr>
					<td style='width:130px'>Task Name</td>
					<td><input id="unodeName" class="easyui-validatebox"
						data-options="required: true" type="text" name="nodeName1"
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
							<td><input id="uauthor" class="easyui-validatebox"
								data-options="required : false" type="text" name="author"
								style='width:90%;' value="" readonly="readonly" /></td>
						</tr>
						<tr>
							<td style='width:120px'>Remark</td>
							<td><textarea id="unodeRemark" rows="5" style='width:90%;'
									name="nodeRemark">${pojo.nodeRemark}</textarea></td>
						</tr>
						<tr>
							<td style='width:120px'>Execute message</td>
							<td><textarea id="uremark" rows="5" style='width:90%;'
									name="remark">${pojo.remark}</textarea></td>
						</tr>
						<tr>
							<td style='width:120px'>Last Update Time</td>
							<td><input id="ucreatedate" class="easyui-validatebox"
								data-options="required : false" type="text" name="createdate"
								style='width:90%;' value="${pojo.createdate}" readonly="readonly" /></td>
						</tr>
					</table>
				</div>
				<div title="Variables" style="padding:10px">
					<table id="taskparam_grid"></table>
				</div>
				<div title="Parameters" style="padding:10px">
					<table id="comparaTable" width="100%" cellpadding="2">
<script type="text/javascript">
var loguser = '<% out.print( ((TAuthorityUser)session.getAttribute(SessionConstants.LOGIN_USER)).getCUlogname() ); %>';
console.log("loguser:"+loguser+",insAuthor:"+insAuthor);

jQuery(document).ready(function() {
// 	$("#browseTask_form").form("load", processpojo);
	$("#utaskTable0 #unodeName").val( "${pojo.nodeName}" );
	
});

var controlTPLS = ${json };

app.dryrunflag = false;
app.dbconnectionurl = false;

for( var i=0,j = controlTPLS.length; i<j; i++ ){
	var controlTPL = controlTPLS[i];
	var inputStr = "";
	
	/** according input type write html element **/
//  	n = new Number(n);
	switch(controlTPL.inputType ){
		case 1: 
			inputStr = "<input class='easyui-validatebox' type='text' name='"+ controlTPL.paraId+"' style='width:90%;' value='"+controlTPL.defValue+"'/>";
			break;
		case 2:
			var disabledattr = "";
			if (loguser == insAuthor) {
				disabledattr = "data-options='disabled:false' ";
			}else{
				disabledattr = "data-options='disabled:true' ";
			}
			inputStr = "<textarea id='exec-bin' name='"+ controlTPL.paraId+"' style='height:60px;width:100%;font-size: 12px;'>"+controlTPL.defValue+"</textarea>"
			+"<a href='javascript:void(0)' class='easyui-linkbutton' iconCls='icon-ok' " + disabledattr 
			+"onclick=\"app.updateShell('${pojo.jobInsId}','${pojo.comId}','"+ controlTPL.paraId + "' );\">Submit Shell</a>";
			break;
		case 3: 
			inputStr = "<input class='easyui-validatebox' type='text' name='"+ controlTPL.paraId+"' style='width:90%;' value='"+controlTPL.defValue+"'/>";
			break;
		case 4:
			app.dbconnectionurl = true;
			inputStr = "<input id='dbconnectionurl' name='"+ controlTPL.paraId+"' value='"+ nvl(controlTPL.defValue) +"'  width='400px'/>";
			break;
		case 101:
			app.dryrunflag = true;
			inputStr = "<input id='fares_dryrun_id' name='"+ controlTPL.paraId+"' value='"+controlTPL.defValue+"'/>";
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
	</div>
<script type="text/javascript">
app.updateShell = function( jobInsId, comId, paraId  ){
	console.log("jobInsId:"+jobInsId+",comId:"+comId+",paraId:"+paraId);

		$.ajax({
			type: "POST",
			url: getContextPath()+"/curd/migComInsCURDmodifySQL.action",
			async: false,
			data:{
				jdata: "{jobInsId:'"+jobInsId+"',comId:'"+comId+"',paraId:"+paraId+"}",
				paraValue: document.getElementById("exec-bin").value
			},
			success: function(oResponse){
//		 		oResponse = eval("(" + oResponse + ")");
				alert(oResponse.msg);
		   }
		});
};

if(app.dryrunflag){

	$('#fares_dryrun_id').combogrid({
		url : getContextPath()+"/query/commonCBBQUERYdfind.action",
		queryParams : {
			tableName : "v_dryrun_config",
			columns: "mig_dryrun_name"
		},
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
			{ title : 'Dry Run', field : 'mig_dryrun_name', width : 120, sortable: true }, 
			{ title : 'Remark', field : 'remark',  width : 300 }
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
	disabled: true,
	iconCls : 'icon-add',
	handler : function() {
		taskparam_append();
		return false;
	}
}, '-', {
	text : 'Remove',
	disabled: true,
	iconCls : 'icon-remove',
	handler : function() {
		taskparam_removeit();
		return false;
	}
} , '-', {
	text : 'Save',
	disabled: true,
	iconCls : 'icon-save',
	handler : function() {
		taskparam_accept();
		return false;
	}
}, '-', {
	text : 'Cancel',
	disabled: true,
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
	url: getContextPath()+"/query/migInsParaJEasyUIQUERYdfind.action",
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
		jdata : encodeURI($.toJSON({ act: VIRTUE.act.QUERY, condition: {nodeId: "${pojo.nodeId}", jobInsId: "${pojo.jobInsId}"  }, operator : {} })),
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
		$('#taskparam_grid').datagrid('appendRow', { nodeId: "${pojo.nodeId}" });
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