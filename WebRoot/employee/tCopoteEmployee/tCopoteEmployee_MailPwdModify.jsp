<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ page import="person.daizhongde.authority.hibernate.pojo.TAuthorityUser"%>
<%@page import="person.daizhongde.authority.constant.SessionConstants"%>
<%@page import="person.daizhongde.authority.spring.service.impl.TAuthorityUserServiceImpl"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="java.util.Base64"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

TAuthorityUser user = (TAuthorityUser) request.getSession().getAttribute(SessionConstants.LOGIN_USER);
if(null==user){
	response.sendRedirect(basePath + SessionConstants.LOGIN_PAGE);
	return;
}

ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(session.getServletContext());
TAuthorityUser u2 = TAuthorityUserServiceImpl.getFromApplicationContext(ctx)
		.findById(user.getNUid());
String emailpwd = StringUtils.isBlank(u2.getCUcip())?"":u2.getCUcip();
if( StringUtils.isNotBlank( emailpwd ) ){
	//反转
	StringBuffer sb = new StringBuffer(emailpwd);
	sb = sb.reverse();
	String reve=sb.toString();
//     System.out.println("反转:"+reve);
	try{
	    //解密
		byte[] byteArr = Base64.getDecoder().decode(reve);
		emailpwd = new String(byteArr);
	}catch(Exception e){
		emailpwd = "";
	}
// 	System.out.println("解密:" + emailpwd);
}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>修改公司邮箱密码</title>
<meta name="keywords" content="keyword1,keyword2,keyword3">
<meta name="description" content="this is my page">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script type="text/javascript" src="../../scripts/jquery.min.js"></script>
<script type="text/javascript" src="../../scripts/json/jquery.json-2.2.min.js"></script>
<script type="text/javascript" src="../../scripts/virtue/virtue.js"></script>

<!--<link rel="stylesheet" type="text/css" href="./styles.css">-->
<style type="text/css">
<!--
body{
	background-color: rgb(228, 235, 241);
}
/* 密码可见性切换（显示和隐藏） */
@font-face {
  font-family: 'iconfont';  /* project id 674189 */
	 src: url('../../css/font/font_674189_dvawifegwrj.eot');
	 src: url('../../css/font/font_674189_dvawifegwrj.eot?#iefix') format('embedded-opentype'),
		  url('../../css/font/font_674189_dvawifegwrj.woff') format('woff'),
		  url('../../css/font/font_674189_dvawifegwrj.ttf') format('truetype'),
		  url('../../css/font/font_674189_dvawifegwrj.svg#iconfont') format('svg');
}
.iconfont {
  display: inline-block;
  font-family: 'iconfont';
  font-style: normal;
  text-rendering: auto;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
          -webkit-transform: translate(0, 0);
              -ms-transform: translate(0, 0);
                  transform: translate(0, 0);
  -webkit-text-stroke-width: 0.2px;
}
 
