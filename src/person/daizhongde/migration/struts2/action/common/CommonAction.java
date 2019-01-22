package person.daizhongde.migration.struts2.action.common;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import person.daizhongde.authority.struts2.action.BaseAction;
import person.daizhongde.migration.spring.service.CommonService;

/**
 * globle constant 
 * <br>
 * constant
 * @author dzd
 *
 */
public class CommonAction extends BaseAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 756574523370955608L;

	private CommonService dataService;
	
	private String sResponse;
	private Map map = new HashMap();
	/**
	 * 
	 * @return 
	 * {"total":2,
	 * 		"rows":[
	 * 			{ "TABLE_NAME" : "T_AUTHORITY_LEVEL" },
	 * 			{ "TABLE_NAME" : "T_AUTHORITY_MODULE" }
	 * 		]
	 * }
	 */
	public String TableNames() {
		System.out.println("#########  TableNames  ########");
		this.sResponse = dataService.getTableNames();
		this.setSResponse(sResponse);
		return "sResponse";
	}
	
	public String currentUserLogname() {
		this.sResponse = super.getUserlogname();
		this.setSResponse(sResponse);
		return "sResponse";
	}
	/**
	 * 升级后的struts2  action属性没有放到request的Attribute中
	 * @param sResponse
	 */
	public void setSResponse(String sResponse) {
		this.sResponse = sResponse;
		HttpServletRequest request=ServletActionContext.getRequest();  
        ServletContext cxt=ServletActionContext.getServletContext();  
        request.setAttribute("sResponse", sResponse );
	}
	public String getSResponse() {
		return sResponse;
	}
	public Map getMap() {
		return map;
	}

	public void setDataService(CommonService dataService) {
		this.dataService = dataService;
	}
	
}
