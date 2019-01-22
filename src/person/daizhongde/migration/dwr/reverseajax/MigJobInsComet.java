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

import java.util.LinkedList;

import org.directwebremoting.Browser;
import org.directwebremoting.ScriptSessions;

import person.daizhongde.migration.hibernate.dao.MigJobProcessDAO;

/**
 * @author daizd 
 */
public class MigJobInsComet
{
	MigJobProcessDAO dataDAO;
	
	public void setDataDAO(MigJobProcessDAO dataDAO) {
		this.dataDAO = dataDAO;
	}
	/**
     * @param text The new message text to add
     */
    public void sendInsId(final String insId, final String currentJobId )
    {
        if (insId != null && !insId.trim().equalsIgnoreCase("")
        		&& currentJobId != null && !currentJobId.trim().equalsIgnoreCase(""))
        {

        }

        Browser.withCurrentPage(new Runnable()
        {
            @SuppressWarnings("unchecked")
			public void run()
            {
//            	while(true){
            		nodes.clear();
                	nodes.addAll(dataDAO.findByProperty2(insId, currentJobId ));
                	try {
						Thread.sleep(5*1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                    ScriptSessions.addFunctionCall("receiveMessages", nodes);
//            	}
            }
        });
    }

    /**
     * The current set of messages
     */
    private final LinkedList<Node> nodes = new LinkedList<Node>();
}
