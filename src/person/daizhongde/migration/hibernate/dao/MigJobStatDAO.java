package person.daizhongde.migration.hibernate.dao;

import java.util.List;
import java.util.Map;

import person.daizhongde.virtue.dao.SpringHibernateDao;

import person.daizhongde.migration.hibernate.pojo.MigJobStat;

public interface MigJobStatDAO extends SpringHibernateDao{

	//property constants
	public static final String JOB_ID = "jobId";
	public static final String JOB_INS_ID = "jobInsId";
	public static final String STATUS = "status";
	public static final String REMARK = "remark";

	public abstract void RN_WritelogSQL(Map condition);
	
	public abstract void save(MigJobStat transientInstance);

	public abstract void delete(MigJobStat persistentInstance);
	public abstract void deleteByInsId(String jobInsId);
	public abstract void deleteByInsId(List<String> jobInsId);
	
	
	public abstract MigJobStat findById(java.lang.String id);

	public abstract List findByExample(MigJobStat instance);

	public abstract List findByProperty(String propertyName, Object value);

	public abstract List findByJobId(Object jobId);

	public abstract List findByJobInsId(Object jobInsId);

	public abstract List findByStatus(Object status);

	public abstract List findByRemark(Object remark);

	public abstract List findAll();

	public abstract MigJobStat merge(MigJobStat detachedInstance);

	public abstract void attachDirty(MigJobStat instance);

	public abstract void attachClean(MigJobStat instance);

}