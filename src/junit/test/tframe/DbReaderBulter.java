package junit.test.tframe;

import java.util.List;
import java.util.Vector;
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
public interface DbReaderBulter
{
    public abstract void release();

    public abstract QueryResultRow doQuery_SingleRow(String strJndiName_DataSource, String strSql) throws SQLException,
        NamingException;

    public abstract Vector doQuery_MultiRows(String strJndiName_DataSource, String strSql, int cMaxRows) throws
        SQLException, NamingException;

    /**
     * query return result in the form of map
     * @param strJndiName_DataSource
     * @param strSql
     * @param values parameter value array
     * @return
     * @throws SQLException
     * @throws NamingException
     * @author dzd,  date:2013/09/03
     */
    public abstract List doQueryInMap( final String strJndiName_DataSource,  final String strSql ,final Object[] values ) throws
    	SQLException, NamingException;
    /**
     * query return result in the form of map
     * @param strJndiName_DataSource
     * @param strSql
     * @param values parameter value array
     * @param SQLTypes SQL Type array
     * @return
     * @throws SQLException
     * @throws NamingException
     * @author dzd,  date:2013/09/03
     */
    public abstract List doQueryInMap( final String strJndiName_DataSource,  final String strSql ,final Object[] values, int[] SQLTypes ) throws
    	SQLException, NamingException;
    /**
     * query return result in the form of array
     * @param strJndiName_DataSource
     * @param strSql parameter value array
     * @param values parameter value array
     * @return
     * @throws SQLException
     * @throws NamingException
     * @author dzd,  date:2013/09/03
     */
    public abstract List doQueryInArray( final String strJndiName_DataSource,  final String strSql ,final Object[] values ) throws
	SQLException, NamingException;

    /**
     * query return result in the form of array
     * @param strJndiName_DataSource
     * @param strSql
     * @param values parameter value array
     * @param SQLTypes SQL Type array
     * @return
     * @throws SQLException
     * @throws NamingException
     * @author dzd,  date:2013/09/03
     */
    public abstract List doQueryInArray( final String strJndiName_DataSource,  final String strSql ,final Object[] values, int[] SQLTypes ) throws
	SQLException, NamingException;
    /**
     * pagination query return result in the form of map
     * @param strJndiName_DataSource
     * @param strSql
     * @param values parameter value array
     * @param offset firstResult a row number, numbered from 0
     * @param pageSize the maximum number of rows
     * @return
     * @throws SQLException
     * @throws NamingException
     * @author dzd,  date:2013/09/03
     */
    public abstract List doQueryByPageInMap(final String strJndiName_DataSource, String strSql , final Object[] values, final int offset, final int pageSize ) throws
    	SQLException, NamingException;
    /**
     * pagination query return result in the form of map
     * @param strJndiName_DataSource
     * @param strSql
     * @param values parameter value array
     * @param SQLTypes SQL Type array
     * @param offset firstResult a row number, numbered from 0
     * @param pageSize the maximum number of rows
     * @return
     * @throws SQLException
     * @throws NamingException
     * @author dzd,  date:2013/09/03
     */
    public abstract List doQueryByPageInMap(final String strJndiName_DataSource, String strSql , final Object[] values, int[] SQLTypes, final int offset, final int pageSize ) throws
    	SQLException, NamingException;
    /**
     * pagination query return result in the form of array
     * @param strJndiName_DataSource
     * @param strSql
     * @param values parameter value array
     * @param offset firstResult a row number, numbered from 0
     * @param pageSize the maximum number of rows
     * @return
     * @throws SQLException
     * @throws NamingException
     * @author dzd,  date:2013/09/03
     */
    public abstract List doQueryByPageInArray(final String strJndiName_DataSource, String strSql , final Object[] values, final int offset, final int pageSize ) throws
    	SQLException, NamingException;

    /**
     * pagination query return result in the form of array
     * @param strJndiName_DataSource
     * @param strSql
     * @param values parameter value array
     * @param SQLTypes SQL Type array
     * @param offset firstResult a row number, numbered from 0
     * @param pageSize the maximum number of rows
     * @return
     * @throws SQLException
     * @throws NamingException
     * @author dzd,  date:2013/09/03
     */
    public abstract List doQueryByPageInArray(final String strJndiName_DataSource, final String strSql , final Object[] values, int[] SQLTypes, final int offset, final int pageSize ) throws
    	SQLException, NamingException;
    
    public abstract QueryResult doSelect(String strJndiName_DataSource, QueryInfo queryInfo) throws SQLException,
        NamingException;

    public abstract QueryResult doSelect(DBStatement dbStatement, int nPageSize, int nPageNo) throws SQLException,
        NamingException;

} // class DbReaderBulter
