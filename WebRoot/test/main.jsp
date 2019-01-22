<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="person.daizhongde.authority.hibernate.pojo.TAuthorityUser"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

TAuthorityUser user = (TAuthorityUser)session.getAttribute("TAuthorityUser");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>EMS数据分析平台</title>
    <meta name="content-type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>scripts/jquery-easyui/1.3.5/themes/default/easyui.css"/>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>scripts/jquery-easyui/1.3.5/themes/icon.css"/>
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
	<script type="text/javascript" src="<%=basePath%>scripts/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>scripts/jquery-easyui/1.3.5/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>scripts/jquery-easyui/1.3.5/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="<%=basePath%>scripts/json/jquery.json-2.2.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>scripts/virtue/virtue.js"></script>
<script type="text/javascript">
var add = VIRTUE.add;
var query = VIRTUE.query;

function getSender(sex, logname, name )
{
	var sender = "";
	if(sex=="男")
		sender = "<img src='images/vq/face/16-127-1.bmp'/>";
	else
		sender = "<img src='images/vq/face/16-128-1.bmp'/>";
	sender+= "<span>"+logname+"("+name+")</span>";
	
	return sender;
};

var sender = getSender('<%=user.getCUsex().trim() %>','<%=user.getCUlogname() %>','<%=user.getCUname() %>'); 

// alert(sender);

$(document).ready(function(){
	RoundMSG();
});
var mid = ""; 
var twinkleFlag = 2;//1闪,2不闪

function RoundMSG()
{
	query.init();
	jQuery.post( "<%=basePath%>tEmsdaGmstateJEasyUIQUERY.do?method=dfindTotal",
		{ jdata: encodeURI($.toJSON( query.jdata )) },
	    function(oResponse, status){
		    oResponse = eval("(" + oResponse + ")");//如 果不是struts2的json插件返回就需要加上
//  		    alert(oResponse);
		    if( oResponse>0 ){//有离线消息
		    	twinkleFlag = 1;
		    	show();
		    	receiveOfflineMsg(oResponse);
		    }
	});
};
function show(){
  var imgid=document.getElementById("imgid");
  if(imgid.style.visibility == "visible")
  	 imgid.style.visibility = "hidden";
  else
     imgid.style.visibility = "visible";

  if(twinkleFlag==1)
	 setTimeout('show()',300);
  else
	 imgid.style.visibility = "visible";
};
/** 接收离线消息 **/
function receiveOfflineMsg( count )
{
	query.init();
	jQuery.post( "<%=basePath%>tEmsdaGmstateJEasyUIQUERY.do?method=dfind",
		{ jdata: encodeURI($.toJSON( query.jdata )) },
	    function(oResponse, status){
		    oResponse = eval("(" + oResponse + ")");//如 果不是struts2的json插件返回就需要加上
//  		    alert( oResponse.total );
		    if( oResponse.total>0 ){//有离线消息
		    	var row = oResponse.rows;
		    	for(var i=0; i<count; i++){
		    	    $("#pubmsg").append( getSender(row[i].sex, row[i].logname, row[i].name )+"&nbsp;"+row[i].stime+"<br/>"); //时间统一用数据库时间
		    	    $("#pubmsg").append("<div style='padding:0px 0 0px 10px'><span>"+row[i].msg+"</span></div>");
		    	}
		    }
	});
};
	  
/** 获取用户的输入信息 **/
function getMymsg(){
	return $("#mymsg").val();
};
function clearMymsg(){
	return $("#mymsg").val("");
};
/** 发送群消息
@param mType 消息类型|1点对点消息,2群消息
@param mDest 消息目标|mType=1时为接收者ID,mType=2时为群ID
 **/
function sendGM(mType, mDest) {
	send(mType, mDest, getMymsg() );
};
/** 发送消息
@param mType 消息类型|1点对点消息,2群消息
@param mDest 消息目标|mType=1时为接收者ID,mType=2时为群ID
@param msg 消息内容
 **/
