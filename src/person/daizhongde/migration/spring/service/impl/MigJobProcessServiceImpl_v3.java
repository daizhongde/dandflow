package person.daizhongde.migration.spring.service.impl;

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
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.context.support.WebApplicationContextUtils;

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
import person.daizhongde.virtue.util.test.Printer;

import person.daizhongde.authority.hibernate.dao.TAuthorityUserDAO;
import person.daizhongde.authority.hibernate.pojo.TAuthorityUser;
import person.daizhongde.migration.constant.ConstMigJobProcess;
import person.daizhongde.migration.constant.Control;
import person.daizhongde.migration.constant.JobLockState;
import person.daizhongde.migration.constant.JobState;
import person.daizhongde.migration.constant.NodeType;
import person.daizhongde.migration.constant.TaskState;
import person.daizhongde.migration.exception.AccountEmailException;
import person.daizhongde.migration.exception.BusinessException;
import person.daizhongde.migration.hibernate.dao.MigAuditvConfigDAO;
import person.daizhongde.migration.hibernate.dao.MigComInsDAO;
import person.daizhongde.migration.hibernate.dao.MigInsParaDAO;
import person.daizhongde.migration.hibernate.dao.MigJobInsDAO;
import person.daizhongde.migration.hibernate.dao.MigJobProcessDAO;
import person.daizhongde.migration.hibernate.dao.MigJobStatDAO;
import person.daizhongde.migration.hibernate.dto.AIDMResp;
import person.daizhongde.migration.hibernate.pojo.MigAuditvConfig;
import person.daizhongde.migration.hibernate.pojo.MigComIns;
import person.daizhongde.migration.hibernate.pojo.MigComInsId;
import person.daizhongde.migration.hibernate.pojo.MigControlTemplate;
import person.daizhongde.migration.hibernate.pojo.MigInsPara;
import person.daizhongde.migration.hibernate.pojo.MigJobIns;
import person.daizhongde.migration.hibernate.pojo.MigJobProcess;
import person.daizhongde.migration.spring.service.AccountEmailService;
import person.daizhongde.migration.spring.service.BusiMemoryService;
import person.daizhongde.migration.spring.service.MigJobProcessService;
import person.daizhongde.migration.spring.service.PubService;
import person.daizhongde.migration.spring.service.restclient.MigRestClientService;
import person.daizhongde.migration.spring.service.wsclient.MigWSClientService;
import person.daizhongde.migration.util.JobSemaphore;
import person.daizhongde.migration.util.StaticThreadResource;
import person.daizhongde.migration.util.TemplateUtils;
import person.daizhongde.migration.util.VariableUtils;
/**
 * v3
 * best edition, Being perfected, wait to finish......
 * 
 * JobSemaphore can control the second layer's task that no prepos and postpos 
 * @author daizd
 *
 */
public class MigJobProcessServiceImpl_v3 implements MigJobProcessService {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	private MigJobProcessDAO dataDAO;
	private MigComInsDAO comInsDAO;
	private MigInsParaDAO insParaDAO;
	private MigJobInsDAO jobInsDAO;
	
//	private MigJobStatDAO statDAO;
	private PubService pubService;
	
//	MigWSClientService wsClientService;
	MigRestClientService wsClientService2;
	
	private MigAuditvConfigDAO auditvConfigDAO;
//	private Semaphore semp = new Semaphore(1);
	private BusiMemoryService busiMemoryService;
	
	/* Open after config special email  */
//	private AccountEmailService accountEmailService;
	private boolean taskErrorEmail;
	private TAuthorityUserDAO userDAO;
	
	public List findByProperty(String insid, String jobid){
		return dataDAO.findByProperty2(insid, jobid );
	}
//	public void startJobNoContext(String jobInsId) throws InterruptedException {
//		System.out.println("Schedule startJobNoContext jobInsId:"+jobInsId);
//		ApplicationContext ctx = null;
//		this.getFromApplicationContext(ctx);
//		startJob(jobInsId);
//	}
	/**
	 * 用于作业定时调度
	 * user传实例author
	 * @param jobInsId
	 * @throws InterruptedException
	 */
	public void startJob(String jobInsId) throws InterruptedException {
		System.out.println("Schedule startJob jobInsId:"+jobInsId);
//		String logname = jobInsDAO.findById(jobInsId).getAuthor();
		MigJobIns t = jobInsDAO.findById(jobInsId);
		String logname = t.getAuthor();
		List list = userDAO.findByCUlogname(logname);
		TAuthorityUser user = (list==null || list.size()==0)?null:(TAuthorityUser)list.get(0);
		startJob(jobInsId, user);
	}
//	@Async
	public void startJob(String jobInsId, TAuthorityUser user) throws InterruptedException {
//		job状态|【0】未执行；【1】正在执行；【2】执行完成；【3】暂停执行;【5】暂停中(人工);【6】暂停中(出错);【-1】执行出错
		
		MigJobIns jobIns = jobInsDAO.findById( jobInsId );
		
		//判断job是否具备启动的条件
		checkJob(jobIns);
	
		String[] args = new String[3];
		args[0]="GZ";//GZ 过程
		args[1]= user.getCUname();
		args[2]= jobInsId;
//		log.info("{} user {} start job {}......", args);
		
		List<MigJobProcess> processes = dataDAO.findProcessesWhosePreisNullAndIsTop(jobInsId, jobIns.getJobId());
		
		HashMap<String, String> jparaMap = insParaDAO.findByNodeId2(jobInsId, jobIns.getJobId());
		
		if(processes.size() == 0){
			//空的job启动后直接置job状态为成功
			jobInsDAO.sqlQueryUpdateJobInsState( jobInsId,
					JobState.FINISH, JobLockState.UNLOCK, 0,
					JobState.INITIAL, JobLockState.UNLOCK, 0 );
			
		}else{
			for(MigJobProcess e : processes ){
				createAndStartAThread(e, 0, "", jobIns.getDryrunId(), jparaMap );
			}
		}
		
	}
//	@Async
	public void pauseJob(String jobInsId, TAuthorityUser user) {
		String[] args = new String[3];
		args[0]="GZ";//GZ 过程
		args[1]= user.getCUname();
		args[2]= jobInsId;
//		log.info("{} 用户 {} 开始暂停作业 {}......", args);				
		
		MigJobIns jobIns = jobInsDAO.findById( jobInsId );
		
		try{
			jobInsDAO.sqlQueryUpdateJobInsState(jobInsId, 
					JobState.PAUSING_MANUAL, JobLockState.LOCK, 1,
					jobIns.getStatus(), jobIns.getLockStatus(), 1 );
		} catch (Exception e) {
	    	e.printStackTrace();
			throw new BusinessException("The job state is not executing !");
		}
		log.info("{} 用户 {} 暂停作业 {} 完成！", args);
	}
	
	public void stopTaskSignal(String jobInsId, String nodeId, TAuthorityUser user){
		MigJobIns jobIns = jobInsDAO.findById(jobInsId);
		wsClientService2.invokeSingle( jobIns.getJobId(), jobInsId, 0, nodeId, 1);
	}
	
	public List<MigJobProcess> monitorJob(String jobInsId, String jobId, TAuthorityUser user){
//		List list = new ArrayList();
		
//		System.out.println("jobInsId:"+jobInsId +", jobId:"+jobId);
		List<MigJobProcess> processes = dataDAO.findByJobInsIdAndJobId(jobInsId, jobId);

//		log.debug("111");
//		Printer.printJSON(processes);
//		log.debug("222");
		return processes;
	}
	