.bui-input {
    box-sizing: border-box;
    height: 40px;
    padding: 8px 10px;
    line-height: 24px;
    border: 1px solid #DDDDDD;
    color: #5F5F5F;
    font-size: 14px;
    vertical-align: middle;
    border-radius: 4px;
    width: 330px;
}
.bui-input:hover{
    border: 1px #659aea solid;
}
.bui-input:focus {
    outline: none;
    border: 1px solid #4F9FE9;
    box-shadow: 0 0 3px 0 #2171BB;
    color: #595959;
}
.password-wrap { position: relative; width: 330px; }
.password-wrap .bt-showpwd { color: #999999; position: absolute; top: 8px; right: 10px; line-height: 24px; width: 24px; height: 24px;    text-align: center; cursor: pointer; }
.password-wrap .bt-showpwd.off::before { content: "\e60a"; font-family: "iconfont"; font-size: 18px; }
.password-wrap .bt-showpwd.on::before { content: "\e60b"; font-family: "iconfont"; font-size: 18px; }
	
/* 两个按钮 */
.btn { display: block; position: relative; background: #aaa; padding: 5px; float: left; color: #fff; text-decoration: none; cursor: pointer; }  
.btn * { font-style: normal;  background-repeat: no-repeat; display: block; position: relative; }  
.btn i { background-position: top left; position: absolute; margin-bottom: -5px;  top: 0; left: 0; width: 5px; height: 5px; }  
.btn span { background-position: bottom left; left: -5px; padding: 0 0 5px 10px; margin-bottom: -5px; }  
.btn span i { background-position: bottom right; margin-bottom: 0; position: absolute; left: 100%; width: 10px; height: 100%; top: 0; }  
.btn span span { background-position: top right; position: absolute; right: -10px; margin-left: 10px; top: -5px; height: 0; }  
* html .btn span,  
* html .btn i { float: left; width: auto; background-image: none; cursor: pointer; }  
.btn.blue { background: #2ae; font-size: 18px;}  
.btn.green { background: #9d4; }  
.btn.pink { background: #e1a; }  
.btn:hover { background-color: #a00; }  
.btn:active { background-color: #444; }  
.btn[class] {    background-position: bottom; }  
* html .btn { border: 3px double #aaa; }  
* html .btn.blue { border-color: #2ae; }  
* html .btn.green { border-color: #9d4; }  
* html .btn.pink { border-color: #e1a; }  
* html .btn:hover { border-color: #a00; }  
	-->
</style>

<script type="text/javascript">
var update = VIRTUE.update;
var OPE = VIRTUE.operator;

//获取指定请求参数的值
function getParameter(name){ 
  var paramStr=location.search; 
  if(paramStr.length==0)return null; 
  if(paramStr.charAt(0)!='?')return null; 
  paramStr=unescape(paramStr); 
  paramStr=paramStr.substring(1); 
  if(paramStr.length==0)return null; 
  var params=paramStr.split('&'); 
  //alert(params);
  var p = null;
  for(var i=0;i<params.length;i++){
      if(params[i].indexOf(name) >= 0){           
          p = params[i].split('=');
          p = p[1];         
      }
  }
  return p;
}
var uid="";
uid=getParameter("id");

function button_Save_onClick()
{
	var oldPwd = document.getElementById("editor_C_OLDMM").value;
	var newPwd = document.getElementById("editor_C_NEWMM").value;
	var newPwd2 = document.getElementById("editor_C_EMAILMM").value;
	
	if(newPwd=="")
	{
		alert("New Password is Required!");
		document.getElementById("editor_C_NEWMM").focus();
		return;
	}
	if(newPwd2=="")
	{
		alert("Comfirm Password is Required!");
		document.getElementById("editor_C_EMAILMM").focus();
		return;
	}
	
	if ( newPwd != newPwd2 )
	{
		alert ("Two passwords entered are inconsistent!");
		document.getElementById("editor_C_NEWMM").focus();
		return;
	}
	if ( oldPwd == newPwd )
	{
		alert ("New Password must different from current's!");
		document.getElementById("editor_C_NEWMM").focus();
		return;
	}
	/* 	if (uid == "" || uid==undefined)
	{
		alert ("员工ID不能为空!");
		return;
	} */
	update.init();
// 	update.jdata.condition.id = uid;//userid get in action from session
// 	update.jdata.operator.id = OPE.EQUAL;
	
	update.jdata.data.cip = newPwd;
	
	jQuery.post( "../../curd/tAuthorityUserCURDmodifyEMAILPWD.action", 
	  { jdata: encodeURI($.toJSON( update.jdata )) }, 
	  function( oResponse, status )
	  {
// 		oResponse = eval("(" + oResponse + ")");
		alert(oResponse.msg);
		if(oResponse.success == true){
			window.location.reload();
			window.close();
		}
	  }
	);
};

function button_Close_onClick()
{
	window.close();
};

</script>
</head>
<body>
	<form id="pwdModForm" action="" method="post"
		style="margin:0;padding:0;">
		<fieldset title="修改公司邮箱密码">
			<legend>&nbsp;Copote Email Password Management&nbsp;</legend>
			<table>
				<tr>
					<td><label id="label_C_OLDMM" >Current Password：</label></td>
					<td>
					    <div class="password-wrap">
					        <div class="password-input">
					            <input type="password" id="editor_C_OLDMM" name="editor_C_OLDMM" 
					            	value="<%=emailpwd %>" class="bui-input" autocomplete="off" disabled="disabled"/>
					        </div>
					        <i class="bt-showpwd off"></i>
					    </div>
					</td>
				</tr>
				<tr>
					<td><label id="label_C_NEWMM">New Password：</label></td>
					<td>
					    <div class="password-wrap">
					        <div class="password-input">
					            <input type="password" id="editor_C_NEWMM" name="editor_C_NEWMM" class="bui-input" autocomplete="off" />
					        </div>
					        <i class="bt-showpwd off"></i>
					    </div>
					</td>
				</tr>
				<tr>
					<td><label id="label_C_EMAILMM">Comfirm Password：</label></td>
					<td>
					    <div class="password-wrap">
					        <div class="password-input">
					            <input type="password" id="editor_C_EMAILMM" name="editor_C_EMAILMM" class="bui-input" autocomplete="off" />
					        </div>
					        <i class="bt-showpwd off"></i>
					    </div>
					</td>
				</tr>
			</table>
		</fieldset>
		
		<div id="pwdModForm-buttons">
			<input class="btn blue big" type="button" value="Submit" onclick="button_Save_onClick()"/>
			&nbsp;
			<input class="btn blue big" type="button" value="Close" onclick="button_Close_onClick()" style="margin:0 0 0 50px"/>
		</div>
	</form>
<script type="text/javascript">

jQuery(document).ready(function(){
	/* 密码可见性切换（显示和隐藏） */
	$(".bt-showpwd").on("click",  function (e) {
	    e.preventDefault();
	    var $this = $(this);
	    var $password = $this.closest(".password-wrap");
	    var $input = $password.find('input');
	    var $inputWrap = $password.find('.password-input');
	    var newinput = '', inputHTML = $inputWrap.html(), inputValue = $input.val();
	    if ($input.attr('type') === 'password') {
	        newinput = inputHTML.replace(/type\s*=\s*('|")?password('|")?/ig, 'type="text"');
	        $inputWrap.html(newinput).find('input')[0].value = inputValue;
	        $this.removeClass("off").addClass("on");
	    } else {
	        newinput = inputHTML.replace(/type\s*=\s*('|")?text('|")?/ig, 'type="password"');
	        $inputWrap.html(newinput).find('input')[0].value = inputValue;
	        $this.removeClass("on").addClass("off");
	    }
	});

}); 
</script>
</body>
</html>