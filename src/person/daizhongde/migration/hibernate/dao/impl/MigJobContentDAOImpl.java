package person.daizhongde.migration.hibernate.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import person.daizhongde.virtue.dao.SpringHibernateDaoSupport;

import person.daizhongde.migration.constant.NodeType;
import person.daizhongde.migration.hibernate.dao.MigJobContentDAO;
import person.daizhongde.migration.hibernate.pojo.MigJobContent;
import person.daizhongde.migration.hibernate.pojo.MigJobContentId;
import person.daizhongde.migration.hibernate.pojo.MigJobInfo;
import person.daizhongde.migration.hibernate.pojo.MigJobProcess;
import person.daizhongde.migration.hibernate.pojo.MigTaskInfo;

/**
 * A data access object (DAO) providing persistence and search support for
 * MigJobContent entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see person.daizhongde.migration.hibernate.dao.impl.MigJobContent
 * @author MyEclipse Persistence Tools
 */
public class MigJobContentDAOImpl extends SpringHibernateDaoSupport implements MigJobContentDAO {
	private static final Logger log = LoggerFactory
			.getLogger(MigJobContentDAOImpl.class);

	// property constants

	protected void initDao() {
		// do nothing
	}
	
	public int moveTasks2AnotherJob( String jobId, List<String> taskIds){
		Map condition = new HashMap(2);
		condition.put("taskIds", taskIds);
		condition.put("jobId", jobId);
		
		String sql="update mig_job_content set job_id = :jobId  where node_id in ( :taskIds ) and isleaf=1";
		return this.sqlQueryExeUByMap(sql,condition);	
	}

	/** 查询直接子任务   **/
	public List<MigTaskInfo> findDirectSubtask( String jobId ){
		String queryString = "from MigTaskInfo as model where model.taskId "
				+ " in ( select content.id.nodeId "
				+ "		   from MigJobContent as content "
				+ "		  where content.id.jobId = ? and isleaf=1 ) ";
		
		return (List<MigTaskInfo>)getHibernateTemplate().find(queryString, jobId);
	};
	
	/** 查询直接子作业   **/
	public List<MigJobInfo> findDirectSubJob( String jobId ){
		String queryString = "from MigJobInfo as model where model.jobId "
				+ " in ( select content.id.nodeId "
				+ "		   from MigJobContent as content "
				+ "		  where content.id.jobId = ? and isleaf=0 ) ";
		
		return (List<MigJobInfo>)getHibernateTemplate().find(queryString, jobId);
	};
	/** 查询直接子作业ID集合   **/
	public List<String> findDirectSubJobId( String jobId ){
		String queryString = "select content.id.nodeId "
				+ "		   from MigJobContent as content "
				+ "		  where content.id.jobId = ? and isleaf= " + NodeType.NOLEAF;
		
		return (List<String>)getHibernateTemplate().find(queryString, jobId);
	};
	public void updateCoords(String coords, String jobId, String nodeId ){
		Map condition = new HashMap(3);
		condition.put("coords", coords);
		condition.put("node_id", nodeId);
		condition.put("job_id", jobId);
		
		String sql="update mig_job_content set coords = :coords  where node_id = :node_id and job_id = :job_id";
		this.sqlQueryExeUByMap(sql,condition);	
	};
	
	@Override
	public void modifyStatus(String jobId, String nodeId, String status) {
		Map condition = new HashMap(3);
		condition.put("node_status", status);
		condition.put("node_id", nodeId);
		condition.put("job_id", jobId);

		String sql="update mig_job_content set node_status=:node_status  where node_id=:node_id and job_id=:job_id";
		this.sqlQueryExeUByMap(sql,condition);		
	}
	
	public  MigJobContent findByNodeId(String jobId,String nodeId){
		Map condition = new HashMap(2);
		condition.put("node_id", nodeId);
		condition.put("job_id", jobId);

		String HQL="from MigJobContent where id.nodeId=:node_id and id.jobId=:job_id";
		return (MigJobContent)this.listAllByMap(HQL,condition).get(0);
	}
	
	public  MigJobContent findByNodeId1(String jobId,String nodeId){
		Map condition = new HashMap(1);
		condition.put("node_id", nodeId);
//		condition.put("job_id", jobId);
		
		String HQL="from MigJobContent where id.nodeId=:node_id and isleaf=0";
		return (MigJobContent)this.listAllByMap(HQL,condition).get(0);
	}
	public  MigJobContent findByNodeId2(String jobId,String nodeId){
		Map condition = new HashMap(1);
		condition.put("node_id", nodeId);
//		condition.put("job_id", jobId);
		
		String HQL="from MigJobContent where id.nodeId=:node_id and isleaf=1";
		return (MigJobContent)this.listAllByMap(HQL,condition).get(0);
	}
	
