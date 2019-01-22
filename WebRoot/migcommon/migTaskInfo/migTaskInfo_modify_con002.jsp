<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>任务设置load data</title>
    
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
//这里如果有控件增加需要改造的话，可以通过控件ID使用重定向
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
			if(controlTPL.paraName == "business"){
				//inputStr += "<input type='button' value='Select Config' onclick='app.openConfig(\""+ controlTPL.controlId +"\");'/>";
				inputStr += "<a href='javascript:void(0)' class='easyui-linkbutton' iconCls='icon-search' onclick='app.openConfig(\""+ controlTPL.controlId +"\");'>Select Config</a>";
				inputStr += "<textarea disabled=disabled id="+controlTPL.paraName+"' name='"+ controlTPL.paraId+"' style='height:60px;width:100%;font-size: 12px;'>"+ nvl(controlTPL.defValue)+"</textarea>";
				break;
			}
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
	
<div id="chooseconfig-dlg1"></div>
<div id="chooseconfig-dlg2"></div>
<div id="chooseconfig-dlg3"></div>
<div id="chooseconfig-dlg4"></div>
<div id="chooseconfig-dlg5"></div>
<div id="chooseconfig-dlg6"></div>
<div id="chooseconfig-dlg7"></div>
<div id="chooseconfig-dlg8"></div>
<div id="chooseconfig-dlg9"></div>
<div id="chooseconfig-dlg0"></div>

	</div>
<script type="text/javascript">
app.openConfig = function( controlId ){
// 	console.log("controlId:" + controlId );
// 	console.log("#chooseconfig-dlg" + controlId.charAt(controlId.length - 1)+":" + "#chooseconfig-dlg" + controlId.charAt(controlId.length - 1)  );
	
	$("#chooseconfig-dlg" + controlId.charAt(controlId.length - 1) ).dialog('open');
	
	var comforms = $('#comparaTable textarea');
	app.idstr = "";//目前此功能只支持控件参数只有一个textarea的界面
	for(var i=0, j=comforms.length; i<j; i++ ){
		if(comforms[i].value != "" ){
			app.idstr = comforms[i].value;
			break;
		}
	}
};

