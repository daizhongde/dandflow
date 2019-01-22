package person.daizhongde.migration.spring.jdbc;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import person.daizhongde.migration.exception.BusinessException;

import jxl.Sheet;
import jxl.Workbook;

/**
 * 
 * @author pengsheng
 * @integration daizd
 * @modify daizd
 * <p>导入稽核脚本
 *
 */
class Audit {
	public int audit_id;
	public String audit_name;
	public String db;
	public String type;
	public String sql;
	public String sql_rep;
	public String author;
	public String audit_value;
	public String operator;
	public String audit_unit;
	public String audit_flag;
	public String invalid_sql;
	public String scr_table;
	public String dst_table;
	public String enum_column;
	public String relation_condition;
	public String remark;
	public String version;
	public String reason;
	public String priority;
}

public class Audit_Load {
	private static final Logger log = LoggerFactory.getLogger(Audit_Load.class);

	private String fileName;
	private String author;
	
	private List<Audit> list = new ArrayList();
	private Hashtable<String, Integer> domainHash = new Hashtable<String, Integer>();

	private Connection conn;
	public Connection getConn() {
		return conn;
	}
	public void setConn(Connection conn) {
		this.conn = conn;
	}
	public Audit_Load(Connection con, String fileName, String author) {
		log.info("enter  Audit_Load construct!");
		this.setConn(con);
		this.fileName = fileName;
		this.author = author;
		log.info("exit Audit_Load  construct!");
	}

	public static Connection getJDBCConnection(String IP, String port,
			String db, String user, String passwd) throws Exception {
		String url = ("jdbc:mysql://" + IP + ":" + port + "/" + db + "?user="
				+ user + "&password=" + passwd);
		System.out.println("url:"+url);
		String driver = "com.mysql.jdbc.Driver";
		Class.forName(driver);
		return DriverManager.getConnection(url);
	}

//	public int disconnect() {
//		if (this.conn != null) {
//			try {
//				this.conn.commit();
//				this.conn.close();
//				this.conn = null;
//			} catch (Exception ex) {
//				log.error(ex.getLocalizedMessage());
//				return -1;
//			}
//		}
//		return 0;
//	}

