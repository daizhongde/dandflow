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
		<div style="padding:10px 0 10px 60px">
		    <form id="u_form" method="post" action="" style="margin:0;padding:0;">
		    	<table align="center">
		    		<tr>
		    			<td>ID:</td>
		    			<td><input class="easyui-validatebox" type="text" 
		    			id="mig_dryrun_id_U" name="mig_dryrun_id" value="${map.mig_dryrun_id }" data-options="required:true" readonly="readonly"></input></td>
		    		</tr>
		    		<tr>
		    			<td>Dryrun Name:</td>
		    			<td><input class="easyui-validatebox" type="text" 
		    			id="mig_dryrun_name_U" name="mig_dryrun_name" value="${map.mig_dryrun_name }" data-options="required:true"></input></td>
		    		</tr>
		    		<tr>
		    			<td>Description:</td>
		    			<td><textarea id="remark_U" name="remark"  style="height:60px;font-size: 12px;">${map.remark }</textarea></td>
		    		</tr>
		    	</table>
		    </form>
	    </div>
    </div>
<script type="text/javascript">
var update = VIRTUE.update;

update.submitForm = function (){
	var mig_dryrun_id = $('#mig_dryrun_id_U').val();
	var mig_dryrun_name = $('#mig_dryrun_name_U').val();
	var remark = $('#remark_U').val();
	
	update.init();
	update.jdata.condition.mig_dryrun_id = mig_dryrun_id;
	update.jdata.operator.mig_dryrun_id = OPE.EQUAL;
	
	update.jdata.data.mig_dryrun_name = mig_dryrun_name;
	update.jdata.data.remark = remark;
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