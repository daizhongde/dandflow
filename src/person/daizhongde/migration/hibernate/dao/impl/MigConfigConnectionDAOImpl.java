package person.daizhongde.migration.hibernate.dao.impl;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import person.daizhongde.migration.hibernate.dao.MigConfigConnectionDAO;
import person.daizhongde.migration.hibernate.pojo.MigConfigConnection;

/**
 * A data access object (DAO) providing persistence and search support for
 * MigConfigConnection entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see person.daizhongde.migration.hibernate.dao.MigConfigConnectionDAO
 * @author MyEclipse Persistence Tools
 */
public class MigConfigConnectionDAOImpl extends HibernateDaoSupport implements MigConfigConnectionDAO {
	private static final Logger log = LoggerFactory
			.getLogger(MigConfigConnectionDAOImpl.class);
	protected void initDao() {
		// do nothing
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigConfigConnection#save(person.daizhongde.migration.hibernate.pojo.MigConfigConnection)
	 */
	@Override
	public void save(MigConfigConnection transientInstance) {
		log.debug("saving MigConfigConnection instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigConfigConnection#delete(person.daizhongde.migration.hibernate.pojo.MigConfigConnection)
	 */
	@Override
	public void delete(MigConfigConnection persistentInstance) {
		log.debug("deleting MigConfigConnection instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigConfigConnection#findById(java.lang.Integer)
	 */
	@Override
	public MigConfigConnection findById(java.lang.Integer id) {
		log.debug("getting MigConfigConnection instance with id: " + id);
		try {
			MigConfigConnection instance = (MigConfigConnection) getHibernateTemplate()
					.get("person.daizhongde.migration.hibernate.pojo.MigConfigConnection",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigConfigConnection#findByExample(person.daizhongde.migration.hibernate.pojo.MigConfigConnection)
	 */
	@Override
	public List findByExample(MigConfigConnection instance) {
		log.debug("finding MigConfigConnection instance by example");
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
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigConfigConnection#findByProperty(java.lang.String, java.lang.Object)
	 */
	@Override
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding MigConfigConnection instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from MigConfigConnection as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigConfigConnection#findByUrl(java.lang.Object)
	 */
	@Override
	public List findByUrl(Object url) {
		return findByProperty(URL, url);
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigConfigConnection#findByRemark(java.lang.Object)
	 */
	@Override
	public List findByRemark(Object remark) {
		return findByProperty(REMARK, remark);
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigConfigConnection#findAll()
	 */
	@Override
	public List findAll() {
		log.debug("finding all MigConfigConnection instances");
		try {
			String queryString = "from MigConfigConnection";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigConfigConnection#merge(person.daizhongde.migration.hibernate.pojo.MigConfigConnection)
	 */
	@Override
	public MigConfigConnection merge(MigConfigConnection detachedInstance) {
		log.debug("merging MigConfigConnection instance");
		try {
			MigConfigConnection result = (MigConfigConnection) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigConfigConnection#attachDirty(person.daizhongde.migration.hibernate.pojo.MigConfigConnection)
	 */
	@Override
	public void attachDirty(MigConfigConnection instance) {
		log.debug("attaching dirty MigConfigConnection instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigConfigConnection#attachClean(person.daizhongde.migration.hibernate.pojo.MigConfigConnection)
	 */
	@Override
	public void attachClean(MigConfigConnection instance) {
		log.debug("attaching clean MigConfigConnection instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static MigConfigConnectionDAOImpl getFromApplicationContext(
			ApplicationContext ctx) {
		return (MigConfigConnectionDAOImpl) ctx.getBean("migConfigConnectionDAO");
	}
}