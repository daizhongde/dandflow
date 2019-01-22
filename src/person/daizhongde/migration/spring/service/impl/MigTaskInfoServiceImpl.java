package person.daizhongde.migration.spring.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
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

import person.daizhongde.migration.constant.ConstMigTaskInfo;
import person.daizhongde.migration.constant.TableName;
import person.daizhongde.migration.exception.BusinessException;
import person.daizhongde.migration.hibernate.dao.MigComInfoDAO;
import person.daizhongde.migration.hibernate.dao.MigComInsDAO;
import person.daizhongde.migration.hibernate.dao.MigInsParaDAO;
import person.daizhongde.migration.hibernate.dao.MigJobParaDAO;
import person.daizhongde.migration.hibernate.dao.MigTaskInfoDAO;
import person.daizhongde.migration.hibernate.dto.MigComInfoDto;
import person.daizhongde.migration.hibernate.dto.MigJobParaDto;
import person.daizhongde.migration.hibernate.pojo.MigComInfo;
import person.daizhongde.migration.hibernate.pojo.MigComInfoId;
import person.daizhongde.migration.hibernate.pojo.MigComIns;
import person.daizhongde.migration.hibernate.pojo.MigComInsId;
import person.daizhongde.migration.hibernate.pojo.MigInsPara;
import person.daizhongde.migration.hibernate.pojo.MigControlTemplate;
import person.daizhongde.migration.hibernate.pojo.MigJobPara;
import person.daizhongde.migration.hibernate.pojo.MigJobParaId;
import person.daizhongde.migration.hibernate.pojo.MigTaskInfo;
import person.daizhongde.migration.spring.service.BusiMemoryService;
import person.daizhongde.migration.spring.service.MigComInfoService;
import person.daizhongde.migration.spring.service.MigJobProcessService;
import person.daizhongde.migration.spring.service.MigTaskInfoService;
import person.daizhongde.migration.spring.service.PubService;
import person.daizhongde.migration.spring.service.wsclient.MigWSClientService;
import person.daizhongde.migration.util.TemplateUtils;
import person.daizhongde.migration.util.VariableUtils;

public class MigTaskInfoServiceImpl implements MigTaskInfoService {

	private static final String taskInfo = null;

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	private MigTaskInfoDAO dataDAO;
	private MigComInfoDAO comInfoDAO;
	private MigComInsDAO comInsDAO;
	private PubService pubSrv;
	private MigJobProcessService jobProcessService;
	private MigJobParaDAO jobParaDAO;
	private MigInsParaDAO insParaDAO;

	private MigWSClientService wsClientService;
	private MigComInfoService comInfoSrv;
	private BusiMemoryService busiMemoryService;
	
	public void setPubSrv(PubService pubSrv) {
		this.pubSrv = pubSrv;
	}

	public  String getNewTaskId(){
		return pubSrv.get10ByteCode(TableName.mig_task_info);
	}
	
	public void checkTaskInfo(String jobId, String taskId){
		MigTaskInfo taskInfo = dataDAO.findById(taskId);
		String controlId = taskInfo.getControlId();
		
		List<MigComIns> result = new ArrayList<MigComIns>();
		
		List<MigJobPara> jobParas = jobParaDAO.findAllParaByNodeId(jobId);
		List<MigJobPara> taskParas = jobParaDAO.findAllParaByNodeId(taskId);
		
//		List<MigControlTemplate> templates = controlTemplateDAO.findByProperty("id.controlId", controlId );
		List<MigControlTemplate> templates = busiMemoryService.getControlMap().get( controlId );
		Map<Integer, String> map =  TemplateUtils.getIdNameMap(templates);

		List<MigComInfo> comInfo = comInfoDAO.getCominfoByComId(taskInfo.getComId());
		for(MigComInfo e : comInfo){

			//为了保持调用校验控件的接口一致，使用comins对象传递
			MigComIns tempE = new MigComIns(new MigComInsId());
			tempE.setComId(e.getId().getComId());
			tempE.setParaId(e.getId().getParaId());
			tempE.setParaName( map.get(e.getId().getParaId()) );
			tempE.setParaValue( 
					String.valueOf( this.getComParameterValue(jobParas, taskParas, e.getParaValue() ) ) 
					);
			
			result.add(tempE);
		}
		

		log.debug(" ############### invoke webservice check beginning ... "+taskId);
		
		//此时没有jobinsid，传递0值
        JSONObject retmsg = wsClientService.invoke( result, jobId, "0", 0, taskId,"1", taskInfo.getControlId());
        if(false == retmsg.getBoolean("success")){
        	String errmsg = retmsg.getString("msg");
        	throw new BusinessException("Check control error, errmsg:"+errmsg); 
        }

		log.debug(" ############### invoke webservice check finished ! " +taskId);
		
	}
	
