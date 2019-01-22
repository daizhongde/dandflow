package person.daizhongde.migration.struts2.action.busi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import person.daizhongde.virtue.util.test.Printer;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import person.daizhongde.authority.struts2.action.BaseAction;
import person.daizhongde.migration.spring.service.MigJobContentService;

/**
 * 作业内容业务action
 * <br>only used by jquery-easy-ui
 * <br>this type Acitons whose name are match "*JEasyUIQUERYAction"
 * <br>are only do a work: query table data
 * @author dzd
 * @date 2015-01-07
 */
public class MigJobContentBUSIAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private MigJobContentService dataService;

	public String findNodesByJob(){
		JSONObject json = StringUtils.isEmpty( super.getJdata() )?null:JSONObject.fromObject( super.getJdata() );
		super.setJsonObject(json);
		
		List l = dataService.findByJobId( super.get( "jobId" ) );
		super.setJson( JSONArray.fromObject( l ).toString()  );
		
		return SUCCESS;
	}
	
	public String link(){
		JSONObject json = StringUtils.isEmpty( super.getJdata() )?null:JSONObject.fromObject( super.getJdata() );
		super.setJsonObject(json);
		
		dataService.link( super.get( "jobId" ),
				super.get( "fromTask" ),
				super.get( "toTask" ) );
		
		Map map = new HashMap();
		map.put("success", true );
		map.put("msg", "连接成功!");
		super.setJson( map );
		return SUCCESS;
	}
	
	public String unLink(){
		JSONObject json = StringUtils.isEmpty( super.getJdata() )?null:JSONObject.fromObject( super.getJdata() );
		super.setJsonObject(json);
		
		dataService.unLink( super.get( "jobId" ),
				super.get( "fromTask" ),
				super.get( "toTask" ) );
		
		Map map = new HashMap();
		map.put("success", true );
		map.put("msg", "删除连接成功!");
		super.setJson( map );
		return SUCCESS;
	}
	/**
	 * @deprecated
	 * 目前没有用，这也是复制一个空壳
	 * @return
	 */
	public String copyJob(){
		JSONObject json = StringUtils.isEmpty( super.getJdata() )?null:JSONObject.fromObject( super.getJdata() );
		super.setJsonObject(json);
		
		String id = "";
		try{
			id = dataService.addJobNodeRetId(
				super.get("jobName"),
				super.getInt("type"),
				super.get("jobRemark"),
				super.get("coords"),
				super.get("jobId"),
				super.getLoginUser()
				);
		}catch(Exception e){
			e.printStackTrace();
			
			Throwable e2 = e;
			while(e2.getCause() != null ){
				e2 = e2.getCause();
			}
			Map map = new HashMap();
			map.put("success", Boolean.FALSE );
			map.put("msg", e2.getLocalizedMessage());
			map.put("id", id);
			super.setJson( map );
			return SUCCESS;
		}
		Map map = new HashMap();
		map.put("success", true );
		map.put("msg", "复制成功!");
		map.put("id", id);
		super.setJson( map );
		return SUCCESS;
	}
	/**
	 * 挂接
	 * @return
	 */
	public String hangJob(){
		JSONObject json = StringUtils.isEmpty( super.getJdata() )?null:JSONObject.fromObject( super.getJdata() );
		super.setJsonObject(json);
		
		String id = super.get("jobId");
		String parentJobId = super.get("parentJobId");
		if( id.equalsIgnoreCase( parentJobId ) ){
			Map map = new HashMap();
			map.put("success", Boolean.FALSE );
			map.put("msg", "作业自身，不能挂接！");
			map.put("id", id);
			super.setJson( map );
			return SUCCESS;
		}
		boolean bool = dataService.whetherContainParent( id, parentJobId );
		if(bool){
			Map map = new HashMap();
			map.put("success", Boolean.FALSE );
			map.put("msg", "该作业中包含作业自身，不能挂接！");
			map.put("id", id);
			super.setJson( map );
			return SUCCESS;
		}
		try{
			dataService.hangJobNode(
				super.get("parentJobId"),	
				super.get("jobId"),
				super.get("coords"),
				super.getLoginUser()
				);
		}catch(Exception e){
			e.printStackTrace();
			
			Throwable e2 = e;
			while(e2.getCause() != null ){
				e2 = e2.getCause();
			}
			Map map = new HashMap();
			map.put("success", Boolean.FALSE );
			map.put("msg", e2.getLocalizedMessage());
			map.put("id", id);
			super.setJson( map );
			return SUCCESS;
		}
		Map map = new HashMap();
		map.put("success", true );
		map.put("msg", "挂接成功!");
		map.put("id", id);
		super.setJson( map );
		return SUCCESS;
	}
	/**
	 * 取消挂接作业
	 * @return
	 */
	public String unhangJob(){
		JSONObject json = StringUtils.isEmpty( super.getJdata() )?null:JSONObject.fromObject( super.getJdata() );
		super.setJsonObject(json);

		try{
			dataService.unhangJobNode(
				super.get("parentJobId"),
				super.get("jobId"),
				super.getLoginUser()
				);
		}catch(Exception e){
			e.printStackTrace();
			
			Throwable e2 = e;
			while(e2.getCause() != null ){
				e2 = e2.getCause();
			}
			Map map = new HashMap();
			map.put("success", Boolean.FALSE );
			map.put("msg", e2.getLocalizedMessage());
			super.setJson( map );
			return SUCCESS;
		}
		Map map = new HashMap();
		map.put("success", true );
		map.put("msg", "取消挂接成功!");
		super.setJson( map );
		return SUCCESS;
	}
	
	/**
	 * 删除任务
	 * @return
	 */
	public String removeTask(){
		JSONObject json = StringUtils.isEmpty( super.getJdata() )?null:JSONObject.fromObject( super.getJdata() );
		super.setJsonObject(json);

		try{
			dataService.removeTask(
				super.get("jobId"),
				super.get("taskId"),
				super.get("comId"),
				super.getLoginUser()
				);
		}catch(Exception e){
			e.printStackTrace();
			
			Throwable e2 = e;
			while(e2.getCause() != null ){
				e2 = e2.getCause();
			}
			Map map = new HashMap();
			map.put("success", Boolean.FALSE );
			map.put("msg", e2.getLocalizedMessage());
			super.setJson( map );
			return SUCCESS;
		}
		Map map = new HashMap();
		map.put("success", true );
		map.put("msg", "取消挂接成功!");
		super.setJson( map );
		return SUCCESS;
	}
	public String moveTasks(){
		JSONObject json = StringUtils.isEmpty( super.getJdata() )?null:JSONObject.fromObject( super.getJdata() );
		super.setJsonObject(json);

		try{
//			super.get("taskIds"),
//			System.out.println("taskIds:"+ super.get("taskIds") );
			List<String> taskIds = JSONArray.fromObject( super.get("taskIds") );
			Printer.printJSON(taskIds );
			dataService.moveTasks2AnotherJob(
				super.get("jobId"),	
				taskIds,
				super.getLoginUser()
				);
		}catch(Exception e){
			e.printStackTrace();
			
			Throwable e2 = e;
			while(e2.getCause() != null ){
				e2 = e2.getCause();
			}
			Map map = new HashMap();
			map.put("success", Boolean.FALSE );
			map.put("msg", e2.getLocalizedMessage());
			super.setJson( map );
			return SUCCESS;
		}
		Map map = new HashMap();
		map.put("success", true );
		map.put("msg", "更新挂接成功!");//现在默认选择的任务都是包含在某一个作业中的
		super.setJson( map );
		return SUCCESS;
	}
	public void setDataService(MigJobContentService dataService) {
		this.dataService = dataService;
	}
}