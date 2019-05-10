package junit.test.java2word;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.LinkedHashMap;

import word.api.interfaces.IDocument;
import word.w2004.Document2004;
import word.w2004.Document2004.Encoding;
import word.w2004.elements.Heading1;
import word.w2004.elements.Heading2;
import word.w2004.elements.Paragraph;
import word.w2004.elements.ParagraphPiece;
import word.w2004.style.HeadingStyle.Align;
/**
 * java2word写 word
 * @author daizd
 * @date 2019年4月26日
 */
public class Forrest2Word {
	public static void main(String args[]) throws Exception{
		Forrest2Word f = new  Forrest2Word();
		f.export2Word("", null,"D:/test2.doc");
		
		
	}
	public void export2Word(String courseName, LinkedHashMap<String,Integer> lmap,String fpath) throws Exception{
		IDocument myDoc = new Document2004();
		myDoc.encoding(Encoding.UTF_8);

        myDoc.addEle(Heading1.with("长沙教育学院《中学教育心理学》练习题（中学）").withStyle()
                .align(Align.CENTER).create());
        
        myDoc.addEle(Heading2.with("选择题").create());
        
        myDoc.addEle(Paragraph.with("1、生学习是为了获得老师和长辈的赏识，根据奥苏伯尔的学习动机理论，这样的学习动机属于（ ）")
                .create());
        myDoc.addEle(Paragraph
                .with("  A 认知内驱力 B 自我提高内驱力")
                .create());
        myDoc.addEle(Paragraph.with("  C 附属内驱力 D 求知欲")
                .create());
        myDoc.addEle(Paragraph.with("   ").create());
        
        myDoc.addEle(Paragraph.with("2、行为主义学者斯金纳认为，个体行为动机的激发与这种行为先前所受到的（ ）有很大的关系。")
                .create());
        myDoc.addEle(Paragraph.with("  A 归因训练 B 强化")
                .create());
        myDoc.addEle(Paragraph.with("  C 缺失性需要的满足 D 自我效能感的训练")
                .create());
        myDoc.addEle(Paragraph.with("   ").create());
        
        myDoc.addEle(Paragraph.with("3、马斯洛在他的需要层次论中指出，成长性需要是指（ ）。")
                .create());
        myDoc.addEle(Paragraph.with("  A 生理的需要 B 安全的需要")
                .create());
        myDoc.addEle(Paragraph.with("  C 自我实现的需要 D 归属与爱的需要")
                .create());
        myDoc.addEle(Paragraph.with("    ").create());
        
        myDoc.addEle(Paragraph.with("4、某学生考试失败后，家长斥责他时说：“我看你就不是读书这块料”，这实际上是将失败归因于（ ）。")
                .create());
        myDoc.addEle(Paragraph.with("  A 努力 B 运气")
                .create());
        myDoc.addEle(Paragraph.with("  C 难度 D 能力")
                .create());
        myDoc.addEle(Paragraph.with("    ").create());
        
        myDoc.addEle(Paragraph.with("5、关于学习动机的激发水平，美国心理学家耶克斯和多德森认为，最有利于学习效果提高的动机激起水平是（ ）。")
                .create());
        myDoc.addEle(Paragraph.with("  A 低等程度 B 紧张程度")
                .create());
        myDoc.addEle(Paragraph.with("  C 中等程度 D 高等程度")
                .create());
        myDoc.addEle(Paragraph.with("    ").create());
        
        myDoc.addEle(Heading2.with("填空题").create());
        
        myDoc.addEle(Paragraph.with("1、一般来说，动机具有三种功能：一是激活功能；二是指向功能；三是（ ）功能。")
                .create());
        myDoc.addEle(Paragraph.with("    ").create());
        
        myDoc.addEle(Paragraph.with("2、学习动机有两个基本成分，两者相互作用形成学习的动机系统，它们是学习需要和（ ）。")
                .create());
        myDoc.addEle(Paragraph.with("    ").create());
        
        myDoc.addEle(Paragraph.with("3、到了青年期，（ ）和自我提高内驱力成为学生学习的主要动机。")
                .create());
        myDoc.addEle(Paragraph.with("    ").create());
        
        myDoc.addEle(Paragraph.with("4、阿特金森认为，力求成功者追求成就的获取，多选择成功概率为（ ）的任务。")
                .create());
        myDoc.addEle(Paragraph.with("    ").create());
        
        myDoc.addEle(Paragraph.with("5、在学校里，老师经常通过一定的榜样来强化相应的学习行为或学习行为倾向。班杜拉认为老师的这种行为属于（ ）强化。")
                .create());
        myDoc.addEle(Paragraph.with("    ").create());
        
        myDoc.addEle(Paragraph.with("6、在学习动机系统中，能够激起有机体的定向行为，并能满足某种需要的外部条件或刺激物叫（ ）。")
                .create());
        myDoc.addEle(Paragraph.with("    ").create());
        
        myDoc.addEle(Paragraph.with("7、根据维纳的动机归因理论，学生将学习成功或失败归因于（ ）比归因于能力更可能产生强烈的情绪体验。")
                .create());        
        myDoc.addEle(Paragraph.with("    ").create());
        
        myDoc.addEle(Heading2.with("简答题").create());
        myDoc.addEle(Paragraph.with("1、简述学习动机与学习效果的关系。")
                .create());
        myDoc.addEle(Paragraph.with("    ").create());
        
        myDoc.addEle(Paragraph.with("2、简述教师激发和培养学生自信心的策略。")
                .create());       
        myDoc.addEle(Paragraph.with("    ").create());
        
        
        myDoc.addEle(Heading2.with("论述题").create());
        myDoc.addEle(Paragraph.with("1、请从教师的视角阐述：从内、外动机相结合入手促使学生积极参与学习的策略")
                .create());
        myDoc.addEle(Paragraph.with("    ").create());
        
        myDoc.addEle(Heading1.with("参考答案").withStyle()
                .align(Align.CENTER).create());
        
        myDoc.addEle(Heading2.with("选择题").create());
        myDoc.addEle(Paragraph.with("1、C").create());
        myDoc.addEle(Paragraph.with("2、B").create());
        myDoc.addEle(Paragraph.with("3、C").create());
        myDoc.addEle(Paragraph.with("4、D").create());
        myDoc.addEle(Paragraph.with("5、C").create());
        
        myDoc.addEle(Heading2.with("填空题").create());
        myDoc.addEle(Paragraph.with("1、维持").create());
        myDoc.addEle(Paragraph.with("2、学习期待").create());
        myDoc.addEle(Paragraph.with("3、认知内驱力").create());
        myDoc.addEle(Paragraph.with("4、50%").create());
        myDoc.addEle(Paragraph.with("5、替代性").create());
        myDoc.addEle(Paragraph.with("6、诱因").create());
        myDoc.addEle(Paragraph.with("7、努力").create());
        
        myDoc.addEle(Heading2.with("简答题").create());
        myDoc.addEle(Paragraph.with("1、答：学习动机与学习效果的关系，主要表现为：").create());
        myDoc.addEle(Paragraph.with("  1）一定范围内有一致性；").create());
        myDoc.addEle(Paragraph.with("  2）一定范围内有一致性；").create());
        myDoc.addEle(Paragraph.with("    ").create());
        
        myDoc.addEle(Paragraph.with("2、答：激发和培养学生自信心的策略：").create());
        myDoc.addEle(Paragraph.with("  1）提高学生的自我效能感。").create());
        myDoc.addEle(Paragraph.with("  2）设置合理的目标；").create());
        myDoc.addEle(Paragraph.with("  3）进行归因训练；").create());
        
        myDoc.addEle(Heading2.with("论述题").create());
        myDoc.addEle(Paragraph.with("1、答：从内部动机和外部动机结合入手促使学生积极参与学习的策略：").create());
        myDoc.addEle(Paragraph.with("  1．使任务更有趣；").create());
        myDoc.addEle(Paragraph.with("  2．引发认知冲突；").create());
        myDoc.addEle(Paragraph.with("  3．合理使用表扬；").create());
        myDoc.addEle(Paragraph.with("  1）有效地应用表扬；").create());
        myDoc.addEle(Paragraph.with("  2）以目标结构为基础的评价体系；").create());
        myDoc.addEle(Paragraph.with("  4．合适的反馈；").create());
		
        myDoc.getFooter().addEle(
                Paragraph.withPieces(
                        ParagraphPiece.with("湖南"),
                        ParagraphPiece.with(" 彭柳 ").withStyle().bold()
                                .create(), ParagraphPiece.with("科技股份有限公司"))
                        .create());

        myDoc.getHeader().addEle(
                Paragraph.with("智能组卷系统").create());
        
        String myWord = myDoc.getContent();
//        System.out.println("myWord:\n"+myWord);
        //Property prop = new Property("    ");
//        Properties prop = new Properties();
        String tmpDocs = "";
//        try {            
//            prop.load(new FileInputStream("build.properties"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        tmpDocs = (String) prop.get("tmp.docs.dir");
//        tmpDocs = "D:";
        //System.out.println(tmpDocs);
        //"/home/leonardo/Desktop/Java2word_allInOne.doc"
        
//        String fileName = "Test.doc";
        File fileObj = new File(fpath);

        PrintWriter writer = null;
        try {
            writer = new PrintWriter(fileObj,"UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        writer.println(myWord);
        writer.close();
		
	}
}
