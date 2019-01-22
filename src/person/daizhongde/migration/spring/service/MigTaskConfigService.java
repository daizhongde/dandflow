package person.daizhongde.migration.spring.service;

import java.util.List;
import java.util.Map;

import person.daizhongde.authority.hibernate.pojo.TAuthorityUser;
import person.daizhongde.migration.hibernate.pojo.MigJobPara;
import person.daizhongde.migration.hibernate.pojo.MigTaskConfig;

import person.daizhongde.virtue.spring.BaseService;

/**
 * p1:jobstart update job's prepos为null的processes
 * p2:
 * @author daizd
 *
 */
public interface MigTaskConfigService extends BaseService{
//	public void instanceTaskConfig(String jobInsId, String jobId);
	public abstract List<MigTaskConfig> findRowsByIdList( List<Integer> idList );

	public abstract int add( String jdata, TAuthorityUser user );
	
	public abstract List getData_JEasyUI_Tree(int type);
	
	public abstract List getData_JEasyUI_CheckBoxTree(int type);
}