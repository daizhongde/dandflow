function init() {
  dwr.engine.setActiveReverseAjax(true);
}

function sendMessage() {
//	alert("sendMessage");
	SalaryImportSendMailMsg.sendDisplayflag(displayFlag );
}

function receiveMessages(oResponse) {
	$("#backmsg").text(oResponse);
	if (displayFlag) {
		sendMessage();
	}
	/* 页面间隔发起sendMessage 触发服务端推送 */
	// setTimeout("sendMessage()", 3*1000);
};
