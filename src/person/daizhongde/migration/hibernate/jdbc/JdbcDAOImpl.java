//package person.daizhongde.migration.hibernate.jdbc;
//
//import java.io.File;
//import java.io.IOException;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Hashtable;
//import java.util.List;
//import java.util.Set;
//
//import org.hibernate.HibernateException;
//import org.hibernate.Session;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.orm.hibernate3.HibernateCallback;
//import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
//
//import person.daizhongde.migration.exception.BusinessException;
//import person.daizhongde.migration.hibernate.dto.Temp;
//
///**
// * This class unuse
// * @author daizd
// *
// */
//public class JdbcDAOImpl extends HibernateDaoSupport implements JdbcDAO {
//	private static final Logger log = LoggerFactory.getLogger(JdbcDAOImpl.class);
//	
//	/**
//		hibernate3
//		Connection conn = session.connection();
//		
//		hibernate4
//		SessionFactoryImplementor sessionFactory = (SessionFactoryImplementor)new Configuration().configure().buildSessionFactory();   
//		Connection conn = sessionFactory.getConnectionProvider().getConnection();
//		
//		hibernate4
//		ConnectionProvider cp =((SessionFactoryImplementor)session.getSessionFactory()).getConnectionProvider();  
//		Connection conn = cp.getConnection();  
//		
//		hibernate4
//		getSession().doWork(  
//				  new Work() {  
//				    public void execute(Connection connection) {  
//				      // 这里已经得到connection了，可以继续你的JDBC代码。  
//				      // 注意不要close了这个connection。  
//				    }  
//				  }  
//				);
//	 */
//	@SuppressWarnings({ "unchecked", "rawtypes" })
//	public void importJob(final File file, final String srcFileName,
//			final String _, final String jobId, final String jobName)
//			throws IndexOutOfBoundsException, IOException {
//
//		final String author = getContent(
//				Workbook.getWorkbook(file).getSheet(1), 0, 1);
//
//		/* hibernate3 HibernateCallback */
////		getHibernateTemplate().execute(new HibernateCallback() {
////			@SuppressWarnings("deprecation")
////			public Object doInHibernate(Session session)
////					throws HibernateException, SQLException {
////				
////				Connection conn = session.connection();
////				
////				conn.setAutoCommit(false);
//////				session.beginTransaction();
////				try{
////					getAndInsert(file, _, conn, jobId, jobName, author);
////					
//////					session.flush();
//////					session.close();
////					conn.commit();
////				}catch(BusinessException e){
////					conn.rollback();
////					throw e;
////				}finally{
////					conn.setAutoCommit(true);
////				}
////				return null;
////			}
////		});
//		
//		/* hibernate4 doWork*/
//		getHibernateTemplate().execute(new HibernateCallback() {
//			@SuppressWarnings("deprecation")
//			public Object doInHibernate(Session session)
//					throws HibernateException {
//				
//				session.doWork( new org.hibernate.jdbc.Work(){
//				    public void execute(Connection conn) throws SQLException {  
//				      // 这里已经得到connection了，可以继续你的JDBC代码。  
//				      // 注意不要close了这个connection。  
//						
//						try{
//							conn.setAutoCommit(false);
//							getAndInsert(file, _, conn, jobId, jobName, author);
//							
////							session.flush();
////							session.close();
//							conn.commit();
//						}catch( BusinessException e){
//							conn.rollback();
//							throw e;
////							throw new HibernateException(e.getLocalizedMessage());
//						}finally{
//							conn.setAutoCommit(true);
//						}				    	
//				    }  
//				  } );
//				return null;
//			}
//		});
//		
//		/* hibernate4 HibernateCallback*/
////		getHibernateTemplate().execute(new HibernateCallback() {
////			@SuppressWarnings("deprecation")
////			public Object doInHibernate(Session session)
////					throws HibernateException {
////
////				Connection conn = null;
////				try{
////					ConnectionProvider cp =((SessionFactoryImplementor)session.getSessionFactory()).getConnectionProvider();  
////					conn = cp.getConnection();
////					
////					conn.setAutoCommit(false);
//////					session.beginTransaction();
////				
////					getAndInsert(file, _, conn, jobId, jobName, author);
////					
//////					session.flush();
//////					session.close();
////					conn.commit();
////				}catch(BusinessException|SQLException e){
////					try {
////						conn.rollback();
////					} catch (SQLException e1) {
////						// TODO Auto-generated catch block
////						e1.printStackTrace();
////					}
////					throw new HibernateException(e.getLocalizedMessage());
////				}finally{
////					try {
////						conn.setAutoCommit(true);
////					} catch (SQLException e) {
////						// TODO Auto-generated catch block
////						throw new HibernateException(e.getLocalizedMessage());
////					}
////				}
////				return null;
////			}
////		});
//	}
//
//	private String getContent(Sheet sht, int col, int row) {
//		String result;
//		try {
//			result = sht.getCell(col, row).getContents().trim();
//		} catch (Exception e) {
//			result = "";
//		}
//
//		return result;
//	}
//
//	private void doJobInfo(Set set, Connection con, String jobId,
//			String jobName, String author) {
//		log.debug("entering doJobinfo..");
//		int i = 0;
//		PreparedStatement stmt = null;
//		String sql = "insert into mig_job_info(job_id,job_name,job_author,job_remark,job_update) values(?,'zwf_test',?,'zzzz',now());";
//		try {
//			stmt = con.prepareStatement(sql);
//
//			stmt.setString(1, jobId);
//			stmt.setString(2, author);
//			stmt.addBatch();
//			++i;
//
//			for (Object obj : set) {
//				String order = (String) obj;
//				stmt.setString(1, order);
//				stmt.setString(2, author);
//				stmt.addBatch();
//				++i;
//			}
//			stmt.executeBatch();
//			stmt.close();
//		} catch (Exception ex) {
//			ex.printStackTrace();
//			Throwable e2 = ex;
//			while(e2.getCause() != null ){
//				e2 = e2.getCause();
//			}
//			throw new BusinessException("Error when importing Job Information:"+e2.getLocalizedMessage());
//		}
//		log.info("finish doJobinfo..insert " + i + " records!");
//	}
//
//	private void doJobPara(String global, Connection con, String jobId,
//			String jobName, String author) throws Exception {
//		log.debug("entring doJobPara...!");
//		int i = 0;
//		String sql = "insert into mig_job_para(node_id,para,para_name,para_type,para_value) values(?,?,'zwf_test',1,?)";
//		PreparedStatement stmt = null;
//		try{
//			stmt = con.prepareStatement(sql);
//			String[] paras = {};
//			if (!global.trim().equals("")) {
//				paras = global.split(";");
//			}
//	
//			for (String str : paras) {
//				String[] para = str.split("=");
//				if (para.length == 0) {
//					continue;
//				}
//				List ls = new ArrayList();
//				if (para[1].indexOf("..") != -1) {
//					String[] tmp = para[1].split("..");
//					for (int j = Integer.parseInt(tmp[0]); j < Integer
//							.parseInt(tmp[1]) + 1; j++) {
//						ls.add(j);
//					}
//				} else if (para[1].indexOf(",") != -1) {
//					String[] tmp = para[1].split(",");
//					for (String s : tmp) {
//						ls.add(s);
//					}
//				} else {
//					ls.add(para[1]);
//				}
//	
//				for (Object obj : ls) {
//					String s = (String) obj;
//					stmt.setString(1, jobId);
//					stmt.setString(2, para[0]);
//					stmt.setString(3, s);
//					stmt.addBatch();
//					++i;
//				}
//	
//			}
//			stmt.executeBatch();
//			stmt.close();
//		}catch(Exception e){
//			e.printStackTrace();
//			Throwable e2 = e;
//			while(e2.getCause() != null ){
//				e2 = e2.getCause();
//			}
//			throw new BusinessException("Error while importing job parameters:"+e2.getLocalizedMessage());
//		}
//		log.info("finish doJobPara..insert " + i + " records!");
//
//	}
//
//	private void doJobContent(Hashtable hash, List leafList, Connection con,
//			String jobId, String jobName, String author) {
//
//		log.debug("entering doJobContent..");
//
//		int j = 0;
//
//		String sql = "insert into mig_job_content(job_id,node_id,isleaf,node_status,prepos,postpos) values(?,?,?,0,?,?)";
//		PreparedStatement stmt=null;
//		try {
//			stmt = con.prepareStatement(sql);
//
//			List<Integer> ls = new ArrayList<Integer>();
//
//			for (Object obj : hash.keySet()) {
//				int tmp = Integer.parseInt((String) obj);
//				ls.add(tmp);
//			}
//
//			int[] a = new int[ls.size()];
//
//			for (int i = 0; i < ls.size(); i++) {
//				a[i] = ls.get(i);
//			}
//
//			Arrays.sort(a);
//
//			for (int i = 0; i < a.length; i++) {
//				stmt.setString(1, jobId);
//				stmt.setString(2, a[i] + "");
//				stmt.setInt(3, 0);
//				stmt.setString(4, i - 1 < 0 ? "" : a[i - 1] + "");
//				stmt.setString(5, i + 1 >= a.length ? "" : a[i + 1] + "");
//				stmt.addBatch();
//				++j;
//
//				List list = (List) hash.get(a[i] + "");
//
//				for (Object o : list) {
//					String s = (String) o;
//					stmt.setString(1, a[i] + "");
//					stmt.setString(2, s);
//					stmt.setInt(3, 1);
//					stmt.setString(4, "");
//					stmt.setString(5, "");
//					stmt.addBatch();
//					leafList.add(s);
//					++j;
//				}
//			}
//
//			stmt.executeBatch();
//			stmt.close();
//		} catch (Exception ex) {
//			ex.printStackTrace();
//			Throwable e2 = ex;
//			while(e2.getCause() != null ){
//				e2 = e2.getCause();
//			}
//			throw new BusinessException("Error when importing job content:"+e2.getLocalizedMessage());
//		}
//
//		log.info("finish doJobContent....insert " + j + " records!");
//
//	}
//
//	private void doTaskInfo(List<String> ls, Connection con, String jobId,
//			String jobName, String author) throws Exception {
//		log.debug("entering doTaskInfo..");
//		int j = 0;
//		PreparedStatement stmt = null;
//		try{
//			
//			String sql = "insert into mig_task_info(task_id,task_name,task_author,task_update,control_id,com_id) values(?,'zwf_test',?,now(),'con005',?)";
//			stmt = con.prepareStatement(sql);
//	
//			for (int i = 0; i < ls.size(); i++) {
//				stmt.setString(1, ls.get(i));
//				stmt.setString(2, author);
//				stmt.setString(3, "c0" + i);
//				stmt.addBatch();
//				++j;
//			}
//			stmt.executeBatch();
//			stmt.close();
//		}catch(Exception ex){
//			ex.printStackTrace();
//			Throwable e2 = ex;
//			while(e2.getCause() != null ){
//				e2 = e2.getCause();
//			}
//			throw new BusinessException("Error when importing Job Information:"+e2.getLocalizedMessage());
//		}
//		log.info("finish doTaskInfo.....insert " + j + " records!");
//
//	}
//
//	private void doComInfo(Hashtable<String, Temp> tmpList,
//			List<String> leafList, Connection con, String jobId,
//			String jobName, String author) throws Exception {
//
//		log.debug("entering doComInfo..");
//		int j = 0;
//
//		PreparedStatement stmt = null;
//		
//		String sql = "insert into mig_com_info(com_id,para_id,para_value) values(?,?,?)";
//		
//		try{
//			
//			stmt = con.prepareStatement(sql);
//			for (int i = 0; i < leafList.size(); i++) {
//				Temp t = tmpList.get(leafList.get(i));
//	
//				stmt.setString(1, "c0" + i);
//				stmt.setInt(2, 1);
//				stmt.setString(3, "1");
//				stmt.addBatch();
//	
//				stmt.setString(1, "c0" + i);
//				stmt.setInt(2, 2);
//				stmt.setString(3, t.db);
//				stmt.addBatch();
//	
//				stmt.setString(1, "c0" + i);
//				stmt.setInt(2, 3);
//				stmt.setString(3, t.sql);
//				stmt.addBatch();
//	
//				stmt.setString(1, "c0" + i);
//				stmt.setInt(2, 4);
//				stmt.setString(3, t.replace);
//				stmt.addBatch();
//	
//				stmt.setString(1, "c0" + i);
//				stmt.setInt(2, 5);
//				stmt.setString(3, "8");
//				stmt.addBatch();
//				j += 5;
//			}
//			stmt.executeBatch();
//			stmt.close();
//		}catch(Exception ex){
//			ex.printStackTrace();
//			Throwable e2 = ex;
//			while(e2.getCause() != null ){
//				e2 = e2.getCause();
//			}
//			throw new BusinessException("Error when importing com information:"+e2.getLocalizedMessage());
//		}
//		log.info("finish doComInfo....insert " + j + " records!");
//	}
//
//	private void getAndInsert(File file, String _, Connection conn,
//			String jobId, String jobName, String author){
//		int i = 0;
//
//		try{
//			Workbook book = Workbook.getWorkbook(file);
//			Sheet[] sheetArray = book.getSheets();
//			if (sheetArray.length < 4) {
//				throw new BusinessException("Excel must have four sheet!");
//			}
//			Sheet sheetExecOrder = sheetArray[1];
//	
//			int status = 0;
//	
//			i = 3;
//			String stepId = "";
//			List<String> leafList;
//	
//			int rows = sheetExecOrder.getRows();
//			Hashtable orderHash = new Hashtable();
//			String order;
//	
//			Hashtable<String, Temp> tmpList = new Hashtable<String, Temp>();
//	
//			while (true) {
//				if (i + 1 > rows) {
//					break;
//				}
//	
//				if (getContent(sheetExecOrder, 2, i).trim().equals("")) {
//					break;
//				}
//	
//				order = getContent(sheetExecOrder, 1, i);
//				String step_id = getContent(sheetExecOrder, 2, i);
//	
//				Temp t = new Temp();
//				t.replace = getContent(sheetExecOrder, 4, i);
//				tmpList.put(step_id, t);
//	
//				if (!orderHash.containsKey(order)) {
//					List stepList = new ArrayList();
//					stepList.add(step_id);
//					orderHash.put(order, stepList);
//				} else {
//					((List) orderHash.get(order)).add(step_id);
//				}
//				i++;
//			}
//	
//			doJobInfo(orderHash.keySet(), conn, jobId, jobName, author);
//	
//			String global = getContent(sheetExecOrder, 1, 1).trim();
//			doJobPara(global, conn, jobId, jobName, author);
//	
//			leafList = new ArrayList<String>();
//			doJobContent(orderHash, leafList, conn, jobId, jobName, author);
//	
//			doTaskInfo(leafList, conn, jobId, jobName, author);
//	
//			log.debug("order deal complete");
//	
//			for (int idx = 3; idx < sheetArray.length; idx++) {
//				Sheet sheetMapping = sheetArray[idx];
//				i = 1;
//	
//				while (true) {
//					rows = sheetMapping.getRows();
//					if (i + 1 > rows) {
//						break;
//					}
//	
//					String tgtColumn = getContent(sheetMapping, 4, i);
//					String isRunSql = getContent(sheetMapping, 12, i);
//	
//					if ((tgtColumn.trim().equals(""))
//							&& (isRunSql.trim().equals(""))) {
//						break;
//					}
//	
//					if (getContent(sheetMapping, 0, i).trim() != "") {
//						Temp t = tmpList.get(getContent(sheetMapping, 0, i));
//						t.sql = getContent(sheetMapping, 13, i);
//						t.db = getContent(sheetMapping, 2, i);
//					}
//					i++;
//				}
//	
//			}
//	
//			doComInfo(tmpList, leafList, conn, jobId, jobName, author);
//	
//			book.close();
//		}catch(Exception e){
//			Throwable e2 = e;
//			while(e2.getCause() != null ){
//				e2 = e2.getCause();
//			}
//			throw new BusinessException("Error! Error while importing:"+e2.getLocalizedMessage());
//		}
//	}
//}
