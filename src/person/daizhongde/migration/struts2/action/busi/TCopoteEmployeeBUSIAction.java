package person.daizhongde.migration.struts2.action.busi;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import net.sf.json.JSONObject;
import person.daizhongde.authority.hibernate.pojo.TAuthorityUser;
import person.daizhongde.authority.spring.service.TAuthorityInstService;
import person.daizhongde.authority.struts2.action.BaseAction;
import person.daizhongde.migration.constant.ConstTCopoteEmployee;
import person.daizhongde.migration.spring.jdbc.HttpResponse2DB_copote;
import person.daizhongde.migration.spring.service.TCopoteEmployeeService;
import person.daizhongde.virtue.assemble.sql.SQLAssembleQ;
import person.daizhongde.virtue.constant.AbstractConstant;
import person.daizhongde.virtue.constant.INIT;
import person.daizhongde.virtue.interact.BackendInfo;

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
	private TAuthorityInstService authorityInstService;
	
	private HttpResponse2DB_copote copoteEmployeeReptiles;
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
		TAuthorityUser user= super.getLoginUser();
		BackendInfo.msg.put( user.getCUemail(), "开始更新员工数据...");
		
		JSONObject json = StringUtils.isEmpty( super.getJdata() )?null:JSONObject.fromObject( super.getJdata() );
		super.setJsonObject(json);
		// &user=root&password=123
		// &user=root&password=nStamp_2017
		String jdbcurl = INIT.url+"&user="+INIT.dbuser+"&password="+INIT.dbpass;
		

		AbstractConstant absConstant = new ConstTCopoteEmployee();
		SQLAssembleQ sqlA = new SQLAssembleQ(
				absConstant.getSQLDOC(),
				"select n_iid nIid from tool.t_authority_inst where n_ilevel=2",
				null, 
				null,
				absConstant.getColumnTypes(),
				absConstant.getFront2col()
			);

		BackendInfo.msg.put( user.getCUemail(), "开始更新员工数据：查询部门数据...");
		List<Map<String, Long>> ret = authorityInstService.getRowsInMap(sqlA);
		BackendInfo.msg.put( user.getCUemail(), "开始更新员工数据：查询部门数据完成！");
		List<Long> l = new ArrayList<Long>();
		
		for( int i=0; i < ret.size(); i++ ){
			Map<String, Long> map = (Map<String, Long>)ret.get(i);
			l.add( new Long( String.valueOf( map.get("nIid") )) );
		}
		int affectrow=-1;
		try{
			affectrow = copoteEmployeeReptiles.updateEmployee(jdbcurl, l, cookies, user );
		}catch(Exception e){
			Throwable e2 = e;
			while(e2.getCause() != null ){
				e2 = e2.getCause();
			}
			super.setJson( "{success: false, msg:\"affectrow:0,"+e2.getLocalizedMessage()+"或无效Cookies或其它异常\"}" );
			return SUCCESS;
		}
		if(affectrow == -1){
			super.setJson( "{success: false, msg:\"affectrow:0,更新时出错!请联系管理员QQ：413881461\"}" );
			return SUCCESS;
		}
		super.setJson( "{success: true, msg:\"Update Success! Update rows num:"+affectrow+"\"}" );
		return SUCCESS;
	}

	public void setDataService(TCopoteEmployeeService dataService) {
		this.dataService = dataService;
	}

	public void setAuthorityInstService(TAuthorityInstService authorityInstService) {
		this.authorityInstService = authorityInstService;
	}

	public void setCopoteEmployeeReptiles(HttpResponse2DB_copote copoteEmployeeReptiles) {
		this.copoteEmployeeReptiles = copoteEmployeeReptiles;
	}
	
}