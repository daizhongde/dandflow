package person.daizhongde.migration.util;

import java.text.MessageFormat;

/**
 * key序列，可防重启重复
 * @key组成方式yyyymmdd+序号，序号为定长六位，采用右对齐，左补0格方式。
 * @author dzd
 *
 *
 */
public class J_KeySerial2 {
	private static int currval = 0;
	/**
	 * 取得下一个六位序号
	 * @return
	 */
	public static String nextval(){
		//MessageFormat.format("{0,number,00000000}"
		//String paySerial = "";
		if(currval == 0){//invoke first time
			//zero count <= nine count, less zero to reduce reset times 
			currval = (int)(Math.random()*100);//防止程序一天启动多次重启，产生相同的交易流水号
		}else{
			currval = (currval >= 99)? 1: currval + 1;
		}
		return MessageFormat.format("{0,number,00}", new Integer(currval));
	}
	/**
	 * 取得当前(最后一次使用的)六位序号,类似于oracle中的sequence
	 * @return
	 */
	public static String currval(){
		return MessageFormat.format("{0,number,00}",new Integer(currval));
	}
	
	public static void main(String args[]){
		for(int i=0; i<99; i++ ){
			String jobid = nextval();
			System.out.println("new job id: "+jobid);
		}
	}
}
