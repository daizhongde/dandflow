<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>menu and table 几种实现</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  <body>
  <a href="Webmain/main_allmenu.html" target="_blank">欢迎界面</a>
  <a href="dev/tAuthorityModule/module_main.html" target="_blank">模块信息管理</a>
  <a href="dev/tAuthorityLevel/level_main.html" target="_blank">级别信息管理</a>
   <a href="scripts/jeasyui-extensions/editableSelect/usage.html" target="_blank">editableSelect</a>
   <a href="test/demo2.html" target="_blank">tab</a>
   <br/>
   
   <a href="migcommon/migJobIns/migJobIns_monitor.html" target="_blank">实例监控</a>
  
   
  <h2>菜单</h2>
  <table border="1">
  	<thead>
  		<tr>
  			<td>菜单树</td><td>菜单条</td><td>手风琴</td>
  		</tr>
  	</thead>
  	<tbody>
	  	<tr>
	  		<td>
	  			<ol>
				   	<li><a href="TreeView/gubusoft/tree_gubusoft.html" target="_blank">菜单树(gubusoft treeview) HTML</a>&nbsp;&nbsp;<a href="TreeView/gubusoft/tree_gubusoft.jsp">菜单树(gubusoft treeview) JSP</a></li>
				   	<li><a href="TreeView/fason/tree_fason.html" target="_blank">菜单树(fason treeview) HTML</a>&nbsp;&nbsp;<a href="TreeView/fason/tree_fason.jsp">菜单树(fason treeview) JSP</a></li>
				   	<li><a href="TreeView/webfx/tree_webfx.html" target="_blank">菜单树(webfxtree 集邮版) HTML</a>&nbsp;&nbsp;<a href="TreeView/webfx/tree_webfx.jsp">菜单树(webfxtree treeview) JSP</a>
				   		<br><a href="TreeView/xtree/tree_xtree.html" target="_blank">菜单树(webfxtree ) HTML</a>
				   	</li>
				</ol>
				<ol>
				   	<li>
				   		<a href="TreeView/jeasyui/tree_jeasyui.html" target="_blank">菜单树(jquery-easy-ui 1.3.5)</a>
				   		&nbsp;&nbsp;&nbsp;&nbsp;
				   		<a href="TreeView/jeasyui/tree_jeasyui_async.html" target="_blank">异步菜单树</a>
				   	
				   	</li>
			    	<li><a href="TreeView/rico21/tree_rico21.html" target="_blank">菜单树(rico21)</a>
						&nbsp;&nbsp;
						<a href="TreeView/rico21/tree_rico21.jsp">菜单树(rico21) JSP</a></li>
				   	<li><a href="TreeView/dojo/tree_TreeStoreModel.html" target="_blank">菜单树(dojo 1.9.1-TreeStoreModel)</a>
				   		&nbsp;&nbsp;
				   		<br>
					   	<a href="TreeView/dojo/tree_ForestStoreModel.html" target="_blank">菜单树(dojo 1.9.1-ForestStoreModel)</a>
					   	&nbsp;&nbsp;&nbsp;&nbsp;
					   	<br>
					   	<a href="TreeView/dojo/tree_ForestStoreModel2.html" target="_blank">菜单树(dojo 1.9.1-Custom_TreeNode)</a>
					   	&nbsp;&nbsp;&nbsp;&nbsp;
				<!-- 	   	<a href="TreeView/dojo/tree_lazyload.html" target="_blank">菜单树(dojo 1.9.1-LazyLoad)</a> -->
				   	</li>
				   	<li><a href="TreeView/yui3/tree_yui3.html" target="_blank">菜单树(yui3.10.3) gallery-sm-treeview</a>
					   	&nbsp;&nbsp;&nbsp;&nbsp;
					   	<a href="TreeView/yui2/tree_yui2_default.html" target="_blank">菜单树(yui2.9.0) normal</a>
					   	&nbsp;&nbsp;&nbsp;&nbsp;
					   	<br>
					   	<a href="TreeView/yui2/tree_yui2_customicons.html" target="_blank">菜单树(yui2.9.0) customicons</a>
				   		&nbsp;&nbsp;&nbsp;&nbsp;
					   	<a href="TreeView/yui2/tree_yui2_loader.html" target="_blank">菜单树(yui2.9.0) YUI Loader</a>
					   	&nbsp;&nbsp;&nbsp;&nbsp;
					   	<br>
					   	<a href="TreeView/yui2/tree_yui2_lazyload.html" target="_blank">菜单树(yui2.9.0) LazyLoad</a>
				   	</li>
				   	<li>
				   		<a href="TreeView/ext4/tree_ext4.html" target="_blank">菜单树(extjs 4)</a>
				   		&nbsp;&nbsp;&nbsp;&nbsp;
				   		<a href="TreeView/ext3/tree_ext3.html" target="_blank">菜单树(extjs 3)</a>
				   		&nbsp;&nbsp;&nbsp;&nbsp;
				   		<!-- 
				   		<a href="TreeView/ext3/tree_ext3_lazyload.html" target="_blank">LazyLoad</a> -->
				   	</li>
				</ol>
			</td>
			<td>
				<ol>
				   	<li><a href="MenuBar/jeasyui/menubar_jeasyui.html" target="_blank">菜单条(jquery-easy-ui 1.3.5)</a></li>
				    <li><a href="MenuBar/dojo/menubar_dojo.html" target="_blank">菜单条(dojo 1.9.1)</a></li>
				   	<li>
				   		<a href="MenuBar/yui3/menubar_yui3.html" target="_blank">菜单条(yui3.10.3)</a>
				   		&nbsp;&nbsp;
	   					<a href="MenuBar/yui2/menubar_yui2.html" target="_blank">菜单条(yui2.9.0) YUI</a>
				   	</li>
				   	<li>
				   		<a href="MenuBar/ext4/menubar_ext4.html" target="_blank">菜单条(extjs 4)</a>
				   		&nbsp;&nbsp;&nbsp;&nbsp;
   						<a href="MenuBar/ext3/menubar_ext3.html" target="_blank">菜单条(extjs 3)</a>
   					</li>
				</ol>
			</td>
			<td>
				<ol>
				   	<li><a href="Accordion/jeasyui/accordion_jeasyui.html" target="_blank">手风琴(jquery-easy-ui 1.3.4)</a></li>
				    <li><a href="Accordion/dojo/accordion_dojo.html" target="_blank">手风琴(dojo 1.9.1)</a></li>
				   		<li>
				   		<a href="Accordion/yui3/accordion_yui3.html" target="_blank">手风琴(yui3.10.3)</a>
				   		&nbsp;&nbsp;
	   					<a href="Accordion/yui2/accordion_yui2.html" target="_blank">手风琴(yui2.9.0) YUI</a>
				   	</li>
				   	<li>
				   		<a href="Accordion/ext4/accordion_ext4.html" target="_blank">手风琴(extjs 4)</a>
				   		&nbsp;&nbsp;&nbsp;&nbsp;
   						<a href="Accordion/ext3/accordion_ext3.html" target="_blank">手风琴(extjs 3)</a>
   					</li>
				   	<li>
				   		<a href="Accordion/jquerymetis/accordion_jquerymetis.html" target="_blank">bootstrap(accordion)</a>
				   		&nbsp;&nbsp;&nbsp;&nbsp;
   						<a href="Accordion/jquerymetis/accordion_jquerymetis.html" target="_blank">bootstrap(metis)</a>
   					</li>
				</ol>
			</td>
	  	</tr>
  	</tbody>
  </table>

   <h2>数据表格</h2>
   <ol>
   	<li><a href="DataTable/jeasyui/table_jeasyui.html" target="_blank">数据表格(jquery-easy-ui 1.3.4)</a>
   		&nbsp;&nbsp;&nbsp;&nbsp;
   		<a href="DataTable/jeasyui/table_jeasyui_withEXP.html" target="_blank">数据表格withEXP</a>
   		&nbsp;&nbsp;&nbsp;&nbsp;
   		<a href="DataTable/jeasyui/table_jeasyui_withIMP.html" target="_blank">数据表格withIMP</a>
   		&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="public/tbPayerCommiInfo/commiinfo_main.html" target="_blank">代付签约信息</a>
   	</li>
   	<li><a href="DataTable/rico21/table_rico21.html" target="_blank">数据表格(rico21)</a>&nbsp;&nbsp;
   	<li><a href="DataTable/dojo/table_dojo.html" target="_blank">数据表格(dojo 1.9.1)</a></li>
   	<li>
   		<a href="DataTable/yui3/table_yui3.html" target="_blank">数据表格(yui3.10.3)</a>
   		&nbsp;&nbsp;&nbsp;&nbsp;
	   	<a href="DataTable/yui2/table_yui2.html" target="_blank">数据表格(yui2.9.0) YUI</a>
   	</li>
   	<li>
   		<a href="DataTable/ext4/table_ext4.html" target="_blank">数据表格(extjs 4)</a>
   		&nbsp;&nbsp;&nbsp;&nbsp;
   		<a href="DataTable/ext3/table_ext3.html" target="_blank">数据表格(extjs 3)</a>
   	</li>
   </ol>

