function init() {
  dwr.engine.setActiveReverseAjax(true);
}

function sendMessage() {
//	alert( "sendMessage:"+ displayFlag||displayFla1 );
	SalaryImportSendMailMsg.sendDisplayflag( displayFlag||displayFla1 );
}

function receiveMessages(oResponse) {
	if(displayFlag){
		$("#backmsg").text(oResponse);
	}else if(displayFla1){
		$("#backmsg2").text(oResponse);
	}
	if (displayFlag || displayFla1) {
//		setTimeout("sendMessage()", 1*1000);
		sendMessage();
	}
	/* 页面间隔发起sendMessage 触发服务端推送 */
	// setTimeout("sendMessage()", 3*1000);
};
