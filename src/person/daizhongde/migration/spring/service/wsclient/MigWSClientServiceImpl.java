package person.daizhongde.migration.spring.service.wsclient;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

import person.daizhongde.virtue.util.test.Printer;

import person.daizhongde.migration.exception.BusinessException;
import person.daizhongde.migration.hibernate.dao.MigComInfoDAO;
import person.daizhongde.migration.hibernate.dao.MigControlInfoDAO;
import person.daizhongde.migration.hibernate.dao.MigTaskInfoDAO;
import person.daizhongde.migration.hibernate.pojo.MigComIns;
import person.daizhongde.migration.hibernate.pojo.MigControlInfo;
import person.daizhongde.migration.hibernate.pojo.MigControlTemplate;
import person.daizhongde.migration.hibernate.pojo.MigJobPara;
import person.daizhongde.migration.hibernate.pojo.MigTaskInfo;
import person.daizhongde.migration.spring.service.BusiMemoryService;
//import person.daizhongde.migration.spring.service.MigJobProcessService;
import person.daizhongde.migration.spring.service.wsclient.cominterface.AUDITDATACONFIG;
import person.daizhongde.migration.spring.service.wsclient.cominterface.CONSISTENCYAUDITCONFIG;
import person.daizhongde.migration.spring.service.wsclient.cominterface.CONTROLSIGNAL;
import person.daizhongde.migration.spring.service.wsclient.cominterface.ComInterFacePortType;
import person.daizhongde.migration.spring.service.wsclient.cominterface.EXCUTESQLCONFIG;
import person.daizhongde.migration.spring.service.wsclient.cominterface.EXELINUXBINCONFIG;
import person.daizhongde.migration.spring.service.wsclient.cominterface.FIELDAUDITCONFIG;
import person.daizhongde.migration.spring.service.wsclient.cominterface.LOADDATACONFIG;
import person.daizhongde.migration.spring.service.wsclient.cominterface.OUTDATABASECONFIG;
import person.daizhongde.migration.spring.service.wsclient.cominterface.OUTDATAFILECONFIG;
import person.daizhongde.migration.spring.service.wsclient.cominterface.SPLITCONFIG;

import net.sf.json.JSONObject;

public class MigWSClientServiceImpl implements MigWSClientService {

	private ComInterFacePortType migWsClient;
	private MigControlInfoDAO controlInfodataDAO;
	
	private MigComInfoDAO comInsDAO;
	private MigTaskInfoDAO taskInfoDAO;
	private BusiMemoryService busiMemoryService;
	
	public JSONObject invokeSingle( String jobId, String jobInsId, Integer dryrunid, String nodeId, int signal ){
		return this.controller(jobId, jobInsId, dryrunid, nodeId, signal);
	}
	
	public JSONObject invoke( List<MigComIns> result, String jobId, String jobInsId, 
			 Integer dryrunid, String nodeId, String isCheck, String controlId ){
		
//		Printer.printJSON(result);

		MigControlInfo ctrlInfo= controlInfodataDAO.findById( controlId );	

		switch(ctrlInfo.getControlName()){
		case "split_file":    return this.splitFile(result, jobId, jobInsId, dryrunid, nodeId, isCheck); 
		case "load_data":     return this.loadData(result, jobId, jobInsId, dryrunid, nodeId, isCheck); 
		case "out_data_file": return this.outDataFile(result, jobId, jobInsId, dryrunid, nodeId, isCheck); 
		case "out_data_base": return this.outDataBase(result, jobId, jobInsId, dryrunid, nodeId, isCheck); 
		case "excute_sql":    return this.executeSql(result, jobId, jobInsId, dryrunid, nodeId, isCheck); 
		case "excute_bin":    return this.exeLinuxBin(result, jobId, jobInsId, dryrunid, nodeId, isCheck); 
		case "legality_audit":        return this.legalityAudit(result, jobId, jobInsId, dryrunid, nodeId, isCheck);
		case "consistency_audit":     return this.consistencyAudit(result, jobId, jobInsId, dryrunid, nodeId, isCheck); 
		case "quality_audit":         return this.qualityAudit(result, jobId, jobInsId, dryrunid, nodeId, isCheck); 
		
//		case "control":       return this.controller(result, jobId, taskId); 
		default : return JSONObject.fromObject( "{success: false, msg :'程序内部错误!'}" );
		}
	};
	
