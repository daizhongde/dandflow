package person.daizhongde.migration.spring.service.imp;

import java.io.File;

import person.daizhongde.authority.hibernate.pojo.TAuthorityUser;

public interface TCopoteEmployeeIMPService {
/**
 * 导入工资文件并发送email
 * @author dzd 20190421
 * 
 * @param file
 * @param srcFileName
 * @param uploadContentType
 * @param _
 * @param user
 * @throws Exception
 */
	public abstract void importSalaryXLS(File file,  String uploadFileName, String uploadContentType, String _ ,  TAuthorityUser user) throws Exception;
	
	
}
