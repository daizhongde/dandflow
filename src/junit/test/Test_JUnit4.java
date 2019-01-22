package junit.test;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test_JUnit4 {

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
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext(new String[]{"/module/applicationContext-globalEnv.xml","applicationContext.xml"});
		
		//(new String[]{"beans.xml"});
		System.out.println("2"); 
//		SettingService dynamicEnvDAO = (SettingService)ctx.getBean("settingService");
		System.out.println("3");
//		System.out.println(dynamicEnvDAO.listSetting("政务中心公告类别")+"  hello");
		System.out.println("4");
		ctx.close();
	}

}
