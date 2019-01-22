package junit.test;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import person.daizhongde.authority.hibernate.pojo.TAuthorityUser;
import person.daizhongde.migration.constant.TableName;
import person.daizhongde.migration.hibernate.dao.TPubSeqtableDAO;
import person.daizhongde.migration.spring.service.MigJobProcessService;
import person.daizhongde.migration.spring.service.PubService;

public class SpringTest3 {

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
	@Test public void instanceSpring() throws InterruptedException{
		System.out.println("1");
		AbstractApplicationContext ctx = 
				new ClassPathXmlApplicationContext(
						new String[]{"applicationContext.xml"}
				);
		
		System.out.println("2"); 
//		Properties  properties = System.getProperties();
//		properties.list(System.out);
		
		PubService service = (PubService)ctx.getBean("pubService");
//		TPubSeqtableDAO dao = (TPubSeqtableDAO)ctx.getBean("tPubSeqtableDAO");
		
//		System.out.println("3");
//		TAuthorityUser user = new TAuthorityUser();
//		user.setCUname("daizd");
		
		
//		int intId  = 22;
//		intId = dao.sqlQuerySequenceNEXTVAL2("mig_com_ins", false);
//		System.out.println("intId:"+intId );
		
		String sID = "";
		
		sID = service.get10ByteCode("NB", TableName.mig_com_ins );
		System.out.println("sID:"+sID );
		sID = service.get14ByteCode("PP", TableName.mig_control_info);
		System.out.println("sID:"+sID );
		
		System.out.println("4");
		
//		 try {
//	            Thread.sleep(4 * 60 * 1000);
//	        } catch (InterruptedException e) {
//	            e.printStackTrace();  
//	     }
	  
		ctx.close();
	}

}
