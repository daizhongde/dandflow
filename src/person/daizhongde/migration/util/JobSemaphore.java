package person.daizhongde.migration.util;

import java.util.concurrent.Semaphore;

import person.daizhongde.virtue.constant.INIT;
/**
 * JobSemaphore 
 * @author dzd
 *
 */
public class JobSemaphore{
//	/**a time a thread access the dataDAO    **/
//	public static Semaphore semp = new Semaphore(1);
		
	/**max thread number config in init.properties file   **/
	public static Semaphore maxThreadNum = new Semaphore( INIT.maxThreadNum);
		
}
