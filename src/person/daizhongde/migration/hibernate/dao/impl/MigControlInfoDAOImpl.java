package person.daizhongde.migration.hibernate.dao.impl;

import java.util.List;

import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import person.daizhongde.virtue.dao.SpringHibernateDaoSupport;

import person.daizhongde.migration.hibernate.dao.MigControlInfoDAO;
import person.daizhongde.migration.hibernate.pojo.MigControlInfo;

/**
 * A data access object (DAO) providing persistence and search support for
 * MigControlInfo entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see person.daizhongde.migration.hibernate.dao.impl.MigControlInfo
 * @author MyEclipse Persistence Tools
 */
public class MigControlInfoDAOImpl extends SpringHibernateDaoSupport implements MigControlInfoDAO {
	private static final Logger log = LoggerFactory
			.getLogger(MigControlInfoDAOImpl.class);
	protected void initDao() {
		// do nothing
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigControlInfoDAO#save(person.daizhongde.migration.hibernate.pojo.MigControlInfo)
	 */
	@Override
	public void save(MigControlInfo transientInstance) {
		log.debug("saving MigControlInfo instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigControlInfoDAO#delete(person.daizhongde.migration.hibernate.pojo.MigControlInfo)
	 */
	@Override
	public void delete(MigControlInfo persistentInstance) {
		log.debug("deleting MigControlInfo instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigControlInfoDAO#findById(java.lang.String)
	 */
	@Override
	public MigControlInfo findById(java.lang.String id) {
		log.debug("getting MigControlInfo instance with id: " + id);
		try {
			MigControlInfo instance = (MigControlInfo) getHibernateTemplate()
					.get("person.daizhongde.migration.hibernate.pojo.MigControlInfo",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigControlInfoDAO#findByExample(person.daizhongde.migration.hibernate.pojo.MigControlInfo)
	 */
	@Override
	public List findByExample(MigControlInfo instance) {
		log.debug("finding MigControlInfo instance by example");
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
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigControlInfoDAO#findByProperty(java.lang.String, java.lang.Object)
	 */
	@Override
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding MigControlInfo instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from MigControlInfo as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigControlInfoDAO#findByControlName(java.lang.Object)
	 */
	@Override
	public List findByControlName(Object controlName) {
		return findByProperty(CONTROL_NAME, controlName);
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigControlInfoDAO#findByControlMark(java.lang.Object)
	 */
	@Override
	public List findByControlMark(Object controlMark) {
		return findByProperty(CONTROL_MARK, controlMark);
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigControlInfoDAO#findAll()
	 */
	@Override
	public List findAll() {
		log.debug("finding all MigControlInfo instances");
		try {
			String queryString = "from MigControlInfo order by controlId";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigControlInfoDAO#merge(person.daizhongde.migration.hibernate.pojo.MigControlInfo)
	 */
	@Override
	public MigControlInfo merge(MigControlInfo detachedInstance) {
		log.debug("merging MigControlInfo instance");
		try {
			MigControlInfo result = (MigControlInfo) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigControlInfoDAO#attachDirty(person.daizhongde.migration.hibernate.pojo.MigControlInfo)
	 */
	@Override
	public void attachDirty(MigControlInfo instance) {
		log.debug("attaching dirty MigControlInfo instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigControlInfoDAO#attachClean(person.daizhongde.migration.hibernate.pojo.MigControlInfo)
	 */
	@Override
	public void attachClean(MigControlInfo instance) {
		log.debug("attaching clean MigControlInfo instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static MigControlInfoDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (MigControlInfoDAO) ctx.getBean("migControlInfoDAO");
	}
}