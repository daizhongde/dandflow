<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="person.daizhongde.authority.hibernate.pojo.TAuthorityUser"%>
<%@ page import="person.daizhongde.authority.hibernate.pojo.TAuthorityRole"%>
<%@page import="person.daizhongde.authority.constant.SessionConstants"%>
<%@page
	import="person.daizhongde.authority.spring.service.impl.TAuthorityRoleServiceImpl"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page
	import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	TAuthorityUser user = (TAuthorityUser) request.getSession()
			.getAttribute(SessionConstants.LOGIN_USER);

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
<link rel="stylesheet" type="text/css" href="scripts/jquery-easyui/1.4.1/themes/default/easyui.css"/>
<link rel="stylesheet" type="text/css" href="scripts/jquery-easyui/1.4.1/themes/icon.css"/>
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

.virtueTask{
	background-color: #2D95CE;
}
.virtueMemb{
	background-color: #D6DEE6;
}
pre .message{
	padding: -60px,0,0,0px;
}
.icon-group{
	background:url('<%=basePath%>images/vq/group-min.jpg') no-repeat center center;
}
.icon-msgr{
	background:url('<%=basePath%>images/vq/msgrecord.jpg') no-repeat center center;
}

</style>
<script type="text/javascript" src="scripts/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="scripts/jquery-easyui/1.4.1/jquery.easyui.min.js"></script>
<script type="text/javascript" src="scripts/json/jquery.json-2.2.min.js"></script>
<script type="text/javascript" src="scripts/virtue/virtue.js"></script>
<script type="text/javascript" src="tree/tAuthorityModuleTREEquery_GubuSoft_TreeJS_User.action"></script>

<script type='text/javascript' src='dwr/engine.js'> </script>
<script type='text/javascript' src='dwr/interface/JavascriptChat.js'> </script>
<script type='text/javascript' src='dwr/util.js'> </script>
<script type="text/javascript" src='javascript-chat2.js'> </script>
  
<script type="text/javascript">
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

</script>
</head>
<body onload="init();">
	<!-- 顶部 开始 -->
	<div class="m_header">
		<div class="logo logo-en"></div>
		<div class="fn">
			<div class="user">
<!-- 				<a href="javascript:void(0)" class="icon-reload" onclick="javascript:$('#LOGdlg1').panel('refresh')">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a> -->
				<span class="name"><%=user.getCUlogname()%>-<%=user.getCUname()%></span>
				<span class="role">[<%=role%>]
				</span>
			</div>
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
	
}); 
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
			<a href="javascript:void(0)" class="icon-back" onclick="javascript:basefrm.history.go(-1)">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
			<a href="javascript:void(0)" class="icon-forward" onclick="javascript:basefrm.history.go(1)">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
			<a href="javascript:void(0)" class="icon-reload" onclick="javascript:basefrm.location.reload()">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
			<a href="javascript:void(0)" class="icon-home" onclick="javascript:basefrm.location.href='welcome.html'">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
		</div>
	Copyright&copy;2015 Asiainfo.All Rights
		Reserved
		<div style="float:right;visibility: visible;">
			<span onclick="javascript:$('#w').window('open');"><img id="imgid" src="<%=basePath%>images/vq/bull-1-min.gif" 
			 style="padding:1px 18px 0px 1px" height="24px" width="24px" align="right"
			 alt="VQ:Migration Group(1)"/>
			</span>
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


	<!-- 群聊对话框 -->
<div id="w" class="easyui-window" title="Migration Group" data-options="iconCls:'icon-group',
			closed:true,
			onOpen:function(){
				twinkleFlag =2;
			}" style="width:594px;height:518px;" style="padding:0px">
	<div class="easyui-tabs" data-options="fit:true,border:false,plain:true">
		<div title="聊天" style="padding:0px;margin:0;">
			<div class="easyui-layout" data-options="fit:true">
				<div data-options="region:'east',split:false,title:'群成员(<span id=onlineNum>2</span>/215)'" style="width:200px" bodyCls="virtueMemb">
				<img src="images/vq/face/16-127-1.bmp"/><span>41000000(谢某某)</span><br>
					<img src="images/vq/face/16-128-1.bmp"/><span>41000001(张某某)</span>
				</div>
				<div data-options="region:'center'" style="padding:0px;margin:0;">
					<div class="easyui-layout" data-options="fit:true,border:false,plain:true">
						<!-- 消息显示 &nbsp;&nbsp;&nbsp;&nbsp;maxWidth=100 -->
						<div id="chatlog" name="chatlog" data-options="region:'north'" style="height:300px;padding:0px;margin:0;">
						
<!-- <img src="images/vq/face/16-127-1.bmp"/><span>41000000-谢某某&nbsp;&nbsp;11:37:30 </span>
<br/>
<div style="padding:0px 0 0px 10px">
	<span>324234</span>
</div>

<img src="images/vq/face/16-128-1.bmp"/><span>41000001-张某某&nbsp;&nbsp;11:37:30 </span>
<br/>
<div style="padding:0px 0 0px 10px">
	<span>sdfsdf张某某张某某张某某张某某张某某erfe无sfetwretw无无无无f无无无无张某某张某某张某某张某某
	椟槥无 无f无无无无张某某张某无f无无无无张某某张某无f无无无无张某某张某无f无无无无张某某张某
	</span>
</div>
 -->
						</div>
						<div data-options="region:'center'" style="padding:0px;margin:0;">
							<div class="easyui-layout" data-options="fit:true,border:false,plain:true">
								<div id="toolbar" name="toolbar" data-options="region:'north'" style="height:20px;padding:0px;margin:0;">
								A&nbsp;<span>消息记录</span>
								</div>
								<!-- 消息输入 -->
								<div data-options="region:'center'" style= "overflow:visible;height:280px;padding:0px;margin:0;">
									<textarea rows="5" cols="42" id="mymsg" name="mymsg" onkeypress="dwr.util.onReturn(event, sendMessage)"></textarea>
								</div>
								<!-- 下面的发送按钮 -->
								<div data-options="region:'south',split:false" style="height:25px;background-color:#E6D5EC; FILTER: Alpha(Opacity=100,Finishopacity=0,Style=1,Startx=0,Starty=0,Finishx=100,Finishy=0);"><!-- 发送按钮 -->
									<span style="float:right">
										<input type="button" onclick="javascript:$('#w').window('close')" value="Close(C)" align="right"/>
										<input type="button" onclick="javascript:sendMessage()" value="Send(S)" align="right"/>
									</span>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>