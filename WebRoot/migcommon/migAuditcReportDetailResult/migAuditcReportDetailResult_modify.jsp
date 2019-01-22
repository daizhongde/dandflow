<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>稽核差异分析录入</title>
</head>
<body>
<div class="easyui-layout" data-options="fit:true">
	<div data-options="region:'center',border:false" title="" noheader="true">
		<div style="padding:5px 0 5px 5px">
		    <form id="u_form" method="post" action="" style="margin:0;padding:0;">
		    	<table align="center">
		    		<tr>
		    			<td></td>
		    			<td><input type="hidden" id="id_U" name="id" value="${map.id }"></input></td>
		    			<td></td>
		    			<td></td>
		    		</tr>
		    		<tr>
		    			<td>Domain:</td>
		    			<td>${map.entity }</td>
		    			<td>Sub Domain:</td>
		    			<td>${map.audit_item }</td>
		    		</tr>
		    		<tr>
		    			<td>Enum Description:</td>
		    			<td>${map.enum_desc }</td>
		    			<td>Flag:</td>
		    			<td>${map.split_flag }</td>
		    		</tr>
		    		<tr>
		    			<td>Source Enum:</td>
		    			<td>${map.src_enum }</td>
		    			<td>Target Enum:</td>
		    			<td>${map.dst_enum }</td>
		    		</tr>
		    		<tr>
		    			<td>Source Count:</td>
		    			<td>${map.src_count }</td>
		    			<td>Target Count:</td>
		    			<td>${map.dst_enum }</td>
		    		</tr>
		    		<!-- 
		    		<tr>
		    			<td>作者:</td>
		    			<td><input class="easyui-validatebox" type="text" id="audit_author_U" name="audit_author" 
		    				value="${map.audit_author }" data-options="required:true" readonly="readonly"></input></td>
		    			<td></td>
		    			<td></td>
		    		</tr> -->
		    		<tr>
		    			<td>DryRun:</td>
		    			<td>${map.dryrun_name }</td>
		    			<td></td>
		    			<td></td>
		    		</tr>
		    		<tr>
		    			<td>Difference Reasons:</td>
		    			<td colspan="3"><textarea id="min_analysis_U" name="min_analysis"  style="height:150px;width:450px;font-size: 12px;">${map.min_analysis }</textarea></td>
		    		</tr>
		    	</table>
		    </form>
	    </div>
    </div>
<script type="text/javascript">
$(function(){
	$('#fares_dryrun_id_U').combogrid({
		url : "../../query/commonCBBQUERYdfind.action",
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
		onLoadSuccess : function() {
			var g = $('#fares_dryrun_id_U').combogrid('grid');	// get datagrid object
			var selrow = g.datagrid('getSelected');
// 			console.log(selrow);
			if(selrow==null){
// 				g.datagrid('insertRow',{
// 					index: 0,	// index start with 0
// 					row: app.dryrunRow
// 				});
			}
		},
		columns : [ [
			{ title : 'ID', field : 'mig_dryrun_id',width : 90, sortable: true }, 
			{ title : 'Dry Run', field : 'mig_dryrun_name', width : 120, sortable: true }, 
			{ title : 'Description', field : 'remark',  width : 300 } 
		] ]
	});
});
var update = VIRTUE.update;

update.submitForm = function (){
	if( ! $('#u_form').form("validate") )
	{
		return;
	}
	if($('#u_form #min_analysis_U').val() == ""){
		alert("Difference Reasons is Required！");
		return;
	}
	
	var formData = $('#u_form').form( 'getData');
// 	console.log("formData:" + $.toJSON(formData) );
	
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
</script>
	<div data-options="region:'south',border:false" style="text-align:right;padding:5px 5px 5px 0;background:#F4F4F4;">
		<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" href="javascript:void(0)" onclick="javascript: update.submitForm()" style="width:80px">Submit</a>
		<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="javascript:void(0)" onclick="javascript:$('#win').dialog('close');" style="width:80px">Cancel</a>
	</div>
</div>
</body>
</html>