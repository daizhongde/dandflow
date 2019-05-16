package person.daizhongde.migration.spring.service.imp;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import person.daizhongde.virtue.constant.INIT;
import person.daizhongde.virtue.jdbc.JDBCSpringConnection;
import person.daizhongde.virtue.jdbc.batch.JDBCBatchInsert;
import person.daizhongde.virtue.util.codec.Base64Util;
import person.daizhongde.virtue.util.date.DateCalc;
import person.daizhongde.virtue.util.date.ElapsedTimePrinter;
import person.daizhongde.virtue.util.word.type2.CustomXWPFDocument;
import person.daizhongde.virtue.util.word.type2.WordUtils;
import person.daizhongde.authority.hibernate.pojo.TAuthorityUser;
import person.daizhongde.migration.exception.AccountEmailException;
import person.daizhongde.migration.exception.BusinessException;
import person.daizhongde.migration.hibernate.dao.TCopoteEmployeeDAO;
import person.daizhongde.migration.hibernate.pojo.TCopoteEmployee;
import person.daizhongde.migration.hibernate.vo.SalaryVo;
import person.daizhongde.migration.util.copote.MinRYVo;
import person.daizhongde.migration.util.copote.SalaryUtil;

/**
 * 
 *  multi-thread  send email
 * @author daizd
 * 暂时没有用，原来的改成了prototype
 *
 */
public class Excel2Email {
	
	private static final Logger log = LoggerFactory.getLogger(Excel2Email.class);
	
	private TCopoteEmployeeDAO dataDAO;

//	private AccountEmailService accountEmailService;
	private MailUtil mailUtil;

	private JDBCSpringConnection jdbcSConnection;
	
	private int taskNum = INIT.maxThreadNum/2;
//	private int taskNum = 2;
	
	private Set<String> dupName;

	private Set<String> dupCol;
	/** 不在系统的人名向量  */
	private Vector<String> nonameVec;
	/** 工资数据不全的人名向量  */
	private Vector<String> dataErrorNameVec;
	/** 发送邮件失败的人名向量  */
	private Vector<String> sendErrorNameVec;

//	/** 发送失败的人名集合  */
//	private Set<String> errorNameSet = new HashSet<String>();
	
	/** key:发送邮件的收件人邮箱	value : 工资表格行数据   */
	private Map<String,String[]> dataMap;

	private Map<String,Map<String, Double>> mapMap;
	
	private Map<String,SalaryVo> salaryMap;

	/** 列头  */
	private Vector<String> colNameVec;
	/** 列头,用于校验是否存在重名列  */
	private Set<String> colNameSet;
	/** 人名向量  */
	private Vector<String> nameVec;
	/** 人名集合  */
	private Set<String> nameSet;

	/** 列头  */
	private String[] celltitle = null;
	
	private List<MinRYVo> volist;
	
	private CustomXWPFDocument xwpf;
	private String duration;
	private String ny;
	
	private String alias;
	
	/** key为email(alias) */
	public static Map<String,String> msg = new HashMap<String,String>();
	
	public void initXwpf( ) throws Exception {
//		this.xwpf =  new CustomXWPFDocument(POIXMLDocument.openPackage(
//				Excel2Email.class.getResource("/").getPath()+"\\template\\工资清单-非mercer.docx")
//			);
		System.out.println("构造DOC XWP对象....");
		Timestamp beginTime = new Timestamp( new Date().getTime());
		String template = Excel2Email.class.getResource("/").getPath()+"template/工资清单.docx";
		template = URLDecoder.decode(template);
		this.xwpf =  new CustomXWPFDocument(POIXMLDocument.openPackage( 
				template ) 
//				"D:\\usr\\copoteOA\\工资清单.docx")
			);
		Timestamp endTime = new Timestamp(new Date().getTime());
		ElapsedTimePrinter.printElapsedTime(beginTime, endTime, "构造DOC XWP对象完成！耗时：");
	}
	public void setDataDAO(TCopoteEmployeeDAO dataDAO) {
		this.dataDAO = dataDAO;
	}
	public void setJdbcSConnection(JDBCSpringConnection jdbcSConnection) {
		this.jdbcSConnection = jdbcSConnection;
	}
	
