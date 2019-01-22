package person.daizhongde.migration.hibernate.dao.impl;

//import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Semaphore;

import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
//import org.springframework.orm.hibernate3.support.HibernateDaoSupport;




import person.daizhongde.virtue.dao.SpringHibernateDaoSupport;

import person.daizhongde.migration.constant.JobCompiled;
import person.daizhongde.migration.constant.JobLockState;
import person.daizhongde.migration.constant.JobState;
import person.daizhongde.migration.hibernate.dao.MigJobInfoDAO;
import person.daizhongde.migration.hibernate.pojo.MigJobInfo;

/**
 * A data access object (DAO) providing persistence and search support for
 * MigJobInfo entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see person.daizhongde.migration.hibernate.dao.impl.MigJobInfo
 * @author MyEclipse Persistence Tools
 */
public class MigJobInfoDAOImpl extends SpringHibernateDaoSupport implements MigJobInfoDAO {
	private static final Logger log = LoggerFactory
			.getLogger(MigJobInfoDAOImpl.class);
	public static Semaphore sem_lock = new Semaphore(1);
	protected void initDao() {
		// do nothing
	}

	
	public  void DeleteJobById(String jobId){
		String sql="delete from mig_job_info where job_id=:job_id";
		Map condition = new HashMap(1);
		condition.put("job_id", jobId);
		this.sqlQueryExeUByMap(sql,condition);	

	}

//	public int updateCompileStatus2uncompiled( String jobId ){
//		String sql="update mig_job_info set status = '" +JobState.INITIAL+ "', compiled = '" +JobCompiled.UNCOMPILED+ "' where job_id='"+jobId+"' and compiled = '" +JobCompiled.COMPILED+ "'";
//		return this.sqlQueryExeU(sql);
//	}
	
	public  void updateColumnById(String jobId,String columnName,String value){
	    SimpleDateFormat df = new SimpleDateFormat(
	    	      "yyyy-MM-dd HH:mm:ss");    
	    
	    Map condition = new HashMap(3);
		condition.put("value", value);
		condition.put("job_update", df.format(new Date()));
		condition.put("job_id", jobId);
	
		String sql="update mig_job_info set "+columnName+"=:value,job_update=:job_update  where job_id=:job_id";
		log.debug(sql);
		this.sqlQueryExeUByMap(sql,condition);	
	}

//	public void updateJobState(String jobId, String status){
//		Map condition = new HashMap(2);
//		condition.put("jobId", jobId);
//		condition.put("status", status);
//		
//		String HQL = "update MigJobInfo set status = :status where jobId = :jobId ";
//		this.exeUByMap(HQL, condition);
//	}
//	public void updateJobState(String jobId, String status, String lockStatus){
//		Map condition = new HashMap(3);
//		condition.put("jobId", jobId);
//		condition.put("status", status);
//		condition.put("lockStatus", lockStatus);
//		
//		String HQL = "update MigJobInfo set status = :status, lockStatus = :lockStatus where jobId = :jobId ";
//		this.exeUByMap(HQL, condition);
//	}
//	
//	public int updateJobState(String jobId, String status, String lockStatus, String oldstats, String oldlockStatus){
//		Map condition = new HashMap(5);
//		condition.put("jobId", jobId);
//		condition.put("status", status);
//		condition.put("lockStatus", lockStatus);
//		condition.put("oldstats", oldstats);
//		condition.put("oldlockStatus", oldlockStatus);
//		
//		String HQL = "update MigJobInfo set status = :status, lockStatus = :lockStatus"
//				+ " where jobId = :jobId and status = :oldstats and lockStatus = :oldlockStatus";
//		return this.exeUByMap(HQL, condition);
//	}
//	public int sqlQueryUpdateJobState(String jobId, String status){
//		Map condition = new HashMap(5);
//		condition.put("jobId", jobId);
//		condition.put("status", status);
//			
//		String SQL = "update mig_job_info set status = :status "
//				+ "where job_Id = :jobId";
//		return this.sqlQueryExeUByMap(SQL, condition);
//	}
//	public int sqlQueryUpdateJobState(String jobId, String status, String lockStatus ){
//		Map condition = new HashMap(5);
//		condition.put("jobId", jobId);
//		condition.put("status", status);
//		condition.put("lockStatus", lockStatus);
//			
//		String SQL = "update mig_job_info set status = :status, lock_Status = :lockStatus"
//				+ " where job_Id = :jobId";
//		return this.sqlQueryExeUByMap(SQL, condition);
//	}
//	public int sqlQueryUpdateJobState(String jobId, String status, String lockStatus, String oldstats, String oldlockStatus){
//		Map condition = new HashMap(5);
//		condition.put("jobId", jobId);
//		condition.put("status", status);
//		condition.put("lockStatus", lockStatus);
//		condition.put("oldstats", oldstats);
//		condition.put("oldlockStatus", oldlockStatus);
//			
//		String SQL = "update mig_job_info set status = :status, lock_Status = :lockStatus"
//				+ " where job_Id = :jobId and status = :oldstats and lock_Status = :oldlockStatus";
//		return this.sqlQueryExeUByMap(SQL, condition);
//	}
	
