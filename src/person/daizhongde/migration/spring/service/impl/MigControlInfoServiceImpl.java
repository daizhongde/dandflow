package person.daizhongde.migration.spring.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
import person.daizhongde.virtue.assemble.sql.SQLAssembleR;
import person.daizhongde.virtue.assemble.sql.SQLAssembleU;
import person.daizhongde.virtue.constant.AbstractConstant;
import person.daizhongde.virtue.constant.Operator;

import person.daizhongde.authority.hibernate.pojo.JEasyUI_Tree;
import person.daizhongde.authority.hibernate.pojo.JEasyUI_Tree2;
import person.daizhongde.authority.hibernate.pojo.JEasyUI_Tree2_Leaf;
import person.daizhongde.authority.hibernate.pojo.JEasyUI_Tree_Leaf;
import person.daizhongde.authority.hibernate.pojo.TAuthorityLevel;
import person.daizhongde.authority.hibernate.pojo.TAuthorityRole;
import person.daizhongde.authority.hibernate.pojo.TAuthorityUser;
import person.daizhongde.migration.constant.ConstMigControlInfo;
import person.daizhongde.migration.hibernate.dao.MigControlInfoDAO;
import person.daizhongde.migration.hibernate.dao.MigTaskInfoDAO;
import person.daizhongde.migration.hibernate.pojo.MigConfigConnection;
import person.daizhongde.migration.hibernate.pojo.MigControlInfo;
import person.daizhongde.migration.hibernate.pojo.MigControlTemplate;
import person.daizhongde.migration.hibernate.pojo.MigTaskInfo;
import person.daizhongde.migration.spring.service.BusiMemoryService;
import person.daizhongde.migration.spring.service.MigControlInfoService;

public class MigControlInfoServiceImpl implements MigControlInfoService {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	private MigControlInfoDAO dataDAO;

	private BusiMemoryService busiMemoryService;
	    
	public List<MigControlInfo> getCtlInfoAll(){			
		List ls=dataDAO.findAll();
		log.debug("get all control list success");
		return ls;
	}
	
