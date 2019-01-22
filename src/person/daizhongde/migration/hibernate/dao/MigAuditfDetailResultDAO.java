package person.daizhongde.migration.hibernate.dao;

import java.util.List;

import person.daizhongde.virtue.dao.SpringHibernateDao;

import person.daizhongde.migration.hibernate.pojo.MigAuditfDetailResult;

public interface MigAuditfDetailResultDAO extends SpringHibernateDao {

	public abstract void save(MigAuditfDetailResult transientInstance);

	public abstract void delete(MigAuditfDetailResult persistentInstance);

	public abstract MigAuditfDetailResult findById(
			person.daizhongde.migration.hibernate.pojo.MigAuditfDetailResultId id);

	public abstract List findByExample(MigAuditfDetailResult instance);

	public abstract List findByProperty(String propertyName, Object value);

	public abstract List findAll();

	public abstract MigAuditfDetailResult merge(
			MigAuditfDetailResult detachedInstance);

	public abstract void attachDirty(MigAuditfDetailResult instance);

	public abstract void attachClean(MigAuditfDetailResult instance);

}