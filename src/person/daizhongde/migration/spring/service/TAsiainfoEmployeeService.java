package person.daizhongde.migration.spring.service;


import java.util.List;

import person.daizhongde.authority.hibernate.pojo.TAuthorityUser;

import person.daizhongde.virtue.spring.BaseService;

/**
 * @author daizd
 *
 */
public interface TAsiainfoEmployeeService extends BaseService{

	public abstract int add( String jdata, TAuthorityUser user );
	
	/**
	 * generate jsondata InMap for combobox
	 * <br>getCBBData_XXX  XXX: field name
	 * <p><b>used by jquery-easy-ui</b>
	 * @return json
	 */
	public abstract List getCBBData_SBU(String jdata);
	/**
	 * generate array for combobox  
	 * <br>getCBBData_XXX  XXX: field name
	 * <p><b>used by jquery-easy-ui</b>
	 * @return array
	 */
	public abstract List getCBBData_Company(String jdata);
	/**
	 * generate jsondata(array) for combotree asynchronous
	 * <br>attention: max level is ten
	 * <br>id,text,state,attributes
	 * <p><b>used by JEasyUI</b>
	 * @param userid
	 * @param moduleid
	 * @return
	 */
	public abstract String getData_JEasyUI_CBT_Async( Integer moduleid, boolean WithRoot );
	
}