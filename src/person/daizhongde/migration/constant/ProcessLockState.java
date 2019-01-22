package person.daizhongde.migration.constant;
/**
 * 任务类型,'F'：非叶子节点,‘S’：叶子节点<br>
 * 任务状态,'0':未执行,'1':正在执行,'2':执行完成,'3':暂停执行,'4':跳过执行,'-1':执行出错<br>
 * OCK_STATUS '0':未锁定,'1'锁定<br>
 * @author daizd
 *
 */
public class ProcessLockState {
	/** 1 **/
	public static final String LOCK="1";
	/** 0 **/
	public static final String UNLOCK="0";
	
}
