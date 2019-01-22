package person.daizhongde.migration.util;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import person.daizhongde.migration.constant.NodeType;
import person.daizhongde.migration.hibernate.pojo.MigJobIns_JEasyUI_TreeGrid;
import person.daizhongde.migration.hibernate.pojo.MigJobIns_JEasyUI_TreeGrid_Leaf;
import person.daizhongde.migration.hibernate.pojo.MigJobProcess;

/**
 * 
 * 组装实例树所需的json数据类
 * <p>
 * 注：
 * <br>java 方法参数传递：
 * <br>pojo和list类型参数都是引用传递
 * 
 * @author dzd
 *
 */
public class TreeDataUtil_Instance {
	
	private static final Log log = LogFactory.getLog(TreeDataUtil_Instance.class);

	/**
	 * recursive assemble Data for JEasyUI Tree's lazy children
	 * attention： max level is ten,exclude rootnode
	 *            MigJobIns is the pojo mapping database module table
	 *            JEasyUI_Tree and JEasyUI_Tree_Leaf must overload method hasCode and equals
	 *            a method and a little code accomplished this function perfect
	 * @param dataList  node's children
	 * @param list module list
	 */
	public void assembleData_JEasyUI_Tree_Async(List dataList, List<MigJobProcess> children, 
			Map<String, String> controlIconMap, 
			Map<String, String> controlNameMap,
			Map<String, String> jobTypeMap ) {
		
//		log.debug("#########assembleData_JEasyUI_Tree_Async  begin...###########");
		for (int i = 0, j = children.size(); i < j; i++) {
			MigJobProcess m = (MigJobProcess) children.get(i);
//			if(m.getMigJobIns().getNIid().compareTo(parentM.getNIid()) == 0){
//			if(m.getMigJobIns().getNIid().getNIid().compareTo( parentM.getNIid() ) == 0){
			if ( m.getIsleaf() == NodeType.NOLEAF ) {// not leaf
				MigJobIns_JEasyUI_TreeGrid mJEU = new MigJobIns_JEasyUI_TreeGrid();

				mJEU.setId( m.getJobInsId() + "-" + m.getNodeId() );
				mJEU.setText( m.getNodeName() );
				mJEU.setIconCls( "icon-job" );
				mJEU.setState("closed");
				mJEU.setNote( m.getRemark() );
//				mJEU.setDryrunId( dryrunId );
				String controlId = m.getControlId();
				mJEU.setType( null== controlId ? "" : jobTypeMap.get( m.getControlId() ) );
				mJEU.setStatus(m.getStatus().toString());
				mJEU.setAuthor(m.getAuthor());
				mJEU.setBeginTime(m.getBeginTime() );
				mJEU.setEndTime(m.getEndTime());
				mJEU.setTotalTime(m.getTotalTime());
				
				dataList.add(mJEU);
				
			} else{
				MigJobIns_JEasyUI_TreeGrid_Leaf mJEU_L = new MigJobIns_JEasyUI_TreeGrid_Leaf();
				
				mJEU_L.setId( m.getJobInsId() + "-" + m.getNodeId() );
				mJEU_L.setText( m.getNodeName() );
				mJEU_L.setIconCls( controlIconMap.get(m.getControlId()) );
				mJEU_L.setNote( m.getRemark() );
//				mJEU_L.setDryrunId( dryrunId );
				String controlId = m.getControlId();
				mJEU_L.setType( null== controlId ? "" : controlNameMap.get( m.getControlId() ) );
				mJEU_L.setStatus(m.getStatus().toString());
				mJEU_L.setAuthor(m.getAuthor());
				mJEU_L.setBeginTime(m.getBeginTime() );
				mJEU_L.setEndTime(m.getEndTime());
				mJEU_L.setTotalTime(m.getTotalTime());
				
				dataList.add(mJEU_L);
			}
//			}
		}
//		log.debug("#########assembleData_JEasyUI_Tree_Async   over! ###########");
	}
}
