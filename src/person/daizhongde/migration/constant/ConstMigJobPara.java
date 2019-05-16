package person.daizhongde.migration.constant;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;import org.slf4j.LoggerFactory;

import person.daizhongde.virtue.configutils.ConfigDocument_JS;
import person.daizhongde.virtue.configutils.ConfigDocument_SQL;
import person.daizhongde.virtue.configutils.ConfigReader_JS;
import person.daizhongde.virtue.configutils.ConfigReader_PROP;
import person.daizhongde.virtue.configutils.ConfigReader_SQL;
import person.daizhongde.virtue.configutils.JSExportNode;
import person.daizhongde.virtue.configutils.JSFieldNode;
import person.daizhongde.virtue.configutils.JSQLParser;
import person.daizhongde.virtue.configutils.StringCaculate;
import person.daizhongde.virtue.constant.AbstractConstant;
import person.daizhongde.virtue.constant.EXPORT;
import person.daizhongde.virtue.constant.IMPORT;
import person.daizhongde.virtue.constant.INIT;

/**
 * load on web server start. 
 * <br>change value need restart server, then the new value become effective
 * <p>globel config don't write getter,but special config like pertable need write getter to support develop mode 
 * @author dzd
 * date: 20131224
 * modify dzd 20140225
 *
 */
public class ConstMigJobPara extends AbstractConstant {
	
	private static org.slf4j.Logger log = LoggerFactory.getLogger(ConstMigJobPara.class);
	
	private static String clazzName = new SecurityManager() {
		public String getClassName() {
			return getClassContext()[1].getName();
		}
	}.getClassName();

	private static String configFileDirectory = INIT.configFileDirectory;
	private static String fileName = clazzName.substring( clazzName.lastIndexOf(".") + 6 );
	private static String propFile = configFileDirectory+"/"+fileName+".properties";
	private static String sqlFile = configFileDirectory+"/"+fileName+".sql";
	private static String jsFile = configFileDirectory+"/"+fileName+".js";
	
	private static String tableName = 
			ConfigReader_PROP.findProperty(propFile,	"tableName");

	private static String pojoName = 
			ConfigReader_PROP.findProperty(propFile,	"pojoName");
	
	private static String tableName_zh = 
			ConfigReader_PROP.findProperty(propFile,	"tableName_zh");
	private static String primaryKeyColumnName = ConfigReader_PROP.findProperty(propFile,"primaryKeyColumnName");
	private static String primaryKeySequence = ConfigReader_PROP.findProperty(propFile, "primaryKeySequence");
	
	private static String _EXPfileName = ConfigReader_PROP.findProperty(propFile,"export.fileName");
	private static String EXPfileName = (_EXPfileName==null || _EXPfileName.trim().equalsIgnoreCase("")) ? 
			EXPORT.fileName : _EXPfileName;
	
	private static String _EXPsheetName = ConfigReader_PROP.findProperty(propFile, "export.sheetName");
	private static String EXPsheetName = (_EXPsheetName==null || _EXPsheetName.trim().equalsIgnoreCase("")) ?
			EXPORT.sheetName : _EXPsheetName;
	
	/** fixed column count range:0~columnCount, recomend 0~4,the column start at this first and obey the original order **/
	private static String _EXPfixColumnCount = ConfigReader_PROP.findProperty(propFile, "export.fixColumnCount");
	private static int EXPfixColumnCount = (_EXPfixColumnCount==null || _EXPfixColumnCount.trim().equalsIgnoreCase("")) ?
			EXPORT.fixColumnCount : Integer.valueOf(_EXPfixColumnCount);
	
	
	/** whether office excel file's sheet autoSizeColumn ,if data hurge care and suggest set false **/
	private static String _EXPsheetAutoSizeColumn = ConfigReader_PROP.findProperty( propFile, "export.sheetAutoSizeColumn");
	private static boolean EXPsheetAutoSizeColumn = (_EXPsheetAutoSizeColumn==null || _EXPsheetAutoSizeColumn.trim().equalsIgnoreCase("")) ? 
			EXPORT.sheetAutoSizeColumn : Boolean.parseBoolean(_EXPsheetAutoSizeColumn);
			
	/** if the EXP file compressed, value can be:  true,false **/
	private static String _EXPcompress = ConfigReader_PROP.findProperty( propFile, "export.compress");
	private static boolean EXPcompress = (_EXPcompress==null || _EXPcompress.trim().equalsIgnoreCase("")) ? 
			EXPORT.compress : Boolean.parseBoolean(_EXPcompress);
	
	/** the max record size of single excel sheet , range :1~65534, one row remain for column label **/
	private static String _EXPsingleSheetRecordSize = ConfigReader_PROP.findProperty( propFile, "export.singleSheetRecordSize");
	private static int EXPsingleSheetRecordSize = (_EXPsingleSheetRecordSize==null || _EXPsingleSheetRecordSize.trim().equalsIgnoreCase("")) ?
			EXPORT.singleSheetRecordSize : Integer.valueOf(_EXPsingleSheetRecordSize);
	
