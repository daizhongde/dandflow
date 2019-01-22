package person.daizhongde.migration.hibernate.dao.impl;

import java.util.List;

import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import person.daizhongde.virtue.dao.SpringHibernateDaoSupport;

import person.daizhongde.migration.hibernate.dao.MigTaskConfigDAO;
import person.daizhongde.migration.hibernate.pojo.MigTaskConfig;

/**
 	* A data access object (DAO) providing persistence and search support for MigTaskConfig entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigTaskConfig
  * @author MyEclipse Persistence Tools 
 */
public class MigTaskConfigDAOImpl extends SpringHibernateDaoSupport implements MigTaskConfigDAO  {
	     private static final Logger log = LoggerFactory.getLogger(MigTaskConfigDAOImpl.class);
		protected void initDao() {
		//do nothing
	}
    
	public List findDomains(int type){
		return this.listAll("select distinct domain from MigTaskConfig where migConfigType="+type);
//		return this.sqlQuerylistAll("select distinct domain from tool.mig_task_config where mig_Config_Type='"+type+"'");
	};
		
    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigTaskConfigDAO#save(person.daizhongde.migration.hibernate.pojo.MigTaskConfig)
	 */
    @Override
	public void save(MigTaskConfig transientInstance) {
        log.debug("saving MigTaskConfig instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigTaskConfigDAO#delete(person.daizhongde.migration.hibernate.pojo.MigTaskConfig)
	 */
	@Override
	public void delete(MigTaskConfig persistentInstance) {
        log.debug("deleting MigTaskConfig instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigTaskConfigDAO#findById(java.lang.Integer)
	 */
    @Override
	public MigTaskConfig findById( java.lang.Integer id) {
        log.debug("getting MigTaskConfig instance with id: " + id);
        try {
            MigTaskConfig instance = (MigTaskConfig) getHibernateTemplate()
                    .get("person.daizhongde.migration.hibernate.pojo.MigTaskConfig", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigTaskConfigDAO#findByExample(person.daizhongde.migration.hibernate.pojo.MigTaskConfig)
	 */
    @Override
	public List findByExample(MigTaskConfig instance) {
        log.debug("finding MigTaskConfig instance by example");
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
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigTaskConfigDAO#findByProperty(java.lang.String, java.lang.Object)
	 */
    @Override
	public List findByProperty(String propertyName, Object value) {
      log.debug("finding MigTaskConfig instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from MigTaskConfig as model where model." 
         						+ propertyName + "= ?";
		 return getHibernateTemplate().find(queryString, value);
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}
	public List findByProperty(String propertyName, Object value, String order_column) {
	      log.debug("finding MigTaskConfig instance with property: " + propertyName
	            + ", value: " + value);
	      try {
	         String queryString = "from MigTaskConfig as model where model." 
	         						+ propertyName + "= ? order by "+order_column;
			 return getHibernateTemplate().find(queryString, value);
	      } catch (RuntimeException re) {
	         log.error("find by property name failed", re);
	         throw re;
	      }
		}
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigTaskConfigDAO#findByMigConfigType(java.lang.Object)
	 */
	@Override
	public List findByMigConfigType(Object migConfigType
	) {
		return findByProperty(MIG_CONFIG_TYPE, migConfigType
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigTaskConfigDAO#findByMigSrc(java.lang.Object)
	 */
	@Override
	public List findByMigSrc(Object migSrc
	) {
		return findByProperty(MIG_SRC, migSrc
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigTaskConfigDAO#findByMigSrcConn(java.lang.Object)
	 */
	@Override
	public List findByMigSrcConn(Object migSrcConn
	) {
		return findByProperty(MIG_SRC_CONN, migSrcConn
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigTaskConfigDAO#findByMigWhere(java.lang.Object)
	 */
	@Override
	public List findByMigWhere(Object migWhere
	) {
		return findByProperty(MIG_WHERE, migWhere
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigTaskConfigDAO#findByMigDst(java.lang.Object)
	 */
	@Override
	public List findByMigDst(Object migDst
	) {
		return findByProperty(MIG_DST, migDst
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigTaskConfigDAO#findByMigDstConn(java.lang.Object)
	 */
	@Override
	public List findByMigDstConn(Object migDstConn
	) {
		return findByProperty(MIG_DST_CONN, migDstConn
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigTaskConfigDAO#findByMigAuthor(java.lang.Object)
	 */
	@Override
	public List findByMigAuthor(Object migAuthor
	) {
		return findByProperty(MIG_AUTHOR, migAuthor
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigTaskConfigDAO#findByMigDesc(java.lang.Object)
	 */
	@Override
	public List findByMigDesc(Object migDesc
	) {
		return findByProperty(MIG_DESC, migDesc
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigTaskConfigDAO#findByMigStatus(java.lang.Object)
	 */
	@Override
	public List findByMigStatus(Object migStatus
	) {
		return findByProperty(MIG_STATUS, migStatus
		);
	}
	

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigTaskConfigDAO#findAll()
	 */
	@Override
	public List findAll() {
		log.debug("finding all MigTaskConfig instances");
		try {
			String queryString = "from MigTaskConfig";
		 	return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigTaskConfigDAO#merge(person.daizhongde.migration.hibernate.pojo.MigTaskConfig)
	 */
    @Override
	public MigTaskConfig merge(MigTaskConfig detachedInstance) {
        log.debug("merging MigTaskConfig instance");
        try {
            MigTaskConfig result = (MigTaskConfig) getHibernateTemplate()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigTaskConfigDAO#attachDirty(person.daizhongde.migration.hibernate.pojo.MigTaskConfig)
	 */
    @Override
	public void attachDirty(MigTaskConfig instance) {
        log.debug("attaching dirty MigTaskConfig instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigTaskConfigDAO#attachClean(person.daizhongde.migration.hibernate.pojo.MigTaskConfig)
	 */
    @Override
	public void attachClean(MigTaskConfig instance) {
        log.debug("attaching clean MigTaskConfig instance");
        try {
                      	getHibernateTemplate().lock(instance, LockMode.NONE);
                        log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

	public static MigTaskConfigDAO getFromApplicationContext(ApplicationContext ctx) {
    	return (MigTaskConfigDAO) ctx.getBean("MigTaskConfigDAO");
	}
}