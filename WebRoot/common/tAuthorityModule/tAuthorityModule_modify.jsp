<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>修改模块 JDATA</title>
	<link rel="stylesheet" type="text/css" href="../../scripts/jquery-easyui/1.3.5/themes/default/easyui.css"/>
	<link rel="stylesheet" type="text/css" href="../../scripts/jquery-easyui/1.3.5/themes/icon.css"/>
	<script type="text/javascript" src="../../scripts/jquery-1.4.4.min.js" ></script>
	<script type="text/javascript" src="../../scripts/jquery-easyui/1.3.5/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="../../scripts/json/jquery.json-2.2.min.js"></script>
</head>
<body rightmargin="0" bottommargin="0" leftmargin="0" topmargin="0" class="easyui-layout">
<div data-options="region:'center',border:false" title="模块main" style="overflow:hidden;" noheader="true">
<!-- 	<div class="easyui-panel" title="New Topic" style="width:400px"> -->
		<div style="padding:10px 0 10px 60px">
	    <form id="update.form" method="post" action="../../jsonCURD/tAuthorityModuleJsonCURDadd.action">
	    	<table align="center">
	    		<tr>
	    			<td>模块名称:</td>
	    			<td><input class="easyui-validatebox" type="text" id="cmname_U" name="pojo.CMname" value="${pojo.CMname }" data-options="required:true"></input></td>
	    		</tr>
	    		<tr>
	    			<td>模块路径:</td>
	    			<td><input class="easyui-validatebox" type="text" id="cmpath_U" name="pojo.CMpath" value="${pojo.CMpath }" data-options="required:false"></input></td>
	    		</tr>
	    		<tr>
	    			<td>叶子节点:</td>
	    			<td><select class="easyui-combobox" id="cmleaf_U" name="pojo.CMleaf" style="width:200px;">
	    			<!-- <option value="">--选择--</option> --> 
                        	<option value="true">是</option>
                        	<option value="false">否</option>
                        </select>
                    </td>
	    		</tr>
	    		<tr>
	    			<td>模块级别:</td>
	    			<td><select class="easyui-combobox" id="nmlevel_U" name="pojo.NMlevel" style="width:200px;" data-options="required:true">
	    					<option value="0">零级模块</option>
                        	<option value="1">一级模块</option>
                        	<option value="2">二级模块</option>
                        	<option value="3">三级模块</option>
                        	<option value="4">四级模块</option>
                        </select>
                     </td>
	    		</tr>
	    		<tr>
	    			<td>上级模块:</td>
	    			<td>
