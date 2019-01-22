package person.daizhongde.migration.hibernate.dao.impl;

import java.util.List;

import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import person.daizhongde.virtue.dao.SpringHibernateDaoSupport;

import person.daizhongde.migration.hibernate.dao.MigBusiDomainDAO;
import person.daizhongde.migration.hibernate.pojo.MigBusiDomain;

/**
 	* A data access object (DAO) providing persistence and search support for MigBusiDomain entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see person.daizhongde.migration.hibernate.pojo.MigBusiDomain
  * @author MyEclipse Persistence Tools 
 */
public class MigBusiDomainDAOImpl extends SpringHibernateDaoSupport implements MigBusiDomainDAO  {
	     private static final Logger log = LoggerFactory.getLogger(MigBusiDomainDAOImpl.class);
		protected void initDao() {
		//do nothing
	}
    
    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigBusiDomainDAO#save(person.daizhongde.migration.hibernate.pojo.MigBusiDomain)
	 */
    @Override
	public void save(MigBusiDomain transientInstance) {
        log.debug("saving MigBusiDomain instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigBusiDomainDAO#delete(person.daizhongde.migration.hibernate.pojo.MigBusiDomain)
	 */
	@Override
	public void delete(MigBusiDomain persistentInstance) {
        log.debug("deleting MigBusiDomain instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigBusiDomainDAO#findById(java.lang.Integer)
	 */
    @Override
	public MigBusiDomain findById( java.lang.Integer id) {
        log.debug("getting MigBusiDomain instance with id: " + id);
        try {
            MigBusiDomain instance = (MigBusiDomain) getHibernateTemplate()
                    .get("person.daizhongde.migration.hibernate.pojo.MigBusiDomain", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigBusiDomainDAO#findByExample(person.daizhongde.migration.hibernate.pojo.MigBusiDomain)
	 */
    @Override
	public List findByExample(MigBusiDomain instance) {
        log.debug("finding MigBusiDomain instance by example");
        try {
            List results = getHibernateTemplate().findByExample(instance);
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }    
    
    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigBusiDomainDAO#findByProperty(java.lang.String, java.lang.Object)
	 */
    @Override
	public List findByProperty(String propertyName, Object value) {
      log.debug("finding MigBusiDomain instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from MigBusiDomain as model where model." 
         						+ propertyName + "= ?";
		 return getHibernateTemplate().find(queryString, value);
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigBusiDomainDAO#findByName(java.lang.Object)
	 */
	@Override
	public List findByName(Object name
	) {
		return findByProperty(NAME, name
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigBusiDomainDAO#findByRemark(java.lang.Object)
	 */
	@Override
	public List findByRemark(Object remark
	) {
		return findByProperty(REMARK, remark
		);
	}
	

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigBusiDomainDAO#findAll()
	 */
	@Override
	public List findAll() {
		log.debug("finding all MigBusiDomain instances");
		try {
			String queryString = "from MigBusiDomain";
		 	return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	public List findAll(String order_column) {
		log.debug("finding all MigBusiDomain instances");
		try {
			String queryString = "from MigBusiDomain order by "+order_column;
		 	return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigBusiDomainDAO#merge(person.daizhongde.migration.hibernate.pojo.MigBusiDomain)
	 */
    @Override
	public MigBusiDomain merge(MigBusiDomain detachedInstance) {
        log.debug("merging MigBusiDomain instance");
        try {
            MigBusiDomain result = (MigBusiDomain) getHibernateTemplate()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigBusiDomainDAO#attachDirty(person.daizhongde.migration.hibernate.pojo.MigBusiDomain)
	 */
    @Override
	public void attachDirty(MigBusiDomain instance) {
        log.debug("attaching dirty MigBusiDomain instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigBusiDomainDAO#attachClean(person.daizhongde.migration.hibernate.pojo.MigBusiDomain)
	 */
    @Override
	public void attachClean(MigBusiDomain instance) {
        log.debug("attaching clean MigBusiDomain instance");
        try {
                      	getHibernateTemplate().lock(instance, LockMode.NONE);
                        log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

	public static MigBusiDomainDAO getFromApplicationContext(ApplicationContext ctx) {
    	return (MigBusiDomainDAO) ctx.getBean("migBusiDomainDAO");
	}
}