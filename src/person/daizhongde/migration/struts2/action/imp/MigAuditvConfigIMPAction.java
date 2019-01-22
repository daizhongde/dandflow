package person.daizhongde.migration.struts2.action.imp;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import person.daizhongde.virtue.constant.AbstractConstant;
import person.daizhongde.virtue.constant.INIT;
import person.daizhongde.virtue.util.file.FileUtil;

import person.daizhongde.authority.struts2.action.BaseAction;
import person.daizhongde.migration.constant.ConstCommon;
import person.daizhongde.migration.spring.jdbc.MigAuditvConfigIMPService;

/**
 * Invalid
 * 
 * this type Acitons whose name are match "*ImportAction"
 * are only do a work: import data
 * <br>wait to finish..... add sort....
 * @author dzd
 * 
 */
public class MigAuditvConfigIMPAction extends BaseAction  {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3791570688481329748L;

	/** for muti-thread */
	private String _;
	
    private File upload;
    private String uploadContentType;
    private String uploadFileName;
	
	//** 接受依赖注入的属性 ,在这里从配置文件中读来*/
    private String savePath;
	
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
	private MigAuditvConfigIMPService dataService;
	
    /** for response */
	private String sResponse;
	
	/**
	 * 处理用户导入文件的方法
	 * 同步导入EXCEL(以提交form的形式导入)
	 * PayerCommiInfo.Import.import  --ImportXLS()
	 * PayerCommiInfo.Import.importDayReport  --ImportDayReportXLS()
	 * PayerCommiInfo.Import.importMonthReport  --ImportMonthReportXLS()
	 * @throws Throwable 
	 * 
	 * 
	 * 
	 */
	public String importXLS() throws Throwable{
		return this.importAuditvConfigXLS();
	}
	/**
	 * 处理用户导入文件的方法
	 * 同步导入EXCEL(以提交form的形式导入)
	 * @throws Throwable 
	 * 
	 * 
	 * 
	 */
	public String importAuditvConfigXLS() throws Throwable{
		log.debug("begin upload mapping file-----------------------");
		log.debug("==========" + getUploadFileName());
		log.debug("==========" + getUploadContentType());
		log.debug("==========" + getUpload());
		
		//以服务器的文件保存地址和原文件名建立上传文件输出流
		this.savePath = getSavePath();
//		System.out.println("savePath:"+savePath);
		
//		System.out.println("upload.getAbsolutePath():"+upload.getAbsolutePath());
				
		try
		{
			dataService.importAuditvConfigXLS(upload, uploadFileName, _, super.getLoginUser());
			log.info("Finished! Legality audit config import success!");

			//validate
			
			AbstractConstant absConstant = new ConstCommon();
	//		/** server file absolute directory , end with '/' **/
	//		String tempFAbsDir = INIT.tempFileDirectory+"import/excel/";
			SimpleDateFormat stm=new SimpleDateFormat("yyMMdd-HHmmss-S");
			String tempFAbsName = absConstant.getEXPfileName() + stm.format(new Date()) + ".xls";
			
			/** server file absolute directory , end with '/', 
			 * if config end with '/' INIT.java would delete it **/
			String tempFAbsDir = INIT.tempFileDirectory+"/import/excel/";
	//		System.out.println("tempFAbsDir:"+tempFAbsDir);
			java.io.File fold = new java.io.File(tempFAbsDir);
			if(!fold.exists())
				fold.mkdirs();
			
			String fAbsPath = tempFAbsDir + tempFAbsName;
			
			log.info("begin copy temp file....");
			//copy upload temp file to VIRTUE temp directory.(After import success)
			FileUtil.copy(getUpload(), fAbsPath);
			log.info("copy temp file finished!");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
			Throwable e2 = e;
			while(e2.getCause() != null ){
				e2 = e2.getCause();
			}
			Map map = new HashMap(2);
			map.put("success", Boolean.FALSE );
			map.put("msg", e2.getLocalizedMessage());
			super.setMap(map);
			return "map";
		}
		
		Map map = new HashMap(2);
		map.put("success", Boolean.TRUE );
		map.put("msg", "Import Success！");
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
	public void setDataService(MigAuditvConfigIMPService dataService) {
		this.dataService = dataService;
	}

}
