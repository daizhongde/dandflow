package junit.test.java2word;

import org.apache.log4j.Logger;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.*;
import org.apache.poi.hwpf.model.*;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * 用POI读Word
 * @author daizd
 * @date 2019年4月26日
 */
public final class Word2Forrest {
	private static final Logger log = Logger.getLogger(Word2Forrest.class); 
	private String qType = null, qTypeName = null, content = null,chapterName = null;

	String QueryStr = " select max(id) from question ", hql="update Question set answer = ? where id = ?";
    Integer id, begin_id, answer_id, chapterNo = null;
    int answer_Flag = 0;
    
	public static void main(String args[]) throws Exception{
		//这个类是把试题以doc格式导入到数据库
		
	}
	
	/**
	 * 构造函数，初始化银行返回码，名值映射map
	 */
	public Word2Forrest() {

	}

	public void word2DB(HWPFDocument doc, Integer courseId) throws Exception{
//		init();
//		openDocument();
//		openBody(); 

		Range r = doc.getRange();
		StyleSheet styleSheet = doc.getStyleSheet();

		int sectionLevel = 0;
		int lenParagraph = r.numParagraphs();
		boolean inCode = false;

		System.out.println("r.numParagraphs ():" + lenParagraph);// ---------------

		QuestionDao questionDao = new QuestionDao();
		
		for (int x = 0; x < lenParagraph; x++) { 
			Paragraph p = r.getParagraph(x);

			String text = p.text();
			if (text.trim().length() == 0) {
				continue;
			}
			StyleDescription paragraphStyle = styleSheet.getStyleDescription(p
					.getStyleIndex());
			String styleName = paragraphStyle.getName();
			System.out.println((x + 1) + "-------------------------------\n"
					+ "paragraphStyle.getBaseStyle():"
					+ paragraphStyle.getBaseStyle());

			int level, ilevel, type, justy;
			level = p.getLvl();
			ilevel = p.getIlvl();
			type = p.TYPE_TEXT;
			justy = p.getJustification();
			System.out.println("p.getLvl():" + level + "  ilevel:" + ilevel
					+ "  type:" + type + "  p.getJustification():"
					+ p.getJustification());
			System.out.println("p.getTableLevel():" + p.getTableLevel()
					+ "  p.getStyleIndex():" + p.getStyleIndex());
			System.out.println("p.numParagraphs():" + p.numParagraphs()
					+ "  p.numSections():" + p.numSections());

			System.out.println("styleName:" + styleName);// -----------------
			try{
				switch(answer_Flag){
				case 0: 
					if (level != 9) {
						//保存上一qType的最后一题
						if(questionDao.text.length() != 0){
							questionDao.save(courseId, chapterNo,qType);// 保存
							questionDao.clean();// 清空
						}
						int headerLevel;
						headerLevel = level + 1;
						System.out.println("headerLevel:" + headerLevel
								+ ", sectionLevel:" + sectionLevel);// ---------------
	
						if (headerLevel == 2) {//2级标题取试题类型
							qTypeName = p.text().substring(0, 2);
							System.out.println("qTypeName:"+qTypeName); 
						}else if(headerLevel == 1){//1级标题取试卷名称
							if(text.indexOf("答案") > 0){
								answer_Flag = 1;
								//保存试卷最后一题(写试题表)
								if(questionDao.text.length() != 0){
									questionDao.save(courseId, chapterNo,qType);// 保存
									questionDao.clean();// 清空
								}
							}else{ 
							}
						}
					} else {
						System.out.println("questionDao.text:"+questionDao.text);
						System.out.println("justy:"+justy);
						if ((justy == 3 || type == 4) && questionDao.text.length() != 0) {//有编号的行先保存上一题
							questionDao.save(courseId, chapterNo,qType);// 保存
							questionDao.clean();// 清空
							questionDao.append(text);// 添加
						} else if(justy == 1){//取章节号
							chapterName = text.substring(text.indexOf("第")+1, text.indexOf("章"));
							System.out.println("chapterName:"+chapterName);
							if(!Pattern.matches("\\d{1,2}",chapterName)){ 
					        }
						} else {
							questionDao.append(text);
						}
					} break;
				case 1:
					if (level != 9) {//跳过
						continue;
					} else {
						if ((justy == 3 || type == 4) && questionDao.text.length() != 0) {
							questionDao.writeAnswer(answer_id);// 保存
							questionDao.clean();// 清空
							questionDao.append(text);// 添加
						} else {//比上面少了取章节号一说
							questionDao.append(text);
						}
					} break;
				}//end of switch
			}catch(Exception e){
				System.out.println("读word时出错，第<"+(x+1)+">行.");
				e.printStackTrace();
				throw e;
			}
		}//end of for
		//更新试卷最后一题的答案
		if(questionDao.text.length() != 0){
			questionDao.writeAnswer(answer_id);// 保存
			questionDao.clean();// 清空
		} 
	}

