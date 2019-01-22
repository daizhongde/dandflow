package person.daizhongde.migration.struts2.action.cbb;

import java.io.UnsupportedEncodingException;

import person.daizhongde.authority.struts2.action.BaseAction;
import person.daizhongde.migration.spring.service.TAsiainfoEmployeeService;

/**
 * 模块信息相关下拉框数据
 * combobox data
 * @author dzd
 *
 */
public class TAsiainfoEmployeeCBBAction extends BaseAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1625277481198555717L;

	protected String jdata;
	
	private String sResponse;

	protected TAsiainfoEmployeeService dataService;
	
	/**
	 * 下拉框Json数据
	 * <p>cbb : combobox
	 * @return JSONObject 
	 */
	public String getENUMData_SBU(){
		log.debug("action............getCBBData_SBU........");
		if(jdata==null || jdata.trim().equalsIgnoreCase("")){
			jdata = "{ act: \"queryCBB\",condition: {},operator : {} }";
		}
		super.setJson( dataService.getCBBData_SBU(jdata) );
		return SUCCESS;
	}

	/**
	 * 下拉框Json数据
	 * <p>cbb : combobox
	 * @return JSONArray 
	 */
	public String getENUMData_Company(){
		log.debug("action............getCBBData_Company........");
		if(jdata==null || jdata.trim().equalsIgnoreCase("")){
			jdata = "{ act: \"queryCBB\",condition: {},operator : {} }";
		}
//		sResponse = dataService.getCBBDataInArray_Id(jdata);
		super.setJson( dataService.getCBBData_Company(jdata) );
		return SUCCESS;
	}

	public void setJdata(String jdata) throws UnsupportedEncodingException {
//		log.debug("encoded jdata:" + jdata.toString());
		String decode = java.net.URLDecoder.decode(jdata, "UTF-8");
		log.debug("decoded jdata:" + decode.toString());
		this.jdata = decode;
	}
	public void setSResponse(String sResponse) {
		this.sResponse = sResponse;
	}
	public String getSResponse() {
		return sResponse;
	}
	public void setDataService(TAsiainfoEmployeeService dataService) {
		this.dataService = dataService;
	}
}
