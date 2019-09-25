package person.daizhongde.migration.spring.jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.junit.Test;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import org.jsoup.Connection.Method;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import person.daizhongde.authority.hibernate.pojo.TAuthorityUser;
import person.daizhongde.virtue.interact.BackendInfo;

/**
 * 把从网络获取的亚信员工信息写入数据库
 * 
 * http:
 * 
 * 软开
 * http://mail.copote.com/cgi-bin/laddr_biz?
 * t=memtree
 * &limit=500
 * &partyid=8025053
 * &action=show_party
 * &sid=d-BWoyXVVOIpAip2,2
 * 
 * 没有挂到部门下的员工（挂在公司下的）：
 * http://mail.copote.com/cgi-bin/laddr_biz?
 * t=memtree
 * &limit=500
 * &partyid=8020083
 * &action=show_party
 * &sid=d-BWoyXVVOIpAip2,2
 * 
 * http://mail.copote.com/cgi-bin/laddr_biz?t=memtree&limit=500&action=show_party&sid=d-BWoyXVVOIpAip2,2&partyid=8020083
 * 
 * 
1、单击通讯录
2、单击企业地址本
3、按键F12
4、单击高管（其它部门也行）
5、单击laddr_biz开头的地址
6、标头-请求标头-Cookie->右键->复制值
 * 
 */
@SuppressWarnings("unchecked")
public class HttpResponse2DB_copote {

	private static String Referer="http://mail.copote.com/";
//	private static Map<String, String> cookies;
	private static String  cookies_s="CCSHOW=0000; qm_authimgs_id=0; qm_verifyimagesession=h019cebaaf9b5e3ee06f85a486d0e7bb14430377e56cf49a49c818516c5c7af2f540c98feef988afc58; qqmail_alias=daizhongde@copote.com; tinfo=1560990491.0000*; qm_flag=0; qqmail_alias=daizhongde@copote.com; sid=683050631&68d2a8b93ff7067a8d5265b1e1b79b2a,c2WYKorCuMwI.; qm_sid=68d2a8b93ff7067a8d5265b1e1b79b2a,c2WYKorCuMwI.; qm_username=683050631; biz_username=683050631; ssl_edition=sail.qq.com; username=683050631&683050631; qylevel=3; new_mail_num=683050631&48; qm_sk=683050631&fLbQO-Ct; qm_ssum=683050631&0c9d828832b6f4850c493e0c67f77d34";
	
	private String loginURL1="http://mail.copote.com/cgi-bin/login";
	private String searchUrl;
	private org.jsoup.Connection jsoupConn2;
	
	private static Vector<String> columnName;
	private static String targetTableName = "t_copote_employee_1";
	private static String createTableSQL = "";
	private static String insertSQL = "";

	
	private static String address = "http://mail.copote.com/cgi-bin/laddr_biz"
			+ "?t=memtree&limit=500&action=show_party&sid=d-BWoyXVVOIpAip2,2&partyid=";

//	private static String url = "jdbc:mysql://localhost/tool?charset=utf-8&user=root&password=123";
	private static String url = "jdbc:mysql://rm-wz92qg5g95kqq74xto.mysql.rds.aliyuncs.com:3306/tool?charset=utf-8&useSSL=true&user=root&password=nStamp_2017";
	private static Connection conn;

	public static Connection getMySQLJDBCConnection(String url, String username, String password) throws Exception {
		String driver = "com.mysql.jdbc.Driver";
		Class.forName(driver);
		if (null == username || username.equalsIgnoreCase("")) {
			return DriverManager.getConnection(url);
		}
		return DriverManager.getConnection(url, username, password);
	}

	static int length = 194;// 182|194
							// 182只有pageIndex=0&pageSize=10&key_mobile=%25(没有sortField和sortOrder) 
//	static Map params = new HashMap();
	static Map propertys = new HashMap();
	
