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
import person.daizhongde.virtue.util.test.Printer;

import person.daizhongde.authority.hibernate.pojo.TAuthorityUser;
import person.daizhongde.migration.constant.ConstMigJobIns;
import person.daizhongde.migration.constant.JobLockState;
import person.daizhongde.migration.constant.JobState;
import person.daizhongde.migration.constant.NodeType;
import person.daizhongde.migration.constant.TaskState;
import person.daizhongde.migration.exception.BusinessException;
import person.daizhongde.migration.hibernate.dao.MigComInsDAO;
import person.daizhongde.migration.hibernate.dao.MigInsParaDAO;
import person.daizhongde.migration.hibernate.dao.MigJobInsDAO;
import person.daizhongde.migration.hibernate.dao.MigJobLogDAO;
import person.daizhongde.migration.hibernate.dao.MigJobProcessDAO;
import person.daizhongde.migration.hibernate.dao.MigJobStatDAO;
import person.daizhongde.migration.hibernate.pojo.MigJobIns;
import person.daizhongde.migration.hibernate.pojo.MigJobIns_JEasyUI_TreeGrid;
import person.daizhongde.migration.hibernate.pojo.MigJobProcess;
import person.daizhongde.migration.hibernate.pojo.TPubDictionary;
import person.daizhongde.migration.spring.service.BusiMemoryService;
import person.daizhongde.migration.spring.service.MigJobInsService;
import person.daizhongde.migration.spring.service.MigTaskInfoService;
import person.daizhongde.migration.spring.service.TPubDictionaryService;
import person.daizhongde.migration.util.TreeDataUtil_Instance;

public class MigJobInsServiceImpl implements MigJobInsService {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	private MigJobInsDAO dataDAO;
	private MigJobProcessDAO processDAO;
	private MigTaskInfoService taskInfoSrv;
	private MigInsParaDAO insParaDAO;
	private MigComInsDAO comInsDAO;
	private MigJobLogDAO logDAO;
	private MigJobStatDAO statDAO;
	
	private BusiMemoryService busiMemoryService; 
	
	private TPubDictionaryService pubDictionaryService;
	
	private TreeDataUtil_Instance mdUtil = new TreeDataUtil_Instance();
	
