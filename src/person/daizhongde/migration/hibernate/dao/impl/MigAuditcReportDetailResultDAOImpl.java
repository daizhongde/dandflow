package person.daizhongde.migration.hibernate.dao.impl;

import java.util.List;

import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import person.daizhongde.virtue.dao.SpringHibernateDaoSupport;

import person.daizhongde.migration.hibernate.dao.MigAuditcReportDetailResultDAO;
import person.daizhongde.migration.hibernate.pojo.MigAuditcReportDetailResult;

/**
 * A data access object (DAO) providing persistence and search support for
 * MigAuditcReportDetailResult entities. Transaction control of the save(),
 * update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditcReportDetailResult
 * @author MyEclipse Persistence Tools
 */
public class MigAuditcReportDetailResultDAOImpl extends SpringHibernateDaoSupport implements MigAuditcReportDetailResultDAO {
	private static final Logger log = LoggerFactory
			.getLogger(MigAuditcReportDetailResultDAOImpl.class);
	protected void initDao() {
		// do nothing
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditcReportDetailResultDAO#save(person.daizhongde.migration.hibernate.pojo.MigAuditcReportDetailResult)
	 */
	@Override
	public void save(MigAuditcReportDetailResult transientInstance) {
		log.debug("saving MigAuditcReportDetailResult instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditcReportDetailResultDAO#delete(person.daizhongde.migration.hibernate.pojo.MigAuditcReportDetailResult)
	 */
	@Override
	public void delete(MigAuditcReportDetailResult persistentInstance) {
		log.debug("deleting MigAuditcReportDetailResult instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditcReportDetailResultDAO#findById(java.lang.Integer)
	 */
	@Override
	public MigAuditcReportDetailResult findById(java.lang.Integer id) {
		log.debug("getting MigAuditcReportDetailResult instance with id: " + id);
		try {
			MigAuditcReportDetailResult instance = (MigAuditcReportDetailResult) getHibernateTemplate()
					.get("person.daizhongde.migration.hibernate.pojo.MigAuditcReportDetailResult",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditcReportDetailResultDAO#findByExample(person.daizhongde.migration.hibernate.pojo.MigAuditcReportDetailResult)
	 */
	@Override
	public List findByExample(MigAuditcReportDetailResult instance) {
		log.debug("finding MigAuditcReportDetailResult instance by example");
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
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditcReportDetailResultDAO#findByProperty(java.lang.String, java.lang.Object)
	 */
	@Override
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding MigAuditcReportDetailResult instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from MigAuditcReportDetailResult as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditcReportDetailResultDAO#findByEntity(java.lang.Object)
	 */
	@Override
	public List findByEntity(Object entity) {
		return findByProperty(ENTITY, entity);
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditcReportDetailResultDAO#findByAuditItem(java.lang.Object)
	 */
	@Override
	public List findByAuditItem(Object auditItem) {
		return findByProperty(AUDIT_ITEM, auditItem);
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditcReportDetailResultDAO#findByEnumDesc(java.lang.Object)
	 */
	@Override
	public List findByEnumDesc(Object enumDesc) {
		return findByProperty(ENUM_DESC, enumDesc);
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditcReportDetailResultDAO#findBySplitFlag(java.lang.Object)
	 */
	@Override
	public List findBySplitFlag(Object splitFlag) {
		return findByProperty(SPLIT_FLAG, splitFlag);
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditcReportDetailResultDAO#findBySrcEnum(java.lang.Object)
	 */
	@Override
	public List findBySrcEnum(Object srcEnum) {
		return findByProperty(SRC_ENUM, srcEnum);
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditcReportDetailResultDAO#findByDstEnum(java.lang.Object)
	 */
	@Override
	public List findByDstEnum(Object dstEnum) {
		return findByProperty(DST_ENUM, dstEnum);
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditcReportDetailResultDAO#findBySrcCount(java.lang.Object)
	 */
	@Override
	public List findBySrcCount(Object srcCount) {
		return findByProperty(SRC_COUNT, srcCount);
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditcReportDetailResultDAO#findByDstCount(java.lang.Object)
	 */
	@Override
	public List findByDstCount(Object dstCount) {
		return findByProperty(DST_COUNT, dstCount);
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditcReportDetailResultDAO#findByAuditAuthor(java.lang.Object)
	 */
	@Override
	public List findByAuditAuthor(Object auditAuthor) {
		return findByProperty(AUDIT_AUTHOR, auditAuthor);
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditcReportDetailResultDAO#findByFaresDryrunId(java.lang.Object)
	 */
	@Override
	public List findByFaresDryrunId(Object faresDryrunId) {
		return findByProperty(FARES_DRYRUN_ID, faresDryrunId);
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditcReportDetailResultDAO#findByMinAnalysis(java.lang.Object)
	 */
	@Override
	public List findByMinAnalysis(Object minAnalysis) {
		return findByProperty(MIN_ANALYSIS, minAnalysis);
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditcReportDetailResultDAO#findAll()
	 */
	@Override
	public List findAll() {
		log.debug("finding all MigAuditcReportDetailResult instances");
		try {
			String queryString = "from MigAuditcReportDetailResult";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditcReportDetailResultDAO#merge(person.daizhongde.migration.hibernate.pojo.MigAuditcReportDetailResult)
	 */
	@Override
	public MigAuditcReportDetailResult merge(
			MigAuditcReportDetailResult detachedInstance) {
		log.debug("merging MigAuditcReportDetailResult instance");
		try {
			MigAuditcReportDetailResult result = (MigAuditcReportDetailResult) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditcReportDetailResultDAO#attachDirty(person.daizhongde.migration.hibernate.pojo.MigAuditcReportDetailResult)
	 */
	@Override
	public void attachDirty(MigAuditcReportDetailResult instance) {
		log.debug("attaching dirty MigAuditcReportDetailResult instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigAuditcReportDetailResultDAO#attachClean(person.daizhongde.migration.hibernate.pojo.MigAuditcReportDetailResult)
	 */
	@Override
	public void attachClean(MigAuditcReportDetailResult instance) {
		log.debug("attaching clean MigAuditcReportDetailResult instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static MigAuditcReportDetailResultDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (MigAuditcReportDetailResultDAO) ctx
				.getBean("MigAuditcReportDetailResultDAO");
	}
}