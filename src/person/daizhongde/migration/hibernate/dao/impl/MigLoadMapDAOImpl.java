package person.daizhongde.migration.hibernate.dao.impl;

import java.util.List;

import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import person.daizhongde.virtue.dao.SpringHibernateDaoSupport;

import person.daizhongde.migration.hibernate.dao.MigLoadMapDAO;
import person.daizhongde.migration.hibernate.pojo.MigLoadMap;

/**
 * A data access object (DAO) providing persistence and search support for
 * MigLoadMap entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see person.daizhongde.migration.hibernate.dao.impl.MigLoadMap
 * @author MyEclipse Persistence Tools
 */
public class MigLoadMapDAOImpl extends SpringHibernateDaoSupport implements MigLoadMapDAO {
	private static final Logger log = LoggerFactory
			.getLogger(MigLoadMapDAOImpl.class);

	// property constants

	protected void initDao() {
		// do nothing
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigLoadMapDAO#save(person.daizhongde.migration.hibernate.pojo.MigLoadMap)
	 */
	@Override
	public void save(MigLoadMap transientInstance) {
		log.debug("saving MigLoadMap instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigLoadMapDAO#delete(person.daizhongde.migration.hibernate.pojo.MigLoadMap)
	 */
	@Override
	public void delete(MigLoadMap persistentInstance) {
		log.debug("deleting MigLoadMap instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigLoadMapDAO#findById(person.daizhongde.migration.hibernate.pojo.MigLoadMapId)
	 */
	@Override
	public MigLoadMap findById(
			person.daizhongde.migration.hibernate.pojo.MigLoadMapId id) {
		log.debug("getting MigLoadMap instance with id: " + id);
		try {
			MigLoadMap instance = (MigLoadMap) getHibernateTemplate().get(
					"person.daizhongde.migration.hibernate.pojo.MigLoadMap", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigLoadMapDAO#findByExample(person.daizhongde.migration.hibernate.pojo.MigLoadMap)
	 */
	@Override
	public List findByExample(MigLoadMap instance) {
		log.debug("finding MigLoadMap instance by example");
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
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigLoadMapDAO#findByProperty(java.lang.String, java.lang.Object)
	 */
	@Override
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding MigLoadMap instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from MigLoadMap as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigLoadMapDAO#findAll()
	 */
	@Override
	public List findAll() {
		log.debug("finding all MigLoadMap instances");
		try {
			String queryString = "from MigLoadMap";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigLoadMapDAO#merge(person.daizhongde.migration.hibernate.pojo.MigLoadMap)
	 */
	@Override
	public MigLoadMap merge(MigLoadMap detachedInstance) {
		log.debug("merging MigLoadMap instance");
		try {
			MigLoadMap result = (MigLoadMap) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigLoadMapDAO#attachDirty(person.daizhongde.migration.hibernate.pojo.MigLoadMap)
	 */
	@Override
	public void attachDirty(MigLoadMap instance) {
		log.debug("attaching dirty MigLoadMap instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigLoadMapDAO#attachClean(person.daizhongde.migration.hibernate.pojo.MigLoadMap)
	 */
	@Override
	public void attachClean(MigLoadMap instance) {
		log.debug("attaching clean MigLoadMap instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static MigLoadMapDAO getFromApplicationContext(ApplicationContext ctx) {
		return (MigLoadMapDAO) ctx.getBean("migLoadMapDAO");
	}
}