	public  List<MigJobContent> findByNodeId(String nodeId){
		Map condition = new HashMap(1);
		condition.put("node_id", nodeId);
		String HQL="from MigJobContent where id.nodeId=:node_id";
		return this.listAllByMap(HQL,condition);
	}
	public  List<MigJobContent> findAllJobNodeByNodeId(String nodeId){
		Map condition = new HashMap(1);
		condition.put("node_id", nodeId);
		String HQL="from MigJobContent where id.nodeId=:node_id and isleaf="+NodeType.NOLEAF;
		return this.listAllByMap(HQL,condition);
	}
	public  List<MigJobContent> findAllJobNodeByNodeId(List<String> nodeId){
		Map condition = new HashMap(1);
		condition.put("node_id", nodeId);
		String HQL="from MigJobContent where id.nodeId in ( :node_id ) and isleaf="+NodeType.NOLEAF;
		return this.listAllByMap(HQL,condition);
	}
	public  List<MigJobContent> findByPartialPrePos(String PartialPrePos){
		String WherePartialPrePos = "%" + PartialPrePos + "%";
		Map condition = new HashMap(1);
		condition.put("prepos", WherePartialPrePos);
		String HQL="from MigJobContent where prepos like :prepos";
		return this.listAllByMap(HQL,condition);
		
	}

	public  List<MigJobContent> findByPartialPostPos(String PartialPostPos){
		String WherePartialPostPos = "%" + PartialPostPos + "%";
		Map condition = new HashMap(1);
		condition.put("postpos", WherePartialPostPos);
		String HQL="from MigJobContent where postpos like :postpos";
		return this.listAllByMap(HQL,condition);
	}
	
	public  void link(String jobId,String fromNode,String toNode){
		Map condition = new HashMap(3);
//		MigJobInfo job1 = new MigJobInfo();
//		job1.setJobId(jobId);
		MigJobContent jobc = this.findById(  new MigJobContentId( jobId, fromNode ) );
		String postpos = jobc.getPostpos();
		postpos = "".equalsIgnoreCase(postpos) ? toNode : postpos + "|" + toNode;
		
		condition.put("postpos", postpos);
		condition.put("node_id", fromNode);
		condition.put("job_id", jobId);
		
		String sql = "update mig_job_content set postpos = :postpos where node_id= :node_id and job_id= :job_id";
		this.sqlQueryExeUByMap(sql,condition);	
		
		Map condition2 = new HashMap(3);
//		MigJobInfo job = new MigJobInfo();
//		job.setJobId(jobId);
		MigJobContent jobt = this.findById(  new MigJobContentId( jobId, toNode ) );
		String prepos = jobt.getPrepos();
		prepos = "".equalsIgnoreCase(prepos) ? fromNode : prepos + "|" + fromNode;
		
		condition2.put("prepos", prepos);
		condition2.put("node_id", toNode);
		condition2.put("job_id", jobId);
		sql = "update mig_job_content set prepos = :prepos where node_id = :node_id and job_id = :job_id";
		this.sqlQueryExeUByMap(sql,condition2);	
	}
	
