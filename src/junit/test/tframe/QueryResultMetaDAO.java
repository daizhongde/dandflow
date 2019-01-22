package junit.test.tframe;

/**
 * <p>Title: server-side framework </p>
 * <p>Description: server-side framework powered by tframe</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: copote</p>
 * @author wg
 * @version 1.0
 */

import java.sql.Types;
import java.sql.SQLException;
import java.sql.ResultSetMetaData;

public class QueryResultMetaDAO implements QueryResultMeta
{
    public static final char CHAR_SeparatorBetweenTableAndField = '.';

    private int m_cCols = 0;

    private String[] m_astrColTables = null;
    private String[] m_astrColNames = null;
    private String[] m_astrColLabels = null;
    private int[] m_anColSqlTypes = null;
    private int[] m_anColDisplaySizes = null;

    private int[] m_anColNullables = null;
    private boolean[] m_abReadOnlys = null;
    private boolean[] m_abWritables = null;

    public QueryResultMetaDAO (ResultSetMetaData resultSetMetaData)
    {
        try
        {
            m_cCols = resultSetMetaData.getColumnCount ();
            if (m_cCols > 0)
            {
                m_astrColTables = new String[m_cCols];
                m_astrColNames = new String[m_cCols];
                m_astrColLabels = new String[m_cCols];
                m_anColSqlTypes = new int[m_cCols];
                m_anColDisplaySizes = new int[m_cCols];

                m_anColNullables = new int[m_cCols];
                m_abReadOnlys = new boolean[m_cCols];
                m_abWritables = new boolean[m_cCols];

                for (int i = 0; i < m_cCols; i++)
                {
                    m_astrColTables[i] = resultSetMetaData.getTableName (i + 1);
                    m_astrColNames[i] = resultSetMetaData.getColumnName (i + 1);
                    m_astrColLabels[i] = resultSetMetaData.getColumnLabel (i + 1);
                    m_anColSqlTypes[i] = resultSetMetaData.getColumnType (i + 1);
                    m_anColDisplaySizes[i] = resultSetMetaData.getColumnDisplaySize (i + 1);

                    m_anColNullables[i] = resultSetMetaData.isNullable (i + 1);
                    m_abReadOnlys[i] = resultSetMetaData.isReadOnly (i + 1);
                    m_abWritables[i] = resultSetMetaData.isWritable (i + 1);
                }
            }
        }
        catch (SQLException ex)
        {
            release ();
//            ErrUtil.writeDebug2 ("QueryResultMetaDAO reported:" + ex.toString ());
        }

    } // QueryResultMetaDAO

    public int getColumnCount ()
    {
        return m_cCols;
    }

    public String getTableName (int nColumnIndex)
    {
        if (m_astrColTables == null)
        {
            return "";
        }

        if (nColumnIndex >= 0 && nColumnIndex < m_cCols)
        {
            return m_astrColTables[nColumnIndex];
        }

        return "";
    }

    public String getColumnName (int nColumnIndex)
    {
        if (m_astrColNames == null)
        {
            return "";
        }

        if (nColumnIndex >= 0 && nColumnIndex < m_cCols)
        {
            return m_astrColNames[nColumnIndex];
        }

        return "";
    }

    public String getColumnFullName (int nColumnIndex)
    {
        if (m_astrColNames == null)
        {
            return "";
        }

        if (nColumnIndex >= 0 && nColumnIndex < m_cCols)
        {
            return m_astrColTables[nColumnIndex] + CHAR_SeparatorBetweenTableAndField + m_astrColNames[nColumnIndex];
        }

        return "";
    }

    public String getColumnLabel (int nColumnIndex)
    {
        if (m_astrColLabels == null)
        {
            return "";
        }

        if (nColumnIndex >= 0 && nColumnIndex < m_cCols)
        {
            return m_astrColLabels[nColumnIndex];
        }

        return "";
    }

    public int getColumnSqlType (int nColumnIndex)
    {
        if (m_anColSqlTypes == null)
        {
            return Types.NULL;
        }

        if (nColumnIndex >= 0 && nColumnIndex < m_cCols)
        {
            return m_anColSqlTypes[nColumnIndex];
        }

        return Types.NULL;
    }

    public int getColumnDisplaySize (int nColumnIndex)
    {
        if (m_anColDisplaySizes == null)
        {
            return 0;
        }

        if (nColumnIndex >= 0 && nColumnIndex < m_cCols)
        {
            return m_anColDisplaySizes[nColumnIndex];
        }

        return 0;
    }

    public int isNullable (int nColumnIndex)
    {
        if (m_anColNullables == null)
        {
            return ResultSetMetaData.columnNullableUnknown;
        }

        if (nColumnIndex >= 0 && nColumnIndex < m_cCols)
        {
            return m_anColNullables[nColumnIndex];
        }

        return ResultSetMetaData.columnNullableUnknown;
    }

    public boolean isReadOnly (int nColumnIndex)
    {
        if (m_abReadOnlys == null)
        {
            return false;
        }

        if (nColumnIndex >= 0 && nColumnIndex < m_cCols)
        {
            return m_abReadOnlys[nColumnIndex];
        }

        return false;
    }

    public boolean isWritable (int nColumnIndex)
    {
        if (m_abWritables == null)
        {
            return true;
        }

        if (nColumnIndex >= 0 && nColumnIndex < m_cCols)
        {
            return m_abWritables[nColumnIndex];
        }

        return true;
    }

    public void release ()
    {
        m_cCols = 0;

        m_astrColTables = null;
        m_astrColNames = null;
        m_astrColLabels = null;
        m_anColSqlTypes = null;
        m_anColDisplaySizes = null;

        m_anColNullables = null;
        m_abReadOnlys = null;
        m_abWritables = null;

    } // release

} // class QueryResultMetaDAO