<br>
<table border="1">
	<thead>
		<tr>
			<td>dojo 1.9.1 demos</td>
			<td>yui 2 demos</td>
			<td>yui 3 demos</td>
			<td>ext 3 demos</td>
			<td>ext 4 demos</td>
			<td>jeasyui demos</td>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td>
				<ol>
				   	<li><a href="/dojo-1.9.1/dojox/grid/index.jsp">dojox.grid</a></li>
				   	<li><a href="/dojo-1.9.1/dojox/data/index.jsp">dojox.data</a></li>
				   	<li><a href="/dojo-1.9.1/demos/grid/demo.html" target="_blank">demos.grid.demo.html</a></li>
				   	<li><a href="/dojo-1.9.1/dijit/tests/index.jsp">dijit.tests</a></li>
				   	<li><a href="test/dojo/index.jsp">书上代码</a></li>
				   	
				</ol>
   			</td>
			<td>
				<ol>
					<li><a href="/yui_2.9.0/index.html" target="_blank">index.html</a></li>
				</ol>
   			</td>
			<td>
				<ol>
			   		<li><a href="/yui_3.10.3/index.jsp">index.jsp</a></li>
				</ol>
   			</td>
			<td>
			   	<ol>
			   		<li><a href="/ext-3.4.1/index.html" target="_blank">index.html</a></li>
			   	</ol>
   			</td>
			<td>
			   	<ol>
			   		<li><a href="/ext-4.2.1.883/index.html" target="_blank">index.html</a></li>
			   	</ol>
   			</td>
   			<td>
			   	<ol>
			   		<li><a href="/jquery-easyui-1.5" target="_blank">index.html</a></li>
			   	</ol>
   			</td>
		</tr>
	</tbody>
	</table>
  </body>
</html>
