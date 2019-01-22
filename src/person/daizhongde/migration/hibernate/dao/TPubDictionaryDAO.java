package person.daizhongde.migration.hibernate.dao;

import java.util.List;

import person.daizhongde.virtue.dao.SpringHibernateDao;

import person.daizhongde.migration.hibernate.pojo.TPubDictionary;

public interface TPubDictionaryDAO  extends SpringHibernateDao {

	//property constants
	public static final String CODE = "code";
	public static final String VALUE = "value";
	public static final String TYPE = "type";
	public static final String PARENTID = "parentid";
	public static final String VERSION = "version";

	public abstract List queryListByInfo(TPubDictionary instance);
	
	public abstract void save(TPubDictionary transientInstance);

	public abstract void delete(TPubDictionary persistentInstance);

	public abstract TPubDictionary findById(java.lang.Integer id);

	public abstract List findByExample(TPubDictionary instance);

	public abstract List findByProperty(String propertyName, Object value);

	public abstract List findByCode(Object code);

	public abstract List findByValue(Object value);

	public abstract List findByType(Object type);

	public abstract List findByParentid(Object parentid);

	public abstract List findByVersion(Object version);

	public abstract List findAll();

	public abstract TPubDictionary merge(TPubDictionary detachedInstance);

	public abstract void attachDirty(TPubDictionary instance);

	public abstract void attachClean(TPubDictionary instance);

}