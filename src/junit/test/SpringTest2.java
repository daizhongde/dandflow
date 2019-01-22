package junit.test;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

//import net.sf.jasperreports.engine.JRException;
//import net.sf.jasperreports.engine.JasperFillManager;








import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import person.daizhongde.migration.spring.service.wsclient.cominterface.AUDITDATACONFIG;
import person.daizhongde.migration.spring.service.wsclient.cominterface.ComInterFacePortType;
import person.daizhongde.migration.spring.service.wsclient.cominterface.EXCUTESQLCONFIG;
import person.daizhongde.migration.spring.service.wsclient.cominterface.LOADDATACONFIG;
import person.daizhongde.migration.spring.service.wsclient.cominterface.OUTDATABASECONFIG;

//import com.copote.authority.util.SpringConnection;

public class SpringTest2 { 

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("前置 针对所有测试，只执行一次，且必须为static void ");
	}
	@AfterClass
	public static void setUpAfterClass() throws Exception {
		System.out.println("后置 针对所有测试，只执行一次，且必须为static void ");
	}
	@Before public void beforeTestCase(){
		System.out.println("前置 用例执行前的环境处理");
	}
	@After public void afterTestCase(){
		System.out.println("后置 用例执行后的环境处理");
	}
	@Test public void instanceSpring(){
		System.out.println("1");
		AbstractApplicationContext ctx = 
				new ClassPathXmlApplicationContext(
						new String[]{"applicationContext.xml"}
				);
		//(new String[]{"beans.xml"});
		System.out.println("2"); 
		ComInterFacePortType service = (ComInterFacePortType)ctx.getBean("migWsClient");
		System.out.println("3");
		
//		String response = service.loadData( assembleArg0() );
		String response = service.executeSql(assembleArg0() );
//		String response = service.audit(assembleArg0() );
//		String response = service.outDataBase(assembleArg0());
		System.out.println("response:"+response);
//		System.exit(0);
		System.out.println("4");
		ctx.close();
	}
	
//	    public  LOADDATACONFIG assembleArg0(){
//	    	LOADDATACONFIG param1 = new LOADDATACONFIG();
//	/*        "taskId",
//	        "jobId",
//	        "cfgConn",
//	        "dbType",
//	        "delimiter",
//	        "inputPath",
//	        "dealPath",
//	        "successPath",
//	        "errorPath",
//	        "backupPath",
//	        "parallelNum",
//	        "loadfileOver"*/
//	        param1.setTaskId(
//	        		new JAXBElement<String>(
//	        				new QName("", "task-id"), 
//	        				String.class, 
//	        				"taskId-v"
//	        			) 
//	        		);
//	        param1.setJobId( 
//	        		new JAXBElement<String>(new QName("", "job-id"), String.class, "jobId-v") );
//	        
//	        param1.setCfgConn( 
//	        		new JAXBElement<String>(new QName("", "cfg-conn"), String.class, "cfgConn-v") );
//	        param1.setDbType( 
//	        		new JAXBElement<String>(new QName("", "db-type"), String.class, "dbType-v") );
//	        param1.setDelimiter(
//	        		new JAXBElement<String>(new QName("", "delimiter"), String.class, "delimiter-v") );
//	        
//	        param1.setInputPath(
//	        		new JAXBElement<String>(new QName("", "input-path"), String.class, "inputPath-v") );
//	        param1.setDealPath(
//	        		new JAXBElement<String>(new QName("", "deal-path"), String.class, "dealPath-v") );
//	        param1.setSuccessPath(
//	        		new JAXBElement<String>(new QName("", "success-path"), String.class, "successPath-v") );
//	        param1.setErrorPath(
//	        		new JAXBElement<String>(new QName("", "error-path"), String.class, "errorPath-v") );
//	        param1.setBackupPath(
//	        		new JAXBElement<String>(new QName("", "backup-path"), String.class, "backupPath-v") );
//	        
//	        param1.setParallelNum(
//	        		new JAXBElement<String>(new QName("", "parallel-num"), String.class, "parallelNum-v") );
//	        param1.setLoadfileOver(
//	        		new JAXBElement<String>(new QName("", "loadfile-over"), String.class, "loadfileOver-v") );
//	       
//	        return param1;
//	    }
//	    


	public EXCUTESQLCONFIG assembleArg0(){ 
		 EXCUTESQLCONFIG param1 = new EXCUTESQLCONFIG();
		 /*  "taskId",
    "jobId",
    "dstConn",
    "dbType",
    "execSql",
    "sqlPara",
    "parallelNum"*/
        param1.setTaskId(
        		new JAXBElement<String>(new QName("", "task-id"),String.class,"taskId-v") );
        param1.setJobId( 
        		new JAXBElement<String>(new QName("", "job-id"), String.class, "jobId-v") );
        
        param1.setDstConn( 
        		new JAXBElement<String>(new QName("", "dst-conn"), String.class, "10.1.249.100|root|easeaseas|test") );
//        param1.setDbType( 
//        		new JAXBElement<String>(new QName("", "db-type"), String.class, "1") );
        param1.setExecSql(
        		new JAXBElement<String>(new QName("", "exec-sql"), String.class, "create table jiangsh1_test_$year$month_$id (mm char(1));") );
        
        param1.setComPara(
        		new JAXBElement<String>(new QName("", "com-para"), String.class, "month=[01..04];id=[1,2];year=[2012..2015]") );
      
        param1.setParallelNum(
        		new JAXBElement<String>(new QName("", "parallel-num"), String.class, "1") );
     
        return param1;
    }
	
