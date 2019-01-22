/** 获取用户的输入信息 **/
function getMymsg(){
	return $("#mymsg").val();
};
function clearMymsg(){
	return $("#mymsg").val("");
};

function getSender(sex, logname, name )
{
	var sender = "";
//	alert(sex);
	if(sex=="男" || sex=="male")
		sender = "<img src='images/vq/face/16-127-1.bmp'/>";
	else
		sender = "<img src='images/vq/face/16-128-1.bmp'/>";
	sender+= "<span>"+logname+"("+name+")</span>";
	
	return sender;
};

function init() {
  dwr.engine.setActiveReverseAjax(true);
}

function sendMessage() {
  var text = dwr.util.getValue("mymsg");

  dwr.util.setValue("mymsg", "");
  JavascriptChat.addMessage(text);
}

function receiveMessages(messages) {
  var chatlog = "";
  var i=0;
  for (var data in messages) {
//	  alert("messages[data"+(i++)+"].text:"+messages[data].text);
//    chatlog = "<div>" + dwr.util.escapeHtml(messages[data].text) + "</div>" + chatlog;
	  var stime = "11:37:30";; //时间统一用数据库时间
	  chatlog =  getSender(messages[data].sex, 
			  messages[data].userlogname, 
			  messages[data].username )+"&nbsp;"+ messages[data].time +"<br/>"
	  +"<div style='padding:0px 0 0px 10px'><span>"+ dwr.util.escapeHtml(messages[data].text) +"</span></div>" + chatlog;
    
  }
//  alert(chatlog);
  dwr.util.setValue("chatlog", chatlog, { escapeHtml:false });
};

function formatUser(val,row){
//	var logins = row.logins;
//	var sex = row.sex; 
	
	var ret = "";
	if(row.sex=="男" || row.sex=="male"){
		if(row.logins=="0"){
			ret = "<img src='images/vq/face/16-127-2.bmp'/>";
		}else{
			ret = "<img src='images/vq/face/16-127-1.bmp'/>";
		};
	}else{
		if(row.logins=="0"){
			ret = "<img src='images/vq/face/16-128-2.bmp'/>";
		}else{
			ret = "<img src='images/vq/face/16-128-1.bmp'/>";
		};
	}
	ret+= "<span>"+row.logname+"("+row.name+")</span>";
	
	return ret;
};
