package person.daizhongde.migration.hibernate.dao;

import java.util.List;

import person.daizhongde.virtue.dao.SpringHibernateDao;

import person.daizhongde.migration.hibernate.pojo.MigAuditfSub;

public interface MigAuditfSubDAO extends SpringHibernateDao {

	// property constants
	public static final String FAUDIT_SRC_FIELD = "fauditSrcField";
	public static final String FAUDIT_DST_FIELD = "fauditDstField";
	public static final String FAUDIT_ISKEY = "fauditIskey";
	public static final String FAUDIT_OPT = "fauditOpt";
	public static final String FAUDIT_STATUS = "fauditStatus";

	public abstract void save(MigAuditfSub transientInstance);

	public abstract void delete(MigAuditfSub persistentInstance);

	public abstract MigAuditfSub findById(
			person.daizhongde.migration.hibernate.pojo.MigAuditfSubId id);

	public abstract List findByExample(MigAuditfSub instance);

	public abstract List findByProperty(String propertyName, Object value);

	public abstract List findByFauditSrcField(Object fauditSrcField);

	public abstract List findByFauditDstField(Object fauditDstField);

	public abstract List findByFauditIskey(Object fauditIskey);

	public abstract List findByFauditOpt(Object fauditOpt);

	public abstract List findByFauditStatus(Object fauditStatus);

	public abstract List findAll();

	public abstract MigAuditfSub merge(MigAuditfSub detachedInstance);

	public abstract void attachDirty(MigAuditfSub instance);

	public abstract void attachClean(MigAuditfSub instance);

}