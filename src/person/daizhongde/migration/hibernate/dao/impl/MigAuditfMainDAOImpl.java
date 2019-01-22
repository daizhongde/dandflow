package person.daizhongde.migration.hibernate.dao.impl;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import person.daizhongde.virtue.dao.SpringHibernateDaoSupport;

import person.daizhongde.migration.hibernate.dao.MigAuditfMainDAO;
import person.daizhongde.migration.hibernate.pojo.MigAuditfMain;

/**
 	* A data access object (DAO) providing persistence and search support for MigAuditfMain entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditfMain
  * @author MyEclipse Persistence Tools 
 */
public class MigAuditfMainDAOImpl extends SpringHibernateDaoSupport implements MigAuditfMainDAO  {
	     private static final Logger log = LoggerFactory.getLogger(MigAuditfMainDAOImpl.class);
		protected void initDao() {
		//do nothing
	}
    
    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditfMainDAO#save(person.daizhongde.migration.hibernate.pojo.MigAuditfMain)
	 */
    @Override
	public void save(MigAuditfMain transientInstance) {
        log.debug("saving MigAuditfMain instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditfMainDAO#delete(person.daizhongde.migration.hibernate.pojo.MigAuditfMain)
	 */
	@Override
	public void delete(MigAuditfMain persistentInstance) {
        log.debug("deleting MigAuditfMain instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditfMainDAO#findById(java.lang.Integer)
	 */
    @Override
	public MigAuditfMain findById( java.lang.Integer id) {
        log.debug("getting MigAuditfMain instance with id: " + id);
        try {
            MigAuditfMain instance = (MigAuditfMain) getHibernateTemplate()
                    .get("person.daizhongde.migration.hibernate.pojo.MigAuditfMain", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditfMainDAO#findByExample(person.daizhongde.migration.hibernate.pojo.MigAuditfMain)
	 */
    @Override
	public List findByExample(MigAuditfMain instance) {
        log.debug("finding MigAuditfMain instance by example");
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
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditfMainDAO#findByProperty(java.lang.String, java.lang.Object)
	 */
    @Override
	public List findByProperty(String propertyName, Object value) {
      log.debug("finding MigAuditfMain instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from MigAuditfMain as model where model." 
         						+ propertyName + "= ?";
		 return getHibernateTemplate().find(queryString, value);
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditfMainDAO#findByFauditName(java.lang.Object)
	 */
	@Override
	public List findByFauditName(Object fauditName
	) {
		return findByProperty(FAUDIT_NAME, fauditName
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditfMainDAO#findByFauditSrctableName(java.lang.Object)
	 */
	@Override
	public List findByFauditSrctableName(Object fauditSrctableName
	) {
		return findByProperty(FAUDIT_SRCTABLE_NAME, fauditSrctableName
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditfMainDAO#findByFauditSrctableConn(java.lang.Object)
	 */
	@Override
	public List findByFauditSrctableConn(Object fauditSrctableConn
	) {
		return findByProperty(FAUDIT_SRCTABLE_CONN, fauditSrctableConn
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditfMainDAO#findByFauditDsttableName(java.lang.Object)
	 */
	@Override
	public List findByFauditDsttableName(Object fauditDsttableName
	) {
		return findByProperty(FAUDIT_DSTTABLE_NAME, fauditDsttableName
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditfMainDAO#findByFauditDsttableConn(java.lang.Object)
	 */
	@Override
	public List findByFauditDsttableConn(Object fauditDsttableConn
	) {
		return findByProperty(FAUDIT_DSTTABLE_CONN, fauditDsttableConn
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditfMainDAO#findByFauditType(java.lang.Object)
	 */
	@Override
	public List findByFauditType(Object fauditType
	) {
		return findByProperty(FAUDIT_TYPE, fauditType
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditfMainDAO#findByFauditGroup(java.lang.Object)
	 */
	@Override
	public List findByFauditGroup(Object fauditGroup
	) {
		return findByProperty(FAUDIT_GROUP, fauditGroup
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditfMainDAO#findByFauditGroupName(java.lang.Object)
	 */
	@Override
	public List findByFauditGroupName(Object fauditGroupName
	) {
		return findByProperty(FAUDIT_GROUP_NAME, fauditGroupName
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditfMainDAO#findByFauditBussinessCluster(java.lang.Object)
	 */
	@Override
	public List findByFauditBussinessCluster(Object fauditBussinessCluster
	) {
		return findByProperty(FAUDIT_BUSSINESS_CLUSTER, fauditBussinessCluster
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditfMainDAO#findByFauditBussinessName(java.lang.Object)
	 */
	@Override
	public List findByFauditBussinessName(Object fauditBussinessName
	) {
		return findByProperty(FAUDIT_BUSSINESS_NAME, fauditBussinessName
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditfMainDAO#findByAuthor(java.lang.Object)
	 */
	@Override
	public List findByAuthor(Object author
	) {
		return findByProperty(AUTHOR, author
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditfMainDAO#findByFauditDesc(java.lang.Object)
	 */
	@Override
	public List findByFauditDesc(Object fauditDesc
	) {
		return findByProperty(FAUDIT_DESC, fauditDesc
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditfMainDAO#findByFauditStatus(java.lang.Object)
	 */
	@Override
	public List findByFauditStatus(Object fauditStatus
	) {
		return findByProperty(FAUDIT_STATUS, fauditStatus
		);
	}
	

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditfMainDAO#findAll()
	 */
	@Override
	public List findAll() {
		log.debug("finding all MigAuditfMain instances");
		try {
			String queryString = "from MigAuditfMain";
		 	return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditfMainDAO#merge(person.daizhongde.migration.hibernate.pojo.MigAuditfMain)
	 */
    @Override
	public MigAuditfMain merge(MigAuditfMain detachedInstance) {
        log.debug("merging MigAuditfMain instance");
        try {
            MigAuditfMain result = (MigAuditfMain) getHibernateTemplate()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditfMainDAO#attachDirty(person.daizhongde.migration.hibernate.pojo.MigAuditfMain)
	 */
    @Override
	public void attachDirty(MigAuditfMain instance) {
        log.debug("attaching dirty MigAuditfMain instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditfMainDAO#attachClean(person.daizhongde.migration.hibernate.pojo.MigAuditfMain)
	 */
    @Override
	public void attachClean(MigAuditfMain instance) {
        log.debug("attaching clean MigAuditfMain instance");
        try {
                      	getHibernateTemplate().lock(instance, LockMode.NONE);
                        log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

	public static MigAuditfMainDAO getFromApplicationContext(ApplicationContext ctx) {
    	return (MigAuditfMainDAO) ctx.getBean("MigAuditfMainDAO");
	}
}