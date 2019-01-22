package person.daizhongde.migration.spring.jdbc;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.test.HSSFReadWrite;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.context.ApplicationContext;

import person.daizhongde.migration.constant.ConstMigSyncConfig;
import person.daizhongde.migration.exception.BusinessException;
import person.daizhongde.migration.hibernate.dao.MigSyncConfigDAO;

import person.daizhongde.virtue.assemble.sql.SQLAssembleE;
import person.daizhongde.virtue.constant.AbstractConstant;
import person.daizhongde.virtue.constant.EXPORT;
import person.daizhongde.virtue.constant.IMPORT;
import person.daizhongde.virtue.jdbc.JDBCSpringConnection;
import person.daizhongde.virtue.jdbc.batch.JDBCBatchInsert;
import person.daizhongde.virtue.spring.BaseImportService;
import person.daizhongde.virtue.util.collection.MapUtils;
import person.daizhongde.virtue.util.ie.HSSFWorkbookUtil4Import;
import person.daizhongde.virtue.util.ie.POICellStyle;
import person.daizhongde.virtue.util.ie.POICellUtil;
import person.daizhongde.virtue.util.ie.SSFReadUtil;

public class MigSyncConfigServiceImpl_IMP implements BaseImportService {

	private static final Log log = LogFactory.getLog(MigSyncConfigServiceImpl_IMP.class);
	
	private JDBCSpringConnection jdbcSConnection;
//	private MigSyncConfigDAO dataDAO;
	
	public void importXLS(File file, String uploadContentType, String _ ) throws Exception{
		log.debug("-->1");
		
		AbstractConstant absConstant = new ConstMigSyncConfig();
//		System.out.println("absConstant.getJSDOC().getImport().get(\"import\").getDefaultColumns():"+absConstant
//				.getJSDOC()
//				.getImport()
//				.get("import")
//				.getDefaultColumns());
		this.importXLS(file, uploadContentType, _,
				absConstant
					.getJSDOC()
					.getImport()
					.get("import")
					.getDefaultColumns(),
				IMPORT.xlsImportOptions
			);
	}
	public void importXLS(File file,  String uploadContentType,String _, List customColumns ) throws Exception{
		log.debug("-->2");
		this.importXLS(file, uploadContentType, _, 
				customColumns,
				IMPORT.xlsImportOptions
			);
	}
	public void importXLS(File file, String uploadContentType, String _, Map options ) throws Exception{
		log.debug("-->3");
		
		AbstractConstant absConstant = new ConstMigSyncConfig();
		
		//集合类型final修饰的只是引用，所以集合本身的值可以改变
		IMPORT.xlsImportOptions.putAll(options);
		options = IMPORT.xlsImportOptions;
		
		this.importXLS(file, uploadContentType, _,
				absConstant
					.getJSDOC()
					.getImport()
					.get("import")
					.getDefaultColumns(),
				options
			);
	}
	@Override
	public void importXLS(File file,  String uploadContentType,String _, List customColumns, Map options )
			throws Exception {
//		System.out.println("customColumns.size():"+customColumns.size());
		
		AbstractConstant absConstant = new ConstMigSyncConfig();
//		//集合类型final修饰的只是引用，所以集合本身的值可以改变,下面的代码会改变IMPORT.xlsImportOptions的值
//		IMPORT.xlsImportOptions.putAll(options);
//		options = IMPORT.xlsImportOptions;
		
		HashMap copyOptions = MapUtils.Map2HashMap(IMPORT.xlsImportOptions);
		copyOptions.putAll(options);
		options = copyOptions;
		this.importXLS2DB(file, uploadContentType, absConstant, _, customColumns, options );
	}
	
