package person.daizhongde.migration.hibernate.dao.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import person.daizhongde.virtue.dao.SpringHibernateDaoSupport;
import person.daizhongde.virtue.util.test.Printer;

import person.daizhongde.migration.hibernate.dao.MigJobParaDAO;
import person.daizhongde.migration.hibernate.dto.MigJobParaDto;
import person.daizhongde.migration.hibernate.pojo.MigJobPara;

/**
 * A data access object (DAO) providing persistence and search support for
 * MigJobPara entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see person.daizhongde.migration.hibernate.dao.impl.MigJobPara
 * @author MyEclipse Persistence Tools
 */
public class MigJobParaDAOImpl extends SpringHibernateDaoSupport implements MigJobParaDAO {
	private static final Logger log = LoggerFactory
			.getLogger(MigJobParaDAOImpl.class);

	// property constants

	protected void initDao() {
		// do nothing
	}

    public  void deleteByNodeId(String nodeId){
    	   Map condition=new HashMap(1); 
		   String sql="delete from tool.mig_job_para  where node_id=:node_id";
		   condition.put("node_id", nodeId);
		   this.sqlQueryExeUByMap(sql, condition);	
    }

	
	public  void newJobPara(MigJobPara mjp){
		this.save(mjp);
	}
	
	public  void updateJobPara(String nodeId,Map<String,String> paras){
		Set<String> st=paras.keySet();
		
		for(String s:st){
		   Map condition=new HashMap(3);
		   String sql="update mig_job_para set para_value=:para_value where node_id=:node_id and para=:para";
		   condition.put("para_value", paras.get(s));
		   condition.put("node_id", nodeId);
		   condition.put("para", s);           
		   this.sqlQueryExeUByMap(sql, condition);		   
		}
	}
	public  void deleteJobPara(String nodeId,String para){
		   Map condition=new HashMap(2); 
		   String sql="delete from tool.mig_job_para  where node_id=:node_id and para=:para";
		   condition.put("node_id", nodeId);
		   condition.put("para", para);           
		   this.sqlQueryExeUByMap(sql, condition);
	}
	public  List<MigJobPara> findAllParaByNodeId(String nodeId){
		   Map condition=new HashMap(1); 
		   String sql="from MigJobPara  where id.nodeId=:node_id";
		   condition.put("node_id", nodeId);
		   
		   return this.listAllByMap(sql, condition);
	}
	@SuppressWarnings("unchecked")
	public Set<String> findAllParaByNodeId2(String nodeId){
		List<MigJobPara> list = this.findAllParaByNodeId(nodeId);
		Set<String> params = new HashSet<String>(list.size());// 创建行向量
		if( null == list || list.size()==0 ){
			return params;
		}
		for (int i = 0; i < list.size(); i++) {
			params.add( list.get(i).getId().getPara() );// 添加列值
		}
		return params;
	}
	@SuppressWarnings("unchecked")
	public Map<String, String> findAllParaByNodeId3(String nodeId){
		List<MigJobPara> list = this.findAllParaByNodeId(nodeId);
		Map<String, String> params = new HashMap<String, String>(list.size());// 创建行向量
		if( null == list || list.size()==0 ){
			return params;
		}
		for (int i = 0; i < list.size(); i++) {
			params.put( list.get(i).getId().getPara(), list.get(i).getParaValue() );// 添加列值
		}
		return params;
	}
	public List<MigJobPara> find(String jobId, String nodeId){
		Map condition = new HashMap(2);
		condition.put("node_id", nodeId);
		condition.put("job_id", jobId);
		
		String HQL = "from MigJobPara where id.nodeId = :node_id or id.nodeId = :job_id";
		return this.listAllByMap(HQL, condition);
	}
	
