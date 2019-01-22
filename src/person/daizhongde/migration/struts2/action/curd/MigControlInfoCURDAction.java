package person.daizhongde.migration.struts2.action.curd;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import person.daizhongde.authority.struts2.action.BaseAction;
import person.daizhongde.migration.hibernate.pojo.MigControlInfo;
import person.daizhongde.migration.spring.service.MigControlInfoService;

/**
 * 控件信息CURD
 * <br>this type Actions whose name are match "*JsonCURDAction"
 * <br>only do three work:
 * <br>        add, modify, delete a record, current also do read 
 * @author dzd
 * @date 2015-01-07
 */
public class MigControlInfoCURDAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** jquery-easy-ui refreshpage params 
	 * $('#win').window("refresh", "../tAM_add.html");
	 * **/
	private String _;
	protected String jdata;
	
	private Boolean success;// 只有private的变量(并且定义get方法)json插件才能返回
	private String msg;
	private Integer id;
	
	protected Map map = new HashMap();
	protected Object[] arr;
	protected MigControlInfo pojo;
	protected MigControlInfoService dataService;

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
			i = dataService.addWithId(jdata);
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
			i = dataService.modify(jdata);
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
			i = dataService.delete(jdata);
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
	public void setJdata(String jdata) throws UnsupportedEncodingException {
//		log.debug("encoded jdata:" + jdata.toString());
		String decode = java.net.URLDecoder.decode(jdata, "UTF-8");
		log.debug("decoded jdata:" + decode.toString());
		this.jdata = decode;
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
	public void setPojo(MigControlInfo pojo) {
		this.pojo = pojo;
	}
	/**
	 * struts2 map pojo must have get Method
	 * @return
	 */
	public MigControlInfo getPojo() {
		return pojo;
	}
	public void setDataService(MigControlInfoService dataService) {
		this.dataService = dataService;
	}
}
