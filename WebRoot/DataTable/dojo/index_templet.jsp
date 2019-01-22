<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>dojo1.9.1 example</title>
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
   <h1>dijit.tree.**.StoreModel</h1>
   <table>
   <tr>
   <td>
   <ol>
   	<li><a href="test_backwards_compatibility.html">test_backwards_compatibilit</a></li>
   	<li><a href="test">test</a></li>
   	<li><a href="test">test</a></li>
   	

  
   </ol>
   <!-- 
   	<ol>
   	<li><a href="test">test</a></li>
   	<li><a href="test">test</a></li>
   	<li><a href="test">test</a></li>
   	<li><a href="test">test</a></li>
   	<li><a href="test">test</a></li>
   	<li><a href="test">test</a></li>
   	<li><a href="test">test</a></li>
   	<li><a href="test">test</a></li>
   	<li><a href="test">test</a></li>
   	<li><a href="test">test</a></li>
   	</ol>
   	 -->
   </td>
   <td>
     <table>
       <tr><td>bidi</td></tr>
       <tr><td>
      	 <ol>
          	<li><a href="test">test</a></li>
		</ol>
       </td></tr>
       <tr><td>enhanced</td></tr>
       <tr><td>
       	 <ol>
		   	<li><a href="test">test</a></li>
		</ol>
       </td></tr>
     </table>
   </td>
</tr>
	</table>
</html>
