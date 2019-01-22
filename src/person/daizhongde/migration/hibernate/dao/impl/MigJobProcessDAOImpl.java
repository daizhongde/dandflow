package person.daizhongde.migration.hibernate.dao.impl;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import person.daizhongde.virtue.dao.SpringHibernateDaoSupport;
import person.daizhongde.virtue.util.test.Printer;

import person.daizhongde.migration.constant.TaskState;
import person.daizhongde.migration.exception.BusinessException;
import person.daizhongde.migration.hibernate.dao.MigJobProcessDAO;
import person.daizhongde.migration.hibernate.pojo.MigJobProcess;

/**
 * A data access object (DAO) providing persistence and search support for
 * MigJobProcess entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see person.daizhongde.migration.hibernate.dao.impl.MigJobProcess
 * @author MyEclipse Persistence Tools
 */
public class MigJobProcessDAOImpl extends SpringHibernateDaoSupport implements MigJobProcessDAO {
	private static final Logger log = LoggerFactory
			.getLogger(MigJobProcessDAOImpl.class);
	protected void initDao() {
		// do nothing
	}
	
	public int sqlQueryUpdateProcessState_4recursive1(String jobInsId, String node_id){
		Map condition = new HashMap(2);
		condition.put("job_ins_id", jobInsId);
		condition.put("node_id", node_id);
		String sql="UPDATE tool.mig_job_process SET STATUS=1 "
				+ "  WHERE job_ins_id= :job_ins_id AND node_id= :node_id ";
		return this.sqlQueryExeUByMap(sql,condition);
	}
	
	public  void deleteByJobInsId(String jobInsId){
		Map condition = new HashMap(1);
		condition.put("job_ins_id", jobInsId);
		String sql="delete from mig_job_process where job_ins_id=:job_ins_id";
		this.sqlQueryExeUByMap(sql,condition);
	}

	public  void deleteByJobInsId(List<String> jobInsId){
		Map condition = new HashMap(1);
		condition.put("job_ins_id", jobInsId);
		String sql="delete from mig_job_process where job_ins_id in ( :job_ins_id )";
		this.sqlQueryExeUByMap(sql,condition);
	}
	
	public int sqlQueryUpdateProcessState(String processId, int status, int oldstatus){
		Map condition = new HashMap(3);
		condition.put("process_id", processId);
		condition.put("status", status);
		condition.put("oldstats", oldstatus);

		String SQL = "update mig_job_process set status = :status"
				+ " where process_id = :process_id and status = :oldstats";
		return this.sqlQueryExeUByMap(SQL, condition);
	}
	public int sqlQueryUpdateProcessState_init2Running( String processId ){
		Map condition = new HashMap(1);
		condition.put("process_id", processId);

		String SQL = "update mig_job_process "
				+ "      set status = "+TaskState.EXECUTING+", begin_time=NOW() "
				+ "    where process_id = :process_id and status = "+TaskState.INITIAL;
		return this.sqlQueryExeUByMap(SQL, condition);
	}
	public int sqlQueryUpdateProcessState_2Running( String processId ){
		Map condition = new HashMap(1);
		condition.put("process_id", processId);

		String SQL = "update mig_job_process "
				+ "      set status = "+TaskState.EXECUTING+", begin_time=NOW() "
				+ "    where process_id = :process_id";
		return this.sqlQueryExeUByMap(SQL, condition);
	}
	public int sqlQueryUpdateProcessState_2Running2( String processId ){
		Map condition = new HashMap(1);
		condition.put("process_id", processId);

		String SQL = "update mig_job_process "
				+ "      set status = "+TaskState.EXECUTING+" "
				+ "    where process_id = :process_id";
		return this.sqlQueryExeUByMap(SQL, condition);
	}
	public int sqlQueryUpdateProcessState_Running2Finish( String processId, String remark ){
		Map condition = new HashMap(2);
		condition.put("process_id", processId);
		condition.put("remark", remark);

		String SQL = "update mig_job_process "
				+ "      set status = "+TaskState.FINISH+", end_time=NOW(), "
				+ "          remark = :remark "
				+ "    where process_id = :process_id and status = "+TaskState.EXECUTING;
		return this.sqlQueryExeUByMap(SQL, condition);
	}
	public int sqlQueryUpdateProcessState_2Finish( String processId){
		Map condition = new HashMap(1);
		condition.put("process_id", processId);

		String SQL = "update mig_job_process "
				+ "      set status = "+TaskState.FINISH+", end_time=NOW(), "
				+ "          remark = CASE STATUS WHEN 1 THEN 'Finish(Normal)' WHEN -1 THEN 'Finish(Problem have been Processed)' ELSE 'Finish(From other state)' END "
				+ "    where process_id = :process_id";
		return this.sqlQueryExeUByMap(SQL, condition);
	}
	
