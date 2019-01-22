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

/**
 * A POJO that represents a typed message
 * @author daizd
 */
public class Message
{
    /**
     * @param newtext the new message text
     */
    public Message(String newtext)
    {
        text = newtext;

        if (text.length() > 256)
        {
            text = text.substring(0, 256);
        }
    }
    /**
     * @param newtext the new message text
     */
    public Message(String newtext, String newtime, 
    		String newuserlogname, String newusername, String newsex)
    {
        text = newtext;
        time = newtime;
        userlogname = newuserlogname;
        username = newusername;
        sex = newsex;
        
        if (text.length() > 256)
        {
            text = text.substring(0, 256);
        }
    }
    /**
     * @return the message id
     */
    public long getId()
    {
        return id;
    }

    /**
     * @return the message itself
     */
    public String getText()
    {
        return text;
    }

    /**
     * When the message was created
     */
    private long id = System.currentTimeMillis();

    /**
     * The text of the message
     */
    private String text;
    
    /**
     * time
     */
    private String time;
    
    /**
     * logname
     */
    private String userlogname;
    /**
     * username
     */
    private String username;
    private String sex;

	public String getUserlogname() {
		return userlogname;
	}

	public String getUsername() {
		return username;
	}

	public String getTime() {
		return time;
	}
	public String getSex() {
		return sex;
	}
    
}