	public JSONObject controller( String jobId, String jobInsId, Integer dryrunid, String nodeId, int signal ){
		String response = migWsClient.controller( assembleCONTROLSIGNAL( jobId, jobInsId, dryrunid, nodeId, signal ) );
		
		System.out.println("---------------------|"+response+"|-----------");
		return JSONObject.fromObject( response );
	}
	
	public JSONObject splitFile( List<MigComIns> result, String jobId, String jobInsId, Integer dryrunid, 
			String nodeId, String isCheck){
//		, int ws 
//		String response="";
//		if(ws==0){
//			response = migWsClient.splitFile( assembleSPLITCONFIG( result, jobId, jobInsId, dryrunid, nodeId, isCheck ) );
//		}else{
//			response = migWsClient.splitFile( assembleSPLITCONFIG( result, jobId, jobInsId, dryrunid, nodeId, isCheck ) );
//		}
		String response = migWsClient.splitFile( assembleSPLITCONFIG( result, jobId, jobInsId, dryrunid, nodeId, isCheck ) );
		System.out.println("---------------------|"+response+"|-----------");
		return JSONObject.fromObject( response );
	}
	public JSONObject loadData( List<MigComIns> result, String jobId, String jobInsId, Integer dryrunid, String nodeId, String isCheck ){
		String response = migWsClient.loadData( assembleLOADDATACONFIG( result, jobId, jobInsId, dryrunid, nodeId, isCheck  ) );
		System.out.println("---------------------|"+response+"|-----------");

		return JSONObject.fromObject( response );
	}
	
	public JSONObject outDataFile( List<MigComIns> result, String jobId, String jobInsId, Integer dryrunid, String nodeId, String isCheck ){
		
		String response = migWsClient.outDataFile( assembleOUTDATAFILECONFIG( result, jobId, jobInsId, dryrunid, nodeId, isCheck  ) );
		System.out.println("---------------------|"+response+"|-----------");

		return JSONObject.fromObject( response );
	}
	public JSONObject outDataBase( List<MigComIns> result, String jobId, String jobInsId, Integer dryrunid, String nodeId, String isCheck ){
		
		
		String response = migWsClient.outDataBase( assembleOUTDATABASECONFIG( result, jobId, jobInsId, dryrunid, nodeId, isCheck  ) );
		System.out.println("---------------------|"+response+"|-----------");
		
		return JSONObject.fromObject( response );
	}
	public JSONObject executeSql( List<MigComIns> result, String jobId, String jobInsId, Integer dryrunid, String nodeId, String isCheck ){
		
		String response = migWsClient.executeSql( assembleEXCUTESQLCONFIG( result, jobId, jobInsId, dryrunid, nodeId, isCheck  ) );
		System.out.println("---------------------|"+response+"|-----------");

		return JSONObject.fromObject( response );
	}
	public JSONObject exeLinuxBin( List<MigComIns> result, String jobId, String jobInsId, Integer dryrunid, String nodeId, String isCheck ){
		
		String response = migWsClient.exeLinuxBin( assembleEXELINUXBINCONFIG( result, jobId, jobInsId, dryrunid, nodeId, isCheck  ) );
		System.out.println("---------------------|"+response+"|-----------");
	
		return JSONObject.fromObject( response );
	}
	

//case "legality_audit":        return this.legalityAudit(result, jobId, jobInsId, nodeId, isCheck);
//case "quality_audit":         return this.qualityAudit(result, jobId, jobInsId, nodeId, isCheck); 
//case "consistency_audit":     return this.consistencyAudit(result, jobId, jobInsId, nodeId, isCheck); 

	public JSONObject legalityAudit( List<MigComIns> result, String jobId, String jobInsId, Integer dryrunid, String nodeId, String isCheck ){
		
		String response = migWsClient.legalityAudit( assembleAUDITDATACONFIG( result, jobId, jobInsId, dryrunid, nodeId, isCheck  ) );
		System.out.println("---------------------|"+response+"|-----------");

		return JSONObject.fromObject( response );
	}
	public JSONObject qualityAudit( List<MigComIns> result, String jobId, String jobInsId, Integer dryrunid, String nodeId, String isCheck ){
		
		String response = migWsClient.fieldAudit( assembleFIELDAUDITCONFIG( result, jobId, jobInsId, dryrunid, nodeId, isCheck  ) );
		System.out.println("---------------------|"+response+"|-----------");

		return JSONObject.fromObject( response );
	}
	public JSONObject consistencyAudit( List<MigComIns> result, String jobId, String jobInsId, Integer dryrunid, String nodeId, String isCheck ){
		
		String response = migWsClient.consistencyAudit( assembleCONSISTENCYAUDITCONFIG( result, jobId, jobInsId, dryrunid, nodeId, isCheck  ) );
		System.out.println("---------------------|"+response+"|-----------");

		return JSONObject.fromObject( response );
	}

