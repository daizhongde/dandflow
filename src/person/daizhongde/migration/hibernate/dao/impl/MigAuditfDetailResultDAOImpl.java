package person.daizhongde.migration.hibernate.dao.impl;

import java.util.List;

import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import person.daizhongde.virtue.dao.SpringHibernateDaoSupport;

import person.daizhongde.migration.hibernate.dao.MigAuditfDetailResultDAO;
import person.daizhongde.migration.hibernate.pojo.MigAuditfDetailResult;

/**
 	* A data access object (DAO) providing persistence and search support for MigAuditfDetailResult entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditfDetailResult
  * @author MyEclipse Persistence Tools 
 */
public class MigAuditfDetailResultDAOImpl extends SpringHibernateDaoSupport implements MigAuditfDetailResultDAO  {
	     private static final Logger log = LoggerFactory.getLogger(MigAuditfDetailResultDAOImpl.class);
		//property constants



	protected void initDao() {
		//do nothing
	}
    
    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditfDetailResultDAO#save(person.daizhongde.migration.hibernate.pojo.MigAuditfDetailResult)
	 */
    @Override
	public void save(MigAuditfDetailResult transientInstance) {
        log.debug("saving MigAuditfDetailResult instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditfDetailResultDAO#delete(person.daizhongde.migration.hibernate.pojo.MigAuditfDetailResult)
	 */
	@Override
	public void delete(MigAuditfDetailResult persistentInstance) {
        log.debug("deleting MigAuditfDetailResult instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditfDetailResultDAO#findById(person.daizhongde.migration.hibernate.pojo.MigAuditfDetailResultId)
	 */
    @Override
	public MigAuditfDetailResult findById( person.daizhongde.migration.hibernate.pojo.MigAuditfDetailResultId id) {
        log.debug("getting MigAuditfDetailResult instance with id: " + id);
        try {
            MigAuditfDetailResult instance = (MigAuditfDetailResult) getHibernateTemplate()
                    .get("person.daizhongde.migration.hibernate.pojo.MigAuditfDetailResult", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditfDetailResultDAO#findByExample(person.daizhongde.migration.hibernate.pojo.MigAuditfDetailResult)
	 */
    @Override
	public List findByExample(MigAuditfDetailResult instance) {
        log.debug("finding MigAuditfDetailResult instance by example");
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
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditfDetailResultDAO#findByProperty(java.lang.String, java.lang.Object)
	 */
    @Override
	public List findByProperty(String propertyName, Object value) {
      log.debug("finding MigAuditfDetailResult instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from MigAuditfDetailResult as model where model." 
         						+ propertyName + "= ?";
		 return getHibernateTemplate().find(queryString, value);
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}


	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditfDetailResultDAO#findAll()
	 */
	@Override
	public List findAll() {
		log.debug("finding all MigAuditfDetailResult instances");
		try {
			String queryString = "from MigAuditfDetailResult";
		 	return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditfDetailResultDAO#merge(person.daizhongde.migration.hibernate.pojo.MigAuditfDetailResult)
	 */
    @Override
	public MigAuditfDetailResult merge(MigAuditfDetailResult detachedInstance) {
        log.debug("merging MigAuditfDetailResult instance");
        try {
            MigAuditfDetailResult result = (MigAuditfDetailResult) getHibernateTemplate()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditfDetailResultDAO#attachDirty(person.daizhongde.migration.hibernate.pojo.MigAuditfDetailResult)
	 */
    @Override
	public void attachDirty(MigAuditfDetailResult instance) {
        log.debug("attaching dirty MigAuditfDetailResult instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditfDetailResultDAO#attachClean(person.daizhongde.migration.hibernate.pojo.MigAuditfDetailResult)
	 */
    @Override
	public void attachClean(MigAuditfDetailResult instance) {
        log.debug("attaching clean MigAuditfDetailResult instance");
        try {
                      	getHibernateTemplate().lock(instance, LockMode.NONE);
                        log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

	public static MigAuditfDetailResultDAO getFromApplicationContext(ApplicationContext ctx) {
    	return (MigAuditfDetailResultDAO) ctx.getBean("MigAuditfDetailResultDAO");
	}
}