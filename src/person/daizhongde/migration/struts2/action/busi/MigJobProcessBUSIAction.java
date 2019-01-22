package person.daizhongde.migration.struts2.action.busi;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import person.daizhongde.virtue.util.test.Printer;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import person.daizhongde.authority.struts2.action.BaseAction;
import person.daizhongde.migration.hibernate.pojo.MigJobInfo;
import person.daizhongde.migration.hibernate.pojo.MigJobIns;
import person.daizhongde.migration.spring.service.MigJobInfoService;
import person.daizhongde.migration.spring.service.MigJobInsService;
import person.daizhongde.migration.spring.service.MigJobProcessService;

/**
 * 作业内容业务action
 * <br>only used by jquery-easy-ui
 * <br>this type Acitons whose name are match "*JEasyUIQUERYAction"
 * <br>are only do a work: query table data
 * @author dzd
 * @date 2015-01-07
 */
public class MigJobProcessBUSIAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;
	private String jid;
	private MigJobIns pojo;
	/** postfix  suffix    **/
	private String pagePostfix="";
	private MigJobProcessService dataService;
//	private MigJobInfoService jobInfoService;
	private MigJobInsService jobInsService;
	
	public String refreshState(){
		super.setJson(dataService.findByProperty(id, jid));
		return SUCCESS;
	}
	public String startJob(){
		JSONObject json = StringUtils.isEmpty( super.getJdata() )?null:JSONObject.fromObject( super.getJdata() );
		super.setJsonObject(json);
		try{
			dataService.startJob( super.get( "job_ins_id" ), super.getLoginUser() );
		}catch(Exception e){
			e.printStackTrace();
			Throwable e2 = e;
			while(e2.getCause() != null ){
				e2 = e2.getCause();
			}
			super.setJson( "{success: false, msg: \""+e2.getLocalizedMessage()+"\"}" );
			return SUCCESS;
		}
		super.setJson( "{success: true, msg: 'Start Success!'}" );
		return SUCCESS;
	}
	
	public String pauseJob(){
		JSONObject json = StringUtils.isEmpty( super.getJdata() )?null:JSONObject.fromObject( super.getJdata() );
		super.setJsonObject(json);
		try{
			dataService.pauseJob( super.get( "job_ins_id" ), super.getLoginUser() );
		}catch(Exception e){
			Throwable e2 = e;
			while(e2.getCause() != null ){
				e2 = e2.getCause();
			}
			super.setJson( "{success: false, msg: \""+e2.getLocalizedMessage()+"\"}" );
			return SUCCESS;
		}
		super.setJson( "{success: true, msg: 'Stop Success!'}" );
		return SUCCESS;
	}
	
	public String monitor(){
//		try{

//		System.out.println("action id:"+id+",jid:"+jid);
		this.pojo = (MigJobIns)jobInsService.browsePOJOById( id );
//		Printer.printJSON(this.pojo);
		List l = dataService.monitorJob( id, jid,super.getLoginUser()  );
		super.setJson( JSONArray.fromObject( l ).toString() );
//		}catch(Exception e){
//			e.printStackTrace();
//		}
		this.pagePostfix = "";
		return "mig_browse";
	}
	public String monitor1(){
		this.pojo = (MigJobIns)jobInsService.browsePOJOById( id );
		List l = dataService.monitorJob( id, jid,super.getLoginUser()  );
		super.setJson( JSONArray.fromObject( l ).toString() );
		this.pagePostfix = "1";
		return "mig_browse";
	}
	public String stopTaskSignal(){
		JSONObject json = StringUtils.isEmpty( super.getJdata() )?null:JSONObject.fromObject( super.getJdata() );
		super.setJsonObject(json);
		try{
			dataService.stopTaskSignal( super.get( "job_ins_id" ), super.get( "taskId" ), super.getLoginUser() );
		}catch(Exception e){
			e.printStackTrace();
			Throwable e2 = e;
			while(e2.getCause() != null ){
				e2 = e2.getCause();
			}
			super.setJson( "{success: false, msg: \""+e2.getLocalizedMessage()+"\"}" );
			return SUCCESS;
		}
		super.setJson( "{success: true, msg: 'Task<"+super.get( "taskId" )+">Stoped!'}" );
		return SUCCESS;
	}
	public String modifyTaskStatus2Init(){
		JSONObject json = StringUtils.isEmpty( super.getJdata() )?null:JSONObject.fromObject( super.getJdata() );
		super.setJsonObject(json);
		try{
			dataService.modifyTaskStatus2Init( super.get( "job_ins_id" ),  super.get( "taskId" ), super.getLoginUser() );
		}catch(Exception e){
			e.printStackTrace();
			Throwable e2 = e;
			while(e2.getCause() != null ){
				e2 = e2.getCause();
			}
			super.setJson( "{success: false, msg: \""+e2.getLocalizedMessage()+"\"}" );
			return SUCCESS;
		}
		super.setJson( "{success: true, msg: 'Task<"+super.get( "taskId" )+">has reset'}" );
		return SUCCESS;
	}
	
	public String modifyTaskStatus2Finish(){
		JSONObject json = StringUtils.isEmpty( super.getJdata() )?null:JSONObject.fromObject( super.getJdata() );
		super.setJsonObject(json);
		try{
			dataService.modifyTaskStatus2Finish( super.get( "job_ins_id" ),  super.get( "taskId" ), super.getLoginUser() );
		}catch(Exception e){
			e.printStackTrace();
			Throwable e2 = e;
			while(e2.getCause() != null ){
				e2 = e2.getCause();
			}
			super.setJson( "{success: false, msg: \""+e2.getLocalizedMessage()+"\"}" );
			return SUCCESS;
		}
		super.setJson( "{success: true, msg: 'Task<"+super.get( "taskId" )+">state change to excute success!'}" );
		return SUCCESS;
	}
	
	public String modifyTaskStatus2Pause(){
		JSONObject json = StringUtils.isEmpty( super.getJdata() )?null:JSONObject.fromObject( super.getJdata() );
		super.setJsonObject(json);
		try{
			dataService.modifyTaskStatus2Pause( super.get( "job_ins_id" ), super.get( "taskId" ), super.getLoginUser() );
		}catch(Exception e){
			e.printStackTrace();
			Throwable e2 = e;
			while(e2.getCause() != null ){
				e2 = e2.getCause();
			}
			super.setJson( "{success: false, msg: \""+e2.getLocalizedMessage()+"\"}" );
			return SUCCESS;
		}
		super.setJson( "{success: true, msg: 'Task<"+super.get( "taskId" )+">paused!'}" );
		return SUCCESS;
	}
	
	public String modifyTaskStatus2Skip(){
		JSONObject json = StringUtils.isEmpty( super.getJdata() )?null:JSONObject.fromObject( super.getJdata() );
		super.setJsonObject(json);
		try{
			dataService.skipTask( super.get( "job_ins_id" ), super.get( "taskId" ), super.getLoginUser() );
		}catch(Exception e){
			e.printStackTrace();
			Throwable e2 = e;
			while(e2.getCause() != null ){
				e2 = e2.getCause();
			}
			super.setJson( "{success: false, msg: \""+e2.getLocalizedMessage()+"\"}" );
			return SUCCESS;
		}
		super.setJson( "{success: true, msg: 'Task<"+super.get( "taskId" )+">has set to skip!'}" );
		return SUCCESS;
	}
	public MigJobIns getPojo() {
		return pojo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getJid() {
		return jid;
	}

	public void setJid(String jid) {
		this.jid = jid;
	}

	public void setDataService(MigJobProcessService dataService) {
		this.dataService = dataService;
	}

	public void setJobInsService(MigJobInsService jobInsService) {
		this.jobInsService = jobInsService;
	}

	public String getPagePostfix() {
		return pagePostfix;
	}

	public void setPagePostfix(String pagePostfix) {
		this.pagePostfix = pagePostfix;
	}

//	public void setJobInfoService(MigJobInfoService jobInfoService) {
//		this.jobInfoService = jobInfoService;
//	}
	
}