package person.daizhongde.migration.constant;
/**
 * 任务类型,'F'：非叶子节点,‘S’：叶子节点<br>
 * 任务状态,'0':未执行,'1':正在执行,'2':执行完成,'3':暂停执行,'4':跳过执行,'-1':执行出错<br>
 * OCK_STATUS '0':未锁定,'1'锁定<br>
 * @author daizd
 *
 */
public class JobRunState {
	/** 1 有后台线程 **/
	public static final int WITHBACKTHREAD=1;
	/** 0  没有后台线程**/
	public static final int NOBACKTHREAD=0;
	
}
