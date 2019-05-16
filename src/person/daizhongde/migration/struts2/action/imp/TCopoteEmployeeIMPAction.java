package person.daizhongde.migration.struts2.action.imp;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import person.daizhongde.virtue.constant.INIT;
import person.daizhongde.virtue.util.file.FileUtil;
import person.daizhongde.authority.hibernate.pojo.TAuthorityUser;
import person.daizhongde.authority.struts2.action.BaseAction;
import person.daizhongde.migration.spring.service.imp.Excel2Email;
import person.daizhongde.migration.spring.service.imp.TCopoteEmployeeIMPService;

/**
 * 导入工资文件并发送email
 * @author dzd 20190421
 * 
 */
public class TCopoteEmployeeIMPAction extends BaseAction  {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3791570688481329748L;

	/** for muti-thread */
	private String _;
	
    private File upload;
    private String uploadContentType;
    private String uploadFileName;
	
	/** 接受依赖注入的属性 ,在这里从配置文件中读来*/
    private String savePath;
    /** 如果需要严格的数据验证   每行除第二列之外的所有数据列，必需有值，否则认为Excel此行数据不全 */
    private boolean strictlyVerify;
    /** 是否只发当前登陆人的工资邮件   */
    private boolean onlySend2me;
	
	private List customColumns;
	
	/** wizard import options */
	@SuppressWarnings("rawtypes")
	private Map options;
	
	/** XLS文件的导入次数 **/
	private static int countXLS=0;
	/** TXT文件的导入次数 **/
	private static int countTXT=0;
	
	/** 导入文件是否需要登陆  */
	private Boolean loginCheck;
	private TCopoteEmployeeIMPService dataService;
	
    /** for response */
	private String sResponse;
	

	/**
	 * 处理用户导入文件的方法
	 * 同步导入EXCEL(以提交form的形式导入)
	 * @throws Throwable 
	 * 
	 * 
	 * 
	 */
	public String importSalaryXLS() throws Throwable{
		TAuthorityUser user= super.getLoginUser();
		Excel2Email.msg.put( user.getCUemail(), "正在上传文件...完成");
		
		log.debug("begin upload Gong Zi file-----------------------");
		log.debug("==========" + getUploadFileName());
		log.debug("==========" + getUploadContentType());
		log.debug("==========" + getUpload());
		log.debug("==========" + strictlyVerify );
		log.debug("==========" + onlySend2me );
		
		//以服务器的文件保存地址和原文件名建立上传文件输出流
		this.savePath = getSavePath();
		System.out.println("upload:"+upload.getAbsolutePath());
		System.out.println("java.io.tmpdir:"+System.getProperty("java.io.tmpdir"));
		
//		System.out.println("upload.getAbsolutePath():"+upload.getAbsolutePath());
		
//		String targetFAbsPath="";
		
		try
		{
//			dataService.importKPIXLS(upload, uploadFileName, uploadContentType, _,  super.getLoginUser() );
			
			SimpleDateFormat stm=new SimpleDateFormat("yyyyMMdd-HHmmss-S");
//			String targetFAbsName = FileUtil.getFileNameNoEx(uploadFileName) +"_"+ stm.format(new Date()) + "."+FileUtil.getExtensionName(uploadFileName);
			/** server file absolute directory , if config end with '/' INIT.java would delete it **/
//			String targetFAbsDir = INIT.tempFileDirectory+"/export/excel/";
//			targetFAbsPath = targetFAbsDir + targetFAbsName;
			dataService.importSalaryXLS(upload,uploadFileName, 
					uploadContentType, 
					strictlyVerify,onlySend2me, 
					_,  user );
			
			log.info("导入Salary文件并发送邮件完成!");
/**  屏蔽记临时文件的代码，因为工资为敏感信息   */
//			//validate 
//			String ExtensionName = FileUtil.getExtensionName(uploadFileName);
//			/** server file absolute directory , end with '/' **/
//			String tempFAbsName = "salary-" + stm.format(new Date()) + "."+ExtensionName;
//			
//			/** server file absolute directory , end with '/', 
//			 * if config end with '/' INIT.java would delete it **/
//			String tempFAbsDir = INIT.tempFileDirectory+"/import/excel/";
//	//		System.out.println("tempFAbsDir:"+tempFAbsDir);
//			java.io.File fold = new java.io.File(tempFAbsDir);
//			if(!fold.exists())
//				fold.mkdirs();
//			
//			String fAbsPath = tempFAbsDir + tempFAbsName;
//			
//			log.info("begin copy temp file....");
//			//copy upload temp file to VIRTUE temp directory.(After import success)
//			FileUtil.copy(getUpload(), fAbsPath);
//			log.info("copy temp file finished!");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
			Throwable e2 = e;
			while(e2.getCause() != null ){
				e2 = e2.getCause();
			}
//			super.setJson( "{success: false, msg: \""+e2.getLocalizedMessage()+"\"}" );
//			return "json";
//		}
//		super.setJson( "{success: true, msg: \"Import Salary Excel and Send Email Success！\"}" );
//		return "json";
	
			Map map = new HashMap(2);
			map.put("success", Boolean.FALSE );
			map.put("msg", URLDecoder.decode( e2.getLocalizedMessage() ) );
			super.setMap(map);
			return "map";
		}
		
		Map map = new HashMap(2);
		map.put("success", Boolean.TRUE );
		map.put("msg", "Import Salary Excel and Send Email Success！");
	//	map.put("path", targetFAbsPath );
		super.setMap(map);
		return "map";
			
		
	}
	public void set_(String _) {
		this._ = _;
	}
	
	public File getUpload() {
		return upload;
	}
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public String getUploadContentType() {
		return uploadContentType;
	}
	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}
	public String getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public String getSavePath() {
//		return savePath;
//		return ServletActionContext.getRequest().getRealPath(savePath);
    	return INIT.tempFileDirectory+"import/excel/";
	}
	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}
	public void setOptions(String options) {
		log.debug("encoded options:" + options.toString());
		String decode=null;
		try {
			decode = java.net.URLDecoder.decode(options, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		log.debug("#######");
		log.debug("decoded options:" + decode.toString());
		log.debug("#######");
		this.options = JSONObject.fromObject(decode);
	}
	/**
	 * '["PAY_ID","ACC_ID"]' or '[PAY_ID,ACC_ID]'  --> list
	 * 
	 * @param customColumns
	 */
	public void setCustomColumns(String customColumns) {
//		this.customColumns = customColumns;
		log.debug("encoded customColumns:" + customColumns.toString());
		String decode=null;
		try {
			decode = java.net.URLDecoder.decode(customColumns, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		log.debug("#######");
		log.debug("decoded customColumns:" + decode.toString());
		log.debug("#######");
		this.customColumns = JSONArray.fromObject(decode);
	}

	public void setLoginCheck(Boolean loginCheck) {
		this.loginCheck = loginCheck;
	}
	public String getSResponse() {
		return sResponse;
	}
	
	public boolean isStrictlyVerify() {
		return strictlyVerify;
	}
	public void setStrictlyVerify(boolean strictlyVerify) {
		this.strictlyVerify = strictlyVerify;
	}
	public boolean isOnlySend2me() {
		return onlySend2me;
	}
	public void setOnlySend2me(boolean onlySend2me) {
		this.onlySend2me = onlySend2me;
	}
	public void setDataService(TCopoteEmployeeIMPService dataService) {
		this.dataService = dataService;
	}

}
