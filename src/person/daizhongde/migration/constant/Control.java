package person.daizhongde.migration.constant;

import java.util.HashMap;
import java.util.Map;

public class Control {
//	SPLIT_FILE
//	LOAD_DATA
//	OUT_DATA_FILE
//	OUT_DATA_BASE
//	EXCUTE_SQL
//	EXCUTE_BIN
//	AUDIT
//	CONTROL
	/** SPLIT_FILE **/
	public static final String SPLIT_FILE="con001";
	/** LOAD_DATA**/
	public static final String LOAD_DATA="con002";
	/** OUT_DATA_FILE**/
	public static final String OUT_DATA_FILE="con003";
	/** OUT_DATA_BASE**/
	public static final String OUT_DATA_BASE="con004";
	/** EXCUTE_SQL**/
	public static final String EXCUTE_SQL="con005";
	/** EXCUTE_BIN**/
	public static final String EXCUTE_BIN="con006";
	/** AUDIT**/
	public static final String LEGALITY_AUDIT="con007";
	/** CONTROL**/
	public static final String CONTROL="con008";
	/** consistency AUDIT**/
	public static final String CONSISTENCY_AUDIT="con009";
	/** quality CONTROL**/
	public static final String QUALITY_CONTROL="con010";
	
	public static Map<String, String> clsMap = new HashMap();
	static {
		clsMap.put("con001", "icon-split");
		clsMap.put("con002", "icon-loaddata");
		clsMap.put("con003", "icon-outdf");
		clsMap.put("con004", "icon-outdb");
		
		clsMap.put("con005", "icon-sql");
		clsMap.put("con006", "icon-shell");
		clsMap.put("con007", "icon-audit");
		clsMap.put("con008", "icon-control");
		clsMap.put("con009", "icon-auditc");
		clsMap.put("con010", "icon-auditf");
	}
	public static String getClsbyId(String id){
		return clsMap.get(id);
	}
}