	public Object getData_JEasyUI_TreeGrid_Async( String moduleid, boolean WithRoot ){
		if(WithRoot)
		{
			return getData_JEasyUI_TreeGrid_AsyncWithRoot(moduleid);
		}
		else
		{
			return getData_JEasyUI_TreeGrid_AsyncNoRoot(moduleid);
		}
	}
	/**
	 * 前台展示单颗实例树用,目前没有查询root的下一级
	 * @param moduleid
	 * @return
	 */
	public Object getData_JEasyUI_TreeGrid_AsyncWithRoot(String moduleid){
//		log.debug("service....All node's state is close...getData_JEasyUI_Tree_Async...........");

		List dataList = new ArrayList();//module's children(Front Type )
		
		MigJobIns rootModule = (MigJobIns)dataDAO.findById(moduleid);
		TPubDictionary dictionary = new TPubDictionary();
		dictionary.setType("job_type");
		List<TPubDictionary> list = pubDictionaryService.queryList(dictionary);
		Map<String, String> jobTypeMap = new HashMap<String, String>(list.size());
		for(TPubDictionary e : list){
			jobTypeMap.put(e.getCode(), e.getValue() );
		}
		
		MigJobIns_JEasyUI_TreeGrid mJEU = new MigJobIns_JEasyUI_TreeGrid();
		
		mJEU.setId( rootModule.getJobInsId() + "-" + rootModule.getJobId() );
		mJEU.setText( rootModule.getJobInsName() );
		mJEU.setIconCls( "icon-job" );
		mJEU.setState("closed");
		mJEU.setNote( rootModule.getRemark() );
		mJEU.setDryrunId( rootModule.getDryrunId() );
//		String controlId = m.getControlId();
		mJEU.setType( jobTypeMap.get( rootModule.getType() ) );
		mJEU.setStatus( rootModule.getStatus() );
		mJEU.setAuthor( rootModule.getAuthor() );
		mJEU.setBeginTime( rootModule.getBeginTime() );
		mJEU.setEndTime( rootModule.getEndTime() );
		mJEU.setTotalTime( rootModule.getTotalTime() );
		
		JSONArray jsonArray = JSONArray.fromObject(mJEU);
		return jsonArray;
	}
	/**
	 * 在过程表中查实例根的下一级或是实例中子包的下一级
	 * @param moduleid
	 * @return
	 */
	public Object getData_JEasyUI_TreeGrid_AsyncNoRoot( String moduleid ){
//		log.debug("service....lazyload, Async tree...getData_JEasyUI_Tree_Async...........");
		List dataList = new ArrayList();//Root's children

		List<MigJobProcess> moduleList = processDAO.findChildrenNoRecursive( moduleid );//module's children

//		int dryrunId = 6;
		TPubDictionary dictionary = new TPubDictionary();
		dictionary.setType("job_type");
		List<TPubDictionary> list = pubDictionaryService.queryList(dictionary);
		Map<String, String> jobTypeMap = new HashMap<String, String>(list.size());
		for(TPubDictionary e : list){
			jobTypeMap.put(e.getCode(), e.getValue() );
		}
		mdUtil.assembleData_JEasyUI_Tree_Async( dataList, moduleList, 
				busiMemoryService.getControlIconMap(), 
				busiMemoryService.getControlNameMap(),
				jobTypeMap);
		
//		JSONArray jsonArray = JSONArray.fromObject(dataList);
//		log.debug("jsonArray:"+jsonArray.toString());		
//		return jsonArray.toString();
		return dataList;
	}
	
