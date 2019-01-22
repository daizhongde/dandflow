package person.daizhongde.migration.util;

public class ThreadResource {
	
	/**  所有线程共享,取值范围：1～Max	 */
	private int num = 1; 
	
	/**  最大数量，实例化里初始化	 */
	private int max = 99999; 
	
	public ThreadResource(int max){
		this.max = max;
		this.num = 1;
	}

	public int readAValue(){
		if(num==max+1){
			num=1;
		}
		return num++;
	}
	public int getNum() {
		return num;
	}
	
	public int getMax() {
		return max;
	}
}
