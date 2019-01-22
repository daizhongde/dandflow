package person.daizhongde.migration.hibernate.dao;

import java.util.List;

import person.daizhongde.virtue.dao.SpringHibernateDao;

import person.daizhongde.migration.hibernate.pojo.MigAuditvResult;

public interface MigAuditvResultDAO extends SpringHibernateDao{

	//property constants
	public static final String AUDIT_CODE = "auditCode";
	public static final String FARES_DRYRUN_ID = "faresDryrunId";
	public static final String MIN_ANALYSIS = "minAnalysis";
	public static final String DOMAIN = "domain";
	public static final String TABLE_NAME = "tableName";
	public static final String AUDIT_NAME = "auditName";
	public static final String SRC_VALUE = "srcValue";
	public static final String DST_VALUE = "dstValue";
	public static final String MIN_VALUE = "minValue";
	public static final String AUDIT_UNIT = "auditUnit";
	public static final String INVALID_DATA_TABLE = "invalidDataTable";
	public static final String RESULT = "result";
	public static final String AUDIT_AUTHOR = "auditAuthor";
	public static final String REMARK = "remark";
	public static final String SUCCESS_FLAG = "successFlag";
	public static final String ERR_MSG = "errMsg";
	public static final String INVALID_DATA_CNT = "invalidDataCnt";

	public abstract void save(MigAuditvResult transientInstance);

	public abstract void delete(MigAuditvResult persistentInstance);

	public abstract MigAuditvResult findById(java.lang.Integer id);

	public abstract List findByExample(MigAuditvResult instance);

	public abstract List findByProperty(String propertyName, Object value);

	public abstract List findByAuditCode(Object auditCode);

	public abstract List findByFaresDryrunId(Object faresDryrunId);

	public abstract List findByMinAnalysis(Object minAnalysis);

	public abstract List findByDomain(Object domain);

	public abstract List findByTableName(Object tableName);

	public abstract List findByAuditName(Object auditName);

	public abstract List findBySrcValue(Object srcValue);

	public abstract List findByDstValue(Object dstValue);

	public abstract List findByMinValue(Object minValue);

	public abstract List findByAuditUnit(Object auditUnit);

	public abstract List findByInvalidDataTable(Object invalidDataTable);

	public abstract List findByResult(Object result);

	public abstract List findByAuditAuthor(Object auditAuthor);

	public abstract List findByRemark(Object remark);

	public abstract List findBySuccessFlag(Object successFlag);

	public abstract List findByErrMsg(Object errMsg);

	public abstract List findByInvalidDataCnt(Object invalidDataCnt);

	public abstract List findAll();

	public abstract MigAuditvResult merge(MigAuditvResult detachedInstance);

	public abstract void attachDirty(MigAuditvResult instance);

	public abstract void attachClean(MigAuditvResult instance);

}