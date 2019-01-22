package person.daizhongde.migration.hibernate.dao;

import java.util.List;

import person.daizhongde.virtue.dao.SpringHibernateDao;

import person.daizhongde.migration.hibernate.pojo.MigDataPrecheck;

public interface MigDataPrecheckDAO extends SpringHibernateDao {

	//property constants
	public static final String DRYRUN_ID = "dryrunId";
	public static final String CHECK_ENV = "checkEnv";
	public static final String CHECK_TYPE = "checkType";
	public static final String CHECK_OBJECT = "checkObject";
	public static final String CHECK_COUNT = "checkCount";
	public static final String CHECK_FIELD = "checkField";
	public static final String CHECK_REMARK = "checkRemark";

	public abstract void save(MigDataPrecheck transientInstance);

	public abstract void delete(MigDataPrecheck persistentInstance);

	public abstract MigDataPrecheck findById(java.lang.String id);

	public abstract List findByExample(MigDataPrecheck instance);

	public abstract List findByProperty(String propertyName, Object value);

	public abstract List findByDryrunId(Object dryrunId);

	public abstract List findByCheckEnv(Object checkEnv);

	public abstract List findByCheckType(Object checkType);

	public abstract List findByCheckObject(Object checkObject);

	public abstract List findByCheckCount(Object checkCount);

	public abstract List findByCheckField(Object checkField);

	public abstract List findByCheckRemark(Object checkRemark);

	public abstract List findAll();

	public abstract MigDataPrecheck merge(MigDataPrecheck detachedInstance);

	public abstract void attachDirty(MigDataPrecheck instance);

	public abstract void attachClean(MigDataPrecheck instance);

}