	/** 
	 * the max record size of single excel file , the max size is depend on the one row data size, 150000 is recommend
	 * <br>singleFileRecordSize>=singleSheetRecordSize  **/
	private static String _EXPsingleFileRecordSize = ConfigReader_PROP.findProperty( propFile, "export.singleFileRecordSize");
	private static int EXPsingleFileRecordSize = (_EXPsingleFileRecordSize==null || _EXPsingleFileRecordSize.trim().equalsIgnoreCase("")) ? 
			EXPORT.singleFileRecordSize : Integer.valueOf(_EXPsingleFileRecordSize);

	private static String _EXPlevel1MinCount = ConfigReader_PROP.findProperty( propFile, "export.level1MinCount");
	private static int EXPlevel1MinCount = (_EXPlevel1MinCount==null || _EXPlevel1MinCount.trim().equalsIgnoreCase("")) ? 
			EXPORT.level1MinCount : Integer.valueOf(_EXPlevel1MinCount);
	
	private static String _EXPlevel2MinCount = ConfigReader_PROP.findProperty( propFile, "export.level2MinCount");
	private static int EXPlevel2MinCount = (_EXPlevel2MinCount==null || _EXPlevel2MinCount.trim().equalsIgnoreCase("")) ? 
			EXPORT.level2MinCount : Integer.valueOf(_EXPlevel2MinCount);
	
	private static String _EXPlevel3MinCount = ConfigReader_PROP.findProperty( propFile, "export.level3MinCount");
	private static int EXPlevel3MinCount = (_EXPlevel3MinCount==null || _EXPlevel3MinCount.trim().equalsIgnoreCase("")) ? 
			EXPORT.level3MinCount : Integer.valueOf(_EXPlevel3MinCount);
	
	/** EXP data mode. value scope: true- synchronous / false - asynchronous **/
	private static String _EXPsynchronous = ConfigReader_PROP.findProperty( propFile,  "export.synchronous");
	private static boolean EXPsynchronous = (_EXPsynchronous==null || _EXPsynchronous.trim().equalsIgnoreCase("")) ? 
			EXPORT.synchronous : Boolean.parseBoolean(_EXPsynchronous);
	
	/** The maximum number of threads ,use of EXP data file **/
	private static String _EXPmaxThreadNumber = ConfigReader_PROP.findProperty( propFile,  "export.maxThreadNumber");
	private static int EXPmaxThreadNumber = (_EXPmaxThreadNumber==null || _EXPmaxThreadNumber.trim().equalsIgnoreCase("")) ? 
			EXPORT.maxThreadNumber : Integer.valueOf(_EXPmaxThreadNumber);
	
	private static String _EXPtimeout = ConfigReader_PROP.findProperty( propFile, "export.timeout");
	private static int EXPtimeout = (_EXPtimeout==null || _EXPtimeout.trim().equalsIgnoreCase("")) ? 
			EXPORT.timeout : new StringCaculate().parse(_EXPtimeout.replaceAll(" ", "")).intValue();
	
	/** 
	 * Import config 
	 * the max record size of single excel file , the max size is depend on the one row data size, 150000 is recommend
	 * <br>singleFileRecordSize>=singleSheetRecordSize  **/
	private static String _IMPsingleFileMaxSize = ConfigReader_PROP.findProperty( propFile, "import.singleFileMaxSize");
	private static int IMPsingleFileMaxSize = (_IMPsingleFileMaxSize==null || _IMPsingleFileMaxSize.trim().equalsIgnoreCase("")) ? 
			IMPORT.singleFileMaxSize : new StringCaculate().parse(ConfigReader_PROP.findProperty( propFile, "import.singleFileMaxSize").replaceAll(" ", "")).intValue();

	private static String _IMPlevel1MinSize = ConfigReader_PROP.findProperty( propFile, "import.level1MinSize");
	private static int IMPlevel1MinSize = (_IMPlevel1MinSize==null || _IMPlevel1MinSize.trim().equalsIgnoreCase("")) ? 
			IMPORT.level1MinSize : new StringCaculate().parse(ConfigReader_PROP.findProperty( propFile, "import.level1MinSize").replaceAll(" ", "")).intValue();
	
	private static String _IMPlevel2MinSize = ConfigReader_PROP.findProperty( propFile, "import.level2MinSize");
	private static int IMPlevel2MinSize = (_IMPlevel2MinSize==null || _IMPlevel2MinSize.trim().equalsIgnoreCase("")) ? 
			IMPORT.level2MinSize : new StringCaculate().parse(ConfigReader_PROP.findProperty( propFile, "import.level2MinSize").replaceAll(" ", "")).intValue();
	
	private static String _IMPlevel3MinSize = ConfigReader_PROP.findProperty( propFile, "import.level3MinSize");
	private static int IMPlevel3MinSize = (_IMPlevel3MinSize==null || _IMPlevel3MinSize.trim().equalsIgnoreCase("")) ? 
			IMPORT.level3MinSize : new StringCaculate().parse(ConfigReader_PROP.findProperty( propFile, "import.level3MinSize").replaceAll(" ", "")).intValue();
	
