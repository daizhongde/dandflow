package person.daizhongde.migration.spring.service;

import person.daizhongde.authority.hibernate.pojo.TAuthorityUser;

import person.daizhongde.virtue.spring.BaseService;

/**
 * p1:jobstart update job's prepos为null的processes
 * p2:
 * @author daizd
 *
 */
public interface MigJobLogService extends BaseService{
	/**
	 * 抓取任务日志
	 * <p>
	 *  任务日志目录
		<br>/usr/migrationWebApp/joblog/{jobname}/{jobinsname}.log
		<br>
		文件内容规则
		<br>{tasked}:XXXXXXXXXXXXXXXXXXXXXXXXXXX
	
	 * @param jobInsId
	 * @param taskId
	 * @return
	 */
	public abstract String fetchLog(String jobId, String jobInsId, String taskId);
}