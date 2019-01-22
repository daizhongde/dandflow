package person.daizhongde.migration.spring.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
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

import person.daizhongde.authority.hibernate.pojo.TAuthorityUser;
import person.daizhongde.migration.constant.ConstCommon;
import person.daizhongde.migration.constant.ConstMigAuditfMain;
import person.daizhongde.migration.constant.ConstMigJobInfo;
import person.daizhongde.migration.constant.Control;
import person.daizhongde.migration.constant.JobState;
import person.daizhongde.migration.constant.LauditFlag;
import person.daizhongde.migration.constant.NodeType;
import person.daizhongde.migration.constant.TableName;
import person.daizhongde.migration.constant.TaskState;
import person.daizhongde.migration.exception.BusinessException;
import person.daizhongde.migration.hibernate.dao.MigAuditvConfigDAO;
import person.daizhongde.migration.hibernate.dao.MigComInfoDAO;
import person.daizhongde.migration.hibernate.dao.MigJobContentDAO;
import person.daizhongde.migration.hibernate.dao.MigJobInfoDAO;
import person.daizhongde.migration.hibernate.dao.MigJobInsDAO;
import person.daizhongde.migration.hibernate.dao.MigJobParaDAO;
import person.daizhongde.migration.hibernate.dao.MigJobProcessDAO;
import person.daizhongde.migration.hibernate.dao.MigTaskInfoDAO;
import person.daizhongde.migration.hibernate.dto.AIDMResp;
import person.daizhongde.migration.hibernate.dto.MigJobNodeDto;
import person.daizhongde.migration.hibernate.dto.MigJobParaDto;
import person.daizhongde.migration.hibernate.pojo.MigAuditvConfig;
import person.daizhongde.migration.hibernate.pojo.MigComInfo;
import person.daizhongde.migration.hibernate.pojo.MigComIns;
import person.daizhongde.migration.hibernate.pojo.MigComInsId;
import person.daizhongde.migration.hibernate.pojo.MigControlTemplate;
import person.daizhongde.migration.hibernate.pojo.MigJobContent;
import person.daizhongde.migration.hibernate.pojo.MigJobInfo;
import person.daizhongde.migration.hibernate.pojo.MigJobIns;
import person.daizhongde.migration.hibernate.pojo.MigJobPara;
import person.daizhongde.migration.hibernate.pojo.MigJobParaId;
import person.daizhongde.migration.hibernate.pojo.MigJobProcess;
import person.daizhongde.migration.hibernate.pojo.MigTaskConfig;
import person.daizhongde.migration.hibernate.pojo.MigTaskInfo;
import person.daizhongde.migration.spring.service.BusiMemoryService;
import person.daizhongde.migration.spring.service.CommonQUERYService;
import person.daizhongde.migration.spring.service.CommonService;
import person.daizhongde.migration.spring.service.MigComInfoService;
import person.daizhongde.migration.spring.service.MigJobContentService;
import person.daizhongde.migration.spring.service.MigJobInfoService;
import person.daizhongde.migration.spring.service.MigJobParaService;
import person.daizhongde.migration.spring.service.MigTaskConfigService;
import person.daizhongde.migration.spring.service.MigTaskInfoService;
import person.daizhongde.migration.spring.service.PubService;
import person.daizhongde.migration.spring.service.restclient.MigRestClientService;
import person.daizhongde.migration.util.TemplateUtils;
import person.daizhongde.migration.util.VariableUtils;

public class MigJobInfoServiceImpl implements MigJobInfoService {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	private MigJobInfoDAO dataDAO;
	private MigJobContentDAO jobcontentDAO;
	private MigTaskInfoDAO taskInfoDAO;
	private MigComInfoDAO comInfoDAO;
	private MigJobProcessDAO processDAO;
	private MigJobParaDAO jobParaDAO;
	private MigJobInsDAO jobInsDAO;
	private PubService pubSrv;
	private MigJobContentService jobContentSrv;
	private MigTaskInfoService taskInfoSrv;
	private MigComInfoService comInfoSrv;
	private MigJobParaService jobPraraSrv;
	private MigRestClientService wsClientService;
	private BusiMemoryService busiMemoryService;
	private MigTaskConfigService taskConfigService;
	
	private MigAuditvConfigDAO auditvConfigDAO;
//	private MigAuditvConfigService auditvConfigService;
//	private MigAuditcConfigService auditcConfigService;
//	private MigAuditfConfigService auditfConfigService;
	private CommonQUERYService queryService;
	private CommonService commonService;
	
	public void setPubSrv(PubService pubSrv) {
		this.pubSrv = pubSrv;
	}

	public String getNewProcessId(){
		return pubSrv.get10ByteCode(TableName.mig_job_process);
	}
	
	public  String getNewJobId(){
		return pubSrv.get10ByteCode(TableName.mig_job_info);
	}
	
	public String getNewJobInsId(){
		return pubSrv.get10ByteCode(TableName.mig_job_ins);
	}
	
	public void checkJobInfo(String jobId){
		List<MigJobContent> contList=jobcontentDAO.findNodesByJob(jobId);
		
		for(MigJobContent jobContent:contList){	
			if(jobContent.getIsleaf() == 1){
				taskInfoSrv.checkTaskInfo(jobId, jobContent.getId().getNodeId());
			}else{
				recurCheckJobInfo(jobId, jobContent.getId().getNodeId());
			}
		}
	}
	
	private void recurCheckJobInfo(String jobId, String subJobId){
		List<MigJobContent> contList=jobcontentDAO.findNodesByJob(subJobId);
		
		for(MigJobContent jobContent:contList){
			if(jobContent.getIsleaf() == 1){
				taskInfoSrv.checkTaskInfo(jobId,jobContent.getId().getNodeId());
			}else{
				recurCheckJobInfo(jobId, jobContent.getId().getNodeId());
			}

		}
	}
	