	/** IMP data mode. value scope: true- synchronous / false - asynchronous **/
	private static String _IMPsynchronous = ConfigReader_PROP.findProperty( propFile,  "import.synchronous");
	private static boolean IMPsynchronous = (_IMPsynchronous==null || _IMPsynchronous.trim().equalsIgnoreCase("")) ? 
			IMPORT.synchronous : Boolean.parseBoolean(_IMPsynchronous);
	
	/** The maximum number of threads ,use of IMP data file **/
	private static String _IMPmaxThreadNumber = ConfigReader_PROP.findProperty( propFile,  "import.maxThreadNumber");
	private static int IMPmaxThreadNumber = (_IMPmaxThreadNumber==null || _IMPmaxThreadNumber.trim().equalsIgnoreCase("")) ? 
			IMPORT.maxThreadNumber : Integer.valueOf(_IMPmaxThreadNumber);
	
	private static String _IMPtimeout = ConfigReader_PROP.findProperty( propFile, "import.timeout");
	private static int IMPtimeout = (_IMPtimeout==null || _IMPtimeout.trim().equalsIgnoreCase("")) ? 
			IMPORT.timeout : new StringCaculate().parse(_IMPtimeout.replaceAll(" ", "")).intValue();

	/** All XXXX.sql's config  */
	private static ConfigDocument_SQL SQLDOC = ConfigReader_SQL.read(sqlFile);

	/** query string in the config file. <p>eg:<br>select t1.N_MID,t1.C_MNAME from t_authority_module t1 left outer join t_authority_module t2 on t1.N_MPARENT=t2.N_MID   **/
	private static String _query_SQL = SQLDOC.getQuery().get("query").getSQL();
	private static String query_SQL = (_query_SQL==null || _query_SQL.trim().equalsIgnoreCase("")) ? 
			"select * from "+tableName : _query_SQL;
	private static int query_SQL_fromIndex = query_SQL.toLowerCase().indexOf("from ");
	/**  from a='f' and b='cf', include 'from' keywords **/
	private static String query_SQL_fromSQL = query_SQL.substring( query_SQL_fromIndex );
	
	private static String _query_HQL = SQLDOC.getQuery().get("query").getHQL();
	private static String query_HQL = (_query_HQL==null || _query_HQL.trim().equalsIgnoreCase(""))?
			"from "+pojoName : _query_HQL;
	private static int query_HQL_fromIndex = query_HQL.toLowerCase().indexOf("from ");
	private static String query_HQL_fromSQL = query_HQL.substring( query_HQL_fromIndex );
	
	private static String _read_SQL= SQLDOC.getRead().get("read").getSQL();
	private static String read_SQL = (_read_SQL==null || _read_SQL.trim().equalsIgnoreCase(""))?
			"select * from "+tableName : _read_SQL;
//	private static int read_SQL_fromIndex = read_SQL.toLowerCase().indexOf("from ");
//	private static String read_SQL_fromSQL = read_SQL.substring( read_SQL_fromIndex );
	
	private static String _read_HQL = SQLDOC.getRead().get("read").getHQL();
	private static String read_HQL = (_read_HQL==null || _read_HQL.trim().equalsIgnoreCase("")) ? 
			"from "+pojoName : _read_HQL;
//	private static int read_HQL_fromIndex = read_HQL.toLowerCase().indexOf("from ");
//	private static String read_HQL_fromSQL = read_HQL.substring( read_HQL_fromIndex );
	
	private static String _combobox_SQL = SQLDOC.getCombobox().get("combobox").getSQL();
	private static String combobox_SQL = (_combobox_SQL==null || _combobox_SQL.trim().equalsIgnoreCase("")) ? 
			"select * from "+tableName : _combobox_SQL;
//	private static int combobox_SQL_fromIndex = combobox_SQL.toLowerCase().indexOf("from ");
//	private static String combobox_SQL_fromSQL = combobox_SQL.substring( combobox_SQL_fromIndex );
	
	private static String _combobox_HQL = SQLDOC.getCombobox().get("combobox").getHQL();
	private static String combobox_HQL = (_combobox_HQL==null || _combobox_HQL.trim().equalsIgnoreCase("")) ? 
			"from "+pojoName : _combobox_HQL;
//	private static int combobox_HQL_fromIndex = combobox_HQL.toLowerCase().indexOf("from ");
//	private static String combobox_HQL_fromSQL = combobox_HQL.substring( combobox_HQL_fromIndex );
	
	private static String nest_SQL =  SQLDOC.getNest().get("nest").getSQL();
	
	private static String nest_HQL = SQLDOC.getNest().get("nest").getHQL();
	
	private static String _EXP_SQL = SQLDOC.getExport().get("export").getSQL();
	
	private static String EXP_SQL = (_EXP_SQL==null || _EXP_SQL.trim().equalsIgnoreCase("")) ? 
			"select * from "+tableName : _EXP_SQL;
	
	private static int EXP_SQL_fromIndex = EXP_SQL.toLowerCase().indexOf("from ");
	
	private static String EXP_SQL_fromSQL = EXP_SQL.substring( EXP_SQL_fromIndex );
	
