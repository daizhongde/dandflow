package person.daizhongde.migration.spring.service.exp;

import java.util.List;
import java.util.Map;

import person.daizhongde.virtue.spring.BaseExportService;

/**
 * @author daizd
 *
 */
public interface MigAuditcReportDetailResultService extends BaseExportService{
	/**
	 * 
	 * @param jdata
	 * @param _
	 * @param columnNames
	 * @param options
	 * @param sql
	 * @return
	 * @throws Exception
	 * @author daizd
	 */
	public abstract String exportXLS_gbsubdomain(String jdata, String _, List columnNames, Map options, String sql) throws Exception;
	
	/**
	 * 
	 * @param jdata
	 * @param _
	 * @param columnNames
	 * @param options
	 * @param sql
	 * @return
	 * @throws Exception
	 * @author daizd
	 */
	public abstract String exportXLS_Diff(String jdata, String _, List columnNames, Map options, String sql) throws Exception;
	
}