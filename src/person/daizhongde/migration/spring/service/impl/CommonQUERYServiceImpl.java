package person.daizhongde.migration.spring.service.impl;

import java.util.List;

import person.daizhongde.migration.spring.service.CommonQUERYService;

import person.daizhongde.virtue.assemble.sql.SQLAssembleQ;
import person.daizhongde.virtue.dao.SpringHibernateDao;
import person.daizhongde.virtue.util.test.Printer;

public class CommonQUERYServiceImpl implements CommonQUERYService {
	
	private SpringHibernateDao virtueDAO;

	@Override
	public long getTotal(SQLAssembleQ sqlA) {
//		Printer.printJSON(sqlA.getMap() );
		return Long.valueOf(
				virtueDAO.sqlQueryfindaValueByMap( sqlA.getCountSQL(), sqlA.getMap() 
			).toString());
	}
	
	@Override
	public List getRowsInMap(SQLAssembleQ sqlA) {
		return virtueDAO.sqlQuerylistAllByMap( sqlA.getSQL(), sqlA.getMap()  );
	}
	@Override
	public List getRowsInMap(SQLAssembleQ sqlA, int offset, int pageSize) {
		return virtueDAO.sqlQueryfindByPageByMap(sqlA.getSQL(), sqlA.getMap(),
				offset, pageSize);// 使用native数据量小
	}
	
	public SpringHibernateDao getVirtueDAO() {
		return virtueDAO;
	}

	public void setVirtueDAO(SpringHibernateDao virtueDAO) {
		this.virtueDAO = virtueDAO;
	}

}
