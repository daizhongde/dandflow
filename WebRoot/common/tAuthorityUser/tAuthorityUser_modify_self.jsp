<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page	import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page	import="person.daizhongde.authority.spring.service.impl.TAuthorityUserServiceImpl"%>
<%@ page import="person.daizhongde.authority.hibernate.pojo.TAuthorityUser" %>
<%@page import="person.daizhongde.authority.constant.SessionConstants"%>
<%@ page import="person.daizhongde.authority.util.JSPParameterPrinter"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

TAuthorityUser user=(TAuthorityUser)request.getSession().getAttribute( SessionConstants.LOGIN_USER );
ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(session.getServletContext());
user = TAuthorityUserServiceImpl.getFromApplicationContext(ctx).findById( user.getNUid() );

JSPParameterPrinter jspPR = new JSPParameterPrinter();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>个人资料</title>
	<link rel="stylesheet" type="text/css" href="../../scripts/jquery-easyui/1.3.5/themes/default/easyui.css"/>
	<link rel="stylesheet" type="text/css" href="../../scripts/jquery-easyui/1.3.5/themes/icon.css"/>
	<script type="text/javascript" src="../../scripts/jquery-1.4.4.min.js" ></script>
	<script type="text/javascript" src="../../scripts/jquery-easyui/1.3.5/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="../../scripts/json/jquery.json-2.2.min.js"></script>
	<script type="text/javascript" src="../../scripts/virtue/virtue.js"></script>
<style type="text/css">
<!--
body{
	background-color: rgb(228, 235, 241);
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
	<div id="mylayer" style="display: none;" ><font size="30" color="green">Modify Saved!</font></div>
	
<!-- 	<div class="easyui-panel" title="New Topic" style="width:400px"> -->
		<div style="padding:10px 0 10px 60px">
	    <form id="update.form" method="post" style="margin:0;padding:0;">
	    	<table align="center">
	    		<tr>
	    			<td>Institution:</td>
	    			<td>
	    			<!-- 	    		_L3  	_Async -->
						<input class="easyui-combotree" 
	                        id="iid_U" name="pojo.NIid_M" 
	                        data-options="url:'../../tree/tAuthorityInstTREEquery_JEasyUI_CBT_Async.action?id=${pojo.TAuthorityInst.NIsuperior}',
	                        method:'post',
	                        mode: 'remote', 
	                        value: <%=user.getTAuthorityInst().getNIid() %>,
	                        onShowPanel:function( ){
								var t = jQuery('#iid_U').combotree('tree');
								var node = t.tree( 'find', <%=user.getTAuthorityInst().getNIid() %>);
								t.tree('expandTo', node.target);
								t.tree('scrollTo',node.target);
							},
	                        required:true"
	                        style="width:260px;" disabled="disabled">
                    </td>
	    		</tr>
	    		<tr>
	    			<td>User ID:</td>
	    			<td><span id="id"><%=user.getNUid() %></span></td>
	    		</tr>
	    		<tr>
	    			<td>Login Name:</td>
	    			<td><%=user.getCUlogname() %></td>
	    		</tr>
	    		<tr>
	    			<td>Real Name:</td>
	    			<td><input class="easyui-validatebox" type="text" id="name" name="pojo.CUname" 
	    			value="<%=user.getCUname() %>" data-options="required:true"
	    			onchange="onChangeUserField(this)"></input>
	    					&nbsp;&nbsp;&nbsp;&nbsp;
	    				Sex:
	    				<select id="sex" name="pojo.CUsex" data-options="required:true"  value="<%=user.getCUsex() %>"
	    				onchange="onChangeUserField(this)">
	    			 		<option value="">--opt--</option>
                        	<option value="男">male</option>
                        	<option value="女">female</option>
                        </select>
	    			</td>
	    		</tr>
	    		
	    		<tr>
	    			<td>Mobile:</td>
	    			<td><input type="text" id="phone" name="pojo.CUphone" 
	    			value="<%=jspPR.out(user.getCUphone()) %>"
	    			onchange="onChangeUserField(this)"></input></td>
	    		</tr>
	    		<tr>
	    			<td>Office tel:</td>
	    			<td><input type="text" id="tel" name="pojo.CUtel" 
	    			value="<%=jspPR.out(user.getCUtel()) %>"
	    			onchange="onChangeUserField(this)"></input></td>
	    		</tr>
	    		<tr>
	    			<td>Fax no:</td>
	    			<td><input type="text" id="fax" name="pojo.CUfax" 
	    			value="<%=jspPR.out(user.getCUfax()) %>"
	    			onchange="onChangeUserField(this)"></input></td>
	    		</tr>
	    		<tr>
	    			<td>E-mail:</td>
	    			<td><input type="text" id="email" name="pojo.CUemail" 
	    			value="<%=jspPR.out(user.getCUemail()) %>"
	    			onchange="onChangeUserField(this)"></input></td>
	    		</tr>
	    		<tr>
	    			<td>QQ:</td>
	    			<td><input class="easyui-numberbox" type="text" id="qq" name="pojo.CUqq" 
	    			value="<%=jspPR.out(user.getCUqq()) %>"
	    			onchange="onChangeUserField(this)"></input></td>
	    		</tr>
	    		<tr>
	    			<td>Contact Address:</td>
	    			<td><input type="text" id="addr" name="pojo.CUaddr" 
	    			value="<%=jspPR.out(user.getCUaddr()) %>"
	    			onchange="onChangeUserField(this)"></input></td>
	    		</tr>
				<tr>
	    			<td>Remark:</td>
	    			<td><textarea id="note" name="pojo.CUnote" style="height:32px;width:260px;font-size: 12px;"
	    			onchange="onChangeUserField(this)"><%=jspPR.out(user.getCUnote()) %></textarea></td>
	    		</tr>
	    	</table>
	    </form>
	    </div>
<!-- 	</div> -->
<script type="text/javascript">
var update = VIRTUE.update;
// update.jdata = { act: "update", data : {} ,condition: {nmid: "{pojo.NUid }"},operator : {nmid : 1}};

/** update tree node's text  **/
function onChangeUserField( o ){
	
	VIRTUE.update.init();
	VIRTUE.update.jdata.data[o.id] = o.value;
	VIRTUE.update.jdata.condition.id = <%=user.getNUid() %>; 
	VIRTUE.update.jdata.operator.id = VIRTUE.operator.EQUAL;//default,this line can comment
	updateModel( VIRTUE.update.jdata );
};

/** update db model **/
function updateModel( jdata ){
	jQuery.post("../../curd/tAuthorityUserCURDmodify.action", 
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
	document.getElementById("sex").value = "<%=user.getCUsex() %>";
}); 

</script>
</div>
</body>
</html>