package person.daizhongde.migration.spring.service;

import java.util.List;
import java.util.Map;

import person.daizhongde.authority.hibernate.pojo.TAuthorityUser;
import person.daizhongde.migration.hibernate.pojo.MigControlTemplate;
import person.daizhongde.migration.hibernate.pojo.MigInsPara;
import person.daizhongde.migration.hibernate.pojo.MigJobProcess;

import person.daizhongde.virtue.spring.BaseService;

/**
 * p1:jobstart update job's prepos为null的processes
 * p2:
 * @author daizd
 *
 */
public interface MigJobProcessService extends BaseService{

	public abstract List findByProperty(String insid, String jobid);
	/**
	 * 启动指定的job,仅用于定时调度
	 * @param jobId
	 * @param user
	 */
	public abstract void startJob(String jobInsId)  throws InterruptedException;
	/**
	 * 启动指定的job
	 * @param jobId
	 * @param user
	 */
	public abstract void startJob(String jobInsId, TAuthorityUser user)  throws InterruptedException;
	
	/**
	 * 暂停作业
	 * @param jobId
	 * @param user
	 */ 
	public abstract void pauseJob(String jobInsId, TAuthorityUser user);
	
	
	public abstract void stopTaskSignal( String jobInsId, String nodeId, TAuthorityUser user);
	/**
	 * 监控作业
	 * @param jobInsId
	 * @param jobId
	 * @param user
	 */
	public abstract List<MigJobProcess> monitorJob(String jobInsId, String jobId, TAuthorityUser user);
		
	/**
	 * 初始化任务
	 * @param jobId
	 * @param taskId
	 * @param user
	 */
	public abstract void modifyTaskStatus2Init(String jobInsId, String taskId, TAuthorityUser user);
	/**
	 * 初始化任务
	 * @param jobId
	 * @param taskId
	 * @param user
	 */
	public abstract void modifyTaskStatus2Finish(String jobId, String taskId, TAuthorityUser user);
	/**
	 * 初始化任务
	 * @param jobId
	 * @param taskId
	 * @param user
	 */
	public abstract void modifyTaskStatus2Pause(String jobId, String taskId, TAuthorityUser user);
	/**
	 * 初始化任务
	 * @param jobId
	 * @param taskId
	 * @param user
	 */
	public abstract void modifyTaskStatus2Skip(String jobId, String taskId, TAuthorityUser user);
//	/**
//	 * 暂停C端任务
//	 * @param jobId
//	 * @param taskId
//	 * @param user
//	 */
//	public abstract void pauseTask(String jobId, String taskId, TAuthorityUser user);
	
	/**
	 * 初始化任务
	 * @param jobId
	 * @param taskId
	 * @param user
	 */
	public abstract void skipTask(String jobId, String taskId, TAuthorityUser user);
	
//	public abstract Map<Integer, String> getIdNameMap(List<MigControlTemplate> templates);
	
}