package person.daizhongde.migration.spring.jdbc;

import java.io.File;

import person.daizhongde.authority.hibernate.pojo.TAuthorityUser;

public interface MigAuditvConfigIMPService {
	/**
	 * input excel,write 
	 * @param file import file
	 * @param _ deal with muti-thread
	 * @throws Exception
	 */

	public abstract void importAuditvConfigXLS(File file, String srcFileName, String _, TAuthorityUser user ) throws Exception;
	
	
}
