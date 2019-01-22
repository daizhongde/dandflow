package person.daizhongde.migration.struts2.action.imp;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import person.daizhongde.virtue.constant.INIT;
import person.daizhongde.virtue.spring.BaseImportService;
import person.daizhongde.virtue.util.file.FileUtil;

import person.daizhongde.authority.constant.SessionConstants;
import person.daizhongde.authority.hibernate.pojo.TAuthorityUser;
import person.daizhongde.authority.struts2.action.BaseAction;
import com.opensymphony.xwork2.ActionContext;

/**
 * 
 * 迁移表清单配置
 * <p>
 * this type Acitons whose name are match "*ImportAction"
 * are only do a work: import data
 * <br>wait to finish..... add sort....
 * @author dzd
 * 
 */
public class MigSyncConfigIMPAction extends BaseAction  {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3791570688481329748L;

	/** for muti-thread */
	private String _;
	
	private String jdata;
	
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
	private BaseImportService dataService;
	
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
		return this.importSyncConfigXLS();
	}
	/**
	 * 处理用户导入文件的方法
	 * 同步导入(以提交form的形式导入)
	 * 
	 * @see com.copote.cpab.struts2.action.exp.TbPayerCommiInfoEXPAction#ImportXLS()
	 * @throws Throwable 
	 * 
	 */
	public String importTXT() throws Throwable{
		return this.importSyncConfigTXT();
	}
	/**
	 * 处理用户导入文件的方法
	 * 同步导入EXCEL(以提交form的形式导入)
	 * @throws Throwable 
	 * 
	 * 
	 * 
	 */
	public String importSyncConfigXLS() throws Throwable{
		log.debug("开始上传单个文件-----------------------");
		log.debug("==========" + getUploadFileName());
		log.debug("==========" + getUploadContentType());
		log.debug("==========" + getUpload());
		
		//以服务器的文件保存地址和原文件名建立上传文件输出流
		this.savePath = getSavePath();
		System.out.println("savePath:"+savePath);
		
		System.out.println("upload.getAbsolutePath():"+upload.getAbsolutePath());
		
		HttpServletRequest request = ServletActionContext.getRequest();
		
		//取得ActionContext实例
		ActionContext ctx = ActionContext.getContext();

		//通过ActionContext访问用户的HttpSession
//		Map<String, Object> session = ctx.getSession();
		loginCheck = false;//不作控制
		if(loginCheck){
			TAuthorityUser user = (TAuthorityUser) request.getSession().getAttribute( SessionConstants.LOGIN_USER );
			if ( user != null ) {
				return SUCCESS;
			}
			log.debug("您还没有登陆，或者登陆的用户名不正确，请重新登陆！");
			ctx.put("tip", "您还没有登陆，或者登陆的用户名不正确，请重新登陆！");
			return LOGIN;
		}

//		String msg = null;
		
		try
		{
			if( customColumns == null && options ==null )
			{
				//导入excel文件 -- default
				System.out.println("导入excel文件 -- default");
				dataService.importXLS(upload, uploadContentType, _ );
			}
			else if(options ==null)
			{
				//导入txt文件-- custom columns, default options
				System.out.println("导入txt文件-- custom columns, default options");
				dataService.importXLS(upload, uploadContentType, _, customColumns );
			}
			else if(customColumns == null)
			{
				//导入txt文件-- default columns, custom options
				System.out.println("导入txt文件-- default columns, custom options");
				dataService.importXLS(upload, uploadContentType, _, options);
			}
			else
			{
				//导入成excel文件 -- custom
				System.out.println("导入excel文件 -- custom");
				dataService.importXLS(upload, uploadContentType, _, customColumns, options );
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
			Throwable e2 = e;
			while(e2.getCause() != null ){
				e2 = e2.getCause();
			}
			throw e2;
//			return ERROR;
		}

		//validate
		
//		/** server file absolute directory , end with '/' **/
//		String tempFAbsDir = INIT.tempFileDirectory+"import/excel/";
		SimpleDateFormat stm=new SimpleDateFormat("yyMMdd-HHmmss-S");
		String tempFAbsName = uploadFileName + stm.format(new Date()) + ".xls";
		
		/** server file absolute directory , end with '/' **/
		String tempFAbsDir = INIT.tempFileDirectory+"import/excel/";
//		System.out.println("tempFAbsDir:"+tempFAbsDir);
		java.io.File fold = new java.io.File(tempFAbsDir);
		if(!fold.exists())
			fold.mkdirs();
		
		String fAbsPath = tempFAbsDir + tempFAbsName;
		
		//copy upload temp file to VIRTUE temp directory.(After import success)
		FileUtil.copy(getUpload(), fAbsPath);
		log.info("导入文件完成!");
		
		return SUCCESS;
	}

	
	/**
	 * 处理用户导入文件的方法
	 * 同步导入(以提交form的形式导入)
	 * @throws Throwable 
	 * 
	 */
	public String importSyncConfigTXT() throws Throwable{
		return null;
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
	public void setDataService(BaseImportService dataService) {
		this.dataService = dataService;
	}

}
