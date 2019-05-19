package person.daizhongde.migration.util;

import java.util.LinkedHashMap;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import person.daizhongde.authority.hibernate.pojo.JEasyUI_CBT;
import person.daizhongde.authority.hibernate.pojo.JEasyUI_CBT_Leaf;
import person.daizhongde.authority.hibernate.pojo.JEasyUI_Tree;
import person.daizhongde.authority.hibernate.pojo.JEasyUI_Tree_Leaf;
import person.daizhongde.migration.hibernate.pojo.TAsiainfoEmployee;

/**
 * 
 * 组装机构树所需的json数据类
 * <p>
 * 注：
 * <br>java 方法参数传递：
 * <br>pojo和list类型参数都是引用传递
 * 
 * @author dzd
 *
 */
public class TreeDataUtil_Employee {
	private static final Log log = LogFactory.getLog(TreeDataUtil_Employee.class);
	/**
	 * recursive assemble Data for JEasyUI Tree's lazy children
	 * attention： max level is ten,exclude rootnode
	 *            TAsiainfoEmployee is the pojo mapping database module table
	 *            TAsiainfoEmployee_GubuSoft_Tree and TAsiainfoEmployee_GubuSoft_Tree_Leaf must overload method hasCode and equals
	 *            TAsiainfoEmployee_GubuSoft_Tree is noleaf node ,TAsiainfoEmployee_GubuSoft_Tree_Leaf is leaf node
	 *            a method and a little code accomplished this function perfect
	 * @param dataList  node's children
	 * @param list module list
	 */
	public void assembleData_JEasyUI_CBT_Async(List dataList, List<TAsiainfoEmployee> children ) {
		
//		log.debug("#########assembleData_JEasyUI_CBT_Async###########");
		for (int i = 0, j = children.size(); i < j; i++) {
			TAsiainfoEmployee m = (TAsiainfoEmployee) children.get(i);
//			if(m.getTAsiainfoEmployee().getNIid().compareTo(parentM.getNIid()) == 0){
//			if(m.getTAsiainfoEmployee().getNIid().getNIid().compareTo( parentM.getNIid() ) == 0){
			if ( !m.getClass_().equalsIgnoreCase("0") ) {// not leaf
				JEasyUI_CBT mJEU = new JEasyUI_CBT();
				mJEU.setId(new Long( m.getPerson_id()) );
				mJEU.setText( m.getOrg_name()  + "-" + m.getLast_name()+ "("+m.getClass_()+")" );
				mJEU.setState("closed");
				dataList.add( mJEU );
				
			} else{ // 下属数量为0
				JEasyUI_CBT_Leaf mJEU_L = new JEasyUI_CBT_Leaf();
				mJEU_L.setId( new Long(m.getPerson_id()) );
				mJEU_L.setText( m.getOrg_name()  + "-" + m.getLast_name()  );
				mJEU_L.setState( "open" );
				dataList.add( mJEU_L );
			}
//			}
		}
//		log.debug("#########assembleData_JEasyUI_CBT_Async   over###########");
	}
}
