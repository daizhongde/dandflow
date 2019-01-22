package person.daizhongde.migration.hibernate.dao;

import java.util.List;

import person.daizhongde.virtue.dao.SpringHibernateDao;

import person.daizhongde.migration.hibernate.pojo.MigComInfo;
import person.daizhongde.migration.hibernate.pojo.MigComIns;

public interface MigComInsDAO extends SpringHibernateDao{

	//property constants
	public static final String JOB_INS_ID = "jobInsId";
	public static final String PARA_VALUE = "paraValue";

	public abstract void updateParaValue(String paraValue, String jobInsId, String comId, int paraId );
	
	public List<MigComIns> getCominsByJobInsIdAndComId(String jobInsId, String comId);
	/**
	 * not used
	 * @param comId
	 */
	public abstract void deleteCominsByComId(String comId);
	public abstract void deleteComins(String jobInsId);
	public abstract void deleteComins(List<String> jobInsId);
	
	public abstract void save(MigComIns transientInstance);

	public abstract void delete(MigComIns persistentInstance);

	public abstract MigComIns findById(
			person.daizhongde.migration.hibernate.pojo.MigComInsId id);

	public abstract List findByExample(MigComIns instance);

	public abstract List findByProperty(String propertyName, Object value);

	public abstract List findByJobInsId(Object jobInsId);

	public abstract List findByParaValue(Object paraValue);

	public abstract List findAll();

	public abstract MigComIns merge(MigComIns detachedInstance);

	public abstract void attachDirty(MigComIns instance);

	public abstract void attachClean(MigComIns instance);

}