	private static String _EXP_HQL = SQLDOC.getExport().get("export").getHQL();
	private static String EXP_HQL = (_EXP_HQL==null || _EXP_HQL.trim().equalsIgnoreCase("")) ? 
			"from "+pojoName : _EXP_HQL;
	private static int EXP_HQL_fromIndex = EXP_HQL.toLowerCase().indexOf("from ");
	private static String EXP_HQL_fromSQL = EXP_HQL.substring( EXP_HQL_fromIndex );
	
	/** All XXXX.js's config  */
	private static ConfigDocument_JS JSDOC = ConfigReader_JS.read(jsFile);
	
	private static JSFieldNode _JSFieldNode = JSDOC.getField();
	
	/** {N_MID:2,C_MNAME:12,C_MCTIME:93,N_MCUSER:2,C_MMTIME:93} */
	private static Map<String, Integer> columnTypes = _JSFieldNode.getColumnTypes();
	private static Map<String, String> col2front = _JSFieldNode.getCol2front();
	private static Map<String, String> col2back = _JSFieldNode.getCol2back();
	private static Map<String, String> front2back = _JSFieldNode.getFront2back();
	private static Map<String, String> front2col = _JSFieldNode.getFront2col();
	private static Map<String, String> back2front = _JSFieldNode.getBack2front();
	private static Map<String, String> back2col = _JSFieldNode.getBack2col();
	
	/** for export config. it is optional  */
	private static JSExportNode _JSExportNode = JSDOC.getExport().get("export");
	
	/**  element ColumnLabel: [ columnTypes, columnPrecisions, columnScales, columnNames_zh, index ]   */
	private static Map<String, List> EXPcolumnMap = _JSExportNode.getColumnMap();
	
	private static String[] EXPcolumnNames = _JSExportNode.getColumnNames();
	private static String[] EXPcolumnNames_zh = _JSExportNode.getColumnNames_zh();
	private static int[] EXPcolumnTypes = _JSExportNode.getColumnTypes();
	private static int[] EXPcolumnPrecisions = _JSExportNode.getColumnPrecisions();
	private static int[] EXPcolumnScales = _JSExportNode.getColumnScales();
	
	private static HashMap<String, String> EXPAllColumnSQLMap = JSQLParser.getSelectMap( EXP_SQL );
	
	/*  below fourteen get are get from sql file */
	public String getTableName() {
		if(INIT.PRODUCTION_MODE){
			return tableName;
		}else{
			tableName = ConfigReader_PROP.findProperty(propFile,	"tableName");
			return tableName;
		}
	}
	public String getPojoName() {
		if(INIT.PRODUCTION_MODE)
		{
			return pojoName;
		}
		else
		{
			pojoName = ConfigReader_PROP.findProperty(propFile,	"pojoName");
			return pojoName;
		}
		
	}
	public String getTableName_zh() {
		if(INIT.PRODUCTION_MODE)
		{
			return tableName_zh;
		}
		else
		{
			tableName_zh = ConfigReader_PROP.findProperty(propFile,	"tableName_zh");
			return tableName_zh;
		}
	}

	public String getPrimaryKeyColumnName() {
		if(INIT.PRODUCTION_MODE)
		{
			return primaryKeyColumnName;
		}
		else
		{
			primaryKeyColumnName = ConfigReader_PROP.findProperty(propFile,	"primaryKeyColumnName");
			return primaryKeyColumnName;
		}
	}

	public String getPrimaryKeySequence() {
		if(INIT.PRODUCTION_MODE)
		{
			return primaryKeySequence;
		}
		else
		{
			primaryKeySequence = ConfigReader_PROP.findProperty(propFile,	"primaryKeySequence");
			return primaryKeySequence;
		}
	}
	public String getEXPfileName() {
		if(INIT.PRODUCTION_MODE)
		{
			return EXPfileName;
		}
		else
		{
			_EXPfileName = ConfigReader_PROP.findProperty(propFile,"export.fileName");
			EXPfileName = (_EXPfileName==null || _EXPfileName.trim().equalsIgnoreCase("")) ? 
					EXPORT.fileName : _EXPfileName;
			return EXPfileName;
		}
	}

	public String getEXPsheetName() {
		if(INIT.PRODUCTION_MODE)
		{
			return EXPsheetName;
		}
		else
		{
			_EXPsheetName = ConfigReader_PROP.findProperty(propFile,"export.sheetName");
			EXPsheetName = (_EXPsheetName==null || _EXPsheetName.trim().equalsIgnoreCase("")) ? 
					EXPORT.sheetName : _EXPsheetName;
			return EXPsheetName;
		}
	}

	public int getEXPfixColumnCount() {
		if(INIT.PRODUCTION_MODE)
		{
			return EXPfixColumnCount;
		}
		else
		{
			_EXPfixColumnCount = ConfigReader_PROP.findProperty(propFile, "export.fixColumnCount");
			EXPfixColumnCount = (_EXPfixColumnCount==null || _EXPfixColumnCount.trim().equalsIgnoreCase("")) ?
					EXPORT.fixColumnCount : Integer.valueOf(_EXPfixColumnCount);
			return EXPfixColumnCount;
		}
	}
	
