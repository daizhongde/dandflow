package person.daizhongde.migration.spring.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import person.daizhongde.authority.hibernate.pojo.TAuthorityUser;
import person.daizhongde.migration.constant.ConstMigJobContent;
import person.daizhongde.migration.constant.NodeType;
import person.daizhongde.migration.constant.TableName;
import person.daizhongde.migration.exception.BusinessException;
import person.daizhongde.migration.hibernate.dao.MigComInfoDAO;
import person.daizhongde.migration.hibernate.dao.MigJobContentDAO;
import person.daizhongde.migration.hibernate.dao.MigJobInfoDAO;
import person.daizhongde.migration.hibernate.dao.MigJobParaDAO;
import person.daizhongde.migration.hibernate.dao.MigTaskInfoDAO;
import person.daizhongde.migration.hibernate.pojo.MigComIns;
import person.daizhongde.migration.hibernate.pojo.MigControlTemplate;
import person.daizhongde.migration.hibernate.pojo.MigJobContent;
import person.daizhongde.migration.hibernate.pojo.MigJobContentId;
import person.daizhongde.migration.hibernate.pojo.MigJobInfo;
import person.daizhongde.migration.hibernate.pojo.MigTaskInfo;
import person.daizhongde.migration.spring.service.BusiMemoryService;
import person.daizhongde.migration.spring.service.MigJobContentService;
import person.daizhongde.migration.spring.service.PubService;

public class MigJobContentServiceImpl implements MigJobContentService {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	private MigJobContentDAO dataDAO;

	private MigComInfoDAO comInfoDAO;
	private MigJobParaDAO jobParaDAO;
	private MigTaskInfoDAO taskInfoDAO;
	private MigJobInfoDAO jobInfoDAO;
	private PubService pubSrv;
//	private MigTaskConfigDAO taskConfigDAO;
	private BusiMemoryService busiMemoryService;
	
	public void moveTasks2AnotherJob( String jobId, List<String> taskIds, TAuthorityUser user ){
		//目前只支持选择存在某个作业中的任务，没有独立存在的任务
		for(String taskid : taskIds){
			
			MigJobContent c = dataDAO.findByNodeId2(jobId, taskid);
			if(StringUtils.isNotEmpty( c.getPrepos() ) ){
				throw new BusinessException("Task<task> has prepos can't package by another job!");
			}else if(StringUtils.isNotEmpty( c.getPostpos() )){
				throw new BusinessException("Task<task> has postpos can't package by another job!");
			}
		}
		//Lists 
		int total = dataDAO.moveTasks2AnotherJob(jobId, taskIds);
	}
	
	public boolean whetherContainParent( String subJobId, String parentJobId ){
		//1:判断是否是自身
//		if(subJobId.equalsIgnoreCase(parentJobId)){
//			return true;
//		}
		//2:判断子作业的子节点(非叶子)是否包含parentJobId(祖先)
		List<String> nodes = dataDAO.findDirectSubJobId(subJobId);
		if( nodes.contains(parentJobId) ){
			return true;
		}
		for( String jobId : nodes ){
			if( whetherContainParent( jobId, parentJobId )){
				return true;
			};
		}
		return false;
	}
	
	public void modifyCoords(String coords, String jobId, String nodeId ){
		dataDAO.updateCoords( coords, jobId, nodeId );
	}
	
	public String addTaskNodeRetId( String taskName, String taskRemark, String coords, String jobId, String controlId, 
			TAuthorityUser user ){
		 	
		/* 1保存task  */
		String nodeId = pubSrv.get10ByteCode(TableName.mig_task_info);
		
		MigTaskInfo mti=new MigTaskInfo( nodeId, taskName,
				user.getCUlogname(), taskRemark,
				new Timestamp( new Date().getTime() ),controlId,"");
		
		taskInfoDAO.save(mti);
		
		/* 2保存关系            */
//		MigJobInfo jobinfo = jobInfoDAO.findById(jobId);
		MigJobContent mjc = new MigJobContent( new MigJobContentId(jobId, nodeId
				), NodeType.LEAF, "0","","", coords );
		dataDAO.save(mjc);

		
		/* 4返回任务ID            */
		return nodeId;
	}
	
