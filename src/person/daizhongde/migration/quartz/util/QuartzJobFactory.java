 package person.daizhongde.migration.quartz.util;
 
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;import org.slf4j.LoggerFactory;
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
 public class QuartzJobFactory
   implements Job
 {
   public final Logger log = LoggerFactory.getLogger(getClass());
 
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		MigInsQuartz scheduleJob = (MigInsQuartz) context.getMergedJobDataMap()
				.get("scheduleJob");

		String beanClass = scheduleJob.getBeanClass();
		//如果是调用bean方法
		if(beanClass.equalsIgnoreCase("migJobProcessService")){
			MigJobProcessService migJobProcessService = getApplicationContext(
					context).getBean( beanClass,//"migJobProcessService",
					MigJobProcessService.class);
			try {
				System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+" quartz start job caseId:"+scheduleJob.getCaseId());
				migJobProcessService.startJob(scheduleJob.getCaseId());
				
//				String methodName = scheduleJob.getMethodName();
//				Method method = migJobProcessService.getClass().getMethod(methodName, new Class[] { String.class });
//				method.invoke(migJobProcessService, new Object[] { scheduleJob.getCaseId()});
//			} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			} catch (InterruptedException e){
				System.out.println("启动作业时出现严重错误！");
				log.error("启动作业时出现严重错误！");
				e.printStackTrace();
			}
		}else{
			//利用反射机制实例化类，然后调用其方法
			TaskUtils.invokMethod(scheduleJob);
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