package person.daizhongde.migration.spring.service;

import java.util.Map;

import person.daizhongde.authority.hibernate.pojo.TAuthorityUser;

public interface CommonCURDService {

	/** C  **/
	public abstract int addWithId( String jdata, String tableName );
	public abstract int addWithId( String jdata, String tableName, String authorColumnName,TAuthorityUser user  );
	
	/** R  **/
	public abstract Map browse( String jdata, String tableName );
	public abstract Map browse( String jdata, String tableName, String readSQLName );
	
	/** U  **/
	public abstract int modify( String jdata, String tableName );
	
	/** D,permanent delete **/
	public abstract int delete( String jdata, String tableName );

}
