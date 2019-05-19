package person.daizhongde.migration.spring.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import person.daizhongde.virtue.assemble.hql.HQLAssembleQ;
import person.daizhongde.virtue.assemble.hql.HQLAssembleR;
import person.daizhongde.virtue.assemble.sql.SQLAssembleC;
import person.daizhongde.virtue.assemble.sql.SQLAssembleD;
import person.daizhongde.virtue.assemble.sql.SQLAssembleQ;
import person.daizhongde.virtue.assemble.sql.SQLAssembleQ_CBB;
import person.daizhongde.virtue.assemble.sql.SQLAssembleR;
import person.daizhongde.virtue.assemble.sql.SQLAssembleU;
import person.daizhongde.virtue.configutils.SQLNode;
import person.daizhongde.virtue.constant.AbstractConstant;
import person.daizhongde.virtue.constant.Operator;

import person.daizhongde.authority.hibernate.pojo.JEasyUI_CBT;
import person.daizhongde.authority.hibernate.pojo.TAuthorityUser;
import person.daizhongde.migration.constant.ConstTAsiainfoEmployee;
import person.daizhongde.migration.hibernate.dao.TAsiainfoEmployeeDAO;
import person.daizhongde.migration.hibernate.pojo.TAsiainfoEmployee;
import person.daizhongde.migration.spring.service.TAsiainfoEmployeeService;
import person.daizhongde.migration.util.TreeDataUtil_Employee;

public class TAsiainfoEmployeeServiceImpl implements TAsiainfoEmployeeService {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	private TAsiainfoEmployeeDAO dataDAO;

	private TreeDataUtil_Employee mdUtil = new TreeDataUtil_Employee();
	
	public List getCBBData_SBU(String jdata){
		JSONObject jsonObject = JSONObject.fromObject(jdata);
		AbstractConstant absConstant = new ConstTAsiainfoEmployee();

		SQLAssembleQ_CBB sqlA = new SQLAssembleQ_CBB(
				absConstant.getSQLDOC(),
				((SQLNode)absConstant.getSQLDOC().getCombobox()
						.get("comboboxSBU")
					).getSQL(),
				jsonObject.getJSONObject("condition"), 
				jsonObject.getJSONObject("operator"),
				absConstant.getColumnTypes(),
				absConstant.getFront2col()
			);
		
//		return JSONArray.fromObject(dataDAO.sqlQuerylistAllByMap( sqlA.getSQL(), sqlA.getMap() )).toString();
		return dataDAO.sqlQuerylistAllByMap( sqlA.getSQL(), sqlA.getMap() );
	}
	public List getCBBData_Company(String jdata){
		JSONObject jsonObject = JSONObject.fromObject(jdata);
		AbstractConstant absConstant = new ConstTAsiainfoEmployee();
		
		SQLAssembleQ_CBB sqlA = new SQLAssembleQ_CBB(
				absConstant.getSQLDOC(),
				((SQLNode)absConstant.getSQLDOC().getCombobox()
						.get("comboboxCompany")
					).getSQL(),
				jsonObject.getJSONObject("condition"), 
				jsonObject.getJSONObject("operator"),
				absConstant.getColumnTypes(),
				absConstant.getFront2col()
			);
		
//		return JSONArray.fromObject(dataDAO.sqlQuerylistAllRetArrayByMap( sqlA.getSQL(), sqlA.getMap() )).toString();
		return dataDAO.sqlQuerylistAllByMap( sqlA.getSQL(), sqlA.getMap() );
	}
	@SuppressWarnings("unchecked")
	public String getData_JEasyUI_CBT_Async( Integer moduleid, boolean WithRoot ){
		if(WithRoot)
		{
			return getData_JEasyUI_CBT_AsyncWithRoot(moduleid);
		}
		else
		{
			return getData_JEasyUI_CBT_AsyncNoRoot(moduleid);
		}
	}
	
	
	public String getData_JEasyUI_CBT_AsyncWithRoot( Integer moduleid ){
//		log.debug("service....getData_JEasyUI_CBT_AsyncWithRoot...........");
		List dataList = new ArrayList();//Root's children

		List<TAsiainfoEmployee> rootModules = dataDAO.findAllBoss();
		
//		List moduleList = dataDAO.findChildren( moduleid, false,1 );//module's children(POJO)
//		List moduleList = dataDAO.findChildrenNoRecursive( moduleid );//module's children(POJO)
		
//		log.debug("Children.size():"+moduleList.size());
		
		mdUtil.assembleData_JEasyUI_CBT_Async( dataList, rootModules );
		
//		JEasyUI_CBT mJEU = new JEasyUI_CBT();
//		
//		mJEU.setId(rootModule.getNIid());
//		mJEU.setText(rootModule.getCIname());
//		
//		if(dataList.size()==0){
//			mJEU.setState("closed");
//		}else{
//			mJEU.setState( "open");
//		}
//		mJEU.setChildren(dataList);
		
		JSONArray jsonArray = JSONArray.fromObject(dataList);
//		log.debug("jsonArray:"+jsonArray.toString());		
		return jsonArray.toString();
	}
	
