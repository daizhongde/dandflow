package person.daizhongde.migration.spring.jdbc;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

//import jxl.Sheet;
//import jxl.Workbook;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.scheduling.annotation.Async;

import person.daizhongde.authority.hibernate.pojo.TAuthorityUser;
import person.daizhongde.migration.exception.BusinessException;
import person.daizhongde.migration.hibernate.dto.Temp;
import person.daizhongde.migration.spring.service.PubService;
import person.daizhongde.migration.util.J_KeySerial2;

import person.daizhongde.virtue.constant.INIT;
import person.daizhongde.virtue.jdbc.JDBCSpringConnection;
import person.daizhongde.virtue.util.file.FileUtil;

/**
 * seven levels total include root. This is a compromise You can modify this
 * class to extend for more levels. gubusoft treeview use
 * <p>
 * 与MigJobInfoIMPServiceImpl不使用DAO类
 * @author dzd
 *
 */
public class MigJobInfoIMPServiceImpl implements MigJobInfoIMPService {
	private static final Log log = LogFactory
			.getLog(MigJobInfoIMPServiceImpl.class);

	private JDBCSpringConnection jdbcSConnection;
	private PubService pubSrv;
	
	public void setPubSrv(PubService pubSrv) {
		this.pubSrv = pubSrv;
	}
	
	public void importJobXLS(File file, String srcFileName, String  uploadContentType,String _, TAuthorityUser user) throws Exception {
				
//		String jobId = pubSrv.get10ByteCode(TableName.mig_job_info);
//		String jobId = "JB" 
//				+ new SimpleDateFormat("yyMMdd").format(new Date()).substring(1)
//				+ "-" + J_KeySerial2.nextval();
//		log.debug("new Job id: "+ jobId );
				
		Connection conn = jdbcSConnection.getConnection();
		
		try{
			conn.setAutoCommit(false);
			ReadXls2 readXls = new ReadXls2( conn, file.getAbsolutePath(), user.getCUlogname(), pubSrv );
//			readXls.setPubSrv(pubSrv);
			readXls.getAndInsert(  );
			
			conn.commit();
		}catch(BusinessException e){
			conn.rollback();
			throw e;
		}finally{
			conn.setAutoCommit(true);
//			conn.close();
		}
	}
//	/**
//	 * To generate report by sql excel
//	 */
//	@Async
//	public void importKPIXLS(File file, String srcFileName,String  uploadContentType,String _, TAuthorityUser user) throws Exception {
//						
////		Connection conn = jdbcSConnection.getConnection();
//		
//		try{
////			conn.setAutoCommit(false);
//			Excel2Excel e2e = new Excel2Excel(  );
//			
//			SimpleDateFormat stm=new SimpleDateFormat("yyyyMMdd-HHmmss-S");
//			String tempFAbsName = FileUtil.getFileNameNoEx(srcFileName) +"_"+ stm.format(new Date()) + "."+FileUtil.getExtensionName(srcFileName);
//			/** server file absolute directory , if config end with '/' INIT.java would delete it **/
//			String tempFAbsDir = INIT.tempFileDirectory+"/export/excel/";
//			String fAbsPath = tempFAbsDir + tempFAbsName;
//			
//			e2e.exchangeExcel(file, fAbsPath, uploadContentType);
//			
////			conn.commit();
//		}catch(BusinessException e){
////			conn.rollback();
//			throw e;
//		}finally{
////			conn.setAutoCommit(true);
////			conn.close();
//		}
//	}
	/**
	 * To generate report by sql excel
	 */
	@Async
	public void importKPIXLS(File file, String targetFAbsPath,String  uploadContentType,String _, TAuthorityUser user) throws Exception {
						
//		Connection conn = jdbcSConnection.getConnection();
		
		try{
//			conn.setAutoCommit(false);
			Excel2Excel e2e = new Excel2Excel(  );
			
//			SimpleDateFormat stm=new SimpleDateFormat("yyyyMMdd-HHmmss-S");
//			String tempFAbsName = FileUtil.getFileNameNoEx(srcFileName) +"_"+ stm.format(new Date()) + "."+FileUtil.getExtensionName(srcFileName);
			/** server file absolute directory , if config end with '/' INIT.java would delete it **/
//			String tempFAbsDir = INIT.tempFileDirectory+"/export/excel/";
//			String fAbsPath = tempFAbsDir + tempFAbsName;
			
			e2e.exchangeExcel(file, targetFAbsPath, uploadContentType);
			
//			conn.commit();
		}catch(BusinessException e){
//			conn.rollback();
			throw e;
		}finally{
//			conn.setAutoCommit(true);
//			conn.close();
		}
	}
	public void setJdbcSConnection(JDBCSpringConnection jdbcSConnection) {
		this.jdbcSConnection = jdbcSConnection;
	}

}
