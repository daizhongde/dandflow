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
public class DbOperator extends DbReader
{
    private static DbOperatorBulter m_dbOperatorBulter = null;

    public static void unInit()
    {
        DbReader.unInit();

        if (m_dbOperatorBulter != null)
        {
            m_dbOperatorBulter.release();
            m_dbOperatorBulter = null;
        }

    } // unInit

    protected static DbReaderBulter getDbReaderBulter()
    {
        return (DbReaderBulter) getDbOperatorBulter();

    } // getDbReaderBulter

    protected static DbOperatorBulter getDbOperatorBulter()
    {
        if (m_dbOperatorBulter == null)
        {
            m_dbOperatorBulter = new DbOperatorBulterDAO();
        }
        return m_dbOperatorBulter;

    } // getDbOperatorBulter

    public static int doUpdate(String strDataSource, QueryInfo queryInfo) throws SQLException, NamingException
    {
//        //ErrUtil.writeDebug2(queryInfo.m_strSql);

        return getDbOperatorBulter().doUpdate(strDataSource, queryInfo);

    } // doUpdate

    /**
     * add by daizhongde, date:2013/09/03
     * <br>Can insert ,update, delete
     * @param strDataSource
     * @param strSql
     * @param values parameter value array
     * @return
     * @throws SQLException
     * @throws NamingException
     * @author dzd,  date:2013/09/03
     */
    public static int doUpdate(String strDataSource, final String strSql , final Object[] values ) throws SQLException, NamingException
    {
        //ErrUtil.writeDebug2( strSql );

        return getDbOperatorBulter().doUpdate( strDataSource , strSql, values );

    } 
    /**
     * add by daizhongde, date:2013/09/03
     * <br>Can insert ,update, delete
     * @param strDataSource
     * @param strSql
     * @param values parameter values
     * @param SQLTypes SQL Type array
     * @return
     * @throws SQLException
     * @throws NamingException
     * @author dzd,  date:2013/09/03
     */
    public static int doUpdate(String strDataSource, final String strSql , final Object[] values , final int[] SQLTypes ) throws SQLException, NamingException
    {
        //ErrUtil.writeDebug2( strSql );

        return getDbOperatorBulter().doUpdate( strDataSource , strSql, values , SQLTypes );

    } 
    public static int callProcedure(String strDataSource, QueryInfo queryInfo) throws SQLException, NamingException
    {
        //ErrUtil.writeDebug2(queryInfo.m_strSql);

        return getDbOperatorBulter().callProcedure(strDataSource, queryInfo);

    } // doUpdate

} // class DbOperator
