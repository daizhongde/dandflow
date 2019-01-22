package person.daizhongde.migration.hibernate.dao;

import java.util.List;

import person.daizhongde.virtue.dao.SpringHibernateDao;

import person.daizhongde.migration.hibernate.pojo.MigLoadMap;

public interface MigLoadMapDAO extends SpringHibernateDao{

	public abstract void save(MigLoadMap transientInstance);

	public abstract void delete(MigLoadMap persistentInstance);

	public abstract MigLoadMap findById(
			person.daizhongde.migration.hibernate.pojo.MigLoadMapId id);

	public abstract List findByExample(MigLoadMap instance);

	public abstract List findByProperty(String propertyName, Object value);

	public abstract List findAll();

	public abstract MigLoadMap merge(MigLoadMap detachedInstance);

	public abstract void attachDirty(MigLoadMap instance);

	public abstract void attachClean(MigLoadMap instance);

}