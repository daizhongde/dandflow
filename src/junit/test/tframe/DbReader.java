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
public class DbReader
{
    protected static String m_strJndiName_DataSource = null;
    private static DbReaderBulter m_dbReaderBulter = null;

    public static void init (String strJndiName_DataSource)
    {
        m_strJndiName_DataSource = strJndiName_DataSource;

    }

    public static void unInit ()
    {
        m_strJndiName_DataSource = null;

        if (m_dbReaderBulter != null)
        {
            m_dbReaderBulter.release ();
            m_dbReaderBulter = null;
        }

    }

    public static QueryResultRow doQuery_SingleRow (String strSql) throws SQLException , NamingException
    {
        //ErrUtil.writeDebug2 (strSql);

        return getDbReaderBulter ().doQuery_SingleRow (m_strJndiName_DataSource , strSql);

    }
    
    public static Vector doQuery_MultiRows (String strSql , int cMaxRows) throws SQLException , NamingException
    {
        //ErrUtil.writeDebug2 (strSql);

        return getDbReaderBulter ().doQuery_MultiRows ( m_strJndiName_DataSource , strSql , cMaxRows);
    }
    /**
     * query data, row form is map
     * @param strSql
     * @param values parameter value array
     * @return
     * @throws SQLException
     * @throws NamingException
     * @author dzd,  date:2013/09/03
     */
    public static List doQueryInMap ( final String strSql , final Object[] values ) throws SQLException , NamingException
    {
        //ErrUtil.writeDebug2 (strSql);

        return getDbReaderBulter ().doQueryInMap ( m_strJndiName_DataSource , strSql, values );
    }
    /**
     * query data, row form is map
     * @param strSql
     * @param values parameter value array
     * @param SQLTypes SQL Type array
     * @return
     * @throws SQLException
     * @throws NamingException
     * @author dzd,  date:2013/09/03
     */
    public static List doQueryInMap ( final String strSql , final Object[] values, final int[] SQLTypes ) throws SQLException , NamingException
    {
        //ErrUtil.writeDebug2 (strSql);

        return getDbReaderBulter ().doQueryInMap ( m_strJndiName_DataSource , strSql, values, SQLTypes );
    }
    /**
     * query data, row form is array
     * @param strSql
     * @param values parameter value array
     * @return
     * @throws SQLException
     * @throws NamingException
     * @author dzd,  date:2013/09/03
     */
    public static List doQueryInArray ( final String strSql , final Object[] values ) throws SQLException , NamingException
    {
        //ErrUtil.writeDebug2 (strSql);

        return getDbReaderBulter ().doQueryInArray ( m_strJndiName_DataSource , strSql, values );
    }

    /**
     * query data, row form is array
     * @param strSql
     * @param values parameter value array
     * @param SQLTypes SQL Type array
     * @return
     * @throws SQLException
     * @throws NamingException
     * @author dzd,  date:2013/09/03
     */
    public static List doQueryInArray ( final String strSql , final Object[] values, final int[] SQLTypes ) throws SQLException , NamingException
    {
        //ErrUtil.writeDebug2 (strSql);

        return getDbReaderBulter ().doQueryInArray ( m_strJndiName_DataSource , strSql, values, SQLTypes );
    }

    /**
     * pagination query data, row form is map
     * <br>Can pagination query
     * @param strSql
     * @param values parameter value array
     * @param offset firstResult a row number, numbered from 0
     * @param pageSize the maximum number of rows
     * @return
     * @throws SQLException
     * @throws NamingException
     * @author dzd,  date:2013/09/03
     */
    public static List doQueryByPageInMap ( String strSql , final Object[] values , final int offset , final int pageSize ) throws SQLException , NamingException
    {
        //ErrUtil.writeDebug2 (strSql);

        return getDbReaderBulter ().doQueryByPageInMap ( m_strJndiName_DataSource , strSql , values , offset, pageSize );
    }
    /**
     * pagination query data, row form is map
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
    public static List doQueryByPageInMap ( String strSql, final Object[] values, final int[] SQLTypes, final int offset, final int pageSize ) throws SQLException , NamingException
    {
        //ErrUtil.writeDebug2 (strSql);

        return getDbReaderBulter ().doQueryByPageInMap (m_strJndiName_DataSource , strSql, values, SQLTypes , offset, pageSize );
    }
    /**
     * pagination query data, row form is array
     * @param strSql
     * @param values parameter value array
     * @param offset firstResult a row number, numbered from 0
     * @param pageSize the maximum number of rows
     * @return
     * @throws SQLException
     * @throws NamingException
     * @author dzd,  date:2013/09/03
     */
    public static List doQueryByPageInArray ( String strSql , final Object[] values , final int offset , final int pageSize ) throws SQLException , NamingException
    {
        //ErrUtil.writeDebug2 (strSql);

        return getDbReaderBulter ().doQueryByPageInArray (m_strJndiName_DataSource , strSql , values , offset , pageSize );
    }

    /**
     * pagination query data, row form is array
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
    public static List doQueryByPageInArray ( String strSql, final Object[] values, final int[] SQLTypes, final int offset, final int pageSize ) throws SQLException , NamingException
    {
        //ErrUtil.writeDebug2 (strSql);

        return getDbReaderBulter ().doQueryByPageInArray (m_strJndiName_DataSource , strSql, values, SQLTypes , offset, pageSize );
    }
    
    public static QueryResult doSelect (QueryInfo queryInfo) throws SQLException , NamingException
    {
        //ErrUtil.writeDebug2 (queryInfo.m_strSql);

        return getDbReaderBulter ().doSelect (m_strJndiName_DataSource , queryInfo);

    }

    public static QueryResult doSelect (DBStatement dbStatement , int nPageSize , int nPageNo) throws SQLException ,
        NamingException
    {
        //ErrUtil.writeDebug2 (dbStatement.getTranslatedSql ());

        return getDbReaderBulter ().doSelect (dbStatement , nPageSize , nPageNo);
    }

    protected static DbReaderBulter getDbReaderBulter ()
    {
        if (m_dbReaderBulter == null)
        {
            m_dbReaderBulter = new DbReaderBulterDAO ();

        }
        return m_dbReaderBulter;

    }

    public static void releaseData (QueryResultRow row)
    {
        if (row != null)
        {
            row.release ();
        }
    }

    public static void releaseData (Vector rows)
    {
        if (rows != null)
        {
            for (int i = 0; i < rows.size (); i++)
            {
                ( (QueryResultRow) rows.get (i)).release ();
            }
            rows.clear ();
        }
    }

} // class DbReader
