package person.daizhongde.migration.hibernate.dao.impl;

import java.util.List;

import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import person.daizhongde.virtue.dao.SpringHibernateDaoSupport;

import person.daizhongde.migration.hibernate.dao.MigAuditvConfigtreeDAO;
import person.daizhongde.migration.hibernate.pojo.MigAuditvConfigtree;

/**
 	* A data access object (DAO) providing persistence and search support for MigAuditvConfigtree entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditvConfigtree
  * @author MyEclipse Persistence Tools 
 */
public class MigAuditvConfigtreeDAOImpl extends SpringHibernateDaoSupport implements MigAuditvConfigtreeDAO  {
	     private static final Logger log = LoggerFactory.getLogger(MigAuditvConfigtreeDAOImpl.class);
		protected void initDao() {
		//do nothing
	}
    
	public List<MigAuditvConfigtree> findChildrenNoRecursive( Integer parentId )
	{
		log.debug("find Direct Children......");
		/* Condition back of OR to support a node link at multi-place */
		String queryString = "from MigAuditvConfigtree m " +
							"where CONCAT('|',m.parent,'|') LIKE '%|"+parentId+"|%'" +
							"order by m.name ";
//		m.parent = :parentId
//		Map map = new HashMap();
//		map.put( "parentId", parentId );
//		return this.listAllByMap( queryString, map );	
		return this.listAll( queryString );
	}
	public int updateParentWhenRemoveLink(int parent, int id ){
		String updateSQL = "UPDATE tool.`mig_auditv_configtree` "
				+ "SET parent = SUBSTRING(REPLACE(CONCAT('|',parent,'|'), CONCAT('|','"+parent+"','|'), '|'), "
				+ " 2, LENGTH(REPLACE(CONCAT('|',parent,'|'), CONCAT('|','"+parent+"','|'), '|'))-2) "
				+ "WHERE id="+id;
		return this.sqlQueryExeU(updateSQL);
	}
    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditvConfigtreeDAO#save(person.daizhongde.migration.hibernate.pojo.MigAuditvConfigtree)
	 */
    @Override
	public void save(MigAuditvConfigtree transientInstance) {
        log.debug("saving MigAuditvConfigtree instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditvConfigtreeDAO#delete(person.daizhongde.migration.hibernate.pojo.MigAuditvConfigtree)
	 */
	@Override
	public void delete(MigAuditvConfigtree persistentInstance) {
        log.debug("deleting MigAuditvConfigtree instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditvConfigtreeDAO#findById(java.lang.Integer)
	 */
    @Override
	public MigAuditvConfigtree findById( java.lang.Integer id) {
        log.debug("getting MigAuditvConfigtree instance with id: " + id);
        try {
            MigAuditvConfigtree instance = (MigAuditvConfigtree) getHibernateTemplate()
                    .get("person.daizhongde.migration.hibernate.pojo.MigAuditvConfigtree", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditvConfigtreeDAO#findByExample(person.daizhongde.migration.hibernate.pojo.MigAuditvConfigtree)
	 */
    @Override
	public List findByExample(MigAuditvConfigtree instance) {
        log.debug("finding MigAuditvConfigtree instance by example");
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
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditvConfigtreeDAO#findByProperty(java.lang.String, java.lang.Object)
	 */
    @Override
	public List findByProperty(String propertyName, Object value) {
      log.debug("finding MigAuditvConfigtree instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from MigAuditvConfigtree as model where model." 
         						+ propertyName + "= ?";
		 return getHibernateTemplate().find(queryString, value);
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditvConfigtreeDAO#findByParent(java.lang.Object)
	 */
	@Override
	public List findByParent(Object parent
	) {
		return findByProperty(PARENT, parent
		);
	}
	
	public List<MigAuditvConfigtree> findByParent2(Object parent ) {
		String queryString = "from MigAuditvConfigtree as m "
				+ "where CONCAT('|',m.parent,'|') LIKE '%|"+parent+"|%'";
		return this.listAll( queryString );
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditvConfigtreeDAO#findByName(java.lang.Object)
	 */
	@Override
	public List findByName(Object name
	) {
		return findByProperty(NAME, name
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditvConfigtreeDAO#findByContent(java.lang.Object)
	 */
	@Override
	public List findByContent(Object content
	) {
		return findByProperty(CONTENT, content
		);
	}
	public List<MigAuditvConfigtree> findByContent2(Object content ) {
		String queryString = "from MigAuditvConfigtree as m "
				+ "where m.content = '"+content+"'";
		return this.listAll( queryString );
	}
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditvConfigtreeDAO#findByIsleaf(java.lang.Object)
	 */
	@Override
	public List findByIsleaf(Object isleaf
	) {
		return findByProperty(ISLEAF, isleaf
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditvConfigtreeDAO#findByStatus(java.lang.Object)
	 */
	@Override
	public List findByStatus(Object status
	) {
		return findByProperty(STATUS, status
		);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditvConfigtreeDAO#findByRemark(java.lang.Object)
	 */
	@Override
	public List findByRemark(Object remark
	) {
		return findByProperty(REMARK, remark
		);
	}
	

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditvConfigtreeDAO#findAll()
	 */
	@Override
	public List findAll() {
		log.debug("finding all MigAuditvConfigtree instances");
		try {
			String queryString = "from MigAuditvConfigtree";
		 	return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditvConfigtreeDAO#merge(person.daizhongde.migration.hibernate.pojo.MigAuditvConfigtree)
	 */
    @Override
	public MigAuditvConfigtree merge(MigAuditvConfigtree detachedInstance) {
        log.debug("merging MigAuditvConfigtree instance");
        try {
            MigAuditvConfigtree result = (MigAuditvConfigtree) getHibernateTemplate()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditvConfigtreeDAO#attachDirty(person.daizhongde.migration.hibernate.pojo.MigAuditvConfigtree)
	 */
    @Override
	public void attachDirty(MigAuditvConfigtree instance) {
        log.debug("attaching dirty MigAuditvConfigtree instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    /* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditvConfigtreeDAO#attachClean(person.daizhongde.migration.hibernate.pojo.MigAuditvConfigtree)
	 */
    @Override
	public void attachClean(MigAuditvConfigtree instance) {
        log.debug("attaching clean MigAuditvConfigtree instance");
        try {
                      	getHibernateTemplate().lock(instance, LockMode.NONE);
                        log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

	public static MigAuditvConfigtreeDAO getFromApplicationContext(ApplicationContext ctx) {
    	return (MigAuditvConfigtreeDAO) ctx.getBean("MigAuditvConfigtreeDAO");
	}
}