	public void get_excel_data() {
		Audit tmp = null;
		int row = 1;
		try {
			Workbook book = Workbook.getWorkbook(new File(this.fileName));
			Sheet[] sheetArray = book.getSheets();
			if (sheetArray.length < 2) {
				throw new Exception("excel must have at list 2 sheets!");
			}

			for (int i = 1; i < sheetArray.length; i++) {
				Sheet sht = sheetArray[i];
				Hashtable hash = new Hashtable();
				for (int j = 0;; j++) {
					String cont = getContent(sht, j, 0);
					if ((cont == null) || (cont.equals("")))
						break;
					hash.put(cont.trim().toUpperCase(), Integer.valueOf(j));
				}
				
				while (true) {
					Audit aud = new Audit();
//					Printer.printJSON(hash);
//					System.out.println("hash.get(AUDIT_ID):"+hash.get("AUDIT_ID"));
					if (getContent(sht,
							((Integer) hash.get("AUDIT_ID")).intValue(), row)
							.equals(""))
						break;
					tmp = aud;
					if (hash.containsKey("VALID_FLAG".toUpperCase()) == false
							|| ("N".equals(getContent(sht, ((Integer) hash
									.get("VALID_FLAG".toUpperCase()))
									.intValue(), row)) == false)) {
						aud.audit_id = Integer.parseInt(getContent(sht,
								((Integer) hash.get("AUDIT_ID".toUpperCase()))
										.intValue(), row));
						aud.audit_name = getContent(sht, ((Integer) hash
								.get("AUDIT_NAME".toUpperCase())).intValue(),
								row);
						aud.db = getContent(sht, ((Integer) hash.get("DB"
								.toUpperCase())).intValue(), row);
						
//						CONSISTANCEY OR LEGITIMACY
//						CONSISTENCY OR LEGITIMACY   --right
						Object v = hash.get("Consistency or Legitimacy"
								.toUpperCase());

						aud.type = getContent(sht, ((Integer) v).intValue(),
								row);
						aud.sql = getContent(sht, ((Integer) hash.get("SQL"
								.toUpperCase())).intValue(), row);
						aud.sql_rep = getContent(sht,
								((Integer) hash.get("SQL_REP".toUpperCase()))
										.intValue(), row);
//						aud.author = getContent(sht,
//								((Integer) hash.get("AUTHOR".toUpperCase()))
//										.intValue(), row);
						aud.author = this.author;
						aud.audit_value = getContent(
								sht,
								((Integer) hash.get("AUDIT_VALUE".toUpperCase()))
										.intValue(), row);
						aud.operator = getContent(sht,
								((Integer) hash.get("OPERATOR".toUpperCase()))
										.intValue(), row);
						aud.audit_unit = getContent(sht, ((Integer) hash
								.get("AUDIT_UNIT".toUpperCase())).intValue(),
								row);
						aud.audit_flag = getContent(sht, ((Integer) hash
								.get("AUDIT_FLAG".toUpperCase())).intValue(),
								row);
						aud.invalid_sql = getContent(
								sht,
								((Integer) hash.get("INVALID_SQL".toUpperCase()))
										.intValue(), row);
						aud.scr_table = getContent(sht,
								((Integer) hash.get("SRC_TABLE".toUpperCase()))
										.intValue(), row);
						aud.dst_table = getContent(sht,
								((Integer) hash.get("DST_TABLE".toUpperCase()))
										.intValue(), row);
						aud.enum_column = getContent(
								sht,
								((Integer) hash.get("ENUM_COLUMN".toUpperCase()))
										.intValue(), row);
						aud.relation_condition = getContent(sht,
								((Integer) hash.get("RELATION_CONDITION"
										.toUpperCase())).intValue(), row);
						aud.remark = getContent(sht,
								((Integer) hash.get("REMARK".toUpperCase()))
										.intValue(), row);
						aud.version = getContent(sht,
								((Integer) hash.get("VERSION".toUpperCase()))
										.intValue(), row);
						// aud.reason= getContent(sht,
						// ((Integer)hash.get("REASON".toUpperCase())).intValue(),
						// row);
						if (hash.containsKey("priority".toUpperCase())) {
							aud.priority = getContent(sht,
									((Integer) hash.get("priority"
											.toUpperCase())).intValue(), row);
						}

						if (null != aud.sql_rep) {
							String[] arr = aud.sql_rep.split(";");
							for (String s : arr) {
								String[] key_arr = s.split("=");

								if (key_arr.length == 2) {
									String key = key_arr[0];
									String value = key_arr[1];
									aud.sql = aud.sql.replace(key, "@{" + key
											+ "}");//注释掉后就会与先前的方式不一样，变量名不需要加特殊符号来避免替换不需要替换的数据
									aud.sql_rep = aud.sql_rep.replace(s, key
											+ "=[" + value + "]");
								}
							}
						}

						this.list.add(aud);
					}
					row++;
				}
			}

		} catch (Exception ex) {
			log.error(ex.getLocalizedMessage() + ":audit_id=" + tmp.audit_id+",row no:"+row);
			log.error("get excel data error!");
			ex.printStackTrace();
			throw new BusinessException("Get excel data error!"+ex.getLocalizedMessage() + ":audit_id=" + tmp.audit_id);
		}
	}

	public void executeDelete(String sql) throws Exception {
		Statement stmt = null;
		stmt = this.conn.createStatement();
		stmt.executeUpdate(sql);
		stmt.close();
	}

