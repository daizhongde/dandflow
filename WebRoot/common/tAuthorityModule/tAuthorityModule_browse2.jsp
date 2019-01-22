<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page	import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page	import="person.daizhongde.authority.spring.service.impl.TAuthorityModuleServiceImpl"%>
<%@page	import="person.daizhongde.authority.hibernate.pojo.TAuthorityModule"%><%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(session.getServletContext());
// TAuthorityModule module = TAuthorityModuleServiceImpl.getFromApplicationContext(ctx).findById( Integer.valueOf(request.getAttribute("pojo.NMparent").toString()) );
//ordinary view jsp
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>浏览模块 JDATA R:pojo</title>
<!-- 	<link rel="stylesheet" type="text/css" href="../../scripts/jquery-easyui/1.3.5/themes/default/easyui.css"/> -->
<!-- 	<link rel="stylesheet" type="text/css" href="../../scripts/jquery-easyui/1.3.5/themes/icon.css"/> -->
<!-- 	<script type="text/javascript" src="../../scripts/jquery-1.4.4.min.js" ></script> -->
<!-- 	<script type="text/javascript" src="../../scripts/jquery-easyui/1.3.5/jquery.easyui.min.js"></script> -->
<!-- 	<script type="text/javascript" src="../../scripts/json/jquery.json-2.2.min.js"></script> -->
</head>
<body rightmargin="0" bottommargin="0" leftmargin="0" topmargin="0" class="easyui-layout">
<div data-options="region:'center',border:false" title="模块main" style="overflow:hidden;" noheader="true">
<!-- 	<div class="easyui-panel" title="New Topic" style="width:400px"> -->
		<div style="padding:10px 0 10px 60px">
	    <form id="ff_V" method="post" action="">
	    	<table align="center">
	    		<tr>
	    			<td>模块名称:</td>
	    			<td>${pojo.CMname }</td>
	    		</tr>
	    		<tr>
	    			<td>模块路径:</td>
	    			<td>${pojo.CMpath }</td>
	    		</tr>
	    		<tr>
	    			<td>叶子节点:</td>
	    			<td><span id="cmleaf_V" name="pojo.CMleaf" >${pojo.CMleaf }</span></td>
	    		</tr>
	    		<tr>
	    			<td>上级模块:</td>
<!-- 	    			out.print(module.getCMname()); -->
	    			<td><span id="nmparent_V" name="pojo.NMparent" >${pojo.NMparent.CMname }</span></td>
	    		</tr>
	    		<tr>
	    			<td>模块次序:</td>
	    			<td>${pojo.NMorder }</td>
	    		</tr>
	    		<tr>
	    			<td>模块级别:</td>
	    			<td><span id="nmlevel_V" name="pojo.NMlevel" >${pojo.NMlevel }</span></td>
	    		</tr>
	    		<tr>
	    			<td>模块说明:</td>
<!-- value="${pojo.CMleaf }" value="${pojo.NMparent }" value="${pojo.NMlevel }" value="${pojo.CMnote }"  -->
	    			<td>${pojo.CMnote }</td>
	    		</tr>
	    	</table>
	    </form>
	    </div>
	    <div style="text-align:center;padding:5px">
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="close_V()">关闭</a>
	    </div>
<!-- 	</div> -->
<script type="text/javascript">
function submitForm_V(){

}
function clearForm_V(){

}
function close_V(){
// 	window.close();
	$('#win').window('close');
}

$(document).ready(function(){
	var cmleaf = getCmleaf("${pojo.CMleaf }");
// 	var nmparent = getNmparent("${pojo.NMparent }");
	var nmlevel = getNmlevel( "${pojo.NMlevel }" );//这里可能需要去掉双引号
// 	alert(nmlevel);
$("#cmleaf_V").html( cmleaf );
$("#nmlevel_V").html( nmlevel );
// 	document.getElementById("cmleaf_V").value = cmleaf;
// 	document.getElementById("nmparent_V").value=nmparent;
// 	document.getElementById("nmlevel_V").value = nmlevel;
}); 
</script>
</div>
</body>
</html>