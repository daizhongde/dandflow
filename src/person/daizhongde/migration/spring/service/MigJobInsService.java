package person.daizhongde.migration.spring.service;

import java.util.List;

import person.daizhongde.authority.hibernate.pojo.TAuthorityUser;

import person.daizhongde.virtue.spring.BaseService;

/**
 * p1:jobstart update job's prepos为null的processes
 * p2:
 * @author daizd
 *
 */
public interface MigJobInsService extends BaseService{
	
	/**
	 * assemble config tree data. 
	 * <p>
	 * data source:<br>
	 * 表: mig_auditv_config, 表: mig_auditv_configtree
	 * 
	 * @return
	 */
	public abstract Object getData_JEasyUI_TreeGrid_Async( String moduleid, boolean WithRoot );
	
	
	
	public abstract boolean unlockJobIns(String jobInsId,TAuthorityUser user);

	/**
	 * 只有执行完成或暂停状态的实例才能被重置
	 * @param jobInsId
	 * @param user
	 */
	public abstract void modifyJobStatus2init(String jobInsId, String jobId, TAuthorityUser user);
	/**
	 *  重置实例中的子作业，实例状态不变，将子作业及其所有子结点状态全置为初始
	 * <p>
	 * 只有执行完成或暂停状态的实例的子作业才能被重置
	 * @param jobInsId
	 * @param user
	 */
	public abstract void modifySubJobStatus2init(String jobInsId, String jobId, TAuthorityUser user);
	/**
	 *  DELETE FROM tool.mig_job_process WHERE job_ins_id='IS00000124';<br/>
		DELETE FROM tool.`mig_ins_para` WHERE job_ins_id='IS00000124';<br/>
		DELETE FROM tool.mig_com_ins WHERE job_ins_id='IS00000124';<br/>
		DELETE FROM tool.`mig_job_log` WHERE job_ins_id='IS00000124';<br/>
		DELETE FROM tool.mig_job_stat WHERE job_ins_id='IS00000124';<br/>
		DELETE FROM tool.mig_job_ins WHERE job_ins_name='CREATE_01_MID_TABLE-实例1';
	 * @param jobInsId
	 * @param user
	 */
	public abstract void removeInstance(String jobInsId, TAuthorityUser user);
	public abstract void removeInstance(List<String> jobInsId, TAuthorityUser user);
}