	public  void unlink(String jobId,String fromNode,String toNode){
		Map condition = new HashMap(3);

//		MigJobInfo job1 = new MigJobInfo();
//		job1.setJobId(jobId);
		MigJobContent jobc = this.findById(  new MigJobContentId( jobId, fromNode ) );
		String postpos = jobc.getPostpos();

		postpos = "|"+postpos;
		postpos = postpos.replaceFirst( "\\|"+toNode, "" );
		postpos = postpos.replaceFirst( "^\\|", "" );

		condition.put("postpos", postpos);
		condition.put("node_id", fromNode);
		condition.put("job_id", jobId);
		String sql="update mig_job_content set postpos=:postpos  where node_id=:node_id and job_id=:job_id";
		this.sqlQueryExeUByMap(sql,condition);
		
		Map condition2 = new HashMap(3);
//		MigJobInfo job = new MigJobInfo();
//		job.setJobId(jobId);
		MigJobContent jobt = this.findById( new MigJobContentId( jobId, toNode ) );
		String prepos = jobt.getPrepos();

		prepos = "|"+prepos;
		prepos = prepos.replaceFirst("\\|"+fromNode, "");
		prepos = prepos.replaceFirst( "^\\|", "" );

		condition2.put("prepos", prepos);
		condition2.put("node_id", toNode);
		condition2.put("job_id", jobId);
		sql="update mig_job_content set prepos= :prepos  where node_id=:node_id and job_id=:job_id";
		this.sqlQueryExeUByMap(sql,condition2);	
	}
	public  List<MigJobContent> findNodesByJob(String jobId){
		Map condition = new HashMap(1);
		condition.put("job_id", jobId);
		String HQL="from MigJobContent where id.jobId =:job_id";
		return this.listAllByMap(HQL, condition);
	}
	public  List<MigJobContent> findNodesByJob_Recursive(String jobId){
		List<MigJobContent> ret = new ArrayList<MigJobContent>();
		
		Map condition = new HashMap(1);
		condition.put("job_id", jobId);
		String HQL="from MigJobContent where id.jobId =:job_id";
		List<MigJobContent> temp = this.listAllByMap(HQL, condition);
		for(int i=0, j=temp.size(); i<j; i++ ){
			MigJobContent e = temp.get(i);
			if( e.getIsleaf() == NodeType.NOLEAF ){
				ret.addAll ( this.findNodesByJob_Recursive( e.getId().getNodeId() ) );
			}
		}
		ret.addAll(temp);
		return ret;
	}
	
//	public  List findTasksByJobid(String jobId){
//		Map condition = new HashMap(1);
//		condition.put("job_id", jobId);
//		String sql="select *  from tool.mig_job_content where job_id=:job_id";
//		return this.sqlQuerylistAllByMap(sql,condition);
//	}
	
	public  void deleteByNodeid(String jobId,String nodeId){
		Map condition = new HashMap(2);
		condition.put("node_id", nodeId);
		condition.put("job_id", jobId);
		String sql="delete from tool.mig_job_content where node_id=:node_id and job_id=:job_id";
		this.sqlQueryExeUByMap(sql,condition);
	}
	
	public  void deleteByNodeid(String nodeId){
		Map condition = new HashMap(1);
		condition.put("node_id", nodeId);
		String sql="delete from tool.mig_job_content where node_id=:node_id";
		this.sqlQueryExeUByMap(sql,condition);
	}
	
	public  void deleteSubsById(String nodeId){
		Map condition = new HashMap(1);
		condition.put("node_id", nodeId);
		String sql="delete from tool.mig_job_content where job_id=:node_id";
		this.sqlQueryExeUByMap(sql,condition);
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigJobContentDAO#save(person.daizhongde.migration.hibernate.pojo.MigJobContent)
	 */
	@Override
	public void save(MigJobContent transientInstance) {
		log.debug("saving MigJobContent instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigJobContentDAO#delete(person.daizhongde.migration.hibernate.pojo.MigJobContent)
	 */
	@Override
	public void delete(MigJobContent persistentInstance) {
		log.debug("deleting MigJobContent instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigJobContentDAO#findById(person.daizhongde.migration.hibernate.pojo.MigJobContentId)
	 */
	@Override
	public MigJobContent findById(
			person.daizhongde.migration.hibernate.pojo.MigJobContentId id) {
		log.debug("getting MigJobContent instance with id: " + id);
		try {
			MigJobContent instance = (MigJobContent) getHibernateTemplate()
					.get("person.daizhongde.migration.hibernate.pojo.MigJobContent",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigJobContentDAO#findByExample(person.daizhongde.migration.hibernate.pojo.MigJobContent)
	 */
	@Override
	public List findByExample(MigJobContent instance) {
		log.debug("finding MigJobContent instance by example");
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
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigJobContentDAO#findByProperty(java.lang.String, java.lang.Object)
	 */
	@Override
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding MigJobContent instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from MigJobContent as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigJobContentDAO#findAll()
	 */
	@Override
	public List findAll() {
		log.debug("finding all MigJobContent instances");
		try {
			String queryString = "from MigJobContent";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigJobContentDAO#merge(person.daizhongde.migration.hibernate.pojo.MigJobContent)
	 */
	@Override
	public MigJobContent merge(MigJobContent detachedInstance) {
		log.debug("merging MigJobContent instance");
		try {
			MigJobContent result = (MigJobContent) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigJobContentDAO#attachDirty(person.daizhongde.migration.hibernate.pojo.MigJobContent)
	 */
	@Override
	public void attachDirty(MigJobContent instance) {
		log.debug("attaching dirty MigJobContent instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigJobContentDAO#attachClean(person.daizhongde.migration.hibernate.pojo.MigJobContent)
	 */
	@Override
	public void attachClean(MigJobContent instance) {
		log.debug("attaching clean MigJobContent instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static MigJobContentDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (MigJobContentDAO) ctx.getBean("migJobContentDAO");
	}


}