	public boolean isEXPsheetAutoSizeColumn() {
		if(INIT.PRODUCTION_MODE)
		{
			return EXPsheetAutoSizeColumn;
		}
		else
		{
			_EXPsheetAutoSizeColumn = ConfigReader_PROP.findProperty( propFile, "export.sheetAutoSizeColumn");
			EXPsheetAutoSizeColumn = (_EXPsheetAutoSizeColumn==null || _EXPsheetAutoSizeColumn.trim().equalsIgnoreCase("")) ? 
					EXPORT.sheetAutoSizeColumn : Boolean.parseBoolean(_EXPsheetAutoSizeColumn);
			return EXPsheetAutoSizeColumn;
		}
	}

	public boolean isEXPcompress() {
		if(INIT.PRODUCTION_MODE)
		{
			return EXPcompress;
		}
		else
		{
			_EXPcompress = ConfigReader_PROP.findProperty( propFile, "export.compress");
			EXPcompress = (_EXPcompress==null || _EXPcompress.trim().equalsIgnoreCase("")) ? 
					EXPORT.compress : Boolean.parseBoolean(_EXPcompress);
			return EXPcompress;
		}
	}
	public int getEXPsingleSheetRecordSize() {
		if(INIT.PRODUCTION_MODE)
		{
			return EXPsingleSheetRecordSize;
		}
		else
		{
			_EXPsingleSheetRecordSize = ConfigReader_PROP.findProperty( propFile, "export.singleSheetRecordSize");
			EXPsingleSheetRecordSize = (_EXPsingleSheetRecordSize==null || _EXPsingleSheetRecordSize.trim().equalsIgnoreCase("")) ?
					EXPORT.singleSheetRecordSize : Integer.valueOf(_EXPsingleSheetRecordSize);
			return EXPsingleSheetRecordSize;
		}
	}

	public int getEXPsingleFileRecordSize() {
		if(INIT.PRODUCTION_MODE)
		{
			return EXPsingleFileRecordSize;
		}
		else
		{
			_EXPsingleFileRecordSize = ConfigReader_PROP.findProperty( propFile, "export.singleFileRecordSize");
			EXPsingleFileRecordSize = (_EXPsingleFileRecordSize==null || _EXPsingleFileRecordSize.trim().equalsIgnoreCase("")) ? 
					EXPORT.singleFileRecordSize : Integer.valueOf(_EXPsingleFileRecordSize);
			return EXPsingleFileRecordSize;
		}
	}
	
	public int getEXPlevel1MinCount() {
		if(INIT.PRODUCTION_MODE)
		{
			return EXPlevel1MinCount;
		}
		else
		{
			_EXPlevel1MinCount = ConfigReader_PROP.findProperty( propFile, "export.level1MinCount");
			EXPlevel1MinCount = (_EXPlevel1MinCount==null || _EXPlevel1MinCount.trim().equalsIgnoreCase("")) ? 
					EXPORT.level1MinCount : Integer.valueOf(_EXPlevel1MinCount);
			return EXPlevel1MinCount;
		}
	}

	public int getEXPlevel2MinCount() {
		if(INIT.PRODUCTION_MODE)
		{
			return EXPlevel2MinCount;
		}
		else
		{
			_EXPlevel2MinCount = ConfigReader_PROP.findProperty( propFile, "export.level2MinCount");
			EXPlevel2MinCount = (_EXPlevel2MinCount==null || _EXPlevel2MinCount.trim().equalsIgnoreCase("")) ? 
					EXPORT.level2MinCount : Integer.valueOf(_EXPlevel2MinCount);
			return EXPlevel2MinCount;
		}
	}

	public int getEXPlevel3MinCount() {
		if(INIT.PRODUCTION_MODE)
		{
			return EXPlevel3MinCount;
		}
		else
		{
			_EXPlevel3MinCount = ConfigReader_PROP.findProperty( propFile, "export.level3MinCount");
			EXPlevel3MinCount = (_EXPlevel3MinCount==null || _EXPlevel3MinCount.trim().equalsIgnoreCase("")) ? 
					EXPORT.level3MinCount : Integer.valueOf(_EXPlevel3MinCount);
			return EXPlevel3MinCount;
		}
	}

	public boolean isEXPsynchronous() {
		if(INIT.PRODUCTION_MODE)
		{
			return EXPsynchronous;
		}
		else
		{
			_EXPsynchronous = ConfigReader_PROP.findProperty( propFile,  "export.synchronous");
			EXPsynchronous = (_EXPsynchronous==null || _EXPsynchronous.trim().equalsIgnoreCase("")) ? 
					EXPORT.synchronous : Boolean.parseBoolean(_EXPsynchronous);
			return EXPsynchronous;
		}
	}

	public int getEXPmaxThreadNumber() {
		if(INIT.PRODUCTION_MODE)
		{
			return EXPmaxThreadNumber;
		}
		else
		{
			_EXPmaxThreadNumber = ConfigReader_PROP.findProperty( propFile,  "export.maxThreadNumber");
			EXPmaxThreadNumber = (_EXPmaxThreadNumber==null || _EXPmaxThreadNumber.trim().equalsIgnoreCase("")) ? 
					EXPORT.maxThreadNumber : Integer.valueOf(_EXPmaxThreadNumber);
			return EXPmaxThreadNumber;
		}
	}

