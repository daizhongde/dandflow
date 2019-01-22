package person.daizhongde.migration.hibernate.dao.impl;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import person.daizhongde.virtue.dao.SpringHibernateDaoSupport;

import person.daizhongde.migration.hibernate.dao.TChatMsgDAO;
import person.daizhongde.migration.hibernate.pojo.TChatMsg;

/**
 	* A data access object (DAO) providing persistence and search support for TChatMsg entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see person.daizhongde.migration.hibernate.dao.impl.TChatMsg
  * @author MyEclipse Persistence Tools 
 */
public class TChatMsgDAOImpl extends SpringHibernateDaoSupport implements TChatMsgDAO  {
	     private static final Logger log = LoggerFactory.getLogger(TChatMsgDAOImpl.class);
		protected void initDao() {
		//do nothing
	}
    
    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.TChatMsgDAO#save(person.daizhongde.migration.hibernate.pojo.TChatMsg)
	 */
    @Override
	public void save(TChatMsg transientInstance) {
        log.debug("saving TChatMsg instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.TChatMsgDAO#delete(person.daizhongde.migration.hibernate.pojo.TChatMsg)
	 */
	@Override
	public void delete(TChatMsg persistentInstance) {
        log.debug("deleting TChatMsg instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.TChatMsgDAO#findById(java.lang.Integer)
	 */
    @Override
	public TChatMsg findById( java.lang.Integer id) {
        log.debug("getting TChatMsg instance with id: " + id);
        try {
            TChatMsg instance = (TChatMsg) getHibernateTemplate()
                    .get("person.daizhongde.migration.hibernate.pojo.TChatMsg", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.TChatMsgDAO#findByExample(person.daizhongde.migration.hibernate.pojo.TChatMsg)
	 */
    @Override
	public List findByExample(TChatMsg instance) {
        log.debug("finding TChatMsg instance by example");
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
	 * @see person.daizhongde.migration.hibernate.dao.impl.TChatMsgDAO#findByProperty(java.lang.String, java.lang.Object)
	 */
    @Override
	public List findByProperty(String propertyName, Object value) {
      log.debug("finding TChatMsg instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from TChatMsg as model where model." 
         						+ propertyName + "= ?";
		 return getHibernateTemplate().find(queryString, value);
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.TChatMsgDAO#findByNUid(java.lang.Object)
	 */
	@Override
	public List findByNUid(Object NUid
	) {
		return findByProperty(_NUID, NUid
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.TChatMsgDAO#findByCMsg(java.lang.Object)
	 */
	@Override
	public List findByCMsg(Object CMsg
	) {
		return findByProperty(_CMSG, CMsg
		);
	}
	

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.TChatMsgDAO#findAll()
	 */
	@Override
	public List findAll() {
		log.debug("finding all TChatMsg instances");
		try {
			String queryString = "from TChatMsg";
		 	return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.TChatMsgDAO#merge(person.daizhongde.migration.hibernate.pojo.TChatMsg)
	 */
    @Override
	public TChatMsg merge(TChatMsg detachedInstance) {
        log.debug("merging TChatMsg instance");
        try {
            TChatMsg result = (TChatMsg) getHibernateTemplate()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.TChatMsgDAO#attachDirty(person.daizhongde.migration.hibernate.pojo.TChatMsg)
	 */
    @Override
	public void attachDirty(TChatMsg instance) {
        log.debug("attaching dirty TChatMsg instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.TChatMsgDAO#attachClean(person.daizhongde.migration.hibernate.pojo.TChatMsg)
	 */
    @Override
	public void attachClean(TChatMsg instance) {
        log.debug("attaching clean TChatMsg instance");
        try {
                      	getHibernateTemplate().lock(instance, LockMode.NONE);
                        log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

	public static TChatMsgDAO getFromApplicationContext(ApplicationContext ctx) {
    	return (TChatMsgDAO) ctx.getBean("TChatMsgDAO");
	}
}