//	public  AUDITDATACONFIG assembleArg0(){ 
//		AUDITDATACONFIG param1 = new AUDITDATACONFIG();
//		 /*  "taskId",
//   "jobId",
//   "dstConn",
//   "dbType",
//   "execSql",
//   "sqlPara",
//   "parallelNum"*/
//       param1.setTaskId(
//       		new JAXBElement<String>(new QName("", "task-id"),String.class,"taskId-v") );
//       param1.setJobId( 
//       		new JAXBElement<String>(new QName("", "job-id"), String.class, "jobId-v") );
//       
//       param1.setCfgConn( 
//       		new JAXBElement<String>(new QName("", "cfg-conn"), String.class, "10.1.249.100|root|easeaseas|tool") );
//       param1.setDbType( 
//       		new JAXBElement<String>(new QName("", "db-type"), String.class, "1") );
//       param1.setSqlList(
//       		new JAXBElement<String>(new QName("", "sql-list"), String.class, "select lock_status from tool.mig_job_info_bak;") );
//       
//       param1.setStdValue(
//       		new JAXBElement<String>(new QName("", "std-value"), String.class, "3;") );
//       
//       param1.setOpt(
//          		new JAXBElement<String>(new QName("", "opt"), String.class, "<;") );
//
//       param1.setParallelNum(
//       		new JAXBElement<String>(new QName("", "parallel-num"), String.class, "1") );
//    
//       return param1;
//   }
   
//		public  OUTDATABASECONFIG assembleArg0(){ 
//			OUTDATABASECONFIG param1 = new OUTDATABASECONFIG();
//		 /*  "taskId",
//	"jobId",
//	"dstConn",
//	"dbType",
//	"execSql",
//	"sqlPara",
//	"parallelNum"*/
//	   param1.setTaskId(
//	   		new JAXBElement<String>(new QName("", "task-id"),String.class,"taskId-v") );
//	   param1.setJobId( 
//	   		new JAXBElement<String>(new QName("", "job-id"), String.class, "jobId-v") );
//	   
//	   param1.setCfgConn( 
//	   		new JAXBElement<String>(new QName("", "cfg-conn"), String.class, "10.1.249.100|root|easeaseas|tool") );
//	   param1.setDbType( 
//	   		new JAXBElement<String>(new QName("", "db-type"), String.class, "1") );
//	   param1.setGroup(
//	   		new JAXBElement<String>(new QName("", "group"), String.class, "1") );
//	   
//	   param1.setParallelNum(
//	   		new JAXBElement<String>(new QName("", "parallel-num"), String.class, "1") );
//	
//	   return param1;
//	}
}
