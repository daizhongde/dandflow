package person.daizhongde.migration.hibernate.dao;

import java.util.List;

import person.daizhongde.virtue.dao.SpringHibernateDao;

import person.daizhongde.migration.hibernate.pojo.MigInsQuartz;

public interface MigInsQuartzDAO extends SpringHibernateDao{

	// property constants
	public static final String CASE_ID = "caseId";
	public static final String JOB_NAME = "jobName";
	public static final String JOB_GROUP = "jobGroup";
	public static final String CRON_EXPRESSION = "cronExpression";
	public static final String BEAN_CLASS = "beanClass";
	public static final String METHOD_NAME = "methodName";

	public abstract void save(MigInsQuartz transientInstance);
	public abstract void update(MigInsQuartz transientInstance);
	
	public abstract void delete(MigInsQuartz persistentInstance);

	public abstract MigInsQuartz findById(java.lang.Integer id);

	public abstract List findByExample(MigInsQuartz instance);

	public abstract List findByProperty(String propertyName, Object value);

	public abstract List findByCaseId(Object caseId);

	public abstract List findByJobName(Object jobName);

	public abstract List findByJobGroup(Object jobGroup);

	public abstract List findByCronExpression(Object cronExpression);

	public abstract List findByBeanClass(Object beanClass);

	public abstract List findByMethodName(Object methodName);

	public abstract List findAll();

	public abstract MigInsQuartz merge(MigInsQuartz detachedInstance);

	public abstract void attachDirty(MigInsQuartz instance);

	public abstract void attachClean(MigInsQuartz instance);

}