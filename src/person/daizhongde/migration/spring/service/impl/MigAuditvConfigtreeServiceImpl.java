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
import person.daizhongde.authority.hibernate.pojo.TAuthorityUser;
import person.daizhongde.migration.constant.ConstMigAuditvConfigtree;
import person.daizhongde.migration.exception.BusinessException;
import person.daizhongde.migration.hibernate.dao.MigAuditvConfigDAO;
import person.daizhongde.migration.hibernate.dao.MigAuditvConfigtreeDAO;
import person.daizhongde.migration.hibernate.pojo.MigAuditvConfigtree;
import person.daizhongde.migration.spring.service.MigAuditvConfigtreeService;
import person.daizhongde.migration.util.TreeDataUtil_AuditvConfigTree;

public class MigAuditvConfigtreeServiceImpl implements MigAuditvConfigtreeService {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	private MigAuditvConfigtreeDAO dataDAO;
	private MigAuditvConfigDAO configDAO;
	private TreeDataUtil_AuditvConfigTree mdUtil = new TreeDataUtil_AuditvConfigTree();
	
	public Object getData_JEasyUI_Tree_Async( Integer moduleid, boolean WithRoot ){
		if(WithRoot)
		{
			return getData_JEasyUI_Tree_AsyncWithRoot(moduleid);
		}
		else
		{
			return getData_JEasyUI_Tree_AsyncNoRoot(moduleid);
		}
	}
	
	public Object getData_JEasyUI_Tree_AsyncWithRoot(Integer moduleid){
//		log.debug("service....All node's state is close...getData_JEasyUI_Tree_Async...........");

		List dataList = new ArrayList();//module's children(Front Type )
		
		MigAuditvConfigtree rootModule = (MigAuditvConfigtree)dataDAO.findById(moduleid);
		
//		List moduleList = dataDAO.findChildren( rootModule.getNIid(), false );//module's children(POJO)
		
		List moduleList = dataDAO.findChildrenNoRecursive( rootModule.getId() );//module's children(POJO)
//		log.debug("Children.size():"+moduleList.size());
		mdUtil.assembleData_JEasyUI_Tree_Async( dataList, moduleList );
		
		JEasyUI_Tree mJEU = new JEasyUI_Tree();
		
		mJEU.setId(rootModule.getId());
		mJEU.setText(rootModule.getName());
		
		if(dataList.size()==0){
			mJEU.setState("closed");
		}else{
			mJEU.setState( "open");
		}
		
		LinkedHashMap attr1 = new LinkedHashMap();
		attr1.put("isleaf", rootModule.getIsleaf() );
		attr1.put("parent", "" );
		attr1.put("remark", rootModule.getRemark()==null?"":rootModule.getRemark() );

		mJEU.setAttributes(attr1);
		mJEU.setChildren(dataList);
		
		JSONArray jsonArray = JSONArray.fromObject(mJEU);
//		log.debug("jsonArray:"+jsonArray.toString());
//		return jsonArray.toString();
		return jsonArray;
	}
	