<!-- 	    			Create combobox from <select> element with a pre-defined structure. -->
<!-- 	    			<select class="easyui-combobox" id="nmparent_U" name="pojo.NMparent" style="width:200px;" data-options="required:true"> -->
<!--                         	<option value="1">湖南社保金融卡省内前置系统</option> -->
<!--                         	<option value="2">系统管理</option> -->
<!--                         	<option value="7">开户机构维护</option> -->
<!--                         	<option value="9">卡商维护</option> -->
<!--                         	<option value="12">系统参数管理</option> -->
<!--                         </select> -->
<!-- <input class="easyui-combobox" id="nmparent_U" name="pojo.NMparent" data-options=" -->
<!-- 		valueField: 'label', -->
<!-- 		textField: 'value', -->
<!-- 		data: [{ -->
<!-- 			label: '1', -->
<!-- 			value: '湖南社保金融卡省内前置系统' -->
<!-- 		},{ -->
<!-- 			label: '2', -->
<!-- 			value: '系统管理' -->
<!-- 		},{ -->
<!-- 			label: '7', -->
<!-- 			value: '开户机构维护' -->
<!-- 		}]" /> -->
<!-- ?jdata={ act: \'queryCBB\',condition: {},operator : {} }   get_data.json -->
<!-- ../../common/getComboboxData_Parent_JEasyUICommontAuthorityModule.action -->
<!-- 						<input id="nmparent_U" class="easyui-combobox" name="pojo.NMparent" style="width:200px;" -->
<!-- 						data-options="valueField:'ID',textField:'TEXT',url:'../../cbb/tAuthorityModuleCBBgetCBBData_Parent.action'"> -->
                        <input class="easyui-combotree" 
	                        id="nmparent_U" name="pojo.NMparent" 
	                        data-options="url:'../../tree/tAuthorityModuleTREEquery_JEasyUI_CBT.action',method:'post',required:true" 
	                        style="width:260px;">
                    </td>
	    		</tr>
	    		
	    		<tr>
	    			<td>模块次序:</td>
	    			<td><input class="easyui-numberbox" type="text" id="nmorder_U" name="pojo.NMorder" value="${pojo.NMorder }" ></input></td>
	    		</tr>
	    		
	    		<tr>
	    			<td>模块说明:</td>
	    			<td><textarea id="cmnote_U" name="pojo.CMnote"  style="height:60px;font-size: 12px;">${pojo.CMnote }</textarea></td>
	    		</tr>
	    	</table>
	    </form>
	    </div>
	    <div style="text-align:center;padding:5px">
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="update.submitForm()">提交</a>
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="update.clearForm()">清空</a>
	    </div>
<!-- 	</div> -->
<script type="text/javascript">
var update = VIRTUE.update;
// update.jdata = { act: "update", data : {} ,condition: {nmid: "${pojo.NMid }"},operator : {nmid : 1}};

update.submitForm = function (){
	var cmname = $('#cmname_U').val();
	var cmpath = $('#cmpath_U').val();
	var cmleaf = $('#cmleaf_U').combobox('getValue');
// 	var nmparent = $('#nmparent_U').combobox('getValue');
	var nmparent = $('#nmparent_U').combotree('getValue');
	var nmlevel = $('#nmlevel_U').combobox('getValue');
	var nmorder = $('#nmorder_U').val();
	var cmnote = $('#cmnote_U').val();
	
	update.init();
	update.jdata.condition.id = ${pojo.NMid };
	update.jdata.operator.id = OPE.EQUAL;
	
	update.jdata.data.name = cmname;
	update.jdata.data.path = cmpath;
	update.jdata.data.leaf = cmleaf;
	update.jdata.data.parent = nmparent;
	update.jdata.data.level = nmlevel;
	update.jdata.data.order = nmorder;
	update.jdata.data.note = cmnote;
	jQuery.post( "../../curd/tAuthorityModuleCURDmodify.action", { jdata: encodeURI($.toJSON( update.jdata )) }, function( oResponse, status ){
// 		oResponse = eval("(" + oResponse + ")");
		alert(oResponse.msg);
		if(oResponse.success == true){
			$('#grid').datagrid('reload',{jdata: encodeURI($.toJSON( query.jdata ))});
			$('#win').window('close');
		}
	   });
};
update.clearForm = function (){
	$('#update.form').form('clear');
};
jQuery(document).ready(function(){
	$("#cmleaf_U").val("${pojo.CMleaf }"); 
	$("#nmparent_U").val("${pojo.NMparent }"); //pojo.NMparent.NMid
	$("#nmlevel_U").val("${pojo.NMlevel }"); 
	
// 	alert($('#cmleaf_U').combobox('getValue'));
// 	$('#cmleaf_U').combobox('setValue', "${pojo.CMleaf }");
// 	alert($('#cmleaf_U').combobox('getValue'));
// 	$('#nmparent_U').combobox('setValue', "${pojo.NMparent }");
// 	$('#nmlevel_U').combobox('setValue', "${pojo.NMlevel }");

// 	document.getElementById("cmleaf_U").value=${pojo.CMleaf };
// 	document.getElementById("nmparent_U").value=${pojo.NMparent };
// 	document.getElementById("nmlevel_U").value=${pojo.NMlevel };
}); 
</script>
</div>
</body>
</html>