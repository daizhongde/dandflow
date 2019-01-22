<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>打印缴款通知书接口测试</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
<!---     #############  need insert code      begin   ###############################              -->
<OBJECT ID="jatoolsPrinter"
	CLASSID="CLSID:B43D3361-D975-4BE2-87FE-057188254255"
	codebase="<%=basePath%>control/jatoolsPrinter.cab#version=1,2,0,5" height=0 width=0></OBJECT>
	
<script type="text/javascript" >
function doPrint(instanceid) {
	//document.getElementById("printNotice").disabled=true;
	//var instanceid = document.getElementById("instanceid").value;
	
	//打印文档对象
    var myreport = {
		print_settings  : {
	        pageWidth : 2100,
	        pageHeight : 2970,
			orientation : 2,    // 指定打打印方向为横向, 1/2 = 纵向/横向 
			//duplex : 1,          // 1: 不双面打印, 2:左侧装订, 3: 上方装订
	        marginIgnored : true   // 强制上、下、左、右边距为零
        },
		documents :  new Array('<%=basePath%>test/new.html'),
		copyrights : '杰创软件拥有版权 www.jatools.com'
	};
	jatoolsPrinter.print(myreport,false);    // 直接打印，不弹出打印机设置对话框
} 
</script>
<!---     #############  need insert code      end   ###############################              -->
  </head>
  
  <body>
  	<input id="instanceid" type="hidden" name="instanceid" value="1820110820">
    <input id="printNotice" type="button" name="printNotice" value="打印缴款通知单" onclick="doPrint('5120110823')">
  </body>
</html>