	public void insert_to_db() {
		PreparedStatement stmt_tpl = null;
		PreparedStatement stmt_ins = null;
		PreparedStatement stmt_aud = null;

		int fail = 0;
		Audit aud = null;
		try {
			/*
			 * stmt_tpl = this.conn .prepareStatement(
			 * "INSERT INTO mig_trans_tpl(mig_type,dst_db,dst_owner,dst_tpl,mig_group,mig_group_sort,mig_sql,mig_author,last_updated,tpl_id,mig_desc) VALUES('DST',?,'migration',?,?,1,?,?,NOW(),?,?)"
			 * ); stmt_ins = this.conn .prepareStatement(
			 * "INSERT INTO mig_trans_instance(mig_type,mig_db,mig_owner,mig_tpl,mig_tab,mig_sql_rep,mig_author,last_updated) VALUES('DST',?,'migration',?,?,?,?,NOW())"
			 * );
			 */stmt_aud = this.conn
					.prepareStatement("INSERT INTO mig_auditv_config(audit_id,domain,table_name,audit_name,audit_type,sql_type,src_audit_sql,audit_value,audit_flag,invalid_data_sql,operator,audit_unit,audit_author,src_db_connect,mig_sql_rep,remark,VERSION) VALUES(?,?,?,?,2,1,?,?,?,?,?,?,?,?,?,?,?)");

			for (Audit tmp : this.list) {
				aud = tmp;

				if (tmp.type.equals("C")) {/*
											 * String str = tmp.sql;
											 * 
											 * if ((str == null) ||
											 * (str.trim().equals(""))) { if
											 * ((tmp.enum_column == null) ||
											 * (tmp.enum_column.equals(""))) {
											 * System.out.println("audit_id=" +
											 * tmp.audit_id +
											 * ":some columns is empty,skipped!"
											 * ); } else { String user =
											 * tmp.enum_column.substring(0,
											 * tmp.enum_column.indexOf(','));
											 * String tabname = tmp.enum_column
											 * .
											 * substring(tmp.enum_column.indexOf
											 * (',') + 1);
											 * 
											 * if ((tmp.relation_condition ==
											 * null) ||
											 * (tmp.relation_condition.trim()
											 * .equals(""))) { str =
											 * "INSERT INTO dmtool.mig_audit_report_detail_result select '"
											 * + user + "','" + tabname + "','"
											 * + tabname +
											 * "','0','zzzz','zzzz',x.src_num,y.dst_num,'"
											 * + tmp.author + "','" +
											 * tmp.version +
											 * "' from  (SELECT COUNT(1) src_num FROM "
											 * + tmp.scr_table +
											 * ") x, (SELECT COUNT(1) dst_num FROM "
											 * + tmp.dst_table + ") y;commit"; }
											 * else { str =
											 * "INSERT INTO dmtool.mig_audit_report_detail_result SELECT z.entity,z.audit_item,z.enum_desc,'0',z.src_enum,z.dst_enum,x.src_num,y.dst_num,'"
											 * + tmp.author + "','" +
											 * tmp.version + "'  FROM (SELECT "
											 * + tmp.relation_condition +
											 * ",count(1)  src_num FROM " +
											 * tmp.scr_table + " group by " +
											 * tmp.relation_condition + ") x," +
											 * "(select " +
											 * tmp.relation_condition +
											 * ",count(1) dst_num from " +
											 * tmp.dst_table + " group by " +
											 * tmp.relation_condition +
											 * ") y,dmtool.mig_enum_mapping z where lower(x."
											 * + tmp.relation_condition +
											 * ")=lower(z.src_enum) and lower(y."
											 * + tmp.relation_condition +
											 * ")=lower(z.dst_enum) and lower(z.entity)=lower('"
											 * + user +
											 * "') and lower(z.audit_item)=lower('"
											 * + tabname + "');commit"; }
											 * 
											 * tmp.sql = str; } } else {
											 * "insert"
											 * .equals(str.trim().substring(0,
											 * 5) .toLowerCase());
											 * 
											 * stmt_tpl.setString(1, tmp.db);
											 * stmt_tpl.setString(2,
											 * tmp.audit_name);
											 * stmt_tpl.setString(3,
											 * tmp.audit_name);
											 * stmt_tpl.setString(4, tmp.sql);
											 * stmt_tpl.setString(5,
											 * tmp.author);
											 * stmt_tpl.setString(6,
											 * String.valueOf(tmp.audit_id));
											 * stmt_tpl.setString(7,
											 * tmp.remark); stmt_tpl.addBatch();
											 * 
											 * stmt_ins.setString(1, tmp.db);
											 * stmt_ins.setString(2,
											 * tmp.audit_name);
											 * stmt_ins.setString(3,
											 * String.valueOf(tmp.audit_id));
											 * stmt_ins.setString(4,
											 * tmp.sql_rep);
											 * stmt_ins.setString(5,
											 * tmp.author); stmt_ins.addBatch();
											 * }
											 */
				} else if (tmp.type.equals("L")) {
					stmt_aud.setInt(1, tmp.audit_id);
					String domain = tmp.audit_name.substring(11,
							tmp.audit_name.lastIndexOf('_'));
					if (domainHash.containsKey(domain))
						stmt_aud.setInt(2, domainHash.get(domain));
					else{
						throw new Exception(domain+" is not exists in system domains!");
//						 ++fail;
//						 continue;
					   }

					stmt_aud.setString(3, tmp.priority);
					stmt_aud.setString(4, tmp.audit_name);
					stmt_aud.setString(5, tmp.sql);
					stmt_aud.setString(6, tmp.audit_value);
					stmt_aud.setString(7, tmp.audit_flag);
					stmt_aud.setString(8, tmp.invalid_sql);
					stmt_aud.setString(9, tmp.operator);
					stmt_aud.setString(10, tmp.audit_unit);
					stmt_aud.setString(11, tmp.author);
					stmt_aud.setString(12, "1|@{DIP}|mig123|mig123|test|@{PORT}");
//					stmt_aud.setString(12, "1|@{IP}|@{USER}|@{PASSWD}|@{DB}|@{PORT}");
					stmt_aud.setString(13, tmp.sql_rep);
					stmt_aud.setString(14, tmp.remark);
					stmt_aud.setString(15, tmp.version);
					stmt_aud.addBatch();
				} else {
					throw new Exception("type should be C or L!");
				}
			}
			// stmt_tpl.executeBatch();
			// stmt_ins.executeBatch();
			stmt_aud.executeBatch();
			// stmt_tpl.close();
			// stmt_ins.close();
			stmt_aud.close();
		} catch (Exception ex) {
			log.error(ex.getLocalizedMessage()+": audit_id is " + aud.audit_id);
			log.error("Insert data error!");
			ex.printStackTrace();
			throw new BusinessException("Insert data error!"+ex.getLocalizedMessage() );
		}
		if(fail!=0){
			log.warn(fail+" number of item failed!");
		}
	}

