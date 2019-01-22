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
		    			<td></td>
		    			<td><input type="hidden" id="id_U" name="id" value="${map.id }" ></input></td>
		    		</tr>
		    		<tr>
		    			<td>Domain:</td>
		    			<td>
			    			<input class="easyui-combobox" 
			                        id='domain_C' name="entity" value="${map.entity }"
			                        data-options="valueField:'code', textField:'value',
			                        editable:false,
			                        required: true,
									data: domains" />
		    			</td>
		    		</tr>
		    		<tr>
		    			<td>Sub Domain:</td>
		    			<td><input class="easyui-validatebox" type="text" id="audit_name_U" name="audit_name" value="${map.audit_name }" data-options="required:true"></input></td>
		    		</tr>
		    		<tr>
		    			<td>Source Enum:</td>
		    			<td><input class="easyui-validatebox" type="text" id="src_enum_U" name="src_enum" value="${map.src_enum }" data-options="required:true"></input></td>
		    		</tr>
		    		<tr>
		    			<td>Target Enum:</td>
		    			<td><input class="easyui-validatebox" type="text" id="dst_enum_U" name="dst_enum" value="${map.dst_enum }" data-options="required:true"></input></td>
		    		</tr>
		    		<tr>
		    			<td>Description:</td>
		    			<td><textarea id="enum_desc_U" name="enum_desc" style="height:60px;font-size: 12px;">${map.enum_desc }</textarea></td>
		    		</tr>
		    	</table>
		    </form>
	    </div>
	</div>
<script type="text/javascript">
var update = VIRTUE.update;

update.submitForm = function (){
	if( ! $('#u_form').form("validate") )
	{
		return;
	}
	if($('#u_form #enum_desc_U').val() == ""){
		alert("Enum Description is required！");
		return;
	}
	
	var formData = $('#u_form').form( 'getData');
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