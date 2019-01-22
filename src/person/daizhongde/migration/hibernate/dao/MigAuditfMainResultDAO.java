package person.daizhongde.migration.hibernate.dao;

import java.util.List;

import person.daizhongde.virtue.dao.SpringHibernateDao;

import person.daizhongde.migration.hibernate.pojo.MigAuditfMainResult;

public interface MigAuditfMainResultDAO extends SpringHibernateDao {

	//property constants
	public static final String FARES_SRC_COUNT = "faresSrcCount";
	public static final String FARES_DST_COUNT = "faresDstCount";
	public static final String FARES_SRC_PASSCNT = "faresSrcPasscnt";
	public static final String FARES_KEY_PASSCNT = "faresKeyPasscnt";
	public static final String FARES_SRC_MORE = "faresSrcMore";
	public static final String FARES_DST_MORE = "faresDstMore";
	public static final String FARES_KEY_UNMATCH = "faresKeyUnmatch";
	public static final String FARES_ELSE_UNMATCH = "faresElseUnmatch";

	public abstract void save(MigAuditfMainResult transientInstance);

	public abstract void delete(MigAuditfMainResult persistentInstance);

	public abstract MigAuditfMainResult findById(
			person.daizhongde.migration.hibernate.pojo.MigAuditfMainResultId id);

	public abstract List findByExample(MigAuditfMainResult instance);

	public abstract List findByProperty(String propertyName, Object value);

	public abstract List findByFaresSrcCount(Object faresSrcCount);

	public abstract List findByFaresDstCount(Object faresDstCount);

	public abstract List findByFaresSrcPasscnt(Object faresSrcPasscnt);

	public abstract List findByFaresKeyPasscnt(Object faresKeyPasscnt);

	public abstract List findByFaresSrcMore(Object faresSrcMore);

	public abstract List findByFaresDstMore(Object faresDstMore);

	public abstract List findByFaresKeyUnmatch(Object faresKeyUnmatch);

	public abstract List findByFaresElseUnmatch(Object faresElseUnmatch);

	public abstract List findAll();

	public abstract MigAuditfMainResult merge(
			MigAuditfMainResult detachedInstance);

	public abstract void attachDirty(MigAuditfMainResult instance);

	public abstract void attachClean(MigAuditfMainResult instance);

}