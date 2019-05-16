package junit.test;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import person.daizhongde.authority.hibernate.pojo.TAuthorityUser;
import person.daizhongde.migration.spring.service.imp.Excel2Email;

public class SpringTest54excel2email {

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
//		MigJobProcessDAO dao = (MigJobProcessDAO)ctx.getBean("migJobProcessDAO");
		Excel2Email toEmail = (Excel2Email)ctx.getBean("excel2Email");
		
		try {
			System.out.println("11");
			TAuthorityUser user = new TAuthorityUser();
			user.setCUemail("daizhongde@copote.com");
			user.setCUcip("DZd123456");
			user.setCUname("戴忠德");
			
			toEmail.notxsendEmailByExcel("D:\\Java项目\\copote_oa\\软件开发部2019年4月工资.xlsx", 
					"软件开发部2019年4月工资.xlsx",
					"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",
					user);
//			toEmail.notxsendEmailByExcel("D:\\Java项目\\copote_oa\\软件开发部2018年4月工资.xls", 
//					"软件开发部2018年4月工资.xls",
//					"application/vnd.ms-excel",
//					user);
		} catch (Exception e) {
			System.out.println("2");	
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("3");	
		ctx.close();
	}

}
