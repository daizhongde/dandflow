package person.daizhongde.migration.spring.service.restclient;

import java.util.List;

import net.sf.json.JSONObject;

import person.daizhongde.migration.hibernate.dto.AIDMResp;
import person.daizhongde.migration.hibernate.pojo.MigComIns;

/**
 * p1:jobstart update job's prepos为null的processes
 * p2:
 * @author daizd
 *<p>
 *  
 */
public interface MigRestClientService {
	/**
	 * 方法分发器
	 * @param jobId
	 * @param taskId
	 */
	public abstract AIDMResp invoke( List<MigComIns> result,String jobId, String jobInsId, Integer dryrunid, String nodeId, String isCheck, String controlId );
	
	public abstract AIDMResp invokeSingle(  String jobId, String jobInsId, Integer dryrunid, String nodeId, int singal );
	
//	public JSONObject invoke( String jobId, String taskId );
}