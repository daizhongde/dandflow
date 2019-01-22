/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 * 
 * 
 * D:\daizd\Workspaces\MyEclipse 2015 CI\WS_Client\src>
 *   src目录下执行命令：  wsdl2java -p person.daizhongde.migration.spring.service.wsclient.cominterface http://10.1.249.109:1111/migration/ComInterFace?wsdl
 */

package person.daizhongde.migration.spring.service.wsclient;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import person.daizhongde.migration.spring.service.wsclient.cominterface.ComInterFace;
import person.daizhongde.migration.spring.service.wsclient.cominterface.ComInterFacePortType;
import person.daizhongde.migration.spring.service.wsclient.cominterface.LOADDATACONFIG;

public final class Client2 {

    private Client2() {
    	
    } 

    public static void main(String args[]) throws Exception {
//        ClientProxyFactoryBean factory = new ClientProxyFactoryBean();
//        if (args != null && args.length > 0 && !"".equals(args[0])) {
//            factory.setAddress(args[0]);
//        } else {
//            factory.setAddress("http://10.1.249.109:1111/migration/ComInterFace");
//        }
//        factory.getServiceFactory().setDataBinding(new AegisDatabinding());
//        ComInterFacePortType client = factory.create(ComInterFacePortType.class);
//        System.out.println("Invoke comInterFaceMethod()....");
    	ComInterFace factory = new ComInterFace();
    	ComInterFacePortType ws = factory.getComInterFace();
		
		//为客户端添加拦截器
		org.apache.cxf.endpoint.Client client = ClientProxy.getClient(ws);
		client.getOutInterceptors().add(new AddHeaderOutInterceptor("gzpostrans"
			, "copotedevelop")); 
		
//		//添加in拦截器
        client.getInInterceptors().add(new LoggingInInterceptor());
//		
//		//添加Out拦截器
        client.getOutInterceptors().add(new LoggingOutInterceptor());
        
        Client2 cc = new Client2();
        String response = ws.loadData( cc.assembleArg0() );
        
        System.out.println( response );
//        Document doc = client.getADocument();
//        Element e = (Element) doc.getFirstChild();
//        System.out.println(e.getTagName());
//        Text t = (Text) e.getFirstChild();
//        System.out.println(t);
    }
    
    public  LOADDATACONFIG assembleArg0(){
    	LOADDATACONFIG param1 = new LOADDATACONFIG();
/*        "taskId",
        "jobId",
        "cfgConn",
        "dbType",
        "delimiter",
        "inputPath",
        "dealPath",
        "successPath",
        "errorPath",
        "backupPath",
        "parallelNum",
        "loadfileOver"*/
        param1.setTaskId(
        		new JAXBElement<String>(
        				new QName("", "taskId"), 
        				String.class, 
        				"taskId-v"
        			) 
        		);
        param1.setJobId( 
        		new JAXBElement<String>(new QName("", "jobId"), String.class, "jobId-v") );
        
//        param1.setCfgConn( 
//        		new JAXBElement<String>(new QName("", "cfgConn"), String.class, "cfgConn-v") );
//        param1.setDbType( 
//        		new JAXBElement<String>(new QName("", "dbType"), String.class, "dbType-v") );
        param1.setDelimiter(
        		new JAXBElement<String>(new QName("", "delimiter"), String.class, "delimiter-v") );
        
        param1.setInputPath(
        		new JAXBElement<String>(new QName("", "inputPath"), String.class, "inputPath-v") );
        param1.setDealPath(
        		new JAXBElement<String>(new QName("", "dealPath"), String.class, "dealPath-v") );
        param1.setSuccessPath(
        		new JAXBElement<String>(new QName("", "successPath"), String.class, "successPath-v") );
        param1.setErrorPath(
        		new JAXBElement<String>(new QName("", "errorPath"), String.class, "errorPath-v") );
        param1.setBackupPath(
        		new JAXBElement<String>(new QName("", "backupPath"), String.class, "backupPath-v") );
        
        param1.setParallelNum(
        		new JAXBElement<String>(new QName("", "parallelNum"), String.class, "parallelNum-v") );
        param1.setLoadfileOver(
        		new JAXBElement<String>(new QName("", "loadfileOver"), String.class, "loadfileOver-v") );
       
        return param1;
    }

}