	public int sqlQueryUpdateProcessState_2Init( String processId ){
		Map condition = new HashMap(1);
		condition.put("process_id", processId);
		
		String SQL = "update mig_job_process "
				+ "      set status = "+TaskState.INITIAL+", begin_time=null, end_time=null, "
				+ "          remark = 'Task has been reset!' "
				+ "    where process_id = :process_id and status != "+TaskState.EXECUTING;
		return this.sqlQueryExeUByMap(SQL, condition);
	}
	public int sqlQueryUpdateProcessStateandRemark(String processId, String remark, int status, int oldstatus){
		Map condition = new HashMap(4);
		condition.put("process_id", processId);
		condition.put("status", status);
		condition.put("oldstats", oldstatus);
		condition.put("remark", remark);
		
		String SQL = "update mig_job_process set status = :status, remark = :remark "
				+ " where process_id = :process_id and status = :oldstats";
		return this.sqlQueryExeUByMap(SQL, condition);
	}
	
//	public int sqlQueryUpdateProcessState(String processId, int status, List<Integer> oldstatus){
//		Map condition = new HashMap(3);
//		condition.put("process_id", processId);
//		condition.put("status", status);
//		condition.put("oldstats", oldstatus);
//		
//		String SQL = "update mig_job_process set status = :status"
//				+ " where process_id = :process_id and status in (:oldstats) ";
//		return this.sqlQueryExeUByMap(SQL, condition);
//	}
	
//	public int sqlQueryUpdateProcessStateByIns(String jobInsId, int status){
//		Map condition = new HashMap(2);
//		condition.put("job_ins_id", jobInsId);
//		condition.put("status", status);
//		
//		String SQL = "update mig_job_process set status = :status"
//				+ " where job_ins_id = :job_ins_id";
//		return this.sqlQueryExeUByMap(SQL, condition);
//	}
	public int sqlQueryUpdateProcessStateByIns_2Init( String jobInsId ){
		Map condition = new HashMap(1);
		condition.put("job_ins_id", jobInsId);
		
		String SQL = "update mig_job_process "
				+ "      set status = "+TaskState.INITIAL+", begin_time=null, end_time=null, "
				+ "          remark = 'Task(all) has been reset!' "
				+ "    where job_ins_id = :job_ins_id";
		return this.sqlQueryExeUByMap(SQL, condition);
	}
	public int sqlQueryUpdateSubJobStateByIns_2Init( String jobInsId,  String jobId, List processidList ){
		Map condition = new HashMap(1);
		condition.put("job_ins_id", jobInsId);
		condition.put("idlist", processidList);
		condition.put("jobId", jobId);
		
		String SQL = "update mig_job_process "
				+ "      set status = "+TaskState.INITIAL+", begin_time=null, end_time=null, "
				+ "          remark = 'Task(subJob) has been reset!' "
				+ "    where job_ins_id = :job_ins_id and ( process_id in ( :idlist ) or node_id = :jobId ) ";
		return this.sqlQueryExeUByMap(SQL, condition);
	}
	public int sqlQueryUpdateProcessStateByIns(String jobInsId, String nodeId, int status){
		Map condition = new HashMap(3);
		condition.put("job_ins_id", jobInsId);
		condition.put("node_id", nodeId);
		condition.put("status", status);
		
		String SQL = "update mig_job_process set status = :status "
				+ " where job_ins_id = :job_ins_id and node_id = :node_id ";
		return this.sqlQueryExeUByMap(SQL, condition);
	}
	public int sqlQueryUpdateProcessStateByIns_2Init(String jobInsId, String nodeId ){
		Map condition = new HashMap(2);
		condition.put("job_ins_id", jobInsId);
		condition.put("node_id", nodeId);
		
		String SQL = "update mig_job_process "
				+ "      set status = "+TaskState.INITIAL+", begin_time=null, end_time=null, "
				+ "          remark= 'Task has been reset' "
				+ "    where job_ins_id = :job_ins_id and node_id = :node_id ";
		return this.sqlQueryExeUByMap(SQL, condition);
	}
	public List<MigJobProcess> findProcessesWhosePreisNullAndIsTop(String jobInsId, String jobId){
		Map condition = new HashMap(2);
		condition.put("job_ins_id", jobInsId);
		condition.put("job_id", jobId);
		
		String HQL = "from MigJobProcess where jobInsId = :job_ins_id "
				+ "    and jobId = :job_id and (prepos is null or prepos='' )";
		return this.listAllByMap(HQL, condition);
	}
	public List<MigJobProcess> findProcessesWhosePreisNull(String jobInsId, String jobId){
		Map condition = new HashMap(2);
		condition.put("jobId", jobId);
		condition.put("job_ins_id", jobInsId);
		
		String HQL = "from MigJobProcess where jobId = :jobId and jobInsId = :job_ins_id and (prepos is null or prepos='' ) ";
		return this.listAllByMap(HQL, condition);
	}

