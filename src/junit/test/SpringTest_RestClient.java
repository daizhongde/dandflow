package junit.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import person.daizhongde.virtue.util.test.Printer;

import person.daizhongde.migration.hibernate.pojo.MigComIns;
import person.daizhongde.migration.hibernate.pojo.MigComInsId;
import person.daizhongde.migration.spring.service.wsclient.MigWSClientService;

public class SpringTest_RestClient {

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

		MigWSClientService service = (MigWSClientService)ctx.getBean("migWsClientService");
		
		System.out.println("3");		
		List<MigComIns> result = new ArrayList<MigComIns>();
		MigComIns e1 = new MigComIns();
		MigComInsId id1 = new MigComInsId("jobInsId", "006", 1);
		e1.setId(id1);
		e1.setParaName("host-ip");
		e1.setParaValue("10.158.240.7:22");
		result.add(e1);
		
		MigComIns e2 = new MigComIns();
		MigComInsId id2 = new MigComInsId("jobInsId", "006", 2);
		e2.setId(id2);
		e2.setParaName("host-conn");
		e2.setParaValue("hadoopadmin/hadoopadmin123#");
		result.add(e2);
		
		MigComIns e3 = new MigComIns();
		MigComInsId id3 = new MigComInsId("jobInsId", "006", 3);
		e3.setId(id3);
		e3.setParaName("command");
		e3.setParaValue("mysql -s -h10.158.240.7 -P3306 -uroot -p'hadoopadmin123!@#' tool -e 'select count(*) from t_authority_user'");
		result.add(e3);
		
		MigComIns e4 = new MigComIns();
		MigComInsId id4 = new MigComInsId("jobInsId", "006", 4);
		e4.setId(id4);
		e4.setParaName("iswait");
		e4.setParaValue("0");
		result.add(e4);
		
		try{
			JSONObject ret = service.invoke(result, "JB001", "JINS001", 8, "TK001", "1", "con006");
			Printer.printJSON(ret);
		}catch(Exception e){
			e.printStackTrace();
			//String mml=e.getLocalizedMessage();
			//System.out.println(mml);
		}
		System.out.println("4");
		
//	  
		ctx.close();
	}

}
