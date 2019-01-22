package person.daizhongde.migration.spring.jdbc;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

import jxl.Sheet;
import jxl.Workbook;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import person.daizhongde.migration.constant.TableName;
import person.daizhongde.migration.exception.BusinessException;
import person.daizhongde.migration.spring.service.PubService;
import person.daizhongde.migration.spring.service.impl.BusiMemoryServiceImpl;

/**
 * 
 * @author pengsheng
 * @integration daizd
 * @modify daizd
 * 
 * <p>导入作业脚本
 */
public class ReadXls2 {
	private static final Log log = LogFactory.getLog(ReadXls2.class);

	 private PubService pubSrv;
	
	 public void setPubSrv(PubService pubSrv) {
		 this.pubSrv = pubSrv;
	 }

	class Temp {
		String sql;
		String replace;
		String db;
		String replace2;
		String isRunSql;
	}

	private Connection con = null;

	private Hashtable<String, Temp> tmpList = new Hashtable<String, Temp>();
	private Hashtable<Integer, List<String>> orderHash = new Hashtable<Integer, List<String>>();
	private String global;

	private String fileName;
	private String author;
	private String group;
	private static int seq = 1;

	String jobId = "";
//	private static int job_currval = 0;
//	private static int com_currval = 0;
//	private static int task_currval = 0;

	// private static int node_currval = 0;

	// public static String node_nextval(){
	// //MessageFormat.format("{0,number,00000000}"
	// //String paySerial = "";
	// if(node_currval == 0){//invoke first time
	// //zero count <= nine count, less zero to reduce reset times
	// node_currval =
	// (int)(Math.random()*10000000);//防止程序�?��启动多次重启，产生相同的交易流水�?
	// }else{
	// node_currval = (node_currval >= 99999999)? 1: node_currval + 1;
	// }
	// return MessageFormat.format("{0,number,00000000}", new
	// Integer(node_currval));
	// }

	public String com_nextval() {
//		if (com_currval == 0) {// invoke first time
//			// zero count <= nine count, less zero to reduce reset times
//			com_currval = (int) (Math.random() * 10000);// 防止程序�?��启动多次重启，产生相同的交易流水�?
//		} else {
//			com_currval = (com_currval >= 99999) ? 1 : com_currval + 1;
//		}
//		return new SimpleDateFormat("yyMM").format(new Date()).substring(1)
//				+ MessageFormat.format("{0,number,00000}", new Integer(
//						com_currval));
		 return pubSrv.get10ByteCode( TableName.mig_com_info+"2" );
	}

	public String task_nextval() {
//		if (task_currval == 0) {// invoke first time
//			// zero count <= nine count, less zero to reduce reset times
//			task_currval = (int) (Math.random() * 10000);// 防止程序�?��启动多次重启，产生相同的交易流水�?
//		} else {
//			task_currval = (task_currval >= 99999) ? 1 : task_currval + 1;
//		}
//		return new SimpleDateFormat("yyMM").format(new Date()).substring(1)
//				+ MessageFormat.format("{0,number,00000}", new Integer(
//						task_currval));
		 return pubSrv.get10ByteCode( TableName.mig_task_info+"2" );
	}

	/**
	 * 取得下一�?位序�?
	 * 
	 * @return
	 */
	public String job_nextval() {
//		if (job_currval == 0) {// invoke first time
//			// zero count <= nine count, less zero to reduce reset times
//			job_currval = (int) (Math.random() * 10000);// 防止程序�?��启动多次重启，产生相同的交易流水�?
//		} else {
//			job_currval = (job_currval >= 99999) ? 1 : job_currval + 1;
//		}
//		return new SimpleDateFormat("yyMM").format(new Date()).substring(1)
//				+ MessageFormat.format("{0,number,00000}", new Integer(
//						job_currval));
		 return pubSrv.get10ByteCode( TableName.mig_job_info+"2" );
	}

	public Connection getCon() {
		return con;
	}

	public void setCon(Connection con) {
		this.con = con;
	}

