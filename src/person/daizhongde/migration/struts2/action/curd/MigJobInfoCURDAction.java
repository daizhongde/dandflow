package person.daizhongde.migration.struts2.action.curd;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.json.JSONException;
import org.apache.struts2.json.JSONUtil;

import person.daizhongde.virtue.util.test.Printer;
import person.daizhongde.virtue.util.json.JsonUtils;

import person.daizhongde.authority.struts2.action.BaseAction;
import person.daizhongde.migration.hibernate.dto.MigJobNodeDto;
import person.daizhongde.migration.hibernate.dto.MigJobParaDto;
import person.daizhongde.migration.hibernate.pojo.MigJobInfo;
import person.daizhongde.migration.hibernate.pojo.MigJobPara;
import person.daizhongde.migration.spring.service.MigJobContentService;
import person.daizhongde.migration.spring.service.MigJobInfoService;

/**
 * 控件信息CURD
 * <br>this type Actions whose name are match "*JsonCURDAction"
 * <br>only do three work:
 * <br>        add, modify, delete a record, current also do read 
 * @author dzd
 * @date 2015-01-07
 */
public class MigJobInfoCURDAction extends BaseAction {
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
	private String rows;
	
	protected Map map = new HashMap();
	protected Object[] arr;
	protected MigJobInfo pojo;
	protected MigJobInfoService dataService;
	private MigJobContentService jobContentService;
	
	
	/**
	 * 作业一次性保存
	 * @return
	 */
	public String saveJobInAll() {
		int i = 0;//insert row count
		try{
			//@return The number of entities updated or deleted or insert.
//			i = dataService.addWithId(super.getJdata());
			JSONObject json = JSONObject.fromObject( super.getJdata() );

			@SuppressWarnings("unchecked")
			List<MigJobNodeDto> list = (List<MigJobNodeDto>)JsonUtils.jsonStr2List(
					json.getJSONArray("rows").toString(),
					MigJobNodeDto.class
				);
			
			MigJobNodeDto job = (MigJobNodeDto)JsonUtils.jsonStr2Entity(json.getJSONObject("node").toString(),
					MigJobNodeDto.class
				);
			id = dataService.saveJobInAll( list, job );
			
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
		this.msg = "作业保存成功！";
		return SUCCESS;
	}
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
			i = dataService.addWithId(super.getJdata());
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
	 * 新增并返回记录ID
	 * @return
	 */
	public String addRetId() {
		JSONObject json = StringUtils.isEmpty( super.getJdata() )?null:JSONObject.fromObject( super.getJdata() );
		super.setJsonObject(json);
		try{
			//@return The number of entities updated or deleted or insert.
			this.id = dataService.addJobRetId( 
					super.get("jobName"),
					super.getInt("type"),
					super.get("jobRemark"),
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
	 * 修改模块初始化,跳到JSP页面
	 * @return
	 * @throws JSONException 
	 * @throws Exception
	 */
	public String initModify() throws JSONException {
		
		this.pojo = (MigJobInfo)dataService.browsePOJOById( id );

		List l = jobContentService.findByJobId( id );

		super.setJson( JSONUtil.serialize(l,true) );

		return "mig_modify";
	}
	/**
	 * 修改模块初始化,跳到html页面
	 * @return
	 * @throws Exception
	 */
	public String initModify2() {
		this.pojo = (MigJobInfo)dataService.browsePOJOById( id );
		return "modify2";
	}
	public String modify(){
		int i = 0;//update row count
		try{
			//@return The number of entities updated or deleted or insert.
			i = dataService.modify(super.getJdata());
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
	public String modifyWithJobParam(){
		try{
			List<MigJobParaDto> list = (List<MigJobParaDto>)JsonUtils.jsonStr2List(rows, MigJobParaDto.class);
			
			//@return The number of entities updated or deleted or insert.
			dataService.modifyWithJobParam(super.getJdata(), list );
		}catch(Exception e){
			e.printStackTrace();
			Throwable e2 = e;
			while(e2.getCause() != null ){
//				System.out.println("e2.getLocalizedMessage():"+e2.getLocalizedMessage());
				e2 = e2.getCause();
			}
			this.success = Boolean.FALSE;
			this.msg = e2.getLocalizedMessage();
			return SUCCESS;
		}
		this.success = Boolean.TRUE;
		this.msg = "Update Success！";
		return SUCCESS;
	}
	public String browse() throws JSONException{
		this.pojo = (MigJobInfo)dataService.browsePOJOById( id );

		List l = jobContentService.findByJobId( id );

		super.setJson( JSONUtil.serialize(l,true) );
		
		return "mig_browse";
	}
//	/**
//	 * 删除一个没有被引用的作业
//	 * @return
//	 */
//	public String delete(){
//		JSONObject json = StringUtils.isEmpty( super.getJdata() )?null:JSONObject.fromObject( super.getJdata() );
//		super.setJsonObject(json);
//		
//		try{
//			dataService.deleteJobbyIdRecursion( json.getJSONObject("condition").getString("id"), super.getLoginUser() );
//		}catch(Exception e){
//			e.printStackTrace();
//			Throwable e2 = e;
//			while(e2.getCause() != null ){
//				e2 = e2.getCause();
//			}
//			this.success = Boolean.FALSE;
//			this.msg = e2.getLocalizedMessage();
//			this.msg = StringUtils.isEmpty( this.msg ) ? "未知异常" : this.msg;
//			return SUCCESS;
//		}
//		this.success = Boolean.TRUE;
//		this.msg = "删除成功！";
//		return SUCCESS;
//	}

//	/**
//	 * 删除作业
//	 * @return
//	 */
//	public String deleteRecursion(){
//		JSONObject json = StringUtils.isEmpty( super.getJdata() )?null:JSONObject.fromObject( super.getJdata() );
//		super.setJsonObject(json);
//		
//		try{
//			dataService.deleteJobbyIdRecursion( json.getJSONObject("condition").getString("id"), super.getLoginUser() );
//		}catch(Exception e){
//			e.printStackTrace();
//			Throwable e2 = e;
//			while(e2.getCause() != null ){
//				e2 = e2.getCause();
//			}
//			this.success = Boolean.FALSE;
//			this.msg = e2.getLocalizedMessage();
//			this.msg = StringUtils.isEmpty( this.msg ) ? "未知异常" : this.msg;
//			return SUCCESS;
//		}
//		this.success = Boolean.TRUE;
//		this.msg = "删除成功！";
//		return SUCCESS;
//	}
	
	public void set_(String _) {
		this._ = _;
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
	public Map getMap() {
		return map;
	}
	public Object[] getArr() {
		return arr;
	}
	/** if haven't this method page cann't get pojo's property value  **/
	public void setPojo(MigJobInfo pojo) {
		this.pojo = pojo;
	}
	/**
	 * struts2 map pojo must have get Method
	 * @return
	 */
	public MigJobInfo getPojo() {
		return pojo;
	}
	
	public void setRows(String rows) throws UnsupportedEncodingException {
		String decode = java.net.URLDecoder.decode(rows, "UTF-8");
		this.rows = decode;
	}
	public void setDataService(MigJobInfoService dataService) {
		this.dataService = dataService;
	}
	public void setJobContentService(MigJobContentService jobContentService) {
		this.jobContentService = jobContentService;
	}
	
}