	public void instanceJobPara(String jobInsId, String nodeId ){
		Map condition = new HashMap(2);
		condition.put("node_id", nodeId);
		condition.put("job_ins_id", jobInsId);
		String sql="insert into mig_ins_para select :job_ins_id,node_id,para,para_name,para_type,para_value from tool.mig_job_para"
				+ " where node_id = :node_id";
		
		this.sqlQueryExeUByMap(sql, condition);
	}
	public void instanceJobPara(String jobInsId, String nodeId, List<MigJobParaDto> jobparamlist){
		Map condition = new HashMap(6);
		condition.put("node_id", nodeId);
		condition.put("job_ins_id", jobInsId);
		String sql="insert into mig_ins_para(job_ins_id,node_id,para,para_name,para_type,para_value) "
				+ "values(:job_ins_id, :node_id, :para, :para_name, :para_type, :para_value)";
		
//		System.out.println("jobInsId:"+jobInsId+",nodeId:"+nodeId+",jobparamlist.size():"+jobparamlist.size());
		
		if(null != jobparamlist && jobparamlist.size() !=0 ){
			for(MigJobParaDto e: jobparamlist){
				condition.put("para", e.getPara() );
				condition.put("para_name", e.getParaName() );
				condition.put("para_type", e.getParaType() );
				condition.put("para_value", e.getParaValue() );
				
				this.sqlQueryExeUByMap(sql, condition);
			}
		}
	}
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigJobParaDAO#save(person.daizhongde.migration.hibernate.pojo.MigJobPara)
	 */
	@Override
	public void save(MigJobPara transientInstance) {
		log.debug("saving MigJobPara instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigJobParaDAO#delete(person.daizhongde.migration.hibernate.pojo.MigJobPara)
	 */
	@Override
	public void delete(MigJobPara persistentInstance) {
		log.debug("deleting MigJobPara instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}
	
//	public List<MigJobPara> find(String jobId, String taskId){
//		Map condition = new HashMap(2);
//		condition.put("jobId", jobId);
//		condition.put("taskId", taskId);
//		
//		String HQL = "from MigJobPara where id.task = :jobId or id.task = :taskId ";
//		return this.listAllByMap(HQL, condition);
//	}
//	
//	public List<MigJobPara> find(String jobId, String taskId, String param){
//		Map condition = new HashMap(3);
//		condition.put("jobId", jobId);
//		condition.put("taskId", taskId);
//		condition.put("param", param);
//		
//		String HQL = "from MigJobPara where (id.task = :jobId or id.task = :taskId) and id.para= :param ";
//		return this.listAllByMap(HQL, condition);
//	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigJobParaDAO#findById(person.daizhongde.migration.hibernate.pojo.MigJobParaId)
	 */
	@Override
	public MigJobPara findById(
			person.daizhongde.migration.hibernate.pojo.MigJobParaId id) {
		log.debug("getting MigJobPara instance with id: " + id);
		try {
			MigJobPara instance = (MigJobPara) getHibernateTemplate().get(
					"person.daizhongde.migration.hibernate.pojo.MigJobPara", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigJobParaDAO#findByExample(person.daizhongde.migration.hibernate.pojo.MigJobPara)
	 */
	@Override
	public List findByExample(MigJobPara instance) {
		log.debug("finding MigJobPara instance by example");
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
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigJobParaDAO#findByProperty(java.lang.String, java.lang.Object)
	 */
	@Override
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding MigJobPara instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from MigJobPara as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigJobParaDAO#findAll()
	 */
	@Override
	public List findAll() {
		log.debug("finding all MigJobPara instances");
		try {
			String queryString = "from MigJobPara";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigJobParaDAO#merge(person.daizhongde.migration.hibernate.pojo.MigJobPara)
	 */
	@Override
	public MigJobPara merge(MigJobPara detachedInstance) {
		log.debug("merging MigJobPara instance");
		try {
			MigJobPara result = (MigJobPara) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigJobParaDAO#attachDirty(person.daizhongde.migration.hibernate.pojo.MigJobPara)
	 */
	@Override
	public void attachDirty(MigJobPara instance) {
		log.debug("attaching dirty MigJobPara instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigJobParaDAO#attachClean(person.daizhongde.migration.hibernate.pojo.MigJobPara)
	 */
	@Override
	public void attachClean(MigJobPara instance) {
		log.debug("attaching clean MigJobPara instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static MigJobParaDAO getFromApplicationContext(ApplicationContext ctx) {
		return (MigJobParaDAO) ctx.getBean("migJobParaDAO");
	}
}