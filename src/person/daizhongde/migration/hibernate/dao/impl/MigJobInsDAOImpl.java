package person.daizhongde.migration.hibernate.dao.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import person.daizhongde.virtue.dao.SpringHibernateDaoSupport;

import person.daizhongde.migration.constant.JobLockState;
import person.daizhongde.migration.constant.JobRunState;
import person.daizhongde.migration.constant.JobState;
import person.daizhongde.migration.hibernate.dao.MigJobInsDAO;
import person.daizhongde.migration.hibernate.pojo.MigJobIns;

/**
 * A data access object (DAO) providing persistence and search support for
 * MigJobIns entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see person.daizhongde.migration.hibernate.pojotemp.MigJobIns
 * @author MyEclipse Persistence Tools
 */
public class MigJobInsDAOImpl extends SpringHibernateDaoSupport implements MigJobInsDAO {
	private static final Logger log = LoggerFactory
			.getLogger(MigJobInsDAOImpl.class);
	protected void initDao() {
		// do nothing
	}
	
//	public  boolean tryLock(String jobId){
//		try {
//			sem_lock.acquire();
//		String sql="select LOCK_STATUS from mig_job_info where job_id=:job_id";
//		Map<String,String> condition = new HashMap<String,String>(2);
//		condition.put("job_id", jobId);
//		condition.put("lock_status", JobLockState.LOCK);
//		String lockStatus=String.valueOf(this.sqlQueryfindaValueByMap(sql, condition));
//		
//		//lockStatus为空值是可以与lock比较的
////		if(lockStatus!=null && lockStatus.equals(JobLockState.LOCK)){
//		if(lockStatus.equals(JobLockState.LOCK)){
//		  sem_lock.release();
//		  return false;
//		}
//		sql="update mig_job_info set LOCK_STATUS=:lock_status where job_id=:job_id";
//		this.sqlQueryExeUByMap(sql, condition);
//		sem_lock.release();
//		return true;
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//			sem_lock.release();
//			return false;
//		}
//		
//	}
	
