package person.daizhongde.migration.hibernate.dao;

import java.util.List;

import person.daizhongde.virtue.dao.SpringHibernateDao;

import person.daizhongde.migration.hibernate.pojo.MigAuditcConsistency;

public interface MigAuditcConsistencyDAO extends SpringHibernateDao  {

	//property constants
	public static final String AUDIT_NAME = "auditName";
	public static final String MIG_SQL = "migSql";
	public static final String AUTHOR = "author";
	public static final String MIG_SQL_REP = "migSqlRep";

	public abstract void save(MigAuditcConsistency transientInstance);

	public abstract void delete(MigAuditcConsistency persistentInstance);

	public abstract MigAuditcConsistency findById(java.lang.Integer id);

	public abstract List findByExample(MigAuditcConsistency instance);

	public abstract List findByProperty(String propertyName, Object value);

	public abstract List findByAuditName(Object auditName);

	public abstract List findByMigSql(Object migSql);

	public abstract List findByAuthor(Object author);

	public abstract List findByMigSqlRep(Object migSqlRep);

	public abstract List findAll();

	public abstract MigAuditcConsistency merge(
			MigAuditcConsistency detachedInstance);

	public abstract void attachDirty(MigAuditcConsistency instance);

	public abstract void attachClean(MigAuditcConsistency instance);

}