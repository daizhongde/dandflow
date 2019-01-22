package person.daizhongde.migration.spring.service.quartz;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.log4j.Logger;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

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
import person.daizhongde.migration.constant.ConstMigInsQuartz;
import person.daizhongde.migration.hibernate.dao.MigInsQuartzDAO;
import person.daizhongde.migration.hibernate.pojo.MigInsQuartz;
import person.daizhongde.migration.quartz.util.QuartzJobFactory;

//@Service("migInsQuartzService")
public class MigInsQuartzServiceImpl implements MigInsQuartzService {
	private MigInsQuartzDAO dataDAO;
	private Logger log = Logger.getLogger(MigInsQuartzServiceImpl.class);

//	@Autowired
	private SchedulerFactoryBean schedulerFactoryBean;

//	@Resource(name = "migInsQuartzDAO")
	public void setDataDAO(MigInsQuartzDAO dataDAO) {
		this.dataDAO = dataDAO;
	}

	public void setSchedulerFactoryBean(SchedulerFactoryBean schedulerFactoryBean) {
		this.schedulerFactoryBean = schedulerFactoryBean;
	}

//	@Override
//    protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
//        System.out.println(TimeUtils.getCurrentTime());
//    }
	/** 
	 * add scheduler
	 * <p>
	 * can Avoid repeated additions
	 * 
	 * @param job
	 * @throws SchedulerException
	 */
	public void addJob(MigInsQuartz job) throws SchedulerException {
		Scheduler scheduler = this.schedulerFactoryBean.getScheduler();
		this.log.debug(scheduler
				+ ".......................................................................................add");
		TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobName(),
				job.getJobGroup());
		CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

		if (trigger == null) {
			Class clazz = QuartzJobFactory.class;

			JobDetail jobDetail = JobBuilder.newJob(clazz)
					.withIdentity(job.getJobName(), job.getJobGroup()).build();

			jobDetail.getJobDataMap().put("scheduleJob", job);

			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder
					.cronSchedule(job.getCronExpression());
			/* 触发时间表达式，triggerIdentity */
			trigger = (CronTrigger) TriggerBuilder.newTrigger()
					.withIdentity(job.getJobName(), job.getJobGroup())
					.withSchedule(scheduleBuilder).build();

			scheduler.scheduleJob(jobDetail, trigger);
		} else {
			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder
					.cronSchedule(job.getCronExpression());

			trigger = (CronTrigger) trigger.getTriggerBuilder()
					.withIdentity(triggerKey).withSchedule(scheduleBuilder)
					.build();

			scheduler.rescheduleJob(triggerKey, trigger);
		}
	}
	/*public void addJob(MigInsQuartz job) throws SchedulerException {
		Scheduler scheduler = this.schedulerFactoryBean.getScheduler();
		this.log.debug(scheduler
				+ ".......................................................................................add");
		CronTrigger trigger = null;
		Class clazz = QuartzJobFactory.class;

		JobDetail jobDetail = JobBuilder.newJob(clazz)
				.withIdentity(job.getJobName(), job.getJobGroup()).build();

		jobDetail.getJobDataMap().put("scheduleJob", job);

		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder
				.cronSchedule(job.getCronExpression());

		trigger = (CronTrigger) TriggerBuilder.newTrigger()
				.withIdentity(job.getJobName(), job.getJobGroup())
				.withSchedule(scheduleBuilder).build();

		scheduler.scheduleJob(jobDetail, trigger);
		
	}*/
	public void rescheduleJob(MigInsQuartz job) throws SchedulerException {
		Scheduler scheduler = this.schedulerFactoryBean.getScheduler();
		this.log.debug(scheduler
				+ ".......................................................................................rescheduleJob");
		TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobName(),
				job.getJobGroup());
		CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder
				.cronSchedule(job.getCronExpression());

		trigger = (CronTrigger) trigger.getTriggerBuilder()
				.withIdentity(triggerKey).withSchedule(scheduleBuilder)
				.build();

		scheduler.rescheduleJob(triggerKey, trigger);
	}
	/**
	 * add逻辑中耦合了更新的逻辑（不推荐），
	 * <p>
	 * 最新应用采用 addMigInsQuartz + updateMigInsQuartz 
	 * <br>
	 * addJob方法逻辑如果采用case_id去判断重复性，才可以避免多个相同实例ID的作业重复调度
	 * 
	 * @param scheduleJob
	 * @throws SchedulerException
	 */
	public void addMigInsQuartz(MigInsQuartz scheduleJob) throws SchedulerException {
		if (scheduleJob.getId() == 0) {
			addJob(scheduleJob);
			dataDAO.save(scheduleJob);
		} else {
			dataDAO.update(scheduleJob);
			rescheduleJob(scheduleJob);
		}
	}
	/*public void addMigInsQuartz(MigInsQuartz scheduleJob) throws SchedulerException {
		addJob(scheduleJob);
		dataDAO.save(scheduleJob);
	}*/
	public void updateMigInsQuartz(MigInsQuartz scheduleJob) throws SchedulerException {
		dataDAO.update(scheduleJob);
		rescheduleJob(scheduleJob);
	}
