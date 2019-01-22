package person.daizhongde.migration.hibernate.dao;

import java.util.List;

import person.daizhongde.virtue.dao.SpringHibernateDao;

import person.daizhongde.migration.hibernate.pojo.MigSyncConfig;

public interface MigSyncConfigDAO extends SpringHibernateDao{

	//property constants
	public static final String MIG_GROUP = "migGroup";
	public static final String MIG_TABLE = "migTable";
	public static final String MIG_AUTHOR = "migAuthor";
	public static final String SRC_TYPE = "srcType";
	public static final String SRC_IP = "srcIp";
	public static final String SRC_PORT = "srcPort";
	public static final String SRC_SCHEMA = "srcSchema";
	public static final String SRC_USER = "srcUser";
	public static final String SRC_PASSWORD = "srcPassword";
	public static final String DST_TYPE = "dstType";
	public static final String DST_IP = "dstIp";
	public static final String DST_PORT = "dstPort";
	public static final String DST_SCHEMA = "dstSchema";
	public static final String DST_USER = "dstUser";
	public static final String DST_PASSWORD = "dstPassword";
	public static final String MIG_WHERE = "migWhere";
	public static final String MIG_MODE = "migMode";
	public static final String MIG_DESC = "migDesc";

	public abstract void save(MigSyncConfig transientInstance);

	public abstract void delete(MigSyncConfig persistentInstance);

	public abstract MigSyncConfig findById(java.lang.Integer id);

	public abstract List findByExample(MigSyncConfig instance);

	public abstract List findByProperty(String propertyName, Object value);

	public abstract List findByMigGroup(Object migGroup);

	public abstract List findByMigTable(Object migTable);

	public abstract List findByMigAuthor(Object migAuthor);

	public abstract List findBySrcType(Object srcType);

	public abstract List findBySrcIp(Object srcIp);

	public abstract List findBySrcPort(Object srcPort);

	public abstract List findBySrcSchema(Object srcSchema);

	public abstract List findBySrcUser(Object srcUser);

	public abstract List findBySrcPassword(Object srcPassword);

	public abstract List findByDstType(Object dstType);

	public abstract List findByDstIp(Object dstIp);

	public abstract List findByDstPort(Object dstPort);

	public abstract List findByDstSchema(Object dstSchema);

	public abstract List findByDstUser(Object dstUser);

	public abstract List findByDstPassword(Object dstPassword);

	public abstract List findByMigWhere(Object migWhere);

	public abstract List findByMigMode(Object migMode);

	public abstract List findByMigDesc(Object migDesc);

	public abstract List findAll();

	public abstract MigSyncConfig merge(MigSyncConfig detachedInstance);

	public abstract void attachDirty(MigSyncConfig instance);

	public abstract void attachClean(MigSyncConfig instance);

}