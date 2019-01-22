package person.daizhongde.migration.struts2.action.curd;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;

import person.daizhongde.authority.struts2.action.BaseAction;
import person.daizhongde.migration.hibernate.pojo.MigJobContent;
import person.daizhongde.migration.spring.service.MigJobContentService;

/**
 * 控件信息CURD
 * <br>this type Actions whose name are match "*JsonCURDAction"
 * <br>only do three work:
 * <br>        add, modify, delete a record, current also do read 
 * @author dzd
 * @date 2015-01-07
 */
public class MigJobContentCURDAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** jquery-easy-ui refreshpage params 
	 * $('#win').window("refresh", "../tAM_add.html");
	 * **/
	private String _;
	
	private Boolean success;// 只有private的变量(并且定义get方法)json插件才能返回
	private String msg;
	private String id;
	
	protected Map map = new HashMap();
	protected Object[] arr;
	protected MigJobContent pojo;
	protected MigJobContentService dataService;

//	public void validate() {
//		addFieldError("jdata", getText("jdata.required"));
//		addFieldError("jdata", "you must input jdata!");
//		System.out.println(result);
//	}
	/**
	 * 新增级别初始化
	 * @return
	 * @throws Exception
	 */
	public String initAdd() throws Exception {
		this.setTitle("发布级别");
		this.setInfo("请填写级别内容。标有 * 号的为必填项。");
		return "add";
	}
	/**
	 * 新增
	 * @return
	 */
	public String addWithId() {
		int i = 0;//insert row count
		try{
			//@return The number of entities updated or deleted or insert.
			i = dataService.addWithId( super.getJdata() );
		}catch(Exception e){
			Throwable e2 = e;
			while(e2.getCause() != null ){
				e2 = e2.getCause();
			}
			this.success = Boolean.FALSE;
			this.msg = e2.getLocalizedMessage();
			return SUCCESS;
		}
		this.success = Boolean.TRUE;
		this.msg = "添加成功！";
		return SUCCESS;
	}
	/**
	 * 新增任务节点并返回任务ID
	 * @return
	 */
	public String addTaskNodeRetId() {
		JSONObject json = StringUtils.isEmpty( super.getJdata() )?null:JSONObject.fromObject( super.getJdata() );
		super.setJsonObject(json);
		try{
			this.id = dataService.addTaskNodeRetId(
					super.get("taskName"),
					super.get("taskRemark"),
					super.get("coords"),
					super.get("jobId"),
					super.get("controlId"),
					super.getLoginUser()
					);
		}catch(Exception e){
			e.printStackTrace();
			
			Throwable e2 = e;
			while(e2.getCause() != null ){
				e2 = e2.getCause();
			}
			this.success = Boolean.FALSE;
			this.msg = e2.getLocalizedMessage();
			return SUCCESS;
		}
		this.success = Boolean.TRUE;
		this.msg = "添加成功！";
		return SUCCESS;
	}
	/**
	 * 新增作业节点并返回作业ID
	 * @return
	 */
	public String addJobNodeRetId() {
		JSONObject json = StringUtils.isEmpty( super.getJdata() )?null:JSONObject.fromObject( super.getJdata() );
		super.setJsonObject(json);
		try{
			this.id = dataService.addJobNodeRetId(
					super.get("jobName"),
					super.getInt("type"),
					super.get("jobRemark"),
					super.get("coords"),
					super.get("jobId"),
					super.getLoginUser()
					);
			//复制作业下面的节点，待完善。。。。。。
		}catch(Exception e){
			e.printStackTrace();
			
			Throwable e2 = e;
			while(e2.getCause() != null ){
				e2 = e2.getCause();
			}
			this.success = Boolean.FALSE;
//			CharacterConvert.testCharSet(e2.getLocalizedMessage());
			this.msg = e2.getLocalizedMessage();
			return SUCCESS;
		}
		this.success = Boolean.TRUE;
		this.msg = "添加成功！";
		return SUCCESS;
	}
	public String modifyCoords(){
		JSONObject json = StringUtils.isEmpty( super.getJdata() )?null:JSONObject.fromObject( super.getJdata() );
		super.setJsonObject(json);
		
		try{
			dataService.modifyCoords(
					super.get( "coords" ),
					super.get( "jobId" ),
					super.get( "nodeId" )
			);
		
		}catch(Exception e){
//			e.printStackTrace();
			Throwable e2 = e;
			while(e2.getCause() != null ){
				e2 = e2.getCause();
			}
			this.success = Boolean.FALSE;
			this.msg = e2.getLocalizedMessage();
			return SUCCESS;
		}
//		this.id = super.get( "nodeId" );
		this.success = Boolean.TRUE;
		this.msg = "更新坐标成功！";
		return SUCCESS;
	}
	
	public String modify(){
		int i = 0;//update row count
		try{
			//@return The number of entities updated or deleted or insert.
			i = dataService.modify( super.getJdata() );
		}catch(Exception e){
			e.printStackTrace();
			Throwable e2 = e;
			while(e2.getCause() != null ){
				e2 = e2.getCause();
			}
			this.success = Boolean.FALSE;
			this.msg = e2.getLocalizedMessage();
			return SUCCESS;
		}
		this.success = Boolean.TRUE;
		this.msg = "更新成功！";
		return SUCCESS;
	}
//	/**
//	 * 删除节点
//	 * @return
//	 */
//	public String removeNode(){
//		JSONObject json = StringUtils.isEmpty( super.getJdata() )?null:JSONObject.fromObject( super.getJdata() );
//		super.setJsonObject(json);
//		
//		try{
//			//@return The number of entities updated or deleted or insert.
//			dataService.removeNode( super.get("nodeId") );
//		}catch(Exception e){
//			e.printStackTrace();
//			Throwable e2 = e;
//			while(e2.getCause() != null ){
//				e2 = e2.getCause();
//			}
//			this.success = Boolean.FALSE;
//			this.msg = e2.getLocalizedMessage();
//			return SUCCESS;
//		}
//		this.success = Boolean.TRUE;
//		this.msg = "删除成功！";
//		return SUCCESS;
//	}
	/**
	 * 删除级别
	 * @return
	 */
	public String delete(){
		int i = 0;//delete count
		try{
			//@return The number of entities updated or deleted or insert.
			i = dataService.delete( super.getJdata() );
		}catch(Exception e){
			e.printStackTrace();
			Throwable e2 = e;
			while(e2.getCause() != null ){
				e2 = e2.getCause();
			}
			this.success = Boolean.FALSE;
			this.msg = e2.getLocalizedMessage();
			return SUCCESS;
		}
		this.success = Boolean.TRUE;
		this.msg = "删除成功！";
		return SUCCESS;
	}

	public void set_(String _) {
		this._ = _;
	}

	
	public Boolean getSuccess() {
		return success;
	}
	public String getMsg() {
		return msg;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Map getMap() {
		return map;
	}
	public Object[] getArr() {
		return arr;
	}
	/** if haven't this method page cann't get pojo's property value  **/
	public void setPojo(MigJobContent pojo) {
		this.pojo = pojo;
	}
	/**
	 * struts2 map pojo must have get Method
	 * @return
	 */
	public MigJobContent getPojo() {
		return pojo;
	}
	public void setDataService(MigJobContentService dataService) {
		this.dataService = dataService;
	}
}