	protected void instanceSubTask(String jobId,String nodeId, String currentlayerjob_author, String jobInsId ){
		List<MigJobPara>  jobparamPojolist = jobPraraSrv.findAllParaByNodeId(nodeId);
		List<MigJobParaDto>  jobparamDtolist = new ArrayList<MigJobParaDto>(jobparamPojolist.size());
		for( MigJobPara p : jobparamPojolist ){
			MigJobParaDto e= new MigJobParaDto();
			e.setNodeId(p.getId().getNodeId());
			e.setPara( p.getId().getPara() );
			e.setParaValue( p.getParaValue() );
			e.setParaName( p.getParaName());
			e.setParaType(p.getParaType() );
			
			jobparamDtolist.add(e);
		}
		//当前层job的变量参数实例化
		jobPraraSrv.instanceJobPara(jobInsId, nodeId, jobparamDtolist);
		
		List<MigJobContent> contList=jobcontentDAO.findNodesByJob(nodeId);
		log.debug("sub level task number:"+contList.size());
		for(MigJobContent tmp:contList){
			
			String comid ="";
			String controlid = "";
			String nodeRemark = "";
			String nodeName = "";
			String subjob_author = "";
			if( tmp.getIsleaf() == 1 ){
//				System.out.println("tmp.getId().getNodeId():"+tmp.getId().getNodeId());
				MigTaskInfo taskinfo = taskInfoDAO.findById(tmp.getId().getNodeId());
				if(null == taskinfo){
					throw new BusinessException("Can't find task<"+tmp.getId().getNodeId()+"> in Table mig_task_info !");
				}
//				System.out.println("taskinfo.getComId():"+taskinfo.getComId());
//				System.out.println("taskinfo.getControlId():"+taskinfo.getControlId());
				
				comid = taskinfo.getComId();
				controlid = taskinfo.getControlId();
				nodeRemark = taskinfo.getTaskRemark();
				nodeName = taskinfo.getTaskName();
				
				List<MigComInfo> rows = getCominfoByComIdWithBusinessAssembled( comid, controlid );
				//叶子的控件参数实例化
				comInfoSrv.instanceComInfo(jobInsId,comid, rows);
				
				//叶子的变量参数实例化
				jobPraraSrv.instanceJobPara(jobInsId, tmp.getId().getNodeId() );
			}else{
				MigJobInfo node_jobInfo = dataDAO.findById(tmp.getId().getNodeId());
				nodeRemark = node_jobInfo.getJobRemark();
				nodeName = node_jobInfo.getJobName();
				subjob_author = node_jobInfo.getJobAuthor();
			}
			
			MigJobProcess process=new MigJobProcess(this.getNewProcessId(), 
					jobInsId, tmp.getId().getJobId(), tmp.getId().getNodeId(),tmp.getIsleaf(),
					nodeName,nodeRemark,controlid,comid,
					TaskState.INITIAL, tmp.getIsleaf() == 1? currentlayerjob_author: subjob_author,
					tmp.getPrepos(), tmp.getPostpos(), 
					new Timestamp(System.currentTimeMillis()), "",tmp.getCoords() );
			
			processDAO.save(process);
			log.debug("subnodeId:"+tmp.getId().getNodeId());
			if( tmp.getIsleaf() == 0 ){
				instanceSubTask(jobId, tmp.getId().getNodeId(), subjob_author, jobInsId );
			}
		}
		log.debug("save sub level task success!");	
	}
	
	
	public boolean instanceJob(String jobId,int type, String jobInsName, Integer dryrunId, List<MigJobParaDto> jobparamlist, 
			TAuthorityUser user){

		if(jobId==null || "".equals(jobId.trim()) ||
				jobInsName==null || "".equals(jobInsName.trim())){
			throw new BusinessException("jobId <"+jobId+"> or jobInsName <"+jobInsName+"> is null !");
		}
		
		/*1、校验作业，判断是否是空作业，
		 * 如果是空作业就抛出异常
		 * 
		 * 
		 * 
		 */
		List<MigJobContent> nodes = jobcontentDAO.findNodesByJob_Recursive(jobId);
		boolean isblank = true;
		for( int i=0,j=nodes.size(); i<j; i++ ){
			if( nodes.get(i).getIsleaf() == NodeType.LEAF ){
				isblank = false;
				break;
			}
		}
		if(isblank){
			throw new BusinessException("Blank job can't instance!");
		}
		
		/* 2、校验参数
		 * 	2.1、无用变量 task+job
		 * 	2.2、变量无定义 task 控件参数中的变量在task级和job级都没有定义
		 * 
		 *  */
		Set<String> allVarAV = new HashSet<String>();
		HashSet<String> jobVarDV = new HashSet<String>();
		HashMap<String, String> jparaMap = new HashMap<String, String>();
		//jobparamlist's all parameter names are add to vector
		for( MigJobParaDto jobPara : jobparamlist ){
			jobVarDV.add( jobPara.getPara() );
			jparaMap.put( jobPara.getPara(), jobPara.getParaValue()  );
		}
		checkParameterInFront( jobId, jobVarDV, allVarAV );
		checkParameterInWebService(jobId, dryrunId, jparaMap);
		
		MigJobInfo jobInfo = dataDAO.findById( jobId );
		String currentlayerjob_author = jobInfo.getJobAuthor();
		
		String jobInsId = this.getNewJobInsId();
		MigJobIns migjobins = new MigJobIns(jobInsId, jobId, dryrunId, jobInsName, type,
				JobState.INITIAL, new Timestamp(System.currentTimeMillis()),
				user.getCUlogname(), "", "0", 0);

		jobInsDAO.save(migjobins);
		
		//task_config配罡实例化
//		taskConfigSrv.instanceTaskConfig(jobInsId, jobId);
		
		//最顶层job的变量参数实例化
		jobPraraSrv.instanceJobPara(jobInsId, jobId, jobparamlist);
		
		List<MigJobContent> contList=jobcontentDAO.findNodesByJob(jobId);
		log.debug("top level task number:"+contList.size());
		for(MigJobContent tmp:contList){
			
			String comid ="";
			String controlid = "";
			String nodeRemark = "";
			String nodeName = "";
			String subjob_author = "";
			if( tmp.getIsleaf() == NodeType.LEAF ){
				MigTaskInfo taskinfo = taskInfoDAO.findById(tmp.getId().getNodeId());
				
				comid = taskinfo.getComId();
				controlid = taskinfo.getControlId();
				nodeRemark = taskinfo.getTaskRemark();
				nodeName = taskinfo.getTaskName();
				//对叶子的参数进行实例化
//				comInfoSrv.instanceComInfo(jobInsId, comid);
				List<MigComInfo> rows = 
						getCominfoByComIdWithBusinessAssembled( comid, controlid );
				
//				System.out.println("##########  printer printjson #############");
//				Printer.printJSON(rows);
				comInfoSrv.instanceComInfo(jobInsId, comid, rows);
				
				//叶子的变量参数实例化
				jobPraraSrv.instanceJobPara(jobInsId, tmp.getId().getNodeId() );
			}else{
				MigJobInfo node_jobInfo = dataDAO.findById(tmp.getId().getNodeId());
				nodeRemark = node_jobInfo.getJobRemark();
				nodeName = node_jobInfo.getJobName();
				subjob_author = node_jobInfo.getJobAuthor();
			}
			
			MigJobProcess process=new MigJobProcess(this.getNewProcessId(), 
					jobInsId, tmp.getId().getJobId(), tmp.getId().getNodeId(),
					tmp.getIsleaf(),nodeName,nodeRemark,controlid,comid,
					TaskState.INITIAL,tmp.getIsleaf() == 1? currentlayerjob_author: subjob_author,
					tmp.getPrepos(), tmp.getPostpos(), 
					new Timestamp(System.currentTimeMillis()), "",tmp.getCoords());
					
			processDAO.save(process);
			if( tmp.getIsleaf() ==  NodeType.NOLEAF ){
				this.instanceSubTask(jobId, tmp.getId().getNodeId(), subjob_author, jobInsId );
			}
		}

		return true;
	}
	private boolean checkParameterInFront(String jobId, HashSet<String> jobVarDV, Set<String> allVarAV ){
//		if(true){
//			return true;
//		}
//		System.out.println("0----------------------------------------");
//		Printer.printJSON(allVarAV);
		/* 1、找出此job下所有的任务 
		 * 2、找出每个任务的控件参数
		 * 3、找出每个控件件参数中的变量
		 * 4、找出所有的job级变量
		 * 5、找出所有的任务级变量
		 * 
		 * D : defined variable
		 * A : These variables who appear in parameter  
		 * */
		
		List<MigTaskInfo> subTasks = jobcontentDAO.findDirectSubtask(jobId);
		for( MigTaskInfo taskInfo : subTasks ){
			String controlId = taskInfo.getControlId();
			
			//找出任务的所有变量
			Set<String> taskVarAV = new HashSet<String>();
			
			//在comInfoDAO中增加一个新的方法，此方法会替换business中的id为具体的业务
//			List<MigComInfo> comInfos = comInfoDAO.findByProperty("id.comId", taskInfo.getComId() );
			List<MigComInfo> comInfos = getCominfoByComIdWithBusinessAssembled( taskInfo.getComId(), taskInfo.getControlId() );
			
			for(MigComInfo comInfo : comInfos ){
				Set<String> comVarV =  VariableUtils.varStatistics( comInfo.getParaValue() );
				taskVarAV.addAll( comVarV );
				if( controlId.equalsIgnoreCase( Control.LEGALITY_AUDIT ) && comInfo.getId().getParaId()==1 )
				{
					String[] ids = comInfo.getParaValue().replaceAll("[{}]", "").split("\\;");
					
					taskVarAV.add("ENV");
					for(int i=0,j=ids.length; i<j; i++){
						int audit_id = new Integer( ids[i] );
						MigAuditvConfig c = auditvConfigDAO.findById(audit_id);
						if(null == c){
							continue;
//							throw new BusinessException("Config<"+audit_id+">record is not exists!");
						}
						String src_db_connect = c.getSrcDbConnect();
						Set<String> comVarV2 =  VariableUtils.varStatistics( src_db_connect );
						taskVarAV.addAll( comVarV2 );
						
						/* check config one by one  */
						//Variable exists in auditSQL, U : used
						Set<String> setU = new HashSet<String>();
						setU = VariableUtils.varStatistics( c.getSrcAuditSql() );
						//Variable defined in mig_sql_rep, D: defined
						Set<String> setD = new HashSet<String>();
						setD = VariableUtils.readVariables( c.getMigSqlRep() );
						//judge wether variable in use are all defined
						for( String  var : setU ){
							if( !setD.contains( var ) ){
								throw new BusinessException("The variable :" + var + 
										" in config<"+ audit_id +"> is not defined! ");
							}
						}
					}
				}
			}
			allVarAV.addAll(taskVarAV);
			
			//find all task level parameter
			List<MigJobPara> taskParas = jobParaDAO.findAllParaByNodeId( taskInfo.getTaskId() );
			
			//task's all parameter names are add to vector
			Set<String> taskVarDV = new HashSet<String>();
			for( MigJobPara taskPara : taskParas ){
				//judeg wether exist unuseable task level variable
//				if( !taskVarAV.contains( taskPara.getId().getPara() ) ){
//					throw new BusinessException("The variable :" + taskPara.getId().getPara() 
//							+ " in task<"+taskInfo.getTaskName()+"> has not been used! ");
//				}
				
				taskVarDV.add( taskPara.getId().getPara() );
			}
			Set<String> taskVarSearchRangeDV = new HashSet<String>();
			taskVarSearchRangeDV.addAll(jobVarDV);
			taskVarSearchRangeDV.addAll(taskVarDV);
			
			//judge wether task variable are all defined
			for( String  taskVar : taskVarAV ){
				if( !taskVarSearchRangeDV.contains( taskVar ) ){
					throw new BusinessException("The variable :" + taskVar + 
							" in task<"+taskInfo.getTaskName()+"> is not defined! ");
				}
			}
		}
		
		List<MigJobInfo> subjobs = jobcontentDAO.findDirectSubJob( jobId );

		for(MigJobInfo job : subjobs ){
//			Set<String> subJobVarDV = jobParaDAO.findAllParaByNodeId2(job.getJobId());
//			jobVarDV.addAll(subJobVarDV);
//			checkParameterInFront( job.getJobId(), jobVarDV, allVarAV );
			
			HashSet<String> jobVarDVClone = (HashSet<String>)jobVarDV.clone();
			Set<String> subJobVarDV = jobParaDAO.findAllParaByNodeId2(job.getJobId());
			jobVarDVClone.addAll(subJobVarDV);
			checkParameterInFront( job.getJobId(), jobVarDVClone, allVarAV );
		}
		
		//run to there indicate before code no exception
		
		//wether has job level variable not be used
//		for(String jobVar : jobVarDV){
//			if( ! allVarAV.contains(jobVar) ){
//				throw new BusinessException("The variable :" + jobVar
//						+ " in job has not been used! ");
//			}
//		}
		return true;
	}

