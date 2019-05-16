package junit.test.tframe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.sql.SQLException;
import javax.naming.NamingException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import javax.sql.DataSource;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import java.math.BigDecimal;
import java.sql.Types;

/**
 * <p>Title: server-side framework </p>
 * <p>Description: server-side framework powered by tframe</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: copote</p>
 * @author wg
 * @version 1.0
 * @modify daizhongde,date:2013/09/03
 */
public class DbReaderBulterDAO implements DbReaderBulter
{
	private org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(this.getClass());
	
    private String m_strJndiName_DataSource = null;

    protected static DataSource m_dataSource = null;

    public void release ()
    {
        m_strJndiName_DataSource = null;

        if (m_dataSource != null)
        {
            m_dataSource = null;
        }
    } // release

    public QueryResultRow doQuery_SingleRow (String strJndiName_DataSource , String strSql) throws SQLException ,
        NamingException
    {
        QueryResultRowDAO row = null;

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        SQLException exception = null;
        try
        {
            getDataSource (strJndiName_DataSource);
            connection = m_dataSource.getConnection ();

            statement = connection.prepareStatement (strSql ,
                ResultSet.TYPE_SCROLL_INSENSITIVE ,
                ResultSet.CONCUR_READ_ONLY);

            resultSet = statement.executeQuery ();

            if (resultSet.next ())
            {
                ResultSetMetaData meta = resultSet.getMetaData ();
                row = new QueryResultRowDAO ();

                int cColumns = meta.getColumnCount ();
                row.setColumnCount (cColumns);

                for (int i = 0; i < cColumns; i++)
                {
                    row.setColumnName (i , meta.getColumnName (i + 1));
                    row.setData (i , resultSet.getObject (i + 1));
                }

                meta = null;
            }
        }
        catch (SQLException ex)
        {
            exception = ex;
        }

        if (resultSet != null)
        {
            resultSet.close ();
            resultSet = null;
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
            if (row != null)
            {
                row.release ();
                row = null;
            }
            throw exception;
        }

        return row;

    } // doQuery_SingleRow

    public Vector doQuery_MultiRows (String strJndiName_DataSource , String strSql , int cMaxRows) throws SQLException ,
        NamingException
    {
        Vector vector = null;

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        SQLException exception = null;
        try
        {
            getDataSource (strJndiName_DataSource);
            connection = m_dataSource.getConnection ();

            statement = connection.prepareStatement (strSql ,
                ResultSet.TYPE_SCROLL_INSENSITIVE ,
                ResultSet.CONCUR_READ_ONLY);

            resultSet = statement.executeQuery ();

            ResultSetMetaData meta = resultSet.getMetaData ();

            vector = new Vector ();
            int cRows = 0;
            int cColumns = meta.getColumnCount ();

            while (resultSet.next ())
            {
                QueryResultRowDAO row = new QueryResultRowDAO ();

                row.setColumnCount (cColumns);
                for (int i = 0; i < cColumns; i++)
                {
                    row.setData (i , resultSet.getObject (i + 1));
                }

                vector.add (row);
                cRows++;
                if (cMaxRows > 0 && cRows >= cMaxRows)
                {
                    break;
                }
            }

            meta = null;
        }
        catch (SQLException ex)
        {
            exception = ex;
        }

        if (resultSet != null)
        {
            resultSet.close ();
            resultSet = null;
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
            if (vector != null)
            {
                for (int i = 0; i < vector.size (); i++)
                {
                    ( (QueryResultRow) vector.get (i)).release ();
                }
                vector.clear ();
                vector = null;
            }
            throw exception;
        }

        return vector;

    } // doQuery_MultiRows
    
    public List doQueryInMap(final String strJndiName_DataSource ,  final String strSql , final Object[] values ) throws SQLException ,
    NamingException
{
        List list = null;

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
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
				statement.setObject( i+1 , values[i] );
//				statement.setObject( i+1 , values[i], SQLType[i] );
//				statement.setObject( i+1 , values[i], SQLType[i], scale[i] );
			}
			resultSet = statement.executeQuery ();
			log.debug("===执行sql语句成功===");
			
