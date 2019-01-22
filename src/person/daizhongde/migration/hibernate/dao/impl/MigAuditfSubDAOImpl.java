package person.daizhongde.migration.hibernate.dao.impl;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import person.daizhongde.virtue.dao.SpringHibernateDaoSupport;

import person.daizhongde.migration.hibernate.dao.MigAuditfSubDAO;
import person.daizhongde.migration.hibernate.pojo.MigAuditfSub;

/**
 * A data access object (DAO) providing persistence and search support for
 * MigAuditfSub entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditfSub
 * @author MyEclipse Persistence Tools
 */
public class MigAuditfSubDAOImpl extends SpringHibernateDaoSupport implements MigAuditfSubDAO {
	private static final Logger log = LoggerFactory
			.getLogger(MigAuditfSubDAOImpl.class);
	protected void initDao() {
		// do nothing
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditfSubDAO#save(person.daizhongde.migration.hibernate.pojo.MigAuditfSub)
	 */
	@Override
	public void save(MigAuditfSub transientInstance) {
		log.debug("saving MigAuditfSub instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditfSubDAO#delete(person.daizhongde.migration.hibernate.pojo.MigAuditfSub)
	 */
	@Override
	public void delete(MigAuditfSub persistentInstance) {
		log.debug("deleting MigAuditfSub instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditfSubDAO#findById(person.daizhongde.migration.hibernate.pojo.MigAuditfSubId)
	 */
	@Override
	public MigAuditfSub findById(
			person.daizhongde.migration.hibernate.pojo.MigAuditfSubId id) {
		log.debug("getting MigAuditfSub instance with id: " + id);
		try {
			MigAuditfSub instance = (MigAuditfSub) getHibernateTemplate().get(
					"person.daizhongde.migration.hibernate.dao.impl.MigAuditfSub",
					id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditfSubDAO#findByExample(person.daizhongde.migration.hibernate.pojo.MigAuditfSub)
	 */
	@Override
	public List findByExample(MigAuditfSub instance) {
		log.debug("finding MigAuditfSub instance by example");
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
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditfSubDAO#findByProperty(java.lang.String, java.lang.Object)
	 */
	@Override
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding MigAuditfSub instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from MigAuditfSub as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditfSubDAO#findByFauditSrcField(java.lang.Object)
	 */
	@Override
	public List findByFauditSrcField(Object fauditSrcField) {
		return findByProperty(FAUDIT_SRC_FIELD, fauditSrcField);
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditfSubDAO#findByFauditDstField(java.lang.Object)
	 */
	@Override
	public List findByFauditDstField(Object fauditDstField) {
		return findByProperty(FAUDIT_DST_FIELD, fauditDstField);
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditfSubDAO#findByFauditIskey(java.lang.Object)
	 */
	@Override
	public List findByFauditIskey(Object fauditIskey) {
		return findByProperty(FAUDIT_ISKEY, fauditIskey);
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditfSubDAO#findByFauditOpt(java.lang.Object)
	 */
	@Override
	public List findByFauditOpt(Object fauditOpt) {
		return findByProperty(FAUDIT_OPT, fauditOpt);
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditfSubDAO#findByFauditStatus(java.lang.Object)
	 */
	@Override
	public List findByFauditStatus(Object fauditStatus) {
		return findByProperty(FAUDIT_STATUS, fauditStatus);
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditfSubDAO#findAll()
	 */
	@Override
	public List findAll() {
		log.debug("finding all MigAuditfSub instances");
		try {
			String queryString = "from MigAuditfSub";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditfSubDAO#merge(person.daizhongde.migration.hibernate.pojo.MigAuditfSub)
	 */
	@Override
	public MigAuditfSub merge(MigAuditfSub detachedInstance) {
		log.debug("merging MigAuditfSub instance");
		try {
			MigAuditfSub result = (MigAuditfSub) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditfSubDAO#attachDirty(person.daizhongde.migration.hibernate.pojo.MigAuditfSub)
	 */
	@Override
	public void attachDirty(MigAuditfSub instance) {
		log.debug("attaching dirty MigAuditfSub instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditfSubDAO#attachClean(person.daizhongde.migration.hibernate.pojo.MigAuditfSub)
	 */
	@Override
	public void attachClean(MigAuditfSub instance) {
		log.debug("attaching clean MigAuditfSub instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static MigAuditfSubDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (MigAuditfSubDAO) ctx.getBean("MigAuditfSubDAO");
	}
}