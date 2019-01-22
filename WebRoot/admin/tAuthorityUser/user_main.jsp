<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="person.daizhongde.authority.hibernate.pojo.TAuthorityUser"%>
<%@page import="person.daizhongde.authority.constant.SessionConstants"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

TAuthorityUser user = (TAuthorityUser)session.getAttribute( SessionConstants.LOGIN_USER );
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户管理</title>

<link rel="stylesheet" type="text/css" href="../../scripts/jquery-easyui/1.3.5/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="../../scripts/jquery-easyui/1.3.5/themes/icon.css">

<script type="text/javascript" src="../../scripts/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="../../scripts/jquery-easyui/1.3.5/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../../scripts/jquery-easyui/1.3.5/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="../../scripts/json/jquery.json-2.2.min.js"></script>
<script type="text/javascript" src="../../scripts/virtue/virtue.js"></script>

<style type="text/css">
body {
    background-color: #C3E3FF;
}
</style>
<script type="text/javascript">
var noquery = VIRTUE.noquery;
var query = VIRTUE.query;
var exp = VIRTUE.exp;

var del = VIRTUE.del;
var read = VIRTUE.read;

var OPE = VIRTUE.operator;

var currentIId = <%=user.getTAuthorityInst().getNIid() %>;//1-省中心，2-长沙市-辖区，17-长沙市
// var currentICode = 1;//430000901-省中心，430100901-长沙市-辖区，430101901-长沙市
var selectedIId = currentIId;//页面默认选择当前用户机构
var selectedIPId = "<%=null==user.getTAuthorityInst().getNIsuperior()?"": user.getTAuthorityInst().getNIsuperior()%>"; 
var flag =1;// 页面树初始化，不返回请求根节点,机构树初始化之后马上置0

query.jdata = { act: VIRTUE.act.QUERY, condition: {did: currentIId }, operator : {}  }; 

/** When click grid row update right role view panel's tree  **/
function updateCheckTree( rowIndex, rowData ){
// 	alert("rowIndex:"+rowIndex+",rowData.id:"+rowData.id);
//由于tree没有reload(url)方法，所以这里对原来的combotree做了覆盖
	$('#rt').tree({
		url : "../../tree/tAuthorityRoleTREEquery_JEasyUI_CheckBoxTree_ExceptDev.action?userId="+rowData.id,
		method:'post',
		animate : true,
		checkbox : true,
		onlyLeafCheck : true
	});
};
/** When select combobox update gridview  **/
function updateGridView( node ){
// 	alert("node.id:"+node.id);
	query.jdata.condition.did=node.id;
	$('#grid').datagrid( 'reload', { jdata: encodeURI( $.toJSON( query.jdata ) ) } );
	$('#grid').datagrid( 'unselectAll' );
	
	$('#rt').tree({
		url : "../../tree/tAuthorityRoleTREEquery_JEasyUI_CheckBoxTree_ExceptDev.action",
		method:'post',
		animate : true,
		checkbox : true,
		onlyLeafCheck : true
	});
};

function newModule(){  //使用一个window
	$('#win').window('open').window('setTitle', '添加用户');
	$('#win').window("refresh", "../../common/tAuthorityUser/tAuthorityUser_add.html");
};

function editModule() {
	var row = $('#grid').datagrid('getSelected');
	if (row) {
		$('#win').window('open').window('setTitle', '编辑用户');
		$('#win').window("refresh", "../../curd/tAuthorityUserCURDinitModify.action?pojo.NUid="+row.id);
	}
};

function browseModule() {
	var row = $('#grid').datagrid('getSelected');
	if (row) {
		$('#win').window('open').window('setTitle', '浏览用户');
		read.init();
		read.jdata.condition.id = row.id;
		read.jdata.operator.id = OPE.EQUAL;
		$('#win').window("refresh", "../../curd/tAuthorityUserCURDbrowse.action?jdata="+encodeURI($.toJSON( read.jdata )));
	}
};

//del.jdata = {act: "del",condition: { id: "" },operator : {nmid : 1} };
function destroyModule(){
	var row = $('#grid').datagrid('getSelected');
	if (row){
		$.messager.confirm('确认','确认删除吗?',function(r){
			if (r){
				del.init();
				del.jdata.condition.id = row.id;
				del.jdata.operator.id = OPE.EQUAL;
				$.post("../../curd/tAuthorityUserCURDdelete.action", { jdata: encodeURI($.toJSON( del.jdata )) }, function(result, status){
					alert(result.msg);
					if (result.success){
						$('#grid').datagrid('reload',{jdata: encodeURI( $.toJSON( query.jdata ) ) } );	// reload data
					}
				});
			}
		});
	}
};

