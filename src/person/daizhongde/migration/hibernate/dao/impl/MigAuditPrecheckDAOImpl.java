package person.daizhongde.migration.hibernate.dao.impl;

import java.util.List;

import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import person.daizhongde.virtue.dao.SpringHibernateDaoSupport;

import person.daizhongde.migration.hibernate.dao.MigAuditPrecheckDAO;
import person.daizhongde.migration.hibernate.pojo.MigAuditPrecheck;

/**
 	* A data access object (DAO) providing persistence and search support for MigAuditPrecheck entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditPrecheck
  * @author MyEclipse Persistence Tools 
 */
public class MigAuditPrecheckDAOImpl extends SpringHibernateDaoSupport implements MigAuditPrecheckDAO  {
	     private static final Logger log = LoggerFactory.getLogger(MigAuditPrecheckDAOImpl.class);
		protected void initDao() {
		//do nothing
	}
    
    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditPrecheckDAO#save(person.daizhongde.migration.hibernate.pojo.MigAuditPrecheck)
	 */
    @Override
	public void save(MigAuditPrecheck transientInstance) {
        log.debug("saving MigAuditPrecheck instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditPrecheckDAO#delete(person.daizhongde.migration.hibernate.pojo.MigAuditPrecheck)
	 */
	@Override
	public void delete(MigAuditPrecheck persistentInstance) {
        log.debug("deleting MigAuditPrecheck instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditPrecheckDAO#findById(java.lang.Integer)
	 */
    @Override
	public MigAuditPrecheck findById( java.lang.Integer id) {
        log.debug("getting MigAuditPrecheck instance with id: " + id);
        try {
            MigAuditPrecheck instance = (MigAuditPrecheck) getHibernateTemplate()
                    .get("person.daizhongde.migration.hibernate.pojo.MigAuditPrecheck", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditPrecheckDAO#findByExample(person.daizhongde.migration.hibernate.pojo.MigAuditPrecheck)
	 */
    @Override
	public List findByExample(MigAuditPrecheck instance) {
        log.debug("finding MigAuditPrecheck instance by example");
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
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditPrecheckDAO#findByProperty(java.lang.String, java.lang.Object)
	 */
    @Override
	public List findByProperty(String propertyName, Object value) {
      log.debug("finding MigAuditPrecheck instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from MigAuditPrecheck as model where model." 
         						+ propertyName + "= ?";
		 return getHibernateTemplate().find(queryString, value);
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditPrecheckDAO#findByAuditEnv(java.lang.Object)
	 */
	@Override
	public List findByAuditEnv(Object auditEnv
	) {
		return findByProperty(AUDIT_ENV, auditEnv
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditPrecheckDAO#findByAuditType(java.lang.Object)
	 */
	@Override
	public List findByAuditType(Object auditType
	) {
		return findByProperty(AUDIT_TYPE, auditType
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditPrecheckDAO#findByAuditMode(java.lang.Object)
	 */
	@Override
	public List findByAuditMode(Object auditMode
	) {
		return findByProperty(AUDIT_MODE, auditMode
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditPrecheckDAO#findByAuditObject(java.lang.Object)
	 */
	@Override
	public List findByAuditObject(Object auditObject
	) {
		return findByProperty(AUDIT_OBJECT, auditObject
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditPrecheckDAO#findByDryrunFront(java.lang.Object)
	 */
	@Override
	public List findByDryrunFront(Object dryrunFront
	) {
		return findByProperty(DRYRUN_FRONT, dryrunFront
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditPrecheckDAO#findByDryrunBack(java.lang.Object)
	 */
	@Override
	public List findByDryrunBack(Object dryrunBack
	) {
		return findByProperty(DRYRUN_BACK, dryrunBack
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditPrecheckDAO#findByCountFront(java.lang.Object)
	 */
	@Override
	public List findByCountFront(Object countFront
	) {
		return findByProperty(COUNT_FRONT, countFront
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditPrecheckDAO#findByCountBack(java.lang.Object)
	 */
	@Override
	public List findByCountBack(Object countBack
	) {
		return findByProperty(COUNT_BACK, countBack
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditPrecheckDAO#findByDiffRatio(java.lang.Object)
	 */
	@Override
	public List findByDiffRatio(Object diffRatio
	) {
		return findByProperty(DIFF_RATIO, diffRatio
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditPrecheckDAO#findByAuditRemark(java.lang.Object)
	 */
	@Override
	public List findByAuditRemark(Object auditRemark
	) {
		return findByProperty(AUDIT_REMARK, auditRemark
		);
	}
	

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditPrecheckDAO#findAll()
	 */
	@Override
	public List findAll() {
		log.debug("finding all MigAuditPrecheck instances");
		try {
			String queryString = "from MigAuditPrecheck";
		 	return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditPrecheckDAO#merge(person.daizhongde.migration.hibernate.pojo.MigAuditPrecheck)
	 */
    @Override
	public MigAuditPrecheck merge(MigAuditPrecheck detachedInstance) {
        log.debug("merging MigAuditPrecheck instance");
        try {
            MigAuditPrecheck result = (MigAuditPrecheck) getHibernateTemplate()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditPrecheckDAO#attachDirty(person.daizhongde.migration.hibernate.pojo.MigAuditPrecheck)
	 */
    @Override
	public void attachDirty(MigAuditPrecheck instance) {
        log.debug("attaching dirty MigAuditPrecheck instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditPrecheckDAO#attachClean(person.daizhongde.migration.hibernate.pojo.MigAuditPrecheck)
	 */
    @Override
	public void attachClean(MigAuditPrecheck instance) {
        log.debug("attaching clean MigAuditPrecheck instance");
        try {
                      	getHibernateTemplate().lock(instance, LockMode.NONE);
                        log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

	public static MigAuditPrecheckDAO getFromApplicationContext(ApplicationContext ctx) {
    	return (MigAuditPrecheckDAO) ctx.getBean("MigAuditPrecheckDAO");
	}
}