	public String addJobNodeRetId( String jobName, int type, String jobRemark, String coords, String jobId, 
			TAuthorityUser user ){
		/* 1保存作业  */
		String nodeId = pubSrv.get10ByteCode(TableName.mig_job_info);
		String tempjobName = jobName;
		for(int i=1; i>-1; i++){
			Long count = jobInfoDAO.findCountByJobName(jobName);
			if(count==0){
				break;
			}else{
				jobName = tempjobName+" ("+i+")";
			}
		}
		
		//job_type 1 一般作业
		MigJobInfo mti=new MigJobInfo( nodeId, jobName, type,
				user.getCUlogname(), jobRemark,
				new Timestamp( new Date().getTime() )  );
		
		jobInfoDAO.save(mti);
		
		/* 2保存关系            */
//		MigJobInfo jobinfo = jobInfoDAO.findById(jobId);
		MigJobContent mjc = new MigJobContent( new MigJobContentId(jobId, nodeId
				), NodeType.NOLEAF, "0","","", coords );
		dataDAO.save(mjc);

		
		/* 4返回任务ID            */
		return nodeId;
	}
	
	public void hangJobNode( String jobId, String nodeId, String coords,
			TAuthorityUser user ){
		/* 1保存挂接关系            */
		MigJobContent mjc = new MigJobContent( new MigJobContentId(jobId, nodeId
				), NodeType.NOLEAF, "0","","", coords );
		dataDAO.save(mjc);
	}
	public void unhangJobNode( String jobId, String nodeId,
			TAuthorityUser user ){
		/* 1挂接关系            */
		MigJobContent e= dataDAO.findByNodeId(jobId, nodeId);
		
		//可能有多个与node存在依赖关系，这些依赖关系都需要删除
		String postPos = e.getPostpos();
		if( null != postPos && !"".equalsIgnoreCase( postPos ) ){
			String[] arr = postPos.split("\\|");
			List<String> list = Arrays.asList(arr);
			for(String s : list){
				unLink(e.getId().getJobId(), nodeId, s);
			}
		}
		
		String prePos = e.getPrepos();
		if( null != prePos && !"".equalsIgnoreCase( prePos ) ){
			String[] arr = prePos.split("\\|");
			List<String> list = Arrays.asList(arr);
			for(String s : list){
				unLink(e.getId().getJobId(), s, nodeId);

			}
		}
		//删除job中本节点content信息
		dataDAO.deleteByNodeid( jobId, nodeId );
//		if( e.getIsleaf() == NodeType.LEAF ){
//			//删除本节点参数信息
//			jobParaDAO.deleteByNodeId(nodeId);
//			taskInfoDAO.deleteByTaskId(nodeId);
//		}
	}
	public List findByJob(String jobId){
		if( StringUtils.isEmpty( jobId )  ){
			log.error("jobId is null or empty");
			return null;
		}
		List<MigJobContent> list = dataDAO.findNodesByJob(jobId);
		List<MigJobContent> list2 = new ArrayList<MigJobContent>();
		for(MigJobContent c : list){
			MigTaskInfo t = taskInfoDAO.findById(c.getId().getNodeId() );
			List<MigComIns> comInss = comInfoDAO.findByProperty("id.comId", t.getComId() );
			list2.add( c );
		}
		return list2;
	}
	public  List findByJobId(String jobId){
		if(jobId==null || "".equals(jobId)){
			throw new BusinessException("jobId <"+jobId+"> is null or empty !");
		}
		List<MigJobContent> list = dataDAO.findNodesByJob(jobId);
		List<MigJobContent> list2 = new ArrayList<MigJobContent>();
		for(MigJobContent e : list){
			if( e.getIsleaf() == 1 ){
				MigTaskInfo t = taskInfoDAO.findById( e.getId().getNodeId() );
				e.setTask(t);
			}else{
				MigJobInfo j = jobInfoDAO.findById( e.getId().getNodeId() );
				e.setJob(j);
			}
			list2.add( e );
		}
		return list2;			
	}

