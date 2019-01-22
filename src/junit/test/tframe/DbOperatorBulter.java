package junit.test.tframe;

import java.sql.SQLException;
import javax.naming.NamingException;

/**
 * <p>Title: Smart Web Application FrameWork</p>
 * <p>Description: framework for J2EE applications</p>
 * <p>Copyright: Copyright (c) Reserved by copote 2008</p>
 * <p>Company: copote</p>
 * @author wg
 * @version 2.1
 * @modify daizhongde,date:2013/09/03
 */
public interface DbOperatorBulter extends DbReaderBulter
{
    public abstract int doUpdate(String strJndiName_DataSource, QueryInfo queryInfo) throws SQLException,
        NamingException;

    /**
     * 
     * @param strJndiName_DataSource
     * @param strSql
     * @param values parameter value array
     * @return
     * @throws SQLException
     * @throws NamingException
     * @modify daizhongde,date:2013/09/03
     */
    public abstract int doUpdate(String strJndiName_DataSource, final String strSql , final Object[] values ) throws SQLException,
    	NamingException;
    /**
     * 
     * @param strJndiName_DataSource
     * @param strSql
     * @param values parameter value array
     * @param SQLTypes SQL types
     * @return
     * @throws SQLException
     * @throws NamingException
     * @modify daizhongde,date:2013/09/03
     */
    public abstract int doUpdate(String strJndiName_DataSource, final String strSql , final Object[] values , final int[] SQLTypes ) throws SQLException,
		NamingException;
    
    
    public abstract int callProcedure(String strJndiName_DataSource, QueryInfo queryInfo) throws SQLException,
        NamingException;

} // class DbOperatorBulter