	public void addJob(String jobName, int type, String remark, String userlogin_name, String newJobId ){
		String SQL = "insert into mig_job_info(job_name,type, job_remark, job_author, job_id, job_update) "
				+ "values( '"+jobName+"', "+type+", '"+remark+"', '"+userlogin_name+"',  '"+newJobId+"',now() )";
		this.sqlQueryExeU(SQL);
	}
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigJobInfoDAO#save(person.daizhongde.migration.hibernate.pojo.MigJobInfo)
	 */
	@Override
	public void save(MigJobInfo transientInstance) {
		log.debug("saving MigJobInfo instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigJobInfoDAO#delete(person.daizhongde.migration.hibernate.pojo.MigJobInfo)
	 */
	@Override
	public void delete(MigJobInfo persistentInstance) {
		log.debug("deleting MigJobInfo instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigJobInfoDAO#findById(java.lang.String)
	 */
	@Override
	public MigJobInfo findById(java.lang.String id) {
//		log.debug("getting MigJobInfo instance with id: " + id);
		try {
			MigJobInfo instance = (MigJobInfo) getHibernateTemplate().get(
					"person.daizhongde.migration.hibernate.pojo.MigJobInfo", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	public String findStatusById(java.lang.String id) {
//		log.debug("find job status by id: " + id);
		try {
			String status = (String)this.sqlQueryfindaValue(
					"select status from tool.mig_job_info where job_id='"+id+"'", "status");
			return status;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigJobInfoDAO#findByExample(person.daizhongde.migration.hibernate.pojo.MigJobInfo)
	 */
	@Override
	public List findByExample(MigJobInfo instance) {
		log.debug("finding MigJobInfo instance by example");
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
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigJobInfoDAO#findByProperty(java.lang.String, java.lang.Object)
	 */
	@Override
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding MigJobInfo instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from MigJobInfo as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigJobInfoDAO#findByJobName(java.lang.Object)
	 */
	@Override
	public List findByJobName(Object jobName) {
		return findByProperty(JOB_NAME, jobName);
	}
	@Override
	public Long findCountByJobName(String jobName) {
		Long total = Long.valueOf( this.sqlQueryfindaValue(
				"select count(*) from tool.mig_job_info where job_Name='"+jobName+"'").toString() );
		return total;
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigJobInfoDAO#findByJobCron(java.lang.Object)
	 */
	@Override
	public List findByJobCron(Object jobCron) {
		return findByProperty(JOB_CRON, jobCron);
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigJobInfoDAO#findByJobAuthor(java.lang.Object)
	 */
	@Override
	public List findByJobAuthor(Object jobAuthor) {
		return findByProperty(JOB_AUTHOR, jobAuthor);
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigJobInfoDAO#findByJobRemark(java.lang.Object)
	 */
	@Override
	public List findByJobRemark(Object jobRemark) {
		return findByProperty(JOB_REMARK, jobRemark);
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigJobInfoDAO#findAll()
	 */
	@Override
	public List findAll() {
		log.debug("finding all MigJobInfo instances");
		try {
			String queryString = "from MigJobInfo";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigJobInfoDAO#merge(person.daizhongde.migration.hibernate.pojo.MigJobInfo)
	 */
	@Override
	public MigJobInfo merge(MigJobInfo detachedInstance) {
		log.debug("merging MigJobInfo instance");
		try {
			MigJobInfo result = (MigJobInfo) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigJobInfoDAO#attachDirty(person.daizhongde.migration.hibernate.pojo.MigJobInfo)
	 */
	@Override
	public void attachDirty(MigJobInfo instance) {
		log.debug("attaching dirty MigJobInfo instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigJobInfoDAO#attachClean(person.daizhongde.migration.hibernate.pojo.MigJobInfo)
	 */
	@Override
	public void attachClean(MigJobInfo instance) {
		log.debug("attaching clean MigJobInfo instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static MigJobInfoDAO getFromApplicationContext(ApplicationContext ctx) {
		return (MigJobInfoDAO) ctx.getBean("migJobInfoDAO");
	}
}