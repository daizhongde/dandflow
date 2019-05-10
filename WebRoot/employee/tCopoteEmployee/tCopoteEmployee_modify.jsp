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
		    			<td><input type="hidden"
							id="id_U" name="id" value="${map.id }"></input>
						</td>
		    		</tr>
					<tr>
						<td>部门:</td>
						<td>
	                        <input class="easyui-combotree" 
			                        id='pid_U' name="pid" value="" editable="false"
			                        data-options="valueField:'id', textField:'text',value:'${map.pid }',
			                        	required:true,
			                        	data:goBizTreeData,
			                         	panelWidth:400,panelHeight: 350" 
			                        style="width:150px;">
			            </td>
					</tr>
					<tr>
						<td>姓名:</td>
						<td><input class="easyui-validatebox" type="text"
							id="name_U" name="name" value="${map.name }" 
							data-options="required:true"
							style="width: 300px"></input></td>
					</tr>
		    		<tr>
		    			<td>性别:</td>
		    			<td><select class="easyui-combobox" id="sex_U" name="sex"
		    				style="width:150px;" data-options="required:true">
		    			 		<option value="">--选择--</option>
	                        	<option value="1" ${map.sex=='1'?'selected=selected':'' }>男</option>
	                        	<option value="2" ${map.sex=='2'?'selected=selected':'' }>女</option>
	                        </select>
	                    </td>
		    		</tr>
					<tr>
						<td>员工编号:</td>
						<td><input class="easyui-validatebox" type="text" id="employee_no_U"
							name="employee_no"  value="${map.employee_no }"
							data-options="required:true" style="width: 300px">（工资表格上的员工编号）</input>
						</td>
					</tr>
					<tr>
						<td>公司邮箱:</td>
						<td><input class="easyui-validatebox" type="text" id="alias_U"
							name="alias"  value="${map.alias }"
							data-options="required:true" style="width: 300px">（也可填写其他邮箱）</input>
						</td>
					</tr>
					<tr>
						<td>手机号码:</td>
						<td><input class="easyui-validatebox" type="text" id="mobile_U"
							name="mobile"  value="${map.mobile }"
							data-options="required:false" style="width: 300px">（可不填）</input>
						</td>
					</tr>
					<tr>
						<td>工牌号码:</td>
						<td><input class="easyui-validatebox" type="text" id="employee_cardno_U"
							name="employee_cardno"  value="${map.employee_cardno }"
							data-options="required:false" style="width: 300px">（可不填）</input>
						</td>
					</tr>
					<tr>
						<td>身份证号码:</td>
						<td><input class="easyui-validatebox" type="text" id="employee_idcard_U"
							name="employee_idcard"   value="${map.employee_idcard }"
							data-options="required:false" style="width:300px">（可不填）</input>
						</td>
					</tr>
					<tr>
						<td>QQ号码:</td>
						<td><input class="easyui-validatebox" type="text" id="qq_U"
							name="qq"   value="${map.qq }"
							data-options="required:false" style="width:300px">（可不填）</input>
						</td>
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
	
	var formData = $('#u_form').form( 'getData');
	update.init();
	update.jdata.condition.id = formData.id;
	update.jdata.operator.id = OPE.EQUAL;
	for (var field in formData) {
		update.jdata.data[field] = formData[field];
	}
	
	update.jdata.data["uin"] = formData["employee_no"];
	
	var t = $('#pid_U').combotree('tree');	// get the tree object
	var n = t.tree('getSelected');		// get selected node
	console.log("node："+$.toJSON(n));
	update.jdata.data["department"] = n["text"];
	
	var checkCNresult = checkChineseName("name_U");
	var checkEMresult = checkEmail("alias_U");
	var checkICresult = checkIDCard("employee_idcard_U");
	if(!checkCNresult){
		alert('真实姓名填写有误!');
		document.getElementById("name_U").focus();
		return false;
	}
	if(!checkEMresult){
		alert('验邮件号码验证不通过!');
		document.getElementById("alias_U").focus();
		return false;
	}
	if(!checkICresult && document.getElementById("employee_idcard_U").value!=''){
		alert('身份证号码验证不通过！要么填正确，要么置空！');
		document.getElementById("employee_idcard_U").focus();
		return false;
	}
	
	// 员工编号、工牌号码为4位数字
	var reg4n = /^(\d{4}$)/; 
	if(reg4n.test(document.getElementById("employee_no_U").value) === false) 
	{ 
		alert("员工编号为4位数字！");
		document.getElementById("employee_no_U").focus();
		return false;
	}
	if(reg4n.test(document.getElementById("employee_cardno_U").value) === false) 
	{ 
		alert("工牌号码为4位数字！");
		document.getElementById("employee_cardno_U").focus();
		return false;
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