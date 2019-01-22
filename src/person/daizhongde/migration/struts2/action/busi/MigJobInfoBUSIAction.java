package person.daizhongde.migration.struts2.action.busi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import person.daizhongde.virtue.util.json.JsonUtils;
import person.daizhongde.virtue.util.test.Printer;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import person.daizhongde.authority.struts2.action.BaseAction;
import person.daizhongde.migration.hibernate.dto.MigJobParaDto;
import person.daizhongde.migration.spring.service.MigJobInfoService;

/**
 * 作业内容业务action
 * <br>only used by jquery-easy-ui
 * <br>this type Acitons whose name are match "*JEasyUIQUERYAction"
 * <br>are only do a work: query table data
 * @author dzd
 * @date 2015-01-07
 */
public class MigJobInfoBUSIAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String jobInsName;
	private MigJobInfoService dataService;

	/**
	 * 从作业中删除一个只被当前作业引用的作业
	 * @return
	 */
	public String deleteInJob(){
		JSONObject json = StringUtils.isEmpty( super.getJdata() )?null:JSONObject.fromObject( super.getJdata() );
		super.setJsonObject(json);
		
		try{
			dataService.deleteJobInJobbyIdRecursion( json.getJSONObject("condition").getString("id"), 
					json.getJSONObject("condition").getString("curJobId"),
					super.getLoginUser() );
		}catch(Exception e){
			e.printStackTrace();
			Throwable e2 = e;
			while(e2.getCause() != null ){
				e2 = e2.getCause();
			}
			String msg = e2.getLocalizedMessage();
			msg = StringUtils.isEmpty( msg ) ? "unknow error" : msg;
			Map map = new HashMap(2);
			map.put("success", Boolean.FALSE);
			map.put("msg", msg);
			super.setJson( JSONObject.fromObject(map).toString() );
			
			return SUCCESS;
		}
		Map map = new HashMap(2);
		map.put("success", Boolean.TRUE);
		map.put("msg", "Delete Success!");
		super.setJson( JSONObject.fromObject(map).toString() );
		return SUCCESS;
	}
	/**
	 * 批量删除作业
	 * @return
	 */
	public String removeJob(){
		JSONObject json = StringUtils.isEmpty( super.getJdata() )?null:JSONObject.fromObject( super.getJdata() );
		super.setJsonObject(json);
		
		try{
			dataService.deleteJob( JSONArray.toList( JSONArray.fromObject(super.get( "job_id" ))) , super.getLoginUser() );
		}catch(Exception e){
			e.printStackTrace();
			Throwable e2 = e;
			while(e2.getCause() != null ){
				e2 = e2.getCause();
			}
			String msg = e2.getLocalizedMessage();
			msg = StringUtils.isEmpty( msg ) ? "unknow error" : msg;
			Map map = new HashMap(2);
			map.put("success", Boolean.FALSE);
			map.put("msg", msg);
			super.setJson( JSONObject.fromObject(map).toString() );
			
			return SUCCESS;
		}
		Map map = new HashMap(2);
		map.put("success", Boolean.TRUE);
		map.put("msg", "Delete Success!");
		super.setJson( JSONObject.fromObject(map).toString() );
		return SUCCESS;
	}
	
	public String instanceJob(){
		JSONObject json = StringUtils.isEmpty( super.getJdata() ) ? null : JSONObject.fromObject( super.getJdata() );
		super.setJsonObject(json);
		try{
			@SuppressWarnings("unchecked")
			List<MigJobParaDto> list = (List<MigJobParaDto>)JsonUtils.jsonStr2List(
					json.getJSONArray("rows").toString(),
					MigJobParaDto.class
				);
//			System.out.println("in action instanceJob:-------------------------------------");
//			Printer.printJSON(list);
//			System.out.println("in action instanceJob:-------------------------------------");
			dataService.instanceJob( super.get( "jobId" ), super.getInt( "type" ), super.get( "jobInsName" ), super.getInt( "dryrunId" ), list, super.getLoginUser() );
		}catch(Exception e){
			e.printStackTrace();
			
			Throwable e2 = e;
			while(e2.getCause() != null ){
				e2 = e2.getCause();
			}
			super.setJson( "{success: false, msg: \""+e2.getLocalizedMessage()+"\"}" );
			return SUCCESS;
		}
		
//		Map map = new HashMap();
//		map.put("success", true );
//		map.put("msg", "编译成功!");
		super.setJson( "{success: true, msg: \"Instance Success!\"}" );
		return SUCCESS;
	}
	
	public String getJobInsName() {
		return jobInsName;
	}

	public void setJobInsName(String jobInsName) {
		this.jobInsName = jobInsName;
	}

	public void setDataService(MigJobInfoService dataService) {
		this.dataService = dataService;
	}
}