	public int getEXPtimeout() {
		if(INIT.PRODUCTION_MODE)
		{
			return EXPtimeout;
		}
		else
		{
			_EXPtimeout = ConfigReader_PROP.findProperty( propFile, "export.timeout");
			EXPtimeout = (_EXPtimeout==null || _EXPtimeout.trim().equalsIgnoreCase("")) ? 
					EXPORT.timeout : new StringCaculate().parse(_EXPtimeout.replaceAll(" ", "")).intValue();
			return EXPtimeout;
		}
	}
	/**
	 *Import constant config
	 */
	public int getIMPsingleFileMaxSize() {
		if(INIT.PRODUCTION_MODE)
		{
			return IMPsingleFileMaxSize;
		}
		else
		{
			_IMPsingleFileMaxSize = ConfigReader_PROP.findProperty( propFile, "import.singleFileMaxSize");
			IMPsingleFileMaxSize = (_IMPsingleFileMaxSize==null || _IMPsingleFileMaxSize.trim().equalsIgnoreCase("")) ? 
					IMPORT.singleFileMaxSize : Integer.valueOf(_IMPsingleFileMaxSize);
			return IMPsingleFileMaxSize;
		}
	}
	
	public int getIMPlevel1MinSize() {
		if(INIT.PRODUCTION_MODE)
		{
			return IMPlevel1MinSize;
		}
		else
		{
			_IMPlevel1MinSize = ConfigReader_PROP.findProperty( propFile, "import.level1MinSize");
			IMPlevel1MinSize = (_IMPlevel1MinSize==null || _IMPlevel1MinSize.trim().equalsIgnoreCase("")) ? 
					IMPORT.level1MinSize : Integer.valueOf(_IMPlevel1MinSize);
			return IMPlevel1MinSize;
		}
	}

	public int getIMPlevel2MinSize() {
		if(INIT.PRODUCTION_MODE)
		{
			return IMPlevel2MinSize;
		}
		else
		{
			_IMPlevel2MinSize = ConfigReader_PROP.findProperty( propFile, "import.level2MinSize");
			IMPlevel2MinSize = (_IMPlevel2MinSize==null || _IMPlevel2MinSize.trim().equalsIgnoreCase("")) ? 
					IMPORT.level2MinSize : Integer.valueOf(_IMPlevel2MinSize);
			return IMPlevel2MinSize;
		}
	}

	public int getIMPlevel3MinSize() {
		if(INIT.PRODUCTION_MODE)
		{
			return IMPlevel3MinSize;
		}
		else
		{
			_IMPlevel3MinSize = ConfigReader_PROP.findProperty( propFile, "import.level3MinSize");
			IMPlevel3MinSize = (_IMPlevel3MinSize==null || _IMPlevel3MinSize.trim().equalsIgnoreCase("")) ? 
					IMPORT.level3MinSize : Integer.valueOf(_IMPlevel3MinSize);
			return IMPlevel3MinSize;
		}
	}

	public boolean isIMPsynchronous() {
		if(INIT.PRODUCTION_MODE)
		{
			return IMPsynchronous;
		}
		else
		{
			_IMPsynchronous = ConfigReader_PROP.findProperty( propFile,  "import.synchronous");
			IMPsynchronous = (_IMPsynchronous==null || _IMPsynchronous.trim().equalsIgnoreCase("")) ? 
					IMPORT.synchronous : Boolean.parseBoolean(_IMPsynchronous);
			return IMPsynchronous;
		}
	}

	public int getIMPmaxThreadNumber() {
		if(INIT.PRODUCTION_MODE)
		{
			return IMPmaxThreadNumber;
		}
		else
		{
			_IMPmaxThreadNumber = ConfigReader_PROP.findProperty( propFile,  "import.maxThreadNumber");
			IMPmaxThreadNumber = (_IMPmaxThreadNumber==null || _IMPmaxThreadNumber.trim().equalsIgnoreCase("")) ? 
					IMPORT.maxThreadNumber : Integer.valueOf(_IMPmaxThreadNumber);
			return IMPmaxThreadNumber;
		}
	}

	public int getIMPtimeout() {
		if(INIT.PRODUCTION_MODE)
		{
			return IMPtimeout;
		}
		else
		{
			_IMPtimeout = ConfigReader_PROP.findProperty( propFile, "import.timeout");
			IMPtimeout = (_IMPtimeout==null || _IMPtimeout.trim().equalsIgnoreCase("")) ? 
					IMPORT.timeout : new StringCaculate().parse(_IMPtimeout.replaceAll(" ", "")).intValue();
			return IMPtimeout;
		}
	}
	
	public ConfigDocument_SQL getSQLDOC() {
		if(INIT.PRODUCTION_MODE)
		{
			return SQLDOC;
		}
		else
		{
			SQLDOC = ConfigReader_SQL.read(sqlFile);
			return SQLDOC;
		}
	}
	public String getQuery_SQL() {
		if(INIT.PRODUCTION_MODE)
		{
			return query_SQL;
		}
		else
		{
			_query_SQL = this.getSQLDOC().getQuery().get("query").getSQL();
			query_SQL = (_query_SQL==null || _query_SQL.trim().equalsIgnoreCase("")) ? 
					"select * from "+tableName : _query_SQL;
			return query_SQL;
		}
	}
	
