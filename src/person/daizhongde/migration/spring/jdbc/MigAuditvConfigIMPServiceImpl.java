package person.daizhongde.migration.spring.jdbc;

import java.io.File;
import java.sql.Connection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import person.daizhongde.authority.hibernate.pojo.TAuthorityUser;
import person.daizhongde.migration.exception.BusinessException;
import person.daizhongde.migration.spring.service.PubService;

import person.daizhongde.virtue.jdbc.JDBCSpringConnection;

/**
 * seven levels total include root. This is a compromise You can modify this
 * class to extend for more levels. gubusoft treeview use
 * <p>
 * 与MigAuditvConfigIMPServiceImpl不同，MigAuditvConfigIMPServiceImpl2不使用DAO类
 * @author dzd
 *
 */
public class MigAuditvConfigIMPServiceImpl implements MigAuditvConfigIMPService {
	private static final Log log = LogFactory
			.getLog(MigAuditvConfigIMPServiceImpl.class);

	private JDBCSpringConnection jdbcSConnection;
	private PubService pubSrv;
	
	public void setPubSrv(PubService pubSrv) {
		this.pubSrv = pubSrv;
	}
	
	public void importAuditvConfigXLS(File file, String srcFileName, String _, TAuthorityUser user) throws Exception {
						
		Connection conn = jdbcSConnection.getConnection();
		
		try{
			conn.setAutoCommit(false);

			Audit_Load load = new Audit_Load( conn, file.getAbsolutePath(), user.getCUlogname() );
			load.getDict();

			load.get_excel_data();
			load.clear_data_from_db();
			load.insert_to_db();
			
			conn.commit();
		}catch(BusinessException e){
			conn.rollback();
			throw e;
		}finally{
			conn.setAutoCommit(true);
		}
	}
	public void setJdbcSConnection(JDBCSpringConnection jdbcSConnection) {
		this.jdbcSConnection = jdbcSConnection;
	}

}