	public HttpResponse2DB_copote(     ) throws IOException {
//		this.loginURL1 = loginURL1;
//		this.searchUrl = searchUrl;
//		this.Referer = Referer;
		
//		this.targetTableName = targetTableName;
				
//		Authenticator.setDefault(new NTAuthenticator());
//		org.jsoup.Connection jsoupConn1 = Jsoup.connect(loginURL1);
////		Response resp1 = jsoupConn1.ignoreContentType(true).execute();
//		
////		cookies = resp1.cookies(); 
////		cookies = Cookie2Map.cookie2Map(cookies_s);
//		jsoupConn2 = Jsoup.connect(searchUrl);
//		
//		jsoupConn2.userAgent("Mozilla/5.0 (Windows NT 10.0; WOW64; rv:45.0) Gecko/20100101 Firefox/45.0")
//		.ignoreContentType(true)
//		.method(Method.GET)
//		.header("Referer",Referer)
//		.header("Host", "mail.copote.com")
//		.header("Cookie",cookies_s)
////		.header("X-Requested-With", "XMLHttpRequest")
////		.data(params)
//		.timeout(30000);
//		
////		jsoupConn2.cookies(cookies);
	}
	/**
	 * 更新员工数据步骤
	 * 1、爬虫数据覆盖 tool.t_copote_employee_1
	 * 2、删除 tool.t_copote_employee_1中这两条错误数据 uin IN (2611879785,2618234849)
	 * 3、把新入职的员工写入员工表
	 * 4、把员工表中增量更新的写入用户表
	 * @param jdbcurl 
	 *           jdbc:mysql://localhost/tool?charset=utf-8&user=root&password=123
	 *           jdbc:mysql://rm-wz92qg5g95kqq74xto.mysql.rds.aliyuncs.com:3306/tool?charset=utf-8&useSSL=true&user=root&password=nStamp_2017
	 *           
	 * @param deptIdList 部门在网页中是静态变量
	 * @param cookie
	 * @throws Exception 
	 */
	public int updateEmployee(String jdbcurl, List<Long> deptIdList, String cookie, TAuthorityUser user ) throws Exception{

		BackendInfo.msg.put( user.getCUemail(), "开始更新员工数据...");
		
		HttpResponse2DB_copote.cookies_s=cookie;
		int ret = -1;
		try {
//			1、爬虫数据覆盖 tool.t_copote_employee_1
			String Referer = "http://mail.copote.com/";
			String searchUrl = address;


			BackendInfo.msg.put( user.getCUemail(), "获取数据格式...");
			/* 使用软开部获取列名 */
			JSONArray json = this.fetch(0, 8025053L);
			BackendInfo.msg.put( user.getCUemail(), "重建员工临时表...");
			
			conn = getMySQLJDBCConnection(jdbcurl, "", "");
			this.DynamicCreateDBTable( json.getJSONObject(0));

			conn.setAutoCommit(false);

			int deptCount = deptIdList.size();
			System.out.println("共" + deptCount + "个部门，分" + deptCount + "次查询！");
			Date beginTime = new Date();
			for (int i = 0; i < deptCount ; i++) {
				BackendInfo.msg.put( user.getCUemail(), "共"+deptCount+"个部门，开始获取 第"+i+"个部门的数据...");
				this.writeFixRow2DynamicTable(this.fetch(i, deptIdList.get(i)), i, deptIdList.get(i));
				conn.commit();
//				if (i == 2) break;// 测试用
			}
			BackendInfo.msg.put( user.getCUemail(), "共"+deptCount+"个部门，开始获取 第"+deptCount+"个部门的数据获取完成！");
			Date endTime = new Date();
			long diff = endTime.getTime() - beginTime.getTime();// 这样得到的差值是微秒级别
			long days = diff / (1000 * 60 * 60 * 24);
			long hours = (diff - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
			long minutes = (diff - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)) / (1000 * 60);
			long seconds = (diff - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60) - minutes * (1000 * 60))
					/ 1000;
			// System.out.println(""+days+"天"+hours+"小时"+minutes+"分");
			System.out.println(
					" use time: " + days + " day " + hours + " hour " + minutes + " minute " + seconds + " seconds");

			BackendInfo.msg.put( user.getCUemail(), "清洗数据......");
//			2、删除 tool.t_copote_employee_1中这两条错误数据 uin IN (2611879785,2618234849)
			longHaul("DELETE FROM tool.t_copote_employee_1 WHERE uin IN (2611879785,2618234849) and pid=8020083");
			
			BackendInfo.msg.put( user.getCUemail(), "更新员工表......");
//			3、把新入职的员工写入员工表
			longHaul("INSERT INTO tool.t_copote_employee ("
					+ "`uin`, `pid`, `name`, `alias`, "
					+ "`sex`, `pos`, `tel`, `birth`, `slave_alias`, "
					+ "`department`, `mobile`, "
					+ "`employee_no`, `employee_cardno`, `employee_idcard`, "
					+ "`logname`, `qq`) "
					+ "SELECT `uin`, `pid`, `name`, `alias`, "
					+ "			`sex`, `pos`, `tel`, `birth`, `slave_alias`, "
					+ "			`department`, `mobile`, "
					+ "			'' employee_no, '' employee_cardno,'' employee_idcard,"
					+ "			SUBSTRING_INDEX(t.alias,\"@\", 1) logname,'' qq"
					+ "  FROM tool.t_copote_employee_1 t"
					+ " WHERE t.uin NOT IN ("
					+ "        SELECT uin FROM tool.t_copote_employee"
					+ " )"
					+ " ");

			BackendInfo.msg.put( user.getCUemail(), "更新用户表......");
//			4、把员工表中增量更新的写入用户表
			ret = longHaul2("INSERT INTO `t_authority_user`(`N_UID`, `C_ULOGNAME`, `C_UNAME`, `C_UPASSWORD`, `C_USEX`,"
					+ " `EMPLOYEE_NUMBER`, `SUPERVISOR_ID`, `C_UPHONE`, `C_UTEL`, `C_UFAX`, "
					+ " `C_UEMAIL`, `C_UQQ`, `C_UADDR`, `C_UNOTE`, `N_IID`, "
					+ " `N_DID`, `C_UCTIME`, `C_UCIP`, `C_UCREATOR`, `C_UMTIME`, "
					+ " `C_UMIP`, `C_UMODIFIER`, `C_UONS`, `C_UONT`, `C_LOGINS`, "
					+ " `C_LOGINIP`, `C_LOGINT`, `C_LOGOUTT`)"
					+ "SELECT u.uin, SUBSTRING_INDEX(u.alias,\"@\", 1) logname,u.name, 'C984AED014AEC7623A54F0591DA07A85FD4B762D',"
					+ "       CASE sex  WHEN '1' THEN '男' WHEN '2' THEN '女' END C_USEX, "
					+ "			'', NULL,u.mobile, u.tel,'',"
					+ "			u.alias, '', '', '', d.N_ISUPERIOR,"
					+ "			u.pid,'2019-05-03 22:20:00',NULL,NULL,NULL,"
					+ " 		NULL,NULL,'1',NULL,'0',"
					+ " 		NULL,NULL,NULL"
					+ "  FROM tool.t_copote_employee u "
		   + "LEFT OUTER JOIN tool.t_authority_inst d"
					+ "    ON u.pid=d.n_iid"
					+ " WHERE u.uin NOT IN ("
					+ "        SELECT N_UID FROM tool.t_authority_user"
					+ " )");
			/* 2，3，4一起提交 */
			conn.commit();
			BackendInfo.msg.put( user.getCUemail(), "更新员工数据完成！");
		}catch(MySQLIntegrityConstraintViolationException e){

			BackendInfo.msg.put( user.getCUemail(), "更新员工数据出错<Duplicate entry>！");
			try {
				conn.rollback();
			} catch (SQLException e1) {
				System.out.println("数据库回滚失败！");
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			throw e;
		} catch (Exception e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			BackendInfo.msg.put( user.getCUemail(), "更新员工数据出错！");
			try {
				conn.rollback();
			} catch (SQLException e1) {
				System.out.println("数据库回滚失败！");
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			throw e;
		} finally {
			try {
				conn.setAutoCommit(true);
				conn.close();
			} catch (SQLException e) {
				System.out.println("关闭连接时出错！");
			}
		}
		return ret;
	}
	@Test
	public void junitTestMain1() {
		
		System.out.println("junitTestMain1");
		String url="jdbc:mysql://localhost:3306/tool?useUnicode=true&charset=utf-8";
		String dbuser="root";
		String dbpass="123";
		String jdbcurl = url+"&user="+dbuser+"&password="+dbpass;
		
		Long[] arr= {
				8024960L,8025033L,8025038L,8025027L,8025047L,
				8025053L,8025063L,1102011041L,9580384L,8030576L};
		List<Long> l = Arrays.asList(arr);
		
		String cookies ="CCSHOW=0000; qm_authimgs_id=1; qm_verifyimagesession=h01737d1aa54a74c8ccc286ad8abacf286eb701ac753b4e4ff7144e780e8428d30097e6c0594712a73c; qqmail_alias=daizhongde@copote.com; tinfo=1565444248.0000*; qm_flag=0; qqmail_alias=daizhongde@copote.com; sid=683050631&ea565d30d7490670c474a122134feed6,c2WYKok7jUN0.; qm_sid=ea565d30d7490670c474a122134feed6,c2WYKok7jUN0.; qm_username=683050631; biz_username=683050631; ssl_edition=sail.qq.com; username=683050631&683050631; pcache=5814fa06ef1bb81MTU2ODAzNjI0OA@683050631@2; qylevel=3; qm_sk=683050631&yKamb4b5; qm_ssum=683050631&075beaeb8cefc1bd97ae67e36f9116cc";
		
		int affectrow=-1;
		try{
			TAuthorityUser user= new TAuthorityUser();
			user.setCUemail("daizhongde@copote.com");
			affectrow = this.updateEmployee(jdbcurl, l, cookies, user);
		}catch(Exception e){
			Throwable e2 = e;
			while(e2.getCause() != null ){
				e2 = e2.getCause();
			}
			System.out.println("{success: false, msg:  affectrow:-1,\""+e2.getLocalizedMessage()+"\"}" );
			 
		}
		System.out.println("{success: true, msg: 'Update Success! Update rows num:"+affectrow+"'}" );
		 
	}
	
	
	public static void main(String[] args) {
		try {
			if(true)return;
			String reqURL1 = "http://mail.copote.com/cgi-bin/login";
//			String Referer = "http://mail.copote.com/";
//			String searchUrl = address;

			HttpResponse2DB_copote w2db = 
					new HttpResponse2DB_copote(  );
			/* 使用软开部获取列名 */
			JSONArray json = w2db.fetch(0, 8025053L);			
			
			conn = getMySQLJDBCConnection(url, "", "");
			w2db.DynamicCreateDBTable( json.getJSONObject(0));

			/* 湘邮科技员工少，按部门查就好 (部门在html里面是写死的)， 不需要分页 */
			Long[] arr= {8020083L,
					8024960L,8025033L,8025038L,8025027L,8025047L,
					8025053L,8025063L,1102011041L,9580384L,8030576L};
			conn.setAutoCommit(false);

			System.out.println("共" + arr.length + "个部门，分" + arr.length + "次查询！");
			Date beginTime = new Date();
			for (int i = 0; i < arr.length ; i++) {
				// w2db.writeFixRow2FixTable( i, pageSize);
				w2db.writeFixRow2DynamicTable(w2db.fetch(i, arr[i]), i, arr[i]);
				conn.commit();
//				if (i == 2) break;// 测试用
			}
			Date endTime = new Date();
			long diff = endTime.getTime() - beginTime.getTime();// 这样得到的差值是微秒级别
			long days = diff / (1000 * 60 * 60 * 24);
			long hours = (diff - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
			long minutes = (diff - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)) / (1000 * 60);
			long seconds = (diff - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60) - minutes * (1000 * 60))
					/ 1000;
			// System.out.println(""+days+"天"+hours+"小时"+minutes+"分");
			System.out.println(
					" use time: " + days + " day " + hours + " hour " + minutes + " minute " + seconds + " seconds");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				System.out.println("数据库回滚失败！");
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			try {
				conn.setAutoCommit(true);
				conn.close();
			} catch (SQLException e) {
				System.out.println("关闭连接时出错！");
			}
		}
	}

