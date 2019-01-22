<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="org.springframework.context.ApplicationContext"%>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@ page import="person.daizhongde.authority.spring.service.impl.TAuthorityInstServiceImpl"%>
<%@ page import="person.daizhongde.authority.hibernate.pojo.TAuthorityUser" %>
<%@ page import="person.daizhongde.authority.hibernate.pojo.TAuthorityInst" %>
<%@page import="person.daizhongde.authority.constant.SessionConstants"%>
<%@ page import="person.daizhongde.authority.util.JSPParameterPrinter"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

TAuthorityUser user=(TAuthorityUser)request.getSession().getAttribute(SessionConstants.LOGIN_USER);
ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(session.getServletContext());
TAuthorityInst inst = (TAuthorityInst)TAuthorityInstServiceImpl.getFromApplicationContext(ctx).browsePOJOById( user.getTAuthorityInst().getNIid().intValue() );

JSPParameterPrinter jspPR = new JSPParameterPrinter();
// Integer IId = inst.getNIid();
// Integer IPId = inst.getNIsuperior();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>本机构资料</title>
	<link rel="stylesheet" type="text/css" href="../../scripts/jquery-easyui/1.3.5/themes/default/easyui.css"/>
	<link rel="stylesheet" type="text/css" href="../../scripts/jquery-easyui/1.3.5/themes/icon.css"/>
	<script type="text/javascript" src="../../scripts/jquery-1.4.4.min.js" ></script>
	<script type="text/javascript" src="../../scripts/jquery-easyui/1.3.5/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="../../scripts/json/jquery.json-2.2.min.js"></script>
	<script type="text/javascript" src="../../scripts/virtue/virtue.js"></script>
<style type="text/css">
<!--
body {
    background-color: #C3E3FF;
}
#mylayer {
  background-color: #FFFFCC;
  position: absolute;
  left: 5px;
  top: 2px;
}
	-->
</style>
</head>
<body rightmargin="0" bottommargin="0" leftmargin="0" topmargin="0" class="easyui-layout">
<div data-options="region:'center',border:false" title="用户main" style="overflow:hidden;background-color: rgb(228, 235, 241)" noheader="true">
	<!-- 信息已保存 -->
	<div id="mylayer" style="display: none;" ><font size="30" color="green">信息已保存!</font></div>
	
<!-- 	<div class="easyui-panel" title="New Topic" style="width:400px"> -->
		<div style="padding:10px 0 10px 60px">
	    <form id="update.form" method="post" style="margin:0;padding:0;">
	    	<table align="center">
	    		<tr>
	    			<td>上级机构:</td>
	    			<td>
						<input class="easyui-combotree" 
	                        id="superior" name="pojo.NIsuperior" 
	                        data-options="
	                        url:'../../tree/tAuthorityInstTREEquery_JEasyUI_CBT_L3.action',
	                        method:'post',
	                        mode: 'remote',
	                        //value: '<=inst.getTAuthorityInst()==null?''inst.getTAuthorityInst().getNIid().toString() >', 
	                        value: '<%=inst.getNIsuperior()==null?"":inst.getNIsuperior() %>',
							disabled:true,
	                        required:true"
	                        style="width:260px;">
                    </td>
	    		</tr>
	    		
	    		<tr>
	    			<td>机构ID:</td>
	    			<td><input type="text" id="id" name="pojo.NIid" data-options="required: true" 
	    				style="width:200px;" disabled="disabled" value="<%=inst.getNIid() %>"></input>
					</td>
	    		</tr>
	    		<tr>
	    			<td>机构编码:</td>
	    			<td><input class="easyui-validatebox" type="text" id="code" name="pojo.CIcode" data-options="required: true" 
	    			value="<%=inst.getCIcode() %>"
	    					onchange="onChangeInstField(this)" style="width:200px;" disabled="disabled"></input>
					</td>
	    		</tr>
	    		<tr>
	    			<td>机构名称:</td>
	    			<td><input class="easyui-validatebox" type="text" id="name" name="pojo.CIname" data-options="required: true" 
	    			value="<%=inst.getCIname() %>"
	    					onchange="onChangeInstField(this)" style="width:200px;" disabled="disabled"></input>
					</td>
	    		</tr>
	    		<tr>
	    			<td>机构级别:</td>
	    			<td><select class="easyui-combobox" id="level" name="pojo.NIlevel" data-options="required: true" 
	    			value="<%=inst.getNIlevel() %>"
	    						 style="width:200px;" disabled="disabled">
<!-- 	    					<option value="" selected="selected">--选择--</option> -->
	    					<option value="0">零级机构</option>
                        	<option value="1">一级机构</option>
                        	<option value="2">二级机构</option>
                        	<option value="3">三级机构</option>
                        	<option value="4">四级机构</option>
                        </select>
                     </td>
	    		</tr>
	    		<tr>
	    			<td>机构类型:</td>
<!-- 	    			1:省公司,2:市公司,3:省处理中心,4:站点,5:揽投部,6:县营业部 -->
	    			<td><select id="type" name="pojo.NItype" style="width:200px;" 
	    			value="<%=inst.getNItype() %>"
	    			onchange="onChangeInstField(this)" disabled="disabled">
