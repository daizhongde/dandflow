package person.daizhongde.migration.hibernate.dao;

import java.util.List;
import java.util.Map;

import person.daizhongde.virtue.dao.SpringHibernateDao;

import person.daizhongde.migration.hibernate.pojo.MigCodedetailDefine;

public interface MigCodedetailDefineDAO extends SpringHibernateDao{

	public abstract List queryListByInfo(MigCodedetailDefine instance);
	
	public abstract void updateById(Map<String,String> paras);
	public abstract void deleteById(String para);

	public abstract void save(MigCodedetailDefine transientInstance);

	public abstract void delete(MigCodedetailDefine persistentInstance);

	public abstract MigCodedetailDefine findById(int id);

	public abstract List findByExample(MigCodedetailDefine instance);

	public abstract List findByProperty(String propertyName, Object value);

	public abstract List findAll();

	public abstract MigCodedetailDefine merge(
			MigCodedetailDefine detachedInstance);

	public abstract void attachDirty(MigCodedetailDefine instance);

	public abstract void attachClean(MigCodedetailDefine instance);

}