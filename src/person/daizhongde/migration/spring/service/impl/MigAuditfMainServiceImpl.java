package person.daizhongde.migration.spring.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

import person.daizhongde.authority.hibernate.pojo.JEasyUI_Tree2;
import person.daizhongde.authority.hibernate.pojo.JEasyUI_Tree2_Leaf;
import person.daizhongde.authority.hibernate.pojo.TAuthorityUser;
import person.daizhongde.migration.constant.ConstMigAuditfMain;
import person.daizhongde.migration.hibernate.dao.MigAuditfMainDAO;
import person.daizhongde.migration.hibernate.dao.MigBusiDomainDAO;
import person.daizhongde.migration.hibernate.pojo.MigAuditfMain;
import person.daizhongde.migration.hibernate.pojo.MigBusiDomain;
import person.daizhongde.migration.spring.service.MigAuditfMainService;

public class MigAuditfMainServiceImpl implements MigAuditfMainService {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	private MigAuditfMainDAO dataDAO;
	private MigBusiDomainDAO domainDAO;
	
	@SuppressWarnings("unchecked")
	public List getData_JEasyUI_Tree(){

		List roots = new ArrayList();//use to save menu JSON data if it has root node when it have mitiply root node

		List<MigBusiDomain> level0ModuleList = domainDAO.findAll("name");
//		List<MigAuditfMain> level1ModuleList = dataDAO.findAll("auditName");
		
		AbstractConstant absConstant = new ConstMigAuditfMain();
		
		List<Map> sort = new ArrayList<Map>();
		Map map = new HashMap();
		map.put( "faudit_name", "asc" );
		sort.add(map);
		
		SQLAssembleQ sqlA = new SQLAssembleQ(
				absConstant.getSQLDOC(),
				absConstant.getSQLDOC().getQuery().get("queryAuditfConfig").getSQL(),
				null, 
				null,
				absConstant.getColumnTypes(),
				absConstant.getFront2col(),
				sort
			);
		
		List<Map> level1ModuleList = dataDAO.sqlQuerylistAllByMap(sqlA.getSQL(), sqlA.getMap() );//使用native数据量小
		
		if( level1ModuleList.size()== 0 ){
			return new ArrayList();
		}
		
		for(int i=0, j=level0ModuleList.size(); i<j; i++ )
		{
			List dataList2 = new ArrayList();
			/** 如果有特殊情况：有多个root节点(level=0的节点),对这里的方法 按level0ModuleList循环即可  **/
			this.assembleData_JEasyUI_Tree2(dataList2, level0ModuleList.get(i).getId(), 1,	
					level1ModuleList );
			
			JEasyUI_Tree2 mJEU = new JEasyUI_Tree2();
			
			mJEU.setId( level0ModuleList.get(i).getName() );
			mJEU.setText( level0ModuleList.get(i).getName() );
			mJEU.setChecked( false );
			mJEU.setState("closed");//机构树太大，关闭所有市公司节点
			
//			LinkedHashMap attr1 = new LinkedHashMap();
//			mJEU.setAttributes(attr1);
			
			mJEU.setChildren(dataList2);

			roots.add(mJEU);
		}
		return roots;

	}

