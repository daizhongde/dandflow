<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>稽核不通过原因录入 input into result table</title>
</head>
<body>
<div class="easyui-layout" data-options="fit:true">
	<div data-options="region:'center',border:false" title="" noheader="true">
		<div style="padding:10px 0 10px 30px">
		    <form id="u_form" method="post" action="" style="margin:0;padding:0;">
		    	<table align="center" width="100%">
		    		<tr>
		    			<td width="100px"></td>
		    			<td><input type="hidden" id="audit_id_U" name="audit_id" value="${map.audit_id }" readonly="readonly"></input></td>
		    			<td width="100px"></td>
		    			<td></td>
		    		</tr>
		    		<tr>
		    			<td>Audit Item:</td>
		    			<td>${map.audit_name }</input>
		    			</td>
		    			<td>Dryrun:</td>
		    			<td>${map.dryrun_name }</td>
		    		</tr>
		    		<tr>
		    			<td>Diff. Reasons:</td>
		    			<td colspan="3"><textarea id="min_analysis_U" name="min_analysis"  style="height:50px;width:90%;font-size: 12px;">${map.min_analysis }</textarea></td>
		    		</tr>
		    		<tr>
		    			<td>DMP NO.:</td>
		    			<td colspan="3">
		    				<input type="text" id="dmp_num_U" name="dmp_num" value="${map.dmp_num }" style="width:250px">
		    			</td>
		    		</tr>
		    		<tr>
		    			<td>Domain Name:</td>
		    			<td>${map.domain2 }</td>
		    			<td>Table Name:</td>
		    			<td>${map.table_name }</td>
		    		</tr>
		    		<tr>
		    			<td>Source Value:</td>
		    			<td>${map.src_value }</td>
		    		</tr>
		    		<tr>
		    			<td>Target Value:</td>
		    			<td>${map.dst_value }</td>
		    		</tr>
		    		<tr>
		    			<td>Difference Value:</td>
		    			<td>${map.min_value }</td>
		    		</tr>
		    		<tr>
		    			<td>Unit:</td>
		    			<td>${map.audit_unit }</td>
		    			<td>Invalid Table:</td>
		    			<td>${map.invalid_data_table }</td>
		    		</tr>
		    		<tr>
		    			<td>Execute Result:</td>
		    			<td>${map.result }</td>
		    			<td>Auditor:</td>
		    			<td>${map.audit_author }</td>
		    		</tr>
		    		<tr>
		    			<td>Audit Time:</td>
		    			<td colspan="3">${map.hdate }</td>
		    		</tr>
		    		<tr>
		    			<td>Remark:</td>
		    			<td colspan="3">${map.remark }</td>
		    		</tr>
		    		<tr>
		    			<td>Success Flag:</td>
		    			<td>${map.success}</td>
		    			<td>Error Data Count:</td>
		    			<td>${map.invalid_data_cnt }</td>
		    		</tr>
		    		<tr>
		    			<td>Error Msg:</td>
		    			<td colspan="3">${map.err_msg }</td>
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
		<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" href="javascript:void(0)" onclick="javascript:update.submitForm()" style="width:80px">Submit</a>
		<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="javascript:void(0)" onclick="javascript:$('#win').dialog('close');" style="width:80px">Cancel</a>
	</div>
</div>
</body>
</html>