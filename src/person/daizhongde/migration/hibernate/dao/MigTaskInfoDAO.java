package person.daizhongde.migration.hibernate.dao;

import java.util.List;

import person.daizhongde.virtue.dao.SpringHibernateDao;

import person.daizhongde.migration.hibernate.pojo.MigJobInfo;
import person.daizhongde.migration.hibernate.pojo.MigTaskInfo;

public interface MigTaskInfoDAO extends SpringHibernateDao{

	
	// property constants
	public static final String TASK_NAME = "taskName";
	public static final String COM_ID = "comId";
	public static final String TASK_AUTHOR = "taskAuthor";
	public static final String TASK_REMARK = "taskRemark";

	public abstract int countByComId(String comId);
	public abstract void deleteByTaskId(String taskId);
	
	public abstract void save(MigTaskInfo transientInstance);
	
//	public abstract void updateNameById( String taskName, String taskId );
	public abstract void updateTaskById( String taskName, String taskRemark, String taskId, String comId  );
	
	public abstract void updateTaskComById( String comId, String taskId );
	
	public abstract void delete(MigTaskInfo persistentInstance);

	public abstract MigTaskInfo findById(java.lang.String id);

	public abstract List findByExample(MigTaskInfo instance);

	public abstract List findByProperty(String propertyName, Object value);

	public abstract List findByTaskName(Object taskName);

	public abstract List findByComId(Object comId);

	public abstract List findByTaskAuthor(Object taskAuthor);

	public abstract List findByTaskRemark(Object taskRemark);

	public abstract List findAll();

	public abstract MigTaskInfo merge(MigTaskInfo detachedInstance);

	public abstract void attachDirty(MigTaskInfo instance);

	public abstract void attachClean(MigTaskInfo instance);

}