	public static Connection getJDBCConnection(String IP, String port,
			String db, String user, String passwd) throws Exception {
		String url = ("jdbc:mysql://" + IP + ":" + port + "/" + db + "?user="
				+ user + "&password=" + passwd);
		String driver = "com.mysql.jdbc.Driver";
		Class.forName(driver);

		return DriverManager.getConnection(url);

	}

//	public ReadXls2(Connection con, String fileName) throws Exception {
//
//		log.info("enter  ReadXls2 construct!");
//		this.setCon(con);
//
//		this.fileName = fileName;
//		this.author = getContent(Workbook.getWorkbook(new File(this.fileName))
//				.getSheet(1), 0, 1);
//		this.group = getContent(Workbook.getWorkbook(new File(this.fileName))
//				.getSheet(1), 0, 4);
//
//		// jobId=fileName;
//		jobId = job_nextval();
//		
//		log.info("exit ReadXls2  construct!");
//	}
//	
	public ReadXls2(Connection con, String fileName, String author ) throws Exception {

		log.info("enter  ReadXls2 construct!");
//		this.pubSrv = pubSrv;
		this.setCon(con);

		this.fileName = fileName;
		this.author = author;
		this.group = getContent(Workbook.getWorkbook(new File(this.fileName))
				.getSheet(1), 0, 4);

		// jobId=fileName;
		jobId = job_nextval();

		log.info("exit ReadXls2  construct!");
	}
	public ReadXls2(Connection con, String fileName, String author, PubService pubSrv) throws Exception {

		log.info("enter  ReadXls2 construct!");
		this.pubSrv = pubSrv;
		this.setCon(con);

		this.fileName = fileName;
		this.author = author;
		this.group = getContent(Workbook.getWorkbook(new File(this.fileName))
				.getSheet(1), 0, 4);

		// jobId=fileName;
		jobId = job_nextval();

		log.info("exit ReadXls2  construct!");
	}
	/*
	 * public static int get_seq() { return seq++; }
	 */
	public String getContent(Sheet sht, int col, int row) {
		String result;
		try {
			result = sht.getCell(col, row).getContents().trim();
		} catch (Exception e) {
			throw new BusinessException("Error! When get value from excel: "
					+ "sheet: " + sht + ", col: " + col + ", row: " + row);
		}

		return result;
	}

	public Hashtable<Integer, String> doJobInfo(Set set) throws Exception {
		log.debug("entering doJobinfo..");
		int i = 0;
		PreparedStatement stmt = null;

		Hashtable<Integer, String> hash = new Hashtable<Integer, String>();
		String sql = "insert into mig_job_info(job_id,job_name,job_author,job_remark,job_update,type) values(?,?,?,'zzzz',now(),?)";

		try {
			stmt = con.prepareStatement(sql);

			stmt.setString(1, jobId);
			stmt.setString(2, this.group);
			stmt.setString(3, this.author);
			stmt.setString(4, "1");
			stmt.addBatch();
			++i;

			for (Object obj : set) {
				int order = (Integer) obj;
				String job = job_nextval();
				stmt.setString(1, job);
				hash.put(order, job);
				stmt.setString(2, this.group + order);
				stmt.setString(3, this.author);
				stmt.setString(4, "5");
				stmt.addBatch();
				++i;
			}
			stmt.executeBatch();
			stmt.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			Throwable e2 = ex;
			while (e2.getCause() != null) {
				e2 = e2.getCause();
			}
			throw new BusinessException("Error when importing Job Information:"
					+ e2.getLocalizedMessage());
		}
		log.info("finish doJobinfo..insert " + i + " records!");

		return hash;
	}

