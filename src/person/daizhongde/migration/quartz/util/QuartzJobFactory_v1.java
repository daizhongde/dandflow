 package person.daizhongde.migration.quartz.util;
 
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import person.daizhongde.migration.hibernate.pojo.MigInsQuartz;
/**
 * 没有注入service,仿paper
 * @author daizd
 *
 */
 public class QuartzJobFactory_v1
   implements Job
 {
   public final Logger log = Logger.getLogger(getClass());
 
   public void execute(JobExecutionContext context) throws JobExecutionException {
     MigInsQuartz scheduleJob = (MigInsQuartz)context.getMergedJobDataMap().get("scheduleJob");

     TaskUtils.invokMethod(scheduleJob);
   }
 }