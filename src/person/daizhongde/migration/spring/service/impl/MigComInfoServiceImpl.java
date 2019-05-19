package person.daizhongde.migration.spring.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

import person.daizhongde.migration.constant.ConstMigComIns;
import person.daizhongde.migration.constant.TableName;
import person.daizhongde.migration.exception.BusinessException;
import person.daizhongde.migration.hibernate.dao.MigComInfoDAO;
import person.daizhongde.migration.hibernate.dao.MigComInsDAO;
import person.daizhongde.migration.hibernate.dao.MigTaskInfoDAO;
import person.daizhongde.migration.hibernate.dto.MigControlTemplateDto;
import person.daizhongde.migration.hibernate.pojo.MigComInfo;
import person.daizhongde.migration.hibernate.pojo.MigComInfoId;
import person.daizhongde.migration.hibernate.pojo.MigControlTemplate;
import person.daizhongde.migration.spring.service.BusiMemoryService;
import person.daizhongde.migration.spring.service.MigComInfoService;
import person.daizhongde.migration.spring.service.PubService;
import person.daizhongde.migration.util.TemplateUtils;

public class MigComInfoServiceImpl implements MigComInfoService {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	private MigComInfoDAO dataDAO;

	private MigComInsDAO comInsDAO;
	private PubService pubSrv;
	private MigTaskInfoDAO taskInfoDAO;
	private BusiMemoryService busiMemoryService;
	
	public  String getNewComId(){
		return pubSrv.get10ByteCode(TableName.mig_com_info);
	}

	public List<MigControlTemplateDto> queryComInfos(String controlId, String comId ){
		List<MigControlTemplate> templates = busiMemoryService.getControlMap().get( controlId );
		
		if( null == comId || "".equalsIgnoreCase(comId) ){

			List<MigControlTemplateDto> ret = new ArrayList<MigControlTemplateDto>();
			for(int i=0,j =templates.size(); i<j; i++ ){
				MigControlTemplate e = templates.get(i);
				MigControlTemplateDto dto = new MigControlTemplateDto();
				dto.setControlId(e.getId().getControlId());
				dto.setParaId(e.getId().getParaId());
				dto.setParaName(e.getParaName());
				dto.setNullable(e.getNullable());
				dto.setIsNumber(e.getIsNumber());
				dto.setDefValue(e.getDefValue());
				dto.setInputType(e.getInputType());
				dto.setCodeType(e.getCodeType());
				dto.setCheckrule(e.getCheckrule());
				dto.setRemark(e.getRemark());
				ret.add(dto);
			}
			return ret;
		}
		
		
		Map<Integer, String> map =  TemplateUtils.getIdNameMap(templates);
		
		@SuppressWarnings("unchecked")
		List<MigComInfo> cominfos = dataDAO.getCominfoByComId(comId);
		
		List<MigControlTemplateDto> ret = new ArrayList<MigControlTemplateDto>();
		for(int i=0,j =templates.size(); i<j; i++ ){
			MigControlTemplate e = templates.get(i);
			MigControlTemplateDto dto = new MigControlTemplateDto();
			dto.setControlId(e.getId().getControlId());
			dto.setParaId(e.getId().getParaId());
			dto.setParaName(e.getParaName());
			dto.setNullable(e.getNullable());
			dto.setIsNumber(e.getIsNumber());
			dto.setDefValue( cominfos.get(i).getParaValue() );
			dto.setInputType(e.getInputType());
			dto.setCodeType(e.getCodeType());
			dto.setCheckrule(e.getCheckrule());
			dto.setRemark(e.getRemark());
			ret.add(dto);
		}
		return ret;
	};
	
	public void modifyPara( String comId, Map<String,String> para ){
		for (Map.Entry<String, String> entry: para.entrySet()) {
			dataDAO.updateCominfoById(entry.getValue(), Integer.valueOf( entry.getKey() ), comId);
		}
	}
		
