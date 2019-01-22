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
<body rightmargin="0" bottommargin="0" leftmargin="0" topmargin="0" class="easyui-layout">
<div data-options="region:'center',border:false" title="模块main" style="overflow:hidden;" noheader="true">
	<div style="padding:10px 0 10px 60px">
	    <form id="u_form" method="post" action="">
	    	<table align="center">
	    		<tr>
	    			<td></td>
	    			<td><input type="hidden" name="main_id" value="${map.main_id }"></input></td>
	    		</tr>
	    		<tr>
	    			<td>配置集合名称:</td>
	    			<td><input class="easyui-validatebox" type="text" 
	    			id="name_U" name="name" value="${map.name }" data-options="required:true"></input></td>
	    		</tr>
	    		<tr>
	    			<td>备注:</td>
	    			<td><textarea id="remark_U" name="remark"  style="height:60px;font-size: 12px;">${map.remark }</textarea></td>
	    		</tr>
	    	</table>
	    </form>
    </div>
    <div style="text-align:center;padding:5px">
        <input type='button' onclick="update.submitForm()" value='提 交'/>&nbsp;
		<input type='button' onclick="update.clearForm()" value='清 空'/>
    </div>
<script type="text/javascript">
var update = VIRTUE.update;

update.submitForm = function (){
	if( ! $('#u_form').form("validate") )
	{
		return;
	}

	var formData = $('#u_form').form( 'getData');
	update.init();
	update.jdata.condition.main_id = formData.main_id;
	update.jdata.operator.main_id = OPE.EQUAL;
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
</div>
</body>
</html>