if( taskpojo.controlId == control.SPLIT_FILE ){//con001
	$("#chooseconfig-dlg1").dialog({
		title : "Select Config",
		href :   getContextPath()+"/migcommon/migTaskInfo/migSplitFileConfig_main.html",
		iconCls : 'icon-search',
		modal : true,
		closed : true,
		resizable: true,
		minimizable: true,
		maximizable: true,
		cache: false,
		width : 500,
		height : 400,
		buttons: [{
            text:'OK',
            iconCls:'icon-ok',
            handler:function(){
            	var nodes = $('#tree-split_file').tree("getChecked");
            	if(nodes.length==0){
            		alert("Please select config!");
            		return;
            	}
            	var  business = "";
            	
//             	MIG_SRC、MIG_WHERE、MIG_DST、MIG_DST_CONN
            	
            	for(var i=0,j = nodes.length; i<j; i++ ){
//             		business += "{" + rows[i].mig_src + "," + rows[i].mig_where + "," + rows[i].mig_dst + ","
//             		+ rows[i].mig_dst_conn+"};";
					var id = nodes[i].id 
            		if( !isNaN( id ) ){
            			if(i!=j-1){
                			business += id+",";
                		}else{
                			business += id;
                		}
            		}
            	}
            	$('#comparaTable textarea').val( business );
            	$('#chooseconfig-dlg1').dialog('close');
            	return false;//for IE6 support
            }
        },{
            text:'Cancel',
            iconCls:'icon-cancel',
            handler:function(){
            	$('#chooseconfig-dlg1').dialog('close');
            }
        }]
	});
}else if( taskpojo.controlId == control.LOAD_DATA ){//con002
	$("#chooseconfig-dlg2").dialog({
		title : "Select Config",
		href :   getContextPath()+"/migcommon/migTaskInfo/migLoaddataConfig_main.html",
		iconCls : 'icon-search',
		modal : true,
		closed : true,
		resizable: true,
		minimizable: true,
		maximizable: true,
		cache: false,
		width : 500,
		height : 400,
		buttons: [{
            text:'OK',
            iconCls:'icon-ok',
            handler:function(){
            	var nodes = $('#tree-load_data').tree("getChecked");
            	if(nodes.length==0){
            		alert("Please select config!");
            		return;
            	}
            	var business = "";
            	
//             	MIG_SRC、MIG_DST、MIG_DST_CONN
            	
            	for(var i=0,j = nodes.length; i<j; i++ ){
//             		business += "{" + rows[i].mig_src + "," + rows[i].mig_dst + ","
//             		+ rows[i].mig_dst_conn+"};";

            		var id = nodes[i].id 
            		if( !isNaN( id ) ){
            			if(i!=j-1){
                			business += id+",";
                		}else{
                			business += id;
                		}
            		}
            	}
            	$('#comparaTable textarea').val( business );
            	$('#chooseconfig-dlg2').dialog('close');
            	return false;//for IE6 support
            }
        },{
            text:'Cancel',
            iconCls:'icon-cancel',
            handler:function(){
            	$('#chooseconfig-dlg2').dialog('close');
            }
        }]
	});
}else if( taskpojo.controlId == control.OUT_DATA_FILE ){//con003
	$("#chooseconfig-dlg3").dialog({
		title : "Select Config",
		href :   getContextPath()+"/migcommon/migTaskInfo/migOutDataFileConfig_main.html",
		iconCls : 'icon-search',
		modal : true,
		closed : true,
		resizable: true,
		minimizable: true,
		maximizable: true,
		cache: false,
		width : 500,
		height : 400,
		buttons: [{
            text:'OK',
            iconCls:'icon-ok',
            handler:function(){
            	var nodes = $('#tree-out_data_file').tree("getChecked");
            	if(nodes.length==0){
            		alert("Please select config!");
            		return;
            	}
            	var business = "";
            	
//             	MIG_SRC、MIG_SRC_CONN、MIG_WHERE、MIG_DST
            	
            	for(var i=0,j = nodes.length; i<j; i++ ){
//             		business += "{" + rows[i].mig_src + "," + rows[i].mig_src_conn + "," + rows[i].mig_where + ","
//             		+ rows[i].mig_dst+"};";

            		var id = nodes[i].id 
            		if( !isNaN( id ) ){
            			if(i!=j-1){
                			business += id+",";
                		}else{
                			business += id;
                		}
            		}
            	}
            	$('#comparaTable textarea').val( business );
            	$('#chooseconfig-dlg3').dialog('close');
            	return false;//for IE6 support
            }
        },{
            text:'Cancel',
            iconCls:'icon-cancel',
            handler:function(){
            	$('#chooseconfig-dlg3').dialog('close');
            }
        }]
	});
}else if( taskpojo.controlId == control.OUT_DATA_BASE ){//con004
	$("#chooseconfig-dlg4").dialog({
		title : "Select Config",
		href :   getContextPath()+"/migcommon/migTaskInfo/migOutDataBaseConfig_main.html",
		iconCls : 'icon-search',
		modal : true,
		closed : true,
		resizable: true,
		minimizable: true,
		maximizable: true,
		cache: false,
		width : 500,
		height : 400,
		buttons: [{
            text:'OK',
            iconCls:'icon-ok',
            handler:function(){
            	var nodes = $('#tree-out_data_base').tree("getChecked");
            	if(nodes.length==0){
            		alert("Please select config!");
            		return;
            	}
            	var business = "";
            	
//             	MIG_SRC、MIG_SRC_CONN、MIG_WHERE、MIG_DST、MIG_DST_CONN
            	
            	for(var i=0,j = nodes.length; i<j; i++ ){
//             		business += "{" + rows[i].mig_src + "," + rows[i].mig_src_conn + "," + rows[i].mig_where + ","
//             		+ rows[i].mig_dst + ","+ rows[i].mig_dst_conn+"};";

            		var id = nodes[i].id 
            		if( !isNaN( id ) ){
            			if(i!=j-1){
                			business += id+",";
                		}else{
                			business += id;
                		}
            		}
            	}
            	$('#comparaTable textarea').val( business );
            	$('#chooseconfig-dlg4').dialog('close');
            	return false;//for IE6 support
            }
        },{
            text:'Cancel',
            iconCls:'icon-cancel',
            handler:function(){
            	$('#chooseconfig-dlg4').dialog('close');
            }
        }]
	});
}else if( taskpojo.controlId == control.EXCUTE_SQL ){//con005
	
}else if( taskpojo.controlId == control.EXCUTE_BIN ){//con006
	
}else if( taskpojo.controlId == control.LEGALITY_AUDIT ){//con007 合法性稽核
	$("#chooseconfig-dlg7").dialog({
		title : "Select Config",
		href :   getContextPath()+"/migcommon/migTaskInfo/migAuditvConfig_main.html",
		iconCls : 'icon-search',
		modal : true,
		closed : true,
		resizable: true,
		minimizable: true,
		maximizable: true,
		cache: false,
		width : 500,
		height : 400,
		buttons: [{
            text:'OK',
            iconCls:'icon-ok',
            handler:function(){
            	var nodes = $('#tree-auditvconfig').tree("getChecked");
            	if(nodes.length==0){
            		alert("Please select config!");
            		return;
            	}
            	var business = "";
            	
            	for(var i=0,j = nodes.length; i<j; i++ ){
//             		business += "{" + rows[i].src_db_connect + "&" + rows[i].src_audit_sql + "&" + rows[i].audit_value + "&"
//             		+ rows[i].operator+"&" + rows[i].audit_code + "&" + rows[i].domain+"&"
//             		+ rows[i].table_name + "&" + rows[i].audit_name+"};";

            		var id = nodes[i].id 
            		if( !isNaN( id ) ){
            			if(i!=j-1){
                			business += id+",";
                		}else{
                			business += id;
                		}
            		}
            	}
            	$('#comparaTable textarea').val( business );
            	$('#chooseconfig-dlg7').dialog('close');
            	return false;//for IE6 support
            }
        },{
            text:'Cancel',
            iconCls:'icon-cancel',
            handler:function(){
            	$('#chooseconfig-dlg7').dialog('close');
            }
        }]
	});
}else if( taskpojo.controlId == control.CONTROL ){//con008
	
}else if( taskpojo.controlId == control.CONSISTENCY_AUDIT ){//con009
	$("#chooseconfig-dlg9").dialog({
		title : "Select Config",
		href :   getContextPath()+"/migcommon/migTaskInfo/migAuditcConfig_main.html",
		iconCls : 'icon-search',
		modal : true,
		closed : true,
		resizable: true,
		minimizable: true,
		maximizable: true,
		cache: false,
		width : 800,
		height : 400,
		buttons: [{
            text:'OK',
            iconCls:'icon-ok',
            handler:function(){
            	var rows = $('#auditcconfig_grid').datagrid("getSelections");
            	if(rows.length==0){
            		alert("Please select config!");
            		return;
            	}
            	var business = "";
            	
            	for(var i=0,j = rows.length; i<j; i++ ){
//             		business += "{" + rows[i].mig_sql + "};";

            		if(i!=j-1){
            			business += rows[i].audit_id+",";
            		}else{
            			business += rows[i].audit_id;
            		}
            	}
            	$('#comparaTable textarea').val( business );
            	$('#chooseconfig-dlg9').dialog('close');
            	return false;//for IE6 support
            }
        },{
            text:'Cancel',
            iconCls:'icon-cancel',
            handler:function(){
            	$('#chooseconfig-dlg9').dialog('close');
            }
        }]
	});
}else if( taskpojo.controlId == control.QUALITY_CONTROL ){//con010
	$("#chooseconfig-dlg0").dialog({
		title : "Select Config",
		href :   getContextPath()+"/migcommon/migTaskInfo/migAuditfConfig_main.html",
		iconCls : 'icon-search',
		modal : true,
		closed : true,
		resizable: true,
		minimizable: true,
		maximizable: true,
		cache: false,
		width : 500,
		height : 400,
		buttons: [{
            text:'OK',
            iconCls:'icon-ok',
            handler:function(){
            	var nodes = $('#tree-auditfconfig').tree("getChecked");
            	if(nodes.length==0){
            		alert("Please select config!");
            		return;
            	}
            	var business = "";
//             	FAUDIT_ID、FAUDIT_SRCTABLE_NAME、FAUDIT_SRCTABLE_CONN、FAUDIT_DSTTABLE_NAME、FAUDIT_DSTTABLE_CONN
				
            	for(var i=0,j = nodes.length; i<j; i++ ){
//             		var sub_desc = rows[i].sub_desc.replace(/\],\[/g,"][");
//             		business += "{" + rows[i].faudit_id + "," + rows[i].faudit_srctable_name + "," + rows[i].faudit_srctable_conn + ","
//             		+ rows[i].faudit_dsttable_name+"," + rows[i].faudit_dsttable_conn + "," + sub_desc+"};";
            		var id = nodes[i].id 
            		var name = nodes[i].text 
            		if( !isNaN( id ) ){
                		if(nodes[i].attributes.sub_desc==null){
                			alert("第"+(++i)+"条配置<"+name+">没有明细，请重新选择！")
                			return;
                		}
            			if(i!=j-1){
                			business += id+",";
                		}else{
                			business += id;
                		}
            		}
            	}
            	$('#comparaTable textarea').val( business );
            	$('#chooseconfig-dlg0').dialog('close');
            	return false;//for IE6 support
            }
        },{
            text:'Cancel',
            iconCls:'icon-cancel',
            handler:function(){
            	$('#chooseconfig-dlg0').dialog('close');
            }
        }]
	});
}
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