	public void init() {
		
		
		
//		com.lzw.dao.AdapterDao.InsertObj_Paper(paper);;
	}
	
	public void openDocument() throws IOException {
		// _out.write ("<document>\r\n");
	}

	public void closeDocument() throws IOException {
		// _out.write ("</document>\r\n");
	}

	public void openBody() throws IOException {
		// _out.write ("<body>\r\n");
	}

	public void closeBody() throws IOException {
		// _out.write ("</body>\r\n");
	}

	public void openSection() throws IOException {
		// _out.write ("<section>");

	}

	public void closeSection() throws IOException {
		// _out.write ("</section>");

	}

	public void openTitle() throws IOException {
		// _out.write ("<title>");
	}

	public void closeTitle() throws IOException {
		// _out.write ("</title>");
	}

	public void writePlainText(String text) throws IOException {
		// _out.write (text);
	}

	public void openParagraph() throws IOException {
		// _out.write ("<p>");
	}

	public void closeParagraph() throws IOException {
		// _out.write ("</p>");
	}

	public void openSource() throws IOException {
		// _out.write ("<source><![CDATA[");
	}

	public void closeSource() throws IOException {
		// _out.write ("]]></source>");
	}

//	public static void main(String[] args) {
//		try {
//			OutputStream out = new FileOutputStream("D:\\test.xml");
//
//			(new Word2Forrest()).word2DB(new HWPFDocument(new FileInputStream(
//					"F:\\技术积累\\apache\\POI\\试题-POI测试模板2.doc")), new Integer("1"));
//			out.close();
//		} catch (Throwable t) {
//			t.printStackTrace();
//		}
//	}
	/**
	 * 内部类，用来表示试题并对其进行操作
	 * @author dzd
	 *
	 */
	class QuestionDao {
		public Object[] values = new Object[2];
		public StringBuilder text = new StringBuilder("");

		public void save(Integer courseId, Integer  chapterNo, String qType) {
			System.out.println("正在导入........");
			System.out.println("课程ID："+courseId+" 试题类型："+qType+" 章节号："+chapterNo);
			System.out.println("-------------------------------");
			System.out.print(this.text);
			 
//			question.setId(id); ;
			
            
			//1 选择 ，2填空，3判断，4简答，5论述，6计算,7应用
			//'choice','fill','judge','brief','discuss','calculate','apply'
//			this.typeMap.put("选择", "choice");
//			this.typeMap.put("填空", "fill");
//			this.typeMap.put("判断", "judge");
//			this.typeMap.put("简答", "brief");
//			this.typeMap.put("论述", "discuss");
			System.out.println("-------------------------------");
			System.out.println("导入完成........");
		}
		
		public void writeAnswer(Integer id) {
			System.out.println("正在更新答案......");
			System.out.println("题号:"+id);
			System.out.println("-------------------------------");
			System.out.print(this.text);
			values[0] = this.text.toString();
			values[1] = answer_id;
			answer_id++;

			
			System.out.println("-------------------------------");
			System.out.println("更新完成........");
		}
		public void clean() {
			this.text = new StringBuilder("");
		}

		public StringBuilder append(String text) {
			this.text.append(text);
			return this.text;
		}
	}
}