	public JSONArray fetch(int pageIndex, Long dept) throws IOException {

		System.out.println("开始获取第"+pageIndex+"页，部门<"+dept+">数据...");
		Map<String, String> params = new HashMap<String, String>();
		/* 请求正文  */
//		params.put("key_sbu", "");
		
		org.jsoup.Connection jsoupConn2 = Jsoup.connect(address+dept);
		jsoupConn2.userAgent("Mozilla/5.0 (Windows NT 10.0; WOW64; rv:45.0) Gecko/20100101 Firefox/45.0")
		.ignoreContentType(true)
		.method(Method.POST)
		.header("Accept","*/*")
		.header("Accept-Encoding","gzip, deflate")
		.header("Accept-Language","zh-CN,zh;q=0.9,en;q=0.8")
		.header("Connection","keep-alive")
		.header("Cookie",cookies_s)
		.header("Acceptncoding","gzip")

		.header("Host", "mail.copote.com")
		.header("Referer",Referer)
		.header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.86 Safari/537.36")
		.data(params)
		.timeout(30000);
		
//		jsoupConn2.cookies( cookies );
		
//		jsoupConn2.data("pageIndex", String.valueOf(pageIndex));
//		jsoupConn2.data("pageSize", String.valueOf(pageSize));
		String str_res = jsoupConn2.post().body().text();
		System.out.println("restr:"+str_res);
		
		System.out.println("获取第"+pageIndex+"页，部门<"+dept+">数据成功！");
				
		str_res = str_res.replace("({", "{");
		str_res = str_res.replace("})", "}");
		JSONObject source = JSONObject.fromObject(str_res);
		JSONObject data = source.getJSONObject("data");
		JSONArray oUserList = data.getJSONArray("oUserList");
		return oUserList;
	}

