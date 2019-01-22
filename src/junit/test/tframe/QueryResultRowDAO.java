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

public class QueryResultRowDAO implements QueryResultRow
{
    private int m_cColumns = 0;
    private String[] m_aColumnNames = null;
    private Object[] m_aData = null;

    private QueryResultDAO m_queryResultDAO = null;

    public void setColumnCount(int cColumns)
    {
        m_cColumns = cColumns;
        m_aData = new Object[cColumns];
    }

    public void setColumnName(int nColumnIndex, String strName)
    {
        if (m_cColumns == 0)
        {
            return;
        }

        if (m_aColumnNames == null)
        {
            m_aColumnNames = new String[m_cColumns];

        }
        if (nColumnIndex >= 0 && nColumnIndex < m_cColumns)
        {
            m_aColumnNames[nColumnIndex] = strName;

        }
    } // setColumnName

    public void setData(int nColumnIndex, Object objData)
    {
        if (m_cColumns == 0)
        {
            return;
        }

        if (nColumnIndex >= 0 && nColumnIndex < m_cColumns)
        {
            m_aData[nColumnIndex] = objData;

        }
    } // setData

/////
    public Object getColumn(int nColumnIndex) // nColumnIndex starts from 0
    {
        Object object = null;
        if (m_aData == null)
        {
            return null;
        }
        if (nColumnIndex >= 0 && nColumnIndex < m_cColumns)
        {
            object = m_aData[nColumnIndex];
        }

        return object;

    } // getColumn

    public Object getColumn(String strColumnName)
    {
        if ( (m_aData == null) || (strColumnName == null))
        {
            return null;
        }

        int nIndex_Found = -1;
        if (m_aColumnNames == null)
        {
            if (m_queryResultDAO != null)
            {
                nIndex_Found = m_queryResultDAO.findColumnIndex_ByName(strColumnName);
            }
        }
        else
        {
            for (int i = 0; i < m_aColumnNames.length; i++)
            {
                if (strColumnName.compareToIgnoreCase(m_aColumnNames[i]) == 0)
                {
                    nIndex_Found = i;
                    break;
                }
            }
        }

        return getColumn(nIndex_Found);

    } // getColumn

    public void release()
    {
        m_cColumns = 0;

        int i;
        if (m_aColumnNames != null)
        {
            for (i = 0; i < m_aColumnNames.length; i++)
            {
                m_aColumnNames[i] = null;
            }
            m_aColumnNames = null;
        }
        if (m_aData != null)
        {
            for (i = 0; i < m_aData.length; i++)
            {
                m_aData[i] = null;
            }
            m_aData = null;
        }

    } // release

} // class QueryResultRowDAO
