package person.daizhongde.migration.struts2.action.tree;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import person.daizhongde.migration.spring.service.MigAuditvConfigtreeService;
import person.daizhongde.authority.struts2.action.BaseAction;
import com.opensymphony.xwork2.ActionContext;

/**
 * 控件树<p>将任务包和控件一起在树中展示
 * <br>get tree json data 
 * @author dzd
 *
 */
public class MigAuditvConfigtreeTREEAction extends BaseAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7769930693708478011L;

	/** ext3 and ext4 treeload params **/
	private String node;
	
	/** ext4 treeload params **/
	private String _dc;
	
	/** lazy load YUI2, JEasyUI, dnd node source, remove node id **/
	private int id;//lazy load parameter
	
	private int userId;
	
	private String jdata;
	
	/** move tree node refer **/
	private int target;
	private String point;
	
	private MigAuditvConfigtreeService dataService;
	
	//for response
	private Object json;
	
	//for response
	private	Map map = new HashMap();
			
	/**
	 * config Json数据-menu
	 * @return JSONArray
	 */
	public String query_JEasyUI_Tree_Async(){
//		log.debug("action........query_JEasyUI_Tree............");
		//取得ActionContext实例
		ActionContext ctx = ActionContext.getContext();
		//通过ActionContext访问用户的HttpSession
		Map session = ctx.getSession();
//		UserInfo userinfo = (UserInfo)session.get("userinfo");
//		if(userinfo==null){
//			return "login";
//		}
//		System.out.println("id:"+userinfo.getNUid()+" name:"+userinfo.getCUname());
//		System.out.println("id:"+id);
		if( id == 0 ){
			json = dataService.getData_JEasyUI_Tree_Async( 1, true );
		}else{
			json = dataService.getData_JEasyUI_Tree_Async(
					id, false );
		}
		return SUCCESS;
	}
	
	public void setNode(String node) {
		this.node = node;
	}

	public void set_dc(String _dc) {
		this._dc = _dc;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}

	public void setJdata(String jdata) throws UnsupportedEncodingException {
//		log.debug("before decode jdata:" + jdata.toString());
		String decode = java.net.URLDecoder.decode(jdata, "UTF-8");
//		log.debug("after  decode jdata:" + decode.toString());
		this.jdata = decode;
	}

	public void setTarget(int target) {
		this.target = target;
	}
	public void setPoint(String point) {
		this.point = point;
	}

	public void setDataService(MigAuditvConfigtreeService dataService) {
		this.dataService = dataService;
	}

	public Map getMap() {
		return map;
	}
	public void setMap(Map map) {
		this.map = map;
	}

	public Object getJson() {
		return json;
	}

	public void setJson(Object json) {
		this.json = json;
	}

}
