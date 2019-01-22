package person.daizhongde.migration.struts2.action.curd;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.json.JSONException;
import org.apache.struts2.json.JSONUtil;

import person.daizhongde.authority.struts2.action.BaseAction;
import person.daizhongde.migration.hibernate.dto.MigControlTemplateDto;
import person.daizhongde.migration.hibernate.pojo.MigJobIns;
import person.daizhongde.migration.hibernate.pojo.MigJobProcess;
import person.daizhongde.migration.spring.service.MigComInsService;
import person.daizhongde.migration.spring.service.MigJobInsService;
import person.daizhongde.migration.spring.service.MigJobProcessService;

/**
 * 控件信息CURD
 * <br>this type Actions whose name are match "*JsonCURDAction"
 * <br>only do three work:
 * <br>        add, modify, delete a record, current also do read 
 * @author dzd
 * @date 2015-01-07
 */
public class MigJobProcessCURDAction extends BaseAction {
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
	private String controlId;
	private String comId;
	private MigJobIns jobIns;
	
	protected Map map = new HashMap();
	protected Object[] arr;
	protected MigJobProcess pojo;
	protected MigJobProcessService dataService;
	private MigJobInsService jobInsService;
	private MigComInsService migComInsService;
	
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
	/**
	 * 查看初始化,跳到JSP页面
	 * @return
	 * @throws JSONException 
	 * @throws Exception
	 */
	public String initBrowse() throws JSONException {
//		JSONObject json = StringUtils.isEmpty( super.getJdata() )?null:JSONObject.fromObject( super.getJdata() );
//		super.setJsonObject(json);
		
		this.pojo = (MigJobProcess)dataService.browsePOJOById( id );
//		pojo.setRemark("");//暂时解决出错任务不能查看的问题，待开发更好的办法
		this.jobIns = (MigJobIns)jobInsService.browsePOJOById( pojo.getJobInsId() );
//		List<MigControlTemplateDto> list = migComInsService.queryComInss( super.get( "controlId" ), comId, pojo.getJobInsId() );
		List<MigControlTemplateDto> list = migComInsService.queryComInss( controlId, comId, pojo.getJobInsId() );

		super.setJson( JSONUtil.serialize(list,true) );
		return "mig_browsepara";
	}
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
	public String getComId() {
		return comId;
	}
	public void setComId(String comId) {
		this.comId = comId;
	}
	public Map getMap() {
		return map;
	}
	public Object[] getArr() {
		return arr;
	}
	/** if haven't this method page cann't get pojo's property value  **/
	public void setPojo(MigJobProcess pojo) {
		this.pojo = pojo;
	}
	/**
	 * struts2 map pojo must have get Method
	 * @return
	 */
	public MigJobProcess getPojo() {
		return pojo;
	}
	
	public String getControlId() {
		return controlId;
	}
	public void setControlId(String controlId) {
		this.controlId = controlId;
	}
	public MigJobIns getJobIns() {
		return jobIns;
	}
	public void setJobIns(MigJobIns jobIns) {
		this.jobIns = jobIns;
	}
	public void setDataService(MigJobProcessService dataService) {
		this.dataService = dataService;
	}
	public void setJobInsService(MigJobInsService jobInsService) {
		this.jobInsService = jobInsService;
	}
	public void setMigComInsService(MigComInsService migComInsService) {
		this.migComInsService = migComInsService;
	}
	
}