	public  boolean unlockJobIns(String jobInsId,TAuthorityUser user){
		if(jobInsId==null || "".equals(jobInsId)){
			 throw new BusinessException("jobId <"+jobInsId+"> is null or empty !");
		}
		dataDAO.unLock(jobInsId);
		return true;	
	}
	/**
	 * 只有执行完成或暂停状态的实例才能被重置
	 * @param jobInsId
	 * @param user
	 */
	public void modifyJobStatus2init(String jobInsId, String jobId, TAuthorityUser user){
		MigJobIns jobIns = dataDAO.findById( jobInsId );
		if(jobIns.getStatus().equalsIgnoreCase( JobState.FINISH )
				|| jobIns.getStatus().equalsIgnoreCase( JobState.PAUSE )){
			
			if( dataDAO.sqlQueryUpdateJobInsState_2Init(jobInsId) == 1 ){
				processDAO.sqlQueryUpdateProcessStateByIns_2Init( jobInsId );
			}else{
				throw new BusinessException("Fatal Error! Instance is locked or running ");
			}
			
		}else{
			throw new BusinessException("Only Finished or Paused instance can be initial! ");
		}
	}
	/**
	 * 重置实例中的子作业，实例状态不变，将子作业及其所有子结点状态全置为初始
	 * <p>
	 * 只有执行完成或暂停状态的实例的子作业才能被重置
	 * @param jobInsId
	 * @param user
	 */
	public void modifySubJobStatus2init(String jobInsId, String jobId, TAuthorityUser user){
		MigJobIns jobIns = dataDAO.findById( jobInsId );
		if(jobIns.getStatus().equalsIgnoreCase( JobState.FINISH )
				|| jobIns.getStatus().equalsIgnoreCase( JobState.PAUSE )){
			
			if( dataDAO.sqlQueryUpdateJobInsState_2Cur(jobInsId) == 1 ){
				/* 获取该子作业及其所有子孙的process id  */
				List<String> processidList = new ArrayList<String>();
				rescurFindProcessId(jobInsId, jobId, processidList);
				processDAO.sqlQueryUpdateSubJobStateByIns_2Init( jobInsId, jobId, processidList );
			}else{
				throw new BusinessException("Fatal Error! Instance is locked or running, Can't reset subjob! ");
			}
			
		}else{
			throw new BusinessException("Only Finished or Paused instance's subjob can be initial! ");
		}
	}
	private void rescurFindProcessId(String jobInsId, String jobId, List<String> processidList){
		
		MigJobProcess p = new MigJobProcess();
		p.setJobInsId(jobInsId);
		p.setJobId(jobId);
		
		List<MigJobProcess> list = processDAO.findByExample(p);
		for(MigJobProcess e: list){
			if(e.getIsleaf() == NodeType.LEAF){
				processidList.add(e.getProcessId());
			}else{
				processidList.add(e.getProcessId());
				rescurFindProcessId(jobInsId,e.getNodeId(), processidList);
			}
		}
	}
	/**
	 *  DELETE FROM tool.mig_job_process WHERE job_ins_id='IS00000124';<br/>
		DELETE FROM tool.`mig_ins_para` WHERE job_ins_id='IS00000124';<br/>
		DELETE FROM tool.mig_com_ins WHERE job_ins_id='IS00000124';<br/>
		DELETE FROM tool.`mig_job_log` WHERE job_ins_id='IS00000124';<br/>
		DELETE FROM tool.mig_job_stat WHERE job_ins_id='IS00000124';<br/>
		DELETE FROM tool.mig_job_ins WHERE job_ins_name='CREATE_01_MID_TABLE-实例1';
	 * @param jobInsId
	 * @param user
	 */
	public void removeInstance(String jobInsId, TAuthorityUser user){
		processDAO.deleteByJobInsId(jobInsId);
		insParaDAO.deleteJobPara(jobInsId);
		comInsDAO.deleteComins(jobInsId);
		logDAO.deleteByInsId(jobInsId);
		statDAO.deleteByInsId(jobInsId);
		dataDAO.deleteByInsId(jobInsId);
	}
	public void removeInstance(List<String> jobInsId, TAuthorityUser user){
		processDAO.deleteByJobInsId(jobInsId);
		insParaDAO.deleteJobPara(jobInsId);
		comInsDAO.deleteComins(jobInsId);
		logDAO.deleteByInsId(jobInsId);
		statDAO.deleteByInsId(jobInsId);
		dataDAO.deleteByInsId(jobInsId);
	}
	
