package person.daizhongde.migration.spring.service;


import person.daizhongde.authority.hibernate.pojo.TAuthorityUser;

import person.daizhongde.virtue.spring.BaseService;

/**
 * @author daizd
 *
 */
public interface MigDataPrecheckService extends BaseService{

	public abstract int add( String jdata, TAuthorityUser user );
	
}