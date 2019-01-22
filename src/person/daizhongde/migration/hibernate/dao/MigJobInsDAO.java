package person.daizhongde.migration.hibernate.dao;

import java.util.List;

import person.daizhongde.virtue.dao.SpringHibernateDao;

import person.daizhongde.migration.hibernate.pojo.MigJobIns;

public interface MigJobInsDAO extends SpringHibernateDao{

	// property constants
	public static final String JOB_INS_NAME = "jobInsName";
	public static final String STATUS = "status";
	public static final String AUTHOR = "author";
	public static final String REMARK = "remark";
	public static final String LOCK_STATUS = "lockStatus";
	/**
	 * recursive 后面的1代表正在执行状态
	 * @param jobInsId
	 * @param node_id
	 */
	public abstract int sqlQueryUpdateJobInsState_4recursive1(String jobInsId, String node_id);
	
	/**
	 * 此方法调用的四个位置<br>
	 * 1、启动ins时更新作业为正在执行<br>
	 * 2、实例最后一个任务执行时完成后将实例设置为完成 <br>
	 * 3、 用户发起暂停操作后把实例设置为暂停中<br>
	 * 4、暂停中最后一个执行中的任务执行完成后把实例状态设置为暂停<br>
	 * 5、有任务出错时递归更新实例每层的状态<br>
	 * @param jobInsId
	 * @param status
	 * @param lockStatus
	 * @param running
	 * @param oldstats
	 * @param oldlockStatus
	 * @param oldRunning
	 * @return
	 */
	public int sqlQueryUpdateJobInsState(String jobInsId, String status, String lockStatus, int running, 
			String oldstats, String oldlockStatus, int oldRunning);
	/** 将初始状态更新为运行态 **/
	public int sqlQueryUpdateJobInsState_init2Running( String jobInsId );
	/** 将运行态更新为完成态 **/
	public int sqlQueryUpdateJobInsState_RunningorError2Finish( String jobInsId );
	/** 将完成态或暂停态的实例更新为初始态 **/
	public int sqlQueryUpdateJobInsState_2Init( String jobInsId );
	/** 用于判断该实例是否可被重置 **/
	public int sqlQueryUpdateJobInsState_2Cur( String jobInsId );
//	public int updateJobInsState(String jobInsId, String status);
	
//	public int updateJobInsState(String jobInsId, String status, String lockStatus);
//	public int updateJobInsState(String jobInsId, String status, String lockStatus, int isRunning );
	/**
	 * try to lock a job
	 * */
//	public abstract boolean tryLock(String jobId);
	
	public abstract boolean unLock(String jobInsId);
	
	public abstract void save(MigJobIns transientInstance);

	public abstract void delete(MigJobIns persistentInstance);
	public abstract void deleteByInsId(String jobInsId);
	public abstract void deleteByInsId(List<String> jobInsId);
	
	/**
	 * 
	 * @param jobInsId
	 * @param Node
	 * @return
	 */
	public abstract int find2_4recursive1(String jobInsId, String Node);
	
	public abstract MigJobIns findById(java.lang.String id);

	public String findStatusById(java.lang.String id);
	
	public abstract List findByExample(MigJobIns instance);

	public abstract List findByProperty(String propertyName, Object value);

	public abstract List findByJobInsName(Object jobInsName);

	public abstract List findByStatus(Object status);

	public abstract List findByAuthor(Object author);

	public abstract List findByRemark(Object remark);

	public abstract List findByLockStatus(Object lockStatus);

	public abstract List findAll();

	public abstract MigJobIns merge(MigJobIns detachedInstance);

	public abstract void attachDirty(MigJobIns instance);

	public abstract void attachClean(MigJobIns instance);

}