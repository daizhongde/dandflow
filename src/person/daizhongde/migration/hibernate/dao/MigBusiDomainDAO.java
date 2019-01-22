package person.daizhongde.migration.hibernate.dao;

import java.util.List;

import person.daizhongde.virtue.dao.SpringHibernateDao;

import person.daizhongde.migration.hibernate.pojo.MigBusiDomain;

public interface MigBusiDomainDAO extends SpringHibernateDao{

	//property constants
	public static final String NAME = "name";
	public static final String REMARK = "remark";

	public abstract void save(MigBusiDomain transientInstance);

	public abstract void delete(MigBusiDomain persistentInstance);

	public abstract MigBusiDomain findById(java.lang.Integer id);

	public abstract List findByExample(MigBusiDomain instance);

	public abstract List findByProperty(String propertyName, Object value);

	public abstract List findByName(Object name);

	public abstract List findByRemark(Object remark);

	public abstract List findAll();
	public abstract List findAll(String order_column);
	
	public abstract MigBusiDomain merge(MigBusiDomain detachedInstance);

	public abstract void attachDirty(MigBusiDomain instance);

	public abstract void attachClean(MigBusiDomain instance);

}