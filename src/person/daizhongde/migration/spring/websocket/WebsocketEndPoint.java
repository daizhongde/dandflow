package person.daizhongde.migration.spring.websocket;


import org.springframework.web.socket.TextMessage;  
import org.springframework.web.socket.WebSocketSession;  
import org.springframework.web.socket.handler.TextWebSocketHandler;  
/**
 * 暂时没有用到
 * @author daizd
 *
 */
public class WebsocketEndPoint extends TextWebSocketHandler {  

  @Override  
  protected void handleTextMessage(WebSocketSession session,  
          TextMessage message) throws Exception {  
      super.handleTextMessage(session, message);  
      TextMessage returnMessage = new TextMessage(message.getPayload()+" received at server");  
      session.sendMessage(returnMessage);  
  }  
}  