	public CONTROLSIGNAL assembleCONTROLSIGNAL(String jobId, String jobInsId, Integer dryrunid, String nodeId, int signal  ){
		CONTROLSIGNAL param1 = new CONTROLSIGNAL();
        param1.setTaskId( 
        		new JAXBElement<String>(new QName("", "task-id"),  String.class,  nodeId )  );
        param1.setJobId(
        		new JAXBElement<String>(new QName("", "job-id"),  String.class,  jobId ) );
        param1.setJobInsId(
                		new JAXBElement<String>(new QName("", "job-ins-id"),  String.class,  jobInsId ) );
        param1.setDryrunId( dryrunid );
        param1.setTaskSignal( signal  );
        
        return param1;
	}
	
	public SPLITCONFIG assembleSPLITCONFIG( List<MigComIns> result, String jobId, String jobInsId, Integer dryrunid, String nodeId, String isCheck ){
		SPLITCONFIG param1 = new SPLITCONFIG();
        param1.setTaskId( 
        		new JAXBElement<String>(new QName("", "task-id"),  String.class,  nodeId )  );
        param1.setJobId(
        		new JAXBElement<String>(new QName("", "job-id"), String.class, jobId) );
        param1.setJobInsId(
        		new JAXBElement<String>(new QName("", "job-ins-id"), String.class, jobInsId) );
        param1.setDryrunId( dryrunid );
        param1.setIsCheck(
        		new JAXBElement<String>(new QName("", "isCheck"), String.class, isCheck) );
        
        for(MigComIns e : result){
        	String paramName = e.getParaName();//cfg-conn
        	JAXBElement paramValue = new JAXBElement<String>(new QName("", paramName ),  String.class,  e.getParaValue()   );
        	String methodName = getMethodName( paramName );//setCfgConn
        	
        	Object[] args = new Object[1];
			args[0] = paramValue;
			Class[] parameterTypes = new Class[1];
			parameterTypes[0] = JAXBElement.class;
			System.out.println("--"+paramName+"--"+methodName+"--"+e.getParaValue()+"--");
		    try {
		    	Method method = SPLITCONFIG.class.getMethod( methodName, parameterTypes );
		    	method.invoke( param1 , args );
			} catch (Exception ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
				throw new BusinessException("组装参数时出错！");
			}
		    
        }

        return param1;
	}

	/**
	 *  cfg-conn   ---->   setCfgConn
	 *  cfgconn    ---->   setCfgconn 
	 * @param label
	 * @return
	 */
	private String getMethodName( String label ){
		int i = label.indexOf("-");
		if(i == -1 ){
			i = label.indexOf("_");
		}
		if( i == -1 ){
			return "set"+Character.toUpperCase( label.charAt(0) ) + label.substring(1);
		}else{
			return "set"+Character.toUpperCase( label.charAt(0) ) + label.substring(1, i) + Character.toUpperCase( label.charAt(i+1) ) + label.substring( i+2 );
		}
	}
	
    private LOADDATACONFIG assembleLOADDATACONFIG( List<MigComIns> result, String jobId, String jobInsId, Integer dryrunid, String nodeId, String isCheck ){
    	//paramName: cfg-conn
    	//methodName: setCfgConn
    	LOADDATACONFIG param1 = new LOADDATACONFIG();
        
        param1.setTaskId( 
        		new JAXBElement<String>(new QName("", "task-id"),  String.class,  nodeId )  );
        param1.setJobId(
        		new JAXBElement<String>(new QName("", "job-id"), String.class, jobId) );
        param1.setJobInsId(
        		new JAXBElement<String>(new QName("", "job-ins-id"), String.class, jobInsId) );
        param1.setDryrunId( dryrunid );
        param1.setIsCheck(
        		new JAXBElement<String>(new QName("", "isCheck"), String.class, isCheck) );
         
        for(MigComIns e : result){
        	String paramName = e.getParaName();//cfg-conn
        	JAXBElement paramValue = new JAXBElement<String>(new QName("", paramName ),  String.class,  e.getParaValue()   );
        	String methodName = getMethodName( paramName );//setCfgConn
        	
        	Object[] args = new Object[1];
			args[0] = paramValue;
			Class[] parameterTypes = new Class[1];
			parameterTypes[0] = JAXBElement.class;
		    try {
		    	Method method = LOADDATACONFIG.class.getMethod( methodName, parameterTypes );
		    	method.invoke( param1 , args );
			} catch (Exception ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
				throw new BusinessException("组装参数时出错！");
			}
		    
        }
        return param1;
    }
    
