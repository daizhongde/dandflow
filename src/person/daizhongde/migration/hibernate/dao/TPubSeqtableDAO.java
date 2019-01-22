package person.daizhongde.migration.hibernate.dao;

import java.util.List;

import person.daizhongde.migration.hibernate.pojo.TPubSeqtable;

public interface TPubSeqtableDAO {

	//property constants
	public static final String SEQ_VALUE = "seqValue";
	public static final String PREFIX = "prefix";

	/**
	 * 使用HQL查询数据库序列表
	 * @param tableName
	 * @return
	 */
	public abstract int sequenceCURRVAL2(String tableName);
	public abstract int sequenceNEXTVAL2( String tableName );
	/**
	 * 使用SQL查询数据库序列表
	 * @param tableName
	 * @return
	 */
	public abstract int sqlQuerySequenceCURRVAL2(String tableName);
	/**
	 * 使用SQL查询数据库序列表
	 * @param tableName
	 * @param resetEveryday 是否每天重置
	 * @return
	 */
	public abstract int sqlQuerySequenceNEXTVAL2( String tableName, boolean resetEveryday );
	
	public abstract void save(TPubSeqtable transientInstance);

	public abstract void delete(TPubSeqtable persistentInstance);

	public abstract TPubSeqtable findById(java.lang.String id);

	public abstract List findByExample(TPubSeqtable instance);

	public abstract List findByProperty(String propertyName, Object value);

	public abstract List findBySeqValue(Object seqValue);

	public abstract List findByPrefix(Object prefix);

	public abstract List findAll();

	public abstract TPubSeqtable merge(TPubSeqtable detachedInstance);

	public abstract void attachDirty(TPubSeqtable instance);

	public abstract void attachClean(TPubSeqtable instance);

}