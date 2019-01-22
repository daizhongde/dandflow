package person.daizhongde.migration.hibernate.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import person.daizhongde.virtue.dao.SpringHibernateDaoSupport;

import person.daizhongde.migration.hibernate.dao.MigInsParaDAO;
import person.daizhongde.migration.hibernate.pojo.MigInsPara;

/**
 * A data access object (DAO) providing persistence and search support for
 * MigInsPara entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see person.daizhongde.migration.hibernate.pojotemp.MigInsPara
 * @author MyEclipse Persistence Tools
 */
public class MigInsParaDAOImpl extends SpringHibernateDaoSupport implements MigInsParaDAO {
	private static final Logger log = LoggerFactory
			.getLogger(MigInsParaDAOImpl.class);
	protected void initDao() {
		// do nothing
	}

    public  void deleteJobParaByJobInsIdAndNodeId(String jobInsId, String nodeId){
 	   Map condition=new HashMap(2); 
		   String sql="delete from mig_ins_para  where job_ins_id=:job_ins_id and node_id=:node_id";
		   condition.put("job_ins_id", jobInsId);
		   condition.put("node_id", nodeId);
		   this.sqlQueryExeUByMap(sql, condition);	
    }

	public  void newJobPara(MigInsPara mjp){
		this.save(mjp);
	}
	public  void updateJobPara(String jobInsId, String nodeId,Map<String,String> paras){
		Set<String> st=paras.keySet();
		
		for(String s:st){
		   Map condition=new HashMap(4);
		   String sql="update mig_ins_para set para_value=:para_value where job_ins_id=:job_ins_id and node_id=:node_id and para=:para";
		   condition.put("para_value", paras.get(s));
		   condition.put("node_id", nodeId);
		   condition.put("job_ins_id", jobInsId);
		   condition.put("para", s);           
		   this.sqlQueryExeUByMap(sql, condition);		   
		}
	}
	public  void deleteJobPara(String jobInsId, String nodeId,String para){
		   Map condition=new HashMap(3); 
		   String sql="delete from mig_ins_para  where job_ins_id=:job_ins_id and node_id=:node_id and para=:para";
		   condition.put("node_id", nodeId);
		   condition.put("job_ins_id", jobInsId);
		   condition.put("para", para);           
		   this.sqlQueryExeUByMap(sql, condition);	
		
	}
	public  void deleteJobPara(String jobInsId){
		   Map condition=new HashMap(1); 
		   String sql="delete from mig_ins_para  where job_ins_id=:job_ins_id";
		   condition.put("job_ins_id", jobInsId);     
		   this.sqlQueryExeUByMap(sql, condition);
	}
	public  void deleteJobPara(List<String> jobInsId){
		   Map condition=new HashMap(1); 
		   String sql="delete from mig_ins_para  where job_ins_id in (:job_ins_id) ";
		   condition.put("job_ins_id", jobInsId);     
		   this.sqlQueryExeUByMap(sql, condition);
	}
	public  List<MigInsPara> findByNodeId(String jobInsId, String nodeId){
		   Map condition=new HashMap(2); 
		   String sql="from MigInsPara  where id.jobInsId = :job_ins_id and id.nodeId = :node_id";
		   condition.put("node_id", nodeId);
		   condition.put("job_ins_id", jobInsId);
		   
		   return this.listAllByMap(sql, condition);
	}
	@SuppressWarnings("unchecked")
	public HashMap<String, String> findByNodeId2(String jobInsId, String nodeId){
		List<MigInsPara> list = this.findByNodeId(jobInsId, nodeId);
		HashMap<String, String> params = new HashMap<String, String>(list.size());// 创建行向量
		if( null == list || list.size()==0 ){
			return params;
		}
		for (int i = 0; i < list.size(); i++) {
			params.put(list.get(i).getId().getPara(), list.get(i).getParaValue() );// 添加列值
		}
		return params;
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigInsParaDAO#save(person.daizhongde.migration.hibernate.pojo.MigInsPara)
	 */
	@Override
	public void save(MigInsPara transientInstance) {
		log.debug("saving MigInsPara instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigInsParaDAO#delete(person.daizhongde.migration.hibernate.pojo.MigInsPara)
	 */
	@Override
	public void delete(MigInsPara persistentInstance) {
		log.debug("deleting MigInsPara instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}
	
	public List<MigInsPara> find(String jobInsId, String jobId, String nodeId){
		Map condition = new HashMap(3);
		condition.put("job_ins_id", jobInsId);
		condition.put("node_id", nodeId);
		condition.put("job_id", jobId);
		
		String HQL = "from MigInsPara where id.jobInsId = :job_ins_id and (id.nodeId = :node_id or id.nodeId = :job_id)";
		return this.listAllByMap(HQL, condition);
	}
	
//	public List<MigInsPara> find(String jobId, String taskId, String param){
//		Map condition = new HashMap(3);
//		condition.put("jobId", jobId);
//		condition.put("taskId", taskId);
//		condition.put("param", param);
//		
//		String HQL = "from MigInsPara where (id.nodeId = :jobId or id.nodeId = :taskId) and id.para= :param ";
//		return this.listAllByMap(HQL, condition);
//	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigInsParaDAO#findById(person.daizhongde.migration.hibernate.pojo.MigInsParaId)
	 */
	@Override
	public MigInsPara findById(
			person.daizhongde.migration.hibernate.pojo.MigInsParaId id) {
		log.debug("getting MigInsPara instance with id: " + id);
		try {
			MigInsPara instance = (MigInsPara) getHibernateTemplate().get(
					"person.daizhongde.migration.hibernate.pojo.MigInsPara", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigInsParaDAO#findByExample(person.daizhongde.migration.hibernate.pojo.MigInsPara)
	 */
	@Override
	public List findByExample(MigInsPara instance) {
		log.debug("finding MigInsPara instance by example");
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
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigInsParaDAO#findByProperty(java.lang.String, java.lang.Object)
	 */
	@Override
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding MigInsPara instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from MigInsPara as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigInsParaDAO#findByJobInsId(java.lang.Object)
	 */
	@Override
	public List findByJobInsId(Object jobInsId) {
		return findByProperty(JOB_INS_ID, jobInsId);
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigInsParaDAO#findByParaName(java.lang.Object)
	 */
	@Override
	public List findByParaName(Object paraName) {
		return findByProperty(PARA_NAME, paraName);
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigInsParaDAO#findByParaType(java.lang.Object)
	 */
	@Override
	public List findByParaType(Object paraType) {
		return findByProperty(PARA_TYPE, paraType);
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigInsParaDAO#findByParaValue(java.lang.Object)
	 */
	@Override
	public List findByParaValue(Object paraValue) {
		return findByProperty(PARA_VALUE, paraValue);
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigInsParaDAO#findAll()
	 */
	@Override
	public List findAll() {
		log.debug("finding all MigInsPara instances");
		try {
			String queryString = "from MigInsPara";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigInsParaDAO#merge(person.daizhongde.migration.hibernate.pojo.MigInsPara)
	 */
	@Override
	public MigInsPara merge(MigInsPara detachedInstance) {
		log.debug("merging MigInsPara instance");
		try {
			MigInsPara result = (MigInsPara) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigInsParaDAO#attachDirty(person.daizhongde.migration.hibernate.pojo.MigInsPara)
	 */
	@Override
	public void attachDirty(MigInsPara instance) {
		log.debug("attaching dirty MigInsPara instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigInsParaDAO#attachClean(person.daizhongde.migration.hibernate.pojo.MigInsPara)
	 */
	@Override
	public void attachClean(MigInsPara instance) {
		log.debug("attaching clean MigInsPara instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static MigInsParaDAO getFromApplicationContext(ApplicationContext ctx) {
		return (MigInsParaDAO) ctx.getBean("MigInsParaDAO");
	}
}