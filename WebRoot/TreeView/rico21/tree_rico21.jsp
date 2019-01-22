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
		<title>菜单树 by rico21 JSP</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<link href="/rico21/examples/client/css/demo.css" type="text/css" rel="stylesheet" />
<script src="/rico21/src/prototype.js" type="text/javascript"></script>
<script src="/rico21/src/rico.js" type="text/javascript"></script>
<script type='text/javascript'>
var jsonData = <%=jsonData %>;
Rico.loadModule('Tree');
var tree1;

Rico.onLoad( function() {
  // initialize tree
  var options={
    showCheckBox : false,
    showLines    : false,
    showPlusMinus: false,
    showFolders  : true
  }
  tree1=new Rico.TreeControl("tree1", null, options);
  tree1.setTreeDiv('tree1');
  //addNode: function(parentId, nodeId, nodeDesc, isContainer, isSelectable, leafIcon, isLast) {
  tree1.addNode(null, jsonData[0].id, jsonData[0].text, 1, 0 );

  /** recursive create node */
  function gNode( o, pFolder )
  {
    if( o.children === undefined ){//create leaf node
      tree1.addNode( pFolder, o.id, o.text, 0, o.url );
    }else{//create noleaf node
      tree1.addNode( pFolder, o.id, o.text, 1, 0);
      /* for ( var i in o.children ){
		 //recursive call itself
		 gNode( o.children[i], o.id );
	  } */
	  o.children.each(function(el){
		  gNode( el, o.id );
	  })
    }
  };

  var childData = jsonData[0].children;
  /*Prototype增加了一个新类Enumerable,
  	并使用该类的方法扩展了JavaScript的内置Array和Object对象
    Prototype(以及其他的JavaScript库)没有任何方式可以将
  	其添加到Array和Object对象中的方法标记为不可枚举。
  	下面的for循环除了遍历每个元素外，还会遍历所有的扩展方法。
   for (var k in childData){
		gNode( childData[k], jsonData[0].id );
  }; */
  childData.each(function(el){
	  gNode( el, jsonData[0].id );
  })
  
  tree1.open();
});

function TreeClick1(e) {
  var items=tree1.getCheckedItems();
  var msg=items.length==0 ? 'No items are checked' : items.join('\n');
  alert(msg);
}
</script>
<style type="text/css">
div.ricoTree {
  border:none;
}
.ricoTreeLevel0 {
  font-weight: bold;
  font-size: larger;
}
.ricoTreeBranch {
  margin-left: 10px;
}
</style>
</head>
<body>
<p><div id='tree1' class='ricoTree'></div>
</body>
</html>