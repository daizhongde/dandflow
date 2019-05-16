package person.daizhongde.migration.quartz.util;

import java.lang.reflect.Method;

import org.slf4j.Logger;import org.slf4j.LoggerFactory;

import person.daizhongde.migration.hibernate.pojo.MigInsQuartz;

public class TaskUtils {
	public static final Logger log = LoggerFactory.getLogger(TaskUtils.class);

	public static void invokMethod(MigInsQuartz scheduleJob) {
		try {
			reflectStartMethod(scheduleJob.getBeanClass(),
					scheduleJob.getMethodName(),
					new Object[] { scheduleJob.getCaseId()},
					new Class[] { String.class });//user传实例author
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			e.printStackTrace();
			return;
		}
	}

	public static Object reflectStartMethod(String className,
			String methodName, Object[] methodParameters,
			Class[] parameterClasses) throws Exception {
		Object result = null;
		Class cls = Class.forName(className);

		Object obj = cls.newInstance();

		Method method = obj.getClass().getMethod(methodName, parameterClasses);

		result = method.invoke(obj, methodParameters);
		return result;
	}
}