	public void checkTaskIns(String jobId, String jobInsId, String taskId){
		MigTaskInfo taskInfo = dataDAO.findById(taskId);
		String comId = taskInfo.getComId();
		List<MigComIns> result = new ArrayList<MigComIns>();
		
		List<MigInsPara> paras = insParaDAO.find(jobInsId, jobId, taskId);
		
		//作业级参数
		Map<String, String> jparaMap = new HashMap<String, String>();
		//任务级参数
		Map<String, String> tparaMap = new HashMap<String, String>();
		
		for(MigInsPara e : paras ){
			if(e.getId().getNodeId().equalsIgnoreCase(taskId)){
				tparaMap.put(e.getId().getPara(), e.getParaValue());
			}else{
				jparaMap.put(e.getId().getPara(), e.getParaValue());
			}
		}
						
		List<MigComIns> comInss = comInsDAO.getCominsByJobInsIdAndComId(jobInsId, comId );
		result.addAll(comInss);
		
		/*old code do parameter replace is deprecated, param replace is not java's task, It's zhourong's
		 * I  assemble com-para's value */
		String paraValue = "";
		//找出任务的所有变量
		Set<String> taskVarAV = new HashSet<String>();
		for(MigComIns comIns : comInss ){
			Set<String> comVarV =  VariableUtils.varStatistics( comIns.getParaValue() );
			taskVarAV.addAll( comVarV );
		}
		for(String varia : taskVarAV ){
			String value = tparaMap.containsKey(varia)?tparaMap.get(varia):jparaMap.get(varia);
			
			if(StringUtils.isEmpty(value)){
				log.error("实例-"+jobInsId+"-,任务-"+taskId+"-中的-"+varia+"-参数没有定义！");
				throw new BusinessException("实例-"+jobInsId+"-,任务-"+taskId+"-中的-"+varia+"-参数没有定义！"); 
			}
			paraValue += varia + "=" + value + ";";
		}
		
		//because table com_ins is not contain para com-para, use para-id -1
		MigComInsId id = new MigComInsId( jobInsId, comId, -1 );
		MigComIns e = new MigComIns(id );
		e.setParaName( "com-para" );
		e.setParaValue( paraValue );
		result.add(e);
		
		log.debug(" ############### invoke webservice task beginning ... "+taskId);
		
		//此时没有jobinsid，传递0值
        JSONObject retmsg = wsClientService.invoke( result, jobId, "0", 0, taskId,"1", taskInfo.getControlId());
        if(false == retmsg.getBoolean("success")){
        	String errmsg = retmsg.getString("msg");
        	throw new BusinessException("Check control error, errmsg:"+errmsg); 
        }

		log.debug(" ############### invoke webservice task finished ! " +taskId);
		
	}
	
	public String modifyWithTaskParamandComInfo( String taskId, String taskName, String taskRemark, 
			String comId, List<MigJobParaDto> jobparas, List<MigComInfoDto> cominfos ){
		
		if(comId == null || "".equalsIgnoreCase(comId)){
			comId = comInfoSrv.getNewComId();
		}else{
			comInfoDAO.deleteCominfoByComId(comId);
		}
		
		/* 3将任务的组件参数信息写入组件信息表*/
		Printer.printJSON( cominfos );
		
		for(MigComInfoDto e : cominfos){
			MigComInfo comInfo = new MigComInfo(new MigComInfoId(comId,e.getParaId()),
					e.getParaValue());
			comInfoDAO.save(comInfo);
		}
		
		/* 1更新任务基本信息 */
		dataDAO.updateTaskById(taskName, taskRemark, taskId, comId);
		
		/* 2将任务级的参数写入作业参数表 */
		jobParaDAO.deleteByNodeId(taskId);
		if( null!=jobparas && 0!=jobparas.size() ){
			for(MigJobParaDto e : jobparas){
				MigJobPara jobPara = new MigJobPara(new MigJobParaId(e.getNodeId(),e.getPara()),
						e.getParaName(),e.getParaType(),e.getParaValue());
				jobParaDAO.save(jobPara);
			}
		}
		
		return comId;
	}
	
