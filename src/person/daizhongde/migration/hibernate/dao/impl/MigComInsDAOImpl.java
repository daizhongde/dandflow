package person.daizhongde.migration.hibernate.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import person.daizhongde.virtue.dao.SpringHibernateDaoSupport;

import person.daizhongde.migration.hibernate.dao.MigComInsDAO;
import person.daizhongde.migration.hibernate.pojo.MigComIns;

/**
 	* A data access object (DAO) providing persistence and search support for MigComIns entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see person.daizhongde.migration.hibernate.pojotemp.MigComIns
  * @author MyEclipse Persistence Tools 
 */
public class MigComInsDAOImpl extends SpringHibernateDaoSupport implements MigComInsDAO  {
	     private static final Logger log = LoggerFactory.getLogger(MigComInsDAOImpl.class);
		protected void initDao() {
		//do nothing
	}
    
	public List<MigComIns> getCominsByJobInsIdAndComId(String jobInsId, String comId){
		Map condition = new HashMap(2);
		condition.put("job_ins_id", jobInsId);
		condition.put("com_id", comId);
		
		String sql="from MigComIns where id.jobInsId=:job_ins_id and id.comId=:com_id order by id.paraId";
		return this.listAllByMap(sql, condition);
	}
	
	public void deleteCominsByComId(String comId){
		Map condition = new HashMap(1);
		condition.put("com_id", comId);
		String sql = "delete from mig_com_ins where com_id=:com_id";
		this.sqlQueryExeUByMap(sql, condition);	
	}
	public void deleteComins(String jobInsId){
		Map condition = new HashMap(1);
		condition.put("jobInsId", jobInsId);
		String sql = "delete from mig_com_ins where JOB_INS_ID=:jobInsId";
		this.sqlQueryExeUByMap(sql, condition);	
	}
	
	public void deleteComins(List<String> jobInsId){
		Map condition = new HashMap(1);
		condition.put("jobInsId", jobInsId);
		String sql = "delete from mig_com_ins where JOB_INS_ID in (:jobInsId )";
		this.sqlQueryExeUByMap(sql, condition);	
	}

	public void updateParaValue(String paraValue, String jobInsId, String comId, int paraId ){
		Map condition = new HashMap(4);
		condition.put("para_value", paraValue);
		condition.put("job_ins_id", jobInsId);
		condition.put("com_id", comId);
		condition.put("para_id", paraId);
		String sql="update mig_com_ins set para_value=:para_value where job_ins_id=:job_ins_id and com_id=:com_id and para_id=:para_id";
		this.sqlQueryExeUByMap(sql, condition);	
	}
		
    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigComInsDAO#save(person.daizhongde.migration.hibernate.pojo.MigComIns)
	 */
    @Override
	public void save(MigComIns transientInstance) {
        log.debug("saving MigComIns instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigComInsDAO#delete(person.daizhongde.migration.hibernate.pojo.MigComIns)
	 */
	@Override
	public void delete(MigComIns persistentInstance) {
        log.debug("deleting MigComIns instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigComInsDAO#findById(person.daizhongde.migration.hibernate.pojo.MigComInsId)
	 */
    @Override
	public MigComIns findById( person.daizhongde.migration.hibernate.pojo.MigComInsId id) {
        log.debug("getting MigComIns instance with id: " + id);
        try {
            MigComIns instance = (MigComIns) getHibernateTemplate()
                    .get("person.daizhongde.migration.hibernate.pojo.MigComIns", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigComInsDAO#findByExample(person.daizhongde.migration.hibernate.pojo.MigComIns)
	 */
    @Override
	public List findByExample(MigComIns instance) {
        log.debug("finding MigComIns instance by example");
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
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigComInsDAO#findByProperty(java.lang.String, java.lang.Object)
	 */
    @Override
	public List findByProperty(String propertyName, Object value) {
      log.debug("finding MigComIns instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from MigComIns as model where model." 
         						+ propertyName + "= ?";
		 return getHibernateTemplate().find(queryString, value);
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigComInsDAO#findByJobInsId(java.lang.Object)
	 */
	@Override
	public List findByJobInsId(Object jobInsId
	) {
		return findByProperty(JOB_INS_ID, jobInsId
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigComInsDAO#findByParaValue(java.lang.Object)
	 */
	@Override
	public List findByParaValue(Object paraValue
	) {
		return findByProperty(PARA_VALUE, paraValue
		);
	}
	

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigComInsDAO#findAll()
	 */
	@Override
	public List findAll() {
		log.debug("finding all MigComIns instances");
		try {
			String queryString = "from MigComIns";
		 	return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigComInsDAO#merge(person.daizhongde.migration.hibernate.pojo.MigComIns)
	 */
    @Override
	public MigComIns merge(MigComIns detachedInstance) {
        log.debug("merging MigComIns instance");
        try {
            MigComIns result = (MigComIns) getHibernateTemplate()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigComInsDAO#attachDirty(person.daizhongde.migration.hibernate.pojo.MigComIns)
	 */
    @Override
	public void attachDirty(MigComIns instance) {
        log.debug("attaching dirty MigComIns instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigComInsDAO#attachClean(person.daizhongde.migration.hibernate.pojo.MigComIns)
	 */
    @Override
	public void attachClean(MigComIns instance) {
        log.debug("attaching clean MigComIns instance");
        try {
                      	getHibernateTemplate().lock(instance, LockMode.NONE);
                        log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

	public static MigComInsDAO getFromApplicationContext(ApplicationContext ctx) {
    	return (MigComInsDAO) ctx.getBean("MigComInsDAO");
	}
}