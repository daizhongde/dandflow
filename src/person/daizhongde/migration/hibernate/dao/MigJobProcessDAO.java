package person.daizhongde.migration.hibernate.dao;

import java.util.List;

import person.daizhongde.virtue.dao.SpringHibernateDao;

import person.daizhongde.migration.hibernate.pojo.MigJobProcess;

public interface MigJobProcessDAO extends SpringHibernateDao{

	// property constants
	public static final String JOB_ID = "jobId";
	public static final String NODE_ID = "nodeId";
	public static final String NODE_NAME = "nodeName";
	public static final String CONTROL_ID = "controlId";
	public static final String COM_ID = "comId";
	public static final String STATUS = "status";
	public static final String PREPOS = "prepos";
	public static final String POSTPOS = "postpos";
	public static final String REMARK = "remark";
	public static final String COORDS = "coords";
	
	/**
	 * 用于递归更新上级为正在运行（1）
	 * <p>注：<br>
	 *     不支持同一实例中有两个相同的node_id<br>因为process表没有上级process_id列<br>
	 *    recursive 后面的1代表正在执行状态
	 * @param jobInsId
	 * @param node_id
	 */
	public abstract int sqlQueryUpdateProcessState_4recursive1(String jobInsId, String node_id);
	
	/**
	 * 删除过程数据
	 * @param processId
	 * @param state
	 */
	public abstract void deleteByJobInsId(String jobInsId);
	public abstract void deleteByJobInsId(List<String> jobInsId);
	
//	/**
//	 * 更新过程状态
//	 * @param processId
//	 * @param state
//	 */
//	public abstract void updateProcessState(String processId, int status);
//	/**
//	 * 更新过程状态，同时更新锁
//	 * @param processId
//	 * @param status
//	 * @param lockStatus
//	 */
//	public abstract void updateProcessState(String processId, int status, String lockStatus);
//	/**
//	 * 更新过程状态，同时更新锁,带同步校验
//	 * @param processId
//	 * @param status
//	 * @param lockStatus
//	 */
//	public abstract int updateProcessState(String processId, int status, String lockStatus,
//			int oldstats, String oldlockStatus);
	/**
	 * 更新过程状态，同时更新锁,带同步校验
	 * @param processId
	 * @param status
	 * @param lockStatus
	 */
	public abstract int sqlQueryUpdateProcessState(String processId, int status, int oldstatus);
	/** 将初始状态更新为运行态 **/
	public abstract int sqlQueryUpdateProcessState_init2Running(String processId);
	/** 将初始状态更新为运行态（目前针对作业包使用）, 更新start_time**/
	public abstract int sqlQueryUpdateProcessState_2Running(String processId);
	/** 将初始状态更新为运行态（目前针对作业包使用）,不更新start_time **/
	public abstract int sqlQueryUpdateProcessState_2Running2(String processId);
	/** 将运行态更新为完成态 **/
	public abstract int sqlQueryUpdateProcessState_Running2Finish(String processId, String remark );
	/** 将运行态更新为完成态（目前针对作业包使用） **/
	public abstract int sqlQueryUpdateProcessState_2Finish(String processId );
	
	/** 将完成态或暂停、跳过、出错态的任务更新为初始态(目前没有被使用到，为以后一个实例支持多个相同的任务而预留) **/
	public abstract int sqlQueryUpdateProcessState_2Init(String processId);
	
