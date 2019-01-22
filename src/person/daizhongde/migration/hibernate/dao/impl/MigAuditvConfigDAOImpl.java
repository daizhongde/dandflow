package person.daizhongde.migration.hibernate.dao.impl;

import java.util.List;

import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import person.daizhongde.virtue.dao.SpringHibernateDaoSupport;

import person.daizhongde.migration.hibernate.dao.MigAuditvConfigDAO;
import person.daizhongde.migration.hibernate.pojo.MigAuditvConfig;

/**
 	* A data access object (DAO) providing persistence and search support for MigAuditvConfig entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see person.daizhongde.migration.hibernate.pojo.MigAuditvConfig
  * @author MyEclipse Persistence Tools 
 */
public class MigAuditvConfigDAOImpl extends SpringHibernateDaoSupport implements MigAuditvConfigDAO  {
	     private static final Logger log = LoggerFactory.getLogger(MigAuditvConfigDAOImpl.class);
		protected void initDao() {
		//do nothing
	}
    
    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditvConfigDAO#save(person.daizhongde.migration.hibernate.pojo.MigAuditvConfig)
	 */
    @Override
	public void save(MigAuditvConfig transientInstance) {
        log.debug("saving MigAuditvConfig instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditvConfigDAO#delete(person.daizhongde.migration.hibernate.pojo.MigAuditvConfig)
	 */
	@Override
	public void delete(MigAuditvConfig persistentInstance) {
        log.debug("deleting MigAuditvConfig instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
	public void deleteById(int id){
		String deleteSQL = "delete from  tool.`mig_auditv_config` "
				+ "WHERE audit_id="+id;
		this.sqlQueryExeU(deleteSQL);
	};
	public void deleteById(String id){
		String deleteSQL = "delete from  tool.`mig_auditv_config` "
				+ "WHERE audit_id='"+id+"'";
		this.sqlQueryExeU(deleteSQL);
	};
	
    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditvConfigDAO#findById(java.lang.Integer)
	 */
    @Override
	public MigAuditvConfig findById( java.lang.Integer id) {
        log.debug("getting MigAuditvConfig instance with id: " + id);
        try {
            MigAuditvConfig instance = (MigAuditvConfig) getHibernateTemplate()
                    .get("person.daizhongde.migration.hibernate.pojo.MigAuditvConfig", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditvConfigDAO#findByExample(person.daizhongde.migration.hibernate.pojo.MigAuditvConfig)
	 */
    @Override
	public List findByExample(MigAuditvConfig instance) {
        log.debug("finding MigAuditvConfig instance by example");
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
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditvConfigDAO#findByProperty(java.lang.String, java.lang.Object)
	 */
    @Override
	public List findByProperty(String propertyName, Object value) {
      log.debug("finding MigAuditvConfig instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from MigAuditvConfig as model where model." 
         						+ propertyName + "= ?";
		 return getHibernateTemplate().find(queryString, value);
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditvConfigDAO#findByAuditCode(java.lang.Object)
	 */
	@Override
	public List findByAuditCode(Object auditCode
	) {
		return findByProperty(AUDIT_CODE, auditCode
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditvConfigDAO#findByDomain(java.lang.Object)
	 */
	@Override
	public List findByDomain(Object domain
	) {
		return findByProperty(DOMAIN, domain
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditvConfigDAO#findByTableName(java.lang.Object)
	 */
	@Override
	public List findByTableName(Object tableName
	) {
		return findByProperty(TABLE_NAME, tableName
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditvConfigDAO#findByAuditName(java.lang.Object)
	 */
	@Override
	public List findByAuditName(Object auditName
	) {
		return findByProperty(AUDIT_NAME, auditName
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditvConfigDAO#findByAuditType(java.lang.Object)
	 */
	@Override
	public List findByAuditType(Object auditType
	) {
		return findByProperty(AUDIT_TYPE, auditType
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditvConfigDAO#findBySqlType(java.lang.Object)
	 */
	@Override
	public List findBySqlType(Object sqlType
	) {
		return findByProperty(SQL_TYPE, sqlType
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditvConfigDAO#findBySrcAuditSql(java.lang.Object)
	 */
	@Override
	public List findBySrcAuditSql(Object srcAuditSql
	) {
		return findByProperty(SRC_AUDIT_SQL, srcAuditSql
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditvConfigDAO#findByDstAuditSql(java.lang.Object)
	 */
	@Override
	public List findByDstAuditSql(Object dstAuditSql
	) {
		return findByProperty(DST_AUDIT_SQL, dstAuditSql
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditvConfigDAO#findByAuditValue(java.lang.Object)
	 */
	@Override
	public List findByAuditValue(Object auditValue
	) {
		return findByProperty(AUDIT_VALUE, auditValue
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditvConfigDAO#findByAuditFlag(java.lang.Object)
	 */
	@Override
	public List findByAuditFlag(Object auditFlag
	) {
		return findByProperty(AUDIT_FLAG, auditFlag
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditvConfigDAO#findByInvalidDataSql(java.lang.Object)
	 */
	@Override
	public List findByInvalidDataSql(Object invalidDataSql
	) {
		return findByProperty(INVALID_DATA_SQL, invalidDataSql
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditvConfigDAO#findByOperator(java.lang.Object)
	 */
	@Override
	public List findByOperator(Object operator
	) {
		return findByProperty(OPERATOR, operator
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditvConfigDAO#findByAuditUnit(java.lang.Object)
	 */
	@Override
	public List findByAuditUnit(Object auditUnit
	) {
		return findByProperty(AUDIT_UNIT, auditUnit
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditvConfigDAO#findByAuditAuthor(java.lang.Object)
	 */
	@Override
	public List findByAuditAuthor(Object auditAuthor
	) {
		return findByProperty(AUDIT_AUTHOR, auditAuthor
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditvConfigDAO#findByRemark(java.lang.Object)
	 */
	@Override
	public List findByRemark(Object remark
	) {
		return findByProperty(REMARK, remark
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditvConfigDAO#findBySrcDbConnect(java.lang.Object)
	 */
	@Override
	public List findBySrcDbConnect(Object srcDbConnect
	) {
		return findByProperty(SRC_DB_CONNECT, srcDbConnect
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditvConfigDAO#findByDstDbConnect(java.lang.Object)
	 */
	@Override
	public List findByDstDbConnect(Object dstDbConnect
	) {
		return findByProperty(DST_DB_CONNECT, dstDbConnect
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditvConfigDAO#findByMigSqlRep(java.lang.Object)
	 */
	@Override
	public List findByMigSqlRep(Object migSqlRep
	) {
		return findByProperty(MIG_SQL_REP, migSqlRep
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditvConfigDAO#findByVersion(java.lang.Object)
	 */
	@Override
	public List findByVersion(Object version
	) {
		return findByProperty(VERSION, version
		);
	}
	

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditvConfigDAO#findAll()
	 */
	@Override
	public List findAll() {
		log.debug("finding all MigAuditvConfig instances");
		try {
			String queryString = "from MigAuditvConfig";
		 	return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	public List findAll(String order_column) {
		log.debug("finding all MigAuditvConfig instances");
		try {
			String queryString = "from MigAuditvConfig order by "+order_column;
		 	return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditvConfigDAO#merge(person.daizhongde.migration.hibernate.pojo.MigAuditvConfig)
	 */
    @Override
	public MigAuditvConfig merge(MigAuditvConfig detachedInstance) {
        log.debug("merging MigAuditvConfig instance");
        try {
            MigAuditvConfig result = (MigAuditvConfig) getHibernateTemplate()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditvConfigDAO#attachDirty(person.daizhongde.migration.hibernate.pojo.MigAuditvConfig)
	 */
    @Override
	public void attachDirty(MigAuditvConfig instance) {
        log.debug("attaching dirty MigAuditvConfig instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditvConfigDAO#attachClean(person.daizhongde.migration.hibernate.pojo.MigAuditvConfig)
	 */
    @Override
	public void attachClean(MigAuditvConfig instance) {
        log.debug("attaching clean MigAuditvConfig instance");
        try {
                      	getHibernateTemplate().lock(instance, LockMode.NONE);
                        log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

	public static MigAuditvConfigDAO getFromApplicationContext(ApplicationContext ctx) {
    	return (MigAuditvConfigDAO) ctx.getBean("MigAuditvConfigDAO");
	}
}