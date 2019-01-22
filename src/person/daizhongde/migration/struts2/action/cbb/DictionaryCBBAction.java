package person.daizhongde.migration.struts2.action.cbb;

import java.util.HashMap;
import java.util.Map;

import person.daizhongde.virtue.assemble.sql.SQLAssembleQ;
import person.daizhongde.virtue.configutils.SQLNode;
import person.daizhongde.virtue.constant.AbstractConstant;

import person.daizhongde.authority.struts2.action.BaseAction;
import person.daizhongde.migration.constant.ConstCommon;
import person.daizhongde.migration.constant.ConstMigCodedetailDefine;
import person.daizhongde.migration.hibernate.pojo.MigCodedetailDefine;
import person.daizhongde.migration.hibernate.pojo.TPubDictionary;
import person.daizhongde.migration.spring.service.MigCodedetailDefineService;
import person.daizhongde.migration.spring.service.MigConfigConnectionService;
import person.daizhongde.migration.spring.service.TPubDictionaryService;


/**
 * 模块信息相关下拉框数据
 * combobox data
 * @author dzd
 *
 */
public class DictionaryCBBAction extends BaseAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1625277481198555717L;
	private String dicType;
	
	private String sResponse;

//	private TPubDictionaryService dataService;
	
	private MigCodedetailDefineService codeDetailService;
	private MigConfigConnectionService connectionService;
	
	/**
	 * 下拉框Json数据
	 * <p>cbb : combobox
	 * @return JSONObject 
	 */
	public String getDicByType(){
//		log.debug("action............getCBBData_Id........");
//      System.out.println("dictionary.getType():" + dictionary.getType() );
		
//		TPubDictionary dictionary = new TPubDictionary();
//        dictionary.setType(dicType);
//		super.setJson( dataService.queryList(dictionary) );
		
		MigCodedetailDefine dictionary = new MigCodedetailDefine();
        dictionary.setType(dicType);
		super.setJson( codeDetailService.queryList(dictionary) );
		
		return SUCCESS;
	}
	/**
	 * 下拉框Json数据
	 * <p>cbb : combobox
	 * @return JSONObject 
	 */
	public String getDicByType2(){
		AbstractConstant absConstant = new ConstMigCodedetailDefine();
		String querycbb = ((SQLNode)absConstant.getSQLDOC().getCombobox()
				.get("combobox")
			).getSQL();
		
		Map<String, String> condition = new HashMap<String, String>(1);
		condition.put("TYPE", dicType );
		
		SQLAssembleQ sqlA = new SQLAssembleQ(
				null,
				querycbb,
				condition, 
				null,
				absConstant.getColumnTypes(),
				null
			);
		
		super.setJson( codeDetailService.getRowsInMap(sqlA) );
		return SUCCESS;
	}
	/**
	 * 下拉框Json数据,后端(C端)数据字典
	 * <p>cbb : combobox
	 * @return JSONObject 
	 */
	public String getDic2ByType(){
		MigCodedetailDefine dictionary = new MigCodedetailDefine();
        dictionary.setType(dicType);
		super.setJson( codeDetailService.queryList(dictionary) );
		
		return SUCCESS;
	}
	/**
	 * 下拉框Json数据,database connections
	 * <p>cbb : combobox
	 * @return JSONObject 
	 */
	public String getDicOfConnection(){
//		MigConfigConnection dictionary = new MigConfigConnection();
//        dictionary.(dicType);
		super.setJson( connectionService.queryAllList() );
		return SUCCESS;
	}
	
	public String getDicOfSchema(){
		AbstractConstant absConstant = new ConstCommon();
		String querycbb = ((SQLNode)absConstant.getSQLDOC().getCombobox()
				.get("combobox_Schema")
			).getSQL();
		
		SQLAssembleQ sqlA = new SQLAssembleQ(
				null,
				querycbb,
				null, 
				null,
				absConstant.getColumnTypes(),
				null
			);
		
		super.setJson( codeDetailService.getRowsInMap(sqlA) );
		return SUCCESS;
	}
	/**
	 * getDicOfSchema也可以套用此方法
	 * @return
	 */
	public String getDicBySQLName(){
		AbstractConstant absConstant = new ConstCommon();
		String querycbb = ((SQLNode)absConstant.getSQLDOC().getCombobox()
				.get("combobox_"+dicType)
			).getSQL();
		
		SQLAssembleQ sqlA = new SQLAssembleQ(
				null,
				querycbb,
				null, 
				null,
				absConstant.getColumnTypes(),
				null
			);
		
		super.setJson( codeDetailService.getRowsInMap(sqlA) );
		return SUCCESS;
	}
	public String getDicType() {
		return dicType;
	}

	public void setDicType(String dicType) {
		this.dicType = dicType;
	}

	public void setSResponse(String sResponse) {
		this.sResponse = sResponse;
	}
	public String getSResponse() {
		return sResponse;
	}
//	public void setDataService(TPubDictionaryService dataService) {
//		this.dataService = dataService;
//	}

	public void setCodeDetailService(MigCodedetailDefineService codeDetailService) {
		this.codeDetailService = codeDetailService;
	}
	public void setConnectionService(MigConfigConnectionService connectionService) {
		this.connectionService = connectionService;
	}
	
}
