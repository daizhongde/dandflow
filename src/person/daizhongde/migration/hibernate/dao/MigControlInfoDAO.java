package person.daizhongde.migration.hibernate.dao;

import java.util.List;

import person.daizhongde.virtue.dao.SpringHibernateDao;

import person.daizhongde.migration.hibernate.pojo.MigControlInfo;

public interface MigControlInfoDAO extends SpringHibernateDao{

	// property constants
	public static final String CONTROL_NAME = "controlName";
	public static final String CONTROL_MARK = "controlMark";

	public abstract void save(MigControlInfo transientInstance);

	public abstract void delete(MigControlInfo persistentInstance);

	public abstract MigControlInfo findById(java.lang.String id);

	public abstract List findByExample(MigControlInfo instance);

	public abstract List findByProperty(String propertyName, Object value);

	public abstract List findByControlName(Object controlName);

	public abstract List findByControlMark(Object controlMark);

	public abstract List findAll();

	public abstract MigControlInfo merge(MigControlInfo detachedInstance);

	public abstract void attachDirty(MigControlInfo instance);

	public abstract void attachClean(MigControlInfo instance);

}