	private Object getJobParameterValue( List<MigJobPara> insParas, List<MigJobPara> nodeParas, String param){
		for(MigJobPara e : nodeParas){
			if( param.equalsIgnoreCase( e.getId().getPara() ) )
				return e.getParaValue();
		}
		
		for(MigJobPara e : insParas){
			if( param.equalsIgnoreCase( e.getId().getPara() ) )
				return e.getParaValue();
		}
		
		throw new BusinessException("找不到对应的参数值<"+param+">");
	}
	
//	private Object getComParameterValue( List<MigJobPara> insParas, List<MigJobPara> nodeParas, String value){
//		char[] b = value.toCharArray();
//		String param="";
//		String ret = "";
//		boolean paramFlag = false;
//		
////		$param1:
//		for(char e : b){
//			if( e=='$' ){
//				paramFlag = true;//init
//				param="";
//			}else if(e ==':' && paramFlag ){
//				ret += this.getJobParameterValue(insParas, nodeParas, param);
//			}else if(paramFlag){
//					param+=e;
//			}else{
//					ret+=e;
//			}
//		}
//		return ret;
//	}
//	
	private Object getComParameterValue( List<MigJobPara> insParas, List<MigJobPara> nodeParas, String value){
		char[] b = value.toCharArray();
		String param="";
		String ret = "";
		boolean paramFlag = false;
		boolean paramBeg = false;
		
//		@{param1}
		for(char e : b){
			if( e=='@' ){
				paramFlag = true;
				param="";
			}else if(e=='{' && paramFlag){
				paramBeg = true;//init
				param="";
			}else if(e =='}' && paramBeg ){
				ret += this.getJobParameterValue(insParas, nodeParas, param);
				paramBeg = false;
			}else if(paramBeg){
					param+=e;
			}else{
					ret+=e;
			}
		}
		return ret;
	}
	
//	public String addSubtaskToTask(String parentTaskId,String taskName,String controlId,Map<Integer,String> paras,String remark,TAuthorityUser user){
//		if(parentTaskId==null || controlId==null || "".equals(parentTaskId)||"".equals(controlId)){
//			log.error("parentTaskId  or controlId is null");
//			return null;
//		}
//		MigTaskInfo mti=new MigTaskInfo(this.getTaskId(), taskName, null,
//					this.getComId(), user.getCUname(), remark,
//					new Timestamp( new Date().getTime() ) );
//		
//		dataDAO.save(mti);
//		MigTaskRel mtr=new MigTaskRel(
//				//new MigTaskRelId(dataDAO.findById(parentTaskId), mti.getTaskId() )
//				new MigTaskRelId(parentTaskId, mti.getTaskId() )
//			);
//		taskRelDAO.save(mtr);
//		
//		 Set<Integer> st=paras.keySet();
//		  for (Integer s : st) {
//			  comInsDAO.save(new MigComIns(new MigComInsId(s,mti.getComId()),controlId, paras.get(s)));
//		 }
//		
//		return mti.getTaskId();
//	}
	
//	public String addNodeToJob(String jobId,String taskName,String control_id,Map<Integer,String> paras,String remark,TAuthorityUser user){
//		if(jobId==null || control_id==null || "".equals(jobId)||"".equals(control_id)){
//			log.error("jobId or control_id is null or empty");
//			return null;
//		  }
//		  MigJobContent mjc=new MigJobContent(new MigJobContentId(jobId, this.getNewTaskId()
//					), "0");
//		
//		  MigTaskInfo mti=new MigTaskInfo(mjc.getId().getTaskId(), taskName, null,
//					this.getComId(), user.getCUname(), remark,
//					new Timestamp( new Date().getTime() ) );
//		  jobcontentDAO.save(mjc);
//		  dataDAO.save(mti);
//		  
//		  Set<Integer> st=paras.keySet();
//		  for (Integer s : st) {
//			  comInsDAO.save(new MigComIns(new MigComInsId(s,mti.getComId()),control_id, paras.get(s)));
//		 }
//		  
//		return mjc.getId().getTaskId();
//	}


