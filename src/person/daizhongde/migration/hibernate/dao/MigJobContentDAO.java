package person.daizhongde.migration.hibernate.dao;

import java.util.List;

import person.daizhongde.virtue.dao.SpringHibernateDao;

import person.daizhongde.migration.hibernate.pojo.MigJobContent;
import person.daizhongde.migration.hibernate.pojo.MigJobInfo;
import person.daizhongde.migration.hibernate.pojo.MigTaskInfo;

public interface MigJobContentDAO extends SpringHibernateDao{

	public abstract int moveTasks2AnotherJob( String jobId, List<String> taskIds);

	/** 查询直接子任务   **/
	public abstract List<MigTaskInfo> findDirectSubtask( String jobId );
	/** 查询直接子作业   **/
	public abstract List<MigJobInfo> findDirectSubJob( String jobId );
	/** 查询直接子作业ID集合   **/
	public abstract List<String> findDirectSubJobId( String jobId );
	/**
	 * 更新任务节点坐标
	 * @param coords
	 * @param jobId
	 * @param taskId
	 */
	public abstract void updateCoords(String coords, String jobId, String nodeId );
	
	/**
	 * modify job content status
	 * */
	public abstract void modifyStatus(String jobId,String nodeId,String status);
	
	/**
	 * link  2 top level task
	 * */
	public abstract void link(String jobId,String fromNode,String toNode);
	
	/**
	 * unlink  2 top level task
	 * */
	public abstract void unlink(String jobId,String fromNode,String toNode);
	

	public abstract List<MigJobContent> findNodesByJob(String jobId);
	public abstract List<MigJobContent> findNodesByJob_Recursive(String jobId);
		
	public abstract MigJobContent findByNodeId(String jobId,String nodeId);
	/**
	 * 查询非叶子（作业）
	 * @param jobId
	 * @param nodeId
	 * @return
	 */
	public abstract MigJobContent findByNodeId1(String jobId,String nodeId);
	/**
	 * 查询叶子（任务）
	 * @param jobId
	 * @param nodeId
	 * @return
	 */
	public abstract MigJobContent findByNodeId2(String jobId,String nodeId);
	
	public  List<MigJobContent> findByNodeId(String nodeId);
	/*用于查询引用它的作业，用于删除时的校验   **/
	public  List<MigJobContent> findAllJobNodeByNodeId(String nodeId);
	/*用于查询引用它的作业，用于删除时的校验   **/
	public  List<MigJobContent> findAllJobNodeByNodeId(List<String> nodeId);
	
	public  List<MigJobContent> findByPartialPrePos(String PartialPrePos);
	public  List<MigJobContent> findByPartialPostPos(String PartialPostPos);
	/**
	 * delete jobcontent by jodid and nodeid
	 * @param jobId
	 * @param nodeId
	 */
	public abstract void deleteByNodeid(String jobId,String nodeId);
	/**
	 * delete jobcontent by nodeid
	 * @param nodeId
	 */
	public  abstract void deleteByNodeid(String nodeId);
	/**
	 * delete job content by job id
	 * @param nodeId
	 */
	public  abstract void deleteSubsById(String nodeId);
	public abstract void save(MigJobContent transientInstance);

	public abstract void delete(MigJobContent persistentInstance);

	public abstract MigJobContent findById(
			person.daizhongde.migration.hibernate.pojo.MigJobContentId id);

	public abstract List findByExample(MigJobContent instance);

	public abstract List findByProperty(String propertyName, Object value);

	public abstract List findAll();

	public abstract MigJobContent merge(MigJobContent detachedInstance);

	public abstract void attachDirty(MigJobContent instance);

	public abstract void attachClean(MigJobContent instance);
	

}