    private OUTDATAFILECONFIG assembleOUTDATAFILECONFIG( List<MigComIns> result, String jobId, String jobInsId, Integer dryrunid, String nodeId, String isCheck ){
    	OUTDATAFILECONFIG param1 = new OUTDATAFILECONFIG();
        param1.setTaskId( 
        		new JAXBElement<String>(new QName("", "task-id"),  String.class,  nodeId )  );
        param1.setJobId(
        		new JAXBElement<String>(new QName("", "job-id"), String.class, jobId) );
        param1.setJobInsId(
        		new JAXBElement<String>(new QName("", "job-ins-id"), String.class, jobInsId) );
        param1.setDryrunId( dryrunid );
        param1.setIsCheck(
        		new JAXBElement<String>(new QName("", "isCheck"), String.class, isCheck) );
        
        for(MigComIns e : result){
        	String paramName = e.getParaName();//cfg-conn
        	JAXBElement paramValue = new JAXBElement<String>(new QName("", paramName ),  String.class,  e.getParaValue()   );
        	String methodName = getMethodName( paramName );//setCfgConn
//        	methodName = getMethodName( methodName );//setCfgConn
        	
        	Object[] args = new Object[1];
			args[0] = paramValue;
			Class[] parameterTypes = new Class[1];
			parameterTypes[0] = JAXBElement.class;
		    try {
		    	Method method = OUTDATAFILECONFIG.class.getMethod( methodName, parameterTypes );
		    	method.invoke( param1 , args );
			} catch (Exception ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
				throw new BusinessException("组装参数时出错！");
			}
		    
        }

        return param1;
	}
    
    private OUTDATABASECONFIG assembleOUTDATABASECONFIG( List<MigComIns> result, String jobId, String jobInsId, Integer dryrunid, String nodeId, String isCheck ){
    	OUTDATABASECONFIG param1 = new OUTDATABASECONFIG();
        param1.setTaskId( 
        		new JAXBElement<String>(new QName("", "task-id"),  String.class,  nodeId )  );
        param1.setJobId(
        		new JAXBElement<String>(new QName("", "job-id"), String.class, jobId) );
        param1.setJobInsId(
        		new JAXBElement<String>(new QName("", "job-ins-id"), String.class, jobInsId) );
        param1.setDryrunId( dryrunid );
        param1.setIsCheck(
        		new JAXBElement<String>(new QName("", "isCheck"), String.class, isCheck) );
        
        for(MigComIns e : result){
        	String paramName = e.getParaName();//cfg-conn
        	JAXBElement paramValue = new JAXBElement<String>(new QName("", paramName ),  String.class,  e.getParaValue()   );
        	String methodName = getMethodName( paramName );//setCfgConn
        	
        	Object[] args = new Object[1];
			args[0] = paramValue;
			Class[] parameterTypes = new Class[1];
			parameterTypes[0] = JAXBElement.class;
		    try {
		    	Method method = OUTDATABASECONFIG.class.getMethod( methodName, parameterTypes );
		    	method.invoke( param1 , args );
			} catch (Exception ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
				throw new BusinessException("组装参数时出错！");
			}
		    
        }

        return param1;
	}
    private EXCUTESQLCONFIG assembleEXCUTESQLCONFIG( List<MigComIns> result, String jobId, String jobInsId, Integer dryrunid, String nodeId, String isCheck ){
    	EXCUTESQLCONFIG param1 = new EXCUTESQLCONFIG();
        
        param1.setTaskId( 
        		new JAXBElement<String>(new QName("", "task-id"),  String.class,  nodeId )  );
        param1.setJobId(
        		new JAXBElement<String>(new QName("", "job-id"), String.class, jobId) );
        param1.setJobInsId(
        		new JAXBElement<String>(new QName("", "job-ins-id"), String.class, jobInsId) );
        param1.setDryrunId( dryrunid );
        param1.setIsCheck(
        		new JAXBElement<String>(new QName("", "isCheck"), String.class, isCheck) );
        
       for(MigComIns e : result){
       	String paramName = e.getParaName();//cfg-conn
       	JAXBElement<String> paramValue = new JAXBElement<String>(new QName("", paramName ),  String.class,  e.getParaValue()   );
       	String methodName = getMethodName( paramName );//setCfgConn
       	
       	Object[] args = new Object[1];
			args[0] = paramValue;
			@SuppressWarnings("rawtypes")
			Class[] parameterTypes = new Class[1];
			parameterTypes[0] = JAXBElement.class;
			
			System.out.println("paramName:"+paramName+", methodName:"+methodName+",value:"+e.getParaValue());
		    try {
		    	Method method = EXCUTESQLCONFIG.class.getMethod( methodName, parameterTypes );
		    	method.invoke( param1 , args );
			} catch (Exception ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
				throw new BusinessException("组装参数时出错！");
			}
		    
       }
       return param1;
	}
    