	public  MigTaskInfo findTaskById(String taskId){
		 if(taskId==null || "".equals(taskId)){
			 log.error("taskId is null");
				return null; 
		 }
		 MigTaskInfo ret=dataDAO.findById(taskId);
		 log.debug("task info "+taskId+"query success");
		 return ret;
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
		AbstractConstant absConstant = new ConstMigTaskInfo();

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
		AbstractConstant absConstant = new ConstMigTaskInfo();

		SQLAssembleC sqlA = new SQLAssembleC(
				absConstant.getTableName(),
				jsonObject.getJSONObject("data"),
				absConstant.getColumnTypes(),
				absConstant.getFront2col()
				);
		
		return dataDAO.sqlQueryExeUByMap(sqlA.getSQL(), sqlA.getMap());
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.authority.spring.service.impl.MigTaskInfoService#addWithId(java.lang.String)
	 */
	@Override
	public int addWithId( String jdata ){
		JSONObject jsonObject = JSONObject.fromObject(jdata);
		AbstractConstant absConstant = new ConstMigTaskInfo();

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
		AbstractConstant absConstant = new ConstMigTaskInfo();
		
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
		AbstractConstant absConstant = new ConstMigTaskInfo();

		SQLAssembleC sqlA = new SQLAssembleC(
				absConstant.getTableName(),
				jsonObject.getJSONObject("data"),
				absConstant.getColumnTypes(),
				absConstant.getFront2col()
				);
		
		MigTaskInfo pojo = new MigTaskInfo();
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
	 * @see person.daizhongde.authority.spring.service.impl.MigTaskInfoService#modify(java.lang.String)
	 */
	@Override
	public int modify( String jdata ){
		JSONObject jsonObject = JSONObject.fromObject(jdata);
		AbstractConstant absConstant = new ConstMigTaskInfo();

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
		AbstractConstant absConstant = new ConstMigTaskInfo();

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
		AbstractConstant absConstant = new ConstMigTaskInfo();
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
		AbstractConstant absConstant = new ConstMigTaskInfo();
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
		AbstractConstant absConstant = new ConstMigTaskInfo();

		SQLAssembleR sqlA = new SQLAssembleR(
				absConstant.getSQLDOC(),
				absConstant.getRead_SQL(), 
				jsonObject.getJSONObject("condition"),
				jsonObject.getJSONObject("operator"),
				absConstant.getColumnTypes(),
				absConstant.getFront2col() );
		
		return (Object[])dataDAO.sqlQuerylistAllRetArrayByMap( sqlA.getSQL(), sqlA.getMap() ).get(0);
	}
	
	public MigTaskInfo browsePOJO(String jdata) {
		JSONObject jsonObject = JSONObject.fromObject(jdata);
		AbstractConstant absConstant = new ConstMigTaskInfo();

		HQLAssembleR hqlA = new HQLAssembleR(
				absConstant.getSQLDOC(),
				absConstant.getRead_SQL(), 
				jsonObject.getJSONObject("condition"),
				jsonObject.getJSONObject("operator"),
				absConstant.getColumnTypes(),
				absConstant.getFront2back() );
		
		return (MigTaskInfo)dataDAO.listAllByMap( "from MigTaskInfo t1 where "+hqlA.getWhereBackHQL(), hqlA.getMap() ).get(0);
	}
	public MigTaskInfo browsePOJOById(int id) {
//		return dataDAO.findById((short)id);
		return null;
	}
	public MigTaskInfo browsePOJOById(String id) {
		return dataDAO.findById( id );
	}
	/* (non-Javadoc)
	 * @see person.daizhongde.authority.spring.service.impl.MigTaskInfoService#delete(java.lang.String)
	 */
	@Override
	public int delete( String jdata ){
		JSONObject jsonObject = JSONObject.fromObject(jdata);
		AbstractConstant absConstant = new ConstMigTaskInfo();

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

	public void setDataDAO(MigTaskInfoDAO dataDAO) {
		this.dataDAO = dataDAO;
	}

	public void setComInsDAO(MigComInfoDAO comInfoDAO) {
		this.comInfoDAO = comInfoDAO;
	}

	public void setComInfoDAO(MigComInfoDAO comInfoDAO) {
		this.comInfoDAO = comInfoDAO;
	}

	public void setJobProcessService(MigJobProcessService jobProcessService) {
		this.jobProcessService = jobProcessService;
	}

	public void setJobParaDAO(MigJobParaDAO jobParaDAO) {
		this.jobParaDAO = jobParaDAO;
	}
	
	public void setWsClientService(MigWSClientService wsClientService) {
		this.wsClientService = wsClientService;
	}

	public void setComInfoSrv(MigComInfoService comInfoSrv) {
		this.comInfoSrv = comInfoSrv;
	}

	public void setComInsDAO(MigComInsDAO comInsDAO) {
		this.comInsDAO = comInsDAO;
	}

	public void setInsParaDAO(MigInsParaDAO insParaDAO) {
		this.insParaDAO = insParaDAO;
	}
	public void setBusiMemoryService(BusiMemoryService busiMemoryService) {
		this.busiMemoryService = busiMemoryService;
	}


	public static MigTaskInfoService getFromApplicationContext(
			ApplicationContext ctx) {
		return (MigTaskInfoService) ctx.getBean("migTaskInfoService");
	}
}
