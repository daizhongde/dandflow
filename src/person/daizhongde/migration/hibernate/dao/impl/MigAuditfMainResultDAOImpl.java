package person.daizhongde.migration.hibernate.dao.impl;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import person.daizhongde.virtue.dao.SpringHibernateDaoSupport;

import person.daizhongde.migration.hibernate.dao.MigAuditfMainResultDAO;
import person.daizhongde.migration.hibernate.pojo.MigAuditfMainResult;

/**
 	* A data access object (DAO) providing persistence and search support for MigAuditfMainResult entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditfMainResult
  * @author MyEclipse Persistence Tools 
 */
public class MigAuditfMainResultDAOImpl extends SpringHibernateDaoSupport implements MigAuditfMainResultDAO  {
    private static final Logger log = LoggerFactory.getLogger(MigAuditfMainResultDAOImpl.class);
	protected void initDao() {
	//do nothing
	}
    
    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditfMainResultDAO#save(person.daizhongde.migration.hibernate.pojo.MigAuditfMainResult)
	 */
    @Override
	public void save(MigAuditfMainResult transientInstance) {
        log.debug("saving MigAuditfMainResult instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditfMainResultDAO#delete(person.daizhongde.migration.hibernate.pojo.MigAuditfMainResult)
	 */
	@Override
	public void delete(MigAuditfMainResult persistentInstance) {
        log.debug("deleting MigAuditfMainResult instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditfMainResultDAO#findById(person.daizhongde.migration.hibernate.pojo.MigAuditfMainResultId)
	 */
    @Override
	public MigAuditfMainResult findById( person.daizhongde.migration.hibernate.pojo.MigAuditfMainResultId id) {
        log.debug("getting MigAuditfMainResult instance with id: " + id);
        try {
            MigAuditfMainResult instance = (MigAuditfMainResult) getHibernateTemplate()
                    .get("person.daizhongde.migration.hibernate.pojo.MigAuditfMainResult", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditfMainResultDAO#findByExample(person.daizhongde.migration.hibernate.pojo.MigAuditfMainResult)
	 */
    @Override
	public List findByExample(MigAuditfMainResult instance) {
        log.debug("finding MigAuditfMainResult instance by example");
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
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditfMainResultDAO#findByProperty(java.lang.String, java.lang.Object)
	 */
    @Override
	public List findByProperty(String propertyName, Object value) {
      log.debug("finding MigAuditfMainResult instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from MigAuditfMainResult as model where model." 
         						+ propertyName + "= ?";
		 return getHibernateTemplate().find(queryString, value);
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditfMainResultDAO#findByFaresSrcCount(java.lang.Object)
	 */
	@Override
	public List findByFaresSrcCount(Object faresSrcCount
	) {
		return findByProperty(FARES_SRC_COUNT, faresSrcCount
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditfMainResultDAO#findByFaresDstCount(java.lang.Object)
	 */
	@Override
	public List findByFaresDstCount(Object faresDstCount
	) {
		return findByProperty(FARES_DST_COUNT, faresDstCount
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditfMainResultDAO#findByFaresSrcPasscnt(java.lang.Object)
	 */
	@Override
	public List findByFaresSrcPasscnt(Object faresSrcPasscnt
	) {
		return findByProperty(FARES_SRC_PASSCNT, faresSrcPasscnt
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditfMainResultDAO#findByFaresKeyPasscnt(java.lang.Object)
	 */
	@Override
	public List findByFaresKeyPasscnt(Object faresKeyPasscnt
	) {
		return findByProperty(FARES_KEY_PASSCNT, faresKeyPasscnt
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditfMainResultDAO#findByFaresSrcMore(java.lang.Object)
	 */
	@Override
	public List findByFaresSrcMore(Object faresSrcMore
	) {
		return findByProperty(FARES_SRC_MORE, faresSrcMore
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditfMainResultDAO#findByFaresDstMore(java.lang.Object)
	 */
	@Override
	public List findByFaresDstMore(Object faresDstMore
	) {
		return findByProperty(FARES_DST_MORE, faresDstMore
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditfMainResultDAO#findByFaresKeyUnmatch(java.lang.Object)
	 */
	@Override
	public List findByFaresKeyUnmatch(Object faresKeyUnmatch
	) {
		return findByProperty(FARES_KEY_UNMATCH, faresKeyUnmatch
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditfMainResultDAO#findByFaresElseUnmatch(java.lang.Object)
	 */
	@Override
	public List findByFaresElseUnmatch(Object faresElseUnmatch
	) {
		return findByProperty(FARES_ELSE_UNMATCH, faresElseUnmatch
		);
	}
	

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditfMainResultDAO#findAll()
	 */
	@Override
	public List findAll() {
		log.debug("finding all MigAuditfMainResult instances");
		try {
			String queryString = "from MigAuditfMainResult";
		 	return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditfMainResultDAO#merge(person.daizhongde.migration.hibernate.pojo.MigAuditfMainResult)
	 */
    @Override
	public MigAuditfMainResult merge(MigAuditfMainResult detachedInstance) {
        log.debug("merging MigAuditfMainResult instance");
        try {
            MigAuditfMainResult result = (MigAuditfMainResult) getHibernateTemplate()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditfMainResultDAO#attachDirty(person.daizhongde.migration.hibernate.pojo.MigAuditfMainResult)
	 */
    @Override
	public void attachDirty(MigAuditfMainResult instance) {
        log.debug("attaching dirty MigAuditfMainResult instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditfMainResultDAO#attachClean(person.daizhongde.migration.hibernate.pojo.MigAuditfMainResult)
	 */
    @Override
	public void attachClean(MigAuditfMainResult instance) {
        log.debug("attaching clean MigAuditfMainResult instance");
        try {
                      	getHibernateTemplate().lock(instance, LockMode.NONE);
                        log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

	public static MigAuditfMainResultDAO getFromApplicationContext(ApplicationContext ctx) {
    	return (MigAuditfMainResultDAO) ctx.getBean("MigAuditfMainResultDAO");
	}
}