    private EXELINUXBINCONFIG assembleEXELINUXBINCONFIG( List<MigComIns> result, String jobId, String jobInsId, Integer dryrunid, String nodeId, String isCheck ){
    	EXELINUXBINCONFIG param1 = new EXELINUXBINCONFIG();
        
        param1.setTaskId( 
        		new JAXBElement<String>(new QName("", "task-id"),  String.class,  nodeId )  );
        param1.setJobId(
        		new JAXBElement<String>(new QName("", "job-id"), String.class, jobId) );
        param1.setJobInsId(
        		new JAXBElement<String>(new QName("", "job-ins-id"), String.class, jobInsId) );
        param1.setDryrunId( dryrunid );
        param1.setIsCheck(
        		new JAXBElement<String>(new QName("", "isCheck"), String.class, isCheck) );
        
          for(MigComIns e : result){
          	String paramName = e.getParaName();//cfg-conn
          	JAXBElement<String> paramValue = new JAXBElement<String>(new QName("", paramName ),  String.class,  e.getParaValue()   );
          	String methodName = getMethodName( paramName );//setCfgConn
          	
          	Object[] args = new Object[1];
   			args[0] = paramValue;
   			@SuppressWarnings("rawtypes")
   			Class[] parameterTypes = new Class[1];
   			parameterTypes[0] = JAXBElement.class;
   			
   			System.out.println("paramName:"+paramName+", methodName:"+methodName+",value:"+e.getParaValue());
   		    try {
   		    	Method method = EXELINUXBINCONFIG.class.getMethod( methodName, parameterTypes );
   		    	method.invoke( param1 , args );
   			} catch (Exception ex) {
   				// TODO Auto-generated catch block
   				ex.printStackTrace();
   				throw new BusinessException("组装参数时出错！");
   			}
   		    
          }

          return param1;
	}
	
    private AUDITDATACONFIG assembleAUDITDATACONFIG( List<MigComIns> result, String jobId, String jobInsId, Integer dryrunid, String nodeId, String isCheck ){
    	AUDITDATACONFIG param1 = new AUDITDATACONFIG();
        param1.setTaskId( 
        		new JAXBElement<String>(new QName("", "task-id"),  String.class,  nodeId )  );
        param1.setJobId(
        		new JAXBElement<String>(new QName("", "job-id"), String.class, jobId) );
        param1.setJobInsId(
        		new JAXBElement<String>(new QName("", "job-ins-id"), String.class, jobInsId) );
        param1.setDryrunId( dryrunid );
        param1.setIsCheck(
        		new JAXBElement<String>(new QName("", "isCheck"), String.class, isCheck) );
        for(MigComIns e : result){
        	String paramName = e.getParaName();//cfg-conn
        	JAXBElement paramValue = new JAXBElement<String>(new QName("", paramName ),  String.class,  e.getParaValue()   );
        	String methodName = getMethodName( paramName );//setCfgConn
        	
        	Object[] args = new Object[1];
			args[0] = paramValue;
			Class[] parameterTypes = new Class[1];
			parameterTypes[0] = JAXBElement.class;
		    try {
		    	Method method = AUDITDATACONFIG.class.getMethod( methodName, parameterTypes );
		    	method.invoke( param1 , args );
			} catch (Exception ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
				throw new BusinessException("组装参数时出错！");
			}
		    
        }

        return param1;
	}

