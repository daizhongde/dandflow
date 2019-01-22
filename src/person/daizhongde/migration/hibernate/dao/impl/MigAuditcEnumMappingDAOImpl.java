package person.daizhongde.migration.hibernate.dao.impl;

import java.util.List;

import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import person.daizhongde.virtue.dao.SpringHibernateDaoSupport;

import person.daizhongde.migration.hibernate.dao.MigAuditcEnumMappingDAO;
import person.daizhongde.migration.hibernate.pojo.MigAuditcEnumMapping;

/**
 	* A data access object (DAO) providing persistence and search support for MigAuditcEnumMapping entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditcEnumMapping
  * @author MyEclipse Persistence Tools 
 */
public class MigAuditcEnumMappingDAOImpl extends SpringHibernateDaoSupport implements MigAuditcEnumMappingDAO  {
	     private static final Logger log = LoggerFactory.getLogger(MigAuditcEnumMappingDAOImpl.class);
		protected void initDao() {
		//do nothing
	}
    
    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditcEnumMappingDAO#save(person.daizhongde.migration.hibernate.pojo.MigAuditcEnumMapping)
	 */
    @Override
	public void save(MigAuditcEnumMapping transientInstance) {
        log.debug("saving MigAuditcEnumMapping instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditcEnumMappingDAO#delete(person.daizhongde.migration.hibernate.pojo.MigAuditcEnumMapping)
	 */
	@Override
	public void delete(MigAuditcEnumMapping persistentInstance) {
        log.debug("deleting MigAuditcEnumMapping instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditcEnumMappingDAO#findById(java.lang.Integer)
	 */
    @Override
	public MigAuditcEnumMapping findById( java.lang.Integer id) {
        log.debug("getting MigAuditcEnumMapping instance with id: " + id);
        try {
            MigAuditcEnumMapping instance = (MigAuditcEnumMapping) getHibernateTemplate()
                    .get("person.daizhongde.migration.hibernate.pojo.MigAuditcEnumMapping", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditcEnumMappingDAO#findByExample(person.daizhongde.migration.hibernate.pojo.MigAuditcEnumMapping)
	 */
    @Override
	public List findByExample(MigAuditcEnumMapping instance) {
        log.debug("finding MigAuditcEnumMapping instance by example");
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
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditcEnumMappingDAO#findByProperty(java.lang.String, java.lang.Object)
	 */
    @Override
	public List findByProperty(String propertyName, Object value) {
      log.debug("finding MigAuditcEnumMapping instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from MigAuditcEnumMapping as model where model." 
         						+ propertyName + "= ?";
		 return getHibernateTemplate().find(queryString, value);
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditcEnumMappingDAO#findByEntity(java.lang.Object)
	 */
	@Override
	public List findByEntity(Object entity
	) {
		return findByProperty(ENTITY, entity
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditcEnumMappingDAO#findByAuditName(java.lang.Object)
	 */
	@Override
	public List findByAuditName(Object auditName
	) {
		return findByProperty(AUDIT_NAME, auditName
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditcEnumMappingDAO#findByEnumDesc(java.lang.Object)
	 */
	@Override
	public List findByEnumDesc(Object enumDesc
	) {
		return findByProperty(ENUM_DESC, enumDesc
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditcEnumMappingDAO#findBySrcEnum(java.lang.Object)
	 */
	@Override
	public List findBySrcEnum(Object srcEnum
	) {
		return findByProperty(SRC_ENUM, srcEnum
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditcEnumMappingDAO#findByDstEnum(java.lang.Object)
	 */
	@Override
	public List findByDstEnum(Object dstEnum
	) {
		return findByProperty(DST_ENUM, dstEnum
		);
	}
	

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditcEnumMappingDAO#findAll()
	 */
	@Override
	public List findAll() {
		log.debug("finding all MigAuditcEnumMapping instances");
		try {
			String queryString = "from MigAuditcEnumMapping";
		 	return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditcEnumMappingDAO#merge(person.daizhongde.migration.hibernate.pojo.MigAuditcEnumMapping)
	 */
    @Override
	public MigAuditcEnumMapping merge(MigAuditcEnumMapping detachedInstance) {
        log.debug("merging MigAuditcEnumMapping instance");
        try {
            MigAuditcEnumMapping result = (MigAuditcEnumMapping) getHibernateTemplate()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditcEnumMappingDAO#attachDirty(person.daizhongde.migration.hibernate.pojo.MigAuditcEnumMapping)
	 */
    @Override
	public void attachDirty(MigAuditcEnumMapping instance) {
        log.debug("attaching dirty MigAuditcEnumMapping instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditcEnumMappingDAO#attachClean(person.daizhongde.migration.hibernate.pojo.MigAuditcEnumMapping)
	 */
    @Override
	public void attachClean(MigAuditcEnumMapping instance) {
        log.debug("attaching clean MigAuditcEnumMapping instance");
        try {
                      	getHibernateTemplate().lock(instance, LockMode.NONE);
                        log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

	public static MigAuditcEnumMappingDAO getFromApplicationContext(ApplicationContext ctx) {
    	return (MigAuditcEnumMappingDAO) ctx.getBean("MigAuditcEnumMappingDAO");
	}
}