package person.daizhongde.migration.struts2.action.busi;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.examples.client.copote.HttpWriteResponse2DB_copote;

import net.sf.json.JSONObject;
import person.daizhongde.authority.spring.service.TAuthorityInstService;
import person.daizhongde.authority.struts2.action.BaseAction;
import person.daizhongde.migration.constant.ConstTCopoteEmployee;
import person.daizhongde.migration.spring.service.TCopoteEmployeeService;
import person.daizhongde.virtue.assemble.sql.SQLAssembleQ;
import person.daizhongde.virtue.constant.AbstractConstant;
import person.daizhongde.virtue.constant.INIT;

/**
 * 
 * @author daizd
 * @date 2019年5月19日
 */
public class TCopoteEmployeeBUSIAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private TCopoteEmployeeService dataService;
	private TAuthorityInstService tAuthorityInstService;
	
	private HttpWriteResponse2DB_copote reptiles;
	private String cookies;
	

	public void setCookies(String cookies) {
		this.cookies = cookies;
	}

	/**
	 * 更新湘邮员工数据
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String updateData(){
		JSONObject json = StringUtils.isEmpty( super.getJdata() )?null:JSONObject.fromObject( super.getJdata() );
		super.setJsonObject(json);
		// &user=root&password=123
		// &user=root&password=nStamp_2017
		String jdbcurl = INIT.url+"&user="+INIT.dbuser+"&password="+INIT.dbpass;
		

		AbstractConstant absConstant = new ConstTCopoteEmployee();
		SQLAssembleQ sqlA = new SQLAssembleQ(
				absConstant.getSQLDOC(),
				"SELECT n_iid as id FROM  tool.t_authority_inst",
				null, 
				null,
				absConstant.getColumnTypes(),
				absConstant.getFront2col()
			);
		
		List<Map<String, Long>> ret = tAuthorityInstService.getRowsInMap(sqlA);
		List<Long> l = new ArrayList<Long>();
		
		for( int i=0; i < ret.size(); i++ ){
			Map<String, Long> map = (Map<String, Long>)ret.get(i);
			l.add( map.get("id") );
		}
		int affectrow=-1;
		try{
			affectrow = reptiles.updateEmployee(jdbcurl, l, cookies);
		}catch(Exception e){
			Throwable e2 = e;
			while(e2.getCause() != null ){
				e2 = e2.getCause();
			}
			super.setJson( "{success: false, msg:  affectrow:-1,\""+e2.getLocalizedMessage()+"\", affectrow:-1}" );
			return SUCCESS;
		}
		super.setJson( "{success: true, msg: 'Stop Success! Affectrow:"+affectrow+"'}" );
		return SUCCESS;
	}

	public void setDataService(TCopoteEmployeeService dataService) {
		this.dataService = dataService;
	}

	public void settAuthorityInstService(TAuthorityInstService tAuthorityInstService) {
		this.tAuthorityInstService = tAuthorityInstService;
	}

	public void setReptiles(HttpWriteResponse2DB_copote reptiles) {
		this.reptiles = reptiles;
	}
	
}