//	@PostConstruct
	public void initMigInsQuartz() {
		System.out.println("initMigInsQuartz ...... ");
//		List<MigInsQuartz> sjs = getAllMigInsQuartz();
//		try {
//			if (!CollectionUtils.isEmpty(sjs))
//				for (MigInsQuartz sj : sjs)
//					addJob(sj);
//		} catch (Exception e) {
//			System.out.println("初始化migInsQuartzService时，初始化定时实例了出错......");
//			e.printStackTrace();
//		}
	}

	public List<MigInsQuartz> getAllMigInsQuartz() {
		List list = this.dataDAO.findAll();
		return list;
	}

	public MigInsQuartz getMigInsQuartzByCastIdAndJobGroup(String castId,
			String jobGroup) {
		MigInsQuartz scheduleJob = null;
//		List list = this.dataDAO.find(instance).find(
//				"from MigInsQuartz where caseId = ? and jobGroup = ?",
//				new Object[] { castId, jobGroup });
		MigInsQuartz instance = new MigInsQuartz();
		instance.setCaseId(castId);
		instance.setJobGroup(jobGroup);
		List list = this.dataDAO.findByExample(instance);
				
		System.out.println("getMigInsQuartzByCastIdAndJobGroup list.size:"+list.size());
		if ((list != null) && (!list.isEmpty())) {
			scheduleJob = (MigInsQuartz) list.get(0);
		}
		return scheduleJob;
	}

	public void deleteMigInsQuartz(Integer id) throws SchedulerException {
		MigInsQuartz sj = (MigInsQuartz) this.dataDAO.findById(id);

		Scheduler scheduler = this.schedulerFactoryBean.getScheduler();
		JobKey jobKey = JobKey.jobKey(sj.getJobName(), sj.getJobGroup());
		scheduler.deleteJob(jobKey);

		this.dataDAO.delete(sj);// schedule job
	}

	public void deleteMigInsQuartz(String castId, String jobGroup) throws SchedulerException {
		MigInsQuartz scheduleJob = getMigInsQuartzByCastIdAndJobGroup(castId,
				jobGroup);

		if (scheduleJob != null)
			deleteMigInsQuartz( scheduleJob.getId() );
	}

	/*	public List<String> getMinuteList() {
		List minuteList = new ArrayList();

		for (int i = 0; i < 60; i++) {
			if (i % 10 == 0) {
				if (i < 10)
					minuteList.add("0" + i);
				else {
					minuteList.add(i);
				}
			}
		}
		return minuteList;
	}

	public List<String> getHourList() {
		List hourList = new ArrayList();
		for (int i = 0; i < 24; i++) {
			if (i < 10)
				hourList.add("0" + i);
			else {
				hourList.add(i);
			}
		}
		return hourList;
	}

	public List<String> getSetHourList() {
		List hourList = new ArrayList();
		for (int i = 1; i < 24; i++) {
			hourList.add(String.valueOf(i));
		}
		return hourList;
	}

	public List<Integer> getMonthList() {
		List mouthList = new ArrayList();
		for (int i = 1; i <= 12; i++) {
			mouthList.add(Integer.valueOf(i));
		}
		return mouthList;
	}

	public List<Integer> getDayList() {
		List dayList = new ArrayList();
		for (int i = 1; i <= 31; i++) {
			dayList.add(Integer.valueOf(i));
		}
		return dayList;
	}
*/
	public long getTotal(SQLAssembleQ sqlA) {
//		Object o= dataDAO.sqlQueryfindaValueByMap( sqlA.getCountSQL(), sqlA.getMap() );
//		System.out.println("o:"+o);
		return Long.valueOf(
				dataDAO.sqlQueryfindaValueByMap( sqlA.getCountSQL(), sqlA.getMap() ).toString()
			);
	}

	public List getRowsInMap(SQLAssembleQ sqlA) {
		return dataDAO.sqlQuerylistAllByMap( sqlA.getSQL(), sqlA.getMap() );//使用native数据量小
	}

	public List getRowsInMap(SQLAssembleQ sqlA, int offset, int pageSize) {
		return dataDAO.sqlQueryfindByPageByMap(sqlA.getSQL(), sqlA.getMap(), 
				offset, pageSize);//使用native数据量小
	}

	public long getTotal(HQLAssembleQ hqlA) {
		// TODO Auto-generated method stub
		return 0;
	}

	public List getRowsInMap(HQLAssembleQ hqlA) {
		// TODO Auto-generated method stub
		return null;
	}

	public List getRowsInMap(HQLAssembleQ sqlA, int offset, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	public List getRowsInArray(SQLAssembleQ sqlA) {
		return dataDAO.sqlQuerylistAllRetArrayByMap(sqlA.getSQL(), sqlA.getMap());//使用native数据量小
	}

	public List getRowsInArray(SQLAssembleQ sqlA, int offset,
			int pageSize) {
		return dataDAO.sqlQueryfindRetArrayByPageByMap(sqlA.getSQL(), sqlA.getMap(), 
				offset, pageSize);//使用native数据量小
	}

	public int add(String jdata) {
		JSONObject jsonObject = JSONObject.fromObject(jdata);
		AbstractConstant absConstant = new ConstMigInsQuartz();

		SQLAssembleC sqlA = new SQLAssembleC(
				absConstant.getTableName(),
				jsonObject.getJSONObject("data"),
				absConstant.getColumnTypes(),
				absConstant.getFront2col()
				);
		
		return dataDAO.sqlQueryExeUByMap(sqlA.getSQL(), sqlA.getMap());
	}

	public int add(String jdata, TAuthorityUser user ) {
		JSONObject jsonObject = JSONObject.fromObject(jdata);
		AbstractConstant absConstant = new ConstMigInsQuartz();

		Map data = jsonObject.getJSONObject("data");
		data.put("author", user.getCUlogname() );
		
		SQLAssembleC sqlA = new SQLAssembleC(
				absConstant.getTableName(),
				data,
				absConstant.getColumnTypes(),
				absConstant.getFront2col()
				);
		
		return dataDAO.sqlQueryExeUByMap(sqlA.getSQL(), sqlA.getMap());
	}
	public int add(String jdata, TAuthorityUser user, String remoteAddr ) throws SchedulerException {
		JSONObject jsonObject = JSONObject.fromObject(jdata);
		AbstractConstant absConstant = new ConstMigInsQuartz();

		Map data = jsonObject.getJSONObject("data");

		MigInsQuartz scheduleJob = new MigInsQuartz();
		scheduleJob.setAuthor(user.getCUlogname());
		scheduleJob.setCaseId(data.get("case_id").toString());
		scheduleJob.setCronExpression(data.get("cronexpression").toString());
		scheduleJob.setBeanClass("migJobProcessService");
		scheduleJob.setMethodName("startJob");
		scheduleJob.setJobGroup(data.get("jobgroup").toString());
		scheduleJob.setJobName(data.get("jobname").toString());
		scheduleJob.setRemark(data.get("remark").toString());
		scheduleJob.setCreateTime( new Timestamp( new Date().getTime() ) );
		scheduleJob.setCip(remoteAddr);
		
		addMigInsQuartz(scheduleJob);
		return 1;
	}
	public int add(Map data) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int addRetId(String jdata) {
		JSONObject jsonObject = JSONObject.fromObject(jdata);
		AbstractConstant absConstant = new ConstMigInsQuartz();

		SQLAssembleC sqlA = new SQLAssembleC(
				absConstant.getTableName(),
				jsonObject.getJSONObject("data"),
				absConstant.getColumnTypes(),
				absConstant.getFront2col()
				);
		
		return dataDAO.sqlQueryExeUByMap(sqlA.getSQL(), sqlA.getMap());
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.authority.spring.service.impl.MigInsQuartzService#addWithId(java.lang.String)
	 */
	@Override
	public int addWithId( String jdata ){
		JSONObject jsonObject = JSONObject.fromObject(jdata);
		AbstractConstant absConstant = new ConstMigInsQuartz();

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
		AbstractConstant absConstant = new ConstMigInsQuartz();
		
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
		AbstractConstant absConstant = new ConstMigInsQuartz();

		SQLAssembleC sqlA = new SQLAssembleC(
				absConstant.getTableName(),
				jsonObject.getJSONObject("data"),
				absConstant.getColumnTypes(),
				absConstant.getFront2col()
				);
		
		MigInsQuartz pojo = new MigInsQuartz();
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
	 * @see person.daizhongde.authority.spring.service.impl.MigInsQuartzService#modify(java.lang.String)
	 */
	@Override
	public int modify( String jdata ){
		JSONObject jsonObject = JSONObject.fromObject(jdata);
		AbstractConstant absConstant = new ConstMigInsQuartz();

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
	public int modify(String jdata, TAuthorityUser user, String remoteAddr  ) throws SchedulerException {
		
		JSONObject jsonObject = JSONObject.fromObject(jdata);
		AbstractConstant absConstant = new ConstMigInsQuartz();

		Map data = jsonObject.getJSONObject("data");

		MigInsQuartz scheduleJob = new MigInsQuartz();
		scheduleJob.setId(Integer.valueOf( data.get("id").toString()) ); 
		scheduleJob.setCaseId(data.get("case_id").toString());
		scheduleJob.setCronExpression(data.get("cronexpression").toString());
		scheduleJob.setJobGroup(data.get("jobgroup").toString());
		scheduleJob.setJobName(data.get("jobname").toString());
		scheduleJob.setRemark(data.get("remark").toString());
		scheduleJob.setBeanClass("migJobProcessService");
		scheduleJob.setMethodName("startJob");
		scheduleJob.setModifier(user.getCUlogname());
		scheduleJob.setModifyTime( new Timestamp( new Date().getTime() ) );
		scheduleJob.setMip(remoteAddr);
		
		updateMigInsQuartz(scheduleJob);
		return 1;
	}
	@Override
	public Map browse(String jdata) {
		JSONObject jsonObject = JSONObject.fromObject(jdata);
		AbstractConstant absConstant = new ConstMigInsQuartz();

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
		AbstractConstant absConstant = new ConstMigInsQuartz();
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
		AbstractConstant absConstant = new ConstMigInsQuartz();
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
		AbstractConstant absConstant = new ConstMigInsQuartz();

		SQLAssembleR sqlA = new SQLAssembleR(
				absConstant.getSQLDOC(),
				absConstant.getRead_SQL(), 
				jsonObject.getJSONObject("condition"),
				jsonObject.getJSONObject("operator"),
				absConstant.getColumnTypes(),
				absConstant.getFront2col() );
		
		return (Object[])dataDAO.sqlQuerylistAllRetArrayByMap( sqlA.getSQL(), sqlA.getMap() ).get(0);
	}
	
	public MigInsQuartz browsePOJO(String jdata) {
		JSONObject jsonObject = JSONObject.fromObject(jdata);
		AbstractConstant absConstant = new ConstMigInsQuartz();

		HQLAssembleR hqlA = new HQLAssembleR(
				absConstant.getSQLDOC(),
				absConstant.getRead_SQL(), 
				jsonObject.getJSONObject("condition"),
				jsonObject.getJSONObject("operator"),
				absConstant.getColumnTypes(),
				absConstant.getFront2back() );
		
		return (MigInsQuartz)dataDAO.listAllByMap( "from MigInsQuartz t1 where "+hqlA.getWhereBackHQL(), hqlA.getMap() ).get(0);
	}
	public MigInsQuartz browsePOJOById(int id) {
//		return dataDAO.findById((short)id);
		return null;
	}
	public MigInsQuartz browsePOJOById(String id) {
//		return dataDAO.findById((short)id);
		return null;
	}
	/* (non-Javadoc)
	 * @see person.daizhongde.authority.spring.service.impl.MigInsQuartzService#delete(java.lang.String)
	 */
	@Override
	public int delete( String jdata ){
		JSONObject jsonObject = JSONObject.fromObject(jdata);
		AbstractConstant absConstant = new ConstMigInsQuartz();

//		Map data = jsonObject.getJSONObject("data");
		SQLAssembleD sqlA = new SQLAssembleD(
				absConstant.getSQLDOC(),
				absConstant.getTableName(), 
				jsonObject.getJSONObject("condition"),
				jsonObject.getJSONObject("operator"),
				absConstant.getColumnTypes(),
				absConstant.getFront2col() );
		
		return dataDAO.sqlQueryExeUByMap(sqlA.getSQL(), sqlA.getMap());
	}
	public int delete( String jdata, TAuthorityUser user , String remoteAddr) throws NumberFormatException, SchedulerException{
		JSONObject jsonObject = JSONObject.fromObject(jdata);
		AbstractConstant absConstant = new ConstMigInsQuartz();

		Map data = jsonObject.getJSONObject("data");
		deleteMigInsQuartz(Integer.valueOf( data.get("id").toString()) );
		return 1;
	}
	@Override
	public int deleteNP(String jdata) {
		return this.modify(jdata);
	}

	public static MigInsQuartzService getFromApplicationContext(
			ApplicationContext ctx) {
		return (MigInsQuartzService) ctx.getBean("migInsQuartzService");
	}


}