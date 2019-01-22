package person.daizhongde.migration.spring.service;

import java.util.List;
import java.util.Map;

import person.daizhongde.authority.hibernate.pojo.TAuthorityUser;
import person.daizhongde.migration.hibernate.dto.MigControlTemplateDto;
import person.daizhongde.migration.hibernate.pojo.MigComIns;
import person.daizhongde.migration.hibernate.pojo.MigComInsId;

import person.daizhongde.virtue.spring.BaseService;

/**
 * p1:jobstart update job's prepos为null的processes
 * p2:
 * @author daizd
 *
 */
public interface MigComInsService extends BaseService{
	
	public abstract List<MigControlTemplateDto> queryComInss(String controlId, String comId, String insId );
	
	public abstract List<MigComIns> getComInsByJobInsIdAndComId(String jobInsId, String comId);
	
	public abstract void modifySQL(String jobInsId, String comId, int paraId, String paraValue );
	
}