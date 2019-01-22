package person.daizhongde.migration.spring.service.impl;

import java.util.Map;

import net.sf.json.JSONObject;

import person.daizhongde.authority.constant.AuthorityLevel;
import person.daizhongde.authority.hibernate.pojo.TAuthorityUser;
import person.daizhongde.migration.constant.ConstCommon;
import person.daizhongde.migration.spring.service.CommonCURDService;
import person.daizhongde.migration.spring.service.CommonService;

import person.daizhongde.virtue.assemble.sql.SQLAssembleC;
import person.daizhongde.virtue.assemble.sql.SQLAssembleD;
import person.daizhongde.virtue.assemble.sql.SQLAssembleR;
import person.daizhongde.virtue.assemble.sql.SQLAssembleU;
import person.daizhongde.virtue.configutils.SQLNode;
import person.daizhongde.virtue.constant.AbstractConstant;
import person.daizhongde.virtue.dao.SpringHibernateDao;

public class CommonCURDServiceImpl implements CommonCURDService {
	
	private SpringHibernateDao virtueDAO;

	private CommonService commonService;
	
	@Override
	public int addWithId( String jdata, String tableName ){
		JSONObject jsonObject = JSONObject.fromObject(jdata);

		SQLAssembleC sqlA = new SQLAssembleC(
				tableName,
				jsonObject.getJSONObject("data"),
				null,
				null
				);
		
		return virtueDAO.sqlQueryExeUByMap(sqlA.getSQL(), sqlA.getMap());
	}
	@Override
	public int addWithId( String jdata, String tableName, 
			String authorColumnName, TAuthorityUser user ){
		JSONObject jsonObject = JSONObject.fromObject(jdata);
		Map data = jsonObject.getJSONObject("data");
		data.put(authorColumnName, user.getCUlogname() );
		
		SQLAssembleC sqlA = new SQLAssembleC(
				tableName,
				data,
				null,
				null
				);
		
		return virtueDAO.sqlQueryExeUByMap(sqlA.getSQL(), sqlA.getMap());
	}
	@Override
	public Map browse(String jdata, String tableName ) {
		JSONObject jsonObject = JSONObject.fromObject(jdata);
		AbstractConstant absConstant = new ConstCommon();
		
		String readSQL = "select "+commonService.getSelectSQLColumns().get(tableName.toLowerCase()) + " from " + tableName;
		
		SQLAssembleR sqlA = new SQLAssembleR(
				null,
				readSQL, 
				jsonObject.getJSONObject("condition"),
				jsonObject.getJSONObject("operator"),
				absConstant.getColumnTypes(),
				null );
		
		return (Map)virtueDAO.sqlQuerylistAllByMap(sqlA.getSQL(), sqlA.getMap()).get(0);
	}
	@Override
	public Map browse(String jdata, String tableName, String readSQLName ) {
		JSONObject jsonObject = JSONObject.fromObject(jdata);
		AbstractConstant absConstant = new ConstCommon();
		
//		String readSQL = "select "+selectSQLColumns + " from " + tableName;
		String readSQL = ((SQLNode)absConstant.getSQLDOC()
				.getRead().get( readSQLName )
			).getSQL();
		
		SQLAssembleR sqlA = new SQLAssembleR(
				null,
				readSQL, 
				jsonObject.getJSONObject("condition"),
				jsonObject.getJSONObject("operator"),
				absConstant.getColumnTypes(),
				null );
		
		return (Map)virtueDAO.sqlQuerylistAllByMap(sqlA.getSQL(), sqlA.getMap()).get(0);
	}
	
	@Override
	public int modify( String jdata, String tableName ){
		JSONObject jsonObject = JSONObject.fromObject(jdata);

		SQLAssembleU sqlA = new SQLAssembleU(
				null,
				tableName,
				jsonObject.getJSONObject("data"),
				jsonObject.getJSONObject("algorithm"),
				jsonObject.getJSONObject("condition"),
				jsonObject.getJSONObject("operator"),
				null,
				null );
		
		return virtueDAO.sqlQueryExeUByMap(sqlA.getSQL(), sqlA.getMap());
	}

	@Override
	public int delete( String jdata, String tableName ){
		JSONObject jsonObject = JSONObject.fromObject(jdata);

		SQLAssembleD sqlA = new SQLAssembleD(
				null,
				tableName, 
				jsonObject.getJSONObject("condition"),
				jsonObject.getJSONObject("operator"),
				null,
				null );
		
		return virtueDAO.sqlQueryExeUByMap(sqlA.getSQL(), sqlA.getMap());
	}

	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}

	public void setVirtueDAO(SpringHibernateDao virtueDAO) {
		this.virtueDAO = virtueDAO;
	}

}