	public List<MigJobProcess> findProcessesWhosePostisNull(String jobInsId, String jobId){
		Map condition = new HashMap(2);
		condition.put("jobId", jobId);
		condition.put("job_ins_id", jobInsId);
		
		String HQL = "from MigJobProcess where jobId = :jobId and jobInsId = :job_ins_id and (postpos is null or postpos='' ) ";
		return this.listAllByMap(HQL, condition);
	}
	public int find2_4recursive1(String jobInsId, String Node){
		Map condition = new HashMap(2);
		condition.put("job_ins_id", jobInsId);
		condition.put("node_id", Node);
		String sql="select count(*) from tool.mig_job_process "
				+ "  WHERE job_ins_id= :job_ins_id AND node_id= :node_id "
				+ "    AND NOT EXISTS (SELECT 1 FROM tool.mig_job_process WHERE job_ins_id= :job_ins_id AND job_id= :node_id AND STATUS = -1 ) "
				+ "    AND EXISTS (SELECT 1 FROM tool.mig_job_process WHERE job_ins_id= :job_ins_id AND node_id= :node_id AND STATUS = -1 )";
//		Object count = this.sqlQueryfindaValueByMap(sql,condition);
//		System.out.println("count:"+count);
//		return Integer.valueOf( count.toString() );
		return Integer.valueOf( this.sqlQueryfindaValueByMap(sql,condition).toString() );
	}
	public MigJobProcess findByJobInsIdAndNode(String jobInsId, String Node){
//		log.debug("jobInsId:"+jobInsId+", Node:"+Node);
		
		Map condition = new HashMap(2);
		condition.put("job_ins_id", jobInsId);
		condition.put("node", Node);
		
		String HQL = "from MigJobProcess where jobInsId = :job_ins_id and nodeId= :node";
		List<MigJobProcess> list = this.listAllByMap(HQL, condition);
		if(list.size()>1){
			throw new BusinessException("Error! need developer deel!");
		}
		return list.size()==0?null:list.get(0);
	}
	