	/**
	 * CREATE TABLE `t_copote_employee_1` ( `sbu_id` char(3) DEFAULT NULL, `sbu`
	 * varchar(100) DEFAULT NULL, ......... PRIMARY KEY (`employee_number`),
	 * UNIQUE KEY `UK_person_id` (`person_id`), UNIQUE KEY `UK_nt_account`
	 * (`nt_account`) )
	 * 
	 * insert into `t_authority_employee` (`sbu_id`, `sbu`, `company_id`,
	 * `company`,`organization_id`, `org_name`, `office`, `pager`, `person_id`,
	 * `employee_number`, `first_name`, `last_name`, `full_name`,
	 * `email_address`,`age`, `assignment_id`, `birth_date`, `class`,
	 * `working_location`, `seat_no`, `mobile`,`nt_account`, `supervisor_id`,
	 * `supervisor_name`, `highest_degree`, `hire_date`)
	 * values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)
	 * 
	 * @param row
	 * @throws SQLException
	 */
	public void DynamicCreateDBTable(JSONObject row) throws SQLException {
		StringBuilder createSB = new StringBuilder("CREATE TABLE " + targetTableName + " ( ");
		StringBuilder insertSB = new StringBuilder("insert into " + targetTableName + " ( ");
		StringBuilder insertSBValues = new StringBuilder("values(");

		@SuppressWarnings("unchecked")
		Set<String> set = row.keySet();
		Iterator<String> it = set.iterator();
		columnName = new Vector<String>();
		while (it.hasNext()) {
			String col = it.next().toString();
			if (col.indexOf("date") != -1) {// 日期列
				createSB.append(col + " datetime DEFAULT NULL,");
			} else {
				createSB.append(col + " varchar(100) DEFAULT NULL,");
			}

			insertSB.append(col + ",");
			insertSBValues.append("?,");
			columnName.add(col);
		}
		createTableSQL = createSB.toString().replaceAll(",$", ")");
		insertSQL = insertSB.toString().replaceAll(",$", ")");
		insertSQL = insertSQL + insertSBValues.toString().replaceAll(",$", ")");
		System.out.println("createTableSQL:" + createTableSQL);
		System.out.println("insertSQL:" + insertSQL);
		// 表名如果存在就先drop
		longHaul("DROP TABLE IF EXISTS " + targetTableName);

		// 创建表
		longHaul(createTableSQL);
	}

