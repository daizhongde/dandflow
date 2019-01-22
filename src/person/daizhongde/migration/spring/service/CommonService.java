package person.daizhongde.migration.spring.service;

import java.util.Map;

public interface CommonService {
	/**
	 * get DB table names
	 * @return
	 */
	public abstract String getTableNames();
	public abstract Map<String, String> getTableComment();
	public abstract Map<String, String> getTableColumns();
	public abstract Map<String, String> getSelectSQLColumns();
	
}
