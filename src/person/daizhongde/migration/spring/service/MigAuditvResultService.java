package person.daizhongde.migration.spring.service;


import java.util.Map;

import person.daizhongde.authority.hibernate.pojo.TAuthorityUser;

import person.daizhongde.virtue.spring.BaseService;

/**
 * @author daizd
 *
 */
public interface MigAuditvResultService extends BaseService{

	public abstract int add( String jdata, TAuthorityUser user );
	public abstract Map browse2( String jdata );
	
}