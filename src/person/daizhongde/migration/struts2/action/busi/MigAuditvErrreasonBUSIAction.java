package person.daizhongde.migration.struts2.action.busi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import net.sf.json.JSONObject;

import person.daizhongde.authority.struts2.action.BaseAction;
import person.daizhongde.migration.hibernate.pojo.MigAuditvErrreason;
import person.daizhongde.migration.spring.service.MigAuditvErrreasonService;

/**
 * 作业内容业务action
 * <br>only used by jquery-easy-ui
 * <br>this type Acitons whose name are match "*JEasyUIQUERYAction"
 * <br>are only do a work: query table data
 * @author dzd
 * @date 2015-01-07
 */
public class MigAuditvErrreasonBUSIAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private MigAuditvErrreasonService dataService;

	public String inputReason(){
		
		dataService.inputReason(super.getJdata(), super.getLoginUser() );
		
		Map map = new HashMap(2);
		map.put("success", Boolean.TRUE);
		map.put("msg", "Input Success!");
		super.setJson( JSONObject.fromObject(map).toString() );
		return SUCCESS;
	}

	public void setDataService(MigAuditvErrreasonService dataService) {
		this.dataService = dataService;
	}
}