	public List getData_JEasyUI_Tree(){
		return busiMemoryService.getControlTreeData();
	}
	
	
	@SuppressWarnings("unchecked")
	public List getData_JEasyUI_Tree2(){
//		log.debug("service....AllNode...getData_JEasyUI_Tree...........");
		List dataList = new ArrayList();//use to save menu JSON data if it has root node when it have mitiply root node
//		List dataList2 = new ArrayList();//Root's children
				
//		List<TAuthorityRole> mList = dataDAO.findAllWithOrder();
//		List<TAuthorityRole> level0ModuleList = new ArrayList<TAuthorityRole>();
//		List<JEasyUI_Tree> level1ModuleList = new ArrayList<JEasyUI_Tree>();
		JEasyUI_Tree2 con = new JEasyUI_Tree2();
		con.setId("1");
		con.setText("Controls");
		con.setState("closed");
		con.setChecked(true);
		
		JEasyUI_Tree2 taskPkg = new JEasyUI_Tree2();
		taskPkg.setId("2");
		taskPkg.setText("Others");
		taskPkg.setState("closed");
		taskPkg.setChecked(true);
		
		
//		List<JEasyUI_Tree_Leaf> level2ModuleList = new ArrayList<JEasyUI_Tree_Leaf>();
		List<MigControlInfo> level2ControlList = dataDAO.findAll();
//		List<MigTaskInfo> level2TaskPkgList = taskDAO.findByTaskNode("F");
		
//		System.out.println("total module count:"+mList.size());
		//如果没有控件就返回空
		if( level2ControlList.size()== 0 ){
//			return "[]";
			return new ArrayList();
		}
		
		//组装控件树枝数据
		List<JEasyUI_Tree2_Leaf> conList = new ArrayList<JEasyUI_Tree2_Leaf>();
		for( MigControlInfo e : level2ControlList ){
			
			JEasyUI_Tree2_Leaf mJEU_L = new JEasyUI_Tree2_Leaf();
			LinkedHashMap attr2 = new LinkedHashMap();
			
			mJEU_L.setId( e.getControlId() );
			mJEU_L.setText( e.getControlName() );
			mJEU_L.setState( "open" );
			mJEU_L.setIconCls( e.getIconCls() );
			
			mJEU_L.setChecked( true );
			
			List<MigControlTemplate> templates = busiMemoryService.getControlMap().get(  e.getControlId() );
			
			attr2.put("note", e.getControlMark() );
			attr2.put("type", "T");
			attr2.put("tpl", templates );

			mJEU_L.setAttributes(attr2);
			conList.add(mJEU_L);
		}
		con.setChildren(conList);
		
		//组装作业树枝数据
		List<JEasyUI_Tree2_Leaf> taskPkgList = new ArrayList();
//		for( MigTaskInfo e : level2TaskPkgList )
//		{
			JEasyUI_Tree2_Leaf mJEU_L = new JEasyUI_Tree2_Leaf();
			LinkedHashMap attr2 = new LinkedHashMap();
			
			mJEU_L.setId( "21" );
			mJEU_L.setText( "作业" );
			mJEU_L.setState( "open" );
			mJEU_L.setIconCls( "icon-job" );
			
			mJEU_L.setChecked( false );
			
			attr2.put("note", "作业" );
			attr2.put("type", "J");
			mJEU_L.setAttributes(attr2);
			taskPkgList.add(mJEU_L);
//		}
		taskPkg.setChildren(taskPkgList);
		
		dataList.add(con);
		dataList.add(taskPkg);
		
		return dataList;
//		JSONArray jsonArray = JSONArray.fromObject(dataList);
//		log.debug("jsonArray:"+jsonArray.toString());
//		return jsonArray.toString();
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
		AbstractConstant absConstant = new ConstMigControlInfo();

		SQLAssembleC sqlA = new SQLAssembleC(
				absConstant.getTableName(),
				jsonObject.getJSONObject("data"),
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
		AbstractConstant absConstant = new ConstMigControlInfo();

		SQLAssembleC sqlA = new SQLAssembleC(
				absConstant.getTableName(),
				jsonObject.getJSONObject("data"),
				absConstant.getColumnTypes(),
				absConstant.getFront2col()
				);
		
		return dataDAO.sqlQueryExeUByMap(sqlA.getSQL(), sqlA.getMap());
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.authority.spring.service.impl.MigControlInfoService#addWithId(java.lang.String)
	 */
	@Override
	public int addWithId( String jdata ){
		JSONObject jsonObject = JSONObject.fromObject(jdata);
		AbstractConstant absConstant = new ConstMigControlInfo();

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
		AbstractConstant absConstant = new ConstMigControlInfo();
		
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
		AbstractConstant absConstant = new ConstMigControlInfo();

		SQLAssembleC sqlA = new SQLAssembleC(
				absConstant.getTableName(),
				jsonObject.getJSONObject("data"),
				absConstant.getColumnTypes(),
				absConstant.getFront2col()
				);
		
		MigControlInfo pojo = new MigControlInfo();
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
	 * @see person.daizhongde.authority.spring.service.impl.MigControlInfoService#modify(java.lang.String)
	 */
	@Override
	public int modify( String jdata ){
		JSONObject jsonObject = JSONObject.fromObject(jdata);
		AbstractConstant absConstant = new ConstMigControlInfo();

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
		AbstractConstant absConstant = new ConstMigControlInfo();

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
		AbstractConstant absConstant = new ConstMigControlInfo();
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
		AbstractConstant absConstant = new ConstMigControlInfo();
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
		AbstractConstant absConstant = new ConstMigControlInfo();

		SQLAssembleR sqlA = new SQLAssembleR(
				absConstant.getSQLDOC(),
				absConstant.getRead_SQL(), 
				jsonObject.getJSONObject("condition"),
				jsonObject.getJSONObject("operator"),
				absConstant.getColumnTypes(),
				absConstant.getFront2col() );
		
		return (Object[])dataDAO.sqlQuerylistAllRetArrayByMap( sqlA.getSQL(), sqlA.getMap() ).get(0);
	}
	
	public MigControlInfo browsePOJO(String jdata) {
		JSONObject jsonObject = JSONObject.fromObject(jdata);
		AbstractConstant absConstant = new ConstMigControlInfo();

		HQLAssembleR hqlA = new HQLAssembleR(
				absConstant.getSQLDOC(),
				absConstant.getRead_SQL(), 
				jsonObject.getJSONObject("condition"),
				jsonObject.getJSONObject("operator"),
				absConstant.getColumnTypes(),
				absConstant.getFront2back() );
		
		return (MigControlInfo)dataDAO.listAllByMap( "from MigControlInfo t1 where "+hqlA.getWhereBackHQL(), hqlA.getMap() ).get(0);
	}
	public MigControlInfo browsePOJOById(int id) {
//		return dataDAO.findById((short)id);
		return null;
	}
	public MigControlInfo browsePOJOById(String id) {
//		return dataDAO.findById((short)id);
		return null;
	}
	/* (non-Javadoc)
	 * @see person.daizhongde.authority.spring.service.impl.MigControlInfoService#delete(java.lang.String)
	 */
	@Override
	public int delete( String jdata ){
		JSONObject jsonObject = JSONObject.fromObject(jdata);
		AbstractConstant absConstant = new ConstMigControlInfo();

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

	public void setDataDAO(MigControlInfoDAO dataDAO) {
		this.dataDAO = dataDAO;
	}

	public void setBusiMemoryService(BusiMemoryService busiMemoryService) {
		this.busiMemoryService = busiMemoryService;
	}

	public static MigControlInfoService getFromApplicationContext(
			ApplicationContext ctx) {
		return (MigControlInfoService) ctx.getBean("migControlInfoService");
	}


}
