package person.daizhongde.migration.util;

public class StaticThreadResource {
	
	/**  所有线程共享,取值范围：1～Max	 */
	private static int num = 1;
	
	/**  最大数量，实例化里初始化	 */
	private static int max = 99999;

	public synchronized static int readAValue(){
		if(num==max+1){
			num=1;
		}
		return num++;
	}
}