	public String getQuery_SQL_fromSQL() {
		if(INIT.PRODUCTION_MODE)
		{
			return query_SQL_fromSQL;
		}
		else
		{
			query_SQL_fromIndex = this.getQuery_SQL().toLowerCase().indexOf("from ");
			/**  from a='f' and b='cf', include 'from' keywords **/
			query_SQL_fromSQL = this.getQuery_SQL().substring( query_SQL_fromIndex );
			return query_SQL_fromSQL;
		}
	}
	public String getQuery_HQL() {
		if(INIT.PRODUCTION_MODE)
		{
			return query_HQL;
		}
		else
		{
			_query_HQL = this.getSQLDOC().getQuery().get("query").getHQL();
			query_HQL = (_query_HQL==null || _query_HQL.trim().equalsIgnoreCase(""))?
					"from "+this.getPojoName() : _query_HQL;
			
			return query_HQL;
		}
	}
	public String getQuery_HQL_fromSQL() {
		if(INIT.PRODUCTION_MODE)
		{
			return query_HQL_fromSQL;
		}
		else
		{
			query_HQL_fromIndex = this.getQuery_HQL().toLowerCase().indexOf("from ");
			query_HQL_fromSQL = this.getQuery_HQL().substring( query_HQL_fromIndex );
			
			return query_HQL_fromSQL;
		}
	}
	
	public String getRead_SQL() {
		if(INIT.PRODUCTION_MODE)
		{
			return read_SQL;
		}
		else
		{
			_read_SQL= this.getSQLDOC().getRead().get("read").getSQL();
			read_SQL = (_read_SQL==null || _read_SQL.trim().equalsIgnoreCase(""))?
					"select * from "+this.getTableName() : _read_SQL;
			
			return read_SQL;
		}
	}
	public String getRead_HQL() {
		if(INIT.PRODUCTION_MODE)
		{
			return read_HQL;
		}
		else
		{
			_read_HQL = this.getSQLDOC().getRead().get("read").getHQL();
			read_HQL = (_read_HQL==null || _read_HQL.trim().equalsIgnoreCase("")) ? 
					"from "+this.getPojoName() : _read_HQL;
			
			return read_HQL;
		}
	}
	public String getCombobox_SQL() {
		if(INIT.PRODUCTION_MODE)
		{
			return combobox_SQL;
		}
		else
		{
			_combobox_SQL = this.getSQLDOC().getCombobox().get("combobox").getSQL();
			combobox_SQL = (_combobox_SQL==null || _combobox_SQL.trim().equalsIgnoreCase("")) ? 
					"select * from "+this.getTableName() : _combobox_SQL;
			
			return combobox_SQL;
		}
	}
	public String getCombobox_HQL() {
		if(INIT.PRODUCTION_MODE)
		{
			return combobox_HQL;
		}
		else
		{
			_combobox_HQL = SQLDOC.getCombobox().get("combobox").getHQL();
			combobox_HQL = (_combobox_HQL==null || _combobox_HQL.trim().equalsIgnoreCase("")) ? 
					"from "+pojoName : _combobox_HQL;
			return combobox_HQL;
		}
	}
	
	public String getNest_SQL() {
		if(INIT.PRODUCTION_MODE)
		{
			return nest_SQL;
		}
		else
		{
			nest_SQL = this.getSQLDOC().getNest().get("nest").getSQL();
			return nest_SQL;
		}
	}

	public String getNest_HQL() {
		if(INIT.PRODUCTION_MODE)
		{
			return nest_HQL;
		}
		else
		{
			nest_HQL = this.getSQLDOC().getNest().get("nest").getHQL();
			return nest_HQL;
		}		
	}
	
	public String getEXP_SQL() {
		if(INIT.PRODUCTION_MODE)
		{
			return EXP_SQL;
		}
		else
		{
			_EXP_SQL = this.getSQLDOC().getExport().get("export").getSQL();
			EXP_SQL = (_EXP_SQL==null || _EXP_SQL.trim().equalsIgnoreCase("")) ? 
					"select * from "+tableName : _EXP_SQL;
			return EXP_SQL;
		}
	}

	public String getEXP_SQL_fromSQL() {
		if(INIT.PRODUCTION_MODE)
		{
			return EXP_SQL_fromSQL;
		}
		else
		{
			EXP_SQL_fromIndex = this.getEXP_SQL().toLowerCase().indexOf("from ");
			EXP_SQL_fromSQL = this.getEXP_SQL().substring( EXP_SQL_fromIndex );
			return EXP_SQL_fromSQL;
		}
	}

	public String getEXP_HQL() {
		if(INIT.PRODUCTION_MODE)
		{
			return EXP_HQL;
		}
		else
		{
			_EXP_HQL = this.getSQLDOC().getExport().get("export").getHQL();
			EXP_HQL = (_EXP_HQL==null || _EXP_HQL.trim().equalsIgnoreCase("")) ? 
					"from "+this.getPojoName() : _EXP_HQL;
			return EXP_HQL;
		}		
	}

