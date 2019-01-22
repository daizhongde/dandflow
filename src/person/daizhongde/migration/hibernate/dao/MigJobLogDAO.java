package person.daizhongde.migration.hibernate.dao;

import java.util.List;

import person.daizhongde.virtue.dao.SpringHibernateDao;

import person.daizhongde.migration.hibernate.pojo.MigJobLog;

public interface MigJobLogDAO  extends SpringHibernateDao{

	// property constants
	public static final String TASK_ID = "taskId";
	public static final String LEVEL = "level";
	public static final String LOG_MSG = "logMsg";
	public static final String REMARK = "remark";

	public abstract void save(MigJobLog transientInstance);

	public abstract void delete(MigJobLog persistentInstance);
	public abstract void deleteByInsId(String jobInsId);
	public abstract void deleteByInsId(List<String> jobInsId);
	
	public abstract MigJobLog findById(java.lang.String id);

	public abstract List findByExample(MigJobLog instance);

	public abstract List findByProperty(String propertyName, Object value);

	public abstract List findByTaskId(Object taskId);

	public abstract List findByLevel(Object level);

	public abstract List findByLogMsg(Object logMsg);

	public abstract List findByRemark(Object remark);

	public abstract List findAll();

	public abstract MigJobLog merge(MigJobLog detachedInstance);

	public abstract void attachDirty(MigJobLog instance);

	public abstract void attachClean(MigJobLog instance);

}