/*
 * Copyright 2005 daizd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package person.daizhongde.migration.dwr.reverseajax;

import java.util.LinkedList;

import org.directwebremoting.Browser;
import org.directwebremoting.ScriptSessions;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;

import person.daizhongde.virtue.dao.GlobalEnvDAO;

import person.daizhongde.authority.constant.SessionConstants;
import person.daizhongde.authority.hibernate.pojo.TAuthorityUser;
import person.daizhongde.migration.hibernate.dao.TChatMsgDAO;
import person.daizhongde.migration.hibernate.pojo.TChatMsg;

/**
 * @author daizd
 */
public class JavascriptGroupChat
{
	TChatMsgDAO tChatMsgDAO;
	GlobalEnvDAO globalEnvDAO;
	
    public void settChatMsgDAO(TChatMsgDAO tChatMsgDAO) {
		this.tChatMsgDAO = tChatMsgDAO;
	}

	public void setGlobalEnvDAO(GlobalEnvDAO globalEnvDAO) {
		this.globalEnvDAO = globalEnvDAO;
	}

	/**
     * @param text The new message text to add
     */
    public void addMessage(String text)
    {
        if (text != null && text.trim().length() > 0)
        {       
        	WebContext ctx = WebContextFactory.get();
//        	ctx.getSession();
//        	ctx.getHttpServletRequest();
        	TAuthorityUser user = (TAuthorityUser) ctx.getSession()
        			.getAttribute(SessionConstants.LOGIN_USER);
        	TChatMsg msg = new TChatMsg( user.getNUid(), text);
        	tChatMsgDAO.save(msg);
            messages.addFirst(new Message(text,
            		globalEnvDAO.getSystemDBDate("%H:%i:%S"), 
            		user.getCUlogname(), 
            		user.getCUname(), 
            		user.getCUsex() ));
            
            while (messages.size() > 10)
            {
                messages.removeLast();
            }
        }

        Browser.withCurrentPage(new Runnable()
        {
            public void run()
            {
                ScriptSessions.addFunctionCall("receiveMessages", messages);
            }
        });
    }

    /**
     * The current set of messages
     */
    private final LinkedList<Message> messages = new LinkedList<Message>();
}
