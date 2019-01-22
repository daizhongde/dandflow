<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>dojo1.9.1 example - dijit.test</title>
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
   <h1>dijit.test</h1>
   <table>
   <tr>
   <td>
   <ol>
   	<li><a href="_AttachMixin.html">_AttachMixin.html</a></li>
   	<li><a href="_Container.html">_Container.html</a></li>
   	<li><a href="_HasDropDown.html">_HasDropDown.html</a></li>
   	<li><a href="_KeyNavContainer.html">_KeyNavContainer.html</a></li>
   	<li><a href="_TemplatedMixin.html">_TemplatedMixin.html</a></li>
   	<li><a href="_Templated-widgetsInTemplate1.x.html">_Templated-widgetsInTemplate1.x.html</a></li>
   	<li><a href="_TimePicker.html">_TimePicker.html</a></li>
   	<li><a href="_Widget-attr.html">_Widget-attr.html</a></li>
   	<li><a href="_Widget-connect-performance.html">_Widget-connect-performance.html</a></li>
   	<li><a href="_Widget-deferredConnect.html">_Widget-deferredConnect.html</a></li>
   	<li><a href="_Widget-lifecycle.html">_Widget-lifecycle.html</a></li>
   	<li><a href="_Widget-on.html">_Widget-on.html</a></li>
   	<li><a href="_Widget-ondijitclick.html">_Widget-ondijitclick.html</a></li>
   	<li><a href="_Widget-placeAt.html">_Widget-placeAt.html</a></li>
   	<li><a href="_WidgetsInTemplateMixin.html">_WidgetsInTemplateMixin.html</a></li>
   	<li><a href="_Widget-subscribe.html">_Widget-subscribe.html</a></li>
   	<li><a href="a11y.html">a11y.html</a></li>
   	<li><a href="Bidi.html">Bidi.html</a></li>
   	<li><a href="CalendarLite.html">CalendarLite.html</a></li>
   	<li><a href="Destroyable.html">Destroyable.html</a></li>
   	<li><a href="Dialog.html">Dialog.html</a></li>
   	<li><a href="Fieldset.html">Fieldset.html</a></li>
   	<li><a href="focus.html">focus.html</a></li>
   	<li><a href="focus-framedojo.html">focus-framedojo.html</a></li>
   	<li><a href="focus-framedojo-child.html">focus-framedojo-child.html</a></li>
   	<li><a href="formAction.html">formAction.html</a></li>
   	<li><a href="loose.html">loose.html</a></li>
   	<li><a href="loremIpsum.html">loremIpsum.html</a></li>
   	<li><a href="Menu.html">Menu.html</a></li>
   	<li><a href="mobile.html">mobile.html</a></li>
   	<li><a href="NodeList-instantiate.html">NodeList-instantiate.html</a></li>
   	<li><a href="place.html">place.html</a></li>
   	<li><a href="place-clip.html">place-clip.html</a></li>
   	<li><a href="place-margin.html">place-margin.html</a></li>
   	<li><a href="popup.html">popup.html</a></li>
   	<li><a href="ProgressBar.html">ProgressBar.html</a></li>
   	<li><a href="quirks.html">quirks.html</a></li>
   	<li><a href="registry.html">registry.html</a></li>
   	<li><a href="runTests.html">runTests.html</a></li>
   	<li><a href="strict.html">strict.html</a></li>
   	<li><a href="test_bgIframe.html">test_bgIframe.html</a></li>
   	<li><a href="test_Calendar.html">test_Calendar.html</a></li>
   	<li><a href="test_ColorPalette.html">test_ColorPalette.html</a></li>
   	<li><a href="test_ColorPalette_quirks.html">test_ColorPalette_quirks.html</a></li>
   	<li><a href="test_Declaration.html">test_Declaration.html</a></li>
   	<li><a href="test_Declaration_1.x.html">test_Declaration_1.x.html</a></li>
   	<li><a href="test_Dialog.html">test_Dialog.html</a></li>
   	<li><a href="test_Dialog_focusDestroy.html">test_Dialog_focusDestroy.html</a></li>
   	<li><a href="test_InlineEditBox.html">test_InlineEditBox.html</a></li>
   	<li><a href="test_Menu.html">test_Menu.html</a></li>
   	<li><a href="test_Menu_iframe.html">test_Menu_iframe.html</a></li>
   	<li><a href="test_TitlePane.html">test_TitlePane.html</a></li>
   	<li><a href="test_Toolbar.html">test_Toolbar.html</a></li>
   	<li><a href="test_Tooltip.html">test_Tooltip.html</a></li>
   	<li><a href="test_TooltipDialog.html">test_TooltipDialog.html</a></li>
   	<li><a href="test_typematic.html">test_typematic.html</a></li>
   	<li><a href="test_UIWindowIssue_child.html">test_UIWindowIssue_child.html</a></li>
   	<li><a href="test_UIWindowIssue_main.html">test_UIWindowIssue_main.html</a></li>
   	<li><a href="TooltipDialog.html">TooltipDialog.html</a></li>
   	<li><a href="Tooltip-placement.html">Tooltip-placement.html</a></li>
   	
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
   </ol>
   </td>
     <td>
     <table>
       <tr><td>dijit.tests.tree</td></tr>
       <tr><td>
      	 <ol>
          	<li><a href="tree/index.jsp">tree</a></li>
         </ol>
       </td></tr>

     </table>
   </td>
</tr>
	</table>
</html>
