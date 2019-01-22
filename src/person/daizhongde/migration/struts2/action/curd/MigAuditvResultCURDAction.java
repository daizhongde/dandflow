package person.daizhongde.migration.struts2.action.curd;

import java.util.HashMap;
import java.util.Map;

import person.daizhongde.authority.struts2.action.BaseAction;
import person.daizhongde.migration.hibernate.pojo.MigAuditfMain;
import person.daizhongde.migration.spring.service.MigAuditvResultService;

/**
 * field audit config main table
 * <br>this type Actions whose name are match "*JsonCURDAction"
 * <br>only do three work:
 * <br>        add, modify, delete a record, current also do read 
 * @author dzd
 * @date 2015-01-07
 */
public class MigAuditvResultCURDAction extends BaseAction {
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
	private Integer id;
	
	protected Map map = new HashMap();
	protected Object[] arr;
	protected MigAuditfMain pojo;
	protected MigAuditvResultService dataService;

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
	public String add() {
		try{
			dataService.add(super.getJdata(), super.getLoginUser());
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
		this.success = Boolean.TRUE;
		this.msg = "添加成功！";
		return SUCCESS;
	}
	/**
	 * 修改初始化,跳到JSP页面
	 * @return
	 * @throws Exception
	 */
	public String initModify() {
		this.map = dataService.browse( super.getJdata() );
		return "mig_modify";
	}
	/**
	 * 不通过原因录入，如果有就update mig_auditv_errreason,如果没有就insert
	 * @return
	 */
	public String initModify2() {
		this.map = dataService.browse2( super.getJdata() );
		return "mig_modify2";
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

	/**
	 * 删除级别
	 * @return
	 */
	public String delete(){
		int i = 0;//delete count
		try{
			//@return The number of entities updated or deleted or insert.
			i = dataService.delete(super.getJdata());
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
	public String browse(){
		map = dataService.browse(super.getJdata() );

		return "mig_browse";
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
	
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getId() {
		return id;
	}
	public Map getMap() {
		return map;
	}
	public Object[] getArr() {
		return arr;
	}
	/** if haven't this method page cann't get pojo's property value  **/
	public void setPojo(MigAuditfMain pojo) {
		this.pojo = pojo;
	}
	/**
	 * struts2 map pojo must have get Method
	 * @return
	 */
	public MigAuditfMain getPojo() {
		return pojo;
	}
	public void setDataService(MigAuditvResultService dataService) {
		this.dataService = dataService;
	}
}
