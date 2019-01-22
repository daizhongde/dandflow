package person.daizhongde.migration.hibernate.dao.impl;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import person.daizhongde.virtue.dao.SpringHibernateDaoSupport;

import person.daizhongde.migration.hibernate.dao.MigAuditvResultDAO;
import person.daizhongde.migration.hibernate.pojo.MigAuditvResult;

/**
 	* A data access object (DAO) providing persistence and search support for MigAuditvResult entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see person.daizhongde.migration.hibernate.pojo.MigAuditvResult
  * @author MyEclipse Persistence Tools 
 */
public class MigAuditvResultDAOImpl extends SpringHibernateDaoSupport implements MigAuditvResultDAO  {
	     private static final Logger log = LoggerFactory.getLogger(MigAuditvResultDAOImpl.class);
		protected void initDao() {
		//do nothing
	}
    
    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditvResultDAO#save(person.daizhongde.migration.hibernate.pojo.MigAuditvResult)
	 */
    @Override
	public void save(MigAuditvResult transientInstance) {
        log.debug("saving MigAuditvResult instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditvResultDAO#delete(person.daizhongde.migration.hibernate.pojo.MigAuditvResult)
	 */
	@Override
	public void delete(MigAuditvResult persistentInstance) {
        log.debug("deleting MigAuditvResult instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditvResultDAO#findById(java.lang.Integer)
	 */
    @Override
	public MigAuditvResult findById( java.lang.Integer id) {
        log.debug("getting MigAuditvResult instance with id: " + id);
        try {
            MigAuditvResult instance = (MigAuditvResult) getHibernateTemplate()
                    .get("person.daizhongde.migration.hibernate.pojo.MigAuditvResult", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditvResultDAO#findByExample(person.daizhongde.migration.hibernate.pojo.MigAuditvResult)
	 */
    @Override
	public List findByExample(MigAuditvResult instance) {
        log.debug("finding MigAuditvResult instance by example");
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
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditvResultDAO#findByProperty(java.lang.String, java.lang.Object)
	 */
    @Override
	public List findByProperty(String propertyName, Object value) {
      log.debug("finding MigAuditvResult instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from MigAuditvResult as model where model." 
         						+ propertyName + "= ?";
		 return getHibernateTemplate().find(queryString, value);
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditvResultDAO#findByAuditCode(java.lang.Object)
	 */
	@Override
	public List findByAuditCode(Object auditCode
	) {
		return findByProperty(AUDIT_CODE, auditCode
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditvResultDAO#findByFaresDryrunId(java.lang.Object)
	 */
	@Override
	public List findByFaresDryrunId(Object faresDryrunId
	) {
		return findByProperty(FARES_DRYRUN_ID, faresDryrunId
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditvResultDAO#findByMinAnalysis(java.lang.Object)
	 */
	@Override
	public List findByMinAnalysis(Object minAnalysis
	) {
		return findByProperty(MIN_ANALYSIS, minAnalysis
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditvResultDAO#findByDomain(java.lang.Object)
	 */
	@Override
	public List findByDomain(Object domain
	) {
		return findByProperty(DOMAIN, domain
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditvResultDAO#findByTableName(java.lang.Object)
	 */
	@Override
	public List findByTableName(Object tableName
	) {
		return findByProperty(TABLE_NAME, tableName
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditvResultDAO#findByAuditName(java.lang.Object)
	 */
	@Override
	public List findByAuditName(Object auditName
	) {
		return findByProperty(AUDIT_NAME, auditName
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditvResultDAO#findBySrcValue(java.lang.Object)
	 */
	@Override
	public List findBySrcValue(Object srcValue
	) {
		return findByProperty(SRC_VALUE, srcValue
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditvResultDAO#findByDstValue(java.lang.Object)
	 */
	@Override
	public List findByDstValue(Object dstValue
	) {
		return findByProperty(DST_VALUE, dstValue
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditvResultDAO#findByMinValue(java.lang.Object)
	 */
	@Override
	public List findByMinValue(Object minValue
	) {
		return findByProperty(MIN_VALUE, minValue
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditvResultDAO#findByAuditUnit(java.lang.Object)
	 */
	@Override
	public List findByAuditUnit(Object auditUnit
	) {
		return findByProperty(AUDIT_UNIT, auditUnit
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditvResultDAO#findByInvalidDataTable(java.lang.Object)
	 */
	@Override
	public List findByInvalidDataTable(Object invalidDataTable
	) {
		return findByProperty(INVALID_DATA_TABLE, invalidDataTable
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditvResultDAO#findByResult(java.lang.Object)
	 */
	@Override
	public List findByResult(Object result
	) {
		return findByProperty(RESULT, result
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditvResultDAO#findByAuditAuthor(java.lang.Object)
	 */
	@Override
	public List findByAuditAuthor(Object auditAuthor
	) {
		return findByProperty(AUDIT_AUTHOR, auditAuthor
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditvResultDAO#findByRemark(java.lang.Object)
	 */
	@Override
	public List findByRemark(Object remark
	) {
		return findByProperty(REMARK, remark
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditvResultDAO#findBySuccessFlag(java.lang.Object)
	 */
	@Override
	public List findBySuccessFlag(Object successFlag
	) {
		return findByProperty(SUCCESS_FLAG, successFlag
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditvResultDAO#findByErrMsg(java.lang.Object)
	 */
	@Override
	public List findByErrMsg(Object errMsg
	) {
		return findByProperty(ERR_MSG, errMsg
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditvResultDAO#findByInvalidDataCnt(java.lang.Object)
	 */
	@Override
	public List findByInvalidDataCnt(Object invalidDataCnt
	) {
		return findByProperty(INVALID_DATA_CNT, invalidDataCnt
		);
	}
	

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditvResultDAO#findAll()
	 */
	@Override
	public List findAll() {
		log.debug("finding all MigAuditvResult instances");
		try {
			String queryString = "from MigAuditvResult";
		 	return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditvResultDAO#merge(person.daizhongde.migration.hibernate.pojo.MigAuditvResult)
	 */
    @Override
	public MigAuditvResult merge(MigAuditvResult detachedInstance) {
        log.debug("merging MigAuditvResult instance");
        try {
            MigAuditvResult result = (MigAuditvResult) getHibernateTemplate()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditvResultDAO#attachDirty(person.daizhongde.migration.hibernate.pojo.MigAuditvResult)
	 */
    @Override
	public void attachDirty(MigAuditvResult instance) {
        log.debug("attaching dirty MigAuditvResult instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditvResultDAO#attachClean(person.daizhongde.migration.hibernate.pojo.MigAuditvResult)
	 */
    @Override
	public void attachClean(MigAuditvResult instance) {
        log.debug("attaching clean MigAuditvResult instance");
        try {
                      	getHibernateTemplate().lock(instance, LockMode.NONE);
                        log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

	public static MigAuditvResultDAO getFromApplicationContext(ApplicationContext ctx) {
    	return (MigAuditvResultDAO) ctx.getBean("MigAuditvResultDAO");
	}
}