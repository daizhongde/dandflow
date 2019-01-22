package person.daizhongde.migration.hibernate.dao;

import java.util.List;

import person.daizhongde.virtue.dao.SpringHibernateDao;

import person.daizhongde.migration.hibernate.pojo.MigControlTemplate;

public interface MigControlTemplateDAO extends SpringHibernateDao{

	public abstract void save(MigControlTemplate transientInstance);
	
	public abstract List getControlTemplateByCtlId(String ctlId);

	public abstract void delete(MigControlTemplate persistentInstance);

	public abstract MigControlTemplate findById(
			person.daizhongde.migration.hibernate.pojo.MigControlTemplateId id);

	public abstract List<MigControlTemplate> findByControlId(String id);
	
	
	public abstract List findByExample(MigControlTemplate instance);

	public abstract List findByProperty(String propertyName, Object value);

	public abstract List findAll();

	public abstract MigControlTemplate merge(MigControlTemplate detachedInstance);

	public abstract void attachDirty(MigControlTemplate instance);

	public abstract void attachClean(MigControlTemplate instance);

}