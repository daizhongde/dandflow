package person.daizhongde.migration.struts2.action.exp;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import person.daizhongde.virtue.constant.AbstractConstant;
import person.daizhongde.virtue.spring.BaseExportService;
import person.daizhongde.virtue.util.character.CharacterConvert;

import person.daizhongde.authority.spring.service.CommonService;
import person.daizhongde.authority.struts2.action.BaseAction;
import person.daizhongde.migration.constant.ConstMigAuditvResult;
import com.opensymphony.xwork2.ActionContext;
/**
 * 合法必稽核结果数据导出
 * this type Acitons whose name are match "*ExportAction"
 * are only do a work: export data
 * <br>wait to finish..... add sort....
 * @author dzd
 * 
 */
public class MigAuditvResultEXPAction extends BaseAction  {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3791570688481329748L;

	/** for muti-thread */
	private String _;
	
	private String jdata;
	/**  	[{"LEVEL1":"desc"},{"LEAF":"asc"}]  **/
	@SuppressWarnings({ "unused", "rawtypes" })
	private List<Map> sortBy;
	
	private List customColumns;
	
	/** wizard export options */
	@SuppressWarnings("rawtypes")
	private Map options;
	
	/** XLS文件的导出次数 **/
	private static int countXLS=0;
	/** TXT文件的导出次数 **/
	private static int countTXT=0;
	
//	/** file absolute directory , end with '/' **/
//	private String tempFAbsDir;
//	/** 服务器临时文件名称（完整文件名） **/
//	private String tempFAbsName;
//	/** 服务器临时文件名称（不包括扩展名） **/
//	private String tempFName;
	/** 服务器临时文件的扩展名（文件类型） **/
	private String tempFExtName;
	
	/** file absolute path **/
	private String tempFAbsPath;
	
	/** 要导出的文件  */
	private InputStream inStream;
	/** 用户导出到本地的文件名 **/
	private String fileName;
	
	/** 导出文件是否需要登陆  */
	private Boolean loginCheck;
	private BaseExportService dataService;
	private CommonService commonService;
	
    /** for response */
	private String sResponse;
	/** for response--currently use it when In ColumnNames and show export progress */
	private	Map map = new HashMap();