    private FIELDAUDITCONFIG assembleFIELDAUDITCONFIG( List<MigComIns> result, String jobId, String jobInsId, Integer dryrunid, String nodeId, String isCheck ){
    	FIELDAUDITCONFIG param1 = new FIELDAUDITCONFIG();
        param1.setTaskId( 
        		new JAXBElement<String>(new QName("", "task-id"),  String.class,  nodeId )  );
        param1.setJobId(
        		new JAXBElement<String>(new QName("", "job-id"), String.class, jobId) );
        param1.setJobInsId(
        		new JAXBElement<String>(new QName("", "job-ins-id"), String.class, jobInsId) );
        param1.setDryrunId( dryrunid );
        param1.setIsCheck(
        		new JAXBElement<String>(new QName("", "isCheck"), String.class, isCheck) );
        for(MigComIns e : result){
        	String paramName = e.getParaName();//cfg-conn
        	JAXBElement paramValue = new JAXBElement<String>(new QName("", paramName ),  String.class,  e.getParaValue()   );
        	String methodName = getMethodName( paramName );//setCfgConn
        	
        	Object[] args = new Object[1];
			args[0] = paramValue;
			Class[] parameterTypes = new Class[1];
			parameterTypes[0] = JAXBElement.class;
		    try {
		    	Method method = FIELDAUDITCONFIG.class.getMethod( methodName, parameterTypes );
		    	method.invoke( param1 , args );
			} catch (Exception ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
				throw new BusinessException("组装参数时出错！");
			}
		    
        }

        return param1;
	}
    private CONSISTENCYAUDITCONFIG assembleCONSISTENCYAUDITCONFIG( List<MigComIns> result, String jobId, String jobInsId, Integer dryrunid, String nodeId, String isCheck ){
    	CONSISTENCYAUDITCONFIG param1 = new CONSISTENCYAUDITCONFIG();
        param1.setTaskId( 
        		new JAXBElement<String>(new QName("", "task-id"),  String.class,  nodeId )  );
        param1.setJobId(
        		new JAXBElement<String>(new QName("", "job-id"), String.class, jobId) );
        param1.setJobInsId(
        		new JAXBElement<String>(new QName("", "job-ins-id"), String.class, jobInsId) );
        param1.setDryrunId( dryrunid );
        param1.setIsCheck(
        		new JAXBElement<String>(new QName("", "isCheck"), String.class, isCheck) );
        for(MigComIns e : result){
        	String paramName = e.getParaName();//cfg-conn
        	JAXBElement paramValue = new JAXBElement<String>(new QName("", paramName ),  String.class,  e.getParaValue()   );
        	String methodName = getMethodName( paramName );//setCfgConn
        	
        	Object[] args = new Object[1];
			args[0] = paramValue;
			Class[] parameterTypes = new Class[1];
			parameterTypes[0] = JAXBElement.class;
		    try {
		    	Method method = CONSISTENCYAUDITCONFIG.class.getMethod( methodName, parameterTypes );
		    	method.invoke( param1 , args );
			} catch (Exception ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
				throw new BusinessException("组装参数时出错！");
			}
		    
        }

        return param1;
	}
    public void setMigWsClient(ComInterFacePortType migWsClient) {
		this.migWsClient = migWsClient;
	}

	public MigControlInfoDAO getControlInfodataDAO() {
		return controlInfodataDAO;
	}
	
	public void setControlInfodataDAO(MigControlInfoDAO controlInfodataDAO) {
		this.controlInfodataDAO = controlInfodataDAO;
	}

	public MigTaskInfoDAO getTaskInfoDAO() {
		return taskInfoDAO;
	}

	public void setTaskInfoDAO(MigTaskInfoDAO taskInfoDAO) {
		this.taskInfoDAO = taskInfoDAO;
	}

	public MigComInfoDAO getComInsDAO() {
		return comInsDAO;
	}

	public void setComInsDAO(MigComInfoDAO comInsDAO) {
		this.comInsDAO = comInsDAO;
	}
    
}