	private boolean checkParameterInWebService(String jobId, Integer dryrunId, HashMap<String, String> jparaMap ){
//		if(true){
//			return true;
//		}
		
		/* 1、找出此job下所有的任务 
		 * 2、找出每个任务的控件参数
		 * 3、找出每个控件件参数中的变量
		 * 4、找出所有的job级变量
		 * 5、找出所有的任务级变量
		 * 
		 * D : defined variable
		 * A : These variables who appear in parameter  
		 * */

		List<MigTaskInfo> subTasks = jobcontentDAO.findDirectSubtask(jobId);
		for( MigTaskInfo taskInfo : subTasks ){
			String taskId = taskInfo.getTaskId();
			String comId = taskInfo.getComId();
			String controlId = taskInfo.getControlId();
			
//			List<MigControlTemplate> templates = controlTemplateDAO.findByProperty("id.controlId", controlId );
			List<MigControlTemplate> templates = busiMemoryService.getControlMap().get( controlId );
			Map<Integer, String> map =  TemplateUtils.getIdNameMap(templates);
			
			List<MigComIns> result = new ArrayList<MigComIns>();
			
			List<MigJobPara> paras = jobParaDAO.find( jobId, taskId);
			
			//任务级参数
			Map<String, String> tparaMap = new HashMap<String, String>();
			
			//只从这里取任务级的参数，作业级参数在传入的参数集合中取
			for(MigJobPara e : paras ){
				if(e.getId().getNodeId().equalsIgnoreCase(taskId)){
					tparaMap.put(e.getId().getPara(), e.getParaValue());
				}
			}
			//在comInfoDAO中增加一个新的方法，此方法会替换business中的id为具体的业务
//			List<MigComInfo> comInfos = comInfoDAO.getCominfoByComId( comId );
			List<MigComInfo> comInfos = getCominfoByComIdWithBusinessAssembled( taskInfo.getComId(), taskInfo.getControlId() );
			
			for(MigComInfo e : comInfos){

				//为了保持调用校验控件的接口一致，使用comins对象传递
				MigComIns tempE = new MigComIns(new MigComInsId());
				tempE.setComId( comId );
				tempE.setParaId(e.getId().getParaId());
				tempE.setParaValue(e.getParaValue());
				tempE.setParaName( map.get(e.getId().getParaId())  );
				
				result.add(tempE);
			}
			
			/*old code do parameter replace is deprecated, param replace is not java's task, It's zhourong's
			 * I  assemble com-para's value */
			String paraValue = "";
			//找出任务的所有变量
			Set<String> taskVarAV = new HashSet<String>();
			for(MigComInfo comInfo : comInfos ){
				Set<String> comVarV =  VariableUtils.varStatistics( comInfo.getParaValue() );
				taskVarAV.addAll( comVarV );
				
				if( controlId.equalsIgnoreCase( Control.LEGALITY_AUDIT ) && comInfo.getId().getParaId()==1 ){
					String[] ids = comInfo.getParaValue().replaceAll("[{}]", "").split("\\;");
					
					taskVarAV.add("ENV");
					for(int i=0,j=ids.length; i<j; i++){
						int audit_id = new Integer( ids[i] );
						MigAuditvConfig c = auditvConfigDAO.findById(audit_id);
						if(null == c){
							continue;
//							throw new BusinessException("Config<"+audit_id+">record is not exists!");
						}
						String src_db_connect = c.getSrcDbConnect();
						Set<String> comVarV2 =  VariableUtils.varStatistics( src_db_connect );
						taskVarAV.addAll( comVarV2 );
					}
				}
			}
			
			for(String varia : taskVarAV ){
				String value = tparaMap.containsKey(varia)?tparaMap.get(varia):jparaMap.get(varia);
				
//				if(varia.equalsIgnoreCase("TENANT")){
//					Printer.print(jparaMap);
//					Printer.print(tparaMap);
//					System.out.println("varia.equalsIgnoreCase TENANT :"+ value);
//				}
				if(StringUtils.isEmpty(value)){
					log.error( "任务-"+ taskId +"-中的-"+varia+"-参数没有定义！");
					throw new BusinessException("Variable<"+varia+">in task <"+ taskId +"> is not defined！"); 
				}
//				paraValue += varia + "=" + value + ";";
				paraValue += varia + "=[" + value + "];";
			}
			
			//because table com_ins is not contain para com-para, use para-id -1			
			//为了保持调用校验控件的接口一致，使用comins对象传递
			MigComIns tempE = new MigComIns(new MigComInsId());
			tempE.setComId( comId );
			tempE.setParaId( -1 );
			tempE.setParaName( "com-para" );
			tempE.setParaValue( paraValue );
			
			result.add(tempE);
			
			log.debug(" ############### invoke webservice check beginning ... "+taskId);
			
			//此时没有jobinsid，传递0值
//	        JSONObject retmsg = wsClientService.invoke( result, jobId, "0", dryrunId, taskId,"1", taskInfo.getControlId());
			AIDMResp retmsg = wsClientService.invoke( result, jobId, "0", dryrunId, taskId,"1", taskInfo.getControlId());
			
//	        if(false == retmsg.getBoolean("success")){
//	        	String errmsg = retmsg.getString("msg");
			 if(false == retmsg.isSuccess()){
		        String errmsg = retmsg.getMsg();
	        	throw new BusinessException("Check control error, errmsg:"+errmsg); 
	        }

			log.debug(" ############### invoke webservice check finished ! " +taskId);
		}
		
		List<MigJobInfo> subjobs = jobcontentDAO.findDirectSubJob( jobId );
		for(MigJobInfo job : subjobs ){
			HashMap<String, String> copyjparaMap = (HashMap)jparaMap.clone();
			Map<String, String> subJobParaMap = jobParaDAO.findAllParaByNodeId3(job.getJobId());
			copyjparaMap.putAll(subJobParaMap);
			
			checkParameterInWebService( job.getJobId(), dryrunId, copyjparaMap );
		}
		//run to there indicate before code no exception
				
		return true;
	}
	private String nvl(Object o){
		if(o==null)
			return "";
		else
			return o.toString();
	}
	private List<MigComInfo> getCominfoByComIdWithBusinessAssembled(String comId, String controlId ){
		List<MigComInfo> comInfos = comInfoDAO.getCominfoByComId( comId );
		List<MigComInfo> ret = new ArrayList<MigComInfo>(comInfos.size());
		AbstractConstant absConstant = new ConstCommon();
		
		for(MigComInfo e :comInfos){
			
			String paramName = busiMemoryService
					.getParamNameByControlIdandParamId( controlId, e.getId().getParaId() );
			
			MigComInfo newComInfo = new MigComInfo();
			newComInfo.setId( e.getId() );
			
			if( paramName.equalsIgnoreCase("business") 
					&& StringUtils.isNotEmpty(e.getParaValue())
					&& !"null".equalsIgnoreCase(e.getParaValue()) ){
				String idstr = e.getParaValue();
				String[] id_arr = idstr.split("\\,");
				List<String> id_listS = Arrays.asList(id_arr);
				List<Integer> id_list = new ArrayList<Integer>();
				for( String id : id_listS ){
					id_list.add( new Integer(id) );
				}
				
				String business = "";
				switch( controlId ){
					case "con001": 
						List<MigTaskConfig> rows1 = taskConfigService.findRowsByIdList(id_list);
		            	for(MigTaskConfig row : rows1 ){
		             		business += "{" + nvl(row.getMigSrc()) + "," + nvl(row.getMigWhere()) + "," + nvl(row.getMigDst()) + ","
		             		+ nvl(row.getMigDstConn())+"};";
		            	}
						break;
					case "con002": 
						List<MigTaskConfig> rows2 = taskConfigService.findRowsByIdList(id_list);
		            	for(MigTaskConfig row : rows2 ){
		             		business += "{" + nvl(row.getMigSrc()) + "," + nvl(row.getMigDst()) + ","
		             		+ nvl(row.getMigDstConn())+"};";
		            	}
						break;
					case "con003": 
						List<MigTaskConfig> rows3 = taskConfigService.findRowsByIdList(id_list);
		            	for(MigTaskConfig row : rows3 ){
		             		business += "{" + nvl(row.getMigSrc()) + "," + nvl(row.getMigSrcConn()) + "," + nvl(row.getMigWhere()) + ","
		             		+ nvl(row.getMigDst())+"};";
		            	}
						break;
					case "con004": 
						List<MigTaskConfig> rows4 = taskConfigService.findRowsByIdList(id_list);
		            	for(MigTaskConfig row : rows4 ){
		             		business += "{" + nvl(row.getMigSrc()) + "," + nvl(row.getMigSrcConn()) + "," + nvl(row.getMigWhere()) + ","
		             		+ nvl(row.getMigDst()) + ","+ nvl(row.getMigDstConn())+"};";
		            	}
						break;
					case "con005": log.error("没有这个控件对应的business,ID："+controlId); break;
					case "con006": log.error("没有这个控件对应的business,ID："+controlId); break;
					case "con007": //auditv
						String tableName7 = "mig_auditv_config";
						Map condition7 = new HashMap();
						condition7.put("audit_id", id_list );
						condition7.put("audit_flag", LauditFlag.AUDIT );
						
						Map operator7 = new HashMap();
						operator7.put( "audit_id", Operator.IN );
						operator7.put( "audit_flag", Operator.EQUAL );
						
						SQLAssembleQ sqlA7 = new SQLAssembleQ(
								null,
								"select "+commonService.getSelectSQLColumns().get(tableName7.toLowerCase())+" from "+tableName7,
								condition7,
								operator7,
								absConstant.getColumnTypes(),
								null
							);
						List<Map> rows7 = queryService.getRowsInMap(sqlA7);
		            	for(Map row : rows7 ){
//		            		String laudit_flag = row.get("audit_flag").toString();
//		            		if( laudit_flag.trim().toUpperCase().equalsIgnoreCase(LauditFlag.AUDIT) ){
	//		             		business += "{" + nvl(row.get("src_db_connect")) + "&" + nvl(row.get("src_audit_sql")) + "&" + nvl(row.get("audit_value")) + "&"
	//		                     		+ nvl(row.get("operator")) + "&" + nvl(row.get("audit_id")) + "&" + nvl(row.get("domain"))+"&"
	//		                     		+ nvl(row.get("table_name")) + "&" + nvl(row.get("audit_name")) +"};";
			            		business += "{" + row.get("audit_id") +"};";
//		            		}
		            	}
						break;
					case "con008": log.error("没有这个控件对应的business,ID："+controlId); break;
					case "con009": //auditc
						String tableName9 = "mig_auditc_consistency";
						Map condition9 = new HashMap();
						condition9.put("audit_id", id_list );
						
						Map operator9 = new HashMap();
						operator9.put( "audit_id", Operator.IN );
						
						SQLAssembleQ sqlA9 = new SQLAssembleQ(
								null,
								"select "+commonService.getSelectSQLColumns().get(tableName9.toLowerCase())+" from "+tableName9,
								condition9, 
								operator9,
								absConstant.getColumnTypes(),
								null
							);
						List<Map> rows9 = queryService.getRowsInMap(sqlA9);
						//{sql语句2$[conn2]};
		            	for(Map row : rows9 ){
		            		business += "{" + nvl(row.get("mig_sql")) + "$["+nvl(row.get("sql_db"))+"]"+ "$"+nvl(row.get("audit_id"))+"};";
		            	}
						break;
					case "con010": //auditf
						Map condition0 = new HashMap();
						condition0.put("faudit_id", id_list );
						
						Map operator0 = new HashMap();
						operator0.put( "faudit_id", Operator.IN );
						
						AbstractConstant absConstant0 = new ConstMigAuditfMain();
						SQLAssembleQ sqlA0 = new SQLAssembleQ(
								absConstant0.getSQLDOC(),
								absConstant0.getSQLDOC().getQuery().get("queryAuditfConfig").getSQL(),
								condition0, 
								operator0,
								absConstant0.getColumnTypes(),
								absConstant0.getFront2col()
							);
						List<Map> rows0 = queryService.getRowsInMap(sqlA0);
		            	for(Map row : rows0 ){
//		            		Integer faudit_status = new Integer( row.get("faudit_status").toString() );
//		            		if(faudit_status==FauditStatus.AUDIT){
	            			String sub_desc = String.valueOf( row.get("sub_desc")).replaceAll("\\],\\[","][");
		             		business += "{" + nvl(row.get("faudit_id")) + "," + nvl(row.get("domain"))
		             				+ "," + nvl(row.get("faudit_srctable_name")) + "," + nvl(row.get("faudit_srctable_conn")) + ","
		             		+ nvl(row.get("faudit_dsttable_name")) + "," + nvl(row.get("faudit_dsttable_conn")) + "," + nvl(sub_desc) + "};";
//			            	}
		            	}
						break;
					default : 
						log.error("没有这个控件ID："+controlId);
						throw new BusinessException("没有这个控件ID："+controlId);
				}
				newComInfo.setParaValue( business );
				ret.add(newComInfo);
			}else{
				
				if( StringUtils.isEmpty(e.getParaValue())
					|| "null".equalsIgnoreCase(e.getParaValue()) ){
					e.setParaValue("");
				}
				ret.add(e);
			}
		}
		return ret;
	}
		
