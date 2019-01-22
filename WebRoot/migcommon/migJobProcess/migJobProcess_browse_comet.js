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
	MigJobInsComet.sendInsId(insId, currentJobId );
}

function receiveMessages(oResponse) {
	 for(var i=0; i<oResponse.length; i++){
//	     imges[i].attr("src","../images/job/"+PSTATEICON[oResponse[i].status])
//		 console.log("img"+oResponse[i].node_id+"|"+oResponse[i].status);
		 r.getById("img"+oResponse[i].node_id).attr("src","../images/job/"+PSTATEICON[oResponse[i].status]);
	 }
	 
//	 sendMessage();
	 /* 页面间隔发起sendMessage 触发服务端推送 */
	 setTimeout("sendMessage()", 3*1000);
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
