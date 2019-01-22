package person.daizhongde.migration.hibernate.dao;

import java.util.List;

import person.daizhongde.virtue.dao.SpringHibernateDao;

import person.daizhongde.migration.hibernate.pojo.MigAuditvErrreason;

public interface MigAuditvErrreasonDAO extends SpringHibernateDao{

	//property constants
	public static final String DMP_NO = "dmpNo";
	public static final String REASON = "reason";

	public abstract void save(MigAuditvErrreason transientInstance);

	public abstract void delete(MigAuditvErrreason persistentInstance);

	public abstract MigAuditvErrreason findById(
			person.daizhongde.migration.hibernate.pojo.MigAuditvErrreasonId id);

	public abstract List findByExample(MigAuditvErrreason instance);

	public abstract List findByProperty(String propertyName, Object value);

	public abstract List findByDmpNo(Object dmpNo);

	public abstract List findByReason(Object reason);

	public abstract List findAll();

	public abstract MigAuditvErrreason merge(MigAuditvErrreason detachedInstance);

	public abstract void attachDirty(MigAuditvErrreason instance);

	public abstract void attachClean(MigAuditvErrreason instance);

}