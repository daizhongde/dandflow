//package junit.test;
//
//import java.awt.Container;
//import java.io.File;
//import java.sql.Connection;
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.swing.JApplet;
//import javax.swing.JFrame;
//
//import person.daizhongde.virtue.jdbc.JDBCSpringConnection;
//import net.sf.jasperreports.engine.JRException;
//import net.sf.jasperreports.engine.JasperFillManager;
//
////import person.daizhongde.authority.util.DBConnectionBean;
////import person.daizhongde.authority.util.FilePathUtils;
////
////import net.sf.jasperreports.engine.JRException;
////import net.sf.jasperreports.engine.JasperFillManager;
////import net.sf.jasperreports.view.JRViewer;
//
//public class J_Report1 extends JApplet {
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = -3535205052657002566L;
//	JDBCSpringConnection db = new JDBCSpringConnection();
//
//	Connection conn = db.getConnection();
//
//	public void init() {
//		System.out.println("hello world!");
//		// 锟斤拷锟斤拷一锟斤拷JasperReport锟斤拷锟斤拷
//		// JasperReport jasperReport =
//		// (JasperReport)JRLoader.loadObject(reportFile.getPath());
//		Map params = new HashMap();
//		params.put("author", "李刚");
//		params.put("book1", "Spring2.0疯狂讲义");
//		params.put("book2", "J2EE精通");
//		params.put("book3", "J2EE与Ajax讲义");
//
//		try {
////			FilePathUtils fp = new FilePathUtils();
////			File reportFile = new File(fp.getWebRoot()
////					+ "jasper/onlinechartByDay.jasper");
////
//			JasperFillManager.fillReportToFile(
//					"D:\\workdir\\validation.jasper", params, conn);
//		} catch (JRException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
////
////		db.closeConnection(conn);
////
//		Container container = getContentPane();
////		JRViewer jr = null;
////		try {
////			jr = new JRViewer("D:\\workdir\\onlinechartByYear.jrprint", false);
////		} catch (JRException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
//		JFrame jf = new JFrame("好东东");
////		jf.add(jr);
//		jf.pack();
//		jf.setVisible(true);
//		container.add(jf);
//
//	}
//	 public static void main(String[] args) throws Exception
//    {
//	 J_Report1 jr = new J_Report1();
//	 jr.init();
//    }
//}
