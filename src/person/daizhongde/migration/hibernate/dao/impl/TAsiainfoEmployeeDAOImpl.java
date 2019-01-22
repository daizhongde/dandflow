package person.daizhongde.migration.hibernate.dao.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import person.daizhongde.virtue.dao.SpringHibernateDaoSupport;

import person.daizhongde.migration.hibernate.dao.TAsiainfoEmployeeDAO;
import person.daizhongde.migration.hibernate.pojo.TAsiainfoEmployee;

/**
 * A data access object (DAO) providing persistence and search support for
 * TAsiainfoEmployee entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see person.daizhongde.migration.hibernate.dao.impl.TAsiainfoEmployee
 * @author MyEclipse Persistence Tools
 */
public class TAsiainfoEmployeeDAOImpl extends SpringHibernateDaoSupport implements TAsiainfoEmployeeDAO {
	private static final Logger log = LoggerFactory
			.getLogger(TAsiainfoEmployeeDAOImpl.class);
	protected void initDao() {
		// do nothing
	}

	public List<TAsiainfoEmployee> findAllBoss(){
		try {
//			String queryString = "from TAsiainfoEmployee WHERE supervisor_id IS NULL ORDER BY org_name,last_name";
//			return (List<TAsiainfoEmployee>)getHibernateTemplate().find(queryString);
			return this.NamedQuery("findAllBoss");
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	public List findChildrenNoRecursive( Integer parentId )
	{
		log.debug("findChildren......");
//		String queryString = "from TAsiainfoEmployee m " +
//							"where m.supervisor_id = :parentId " +
//							"ORDER BY org_name,last_name ";  // CIcode CIname
		Map condition = new HashMap();
		condition.put( "parentId", parentId );
		return this.NamedQuery("findChildrenNoRecursive", condition);
//		return this.listAllByMap( queryString, map );	
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.TAsiainfoEmployeeDAO#save(person.daizhongde.migration.hibernate.pojo.TAsiainfoEmployee)
	 */
	@Override
	public void save(TAsiainfoEmployee transientInstance) {
		log.debug("saving TAsiainfoEmployee instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.TAsiainfoEmployeeDAO#delete(person.daizhongde.migration.hibernate.pojo.TAsiainfoEmployee)
	 */
	@Override
	public void delete(TAsiainfoEmployee persistentInstance) {
		log.debug("deleting TAsiainfoEmployee instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.TAsiainfoEmployeeDAO#findById(java.lang.String)
	 */
	@Override
	public TAsiainfoEmployee findById(java.lang.String id) {
		log.debug("getting TAsiainfoEmployee instance with id: " + id);
		try {
			TAsiainfoEmployee instance = (TAsiainfoEmployee) getHibernateTemplate()
					.get("person.daizhongde.migration.hibernate.pojo.TAsiainfoEmployee",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.TAsiainfoEmployeeDAO#findByExample(person.daizhongde.migration.hibernate.pojo.TAsiainfoEmployee)
	 */
	@Override
	public List findByExample(TAsiainfoEmployee instance) {
		log.debug("finding TAsiainfoEmployee instance by example");
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
	 * @see person.daizhongde.migration.hibernate.dao.impl.TAsiainfoEmployeeDAO#findByProperty(java.lang.String, java.lang.Object)
	 */
	@Override
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding TAsiainfoEmployee instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from TAsiainfoEmployee as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.TAsiainfoEmployeeDAO#findBySbuId(java.lang.Object)
	 */
	@Override
	public List findBySbuId(Object sbuId) {
		return findByProperty(SBU_ID, sbuId);
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.TAsiainfoEmployeeDAO#findBySbu(java.lang.Object)
	 */
	@Override
	public List findBySbu(Object sbu) {
		return findByProperty(SBU, sbu);
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.TAsiainfoEmployeeDAO#findByCompanyId(java.lang.Object)
	 */
	@Override
	public List findByCompanyId(Object companyId) {
		return findByProperty(COMPANY_ID, companyId);
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.TAsiainfoEmployeeDAO#findByCompany(java.lang.Object)
	 */
	@Override
	public List findByCompany(Object company) {
		return findByProperty(COMPANY, company);
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.TAsiainfoEmployeeDAO#findByOrganizationId(java.lang.Object)
	 */
	@Override
	public List findByOrganizationId(Object organizationId) {
		return findByProperty(ORGANIZATION_ID, organizationId);
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.TAsiainfoEmployeeDAO#findByOrgName(java.lang.Object)
	 */
	@Override
	public List findByOrgName(Object orgName) {
		return findByProperty(ORG_NAME, orgName);
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.TAsiainfoEmployeeDAO#findByOffice(java.lang.Object)
	 */
	@Override
	public List findByOffice(Object office) {
		return findByProperty(OFFICE, office);
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.TAsiainfoEmployeeDAO#findByPager(java.lang.Object)
	 */
	@Override
	public List findByPager(Object pager) {
		return findByProperty(PAGER, pager);
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.TAsiainfoEmployeeDAO#findByPersonId(java.lang.Object)
	 */
	@Override
	public List findByPersonId(Object personId) {
		return findByProperty(PERSON_ID, personId);
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.TAsiainfoEmployeeDAO#findByFirstName(java.lang.Object)
	 */
	@Override
	public List findByFirstName(Object firstName) {
		return findByProperty(FIRST_NAME, firstName);
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.TAsiainfoEmployeeDAO#findByLastName(java.lang.Object)
	 */
	@Override
	public List findByLastName(Object lastName) {
		return findByProperty(LAST_NAME, lastName);
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.TAsiainfoEmployeeDAO#findByFullName(java.lang.Object)
	 */
	@Override
	public List findByFullName(Object fullName) {
		return findByProperty(FULL_NAME, fullName);
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.TAsiainfoEmployeeDAO#findByEmailAddress(java.lang.Object)
	 */
	@Override
	public List findByEmailAddress(Object emailAddress) {
		return findByProperty(EMAIL_ADDRESS, emailAddress);
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.TAsiainfoEmployeeDAO#findByAge(java.lang.Object)
	 */
	@Override
	public List findByAge(Object age) {
		return findByProperty(AGE, age);
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.TAsiainfoEmployeeDAO#findByAssignmentId(java.lang.Object)
	 */
	@Override
	public List findByAssignmentId(Object assignmentId) {
		return findByProperty(ASSIGNMENT_ID, assignmentId);
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.TAsiainfoEmployeeDAO#findByClass_(java.lang.Object)
	 */
	@Override
	public List findByClass_(Object class_) {
		return findByProperty(CLASS_, class_);
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.TAsiainfoEmployeeDAO#findByWorkingLocation(java.lang.Object)
	 */
	@Override
	public List findByWorkingLocation(Object workingLocation) {
		return findByProperty(WORKING_LOCATION, workingLocation);
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.TAsiainfoEmployeeDAO#findBySeatNo(java.lang.Object)
	 */
	@Override
	public List findBySeatNo(Object seatNo) {
		return findByProperty(SEAT_NO, seatNo);
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.TAsiainfoEmployeeDAO#findByMobile(java.lang.Object)
	 */
	@Override
	public List findByMobile(Object mobile) {
		return findByProperty(MOBILE, mobile);
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.TAsiainfoEmployeeDAO#findByNtAccount(java.lang.Object)
	 */
	@Override
	public List findByNtAccount(Object ntAccount) {
		return findByProperty(NT_ACCOUNT, ntAccount);
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.TAsiainfoEmployeeDAO#findBySupervisorId(java.lang.Object)
	 */
	@Override
	public List findBySupervisorId(Object supervisorId) {
		return findByProperty(SUPERVISOR_ID, supervisorId);
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.TAsiainfoEmployeeDAO#findBySupervisorName(java.lang.Object)
	 */
	@Override
	public List findBySupervisorName(Object supervisorName) {
		return findByProperty(SUPERVISOR_NAME, supervisorName);
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.TAsiainfoEmployeeDAO#findByHighestDegree(java.lang.Object)
	 */
	@Override
	public List findByHighestDegree(Object highestDegree) {
		return findByProperty(HIGHEST_DEGREE, highestDegree);
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.TAsiainfoEmployeeDAO#findAll()
	 */
	@Override
	public List findAll() {
		log.debug("finding all TAsiainfoEmployee instances");
		try {
			String queryString = "from TAsiainfoEmployee";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.TAsiainfoEmployeeDAO#merge(person.daizhongde.migration.hibernate.pojo.TAsiainfoEmployee)
	 */
	@Override
	public TAsiainfoEmployee merge(TAsiainfoEmployee detachedInstance) {
		log.debug("merging TAsiainfoEmployee instance");
		try {
			TAsiainfoEmployee result = (TAsiainfoEmployee) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.TAsiainfoEmployeeDAO#attachDirty(person.daizhongde.migration.hibernate.pojo.TAsiainfoEmployee)
	 */
	@Override
	public void attachDirty(TAsiainfoEmployee instance) {
		log.debug("attaching dirty TAsiainfoEmployee instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.TAsiainfoEmployeeDAO#attachClean(person.daizhongde.migration.hibernate.pojo.TAsiainfoEmployee)
	 */
	@Override
	public void attachClean(TAsiainfoEmployee instance) {
		log.debug("attaching clean TAsiainfoEmployee instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static TAsiainfoEmployeeDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (TAsiainfoEmployeeDAO) ctx.getBean("TAsiainfoEmployeeDAO");
	}
}