	public Object getData_JEasyUI_Tree_AsyncNoRoot( Integer moduleid ){
//		log.debug("service....lazyload, Async tree...getData_JEasyUI_Tree_Async...........");
		List dataList = new ArrayList();//Root's children
		//findChildrenNoRecursive
//		List moduleList = dataDAO.findChildren( moduleid, false );//module's children
		List moduleList = dataDAO.findChildrenNoRecursive( moduleid );//module's children
		
		mdUtil.assembleData_JEasyUI_Tree_Async( dataList, moduleList );
		
//		JSONArray jsonArray = JSONArray.fromObject(dataList);
//		log.debug("jsonArray:"+jsonArray.toString());		
//		return jsonArray.toString();
		return dataList;
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
		AbstractConstant absConstant = new ConstMigAuditvConfigtree();

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
		AbstractConstant absConstant = new ConstMigAuditvConfigtree();

		Map data = jsonObject.getJSONObject("data");
//		data.put("author", user.getCUlogname() );
		if( !data.containsKey("content") ){//add fold
			SQLAssembleC sqlA = new SQLAssembleC(
					absConstant.getTableName(),
					data,
					absConstant.getColumnTypes(),
					absConstant.getFront2col()
					);
			
			return dataDAO.sqlQueryExeUByMap(sqlA.getSQL(), sqlA.getMap());
		}
		//add config
		int content = new Integer(data.get("content").toString() );
		
		List<MigAuditvConfigtree> list = dataDAO.findByContent2(content);
		if(list.size()==0){
			SQLAssembleC sqlA = new SQLAssembleC(
					absConstant.getTableName(),
					data,
					absConstant.getColumnTypes(),
					absConstant.getFront2col()
					);
			
			return dataDAO.sqlQueryExeUByMap(sqlA.getSQL(), sqlA.getMap());
		}else if(list.size()==1){
			int id = list.get(0).getId();
			String parent = list.get(0).getParent();
			String newParent = data.get("parent").toString();
			if(!new String("|"+parent+"|").contains("|"+data.get("parent")+"|") ){
				String updateSQL = "UPDATE tool.`mig_auditv_configtree` SET parent = CONCAT(parent,'|',"+newParent+") WHERE id="+id;
				return dataDAO.sqlQueryExeU( updateSQL );
			}else{
				throw new BusinessException("The config has been in the fold!");
			}
		}else{
			throw new BusinessException("config rel fatal error!");
		}
	}
	