	/**
	 * Usually, It would not use this method
	 * @param moduleid
	 * @return
	 */
	public String getData_JEasyUI_CBT_AsyncNoRoot( Integer moduleid ) {
//		log.debug("service....getData_JEasyUI_CBT_AsyncNoRoot...........");
		List dataList = new ArrayList();//Root's children
		
//		List moduleList = dataDAO.findChildren( moduleid, false );//module's children
		
		List moduleList = dataDAO.findChildrenNoRecursive( moduleid );//module's children
//		System.out.println("total module count:"+dataList.size());
		
//		log.debug("mList.size():"+dataList.size());
		
		mdUtil.assembleData_JEasyUI_CBT_Async( dataList, moduleList );
		
		JSONArray jsonArray = JSONArray.fromObject(dataList);
//		log.debug("jsonArray:"+jsonArray.toString());		
		return jsonArray.toString();
	}
	@Override
	public long getTotal(SQLAssembleQ sqlA) {
//		Object o= dataDAO.sqlQueryfindaValueByMap( sqlA.getCountSQL(), sqlA.getMap() );
//		System.out.println("o:"+o);
		return Long.valueOf(
				dataDAO.sqlQueryfindaValueByMap( sqlA.getCountSQL(), sqlA.getMap() ).toString()
			);
	}
	
	@Override
	public List getRowsInMap(SQLAssembleQ sqlA) {
		return dataDAO.sqlQuerylistAllByMap( sqlA.getSQL(), sqlA.getMap() );//使用native数据量小
	}
	
	@Override
	public List getRowsInMap(SQLAssembleQ sqlA, int offset, int pageSize) {
		return dataDAO.sqlQueryfindByPageByMap(sqlA.getSQL(), sqlA.getMap(), 
				offset, pageSize);//使用native数据量小
	}