	public void setMailUtil(MailUtil mailUtil) {
		this.mailUtil = mailUtil;
	}
	/** input file's path is absolute path
	 * 这个方法不加事务
	 * @throws Exception */
	public void notxsendEmailByExcel( 
			String inputFile, 
			String uploadFileName, 
			String uploadContentType,
			TAuthorityUser user  
			) throws Exception{

		if(!new File(inputFile).exists() ){
			throw new BusinessException("input file is not exist!");
		}
		try{
			this.notxsendEmailByExcel(new File(inputFile), uploadFileName, uploadContentType,
					user);
		}catch(Exception e){
			throw e;
		}finally{
//			con_Default.close();
//			closeAllConnections();
		}
	}

	public void notxsendEmailByExcel(
			File inputFile, 
			String uploadFileName, 
			String uploadContentType,
			TAuthorityUser user 
			) throws Exception {

		this.notxsendEmailByExcel(inputFile, uploadFileName, uploadContentType, 
				true, true, user);
	}
	/**
	 * 这个方法不加事务
	 * <p>
	 * 两个时间  
	 * Duration 2018/02/01-2018/02/28
	 * YyyyMM 2018年02月 
	 * 从文件名中取，规则如下：
	 *   文件名中'月'关键字向左取8个字符
	 * 
	 * @param inputFile
	 * @param uploadContentType
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	public void notxsendEmailByExcel(File inputFile, 
			String uploadFileName, String uploadContentType,
			boolean strictlyVerify, boolean onlySend2me,
			TAuthorityUser user ) throws Exception {

		alias = user.getCUemail();
		msg.put(alias, "开始读取文件......");
		
		dupName = new HashSet<String>();
		dupCol = new HashSet<String>();
		/** 不在系统的人名向量  */
		nonameVec = new Vector<String>();
		/** 工资数据不全的人名向量  */
		dataErrorNameVec = new Vector<String>();
		/** 发送邮件失败的人名向量  */
		sendErrorNameVec = new Vector<String>();
		/** key:发送邮件的收件人邮箱	value : 工资表格行数据   */
		dataMap = new LinkedHashMap<String,String[]>();
		mapMap = new LinkedHashMap<String,Map<String, Double>>();
		salaryMap = new LinkedHashMap<String,SalaryVo>();
		/** 列头  */
		colNameVec = new Vector<String>();
		/** 列头,用于校验是否存在重名列  */
		colNameSet = new HashSet<String>();
		/** 人名向量  */
		nameVec = new Vector<String>();
		/** 人名集合  */
		nameSet = new LinkedHashSet<String>();
		volist = new ArrayList<MinRYVo>();
		
//		String fname = uploadFileName;
		int idxN = uploadFileName.indexOf("年");
		int idxY = uploadFileName.indexOf("月");
		if(idxN==-1 || idxY==-1 || idxN>=idxY || idxY<7){
			log.error("文件名不合法！规则：文件名必须包含{yyyy}年{MM}月，当前文件名:" + uploadFileName );
			System.out.println("文件名不合法！规则：文件名必须包中含{yyyy}年{MM}月，当前文件名:" + uploadFileName );
			throw new RuntimeException("文件名不合法！规则：文件名必须包含{yyyy}年{MM}月，当前文件名:" + uploadFileName );
		}
		/* YyyyMM */
		ny = uploadFileName.substring(idxN-4, idxY+1);
		Date date = new SimpleDateFormat("yyyy年MM月").parse(ny);

		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(date);
		
		String start = DateCalc.getFisrtDayOfMonth(
				gc.get(Calendar.YEAR), 
				gc.get(Calendar.MONTH),
				"yyyy/MM/dd");
		String end = DateCalc.getLastDayOfMonth(
				gc.get(Calendar.YEAR), 
				gc.get(Calendar.MONTH),
				"yyyy/MM/dd");
		/* duration */
		duration = start+"-"+end;
		