	public int add(Map data) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int addRetId(String jdata) {
		JSONObject jsonObject = JSONObject.fromObject(jdata);
		AbstractConstant absConstant = new ConstMigAuditvConfigtree();

		SQLAssembleC sqlA = new SQLAssembleC(
				absConstant.getTableName(),
				jsonObject.getJSONObject("data"),
				absConstant.getColumnTypes(),
				absConstant.getFront2col()
				);
		
		return dataDAO.sqlQueryExeUByMap(sqlA.getSQL(), sqlA.getMap());
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.authority.spring.service.impl.MigAuditvConfigtreeService#addWithId(java.lang.String)
	 */
	@Override
	public int addWithId( String jdata ){
		JSONObject jsonObject = JSONObject.fromObject(jdata);
		AbstractConstant absConstant = new ConstMigAuditvConfigtree();

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
		AbstractConstant absConstant = new ConstMigAuditvConfigtree();
		
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
		AbstractConstant absConstant = new ConstMigAuditvConfigtree();

		SQLAssembleC sqlA = new SQLAssembleC(
				absConstant.getTableName(),
				jsonObject.getJSONObject("data"),
				absConstant.getColumnTypes(),
				absConstant.getFront2col()
				);
		
		MigAuditvConfigtree pojo = new MigAuditvConfigtree();
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
	 * @see person.daizhongde.authority.spring.service.impl.MigAuditvConfigtreeService#modify(java.lang.String)
	 */
	@Override
	public int modify( String jdata ){
		JSONObject jsonObject = JSONObject.fromObject(jdata);
		AbstractConstant absConstant = new ConstMigAuditvConfigtree();

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
	/** U  **/
	public int modifyContent2NULL( String jdata ){
		JSONObject jsonObject = JSONObject.fromObject(jdata);
		AbstractConstant absConstant = new ConstMigAuditvConfigtree();

		SQLAssembleU sqlA = new SQLAssembleU(
				absConstant.getSQLDOC(),
				absConstant.getTableName(),
				jsonObject.getJSONObject("data"),
				jsonObject.getJSONObject("algorithm"),
				jsonObject.getJSONObject("condition"),
				jsonObject.getJSONObject("operator"),
				absConstant.getColumnTypes(),
				absConstant.getFront2col() );
		
		String updateSQL = "UPDATE tool.`mig_auditv_configtree` SET content = null  WHERE id = :id ";
		return dataDAO.sqlQueryExeUByMap( updateSQL, sqlA.getMap());
	}
	@Override
	public Map browse(String jdata) {
		JSONObject jsonObject = JSONObject.fromObject(jdata);
		AbstractConstant absConstant = new ConstMigAuditvConfigtree();

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
		AbstractConstant absConstant = new ConstMigAuditvConfigtree();
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
		AbstractConstant absConstant = new ConstMigAuditvConfigtree();
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
		AbstractConstant absConstant = new ConstMigAuditvConfigtree();

		SQLAssembleR sqlA = new SQLAssembleR(
				absConstant.getSQLDOC(),
				absConstant.getRead_SQL(), 
				jsonObject.getJSONObject("condition"),
				jsonObject.getJSONObject("operator"),
				absConstant.getColumnTypes(),
				absConstant.getFront2col() );
		
		return (Object[])dataDAO.sqlQuerylistAllRetArrayByMap( sqlA.getSQL(), sqlA.getMap() ).get(0);
	}
	
	public MigAuditvConfigtree browsePOJO(String jdata) {
		JSONObject jsonObject = JSONObject.fromObject(jdata);
		AbstractConstant absConstant = new ConstMigAuditvConfigtree();

		HQLAssembleR hqlA = new HQLAssembleR(
				absConstant.getSQLDOC(),
				absConstant.getRead_SQL(), 
				jsonObject.getJSONObject("condition"),
				jsonObject.getJSONObject("operator"),
				absConstant.getColumnTypes(),
				absConstant.getFront2back() );
		
		return (MigAuditvConfigtree)dataDAO.listAllByMap( "from MigAuditvConfigtree t1 where "+hqlA.getWhereBackHQL(), hqlA.getMap() ).get(0);
	}
	public MigAuditvConfigtree browsePOJOById(int id) {
		return dataDAO.findById( id);
//		return null;
	}
	public MigAuditvConfigtree browsePOJOById(String id) {
//		return dataDAO.findById((short)id);
		return null;
	}
	/* (non-Javadoc)
	 * @see person.daizhongde.authority.spring.service.impl.MigAuditvConfigtreeService#delete(java.lang.String)
	 */
	@Override
	public int delete( String jdata ){
		JSONObject jsonObject = JSONObject.fromObject(jdata);
		AbstractConstant absConstant = new ConstMigAuditvConfigtree();

		JSONObject cond = jsonObject.getJSONObject("condition"); 
		int id = cond.getInt("id");
		
		MigAuditvConfigtree pojo = this.browsePOJOById(id);
		int isleaf = pojo.getIsleaf();
		String parent = pojo.getParent();
		if(isleaf==1){
			int content = pojo.getContent();
			/*1:judge link, count >1 exe2,else exe3
			 *2:update parent , end.
			 *3:delete config
			 *4:delete rel  */
			if( parent.indexOf("|") != -1 ){
				int parentId = cond.getInt("parent");//这里的parent是前台传来的某一个node的parentId
				return dataDAO.updateParentWhenRemoveLink(parentId, id);
				
			}else{
				configDAO.deleteById(content);
				
				SQLAssembleD sqlA = new SQLAssembleD(
						absConstant.getSQLDOC(),
						absConstant.getTableName(), 
						jsonObject.getJSONObject("condition"),
						jsonObject.getJSONObject("operator"),
						absConstant.getColumnTypes(),
						absConstant.getFront2col() );
				
				return dataDAO.sqlQueryExeUByMap(sqlA.getSQL(), sqlA.getMap());
			}			
		}else{
			/* 1:judge children, count>0 return; else exe2
			 * delete rel */
			List<MigAuditvConfigtree> list = dataDAO.findChildrenNoRecursive(id);
			if(list.size()>0){
				throw new BusinessException("This fold is not empty, can't be delete!");
			}
			
			SQLAssembleD sqlA = new SQLAssembleD(
					absConstant.getSQLDOC(),
					absConstant.getTableName(), 
					jsonObject.getJSONObject("condition"),
					jsonObject.getJSONObject("operator"),
					absConstant.getColumnTypes(),
					absConstant.getFront2col() );
			
			return dataDAO.sqlQueryExeUByMap(sqlA.getSQL(), sqlA.getMap());
		}
	}
	@Override
	public int deleteNP(String jdata) {
		return this.modify(jdata);
	}

	public void setDataDAO(MigAuditvConfigtreeDAO dataDAO) {
		this.dataDAO = dataDAO;
	}

	public void setConfigDAO(MigAuditvConfigDAO configDAO) {
		this.configDAO = configDAO;
	}

	public static MigAuditvConfigtreeService getFromApplicationContext(
			ApplicationContext ctx) {
		return (MigAuditvConfigtreeService) ctx.getBean("migAuditvConfigtreeService");
	}


}
