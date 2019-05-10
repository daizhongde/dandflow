package person.daizhongde.migration.hibernate.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import person.daizhongde.migration.hibernate.dao.TCopoteEmployeeDAO;
import person.daizhongde.migration.hibernate.pojo.TCopoteEmployee;
import person.daizhongde.virtue.dao.SpringHibernateDaoSupport;

/**
 	* A data access object (DAO) providing persistence and search support for TCopoteEmployee entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see person.daizhongde.migration.hibernate.dao.impl.TCopoteEmployee
  * @author MyEclipse Persistence Tools 
 */
//    @Transactional   
public class TCopoteEmployeeDAOImpl extends SpringHibernateDaoSupport implements TCopoteEmployeeDAO  {
	     private static final Logger log = LoggerFactory.getLogger(TCopoteEmployeeDAOImpl.class);
		protected void initDao() {
		//do nothing
	}
    
    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.TCopoteEmployeeDAO#save(person.daizhongde.migration.hibernate.pojo.TCopoteEmployee)
	 */
    @Override
	public void save(TCopoteEmployee transientInstance) {
        log.debug("saving TCopoteEmployee instance");
        try {
        	getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void updateYGBHZJHM(	String employee_no,
			String name,
			String employee_idcard){
		Map condition = new HashMap(3);
		condition.put("employee_no", employee_no);
		condition.put("name", name);
		condition.put("employee_idcard", employee_idcard);
		String sql="update t_copote_employee "
				+ "    set employee_no=:employee_no employee_idcard=:employee_idcard  "
				+ "  where name=:name AND (employee_no IS NULL OR TRIM(employee_no) ='')";
		this.sqlQueryExeUByMap(sql, condition);	
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.TCopoteEmployeeDAO#delete(person.daizhongde.migration.hibernate.pojo.TCopoteEmployee)
	 */
	@Override
	public void delete(TCopoteEmployee persistentInstance) {
        log.debug("deleting TCopoteEmployee instance");
        try {
        	getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.TCopoteEmployeeDAO#findById(java.lang.Integer)
	 */
    @Override
	public TCopoteEmployee findById( java.lang.Integer id) {
        log.debug("getting TCopoteEmployee instance with id: " + id);
        try {
            TCopoteEmployee instance = (TCopoteEmployee) getHibernateTemplate()
                    .get("person.daizhongde.migration.hibernate.pojo.TCopoteEmployee", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.TCopoteEmployeeDAO#findByExample(person.daizhongde.migration.hibernate.pojo.TCopoteEmployee)
	 */
    @Override
	public List findByExample(TCopoteEmployee instance) {
        log.debug("finding TCopoteEmployee instance by example");
        try {
//            List results = getHibernateTemplate().createCriteria("person.daizhongde.migration.hibernate.pojo.TCopoteEmployee") .add(Example.create(instance)).list();

            List results = getHibernateTemplate().findByExample(instance);
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }    
    
    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.TCopoteEmployeeDAO#findByProperty(java.lang.String, java.lang.Object)
	 */
    @Override
	public List findByProperty(String propertyName, Object value) {
      log.debug("finding TCopoteEmployee instance with property: " + propertyName
            + ", value: " + value);
      try {
//         String queryString = "from TCopoteEmployee as model where model." 
//         						+ propertyName + "= ?";
//         Query queryObject = getHibernateTemplate().createQuery(queryString);
// 		 queryObject.setParameter(0, value);
// 		 return queryObject.list();

			String queryString = "from TCopoteEmployee as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
			
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.TCopoteEmployeeDAO#findByUin(java.lang.Object)
	 */
	@Override
	public List findByUin(Object uin
	) {
		return findByProperty(UIN, uin
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.TCopoteEmployeeDAO#findByPid(java.lang.Object)
	 */
	@Override
	public List findByPid(Object pid
	) {
		return findByProperty(PID, pid
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.TCopoteEmployeeDAO#findByName(java.lang.Object)
	 */
	@Override
	public List findByName(Object name
	) {
		return findByProperty(NAME, name
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.TCopoteEmployeeDAO#findByAlias(java.lang.Object)
	 */
	@Override
	public List findByAlias(Object alias
	) {
		return findByProperty(ALIAS, alias
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.TCopoteEmployeeDAO#findBySex(java.lang.Object)
	 */
	@Override
	public List findBySex(Object sex
	) {
		return findByProperty(SEX, sex
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.TCopoteEmployeeDAO#findByPos(java.lang.Object)
	 */
	@Override
	public List findByPos(Object pos
	) {
		return findByProperty(POS, pos
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.TCopoteEmployeeDAO#findByTel(java.lang.Object)
	 */
	@Override
	public List findByTel(Object tel
	) {
		return findByProperty(TEL, tel
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.TCopoteEmployeeDAO#findByBirth(java.lang.Object)
	 */
	@Override
	public List findByBirth(Object birth
	) {
		return findByProperty(BIRTH, birth
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.TCopoteEmployeeDAO#findBySlaveAlias(java.lang.Object)
	 */
	@Override
	public List findBySlaveAlias(Object slaveAlias
	) {
		return findByProperty(SLAVE_ALIAS, slaveAlias
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.TCopoteEmployeeDAO#findByDepartment(java.lang.Object)
	 */
	@Override
	public List findByDepartment(Object department
	) {
		return findByProperty(DEPARTMENT, department
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.TCopoteEmployeeDAO#findByMobile(java.lang.Object)
	 */
	@Override
	public List findByMobile(Object mobile
	) {
		return findByProperty(MOBILE, mobile
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.TCopoteEmployeeDAO#findByEmployeeNo(java.lang.Object)
	 */
	@Override
	public List findByEmployeeNo(Object employeeNo
	) {
		return findByProperty(EMPLOYEE_NO, employeeNo
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.TCopoteEmployeeDAO#findByEmployeeCardno(java.lang.Object)
	 */
	@Override
	public List findByEmployeeCardno(Object employeeCardno
	) {
		return findByProperty(EMPLOYEE_CARDNO, employeeCardno
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.TCopoteEmployeeDAO#findByEmployeeIdcard(java.lang.Object)
	 */
	@Override
	public List findByEmployeeIdcard(Object employeeIdcard
	) {
		return findByProperty(EMPLOYEE_IDCARD, employeeIdcard
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.TCopoteEmployeeDAO#findByLogname(java.lang.Object)
	 */
	@Override
	public List findByLogname(Object logname
	) {
		return findByProperty(LOGNAME, logname
		);
	}
	

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.TCopoteEmployeeDAO#findAll()
	 */
	@Override
	public List findAll() {
		log.debug("finding all TCopoteEmployee instances");
		try {
//			String queryString = "from TCopoteEmployee";
//	         Query queryObject = getHibernateTemplate().createQuery(queryString);
//			 return queryObject.list();

			String queryString = "from TCopoteEmployee";
			return getHibernateTemplate().find(queryString);
			
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.TCopoteEmployeeDAO#merge(person.daizhongde.migration.hibernate.pojo.TCopoteEmployee)
	 */
    @Override
	public TCopoteEmployee merge(TCopoteEmployee detachedInstance) {
        log.debug("merging TCopoteEmployee instance");
        try {
            TCopoteEmployee result = (TCopoteEmployee) getHibernateTemplate()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.TCopoteEmployeeDAO#attachDirty(person.daizhongde.migration.hibernate.pojo.TCopoteEmployee)
	 */
    @Override
	public void attachDirty(TCopoteEmployee instance) {
        log.debug("attaching dirty TCopoteEmployee instance");
        try {
        	getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.TCopoteEmployeeDAO#attachClean(person.daizhongde.migration.hibernate.pojo.TCopoteEmployee)
	 */
    @Override
	public void attachClean(TCopoteEmployee instance) {
        log.debug("attaching clean TCopoteEmployee instance");
        try {
//        	getHibernateTemplate().buildLockRequest(LockOptions.NONE).lock(instance);
//          	            log.debug("attach successful");

			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
			
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

	public static TCopoteEmployeeDAO getFromApplicationContext(ApplicationContext ctx) {
    	return (TCopoteEmployeeDAO) ctx.getBean("TCopoteEmployeeDAO");
	}
}