	/**
	 * 导出用的Action应该返回一个InputStream实例
	 * 该方法对应在result里的inputName属性值为targetFile
	 * @return
	 * @throws Exception
	 */
	public InputStream getTargetFile() throws Exception {
//		System.out.println("----------getTargetFile--------------");
		this.inStream = new BufferedInputStream(new FileInputStream( tempFAbsPath ), 1*1024*1024);
		String iso88951 = "";//这里还没有转换  eg: temp.xls
		CharacterConvert cv = new CharacterConvert();

		String Timestamp="";
//		Encoding		: "GB2312",
//		AddTimestamp 	: "false",//or "true"
//		TimestampFormat	: "",////or "YYYYMMDD","YYYY-MM-DD-HHNNSS"
		
		if( options!=null 
		 && Boolean.parseBoolean(options.get("AddTimestamp").toString()) ){
			//"yyyy年MM月dd日 HH:mm"
			SimpleDateFormat stm=new SimpleDateFormat(options.get("TimestampFormat").toString());
			Timestamp = "_"+stm.format(new Date());
		}
		
		AbstractConstant absConstant = new ConstMigAuditvResult();
		
		switch(this.tempFExtName){
		case "xls": iso88951 = cv.getGBK2ISO( this.getFileName() + (++countXLS) + Timestamp + ".xls");
			break;
		case "txt": iso88951= cv.getGBK2ISO( this.getFileName() + (++countTXT) + Timestamp + ".txt");
			break;
		default : throw new Exception("No such filetype!");
		}

		this.setFileName(iso88951);
		return this.inStream;  
	}
	/**
	 * 处理用户导出文件的方法
	 * 同步导出EXCEL(以提交form的形式导出)
	 * ConstMigAuditvResult.Export.export  --ExportXLS()
	 * ConstMigAuditvResult.Export.exportDayReport  --ExportDayReportXLS()
	 * ConstMigAuditvResult.Export.exportMonthReport  --ExportMonthReportXLS()
	 * @throws Throwable 
	 * 
	 * 
	 * 
	 */
	public String exportXLS() throws Throwable{
		return this.auditvResultXLS();
	}
	/**
	 * 处理用户导出文件的方法
	 * 同步导出(以提交form的形式导出)
	 * 
	 * @see person.daizhongde.migration.struts2.action.exp.MigAuditvResultEXPAction#ExportXLS()
	 * @throws Throwable 
	 * 
	 */
	public String exportTXT() throws Throwable{
		return this.commiInfoTXT();
	}
	/**
	 * 处理用户导出文件的方法
	 * 同步导出EXCEL(以提交form的形式导出)
	 * @throws Throwable 
	 * 
	 * 
	 * 
	 */
	public String auditvResultXLS() throws Throwable{
		this.setFileName( "合法性稽核结果明细" );
//		System.out.println("来了#####");
//		<1>取得HttpServletRequest对象: 
		HttpServletRequest request = ServletActionContext.getRequest();
//		<2>取得HttpSession对象: 
//		HttpSession session = ServletActionContext.getRequest().getSession();
//		HttpSession session = session; 
		
//		this.setFileName( "模块信息.xls" );
		this.tempFExtName = "xls";
		//取得ActionContext实例
		ActionContext ctx = ActionContext.getContext();

		//通过ActionContext访问用户的HttpSession
//		Map<String, Object> session = ctx.getSession();
		loginCheck=false;//不作控制
		if(loginCheck){
			String user = (String) request.getSession().getAttribute("CUname");
			if ((user != null) && !user.equals("")) {
				return SUCCESS;
			}
			log.debug("您还没有登陆，或者登陆的用户名不正确，请重新登陆！");
			ctx.put("tip", "您还没有登陆，或者登陆的用户名不正确，请重新登陆！");
			return LOGIN;
		}
//		System.out.println("customColumns:"+customColumns);
		try
		{
			if( customColumns == null && options ==null )
			{
				//生成excel文件 -- default
				System.out.println("生成excel文件 -- default");
				this.tempFAbsPath = dataService.exportXLS(jdata, _ );
			}
			else if(options ==null)
			{
				//生成excel文件-- custom columns, default options
				System.out.println("生成txt文件-- custom columns, default options");
				this.tempFAbsPath = dataService.exportXLS(jdata, _, customColumns );
			}
			else if(customColumns == null)
			{
				//生成excel文件-- default columns, custom options
				System.out.println("生成txt文件-- default columns, custom options");
				this.tempFAbsPath = dataService.exportXLS(jdata, _, options);
			}
			else
			{
				//生成excel文件 -- custom
				System.out.println("生成excel文件 -- custom");
				this.tempFAbsPath = dataService.exportXLS(jdata, _, customColumns, options );
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
		log.info("导出模块信息文件完成!");
		return SUCCESS;
	}
	/**
	 * 处理用户导出文件的方法
	 * 同步导出EXCEL(以提交form的形式导出)
	 * @throws Throwable 
	 * 
	 * 
	 * 
	 */
	public String auditvResult4ReportXLS() throws Throwable{
		this.fileName = "KPI-Legality";
		
		HttpServletRequest request = ServletActionContext.getRequest();
		this.tempFExtName = "xls";
		ActionContext ctx = ActionContext.getContext();
		loginCheck=false;//不作控制
		if(loginCheck){
			String user = (String) request.getSession().getAttribute("CUname");
			if ((user != null) && !user.equals("")) {
				return SUCCESS;
			}
			log.debug("您还没有登陆，或者登陆的用户名不正确，请重新登陆！");
			ctx.put("tip", "您还没有登陆，或者登陆的用户名不正确，请重新登陆！");
			return LOGIN;
		}
		try
		{
			this.tempFAbsPath = dataService.exportXLS1(jdata, _, customColumns, options );
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
			Throwable e2 = e;
			while(e2.getCause() != null ){
				e2 = e2.getCause();
			}
			throw e2;
		}
		log.info("导出模块信息文件完成!");
		return SUCCESS;
	}
	/**
	 * 处理用户导出文件的方法
	 * 异步导出(文件生成完成后在ajax请求的回调函数中导出文件)
	 * 
	 * 
	 * 
	 
	public String expCommiInfoAsyn(){
//		this.filename = "模块信息.xls";
		this.setFileName( "模块信息.xls" );
		//取得ActionContext实例
		ActionContext ctx = ActionContext.getContext();
		//通过ActionContext访问用户的HttpSession
		Map<String, Object> session = ctx.getSession();
		loginCheck=false;//不作控制
		if(loginCheck){
			String user = (String) request.getSession().get("CUname");
			if ((user != null) && !user.equals("")) {
				return SUCCESS;
			}
			log.debug("您还没有登陆，或者登陆的用户名不正确，请重新登陆！");
			ctx.put("tip", "您还没有登陆，或者登陆的用户名不正确，请重新登陆！");
			return LOGIN;
		}
		
		try{
			//生成excel文件
			this.tempFAbsPath = dataService.exportXLS(jdata, session );
		}catch(Exception e){
			e.printStackTrace();
			
			Throwable e2 = e;
			while(e2.getCause() != null ){
				e2 = e2.getCause();
			}
			return ERROR;
		}
		log.info("导出模块信息文件完成!");
		return SUCCESS;
	}*/
	
	/**
	 * 处理用户导出文件的方法
	 * 同步导出(以提交form的形式导出)
	 * @throws Throwable 
	 * 
	 */
	public String commiInfoTXT() throws Throwable{
		HttpServletRequest request = ServletActionContext.getRequest();
//		this.setFileName( "模块信息.txt" );
		this.tempFExtName = "txt";
		//取得ActionContext实例
		ActionContext ctx = ActionContext.getContext();

		//通过ActionContext访问用户的HttpSession
		loginCheck=false;//不作控制
		if(loginCheck){
			String user = (String) request.getSession().getAttribute("CUname");
			if ((user != null) && !user.equals("")) {
				return SUCCESS;
			}
			log.debug("您还没有登陆，或者登陆的用户名不正确，请重新登陆！");
			ctx.put("tip", "您还没有登陆，或者登陆的用户名不正确，请重新登陆！");
			return LOGIN;
		}
		
//		System.out.println("customColumns:"+customColumns);

		try
		{
			if( customColumns == null && options ==null )
			{
				//生成txt文件-- default
				this.tempFAbsPath = dataService.exportTXT(jdata, _ );
			}
			else if(options ==null)
			{
				//生成txt文件-- custom columns, default options
				this.tempFAbsPath = dataService.exportTXT(jdata, _, customColumns );
			}
			else if(customColumns == null)
			{
				//生成txt文件-- default columns, custom options
				this.tempFAbsPath = dataService.exportTXT(jdata, _, options);
			}
			else
			{
				//生成txt文件-- custom
				this.tempFAbsPath = dataService.exportTXT(jdata, _, customColumns, options);
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

		log.info("导出模块信息文件完成!");
		return SUCCESS;
	}
	
	public String TableNames() {
		System.out.println("###1 this.sResponse:"+this.sResponse);

		List dataList = new ArrayList(1);
		int size =1;
		
//		for( int i=0, j=size; i<j; i++ ) {
//			Map map = new HashMap(2);
//			map.put("tname", "TB_PAYER_COMMI_INFO" );
//			map.put("comments", "模块信息表 1" );
//			dataList.add(map);
//		}
		
		System.out.println("commonService.getTableNames_Map().size():"+commonService.getTableNames_Map().size() );
		System.out.println("TableNames_Map:"+JSONObject.fromObject(commonService.getTableNames_Map()).toString() );
		
		String key ="TB_PAYER_COMMI_INFO";
		for( int i=0, j=size; i<j; i++ ) {
			Map map = new HashMap(2);
			map.put("tname", key );
			map.put("comments", 
					commonService.getTableNames_Map().get(key).toString() 
					);
//			map.put("comments", "模块信息表" );
			dataList.add(map);
		}
		
		map.put("total", size );
		map.put("rows", dataList );
		System.out.println("map:"+JSONObject.fromObject(map).toString() );
		return "map";
	}
	
	public String DefaultColumns(){
		AbstractConstant absConstant = new ConstMigAuditvResult();

		this.sResponse = absConstant
				.getJSDOC()
				.getExport()
				.get("export")
				.getDefaultColumns().toString();
		
		System.out.println("###DefaultColumns() this.sResponse:"+this.sResponse);
		this.setSResponse(sResponse);
		return "sResponse";
	}
	
	/**
	 * 
	 * @return 
	 * {"total":6,
	 * 		"rows":[
	 * 			{"name_zh":"签约专号","name":"PAY_ID"},
	 * 			{"name_zh":"用户名","name":"SIGN_NAME"},
	 * 			{"name_zh":"签约客户账号","name":"ACC_ID"}
	 * 		]
	 * }
	 */
	public String ColumnNames() {
		System.out.println("###1 this.sResponse:"+this.sResponse);
		AbstractConstant absConstant = new ConstMigAuditvResult();
		
//		Map<String, List> ColumnMap = absConstant.getJSDOC().getExport().get("export").getColumnMap();
    	Map<String, List> ColumnMap = absConstant.getEXPcolumnMap();
    	this.sResponse ="";
		Set set = ColumnMap.keySet();
		int size = ColumnMap.size();

		List dataList = new ArrayList(size);
		
		Iterator it = set.iterator();
		Object key;
		for( int i=0, j=size; i<j; i++ ) {
			key = it.next();
			List list = (List)ColumnMap.get( key );
			Map map = new HashMap(2);
			map.put("name", (String)key );
			map.put("name_zh", (String)list.get(3) );
			dataList.add(map);
		}
//		Map swtData = new HashMap(2);
//		swtData.put("total", size );
//		swtData.put("rows", dataList );
//		JSONObject jsonObject = JSONObject.fromObject(swtData);
//		this.sResponse = jsonObject.toString();
//		System.out.println("###2 this.sResponse:"+this.sResponse);
//		this.setSResponse(sResponse);
//		return "sResponse";
		
		map.put("total", size );
		map.put("rows", dataList );
		System.out.println("map:"+JSONObject.fromObject(map).toString() );
		return "map";
	}

	/**
	 * Get process rate and message
	 * @return
	 */
	public String getProgress(){
		//取得ActionContext实例
//		ActionContext ctx = ActionContext.getContext();
		//通过ActionContext访问用户的HttpSession
//		Map<String, Object> session = ctx.getSession();
//		HttpServletRequest request = ServletActionContext.getRequest();
		
//		System.out.println( "#############################\n"+
//			JSONObject.fromObject( request.getSession() ).toString() 
//			+"\n#############################"
//		);
//		Progress temp = Progresses.map.get(_);
//		Progress p = temp == null? temp: new Progress(0,"正在开始...");
//		Progress p;
//		if(Progresses.map.size()!=0){
//			p = Progresses.map.get(_);
//		}else{
//			p = new Progress(0,"正在开始...");
//		}
//		map.put( "progress", p.getProgress() );
//		map.put( "msg", p.getMsg() );
		return "map";
	}

	public void setJdata(String jdata) throws UnsupportedEncodingException {
//		log.debug("encoded jdata:" + jdata.toString());
		String decode = java.net.URLDecoder.decode(jdata, "UTF-8");
		log.debug("#######");
		log.debug("decoded jdata:" + decode.toString());
		log.debug("#######");
		this.jdata = decode;
	}
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void setOptions(String options) throws UnsupportedEncodingException {
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
//	public void setOptions(Map options) {
//		this.options = options;
//	}
	
	public void setSortBy(List<Map> sortBy) {
		this.sortBy = sortBy;
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
	public void set_(String _) {
		this._ = _;
	}
	public void setLoginCheck(Boolean loginCheck) {
		this.loginCheck = loginCheck;
	}
	/**
	 * 升级后的struts2  action属性没有放到request的Attribute中
	 * @param sResponse
	 */
	public void setSResponse(String sResponse) {
		this.sResponse = sResponse;
		HttpServletRequest request=ServletActionContext.getRequest();  
        ServletContext cxt=ServletActionContext.getServletContext();  
        request.setAttribute("sResponse", sResponse );
	}
	public String getSResponse() {
		return sResponse;
	}
	
	public Map getMap() {
		return map;
	}
	public void setMap(Map map) {
		this.map = map;
	}
	public void setDataService(BaseExportService dataService) {
		this.dataService = dataService;
	}
	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}
}
