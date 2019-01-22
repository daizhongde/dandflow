package person.daizhongde.migration.spring.service;

import java.util.List;
import java.util.Map;

import person.daizhongde.authority.hibernate.pojo.TAuthorityUser;
import person.daizhongde.migration.hibernate.dto.MigComInfoDto;
import person.daizhongde.migration.hibernate.dto.MigJobParaDto;
import person.daizhongde.migration.hibernate.pojo.MigTaskInfo;

import person.daizhongde.virtue.spring.BaseService;

/**
 * p1:jobstart update job's prepos为null的processes
 * p2:
 * @author daizd
 *
 */
public interface MigTaskInfoService extends BaseService{

    
	/**
	 * 获取一个任务ID
	 * @return
	 */
	public  String getNewTaskId();
	/**
	 * 通过
	 * taskId修改任务名称
	 * com_ID修改控件实例化参数信息
	 * @param comId
	 * @param para
	 * @param user
	 */
	public abstract String modifyWithTaskParamandComInfo( String taskId, String taskName, String taskRemark,
			String comId, List<MigJobParaDto> jobparas, List<MigComInfoDto> cominfos );

	/**
     * find a new task by taskId
     * @param taskId: task unique identifier.
     */
	public abstract MigTaskInfo findTaskById(String taskId);

	public abstract void checkTaskInfo(String jobId, String taskId);
	public abstract void checkTaskIns(String jobId, String jobInsId, String taskId);
}