function send(mType, mDest, msg) {
	if(msg=="") return;
	
	add.init();
	add.jdata.data.gid = mDest;
	add.jdata.data.msg = msg;
	
	jQuery.post( "<%=basePath%>tEmsdaGmsgCURD.do?method=add",
		{ jdata: encodeURI($.toJSON( add.jdata )) },
	    function(oResponse, status){
		    oResponse = eval("(" + oResponse + ")");//如 果不是struts2的json插件返回就需要加上
// 		    alert(oResponse.msg);
		    clearMymsg();
// 		    <img src="images/vq/face/16-127-1.bmp"/><span>41000000(谢某某)</span>&nbsp;&nbsp;11:37:30 
// 		    <br/>
// 		    <div style="padding:0px 0 0px 10px">
// 		    	<span>324234</span>
// 		    </div>
		    $("#pubmsg").append(sender+"&nbsp;"+oResponse.time+"<br/>"); //时间统一为数据库时间
		    $("#pubmsg").append("<div style='padding:0px 0 0px 10px'><span>"+msg+"</span></div>");
		    
		    if( !oResponse.success ){//发送成功
// 			   alert("没有发送成功！");
		    }
	});
};

</script>
</head>

	<body marginheight="0" marginwidth="0" margintop="0" marginright="0" marginleft="0" marginbottom="0" 
	style="margin:0;padding:0;" class="easyui-layout">
		<div data-options="region:'south',split:true" class="easyui-panel" style="height:50px;" bodyCls="virtueTask">
			<span onclick="javascript:$('#w').window('open');"><img id="imgid" src="<%=basePath%>images/vq/bull-1.gif" 
			style="padding:6px 5px 0px 5px" height="30px" width="30px" align="right"
			alt="VQ:EMS群(1)" 
			/></span>
		</div>
		<div data-options="region:'center'" style="overflow:hidden;" noheader="true">
			<iframe name="myiframe" src="<%=basePath%>Webmain/main.html" height="100%" width="100%" style="margin:0;padding:0;"  scrolling="no"></iframe>
		</div>
	
	<!-- 群聊对话框 -->
<div id="w" class="easyui-window" title="EMS群" data-options="iconCls:'icon-group',
			closed:true,
			onOpen:function(){
				twinkleFlag =2;
			}" style="width:594px;height:518px;" style="padding:0px">
	<div class="easyui-tabs" data-options="fit:true,border:false,plain:true">
		<div title="聊天" style="padding:0px;margin:0;">
			<div class="easyui-layout" data-options="fit:true">
				<div data-options="region:'east',split:true,title:'群成员(<span id=onlineNum>2</span>/215)'" style="width:200px" bodyCls="virtueMemb">
				<img src="images/vq/face/16-127-1.bmp"/><span>41000000(谢某某)</span><br>
				<img src="images/vq/face/16-128-1.bmp"/><span>41000001(张某某)</span>
				</div>
				<div data-options="region:'center'" style="padding:0px;margin:0;">
					<div class="easyui-layout" data-options="fit:true,border:false,plain:true">
						<!-- 消息显示 &nbsp;&nbsp;&nbsp;&nbsp; -->
						<div id="pubmsg" name="pubmsg" data-options="region:'north'" maxWidth=100 style="height:300px;padding:0px;margin:0;">
						
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
						<!-- 下面的发送按钮 -->
						<div data-options="region:'south',split:false" style="height:25px;background-color:#E6D5EC; FILTER: Alpha(Opacity=100,Finishopacity=0,Style=1,Startx=0,Starty=0,Finishx=100,Finishy=0);"><!-- 发送按钮 -->
							<span style="float:right">
								<input type="button" onclick="javascript:$('#w').window('close')" value="关闭(C)" align="right"/>
								<input type="button" onclick="javascript:sendGM(2,1)" value="发送(S)" align="right"/>
							</span>
						</div>
						<!-- 消息输入 -->
						<div data-options="region:'center',title:'A&nbsp;<span>消息记录</span>'">
							<textarea rows="5" cols="42" id="mymsg" name="mymsg"></textarea>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- <div style="width:156px; height:200px; background-color:#3F3F3F; FILTER: Alpha(Opacity=100,Finishopacity=0,Style=1,Startx=0,Starty=0,Finishx=0,Finishy=100);">
   <p>div+css设置背景色渐形效果</p>
   <p>用div+css生成的背景色渐形效果</p>
   <p>div+css背景色渐形效果</p>
   <p>设置背景色渐形效果div+css</p>
   <p>牛奶支持的背景色渐形div+css效果</p>
</div>

/*
程序说明：
Opacity"透明度.从0到100,0代表完全透明.100代表完全不透明.
"Finishopacity"可选，指定结束时的透明度.0到100.
"Style"透明区域形状.其中#可为:0代表统一形状,1线形,2放射状,3长方形.
"Startx"和"Starty"渐变透明效果的开始X和Y坐标.
"Finsihx"和"Finsihy"渐变透明效果结束X和Y的坐标. 
*/  -->
	</body>
</html>