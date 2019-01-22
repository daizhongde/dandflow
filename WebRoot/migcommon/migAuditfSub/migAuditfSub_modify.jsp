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
		    			<td>Audit Item:</td>
		    			<td><input class="easyui-validatebox" type="text" 
		    			id="faudit_main_id_U" name="faudit_main_id" value="${map.faudit_main_id }" data-options="required:true" readonly="readonly"></input></td>
		    		</tr>
		    		<tr>
		    			<td>Index:</td>
		    			<td><input class="easyui-validatebox" type="text" 
		    			id="faudit_sub_index_U" name="faudit_sub_index" value="${map.faudit_sub_index }" data-options="required:true"></input></td>
		    		</tr>
		    		<tr>
		    			<td>Source Field:</td>
		    			<td><input class="easyui-validatebox" type="text" 
		    			id="faudit_src_field_U" name="faudit_src_field" value="${map.faudit_src_field }" data-options="required:true"></input></td>
		    		</tr>
		    		<tr>
		    			<td>Target Field:</td>
		    			<td><input class="easyui-validatebox" type="text" 
		    			id="faudit_dst_field_U" name="faudit_dst_field" value="${map.faudit_dst_field }" data-options="required:true"></input></td>
		    		</tr>
		    		<tr>
		    			<td>isKey:</td>
		    			<td>
		    				<select class="easyui-combobox" id="faudit_iskey_U" name="faudit_iskey" value="${map.faudit_iskey }"
		    					data-options="required:true">
	                        	<option value="0">N</option>
	                        	<option value="1">Y</option>
	                        </select>
		    			</td>
		    		</tr>
		    		<tr>
		    			<td>Fields Correlation:</td>
		    			<td>
		    				<input class="easyui-combobox" 
		                        id='faudit_opt_C' name="faudit_opt" value="" editable="false"
		                        data-options="valueField:'code', textField:'value',url:'../../cbb/dicCBBgetDic2ByType.action?dicType=opt',
		                        method:'post',value:'${map.faudit_opt }' ">
		    			</td>
		    		</tr>
		    		<tr>
		    			<td>Status:</td>
		    			<td>
		    				<select class="easyui-combobox" id="faudit_status_U" name="faudit_status" 
		    					value="${map.faudit_status }" data-options="required:true">
	                        	<option value="0">Invalid</option>
	                        	<option value="1" selected="selected">Valid</option>
	                        </select>
		    			</td>
		    		</tr>
		    	</table>
		    </form>
	    </div>
    </div>
<script type="text/javascript">
$(function(){
	$('#faudit_main_id_U').combogrid({
		url : "../../query/migAuditfMainCBBQUERYdfind.action",
		panelWidth : 500,
		panelHeight : 200,
		idField : 'faudit_id',
		textField : 'faudit_name',
		value : app.faudit_id,
		pagination : true,
		fitColumns : false,
		required : true,
		editable : false,
		rownumbers : true,
		mode : 'remote',
		delay : 500,
		sortName : 'faudit_name',
		sortOrder : 'asc',
		pageSize : 5,
		pageList : [ 5, 10 ],
		onLoadSuccess : function() {
			var g = $('#faudit_main_id_C').combogrid('grid');	// get datagrid object
			var selrow = g.datagrid('getSelected');
// 			console.log(selrow);
			if(selrow==null){
				g.datagrid('insertRow',{
					index: 0,	// index start with 0
					row: app.auditMainRow
				});
			}
		},
		frozenColumns: [[ {
   			field : 'faudit_id',
   			title : 'ID',
   			width : 100,
   			hidden : true
   		}, {
   			field : 'faudit_name',
   			title : 'Audit Item',
   			width : 100,
   			sortable : true
	   	}]],
		columns : [ [ {
			field : 'faudit_srctable_name',
			title : 'Source Table',
			width : 100,
			sortable : true
		}, {
			field : 'faudit_dsttable_name',
			title : 'Target Table',
			width : 100,
			sortable : true
		}, {
			field : 'faudit_type',
			title : 'Audit Type',
			width : 100,
			sortable : true
		}, {
			field : 'faudit_group',
			title : 'Audit Group',
			width : 100,
			sortable : true
		}, {
			field : 'faudit_bussiness_cluster',
			title : 'Business Type',
			width : 100,
			sortable : true
		}, {
			field : 'faudit_status',
			title : 'Audit Status',
			width : 100,
			sortable : true
		} ] ]
	});
});

var update = VIRTUE.update;

update.submitForm = function (){	
	if( ! $('#u_form').form("validate") )
	{
		return;
	}

	var formData = $('#u_form').form( 'getData');
	update.init();
	update.jdata.condition.faudit_main_id = formData.faudit_main_id;
	update.jdata.operator.faudit_main_id = OPE.EQUAL;
	update.jdata.condition.faudit_sub_index = formData.faudit_sub_index;
	update.jdata.operator.faudit_sub_index = OPE.EQUAL;
	
	for (var field in formData) {
		update.jdata.data[field] = formData[field];
	}
	
	jQuery.post( getContextPath()+"/curd/migAuditfSubCURDmodify.action", 
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