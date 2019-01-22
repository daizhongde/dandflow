package person.daizhongde.migration.spring.service;

import java.util.List;
import java.util.Map;

import person.daizhongde.authority.hibernate.pojo.TAuthorityUser;
import person.daizhongde.migration.hibernate.dto.MigControlTemplateDto;
import person.daizhongde.migration.hibernate.pojo.MigComInfo;
import person.daizhongde.migration.hibernate.pojo.MigControlTemplate;

import person.daizhongde.virtue.spring.BaseService;

/**
 * p1:jobstart update job's prepos为null的processes
 * p2:
 * @author daizd
 *
 */
public interface MigComInfoService extends BaseService{
	
	public abstract List<MigControlTemplateDto> queryComInfos(String controlId, String comId );
	/**
	 * 通过com_ID修改控件实例化参数信息
	 * @param comId
	 * @param para
	 * @param user
	 */
	public abstract void modifyPara( String comId, Map<String,String> para );

	/**
	 * create a new com instance
	 * @param comInsList:list of all control para instance
	 */
	public abstract void newCom(String taskId, List<MigComInfo> comInfoList);
	/**
	 * update  com instance by its id
	 * @param comId: com id to be update
	 * @param paraIds: list of com para ids
	 * @param values: para value that Consistent to paraIds,same indexes identified its relation
	 */
	public abstract void updateComById(String comId,Map<Integer,String> paras);
	/**
	 * delete  com instance by com id
	 * @param comId: com id to be delete
	 */
	public abstract void deleteComInfoById(String comId);
	/**
	 * query  com instance by com id
	 * @param comId: com id to be query
	 * @return all com instance data in list
	 */
	public abstract List<MigComInfo> getComInfoByComId(String comId);
	
	public abstract void instanceComInfo(String jobInsId, String ComId);
	public abstract void instanceComInfo(String jobInsId, String comId,  List<MigComInfo> rows);
	
	public  abstract String getNewComId();
}