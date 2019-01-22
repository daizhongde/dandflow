package person.daizhongde.migration.hibernate.dao;

import java.util.List;

import person.daizhongde.virtue.dao.SpringHibernateDao;

import person.daizhongde.migration.hibernate.pojo.TChatMsg;

public interface TChatMsgDAO extends SpringHibernateDao{

	//property constants
	public static final String _NUID = "NUid";
	public static final String _CMSG = "CMsg";

	public abstract void save(TChatMsg transientInstance);

	public abstract void delete(TChatMsg persistentInstance);

	public abstract TChatMsg findById(java.lang.Integer id);

	public abstract List findByExample(TChatMsg instance);

	public abstract List findByProperty(String propertyName, Object value);

	public abstract List findByNUid(Object NUid);

	public abstract List findByCMsg(Object CMsg);

	public abstract List findAll();

	public abstract TChatMsg merge(TChatMsg detachedInstance);

	public abstract void attachDirty(TChatMsg instance);

	public abstract void attachClean(TChatMsg instance);

}