	/**
	 * 
	 * @param pageIndex
	 *            start from 0
	 * @param dept 部门id
	 */
	public void writeFixRow2DynamicTable(JSONArray array, int pageIndex, Long dept) {
		try {


			PreparedStatement stmt = null;
			stmt = conn.prepareStatement(insertSQL);

			System.out.println("开始插入第" + pageIndex + "页，部门<"+dept+">数据...");
			// 1963-01-01T00:00:00
			SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd H:m:s");

			for (int i = 0, j = array.size(); i < j; i++) {
				JSONObject e = array.getJSONObject(i);
				// 如果用e的key去循环可以会与insert语句的列对应不上
				for (int m = 1, n = columnName.size(); m <= n; m++) {
					String col = columnName.get(m - 1);
					String value = e.getString(col);
					if (col.indexOf("date") != -1) {// 日期列
						if (StringUtils.isEmpty(value) || "null".equalsIgnoreCase(value))
							stmt.setNull(m, Types.DATE);
						else {
							String birth_date = value;
							birth_date = birth_date.replace("T", " ");
							stmt.setDate(m, new java.sql.Date(dateformat.parse(birth_date).getTime()));
						}
					} else {
						if (StringUtils.isEmpty(value) || "null".equalsIgnoreCase(value))
							stmt.setNull(m, Types.VARCHAR);
						else {
							if (value.length() > 100) {// 因为列长为100，所以这里需要做一个小小的监控
								System.out.println("colName:" + col + ",value:" + value + ", length > 100");
							}
							stmt.setString(m, value);

						}
					}
				}
				stmt.addBatch();
			}

			stmt.executeBatch();
			stmt.close();

			System.out.println("插入第" + pageIndex + "页，部门<"+dept+">数据成功！");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 插入、修改、删除记录
	protected static boolean longHaul(String sql) throws SQLException {
		System.out.println(sql);
		boolean isLongHaul = true;// 默认持久化成功
		SQLException e3 = null;
		try {
//			conn.setAutoCommit(false);// 设置为手动提交
			Statement stmt = conn.createStatement();// 创建连接状态对象
			stmt.executeUpdate(sql);// 执行SQL语句
			stmt.close();// 关闭连接状态对象
//			conn.commit();// 提交持久化
		} catch(MySQLIntegrityConstraintViolationException e){
			isLongHaul = false;// 持久化失败
			throw e;
		}catch (SQLException e) {
			isLongHaul = false;// 持久化失败
//			try {
//				conn.rollback();// 回滚
//			} catch (SQLException e1) {
//				e1.printStackTrace();
//			}
			e.printStackTrace();
			e3 = e;
		}
		if (e3 != null) {
			throw e3;
		}
		return isLongHaul;// 返回持久化结果
	}
	// 插入、修改、删除记录
		protected static int longHaul2(String sql) throws SQLException {
			System.out.println(sql);
			int isLongHaul = -1;// 默认持久化成功
			SQLException e3 = null;
			try {
//				conn.setAutoCommit(false);// 设置为手动提交
				Statement stmt = conn.createStatement();// 创建连接状态对象
				isLongHaul = stmt.executeUpdate(sql);// 执行SQL语句
				stmt.close();// 关闭连接状态对象
//				conn.commit();// 提交持久化
			} catch (SQLException e) {
				isLongHaul = -1;// 持久化失败
//				try {
//					conn.rollback();// 回滚
//				} catch (SQLException e1) {
//					e1.printStackTrace();
//				}
				e.printStackTrace();
				e3 = e;
			}
			if (e3 != null) {
				throw e3;
			}
			return isLongHaul;// 返回持久化结果
		}
//	private static class NTAuthenticator extends Authenticator {
//		@Override
//		public PasswordAuthentication getPasswordAuthentication() {
//			return (new PasswordAuthentication("daizhongde@copote.com", "DZd123456".toCharArray() ));
//		}
//	}
}
