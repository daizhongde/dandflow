package person.daizhongde.migration.hibernate.dao;

import java.util.List;

import person.daizhongde.migration.hibernate.pojo.MigConfigConnection;

public interface MigConfigConnectionDAO {

	// property constants
	public static final String URL = "url";
	public static final String REMARK = "remark";

	public abstract void save(MigConfigConnection transientInstance);

	public abstract void delete(MigConfigConnection persistentInstance);

	public abstract MigConfigConnection findById(java.lang.Integer id);

	public abstract List findByExample(MigConfigConnection instance);

	public abstract List findByProperty(String propertyName, Object value);

	public abstract List findByUrl(Object url);

	public abstract List findByRemark(Object remark);

	public abstract List findAll();

	public abstract MigConfigConnection merge(
			MigConfigConnection detachedInstance);

	public abstract void attachDirty(MigConfigConnection instance);

	public abstract void attachClean(MigConfigConnection instance);

}