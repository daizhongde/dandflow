package person.daizhongde.migration.struts2.action.common;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import person.daizhongde.virtue.util.field.TableUtil;

import person.daizhongde.authority.struts2.action.BaseAction;
import person.daizhongde.migration.spring.service.CommonCURDService;

/**
 * 通用CURD
 * <br>this type Actions whose name are match "*JsonCURDAction"
 * <br>only do three work:
 * <br>        add, modify, delete a record, current also do read 
 * @author dzd
 * @date 2015-01-07
 */
public class CommonCURDAction extends BaseAction {
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

	private String tableName;
	private String frontPageName;
	/** foldName is path. eg:
	 *  migcommon 
	 * 	migcommon/migConfigConnection
	 *  migConfigConnection
	 *  defalut:
	 *    migcommon/${frontPageName}
	 *   **/
	private String foldName;
	
	private String authorColumnName;
	private String readSQLName;
	
	private CommonCURDService curdService;

	/**
	 * 新增初始化
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
			if(StringUtils.isEmpty( authorColumnName )){
				i = curdService.addWithId(super.getJdata(), tableName );
			}else{
				i = curdService.addWithId(super.getJdata(), tableName, authorColumnName, super.getLoginUser() );
			}
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
	 * 修改初始化,跳到JSP页面
	 * @return
	 * @throws Exception
	 
	public String initModify2() {
		this.map = curdService.browse( super.getJdata(), tableName);
		return "common_modify2";
	}*/
	/**
	 * 修改初始化,跳到JSP页面
	 * @return
	 * @throws Exception
	 */
	public String initModify() {
		this.map = curdService.browse( super.getJdata(), tableName);
		return "common_modify";
	}
	public String modify(){
		int i = 0;//update row count
		try{
			//@return The number of entities updated or deleted or insert.
			i = curdService.modify(super.getJdata(), tableName);
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
	 * 删除
	 * @return
	 */
	public String delete(){
		int i = 0;//delete count
		try{
			//@return The number of entities updated or deleted or insert.
			i = curdService.delete(super.getJdata(), tableName);
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
	/**
	 * 查看
	 * @return map
	 */
	public String browse2(){
		if(StringUtils.isEmpty( readSQLName )){
			map = curdService.browse(super.getJdata(), tableName );
		}else{
			map = curdService.browse(super.getJdata(), tableName, readSQLName );
		}
		return "common_browse2";
	}
	/**
	 * 查看
	 * @return map
	 */
	public String browse(){
		if(StringUtils.isEmpty( readSQLName )){
			map = curdService.browse(super.getJdata(), tableName );
		}else{
			map = curdService.browse(super.getJdata(), tableName, readSQLName );
		}
		return "common_browse";
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
	
	public String getFrontPageName() {
		
		return StringUtils.isEmpty( frontPageName ) ? 
				TableUtil.cvtTableName2frontPageName(tableName) : frontPageName;
	}
	
	public String getFoldName() {
		return StringUtils.isEmpty( foldName ) ? 
				"migcommon/"+getFrontPageName() : foldName;
	}
	public void setFoldName(String foldName) {
		this.foldName = foldName;
	}
	public void setFrontPageName(String frontPageName) {
		this.frontPageName = frontPageName;
	}
	public Map getMap() {
		return map;
	}
	public Object[] getArr() {
		return arr;
	}
	
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	public void setAuthorColumnName(String authorColumnName) {
		this.authorColumnName = authorColumnName;
	}
	
	public void setReadSQLName(String readSQLName) {
		this.readSQLName = readSQLName;
	}
	public void setCurdService(CommonCURDService curdService) {
		this.curdService = curdService;
	}

}
