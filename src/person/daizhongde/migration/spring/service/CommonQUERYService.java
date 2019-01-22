package person.daizhongde.migration.spring.service;

import java.util.List;

import person.daizhongde.virtue.assemble.sql.SQLAssembleQ;

public interface CommonQUERYService {
	/**
	 * 分页查询时用到, parameter sqlA pass in, because of it can only assemble a time
	 * @param sqlA
	 * @return 记录总条数
	 */
	public abstract long getTotal(SQLAssembleQ sqlA);

	
	/**
	 * 查询, parameter sqlA pass in
	 * @param sqlA
	 * @return 指定页的所有行
	 */
	public abstract List getRowsInMap( SQLAssembleQ sqlA );
	
	/**
	 * 分页查询, parameter sqlA pass in, because of it can only assemble a time
	 * @param sqlA
	 * @param offset
	 * @param pageSize
	 * @return 指定页的所有行
	 */
	public abstract List getRowsInMap(SQLAssembleQ sqlA, int offset, int pageSize);
}