	public  MigJobInfo findById(String jobId){
		return dataDAO.findById(jobId);	
	}
	
	
	public List<MigJobInfo> getAllJobs(TAuthorityUser user){
		return dataDAO.findAll();
	}
	
	public String saveJobInAll( List<MigJobNodeDto> nodes,  MigJobNodeDto job ){
		String jobId = getNewJobId();
		//待完善
		/*
		 * 大概步骤如下：
	 	0、写作业信息表
	 	
		1、保存节点信息
			1.1、判断节点类型，如果是任务进入下一不步，如果是作业进入1.3
			1.2、是任务
				1.2.1、保存任务参数到作业参数表
				1.2.2、获取控件ID(前台不传comId)，保存任务对应的控件参数到组件信息表
				1.2.3、保存任务信息到任务信息表并写comId(任务ID是前台传过来的)
				1.2.4、写作业内容表
			1.3、是作业
				1.3.1、写作业内容表
			1.4、循环执行1直到所有的节点信息都已经保存
			*/
		return jobId;
	}

//	public void deleteJobbyId(String jobId,TAuthorityUser user){
//		if(jobId==null || jobId.equals("")){
//			throw new BusinessException("jobId <"+jobId+"> is null or empty !");
//		}
//		//1:判断作业是否被其它作业引用，如果有被引用就不能删
//		List<MigJobContent> contents = jobcontentDAO.findAllJobNodeByNodeId(jobId);
//		
//		if(contents.size()>0){
//			throw new BusinessException("作业 <"+jobId+"> 有被引用,不能删除!");
//		}
//		jobContentSrv.removeNode(jobId);
//		
//	}
//	public void deleteJobbyIdRecursion(String jobId,TAuthorityUser user){
//		if(jobId==null || jobId.equals("")){
//			throw new BusinessException("jobId <"+jobId+"> is null or empty !");
//		}
//		//1:判断作业是否被其它作业引用，如果有被引用就不能删
//		List<MigJobContent> contents = jobcontentDAO.findAllJobNodeByNodeId(jobId);
//		
//		if(contents.size()>0){
//			throw new BusinessException("Job <"+jobId+"> have been referenced, It can't delete!");
//		}
//		jobContentSrv.removeNode(jobId);
//	}
	public void deleteJobInJobbyIdRecursion(String jobId,String curJobId, TAuthorityUser user){
		if(jobId==null || jobId.equals("")){
			throw new BusinessException("jobId <"+jobId+"> is null or empty !");
		}
		//1:判断作业是否被其它作业引用，如果有被引用就不能删
		List<MigJobContent> contents = jobcontentDAO.findAllJobNodeByNodeId(jobId);
		
		if(contents.size()>0){
			if(contents.size()==1 && contents.get(0).getId().getJobId().equalsIgnoreCase(curJobId)){
				
			}else{
				throw new BusinessException("Job <"+jobId+"> have been referenced by another job, It can't delete!");
			}
			
		}
		jobContentSrv.removeNode(jobId, user);
	}
	public void deleteJob(List<String> jobId,TAuthorityUser user){
		
		//1:判断作业是否被其它作业引用，如果有被引用就不能删
		List<MigJobContent> contents = jobcontentDAO.findAllJobNodeByNodeId(jobId);
		
		if(contents.size()>0){
//			List<String> ids = new ArrayList(contents.size());
//			for(MigJobContent e : contents){
//				ids.add(e.getId().getNodeId());
//			}
			throw new BusinessException("Job <"+jobId+"> have been referenced, It can't delete!");
		}
		jobContentSrv.removeNode(jobId, user);
	}
	
