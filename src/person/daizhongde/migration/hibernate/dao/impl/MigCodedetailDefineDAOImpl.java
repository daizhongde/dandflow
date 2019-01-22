package person.daizhongde.migration.hibernate.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import person.daizhongde.virtue.dao.SpringHibernateDaoSupport;

import person.daizhongde.migration.hibernate.dao.MigCodedetailDefineDAO;
import person.daizhongde.migration.hibernate.pojo.MigCodedetailDefine;

/**
 * A data access object (DAO) providing persistence and search support for
 * MigCodedetailDefine entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see person.daizhongde.migration.hibernate.dao.impl.MigCodedetailDefine
 * @author MyEclipse Persistence Tools
 */
public class MigCodedetailDefineDAOImpl extends SpringHibernateDaoSupport implements MigCodedetailDefineDAO {
	private static final Logger log = LoggerFactory
			.getLogger(MigCodedetailDefineDAOImpl.class);

	// property constants

	protected void initDao() {
		// do nothing
	}
	public List queryListByInfo(MigCodedetailDefine instance) {
        log.debug("queryListByInfo");
        try {
            List results = getHibernateTemplate().findByExample(instance);
            log.debug("queryListByInfo successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("queryListByInfo failed", re);
            throw re;
        }
    }   
	public  void updateById(Map<String,String> paras){
		 Set<String> st=paras.keySet();	
		 for(String s:st){
			 Map condition=new HashMap(2);
			 String sql="update mig_staticprar_define set PARA_VALUE=:PARA_VALUE where para=:para";
			 condition.put("para", s);
			 condition.put("PARA_VALUE", paras.get(s));
			 this.sqlQueryExeUByMap(sql, condition);	 
		 }
	}
	public  void deleteById(String para){
		Map condition=new HashMap(1);
		String sql="delete from tool.mig_staticprar_define where para=:para";
		condition.put("para", para);
		this.sqlQueryExeUByMap(sql, condition);
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigCodedetailDefineDAO#save(person.daizhongde.migration.hibernate.pojo.MigCodedetailDefine)
	 */
	@Override
	public void save(MigCodedetailDefine transientInstance) {
		log.debug("saving MigCodedetailDefine instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigCodedetailDefineDAO#delete(person.daizhongde.migration.hibernate.pojo.MigCodedetailDefine)
	 */
	@Override
	public void delete(MigCodedetailDefine persistentInstance) {
		log.debug("deleting MigCodedetailDefine instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigCodedetailDefineDAO#findById(person.daizhongde.migration.hibernate.pojo.MigCodedetailDefineId)
	 */
	@Override
	public MigCodedetailDefine findById(int id) {
		log.debug("getting MigCodedetailDefine instance with id: " + id);
		try {
			MigCodedetailDefine instance = (MigCodedetailDefine) getHibernateTemplate()
					.get("person.daizhongde.migration.hibernate.pojo.MigCodedetailDefine",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigCodedetailDefineDAO#findByExample(person.daizhongde.migration.hibernate.pojo.MigCodedetailDefine)
	 */
	@Override
	public List findByExample(MigCodedetailDefine instance) {
		log.debug("finding MigCodedetailDefine instance by example");
		try {
			List results = getHibernateTemplate().findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigCodedetailDefineDAO#findByProperty(java.lang.String, java.lang.Object)
	 */
	@Override
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding MigCodedetailDefine instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from MigCodedetailDefine as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigCodedetailDefineDAO#findAll()
	 */
	@Override
	public List findAll() {
		log.debug("finding all MigCodedetailDefine instances");
		try {
			String queryString = "from MigCodedetailDefine";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigCodedetailDefineDAO#merge(person.daizhongde.migration.hibernate.pojo.MigCodedetailDefine)
	 */
	@Override
	public MigCodedetailDefine merge(MigCodedetailDefine detachedInstance) {
		log.debug("merging MigCodedetailDefine instance");
		try {
			MigCodedetailDefine result = (MigCodedetailDefine) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigCodedetailDefineDAO#attachDirty(person.daizhongde.migration.hibernate.pojo.MigCodedetailDefine)
	 */
	@Override
	public void attachDirty(MigCodedetailDefine instance) {
		log.debug("attaching dirty MigCodedetailDefine instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigCodedetailDefineDAO#attachClean(person.daizhongde.migration.hibernate.pojo.MigCodedetailDefine)
	 */
	@Override
	public void attachClean(MigCodedetailDefine instance) {
		log.debug("attaching clean MigCodedetailDefine instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static MigCodedetailDefineDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (MigCodedetailDefineDAO) ctx.getBean("migStaticprarDefineDAO");
	}
}