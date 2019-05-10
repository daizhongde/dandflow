package junit.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import person.daizhongde.authority.hibernate.pojo.TAuthorityUser;
import person.daizhongde.migration.constant.JobLockState;
import person.daizhongde.migration.hibernate.dao.MigJobProcessDAO;
import person.daizhongde.migration.spring.service.MigJobInfoService;
import person.daizhongde.migration.spring.service.MigJobProcessService;
import person.daizhongde.migration.spring.service.MigTaskConfigService;
import person.daizhongde.migration.spring.service.PubService;
import person.daizhongde.migration.spring.service.MigTaskInfoService;
import person.daizhongde.migration.spring.service.wsclient.MigWSClientService;

public class SpringTest1 {

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

//		MigWSClientService service = (MigWSClientService)ctx.getBean("migWsClientService");
//		MigTaskConfigService service = (MigTaskConfigService)ctx.getBean("migTaskConfigService");
//		MigJobProcessService service = (MigJobProcessService)ctx.getBean("migJobProcessService");
		MigJobProcessDAO dao = (MigJobProcessDAO)ctx.getBean("migJobProcessDAO");
		
		System.out.println("3");		
		TAuthorityUser user = new TAuthorityUser();
		user.setCUname("daizd");
		try{
//			service.removeTask("FF00000062");
//			service.monitorJob("100", "100", user);
//			service.invoke("1111", "1");
//			service.compileJob("2", user);
//		TAuthorityUser user1 = new TAuthorityUser();
//		user1.setCUname("mm");
//		Map<Integer,String> condition = new HashMap<Integer,String>(2);
//		condition.put(1, "jiangsh");
//		condition.put(2, "shjiang");
			System.out.println("##########");
			int num = dao.find2_4recursive1("IS00000520","J60300701");
//		String bb = service.addJobandRelRetId("jiangsh", "S","100,100","1","con001",condition,"com001",user1);
		System.out.println("------------num:"+num);
		}catch(Exception e){
			e.printStackTrace();
			//String mml=e.getLocalizedMessage();
			//System.out.println(mml);
		}
		System.out.println("4");
		
//		 try {
//	            Thread.sleep(40 * 60 * 1000);
//	        } catch (InterruptedException e) {
//	            e.printStackTrace();  
//	     }
//	  
		ctx.close();
	}

}
