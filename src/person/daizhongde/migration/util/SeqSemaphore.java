package person.daizhongde.migration.util;

import java.util.concurrent.Semaphore;
/**
 * JobSemaphore 
 * @author dzd
 *
 */
public class SeqSemaphore{
	/**mig_com_ins    **/
	public static Semaphore mig_com_ins = new Semaphore(1);
		
	/**mig_control_info   **/
	public static Semaphore mig_control_info = new Semaphore(1);
	
	/**mig_control_template   **/
	public static Semaphore mig_control_template = new Semaphore(1);
	
	/**mig_job_content   **/
	public static Semaphore mig_job_content = new Semaphore(1);
	
	/**mig_job_info   **/
	public static Semaphore mig_job_info = new Semaphore(1);
	
	/**mig_job_log   **/
	public static Semaphore mig_job_log = new Semaphore(1);
	
	/**mig_job_para   **/
	public static Semaphore mig_job_para = new Semaphore(1);
	
	/**mig_job_process   **/
	public static Semaphore mig_job_process = new Semaphore(1);
	
	/**mig_job_stat   **/
	public static Semaphore mig_job_stat = new Semaphore(1);
	
	/**mig_load_map   **/
	public static Semaphore mig_load_map = new Semaphore(1);
	
	/**mig_staticprar_define   **/
	public static Semaphore mig_staticprar_define = new Semaphore(1);
	
	/**mig_task_info   **/
	public static Semaphore mig_task_info = new Semaphore(1);
	
	/**mig_task_rel   **/
	public static Semaphore mig_task_rel = new Semaphore(1);
		
}
