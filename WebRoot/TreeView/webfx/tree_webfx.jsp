<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page	import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page	import="person.daizhongde.authority.spring.service.impl.TAuthorityModuleServiceImpl"%><%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

	ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(session.getServletContext());
	String jsonData = TAuthorityModuleServiceImpl.getFromApplicationContext(ctx).getData_GubuSoft_Tree();
			  
%><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
	<head>
		<base href="<%=basePath%>">
		<title>菜单树 by webfxtree JSP</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<style type="text/css">
body {
	font-size: 12px;
	line-height: 24px;
	color: #000000;
	text-decoration: none;
	margin: 0px;
	padding: 0px;
	overflow: auto;
	SCROLLBAR-FACE-COLOR: #E1EDF3;
	SCROLLBAR-HIGHLIGHT-COLOR: #A5BFCC;
	SCROLLBAR-SHADOW-COLOR: #ffffff;
	SCROLLBAR-3DLIGHT-COLOR: #ffffff;
	SCROLLBAR-ARROW-COLOR: #769FB4;
	SCROLLBAR-TRACK-COLOR: #F4F4F4;
	SCROLLBAR-DARKSHADOW-COLOR: #A5BFCC;
	SCROLLBAR-BASE-COLOR: #22960D;
	background-color: #FAFAFA;
	background-repeat: no-repeat;
}
.maintable {
	border-right-width: 1px;
	border-right-style: none;
	border-right-color: #666666;
}
a:link {
	font-size: 12px;
	color: #000000;
	text-decoration: none;
}
a:visited {
	font-size: 12px;
	color: #000000;
	text-decoration: none;
	font-weight: normal;
}

a:hover {
	font-size: 12px;
	color: #ff6600;
	text-decoration: none;
	font-weight: normal;
}
a:active {
	font-size: 12px;
	color: #000000;
	text-decoration: none;
	font-weight: normal;
}
table {
	font-size: 12px;
	line-height: 22px;
	color: #000000;
}
.menutable {
	background-color: #C3E3FF;
	border: 0px solid #666666;
	padding: 0px;
}
.webfx-tree-container {
	margin: 0px;
	padding: 0px;
	font: icon;
	white-space: nowrap;
}
.webfx-tree-item {
	padding: 0px;
	margin: 0px;
	font: icon;
	color: black;
	white-space: nowrap;
}
.webfx-tree-item a,.webfx-tree-item a:active,.webfx-tree-item a:hover {
	margin-left: 3px;
	padding: 1px 2px 1px 2px;
}
.webfx-tree-item a {
	color: black;
	text-decoration: none;
}
.webfx-tree-item a:hover {
	color: blue;
	text-decoration: underline;
}
.webfx-tree-item a:active {
	background: #ff6600;
	color: #ffffff;
	text-decoration: none;
}
.webfx-tree-item img {
	vertical-align: middle;
	border: 0px;
}
.webfx-tree-icon {
	width: 16px;
	height: 24px;
}
.webfx-tree-item a.selected {
	color: selectedtext;
	background: selected;
}
.webfx-tree-item a.selected-inactive {
	color: #ffffff;
	background: #ff6600;
}
</style>
<SCRIPT type="text/javascript">
	var ICONPATH = 'images/webfx/';
</SCRIPT>
<script type="text/javascript" src="scripts/webfx/xtree.min.js"></script>
</head>
<body>
	<table width="100%" height="100%" border="0" cellpadding="0"
		cellspacing="0" class="menutable">
		<tr>
			<td valign="top">
				<table width="100%" border="0" cellpadding="0" cellspacing="6"
					class="maintable">
					<tr>
						<td><script language="javascript">
var jsonData = <%=jsonData %>;
if(jsonData.length==0){
	alert("没有找到根模块!");
}
var root = new WebFXTree("<B>"+jsonData[0].text+"</B>","javascript:void(0);","classic");
root.setBehavior('classic');

/** recursive create node. author: daizhongde dep.IT  **/
function gNode( o, pFolder )
{
	if(o.children===undefined){//create leaf node
		MENU_10001 = new WebFXTreeItem(o.text, o.url);
		pFolder.add(MENU_10001);
	}else{//create noleaf node
		var MENU_6400000 = new WebFXTreeItem(o.text);
		pFolder.add(MENU_6400000);
		for (var i in o.children){//recursive call itself
			gNode( o.children[i], MENU_6400000 );
		}
	}
}

var childData = jsonData[0].children;
for (var k in childData){
	gNode( childData[k], root );
}

document.write(root);</script></td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</body>
</html>