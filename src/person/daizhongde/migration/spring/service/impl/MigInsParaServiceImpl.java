package person.daizhongde.migration.spring.service.impl;

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
import person.daizhongde.virtue.constant.Operator;

import person.daizhongde.migration.constant.ConstMigInsPara;
import person.daizhongde.migration.hibernate.dao.MigInsParaDAO;
import person.daizhongde.migration.hibernate.pojo.MigInsPara;
import person.daizhongde.migration.spring.service.MigInsParaService;

public class MigInsParaServiceImpl implements MigInsParaService {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	private MigInsParaDAO dataDAO;
	
	
//	public  void newJobPara(MigInsPara mjp){
//		if(mjp!=null)
//			dataDAO.save(mjp);
//		log.debug("newJobPara success");
//	}
//	public  void updateJobPara(String taskId,Map<String,String> paras){
//		if(taskId!=null && paras!=null)
//			dataDAO.updateJobPara(taskId, paras);
//		log.debug("updateJobPara success");
//	}
//	public  void deleteJobPara(String taskId,String para){
//		if(taskId!=null && para!=null)
//			dataDAO.deleteJobPara(taskId, para);
//		log.debug("deleteJobPara success");	
//	}
//	public  List<MigInsPara> findAllTaskParaByTaskId(String taskId){
//		if(taskId!=null)
//			return dataDAO.findAllTaskParaByTaskId(taskId);
//		return null;
//	}
	
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
		AbstractConstant absConstant = new ConstMigInsPara();

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
		AbstractConstant absConstant = new ConstMigInsPara();

		SQLAssembleC sqlA = new SQLAssembleC(
				absConstant.getTableName(),
				jsonObject.getJSONObject("data"),
				absConstant.getColumnTypes(),
				absConstant.getFront2col()
				);
		
		return dataDAO.sqlQueryExeUByMap(sqlA.getSQL(), sqlA.getMap());
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.authority.spring.service.impl.MigInsParaService#addWithId(java.lang.String)
	 */
	@Override
	public int addWithId( String jdata ){
		JSONObject jsonObject = JSONObject.fromObject(jdata);
		AbstractConstant absConstant = new ConstMigInsPara();

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
		AbstractConstant absConstant = new ConstMigInsPara();
		
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
		AbstractConstant absConstant = new ConstMigInsPara();

		SQLAssembleC sqlA = new SQLAssembleC(
				absConstant.getTableName(),
				jsonObject.getJSONObject("data"),
				absConstant.getColumnTypes(),
				absConstant.getFront2col()
				);
		
		MigInsPara pojo = new MigInsPara();
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
	 * @see person.daizhongde.authority.spring.service.impl.MigInsParaService#modify(java.lang.String)
	 */
	@Override
	public int modify( String jdata ){
		JSONObject jsonObject = JSONObject.fromObject(jdata);
		AbstractConstant absConstant = new ConstMigInsPara();

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
		AbstractConstant absConstant = new ConstMigInsPara();

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
		AbstractConstant absConstant = new ConstMigInsPara();
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
		AbstractConstant absConstant = new ConstMigInsPara();
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
		AbstractConstant absConstant = new ConstMigInsPara();

		SQLAssembleR sqlA = new SQLAssembleR(
				absConstant.getSQLDOC(),
				absConstant.getRead_SQL(), 
				jsonObject.getJSONObject("condition"),
				jsonObject.getJSONObject("operator"),
				absConstant.getColumnTypes(),
				absConstant.getFront2col() );
		
		return (Object[])dataDAO.sqlQuerylistAllRetArrayByMap( sqlA.getSQL(), sqlA.getMap() ).get(0);
	}
	
	public MigInsPara browsePOJO(String jdata) {
		JSONObject jsonObject = JSONObject.fromObject(jdata);
		AbstractConstant absConstant = new ConstMigInsPara();

		HQLAssembleR hqlA = new HQLAssembleR(
				absConstant.getSQLDOC(),
				absConstant.getRead_SQL(), 
				jsonObject.getJSONObject("condition"),
				jsonObject.getJSONObject("operator"),
				absConstant.getColumnTypes(),
				absConstant.getFront2back() );
		
		return (MigInsPara)dataDAO.listAllByMap( "from MigInsPara t1 where "+hqlA.getWhereBackHQL(), hqlA.getMap() ).get(0);
	}
	public MigInsPara browsePOJOById(long id) {
//		return dataDAO.findById((short)id);
		return null;
	}
	public MigInsPara browsePOJOById(String id) {
//		return dataDAO.findById((short)id);
		return null;
	}
	/* (non-Javadoc)
	 * @see person.daizhongde.authority.spring.service.impl.MigInsParaService#delete(java.lang.String)
	 */
	@Override
	public int delete( String jdata ){
		JSONObject jsonObject = JSONObject.fromObject(jdata);
		AbstractConstant absConstant = new ConstMigInsPara();

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

	public void setDataDAO(MigInsParaDAO dataDAO) {
		this.dataDAO = dataDAO;
	}

	public static MigInsParaService getFromApplicationContext(
			ApplicationContext ctx) {
		return (MigInsParaService) ctx.getBean("migInsParaService");
	}


}