	/**
	 * 
	 * @param fAbsPath
	 * @param absConstant
	 * @param total
	 * @param sqlA
	 * @param _
	 * @throws Exception 
	 */
	private void importXLS2DB(File file,
			String uploadContentType, 
			AbstractConstant absConstant,
			String _, List customColumns, Map options ) throws Exception {
    	
		log.debug("-->read excel and write data to database...");
		int[] columnTypes;
    	int[] columnPrecisions;
    	int[] columnScales;
    	String[] columnNames_zh;
    	
		Map<String, List> Field = absConstant.getJSDOC().getField().getField();
		int size = customColumns.size();
		columnTypes = new int[size];
		columnPrecisions = new int[size];
		columnScales = new int[size];
		columnNames_zh = new String[size];

//		Iterator it = set.iterator();
		Object key;
		for( int i=0, j=size; i<j; i++ ) {
			key = customColumns.get(i);
			log.debug("key:"+key);
			List list = (List)Field.get( key );
			if( null==list ){
				//默认值处理
				columnTypes[i] = 12;//varchar(mysql)
				columnPrecisions[i] = 200;
				columnScales[i] = 0;
				columnNames_zh[i] = "未指明列";
			}else{
				columnTypes[i] = (Integer)list.get(0);
				columnPrecisions[i] = (Integer)list.get(1);
				columnScales[i] = (Integer)list.get(2);
				columnNames_zh[i] = (String)list.get(3);
			}
		}
		HSSFWorkbook wb = SSFReadUtil.readHSSFFile( file.getAbsolutePath() );
		HSSFFormulaEvaluator evaluator = new HSSFFormulaEvaluator(wb); 
		
//		Workbook wb = SSFReadUtil.readFile(file.getAbsolutePath(), uploadContentType);
		
//		FormulaEvaluator evaluator = null; 
//		if (uploadContentType.toLowerCase().equals("application/vnd.ms-excel")) {
//			evaluator = new HSSFFormulaEvaluator((HSSFWorkbook)wb); 
//	    }
//	    else if(uploadContentType.toLowerCase().equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))  
//	    {
//	    	evaluator = new XSSFFormulaEvaluator((XSSFWorkbook)wb);
//	    }
//        else  
//        {
//            System.out.println("The file with the wrong format！");
//            throw new RuntimeException("The file with the wrong format");
//        }
		/* insert into `t_asiainfo_employee` 
		 * (`sbu_id`, `sbu`, `company_id`, `company`, `organization_id`, 
		 * `org_name`, `office`, `pager`, `person_id`, `employee_number`,
		 * `first_name`, `last_name`, `full_name`, `email_address`, `age`, 
		 * `assignment_id`, `birth_date`, `class`, `working_location`, `seat_no`, 
		 * `mobile`, `nt_account`, `supervisor_id`, `supervisor_name`, `highest_degree`, 
		 * `hire_date`) 
		 * values
		 * ('52','NBS','62','南京联创','13838',
		 * 'BSD-Billing',NULL,NULL,'102','0019',
		 * 'BSDGS','BSD工时','BSD工时BSDGS','guoxk@asiainfo-linkage.com','24',
		 * '58263','1988-05-11 00:00:00',NULL,'南京','-',
		 * '13606636583','bsdgs','183',NULL,NULL,
		 * '2010-05-11 00:00:00'); */
//		String insertSQL ="";
		
		StringBuilder sb = new StringBuilder("insert into " + absConstant.getTableName() + "(");
		for( int i=0, j=size; i<j; i++ ) {
			key = customColumns.get(i);
			sb.append(key+",");
		}
		sb.deleteCharAt(sb.length()-1);
		sb.append(") values (");
		
		for( int i=0, j=size; i<j; i++ ) {
			key = customColumns.get(i);
			sb.append("?,");
		}
		sb.deleteCharAt(sb.length()-1);
		sb.append(")");
		
		System.out.println("insertSQL:"+sb.toString());
		
		HSSFWorkbookUtil4Import hwbUtil = new HSSFWorkbookUtil4Import(wb,
				columnTypes, 
				columnPrecisions,
				columnScales,
				columnNames_zh, 
				options,
				evaluator );

		Connection conn = jdbcSConnection.getConnection();
		
		try{
			conn.setAutoCommit(false);
			
			JDBCBatchInsert batch = new JDBCBatchInsert();
			
			for (int k = 0; k < wb.getNumberOfSheets(); k++) {
				List<Object[]> oa_row = hwbUtil.readASheetData(k);
				/* write data to database, commit per sheet  */
				// batch insert wait for finish....
				batch.batchInsert(conn, sb.toString(), oa_row, columnTypes);
			}
		}catch(BusinessException | ParseException e){
			throw e;
		}finally{
			wb.close();
			conn.setAutoCommit(true);
	//		conn.close();
		}
	}
	
	@Override
	public void importTXT(File file, String _) throws Exception {
		AbstractConstant absConstant = new ConstMigSyncConfig();
		this.importTXT(file, _,
				absConstant
					.getJSDOC()
					.getImport()
					.get("import")
					.getDefaultColumns(),
				IMPORT.txtImportOptions
			);
	}
	@Override
	public void importTXT(File file, String _, List customColumns ) throws Exception {
		AbstractConstant absConstant = new ConstMigSyncConfig();
		this.importTXT(file, _, 
				absConstant.getJSDOC()
					.getImport()
					.get("import")
					.getDefaultColumns(),
				IMPORT.txtImportOptions
			);
	}
	
	@Override
	public void importTXT(File file, String _, Map options ) throws Exception {
		AbstractConstant absConstant = new ConstMigSyncConfig();
		this.importTXT(file, _,
				absConstant
					.getJSDOC()
					.getImport()
					.get("import")
					.getDefaultColumns(),
				options
			);
	}
	
	@Override
	public void importTXT(File file, String _, List customColumns, Map options ) throws Exception {
	}
	
	private void importTXT2DB(File file, 
			AbstractConstant absConstant, 
			int total, 
			SQLAssembleE sqlA,
			String _, List customColumns, Map options ) throws IOException {
   
	}
	public void setJdbcSConnection(JDBCSpringConnection jdbcSConnection) {
		this.jdbcSConnection = jdbcSConnection;
	}
	public static BaseImportService getFromApplicationContext(
			ApplicationContext ctx) {
		return (BaseImportService) ctx.getBean("migSyncConfigService_EXP");
	}
}
