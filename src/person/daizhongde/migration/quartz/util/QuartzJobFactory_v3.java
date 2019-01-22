 package person.daizhongde.migration.quartz.util;
 
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.springframework.context.ApplicationContext;

import person.daizhongde.migration.hibernate.pojo.MigInsQuartz;
import person.daizhongde.migration.spring.service.MigJobProcessService;
/**
 * 注入了service,仿paper
 * @author daizd
 *
 */
 public class QuartzJobFactory_v3
   implements Job
 {
   public final Logger log = Logger.getLogger(getClass());
 
   public void execute(JobExecutionContext context) throws JobExecutionException {
     MigInsQuartz scheduleJob = (MigInsQuartz)context.getMergedJobDataMap().get("scheduleJob");

		MigJobProcessService migJobProcessService = getApplicationContext(
				context).getBean("migJobProcessService",
				MigJobProcessService.class);
		try {
			migJobProcessService.startJob(scheduleJob.getCaseId());
		} catch (InterruptedException e) {
			log.error("启动作业时出现严重错误！");
			e.printStackTrace();
		}
   }
	private ApplicationContext getApplicationContext(
			final JobExecutionContext jobexecutioncontext) {
		try {
			return (ApplicationContext) jobexecutioncontext.getScheduler()
					.getContext().get("applicationContextKey");
		} catch (SchedulerException e) {
			log.error("jobexecutioncontext.getScheduler().getContext() error!",
					e);
			throw new RuntimeException(e);
		}
	}
 }