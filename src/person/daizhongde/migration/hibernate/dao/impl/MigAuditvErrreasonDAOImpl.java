package person.daizhongde.migration.hibernate.dao.impl;

import java.util.List;

import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import person.daizhongde.virtue.dao.SpringHibernateDaoSupport;

import person.daizhongde.migration.hibernate.dao.MigAuditvErrreasonDAO;
import person.daizhongde.migration.hibernate.pojo.MigAuditvErrreason;

/**
 	* A data access object (DAO) providing persistence and search support for MigAuditvErrreason entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditvErrreason
  * @author MyEclipse Persistence Tools 
 */
public class MigAuditvErrreasonDAOImpl extends SpringHibernateDaoSupport implements MigAuditvErrreasonDAO  {
	     private static final Logger log = LoggerFactory.getLogger(MigAuditvErrreasonDAOImpl.class);
		protected void initDao() {
		//do nothing
	}
    
    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditvErrreasonDAO#save(person.daizhongde.migration.hibernate.pojo.MigAuditvErrreason)
	 */
    @Override
	public void save(MigAuditvErrreason transientInstance) {
        log.debug("saving MigAuditvErrreason instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditvErrreasonDAO#delete(person.daizhongde.migration.hibernate.pojo.MigAuditvErrreason)
	 */
	@Override
	public void delete(MigAuditvErrreason persistentInstance) {
        log.debug("deleting MigAuditvErrreason instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditvErrreasonDAO#findById(person.daizhongde.migration.hibernate.pojo.MigAuditvErrreasonId)
	 */
    @Override
	public MigAuditvErrreason findById( person.daizhongde.migration.hibernate.pojo.MigAuditvErrreasonId id) {
        log.debug("getting MigAuditvErrreason instance with id: " + id);
        try {
            MigAuditvErrreason instance = (MigAuditvErrreason) getHibernateTemplate()
                    .get("person.daizhongde.migration.hibernate.pojo.MigAuditvErrreason", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditvErrreasonDAO#findByExample(person.daizhongde.migration.hibernate.pojo.MigAuditvErrreason)
	 */
    @Override
	public List findByExample(MigAuditvErrreason instance) {
        log.debug("finding MigAuditvErrreason instance by example");
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
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditvErrreasonDAO#findByProperty(java.lang.String, java.lang.Object)
	 */
    @Override
	public List findByProperty(String propertyName, Object value) {
      log.debug("finding MigAuditvErrreason instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from MigAuditvErrreason as model where model." 
         						+ propertyName + "= ?";
		 return getHibernateTemplate().find(queryString, value);
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditvErrreasonDAO#findByDmpNo(java.lang.Object)
	 */
	@Override
	public List findByDmpNo(Object dmpNo
	) {
		return findByProperty(DMP_NO, dmpNo
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditvErrreasonDAO#findByReason(java.lang.Object)
	 */
	@Override
	public List findByReason(Object reason
	) {
		return findByProperty(REASON, reason
		);
	}
	

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditvErrreasonDAO#findAll()
	 */
	@Override
	public List findAll() {
		log.debug("finding all MigAuditvErrreason instances");
		try {
			String queryString = "from MigAuditvErrreason";
		 	return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditvErrreasonDAO#merge(person.daizhongde.migration.hibernate.pojo.MigAuditvErrreason)
	 */
    @Override
	public MigAuditvErrreason merge(MigAuditvErrreason detachedInstance) {
        log.debug("merging MigAuditvErrreason instance");
        try {
            MigAuditvErrreason result = (MigAuditvErrreason) getHibernateTemplate()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditvErrreasonDAO#attachDirty(person.daizhongde.migration.hibernate.pojo.MigAuditvErrreason)
	 */
    @Override
	public void attachDirty(MigAuditvErrreason instance) {
        log.debug("attaching dirty MigAuditvErrreason instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditvErrreasonDAO#attachClean(person.daizhongde.migration.hibernate.pojo.MigAuditvErrreason)
	 */
    @Override
	public void attachClean(MigAuditvErrreason instance) {
        log.debug("attaching clean MigAuditvErrreason instance");
        try {
                      	getHibernateTemplate().lock(instance, LockMode.NONE);
                        log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

	public static MigAuditvErrreasonDAO getFromApplicationContext(ApplicationContext ctx) {
    	return (MigAuditvErrreasonDAO) ctx.getBean("MigAuditvErrreasonDAO");
	}
}