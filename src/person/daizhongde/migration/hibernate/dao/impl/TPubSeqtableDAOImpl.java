package person.daizhongde.migration.hibernate.dao.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Semaphore;

import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import person.daizhongde.virtue.dao.SpringHibernateDaoSupport;

import person.daizhongde.migration.hibernate.dao.TPubSeqtableDAO;
import person.daizhongde.migration.hibernate.pojo.TPubSeqtable;
import person.daizhongde.migration.util.SeqSemaphore;

/**
 	* A data access object (DAO) providing persistence and search support for TPubSeqtable entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see person.daizhongde.migration.hibernate.dao.impl.TPubSeqtable
  * @author MyEclipse Persistence Tools 
 */
public class TPubSeqtableDAOImpl extends SpringHibernateDaoSupport implements TPubSeqtableDAO  {
	     private static final Logger log = LoggerFactory.getLogger(TPubSeqtableDAOImpl.class);
	     public static final Semaphore sem_lock = new Semaphore(1);
		protected void initDao() {
		//do nothing
	}
    
	public int sequenceCURRVAL2(String tableName){
		String sHQL = "select seqValue from TPubSeqtable where seqName = '" + tableName+"'";
		List<Integer> list = new ArrayList<Integer>();
		list = this.listAll( sHQL );
		int val =  list.size()==0?-1:list.get(0);
		return val;
	};
	
	public int sequenceNEXTVAL2( String tableName ){
	
		int val = sequenceCURRVAL2(tableName);
		
		String uHQL = "update TPubSeqtable set seqValue=seqValue+1 where seqName = '" + tableName+"'";
		this.exeU( uHQL );
		return val+1;
	};

	public int sqlQuerySequenceCURRVAL2(String tableName){
		String sSQL = "select seq_value from t_pub_seqtable where seq_name = '" + tableName+"'";
		List<Integer> list = new ArrayList<Integer>();
		list = this.sqlQuerylistAll( sSQL );
		int val =  list.size()==0?-1:list.get(0);
		return val;
	};
	
	public int sqlQuerySequenceNEXTVAL2( String tableName, boolean resetEveryday ){
		
//		//session改为不自动提交，查询并设置行级排他锁
//		String queryString = "set autocommit = 0;select seq_value from t_pub_seqtable where seq_name='" + tableName + "' for update;";
//		String seq_value = sqlQueryfindaValue(queryString).toString();
		try{
				sem_lock.acquire();
				TPubSeqtable seq = findById( tableName );
//				log.debug(" sqlQuerySequenceNEXTVAL2...tableName:"+tableName +", resetEveryday:"+resetEveryday);
				if(resetEveryday ){
					//查询当前数据库时间
					String queryString = "select DATE_FORMAT(curdate(),'%Y-%m-%d');";
					String curdate = sqlQueryfindaValue(queryString).toString();
					
					if( curdate.equalsIgnoreCase( seq.getSeqTime() )){
						String uSQL = "update t_pub_seqtable set seq_value=seq_value+1 where seq_name = '" + tableName+"'";
						this.sqlQueryExeU( uSQL );
						sem_lock.release();
						return seq.getSeqValue()+1;//返回数据库当前值+1
					}else{
						String uSQL = "update t_pub_seqtable set seq_value=1 and seq_time=DATE_FORMAT(curdate(),'%Y-%m-%d') where seq_name = '" + tableName+"'";
						this.sqlQueryExeU( uSQL );
						sem_lock.release();
						return 1;
					}
				}else{
					
					//执行序列+1
					String uSQL = "update t_pub_seqtable set seq_value=seq_value+1 where seq_name = '" + tableName+"';";
					this.sqlQueryExeU( uSQL );	
					sem_lock.release();
					return seq.getSeqValue()+1;//返回数据库当前值+1
				}
			}catch (InterruptedException e) {
				e.printStackTrace();
				sem_lock.release();
				return -1;
			}
	};
	
    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.TPubSeqtableDAO#save(person.daizhongde.migration.hibernate.pojo.TPubSeqtable)
	 */
    @Override
	public void save(TPubSeqtable transientInstance) {
        log.debug("saving TPubSeqtable instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.TPubSeqtableDAO#delete(person.daizhongde.migration.hibernate.pojo.TPubSeqtable)
	 */
	@Override
	public void delete(TPubSeqtable persistentInstance) {
        log.debug("deleting TPubSeqtable instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.TPubSeqtableDAO#findById(java.lang.String)
	 */
    @Override
	public TPubSeqtable findById( java.lang.String id) {
        log.debug("getting TPubSeqtable instance with id: " + id);
        try {
            TPubSeqtable instance = (TPubSeqtable) getHibernateTemplate()
                    .get("person.daizhongde.migration.hibernate.pojo.TPubSeqtable", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.TPubSeqtableDAO#findByExample(person.daizhongde.migration.hibernate.pojo.TPubSeqtable)
	 */
    @Override
	public List findByExample(TPubSeqtable instance) {
        log.debug("finding TPubSeqtable instance by example");
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
	 * @see person.daizhongde.migration.hibernate.dao.impl.TPubSeqtableDAO#findByProperty(java.lang.String, java.lang.Object)
	 */
    @Override
	public List findByProperty(String propertyName, Object value) {
      log.debug("finding TPubSeqtable instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from TPubSeqtable as model where model." 
         						+ propertyName + "= ?";
		 return getHibernateTemplate().find(queryString, value);
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.TPubSeqtableDAO#findBySeqValue(java.lang.Object)
	 */
	@Override
	public List findBySeqValue(Object seqValue
	) {
		return findByProperty(SEQ_VALUE, seqValue
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.TPubSeqtableDAO#findByPrefix(java.lang.Object)
	 */
	@Override
	public List findByPrefix(Object prefix
	) {
		return findByProperty(PREFIX, prefix
		);
	}
	

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.TPubSeqtableDAO#findAll()
	 */
	@Override
	public List findAll() {
		log.debug("finding all TPubSeqtable instances");
		try {
			String queryString = "from TPubSeqtable";
		 	return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.TPubSeqtableDAO#merge(person.daizhongde.migration.hibernate.pojo.TPubSeqtable)
	 */
    @Override
	public TPubSeqtable merge(TPubSeqtable detachedInstance) {
        log.debug("merging TPubSeqtable instance");
        try {
            TPubSeqtable result = (TPubSeqtable) getHibernateTemplate()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.TPubSeqtableDAO#attachDirty(person.daizhongde.migration.hibernate.pojo.TPubSeqtable)
	 */
    @Override
	public void attachDirty(TPubSeqtable instance) {
        log.debug("attaching dirty TPubSeqtable instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.TPubSeqtableDAO#attachClean(person.daizhongde.migration.hibernate.pojo.TPubSeqtable)
	 */
    @Override
	public void attachClean(TPubSeqtable instance) {
        log.debug("attaching clean TPubSeqtable instance");
        try {
                      	getHibernateTemplate().lock(instance, LockMode.NONE);
                        log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

	public static TPubSeqtableDAO getFromApplicationContext(ApplicationContext ctx) {
    	return (TPubSeqtableDAO) ctx.getBean("tPubSeqtableDAO");
	}
}