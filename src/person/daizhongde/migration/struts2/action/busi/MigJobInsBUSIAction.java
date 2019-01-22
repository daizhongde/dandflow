package person.daizhongde.migration.struts2.action.busi;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import person.daizhongde.authority.struts2.action.BaseAction;
import person.daizhongde.migration.spring.service.MigJobInsService;

/**
 * 作业内容业务action
 * <br>only used by jquery-easy-ui
 * <br>this type Acitons whose name are match "*JEasyUIQUERYAction"
 * <br>are only do a work: query table data
 * @author dzd
 * @date 2015-01-07
 */
public class MigJobInsBUSIAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String jobInsName;
	private MigJobInsService dataService;

	public String unlock(){
		JSONObject json = StringUtils.isEmpty( super.getJdata() )?null:JSONObject.fromObject( super.getJdata() );
		super.setJsonObject(json);
		try{
			dataService.unlockJobIns( super.get( "job_ins_id" ), super.getLoginUser() );
		}catch(Exception e){
			e.printStackTrace();
			
			Throwable e2 = e;
			while(e2.getCause() != null ){
				e2 = e2.getCause();
			}
			super.setJson( "{success: false, msg: \""+e2.getLocalizedMessage()+"\"}" );
			return SUCCESS;
		}
		super.setJson( "{success: true, msg: \"Instance<"+super.get( "job_ins_id" )+">unlock Success!\"}" );
		return SUCCESS;
	}
	
	public String modifyJobStatus2init(){
		JSONObject json = StringUtils.isEmpty( super.getJdata() )?null:JSONObject.fromObject( super.getJdata() );
		super.setJsonObject(json);
		try{
			dataService.modifyJobStatus2init( super.get( "job_ins_id" ),super.get( "jobId" ), super.getLoginUser() );
		}catch(Exception e){
			e.printStackTrace();
			
			Throwable e2 = e;
			while(e2.getCause() != null ){
				e2 = e2.getCause();
			}
			super.setJson( "{success: false, msg: \""+e2.getLocalizedMessage()+"\"}" );
			return SUCCESS;
		}
		super.setJson( "{success: true, msg: \"Instance<"+super.get( "job_ins_id" )+">inital success!\"}" );
		return SUCCESS;
	}
	public String modifySubJobStatus2init(){
		JSONObject json = StringUtils.isEmpty( super.getJdata() )?null:JSONObject.fromObject( super.getJdata() );
		super.setJsonObject(json);
		try{
			dataService.modifySubJobStatus2init( super.get( "job_ins_id" ),super.get( "jobId" ), super.getLoginUser() );
		}catch(Exception e){
			e.printStackTrace();
			
			Throwable e2 = e;
			while(e2.getCause() != null ){
				e2 = e2.getCause();
			}
			super.setJson( "{success: false, msg: \""+e2.getLocalizedMessage()+"\"}" );
			return SUCCESS;
		}
		super.setJson( "{success: true, msg: \"Instance<"+super.get( "job_ins_id" )+">inital success!\"}" );
		return SUCCESS;
	}
	public String removeInstance(){
		JSONObject json = StringUtils.isEmpty( super.getJdata() )?null:JSONObject.fromObject( super.getJdata() );
		super.setJsonObject(json);
		try{
//			System.out.println("super.get( 'job_ins_id' )"+super.get( "job_ins_id" ));
//			dataService.removeInstance( super.get( "job_ins_id" ), super.getLoginUser() );
			dataService.removeInstance( JSONArray.toList( JSONArray.fromObject(super.get( "job_ins_id" )) ), super.getLoginUser() );
		}catch(Exception e){
			e.printStackTrace();
			
			Throwable e2 = e;
			while(e2.getCause() != null ){
				e2 = e2.getCause();
			}
			super.setJson( "{success: false, msg: \""+e2.getLocalizedMessage()+"\"}" );
			return SUCCESS;
		}
		Map map = new HashMap(2);
		map.put("success", Boolean.TRUE);
		map.put("msg", "Instance<"+super.get( "job_ins_id" )+">Delete Success!");
		super.setJson( JSONObject.fromObject(map).toString() );
//		super.setJson( "{success: true, msg: \"实例<"+super.get( "job_ins_id" )+">删除成功!\"}" );
		return SUCCESS;
	}
	public String getJobInsName() {
		return jobInsName;
	}

	public void setJobInsName(String jobInsName) {
		this.jobInsName = jobInsName;
	}

	public void setDataService(MigJobInsService dataService) {
		this.dataService = dataService;
	}
}