	public  boolean unLock(String jobInsId){
		Map<String,String> condition = new HashMap<String,String>(2);
		condition.put("job_ins_id", jobInsId);
		condition.put("lock_status", JobLockState.UNLOCK);
		String sql="update mig_job_ins set lock_status=:lock_status where job_ins_id=:job_ins_id";
		this.sqlQueryExeUByMap(sql, condition);
		return true;
	}
	public int sqlQueryUpdateJobInsState_4recursive1(String jobInsId, String node_id){
		Map condition = new HashMap(2);
		condition.put("job_ins_id", jobInsId);
		condition.put("node_id", node_id);
		String sql="UPDATE tool.mig_job_ins SET STATUS=1 "
				+ "  WHERE job_ins_id = :job_ins_id AND job_id= :node_id AND STATUS = -1  ";
		return this.sqlQueryExeUByMap(sql,condition);
	}
	/**
	 * 此方法调用的四个位置<br>
	 * 1、启动ins时更新作业为正在执行<br>
	 * 2、实例最后一个任务执行时完成后将实例设置为完成 <br>
	 * 3、 用户发起暂停操作后把实例设置为暂停中<br>
	 * 4、暂停中最后一个执行中的任务执行完成后把实例状态设置为暂停<br>
	 * <p>
	 * 更新begin_time和end_time的时间：<br>
	 * 1、实例从初始状态启动时记录begin_time<br>
	 * 2、完成时记录end_time<br>
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
			String oldstats, String oldlockStatus, int oldRunning){
		Map condition = new HashMap(7);
		condition.put("job_ins_id", jobInsId);
		condition.put("status", status);
		condition.put("lock_Status", lockStatus);
		condition.put("oldstats", oldstats);
		condition.put("oldlock_Status", oldlockStatus);
		condition.put("running", running);
		condition.put("oldRunning", oldRunning);
		
		String SQL = "update mig_job_ins set status = :status, lock_Status = :lock_Status,"
				+ " running = :running"
				+ " where job_ins_id = :job_ins_id and status = :oldstats and lock_Status = :oldlock_Status"
				+ " and running = :oldRunning";
		return this.sqlQueryExeUByMap(SQL, condition);
	}
	public int sqlQueryUpdateJobInsState_init2Running(String jobInsId){
		Map condition = new HashMap(1);
		condition.put("job_ins_id", jobInsId);
		
		String SQL = "update mig_job_ins "
				+ "      set status = '"+JobState.EXECUTING+"', lock_Status = '"+JobLockState.LOCK+"',"
				+ "          running = "+JobRunState.WITHBACKTHREAD+", begin_time=NOW() "
				+ "    where job_ins_id = :job_ins_id "
				+ "      and status = '"+JobState.INITIAL+"' and lock_Status = '"+JobLockState.UNLOCK+"' "
				+ "      and running = "+JobRunState.NOBACKTHREAD;
		return this.sqlQueryExeUByMap(SQL, condition);
	}
	public int sqlQueryUpdateJobInsState_RunningorError2Finish(String jobInsId){
		Map condition = new HashMap(1);
		condition.put("job_ins_id", jobInsId);
		
		String SQL = "update mig_job_ins "
				+ "      set status = '"+JobState.FINISH+"', lock_Status = '"+JobLockState.UNLOCK+"',"
				+ "          running = "+JobRunState.NOBACKTHREAD+", end_time=NOW() "
				+ "    where job_ins_id = :job_ins_id "
				+ "      and (status = '"+JobState.EXECUTING+"' or status = '"+JobState.ERROR+"') and lock_Status = '"+JobLockState.LOCK+"' "
				+ "      and running = "+JobRunState.WITHBACKTHREAD;
		return this.sqlQueryExeUByMap(SQL, condition);
	}
	public int sqlQueryUpdateJobInsState_2Init(String jobInsId){
		Map condition = new HashMap(1);
		condition.put("job_ins_id", jobInsId);
		
		String SQL = "update mig_job_ins "
				+ "      set status = '"+JobState.INITIAL+"', lock_Status = '"+JobLockState.UNLOCK+"',"
				+ "          running = "+JobRunState.NOBACKTHREAD+", begin_time=null, end_time=null "
				+ "    where job_ins_id = :job_ins_id "
				+ "      and (status = '"+JobState.FINISH+"' or status = '"+JobState.PAUSE+"') "
			    + "      and lock_Status = '"+JobLockState.UNLOCK+"' "
				+ "      and running = "+JobRunState.NOBACKTHREAD;
		return this.sqlQueryExeUByMap(SQL, condition);
	}
	public int sqlQueryUpdateJobInsState_2Cur(String jobInsId){
		Map condition = new HashMap(1);
		condition.put("job_ins_id", jobInsId);
		
		String SQL = "update mig_job_ins "
				+ "      set lock_Status = '"+JobLockState.UNLOCK+"',"
				+ "          running = "+JobRunState.NOBACKTHREAD+", begin_time=null, end_time=null "
				+ "    where job_ins_id = :job_ins_id "
				+ "      and (status = '"+JobState.FINISH+"' or status = '"+JobState.PAUSE+"') "
			    + "      and lock_Status = '"+JobLockState.UNLOCK+"' "
				+ "      and running = "+JobRunState.NOBACKTHREAD;
		return this.sqlQueryExeUByMap(SQL, condition);
	}
//	public int updateJobInsState(String jobInsId, String status){
//		Map condition = new HashMap(2);
//		condition.put("job_ins_id", jobInsId);
//		condition.put("status", status);
//			
//		String SQL = "update mig_job_ins set status = :status "
//				+ "where job_ins_id = :job_ins_id";
//		return this.sqlQueryExeUByMap(SQL, condition);
//	}
	
//	public int updateJobInsState(String jobInsId, String status, String lockStatus){
//		Map condition = new HashMap(3);
//		condition.put("job_ins_id", jobInsId);
//		condition.put("status", status);
//		condition.put("lockStatus", lockStatus);
//		
//		String SQL = "update mig_job_ins set status = :status, lock_Status = :lockStatus where job_ins_id = :job_ins_id ";
//		return this.sqlQueryExeUByMap(SQL, condition);
//	}
	/*public int updateJobInsState(String jobInsId, String status, String lockStatus, int isRunning ){
		Map condition = new HashMap(3);
		condition.put("job_ins_id", jobInsId);
		condition.put("status", status);
		condition.put("lockStatus", lockStatus);
		condition.put("isRunning", isRunning);
		
		String SQL = "update mig_job_ins set status = :status, lock_Status = :lockStatus, running = :isRunning where job_ins_id = :job_ins_id ";
		return this.sqlQueryExeUByMap(SQL, condition);
	}*/
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigJobInsDAO#save(person.daizhongde.migration.hibernate.pojo.MigJobIns)
	 */
	@Override
	public void save(MigJobIns transientInstance) {
		log.debug("saving MigJobIns instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigJobInsDAO#delete(person.daizhongde.migration.hibernate.pojo.MigJobIns)
	 */
	@Override
	public void delete(MigJobIns persistentInstance) {
		log.debug("deleting MigJobIns instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}
	public void deleteByInsId(String jobInsId){
		Map condition = new HashMap(1);
		condition.put("jobInsId", jobInsId);
		String sql = "delete from mig_job_ins where JOB_INS_ID=:jobInsId";
		this.sqlQueryExeUByMap(sql, condition);	
	}
	public void deleteByInsId(List<String> jobInsId){
		Map condition = new HashMap(1);
		condition.put("jobInsId", jobInsId);
		String sql = "delete from mig_job_ins where JOB_INS_ID in (:jobInsId)";
		this.sqlQueryExeUByMap(sql, condition);	
	}
	
	public int find2_4recursive1(String jobInsId, String Node){
		Map condition = new HashMap(2);
		condition.put("job_ins_id", jobInsId);
		condition.put("node_id", Node);
		String sql="SELECT count(*) FROM tool.mig_job_ins "
				+ "WHERE job_ins_id= :job_ins_id AND job_id= :node_id AND STATUS = -1";

		return Integer.valueOf( this.sqlQueryfindaValueByMap(sql,condition).toString() );
	}
	
	public MigJobIns findById(java.lang.String id) {
//		log.debug("getting MigJobIns instance with id: " + id);
		try {
			MigJobIns instance = (MigJobIns) getHibernateTemplate().get(
					"person.daizhongde.migration.hibernate.pojo.MigJobIns", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public String findStatusById(java.lang.String id) {
		try {
			String status = (String)this.sqlQueryfindaValue(
					"select status from mig_job_ins where job_ins_id='"+id+"'", "status");
			return status;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigJobInsDAO#findByExample(person.daizhongde.migration.hibernate.pojo.MigJobIns)
	 */
	@Override
	public List findByExample(MigJobIns instance) {
		log.debug("finding MigJobIns instance by example");
		try {
			List results = getHibernateTemplate().findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigJobInsDAO#findByProperty(java.lang.String, java.lang.Object)
	 */
	@Override
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding MigJobIns instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from MigJobIns as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigJobInsDAO#findByJobInsName(java.lang.Object)
	 */
	@Override
	public List findByJobInsName(Object jobInsName) {
		return findByProperty(JOB_INS_NAME, jobInsName);
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigJobInsDAO#findByStatus(java.lang.Object)
	 */
	@Override
	public List findByStatus(Object status) {
		return findByProperty(STATUS, status);
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigJobInsDAO#findByAuthor(java.lang.Object)
	 */
	@Override
	public List findByAuthor(Object author) {
		return findByProperty(AUTHOR, author);
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigJobInsDAO#findByRemark(java.lang.Object)
	 */
	@Override
	public List findByRemark(Object remark) {
		return findByProperty(REMARK, remark);
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigJobInsDAO#findByLockStatus(java.lang.Object)
	 */
	@Override
	public List findByLockStatus(Object lockStatus) {
		return findByProperty(LOCK_STATUS, lockStatus);
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigJobInsDAO#findAll()
	 */
	@Override
	public List findAll() {
		log.debug("finding all MigJobIns instances");
		try {
			String queryString = "from MigJobIns";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigJobInsDAO#merge(person.daizhongde.migration.hibernate.pojo.MigJobIns)
	 */
	@Override
	public MigJobIns merge(MigJobIns detachedInstance) {
		log.debug("merging MigJobIns instance");
		try {
			MigJobIns result = (MigJobIns) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigJobInsDAO#attachDirty(person.daizhongde.migration.hibernate.pojo.MigJobIns)
	 */
	@Override
	public void attachDirty(MigJobIns instance) {
		log.debug("attaching dirty MigJobIns instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigJobInsDAO#attachClean(person.daizhongde.migration.hibernate.pojo.MigJobIns)
	 */
	@Override
	public void attachClean(MigJobIns instance) {
		log.debug("attaching clean MigJobIns instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static MigJobInsDAO getFromApplicationContext(ApplicationContext ctx) {
		return (MigJobInsDAO) ctx.getBean("MigJobInsDAO");
	}
}