		// 创建工作文档对象
		Workbook wb = null;

		if (uploadContentType.equals("application/vnd.ms-excel")) { // xls
			wb = new HSSFWorkbook(new FileInputStream(inputFile));
		}
		// xlsx
		else if (uploadContentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
			wb = new XSSFWorkbook(new FileInputStream(inputFile));
		} else {
			System.out.println("The file with the wrong format！");
		}

		// cs = new POICellStyle(wb);

		// for(int sn=0; sn<wb.getNumberOfSheets(); sn++) {
		// for(int sn=0; sn<1; sn++) {
		// HSSFSheet t = new HSSFSheet();
		Sheet sheet = wb.getSheetAt(0);// 这里只读第一个sheet
		System.out.println("Sheet #" + 0 + " : " + sheet.getSheetName());

		/* 第一行 列头 */
		Row firstRow = sheet.getRow(0);
		int COLNUM = firstRow.getLastCellNum();
		celltitle = new String[COLNUM];
		System.out.println("sheetName:<"+sheet.getSheetName()+">,LastCellNum (1-based):"+COLNUM);
		
		// for(Cell cell : firstRow) {
		for (int i = 0; i < COLNUM; i++) {
			Cell cell = firstRow.getCell(i);// 0-based
//			if (cell.getCellType() != HSSFCell.CELL_TYPE_STRING) {
//				continue;
//			}
			String value = cell.getStringCellValue();
			value = value.trim().replaceAll(" ", "");// 去空
			colNameVec.add(value);
			colNameSet.add(value);
			celltitle[i] = value;
			

//        	indexMap.put(value, i);

            System.out.print("colName "+i+":"+value);
			if(i%5==0){
				System.out.print("\n");
			}

		} // end of for cell
		System.out.print("\n");
		
		if (colNameVec.size() != colNameSet.size()) {
			for(String col : colNameSet){
				if(colNameVec.indexOf(col)!=colNameVec.lastIndexOf(col)){
					dupCol.add(col);
				}
			}
			log.error("工资源文件正在同名列！dupCol:"+dupCol.toString());
			System.out.println("工资源文件正在同名列！dupCol:"+dupCol.toString());
			throw new RuntimeException("工资源文件格式校验不通过：存正在同名列！"+dupCol.toString());
		}

		int lastRowNum = sheet.getLastRowNum();
		System.out.println("sheetName:<"+sheet.getSheetName()+">,lastRowNum (0-based):"+lastRowNum);
		/* 第二行开始为：数据行 */
		for (int i = 1; i <= lastRowNum; i++) {
			Row row = sheet.getRow(i);
			if(null==row|| null==row.getCell(0)){
				System.out.println("null==row|| null==row.getCell(0) break; 行号 i:"+i);
				break;
			}
//			System.out.println(sheet.getSheetName() + "  Row " + row.getRowNum());
			String[] salaryArr = new String[COLNUM];
			
			Map rowMap = new LinkedHashMap();
			MinRYVo vo = new MinRYVo();
			SalaryVo rowVo = new SalaryVo();
			
			// for(Cell cell : row) {
			// COLNUM
//			for (int j = 0; j < row.getLastCellNum(); j++) {
			for (int j = 0; j < COLNUM; j++) {
				Cell cell = row.getCell(j);
				String value="";
				if (null == cell) {
					value = "";
				}else if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
					value = String.valueOf( cell.getNumericCellValue() );
				}else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
					value = cell.getStringCellValue();
				}else{
					value = "";
				} 
				value = (null==value||"null".equalsIgnoreCase(value)?"":value);
				value = value.trim().replaceAll("  ", " ");
				