<!-- 	    					<option value="" selected="selected">--选择--</option> -->
                        	<option value="1">省公司</option>
                        	<option value="2">市公司</option>
                        	<option value="3">省处理中心</option>
                        	<option value="4">站点</option>
                        	<option value="5">揽投部</option>
                        	<option value="6">县营业部</option>
                        </select>
                     </td>
	    		</tr>
	    		<tr>
	    			<td>是否某级:</td>
	    			<td><select id="leaf" name="pojo.CIleaf" style="width:200px;" 
	    			value="<%=inst.getCIleaf() %>"
	    			onchange="onChangeInstField(this)" disabled="disabled">
<!-- 	    					<option value="" selected="selected">--选择--</option> -->
                        	<option value="true">是</option>
                        	<option value="false">否</option>
                        </select>
                    </td>
	    		</tr>

	    		<tr>
	    			<td>机构领导:</td>
	    			<td><input class="easyui-validatebox" type="text" id="manager" name="pojo.CImanager" data-options="required:false" 
	    			value="<%=jspPR.out(inst.getCImanager() ) %>"
	    			onchange="onChangeInstField(this)" style="width:200px;"></input></td>
	    		</tr>
	    		<tr>
	    			<td>领导电话:</td>
	    			<td><input class="easyui-validatebox" type="text" id="mtel" name="pojo.CImtel" data-options="required:false" 
	    			value="<%=jspPR.out(inst.getCImtel()  )%>"
	    			onchange="onChangeInstField(this)" style="width:200px;"></input></td>
	    		</tr>
	    		<tr>
	    			<td>领导邮箱:</td>
	    			<td><input class="easyui-validatebox" type="text" id="memail" name="pojo.CImemail" data-options="required:false" 
	    			value="<%=jspPR.out(inst.getCImemail() ) %>"
	    			onchange="onChangeInstField(this)" style="width:200px;"></input></td>
	    		</tr>
				<tr>
	    			<td>领导QQ:</td>
	    			<td><input class="easyui-validatebox" type="text" id="mqq" name="pojo.CImqq" data-options="required:false" 
	    			value="<%=jspPR.out(inst.getCImqq() ) %>"
	    			onchange="onChangeInstField(this)" style="width:200px;"></input></td>
	    		</tr>
	    		<tr>
	    			<td>机构联系人:</td>
	    			<td><input class="easyui-validatebox" type="text" id="linkman" name="pojo.CIlinkman" data-options="required:false"
	    			value="<%=jspPR.out(inst.getCIlinkman() )%>" 
	    			onchange="onChangeInstField(this)" style="width:200px;"></input></td>
	    		</tr>
	    		<tr>
	    			<td>联系人电话:</td>
	    			<td><input class="easyui-validatebox" type="text" id="ltel" name="pojo.CIltel" data-options="required:false" 
	    			value="<%=jspPR.out(inst.getCIltel() ) %>"
	    			onchange="onChangeInstField(this)" style="width:200px;"></input></td>
	    		</tr>
	    		<tr>
	    			<td>联系人邮箱:</td>
	    			<td><input class="easyui-validatebox" type="text" id="lemail" name="pojo.CIlemail" data-options="required:false" 
	    			value="<%=jspPR.out(inst.getCIlemail() ) %>"
	    			onchange="onChangeInstField(this)" style="width:200px;"></input></td>
	    		</tr>
				<tr>
	    			<td>联系人QQ:</td>
	    			<td><input class="easyui-validatebox" type="text" id="lqq" name="pojo.CIlqq" data-options="required:false" 
	    			value="<%=jspPR.out(inst.getCIlqq() ) %>"
	    			onchange="onChangeInstField(this)" style="width:200px;"></input></td>
	    		</tr>
	    		
    			<tr>
	    			<td>机构地址:</td>
	    			<td><input class="easyui-validatebox" type="text" id="address" name="pojo.CIaddress" data-options="required:false" 
	    			value="<%=jspPR.out(inst.getCIaddress() ) %>"
	    			onchange="onChangeInstField(this)" style="width:200px;"></input></td>
	    		</tr>
	    		<tr>
	    			<td>机构简介:</td>
	    			<td><textarea id="description" name="pojo.CIdescription" style="width:300px; height:30px; font-size: 12px;" 
	    			onchange="onChangeInstField(this)"><%=jspPR.out(inst.getCIdescription() ) %></textarea></td>
	    		</tr>
	    	</table>
	    </form>
	    </div>
<!-- 	</div> -->
<script type="text/javascript">
var update = VIRTUE.update;
// update.jdata = { act: "update", data : {} ,condition: {nmid: "{pojo.NUid }"},operator : {nmid : 1}};

/** update tree node's text  **/
function onChangeInstField( o ){
	
	VIRTUE.update.init();
	VIRTUE.update.jdata.data[o.id] = o.value;
	VIRTUE.update.jdata.condition.id = <%=inst.getNIid() %>; 
	VIRTUE.update.jdata.operator.id = VIRTUE.operator.EQUAL;//default,this line can comment
	updateModel( VIRTUE.update.jdata );
};

/** update db model **/
function updateModel( jdata ){
	jQuery.post("../../curd/tAuthorityInstCURDmodify.action", 
		{
			jdata : encodeURI( jQuery.toJSON( jdata ) )
		}, function(oResponse, status) {
// 			oResponse = eval("(" + oResponse + ")");
	// 		alert(oResponse.msg);
			if (oResponse.success ) {
				//弹出一个层一秒钟后消失
			    document.getElementById("mylayer").style.display="inline";
			    var t=setTimeout("document.getElementById('mylayer').style.display='none'",1000);
			}else{
				alert(oResponse.msg);
			}
		});
};

jQuery(document).ready(function(){


}); 

</script>
</div>
</body>
</html>