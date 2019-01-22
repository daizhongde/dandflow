package person.daizhongde.migration.hibernate.dao.impl;

import java.util.List;

import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import person.daizhongde.virtue.dao.SpringHibernateDaoSupport;

import person.daizhongde.migration.hibernate.dao.MigInsConfigDAO;
import person.daizhongde.migration.hibernate.pojo.MigInsConfig;

/**
 	* A data access object (DAO) providing persistence and search support for MigInsConfig entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigInsConfig
  * @author MyEclipse Persistence Tools 
 */
public class MigInsConfigDAOImpl extends SpringHibernateDaoSupport implements MigInsConfigDAO  {
	     private static final Logger log = LoggerFactory.getLogger(MigInsConfigDAOImpl.class);
		//property constants



	protected void initDao() {
		//do nothing
	}
    
    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigInsConfigDAO#save(person.daizhongde.migration.hibernate.pojo.MigInsConfig)
	 */
    @Override
	public void save(MigInsConfig transientInstance) {
        log.debug("saving MigInsConfig instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigInsConfigDAO#delete(person.daizhongde.migration.hibernate.pojo.MigInsConfig)
	 */
	@Override
	public void delete(MigInsConfig persistentInstance) {
        log.debug("deleting MigInsConfig instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigInsConfigDAO#findById(person.daizhongde.migration.hibernate.pojo.MigInsConfigId)
	 */
    @Override
	public MigInsConfig findById( person.daizhongde.migration.hibernate.pojo.MigInsConfigId id) {
        log.debug("getting MigInsConfig instance with id: " + id);
        try {
            MigInsConfig instance = (MigInsConfig) getHibernateTemplate()
                    .get("person.daizhongde.migration.hibernate.pojo.MigInsConfig", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigInsConfigDAO#findByExample(person.daizhongde.migration.hibernate.pojo.MigInsConfig)
	 */
    @Override
	public List findByExample(MigInsConfig instance) {
        log.debug("finding MigInsConfig instance by example");
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
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigInsConfigDAO#findByProperty(java.lang.String, java.lang.Object)
	 */
    @Override
	public List findByProperty(String propertyName, Object value) {
      log.debug("finding MigInsConfig instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from MigInsConfig as model where model." 
         						+ propertyName + "= ?";
		 return getHibernateTemplate().find(queryString, value);
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}


	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigInsConfigDAO#findAll()
	 */
	@Override
	public List findAll() {
		log.debug("finding all MigInsConfig instances");
		try {
			String queryString = "from MigInsConfig";
		 	return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigInsConfigDAO#merge(person.daizhongde.migration.hibernate.pojo.MigInsConfig)
	 */
    @Override
	public MigInsConfig merge(MigInsConfig detachedInstance) {
        log.debug("merging MigInsConfig instance");
        try {
            MigInsConfig result = (MigInsConfig) getHibernateTemplate()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigInsConfigDAO#attachDirty(person.daizhongde.migration.hibernate.pojo.MigInsConfig)
	 */
    @Override
	public void attachDirty(MigInsConfig instance) {
        log.debug("attaching dirty MigInsConfig instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigInsConfigDAO#attachClean(person.daizhongde.migration.hibernate.pojo.MigInsConfig)
	 */
    @Override
	public void attachClean(MigInsConfig instance) {
        log.debug("attaching clean MigInsConfig instance");
        try {
                      	getHibernateTemplate().lock(instance, LockMode.NONE);
                        log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

	public static MigInsConfigDAO getFromApplicationContext(ApplicationContext ctx) {
    	return (MigInsConfigDAO) ctx.getBean("MigInsConfigDAO");
	}
}