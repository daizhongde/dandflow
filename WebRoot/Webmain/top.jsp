<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="person.daizhongde.authority.hibernate.pojo.TAuthorityUser" %>
<%@page import="person.daizhongde.authority.constant.SessionConstants"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

TAuthorityUser user=(TAuthorityUser)request.getSession().getAttribute( SessionConstants.LOGIN_USER );

%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<base href="<%=basePath%>">
  	
    <title>顶部窗口</title>
    <meta name="keywords" content="keyword1,keyword2,keyword3">
    <meta name="description" content="this is my page">
    <meta name="content-type" content="text/html; charset=UTF-8">
    <link href="<%=basePath%>css/top.css" rel="stylesheet" type="text/css">

<style type="text/css">
<!--
#sysname{
	font-family: "微软雅黑", Arial;
	font-size:24px;
	color: #FFFFFF;
	padding:0px 0px 0px 0px;
}
#sysdiv{
	padding:10px 0px 0px 0px;
	width: 600px;
	height: 40px
}
	-->
</style>
<script type="text/javascript">
	function ShowMenu()
	{
		parent.Frameleft.cols="260,*";
		hidediv.style.display="block";
	}
	
	function HideMenu()
	{
		parent.Frameleft.cols="0,*";
		hidediv.style.display="none";
	}
	
	function help(){
	    var dLeft = (window.screen.AvailWidth-800-10)+"px";	    
	    var dTop  = (window.screen.availHeight-600)+"px";
	    var r_url = window.parent.document.getElementById('basefrm').src;
// 		alert(r_url);
	    var params = "dialogLeft="+dLeft+";dialogTop="+dTop+";dialogWidth=800px;dialogHeight=600px;center=no;scroll=yes;resizable=yes;help=no;status=no;";
	    
	    try{
	    	window.showModelessDialog( "<%=basePath%>help/helpIframe.jsp?r_url=" + r_url, window, params );
// 	    	window.showModelessDialog( "<%=basePath%>help/index.htm?r_url=" + r_url, window, params );
	    }catch(ex){
	    	alert("View help contents need IE browser!");
// 	    	alert("catch error!ex:"+ex);
	    }
	}
	
	/** Refresh left frame, When change menu style  **/
	function onChangeMenuStyle( value ){
		if( value.indexOf('<%=basePath%>MenuBar/') ==-1 ){
			window.parent.document.getElementById('menufrm').src=value;
		}else{
			//hide left frame
			parent.Frameleft.cols="0,*";
			hidediv.style.display="none";
			//change right frame URL
			window.parent.document.getElementById('basefrm').src=value;
		}
		
	};
</script>
</head>
<body style="overflow:hidden">

<table width="100%" height="59" border="0" cellpadding="0" cellspacing="0" >
  <tr>
    <td valign="top">
	   <table width="100%" border="0" cellspacing="0" cellpadding="0">
	     <tr>
	       <td background="<%=basePath%>images/top/top_bg01.gif"><div id="sysdiv"><span id="sysname">AsiaInfo Data Migration Tool V2.0</span></div></td>
	       <td width="82" align="right"><img src="<%=basePath%>images/top/top_img01.gif" width="82" height="50"></td>
	     </tr>
	   </table>
       <table width="100%" height="28" border="0" cellpadding="0" cellspacing="0" background="<%=basePath%>images/top/top_bg02.gif">
         <tr>
           <td width="122">
             <table border="0" cellpadding="0" cellspacing="2">
                <tr>
                  <td width="84" align="right"><img src="<%=basePath%>images/top/top_xtcds.gif" width="80" height="24" onClick="ShowMenu()" style="cursor:pointer" onMouseOver="this.src='<%=basePath%>images/top/top_xtcdso.gif'" onMouseDown="this.src='<%=basePath%>images/top/top_xtcdsd.gif'" onMouseUp="this.src='<%=basePath%>images/top/top_xtcdso.gif'" onMouseOut="this.src='<%=basePath%>images/top/top_xtcds.gif'"></td>
                  <td id="hidediv" style="display:block">
                  	<img src="<%=basePath%>images/top/top_xtcds_hid.gif" width="28" height="24" 
	                  	onClick="HideMenu()" style="cursor:pointer" 
	                  	onMouseOver="this.src='<%=basePath%>images/top/top_xtcds_hido.gif'" 
	                  	onMouseDown="this.src='<%=basePath%>images/top/top_xtcds_hidd.gif'" 
	                  	onMouseUp="this.src='<%=basePath%>images/top/top_xtcds_hido.gif'" 
	                  	onMouseOut="this.src='<%=basePath%>images/top/top_xtcds_hid.gif'">
                  </td>
                  <td>
                  </td>
                </tr>
            </table>
           </td>
           <td align="center">Welcome! <%out.print(user.getTAuthorityInst().getCIcode()+"-"+user.getTAuthorityInst().getCIname()+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+user.getCUlogname()+"-"+user.getCUname() ); %></td>
        </tr>
      </table>
    </td>
    <td width="400"  valign="top">
      <table width="100%" height="28" border="0" cellpadding="0" cellspacing="0" background="<%=basePath%>images/top/top_bg04.gif">
	      <tr>
	        <td align="right">
		        <table width="400" border="0" cellpadding="0" cellspacing="0">
		          <tr>
		            <td width="90">
