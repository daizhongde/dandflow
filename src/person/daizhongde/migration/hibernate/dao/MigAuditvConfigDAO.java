package person.daizhongde.migration.hibernate.dao;

import java.util.List;

import person.daizhongde.virtue.dao.SpringHibernateDao;

import person.daizhongde.migration.hibernate.pojo.MigAuditvConfig;

public interface MigAuditvConfigDAO extends SpringHibernateDao{

	//property constants
	public static final String AUDIT_CODE = "auditCode";
	public static final String DOMAIN = "domain";
	public static final String TABLE_NAME = "tableName";
	public static final String AUDIT_NAME = "auditName";
	public static final String AUDIT_TYPE = "auditType";
	public static final String SQL_TYPE = "sqlType";
	public static final String SRC_AUDIT_SQL = "srcAuditSql";
	public static final String DST_AUDIT_SQL = "dstAuditSql";
	public static final String AUDIT_VALUE = "auditValue";
	public static final String AUDIT_FLAG = "auditFlag";
	public static final String INVALID_DATA_SQL = "invalidDataSql";
	public static final String OPERATOR = "operator";
	public static final String AUDIT_UNIT = "auditUnit";
	public static final String AUDIT_AUTHOR = "auditAuthor";
	public static final String REMARK = "remark";
	public static final String SRC_DB_CONNECT = "srcDbConnect";
	public static final String DST_DB_CONNECT = "dstDbConnect";
	public static final String MIG_SQL_REP = "migSqlRep";
	public static final String VERSION = "version";

	public abstract void save(MigAuditvConfig transientInstance);

	public abstract void delete(MigAuditvConfig persistentInstance);
	public abstract void deleteById(int id);
	public abstract void deleteById(String id);
	

	public abstract MigAuditvConfig findById(java.lang.Integer id);

	public abstract List findByExample(MigAuditvConfig instance);

	public abstract List findByProperty(String propertyName, Object value);

	public abstract List findByAuditCode(Object auditCode);

	public abstract List findByDomain(Object domain);

	public abstract List findByTableName(Object tableName);

	public abstract List findByAuditName(Object auditName);

	public abstract List findByAuditType(Object auditType);

	public abstract List findBySqlType(Object sqlType);

	public abstract List findBySrcAuditSql(Object srcAuditSql);

	public abstract List findByDstAuditSql(Object dstAuditSql);

	public abstract List findByAuditValue(Object auditValue);

	public abstract List findByAuditFlag(Object auditFlag);

	public abstract List findByInvalidDataSql(Object invalidDataSql);

	public abstract List findByOperator(Object operator);

	public abstract List findByAuditUnit(Object auditUnit);

	public abstract List findByAuditAuthor(Object auditAuthor);

	public abstract List findByRemark(Object remark);

	public abstract List findBySrcDbConnect(Object srcDbConnect);

	public abstract List findByDstDbConnect(Object dstDbConnect);

	public abstract List findByMigSqlRep(Object migSqlRep);

	public abstract List findByVersion(Object version);

	public abstract List findAll();
	public abstract List findAll(String order_column);
	
	public abstract MigAuditvConfig merge(MigAuditvConfig detachedInstance);

	public abstract void attachDirty(MigAuditvConfig instance);

	public abstract void attachClean(MigAuditvConfig instance);

}