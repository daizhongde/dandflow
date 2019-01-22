package person.daizhongde.migration.hibernate.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import person.daizhongde.virtue.dao.SpringHibernateDaoSupport;

import person.daizhongde.migration.hibernate.dao.MigJobStatDAO;
import person.daizhongde.migration.hibernate.pojo.MigJobStat;

/**
 	* A data access object (DAO) providing persistence and search support for MigJobStat entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigJobStat
  * @author MyEclipse Persistence Tools 
 */
public class MigJobStatDAOImpl extends SpringHibernateDaoSupport implements MigJobStatDAO  {
	    private static final Logger log = LoggerFactory.getLogger(MigJobStatDAOImpl.class);
		protected void initDao() {
		//do nothing
	}
    
	public void RN_WritelogSQL(Map condition){
		String writelogSQL = "insert into Mig_Job_Stat(job_Id,job_Ins_Id,dryrun_Id,node_Id,"
				+ "begin_Time,end_Time,status,remark) "
				+ "values( :job_Id, :job_Ins_Id, :dryrun_Id, :node_Id, "
				+ ":begin_Time, :end_Time, :status, :remark) ";
		this.sqlQueryExeUByMap( writelogSQL, condition );
	}
		
    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigJobStatDAO#save(person.daizhongde.migration.hibernate.pojo.MigJobStat)
	 */
    @Override
	public void save(MigJobStat transientInstance) {
        log.debug("saving MigJobStat instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigJobStatDAO#delete(person.daizhongde.migration.hibernate.pojo.MigJobStat)
	 */
	@Override
	public void delete(MigJobStat persistentInstance) {
        log.debug("deleting MigJobStat instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
	public void deleteByInsId(String jobInsId){
		Map condition = new HashMap(1);
		condition.put("jobInsId", jobInsId);
		String sql = "delete from mig_job_stat where JOB_INS_ID=:jobInsId";
		this.sqlQueryExeUByMap(sql, condition);	
	}
	public void deleteByInsId(List<String> jobInsId){
		Map condition = new HashMap(1);
		condition.put("jobInsId", jobInsId);
		String sql = "delete from mig_job_stat where JOB_INS_ID in ( :jobInsId )";
		this.sqlQueryExeUByMap(sql, condition);	
	}
    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigJobStatDAO#findById(java.lang.String)
	 */
    @Override
	public MigJobStat findById( java.lang.String id) {
        log.debug("getting MigJobStat instance with id: " + id);
        try {
            MigJobStat instance = (MigJobStat) getHibernateTemplate()
                    .get("person.daizhongde.migration.hibernate.pojo.MigJobStat", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigJobStatDAO#findByExample(person.daizhongde.migration.hibernate.pojo.MigJobStat)
	 */
    @Override
	public List findByExample(MigJobStat instance) {
        log.debug("finding MigJobStat instance by example");
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
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigJobStatDAO#findByProperty(java.lang.String, java.lang.Object)
	 */
    @Override
	public List findByProperty(String propertyName, Object value) {
      log.debug("finding MigJobStat instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from MigJobStat as model where model." 
         						+ propertyName + "= ?";
		 return getHibernateTemplate().find(queryString, value);
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigJobStatDAO#findByJobId(java.lang.Object)
	 */
	@Override
	public List findByJobId(Object jobId
	) {
		return findByProperty(JOB_ID, jobId
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigJobStatDAO#findByJobInsId(java.lang.Object)
	 */
	@Override
	public List findByJobInsId(Object jobInsId
	) {
		return findByProperty(JOB_INS_ID, jobInsId
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigJobStatDAO#findByStatus(java.lang.Object)
	 */
	@Override
	public List findByStatus(Object status
	) {
		return findByProperty(STATUS, status
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigJobStatDAO#findByRemark(java.lang.Object)
	 */
	@Override
	public List findByRemark(Object remark
	) {
		return findByProperty(REMARK, remark
		);
	}
	

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigJobStatDAO#findAll()
	 */
	@Override
	public List findAll() {
		log.debug("finding all MigJobStat instances");
		try {
			String queryString = "from MigJobStat";
		 	return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigJobStatDAO#merge(person.daizhongde.migration.hibernate.pojo.MigJobStat)
	 */
    @Override
	public MigJobStat merge(MigJobStat detachedInstance) {
        log.debug("merging MigJobStat instance");
        try {
            MigJobStat result = (MigJobStat) getHibernateTemplate()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigJobStatDAO#attachDirty(person.daizhongde.migration.hibernate.pojo.MigJobStat)
	 */
    @Override
	public void attachDirty(MigJobStat instance) {
        log.debug("attaching dirty MigJobStat instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigJobStatDAO#attachClean(person.daizhongde.migration.hibernate.pojo.MigJobStat)
	 */
    @Override
	public void attachClean(MigJobStat instance) {
        log.debug("attaching clean MigJobStat instance");
        try {
                      	getHibernateTemplate().lock(instance, LockMode.NONE);
                        log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

	public static MigJobStatDAO getFromApplicationContext(ApplicationContext ctx) {
    	return (MigJobStatDAO) ctx.getBean("MigJobStatDAO");
	}
}