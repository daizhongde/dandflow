package person.daizhongde.migration.spring.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import person.daizhongde.migration.constant.MIGINIT;
import person.daizhongde.migration.spring.service.ConstantService;

import person.daizhongde.virtue.constant.INIT;
import person.daizhongde.virtue.dao.SpringHibernateDao;

public class ConstantServiceImpl implements ConstantService {
	
	private SpringHibernateDao virtueDAO;

	@Override
	public Object dfindENV() {
		Map<String, Map<String, String>> env = new HashMap<String, Map<String, String>>(3);
		Map<String, String> envSIT = new HashMap<String, String>(3);
		envSIT.put("HIP", MIGINIT.sit_HIP);
		envSIT.put("MySQLPARAM", MIGINIT.sit_MySQLPARAM);
		envSIT.put("DBNAME", MIGINIT.sit_DBNAME);
		
		Map<String, String> envUAT = new HashMap<String, String>(3);
		envUAT.put("HIP", MIGINIT.uat_HIP);
		envUAT.put("MySQLPARAM", MIGINIT.uat_MySQLPARAM);
		envUAT.put("DBNAME", MIGINIT.uat_DBNAME);
		
		Map<String, String> envPROD = new HashMap<String, String>(3);
		envPROD.put("HIP", MIGINIT.prod_HIP);
		envPROD.put("MySQLPARAM", MIGINIT.prod_MySQLPARAM);
		envPROD.put("DBNAME", MIGINIT.prod_DBNAME);
		
		env.put("sit", envSIT);
		env.put("uat", envUAT);
		env.put("prod", envPROD);
		
		Map<String, String> envSRCTEST = new HashMap<String, String>(3);
		envSRCTEST.put("HIP", MIGINIT.src_test_HIP);
		envSRCTEST.put("OraclePARAM", MIGINIT.src_test_OraclePARAM);
		
		Map<String, String> envSRCPROD = new HashMap<String, String>(3);
		envSRCPROD.put("HIP", MIGINIT.src_prod_HIP);
		envSRCPROD.put("OraclePARAM", MIGINIT.src_prod_OraclePARAM);
		
		env.put("src_test", envSRCTEST);
		env.put("src_prod", envSRCPROD);
		return env;
	}
	
	public SpringHibernateDao getVirtueDAO() {
		return virtueDAO;
	}

	public void setVirtueDAO(SpringHibernateDao virtueDAO) {
		this.virtueDAO = virtueDAO;
	}

}