	public String addJobRetId( String jobName, int type, String remark ,TAuthorityUser user){
		String newJobId = getNewJobId();
		
		String tempjobName = jobName;
		for(int i=1; i>-1; i++){
			Long count = dataDAO.findCountByJobName(jobName);
			if(count==0){
				break;
			}else{
				jobName = tempjobName+" ("+i+")";
			}
		}
		
		dataDAO.addJob(jobName, type, remark, user.getCUlogname(), newJobId );
		
		return newJobId;
	}
//	public  String newJob(TAuthorityUser user){
//
//		MigJobInfo jobInfo=new MigJobInfo(getNewJobId(),null, null,
//				user.getCUname(), null,new Timestamp(System.currentTimeMillis()),"0","0", null);
//		dataDAO.save(jobInfo);
//		return jobInfo.getJobId();
//	}
	public  void updateJobNameById(String jobId, String jobName,TAuthorityUser user){
		if(jobId==null || "".equals(jobId)){
			throw new BusinessException("jobId <"+jobId+"> is null or empty !");
		}
		dataDAO.updateColumnById(jobId, "JOB_NAME", jobName);
	}
	
	public void updateJobRemarkById(String jobId, String remark,TAuthorityUser user){
		if(jobId==null || "".equals(jobId)){
			throw new BusinessException("jobId <"+jobId+"> is null or empty !");
		}
		dataDAO.updateColumnById(jobId, "JOB_REMARK", remark);	
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
		AbstractConstant absConstant = new ConstMigJobInfo();

		SQLAssembleC sqlA = new SQLAssembleC(
				absConstant.getTableName(),
				jsonObject.getJSONObject("data"),
				absConstant.getColumnTypes(),
				absConstant.getFront2col()
				);
		
		return dataDAO.sqlQueryExeUByMap(sqlA.getSQL(), sqlA.getMap());
	}
	

