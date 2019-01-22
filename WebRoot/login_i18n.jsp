<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	
// 	session.setAttribute("SubmitFlag","login.jsp"); 
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><s:text name="systemname"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	overflow:hidden;
}
.STYLE3 {font-size: 12px; color: #adc9d9; }
#errormsg{
	font-family: "微软雅黑", Arial;
	font-size:16px;
	color: red;
	padding:0px 0px 0px 0px;
}
#errordiv{
	padding:6px 0px 6px 370px;
}

.system{
	position: relative;
  	left: 235px;
  	top: 125px;
}
.system .name_cn{
	font-family: "微软雅黑", Arial;
	font-size:30px;
	color: white;
}

.system .name_en{
	font-family: "微软雅黑", Arial;
	font-size:15px;
	color: white;
}

.nospacetext_dl{
 	position: absolute;
  	left: 850px;
  	top: 360px;
	font-family: "微软雅黑", Arial;
	font-size:12px;
	color: white;
}
.nospacetext_cz{
 	position: absolute;
  	left: 850px;
  	top: 385px;
	font-family: "微软雅黑", Arial;
	font-size:12px;
	color: white;
}
-->
</style>
<script type="text/javascript">

function breakout(){
	if (window.top!=window.self){
		window.top.location="login.jsp";
// 		t=setTimeout("randomTime()",Math.round(Math.random()*10)* 1000);
// 		t=setTimeout( "randomTime()", Math.round(Math.random()*10000) );
	}
	winopen();
};

/** This function is used to set cookies */
function setCookie(name,value,expires,path,domain,secure) {
  document.cookie = name + "=" + escape (value) +
    ((expires) ? "; expires=" + expires.toGMTString() : "") +
    ((path) ? "; path=" + path : "") +
    ((domain) ? "; domain=" + domain : "") + ((secure) ? "; secure" : "");
};

/** This function is used to get cookies */
function getCookie(name) {
	var prefix = name + "=" ;
	var start = document.cookie.indexOf(prefix);

	if (start==-1) {
		return null;
	}
	
	var end = document.cookie.indexOf(";", start+prefix.length);
	if (end==-1) {
		end=document.cookie.length;
	}

	var value=document.cookie.substring(start+prefix.length, end);
	return unescape(value);
};

var img_array=new Array(10);
for (var counter=0;counter<img_array.length;counter++)
{
         img_array[counter]=new Image();
	 img_filename="images/login/digital"+counter+".gif";
	 img_array[counter].src=img_filename;
};