<!-- 		            	<img src="<%=basePath%>images/top/lock.gif" width="24" height="24" align="absmiddle"><a href="javascript:showModalDialog('<%=basePath%>common/tAuthorityUser/tAuthorityUser_PasswordModify.html?id='+(new Date()).getTime(),[],'dialogHeight:300px;dialogWidth:	500px;center:Yes;help:No;resizable:yes;status:No');" target="basefrm" id="one1">密码维护</a> -->
		            </td>
		            <td width="90"><img src="<%=basePath%>images/top/top_icon02.gif" width="24" height="24" align="absmiddle"><a href="#" onclick="help();return false;" id="one">Help Contents</a></td>
<!-- 		            logoutServlet logout.action-->
		            <td width="90"><img src="<%=basePath%>images/top/top_icon03.gif" width="24" height="24" align="absmiddle"><a href="<%=basePath%>logoutServlet" target="_parent" id="one">Logout</a></td>
		          </tr>
		        </table>
	        </td>
	      </tr>
      </table>
      <table width="100%" height="50" border="0" cellpadding="0" cellspacing="0" background="<%=basePath%>images/top/top_bg03.gif">
         <tr>
           <td>
             <table width="256" border="0" cellpadding="0" cellspacing="4">
	           <tr>
	             <td width="80"><img src="<%=basePath%>images/top/top_menu01.gif" width="74" height="38" onMouseOver="this.src='<%=basePath%>images/top/top_menu01o.gif'" onMouseDown="this.src='<%=basePath%>images/top/top_menu01d.gif'" onMouseUp="this.src='<%=basePath%>images/top/top_menu01u.gif'" onMouseOut="this.src='<%=basePath%>images/top/top_menu01.gif'" onClick="javascript:history.go(-1)" style="cursor:pointer"></td>
	             <td width="80" align="center"><img src="<%=basePath%>images/top/top_menu02.gif" width="74" height="38" onMouseOver="this.src='<%=basePath%>images/top/top_menu02o.gif'" onMouseDown="this.src='<%=basePath%>images/top/top_menu02d.gif'" onMouseUp="this.src='<%=basePath%>images/top/top_menu02u.gif'" onMouseOut="this.src='<%=basePath%>images/top/top_menu02.gif'" onClick="javascript:history.go(1)" style="cursor:pointer"></td>
	             <td width="80"><img src="<%=basePath%>images/top/top_menu03.gif" width="74" height="38" onMouseOver="this.src='<%=basePath%>images/top/top_menu03o.gif'" onMouseDown="this.src='<%=basePath%>images/top/top_menu03d.gif'" onMouseUp="this.src='<%=basePath%>images/top/top_menu03u.gif'" onMouseOut="this.src='<%=basePath%>images/top/top_menu03.gif'" onClick="javascript:parent.basefrm.location.reload()" style="cursor:pointer"></td>
	             <td width="80"><img src="<%=basePath%>images/top/top_menu04.gif" width="74" height="38" onMouseOver="this.src='<%=basePath%>images/top/top_menu04o.gif'" onMouseDown="this.src='<%=basePath%>images/top/top_menu04d.gif'" onMouseUp="this.src='<%=basePath%>images/top/top_menu04u.gif'" onMouseOut="this.src='<%=basePath%>images/top/top_menu04.gif'" style="cursor:pointer" onClick="javascript:parent.basefrm.location.href='<%=basePath%>/Webmain/welcome.html'"></td>
	           </tr>
	         </table>
           </td>
         </tr>
      </table>
    </td>
  </tr>
</table>
</body>
</html>
