package person.daizhongde.migration.spring.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;

import person.daizhongde.authority.hibernate.pojo.JEasyUI_Tree2;
import person.daizhongde.authority.hibernate.pojo.JEasyUI_Tree2_Leaf;
import person.daizhongde.migration.hibernate.dao.MigControlInfoDAO;
import person.daizhongde.migration.hibernate.dao.MigControlTemplateDAO;
import person.daizhongde.migration.hibernate.pojo.MigControlInfo;
import person.daizhongde.migration.hibernate.pojo.MigControlTemplate;
import person.daizhongde.migration.spring.service.BusiMemoryService;
import person.daizhongde.migration.util.TemplateUtils;

import person.daizhongde.virtue.dao.SpringHibernateDao;

public class BusiMemoryServiceImpl implements BusiMemoryService {
	
	private SpringHibernateDao virtueDAO;
	private MigControlInfoDAO migControlInfoDAO;
	private MigControlTemplateDAO migControlTemplateDAO;
	
	private static Map<String, String> controlIconMap = new HashMap<String, String>();
	private static Map<String, String> controlNameMap = new HashMap<String, String>();
	private static List controlTreeData = new ArrayList();
	private static Map<String, List<MigControlTemplate>> controlMap = new HashMap<String, List<MigControlTemplate>>();
	
	public void init(){
		List dataList = new ArrayList();

		JEasyUI_Tree2 con = new JEasyUI_Tree2();
		con.setId("1");
		con.setText("Control");
		con.setState("closed");
		con.setChecked(true);
		
		JEasyUI_Tree2 taskPkg = new JEasyUI_Tree2();
		taskPkg.setId("2");
		taskPkg.setText("Others");
		taskPkg.setState("closed");
		taskPkg.setChecked(true);
		
		List<MigControlInfo> level2ControlList = migControlInfoDAO.findAll();
		//如果没有控件就返回空
		if( level2ControlList.size()== 0 ){
			return;
		}
		
		//组装控件树枝数据
		List<JEasyUI_Tree2_Leaf> conList = new ArrayList<JEasyUI_Tree2_Leaf>();
		for( MigControlInfo e : level2ControlList ){
			
			controlIconMap.put(e.getControlId(), e.getIconCls() );
			controlNameMap.put(e.getControlId(), e.getControlName() );
			
			JEasyUI_Tree2_Leaf mJEU_L = new JEasyUI_Tree2_Leaf();
			LinkedHashMap attr2 = new LinkedHashMap();
			
			mJEU_L.setId( e.getControlId() );
			mJEU_L.setText( e.getControlName() );
			mJEU_L.setState( "open" );
			mJEU_L.setIconCls( e.getIconCls() );
			
			mJEU_L.setChecked( true );
			
			attr2.put("note", e.getControlMark() );
			attr2.put("type", "T");
			List<MigControlTemplate> list =  migControlTemplateDAO.findByControlId( e.getControlId() );
			attr2.put("tpl",  list);

			mJEU_L.setAttributes(attr2);
			conList.add(mJEU_L);
			
			//add by daizd 20140424 controlMap load in memory
			controlMap.put( e.getControlId() , list );
		}
		con.setChildren(conList);
		
		//组装作业树枝数据
		List<JEasyUI_Tree2_Leaf> taskPkgList = new ArrayList();
		JEasyUI_Tree2_Leaf mJEU_L = new JEasyUI_Tree2_Leaf();
		LinkedHashMap attr2 = new LinkedHashMap();
		
		mJEU_L.setId( "21" );
		mJEU_L.setText( "Job" );
		mJEU_L.setState( "open" );
		mJEU_L.setIconCls( "icon-job" );
		
		mJEU_L.setChecked( false );
		
		attr2.put( "note", "Migration Job" );
		attr2.put( "type", "J" );
		mJEU_L.setAttributes( attr2 );
		taskPkgList.add( mJEU_L );
		taskPkg.setChildren( taskPkgList );
		
		dataList.add(con);
		dataList.add(taskPkg);
		
		controlTreeData = dataList;
	}

	public Map<String, String> getControlIconMap() {
		return controlIconMap;
	}

	public Map<String, String> getControlNameMap() {
		return controlNameMap;
	}

	public List getControlTreeData() {
		return controlTreeData;
	}

	public Map<String, List<MigControlTemplate>> getControlMap() {
		return controlMap;
	}
	public List<MigControlTemplate> getTemplateByControlId(String controlId){
		return controlMap.get(controlId);
	}
	public String getParamNameByControlIdandParamId( String controlId, int paraId ){
		
		Map<Integer, String> map =  TemplateUtils.getIdNameMap(controlMap.get(controlId));
		
		return map.get( paraId ) ;
	}
	public SpringHibernateDao getVirtueDAO() {
		return virtueDAO;
	}

	public void setVirtueDAO(SpringHibernateDao virtueDAO) {
		this.virtueDAO = virtueDAO;
	}

	public void setMigControlInfoDAO(MigControlInfoDAO migControlInfoDAO) {
		this.migControlInfoDAO = migControlInfoDAO;
	}

	public void setMigControlTemplateDAO(MigControlTemplateDAO migControlTemplateDAO) {
		this.migControlTemplateDAO = migControlTemplateDAO;
	}
	
	public static BusiMemoryService getFromApplicationContext(
			ApplicationContext ctx) {
		return (BusiMemoryService) ctx.getBean("busiMemoryService");
	}

}
