package person.daizhongde.migration.spring.service.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
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
import person.daizhongde.virtue.constant.INIT;
import person.daizhongde.virtue.constant.Operator;

import person.daizhongde.authority.hibernate.pojo.JEasyUI_Tree2;
import person.daizhongde.authority.hibernate.pojo.JEasyUI_Tree2_Leaf;
import person.daizhongde.authority.hibernate.pojo.TAuthorityUser;
import person.daizhongde.migration.constant.ConstMigAuditvConfig;
import person.daizhongde.migration.hibernate.dao.MigAuditvConfigDAO;
import person.daizhongde.migration.hibernate.dao.MigBusiDomainDAO;
import person.daizhongde.migration.hibernate.pojo.MigAuditvConfig;
import person.daizhongde.migration.hibernate.pojo.MigBusiDomain;
import person.daizhongde.migration.spring.service.MigAuditvConfigService;

public class MigAuditvConfigServiceImpl implements MigAuditvConfigService {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	private MigAuditvConfigDAO dataDAO;
	private MigBusiDomainDAO domainDAO;
	
	public List getData_JEasyUI_Tree(){

		List roots = new ArrayList();//use to save menu JSON data if it has root node when it have mitiply root node

		List<MigBusiDomain> level0ModuleList = domainDAO.findAll("name");
		List<MigAuditvConfig> level1ModuleList = dataDAO.findAll("auditName");
		
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
			
			/* Because legality audit config has assigned number range to every one, and all of the number is 8 byte
			 * this tree's fold's id can be a number  
			 */
			mJEU.setId( level0ModuleList.get(i).getId().toString() );
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
			List<MigAuditvConfig> list1 ) {
		
//		log.debug("#########assembleData_JEasyUI_Tree2###########");
		int nextLevel = level+1;
		List<MigAuditvConfig> tempList = new ArrayList<MigAuditvConfig>();
		switch (level) {
		case 0: break;
		case 1: tempList = list1; break;
		default:
			System.out.println("level:"+level);
			System.out.println("module level invalid!");
		}
		for (int i = 0, j = tempList.size(); i < j; i++) {
			MigAuditvConfig m = (MigAuditvConfig) tempList.get(i);
			if( m.getDomain() == parentM  ){

					JEasyUI_Tree2_Leaf mJEU_L = new JEasyUI_Tree2_Leaf();
//					LinkedHashMap attr2 = new LinkedHashMap();
					mJEU_L.setId( m.getAuditId().toString() );
					mJEU_L.setText( m.getAuditName() );
//					mJEU_L.setIconCls( "" );
					mJEU_L.setChecked( false );
					
					mJEU_L.setState( "open" );
					
//					attr2.put("url", m.getCMpath());
//					mJEU_L.setAttributes(attr2);
					
					dataList.add(mJEU_L);
			}
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public List<MigAuditvConfig> findRowsByIdList( List<String> id_List ){
		AbstractConstant absConstant = new ConstMigAuditvConfig();

		Map condition = new HashMap();
		condition.put("audit_id", id_List );
		
		Map operator = new HashMap();
		operator.put( "audit_id", Operator.IN );
		
		SQLAssembleQ sqlA = new SQLAssembleQ(
				absConstant.getSQLDOC(),
				absConstant.getQuery_SQL(), 
				condition,
				operator,
				absConstant.getColumnTypes(),
				absConstant.getFront2col() );
		
		return dataDAO.sqlQuerylistAllByMap( sqlA.getSQL(), sqlA.getMap() );
	};
	
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
		AbstractConstant absConstant = new ConstMigAuditvConfig();

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
		AbstractConstant absConstant = new ConstMigAuditvConfig();
		Map data = jsonObject.getJSONObject("data");
		
//		data.put("author", user.getCUlogname() );
		absConstant.getPrimaryKeySequence();
//		select @@IDENTITY;
//		SELECT AUTO_INCREMENT FROM information_schema.tables  WHERE table_schema='tool' AND table_name='mig_auditv_config'; 
		
		//nextval  CURRVAL
		Integer id = Integer.valueOf(
				dataDAO.sqlQueryfindaValueByMap("select AUTO_INCREMENT FROM information_schema.tables "
						+ "WHERE table_schema='" + INIT.BUSI_schema + "' AND table_name='"+absConstant.getTableName()+"'", 
						new HashMap() ).toString()
				);
		
		Map valueMap = new HashMap();
		valueMap.put(absConstant.getPrimaryKeyColumnName(), id);
		if( !jsonObject.getJSONObject("data").containsKey( absConstant.getPrimaryKeyColumnName().toLowerCase() ) )
		{
			valueMap.put( absConstant.getPrimaryKeyColumnName(), id );
		}
		
		SQLAssembleC sqlA = new SQLAssembleC(
				absConstant.getTableName(),
				data,
				absConstant.getColumnTypes(),
				absConstant.getFront2col(),
				valueMap
			);
		
		dataDAO.sqlQueryExeUByMap(sqlA.getSQL(), sqlA.getMap());
//		String audit_id = "";
//		audit_id = dataDAO.sqlQueryfindaValue(
//				"SELECT audit_id FROM tool.mig_auditv_config WHERE audit_name ='"+data.get("audit_name").toString()+"'").toString();
		
		return id;
	}
	
	public int add(Map data) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int addRetId(String jdata) {
		JSONObject jsonObject = JSONObject.fromObject(jdata);
		AbstractConstant absConstant = new ConstMigAuditvConfig();

		SQLAssembleC sqlA = new SQLAssembleC(
				absConstant.getTableName(),
				jsonObject.getJSONObject("data"),
				absConstant.getColumnTypes(),
				absConstant.getFront2col()
				);
		
		return dataDAO.sqlQueryExeUByMap(sqlA.getSQL(), sqlA.getMap());
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.authority.spring.service.impl.MigAuditvConfigService#addWithId(java.lang.String)
	 */
	@Override
	public int addWithId( String jdata ){
		JSONObject jsonObject = JSONObject.fromObject(jdata);
		AbstractConstant absConstant = new ConstMigAuditvConfig();

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
		AbstractConstant absConstant = new ConstMigAuditvConfig();
		
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
		AbstractConstant absConstant = new ConstMigAuditvConfig();

		SQLAssembleC sqlA = new SQLAssembleC(
				absConstant.getTableName(),
				jsonObject.getJSONObject("data"),
				absConstant.getColumnTypes(),
				absConstant.getFront2col()
				);
		
		MigAuditvConfig pojo = new MigAuditvConfig();
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
	 * @see person.daizhongde.authority.spring.service.impl.MigAuditvConfigService#modify(java.lang.String)
	 */
	@Override
	public int modify( String jdata ){
		JSONObject jsonObject = JSONObject.fromObject(jdata);
		AbstractConstant absConstant = new ConstMigAuditvConfig();

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
		AbstractConstant absConstant = new ConstMigAuditvConfig();

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
		AbstractConstant absConstant = new ConstMigAuditvConfig();
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
		AbstractConstant absConstant = new ConstMigAuditvConfig();
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
		AbstractConstant absConstant = new ConstMigAuditvConfig();

		SQLAssembleR sqlA = new SQLAssembleR(
				absConstant.getSQLDOC(),
				absConstant.getRead_SQL(), 
				jsonObject.getJSONObject("condition"),
				jsonObject.getJSONObject("operator"),
				absConstant.getColumnTypes(),
				absConstant.getFront2col() );
		
		return (Object[])dataDAO.sqlQuerylistAllRetArrayByMap( sqlA.getSQL(), sqlA.getMap() ).get(0);
	}
	
	public MigAuditvConfig browsePOJO(String jdata) {
		JSONObject jsonObject = JSONObject.fromObject(jdata);
		AbstractConstant absConstant = new ConstMigAuditvConfig();

		HQLAssembleR hqlA = new HQLAssembleR(
				absConstant.getSQLDOC(),
				absConstant.getRead_SQL(), 
				jsonObject.getJSONObject("condition"),
				jsonObject.getJSONObject("operator"),
				absConstant.getColumnTypes(),
				absConstant.getFront2back() );
		
		return (MigAuditvConfig)dataDAO.listAllByMap( "from MigAuditvConfig t1 where "+hqlA.getWhereBackHQL(), hqlA.getMap() ).get(0);
	}
	public MigAuditvConfig browsePOJOById(int id) {
		return dataDAO.findById( id );
//		return null;
	}
	public MigAuditvConfig browsePOJOById(String id) {
//		return dataDAO.findById((short)id);
		return null;
	}
	/* (non-Javadoc)
	 * @see person.daizhongde.authority.spring.service.impl.MigAuditvConfigService#delete(java.lang.String)
	 */
	@Override
	public int delete( String jdata ){
		JSONObject jsonObject = JSONObject.fromObject(jdata);
		AbstractConstant absConstant = new ConstMigAuditvConfig();

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

	public void setDataDAO(MigAuditvConfigDAO dataDAO) {
		this.dataDAO = dataDAO;
	}

	public MigBusiDomainDAO getDomainDAO() {
		return domainDAO;
	}

	public void setDomainDAO(MigBusiDomainDAO domainDAO) {
		this.domainDAO = domainDAO;
	}

	public static MigAuditvConfigService getFromApplicationContext(
			ApplicationContext ctx) {
		return (MigAuditvConfigService) ctx.getBean("migAuditvConfigService");
	}


}
