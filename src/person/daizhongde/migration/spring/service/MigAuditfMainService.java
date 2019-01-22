package person.daizhongde.migration.spring.service;


import java.util.List;

import person.daizhongde.authority.hibernate.pojo.TAuthorityUser;
import person.daizhongde.migration.hibernate.pojo.MigAuditfMain;

import person.daizhongde.virtue.spring.BaseService;

/**
 * @author daizd
 *
 */
public interface MigAuditfMainService extends BaseService{

	public abstract int add( String jdata, TAuthorityUser user );
	public abstract List getData_JEasyUI_Tree();
}