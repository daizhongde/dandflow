package person.daizhongde.migration.spring.service;

import java.util.List;

import person.daizhongde.authority.hibernate.pojo.TAuthorityUser;

import person.daizhongde.virtue.spring.BaseService;

/**
 * p1:jobstart update job's prepos为null的processes
 * p2:
 * @author daizd
 */
public interface MigJobContentService extends BaseService{

	
	public abstract void moveTasks2AnotherJob( String jobId, List<String> taskIds, TAuthorityUser user );
	/**
	 * 判断子作业是否包含有父作业
	 * @param subJobId
	 * @param parentJobId
	 * @return
	 */
	public abstract boolean whetherContainParent( String subJobId, String parentJobId );
	/**
	 * 修改任务节点坐标
	 * @param coords
	 * @param jobId
	 * @param taskId
	 */
	public abstract void modifyCoords(String coords, String jobId, String nodeId );
	
	/**
	 * 新增任务节点
	 * @param taskName
	 * @param taskRemark
	 * @param coords
	 * @param jobId
	 * @param controlId
	 * @param user
	 * @return
	 */
	public abstract String addTaskNodeRetId( String taskName, String taskRemark, String coords, String jobId, String controlId,
			TAuthorityUser user );
	/**
	 * 新增作业节点
	 * @param jobName
	 * @param jobRemark
	 * @param coords
	 * @param jobId
	 * @param user
	 * @return
	 */
	public abstract String addJobNodeRetId( String jobName, int type, String jobRemark,String coords, String jobId, 
			TAuthorityUser user );
	
	/**
	 * 挂接作业节点
	 * @param jobName
	 * @param jobRemark
	 * @param coords
	 * @param jobId
	 * @param user
	 * @return
	 */
	public abstract void hangJobNode( String jobId, String nodeId, String coords,
			TAuthorityUser user );
	/**
	 * 取消挂接作业节点
	 * @param jobName
	 * @param jobRemark
	 * @param coords
	 * @param jobId
	 * @param user
	 * @return
	 */
	public abstract void unhangJobNode( String jobId, String nodeId,
			TAuthorityUser user );
	/**
	 * link two top level task
	 * 
	 * */
	public abstract void link(String jobId,String fromTask,String toTask);
	
	/**
	 * unlink two top level task
	 * 
	 * */
	public abstract void unLink(String jobId,String fromTask,String toTask);
	/**
	 * this method is necesssary,
	 * <p>
	 * scene :when delete a job in job edit page
	 * @param nodeId
	 */
	public abstract void removeNode(String nodeId, TAuthorityUser user);
	/**
	 * <p>
	 * scene :when delete jobs on job manage main page
	 * @param nodeId
	 */
	public abstract void removeNode(List<String> nodeId, TAuthorityUser user);
	/**
	 * recursive delete when the job with subjob
	 * <p>
	 * when subjob is refered by another job, unlink subjob
	 * @param jobId
	 */
	public abstract void recurRemoveJob(String jobId, TAuthorityUser user);
	
	public void removeTask(String jobId, String nodeId,String comId,
			TAuthorityUser user);
	/**
	 * get job content by jobId.
	 * 
	 * <p>查询指定作业的顶层节点，带组件参数信息
	 * 
	 * */
	public abstract List findByJob(String jobId);
	/**
	 * 查询指定作业的顶层节点，不带组件参数信息
	 * @param jobId
	 * @return
	 */
	public abstract List findByJobId(String jobId);
}