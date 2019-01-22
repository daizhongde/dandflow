package junit.test.tframe;

/**
 * <p>Title: server-side framework </p>
 * <p>Description: server-side framework powered by tframe</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: copote</p>
 * @author wg
 * @version 1.0
 */

import java.util.Vector;
import java.sql.SQLException;
import java.sql.Types;

public class QueryResultDAO implements QueryResult
{
    private QueryResultMeta m_meta = null;
    private Vector m_aRows = null;

    private int m_cRows = 0;
    private int m_nCurPageNo = 0, m_nPageSize = 0;

    public void setMetaData(QueryResultMeta meta)
    {
        m_meta = meta;
    }

    public void setRows(Vector rows)
    {
        m_aRows = rows;
    }

    public QueryResultMeta getQueryResultMeta()
    {
        return m_meta;
    }

    public int getRowCount()
    {
        return m_cRows;

        /****
                 if (m_aRows == null)
            return 0;
                 return m_aRows.size();
         ****/

    } // getRowCount

    public Object fetchData(int nRowIndex, int nColIndex)
    {
        Object object = null;
        if ( (nRowIndex < 0) || (nRowIndex >= m_cRows))
        {
            return null;
        }

        int nFirstRowIndex = 0;
        if ( (m_nCurPageNo > 0) && (m_nPageSize > 0))
        {
            nFirstRowIndex = (m_nCurPageNo - 1) * m_nPageSize;

            if ( (nRowIndex < nFirstRowIndex) || (nRowIndex > nFirstRowIndex + m_nPageSize))
            {
//                ErrUtil.writeDebug2("++++ [QueryResultDAO::fetchData]: ERROR: row-index[" + nRowIndex +
//                                    "] out of range by current-page[" + m_nCurPageNo + "], page-size[" + m_nPageSize +
//                                    "]");
                return null;
            }
        }

        if (m_aRows == null)
        {
            return null;
        }

        nRowIndex -= nFirstRowIndex;
        if (nRowIndex >= 0 && nRowIndex < m_aRows.size())
        {
            QueryResultRow row = (QueryResultRow) m_aRows.get(nRowIndex);

            object = row.getColumn(nColIndex);
        }
        return object;

    } // fetchData

    public int findColumnIndex_ByName(String strColumnName)
    {
        if ( (m_meta == null) || (strColumnName == null))
        {
            return -1;
        }

        int cCols = m_meta.getColumnCount();
        for (int i = 0; i < cCols; i++)
        {
            if (strColumnName.compareToIgnoreCase(m_meta.getColumnName(i)) == 0)
            {
                return i;
            }
        }

        return -1;

    } // findColumnIndex_ByName

    public void setTotalRowCount(int cRows)
    {
        m_cRows = cRows;
    }

    public void setPageSize(int nPageSize)
    {
        m_nPageSize = nPageSize;
    }

    public void setCurPageNo(int nPageNo)
    {
        m_nCurPageNo = nPageNo;
    }

    public void release()
    {
        if (m_meta != null)
        {
            m_meta.release();
            m_meta = null;
        }

        if (m_aRows != null)
        {
            for (int i = 0; i < m_aRows.size(); i++)
            {
                ( (QueryResultRow) m_aRows.get(i)).release();
            }
            m_aRows = null;
        }

    } // release

} // class QueryResultDAO