				if(   celltitle[j].contains("工号") || celltitle[j].contains("签名") ){//第二列工号列  
					continue;
				}else if( null==cell || StringUtils.isBlank(value) ){// j!=1
					/* 如果需要严格的数据验证 */
					if(strictlyVerify){
						/** 每行除第二列之外的所有数据列，必需有值，否则认为Excel此行数据不全 */
						dataErrorNameVec.add(vo.name);//导入的Excel中的数据不全，不发邮件(程序认为导入的Excel姓名数据是全的，且在不全的数据列之前)
						break;
					}else if(celltitle[j].contains("员工编号") 
							|| celltitle[j].contains("姓名")
							|| celltitle[j].contains("基本工资")
							|| celltitle[j].contains("绩效")
							|| celltitle[j].contains("应发合计")
							|| celltitle[j].contains("基本养老金")
							|| celltitle[j].contains("医疗保险金")
							|| celltitle[j].contains("失业保险金")
							|| celltitle[j].contains("住房公积金")
							|| celltitle[j].contains("企业年金")
							|| celltitle[j].contains("工会费")
							|| celltitle[j].contains("代扣税")
							|| celltitle[j].contains("实际扣款合计")
							|| celltitle[j].contains("实发合计")
					){
						
					/** 当不勾选严格验证时，只校验: 
						        员工编号、姓名、基本工资、绩效、应发合计、
						        基本养老金、医疗保险金、失业保险金、住房公积金、企业年金、
						        工会费、代扣税、实际扣款合计、实发合计
						        */
						dataErrorNameVec.add(vo.name);//导入的Excel中的数据不全，不发邮件(程序认为导入的Excel姓名数据是全的，且在不全的数据列之前)
						break;
					}
				}

				if(j==35){
//					System.out.println("test");
				}
				rowMap.put(celltitle[j], value);
				
				SalaryUtil.switchCaseColNameAndValue(
						celltitle[j], value, 
						vo, rowVo,
						nameSet,nameVec );
				
