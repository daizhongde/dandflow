package person.daizhongde.migration.hibernate.dao.impl;

import java.util.List;

import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import person.daizhongde.virtue.dao.SpringHibernateDaoSupport;

import person.daizhongde.migration.hibernate.dao.TPubDictionaryDAO;
import person.daizhongde.migration.hibernate.pojo.TPubDictionary;

/**
 	* A data access object (DAO) providing persistence and search support for TPubDictionary entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see person.daizhongde.migration.hibernate.dao.impl.TPubDictionary
  * @author MyEclipse Persistence Tools 
 */
public class TPubDictionaryDAOImpl extends SpringHibernateDaoSupport implements TPubDictionaryDAO  {
	     private static final Logger log = LoggerFactory.getLogger(TPubDictionaryDAOImpl.class);
		protected void initDao() {
		//do nothing
	}
		
	public List queryListByInfo(TPubDictionary instance) {
        log.debug("queryListByInfo");
        try {
            List results = getHibernateTemplate().findByExample(instance);
            log.debug("queryListByInfo successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("queryListByInfo failed", re);
            throw re;
        }
    }    
		
    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.TPubDictionaryDAO#save(person.daizhongde.migration.hibernate.pojo.TPubDictionary)
	 */
    @Override
	public void save(TPubDictionary transientInstance) {
        log.debug("saving TPubDictionary instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.TPubDictionaryDAO#delete(person.daizhongde.migration.hibernate.pojo.TPubDictionary)
	 */
	@Override
	public void delete(TPubDictionary persistentInstance) {
        log.debug("deleting TPubDictionary instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.TPubDictionaryDAO#findById(java.lang.Integer)
	 */
    @Override
	public TPubDictionary findById( java.lang.Integer id) {
        log.debug("getting TPubDictionary instance with id: " + id);
        try {
            TPubDictionary instance = (TPubDictionary) getHibernateTemplate()
                    .get("person.daizhongde.migration.hibernate.pojo.TPubDictionary", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.TPubDictionaryDAO#findByExample(person.daizhongde.migration.hibernate.pojo.TPubDictionary)
	 */
    @Override
	public List findByExample(TPubDictionary instance) {
        log.debug("finding TPubDictionary instance by example");
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
	 * @see person.daizhongde.migration.hibernate.dao.impl.TPubDictionaryDAO#findByProperty(java.lang.String, java.lang.Object)
	 */
    @Override
	public List findByProperty(String propertyName, Object value) {
      log.debug("finding TPubDictionary instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from TPubDictionary as model where model." 
         						+ propertyName + "= ?";
		 return getHibernateTemplate().find(queryString, value);
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.TPubDictionaryDAO#findByCode(java.lang.Object)
	 */
	@Override
	public List findByCode(Object code
	) {
		return findByProperty(CODE, code
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.TPubDictionaryDAO#findByValue(java.lang.Object)
	 */
	@Override
	public List findByValue(Object value
	) {
		return findByProperty(VALUE, value
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.TPubDictionaryDAO#findByType(java.lang.Object)
	 */
	@Override
	public List findByType(Object type
	) {
		return findByProperty(TYPE, type
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.TPubDictionaryDAO#findByParentid(java.lang.Object)
	 */
	@Override
	public List findByParentid(Object parentid
	) {
		return findByProperty(PARENTID, parentid
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.TPubDictionaryDAO#findByVersion(java.lang.Object)
	 */
	@Override
	public List findByVersion(Object version
	) {
		return findByProperty(VERSION, version
		);
	}
	

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.TPubDictionaryDAO#findAll()
	 */
	@Override
	public List findAll() {
		log.debug("finding all TPubDictionary instances");
		try {
			String queryString = "from TPubDictionary";
		 	return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.TPubDictionaryDAO#merge(person.daizhongde.migration.hibernate.pojo.TPubDictionary)
	 */
    @Override
	public TPubDictionary merge(TPubDictionary detachedInstance) {
        log.debug("merging TPubDictionary instance");
        try {
            TPubDictionary result = (TPubDictionary) getHibernateTemplate()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.TPubDictionaryDAO#attachDirty(person.daizhongde.migration.hibernate.pojo.TPubDictionary)
	 */
    @Override
	public void attachDirty(TPubDictionary instance) {
        log.debug("attaching dirty TPubDictionary instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.TPubDictionaryDAO#attachClean(person.daizhongde.migration.hibernate.pojo.TPubDictionary)
	 */
    @Override
	public void attachClean(TPubDictionary instance) {
        log.debug("attaching clean TPubDictionary instance");
        try {
                      	getHibernateTemplate().lock(instance, LockMode.NONE);
                        log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

	public static TPubDictionaryDAO getFromApplicationContext(ApplicationContext ctx) {
    	return (TPubDictionaryDAO) ctx.getBean("TPubDictionaryDAO");
	}
}