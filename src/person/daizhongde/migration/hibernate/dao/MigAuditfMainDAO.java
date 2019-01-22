package person.daizhongde.migration.hibernate.dao;

import java.util.List;

import person.daizhongde.virtue.dao.SpringHibernateDao;

import person.daizhongde.migration.hibernate.pojo.MigAuditfMain;

public interface MigAuditfMainDAO  extends SpringHibernateDao{

	//property constants
	public static final String FAUDIT_NAME = "fauditName";
	public static final String FAUDIT_SRCTABLE_NAME = "fauditSrctableName";
	public static final String FAUDIT_SRCTABLE_CONN = "fauditSrctableConn";
	public static final String FAUDIT_DSTTABLE_NAME = "fauditDsttableName";
	public static final String FAUDIT_DSTTABLE_CONN = "fauditDsttableConn";
	public static final String FAUDIT_TYPE = "fauditType";
	public static final String FAUDIT_GROUP = "fauditGroup";
	public static final String FAUDIT_GROUP_NAME = "fauditGroupName";
	public static final String FAUDIT_BUSSINESS_CLUSTER = "fauditBussinessCluster";
	public static final String FAUDIT_BUSSINESS_NAME = "fauditBussinessName";
	public static final String AUTHOR = "author";
	public static final String FAUDIT_DESC = "fauditDesc";
	public static final String FAUDIT_STATUS = "fauditStatus";

	public abstract void save(MigAuditfMain transientInstance);

	public abstract void delete(MigAuditfMain persistentInstance);

	public abstract MigAuditfMain findById(java.lang.Integer id);

	public abstract List findByExample(MigAuditfMain instance);

	public abstract List findByProperty(String propertyName, Object value);

	public abstract List findByFauditName(Object fauditName);

	public abstract List findByFauditSrctableName(Object fauditSrctableName);

	public abstract List findByFauditSrctableConn(Object fauditSrctableConn);

	public abstract List findByFauditDsttableName(Object fauditDsttableName);

	public abstract List findByFauditDsttableConn(Object fauditDsttableConn);

	public abstract List findByFauditType(Object fauditType);

	public abstract List findByFauditGroup(Object fauditGroup);

	public abstract List findByFauditGroupName(Object fauditGroupName);

	public abstract List findByFauditBussinessCluster(
			Object fauditBussinessCluster);

	public abstract List findByFauditBussinessName(Object fauditBussinessName);

	public abstract List findByAuthor(Object author);

	public abstract List findByFauditDesc(Object fauditDesc);

	public abstract List findByFauditStatus(Object fauditStatus);

	public abstract List findAll();

	public abstract MigAuditfMain merge(MigAuditfMain detachedInstance);

	public abstract void attachDirty(MigAuditfMain instance);

	public abstract void attachClean(MigAuditfMain instance);

}