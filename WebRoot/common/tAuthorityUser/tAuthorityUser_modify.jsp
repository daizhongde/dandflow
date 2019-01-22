<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>修改用户 JDATA</title>
	<link rel="stylesheet" type="text/css" href="../../scripts/jquery-easyui/1.3.5/themes/default/easyui.css"/>
	<link rel="stylesheet" type="text/css" href="../../scripts/jquery-easyui/1.3.5/themes/icon.css"/>
	<script type="text/javascript" src="../../scripts/jquery-1.4.4.min.js" ></script>
	<script type="text/javascript" src="../../scripts/jquery-easyui/1.3.5/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="../../scripts/json/jquery.json-2.2.min.js"></script>
</head>
<body rightmargin="0" bottommargin="0" leftmargin="0" topmargin="0" class="easyui-layout">
<div data-options="region:'center',border:false" title="用户main" style="overflow:hidden;" noheader="true">
<!-- 	<div class="easyui-panel" title="New Topic" style="width:400px"> -->
		<div style="padding:10px 0 10px 60px">
	    <form id="update.form" method="post">
	    	<table align="center">
	    		<tr>
	    			<td>所属机构:</td>
	    			<td>
	    			<!-- 	    		_L3  	_Async -->
						<input class="easyui-combotree" 
	                        id="iid_U" name="pojo.NIid" 
	                        data-options="url:'../../tree/tAuthorityInstTREEquery_JEasyUI_CBT_Async.action?id=${pojo.TAuthorityInst.NIsuperior}',
	                        method:'post',
	                        mode: 'remote', 
	                        value: ${pojo.TAuthorityInst.NIid },
	                        onShowPanel:function(){
								var t = jQuery('#iid_U').combotree('tree');
								var node = t.tree( 'find', ${pojo.TAuthorityInst.NIid } );
								t.tree('expandTo', node.target);
								t.tree('scrollTo',node.target);
							},
							onLoadSuccess :  function( node, data ){
								if(node===null){
									var t = jQuery('#iid_U').combotree('tree');
									var snode = t.tree( 'find', selectedIId );
									if(snode===null)
									{
										//expand selectedIPId
										var spnode = t.tree( 'find', selectedIPId );
										t.tree('expand', spnode.target);
									}
								}
							},
							disabled:true,
	                        required:true" 
	                        style="width:260px;">
                    </td>
	    		</tr>
	    		<tr>
	    			<td>用户ID:</td>
	    			<td><span id="id">${pojo.NUid }</span></td>
	    		</tr>
	    		<tr>
	    			<td>用户登陆名:</td>
	    			<td>${pojo.CUlogname }</td>
	    		</tr>
	    		<tr>
	    			<td>用户姓名:</td>
	    			<td><input class="easyui-validatebox" type="text" id="name_U" name="pojo.CUname" value="${pojo.CUname }" data-options="required:true"></input></td>
	    		</tr>
	    		
	    		<tr>
	    			<td>性别:</td>
	    			<td><select class="easyui-combobox" id="sex_U" name="pojo.CUsex" style="width:200px;" value="${pojo.CUsex }" data-options="required:true">
	    			 		<option value="">--选择--</option>
                        	<option value="男">男</option>
                        	<option value="女">女</option>
                        </select>
                    </td>
	    		</tr>
	    		<tr>
	    			<td>员工号:</td>
	    			<td><input class="easyui-validatebox" type="text" id="employee_number_U" name="pojo.EmployeeNumber" value="${pojo.EMployeeNumber }" data-options="required:true"></input></td>
	    		</tr>
	    		<tr>
	    			<td>上司ID:</td>
	    			<td><input class="easyui-numberbox" type="text" id="supervisor_id_U" name="pojo.SupervisorId" value="${pojo.SUpervisorId }"></input></td>
	    		</tr>
	    		<tr>
	    			<td>联系电话:</td>
	    			<td><input class="easyui-numberbox" type="text" id="phone_U" name="pojo.CUphone" value="${pojo.CUphone }"></input></td>
	    		</tr>
	    		<tr>
	    			<td>办公电话:</td>
	    			<td><input type="text" id="tel_U" name="pojo.CUtel" value="${pojo.CUtel }"></input></td>
	    		</tr>
	    		<tr>
	    			<td>传真:</td>
	    			<td><input type="text" id="fax_U" name="pojo.CUfax" value="${pojo.CUfax }"></input></td>
	    		</tr>
	    		<tr>
	    			<td>电子邮箱:</td>
	    			<td><input type="text" id="email_U" name="pojo.CUemail" value="${pojo.CUemail }"></input></td>
	    		</tr>
	    		<tr>
	    			<td>QQ:</td>
	    			<td><input class="easyui-numberbox" type="text" id="qq_U" name="pojo.CUqq" value="${pojo.CUqq }"></input></td>
	    		</tr>
	    		<tr>
	    			<td>通迅地址:</td>
	    			<td><input type="text" id="addr_U" name="pojo.CUaddr" value="${pojo.CUaddr }"></input></td>
	    		</tr>
				<tr>
	    			<td>备注:</td>
	    			<td><textarea id="note_U" name="pojo.CUnote" style="height:32px;width:260px;font-size: 12px;">${pojo.CUnote }</textarea></td>
	    		</tr>
	    	</table>
	    </form>
	    </div>
	    <div style="text-align:center;padding:5px">
	    	<input type="button" onclick="update.submitForm()" value="提交"/>
	    </div>
