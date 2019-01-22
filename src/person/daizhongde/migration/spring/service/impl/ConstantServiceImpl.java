package person.daizhongde.migration.spring.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import person.daizhongde.migration.spring.service.ConstantService;

import person.daizhongde.virtue.constant.INIT;
import person.daizhongde.virtue.dao.SpringHibernateDao;

public class ConstantServiceImpl implements ConstantService {
	
	private SpringHibernateDao virtueDAO;

	@Override
	public Object dfindENV() {
		Map<String, Map> env = new HashMap(2);
		Map<String, String> envC = new HashMap(2);
//		envC.put("DIP", INIT.connextion_ip);
//		envC.put("PORT", INIT.connextion_port);
		
		Map<String, String> envR = new HashMap(2);
//		envR.put("DIP", INIT.retail_ip);
//		envR.put("PORT", INIT.retail_port);
		
		env.put("C",envC);
		env.put("R",envR);
		return env;
	}
	
	public SpringHibernateDao getVirtueDAO() {
		return virtueDAO;
	}

	public void setVirtueDAO(SpringHibernateDao virtueDAO) {
		this.virtueDAO = virtueDAO;
	}

}
