package person.daizhongde.migration.constant;
/**
 * 任务状态,'0':未执行,'1':正在执行,'2':执行完成,'3':暂停执行,'4':跳过执行,'-1':执行出错<br>
 * OCK_STATUS '0':未锁定,'1'锁定<br>
 * @author daizd
 *
 */
public class TaskState {
	
	
	/** 0  **/
	public static final int INITIAL=0;
	/** 1  **/
	public static final int EXECUTING=1;
	/** 2  **/
	public static final int FINISH=2;
	/** 3  **/
	public static final int PAUSE=3;
	/** 4  **/
	public static final int SKIP=4;
	/** -1  **/
	public static final int ERROR=-1;
	
	
}
