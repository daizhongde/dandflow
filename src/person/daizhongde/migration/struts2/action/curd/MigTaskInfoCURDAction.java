package person.daizhongde.migration.struts2.action.curd;

import java.io.UnsupportedEncodingException;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.json.JSONException;
import org.apache.struts2.json.JSONUtil;

import person.daizhongde.virtue.util.json.JsonUtils;

import person.daizhongde.authority.struts2.action.BaseAction;
import person.daizhongde.migration.hibernate.dto.MigComInfoDto;
import person.daizhongde.migration.hibernate.dto.MigControlTemplateDto;
import person.daizhongde.migration.hibernate.dto.MigJobParaDto;
import person.daizhongde.migration.hibernate.pojo.MigTaskInfo;
import person.daizhongde.migration.spring.service.MigComInfoService;
import person.daizhongde.migration.spring.service.MigTaskInfoService;

/**
 * 任务信息CURD
 * <br>this type Actions whose name are match "*CURDAction"
 * <br>only do three work:
 * <br>        add, modify, delete a record, current also do read 
 * @author dzd
 * @date 2015-01-07
 */
public class MigTaskInfoCURDAction extends BaseAction {
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
	private String rows;
	private String cominfos;
	
	protected Object[] arr;
	protected MigTaskInfo pojo;
	protected MigTaskInfoService dataService;
	private MigComInfoService migComInfoService;
	
	public String nextNewTaskId(){
		try{
			id = dataService.getNewTaskId();
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
	 * 新增任务初始化
	 * @return
	 * @throws Exception
	 */
	public String initAdd() throws Exception {
		this.setTitle("发布任务");
		this.setInfo("请填写任务内容。标有 * 号的为必填项。");
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
	 * 修改模块初始化,跳到JSP页面
	 * @return
	 * @throws JSONException 
	 * @throws Exception
	 */
	public String initModify() throws JSONException {
//		JSONObject json = StringUtils.isEmpty( super.getJdata() )?null:JSONObject.fromObject( super.getJdata() );
//		super.setJsonObject(json);
		
		this.pojo = (MigTaskInfo)dataService.browsePOJOById( id );
//		String controlId = super.get( "controlId" );
//		this.setControlId(controlId);
//		List<MigControlTemplateDto> list = migComInfoService.queryComInfos( super.get( "controlId" ), comId );
		List<MigControlTemplateDto> list = migComInfoService.queryComInfos( controlId, comId );
		
		super.setJson( JSONUtil.serialize(list,true) );
		return "mig_modifypara";
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
	
	@SuppressWarnings("unchecked")
	public String modifyWithTaskParamandComInfo(){
		JSONObject json = StringUtils.isEmpty( super.getJdata() )?null:JSONObject.fromObject( super.getJdata() );
		super.setJsonObject(json);
		
		try{
			List<MigJobParaDto> list = (List<MigJobParaDto>)JsonUtils.jsonStr2List(rows, MigJobParaDto.class);
			List<MigComInfoDto> list2 = (List<MigComInfoDto>)JsonUtils.jsonStr2List(cominfos, MigComInfoDto.class);
			
			//@return The number of entities updated or deleted or insert.
//			System.out.println("modifyWithTaskParamandComInfo comId:"+comId);
			comId = dataService.modifyWithTaskParamandComInfo(
					super.get("taskId"),
					super.get("taskName"),
					super.get("taskRemark"),
					comId,
					list,
					list2
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
		this.msg = "更新成功！";
		return SUCCESS;
	}
	
	/**
	 * 删除任务
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
	
	public String get_() {
		return _;
	}
	public Boolean getSuccess() {
		return success;
	}
	public String getMsg() {
		return msg;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	public String getId() {
		return id;
	}

	public String getControlId() {
		return controlId;
	}
	public void setControlId(String controlId) {
		this.controlId = controlId;
	}
	public String getComId() {
		return comId;
	}
	public void setComId(String comId) {
		this.comId = comId;
	}
	
	public String getRows() {
		return rows;
	}
	public void setRows(String rows) throws UnsupportedEncodingException {
//		String decode = java.net.URLDecoder.decode(rows, "UTF-8");
		this.rows = rows;
	}
	public String getCominfos() {
		return cominfos;
	}
	public void setCominfos(String cominfos) throws UnsupportedEncodingException {
//		String decode = java.net.URLDecoder.decode(cominfos, "UTF-8");
		this.cominfos = cominfos;
	}
	public Object[] getArr() {
		return arr;
	}
	/** if haven't this method page cann't get pojo's property value  **/
	public void setPojo(MigTaskInfo pojo) {
		this.pojo = pojo;
	}
	/**
	 * struts2 map pojo must have get Method
	 * @return
	 */
	public MigTaskInfo getPojo() {
		return pojo;
	}

	public void setMigComInfoService(MigComInfoService migComInfoService) {
		this.migComInfoService = migComInfoService;
	}

	public void setDataService(MigTaskInfoService dataService) {
		this.dataService = dataService;
	}
}
