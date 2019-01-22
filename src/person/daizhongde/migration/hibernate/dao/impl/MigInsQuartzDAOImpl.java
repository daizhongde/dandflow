package person.daizhongde.migration.hibernate.dao.impl;

import java.util.List;

import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import person.daizhongde.virtue.dao.SpringHibernateDaoSupport;

import person.daizhongde.migration.hibernate.dao.MigInsQuartzDAO;
import person.daizhongde.migration.hibernate.pojo.MigInsQuartz;

/**
 * A data access object (DAO) providing persistence and search support for
 * MigInsQuartz entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see person.daizhongde.migration.hibernate.pojo.MigInsQuartz
 * @author MyEclipse Persistence Tools
 */
public class MigInsQuartzDAOImpl extends SpringHibernateDaoSupport implements MigInsQuartzDAO {
	private static final Logger log = LoggerFactory
			.getLogger(MigInsQuartzDAOImpl.class);
	protected void initDao() {
		// do nothing
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigInsQuartzDAO#save(person.daizhongde.migration.hibernate.pojo.MigInsQuartz)
	 */
	@Override
	public void save(MigInsQuartz transientInstance) {
		log.debug("saving MigInsQuartz instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	public void update(MigInsQuartz persistentInstance) {
		log.debug("update MigInsQuartz instance");
		try {
			getHibernateTemplate().save(persistentInstance);
			log.debug("update successful");
		} catch (RuntimeException re) {
			log.error("update failed", re);
			throw re;
		}
	}
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigInsQuartzDAO#delete(person.daizhongde.migration.hibernate.pojo.MigInsQuartz)
	 */
	@Override
	public void delete(MigInsQuartz persistentInstance) {
		log.debug("deleting MigInsQuartz instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigInsQuartzDAO#findById(java.lang.Integer)
	 */
	@Override
	public MigInsQuartz findById(java.lang.Integer id) {
		log.debug("getting MigInsQuartz instance with id: " + id);
		try {
			MigInsQuartz instance = (MigInsQuartz) getHibernateTemplate().get(
					"person.daizhongde.migration.hibernate.pojo.MigInsQuartz",
					id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigInsQuartzDAO#findByExample(person.daizhongde.migration.hibernate.pojo.MigInsQuartz)
	 */
	@Override
	public List findByExample(MigInsQuartz instance) {
		log.debug("finding MigInsQuartz instance by example");
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
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigInsQuartzDAO#findByProperty(java.lang.String, java.lang.Object)
	 */
	@Override
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding MigInsQuartz instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from MigInsQuartz as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigInsQuartzDAO#findByCaseId(java.lang.Object)
	 */
	@Override
	public List findByCaseId(Object caseId) {
		return findByProperty(CASE_ID, caseId);
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigInsQuartzDAO#findByJobName(java.lang.Object)
	 */
	@Override
	public List findByJobName(Object jobName) {
		return findByProperty(JOB_NAME, jobName);
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigInsQuartzDAO#findByJobGroup(java.lang.Object)
	 */
	@Override
	public List findByJobGroup(Object jobGroup) {
		return findByProperty(JOB_GROUP, jobGroup);
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigInsQuartzDAO#findByCronExpression(java.lang.Object)
	 */
	@Override
	public List findByCronExpression(Object cronExpression) {
		return findByProperty(CRON_EXPRESSION, cronExpression);
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigInsQuartzDAO#findByBeanClass(java.lang.Object)
	 */
	@Override
	public List findByBeanClass(Object beanClass) {
		return findByProperty(BEAN_CLASS, beanClass);
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigInsQuartzDAO#findByMethodName(java.lang.Object)
	 */
	@Override
	public List findByMethodName(Object methodName) {
		return findByProperty(METHOD_NAME, methodName);
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigInsQuartzDAO#findAll()
	 */
	@Override
	public List findAll() {
		log.debug("finding all MigInsQuartz instances");
		try {
			String queryString = "from MigInsQuartz";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigInsQuartzDAO#merge(person.daizhongde.migration.hibernate.pojo.MigInsQuartz)
	 */
	@Override
	public MigInsQuartz merge(MigInsQuartz detachedInstance) {
		log.debug("merging MigInsQuartz instance");
		try {
			MigInsQuartz result = (MigInsQuartz) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigInsQuartzDAO#attachDirty(person.daizhongde.migration.hibernate.pojo.MigInsQuartz)
	 */
	@Override
	public void attachDirty(MigInsQuartz instance) {
		log.debug("attaching dirty MigInsQuartz instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigInsQuartzDAO#attachClean(person.daizhongde.migration.hibernate.pojo.MigInsQuartz)
	 */
	@Override
	public void attachClean(MigInsQuartz instance) {
		log.debug("attaching clean MigInsQuartz instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static MigInsQuartzDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (MigInsQuartzDAO) ctx.getBean("MigInsQuartzDAO");
	}
}