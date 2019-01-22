package person.daizhongde.migration.hibernate.dao.impl;

import java.util.List;

import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import person.daizhongde.virtue.dao.SpringHibernateDaoSupport;

import person.daizhongde.migration.hibernate.dao.MigAuditfFieldResultDAO;
import person.daizhongde.migration.hibernate.pojo.MigAuditfFieldResult;

/**
 	* A data access object (DAO) providing persistence and search support for MigAuditfFieldResult entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditfFieldResult
  * @author MyEclipse Persistence Tools 
 */
public class MigAuditfFieldResultDAOImpl extends SpringHibernateDaoSupport implements MigAuditfFieldResultDAO  {
	     private static final Logger log = LoggerFactory.getLogger(MigAuditfFieldResultDAOImpl.class);
		//property constants



	protected void initDao() {
		//do nothing
	}
    
    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditfFieldResultDAO#save(person.daizhongde.migration.hibernate.pojo.MigAuditfFieldResult)
	 */
    @Override
	public void save(MigAuditfFieldResult transientInstance) {
        log.debug("saving MigAuditfFieldResult instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditfFieldResultDAO#delete(person.daizhongde.migration.hibernate.pojo.MigAuditfFieldResult)
	 */
	@Override
	public void delete(MigAuditfFieldResult persistentInstance) {
        log.debug("deleting MigAuditfFieldResult instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditfFieldResultDAO#findById(person.daizhongde.migration.hibernate.pojo.MigAuditfFieldResultId)
	 */
    @Override
	public MigAuditfFieldResult findById( person.daizhongde.migration.hibernate.pojo.MigAuditfFieldResultId id) {
        log.debug("getting MigAuditfFieldResult instance with id: " + id);
        try {
            MigAuditfFieldResult instance = (MigAuditfFieldResult) getHibernateTemplate()
                    .get("person.daizhongde.migration.hibernate.pojo.MigAuditfFieldResult", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditfFieldResultDAO#findByExample(person.daizhongde.migration.hibernate.pojo.MigAuditfFieldResult)
	 */
    @Override
	public List findByExample(MigAuditfFieldResult instance) {
        log.debug("finding MigAuditfFieldResult instance by example");
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
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditfFieldResultDAO#findByProperty(java.lang.String, java.lang.Object)
	 */
    @Override
	public List findByProperty(String propertyName, Object value) {
      log.debug("finding MigAuditfFieldResult instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from MigAuditfFieldResult as model where model." 
         						+ propertyName + "= ?";
		 return getHibernateTemplate().find(queryString, value);
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}


	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditfFieldResultDAO#findAll()
	 */
	@Override
	public List findAll() {
		log.debug("finding all MigAuditfFieldResult instances");
		try {
			String queryString = "from MigAuditfFieldResult";
		 	return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditfFieldResultDAO#merge(person.daizhongde.migration.hibernate.pojo.MigAuditfFieldResult)
	 */
    @Override
	public MigAuditfFieldResult merge(MigAuditfFieldResult detachedInstance) {
        log.debug("merging MigAuditfFieldResult instance");
        try {
            MigAuditfFieldResult result = (MigAuditfFieldResult) getHibernateTemplate()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditfFieldResultDAO#attachDirty(person.daizhongde.migration.hibernate.pojo.MigAuditfFieldResult)
	 */
    @Override
	public void attachDirty(MigAuditfFieldResult instance) {
        log.debug("attaching dirty MigAuditfFieldResult instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditfFieldResultDAO#attachClean(person.daizhongde.migration.hibernate.pojo.MigAuditfFieldResult)
	 */
    @Override
	public void attachClean(MigAuditfFieldResult instance) {
        log.debug("attaching clean MigAuditfFieldResult instance");
        try {
                      	getHibernateTemplate().lock(instance, LockMode.NONE);
                        log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

	public static MigAuditfFieldResultDAO getFromApplicationContext(ApplicationContext ctx) {
    	return (MigAuditfFieldResultDAO) ctx.getBean("MigAuditfFieldResultDAO");
	}
}