	private void assembleData_JEasyUI_Tree2(List dataList, int parentM, int level, 
			List<Map> list1 ) {
		
//		log.debug("#########assembleData_JEasyUI_Tree2###########");
		int nextLevel = level+1;
		List<Map> tempList = new ArrayList<Map>();
		switch (level) {
		case 0: break;
		case 1: tempList = list1; break;
		default:
			System.out.println("level:"+level);
			System.out.println("module level invalid!");
		}
		for (int i = 0, j = tempList.size(); i < j; i++) {
			Map m = (Map) tempList.get(i);
			if( new Integer( m.get("domain").toString() ) == parentM  ){

					JEasyUI_Tree2_Leaf mJEU_L = new JEasyUI_Tree2_Leaf();
					
					mJEU_L.setId( m.get("faudit_id").toString() );
					mJEU_L.setText( m.get("faudit_name").toString() );
//					mJEU_L.setIconCls( "" );
					mJEU_L.setChecked( false );
					
					mJEU_L.setState( "open" );
					
					LinkedHashMap attr2 = new LinkedHashMap();
					attr2.put("sub_desc", m.get("sub_desc") );
					mJEU_L.setAttributes(attr2);
					
					dataList.add(mJEU_L);
			}
		}
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
		AbstractConstant absConstant = new ConstMigAuditfMain();

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
		AbstractConstant absConstant = new ConstMigAuditfMain();

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
		AbstractConstant absConstant = new ConstMigAuditfMain();

		SQLAssembleC sqlA = new SQLAssembleC(
				absConstant.getTableName(),
				jsonObject.getJSONObject("data"),
				absConstant.getColumnTypes(),
				absConstant.getFront2col()
				);
		
		return dataDAO.sqlQueryExeUByMap(sqlA.getSQL(), sqlA.getMap());
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.authority.spring.service.impl.MigAuditfMainService#addWithId(java.lang.String)
	 */
	@Override
	public int addWithId( String jdata ){
		JSONObject jsonObject = JSONObject.fromObject(jdata);
		AbstractConstant absConstant = new ConstMigAuditfMain();

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
		AbstractConstant absConstant = new ConstMigAuditfMain();
		
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
		AbstractConstant absConstant = new ConstMigAuditfMain();

		SQLAssembleC sqlA = new SQLAssembleC(
				absConstant.getTableName(),
				jsonObject.getJSONObject("data"),
				absConstant.getColumnTypes(),
				absConstant.getFront2col()
				);
		
		MigAuditfMain pojo = new MigAuditfMain();
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
	 * @see person.daizhongde.authority.spring.service.impl.MigAuditfMainService#modify(java.lang.String)
	 */
	@Override
	public int modify( String jdata ){
		JSONObject jsonObject = JSONObject.fromObject(jdata);
		AbstractConstant absConstant = new ConstMigAuditfMain();

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
		AbstractConstant absConstant = new ConstMigAuditfMain();

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
		AbstractConstant absConstant = new ConstMigAuditfMain();
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
		AbstractConstant absConstant = new ConstMigAuditfMain();
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
		AbstractConstant absConstant = new ConstMigAuditfMain();

		SQLAssembleR sqlA = new SQLAssembleR(
				absConstant.getSQLDOC(),
				absConstant.getRead_SQL(), 
				jsonObject.getJSONObject("condition"),
				jsonObject.getJSONObject("operator"),
				absConstant.getColumnTypes(),
				absConstant.getFront2col() );
		
		return (Object[])dataDAO.sqlQuerylistAllRetArrayByMap( sqlA.getSQL(), sqlA.getMap() ).get(0);
	}
	
	public MigAuditfMain browsePOJO(String jdata) {
		JSONObject jsonObject = JSONObject.fromObject(jdata);
		AbstractConstant absConstant = new ConstMigAuditfMain();

		HQLAssembleR hqlA = new HQLAssembleR(
				absConstant.getSQLDOC(),
				absConstant.getRead_SQL(), 
				jsonObject.getJSONObject("condition"),
				jsonObject.getJSONObject("operator"),
				absConstant.getColumnTypes(),
				absConstant.getFront2back() );
		
		return (MigAuditfMain)dataDAO.listAllByMap( "from MigAuditfMain t1 where "+hqlA.getWhereBackHQL(), hqlA.getMap() ).get(0);
	}
	public MigAuditfMain browsePOJOById(long id) {
//		return dataDAO.findById((short)id);
		return null;
	}
	public MigAuditfMain browsePOJOById(String id) {
//		return dataDAO.findById((short)id);
		return null;
	}
	/* (non-Javadoc)
	 * @see person.daizhongde.authority.spring.service.impl.MigAuditfMainService#delete(java.lang.String)
	 */
	@Override
	public int delete( String jdata ){
		JSONObject jsonObject = JSONObject.fromObject(jdata);
		AbstractConstant absConstant = new ConstMigAuditfMain();

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

	public void setDataDAO(MigAuditfMainDAO dataDAO) {
		this.dataDAO = dataDAO;
	}

	public MigBusiDomainDAO getDomainDAO() {
		return domainDAO;
	}

	public void setDomainDAO(MigBusiDomainDAO domainDAO) {
		this.domainDAO = domainDAO;
	}

	public static MigAuditfMainService getFromApplicationContext(
			ApplicationContext ctx) {
		return (MigAuditfMainService) ctx.getBean("migAuditfMainService");
	}


}
