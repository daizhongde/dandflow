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

import person.daizhongde.migration.hibernate.dao.MigTaskInfoDAO;
import person.daizhongde.migration.hibernate.pojo.MigJobInfo;
import person.daizhongde.migration.hibernate.pojo.MigTaskInfo;

/**
 * A data access object (DAO) providing persistence and search support for
 * MigTaskInfo entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see person.daizhongde.migration.hibernate.dao.impl.MigTaskInfo
 * @author MyEclipse Persistence Tools
 */
public class MigTaskInfoDAOImpl extends SpringHibernateDaoSupport implements MigTaskInfoDAO {
	private static final Logger log = LoggerFactory
			.getLogger(MigTaskInfoDAOImpl.class);
	protected void initDao() {
		// do nothing
	}
	public int countByComId(String comId){
		Map condition = new HashMap(1);
		condition.put("com_Id", comId);
		
		String sql="select count(*) from tool.mig_task_info where com_Id=:com_Id";
		return new Integer( this.sqlQueryfindaValueByMap(sql, condition).toString() ).intValue();
	}
	public void deleteByTaskId(String taskId){
		Map condition = new HashMap(1);
		condition.put("task_id", taskId);
		
		String sql="delete from tool.mig_task_info where task_id=:task_id";
		this.sqlQueryExeUByMap(sql,condition);
	}


	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigTaskInfoDAO#save(person.daizhongde.migration.hibernate.pojo.MigTaskInfo)
	 */
	@Override
	public void save(MigTaskInfo transientInstance) {
		log.debug("saving MigTaskInfo instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

//	public void updateNameById( String taskName, String taskId ){
//		Map condition = new HashMap(1);
//		condition.put("taskName", taskName);
//		condition.put("taskId", taskId);
//		String sql = "update tool.mig_task_info set task_name = :taskName where task_id = :taskId";
//		this.sqlQueryExeUByMap(sql, condition);	
//	};
	public void updateTaskById( String taskName, String taskRemark, String taskId, String comId ){
		Map condition = new HashMap(4);
		condition.put("taskName", taskName);
		condition.put("taskId", taskId);
		condition.put("taskRemark", taskRemark);
		condition.put("comId", comId);
		String sql = "update tool.mig_task_info set com_id = :comId, task_name = :taskName, task_remark = :taskRemark where task_id = :taskId";
		this.sqlQueryExeUByMap(sql, condition);	
	};
	
	public void updateTaskComById( String comId, String taskId ){
		Map condition = new HashMap(2);
		condition.put("com_id", comId);
		condition.put("task_id", taskId);

		String sql = "update tool.mig_task_info set com_id = :com_id where task_id = :task_id";
		this.sqlQueryExeUByMap(sql, condition);	
	};
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigTaskInfoDAO#delete(person.daizhongde.migration.hibernate.pojo.MigTaskInfo)
	 */
	@Override
	public void delete(MigTaskInfo persistentInstance) {
		log.debug("deleting MigTaskInfo instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigTaskInfoDAO#findById(java.lang.String)
	 */
	@Override
	public MigTaskInfo findById(java.lang.String id) {
//		log.debug("getting MigTaskInfo instance with id: " + id);
		try {
			MigTaskInfo instance = (MigTaskInfo) getHibernateTemplate()
					.get("person.daizhongde.migration.hibernate.pojo.MigTaskInfo",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigTaskInfoDAO#findByExample(person.daizhongde.migration.hibernate.pojo.MigTaskInfo)
	 */
	@Override
	public List findByExample(MigTaskInfo instance) {
		log.debug("finding MigTaskInfo instance by example");
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
	 * @see person.daizhongde.migration.hibernate.pojotemp.MigTaskInfo#findByProperty(java.lang.String, java.lang.Object)
	 */
	@Override
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding MigTaskInfo instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from MigTaskInfo as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.pojotemp.MigTaskInfo#findByTaskName(java.lang.Object)
	 */
	@Override
	public List findByTaskName(Object taskName) {
		return findByProperty(TASK_NAME, taskName);
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.pojotemp.MigTaskInfo#findByComId(java.lang.Object)
	 */
	@Override
	public List findByComId(Object comId) {
		return findByProperty(COM_ID, comId);
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.pojotemp.MigTaskInfo#findByTaskAuthor(java.lang.Object)
	 */
	@Override
	public List findByTaskAuthor(Object taskAuthor) {
		return findByProperty(TASK_AUTHOR, taskAuthor);
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.pojotemp.MigTaskInfo#findByTaskRemark(java.lang.Object)
	 */
	@Override
	public List findByTaskRemark(Object taskRemark) {
		return findByProperty(TASK_REMARK, taskRemark);
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.pojotemp.MigTaskInfo#findAll()
	 */
	@Override
	public List findAll() {
		log.debug("finding all MigTaskInfo instances");
		try {
			String queryString = "from MigTaskInfo";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.pojotemp.MigTaskInfo#merge(person.daizhongde.migration.hibernate.pojo.MigTaskInfo)
	 */
	@Override
	public MigTaskInfo merge(MigTaskInfo detachedInstance) {
		log.debug("merging MigTaskInfo instance");
		try {
			MigTaskInfo result = (MigTaskInfo) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.pojotemp.MigTaskInfo#attachDirty(person.daizhongde.migration.hibernate.pojo.MigTaskInfo)
	 */
	@Override
	public void attachDirty(MigTaskInfo instance) {
		log.debug("attaching dirty MigTaskInfo instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.pojotemp.MigTaskInfo#attachClean(person.daizhongde.migration.hibernate.pojo.MigTaskInfo)
	 */
	@Override
	public void attachClean(MigTaskInfo instance) {
		log.debug("attaching clean MigTaskInfo instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static MigTaskInfoDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (MigTaskInfoDAO) ctx.getBean("migTaskInfoDAO");
	}
}