	@Override
	public long getTotal(HQLAssembleQ hqlA) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List getRowsInMap(HQLAssembleQ hqlA) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List getRowsInMap(HQLAssembleQ sqlA, int offset, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List getRowsInArray(SQLAssembleQ sqlA) {
		return dataDAO.sqlQuerylistAllRetArrayByMap(sqlA.getSQL(), sqlA.getMap());//使用native数据量小
	}
	@Override
	public List getRowsInArray(SQLAssembleQ sqlA, int offset,
			int pageSize) {
		return dataDAO.sqlQueryfindRetArrayByPageByMap(sqlA.getSQL(), sqlA.getMap(), 
				offset, pageSize);//使用native数据量小
	}
	@Override
	public int add(String jdata) {
		JSONObject jsonObject = JSONObject.fromObject(jdata);
		AbstractConstant absConstant = new ConstTAsiainfoEmployee();

		SQLAssembleC sqlA = new SQLAssembleC(
				absConstant.getTableName(),
				jsonObject.getJSONObject("data"),
				absConstant.getColumnTypes(),
				absConstant.getFront2col()
				);
		
		return dataDAO.sqlQueryExeUByMap(sqlA.getSQL(), sqlA.getMap());
	}
	@Override
	public int add(String jdata, TAuthorityUser user ) {
		JSONObject jsonObject = JSONObject.fromObject(jdata);
		AbstractConstant absConstant = new ConstTAsiainfoEmployee();

		Map data = jsonObject.getJSONObject("data");
		data.put("author", user.getCUlogname() );
		
		SQLAssembleC sqlA = new SQLAssembleC(
				absConstant.getTableName(),
				data,
				absConstant.getColumnTypes(),
				absConstant.getFront2col()
				);
		
		return dataDAO.sqlQueryExeUByMap(sqlA.getSQL(), sqlA.getMap());
	}
	public int add(Map data) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int addRetId(String jdata) {
		JSONObject jsonObject = JSONObject.fromObject(jdata);
		AbstractConstant absConstant = new ConstTAsiainfoEmployee();

		SQLAssembleC sqlA = new SQLAssembleC(
				absConstant.getTableName(),
				jsonObject.getJSONObject("data"),
				absConstant.getColumnTypes(),
				absConstant.getFront2col()
				);
		
		return dataDAO.sqlQueryExeUByMap(sqlA.getSQL(), sqlA.getMap());
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.authority.spring.service.impl.TAsiainfoEmployeeService#addWithId(java.lang.String)
	 */
	@Override
	public int addWithId( String jdata ){
		JSONObject jsonObject = JSONObject.fromObject(jdata);
		AbstractConstant absConstant = new ConstTAsiainfoEmployee();

		SQLAssembleC sqlA = new SQLAssembleC(
				absConstant.getTableName(),
				jsonObject.getJSONObject("data"),
				absConstant.getColumnTypes(),
				absConstant.getFront2col()
				);
		
		return dataDAO.sqlQueryExeUByMap(sqlA.getSQL(), sqlA.getMap());
	}
	@Override
	public int addWithIdRetId(String jdata) {
		JSONObject jsonObject = JSONObject.fromObject(jdata);
		AbstractConstant absConstant = new ConstTAsiainfoEmployee();
		
		SQLAssembleC sqlA = new SQLAssembleC(
				absConstant.getTableName(),
				jsonObject.getJSONObject("data"),
				absConstant.getColumnTypes(),
				absConstant.getFront2col()
				);
		
		dataDAO.sqlQueryExeUByMap(sqlA.getSQL(), sqlA.getMap());
		
//		return (Integer)sqlA.getMap().get(
//					absConstant.getBack2front().get("NLid")
//				);
		/*The below Code is dependent on front field, 
		 * but sometimes It's compatibility is better then the top Code  */
		return (Integer)sqlA.getMap().get("id");
	}
	
	@Override
	public void addBySavePOJO(String jdata) {
		JSONObject jsonObject = JSONObject.fromObject(jdata);
		AbstractConstant absConstant = new ConstTAsiainfoEmployee();

		SQLAssembleC sqlA = new SQLAssembleC(
				absConstant.getTableName(),
				jsonObject.getJSONObject("data"),
				absConstant.getColumnTypes(),
				absConstant.getFront2col()
				);
		
		TAsiainfoEmployee pojo = new TAsiainfoEmployee();
		Map map =sqlA.getMap();
		for(int i=0, j=sqlA.getMap().size(); i<j; i++){
//			pojo.setNLid((Short)map.get("id"));
//			pojo.setCLname(String.valueOf(map.get("name")));
		}
		dataDAO.save(pojo);
		
	}
	public void addBySavePOJO2( Object pojo ){
		
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.authority.spring.service.impl.TAsiainfoEmployeeService#modify(java.lang.String)
	 */
	@Override
	public int modify( String jdata ){
		JSONObject jsonObject = JSONObject.fromObject(jdata);
		AbstractConstant absConstant = new ConstTAsiainfoEmployee();

		SQLAssembleU sqlA = new SQLAssembleU(
				absConstant.getSQLDOC(),
				absConstant.getTableName(),
				jsonObject.getJSONObject("data"),
				jsonObject.getJSONObject("algorithm"),
				jsonObject.getJSONObject("condition"),
				jsonObject.getJSONObject("operator"),
				absConstant.getColumnTypes(),
				absConstant.getFront2col() );
		
		return dataDAO.sqlQueryExeUByMap(sqlA.getSQL(), sqlA.getMap());
	}
	@Override
	public Map browse(String jdata) {
		JSONObject jsonObject = JSONObject.fromObject(jdata);
		AbstractConstant absConstant = new ConstTAsiainfoEmployee();

		SQLAssembleR sqlA = new SQLAssembleR(
				absConstant.getSQLDOC(),
				absConstant.getRead_SQL(), 
				jsonObject.getJSONObject("condition"),
				jsonObject.getJSONObject("operator"),
				absConstant.getColumnTypes(),
				absConstant.getFront2col() );
		
		return (Map)dataDAO.sqlQuerylistAllByMap(sqlA.getSQL(), sqlA.getMap()).get(0);
	}
	@Override
	public Map browseById(int id) {
		AbstractConstant absConstant = new ConstTAsiainfoEmployee();
		String pkcolName = absConstant.getPrimaryKeyColumnName();
		
		Map cond = new HashMap(1);
		cond.put( pkcolName, id);
		Map oper = new HashMap(1); 
		oper.put( pkcolName, Operator.EQUAL);
		
		SQLAssembleR sqlA = new SQLAssembleR(
				absConstant.getSQLDOC(),
				absConstant.getRead_SQL(), 
				cond,
				oper,
				absConstant.getColumnTypes(),
				absConstant.getFront2col() );
		
		return (Map)dataDAO.sqlQuerylistAllByMap(sqlA.getSQL(), sqlA.getMap()).get(0);
	}
	@Override
	public Map browseById(String id) {
		AbstractConstant absConstant = new ConstTAsiainfoEmployee();
		String pkcolName = absConstant.getPrimaryKeyColumnName();
		
		Map cond = new HashMap(1);
		cond.put( pkcolName, id);
		Map oper = new HashMap(1); 
		oper.put( pkcolName, Operator.EQUAL);
		
		SQLAssembleR sqlA = new SQLAssembleR(
				absConstant.getSQLDOC(),
				absConstant.getRead_SQL(), 
				cond,
				oper,
				absConstant.getColumnTypes(),
				absConstant.getFront2col() );
		
		return (Map)dataDAO.sqlQuerylistAllByMap(sqlA.getSQL(), sqlA.getMap()).get(0);
	}

	public Object[] browseArray(String jdata) {
		JSONObject jsonObject = JSONObject.fromObject(jdata);
		AbstractConstant absConstant = new ConstTAsiainfoEmployee();

		SQLAssembleR sqlA = new SQLAssembleR(
				absConstant.getSQLDOC(),
				absConstant.getRead_SQL(), 
				jsonObject.getJSONObject("condition"),
				jsonObject.getJSONObject("operator"),
				absConstant.getColumnTypes(),
				absConstant.getFront2col() );
		
		return (Object[])dataDAO.sqlQuerylistAllRetArrayByMap( sqlA.getSQL(), sqlA.getMap() ).get(0);
	}
	
	public TAsiainfoEmployee browsePOJO(String jdata) {
		JSONObject jsonObject = JSONObject.fromObject(jdata);
		AbstractConstant absConstant = new ConstTAsiainfoEmployee();

		HQLAssembleR hqlA = new HQLAssembleR(
				absConstant.getSQLDOC(),
				absConstant.getRead_SQL(), 
				jsonObject.getJSONObject("condition"),
				jsonObject.getJSONObject("operator"),
				absConstant.getColumnTypes(),
				absConstant.getFront2back() );
		
		return (TAsiainfoEmployee)dataDAO.listAllByMap( "from TAsiainfoEmployee t1 where "+hqlA.getWhereBackHQL(), hqlA.getMap() ).get(0);
	}
	public TAsiainfoEmployee browsePOJOById(long id) {
//		return dataDAO.findById((short)id);
		return null;
	}
	public TAsiainfoEmployee browsePOJOById(String id) {
//		return dataDAO.findById((short)id);
		return null;
	}
	/* (non-Javadoc)
	 * @see person.daizhongde.authority.spring.service.impl.TAsiainfoEmployeeService#delete(java.lang.String)
	 */
	@Override
	public int delete( String jdata ){
		JSONObject jsonObject = JSONObject.fromObject(jdata);
		AbstractConstant absConstant = new ConstTAsiainfoEmployee();

		SQLAssembleD sqlA = new SQLAssembleD(
				absConstant.getSQLDOC(),
				absConstant.getTableName(), 
				jsonObject.getJSONObject("condition"),
				jsonObject.getJSONObject("operator"),
				absConstant.getColumnTypes(),
				absConstant.getFront2col() );
		
		return dataDAO.sqlQueryExeUByMap(sqlA.getSQL(), sqlA.getMap());
	}
	@Override
	public int deleteNP(String jdata) {
		return this.modify(jdata);
	}

	public void setDataDAO(TAsiainfoEmployeeDAO dataDAO) {
		this.dataDAO = dataDAO;
	}

	public static TAsiainfoEmployeeService getFromApplicationContext(
			ApplicationContext ctx) {
		return (TAsiainfoEmployeeService) ctx.getBean("TAsiainfoEmployeeService");
	}


}
