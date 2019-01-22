package junit.test.tframe;

import java.sql.SQLException;
import javax.naming.NamingException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.CallableStatement;
import java.sql.ResultSet;


/**
 * <p>Title: Smart Web Application FrameWork</p>
 * <p>Description: framework for J2EE applications</p>
 * <p>Copyright: Copyright (c) Reserved by copote 2008</p>
 * <p>Company: copote</p>
 * @author wg
 * @version 2.1
 * @modify daizhongde,date:2013/09/03
 */

public class DbOperatorBulterDAO extends DbReaderBulterDAO implements DbOperatorBulter
{
	private org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(this.getClass());
	
    public int doUpdate (String strJndiName_DataSource , QueryInfo queryInfo) throws SQLException , NamingException
    {
        int nResult = 0;

        Connection connection = null;
        PreparedStatement statement = null;
        SQLException exception = null;
        try
        {

            getDataSource (strJndiName_DataSource);
            connection = m_dataSource.getConnection ();

            String strSql = queryInfo.m_strSql;

            statement = connection.prepareStatement (strSql);
            for (int nParam = 0; nParam < queryInfo.m_aobjParams.length; nParam++)
            {
                setStatementObject (statement , nParam + 1 ,
                                    queryInfo.m_aobjParams[nParam] , queryInfo.m_anParamSqlTypes[nParam]);
            }

            nResult = statement.executeUpdate ();
        }

        catch (SQLException ex)
        {
            exception = ex;
        }
        catch (Exception ex)
        {

        }

        if (statement != null)
        {
            statement.close ();
            statement = null;
        }
        if (connection != null)
        {
            connection.close ();
            connection = null;
        }

        if (exception != null)
        {
            throw exception;
        }

        return nResult;

    } // doUpdate

    public int doUpdate (final String strJndiName_DataSource , final String strSql , final Object[] values ) throws SQLException , NamingException
    {
        int nResult = 0;

        Connection connection = null;
        PreparedStatement statement = null;
        SQLException exception = null;
        try
        {

            getDataSource (strJndiName_DataSource);
            connection = m_dataSource.getConnection ();

            log.debug(strSql);
            statement = connection.prepareStatement (strSql ,
                ResultSet.TYPE_SCROLL_INSENSITIVE ,
                ResultSet.CONCUR_READ_ONLY);
            
            log.debug("===给sql语句中的参数进行赋值===");
			for(int i=0,j=values.length; i<j; i++){
				System.out.println( "values[" + i + "]:" + values[i] );
				statement.setObject( i+1 , values[i] );
//				statement.setObject( i+1 , values[i], SQLType[i] );
//				statement.setObject( i+1 , values[i], SQLType[i], scale[i] );
			}
//			System.out.println("hello world1!");
            nResult = statement.executeUpdate ();
//            System.out.println("hello world2!");
        }

        catch (SQLException ex)
        {
            exception = ex;
        }
        catch (Exception ex)
        {
        	ex.printStackTrace();
        }

        if (statement != null)
        {
            statement.close ();
            statement = null;
        }
        if (connection != null)
        {
            connection.close ();
            connection = null;
        }

        if (exception != null)
        {
            throw exception;
        }

        return nResult;

    } // doUpdate
    public int doUpdate (final String strJndiName_DataSource , final String strSql , final Object[] values , final int[] SQLType ) throws SQLException , NamingException
    {
        int nResult = 0;

        Connection connection = null;
        PreparedStatement statement = null;
        SQLException exception = null;
        try
        {

            getDataSource (strJndiName_DataSource);
            connection = m_dataSource.getConnection ();

            log.debug(strSql);
            statement = connection.prepareStatement (strSql ,
                ResultSet.TYPE_SCROLL_INSENSITIVE ,
                ResultSet.CONCUR_READ_ONLY);
            
            log.debug("===给sql语句中的参数进行赋值===");
			for(int i=0,j=values.length; i<j; i++){
//				statement.setObject( i+1 , values[i] );
				statement.setObject( i+1 , values[i], SQLType[i] );
//				statement.setObject( i+1 , values[i], SQLType[i], scale[i] );
			}
            nResult = statement.executeUpdate ();
        }

        catch (SQLException ex)
        {
            exception = ex;
        }
        catch (Exception ex)
        {

        }

        if (statement != null)
        {
            statement.close ();
            statement = null;
        }
        if (connection != null)
        {
            connection.close ();
            connection = null;
        }

        if (exception != null)
        {
            throw exception;
        }

        return nResult;

    } // doUpdate
    public int callProcedure (String strJndiName_DataSource , QueryInfo queryInfo) throws SQLException ,
        NamingException
    {
        Connection connection = null;
        CallableStatement callStatement = null;
        SQLException exception = null;
        int nResult = 0;
        try
        {
            getDataSource (strJndiName_DataSource);
            connection = m_dataSource.getConnection ();
            String strSql = queryInfo.m_strSql;

            callStatement = connection.prepareCall (strSql);
            callStatement.registerOutParameter (1 , queryInfo.m_anParamSqlTypes[0]);

            for (int nParam = 1; nParam < queryInfo.m_aobjParams.length; nParam++)
            {
                setStatementObject (callStatement , nParam + 1 ,
                                    queryInfo.m_aobjParams[nParam] , queryInfo.m_anParamSqlTypes[nParam]);

            }

            callStatement.execute ();
            nResult = callStatement.getInt (2);

        }
        catch (SQLException ex)
        {
            exception = ex;
        }
        catch (Exception ex)
        {

        }

        if (callStatement != null)
        {
            callStatement.close ();
            callStatement = null;
        }
        if (connection != null)
        {
            connection.close ();
            connection = null;
        }

        if (exception != null)
        {
            throw exception;
        }

        return nResult;

    }

} // class DbOperatorBulterDAO