	public  void link(String jobId, String fromTask,String toTask){
		if(fromTask==null || toTask==null ||"".equals(fromTask) || "".equals(toTask)){
			throw new BusinessException("some arguments are null or empty !");
		}
		dataDAO.link(jobId,fromTask, toTask);
		log.debug(fromTask+"-"+toTask+" link success!");
	}
	
	
	public  void unLink(String jobId, String fromTask,String toTask){
		if(fromTask==null || toTask==null ||"".equals(fromTask) || "".equals(toTask)){
			throw new BusinessException("some arguments are null or empty !");
		}
		dataDAO.unlink(jobId,fromTask, toTask);	
		log.debug(fromTask+"-"+toTask+" unlink success!");
	}

	public void recurRemoveJob(String jobId, TAuthorityUser user){
		List<MigJobContent> smjc = dataDAO.findNodesByJob(jobId);
		for(MigJobContent e : smjc ){
			
			if(e.getIsleaf() == NodeType.NOLEAF){
				/* 1:判断作业(jobid是content 中的nodeid)是否有被其它作业引用Y:unlink N:recurRemoveJob */
				//1:判断作业是否被其它作业引用，如果有被引用就不能删
				List<MigJobContent> referdcontents = dataDAO.findAllJobNodeByNodeId(e.getId().getNodeId());
				
				if(referdcontents.size()>0){
					if( referdcontents.size()==1 && referdcontents.get(0).getId().getJobId().equalsIgnoreCase(jobId)){
						recurRemoveJob(e.getId().getNodeId(), user);
					}else{
						unhangJobNode(jobId,e.getId().getNodeId() ,
								user);
					}
				}else{
					recurRemoveJob(e.getId().getNodeId(), user);
				}
			}else{
				MigTaskInfo t = taskInfoDAO.findById(e.getId().getNodeId());
				
				//删除参数信息,任务包可能也有参数
				jobParaDAO.deleteByNodeId(e.getId().getNodeId());
				//删除本节点cominfo信息
				comInfoDAO.deleteCominfoByComId(t.getComId());
				//删除本节点content信息
				dataDAO.deleteByNodeid(e.getId().getNodeId());
				//删除叶子节点任务信息
				taskInfoDAO.deleteByTaskId(e.getId().getNodeId());
			}
		}
		//删除本节点参数信息
		jobParaDAO.deleteByNodeId(jobId);
		//删除以本作业为内容的记录
		dataDAO.deleteByNodeid(jobId);
		/*删除本作业内容信息  */
		dataDAO.deleteSubsById(jobId);
		//删除本作业信息
		jobInfoDAO.DeleteJobById(jobId);
	}
	/**
	 * 删除引用此作业的作业内容信息  
		dataDAO.deleteByNodeid( jobId );
	 * @param nodeId
	 */
	public void removeNode(String nodeId, TAuthorityUser user){

		List<MigJobContent> mjc= dataDAO.findByNodeId(nodeId);
		for(MigJobContent e : mjc ) {
		
			//可能有多个与node存在依赖关系，这些依赖关系都需要删除
			String postPos = e.getPostpos();
			if( null != postPos && !"".equalsIgnoreCase( postPos ) ){
				String[] arr = postPos.split("\\|");
				List<String> list = Arrays.asList(arr);
				for(String s : list){
					unLink(e.getId().getJobId(), nodeId, s);
				}
			}
			
			String prePos = e.getPrepos();
			if( null != prePos && !"".equalsIgnoreCase( prePos ) ){
				String[] arr = prePos.split("\\|");
				List<String> list = Arrays.asList(arr);
				for(String s : list){
					unLink(e.getId().getJobId(), s, nodeId);
				}
			}
		}

		MigJobInfo jobInfo = jobInfoDAO.findById(nodeId);
		if( jobInfo == null || jobInfo.equals("")){//任务
			MigTaskInfo t = taskInfoDAO.findById(nodeId);
			//删除本节点参数信息
			jobParaDAO.deleteByNodeId(nodeId);
			//删除本节点cominfo信息
			comInfoDAO.deleteCominfoByComId( t.getComId() );
			//删除本节点content信息
			dataDAO.deleteByNodeid(nodeId);
			//删除taskinfo表信息
			taskInfoDAO.deleteByTaskId(nodeId);
		}else{//作业
			//递归删除子结点
			recurRemoveJob(nodeId,user);
		}
	};
	public void removeNode(List<String> nodeId, TAuthorityUser user){
		for(String job_id : nodeId){
			//递归删除作业
			recurRemoveJob(job_id,user);
		}
	};
	