	public void newCom(String taskId, List<MigComInfo> comInfoList) {
		if (comInfoList == null) {
			throw new BusinessException("comIns list is null!,do nothing!");
		}
		
		String comId = getNewComId();
		
		for (MigComInfo tmp : comInfoList) {
			
			tmp.setId(new MigComInfoId(comId, tmp.getId().getParaId()));;
			dataDAO.save(tmp);
		}
		taskInfoDAO.updateTaskComById(comId, taskId);
		
		log.debug("save com success!");
	}

	public void updateComById(String comId, Map<Integer,String> paras) {
		
		if(comId==null || "".equals(comId)){
			throw new BusinessException("comId not correct!");
		}
		if(paras==null ){
			throw new BusinessException("paras not correct!");
		}
        
		Set<Integer> st=paras.keySet();
        for(Integer i:st){
        	
        	dataDAO.updateCominfoById(paras.get(i),i,comId);
        }
        log.debug("update comIns success!");
	}

	public void deleteComInfoById(String comId) {
		if (comId == null || comId.trim().equals("")) {
		    throw new BusinessException("comid is null or empty!");
		}
		dataDAO.deleteCominfoByComId(comId);
        log.debug("delete com_id="+comId+"success!");
	}

	public List<MigComInfo> getComInfoByComId(String comId) {
		if (comId == null || comId.trim().equals("")) {
			throw new BusinessException("comid is null or empty!");
		}
		
		List ls=dataDAO.getCominfoByComId(comId);
		log.debug("get comins success");
		return ls;	
	}
	
	public void instanceComInfo(String jobInsId, String comId){
		log.debug("comId:"+comId);
		if (jobInsId == null || jobInsId.trim().equals("") || comId == null || comId.trim().equals("") ) {
			throw new BusinessException("jobInsId or comid is null or empty!");
		}
		List comInsList = comInsDAO.getCominsByJobInsIdAndComId(jobInsId, comId);
		if(comInsList.size() == 0){
			dataDAO.instanceComInfo(jobInsId, comId);
		}
	}
	public void instanceComInfo(String jobInsId, String comId,  List<MigComInfo> rows){
		log.debug("jobInsId:"+jobInsId+",comId:"+comId);
		if (jobInsId == null || jobInsId.trim().equals("") || comId == null || comId.trim().equals("") ) {
			throw new BusinessException("jobInsId or comid is null or empty!");
		}
		List comInsList = comInsDAO.getCominsByJobInsIdAndComId(jobInsId, comId);
		if(comInsList.size() == 0){
			dataDAO.instanceComInfo(jobInsId, comId, rows );
		}
	}
	@Override
	public long getTotal(SQLAssembleQ sqlA) {
		// Object o= dataDAO.sqlQueryfindaValueByMap( sqlA.getCountSQL(),
		// sqlA.getMap() );
		// System.out.println("o:"+o);
		return Long.valueOf(dataDAO.sqlQueryfindaValueByMap(sqlA.getCountSQL(),
				sqlA.getMap()).toString());
	}

	@Override
	public List getRowsInMap(SQLAssembleQ sqlA) {
		return dataDAO.sqlQuerylistAllByMap(sqlA.getSQL(), sqlA.getMap());// 使用native数据量小
	}
	
