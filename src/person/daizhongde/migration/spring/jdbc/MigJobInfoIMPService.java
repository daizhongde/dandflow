package person.daizhongde.migration.spring.jdbc;

import java.io.File;

import person.daizhongde.authority.hibernate.pojo.TAuthorityUser;

public interface MigJobInfoIMPService {
	/**
	 * 导入excel
	 * @param file import file
	 * @param _ deal with muti-thread
	 * @throws Exception
	 */
	public abstract void importJobXLS(File file, String srcFileName, String uploadContentType, String _ ,  TAuthorityUser user) throws Exception;
	
	public abstract void importKPIXLS(File file, String srcFileName, String uploadContentType, String _ ,  TAuthorityUser user) throws Exception;
	
	
}
