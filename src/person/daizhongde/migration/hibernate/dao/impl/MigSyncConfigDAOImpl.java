package person.daizhongde.migration.hibernate.dao.impl;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import person.daizhongde.virtue.dao.SpringHibernateDaoSupport;

import person.daizhongde.migration.hibernate.dao.MigSyncConfigDAO;
import person.daizhongde.migration.hibernate.pojo.MigSyncConfig;

/**
 	* A data access object (DAO) providing persistence and search support for MigSyncConfig entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigSyncConfig
  * @author MyEclipse Persistence Tools 
 */
public class MigSyncConfigDAOImpl extends SpringHibernateDaoSupport implements MigSyncConfigDAO  {
	     private static final Logger log = LoggerFactory.getLogger(MigSyncConfigDAOImpl.class);
		protected void initDao() {
		//do nothing
	}
    
    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigSyncConfigDAO#save(person.daizhongde.migration.hibernate.pojo.MigSyncConfig)
	 */
    @Override
	public void save(MigSyncConfig transientInstance) {
        log.debug("saving MigSyncConfig instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigSyncConfigDAO#delete(person.daizhongde.migration.hibernate.pojo.MigSyncConfig)
	 */
	@Override
	public void delete(MigSyncConfig persistentInstance) {
        log.debug("deleting MigSyncConfig instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigSyncConfigDAO#findById(java.lang.Integer)
	 */
    @Override
	public MigSyncConfig findById( java.lang.Integer id) {
        log.debug("getting MigSyncConfig instance with id: " + id);
        try {
            MigSyncConfig instance = (MigSyncConfig) getHibernateTemplate()
                    .get("person.daizhongde.migration.hibernate.pojo.MigSyncConfig", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigSyncConfigDAO#findByExample(person.daizhongde.migration.hibernate.pojo.MigSyncConfig)
	 */
    @Override
	public List findByExample(MigSyncConfig instance) {
        log.debug("finding MigSyncConfig instance by example");
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
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigSyncConfigDAO#findByProperty(java.lang.String, java.lang.Object)
	 */
    @Override
	public List findByProperty(String propertyName, Object value) {
      log.debug("finding MigSyncConfig instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from MigSyncConfig as model where model." 
         						+ propertyName + "= ?";
		 return getHibernateTemplate().find(queryString, value);
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigSyncConfigDAO#findByMigGroup(java.lang.Object)
	 */
	@Override
	public List findByMigGroup(Object migGroup
	) {
		return findByProperty(MIG_GROUP, migGroup
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigSyncConfigDAO#findByMigTable(java.lang.Object)
	 */
	@Override
	public List findByMigTable(Object migTable
	) {
		return findByProperty(MIG_TABLE, migTable
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigSyncConfigDAO#findByMigAuthor(java.lang.Object)
	 */
	@Override
	public List findByMigAuthor(Object migAuthor
	) {
		return findByProperty(MIG_AUTHOR, migAuthor
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigSyncConfigDAO#findBySrcType(java.lang.Object)
	 */
	@Override
	public List findBySrcType(Object srcType
	) {
		return findByProperty(SRC_TYPE, srcType
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigSyncConfigDAO#findBySrcIp(java.lang.Object)
	 */
	@Override
	public List findBySrcIp(Object srcIp
	) {
		return findByProperty(SRC_IP, srcIp
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigSyncConfigDAO#findBySrcPort(java.lang.Object)
	 */
	@Override
	public List findBySrcPort(Object srcPort
	) {
		return findByProperty(SRC_PORT, srcPort
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigSyncConfigDAO#findBySrcSchema(java.lang.Object)
	 */
	@Override
	public List findBySrcSchema(Object srcSchema
	) {
		return findByProperty(SRC_SCHEMA, srcSchema
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigSyncConfigDAO#findBySrcUser(java.lang.Object)
	 */
	@Override
	public List findBySrcUser(Object srcUser
	) {
		return findByProperty(SRC_USER, srcUser
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigSyncConfigDAO#findBySrcPassword(java.lang.Object)
	 */
	@Override
	public List findBySrcPassword(Object srcPassword
	) {
		return findByProperty(SRC_PASSWORD, srcPassword
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigSyncConfigDAO#findByDstType(java.lang.Object)
	 */
	@Override
	public List findByDstType(Object dstType
	) {
		return findByProperty(DST_TYPE, dstType
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigSyncConfigDAO#findByDstIp(java.lang.Object)
	 */
	@Override
	public List findByDstIp(Object dstIp
	) {
		return findByProperty(DST_IP, dstIp
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigSyncConfigDAO#findByDstPort(java.lang.Object)
	 */
	@Override
	public List findByDstPort(Object dstPort
	) {
		return findByProperty(DST_PORT, dstPort
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigSyncConfigDAO#findByDstSchema(java.lang.Object)
	 */
	@Override
	public List findByDstSchema(Object dstSchema
	) {
		return findByProperty(DST_SCHEMA, dstSchema
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigSyncConfigDAO#findByDstUser(java.lang.Object)
	 */
	@Override
	public List findByDstUser(Object dstUser
	) {
		return findByProperty(DST_USER, dstUser
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigSyncConfigDAO#findByDstPassword(java.lang.Object)
	 */
	@Override
	public List findByDstPassword(Object dstPassword
	) {
		return findByProperty(DST_PASSWORD, dstPassword
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigSyncConfigDAO#findByMigWhere(java.lang.Object)
	 */
	@Override
	public List findByMigWhere(Object migWhere
	) {
		return findByProperty(MIG_WHERE, migWhere
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigSyncConfigDAO#findByMigMode(java.lang.Object)
	 */
	@Override
	public List findByMigMode(Object migMode
	) {
		return findByProperty(MIG_MODE, migMode
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigSyncConfigDAO#findByMigDesc(java.lang.Object)
	 */
	@Override
	public List findByMigDesc(Object migDesc
	) {
		return findByProperty(MIG_DESC, migDesc
		);
	}
	

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigSyncConfigDAO#findAll()
	 */
	@Override
	public List findAll() {
		log.debug("finding all MigSyncConfig instances");
		try {
			String queryString = "from MigSyncConfig";
		 	return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigSyncConfigDAO#merge(person.daizhongde.migration.hibernate.pojo.MigSyncConfig)
	 */
    @Override
	public MigSyncConfig merge(MigSyncConfig detachedInstance) {
        log.debug("merging MigSyncConfig instance");
        try {
            MigSyncConfig result = (MigSyncConfig) getHibernateTemplate()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigSyncConfigDAO#attachDirty(person.daizhongde.migration.hibernate.pojo.MigSyncConfig)
	 */
    @Override
	public void attachDirty(MigSyncConfig instance) {
        log.debug("attaching dirty MigSyncConfig instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigSyncConfigDAO#attachClean(person.daizhongde.migration.hibernate.pojo.MigSyncConfig)
	 */
    @Override
	public void attachClean(MigSyncConfig instance) {
        log.debug("attaching clean MigSyncConfig instance");
        try {
                      	getHibernateTemplate().lock(instance, LockMode.NONE);
                        log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

	public static MigSyncConfigDAO getFromApplicationContext(ApplicationContext ctx) {
    	return (MigSyncConfigDAO) ctx.getBean("MigSyncConfigDAO");
	}
}