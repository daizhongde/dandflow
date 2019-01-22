package person.daizhongde.migration.util;

import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import person.daizhongde.authority.hibernate.pojo.JEasyUI_Tree;
import person.daizhongde.authority.hibernate.pojo.JEasyUI_Tree_Leaf;
import person.daizhongde.migration.hibernate.pojo.MigAuditvConfigtree;

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
public class TreeDataUtil_AuditvConfigTree {
	private static final Log log = LogFactory.getLog(TreeDataUtil_AuditvConfigTree.class);
	/**
	 * recursive assemble Data for JEasyUI Tree's lazy children
	 * attention： max level is ten,exclude rootnode
	 *            MigAuditvConfigtree is the pojo mapping database module table
	 *            JEasyUI_Tree and JEasyUI_Tree_Leaf must overload method hasCode and equals
	 *            a method and a little code accomplished this function perfect
	 * @param dataList  node's children
	 * @param list module list
	 */
	public void assembleData_JEasyUI_Tree_Async(List dataList, List<MigAuditvConfigtree> children ) {
		
//		log.debug("#########assembleData_JEasyUI_Tree_Async  begin...###########");
		for (int i = 0, j = children.size(); i < j; i++) {
			MigAuditvConfigtree m = (MigAuditvConfigtree) children.get(i);
//			if(m.getMigAuditvConfigtree().getNIid().compareTo(parentM.getNIid()) == 0){
//			if(m.getMigAuditvConfigtree().getNIid().getNIid().compareTo( parentM.getNIid() ) == 0){
			if ( m.getIsleaf()==0 ) {// not leaf
				JEasyUI_Tree mJEU = new JEasyUI_Tree();

				mJEU.setId(m.getId());
				mJEU.setText(m.getName());
				
				mJEU.setState("closed");
				
				LinkedHashMap attr1 = new LinkedHashMap();
				attr1.put("isleaf", m.getIsleaf() );
				attr1.put("parent", m.getParent() );
				attr1.put("content", m.getContent()==null?"":m.getContent() );
				attr1.put("remark", m.getRemark()==null?"":m.getRemark() );

				mJEU.setAttributes(attr1);
//				mJEU.setChildren(nextList);
				dataList.add(mJEU);
				
			} else{
				JEasyUI_Tree_Leaf mJEU_L = new JEasyUI_Tree_Leaf();
				
				mJEU_L.setId(m.getId());
				mJEU_L.setText(m.getName()+"-"+m.getContent());
				mJEU_L.setState( "open");
				
				LinkedHashMap attr2 = new LinkedHashMap();
				attr2.put("name", m.getName() );
				attr2.put("isleaf", m.getIsleaf() );
				attr2.put("parent", m.getParent() );
				attr2.put("content", m.getContent()==null?"":m.getContent() );
				attr2.put("remark", m.getRemark()==null?"":m.getRemark() );
//				
				mJEU_L.setAttributes(attr2);
				dataList.add(mJEU_L);
			}
//			}
		}
//		log.debug("#########assembleData_JEasyUI_Tree_Async   over! ###########");
	}
}
