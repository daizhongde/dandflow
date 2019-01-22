package person.daizhongde.migration.struts2.action.cbb;

import person.daizhongde.authority.struts2.action.BaseAction;
import person.daizhongde.migration.spring.service.MigControlInfoService;

/**
 * 模块信息相关下拉框数据
 * combobox data
 * @author dzd
 *
 */
public class ControlCBBAction extends BaseAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1625277481198555717L;
	private String dicType;
	
	private String sResponse;

	private MigControlInfoService dataService;
		
	/**
	 * 下拉框Json数据
	 * <p>cbb : combobox
	 * @return JSONObject 
	 */
	public String getDicByType(){
		super.setJson( dataService.getCtlInfoAll() );
		return SUCCESS;
	}

	public String getDicType() {
		return dicType;
	}

	public void setDicType(String dicType) {
		this.dicType = dicType;
	}

	public void setSResponse(String sResponse) {
		this.sResponse = sResponse;
	}
	public String getSResponse() {
		return sResponse;
	}

	public MigControlInfoService getDataService() {
		return dataService;
	}

	public void setDataService(MigControlInfoService dataService) {
		this.dataService = dataService;
	}
	
}
