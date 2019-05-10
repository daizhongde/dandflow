<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="person.daizhongde.authority.hibernate.pojo.TAuthorityUser"%>
<%@ page import="person.daizhongde.authority.hibernate.pojo.TAuthorityRole"%>
<%@page import="person.daizhongde.authority.constant.SessionConstants"%>
<%@page import="person.daizhongde.authority.spring.service.impl.TAuthorityRoleServiceImpl"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	TAuthorityUser user = (TAuthorityUser) request.getSession()
			.getAttribute(SessionConstants.LOGIN_USER);

	if(null==user){
		response.sendRedirect(basePath + SessionConstants.LOGIN_PAGE);
		return;
	}
	
	ApplicationContext ctx = WebApplicationContextUtils
			.getWebApplicationContext(session.getServletContext());
	List<TAuthorityRole> roles = TAuthorityRoleServiceImpl
			.getFromApplicationContext(ctx).findRoleByUserId(
					user.getNUid());
	String role = "";
	for (int i = 0, j = roles.size(); i < j; i++) {
		if (i == 0) {
			role += roles.get(i).getCRname();
		} else {
			role += "+" + roles.get(i).getCRname();
		}
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Welcome to AIDM</title>
<link href="css/frame-css/frame.css" rel="stylesheet" type="text/css" />
<link rel="shortcut icon" href="images/ico/asiainfo.ico" type="image/x-icon" />
<!-- <link rel="stylesheet" type="text/css" href="scripts/jquery-easyui/1.4.1/themes/default/easyui.css"/> -->
<!-- <link rel="stylesheet" type="text/css" href="scripts/jquery-easyui/1.4.1/themes/icon.css"/> -->
<style type="text/css">
.icon-back {
    background: transparent url("images/virtue/back.png") no-repeat scroll center center;
}
.icon-forward {
    background: transparent url("images/virtue/forward.png") no-repeat scroll center center;
}
.icon-reload {
    background: transparent url("scripts/jquery-easyui/1.4.1/themes/icons/reload.png") no-repeat scroll center center;
}
.icon-home {
    background: transparent url("images/virtue/home.png") no-repeat scroll center center;
}

.className{  
    -webkit-animation: twinkling 1s infinite ease-in-out  
}  
.animated{  
    -webkit-animation-duration: 1s;  
    animation-duration: 1s;  
    -webkit-animation-fill-mode: both;  
    animation-fill-mode: both  
}  
@-webkit-keyframes twinkling{  
    0%{  
        opacity: 0.5;  
    }  
    100%{  
        opacity: 1;  
    }  
}  
@keyframes twinkling{  
    0%{  
        opacity: 0.5;  
    }  
    100%{  
        opacity: 1;  
    }  
}  
</style>
<script type="text/javascript" src="scripts/jquery.min.js"></script>
<script type="text/javascript" src="scripts/json/jquery.json-2.2.min.js"></script>

<script type="text/javascript" src="scripts/virtue/virtue.js"></script>
<script type="text/javascript" src="tree/tAuthorityModuleTREEquery_GubuSoft_TreeJS_User.action"></script>
<script type="text/javascript" src="scripts/utils/stringUtil.js"></script>
    
<script type="text/javascript">

var query = VIRTUE.query;

function help(){
    var dLeft = (window.screen.AvailWidth-800-10)+"px";	    
    var dTop  = (window.screen.availHeight-600)+"px";
    var r_url = document.getElementById('basefrm').src;
    var params = "dialogLeft="+dLeft+";dialogTop="+dTop+";dialogWidth=800px;dialogHeight=600px;center=no;scroll=yes;resizable=yes;help=no;status=no;";
    
    try{
    	window.showModelessDialog( "<%=basePath%>webhelp/helpIframe.jsp?r_url=" + r_url, window, params );
    }catch(ex){
//     	alert("View help contents need IE browser!");
    	window.open("<%=basePath%>webhelp/helpIframe.jsp?r_url=" + r_url, "help", "toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=yes, resizable=no, copyhistory=no, width=800, height=600,left="+dLeft+",top="+dTop, true);
    }
};

function f1KeyDown(event)
{
// 	console.log(event.keyCode);
// 	return false;
    if(event.keyCode==112)
    {
    	help();
    	event.stopPropagation();
	}
};
window.onkeydown = f1KeyDown;

frmReload = function(){
    var url = $('.m_mainFrame').attr('src');
    url = convert2newurl(url);
    
    $('.m_mainFrame').attr('src',url);
};

</script>
</head>
<body>
	<!-- 顶部 开始 -->
	<div class="m_header">
		<div class="logo logo-en"></div>
		<div class="fn">
			<div class="user">
<!-- 				<a href="javascript:void(0)" class="icon-reload" onclick="javascript:$('#LOGdlg1').panel('refresh')">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a> -->
				<span class="name"><%=user.getCUlogname()%>-<%=user.getCUname()%></span>
				<span class="role">[<%=role%>]</span>
			</div>
			<a href="<%=basePath%>/sfdsf" target='basefrm' id="notice" class="notice className">消息(2)</a>
			<a href="<%=basePath%>logoutServlet" class="exit">Logout</a>
			<div class="more">
				<a href="javascript:void(0);" class="spot"
					onmouseover="document.getElementById('morefnoption').style.display = '';"
					onmouseout="document.getElementById('morefnoption').style.display = 'none';">More</a>
				<div class="option" style="display:none;" id="morefnoption"
					onmouseover="document.getElementById('morefnoption').style.display = '';"
					onmouseout="document.getElementById('morefnoption').style.display = 'none';">
					<!-- 更多功能（下拉） -->
					<div class="top">
						<span></span>
					</div>
					<div class="list">
						<a href="#nogo" onclick="javascript:help();return false;">Help</a>
						<a href="#nogo" onclick="javascript:return false;">Site map</a>
						<a href="#nogo" onclick="javascript:return false;">Repository</a>
						<a href="ckfinder/standalone.html" target="_blank" onclick="javascript:return true;">File View</a>
						
						<a href="#" target="_blank" onclick="javascript:alert('Expire date <%=person.daizhongde.virtue.constant.Lic.getYear() %>-10-28  德软集团 QQ：413881461');return false;">About</a>
					</div>
					<div class="bottom">
						<span></span>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 顶部 结束 -->

<!-- 导航 开始 -->
<div id="m_nav" class="m_nav">
	<div id="nav" class="nav">
<!-- 		<a href="#nogo" class="on" onmouseover="showsubmenu('menu1');setsubmenu(this,'menu1');" onmouseout="hidesubmenu('menu1')">目录管理</a> -->
	</div>
</div>

<script type="text/javascript">
function changePage(url){
	url = convert2newurl(url);
	document.getElementById('basefrm').src = url;
	
};

jQuery(document).ready(function(){
	if(jsonData.length==0){
		alert("没有找到根模块!");
	}

	/** recursive create node. author: daizhongde dep.IT  **/
	function gNode( o, pFolder, k )
	{
		if(o.children===undefined){//create leaf node
			pFolder.append("<a href='"+o.url+"' target='basefrm' onclick='javascript:changePage(\""+o.url+"\");return false;'>"+o.text+"</a>");
		
// 			MENU_10001 = new WebFXTreeItem(o.text, "../../"+o.url);
// 			MENU_10001.target="basefrm";
// 			pFolder.add(MENU_10001);
		}else{//create noleaf node
			pFolder.append("<a href='#nogo' class='on' onclick='javascript:return false;' onmouseover='showsubmenu(\"menu"+k+"\");setsubmenu(this,\"menu"+k+"\");' onmouseout='hidesubmenu(\"menu"+k+"\")'>" + o.text + "</a>");
			$("#m_nav").append("<div class='mc_subnav' id='menu"+k+"' onmouseover='showsubmenu(\"menu"+k+"\");' onmouseout='hidesubmenu(\"menu"+k+"\")' style='display:none;'>"
				+"<div class='list'><div id='col"+k+"' class='col'></div></div><div class='shadow'></div></div>");
// 			var MENU_6400000 = new WebFXTreeItem(o.text);
// 			pFolder.add(MENU_6400000);
			for (var i in o.children){//recursive call itself
				gNode( o.children[i], $("#col"+k), k );
			}
		}
	}

	var childData = jsonData[0].children;
	for (var k in childData){
		//
		gNode( childData[k], $("#nav"), k );
	}
	//有消息就提示
	RoundMSG();
});

//var twinkleFlag = 2;//1闪,2不闪

function RoundMSG()
{
	query.init();	
	jQuery.post( "<%=basePath%>query/tChatMsgJEasyUIQUERYdfindTotal_Offline.action",
		{ jdata: encodeURI($.toJSON( query.jdata )) },
	    function(oResponse, status){
		    oResponse = eval("(" + oResponse + ")");//如 果不是struts2的json插件返回就需要加上
// 		    alert(oResponse);
		    if( oResponse>0 ){//有离线消息
		    	var imgid=document.getElementById("imgid");
		  		imgid.src="images/vq/bull-2-min.gif";
		    }
	});
};
</script>

<!-- 导航 结束 -->
<script>
	function setsubmenu(o,id) {
		var submenu = document.getElementById(id);
		submenu.style.left = o.offsetLeft - 16 + "px";
	}
	function showsubmenu(id) {
		var submenu = document.getElementById(id);
		submenu.style.display = "";
	}
	function hidesubmenu(id) {
		var submenu = document.getElementById(id);
		submenu.style.display = "none";
	}
</script>

	<div class="m_main">
		<iframe id="basefrm" name="basefrm" class="m_mainFrame" frameborder="0" src="welcome.html"></iframe>
	</div>

	<div class="m_copyright">
		<div style="float:left;">
<!-- 		basefrm.location.reload() -->
			<a href="javascript:void(0)" class="icon-back" onclick="javascript:basefrm.history.go(-1)">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
			<a href="javascript:void(0)" class="icon-forward" onclick="javascript:basefrm.history.go(1)">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
			<a href="javascript:void(0)" class="icon-reload" onclick="javascript:frmReload()">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
			<a href="javascript:void(0)" class="icon-home" onclick="javascript:basefrm.location.href='welcome.html'">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
		</div>
	Copyright&copy;2015 Dand.All Rights
		Reserved
		<div style="float:right;visibility: visible;margin:0;padding:0;">
			<img id="imgid" src="images/vq/bull-1-min.gif" alt="VQ:Migration Group(1)"
			 style="padding:1px 18px 0px 1px" height="24px" width="24px" align="right"
			 onclick="javascript:this.src='images/vq/bull-1-min.gif';window.open('chat/chat.jsp', 'chat windows', {}, {});"/>
		</div>
	</div>
<!-- visibility:hidden;display:none -->
	<div class="m_cover" style="display:none;">
		<!--[if IE 6]><iframe></iframe><div></div><![endif]-->
	</div>

	<!-- 右键菜单 开始 -->
	<div class="c_contextMenu" style="left:550px; top:100px; display:none;">
		<div class="content">
			<div class="wrapper">
				<ul>
					<li><a href="#ogo"><span>关闭当前页</span></a></li>
					<li><a href="#ogo"><span>关闭其他页</span></a></li>
					<li><a href="#ogo"><span>关闭全部</span></a></li>
				</ul>
				<ul>
					<li><a href="#ogo"><span>添加收藏</span></a></li>
				</ul>
			</div>
		</div>
		<div class="shadow"></div>
		<!--[if IE 6]><iframe class="cover"></iframe><![endif]-->
	</div>
	<!-- 右键菜单 结束 -->
	
<!--         <audio id="audio2" controls="controls" loop="loop">  -->
<!-- 		     <source src="chat/msg.wav" type="audio/wav"> -->
<!-- 		     你的浏览器不支持audio标签 -->
<!-- 		</audio> -->
<!-- 		<object height="50%" width="50%" classid="clsid:22D6F312-B0F6-11D0-94AB-0080C74C7E95"> -->
<!-- 			<param name="AutoStart" value="1" /> -->
<!-- 			<param name="FileName" value="chat/msg.wav" /> -->
<!-- 		</object> -->
<!-- 		<img dynsrc="chat/msg.wav" /> -->

</body>
</html>