/*
 * Copyright 2005 Joe Walker
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

import org.directwebremoting.Browser;
import org.directwebremoting.ScriptSessions;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;

import person.daizhongde.authority.constant.SessionConstants;
import person.daizhongde.authority.hibernate.pojo.TAuthorityUser;
import person.daizhongde.migration.spring.service.imp.Excel2Email;

/**
 * 导入工资excel并发送邮件时前台实时展示处理信息
 * @author daizd 
 */
public class SalaryImportSendMailMsg
{
	/**
     * @param text The new message text to add
     */
    public void sendDisplayflag(final boolean displayFlag  )
    {
        if ( !displayFlag )
        {
        	return;
        }
    	WebContext ctx = WebContextFactory.get();
    	TAuthorityUser user = (TAuthorityUser) ctx.getSession()
    			.getAttribute(SessionConstants.LOGIN_USER);
        Browser.withCurrentPage(new Runnable()
        {
            @SuppressWarnings("unchecked")
			public void run()
            {
            	String s = Excel2Email.msg.get( user.getCUemail() );
                ScriptSessions.addFunctionCall("receiveMessages", s );
            }
        });
    }
	/**
     * @param text The new message text to add
     */
    public void modifyMsg(String msg  )
    {
    	WebContext ctx = WebContextFactory.get();
    	TAuthorityUser user = (TAuthorityUser) ctx.getSession()
    			.getAttribute(SessionConstants.LOGIN_USER);
		Excel2Email.msg.put( user.getCUemail(), msg );
    }
	/**
     * @param text The new message text to add
     */
    public void clearMsg()
    {
    	WebContext ctx = WebContextFactory.get();
    	TAuthorityUser user = (TAuthorityUser) ctx.getSession()
    			.getAttribute(SessionConstants.LOGIN_USER);
		Excel2Email.msg.put( user.getCUemail(), "");
    }
}
