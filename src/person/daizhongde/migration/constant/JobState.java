package person.daizhongde.migration.constant;
/**
 * 作业状态(其实是实例状态，因为系统刚开始的设计没有实例，所以实例状态一直沿用了这个类),'0':未执行,'1':正在执行,'2':执行完成,'3':暂停执行,'4':跳过执行,【5】暂停中(人工);【6】暂停中(出错);【-1】执行出错<br>
 * OCK_STATUS '0':未锁定,'1'锁定<br>
 * @author daizd
 *
 */
public class JobState {
	
	/** '0' 未执行 **/
	public static final String INITIAL="0";
	/** '1' 正在执行 **/
	public static final String EXECUTING="1";
	/** '2' 执行完成 **/
	public static final String FINISH="2";
	/** '3' 暂停执行 **/
	public static final String PAUSE="3";
//	/** '4' 跳过执行 **/
//	public static final String SKIP="4";
	/** '5' 暂停中(人工)... **/
	public static final String PAUSING_MANUAL="5";
//	/** '6' 暂停中(出错)... **/
//	public static final String PAUSING_ERROR="6";
	/** '-1' 执行出错 **/
	public static final String ERROR="-1";
	
}