	public void clear_data_from_db() {
		String all_ids_int = "";
		String all_ids_str = "";

		for (Audit tmp : this.list) {
			all_ids_int = all_ids_int + "," + tmp.audit_id;
			all_ids_str = all_ids_str + ",'" + tmp.audit_id + "'";
		}
		all_ids_int = all_ids_int.substring(1);
		all_ids_str = all_ids_str.substring(1);
		try {
			String sql = "delete a FROM mig_auditv_config a WHERE a.audit_id in ("
					+ all_ids_int + ")";
			executeDelete(sql);
			/*
			 * sql = "delete a FROM mig_trans_tpl a WHERE a.tpl_id in (" +
			 * all_ids_str + ")"; executeDelete(sql); sql =
			 * "delete a FROM mig_trans_instance a WHERE a.mig_tab in (" +
			 * all_ids_str + ")"; executeDelete(sql);
			 */
		} catch (Exception ex) {
			log.error(ex.getLocalizedMessage());
			log.error("Clear db data error!");
			ex.printStackTrace();
			throw new BusinessException("Clear db data error!"+ex.getLocalizedMessage() );
		}
	}

	public String getContent(Sheet sht, int col, int row) {
		String result;
		try {
			result = sht.getCell(col, row).getContents().trim();
		} catch (Exception e) {
			result = "";
		}
		return result;
	}

	public void getDict() {
		String sql = "SELECT code,value FROM tool.mig_codedetail_define where TYPE='Busi-Domain'";
		try {
			ResultSet rs = conn.prepareStatement(sql).executeQuery();
			while (rs.next())
				domainHash.put(rs.getString(2), Integer.parseInt(rs.getString(1)));

		} catch (Exception ex) {
			log.info(ex.getLocalizedMessage());
			log.error("read mig_codedetail_define failed!");
			throw new BusinessException("Read mig_codedetail_define failed!");
		}
	}

	public static void main(String[] args) {
 
		/*
		 * 
		 * String IP, String port, String db, String user, String passwd, String
		 * fileName
		 */
		Connection conn = null;
		try{
			/*
			 * Agr&Res&Sales-Audit.xls
			 * Audit_R&Dv2.xls
			 * Audit_template_billing_V6.5.xls
			 * Audit_template_c2b_V6.5.xls
			 * Audit_template_Voucher&SFF&Partner.xls
			 * Audit_template-Cust.xls
			 */
			String inputFilePath ="F:\\asiainfo\\现场迁移工作\\移网项目\\Legility audit\\";
//			String inputFileName ="Agr&Res&Sales-Audit.xls";
			String inputFileName ="Audit_R&Dv2.xls";
//			String inputFileName ="Audit_template_billing_V6.5.xls";
//			String inputFileName ="Audit_template_c2b_V6.5.xls";
//			String inputFileName ="Audit_template_Voucher&SFF&Partner.xls";
//			String inputFileName ="Audit_template-Cust.xls";
			
			
//			conn = getJDBCConnection("localhost", "3306", "tool", "root",
//					"123");
			conn = getJDBCConnection("10.1.249.100", "3306", "tool", "migdb",
					"migdb");
					
			conn.setAutoCommit(false);
			
			Audit_Load load = new Audit_Load( conn, inputFilePath+inputFileName, "daizd");
			load.getDict();

			load.get_excel_data();
			load.clear_data_from_db();
			load.insert_to_db();
			
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