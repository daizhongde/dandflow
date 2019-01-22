package person.daizhongde.migration.struts2.action.busi;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import person.daizhongde.virtue.assemble.sql.SQLAssembleQ;
import person.daizhongde.virtue.constant.AbstractConstant;
import net.sf.json.JSONObject;

import person.daizhongde.authority.struts2.action.BaseAction;
import person.daizhongde.migration.constant.ConstMigControlInfo;
import person.daizhongde.migration.spring.service.MigControlInfoService;

/**
 * 控件信息查询
 * <br>only used by jquery-easy-ui
 * <br>this type Acitons whose name are match "*JEasyUIQUERYAction"
 * <br>are only do a work: query table data
 * @author dzd
 * @date 2015-01-07
 */
public class MigControlInfoBUSIAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String jdata;

	private String json;
	
	private MigControlInfoService dataService;


	public String getJson() {
		return json;
	}
	public void setJson(String json) {
		this.json = json;
	}
	
	public String getJdata() {
		return jdata;
	}
	public void setJdata(String jdata) throws UnsupportedEncodingException {
//		log.debug("encoded jdata:" + jdata.toString());
		String decode = java.net.URLDecoder.decode(jdata, "UTF-8");
		log.debug("#######");
		log.debug("decoded jdata:" + decode.toString());
		log.debug("#######");
		this.jdata = decode;
	}
	public void setDataService(MigControlInfoService dataService) {
		this.dataService = dataService;
	}
}
