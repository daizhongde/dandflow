package person.daizhongde.migration.hibernate.dao;

import java.util.List;

import person.daizhongde.virtue.dao.SpringHibernateDao;

import person.daizhongde.migration.hibernate.pojo.MigInsConfig;

public interface MigInsConfigDAO extends SpringHibernateDao{

	public abstract void save(MigInsConfig transientInstance);

	public abstract void delete(MigInsConfig persistentInstance);

	public abstract MigInsConfig findById(
			person.daizhongde.migration.hibernate.pojo.MigInsConfigId id);

	public abstract List findByExample(MigInsConfig instance);

	public abstract List findByProperty(String propertyName, Object value);

	public abstract List findAll();

	public abstract MigInsConfig merge(MigInsConfig detachedInstance);

	public abstract void attachDirty(MigInsConfig instance);

	public abstract void attachClean(MigInsConfig instance);

}