	@Override
	public List getRowsInMap(SQLAssembleQ sqlA, int offset, int pageSize) {
		return dataDAO.sqlQueryfindByPageByMap(sqlA.getSQL(), sqlA.getMap(),
				offset, pageSize);// 使用native数据量小
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
		AbstractConstant absConstant = new ConstMigComIns();

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
		AbstractConstant absConstant = new ConstMigComIns();

		SQLAssembleC sqlA = new SQLAssembleC(
				absConstant.getTableName(),
				jsonObject.getJSONObject("data"),
				absConstant.getColumnTypes(),
				absConstant.getFront2col()
				);
		
		return dataDAO.sqlQueryExeUByMap(sqlA.getSQL(), sqlA.getMap());
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.authority.spring.service.impl.MigComInsService#addWithId(java.lang.String)
	 */
	@Override
	public int addWithId( String jdata ){
		JSONObject jsonObject = JSONObject.fromObject(jdata);
		AbstractConstant absConstant = new ConstMigComIns();

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
		AbstractConstant absConstant = new ConstMigComIns();
		
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
		AbstractConstant absConstant = new ConstMigComIns();

		SQLAssembleC sqlA = new SQLAssembleC(
				absConstant.getTableName(),
				jsonObject.getJSONObject("data"),
				absConstant.getColumnTypes(),
				absConstant.getFront2col()
				);
		
		MigComInfo pojo = new MigComInfo();
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
	 * @see person.daizhongde.authority.spring.service.impl.MigComInsService#modify(java.lang.String)
	 */
	@Override
	public int modify( String jdata ){
		JSONObject jsonObject = JSONObject.fromObject(jdata);
		AbstractConstant absConstant = new ConstMigComIns();

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
		AbstractConstant absConstant = new ConstMigComIns();

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
		AbstractConstant absConstant = new ConstMigComIns();
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
		AbstractConstant absConstant = new ConstMigComIns();
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
		AbstractConstant absConstant = new ConstMigComIns();

		SQLAssembleR sqlA = new SQLAssembleR(
				absConstant.getSQLDOC(),
				absConstant.getRead_SQL(), 
				jsonObject.getJSONObject("condition"),
				jsonObject.getJSONObject("operator"),
				absConstant.getColumnTypes(),
				absConstant.getFront2col() );
		
		return (Object[])dataDAO.sqlQuerylistAllRetArrayByMap( sqlA.getSQL(), sqlA.getMap() ).get(0);
	}
	
	public MigComInfo browsePOJO(String jdata) {
		JSONObject jsonObject = JSONObject.fromObject(jdata);
		AbstractConstant absConstant = new ConstMigComIns();

		HQLAssembleR hqlA = new HQLAssembleR(
				absConstant.getSQLDOC(),
				absConstant.getRead_SQL(), 
				jsonObject.getJSONObject("condition"),
				jsonObject.getJSONObject("operator"),
				absConstant.getColumnTypes(),
				absConstant.getFront2back() );
		
		return (MigComInfo)dataDAO.listAllByMap( "from MigComIns t1 where "+hqlA.getWhereBackHQL(), hqlA.getMap() ).get(0);
	}
	public MigComInfo browsePOJOById(long id) {
//		return dataDAO.findById((short)id);
		return null;
	}
	public MigComInfo browsePOJOById(String id) {
//		return dataDAO.findById((short)id);
		return null;
	}
	/* (non-Javadoc)
	 * @see person.daizhongde.authority.spring.service.impl.MigComInsService#delete(java.lang.String)
	 */
	@Override
	public int delete( String jdata ){
		JSONObject jsonObject = JSONObject.fromObject(jdata);
		AbstractConstant absConstant = new ConstMigComIns();

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

	public void setDataDAO(MigComInfoDAO dataDAO) {
		this.dataDAO = dataDAO;
	}

	public void setPubSrv(PubService pubSrv) {
		this.pubSrv = pubSrv;
	}

	public void setTaskInfoDAO(MigTaskInfoDAO taskInfoDAO) {
		this.taskInfoDAO = taskInfoDAO;
	}

	public void setComInsDAO(MigComInsDAO comInsDAO) {
		this.comInsDAO = comInsDAO;
	}

	public void setBusiMemoryService(BusiMemoryService busiMemoryService) {
		this.busiMemoryService = busiMemoryService;
	}
	public static MigComInfoService getFromApplicationContext(
			ApplicationContext ctx) {
		return (MigComInfoService) ctx.getBean("migComInfoService");
	}
}