				salaryArr[j] = value;

			} // end of for cell
			/* 计算VO中其它非直接得来的成员值 */
			// 本月工资=基本工资+岗位工资+绩效
			rowVo.setnBygz(rowVo.getnJbgz()+rowVo.getnGwgz()
					+rowVo.nJiXiao);
			// 税前补款=年资津贴+加薪+开门红+防寒暑费+节日费
			rowVo.setnSqbk(rowVo.nNzjt+rowVo.nJiXiao
					+rowVo.nKmh+rowVo.nFhsf
					+rowVo.nJrf);
			// 社保公积金个人扣款=基本养老+医疗保险+失业保险+住房公积金
			rowVo.setnSbgjjgrkk(rowVo.getnEndowment()+rowVo.getnMedical()
					+rowVo.getnSygrkk()+rowVo.getnGjjgrkk());
			// 税后扣款=企业年金+工会费+房租费+电费+物业费
			rowVo.setnShkk(rowVo.nQynj+rowVo.nGhf
					+rowVo.nFzf+rowVo.nDf
					+rowVo.nWyf);
			// 补贴合计=年资津贴+加薪+开门红+防寒暑费+节日费
			rowVo.setnBthj(rowVo.nNzjt+rowVo.salary_increase
					+rowVo.nKmh+rowVo.nFhsf
					+rowVo.nJrf);
			
			// 其他扣款（免税）=0
			rowVo.setnQtkk_ms(0);
			// 其他扣款（纳税）=0
			rowVo.setnQtkk_ns(0);
			// 考勤扣款=0
			rowVo.setnKqkk(0);
			/**
			 * 说明下面两个时间从文件名中取，规则如下：
			 *   文件名中'月'关键字向左取8个字符
			 * 
			 * 
			 * 
			 */
			rowVo.setDuration(duration);// 2018/02/01-2018/02/28
			rowVo.setYyyyMM(ny);//2018年02月  
			//待完善
			
			/* 通过姓名查邮箱 */
			List<TCopoteEmployee> list = dataDAO.findByName(vo.name);
			if (null == list || list.size() == 0) {
				log.error("系统没有<" + vo.name + ">这个人");
				nonameVec.add(vo.name);
				continue;
			} else if (list.size() > 1) {
				List<TCopoteEmployee> emp = dataDAO.findByEmployeeNo(vo.employee_no); 
				
				if( null==emp|| emp.size()==0 ){
					dupName.add(vo.name);
					log.error("系统中<" + vo.name + ">这个人不止一个,且当前人员编号<"+vo.employee_no+">没有录入系统 ");
					System.out.println("系统中<" + vo.name + ">这个人不止一个,且当前人员编号<"+vo.employee_no+">没有录入系统 ");
					continue;
				}else if( dataErrorNameVec.contains(vo.name) ){
					log.error("导入的Excel中<"+vo.name+">的数据不全，不发邮件 ");
					System.out.println("导入的Excel中<"+vo.name+">的数据不全，不发邮件 ");
					continue;
				}else{/**
					数据库存在此人有重名，且都已经录入员工编号(这里是通过人员编号查到的)
					*/
					dataMap.put(emp.get(0).getAlias(),salaryArr);
					mapMap.put(emp.get(0).getAlias(), rowMap);
					salaryMap.put(emp.get(0).getAlias(), rowVo);
//					volist.add(vo);
				}
			} else {/**
					数据库存在此人，且不存在重名(这里是通过姓名查到的)
					*/
				dataMap.put(list.get(0).getAlias(),salaryArr);//发邮件用
				mapMap.put(list.get(0).getAlias(), rowMap);
				salaryMap.put(list.get(0).getAlias(), rowVo);//生成附件用
				
				String idcard = list.get(0).getEmployeeIdcard();
				idcard = StringUtils.isBlank(idcard)?"":idcard;
				rowVo.setEmployee_idcard(idcard);
				
				volist.add(vo);//更新数据库用
			}
		} // end of for row
		// }// end of for sheet

		if(nonameVec.size()>0){
			System.out.println("系统中没有录入这个（些）人！nonameVec:"+nonameVec.toString());
			log.error("这些人需要申请公司邮箱才能在下次收到工资邮件:"+nonameVec.toString());
			log.error("系统中没有录入这个（些）人！nonameVec:"+nonameVec.toString());
//			throw new RuntimeException("系统中没有录入这个（些）人：" +nonameVec.toString());
		}
		if (nameVec.size() != nameSet.size()) {
			for(String col : nameSet){
				if(nameVec.indexOf(col)!=nameVec.lastIndexOf(col)){
					dupName.add(col);
				}
			}
			System.out.println("工资源文件正在同名同姓的人，并且没有在系统中录入员工编号！dupName:"+dupName.toString());
			log.error("工资源文件正在同名同姓的人，并且没有在系统中录入员工编号！dupName:"+dupName.toString());
			throw new RuntimeException("工资源文件存正在同名同姓的人，并且没有在系统中录入员工编号！所以：请对下面的员工录入员工编号：" +dupName.toString());
		}
		if(dataErrorNameVec.size()>0){
			System.out.println("导入的Excel数据不全！dataErrorNameVec:"+dataErrorNameVec.toString());
			log.error("导入的Excel数据不全！dataErrorNameVec:"+dataErrorNameVec.toString());
			throw new RuntimeException("（没有发送任何邮件）原因：导入的Excel数据不全！相关姓名列表：" +dataErrorNameVec.toString());
		}
		
		/** 更新数据库 */
		System.out.println("开始更新数据中的人员编号....");
		notxupdateDB(volist);
		System.out.println("更新数据中的人员编号完成！");

		System.out.println("开始发送邮件....");
		Timestamp beginTime = new Timestamp( new Date().getTime());
				
		String pwd = Base64Util.decodeCopoteMailPWD(user.getCUcip());
		mailUtil.createMailSender(user.getCUemail(), pwd, user.getCUname() );
		/** 发送邮件 */
		if(onlySend2me){
			Map<String,String[]> onlySend2meMap = new HashMap<String,String[]>();
			String[] arr = dataMap.get( user.getCUemail() );
			onlySend2meMap.put(user.getCUemail(), arr);
			sendEmail(onlySend2meMap);
		}else{
			sendEmail(dataMap);
		}
		
		Timestamp endTime = new Timestamp(new Date().getTime());
		ElapsedTimePrinter.printElapsedTime(beginTime, endTime, "发送邮件完成！耗时：");
	}
	

    /** 更新数据库  */
	private void notxupdateDB(List<MinRYVo> list ){ 
		List<String> sqllist = new ArrayList<String>();
		for(int i =0 ; i<list.size(); i++ ){
			MinRYVo vo = list.get(i);
			String idcardSql = " ";
//			if( StringUtils.isNotEmpty(vo.employee_idcard) ){//StringUtils.isNotEmpty(" ")       = true
			if( StringUtils.isNotBlank(vo.employee_idcard) ){//StringUtils.isNotBlank(" ")       = false
				idcardSql= ", employee_idcard='"+vo.employee_idcard+"' ";
			}

			//身份证号如果有值，人员编号就是一定有值
			String sql = "update t_copote_employee "
					+ "    set employee_no='"+vo.employee_no+"' "
							+ idcardSql
					+ "  where name='"+vo.name+"' AND (employee_no IS NULL OR TRIM(employee_no) ='')";
//			CharacterConvert.testCharSet( vo.name );
//			System.out.println("vo.name:"+vo.name);
			System.out.println(sql);
			sqllist.add(sql);
		}
		
		Connection conn = jdbcSConnection.getConnection();
		JDBCBatchInsert batch = new JDBCBatchInsert();

		try{
			conn.setAutoCommit(false);
			
			batch.batchExecSQL(conn, sqllist);
		}catch(BusinessException | SQLException e){
//			throw e;
			log.error("批量更新数据库时出错！");
		}finally{
			try {
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				log.error("置回事务提交属性时出错！");
			}
	//		conn.close();
		}
	}
	/** 生成附件  
	 * @throws Exception **/
	private void generateAttach(String mail, 
			SalaryVo o,
			String tempFAbsDir,
			ByteArrayOutputStream bos ) throws Exception{ 
		
		
//		File f = new File(Test.class.getResource("/").getPath()+"\\template\\工资清单-非mercer.docx");
		
//		System.out.println("FilePathUtils:"+fu.getWebRoot());
		/* 耗时： use time: 0 day 0 hour 0 minute 1 seconds 421 milliseconds 
		 * 
		 * 这个做为湘邮工资邮件的附件生成实现  
		 *   */
		WordUtils util = new WordUtils();
		// 需要进行文本替换的信息
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("${name}", o.getName() );//姓名
		data.put("${yyyyMM}", o.getYyyyMM() );//年月 eg:2018年02月
		data.put("${employee_no}", o.getEmployee_no() );//员工编号   eg:  0086
		String idc = o.getEmployee_idcard();
		data.put("${employee_idcard}", null==idc?"":idc );//证件号  eg:  430722198710286115
		data.put("${duration}", o.getDuration() );//计算期  eg:  2018/02/01-2018/02/28
		
		
		data.put("${nSfgz}", o.getnSfgz() );//实发工资
		data.put("${nBygz}", o.getnBygz() );//本月工资
		data.put("${nSqbk}", o.getnSqbk() );//税前补款
		data.put("${nSbgjjgrkk}", o.getnSbgjjgrkk() );//社保公积金个人扣款
		data.put("${nGrsds}", o.getnGrsds() );//个人所得税
		data.put("${nShkk}", o.getnShkk() );//税后扣款
		
		data.put("${nJbgz}", o.getnJbgz() );//基本工资
//		data.put("${nGwgz}", o.getnGwgz() );//岗位工资
//		data.put("${nJzgxjxsfgz}", o.getnJzgxjxsfgz() );//价值贡献绩效实发工资
		data.put("${nJiXiao}", o.nJiXiao );//价值贡献绩效实发工资
//		data.put("${nBthj}", o.getnBthj() );//补贴合计

		data.put("${nNzjt}", o.nNzjt );//年资津贴
		data.put("${salary_increase}", o.salary_increase );//奖励
		data.put("${nFhsf}", o.nFhsf );//防寒暑费
		data.put("${nJrf}", o.nJrf );//节日费
		
//		data.put("${nQtkk_ns}", o.getnQtkk_ns());// 其他扣款（纳税）
//		data.put("${nKqkk}", o.getnKqkk() );// 考勤扣款
		data.put("${nEndowment}", o.getnEndowment() );//养老个人扣款
		data.put("${nSygrkk}", o.getnSygrkk() );//失业个人扣款
		data.put("${nMedical}", o.getnMedical() );//医疗个人扣款
		data.put("${nGjjgrkk}", o.getnGjjgrkk() );//公积金个人扣款
		data.put("${nQynj}", o.nQynj );//企业年金
		data.put("${nGhf}", o.nGhf );//工会费
		data.put("${nFzf}", o.nFzf );//房租费
		data.put("${nWyf}", o.nWyf );//物业费
		data.put("${nDf}", o.nDf );//电费
		data.put("${nQtkk_ms}", o.getnQtkk_ms() );//其他扣款（免税）
		
		//"D:/daizd/Desktop/工资/工资清单-非mercer.docx"
		this.initXwpf();
//		CustomXWPFDocument xwpf = source.clone();
		CustomXWPFDocument doc = util.getWord(
				xwpf, data, null, null);
		/** 下面的写本地doc文件的代码注释掉，原因：工资为敏感信息不保存临时文件 
		FileOutputStream fopts = new FileOutputStream(
				tempFAbsDir+ny+"工资清单-非mercer-"+ mail +".docx");
		doc.write(fopts); // doc should be a XWPFDocument ;
		fopts.close(); */
		doc.write(bos); 
		
		/*
		 * File f = new File(tempFAbsDir+ny+"工资清单-非mercer-"+ mail +".docx");
		 * String mimetype = new MimetypesFileTypeMap().getContentType(f);
		 * "application/octet-stream",
		 * System.out.println(tempFAbsDir+ny+"工资清单-非mercer-"+ mail +".docx's mimetype is: "+mimetype);
		 */
	}
    /** 不需要事务发送邮件
     *   key: 公司邮箱      value: 邮件正文    
     *   
     * 段鸿飞, 罗俊 没有公司邮箱：
     *    20190503
     * @throws Exception */
	private void sendEmail(Map<String, String[]> map  ) throws Exception{ 

		Timestamp beginTime1 = new Timestamp( new Date().getTime());
		
		String tempFAbsDir = INIT.tempFileDirectory+"/emailAttach/word/";
//		String[] row = //改造为 VO
		
		Iterator<String> it = map.keySet().iterator();
		
		while(it.hasNext()){
			String mail =  it.next();
//			if(!mail.equalsIgnoreCase("daizhongde@copote.com")
//					&&!mail.equalsIgnoreCase("413881461@qq.com")
//					&&!mail.equalsIgnoreCase("dzd2746679@163.com")
//					&&!mail.equalsIgnoreCase("qq413881461@hotmail.com")
//					&&!mail.equalsIgnoreCase("13723868201@139.com")
//					&&!mail.equalsIgnoreCase("daizhongde413881461qq@gmail.com")){
//				System.out.println("给<"+mail+">发送邮件跳过......");
//				continue;
//			}

			System.out.println("开始给<"+mail+">发送邮件....");
			msg.put(alias,  "开始给<"+mail+">发送邮件....");
			Timestamp beginTime = new Timestamp( new Date().getTime());
			try {

				ByteArrayOutputStream bos = new ByteArrayOutputStream();
				/* 生成附件(流)  **/
				generateAttach(  mail, 
						salaryMap.get(mail),
						tempFAbsDir,
						bos
						);
						
//				String text = map.get(mail);
				String text = SalaryUtil.convert2HTMLTable(
						celltitle, dataMap.get(mail) );
//				text="<html><body>"
//						+ "<table border=\"1\"<tr><th>姓名</th> <th>电话</th><th>电话</th></tr><tr> <td>Bill Gates</td> <td>555 77 854</td><td>555 77 855</td></tr></table>"
//		+ "</body></html>";

//				mailUtil.sendMail(mail, ny+"工资清单", 
//						text,
//						tempFAbsDir+ny+"工资清单-非mercer-"+ mail +".docx",
//						"工资清单-"+ny+".docx");
				
				// new MimetypesFileTypeMap().getContentType(f)
				mailUtil.sendMail(mail, "工资清单-"+ny, 
						text,
						new ByteArrayInputStream(bos.toByteArray()),
//						"application/octet-stream",
						"application/vnd.openxmlformats-officedocument.wordprocessingml.document",
						"工资清单-"+ny+".docx");
			} catch (AccountEmailException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				log.error("给<"+mail+">发送邮件失败！"+e.getLocalizedMessage());
				
				String name = salaryMap.get(mail).getName();
				sendErrorNameVec.add(name+"<"+mail+">");
			}
			Timestamp endTime = new Timestamp(new Date().getTime());
//			ElapsedTimePrinter.printElapsedTime(beginTime, endTime, "给<"+mail+">发送邮件完成！耗时：");

			msg.put(alias,  
					ElapsedTimePrinter.printElapsedTime2(
							beginTime, endTime,"给<"+mail+">发送邮件完成！") 
					);
		}//end of while 
		
		if(sendErrorNameVec.size()>0){
			log.error(
					"邮件发送完毕！但部分发送失败："
							+ sendErrorNameVec.toString());
			throw new RuntimeException(
					"邮件发送完毕！但部分发送失败："
							+ sendErrorNameVec.toString());
		}

		Timestamp endTime1 = new Timestamp(new Date().getTime());
		msg.put(alias,  
				ElapsedTimePrinter.printElapsedTime2(
						beginTime1, endTime1,"邮件发送完成！") 
				);
	}
	 
	public static void main(String[] args) {
//		Connection conn = null;
		try {
//			String inputFile = "F:\\asiainfo\\DR4\\connexion report\\OP Migration WS_Connexion Report DR4#.xlsx";
//			String outputFile = "F:\\asiainfo\\DR4\\connexion report\\OP Migration WS_Connexion Report DR4#_output.xlsx";
//			String inputFile = "F:\\Java项目\\migration2.0\\测试文档\\OPP Migration Report DR# - Wholesale  V0.2 - test.xlsx";
//			String outputFile ="F:\\Java项目\\migration2.0\\测试文档\\OPP Migration Report DR# - Wholesale  V0.2 - test1.xlsx";
			
			String inputFile = "G:\\KPI3-sql.xlsx";
			String outputFile ="G:\\result.xlsx";
			
//			"G:\\KPI2-sql.xls","G:\\result.xls"
			
			//"F:\asiainfo\DR4\connexion report\OP Migration WS_Connexion Report DR4#.xlsx"
//			conn = getJDBCConnection("localhost", "3306", "tool", "root",
//					"123");
//			conn.setAutoCommit(false);
			Excel2Email e2e = new Excel2Email();
			TAuthorityUser user = new TAuthorityUser();
			user.setCUemail("daizhongde@copote.com");
			user.setCUcip("DZd123456");
			user.setCUname("戴忠德");
			
			//text/plain,
//			application/vnd.ms-excel,
//			application/vnd.openxmlformats-officedocument.spreadsheetml.sheet
			e2e.notxsendEmailByExcel(inputFile, "KPI3-sql.xlsx", 
					"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",
					user);
			
//			con_Default.commit();
//			con_Default.close();
			System.out.println("all success,no errors!");
		} catch (Exception e) {
//			try {
//				con_Default.rollback();
//				con_Default.close();
//			} catch (SQLException e1) {
//				e1.printStackTrace();
//				System.err.println("rollback error!");
//			}
			e.printStackTrace();
			System.out.println("error:" + e.getLocalizedMessage());
		}finally{
//			try {
//				con_Default.close();
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		}
	}
}
/**
 * 暂没有用到
 * @author daizd
 * @date 2019年4月23日
 */
class SendEmailRunnable implements Runnable {
	private static final Logger log = LoggerFactory.getLogger(SendEmailRunnable.class);
	
//	private static int i=0;

	SendEmailRunnable() {

	}

	@Override
	public void run() {
		try {
			log.info("");


		} catch (Exception e) { 
			
		} finally { 
			
		}
	}
}