<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>合法性稽核配置树关系修改界面-修改节点名称</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

</head>
<body>
<div class="easyui-layout" data-options="fit:true">
	<div data-options="region:'center',border:false" title="" noheader="true">
		<div style="padding:5px 0 5px 5px">
		    <form id="u_form_rel" method="post" action="" style="margin:0;padding:0;">
		    	<table align="center" width="100%">
                <tr>
                    <td>id:</td>
                    <td>
                        <input id="id_rel_U" name="id" value="${map.id}" readonly="readonly">
                    </td>
                </tr>
                <tr>
                    <td>Name:</td>
                    <td>
                        <input id="name_rel_U" name="name" value="${map.name}" class="easyui-validatebox" type="text" data-options="required:true">
                    </td>
                </tr>
                <tr>
                    <td>isleaf:</td>
                    <td>
                    	<select class="easyui-combobox" id="isleaf_rel_U" name="isleaf" style="width:100px;" 
                    		data-options="readonly:true, value:'${map.isleaf}'" >
                        	<option value="1">叶子</option>
                        	<option value="0">非叶子</option>
                        </select>
                    </td>
                </tr>
<!--                 <tr> -->
<!--                     <td>remark:</td> -->
<!--                     <td> -->
<!--                         <input id="remark_rel" name="remark" value=""> -->
<!--                     </td> -->
<!--                 </tr> -->
            </table>
            </form>
        </div>
    </div>
    <div data-options="region:'south',border:false" style="text-align:right;padding:5px 5px 5px 0;background:#F4F4F4;">
		<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" href="javascript:void(0)" onclick="javascript: update.submitForm()" style="width:80px">Submit</a>
		<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="javascript:void(0)" onclick="javascript:$('#win-small').window('close');" style="width:80px">Cancel</a>
	</div>
</div>
<script type="text/javascript">

update.submitForm = function (){
	if( ! $('#u_form_rel').form("validate") )
	{
		return;
	}
	
	var formData = $('#u_form_rel').form( 'getData');
	update.init();
	update.jdata.condition.id = formData.id;
	update.jdata.operator.id = OPE.EQUAL;
	for (var field in formData) {
		update.jdata.data[field] = formData[field];
	}
	
	jQuery.post( getContextPath()+"/curd/migAuditvConfigtreeCURDmodify.action", 
		{
			jdata: $.toJSON( update.jdata )
		}, 
		function( oResponse, status ){
			alert(oResponse.msg);
			if(oResponse.success == true){
				$('#win-small').window('close');
				var t = $('#tt');
				var node = t.tree('getSelected');
				var parent = t.tree('getParent', node.target)
				t.tree('reload', parent.target);
			}
	});
};
jQuery(document).ready(function(){
	
}); 
</script>
</body>
</html>