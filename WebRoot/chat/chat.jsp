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

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<!-- 群聊页面是用户发起sendMessage 触发服务端推送到所有客户端-->
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Welcome to AIDM</title>
<link rel="shortcut icon" href="../images/copote.ico" type="image/x-icon" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>scripts/jquery-easyui/1.4.1/themes/default/easyui.css"/>
<link rel="stylesheet" type="text/css" href="<%=basePath%>scripts/jquery-easyui/1.4.1/themes/icon.css"/>
<style type="text/css">


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
<!-- if use jquery 1.4.4 will cause collapse problem -->
<script type="text/javascript" src="<%=basePath%>scripts/jquery-easyui/1.4.1/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath%>scripts/jquery-easyui/1.4.1/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=basePath%>scripts/json/jquery.json-2.2.min.js"></script>
<script type="text/javascript" src="<%=basePath%>scripts/virtue/virtue.js"></script>

<script type='text/javascript' src='<%=basePath%>dwr/engine.js'> </script>
<script type='text/javascript' src='<%=basePath%>dwr/interface/JavascriptChat.js'> </script>
<script type='text/javascript' src='<%=basePath%>dwr/util.js'> </script>
<script type="text/javascript" src='<%=basePath%>chat/javascript-chat2.js'> </script>
  
<script type="text/javascript">
var query = VIRTUE.query;

/** 每隔5秒刷新一次用户列表 **/
function reload(){
	$('#usergrid').datagrid( 'reload', { jdata: {} } );
};


$(document).ready(function(){
	receiveOfflineMsg();
});
/** 接收离线消息 **/
function receiveOfflineMsg( )
{
	query.init();
	jQuery.post( "<%=basePath%>query/tChatMsgJEasyUIQUERYdfind_Offline.action",
		{ jdata: encodeURI($.toJSON( query.jdata )),
		  sort : "d_mstime",
		  order : "asc"
		},
	    function(oResponse, status){
// 		    oResponse = eval("(" + oResponse + ")");//如 果不是struts2的json插件返回就需要加上
//   		    alert( oResponse.total );
		    if( oResponse.total>0 ){//有离线消息
		    	var row = oResponse.rows;
		    	for(var i=0; i<row.length; i++){
		    	    $("#chatlog").append( getSender(row[i].sex, row[i].userlogname, row[i].username )+"&nbsp;"+row[i].d_mstime+"<br/>"); //时间统一用数据库时间
		    	    $("#chatlog").append("<div style='padding:0px 0 0px 10px'><span>"+dwr.util.escapeHtml(row[i].c_msg)+"</span></div>");
		    	}
		    }
	});
};



</script>
</head>
<body onload="init();">
	<!-- 群聊对话框 -->
<div id="w" class="easyui-window" title="AIGRD ODC Migration Delivery Dept." data-options="iconCls:'icon-group',
			closed:false" style="width:594px;height:518px;" style="padding:0px">
	<div class="easyui-tabs" data-options="fit:true,border:false,plain:true">
		<div title="聊天" style="padding:0px;margin:0;">
			<div class="easyui-layout" data-options="fit:true">
				<div data-options="region:'east',split:false,collapsible:true,title:'群成员(<span id=onlineNum>2/215</span>)'" style="width:200px" bodyCls="virtueMemb">
<!-- 					<img src="images/vq/face/16-127-1.bmp"/><span>41000000(谢某某)</span><br> -->
<!-- 					<img src="images/vq/face/16-128-1.bmp"/><span>41000001(张某某)</span> -->
					<table id="usergrid" class="easyui-datagrid" title="" style="width:700px;height:250px"
							data-options="singleSelect:true,fit:true,collapsible:false,showHeader:false,fitColumns:true,
							url:'query/tAuthorityUserJEasyUIQUERYdfind4Chat.action',method:'get',
							onLoadSuccess:function(data){
								//update count display
								//wait 30 second refresh table data
								document.getElementById('onlineNum').innerHTML= data.online+'/'+data.total;
								var t=setTimeout('reload()',30*1000);
							}">
						<thead>
							<tr>
								<th data-options="field:'id',formatter:formatUser,width:'80'"></th>
							</tr>
						</thead>
					</table>
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
						
						<!-- 消息输入 -->
						<div data-options="region:'center',title:'A&nbsp;<span>消息记录</span>'" style= "overflow:visible;height:300px;padding:0px;margin:0;">
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
</body>
</html>