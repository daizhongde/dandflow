<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page	import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page	import="person.daizhongde.authority.spring.service.impl.TAuthorityModuleServiceImpl"%>
<%@page import="person.daizhongde.authority.hibernate.pojo.TAuthorityUser"%>
<%@page import="person.daizhongde.authority.constant.SessionConstants"%><%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	
	TAuthorityUser user = (TAuthorityUser)session.getAttribute( SessionConstants.LOGIN_USER );
	
	ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(session.getServletContext());
	String jsonData = TAuthorityModuleServiceImpl.getFromApplicationContext(ctx).getData_GubuSoft_Tree( user.getNUid(), user.getCUlogname() );
%><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
	<head>
		<base href="<%=basePath%>">
		<title>left menu</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<style type="text/css">
BODY {background-color: #C3E3FF; }
TD {
	font-size: 10pt;
	font-family: verdana, helvetica;
	text-decoration: none;
	white-space: nowrap;
}
A {
	text-decoration: none;
	color: black;
}
.specialClass {
	font-family: garamond;
	font-size: 12pt;
	color: green;
	font-weight: bold;
	text-decoration: underline
}
</style>
<SCRIPT type="text/javascript" src="scripts/gubusoft/ua+ftiens4.min.js"></SCRIPT>
<script type="text/javascript">
var jsonData = <%=jsonData %>;
BUILDALL=0;
HIGHLIGHT=1;
USETEXTLINKS = 1;
PRESERVESTATE = 1;//Store the state of the tree in cookies, and use that state on next visit
//STARTALLOPEN = 0;//Do not use STARTALLOPEN=1 together with PRESERVESTATE=1. These settings contradict each another. 
ICONPATH = 'images/gubusoft/';
if(jsonData.length==0){
	alert("System cant't find root module!");
}
foldersTree = gFld("<B>"+jsonData[0].text+"</B>", "");
foldersTree.xID = "Frameset";
//foldersTree.iconSrc = ICONPATH + "web.gif";
//recursive create node
function gNode( o, pFolder )
{
   if( o.children === undefined ){//create leaf node
      aux2 = insDoc(pFolder, gLnk(o.target, o.text, '<%=basePath%>'+o.url));
      aux2.xID = o.id;
   }else{//create noleaf node
      var aux1 = insFld(pFolder, gFld(o.text, "javascript:parent.op()"));
      aux1.xID = o.id;
      for ( var i in o.children ){
		 //recursive call itself
		 gNode( o.children[i], aux1 );
	  }
   }
}

var childData = jsonData[0].children;
for (var k in childData){
	gNode( childData[k], foldersTree );
}
</script>
</head>
<BODY marginheight="8">
  <DIV style="position:absolute; top:0; left:0;"><TABLE border=0 style="display:none"><TR><TD><FONT size=-2><A style="font-size:7pt;text-decoration:none;color:silver" href="http://www.treemenu.net/" target=_blank>Javascript Tree Menu</A></FONT></TD></TR></TABLE></DIV>
  <SCRIPT>initializeDocument()</SCRIPT>
  <NOSCRIPT>
   A tree for site navigation will open here if you enable JavaScript in your browser.
  </NOSCRIPT>
</BODY>
</HTML>