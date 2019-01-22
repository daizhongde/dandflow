package person.daizhongde.migration.struts2.action;

import java.util.HashMap;
import java.util.Map;

import person.daizhongde.authority.struts2.action.BaseAction;
import person.daizhongde.migration.spring.service.ConstantService;

/**
 * globle constant 
 * <br>
 * constant
 * @author dzd
 *
 */
public class ConstantJEasyUIQUERYAction extends BaseAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 756574523370955608L;

	private ConstantService dataService;
	
	private Object json;
	private Map map = new HashMap();
	/**
	 * 查询环境信息
	 * @return
	 */
	public String dfindENV() {
		System.out.println("#########  dfindENV  ########");
		this.json = dataService.dfindENV();
		return "json";
	}

	public Object getJson() {
		return json;
	}

	public void setJson(Object json) {
		this.json = json;
	}

	public void setDataService(ConstantService dataService) {
		this.dataService = dataService;
	}
	
}