var toolbar = [{
	text:'添加用户',
	iconCls:'icon-add',
	handler:function()
	{
		newModule();
	}
},{
	text:'编辑用户',
	iconCls:'icon-edit',
	handler:function()
	{
		editModule();
	}
},{
	text:'删除用户',
	iconCls:'icon-remove',
	handler:function()
	{
		destroyModule();
	}
},{
	text:'查看用户',
	iconCls:'icon-search',
	handler:function()
	{ 
		browseModule(); 
	}
}];

//<input class='easyui-combotree' id='instCBT' name='pojo.NIid' style='width:260px;' >
$(function(){
	$('#grid').datagrid({
		title: "<input class='easyui-combotree' id='instCBT' name='pojo.NIid' style='width:260px;' >用户信息-admin",
		toolbar: toolbar,
		nowrap: true,
		striped: true,
		fit: true,
		collapsible: true,
		url: "../../query/tAuthorityUserJEasyUIQUERYdfind_ExceptDev.action",
		idField: "id",
		remoteSort: true,
		sortName: "name",
		sortOrder: "asc",
		multiSort: false,
		singleSelect: true,	
		columns:[[
			{title: "用户ID", field: "id", hidden: false },
			{title: "用户登陆名", field: "logname", width:80 },
			{title: "用户姓名", field: "name", width:140, sortable: true },
		    {title: "性别", field: "sex",width:60, sortable: true },
		    {title: "联系电话", field:"phone", hidden: true },
		    {title: "电子邮箱", field: "email", hidden: true },
		    {title: "QQ", field: "qq",hidden: true},
		    {title: "用户地址",field:"addr", hidden: true},
		    {title: "机构ID", field:"iid", hidden: true },
		    {title: "部门ID", field:"did", hidden: true }
		]],
		rownumbers:true,
		queryParams:{jdata: encodeURI($.toJSON( noquery.jdata )) },
		onSelect: updateCheckTree
	});
});

</script>
</head>
<body class="easyui-layout">
<!-- mt module tree   disabled : true,  disabled="disabled"-->
<!-- 	<div data-options="region:'north'" style="height:50px"> -->
<!-- 		<input class='easyui-combotree' id='instCBT' name='pojo.NIid' style='width:260px;' > -->
<!-- 	</div> -->

	<div data-options="region:'east',border:true,split:true" style="width:400px;padding:10px;" title="他/她所担任的角色" >
		<ul id="rt" class="easyui-tree" data-options="url:'../../tree/tAuthorityRoleTREEquery_JEasyUI_CheckBoxTree_ExceptDev.action',
				method : 'post', 
				mode: 'remote',
				dnd : false, 
				lines: true,
				animate : true, 
				revert:true,
				checkbox : true,
				onlyLeafCheck : true,
				onCheck: function(node, checked){
					//var snum = $('#grid').datagrid('getSelections').length;
					//alert('snum:'+snum);
					
					var userRow = $('#grid').datagrid('getSelected');
					//alert('userRow.id:'+userRow.id);
					if(userRow)
					{
						if(checked)
						{
							VIRTUE.add.init();
							VIRTUE.add.jdata.data.uid = userRow.id;
							VIRTUE.add.jdata.data.rid = node.id;
							grantRole( VIRTUE.add.jdata, node, userRow );
						}
						else
						{
							VIRTUE.del.init();
							VIRTUE.del.jdata.condition.uid = userRow.id;
							VIRTUE.del.jdata.condition.rid = node.id; 
							//VIRTUE.del.jdata.operator.id = VIRTUE.operator.EQUAL;//default =
							//VIRTUE.del.jdata.operator.id = VIRTUE.operator.EQUAL;
							revokeRole(VIRTUE.del.jdata, node, userRow );
						}
					}
					else
					{
						alert('请选择用户！');
					}
				}"
		></ul>
	</div>

    <div data-options="region:'center',border:false" title="用户main" style="overflow:hidden;" noheader="true">
		<table id="grid"></table>
