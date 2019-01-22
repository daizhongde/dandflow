package person.daizhongde.migration.hibernate.dao;

import java.util.List;

import person.daizhongde.virtue.dao.SpringHibernateDao;

import person.daizhongde.migration.hibernate.pojo.MigComInfo;

public interface MigComInfoDAO extends SpringHibernateDao {

	public abstract void updateCominfoById(String value, int Id,String comId);
	
	public abstract List getCominfoByComId(String id);
	
	public abstract void deleteCominfoByComId(String comId);
	
	public abstract void instanceComInfo(String jobInsId, String ComId);
	public abstract void instanceComInfo(String jobInsId, String ComId, List<MigComInfo> rows );
	
	public abstract void save(MigComInfo transientInstance);

	public abstract void delete(MigComInfo persistentInstance);

	public abstract MigComInfo findById(
			person.daizhongde.migration.hibernate.pojo.MigComInfoId id);

	public abstract List findByExample(MigComInfo instance);

	public abstract List findByProperty(String propertyName, Object value);

	public abstract List findAll();

	public abstract MigComInfo merge(MigComInfo detachedInstance);

	public abstract void attachDirty(MigComInfo instance);

	public abstract void attachClean(MigComInfo instance);

}