	private void recurCheckJobIns(String jobId, String jobInsId, String subJobId){
		List<MigJobProcess> processList=processDAO.findByJobInsIdAndJobId(jobInsId, subJobId );
		
		for(MigJobProcess jobProcess:processList){
			if(jobProcess.getIsleaf() == 1){
				taskInfoSrv.checkTaskIns(jobId, jobInsId, jobProcess.getNodeId());
			}else{
				recurCheckJobIns(jobId, jobInsId, jobProcess.getNodeId());
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
		return 0;
	}

	@Override
	public List getRowsInMap(HQLAssembleQ hqlA) {
		return null;
	}

	@Override
	public List getRowsInMap(HQLAssembleQ sqlA, int offset, int pageSize) {
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
		AbstractConstant absConstant = new ConstMigJobIns();

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
		AbstractConstant absConstant = new ConstMigJobIns();

		SQLAssembleC sqlA = new SQLAssembleC(
				absConstant.getTableName(),
				jsonObject.getJSONObject("data"),
				absConstant.getColumnTypes(),
				absConstant.getFront2col()
				);
		
		return dataDAO.sqlQueryExeUByMap(sqlA.getSQL(), sqlA.getMap());
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.authority.spring.service.impl.MigJobInsService#addWithId(java.lang.String)
	 */
	@Override
	public int addWithId( String jdata ){
		JSONObject jsonObject = JSONObject.fromObject(jdata);
		AbstractConstant absConstant = new ConstMigJobIns();

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
		AbstractConstant absConstant = new ConstMigJobIns();
		
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
		AbstractConstant absConstant = new ConstMigJobIns();

		SQLAssembleC sqlA = new SQLAssembleC(
				absConstant.getTableName(),
				jsonObject.getJSONObject("data"),
				absConstant.getColumnTypes(),
				absConstant.getFront2col()
				);
		
		MigJobIns pojo = new MigJobIns();
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
	 * @see person.daizhongde.authority.spring.service.impl.MigJobInsService#modify(java.lang.String)
	 */
	@Override
	public int modify( String jdata ){
		JSONObject jsonObject = JSONObject.fromObject(jdata);
		AbstractConstant absConstant = new ConstMigJobIns();

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
		AbstractConstant absConstant = new ConstMigJobIns();

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
		AbstractConstant absConstant = new ConstMigJobIns();
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
		AbstractConstant absConstant = new ConstMigJobIns();
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
		AbstractConstant absConstant = new ConstMigJobIns();

		SQLAssembleR sqlA = new SQLAssembleR(
				absConstant.getSQLDOC(),
				absConstant.getRead_SQL(), 
				jsonObject.getJSONObject("condition"),
				jsonObject.getJSONObject("operator"),
				absConstant.getColumnTypes(),
				absConstant.getFront2col() );
		
		return (Object[])dataDAO.sqlQuerylistAllRetArrayByMap( sqlA.getSQL(), sqlA.getMap() ).get(0);
	}
	
	public MigJobIns browsePOJO(String jdata) {
		JSONObject jsonObject = JSONObject.fromObject(jdata);
		AbstractConstant absConstant = new ConstMigJobIns();

		HQLAssembleR hqlA = new HQLAssembleR(
				absConstant.getSQLDOC(),
				absConstant.getRead_SQL(), 
				jsonObject.getJSONObject("condition"),
				jsonObject.getJSONObject("operator"),
				absConstant.getColumnTypes(),
				absConstant.getFront2back() );
		
		return (MigJobIns)dataDAO.listAllByMap( "from MigJobIns t1 where "+hqlA.getWhereBackHQL(), hqlA.getMap() ).get(0);
	}
	public MigJobIns browsePOJOById(int id) {
//		return dataDAO.findById((short)id);
		return null;
	}
	public MigJobIns browsePOJOById(String id) {
		return dataDAO.findById(id);
	}
	/* (non-Javadoc)
	 * @see person.daizhongde.authority.spring.service.impl.MigJobInsService#delete(java.lang.String)
	 */
	@Override
	public int delete( String jdata ){
		JSONObject jsonObject = JSONObject.fromObject(jdata);
		AbstractConstant absConstant = new ConstMigJobIns();

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

	public void setDataDAO(MigJobInsDAO dataDAO) {
		this.dataDAO = dataDAO;
	}

	public void setProcessDAO(MigJobProcessDAO processDAO) {
		this.processDAO = processDAO;
	}

	public void setTaskInfoSrv(MigTaskInfoService taskInfoSrv) {
		this.taskInfoSrv = taskInfoSrv;
	}

	public void setComInsDAO(MigComInsDAO comInsDAO) {
		this.comInsDAO = comInsDAO;
	}

	public void setInsParaDAO(MigInsParaDAO insParaDAO) {
		this.insParaDAO = insParaDAO;
	}

	public void setStatDAO(MigJobStatDAO statDAO) {
		this.statDAO = statDAO;
	}

	public void setLogDAO(MigJobLogDAO logDAO) {
		this.logDAO = logDAO;
	}

	public void setBusiMemoryService(BusiMemoryService busiMemoryService) {
		this.busiMemoryService = busiMemoryService;
	}

	public void setPubDictionaryService(TPubDictionaryService pubDictionaryService) {
		this.pubDictionaryService = pubDictionaryService;
	}

	public static MigJobInsService getFromApplicationContext(
			ApplicationContext ctx) {
		return (MigJobInsService) ctx.getBean("migJobInsService");
	}


}