	public void doJobPara(String nodeId, String global, boolean isGlobal,
			String stepId) throws Exception {
		log.debug("entring doJobPara...!");
		int i = 0;
		String sql = "insert into mig_job_para(node_id,para,para_type,para_value,para_name) values(?,?,1,?,?)";
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(sql);

			String[] paras = {};
			if (!global.trim().equals("")) {
				paras = global.split(";");
			}

			for (String str : paras) {
				String[] para = str.split("=");
				if (para.length != 2) {
					continue;
				}

				stmt.setString(1, nodeId);
				stmt.setString(2, para[0]);
				stmt.setString(3, para[1]);
				stmt.setString(4, para[0]);
				stmt.addBatch();

				// String strTmp=null;
				// String strPara=null;

				if (isGlobal) {
					for (String s : tmpList.keySet()) {
						Temp tmp = tmpList.get(s);
						if ((tmp.sql) != null
								&& (tmp.sql.indexOf("@{" + para[0] + "}") == -1))
							tmp.sql = tmp.sql.replace(para[0], "@{" + para[0]
									+ "}");
					}
				} else {
					if ((tmpList.get(stepId) != null)
							&& (tmpList.get(stepId).sql != null)
							&& (tmpList.get(stepId).sql.indexOf("@{" + para[0]
									+ "}") == -1))
						// strTmp=tmpList.get(stepId).sql;
						// strPara=para[0];
						// System.out.println(strTmp);
						// System.out.println("----------------------------------------------");
						// System.out.println(strPara);
						// System.out.println("----------------------------------------------");
						// System.out.println(strTmp.replace(strPara,
						// "@{"+strPara+"}"));
						tmpList.get(stepId).sql = tmpList.get(stepId).sql
								.replace(para[0], "@{" + para[0] + "}");
				}

			}

			stmt.executeBatch();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			Throwable e2 = e;
			while (e2.getCause() != null) {
				e2 = e2.getCause();
			}
			throw new BusinessException("Error while importing job parameters:"
					+ e2.getLocalizedMessage());
		}
		log.info("finish doJobPara..insert " + i + " records!");

	}

	public Hashtable doJobContent(Hashtable hash,
			Hashtable<Integer, String> jobhash) throws Exception {
		log.debug("entering doJobContent..");

		Hashtable coHash = new Hashtable();

		int j = 0;
		String sql = "insert into mig_job_content(job_id,node_id,isleaf,node_status,prepos,postpos,coords) values(?,?,?,0,?,?,?)";
		PreparedStatement stmt = null, stmt_task = null;
		try {
			stmt = con.prepareStatement(sql);

			stmt_task = con
					.prepareStatement("insert into mig_task_info(task_id,task_author,task_update,control_id,com_id,task_name) values(?,?,now(),?,?,?)");

			List<Integer> ls = new ArrayList<Integer>();

			for (Object obj : hash.keySet()) {
				int tmp = (Integer) obj;
				ls.add(tmp);
			}
			int[] a = new int[ls.size()];
			for (int i = 0; i < ls.size(); i++) {
				a[i] = ls.get(i);
			}
			Arrays.sort(a);

			String[] coords = generateCoords(a.length);

			for (int i = 0; i < a.length; i++) {
				stmt.setString(1, this.jobId);
				String node_id = (String) jobhash.get(a[i]);
				if (node_id == null)
					continue;
				stmt.setString(2, node_id);
				stmt.setInt(3, 0);
				stmt.setString(4,
						i - 1 < 0 ? "" : (String) jobhash.get(a[i - 1]));
				stmt.setString(5,
						i + 1 >= a.length ? "" : (String) jobhash.get(a[i + 1]));
				// stmt.setString(6, "200,200");
				stmt.setString(6, coords[i]);
				stmt.addBatch();
				++j;

				List list = (List) hash.get(a[i]);

				String[] coords2 = null;
				if (list != null) {
					coords2 = generateCoords(list.size());

					int m = 0;
					for (Object o : list) {
						String s = (String) o;
						String value = task_nextval();
						stmt.setString(1, (String) jobhash.get(a[i]));
						stmt.setString(2, value);
						stmt.setInt(3, 1);
						stmt.setString(4, "");
						stmt.setString(5, "");

						stmt.setString(6, coords2[m]);
						stmt.addBatch();

						stmt_task.setString(1, value);
						stmt_task.setString(2, this.author);

						String co_id;
						if (coHash.containsKey(s)) {
							co_id = (String) coHash.get(s);
							this.doJobPara(value, tmpList.get(s).replace2,
									false, s);
						} else {
							co_id = com_nextval();
							this.doJobPara(value, tmpList.get(s).replace,
									false, s);
						}

						if ("S".equals(tmpList.get(s).isRunSql))
							stmt_task.setString(3, "con006");
						else 
							stmt_task.setString(3, "con005");
					
						stmt_task.setString(4, co_id);
						stmt_task.setString(5, s);
						coHash.put(s, co_id);
						stmt_task.addBatch();

						++j;
						m++;
					}
				}
				stmt.executeBatch();
				stmt_task.executeBatch();
			}
			stmt.close();
			stmt_task.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			Throwable e2 = ex;
			while (e2.getCause() != null) {
				e2 = e2.getCause();
			}
			throw new BusinessException("Error when importing job content:"
					+ e2.getLocalizedMessage());
		}

		log.info("finish doJobContent....insert " + j + " records!");
		return coHash;
	}

	void doComInfo(Hashtable cohash) throws Exception {

		log.debug("entering doComInfo..");
		int j = 0;

		PreparedStatement stmt = null;
		String sql = "insert into mig_com_info(com_id,para_id,para_value) values(?,?,?)";
		try {

			stmt = con.prepareStatement(sql);
			for (Object o : cohash.keySet()) {
				Temp t = tmpList.get(o);

				if ("S".equals(t.isRunSql)) {
					stmt.setString(1, (String) cohash.get(o));
					stmt.setInt(2, 1);
					stmt.setString(3, "@{$HIP}");
					stmt.addBatch();

					stmt.setString(1, (String) cohash.get(o));
					stmt.setInt(2, 2);
					stmt.setString(3, "@{$USERPASS}");
					stmt.addBatch();

					stmt.setString(1, (String) cohash.get(o));
					stmt.setInt(2, 3);
					stmt.setString(3, t.sql);
					stmt.addBatch();

					stmt.setString(1, (String) cohash.get(o));
					stmt.setInt(2, 4);
					stmt.setString(3, "1");
					stmt.addBatch();

					j += 4;

				} else if ("Y".equals(t.isRunSql)) {
					stmt.setString(1, (String) cohash.get(o));
					stmt.setInt(2, 1);
					stmt.setString(3, "1|@{DIP}|mig123|mig123|test|@{PORT}");
					stmt.addBatch();

					if (null != t.sql)
						t.sql = t.sql.replaceAll("COMMENT '[^']*'", "")
								.replaceAll("comment '[^']*'", "");
					else
						t.sql = "";

					stmt.setString(1, (String) cohash.get(o));
					stmt.setInt(2, 2);
					stmt.setString(3, t.sql);
					stmt.addBatch();

					stmt.setString(1, (String) cohash.get(o));
					stmt.setInt(2, 3);
					stmt.setString(3, "10");
					stmt.addBatch();

					j += 3;
				} else{
					stmt.setString(1, (String) cohash.get(o));
					stmt.setInt(2, 1);
					stmt.setString(3, "1|@{DIP}|mig123|mig123|test|@{PORT}");
					stmt.addBatch();

				
					t.sql = "";

					stmt.setString(1, (String) cohash.get(o));
					stmt.setInt(2, 2);
					stmt.setString(3, t.sql);
					stmt.addBatch();

					stmt.setString(1, (String) cohash.get(o));
					stmt.setInt(2, 3);
					stmt.setString(3, "10");
					stmt.addBatch();
				}
			}
			stmt.executeBatch();
			stmt.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			Throwable e2 = ex;
			while (e2.getCause() != null) {
				e2 = e2.getCause();
			}
			throw new BusinessException("Error when importing com information:"
					+ e2.getLocalizedMessage());
		}
		log.info("finish doComInfo....insert " + j + " records!");

	}

	public void readToM() throws Exception {

		log.info("enter readToM()");

		int i = 0;

		try {
			Workbook book = Workbook.getWorkbook(new File(this.fileName));
			Sheet[] sheetArray = book.getSheets();
			if (sheetArray.length < 4) {
				throw new BusinessException("Excel must have four sheet!");
			}
			Sheet sheetExecOrder = sheetArray[1];

			int status = 0;

			i = 3;
			String stepId = "";
			List<String> leafList;

			int rows = sheetExecOrder.getRows();

			// order对应的step列表
			int order;

			// step_id对应的sql等信�?

			while (true) {
				if (i + 1 > rows) {
					break;
				}

				if (getContent(sheetExecOrder, 2, i).trim().equals("")) {
					break;
				}

				order = Integer.parseInt(getContent(sheetExecOrder, 1, i));
				String step_id = getContent(sheetExecOrder, 2, i).toUpperCase();

				Temp t = new Temp();
				t.replace = getContent(sheetExecOrder, 4, i);

				if (tmpList.containsKey(step_id)) {
					Temp t1 = tmpList.get(step_id);
					t1.replace2 = t.replace;
				} else {
					tmpList.put(step_id, t);
				}

				if (!orderHash.containsKey(order)) {
					List<String> stepList = new ArrayList<String>();
					stepList.add(step_id);
					orderHash.put(order, stepList);
				} else {
					((List<String>) orderHash.get(order)).add(step_id);
				}
				i++;
			}

			for (int idx = 3; idx < sheetArray.length; idx++) {
				Sheet sheetMapping = sheetArray[idx];
				i = 1;

				while (true) {
					if (i == 20) {
						log.debug("20 row reach!");
					}
					rows = sheetMapping.getRows();
					if (i + 1 > rows) {
						break;
					}

					String tgtColumn = getContent(sheetMapping, 4, i);
					String isRunSql = getContent(sheetMapping, 12, i);

					if ((tgtColumn.trim().equals(""))
							&& (isRunSql.trim().equals(""))) {
						break;
					}

					if (getContent(sheetMapping, 0, i).trim() != "") {
						Temp t = tmpList.get(getContent(sheetMapping, 0, i).toUpperCase());
						if (t != null) {
							t.sql = getContent(sheetMapping, 13, i);
							t.db = getContent(sheetMapping, 2, i);
							t.isRunSql = isRunSql;
						}
					}
					i++;
				}

			}

			global = getContent(sheetExecOrder, 1, 1).trim();

			book.close();
		} catch (Exception e) {
			throw e;
		}

		log.info("exit readToM()");

	}

	public void getAndInsert() throws Exception {

		log.info("enter getAndInsert()!");

		readToM();
		// order对应jobid
		Hashtable jobhash = doJobInfo(orderHash.keySet());

		doJobPara(jobId, global, true, null);

		Hashtable leafhash = doJobContent(orderHash, jobhash);

		log.info("order deal complete");

		doComInfo(leafhash);

		log.info("exit getAndInsert()!");

	}

	private String[] generateCoords(int nodeTotal) {
		String[] coords = new String[nodeTotal];

		// edge:(1304, 565) (1274, 545) (1045, 550) (1045, 379)
		// edgeX 由 1304  改为了 1144 一行少显示两个
		int startX = 60, startY = 30, stepX = 80, stepY = 60, x, y, edgeX = 1144, edgeY = 565, page = 1;
		x = startX;
		y = startY;
		coords[0] = x + "," + y;
		int line = 1;

		for (int i = 1; i < nodeTotal; i++) {
			if (y > edgeY - 40) {// 再一次从左上角开始
				line++;
				x = startX;
				y = startY + page * 20;
				stepY = Math.abs(stepY);
				coords[i] = x + "," + y;
				page++;
				continue;
			} else if (x > edgeX - 60 && y <= edgeY - 40)// x越界需要新起一行
			{
				line++;
				x = startX;
				y = startY + (line - 1) * Math.abs(stepY) * 2;
				stepY = Math.abs(stepY);
				coords[i] = x + "," + y;
				continue;
			} else {
				x += stepX;
				y += stepY;
				stepY = -stepY;

				coords[i] = x + "," + y;
			}
		}
		return coords;
	}

	public static void main(String[] args) {
		// if (args.length != 6) {
		// System.out.println("must and only can have 6 parameter identify the xls file!");
		// return;
		// }
		Connection conn = null;
		try {
			// ReadXls rx = new ReadXls(args[0], args[1], args[2], args[3],
			// args[4], args[5]);

			String path = "C:\\Users\\ps\\Desktop\\excel_mapping_20150606\\excel_mapping\\mapping_billing_sr0.32_02_lk_V6.4.xls";
			//
			// String path =
			// "F:\\Java项目\\migration2.0\\测试文档\\测试数据\\mapping_c2b_order_sr0.32.xls";//
			// String path =
			// "F:\\Java项目\\migration2.0\\测试文档\\测试数据\\mapping_crm_patch_sr0.3_gsh_20150517.xls";//
			// String path =
			// "F:\\Java项目\\migration2.0\\测试文档\\测试数据\\mapping_crm_sr0.3_v4bis_gsh_20150517.xls";//
			// String path =
			// "F:\\Java项目\\migration2.0\\测试文档\\测试数据\\mapping_order_patch_sr0.32.xls";//
			// String path =
			// "F:\\Java项目\\migration2.0\\测试文档\\测试数据\\mapping_order_sr0.32.xls";//
			conn = getJDBCConnection("10.1.249.100", "3306", "tool", "root",
					"easeaseas");
			conn.setAutoCommit(false);
			ReadXls2 rx = new ReadXls2(conn, path,"daid");
			rx.getAndInsert();

			conn.commit();
			conn.close();
		} catch (Exception e) {
			try {
				conn.rollback();
				conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
				System.err.println("rollback error!");
			}
			e.printStackTrace();
			System.out.println("error:" + e.getLocalizedMessage());
			return;
		}
		System.out.println("all success,no errors!");
	}
}