package person.daizhongde.migration.spring.service;

import java.util.List;
import java.util.Map;

import person.daizhongde.authority.hibernate.pojo.TAuthorityUser;
import person.daizhongde.migration.hibernate.dto.MigJobParaDto;
import person.daizhongde.migration.hibernate.pojo.MigJobPara;

import person.daizhongde.virtue.spring.BaseService;

/**
 * p1:jobstart update job's prepos为null的processes
 * p2:
 * @author daizd
 *
 */
public interface MigJobParaService extends BaseService{
//	/**
//	 * 参数替换
//	 * @param task
//	 * @param para
//	 * @return
//	 */
//	public abstract Object substitute(String task, String value);
	
	public abstract void newJobPara(MigJobPara mjp);
	public abstract void updateJobPara(String taskId,Map<String,String> paras);
	public abstract void deleteJobPara(String taskId,String para);
	public abstract List<MigJobPara> findAllParaByNodeId(String nodeId);
	/** 实例化叶子任务的变量 **/
	public abstract void instanceJobPara(String jobInsId, String nodeId );
	/** 实例化作业级的变量 **/
	public abstract void instanceJobPara(String jobInsId, String nodeId, List<MigJobParaDto> jobparamlist);
}