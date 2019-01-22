package person.daizhongde.migration.hibernate.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import person.daizhongde.virtue.dao.SpringHibernateDaoSupport;

import person.daizhongde.migration.hibernate.dao.MigJobLogDAO;
import person.daizhongde.migration.hibernate.pojo.MigJobLog;

/**
 * A data access object (DAO) providing persistence and search support for
 * MigJobLog entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see person.daizhongde.migration.hibernate.dao.impl.MigJobLog
 * @author MyEclipse Persistence Tools
 */
public class MigJobLogDAOImpl extends SpringHibernateDaoSupport implements MigJobLogDAO{
	private static final Logger log = LoggerFactory
			.getLogger(MigJobLogDAOImpl.class);
	protected void initDao() {
		// do nothing
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigJobLogDAO#save(person.daizhongde.migration.hibernate.pojo.MigJobLog)
	 */
	@Override
	public void save(MigJobLog transientInstance) {
		log.debug("saving MigJobLog instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigJobLogDAO#delete(person.daizhongde.migration.hibernate.pojo.MigJobLog)
	 */
	@Override
	public void delete(MigJobLog persistentInstance) {
		log.debug("deleting MigJobLog instance");
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
		String sql = "delete from mig_job_log where JOB_INS_ID=:jobInsId";
		this.sqlQueryExeUByMap(sql, condition);	
	}
	public void deleteByInsId(List<String> jobInsId){
		Map condition = new HashMap(1);
		condition.put("jobInsId", jobInsId);
		String sql = "delete from mig_job_log where JOB_INS_ID in (:jobInsId)";
		this.sqlQueryExeUByMap(sql, condition);	
	}
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigJobLogDAO#findById(java.lang.String)
	 */
	@Override
	public MigJobLog findById(java.lang.String id) {
		log.debug("getting MigJobLog instance with id: " + id);
		try {
			MigJobLog instance = (MigJobLog) getHibernateTemplate().get(
					"person.daizhongde.migration.hibernate.pojotemp.MigJobLog", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigJobLogDAO#findByExample(person.daizhongde.migration.hibernate.pojo.MigJobLog)
	 */
	@Override
	public List findByExample(MigJobLog instance) {
		log.debug("finding MigJobLog instance by example");
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
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigJobLogDAO#findByProperty(java.lang.String, java.lang.Object)
	 */
	@Override
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding MigJobLog instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from MigJobLog as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigJobLogDAO#findByTaskId(java.lang.Object)
	 */
	@Override
	public List findByTaskId(Object taskId) {
		return findByProperty(TASK_ID, taskId);
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigJobLogDAO#findByLevel(java.lang.Object)
	 */
	@Override
	public List findByLevel(Object level) {
		return findByProperty(LEVEL, level);
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigJobLogDAO#findByLogMsg(java.lang.Object)
	 */
	@Override
	public List findByLogMsg(Object logMsg) {
		return findByProperty(LOG_MSG, logMsg);
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigJobLogDAO#findByRemark(java.lang.Object)
	 */
	@Override
	public List findByRemark(Object remark) {
		return findByProperty(REMARK, remark);
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigJobLogDAO#findAll()
	 */
	@Override
	public List findAll() {
		log.debug("finding all MigJobLog instances");
		try {
			String queryString = "from MigJobLog";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigJobLogDAO#merge(person.daizhongde.migration.hibernate.pojo.MigJobLog)
	 */
	@Override
	public MigJobLog merge(MigJobLog detachedInstance) {
		log.debug("merging MigJobLog instance");
		try {
			MigJobLog result = (MigJobLog) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigJobLogDAO#attachDirty(person.daizhongde.migration.hibernate.pojo.MigJobLog)
	 */
	@Override
	public void attachDirty(MigJobLog instance) {
		log.debug("attaching dirty MigJobLog instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigJobLogDAO#attachClean(person.daizhongde.migration.hibernate.pojo.MigJobLog)
	 */
	@Override
	public void attachClean(MigJobLog instance) {
		log.debug("attaching clean MigJobLog instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static MigJobLogDAO getFromApplicationContext(ApplicationContext ctx) {
		return (MigJobLogDAO) ctx.getBean("migJobLogDAO");
	}
}