	/**
	 * 校验instance是否具备启动的条件，instance执行完成且没加锁,如果具备启动条件实例就会在这个方法中被启动
	 * <p>
	 * job状态|【0】未执行；【1】正在执行；【2】执行完成；【3】暂停执行;【-1】执行出错<br>
	 * 作业为以下状态禁止启动：<br>&nbsp;&nbsp;正在执行，执行完成，执行出错，暂停中（人工）<br>
	 * 作业锁定状态为以下状态禁止启动：<br>&nbsp;&nbsp;已锁定
	 * @param jobId
	 */
	private void checkJob(MigJobIns jobIns){
		String jobInsId = jobIns.getJobInsId();
		String status = jobIns.getStatus();
		
		//判断job是否锁定
		if( JobLockState.LOCK.equalsIgnoreCase( jobIns.getLockStatus() ) ){
			
			throw new BusinessException("The job <"+jobInsId+"> is locked, can't start !");
		}
		
		//正在执行，正在暂停，完成		
		if( JobState.EXECUTING.equalsIgnoreCase(status) 
				|| JobState.PAUSING_MANUAL.equalsIgnoreCase(status)
				|| JobState.FINISH.equalsIgnoreCase(status) ){
			
			throw new BusinessException("The job <"+jobInsId+"> status is <"+status+">, can't start !");
		}
		
		//暂停，出错
		if( JobState.ERROR.equalsIgnoreCase(status) 
				|| JobState.PAUSE.equalsIgnoreCase(status) ){
			
			//更新job状态为正在执行
			if ( jobInsDAO.sqlQueryUpdateJobInsState( jobInsId,
					JobState.EXECUTING, JobLockState.LOCK, 1,
					jobIns.getStatus(), JobLockState.UNLOCK, 0 ) != 1 ){
				//发现更新不了代表有线程启动了这个作业，所以当前进程需要中止
				throw new BusinessException("Data synchronization, others are updating the data, update failed!");
			}
		}else if( JobState.INITIAL.equalsIgnoreCase(status) ){
			//更新job状态为正在执行
			if ( jobInsDAO.sqlQueryUpdateJobInsState_init2Running( jobInsId ) != 1 ){
				//发现更新不了代表有线程启动了这个作业，所以当前进程需要中止
				throw new BusinessException("Data synchronization, others are updating the data, update failed!");
			}
		}
		
		//初始未执行
	}
	
	
	
//	/**
//	 * 执行叶子任务
//	 * @param jobId
//	 * @param taskId
//	 * @throws InterruptedException 
//	 */
//	@SuppressWarnings("unchecked")
//	private JSONObject executeLeafTask(MigJobProcess proc, Integer dryrunId, int pThread, 
//			String subtasklink, Map<String, String> jparaMap) throws InterruptedException{
//		String jobInsId = proc.getJobInsId();
//		MigJobIns jobIns = jobInsDAO.findById(jobInsId);
//		
//		String jobId = jobIns.getJobId();
//		String nodeId = proc.getNodeId();
//		String comId = proc.getComId();
//		String controlId = proc.getControlId();
//		
//		log.debug("Thread " + pThread + ", find out MigComIns instance property with comid<" + comId +">");
//
//		List<MigComIns> result = new ArrayList<MigComIns>();
//				
//		//任务级参数
//		Map<String, String> tparaMap = insParaDAO.findByNodeId2(jobInsId, nodeId);
//				
//		log.debug("Thread " + pThread + ", find out MigControl instance property with controlid<" + controlId+">");		
//		
//		List<MigControlTemplate> templates = busiMemoryService.getControlMap().get( controlId );
//		Map<Integer, String> map =  TemplateUtils.getIdNameMap(templates);
//		
//		List<MigComIns> comInss = comInsDAO.getCominsByJobInsIdAndComId(jobInsId, comId );
//		
//		for(MigComIns e : comInss){
//			e.setParaName( map.get(e.getId().getParaId())  );
//			result.add(e);
//		}
//		result.addAll(comInss);
//		
//		/*old code do parameter replace is deprecated, param replace is not java's task, It's zhourong's
//		 * I  assemble com-para's value */
//		String paraValue = "";
//		//找出任务的所有变量
//		Set<String> taskVarAV = new HashSet<String>();
//		for(MigComIns comIns : comInss ){
//			Set<String> comVarV =  VariableUtils.varStatistics( comIns.getParaValue() );
//			taskVarAV.addAll( comVarV );
//			
//			if( controlId.equalsIgnoreCase( Control.LEGALITY_AUDIT ) && comIns.getId().getParaId()==1 ){
//				String[] ids = comIns.getParaValue().replaceAll("[{}]", "").split("\\;");
//				taskVarAV.add("ENV");
//				
//				for(int i=0,j=ids.length; i<j; i++){
//					int audit_id = new Integer( ids[i] );
//					MigAuditvConfig c = auditvConfigDAO.findById(audit_id);
//					if(null == c){
//						continue;
////						throw new BusinessException("Config<"+audit_id+">record is not exists!");
//					}
//					String src_db_connect = c.getSrcDbConnect();
//					
//					//连接串可使用任务或作业级变量
//					Set<String> comVarV2 =  VariableUtils.varStatistics( src_db_connect );
//					taskVarAV.addAll( comVarV2 );
//					
//					/* check config one by one--配置中的sql只能使用相应的配置中定义的变量  */
//					//Variable exists in auditSQL, U : used
//					Set<String> setU = new HashSet<String>();
//					setU = VariableUtils.varStatistics( c.getSrcAuditSql() );
//					//Variable defined in mig_sql_rep, D: defined
//					Set<String> setD = new HashSet<String>();
//					setD = VariableUtils.readVariables( c.getMigSqlRep() );
//					//judge wether variable in use are all defined
//					for( String  var : setU ){
//						if( !setD.contains( var ) ){
//							throw new BusinessException("The variable :" + var + 
//									" in config<"+ audit_id +"> is not defined! ");
//						}
//					}
//				}
//			}
//		}
//		for(String varia : taskVarAV ){
//			
//			String value = tparaMap.containsKey(varia)?tparaMap.get(varia):jparaMap.get(varia);
//			log.debug("executeLeafTask value:"+value);
//			
//			//&& !controlId.equalsIgnoreCase( Control.LEGALITY_AUDIT ) 
//			if(StringUtils.isEmpty(value) ){
//				log.error("实例<"+jobInsId+">中,任务<"+nodeId+">中的<"+varia+">参数没有定义！");
//				throw new BusinessException("Variable <"+varia+"> in task <"+nodeId+"> of job instance <"+jobInsId+"> is not defined！"); 
//			}
//			paraValue += varia + "=[" + value + "];";
//		}
//		
//		//because table com_ins is not contain para com-para, use para-id -1
//		MigComInsId id = new MigComInsId( jobInsId, comId, -1 );
//		MigComIns e = new MigComIns( id );
//		e.setParaName( "com-para" );
//		e.setParaValue( paraValue );
//		result.add(e);
//
//		//invoke webservice
//		log.debug("Thread " + pThread + ", ############### invoke webservice task beginning ... "+nodeId);
////        Thread.sleep( 10 * 1000);
//		//commented by daizd
////        JSONObject retmsg = wsClientService.invoke( result, jobId, jobInsId, nodeId, "0", proc.getControlId());
////		Date beginTime = new Date();
//		//update by daizd for log write
////		statDAO.writeLog2Stat();
//		JSONObject oResponse = wsClientService.invoke( result, proc.getJobId(), jobInsId, dryrunId, nodeId, "0", proc.getControlId());
////		Date endTime = new Date();
//		
//		log.debug("Thread " + pThread + ", ############### invoke webservice task finished ! " +nodeId);
//		
//		if(false == oResponse.getBoolean("success") && taskErrorEmail )
//		{//WebService返回失败发送邮件到作者邮箱：三个可选 1：公司邮件  2：qq邮箱  3：手机号qq邮件  先选择第二种
//			String author = proc.getAuthor();
//			List list = userDAO.findByCUlogname(author);
//			TAuthorityUser user = list.size()==0? null : (TAuthorityUser)list.get(0);
//			String qq = user.getCUqq();
//			String mobile = user.getCUphone();
//			String msg = oResponse.getString("msg");
//					
//			String to = (StringUtils.isEmpty( qq ) ? mobile : qq) + "@qq.com";
//			String subject = "AIDM【实例出错】-实例【"+jobIns.getJobInsName()+"】，任务【"+proc.getNodeName()+"】";
//			String htmlText = (null == msg?"":msg);
//			/* 下面注释掉的代码可以配置了专门的邮箱后启用
//			 * 作用：给相关脚本的作者发邮件，并告知出错信息  */
////			try {
////				accountEmailService.sendMail(to, subject, htmlText);
////			} catch (AccountEmailException e1) {
////				e1.printStackTrace();
////			}
//		}
//		
//		return oResponse;
//		
//		/*Map condition = new HashMap(8);
//		condition.put("job_Id", jobId);
//		condition.put("job_Ins_Id", jobInsId);
//		condition.put("dryrun_Id", dryrunId);
//		condition.put("node_Id", nodeId);
//		
//		condition.put("begin_Time", beginTime);
//		condition.put("end_Time", endTime);
//		String msg = retmsg.getString("msg");
//		msg=msg.length()>1024 ? msg.substring(0, 1024)+"..." : msg;
//        if(false == retmsg.getBoolean("success")){
//        	
//        	condition.put("status", -1);
//    		condition.put("remark", msg );
//        	statDAO.RN_WritelogSQL( condition);
//        	
//        	throw new BusinessException("Execute control error, errmsg:"+msg); 
//        }
//        condition.put("status", 2 );
//		condition.put("remark", msg );
//        statDAO.RN_WritelogSQL( condition);*/
//	}
	/**
	 * 执行叶子任务
	 * @param jobId
	 * @param taskId
	 * @throws InterruptedException 
	 */
	@SuppressWarnings("unchecked")
	private JSONObject executeLeafTask(MigJobProcess proc, Integer dryrunId, int pThread, 
			String subtasklink, Map<String, String> jparaMap) throws InterruptedException{
		String jobInsId = proc.getJobInsId();
		MigJobIns jobIns = jobInsDAO.findById(jobInsId);
		
		String jobId = jobIns.getJobId();
		String nodeId = proc.getNodeId();
		String comId = proc.getComId();
		String controlId = proc.getControlId();
		
		log.debug("Thread " + pThread + ", find out MigComIns instance property with comid<" + comId +">");

		List<MigComIns> result = new ArrayList<MigComIns>();
				
		//任务级参数
		Map<String, String> tparaMap = insParaDAO.findByNodeId2(jobInsId, nodeId);
				
		log.debug("Thread " + pThread + ", find out MigControl instance property with controlid<" + controlId+">");		
		
		List<MigControlTemplate> templates = busiMemoryService.getControlMap().get( controlId );
		Map<Integer, String> map =  TemplateUtils.getIdNameMap(templates);
		
		List<MigComIns> comInss = comInsDAO.getCominsByJobInsIdAndComId(jobInsId, comId );
		
		for(MigComIns e : comInss){
			e.setParaName( map.get(e.getId().getParaId())  );
			result.add(e);
		}
		result.addAll(comInss);
		
		/*old code do parameter replace is deprecated, param replace is not java's task, It's zhourong's
		 * I  assemble com-para's value */
		String paraValue = "";
		//找出任务的所有变量
		Set<String> taskVarAV = new HashSet<String>();
		for(MigComIns comIns : comInss ){
			Set<String> comVarV =  VariableUtils.varStatistics( comIns.getParaValue() );
			taskVarAV.addAll( comVarV );
			
			if( controlId.equalsIgnoreCase( Control.LEGALITY_AUDIT ) && comIns.getId().getParaId()==1 ){
				String[] ids = comIns.getParaValue().replaceAll("[{}]", "").split("\\;");
				taskVarAV.add("ENV");
				
				for(int i=0,j=ids.length; i<j; i++){
					int audit_id = new Integer( ids[i] );
					MigAuditvConfig c = auditvConfigDAO.findById(audit_id);
					if(null == c){
						continue;
//						throw new BusinessException("Config<"+audit_id+">record is not exists!");
					}
					String src_db_connect = c.getSrcDbConnect();
					
					//连接串可使用任务或作业级变量
					Set<String> comVarV2 =  VariableUtils.varStatistics( src_db_connect );
					taskVarAV.addAll( comVarV2 );
					
					/* check config one by one--配置中的sql只能使用相应的配置中定义的变量  */
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
		for(String varia : taskVarAV ){
			
			String value = tparaMap.containsKey(varia)?tparaMap.get(varia):jparaMap.get(varia);
			log.debug("executeLeafTask value:"+value);
			
			//&& !controlId.equalsIgnoreCase( Control.LEGALITY_AUDIT ) 
			if(StringUtils.isEmpty(value) ){
				log.error("实例<"+jobInsId+">中,任务<"+nodeId+">中的<"+varia+">参数没有定义！");
				throw new BusinessException("Variable <"+varia+"> in task <"+nodeId+"> of job instance <"+jobInsId+"> is not defined！"); 
			}
			paraValue += varia + "=[" + value + "];";
		}
		
		//because table com_ins is not contain para com-para, use para-id -1
		MigComInsId id = new MigComInsId( jobInsId, comId, -1 );
		MigComIns e = new MigComIns( id );
		e.setParaName( "com-para" );
		e.setParaValue( paraValue );
		result.add(e);

		//invoke webservice
		log.debug("Thread " + pThread + ", ###### 2 ######### invoke webservice task beginning ... "+nodeId);
		AIDMResp oResponse = wsClientService2.invoke( result, proc.getJobId(), jobInsId, dryrunId, nodeId, "0", proc.getControlId());

		log.debug("Thread " + pThread + ", ###### 2 ######### invoke webservice task finished ! " +nodeId);
		
		if(false == oResponse.isSuccess() && taskErrorEmail )
		{//WebService返回失败发送邮件到作者邮箱：三个可选 1：公司邮件  2：qq邮箱  3：手机号qq邮件  先选择第二种
			String author = proc.getAuthor();
			List list = userDAO.findByCUlogname(author);
			TAuthorityUser user = list.size()==0? null : (TAuthorityUser)list.get(0);
			String qq = user.getCUqq();
			String mobile = user.getCUphone();
			String msg = oResponse.getMsg();
					
			String to = (StringUtils.isEmpty( qq ) ? mobile : qq) + "@qq.com";
			String subject = "AIDM【实例出错】-实例【"+jobIns.getJobInsName()+"】，任务【"+proc.getNodeName()+"】";
			String htmlText = (null == msg?"":msg);
			/* 下面注释掉的代码可以配置了专门的邮箱后启用
			 * 作用：给相关脚本的作者发邮件，并告知出错信息  */
//			try {
//				accountEmailService.sendMail(to, subject, htmlText);
//			} catch (AccountEmailException e1) {
//				e1.printStackTrace();
//			}
		}
//		return oResponse;
		return JSONObject.fromObject(oResponse);
	}
	/**
	 * 获取组件的参数值<p>
	 * substitute com parameter value, 一个com parameter里面可能有多个job parameter
	 * @param jobParas
	 * @param taskParas
	 * @param value
	 * @return
	 */
	private Object getComParameterValue( List<MigInsPara> insParas, List<MigInsPara> nodeParas, String value){
		char[] b = value.toCharArray();
		String param="";
		String ret = "";
		boolean paramFlag = false;
		boolean paramBeg = false;
		
//		$param1:
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
				paramFlag = false;
			}else if(paramBeg){
					param+=e;
			}else{
					ret+=e;
			}
		}
		return ret;
	}

	/**
	 * 获取job参数的值<p>
	 * 不查询数据库,在传入的集合里面查,实际为了减少数据库查询采用这种
	 * @param jobId
	 * @param taskId
	 * @param param
	 * @return
	 */
	private Object getJobParameterValue( List<MigInsPara> insParas, List<MigInsPara> nodeParas, String param){
		for(MigInsPara e : nodeParas){
			if( param.equalsIgnoreCase( e.getId().getPara() ) )
				return e.getParaValue();
		}
		
		for(MigInsPara e : insParas){
			if( param.equalsIgnoreCase( e.getId().getPara() ) )
				return e.getParaValue();
		}
		
//		for(MigInsPara e : nodeParas){
//			if( param.equalsIgnoreCase( e.getParaName() ) )
//				return e.getParaValue();
//			else{
//				for(MigInsPara e2 : insParas){
//					if( param.equalsIgnoreCase( e2.getParaName() ) )
//						return e2.getParaValue();
//				}
//			}
//		}
		throw new BusinessException("找不到对应的参数值<"+param+">");
	}
	/**
	 * 只适用于叶子任务
	 * @param proc
	 * @return
	 */
	private boolean preposFinished(MigJobProcess proc){
		String prepos = proc.getPrepos();
//		MigJobInfo job = jobInfoDAO.findById( proc.getJobId() );
//		
//		//如果job是暂停中，则执行后置，让线程函数去更新job状态
//		if( job.getStatus().equalsIgnoreCase(JobState.PAUSING_MANUAL)){
//			return true;
//		}
		
		String jobInsId = proc.getJobInsId();
		
		if( null == prepos || "".equalsIgnoreCase( prepos )  ){
			return true;
		}
		String[] arr = prepos.split("\\|");
		List<String> nodelist = Arrays.asList(arr);
		for(String e : nodelist){
		MigJobProcess jobProcess = dataDAO.findByJobInsIdAndNode(jobInsId, e);
//		for(MigJobProcess e : list){
			if( TaskState.FINISH != jobProcess.getStatus() && TaskState.SKIP != jobProcess.getStatus() ){
				return false;
			}
		}
		return true;
	}
	/**
	 * 只适用于非叶子任务
	 * @param proc
	 * @return
	 * @throws InterruptedException 
	 */
	private boolean allsubtaskFinished(int pThread, MigJobProcess proc) throws InterruptedException{

		List<MigJobProcess> endTasks = dataDAO.findProcessesWhosePostisNull( proc.getJobInsId(), proc.getNodeId() );
		
		for(MigJobProcess e : endTasks){
			if( TaskState.FINISH != e.getStatus() && TaskState.SKIP != e.getStatus()){
				log.debug("Thread " + pThread + ", proc_id<"+e.getProcessId()+"> node<"+e.getNodeId()+"> has not yet been completed !" );
				return false;
			}
		}
		return true;
	}
	
	private boolean isTopNode(MigJobProcess proc) throws InterruptedException{

		MigJobProcess node = dataDAO.findByJobInsIdAndNode( proc.getJobInsId(), proc.getNodeId() );
		
		MigJobIns jobIns = jobInsDAO.findById(proc.getJobInsId());
		if(proc.getJobId().equalsIgnoreCase(jobIns.getJobId())){
			return true;
		}
		return false;
	}
	
//	private boolean canExecute(MigJobProcess proc) throws InterruptedException{
//		MigTaskInfo taskInfo = taskInfoDAO.findById( proc.getSubtask() );
//		if(taskInfo.getTaskNode().equalsIgnoreCase( ProcessState.PARENT ) ){
//			return allsubtaskFinished(proc);
//		}else{
//			return preposFinished(proc);
//		}
//	}
	
	
	/** 
	 * proc执行完成之后需要做什么<p>
	 * 
	 * 线程thread 执行prepos的后置<br>
	 * 递归-针对后面这段代码需写递归函数<br>
	 * 
	 * 叶子,非叶子都能触发后置任务的执行，
	 * (但最根本还是叶子任务触发的，只有非叶子任务的最后一个叶子任务执行完成才能触发非叶子任务的后置任务执行)
	 * 此类分两种情况：
	 *     1:有后置的-当前任务subtask所在的job(可以是子job)，有后置任务
	 *     2:没有后置的-当前任务subtask所在的job(job)没有后置了
	 *       2类又分两种情况:
	 *       2.1: task != jobid
	 *         2.1.1:查找对应的task(父任务)
	 *         2.1.2:如果task中的末任务都执行完成，更新task的状态
	 *         2.1.3:递归
	 *       2.2: end, task=jobid
	 * @throws InterruptedException 
	 *         
	 */
	private void recursiveEXEPostpos(MigJobProcess proc, int thread, String nodelink, Integer dryrunId, HashMap<String,String> jparaMap ) throws InterruptedException{

//		log.debug("Thread " + thread + ", Begin find "+proc.getSubtask() +" postpos task..."  );
//		if(proc.getProcessId().equalsIgnoreCase("PP00000733")){
//			System.out.println("PP00000733来了！");
//		}
		String postpos = proc.getPostpos();
		String jobInsId = proc.getJobInsId();
		if( null != postpos && !"".equalsIgnoreCase( postpos.trim() ) ){
			
			//有后置的，执行后置任务
			log.debug("Thread " +thread+ ", "+proc.getNodeId() +" have some postpos task ..."  );
			String[] arr = postpos.split("\\|");
			List<String> list = Arrays.asList(arr);	
			
			for (String e : list){
				MigJobProcess processes = dataDAO.findByJobInsIdAndNode(jobInsId, e);
			            
//			for(MigJobProcess e : processes ){
				
				boolean judgePreposFinished = false;				
				//任务状态是否为正在执行？
				if( processes.getStatus() != TaskState.EXECUTING  ){
					
					//需判断前置
					judgePreposFinished = true;				
				}else{
					//任务为正在执行
//					MigTaskInfo taskInfo = taskInfoDAO.findById( e.getNodeId() );
					if( processes.getIsleaf() == 0 ){
						//父节点，需判断前置
						judgePreposFinished = true;
						
					}else{
						//子节点，do nothing
						log.debug("Thread " +thread+ ", <" + proc.getNodeId() +"> postpos <"+ processes.getNodeId() + "> stauts <" + processes.getStatus() + "> do not have to perform ...");
					}
				}
				
				if( judgePreposFinished == true ){
					if( preposFinished( processes ) ){
						//只要后置任务不为正在执行，或为正在执行但是父节点，都启动线程，让线程函数去处理任务状态
						log.debug("Thread " +thread+ ", <"+proc.getNodeId() +"> begin to start postpos task <"+processes.getNodeId()+"> ..."  );
//						JobSemaphore.maxThreadNum.release();//释放
						createAndStartAThread(processes, thread, nodelink, dryrunId, jparaMap  );
					}else{
						log.debug("Thread " +thread+ ", <"+proc.getNodeId() +"> postpos <"+processes.getNodeId()+"> prepos do not finished, task deal done. ");
					}
				}
			}
		}else if( !isTopNode(proc) ){
			
			//没有后置的,不是顶层task
			log.debug("Thread "+ thread + ", proc_id<" + proc.getProcessId()
					+ ">,node<" + proc.getNodeId() + "> have no postpos and not the top task,nodelink<" + nodelink + ">");
			
			MigJobProcess parent = dataDAO.findByJobInsIdAndNode( proc.getJobInsId(), proc.getJobId() );
			
			log.debug("Thread "+ thread + ", proc_id<" + parent.getProcessId()
					+ ">,node<" + parent.getNodeId() + "> is <" + proc.getNodeId() + "> father node,nodelink<" + nodelink + ">");
									
			if( allsubtaskFinished(thread, parent) ){
											
				if ( dataDAO.sqlQueryUpdateProcessState_2Finish( parent.getProcessId() ) !=1 ){
					System.out.println("###########   "+parent.getProcessId()+"    ##############");
					return;			
				}
				
				log.debug("Thread "+ thread + ", proc_id<" + parent.getProcessId()
						+ ">,node<" + parent.getNodeId() + "> All the subtasks have finished executing,nodelink<" + nodelink + ">");

				//只有在这里需要重置job parameter map,因为只有这里才会有可能跳到其它层去执行任务
				List<String> ret = new ArrayList<String>();
				ret = recursiveGetlist(jobInsId, parent.getNodeId(), ret );

				HashMap<String, String> newJparaMap = new HashMap<String, String>();
				
				if(ret.size()>0){
					for(int i=ret.size()-1; i>=0; i--){
						Map<String, String> temp = insParaDAO.findByNodeId2(jobInsId, ret.get(i) );
						newJparaMap.putAll( temp );
					}
				}
				recursiveEXEPostpos(parent, thread, nodelink, dryrunId, newJparaMap );
				
			}else{
				log.debug("Thread "+ thread + ", proc_id<" + parent.getProcessId()
						+ ">,node<" + parent.getNodeId() + "nodelink<" + nodelink + "> Contains no completed subtasks ! do nothing.");
			}
		}else{
			
			//没有后置，是顶层task
			log.debug("Thread " +thread+ ", node<"+proc.getNodeId() +"> have no postpos and is the top task."  );
			
			//判断是否所有没有后置的顶层任务都执行完成,where prepos.jobid=prepos.taskid
			//where prepos.jobid=prepos.taskid
			List<MigJobProcess> endTasks = dataDAO.findProcessesWhosePostisNull( proc.getJobInsId(), proc.getJobId() );
			
			//below can comment
			boolean allFinished = true;
			for(MigJobProcess e : endTasks){
				if( TaskState.FINISH != e.getStatus() && TaskState.SKIP != e.getStatus() ){
					log.debug("Thread "+ thread + ", proc_id<" + e.getProcessId()
							+ ">,node<" + e.getNodeId() + ">,nodelink<" + nodelink + "> no complete, do nothing.");
					allFinished = false;
				}
			}
			if(allFinished){
				//job finished
				MigJobIns jobIns = jobInsDAO.findById( proc.getJobInsId() );
				jobInsDAO.sqlQueryUpdateJobInsState_RunningorError2Finish( proc.getJobInsId() );
				log.debug("Thread " +thread+ ", job "+proc.getJobInsId() + " finished!");
				//end a job process finished! Congratulations!
			}
		}
	}
	private List<String> recursiveGetlist(String jobInsId, String nodeId, List<String> ret ){
		MigJobProcess temp = dataDAO.findByJobInsIdAndNode( jobInsId, nodeId );
		if( null == temp ){
			return ret;
		}else{
			ret.add(temp.getJobId());
			return recursiveGetlist(jobInsId, temp.getJobId(), ret );
		}
	}
	/**
	 * 作业出错时用
	 * <p>
	 * 递归更新包(父任务或作业)状态
	 * @param e
	 */
	private void recursiveUpdateTaskPkgStatus( MigJobProcess e, int taskState, int pThread, String nodelink  ) throws InterruptedException{
		String procId = e.getProcessId();
		String jobInsId = e.getJobInsId();
		String jobId = e.getJobId();
		String nodeId = e.getNodeId(); 
		
//		MigJobInfo job = jobInfoDAO.findById( jobId );

//		int taskIngCount = dataDAO.findCountByStatus( TaskState.EXECUTING, jobId, task );
		
//		if( taskIngCount == 0 )
//		{//如果当前任务所在的任务包没有正在执行的任务就将此任务包状态设置为暂停
//			log.debug("Thread "+ pThread + ": proc_id<" + procId+"> subtask<" + subtask
//					+ "> subtasklink<" + subtasklink +"> "
//					+ subtask + "是任务包<" + task + ">最后一个被暂停的任务，将任务包<" + task + ">的状态置为暂停！" );
		
		dataDAO.sqlQueryUpdateProcessState( procId,
				taskState,e.getStatus() );
		
		if( !isTopNode(e) ){
			//不是顶层节点			
			MigJobProcess pkg = dataDAO.findByJobInsIdAndNode( jobInsId, jobId );
			
			recursiveUpdateTaskPkgStatus( pkg, taskState, pThread, nodelink );
		}
		else
		{
//先屏蔽job出错状态--2015/12/17去掉此屏蔽			
			//顶层节点
			String insStatus = jobInsDAO.findStatusById( jobInsId );
			MigJobIns ins = jobInsDAO.findById(jobInsId);
			if( !insStatus.equalsIgnoreCase(JobState.PAUSE) && !insStatus.equalsIgnoreCase(JobState.PAUSING_MANUAL) ){
				
				//人为暂停、暂停中状态级别最高
				jobInsDAO.sqlQueryUpdateJobInsState( jobInsId,
						JobState.ERROR, JobLockState.LOCK, 1,
						JobState.EXECUTING, JobLockState.LOCK, 1 );
			}
//先屏蔽job出错状态	--2015/12/17去掉此屏蔽			
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
		AbstractConstant absConstant = new ConstMigJobProcess();

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
		AbstractConstant absConstant = new ConstMigJobProcess();

		SQLAssembleC sqlA = new SQLAssembleC(
				absConstant.getTableName(),
				jsonObject.getJSONObject("data"),
				absConstant.getColumnTypes(),
				absConstant.getFront2col()
				);
		
		return dataDAO.sqlQueryExeUByMap(sqlA.getSQL(), sqlA.getMap());
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.authority.spring.service.impl.MigJobProcessService#addWithId(java.lang.String)
	 */
	@Override
	public int addWithId( String jdata ){
		JSONObject jsonObject = JSONObject.fromObject(jdata);
		AbstractConstant absConstant = new ConstMigJobProcess();

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
		AbstractConstant absConstant = new ConstMigJobProcess();
		
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
		AbstractConstant absConstant = new ConstMigJobProcess();

		SQLAssembleC sqlA = new SQLAssembleC(
				absConstant.getTableName(),
				jsonObject.getJSONObject("data"),
				absConstant.getColumnTypes(),
				absConstant.getFront2col()
				);
		
		MigJobProcess pojo = new MigJobProcess();
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
	 * @see person.daizhongde.authority.spring.service.impl.MigJobProcessService#modify(java.lang.String)
	 */
	@Override
	public int modify( String jdata ){
		JSONObject jsonObject = JSONObject.fromObject(jdata);
		AbstractConstant absConstant = new ConstMigJobProcess();

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
		AbstractConstant absConstant = new ConstMigJobProcess();

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
		AbstractConstant absConstant = new ConstMigJobProcess();
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
		AbstractConstant absConstant = new ConstMigJobProcess();
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
		AbstractConstant absConstant = new ConstMigJobProcess();

		SQLAssembleR sqlA = new SQLAssembleR(
				absConstant.getSQLDOC(),
				absConstant.getRead_SQL(), 
				jsonObject.getJSONObject("condition"),
				jsonObject.getJSONObject("operator"),
				absConstant.getColumnTypes(),
				absConstant.getFront2col() );
		
		return (Object[])dataDAO.sqlQuerylistAllRetArrayByMap( sqlA.getSQL(), sqlA.getMap() ).get(0);
	}
	
	public MigJobProcess browsePOJO(String jdata) {
		JSONObject jsonObject = JSONObject.fromObject(jdata);
		AbstractConstant absConstant = new ConstMigJobProcess();

		HQLAssembleR hqlA = new HQLAssembleR(
				absConstant.getSQLDOC(),
				absConstant.getRead_SQL(), 
				jsonObject.getJSONObject("condition"),
				jsonObject.getJSONObject("operator"),
				absConstant.getColumnTypes(),
				absConstant.getFront2back() );
		
		return (MigJobProcess)dataDAO.listAllByMap( "from MigJobProcess t1 where "+hqlA.getWhereBackHQL(), hqlA.getMap() ).get(0);
	}
	public MigJobProcess browsePOJOById(long id) {
//		return dataDAO.findById((short)id);
		return null;
	}
	public MigJobProcess browsePOJOById(String id) {
		return dataDAO.findById( id );
//		return null;
	}
	/* (non-Javadoc)
	 * @see person.daizhongde.authority.spring.service.impl.MigJobProcessService#delete(java.lang.String)
	 */
	@Override
	public int delete( String jdata ){
		JSONObject jsonObject = JSONObject.fromObject(jdata);
		AbstractConstant absConstant = new ConstMigJobProcess();

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
	
//	public MigJobInfoDAO getJobInfoDAO() {
//			return jobInfoDAO;
//		}

//	public void setJobInfoDAO(MigJobInfoDAO jobInfoDAO) {
//		this.jobInfoDAO = jobInfoDAO;
//	}

/**
 * task执行线程类
 * <p>
 * 执行前需要进行校验
 * @author daizd
 *
 */
	private class MigTaskThread implements Runnable {
		private int i;
		private int pThread;
		
		private MigJobProcess proc;
		
		private String jobInsId;
		private String processId;
		private Integer dryrunId;
		private HashMap<String, String> jparaMap;
		
		private String node;
		private String nodelink;
		private Integer taskStatus;
		private int status;
		
		public MigTaskThread( MigJobProcess proc, int i, int pThread, String nodelink, 
				Integer dryrunId, HashMap<String, String> jparaMap ) {
			this.i = i;
			this.pThread = pThread;
			this.proc = proc;
			
			this.jobInsId = proc.getJobInsId();
			this.processId = proc.getProcessId();
			this.node = proc.getNodeId();
			this.status = proc.getStatus();
			
			this.nodelink = nodelink;
			this.dryrunId = dryrunId;
//			this.jparaMap = jparaMap;
			this.jparaMap = (HashMap)jparaMap.clone();
			
			String[] splitWord;
			//拼装任务执行路由
			if(!StringUtils.isEmpty( nodelink )){
				splitWord = this.nodelink.split("\\.");
				if( !splitWord[splitWord.length-1].equalsIgnoreCase(proc.getNodeId()) ){
					this.nodelink =  this.nodelink + "." + proc.getNodeId();
				}
			}else{
				this.nodelink = proc.getNodeId();
			}
		}
		
		
		/* (non-Javadoc)
		 * @see java.lang.Runnable#run()
		 */
		public void run() {
			
			log.debug( "Thread "+this.i+", Running proc_id<" + this.processId + ">,node<" + this.node + ">,nodelink<" + this.nodelink + ">");

			try {
				String jobInsStatus = jobInsDAO.findStatusById( jobInsId );
				
				/* 分三种情况：
				 *  1、执行完成、暂停执行、初始未执行
				 *  2、暂停中
				 *  3、出错、正在执行
				 */
				if( JobState.FINISH.equalsIgnoreCase(jobInsStatus)
						|| JobState.PAUSE.equalsIgnoreCase(jobInsStatus)
						|| JobState.INITIAL.equalsIgnoreCase(jobInsStatus)
				){
					log.debug("Thread " + this.i + ", job status<" + jobInsStatus + "> can not start !" );
					JobSemaphore.maxThreadNum.release();//释放
					return;
					
				}else if( JobState.PAUSING_MANUAL.equalsIgnoreCase(jobInsStatus) ){
					
					log.debug("Thread " + this.i + ", proc_id<" + this.processId + ">,node<" + this.node + ">,nodelink<" + this.nodelink + ">, Find the job status is pausing_manual ... ");
					
					List<MigJobProcess> processes = dataDAO.findProcessesByStatus(TaskState.EXECUTING, jobInsId);
					boolean executingSubTask = false;
					for(MigJobProcess e : processes ){
						
						if( e.getIsleaf() == 1 ){
							executingSubTask = true;
							break;
						}
					}
					
					if( false == executingSubTask ){
						
						log.debug("Thread " + this.i + ", proc_id<" + this.processId + ">,node<" + this.node + ">,nodelink<" + this.nodelink + "> is the last task, update job status to pause. " );
						
						//暂停中最后一个完成的任务，须更新job状态为暂停
						jobInsDAO.sqlQueryUpdateJobInsState( jobInsId,
								JobState.PAUSE, JobLockState.UNLOCK, 0,
								JobState.PAUSING_MANUAL, JobLockState.LOCK, 1 );
					}else{
					
						log.debug("Thread " + this.i + ", proc_id<" + this.processId + ">,node<" + this.node + ">,nodelink<" + this.nodelink + "> is not the last task, task deal done. " );		
					}
				}else if( JobState.ERROR.equalsIgnoreCase(jobInsStatus)
						|| JobState.EXECUTING.equalsIgnoreCase(jobInsStatus)){
					
					//取节点的实时状态
					this.taskStatus = dataDAO.findStatusById(this.processId);
					
					if( TaskState.PAUSE == this.taskStatus ){
						
						Thread.sleep( 5 * 1000);
						JobSemaphore.maxThreadNum.release();//释放
						createAndStartAThread(proc, this.i, nodelink, dryrunId,jparaMap );
						
					}else if( TaskState.FINISH == this.taskStatus || TaskState.SKIP == this.taskStatus ){

						//执行后置
						recursiveEXEPostpos(proc, this.i, nodelink, dryrunId, jparaMap );
					}else if( TaskState.INITIAL == this.taskStatus 
							|| TaskState.EXECUTING == this.taskStatus
							|| TaskState.ERROR == this.taskStatus ){

						if( proc.getIsleaf() == NodeType.NOLEAF ){
							
							/* 目前对于task出错时，暂停Job后再执行时，KPI时间有问题。
							 * 更新Process表中的begin_time时，增加判断：
							 * 1、当节点为跳过，已成功执行，则不更新，不对子节点处理；
							 * 2、当节点为出错，正在执行，则不更新，并对子节点进行处理；*
							 * 3、当节点为初始，则更新。
							 * */
							if(TaskState.EXECUTING == this.taskStatus
								|| TaskState.ERROR == this.taskStatus){
								//允许父节点状态为正在执行时，执行其子节点
								dataDAO.sqlQueryUpdateProcessState_2Running2( processId );
							}else{
								//允许父节点状态为正在执行时，执行其子节点
								dataDAO.sqlQueryUpdateProcessState_2Running( processId );
							}
							
							/* 下面四行是针对空作业包添加的 */
							List<MigJobProcess> list = dataDAO.findByJobInsIdAndJobId(jobInsId, node);
							if(list.size()==0){
								dataDAO.sqlQueryUpdateProcessState_Running2Finish( processId, "blank job!"  );
								//叶子任务执行完成之后的工作:查找后置并检查后置启动的条件是否满足，如果满足就启动
								recursiveEXEPostpos( proc, i, nodelink, dryrunId, jparaMap );
							}
							
							/* executeNoLeafTask(jobId, taskInfo.getTaskId() );  */
							log.debug("Thread "+ this.i + ", proc_id<" + this.processId 
									+ ">,node<" + this.node + ">,nodelink<" + this.nodelink + "> Not leaves, begin to start its subtasks ...");
							
							List<MigJobProcess> processes = dataDAO.findProcessesWhosePreisNull( jobInsId, node );
						
							Map<String, String> subJobParamMap = insParaDAO.findByNodeId2(jobInsId, node );

							jparaMap.putAll(subJobParamMap);
							
							if(processes!=null && processes.size()!=0)JobSemaphore.maxThreadNum.release();//释放
							for(MigJobProcess e : processes ){
								createAndStartAThread(e, this.i, nodelink, dryrunId,jparaMap  );
							}
							//end of parent
							
						}else if( proc.getIsleaf() == NodeType.LEAF ){

							log.debug("Thread "+ this.i + ", proc_id<" + this.processId 
									+ ">,node<" + this.node + ">,nodelink<" + this.nodelink + "> is leaves, begin to excute it ...");
							
							if( TaskState.EXECUTING == this.taskStatus ){
								//子节点为正在执行，则不允许再执行
								log.debug("Thread " + this.i + ", proc_id<" + this.processId + ">,node<" + this.node + ">,nodelink<" + this.nodelink + "> Find the leaves task is executing, task deal done. " );
								JobSemaphore.maxThreadNum.release();//释放
								return;
								
							}else if( TaskState.ERROR == this.taskStatus ){
								
								Thread.sleep( 5 * 1000);
								JobSemaphore.maxThreadNum.release();//释放
								createAndStartAThread(proc, this.i, nodelink, dryrunId,jparaMap );
								JobSemaphore.maxThreadNum.release();//释放
								return;
							}else if( dataDAO.sqlQueryUpdateProcessState_init2Running( processId ) != 1 ){							
								//叶子节点由初始状态置为运行态，如果更新不到，则返回
								log.debug("Thread " + this.i + ", proc_id<" + this.processId + ">,node<" + this.node + ">,nodelink<" + this.nodelink + "> Find the leaves task is executing, task deal done. " );
								JobSemaphore.maxThreadNum.release();//释放
								return;
							}
							
							/* 加到这里 解决下面的问题：
							 *    对于任务出错了，任务init后，重新执行时，上级节点还都是出错的状态
							 * 分析：
							 *    出错后需要手工触发节点再执行（set task to init）  error --> init
							 *    线程调试 init-->running
							 *    所以出错任务和普通任务（init）更新为正在执行的情况是一样的，都是从init更新到running
							 * 解决方案：
							 *    叶子任务更新为正在执行后，判断同级下是否有出错的，
							 *         有则不更新，
							 *         无则判断上级是否为出错状态，
							 *            是则更新上级节点为正在执行，再递归向上处理
							 *            否则不更新
							 *    即：递归更新的条件为：
							 *       同级没有出错的，且上级节点的状态为出错
							 *    */
							recursiveUpdateSuperNode(proc);
							
							
							int flag = 0;
							JSONObject oResponse =null;
							//这个方法涉及到webservice调用,可能会有超时的情况
							/*  这是递归的结束条件  */
							try{
								/* 目前没有对webservice调用返回值为false的情况进行处理，以后需要再改造 */
								oResponse = executeLeafTask( this.proc, dryrunId,this.i, this.nodelink, jparaMap );
								
							} catch (Exception e) {
						    	//下面的代码一般只有网络异常的情况下才会执行		
					            e.printStackTrace();  
					            //更新当前任务的状态为执行出错，即process表中的subtask的状态
					            dataDAO.sqlQueryUpdateProcessStateandRemark( processId,
					            		e.getMessage(),TaskState.ERROR,TaskState.EXECUTING );
					            
					            recursiveUpdateTaskPkgStatus( this.proc, TaskState.ERROR, pThread, nodelink );
					            JobSemaphore.maxThreadNum.release();//释放
								createAndStartAThread(this.proc, this.i, nodelink, dryrunId,jparaMap  );
								
								flag = 1;
							}
						
							if(0 == flag )
							{//调用WebService并收到返回后执行
								String msg = oResponse.getString("msg");
								msg = msg.length() > 1024 ? msg.substring(0, 1023) + "..." : msg;
//								log.info("special: msg:"+msg);
								if(true == oResponse.getBoolean("success"))
								{//WebService返回成功
									dataDAO.sqlQueryUpdateProcessState_Running2Finish( processId, msg  );
									//叶子任务执行完成之后的工作:查找后置并检查后置启动的条件是否满足，如果满足就启动
									recursiveEXEPostpos( proc, i, nodelink, dryrunId, jparaMap );
								}
								else
								{//WebService返回失败
						            //更新当前任务的状态为执行出错，即process表中的subtask的状态
						            dataDAO.sqlQueryUpdateProcessStateandRemark( processId,
						            		msg,
						            		TaskState.ERROR,TaskState.EXECUTING );
						            
						            recursiveUpdateTaskPkgStatus( this.proc, TaskState.ERROR, pThread, nodelink );
						            
//						            JobSemaphore.maxThreadNum.release();//释放
						            //对作业暂停的情况需要结束这个线程
									createAndStartAThread(this.proc, this.i, nodelink, dryrunId,jparaMap  );
								}
							}
						} else {
							throw new BusinessException("The task node unknown !");
						}
						
					}else{
						
						throw new BusinessException("nodelink <"+nodelink+"> taskStatus <"+taskStatus+"> unknown!");
					}
				}
				
				JobSemaphore.maxThreadNum.release();//释放

			} catch (InterruptedException e1) {
				JobSemaphore.maxThreadNum.release();//释放
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	/**
	 * 递归更新上级为正在运行（1）
	 * <p>
	 *  递归更新的条件为：
     *       同级没有出错的，且上级节点的状态为出错<br>
	 *  注：<br>
	 *     不支持同一实例中有两个相同的node_id<br>因为process表没有上级process_id列<br>
	 *     此方法第一次调用传入的p为叶子节点
	 * @param p
	 */
	private void recursiveUpdateSuperNode(MigJobProcess p){
		/* if 的判断中是更新p的上级， 所以下面方法中node_id参数为p的job_id成员域
		 * 如果满足条件就更新再向上递归，直到条件不满足为止
		 * recursive 后面的1代表正在执行状态
		 */
		if( dataDAO.find2_4recursive1( p.getJobInsId(), p.getJobId() ) == 1 ){//p不是顶层结点
			if(dataDAO.sqlQueryUpdateProcessState_4recursive1( p.getJobInsId(), p.getJobId() ) == 1 ){
				MigJobProcess super_p = dataDAO.findByJobInsIdAndNode( p.getJobInsId(), p.getJobId() );
				recursiveUpdateSuperNode(super_p);
			}else if(dataDAO.sqlQueryUpdateProcessState_4recursive1( p.getJobInsId(), p.getJobId() ) == 1 ){
				MigJobProcess super_p = dataDAO.findByJobInsIdAndNode( p.getJobInsId(), p.getJobId() );
				recursiveUpdateSuperNode(super_p);
			}else{
				log.error("致命错误！");
				throw new BusinessException("致命错误！");
			}
		}else if(jobInsDAO.find2_4recursive1( p.getJobInsId(), p.getJobId() ) == 1){//当p是顶层结点
			if(jobInsDAO.sqlQueryUpdateJobInsState_4recursive1( p.getJobInsId(), p.getJobId() ) == 1 ){
				
			}else{
				log.error("致命错误！");
				throw new BusinessException("致命错误！");
			}
		}
	}
	/**
	 * 创建并启动一个线程
	 * <p>
	 * 作业状态|【0】未执行；【1】正在执行；【2】执行完成；【3】暂停执行;【5】暂停中(人工);【6】暂停中(出错);【-1】执行出错<br>
	 * 任务状态|【0】未执行；【1】正在执行；【2】执行完成；【3】暂停执行;【4】跳过执行;【-1】执行出错
	 * <br>, ThreadGroup tg
	 * @param e
	 * @param threadId
	 * @param subtasklink package name
	 * @throws InterruptedException 
	 */
	private void createAndStartAThread(MigJobProcess e, int pThread, String nodelink, 
			Integer dryrunId,HashMap<String, String> jparaMap ) throws InterruptedException{
		
		String procId = e.getProcessId();
//		String jobInsId = e.getMigJobIns().getJobInsId();
//		String jobId = e.getJobId();
		String nodeId = e.getNodeId();
		
		int i  = StaticThreadResource.readAValue();

//		log.debug("Thread "+ i + ": proc_id<" + procId
//				+ ">,subtask<" + subtask + ">,subtasklink<" + subtasklink + "> Try to thread permission ...");
		JobSemaphore.maxThreadNum.acquire();// 获取许可,在每个线程执行完的时候都一定要释放，且线程不能等待他的子线程
		if(JobSemaphore.maxThreadNum.availablePermits()>INIT.maxThreadNum){
			log.error("线程管理存在问题，请统一把资源执行锁加在叶子任务前，并在叶子任务执行后解锁！");
		}
//		log.debug("Thread "+ i + ": proc_id<" + procId
//				+ ">,subtask<" + subtask + ">,subtasklink<" + subtasklink + "> Successful thread permission !");
		
		log.debug("Thread "+ pThread +", Create thread: " + i + ", proc_id<" + procId 
				+ ">,node<" + nodeId + ">,nodelink<" + nodelink + ">");

		Thread migTaskThread = new Thread( new MigTaskThread( e, i, pThread, nodelink, dryrunId, jparaMap ) );
		
		migTaskThread.start();
//		migTaskThread.wait();
//		migTaskThread.notify();
		
		//这里只等待processes.size()执行完成就跳过，不会等待里面的子线程执行完成。
//		synchronized (migTaskThread) {
//			migTaskThread.wait(4 * 60 * 1000);
//		}
	}

	@Override
	public void modifyTaskStatus2Init(String insId, String taskId,
			TAuthorityUser user) {
		if( 1 != dataDAO.sqlQueryUpdateProcessStateByIns_2Init( insId, taskId ) ){
			throw new BusinessException("Error, Running task can't be initial! Task may be running now!");
		}
	}

	@Override
	public void modifyTaskStatus2Finish(String insId, String taskId,
			TAuthorityUser user) {
		// TODO Auto-generated method stub
		dataDAO.sqlQueryUpdateProcessStateByIns(insId, taskId, TaskState.FINISH );
	}

	@Override
	public void modifyTaskStatus2Pause(String insId, String taskId, TAuthorityUser user) {
		dataDAO.sqlQueryUpdateProcessStateByIns(insId, taskId, TaskState.PAUSE );
	}
	@Override
	public void modifyTaskStatus2Skip(String insId, String taskId, TAuthorityUser user) {
		dataDAO.sqlQueryUpdateProcessStateByIns(insId, taskId, TaskState.SKIP );
	}

	@Override
	public void skipTask(String insId, String taskId, TAuthorityUser user) {
		dataDAO.sqlQueryUpdateProcessStateByIns(insId, taskId, TaskState.SKIP );
	}
	
	public void setDataDAO(MigJobProcessDAO dataDAO) {
		this.dataDAO = dataDAO;
	}

	public void setComInsDAO(MigComInsDAO comInsDAO) {
		this.comInsDAO = comInsDAO;
	}

	public void setInsParaDAO(MigInsParaDAO insParaDAO) {
		this.insParaDAO = insParaDAO;
	}
	
	public void setJobInsDAO(MigJobInsDAO jobInsDAO) {
		this.jobInsDAO = jobInsDAO;
	}
	public void setBusiMemoryService(BusiMemoryService busiMemoryService) {
		this.busiMemoryService = busiMemoryService;
	}
	
//	public void setStatDAO(MigJobStatDAO statDAO) {
//		this.statDAO = statDAO;
//	}

	public void setPubService(PubService pubService) {
		this.pubService = pubService;
	}
	
	public void setAuditvConfigDAO(MigAuditvConfigDAO auditvConfigDAO) {
		this.auditvConfigDAO = auditvConfigDAO;
	}
	
//	public void setAccountEmailService(AccountEmailService accountEmailService) {
//		this.accountEmailService = accountEmailService;
//	}
	
	public boolean isTaskErrorEmail() {
		return taskErrorEmail;
	}
	public void setTaskErrorEmail(boolean taskErrorEmail) {
		this.taskErrorEmail = taskErrorEmail;
	}
	
	public void setUserDAO(TAuthorityUserDAO userDAO) {
		this.userDAO = userDAO;
	}

//	public void setWsClientService(MigWSClientService wsClientService) {
//		this.wsClientService = wsClientService;
//	}
	public void setWsClientService2(MigRestClientService wsClientService2) {
		this.wsClientService2 = wsClientService2;
	}
	public static MigJobProcessService getFromApplicationContext(
			ApplicationContext ctx) {
		return (MigJobProcessService) ctx.getBean("migJobProcessService");
	}
}
