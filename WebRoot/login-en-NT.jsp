<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" class="s_lang-en">
<head>
    <base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Copote OA Login-NT</title>
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

function checkB4TJ(){
	if(document.form1.j_domain.value == ""){
		alert("User domain is required!");
		document.form1.j_domain.focus();
		return;
	}
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
};
</script>
</head>
<body onload="breakout()">
<form name="form1" action="loginServlet4NT" method="post"
		style="margin:0;padding:0;">

	<div class="bg"></div>
	<div class="map"></div>
	<div class="pannel">
		<div class="logo"></div>
		<div class="head"></div>
		<div class="login">
			<ul>
				<li>
					<div class="label">Domain：</div>
					<div class="value">
						<span class="me_input"><input name="j_domain" id="j_domain" value="ai"
							onfocus="this.parentNode.className += ' me_input-focus';" 
							onblur="this.parentNode.className='me_input'" type="text" /></span>
					</div>
				</li>
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
			<a id="use-nta" href="login-en.jsp">使用AIDM账号登陆</a>
		</div>
	</div>
	<div class="copyright">
		<span class="logo"></span>Copyright &copy; 2014-2016 Asiainfo
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