	public abstract int sqlQueryUpdateProcessStateandRemark(String processId, String remark, 
			int status, int oldstatus);	

//	public abstract int sqlQueryUpdateProcessState(String processId, int status, List<Integer> oldstatus);
	
//	/**
//	 * 更新过程状态
//	 * @param processId
//	 * @param status
//	 * @param lockStatus
//	 */
//	public abstract int sqlQueryUpdateProcessStateByIns(String jobInsId, int status);
	/**
	 * 把实例的所有process置为初始
	 * <p>
	 * 这里没有判断各任务的状态，只限制了完成态或暂停态的实例
	 * @param jobInsId
	 * @param status
	 * @param remark
	 * @return
	 */
	public abstract int sqlQueryUpdateProcessStateByIns_2Init(String jobInsId);
	public abstract int sqlQueryUpdateSubJobStateByIns_2Init(String jobInsId,  String jobId, List processidList);
	/**
	 * 更新过程状态
	 * @param jobId
	 * @param taskId
	 * @param status
	 */
	public abstract int sqlQueryUpdateProcessStateByIns(String jobInsId, String nodeId, int status);
	/**
	 * 将完成态或暂停、跳过、出错态的任务更新为初始态 
	 * @param jobId
	 * @param taskId
	 * @param status
	 */
	public abstract int sqlQueryUpdateProcessStateByIns_2Init(String jobInsId, String nodeId);
	/**
	 * find processes whose prepos is null
	 * @param jobId
	 * @return
	 */
	public abstract List<MigJobProcess> findProcessesWhosePreisNullAndIsTop(String jobInsId, String jobId);
	
	/**
	 * find processes whose prepos is null
	 * @param jobId
	 * @param taskId
	 * @return
	 */
	public abstract List<MigJobProcess> findProcessesWhosePreisNull(String jobInsId, String nodeId);
	/**
	 * find processes whose postpos is null
	 * 查询任务的某任务
	 * @param jobId
	 * @param taskId
	 * @return
	 */
	public abstract List<MigJobProcess> findProcessesWhosePostisNull(String jobInsId, String nodeId);
	
	/**
	 * 
	 * @param jobInsId
	 * @param Node
	 * @return
	 */
	public abstract int find2_4recursive1(String jobInsId, String Node);
	/**
	 * 通过jobInsId和subtask查询process<p>
	 * jobInsId和subtask是唯一索引
	 * @param jobId
	 * @param subtask
	 * @return
	 */
	public abstract MigJobProcess findByJobInsIdAndNode(String jobInsId, String Node);

	/**
	 * 通过jobId和status查询process<p>
	 * @param jobId
	 * @param status
	 * @return
	 */
	public abstract  List<MigJobProcess> findProcessesByStatus(String jobInsId, int status);

	/**
	 * 通过jobId和status查询process<p>
	 * @param jobId
	 * @param status
	 * @return
	 */
//	public abstract  List<MigJobProcess> findProcessesByStatus(String jobId, Integer[] status);
	public abstract List<MigJobProcess> findChildrenNoRecursive( String parentId );
	
	public abstract void save(MigJobProcess transientInstance);

	public abstract void delete(MigJobProcess persistentInstance);

	public abstract MigJobProcess findById(java.lang.String id);

	public abstract int findStatusById(java.lang.String id);
	
	public abstract List<MigJobProcess> findByJobInsIdAndJobId(String jobInsId, String jobId);
	
	public abstract List<MigJobProcess> findProcessesByStatus(int status, String jobId);
	
//	public abstract int findCountByStatus(java.lang.Integer status, String jobId, String taskId);
	
	public abstract List findByExample(MigJobProcess instance);

	public abstract List findByProperty(String propertyName, Object value);
	public abstract List findByProperty2(String jobInsId, String curjobId);

//	public abstract List findByJobId(Object jobId);
	
	public abstract List findByNodeId(Object nodeId);

	public abstract List findByNodeName(Object nodeName);

	public abstract List findByControlId(Object controlId);

	public abstract List findByComId(Object comId);

	public abstract List findByStatus(Object status);

	public abstract List findByPrepos(Object prepos);

	public abstract List findByPostpos(Object postpos);

	public abstract List findByRemark(Object remark);

	public abstract List findByCoords(Object coords);

	public abstract List findAll();

	public abstract MigJobProcess merge(MigJobProcess detachedInstance);

	public abstract void attachDirty(MigJobProcess instance);

	public abstract void attachClean(MigJobProcess instance);

}