            ResultSetMetaData meta = resultSet.getMetaData ();

            list = new ArrayList ();
            //there haven't use
            int cRows = 0;
            int cColumns = meta.getColumnCount ();

            //do in this way can Improve performance 
            String[] aliases = new String[cColumns];
            for ( int i = 0; i < cColumns; i ++ ) {
            	aliases[i] = meta.getColumnName( i+1 );
            }
            
            while (resultSet.next ()){
            	//different from doQueryInArray
                Map row = new HashMap( cColumns );
        		for ( int i = 0; i < cColumns; i ++ ) {
//        			String alias = meta.getColumnName( i+1 );
        			String alias = aliases[i];
        			if ( alias != null ) {
        				row.put( alias, resultSet.getObject (i + 1) );
        			}
        		}
        		list.add (row);
        		cRows++;
            }
            meta = null;
        }
        catch (SQLException ex)
        {
            exception = ex;
        }

        if (resultSet != null)
        {
            resultSet.close ();
            resultSet = null;
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
            if (list != null)
            {
            	list.clear ();
            	list = null;
            }
            throw exception;
        }
        return list;
}
    public List doQueryInMap(final String strJndiName_DataSource ,  final String strSql , final Object[] values, final int[] SQLType ) throws SQLException ,
    NamingException
{
        List list = null;

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
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
			resultSet = statement.executeQuery ();
			log.debug("===执行sql语句成功===");
			
            ResultSetMetaData meta = resultSet.getMetaData ();

            list = new ArrayList ();
            //there haven't use
            int cRows = 0;
            int cColumns = meta.getColumnCount ();

            //do in this way can Improve performance 
            String[] aliases = new String[cColumns];
            for ( int i = 0; i < cColumns; i ++ ) {
            	aliases[i] = meta.getColumnName( i+1 );
            }
            
            while (resultSet.next ()){
            	//different from doQueryInArray
                Map row = new HashMap( cColumns );
        		for ( int i = 0; i < cColumns; i ++ ) {
//        			String alias = meta.getColumnName( i+1 );
        			String alias = aliases[i];
        			if ( alias != null ) {
        				row.put( alias, resultSet.getObject (i + 1) );
        			}
        		}
        		list.add (row);
        		cRows++;
            }
            meta = null;
        }
        catch (SQLException ex)
        {
            exception = ex;
        }

        if (resultSet != null)
        {
            resultSet.close ();
            resultSet = null;
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
            if (list != null)
            {
            	list.clear ();
            	list = null;
            }
            throw exception;
        }
        return list;
}
    public List doQueryInArray(final String strJndiName_DataSource ,  final String strSql , final Object[] values ) throws SQLException ,
    NamingException
{
        List list = null;

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
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
				//parameterIndex the first parameter is 1, the second is 2, ...
				statement.setObject( i+1 , values[i] );
//				statement.setDate(parameterIndex, x);
//				statement.setDate(parameterIndex, x, cal);
//				statement.setObject( i+1 , values[i], SQLType[i] );
//				statement.setObject( i+1 , values[i], SQLType[i], scale[i] );
			}
			resultSet = statement.executeQuery ();
			log.debug("===执行sql语句成功===");
			
            ResultSetMetaData meta = resultSet.getMetaData ();

            list = new ArrayList ();
            //there haven't use
            int cRows = 0;
            int cColumns = meta.getColumnCount ();

            while (resultSet.next ()){
            	//different from doQueryInMap
                List row = new ArrayList( cColumns );
        		for ( int i = 0; i < cColumns; i ++ ) {
        			row.add( resultSet.getObject (i + 1) );
        		}
        		list.add (row);
        		cRows ++ ;
            }
            meta = null;
        }
        catch (SQLException ex)
        {
            exception = ex;
        }

        if (resultSet != null)
        {
            resultSet.close ();
            resultSet = null;
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
            if (list != null)
            {
            	list.clear ();
            	list = null;
            }
            throw exception;
        }
        return list;
}
    
    public List doQueryInArray(final String strJndiName_DataSource ,  final String strSql , final Object[] values, final int[] SQLType ) throws SQLException ,
    NamingException
{
        List list = null;

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
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
			resultSet = statement.executeQuery ();
			log.debug("===执行sql语句成功===");
			
            ResultSetMetaData meta = resultSet.getMetaData ();

            list = new ArrayList ();
            //there haven't use
            int cRows = 0;
            int cColumns = meta.getColumnCount ();

            while (resultSet.next ()){
            	//different from doQueryInMap
                List row = new ArrayList( cColumns );
        		for ( int i = 0; i < cColumns; i ++ ) {
        			row.add( resultSet.getObject (i + 1) );
        		}
        		list.add (row);
        		cRows ++ ;
            }
            meta = null;
        }
        catch (SQLException ex)
        {
            exception = ex;
        }

        if (resultSet != null)
        {
            resultSet.close ();
            resultSet = null;
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
            if (list != null)
            {
            	list.clear ();
            	list = null;
            }
            throw exception;
        }
        return list;
}
    
    public List doQueryByPageInMap(final String strJndiName_DataSource , String strSql , final Object[] values, final int offset, final int pageSize  ) throws SQLException ,
    NamingException
{
        List list = null;

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        SQLException exception = null;
        
        strSql = "select * " +
        		"from (" +
        		"    select superjava_table1.*, rownum rc " +
        		"	 from ( " +strSql +
        		"    ) superjava_table1 " +
        		"    where rownum<=" + ( offset + pageSize ) + " " +
        		") " +
        		"where rc>"+offset+" ";
        try
        {
            getDataSource (strJndiName_DataSource);
            connection = m_dataSource.getConnection ();

            log.debug(strSql);
            statement = connection.prepareStatement ( strSql ,
                ResultSet.TYPE_SCROLL_INSENSITIVE ,
                ResultSet.CONCUR_READ_ONLY );
            
			log.debug("===给sql语句中的参数进行赋值===");
			for(int i=0,j=values.length; i<j; i++){
				statement.setObject( i+1 , values[i] );
//				statement.setObject( i+1 , values[i], SQLType[i] );
//				statement.setObject( i+1 , values[i], SQLType[i], scale[i] );
			}
			resultSet = statement.executeQuery ();
			log.debug("===执行sql语句成功===");
			
            ResultSetMetaData meta = resultSet.getMetaData ();

            list = new ArrayList ();
            //there haven't use
            int cRows = 0;
            int cColumns = meta.getColumnCount ();

            //do in this way can Improve performance 
            String[] aliases = new String[cColumns];
            for ( int i = 0; i < cColumns; i ++ ) {
            	aliases[i] = meta.getColumnName( i+1 );
            }
            
            while (resultSet.next ()){
            	//different from doQueryInArray
                Map row = new HashMap( cColumns );
        		for ( int i = 0; i < cColumns; i ++ ) {
//        			String alias = meta.getColumnName( i+1 );
        			String alias = aliases[i];
        			if ( alias != null ) {
        				row.put( alias, resultSet.getObject (i + 1) );
        			}
        		}
        		list.add (row);
        		cRows++;
            }
            meta = null;
        }
        catch (SQLException ex)
        {
            exception = ex;
        }

        if (resultSet != null)
        {
            resultSet.close ();
            resultSet = null;
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
            if (list != null)
            {
            	list.clear ();
            	list = null;
            }
            throw exception;
        }
        return list;
} 
    public List doQueryByPageInMap(final String strJndiName_DataSource , String strSql , final Object[] values, final int[] SQLType, final int offset, final int pageSize  ) throws SQLException ,
    NamingException
{
        List list = null;

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        SQLException exception = null;
        
        strSql = "select * " +
        		"from (" +
        		"    select superjava_table1.*, rownum rc " +
        		"	 from ( " +strSql +
        		"    ) superjava_table1 " +
        		"    where rownum<=" + ( offset + pageSize ) + " " +
        		") " +
        		"where rc>"+offset+" ";
		
        try
        {
            getDataSource (strJndiName_DataSource);
            connection = m_dataSource.getConnection ();

            log.debug(strSql);
            statement = connection.prepareStatement ( strSql ,
                ResultSet.TYPE_SCROLL_INSENSITIVE ,
                ResultSet.CONCUR_READ_ONLY );
            
			log.debug("===给sql语句中的参数进行赋值===");
			for(int i=0,j=values.length; i<j; i++){
//				statement.setObject( i+1 , values[i] );
				statement.setObject( i+1 , values[i], SQLType[i] );
//				statement.setObject( i+1 , values[i], SQLType[i], scale[i] );
			}
			resultSet = statement.executeQuery ();
			log.debug("===执行sql语句成功===");
			
            ResultSetMetaData meta = resultSet.getMetaData ();

            list = new ArrayList ();
            //there haven't use
            int cRows = 0;
            int cColumns = meta.getColumnCount ();

            //do in this way can Improve performance 
            String[] aliases = new String[cColumns];
            for ( int i = 0; i < cColumns; i ++ ) {
            	aliases[i] = meta.getColumnName( i+1 );
            }
            
            while (resultSet.next ()){
            	//different from doQueryInArray
                Map row = new HashMap( cColumns );
        		for ( int i = 0; i < cColumns; i ++ ) {
//        			String alias = meta.getColumnName( i+1 );
        			String alias = aliases[i];
        			if ( alias != null ) {
        				row.put( alias, resultSet.getObject (i + 1) );
        			}
        		}
        		list.add (row);
        		cRows++;
            }
            meta = null;
        }
        catch (SQLException ex)
        {
            exception = ex;
        }

        if (resultSet != null)
        {
            resultSet.close ();
            resultSet = null;
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
            if (list != null)
            {
            	list.clear ();
            	list = null;
            }
            throw exception;
        }
        return list;
} 
    public List doQueryByPageInArray(final String strJndiName_DataSource , String strSql , final Object[] values, final int offset, final int pageSize  ) throws SQLException ,
    NamingException
{
        List list = null;

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        SQLException exception = null;
        
        strSql = "select * " +
        		"from (" +
        		"    select superjava_table1.*, rownum rc " +
        		"	 from ( " +strSql +
        		"    ) superjava_table1 " +
        		"    where rownum<=" + ( offset + pageSize ) + " " +
        		") " +
        		"where rc>"+offset+" ";
		
        try
        {
            getDataSource (strJndiName_DataSource);
            connection = m_dataSource.getConnection ();

            log.debug(strSql);
            statement = connection.prepareStatement ( strSql ,
                ResultSet.TYPE_SCROLL_INSENSITIVE ,
                ResultSet.CONCUR_READ_ONLY );
            
			log.debug("===给sql语句中的参数进行赋值===");
			for(int i=0,j=values.length; i<j; i++){
				statement.setObject( i+1 , values[i] );
//				statement.setObject( i+1 , values[i], SQLType[i] );
//				statement.setObject( i+1 , values[i], SQLType[i], scale[i] );
			}
			resultSet = statement.executeQuery ();
			log.debug("===执行sql语句成功===");
			
            ResultSetMetaData meta = resultSet.getMetaData ();

            list = new ArrayList ();
            //there haven't use
            int cRows = 0;
            int cColumns = meta.getColumnCount ();

            //do in this way can Improve performance 
            String[] aliases = new String[cColumns];
            for ( int i = 0; i < cColumns; i ++ ) {
            	aliases[i] = meta.getColumnName( i+1 );
            }
            
            while (resultSet.next ()){
            	//different from doQueryInArray
                List row = new ArrayList( cColumns );
        		for ( int i = 0; i < cColumns; i ++ ) {
//        			String alias = meta.getColumnName( i+1 );
        			String alias = aliases[i];
        			if ( alias != null ) {
        				row.add( resultSet.getObject (i + 1) );
        			}
        		}
        		list.add (row);
        		cRows++;
            }
            meta = null;
        }
        catch (SQLException ex)
        {
            exception = ex;
        }

        if (resultSet != null)
        {
            resultSet.close ();
            resultSet = null;
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
            if (list != null)
            {
            	list.clear ();
            	list = null;
            }
            throw exception;
        }
        return list;
} 
    public List doQueryByPageInArray(final String strJndiName_DataSource ,  String strSql , final Object[] values, final int[] SQLType, final int offset, final int pageSize  ) throws SQLException ,
    NamingException
{
        List list = null;

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        SQLException exception = null;
        
        strSql = "select * " +
        		"from (" +
        		"    select superjava_table1.*, rownum rc " +
        		"	 from ( " +strSql +
        		"    ) superjava_table1 " +
        		"    where rownum<=" + ( offset + pageSize ) + " " +
        		") " +
        		"where rc>"+offset+" ";
		
        try
        {
            getDataSource (strJndiName_DataSource);
            connection = m_dataSource.getConnection ();

            log.debug(strSql);
            statement = connection.prepareStatement ( strSql ,
                ResultSet.TYPE_SCROLL_INSENSITIVE ,
                ResultSet.CONCUR_READ_ONLY );
            
			log.debug("===给sql语句中的参数进行赋值===");
			for(int i=0,j=values.length; i<j; i++){
//				statement.setObject( i+1 , values[i] );
				statement.setObject( i+1 , values[i], SQLType[i] );
//				statement.setObject( i+1 , values[i], SQLType[i], scale[i] );
			}
			resultSet = statement.executeQuery ();
			log.debug("===执行sql语句成功===");
			
            ResultSetMetaData meta = resultSet.getMetaData ();

            list = new ArrayList ();
            //there haven't use
            int cRows = 0;
            int cColumns = meta.getColumnCount ();

            //do in this way can Improve performance 
            String[] aliases = new String[cColumns];
            for ( int i = 0; i < cColumns; i ++ ) {
            	aliases[i] = meta.getColumnName( i+1 );
            }
            
            while (resultSet.next ()){
            	//different from doQueryInArray
                List row = new ArrayList( cColumns );
        		for ( int i = 0; i < cColumns; i ++ ) {
//        			String alias = meta.getColumnName( i+1 );
        			String alias = aliases[i];
        			if ( alias != null ) {
        				row.add( resultSet.getObject (i + 1) );
        			}
        		}
        		list.add (row);
        		cRows++;
            }
            meta = null;
        }
        catch (SQLException ex)
        {
            exception = ex;
        }

        if (resultSet != null)
        {
            resultSet.close ();
            resultSet = null;
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
            if (list != null)
            {
            	list.clear ();
            	list = null;
            }
            throw exception;
        }
        return list;
} 
    public QueryResult doSelect (String strJndiName_DataSource , QueryInfo queryInfo) throws SQLException ,
        NamingException
    {
        return _doSelect (strJndiName_DataSource , queryInfo , null , 0 , 0);
    }

    public QueryResult doSelect (DBStatement dbStatement , int nPageSize , int nPageNo) throws SQLException ,
        NamingException
    {
        //ErrUtil.writeDebug3 ("++++==== doSelect: " + nPageSize + ", " + nPageNo);
        return _doSelect (dbStatement.getDataSource () , null , dbStatement , nPageSize , nPageNo);
    }

    private QueryResult _doSelect (String strJndiName_DataSource , QueryInfo queryInfo , DBStatement dbStatement ,
                                   int nPageSize , int nPageNo) throws SQLException , NamingException
    {
        QueryResultDAO queryResultDAO = new QueryResultDAO ();
        Connection connection = null;
        PreparedStatement statementCount = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        ResultSet resultSetCount = null;
        SQLException exception = null;
        try
        {
            getDataSource (strJndiName_DataSource);
            connection = m_dataSource.getConnection ();
            String strSql;
            if (queryInfo == null)
            {
                strSql = dbStatement.getTranslatedSql ();
            }
            else
            {
                strSql = queryInfo.m_strSql;
            }

            int nRowCount = 0;
            String strCountSql = "select count(*) from (" + strSql + ")";

            //计算数量
            statementCount = connection.prepareStatement (strCountSql , ResultSet.TYPE_SCROLL_INSENSITIVE ,
                ResultSet.CONCUR_READ_ONLY);
            if (queryInfo == null)
            {

                for (int i = 0; i < dbStatement.getParameterCount (); i++)
                {
                    Parameter param = dbStatement.getParameter (i);
                    statementCount.setString (i + 1 , param != null ? param.getString () : "");

                }
            }
            else
            {
                for (int nParam = 0; nParam < queryInfo.m_aobjParams.length; nParam++)
                {
                    //ErrUtil.writeDebug3 ("++++==== param-from-queryInfo: " + queryInfo.m_aobjParams[nParam]);
                    setStatementObject (statementCount , nParam + 1 ,
                                        queryInfo.m_aobjParams[nParam] , queryInfo.m_anParamSqlTypes[nParam]);
                }
            }
            resultSetCount = statementCount.executeQuery ();

            if (!resultSetCount.isAfterLast ())
            {
                resultSetCount.next ();
                nRowCount = resultSetCount.getInt (1);
            }

            if ( (nPageSize > 0) && (nPageNo > 0))
            {
                int nFirstRowNoInCurPage = (nPageNo - 1) * nPageSize + 1;

                strSql = "select * from (select rownum rn, t.* from (" + strSql + ") t ) where rn >= " +
                    nFirstRowNoInCurPage + " and rn < " + (nFirstRowNoInCurPage + nPageSize);
            }
            else if (nPageSize > 0)
            {
                strSql = "select * from (select rownum rn, t.* from (" + strSql + ") t ) where rn > 0 and rn <= " +
                    nPageSize;
            }
            else
            {
                strSql = "select * from (select rownum rn, t.* from (" + strSql + ") t ) where rn > 0 and rn <= 100";
            }

            statement = connection.prepareStatement (strSql ,
                ResultSet.TYPE_SCROLL_INSENSITIVE ,
                ResultSet.CONCUR_READ_ONLY);

            //分页查询
            if (queryInfo == null)
            {
                for (int i = 0; i < dbStatement.getParameterCount (); i++)
                {
                    Parameter param = dbStatement.getParameter (i);
                    statement.setString (i + 1 , param != null ? param.getString () : "");

                }

            }
            else
            {
                for (int nParam = 0; nParam < queryInfo.m_aobjParams.length; nParam++)
                {
                    setStatementObject (statement , nParam + 1 ,
                                        queryInfo.m_aobjParams[nParam] , queryInfo.m_anParamSqlTypes[nParam]);
                }
            }

            resultSet = statement.executeQuery ();
            ResultSetMetaData meta = resultSet.getMetaData ();

            Vector vector = new Vector ();
            int cColumns = meta.getColumnCount ();
            int cRows = 0;

            if ( (nPageSize > 0) && (nPageNo > 0))
            {
                cRows = nRowCount;
                queryResultDAO.setCurPageNo (nPageNo);
                queryResultDAO.setPageSize (nPageSize);

                while (resultSet.next ())
                {
                    QueryResultRowDAO row = new QueryResultRowDAO ();
                    row.setColumnCount (cColumns - 1);
                    for (int i = 0; i < cColumns - 1; i++)
                    {
                        row.setData (i , resultSet.getObject (i + 2));
                    }
                    vector.add (row);
                }
            }
            else
            {
                while (resultSet.next ())
                {
                    QueryResultRowDAO row = new QueryResultRowDAO ();

                    row.setColumnCount (cColumns - 1);

                    for (int i = 0; i < cColumns - 1; i++)
                    {
                        row.setData (i , resultSet.getObject (i + 2));
                    }
                    vector.add (row);
                    cRows++;
                    if (queryInfo != null)
                    {
                        if ( (queryInfo.m_cMaxRows > 0) && (cRows >= queryInfo.m_cMaxRows))
                        {
                            if (queryInfo.m_bIsRowCountRequired)
                            {
                                cRows = nRowCount;
                            }
                            break;
                        }
                    }
                }
            }
            queryResultDAO.setTotalRowCount (cRows);

            queryResultDAO.setMetaData (new QueryResultMetaDAO (meta));
            queryResultDAO.setRows (vector);

        }
        catch (SQLException ex)
        {
            exception = ex;
        }
        catch (Exception ex)
        {

        }
        finally
        {
            if (resultSetCount != null)
            {
                resultSetCount.close ();
                resultSetCount = null;
            }
            if (statementCount != null)
            {
                statementCount.close ();
                statementCount = null;
            }

            if (resultSet != null)
            {
                resultSet.close ();
                resultSet = null;
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
        }

        if (exception != null)
        {
            if (queryResultDAO != null)
            {
                queryResultDAO.release ();
                queryResultDAO = null;
            }
            throw exception;
        }

        return queryResultDAO;

    } // _doSelect

    protected void getDataSource (String strJndiName_DataSource) throws NamingException
    {
        if (m_dataSource != null)
        {
            if (m_strJndiName_DataSource == null)
            {
                m_dataSource = null;
            }
            else if (strJndiName_DataSource.compareTo (m_strJndiName_DataSource) != 0)
            {
                m_dataSource = null;
            }
        }

        if (m_dataSource == null)
        {
            m_dataSource = (DataSource) JndiUtil.getJndiObject (strJndiName_DataSource);
            m_strJndiName_DataSource = strJndiName_DataSource;
        }

    } // getDataSource

    /**
     * Given a SQL PreparedStatement, a column, and a parameter, set the parameter into the Statement.
     * logic for null detection, to properly call "setNull" in that case.
     */
    protected void setStatementObject (PreparedStatement statement ,
                                       int nColumn , Object objProperty , int nSqlType) throws SQLException
    {
        if (objProperty == null)
        {
            statement.setNull (nColumn , nSqlType);
            //ErrUtil.writeDebug2 ("SQL param(" + nColumn + ") value = null, sqlType = " + nSqlType);
        }
        else
        {
            switch (nSqlType)
            {
                case Types.INTEGER:
                case Types.NUMERIC:
                    if (objProperty instanceof Integer)
                    {
                        int nValue = ( (Integer) objProperty).intValue ();
                        statement.setInt (nColumn , nValue);
                        //ErrUtil.writeDebug2 ("SQL param(" + nColumn + ") (Integer) value = " + nValue + ", sqlType = " +
//                                             nSqlType);
                    }
                    else if (objProperty instanceof BigDecimal)
                    {
                        int nValue = ( (BigDecimal) objProperty).intValue ();
                        statement.setInt (nColumn , nValue);
                        //ErrUtil.writeDebug2 ("SQL param(" + nColumn + ") (BigDecimal) value = " + nValue +
//                                             ", sqlType = " + nSqlType);
                    }
                    break;

                case Types.CHAR:
                case Types.VARCHAR:
                    statement.setString (nColumn , (String) objProperty);
                    //ErrUtil.writeDebug2 ("SQL param(" + nColumn + ") (String) value = " + (String) objProperty +
//                                         ", sqlType = " + nSqlType);
                    break;

                case Types.FLOAT:
                {
                    float fValue = ( (Float) objProperty).floatValue ();
                    statement.setFloat (nColumn , fValue);
                    //ErrUtil.writeDebug2 ("SQL param(" + nColumn + ") (Float) value = " + fValue + ", sqlType = " +
//                                         nSqlType);
                }
                break;

                case Types.DOUBLE:
                {
                    double dValue = ( (Double) objProperty).doubleValue ();
                    statement.setDouble (nColumn , dValue);
                    //ErrUtil.writeDebug2 ("SQL param(" + nColumn + ") (Double) value = " + dValue + ", sqlType = " +
//                                         nSqlType);
                }
                break;

                case Types.BIT:
                case Types.BOOLEAN:
                {
                    boolean bValue = ( (Boolean) objProperty).booleanValue ();
                    statement.setBoolean (nColumn , bValue);
                    //ErrUtil.writeDebug2 ("SQL param(" + nColumn + ") (Boolean) value = " + bValue + ", sqlType = " +
//                                         nSqlType);
                }
                break;

                case Types.DATE:
                    statement.setDate (nColumn , (Date) objProperty);
                    //ErrUtil.writeDebug2 ("SQL param(" + nColumn + ") (Date) value = " + objProperty.toString () +
//                                         ", sqlType = " + nSqlType);
                    break;

                case Types.TIME:
                    statement.setTime (nColumn , (Time) objProperty);
                    //ErrUtil.writeDebug2 ("SQL param(" + nColumn + ") (Time) value = " + objProperty.toString () +
//                                         ", sqlType = " + nSqlType);
                    break;

                case Types.TIMESTAMP:
                    statement.setTimestamp (nColumn , (Timestamp) objProperty);
                    //ErrUtil.writeDebug2 ("SQL param(" + nColumn + ") (Timestamp) value = " + objProperty.toString () +
//                                         ", sqlType = " + nSqlType);
                    break;

                default:
                    //ErrUtil.writeErrMsg ("DbReaderBulterDAO::setStatementObject: unrecognized type:" + nSqlType);
            }
        }

    } // setStatementObject

    /****
      private void setStatementObject(PreparedStatement statement,
        int nColumn, Object objProperty, Class classProperty)
        throws SQLException
         {
       String strClassName;
       if (objProperty != null)
        strClassName = objProperty.getClass().getName();
       else
        strClassName = classProperty.getName();
       if (objProperty != null)
        {
        if (strClassName.equals("java.lang.Integer"))
                statement.setInt(nColumn, ((Integer) objProperty).intValue());
        else if (strClassName.equals("java.lang.String"))
                statement.setString(nColumn, (String) objProperty);
        else if (strClassName.equals("java.lang.Float"))
                statement.setFloat(nColumn, ((Float) objProperty).floatValue());
        else if (strClassName.equals("java.lang.Boolean"))
                statement.setBoolean(nColumn, ((Boolean) objProperty).booleanValue());
        else if (strClassName.equals("java.sql.Timestamp"))
                statement.setTimestamp(nColumn, (Timestamp) objProperty);
        else if (strClassName.equals("java.sql.Double"))
                statement.setDouble(nColumn, ((Double) objProperty).doubleValue());
        else if (strClassName.equals("java.math.BigDecimal"))
                statement.setInt(nColumn, ((BigDecimal) objProperty).intValue());
        else
         //ErrUtil.writeLog("DbBulterDAO::setStatementObject trying to deal with unrecognized type:" + strClassName);
       }
        else
        {
        int nSqlType = 0;
        if (strClassName.equals("java.lang.Integer"))
                nSqlType = Types.INTEGER;
        else if (strClassName.equals("java.lang.String"))
                nSqlType = Types.VARCHAR;
        else if (strClassName.equals("java.lang.Float"))
                nSqlType = Types.FLOAT;
        else if (strClassName.equals("java.lang.Boolean"))
                nSqlType = Types.BIT;
        else if (strClassName.equals("java.sql.Timestamp"))
                nSqlType = Types.TIMESTAMP;
        else if (strClassName.equals("java.sql.Double"))
                nSqlType = Types.DOUBLE;
        else if (strClassName.equals("java.math.BigDecimal"))
                nSqlType = Types.INTEGER;
        else
         //ErrUtil.writeLog("DbBulterDAO::setStatementObject trying to deal with unrecognized type:" + strClassName);
        statement.setNull(nColumn, nSqlType);
       }
      }   // setStatementObject
     ****/

    private int getRowCount (ResultSet resultSet) throws SQLException
    {
        int cRows = 0;

        if (resultSet != null)
        {
            if (resultSet.getType () != ResultSet.TYPE_FORWARD_ONLY)
            {
                resultSet.last ();
                cRows = resultSet.getRow ();
            }
        }

        return cRows;

    } // getRowCount

    private String transGbk2Iso (String strSource) throws Exception
    {
        try
        {
            return new String (strSource.getBytes () , "ISO-8859-1");
        }
        catch (Exception ex)
        {
            throw ex;
        }
    }

} // class DbBulterDAO