	public void removeTask( String jobId, String nodeId,String comId,
			TAuthorityUser user ){

		List<MigJobContent> mjc= dataDAO.findByNodeId(nodeId);
		for(MigJobContent e : mjc ) {
		
			//可能有多个与node存在依赖关系，这些依赖关系都需要删除
			String postPos = e.getPostpos();
			if( null != postPos && !"".equalsIgnoreCase( postPos ) ){
				String[] arr = postPos.split("\\|");
				List<String> list = Arrays.asList(arr);
				for(String s : list){
					unLink(e.getId().getJobId(), nodeId, s);
				}
			}
			
			String prePos = e.getPrepos();
			if( null != prePos && !"".equalsIgnoreCase( prePos ) ){
				String[] arr = prePos.split("\\|");
				List<String> list = Arrays.asList(arr);
				for(String s : list){
					unLink(e.getId().getJobId(), s, nodeId);
				}
			}
		}
		//删除本节点参数信息
		jobParaDAO.deleteByNodeId(nodeId);
		//判断com是否被其它任务使用
		int count = taskInfoDAO.countByComId(comId);
		if(count==1){
			//删除本节点cominfo信息
			comInfoDAO.deleteCominfoByComId(comId);
		}
		//删除本节点content信息
		dataDAO.deleteByNodeid(nodeId);
		//删除taskinfo表信息
		taskInfoDAO.deleteByTaskId(nodeId);
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
		AbstractConstant absConstant = new ConstMigJobContent();

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
		AbstractConstant absConstant = new ConstMigJobContent();

		SQLAssembleC sqlA = new SQLAssembleC(
				absConstant.getTableName(),
				jsonObject.getJSONObject("data"),
				absConstant.getColumnTypes(),
				absConstant.getFront2col()
				);
		
		return dataDAO.sqlQueryExeUByMap(sqlA.getSQL(), sqlA.getMap());
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.authority.spring.service.impl.MigJobContentService#addWithId(java.lang.String)
	 */
	@Override
	public int addWithId( String jdata ){
		JSONObject jsonObject = JSONObject.fromObject(jdata);
		AbstractConstant absConstant = new ConstMigJobContent();

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
		AbstractConstant absConstant = new ConstMigJobContent();
		
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
		AbstractConstant absConstant = new ConstMigJobContent();

		SQLAssembleC sqlA = new SQLAssembleC(
				absConstant.getTableName(),
				jsonObject.getJSONObject("data"),
				absConstant.getColumnTypes(),
				absConstant.getFront2col()
				);
		
		MigJobContent pojo = new MigJobContent();
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
	 * @see person.daizhongde.authority.spring.service.impl.MigJobContentService#modify(java.lang.String)
	 */
	@Override
	public int modify( String jdata ){
		JSONObject jsonObject = JSONObject.fromObject(jdata);
		AbstractConstant absConstant = new ConstMigJobContent();

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
		AbstractConstant absConstant = new ConstMigJobContent();

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
		AbstractConstant absConstant = new ConstMigJobContent();
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
		AbstractConstant absConstant = new ConstMigJobContent();
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
		AbstractConstant absConstant = new ConstMigJobContent();

		SQLAssembleR sqlA = new SQLAssembleR(
				absConstant.getSQLDOC(),
				absConstant.getRead_SQL(), 
				jsonObject.getJSONObject("condition"),
				jsonObject.getJSONObject("operator"),
				absConstant.getColumnTypes(),
				absConstant.getFront2col() );
		
		return (Object[])dataDAO.sqlQuerylistAllRetArrayByMap( sqlA.getSQL(), sqlA.getMap() ).get(0);
	}
	
	public MigJobContent browsePOJO(String jdata) {
		JSONObject jsonObject = JSONObject.fromObject(jdata);
		AbstractConstant absConstant = new ConstMigJobContent();

		HQLAssembleR hqlA = new HQLAssembleR(
				absConstant.getSQLDOC(),
				absConstant.getRead_SQL(), 
				jsonObject.getJSONObject("condition"),
				jsonObject.getJSONObject("operator"),
				absConstant.getColumnTypes(),
				absConstant.getFront2back() );
		
		return (MigJobContent)dataDAO.listAllByMap( "from MigJobContent t1 where "+hqlA.getWhereBackHQL(), hqlA.getMap() ).get(0);
	}
	public MigJobContent browsePOJOById(int id) {
//		return dataDAO.findById((short)id);
		return null;
	}
	public MigJobContent browsePOJOById(String id) {
//		return dataDAO.findById((short)id);
		return null;
	}
	/* (non-Javadoc)
	 * @see person.daizhongde.authority.spring.service.impl.MigJobContentService#delete(java.lang.String)
	 */
	@Override
	public int delete( String jdata ){
		JSONObject jsonObject = JSONObject.fromObject(jdata);
		AbstractConstant absConstant = new ConstMigJobContent();

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

	/**
	 * 将某一个控件模板的参数封装成map对象<p>
	 * map方便id与name的转换
	 * @param templates
	 * @return
	 */
	private Map<Integer, String> getIdNameMap(List<MigControlTemplate> templates){
		Map<Integer, String> map = new HashMap<Integer, String>();
		for(MigControlTemplate e : templates){
			map.put(e.getId().getParaId(), e.getParaName());
		}
		return map;
	}

	/**
	 * 格式化MigComIns实体，为之添加参数名称成员
	 * @param comInss
	 * @return
	 */
	private List<MigComIns> formatMigComIns( List<MigComIns> comInss, String controlId ){
		List<MigComIns> result = new ArrayList<MigComIns>();
		if( null == comInss || comInss.size()==0 ){
			return result;
		}
//		List<MigControlTemplate> templates = controlTemplateDAO.findByProperty("id.controlId", controlId );
		List<MigControlTemplate> templates = busiMemoryService.getControlMap().get( controlId );
		
		Map<Integer, String> map =  getIdNameMap(templates);
		for(MigComIns e : comInss){						
			e.setParaName( map.get(e.getId().getParaId()) );
			
			result.add(e);
		}
		
		return result;
	}
	
	public void setDataDAO(MigJobContentDAO dataDAO) {
		this.dataDAO = dataDAO;
	}

	public void setComInfoDAO(MigComInfoDAO comInfoDAO) {
		this.comInfoDAO = comInfoDAO;
	}

	public void setJobParaDAO(MigJobParaDAO jobParaDAO) {
		this.jobParaDAO = jobParaDAO;
	}

	public void setTaskInfoDAO(MigTaskInfoDAO taskInfoDAO) {
		this.taskInfoDAO = taskInfoDAO;
	}

	public void setJobInfoDAO(MigJobInfoDAO jobInfoDAO) {
		this.jobInfoDAO = jobInfoDAO;
	}

	public void setPubSrv(PubService pubSrv) {
		this.pubSrv = pubSrv;
	}

	public void setBusiMemoryService(BusiMemoryService busiMemoryService) {
		this.busiMemoryService = busiMemoryService;
	}
	public static MigJobContentService getFromApplicationContext(
			ApplicationContext ctx) {
		return (MigJobContentService) ctx.getBean("migJobContentService");
	}

//	public void setTaskConfigDAO(MigTaskConfigDAO taskConfigDAO) {
//		this.taskConfigDAO = taskConfigDAO;
//	}


}
