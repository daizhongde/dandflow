package person.daizhongde.migration.spring.service;

import java.util.List;

import person.daizhongde.authority.hibernate.pojo.TAuthorityUser;
import person.daizhongde.migration.hibernate.dto.MigJobNodeDto;
import person.daizhongde.migration.hibernate.dto.MigJobParaDto;
import person.daizhongde.migration.hibernate.pojo.MigJobInfo;
import person.daizhongde.migration.hibernate.pojo.MigJobPara;

import person.daizhongde.virtue.spring.BaseService;

/**
 * p1:jobstart update job's prepos为null的processes p2:
 * 
 * @author daizd
 *
 */
public interface MigJobInfoService extends BaseService {

	/**
	 * 作业一次性保存
	 * @param nodes
	 * @param job
	 * @return 当前作业ID
	 */
	public abstract String saveJobInAll( List<MigJobNodeDto> nodes,  MigJobNodeDto job );
	
	/** U  **/
	public abstract void modifyWithJobParam( String jdata, List<MigJobParaDto> jobparas );
	
	/**
	 * get a unique job id
	 * 
	 * */
	public abstract String getNewJobId();
	
	/**
	 * get a new unique process id
	 * 
	 * */
	public abstract String getNewProcessId();
	
	public String getNewJobInsId();
	
	public abstract String addJobRetId( String jobName, int type, String remark, TAuthorityUser user );
	/**
	 * create a empty job,unnamed
	 * 
	 * */
//	public abstract String newJob(TAuthorityUser user);
	/**
	 * update job name
	 * 
	 * */
	public abstract void updateJobNameById(String jobId, String jobName,TAuthorityUser user);
	
	/**
	 * update job remark
	 * 
	 * */
	public abstract void updateJobRemarkById(String jobId, String remark,TAuthorityUser user);
	
	/**
	 * delete sujob in job
	 * <p>
	 * subjob only refer by this job
	 * 
	 */
	public abstract void deleteJobInJobbyIdRecursion(String subjobId, String jobId, TAuthorityUser user);
	
	
	public abstract void deleteJob(List<String> jobId,TAuthorityUser user);
	
	/**
	 * delete task by its id
	 * 
	 * */
//	public abstract void deleteTaskById(String jobId, String taskId,TAuthorityUser user);
	
	/**
	 * get all jobs
	 * 
	 * */
	public abstract List<MigJobInfo> getAllJobs(TAuthorityUser user);
	
	/**
	 * lock one job
	 * 
	 * */
//	public abstract boolean tryLockJob(String jobId,TAuthorityUser user);
	
	/**
	 * unlock one job
	 * 
	 * */
//	public abstract boolean unlockJob(String jobId,TAuthorityUser user);
	/**
	 * get job by jobId
	 * 
	 * */
	public abstract MigJobInfo findById(String jobId);
	
	/**
	 * compile job
	 * 
	 * */
	public abstract boolean instanceJob(String jobId, int type, String jobInsName,  Integer dryrunId, List<MigJobParaDto> jobparamlist, TAuthorityUser user);
	
	/**
	 * clear job Processes 
	 * 
	 * */
	public abstract void checkJobInfo(String jobId);
	
}