<!-- 		<table id="grid" class="easyui-datagrid" style="width:938px;height:720px"
		url="../../query/tAuthorityUserJEasyUIQUERYdfind.action" title="用户信息"
		toolbar='#tb',
		nowrap="true"
		striped="true"
		fit= "true",
		collapsible= "true",
		idField= "id",
		remoteSort= "true",
		sortName= "name",
		sortOrder= "asc",
		multiSort= "false",
		singleSelect= "true",	
		rownumbers= "true">
		<thead>
			<tr>
				<th field="id" hidden="false">用户ID</th>
				<th field="logname" width="80">用户登陆名</th>
				<th field="name" width="140" sortable="true">用户姓名</th>
				<th field="sex" width="60" sortable="true">性别</th>
				<th field="phone" hidden="true">联系电话</th>
				<th field="email" hidden="true">电子邮箱</th>
				<th field="qq" hidden="true">QQ</th>
				<th field="addr" hidden="true">用户地址</th>
				<th field="iid" hidden="true">机构ID</th>
				<th field="did" hidden="true">部门ID</th>
			</tr>
		</thead>
	</table> -->
	</div>
	
<!-- 	<div id="tb" style="padding:5px;height:auto;margin:0">
		<div style="margin-bottom:5px">
			<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="false" onclick="newModule()">添加用户</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="false" onclick="editModule()">编辑用户</a> 
			<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="false" onclick="destroyModule()">删除用户</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-search" plain="false" onclick="browseModule()">查看用户</a>
		</div>
		<div>
			<input class='easyui-combotree' id='instCBT' name='pojo.NIid' style='width:260px;' >
		</div>
	</div> -->

	<div id="win" class="easyui-window" title="添加用户" style="width:600px;height:520px"
        data-options="modal:true,closed:true">
	</div>
<script type="text/javascript">

/** grant module to special role
*
@param jdata for sqlassembel
@param roleNode check's roleNode
@param userRow select's userRow
*/
function grantRole( jdata, roleNode, userRow ){
	var t = $('#rt');
	
	jQuery.post("../../curd/tAuthorityUrrelationCURDaddWithId.action", {
		jdata : encodeURI( jQuery.toJSON( jdata ) )
	}, function(oResponse, status) {
//		oResponse = eval("(" + oResponse + ")");
//		alert(oResponse.msg);
		if (oResponse.success == true) {

		}else{
			alert(oResponse.msg);
			t.tree( 'reload' );
		}
	});
};

/** revoke module from special role
*@param jdata for sqlassembel
 @param roleNode uncheck's roleNode
*/
function revokeRole( jdata, roleNode ){

	/** there two post request, You can use a delete request do all back process, but it with Low efficiency  **/
	jQuery.post("../../curd/tAuthorityUrrelationCURDdelete.action", {
		jdata : encodeURI( jQuery.toJSON( jdata ) )
	}, function(oResponse, status) {
		var t = $('#rt');
//		oResponse = eval("(" + oResponse + ")");
//		alert(oResponse.msg);
		if (oResponse.success == true) {
			
		}else{
			alert(oResponse.msg);
			t.tree( 'reload' );
		}
	});
};

jQuery(document).ready(function(){
// 	var t = jQuery('#instCBT').combotree('tree');
// 	alert(currentIId);
// 	var node = t.tree( 'find', 133 );
// 	t.tree('select', node.target);
// 	t.tree('scrollTo',node.target);
	$('#instCBT').combotree({
// 		height: "44",
		url : "../../tree/tAuthorityInstTREEquery_JEasyUI_CBT_AsyncUSubI.action",
		method:'post',
		mode: 'remote', 
		value: currentIId,//if combotree has set default value, is would do a select on initial
		required:true,
		onShowPanel:function( ){
			var t = jQuery('#instCBT').combotree('tree');
// 			var t = this.combotree('tree');
			var node = t.tree( 'find', selectedIId );
			t.tree('expandTo', node.target);
			t.tree('scrollTo',node.target);
// 			t.tree('select', node.target);
		},
// 		onSelect: function(node){// It will trigger select event when expand node(lazy load a node)
		onClick: function(node){
// 			refresh datagrid 
			updateGridView(node);
			
			var t = jQuery('#instCBT').combotree('tree');
			selectedIId = node.id;
			if(selectedIId !=1 )
			{
				selectedIPId = t.tree( 'getParent', node.target ).id; 
			}
			else
			{
				selectedIPId = "";
			}
		},
// 		onLoadSuccess :  function( data ){
		onLoadSuccess :  function( node, data ){
			if(node===null){
				var t = jQuery('#instCBT').combotree('tree');
				var node = t.tree( 'find', currentIId );
				updateGridView(node);
			}
		}
	});
	flag = 0;
}); 
</script>
</body>
</html>