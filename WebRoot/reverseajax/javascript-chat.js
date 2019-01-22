
function init() {
  dwr.engine.setActiveReverseAjax(true);
}

function sendMessage() {
  var text = dwr.util.getValue("text");
  dwr.util.setValue("text", "");
  JavascriptChat.addMessage(text);
}

function receiveMessages(messages) {
  var chatlog = "";
  var i=0;
  for (var data in messages) {
//	  alert("messages[data"+(i++)+"].text:"+messages[data].text);
    chatlog = "<div>" + dwr.util.escapeHtml(messages[data].text) + "</div>" + chatlog;
//    chatlog+=
  }
//  alert(chatlog);
  dwr.util.setValue("chatlog", chatlog, { escapeHtml:false });
}
