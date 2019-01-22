package person.daizhongde.migration.hibernate.dao;

import java.util.List;

import person.daizhongde.virtue.dao.SpringHibernateDao;

import person.daizhongde.migration.hibernate.pojo.MigJobInfo;

public interface MigJobInfoDAO extends SpringHibernateDao{

	// property constants
	public static final String JOB_NAME = "jobName";
	public static final String JOB_CRON = "jobCron";
	public static final String JOB_AUTHOR = "jobAuthor";
	public static final String JOB_REMARK = "jobRemark";
	
//	public abstract int updateCompileStatus2uncompiled( String jobId );
	
	public abstract void addJob(String jobName, int type, String remark, String userlogin_name, String newJobId );

	public abstract void updateColumnById(String jobId,String columnName,String value);
	
	public abstract void DeleteJobById(String jobId);
	
//	/**
//	 * 更新作业状态
//	 * @param jobId
//	 * @param state
//	 */
//	public abstract void updateJobState(String jobId, String status);
//	/**
//	 * 更新作业状态，同时更新锁
//	 * @param jobId
//	 * @param status
//	 * @param lockStatus
//	 */
//	
//	public abstract void updateJobState(String jobId, String status, String lockStatus);
//	/**
//	 * 更新作业状态，同时更新锁,带同步校验
//	 * @param jobId
//	 * @param status
//	 * @param lockStatus
//	 * @param oldstats
//	 * @param oldlockStatus
//	 */
//	public abstract int updateJobState(String jobId, String status, String lockStatus,
//			String oldstats, String oldlockStatus);
//	/**
//	 * 更新作业状态，同时更新锁,带同步校验
//	 * @param jobId
//	 * @param status
//	 */
//	public abstract int sqlQueryUpdateJobState(String jobId, String status );
//	/**
//	 * 更新作业状态，同时更新锁,带同步校验
//	 * @param jobId
//	 * @param status
//	 * @param lockStatus
//	 */
//	public abstract int sqlQueryUpdateJobState(String jobId, String status, String lockStatus);
//	/**
//	 * 更新作业状态，同时更新锁,带同步校验
//	 * @param jobId
//	 * @param status
//	 * @param lockStatus
//	 * @param oldstats
//	 * @param oldlockStatus
//	 */
//	public abstract int sqlQueryUpdateJobState(String jobId, String status, String lockStatus,
//			String oldstats, String oldlockStatus);
//	
	public abstract void save(MigJobInfo transientInstance);

	public abstract void delete(MigJobInfo persistentInstance);

	public abstract MigJobInfo findById(java.lang.String id);
	public abstract String findStatusById(java.lang.String id);
	
	public abstract List findByExample(MigJobInfo instance);

	public abstract List findByProperty(String propertyName, Object value);

	public abstract List findByJobName(Object jobName);
	public abstract Long findCountByJobName(String jobName);
	
	public abstract List findByJobCron(Object jobCron);

	public abstract List findByJobAuthor(Object jobAuthor);

	public abstract List findByJobRemark(Object jobRemark);

	public abstract List findAll();

	public abstract MigJobInfo merge(MigJobInfo detachedInstance);

	public abstract void attachDirty(MigJobInfo instance);

	public abstract void attachClean(MigJobInfo instance);

}