function winopen()
{
	var random_number,random_String;
    random_number=get_random_number();
    if (random_number<10){
      random_String="000"+random_number;
    }
    else
    {
      if (random_number<100){
         random_String="00"+random_number;
      }
      else{
         if (random_number<1000){
           random_String="0"+random_number;
         }
         else{
           random_String=""+random_number;
         }
      }
    }
    for (var ii=1;ii<=4;ii++){
 		document.images["img"+ii].src=img_array[parseInt(random_String.substring(ii-1,ii))].src;
    }
    document.form1.checkrand.value=random_String;
    document.form1.j_username.focus();//
};
function get_random_number(){
     var  number_tmp;
     number_tmp=Math.floor(9999*Math.random());
     return number_tmp;
};
function submit(){
	document.form1.submit();
};
function reset(){
	document.form1.j_username.value="";
	document.form1.j_password.value="";
	document.form1.rand.value="";
};
// onmousedown="whichButton(event)"  onkeydown="whichButton2(event)"
</script>
</head>
<body onload="breakout()">
	<form name="form1" action="loginServlet" method="POST"
		style="margin:0;padding:0;">
		<table width="100%" height="100%" border="0" cellspacing="0" name="table1" 
			cellpadding="0">
			<tr>
				<td bgcolor="#1075b1">&nbsp;</td>
			</tr>
			<tr>
				<td height="608" background="images/login/login_03.gif"><table
						width="847" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td height="318" background="images/login/login_04.gif">
								<div class="system">
									<span class="name_cn">数据迁移管理平台-AIDM V2.0</span>
									<br>
									<span class="name_en">Data Migration Management Platform </span>
								</div>
							</td>
						</tr>
						<tr>
							<td height="84">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="381" height="84"
											background="images/login/login_06.gif">
											<img alt="asiainfo" src="images/login/asiainfo.jpg" 
											width="80px" height="80px" 
											style="padding-left: 235px;"/>
										</td>
										<td width="162" valign="middle"
											background="images/login/login_07.gif"><table width="100%"
												border="0" cellspacing="0" cellpadding="0">
												<tr>
													<td width="44" height="24" valign="bottom"><div
															align="right">
															<span class="STYLE3"><s:text name="user"/></span>
														</div></td>
													<td width="10" valign="bottom">&nbsp;</td>
													<td height="24" colspan="2" valign="bottom">
														<div align="left">
															<input type="text" name="j_username" id="j_username"
																style="width:100px; height:17px; background-color:#87adbf; border:solid 1px #153966; font-size:12px; color:#283439; ">
														</div>
													</td>
												</tr>
												<tr>
													<td height="24" valign="bottom"><div align="right">
															<span class="STYLE3"><s:text name="pass"/></span>
														</div></td>
													<td width="10" valign="bottom">&nbsp;</td>
													<td height="24" colspan="2" valign="bottom"><input
														type="password" name="j_password" id="j_password"
														style="width:100px; height:17px; background-color:#87adbf; border:solid 1px #153966; font-size:12px; color:#283439; "></td>
												</tr>
												<tr>
													<td height="24" valign="bottom"><div align="right">
															<span class="STYLE3"><s:text name="identifyingcode"/></span>
														</div></td>
													<td width="10" valign="bottom">&nbsp;</td>
													<td width="52" height="24" valign="bottom"><input
														type="text" name="rand" id="rand"
														onkeydown="enterKeyDown(event)"
														style="width:50px; height:17px; background-color:#87adbf; border:solid 1px #153966; font-size:12px; color:#283439; "></td>
													<td width="62" valign="bottom"><div align="left"
															valign="bottom">
															<IMG height=15 src="images/login/digital0.gif" width=10
																align=middle border=0 name=img1><IMG height=15
																src="images/login/digital1.gif" width=10 align=middle
																border=0 name=img2><IMG height=15
																src="images/login/digital2.gif" width=10 align=middle
																border=0 name=img3><IMG height=15
																src="images/login/digital3.gif" width=10 align=middle
																border=0 name=img4><INPUT type=hidden
																name=checkrand>
														</div></td>
												</tr>
												<tr></tr>
											</table></td>
										<td width="26"><img src="images/login/login_08.gif"
											width="26" height="84"></td>
										<td width="67" background="images/login/login_09.gif"><table
												width="100%" border="0" cellspacing="0" cellpadding="0">
												<tr>
													<td height="25">
														<div align="center" onclick="submit()">
<!-- 															<input type="button" value="登陆" height='15px'/> -->
															<img src="images/login/dl_zh.gif" onMouseOver="this.border=1"
																onmouseout="this.border=0" width="57" height="20">
														</div>
													</td>
												</tr>
												<tr>
													<td height="25">
														<div align="center" onclick="reset()">
<!-- 															<input type="button" value="重置"/> -->
															<img src="images/login/cz_zh.gif" onMouseOver="this.border=1"
																onmouseout="this.border=0" width="57" height="20">
														</div>
													</td>
												</tr>
											</table></td>
										<td width="211" background="images/login/login_10.gif">&nbsp;</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td height="206" valign="top" background="images/login/login_11.gif"><div id="errordiv"><span id="errorMsg" style="color: red;"><%
					                Object tip=request.getAttribute("tip");
					                if( tip!=null ){
					                  out.println("登录失败!"+tip.toString());
					                }
					                //admin/123qweasd
					                %></span></div></td>
						</tr>
					</table></td>
			</tr>
			<tr>
				<td bgcolor="#152753" align="center" valign="middle"><span style="color: #adc9d9;">Copyright&#169;2015亚信科技（中国）有限公司</span></td>
			</tr>
		</table>
	</form>
</body>
<script type="text/javascript">
try {
	if (getCookie("j_username") != null) {
// 		document.form1.j_username.value = getCookie("j_username");
// 		document.form1.j_password.focus();
		document.getElementById("j_username").value = getCookie("j_username");
		document.getElementById("j_password").focus();
	} else {
// 		document.form1.j_username.focus();
		document.getElementById("j_username").focus();
	}
	
} catch (err) {
	txt = "此页面存在一个错误。\n\n";
	txt += "错误描述: " + err.description + "\n\n";
	txt += "点击OK继续。\n\n";
	alert(txt);
}

function saveUsername(theForm) {
	var expires = new Date();
	expires.setTime(expires.getTime() + 24 * 30 * 60 * 60 * 1000);
// 	setCookie("j_username", theForm.j_username.value, expires);
	setCookie("j_username", document.getElementById("j_username").value, expires);
};

function enterKeyDown(event)
{
    if(event.keyCode==13)
    {
    	document.form1.submit();
	}
};

</script>
</html>