	public String getEXP_HQL_fromSQL() {
		if(INIT.PRODUCTION_MODE)
		{
			return EXP_HQL_fromSQL;
		}
		else
		{
			EXP_HQL_fromIndex = this.getEXP_HQL().toLowerCase().indexOf("from ");
			EXP_HQL_fromSQL = this.getEXP_HQL().substring( EXP_HQL_fromIndex );
			return EXP_HQL_fromSQL;
		}
	}
	
	public ConfigDocument_JS getJSDOC() {
		if(INIT.PRODUCTION_MODE)
		{
			return JSDOC;
		}
		else
		{
			JSDOC = ConfigReader_JS.read(jsFile);
			return JSDOC;
		}
	}
	
	public Map<String, Integer> getColumnTypes() {
		if(INIT.PRODUCTION_MODE)
		{
			return columnTypes;
		}
		else
		{
			columnTypes = this.getJSDOC().getField().getColumnTypes();
			return columnTypes;
		}
	}
	
	public Map<String, String> getCol2front() {
		if(INIT.PRODUCTION_MODE)
		{
			return col2front;
		}
		else
		{
			col2front = this.getJSDOC().getField().getCol2front();
			return col2front;
		}
	}
	public Map<String, String> getCol2back() {
		if(INIT.PRODUCTION_MODE)
		{
			return col2back;
		}
		else
		{
			col2back = this.getJSDOC().getField().getCol2back();
			return col2back;
		}
	}
	public Map<String, String> getFront2back() {
		if(INIT.PRODUCTION_MODE)
		{
			return front2back;
		}
		else
		{
			front2back = this.getJSDOC().getField().getFront2back();
			return front2back;
		}
	}
	
	public Map<String, String> getFront2col() {
		if(INIT.PRODUCTION_MODE)
		{
			return front2col;
		}
		else
		{
			front2col = this.getJSDOC().getField().getFront2col();
			return front2col;
		}
	}
	public Map<String, String> getBack2front() {
		if(INIT.PRODUCTION_MODE)
		{
			return back2front;
		}
		else
		{
			back2front = this.getJSDOC().getField().getBack2front();
			return back2front;
		}
	}
	
	public Map<String, String> getBack2col() {
		if(INIT.PRODUCTION_MODE)
		{
			return back2col;
		}
		else
		{
			back2col = this.getJSDOC().getField().getBack2col();
			return back2col;
		}
	}
	public Map<String, List> getEXPcolumnMap() {
		if(INIT.PRODUCTION_MODE)
		{
			return EXPcolumnMap;
		}
		else
		{
			EXPcolumnMap = this.getJSDOC().getExport().get("export").getColumnMap();
			return EXPcolumnMap;
		}
	}
	public String[] getEXPcolumnNames() {
		if(INIT.PRODUCTION_MODE)
		{
			return EXPcolumnNames;
		}
		else
		{
			EXPcolumnNames = this.getJSDOC().getExport().get("export").getColumnNames();
			return EXPcolumnNames;
		}	
	}
	public String[] getEXPcolumnNames_zh() {
		if(INIT.PRODUCTION_MODE)
		{
			return EXPcolumnNames_zh;
		}
		else
		{
			EXPcolumnNames_zh = this.getJSDOC().getExport().get("export").getColumnNames_zh();
			return EXPcolumnNames_zh;
		}	
	}
	
	public int[] getEXPcolumnTypes() {
		if(INIT.PRODUCTION_MODE)
		{
			return EXPcolumnTypes;
		}
		else
		{
			EXPcolumnTypes = this.getJSDOC().getExport().get("export").getColumnTypes();
			return EXPcolumnTypes;
		}
	}
	
	public int[] getEXPcolumnPrecisions() {
		if(INIT.PRODUCTION_MODE)
		{
			return EXPcolumnPrecisions;
		}
		else
		{
			EXPcolumnPrecisions = this.getJSDOC().getExport().get("export").getColumnPrecisions();
			return EXPcolumnPrecisions;
		}
	}
	public int[] getEXPcolumnScales() {
		if(INIT.PRODUCTION_MODE)
		{
			return EXPcolumnScales;
		}
		else
		{
			EXPcolumnScales = this.getJSDOC().getExport().get("export").getColumnScales();
			return EXPcolumnScales;
		}
	}
	public HashMap<String, String> getEXPAllColumnSQLMap() {
		if(INIT.PRODUCTION_MODE)
		{
			return EXPAllColumnSQLMap;
		}
		else
		{
			EXPAllColumnSQLMap = JSQLParser.getSelectMap( this.getEXP_SQL() ); 
			return EXPAllColumnSQLMap;
		}		
	}
	
	public static void main(String args[]) throws NoSuchFieldException, SecurityException{
//		System.out.println("ConfigConstant.class.getField(\"TXXXX_query\").getName():"+TXXXX_Constant.class.getField("TXXXX_query").getName());
//		System.out.println("ConfigConstant.class.getField(\"TXXXX_query\"):"+TXXXX_Constant.class.getField("TXXXX_query"));
		System.out.println("query_SQL_fromSQL:"+query_SQL_fromSQL);
	}
}