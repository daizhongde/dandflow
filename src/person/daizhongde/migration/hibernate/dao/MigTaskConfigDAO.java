package person.daizhongde.migration.hibernate.dao;

import java.util.List;

import person.daizhongde.virtue.dao.SpringHibernateDao;

import person.daizhongde.migration.hibernate.pojo.MigTaskConfig;

public interface MigTaskConfigDAO extends SpringHibernateDao{

	//property constants
	public static final String MIG_CONFIG_TYPE = "migConfigType";
	public static final String MIG_SRC = "migSrc";
	public static final String MIG_SRC_CONN = "migSrcConn";
	public static final String MIG_WHERE = "migWhere";
	public static final String MIG_DST = "migDst";
	public static final String MIG_DST_CONN = "migDstConn";
	public static final String MIG_AUTHOR = "migAuthor";
	public static final String MIG_DESC = "migDesc";
	public static final String MIG_STATUS = "migStatus";

	public abstract List findDomains(int type);
	
	public abstract void save(MigTaskConfig transientInstance);

	public abstract void delete(MigTaskConfig persistentInstance);

	public abstract MigTaskConfig findById(java.lang.Integer id);

	public abstract List findByExample(MigTaskConfig instance);

	public abstract List findByProperty(String propertyName, Object value);
	public abstract List findByProperty(String propertyName, Object value, String order_column);
	
	public abstract List findByMigConfigType(Object migConfigType);

	public abstract List findByMigSrc(Object migSrc);

	public abstract List findByMigSrcConn(Object migSrcConn);

	public abstract List findByMigWhere(Object migWhere);

	public abstract List findByMigDst(Object migDst);

	public abstract List findByMigDstConn(Object migDstConn);

	public abstract List findByMigAuthor(Object migAuthor);

	public abstract List findByMigDesc(Object migDesc);

	public abstract List findByMigStatus(Object migStatus);

	public abstract List findAll();

	public abstract MigTaskConfig merge(MigTaskConfig detachedInstance);

	public abstract void attachDirty(MigTaskConfig instance);

	public abstract void attachClean(MigTaskConfig instance);

}