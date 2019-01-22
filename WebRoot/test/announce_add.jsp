<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!--
Copyright (c) 2003-2011, CKSource - Frederico Knabben. All rights reserved.
For licensing, see LICENSE.html or http://ckeditor.com/license

taglib uri="http://ckeditor.com" prefix="ckeditor"
-->
<%@ taglib uri="/struts-tags" prefix="struts"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page	import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page language="Java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

//ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
// ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(session.getServletContext());
// String CAcuser = SecuritySessionDaoImpl.getFromApplicationContext(ctx).getUsername();
// String CAcip = request.getRemoteAddr();

//设置标志变量SubmitFlag值
session.putValue("SubmitFlag","announce_add.jsp"); 

%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title><struts:property value="title" /></title>
		<meta content="text/html; charset=GBK" http-equiv="content-type" />
		<link type="text/css" rel="stylesheet" href="<%=basePath%>scripts/ckeditor/_samples/sample.css" />
	<link rel="stylesheet" href="<%=basePath%>scripts/jqueryui/themes/base/jquery.ui.all.css"/> 
	<style type="text/css">
	body{
	    margin: 0;
	    padding: 0;
	}
	div.demo {
		padding: 0px;
		font-family: "Trebuchet MS", "Arial", "Helvetica", "Verdana",
			"sans-serif";
	}
	 
	div.demo {
		clear: left;
		font-size: 38px;
		font-weight: normal;
		padding: 0 0 0 0;
		margin: 0;
	}
	
	p {
		padding: 0px;0px;0px;0px;
	}
	
	</style>
		<script type="text/javascript" src="<%=basePath%>scripts/jquery-1.5.2.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>scripts/jqueryui/ui/minified/jquery.ui.core.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>scripts/jqueryui/ui/minified/jquery.ui.widget.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>scripts/jqueryui/ui/minified/jquery.ui.button.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>scripts/dojo/dojo.xd.js"></script>
		<!-- -->
		<script type="text/javascript" src="<%=basePath%>scripts/ckeditor/ckeditor.js"></script> 
		<script type="text/javascript" src="<%=basePath%>scripts/ckfinder/ckfinder.js"></script>
		<script type="text/javascript" src="<%=basePath%>scripts/util/dateformator.js"></script>
		<script type="text/javascript" src="<%=basePath%>dwr/interface/announceService.js"></script>
		<script type="text/javascript" src="<%=basePath%>dwr/engine.js"></script>
		<script type="text/javascript" src="<%=basePath%>dwr/util.js"></script>
		<script type="text/javascript">

	</script>
	</head>
	<body>
		<!-- This <div> holds alert messages to be display in the sample page. -->
		<div id="alerts">
			<noscript>
				<p>
					<strong>CKEditor requires JavaScript to run</strong>. In a browser
					with no JavaScript support, like yours, you should still see the
					contents (HTML data) and you should be able to edit it normally,
					without a rich editor interface.
				</p>
			</noscript>
		</div>
		<fieldset>
			<legend>
				<struts:property value="title" />
			</legend>
			<form id="addform" name="addform" action="addDefaultannounce.action" method="post">
				<struts:hidden name="title" />
				<struts:hidden name="info" />
				<input type="hidden" id="announce.NAstates" name="announce.NAstates" value="2"></input>
				<struts:hidden name="announce.NAid" />
				<p>
				标题:
				<struts:textfield id="announce.CAtitle" name="announce.CAtitle"
					label="标题" required="true" maxlength="64" size="64" />
				</p>
				类别:
				<struts:select id="announce.CAclass" name="announce.CAclass"
					list="settingList" listKey="id" listValue="paramValue" label="类别"
					required="true"></struts:select>
				<p>
					<label for="editor1">内 容:</label>
					<textarea cols="80" id="editor1" name="announce.CAcontent" rows="10"></textarea>
<script type="text/javascript">

// This is a check for the CKEditor class. If not defined, the paths must be checked.
if ( typeof CKEDITOR == 'undefined' )
{
	document.write(
		'<strong><span style="color: #ff0000">Error</span>: CKEditor not found</strong>.' +
		'This sample assumes that CKEditor (not included with CKFinder) is installed in' +
		'the "/ckeditor/" path. If you have it installed in a different place, just edit' +
		'this file, changing the wrong paths in the &lt;head&gt; (line 5) and the "BasePath"' +
		'value (line 32).' ) ;
}
else
{
	var editor = CKEDITOR.replace( 'editor1', {
        filebrowserBrowseUrl : '<%=basePath%>scripts/ckfinder/ckfinder.html',
        filebrowserImageBrowseUrl : '<%=basePath%>scripts/ckfinder/ckfinder.html?Type=Images',
        filebrowserFlashBrowseUrl : '<%=basePath%>scripts/ckfinder/ckfinder.html?Type=Flash',
        filebrowserUploadUrl : '<%=basePath%>scripts/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Files',
        filebrowserImageUploadUrl : '<%=basePath%>scripts/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Images',
        //filebrowserImageUploadUrl : 'upload/upload.action?command=QuickUpload&type=Images',
        filebrowserFlashUploadUrl : '<%=basePath%>scripts/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Flash'
        //filebrowserImageWindowWidth : '640',
        // filebrowserImageWindowHeight : '480'
                
    });
	CKEDITOR.config.height = 350;
    
	//editor.setData( '<p>Just click the <b>Image</b> or <b>Link</b> button, and then <b>&quot;Browse Server&quot;</b>.</p>' );

	// Just call CKFinder.SetupCKEditor and pass the CKEditor instance as the first argument.
	// The second parameter (optional), is the path for the CKFinder installation (default = "/ckfinder/").
	CKFinder.setupCKEditor( editor, '../scripts/ckfinder' ) ;
}
</script>
				</p>
				<div id="response"></div>
					<center>
						<div class="demo">
							<input type="button" value="发  布"
								style="font-size: 40px; color: #000000;"
								onclick="publishA()" />
							<input id="savedraft" type="button" value="保存草稿"
								style="font-size: 40px; color: #000000;"/>
							<input type="button" value="取  消"
								style="font-size: 40px; color: #000000"
								onclick="javascript:history.back();" />
						</div>
					</center>
			</form>
		</fieldset>
		<!-- 
		<ckeditor:replace replace="announce.CAcontent" basePath="../scripts/ckeditor/" />
		-->
	</body>
</html>