	public List<MigJobProcess> findByJobInsIdAndJobId(String jobInsId, String jobId){
		Map condition = new HashMap(2);
		condition.put("job_ins_id", jobInsId);
		condition.put("jobId", jobId);
		
		String HQL = "from MigJobProcess where jobInsId = :job_ins_id and jobId= :jobId";
		return this.listAllByMap(HQL, condition);
	}
	
	public List<MigJobProcess> findProcessesByStatus(String jobInsId, int status){
		Map condition = new HashMap(2);
		condition.put("job_ins_id", jobInsId);
		condition.put("status", status);
		
		String HQL = "from MigJobProcess where jobInsId = :job_ins_id and status= :status";
		List<MigJobProcess> list = this.listAllByMap(HQL, condition);
		
		return list;
	}
	
//	public List<MigJobProcess> findProcessesByStatus(String jobId, Integer[] status){
//		List<Integer> pl = Arrays.asList(status);
//		Map condition = new HashMap(2);
//		condition.put("jobId", jobId);
//		condition.put("status", pl);
//		
//		String HQL = "from MigJobProcess where jobId = :jobId and status in ( :status )";
//		List<MigJobProcess> list = this.listAllByMap(HQL, condition);
//		
//		return list;
//	}
	public List<MigJobProcess> findChildrenNoRecursive( String parentId )
	{
		log.debug("findChildren......");
		String[] id = parentId.split("\\-");
		if(id.length != 2){
			throw new BusinessException("ID is unlegality!");
		}
		String queryString = "from MigJobProcess m " +
						 	"where m.jobInsId = :jobInsId AND m.jobId = :jobId " +
							"order by m.nodeName ";
		Map map = new HashMap();
		map.put( "jobInsId", id[0] );
		map.put( "jobId", id[1] );
		return this.listAllByMap( queryString, map );	
	}
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigJobProcessDAO#save(person.daizhongde.migration.hibernate.pojo.MigJobProcess)
	 */
	@Override
	public void save(MigJobProcess transientInstance) {
//		log.debug("saving MigJobProcess instance");
		try {
			getHibernateTemplate().save(transientInstance);
//			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigJobProcessDAO#delete(person.daizhongde.migration.hibernate.pojo.MigJobProcess)
	 */
	@Override
	public void delete(MigJobProcess persistentInstance) {
		log.debug("deleting MigJobProcess instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigJobProcessDAO#findById(java.lang.String)
	 */
	@Override
	public MigJobProcess findById(java.lang.String id) {
		log.debug("getting MigJobProcess instance with id: " + id);
		try {
			MigJobProcess instance = (MigJobProcess) getHibernateTemplate()
					.get("person.daizhongde.migration.hibernate.pojo.MigJobProcess",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	public int findStatusById(java.lang.String id) {
//		log.debug("find status by id: " + id);
		try {
			Object status = this.sqlQueryfindaValue(
					"select status from mig_job_process where process_id='"+id+"'", "status");
			
			return Integer.valueOf( status.toString() );
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	
	public List<MigJobProcess> findProcessesByStatus(int status, String jobInsId) {
		Map condition = new HashMap(2);
		condition.put("job_ins_id", jobInsId);
		condition.put("status", status);
		
		String HQL = "from MigJobProcess where jobInsId = :job_ins_id and status= :status ";
		return this.listAllByMap(HQL, condition);
		
		//父节点未执行完成就暂停，父节点状态为正在执行，所以剔除父节点
//		try {
//			Object count = this.sqlQueryfindaValue(
//					"select count(*) from mig_job_process a,MigTaskInfo b "
//				   + "where a.job_id='"+jobId+"' and a.status = " + status 
//				   + " and a.SUBTASK = b.TASK_ID and b.TASK_NODE='F' ");
//			return Integer.valueOf( status.toString() );
//		} catch (RuntimeException re) {
//			log.error("get failed", re);
//			throw re;
//		}
	}
	
//	public int findCountByStatus(java.lang.Integer status, String jobId, String taskId ) {
//		log.debug("find count by status: " + status);
//		try {
//			Object count = this.sqlQueryfindaValue(
//					"select count(*) from mig_job_process "
//				   + "where job_id='"+jobId+"' and task='"+taskId+"' and status = " + status );
//			return Integer.valueOf( status.toString() );
//		} catch (RuntimeException re) {
//			log.error("get failed", re);
//			throw re;
//		}
//	}
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigJobProcessDAO#findByExample(person.daizhongde.migration.hibernate.pojo.MigJobProcess)
	 */
	@Override
	public List findByExample(MigJobProcess instance) {
		log.debug("finding MigJobProcess instance by example");
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
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigJobProcessDAO#findByProperty(java.lang.String, java.lang.Object)
	 */
	@Override
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding MigJobProcess instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from MigJobProcess as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	@Override
	public List findByProperty2(String insId, String curJobId) {
		String sql = "SELECT process_id,node_id,status FROM tool.mig_job_process WHERE job_ins_id= :insId AND job_id= :curJobId";
		Map condition = new HashMap(2);
		condition.put("insId", insId);
		condition.put("curJobId", curJobId);
		
		return this.sqlQuerylistAllByMap(sql, condition);
	}
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigJobProcessDAO#findByJobId(java.lang.Object)
	 */
	@Override
//	public List findByJobId(Object jobInsId) {
//		return findByProperty(migJobIns.jobInsId, jobInsId);
//	}
//
//	/* (non-Javadoc)
//	 * @see person.daizhongde.migration.hibernate.dao.impl.MigJobProcessDAO#findByNode(java.lang.Object)
//	 */
//
//	public List findByTask(List<String> list) {
//		Map condition = new HashMap(1);
//		condition.put("task", list);
//		
//		String HQL = "from MigJobProcess where task in ( :task ) ";
//		return this.listAllByMap(HQL, condition);
//	}

	public List findByNodeId(Object nodeId) {
		return findByProperty(NODE_ID, nodeId);
	}

	public List findByNodeName(Object nodeName) {
		return findByProperty(NODE_NAME, nodeName);
	}

	public List findByControlId(Object controlId) {
		return findByProperty(CONTROL_ID, controlId);
	}

	public List findByComId(Object comId) {
		return findByProperty(COM_ID, comId);
	}

	public List findByStatus(Object status) {
		return findByProperty(STATUS, status);
	}

	public List findByPrepos(Object prepos) {
		return findByProperty(PREPOS, prepos);
	}

	public List findByPostpos(Object postpos) {
		return findByProperty(POSTPOS, postpos);
	}

	public List findByRemark(Object remark) {
		return findByProperty(REMARK, remark);
	}

	public List findByCoords(Object coords) {
		return findByProperty(COORDS, coords);
	}


	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigJobProcessDAO#findAll()
	 */
	@Override
	public List findAll() {
		log.debug("finding all MigJobProcess instances");
		try {
			String queryString = "from MigJobProcess";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigJobProcessDAO#merge(person.daizhongde.migration.hibernate.pojo.MigJobProcess)
	 */
	@Override
	public MigJobProcess merge(MigJobProcess detachedInstance) {
		log.debug("merging MigJobProcess instance");
		try {
			MigJobProcess result = (MigJobProcess) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigJobProcessDAO#attachDirty(person.daizhongde.migration.hibernate.pojo.MigJobProcess)
	 */
	@Override
	public void attachDirty(MigJobProcess instance) {
		log.debug("attaching dirty MigJobProcess instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigJobProcessDAO#attachClean(person.daizhongde.migration.hibernate.pojo.MigJobProcess)
	 */
	@Override
	public void attachClean(MigJobProcess instance) {
		log.debug("attaching clean MigJobProcess instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static MigJobProcessDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (MigJobProcessDAO) ctx.getBean("migJobProcessDAO");
	}
}