<!-- 	</div> -->
<script type="text/javascript">
var update = VIRTUE.update;
// update.jdata = { act: "update", data : {} ,condition: {nmid: "{pojo.NUid }"},operator : {nmid : 1}};

update.submitForm = function (){
// 	var id = $('#id_U').val();
// 	var logname = $('#logname_U').val();
	var name = $('#name_U').val();
	var sex = $('#sex_U').combobox('getValue');
	var employee_number = $('#employee_number_U').val();//add by daizd, 20160420
	var supervisor_id = $('#supervisor_id_U').val();//add by daizd, 20160420
	var phone = $('#phone_U').val();
	var tel = $('#tel_U').val();
	var fax = $('#fax_U').val();
	var email = $('#email_U').val();
	var qq = $('#qq_U').val();
	var addr = $('#addr_U').val();
	var iid = $('#iid_U').combotree('getValue');
	var note = $('#note_U').val();
	
	update.init();
	update.jdata.condition.id = ${pojo.NUid };
	update.jdata.operator.id = OPE.EQUAL;
	
// 	update.jdata.data.logname = logname;
	update.jdata.data.name = name;
	update.jdata.data.sex = sex;
	update.jdata.data.employee_number = employee_number;//add by daizd, 20160420
	update.jdata.data.supervisor_id = supervisor_id;//add by daizd, 20160420
	update.jdata.data.phone = phone;
	update.jdata.data.tel = tel;
	update.jdata.data.fax = fax;
	update.jdata.data.email = email;
	update.jdata.data.qq = qq;
	update.jdata.data.addr = addr;
	update.jdata.data.iid = iid;
	update.jdata.data.note = note;
	
	jQuery.post( "../../curd/tAuthorityUserCURDmodify.action", { jdata: encodeURI($.toJSON( update.jdata )) }, function( oResponse, status ){
// 		oResponse = eval("(" + oResponse + ")");
		alert(oResponse.msg);
		if(oResponse.success == true){
			$('#grid').datagrid('reload',{jdata: encodeURI($.toJSON( query.jdata ))});
			$('#win').window('close');
		}
	   });
};

jQuery(document).ready(function(){
	document.getElementById("sex_U").value = "${pojo.CUsex }";
}); 
</script>
</div>
</body>
</html>