	public void setJobcontentDAO(MigJobContentDAO jobcontentDAO) {
		this.jobcontentDAO = jobcontentDAO;
	}

	public int add(Map data) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int addRetId(String jdata) {
		JSONObject jsonObject = JSONObject.fromObject(jdata);
		AbstractConstant absConstant = new ConstMigJobInfo();

		SQLAssembleC sqlA = new SQLAssembleC(
				absConstant.getTableName(),
				jsonObject.getJSONObject("data"),
				absConstant.getColumnTypes(),
				absConstant.getFront2col()
				);
		
		return dataDAO.sqlQueryExeUByMap(sqlA.getSQL(), sqlA.getMap());
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.authority.spring.service.impl.MigJobInfoService#addWithId(java.lang.String)
	 */
	@Override
	public int addWithId( String jdata ){
		JSONObject jsonObject = JSONObject.fromObject(jdata);
		AbstractConstant absConstant = new ConstMigJobInfo();

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
		AbstractConstant absConstant = new ConstMigJobInfo();
		
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
		AbstractConstant absConstant = new ConstMigJobInfo();

		SQLAssembleC sqlA = new SQLAssembleC(
				absConstant.getTableName(),
				jsonObject.getJSONObject("data"),
				absConstant.getColumnTypes(),
				absConstant.getFront2col()
				);
		
		MigJobInfo pojo = new MigJobInfo();
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
	 * @see person.daizhongde.authority.spring.service.impl.MigJobInfoService#modify(java.lang.String)
	 */
	@Override
	public int modify( String jdata ){
		JSONObject jsonObject = JSONObject.fromObject(jdata);
		AbstractConstant absConstant = new ConstMigJobInfo();

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
	
	public void modifyWithJobParam( String jdata, List<MigJobParaDto> jobparas ){
		JSONObject jsonObject = JSONObject.fromObject(jdata);
		String jobId = jsonObject.getJSONObject("condition").getString("id");
		AbstractConstant absConstant = new ConstMigJobInfo();

		Map data = jsonObject.getJSONObject("data");
//		String jobName = data.get("name").toString();
//		String tempjobName = jobName;
//		for(int i=1; i>-1; i++){
//			Long count = dataDAO.findCountByJobName(jobName);
//			if(count==0){
//				break;
//			}else{
//				jobName = tempjobName+" ("+i+")";
//			}
//		}
//		data.put("name", jobName);
		
		SQLAssembleU sqlA = new SQLAssembleU(
				absConstant.getSQLDOC(),
				absConstant.getTableName(),
				data,
				jsonObject.getJSONObject("algorithm"),
				jsonObject.getJSONObject("condition"),
				jsonObject.getJSONObject("operator"),
				absConstant.getColumnTypes(),
				absConstant.getFront2col() );
		
		dataDAO.sqlQueryExeUByMap(sqlA.getSQL(), sqlA.getMap());
		
		//删除jobpara信息
		jobParaDAO.deleteByNodeId( jobId );
		
		if(null==jobparas || jobparas.size()==0){
			return;
		}
		
		//写入jobpara信息
		for(MigJobParaDto e : jobparas){
			
			MigJobPara jobPara = new MigJobPara(new MigJobParaId(e.getNodeId(),e.getPara()),
					e.getParaName(),e.getParaType(),e.getParaValue());
			jobParaDAO.save(jobPara);
		}
	}
	@Override
	public Map browse(String jdata) {
		JSONObject jsonObject = JSONObject.fromObject(jdata);
		AbstractConstant absConstant = new ConstMigJobInfo();

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
		AbstractConstant absConstant = new ConstMigJobInfo();
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
		AbstractConstant absConstant = new ConstMigJobInfo();
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
		AbstractConstant absConstant = new ConstMigJobInfo();

		SQLAssembleR sqlA = new SQLAssembleR(
				absConstant.getSQLDOC(),
				absConstant.getRead_SQL(), 
				jsonObject.getJSONObject("condition"),
				jsonObject.getJSONObject("operator"),
				absConstant.getColumnTypes(),
				absConstant.getFront2col() );
		
		return (Object[])dataDAO.sqlQuerylistAllRetArrayByMap( sqlA.getSQL(), sqlA.getMap() ).get(0);
	}
	
	public MigJobInfo browsePOJO(String jdata) {
		JSONObject jsonObject = JSONObject.fromObject(jdata);
		AbstractConstant absConstant = new ConstMigJobInfo();

		HQLAssembleR hqlA = new HQLAssembleR(
				absConstant.getSQLDOC(),
				absConstant.getRead_SQL(), 
				jsonObject.getJSONObject("condition"),
				jsonObject.getJSONObject("operator"),
				absConstant.getColumnTypes(),
				absConstant.getFront2back() );
		
		return (MigJobInfo)dataDAO.listAllByMap( "from MigJobInfo t1 where "+hqlA.getWhereBackHQL(), hqlA.getMap() ).get(0);
	}
	public MigJobInfo browsePOJOById(int id) {
//		return dataDAO.findById((short)id);
		return null;
	}
	public MigJobInfo browsePOJOById(String id) {
		return dataDAO.findById( id );
//		return null;
	}
	/* (non-Javadoc)
	 * @see person.daizhongde.authority.spring.service.impl.MigJobInfoService#delete(java.lang.String)
	 */
	@Override
	public int delete( String jdata ){
		JSONObject jsonObject = JSONObject.fromObject(jdata);
		AbstractConstant absConstant = new ConstMigJobInfo();

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

	public void setDataDAO(MigJobInfoDAO dataDAO) {
		this.dataDAO = dataDAO;
	}

	public void setProcessDAO(MigJobProcessDAO processDAO) {
		this.processDAO = processDAO;
	}

	public void setJobParaDAO(MigJobParaDAO jobParaDAO) {
		this.jobParaDAO = jobParaDAO;
	}

	public MigTaskInfoDAO getTaskInfoDAO() {
		return taskInfoDAO;
	}

	public void setTaskInfoDAO(MigTaskInfoDAO taskInfoDAO) {
		this.taskInfoDAO = taskInfoDAO;
	}


	public MigComInfoDAO getcomInfoDAO() {
		return comInfoDAO;
	}

	public void setcomInfoDAO(MigComInfoDAO comInfoDAO) {
		this.comInfoDAO = comInfoDAO;
	}

	public void setJobContentSrv(MigJobContentService jobContentSrv) {
		this.jobContentSrv = jobContentSrv;
	}

	public void setTaskInfoSrv(MigTaskInfoService taskInfoSrv) {
		this.taskInfoSrv = taskInfoSrv;
	}

	public void setJobInsDAO(MigJobInsDAO jobInsDAO) {
		this.jobInsDAO = jobInsDAO;
	}
	
	
	public void setComInfoSrv(MigComInfoService comInfoSrv) {
		this.comInfoSrv = comInfoSrv;
	}

	public void setJobPraraSrv(MigJobParaService jobPraraSrv) {
		this.jobPraraSrv = jobPraraSrv;
	}

	public void setWsClientService(MigRestClientService wsClientService) {
		this.wsClientService = wsClientService;
	}

	public void setComInfoDAO(MigComInfoDAO comInfoDAO) {
		this.comInfoDAO = comInfoDAO;
	}

	public void setBusiMemoryService(BusiMemoryService busiMemoryService) {
		this.busiMemoryService = busiMemoryService;
	}

	public void setTaskConfigService(MigTaskConfigService taskConfigService) {
		this.taskConfigService = taskConfigService;
	}
	public void setQueryService(CommonQUERYService queryService) {
		this.queryService = queryService;
	}
	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}
	
	public void setAuditvConfigDAO(MigAuditvConfigDAO auditvConfigDAO) {
		this.auditvConfigDAO = auditvConfigDAO;
	}

	public static MigJobInfoService getFromApplicationContext(
			ApplicationContext ctx) {
		return (MigJobInfoService) ctx.getBean("migJobInfoService");
	}
}
