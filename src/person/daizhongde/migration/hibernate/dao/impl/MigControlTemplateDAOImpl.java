
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

import person.daizhongde.migration.hibernate.dao.MigControlTemplateDAO;
import person.daizhongde.migration.hibernate.pojo.MigControlTemplate;

/**
 * A data access object (DAO) providing persistence and search support for
 * MigControlTemplate entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see person.daizhongde.migration.hibernate.dao.impl.MigControlTemplateDto
 * @author MyEclipse Persistence Tools
 */
public class MigControlTemplateDAOImpl extends SpringHibernateDaoSupport implements MigControlTemplateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(MigControlTemplateDAOImpl.class);

	// property constants

	protected void initDao() {
		// do nothing
	}

	public List getControlTemplateByCtlId(String ctlId){
		Map condition = new HashMap(1);
		condition.put("control_id", ctlId);
		String sql="select * from mig_control_template where control_id=:control_id";		
		return this.sqlQuerylistAllByMap(sql,condition);		
	}
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigControlTemplateDAO#save(person.daizhongde.migration.hibernate.pojo.MigControlTemplate)
	 */
	@Override
	public void save(MigControlTemplate transientInstance) {
		log.debug("saving MigControlTemplate instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigControlTemplateDAO#delete(person.daizhongde.migration.hibernate.pojo.MigControlTemplate)
	 */
	@Override
	public void delete(MigControlTemplate persistentInstance) {
		log.debug("deleting MigControlTemplate instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigControlTemplateDAO#findById(person.daizhongde.migration.hibernate.pojo.MigControlTemplateId)
	 */
	@Override
	public MigControlTemplate findById(
			person.daizhongde.migration.hibernate.pojo.MigControlTemplateId id) {
		log.debug("getting MigControlTemplate instance with id: " + id);
		try {
			MigControlTemplate instance = (MigControlTemplate) getHibernateTemplate()
					.get("person.daizhongde.migration.hibernate.pojo.MigControlTemplate",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<MigControlTemplate> findByControlId(String id) {
		log.debug("getting MigControlTemplate instance by control id: " + id);
		try {
			
			return (List<MigControlTemplate>)this.listAll(
					"from MigControlTemplate where id.controlId='"+id+"' order by id.paraId ");
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigControlTemplateDAO#findByExample(person.daizhongde.migration.hibernate.pojo.MigControlTemplate)
	 */
	@Override
	public List findByExample(MigControlTemplate instance) {
		log.debug("finding MigControlTemplate instance by example");
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
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigControlTemplateDAO#findByProperty(java.lang.String, java.lang.Object)
	 */
	@Override
	public List findByProperty(String propertyName, Object value) {
//		log.debug("finding MigControlTemplate instance with property: "
//				+ propertyName + ", value: " + value);
		try {
			String queryString = "from MigControlTemplate as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigControlTemplateDAO#findAll()
	 */
	@Override
	public List findAll() {
		log.debug("finding all MigControlTemplate instances");
		try {
			String queryString = "from MigControlTemplate";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigControlTemplateDAO#merge(person.daizhongde.migration.hibernate.pojo.MigControlTemplate)
	 */
	@Override
	public MigControlTemplate merge(MigControlTemplate detachedInstance) {
		log.debug("merging MigControlTemplate instance");
		try {
			MigControlTemplate result = (MigControlTemplate) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigControlTemplateDAO#attachDirty(person.daizhongde.migration.hibernate.pojo.MigControlTemplate)
	 */
	@Override
	public void attachDirty(MigControlTemplate instance) {
		log.debug("attaching dirty MigControlTemplate instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigControlTemplateDAO#attachClean(person.daizhongde.migration.hibernate.pojo.MigControlTemplate)
	 */
	@Override
	public void attachClean(MigControlTemplate instance) {
		log.debug("attaching clean MigControlTemplate instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static MigControlTemplateDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (MigControlTemplateDAO) ctx.getBean("migControlTemplateDAO");
	}
}