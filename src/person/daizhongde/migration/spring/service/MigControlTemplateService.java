package person.daizhongde.migration.spring.service;

import java.util.List;
import person.daizhongde.authority.hibernate.pojo.TAuthorityUser;
import person.daizhongde.migration.hibernate.pojo.MigControlTemplate;
import person.daizhongde.virtue.spring.BaseService;

/**
 * p1:jobstart update job's prepos为null的processes
 * p2:
 * @author daizd
 *
 */
public interface MigControlTemplateService extends BaseService{
	
	/**
	 * get control template by control id
	 * 
	 * @param ctlId:control Identifier
	 */
	public abstract List<MigControlTemplate> getControlTemplateByCtlId(String ctlId);	
	
}