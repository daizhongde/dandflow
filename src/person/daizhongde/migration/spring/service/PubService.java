package person.daizhongde.migration.spring.service;

public interface PubService {
	/**
	 * 获取14位编号：
	 * <p>
	 * 两位字母+六位日期+6位序列<br>
	 * 规则:
	 * 如果参数prefix没有参值系统取数据库的前缀，如果数据库没有前缀就不加前缀<br>
	 * 如果两个地方都没有指定前缀就只返回12位数字字符串
	 * <br>eg: DZ141022000001
	 * @param prefix 前缀
	 * @param table 表名
	 * @return 业务编号
	 */
	public String get14ByteCode(String prefix, String tableName );
	public String get14ByteCode(String tableName );
	/**
	 * 获取10位编号：
	 * <p>
	 * 两位字母+8位序列<br>
	 * 规则:
	 * 如果参数prefix没有参值系统取数据库的前缀，如果数据库没有前缀就不加前缀<br>
	 * 如果两个地方都没有指定前缀就只返回8位数字字符串
	 * <br>eg: DZ00000001
	 * @param prefix 前缀
	 * @param table 表名
	 * @return 业务编号
	 */
	public String get10ByteCode(String prefix, String tableName );
	public String get10ByteCode(String tableName );
}