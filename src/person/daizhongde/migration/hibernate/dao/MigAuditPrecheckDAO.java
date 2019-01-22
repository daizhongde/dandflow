package person.daizhongde.migration.hibernate.dao;

import java.util.List;

import person.daizhongde.virtue.dao.SpringHibernateDao;

import person.daizhongde.migration.hibernate.pojo.MigAuditPrecheck;

public interface MigAuditPrecheckDAO extends SpringHibernateDao {

	//property constants
	public static final String AUDIT_ENV = "auditEnv";
	public static final String AUDIT_TYPE = "auditType";
	public static final String AUDIT_MODE = "auditMode";
	public static final String AUDIT_OBJECT = "auditObject";
	public static final String DRYRUN_FRONT = "dryrunFront";
	public static final String DRYRUN_BACK = "dryrunBack";
	public static final String COUNT_FRONT = "countFront";
	public static final String COUNT_BACK = "countBack";
	public static final String DIFF_RATIO = "diffRatio";
	public static final String AUDIT_REMARK = "auditRemark";

	public abstract void save(MigAuditPrecheck transientInstance);

	public abstract void delete(MigAuditPrecheck persistentInstance);

	public abstract MigAuditPrecheck findById(java.lang.Integer id);

	public abstract List findByExample(MigAuditPrecheck instance);

	public abstract List findByProperty(String propertyName, Object value);

	public abstract List findByAuditEnv(Object auditEnv);

	public abstract List findByAuditType(Object auditType);

	public abstract List findByAuditMode(Object auditMode);

	public abstract List findByAuditObject(Object auditObject);

	public abstract List findByDryrunFront(Object dryrunFront);

	public abstract List findByDryrunBack(Object dryrunBack);

	public abstract List findByCountFront(Object countFront);

	public abstract List findByCountBack(Object countBack);

	public abstract List findByDiffRatio(Object diffRatio);

	public abstract List findByAuditRemark(Object auditRemark);

	public abstract List findAll();

	public abstract MigAuditPrecheck merge(MigAuditPrecheck detachedInstance);

	public abstract void attachDirty(MigAuditPrecheck instance);

	public abstract void attachClean(MigAuditPrecheck instance);

}