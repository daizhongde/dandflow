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

import person.daizhongde.migration.constant.ConstMigJobPara;
import person.daizhongde.migration.exception.BusinessException;
import person.daizhongde.migration.hibernate.dao.MigJobParaDAO;
import person.daizhongde.migration.hibernate.dto.MigJobParaDto;
import person.daizhongde.migration.hibernate.pojo.MigJobPara;
import person.daizhongde.migration.spring.service.MigJobParaService;

public class MigJobParaServiceImpl implements MigJobParaService {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	private MigJobParaDAO dataDAO;
	
	
	public  void newJobPara(MigJobPara mjp){
		if(mjp!=null)
			dataDAO.save(mjp);
		log.debug("newJobPara success");
	}
	public  void updateJobPara(String taskId,Map<String,String> paras){
		if(taskId!=null && paras!=null)
			dataDAO.updateJobPara(taskId, paras);
		log.debug("updateJobPara success");
	}
	public  void deleteJobPara(String taskId,String para){
		if(taskId!=null && para!=null)
			dataDAO.deleteJobPara(taskId, para);
		log.debug("deleteJobPara success");	
	}
	
	public  List<MigJobPara> findAllParaByNodeId(String nodeId){
		if(nodeId!=null)
			return dataDAO.findAllParaByNodeId(nodeId);
		return null;
	}
	public void instanceJobPara(String jobInsId, String nodeId){
		if (jobInsId == null || jobInsId.trim().equals("") || nodeId == null || nodeId.trim().equals("") ) {
			throw new BusinessException("comid is null or empty!");
		}
		
		dataDAO.instanceJobPara(jobInsId, nodeId);
	}
	public void instanceJobPara(String jobInsId, String nodeId, List<MigJobParaDto> jobparamlist){
		if (jobInsId == null || jobInsId.trim().equals("") || nodeId == null || nodeId.trim().equals("") ) {
			throw new BusinessException("comid is null or empty!");
		}
		
		dataDAO.instanceJobPara(jobInsId, nodeId, jobparamlist);
	}
	
	/*
	public Object substitute(String jobId, String taskId, String value){
		char[] b = value.toCharArray();
		String param="";
		String ret = "";
		boolean paramFlag = false;
		
		 ${param1}
		 * for(char e : b){
			if( e=='$' ){
				paramFlag = true;//init
			}else if( e=='{' && paramFlag ){
				param="";
			}else if(e=='}' && paramFlag ){
				
				ret += this.getParameterValue(jobId, taskId, param);
			}else if(paramFlag){
					param+=e;
			}else{
					ret+=e;
			}
		}
//		$param1:
		for(char e : b){
			if( e=='$' ){
				paramFlag = true;//init
				param="";
			}else if(e ==':' && paramFlag ){
				ret += this.getParameterValue(jobId, taskId, param);
			}else if(paramFlag){
					param+=e;
			}else{
					ret+=e;
			}
		}
		return ret;
	}
	
	 * not used
	 * @param taskId
	 * @param param
	 * @return
	
	private Object getParameterValue(String taskId, String param){
		MigJobParaId id = new MigJobParaId(taskId, param);
		MigJobPara para = dataDAO.findById(id);
		//convert to some sqltype.....
		return para.getParaValue();
	}
	private Object getParameterValue(String jobId, String taskId, String param){
		List<MigJobPara> paras = dataDAO.find(jobId,taskId, param);
		switch(paras.size()){
		case 0:throw new RuntimeException("找不到对应的参数值<"+param+">");
		case 1:return paras.get(0).getParaValue();
		case 2:
			String task = paras.get(0).getId().getTask();
			return task.equalsIgnoreCase(taskId)?
					paras.get(0).getParaValue():paras.get(1).getParaValue();
		default: throw new RuntimeException("对应的参数值太多了<"+param+">");
		}
	} */
	
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
		AbstractConstant absConstant = new ConstMigJobPara();

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
		AbstractConstant absConstant = new ConstMigJobPara();

		SQLAssembleC sqlA = new SQLAssembleC(
				absConstant.getTableName(),
				jsonObject.getJSONObject("data"),
				absConstant.getColumnTypes(),
				absConstant.getFront2col()
				);
		
		return dataDAO.sqlQueryExeUByMap(sqlA.getSQL(), sqlA.getMap());
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.authority.spring.service.impl.MigJobParaService#addWithId(java.lang.String)
	 */
	@Override
	public int addWithId( String jdata ){
		JSONObject jsonObject = JSONObject.fromObject(jdata);
		AbstractConstant absConstant = new ConstMigJobPara();

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
		AbstractConstant absConstant = new ConstMigJobPara();
		
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
		AbstractConstant absConstant = new ConstMigJobPara();

		SQLAssembleC sqlA = new SQLAssembleC(
				absConstant.getTableName(),
				jsonObject.getJSONObject("data"),
				absConstant.getColumnTypes(),
				absConstant.getFront2col()
				);
		
		MigJobPara pojo = new MigJobPara();
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
	 * @see person.daizhongde.authority.spring.service.impl.MigJobParaService#modify(java.lang.String)
	 */
	@Override
	public int modify( String jdata ){
		JSONObject jsonObject = JSONObject.fromObject(jdata);
		AbstractConstant absConstant = new ConstMigJobPara();

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
		AbstractConstant absConstant = new ConstMigJobPara();

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
		AbstractConstant absConstant = new ConstMigJobPara();
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
		AbstractConstant absConstant = new ConstMigJobPara();
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
		AbstractConstant absConstant = new ConstMigJobPara();

		SQLAssembleR sqlA = new SQLAssembleR(
				absConstant.getSQLDOC(),
				absConstant.getRead_SQL(), 
				jsonObject.getJSONObject("condition"),
				jsonObject.getJSONObject("operator"),
				absConstant.getColumnTypes(),
				absConstant.getFront2col() );
		
		return (Object[])dataDAO.sqlQuerylistAllRetArrayByMap( sqlA.getSQL(), sqlA.getMap() ).get(0);
	}
	
	public MigJobPara browsePOJO(String jdata) {
		JSONObject jsonObject = JSONObject.fromObject(jdata);
		AbstractConstant absConstant = new ConstMigJobPara();

		HQLAssembleR hqlA = new HQLAssembleR(
				absConstant.getSQLDOC(),
				absConstant.getRead_SQL(), 
				jsonObject.getJSONObject("condition"),
				jsonObject.getJSONObject("operator"),
				absConstant.getColumnTypes(),
				absConstant.getFront2back() );
		
		return (MigJobPara)dataDAO.listAllByMap( "from MigJobPara t1 where "+hqlA.getWhereBackHQL(), hqlA.getMap() ).get(0);
	}
	public MigJobPara browsePOJOById(long id) {
//		return dataDAO.findById((short)id);
		return null;
	}
	public MigJobPara browsePOJOById(String id) {
//		return dataDAO.findById((short)id);
		return null;
	}
	/* (non-Javadoc)
	 * @see person.daizhongde.authority.spring.service.impl.MigJobParaService#delete(java.lang.String)
	 */
	@Override
	public int delete( String jdata ){
		JSONObject jsonObject = JSONObject.fromObject(jdata);
		AbstractConstant absConstant = new ConstMigJobPara();

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

	public void setDataDAO(MigJobParaDAO dataDAO) {
		this.dataDAO = dataDAO;
	}

	public static MigJobParaService getFromApplicationContext(
			ApplicationContext ctx) {
		return (MigJobParaService) ctx.getBean("migJobParaService");
	}


}
