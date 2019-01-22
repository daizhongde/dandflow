<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>稽核不通过原因录入 input into errreason table</title>
</head>
<body>
<div class="easyui-layout" data-options="fit:true">
	<div data-options="region:'center',border:false" title="" noheader="true">
		<div style="padding:10px 0 10px 2px">
		    <form id="u_form" method="post" action="" style="margin:0;padding:0;">
		    	<table align="center" width="100%">
		    		<tr>
		    			<td width="100px">audit id</td>
		    			<td><input type="text" id="audit_id_U" name="audit_id" value="${map.audit_id }" style="width:250px" readonly="readonly"></td>
		    			<td width="100px">Audit Item:</td>
		    			<td>${map.audit_item }</input>
		    		</tr>
		    		<tr>
		    			<td>Dryrun:</td>
		    			<td>${map.dryrun_name }</td>
		    			<td>Domain Name:</td>
		    			<td>${map.domainName }</td>
		    		</tr>
		    		<tr>
		    			<td>Diff. Reasons:</td>
		    			<td colspan="3"><textarea id="reason_U" name="reason"  style="height:50px;width:90%;font-size: 12px;">${map.reason }</textarea></td>
		    		</tr>
		    		<tr>
		    			<td>DMP NO.:</td>
		    			<td>
		    				<input type="text" id="dmp_no_U" name="dmp_no" value="${map.dmp_no }" style="width:250px">
		    			</td>
		    			<td>Environment:</td>
		    			<td><input type="text" id="env_U" name="env" value="${map.env }" readonly="readonly"></td>
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
	if($('#u_form #min_analysis_U').val() == ""){
		alert("Difference Reasons is Required！");
		return;
	}
	
	var formData = $('#u_form').form( 'getData');
// 	console.log("formData:" + $.toJSON(formData) );
	
	update.init();
	update.jdata.condition.audit_id = formData.audit_id;
	update.jdata.operator.audit_id = OPE.EQUAL;
	update.jdata.condition.env = formData.env;
	update.jdata.operator.env = OPE.EQUAL;
	for (var field in formData) {
		update.jdata.data[field] = formData[field];
	}
	
	jQuery.post( getContextPath()+"/busi/migAuditvErrreasonBUSIinputReason.action", 
		{
// 			tableName : "mig_auditv_errreason",
			jdata: encodeURI($.toJSON( update.jdata )) 
		}, 
		function( oResponse, status ){
			oResponse = eval("(" + oResponse + ")");
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
		<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" href="javascript:void(0)" onclick="javascript:update.submitForm()" style="width:80px">Submit</a>
		<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="javascript:void(0)" onclick="javascript:$('#win').dialog('close');" style="width:80px">Cancel</a>
	</div>
</div>
</body>
</html>