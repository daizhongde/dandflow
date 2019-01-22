package person.daizhongde.migration.hibernate.dao.impl;

import java.util.List;

import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import person.daizhongde.virtue.dao.SpringHibernateDaoSupport;

import person.daizhongde.migration.hibernate.dao.MigDataPrecheckDAO;
import person.daizhongde.migration.hibernate.pojo.MigDataPrecheck;

/**
 	* A data access object (DAO) providing persistence and search support for MigDataPrecheck entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigDataPrecheck
  * @author MyEclipse Persistence Tools 
 */
public class MigDataPrecheckDAOImpl extends SpringHibernateDaoSupport implements MigDataPrecheckDAO  {
	     private static final Logger log = LoggerFactory.getLogger(MigDataPrecheckDAOImpl.class);
		protected void initDao() {
		//do nothing
	}
    
    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigDataPrecheckDAO#save(person.daizhongde.migration.hibernate.pojo.MigDataPrecheck)
	 */
    @Override
	public void save(MigDataPrecheck transientInstance) {
        log.debug("saving MigDataPrecheck instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigDataPrecheckDAO#delete(person.daizhongde.migration.hibernate.pojo.MigDataPrecheck)
	 */
	@Override
	public void delete(MigDataPrecheck persistentInstance) {
        log.debug("deleting MigDataPrecheck instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigDataPrecheckDAO#findById(java.lang.String)
	 */
    @Override
	public MigDataPrecheck findById( java.lang.String id) {
        log.debug("getting MigDataPrecheck instance with id: " + id);
        try {
            MigDataPrecheck instance = (MigDataPrecheck) getHibernateTemplate()
                    .get("person.daizhongde.migration.hibernate.pojo.MigDataPrecheck", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigDataPrecheckDAO#findByExample(person.daizhongde.migration.hibernate.pojo.MigDataPrecheck)
	 */
    @Override
	public List findByExample(MigDataPrecheck instance) {
        log.debug("finding MigDataPrecheck instance by example");
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
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigDataPrecheckDAO#findByProperty(java.lang.String, java.lang.Object)
	 */
    @Override
	public List findByProperty(String propertyName, Object value) {
      log.debug("finding MigDataPrecheck instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from MigDataPrecheck as model where model." 
         						+ propertyName + "= ?";
		 return getHibernateTemplate().find(queryString, value);
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigDataPrecheckDAO#findByDryrunId(java.lang.Object)
	 */
	@Override
	public List findByDryrunId(Object dryrunId
	) {
		return findByProperty(DRYRUN_ID, dryrunId
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigDataPrecheckDAO#findByCheckEnv(java.lang.Object)
	 */
	@Override
	public List findByCheckEnv(Object checkEnv
	) {
		return findByProperty(CHECK_ENV, checkEnv
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigDataPrecheckDAO#findByCheckType(java.lang.Object)
	 */
	@Override
	public List findByCheckType(Object checkType
	) {
		return findByProperty(CHECK_TYPE, checkType
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigDataPrecheckDAO#findByCheckObject(java.lang.Object)
	 */
	@Override
	public List findByCheckObject(Object checkObject
	) {
		return findByProperty(CHECK_OBJECT, checkObject
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigDataPrecheckDAO#findByCheckCount(java.lang.Object)
	 */
	@Override
	public List findByCheckCount(Object checkCount
	) {
		return findByProperty(CHECK_COUNT, checkCount
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigDataPrecheckDAO#findByCheckField(java.lang.Object)
	 */
	@Override
	public List findByCheckField(Object checkField
	) {
		return findByProperty(CHECK_FIELD, checkField
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigDataPrecheckDAO#findByCheckRemark(java.lang.Object)
	 */
	@Override
	public List findByCheckRemark(Object checkRemark
	) {
		return findByProperty(CHECK_REMARK, checkRemark
		);
	}
	

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigDataPrecheckDAO#findAll()
	 */
	@Override
	public List findAll() {
		log.debug("finding all MigDataPrecheck instances");
		try {
			String queryString = "from MigDataPrecheck";
		 	return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigDataPrecheckDAO#merge(person.daizhongde.migration.hibernate.pojo.MigDataPrecheck)
	 */
    @Override
	public MigDataPrecheck merge(MigDataPrecheck detachedInstance) {
        log.debug("merging MigDataPrecheck instance");
        try {
            MigDataPrecheck result = (MigDataPrecheck) getHibernateTemplate()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigDataPrecheckDAO#attachDirty(person.daizhongde.migration.hibernate.pojo.MigDataPrecheck)
	 */
    @Override
	public void attachDirty(MigDataPrecheck instance) {
        log.debug("attaching dirty MigDataPrecheck instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigDataPrecheckDAO#attachClean(person.daizhongde.migration.hibernate.pojo.MigDataPrecheck)
	 */
    @Override
	public void attachClean(MigDataPrecheck instance) {
        log.debug("attaching clean MigDataPrecheck instance");
        try {
                      	getHibernateTemplate().lock(instance, LockMode.NONE);
                        log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

	public static MigDataPrecheckDAO getFromApplicationContext(ApplicationContext ctx) {
    	return (MigDataPrecheckDAO) ctx.getBean("MigDataPrecheckDAO");
	}
}