package person.daizhongde.migration.spring.service;

import person.daizhongde.authority.hibernate.pojo.TAuthorityUser;

import person.daizhongde.virtue.spring.BaseService;

/**
 * @author daizd
 *
 */
public interface MigAuditvConfigtreeService extends BaseService{

	public abstract int add( String jdata, TAuthorityUser user );
	/**
	 * assemble config tree data. 
	 * <p>
	 * data source:<br>
	 * 表: mig_auditv_config, 表: mig_auditv_configtree
	 * 
	 * @return
	 */
	public abstract Object getData_JEasyUI_Tree_Async( Integer moduleid, boolean WithRoot );
	
	/** U  **/
	public abstract int modifyContent2NULL( String jdata );
	
}