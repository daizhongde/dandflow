package person.daizhongde.migration.hibernate.dao;

import java.util.List;

import person.daizhongde.virtue.dao.SpringHibernateDao;

import person.daizhongde.migration.hibernate.pojo.MigAuditfFieldResult;

public interface MigAuditfFieldResultDAO extends SpringHibernateDao{

	public abstract void save(MigAuditfFieldResult transientInstance);

	public abstract void delete(MigAuditfFieldResult persistentInstance);

	public abstract MigAuditfFieldResult findById(
			person.daizhongde.migration.hibernate.pojo.MigAuditfFieldResultId id);

	public abstract List findByExample(MigAuditfFieldResult instance);

	public abstract List findByProperty(String propertyName, Object value);

	public abstract List findAll();

	public abstract MigAuditfFieldResult merge(
			MigAuditfFieldResult detachedInstance);

	public abstract void attachDirty(MigAuditfFieldResult instance);

	public abstract void attachClean(MigAuditfFieldResult instance);

}