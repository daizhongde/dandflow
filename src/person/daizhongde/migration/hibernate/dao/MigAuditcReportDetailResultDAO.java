package person.daizhongde.migration.hibernate.dao;

import java.util.List;

import person.daizhongde.virtue.dao.SpringHibernateDao;

import person.daizhongde.migration.hibernate.pojo.MigAuditcReportDetailResult;

public interface MigAuditcReportDetailResultDAO extends SpringHibernateDao  {

	// property constants
	public static final String ENTITY = "entity";
	public static final String AUDIT_ITEM = "auditItem";
	public static final String ENUM_DESC = "enumDesc";
	public static final String SPLIT_FLAG = "splitFlag";
	public static final String SRC_ENUM = "srcEnum";
	public static final String DST_ENUM = "dstEnum";
	public static final String SRC_COUNT = "srcCount";
	public static final String DST_COUNT = "dstCount";
	public static final String AUDIT_AUTHOR = "auditAuthor";
	public static final String FARES_DRYRUN_ID = "faresDryrunId";
	public static final String MIN_ANALYSIS = "minAnalysis";

	public abstract void save(MigAuditcReportDetailResult transientInstance);

	public abstract void delete(MigAuditcReportDetailResult persistentInstance);

	public abstract MigAuditcReportDetailResult findById(java.lang.Integer id);

	public abstract List findByExample(MigAuditcReportDetailResult instance);

	public abstract List findByProperty(String propertyName, Object value);

	public abstract List findByEntity(Object entity);

	public abstract List findByAuditItem(Object auditItem);

	public abstract List findByEnumDesc(Object enumDesc);

	public abstract List findBySplitFlag(Object splitFlag);

	public abstract List findBySrcEnum(Object srcEnum);

	public abstract List findByDstEnum(Object dstEnum);

	public abstract List findBySrcCount(Object srcCount);

	public abstract List findByDstCount(Object dstCount);

	public abstract List findByAuditAuthor(Object auditAuthor);

	public abstract List findByFaresDryrunId(Object faresDryrunId);

	public abstract List findByMinAnalysis(Object minAnalysis);

	public abstract List findAll();

	public abstract MigAuditcReportDetailResult merge(
			MigAuditcReportDetailResult detachedInstance);

	public abstract void attachDirty(MigAuditcReportDetailResult instance);

	public abstract void attachClean(MigAuditcReportDetailResult instance);

}