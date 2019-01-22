package person.daizhongde.migration.hibernate.dao;

import java.util.List;

import person.daizhongde.virtue.dao.SpringHibernateDao;

import person.daizhongde.migration.hibernate.pojo.MigAuditcEnumMapping;

public interface MigAuditcEnumMappingDAO extends SpringHibernateDao  {

	//property constants
	public static final String ENTITY = "entity";
	public static final String AUDIT_NAME = "auditName";
	public static final String ENUM_DESC = "enumDesc";
	public static final String SRC_ENUM = "srcEnum";
	public static final String DST_ENUM = "dstEnum";

	public abstract void save(MigAuditcEnumMapping transientInstance);

	public abstract void delete(MigAuditcEnumMapping persistentInstance);

	public abstract MigAuditcEnumMapping findById(java.lang.Integer id);

	public abstract List findByExample(MigAuditcEnumMapping instance);

	public abstract List findByProperty(String propertyName, Object value);

	public abstract List findByEntity(Object entity);

	public abstract List findByAuditName(Object auditName);

	public abstract List findByEnumDesc(Object enumDesc);

	public abstract List findBySrcEnum(Object srcEnum);

	public abstract List findByDstEnum(Object dstEnum);

	public abstract List findAll();

	public abstract MigAuditcEnumMapping merge(
			MigAuditcEnumMapping detachedInstance);

	public abstract void attachDirty(MigAuditcEnumMapping instance);

	public abstract void attachClean(MigAuditcEnumMapping instance);

}