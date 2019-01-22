package person.daizhongde.migration.spring.service;


import java.util.List;

import person.daizhongde.authority.hibernate.pojo.TAuthorityUser;
import person.daizhongde.migration.hibernate.pojo.MigAuditvConfig;

import person.daizhongde.virtue.spring.BaseService;

/**
 * @author daizd
 *
 */
public interface MigAuditvConfigService extends BaseService{

	public abstract int add( String jdata, TAuthorityUser user );
	
	public abstract List<MigAuditvConfig> findRowsByIdList( List<String> id_List );
	
	public abstract List getData_JEasyUI_Tree();
	
	
}