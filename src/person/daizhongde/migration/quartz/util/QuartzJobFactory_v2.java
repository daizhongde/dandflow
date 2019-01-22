package person.daizhongde.migration.quartz.util;

import java.util.Date;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

import person.daizhongde.migration.hibernate.pojo.MigInsQuartz;
import person.daizhongde.migration.spring.service.MigJobProcessService;
/**
 * executeInternal
 * @author daizd
 *
 */
public class QuartzJobFactory_v2 extends QuartzJobBean {
	public final Logger log = Logger.getLogger(getClass());

	private static int counter = 0;

	protected void executeInternal(JobExecutionContext context)
			throws JobExecutionException {
		MigInsQuartz scheduleJob = (MigInsQuartz) context.getMergedJobDataMap()
				.get("scheduleJob");

		System.out.println();
		long ms = System.currentTimeMillis();
		System.out.println("\t\t" + new Date(ms));
		System.out.println(ms);
		System.out.println("(" + counter++ + ")");
		// String s = (String) context.getMergedJobDataMap().get("scheduleJob");
		// System.out.println(s);
		// System.out.println();
//		Trigger trigger = context.getTrigger();
//		String triggerName = trigger.getKey().getName();

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