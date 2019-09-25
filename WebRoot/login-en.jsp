<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="person.daizhongde.authority.constant.SessionConstants"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	
	Object o = session.getAttribute(SessionConstants.LOGIN_USER);
	if(null != o){
		response.sendRedirect( SessionConstants.WELCOME_PAGE );
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" class="s_lang-en">
<head>
    <base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Copote OA Login</title>
<link href="css/frame-css/login.css" rel="stylesheet" type="text/css" />
<link rel="shortcut icon" href="images/ico/copote.ico" type="image/x-icon" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<style type="text/css">
<!--
.pannel .login .value img { 
position:relative;
top:3px
}

#errordiv{
	font-family: "微软雅黑", Arial;
	font-size:20px;
	color: red;
	position: absolute;
	left: 260px;
	top: 470px;
}
-->
</style>
<script type="text/javascript">

function breakout(){
	if (window.top!=window.self){
		window.top.location="login-en.jsp";
// 		t=setTimeout("randomTime()",Math.round(Math.random()*10)* 1000);
// 		t=setTimeout( "randomTime()", Math.round(Math.random()*10000) );
	}
	changecheckrand();
	document.form1.j_username.focus();
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

function changecheckrand()
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
};
function get_random_number(){
     var  number_tmp;
     number_tmp=Math.floor(9999*Math.random());
     return number_tmp;
};
function checkB4TJ(){
	if(document.form1.j_username.value == ""){
		alert("User name is required!");
		document.form1.j_username.focus();
		return;
	}
	if(document.form1.j_password.value == ""){
		alert("Password is required!!");
		document.form1.j_password.focus();
		return;
	}
	if(document.form1.rand.value == ""){
		alert("Verification Code is required!!");
		document.form1.rand.focus();
		return;
	}
	
	var chkbox=document.getElementById("remember");
	if(chkbox.checked){
		saveUsername();
	}
	document.form1.submit();
};
// onmousedown="whichButton(event)"  onkeydown="whichButton2(event)"
/** 重置 */
function reset(){
	document.form1.j_username.value = "";
	document.form1.j_password.value = "";
	document.form1.rand.value = "";
};
</script>
</head>
<body onload="breakout()">
<form name="form1" action="loginServlet" method="post"
		style="margin:0;padding:0;">

	<div class="bg"></div>
	<div class="map"></div>
	<div class="pannel">
		<div class="logo"></div>
		<div class="head"></div>
		<div class="login">
			<ul>
				<li>
					<div class="label">UserName：</div>
					<div class="value">
						<span class="me_input"><input name="j_username" id="j_username"
							onfocus="this.parentNode.className += ' me_input-focus';"
							onblur="this.parentNode.className='me_input'" type="text" /></span>
					</div>
				</li>
				<li>
					<div class="label">Password：</div>
					<div class="value">
						<span class="me_input"><input name="j_password" id="j_password"
							onfocus="this.parentNode.className += ' me_input-focus';"
							onblur="this.parentNode.className='me_input'" type="password" /></span>
					</div>
				</li>
				<li>
					<div class="label">Verify Code：</div>
					<div class="value">
						<span class="me_input-verify"><input name="rand" id="rand"
														onkeydown="enterKeyDown(event)"
							onfocus="this.parentNode.className += ' me_input-focus';"
							onblur="this.parentNode.className='me_input-verify'" type="text" /></span>
<IMG height=15 src="images/login/digital0.gif" width=10
align=middle border=0 name=img1><IMG height=15
src="images/login/digital1.gif" width=10 align=middle
border=0 name=img2><IMG height=15
src="images/login/digital2.gif" width=10 align=middle
border=0 name=img3><IMG height=15
src="images/login/digital3.gif" width=10 align=middle
border=0 name=img4><INPUT type=hidden
name=checkrand>
						<a href="#nogo" class="me_button" onclick="changecheckrand();return false;">refresh</a>
					</div>
				</li>
			</ul>
			<div class="submit">
				<button onclick="checkB4TJ()" type="button"></button>
			</div>
			<div id="errordiv"><span><%
					                Object tip=request.getAttribute("tip");
					                if( tip!=null ){
					                  out.println("Login error!"+tip.toString());
					                }
					                //admin/123qweasd
			%></span></div>
		</div>
		<div class="remember">
			<label for="remember"><input id="remember" type="checkbox" />remember
				me</label> <a href="#nogo">sign in</a>
		</div>
		<div class="use-nt">
			<a id="use-nta" href="login-en-NT.jsp">使用NT账号登陆</a>
		</div>
	</div>
	<div class="copyright">
		<span class="logo"></span>Copyright &copy; 2018-2019 Virtue Software
	</div>

	</form>
</body>
<script type="text/javascript">
try {
	if (getCookie("j_username") != null) {
		document.getElementById("j_username").value = getCookie("j_username");
		document.getElementById("j_password").focus();
	} else {
		document.getElementById("j_username").focus();
	}
	
} catch (err) {
	txt = "此页面存在一个错误。\n\n";
	txt += "错误描述: " + err.description + "\n\n";
	txt += "点击OK继续。\n\n";
	alert(txt);
};

function saveUsername(theForm) {
	var expires = new Date();
	expires.setTime(expires.getTime() + 24 * 30 * 60 * 60 * 1000);
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