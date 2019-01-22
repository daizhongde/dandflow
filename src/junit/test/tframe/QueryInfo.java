package junit.test.tframe;

/**
 * <p>Title: Smart Web Application FrameWork</p>
 * <p>Description: framework for J2EE applications</p>
 * <p>Copyright: Copyright (c) Reserved by copote 2008</p>
 * <p>Company: copote</p>
 * @author wg
 * @version 2.1
 */

public final class QueryInfo
{
    private static final int MAX_Rows = 100;
    public static boolean isTransCharset = true; //字符串是否需要转换编码方式

    public String m_strSql = null;
    public boolean m_bIsMultiRow = false;
    public int m_cMaxRows = -1;

    public boolean m_bIsRowCountRequired = false;

    public Object[] m_aobjParams = null;
    public int[] m_anParamSqlTypes = null;

    public void QueryInfo ()
    {
        setQueryParamCount (0);
    }

    public void setQueryParamCount (int cParams)
    {
        m_aobjParams = new Object[cParams];
        m_anParamSqlTypes = new int[cParams];

    } // setQueryParamCount

    public void setQueryParam (int nParamIndex , Object objParam , int nSqlType) // nParamIndex starts from 0
    {
        if (m_aobjParams == null || m_anParamSqlTypes == null)
        {
            return;
        }

        if (nParamIndex >= 0 && nParamIndex < m_aobjParams.length)
        {
            m_aobjParams[nParamIndex] = objParam;
            m_anParamSqlTypes[nParamIndex] = nSqlType;
        }

    } // setQueryParam

    public void release ()
    {
        m_strSql = null;
        m_bIsMultiRow = false;
        m_cMaxRows = -1;

        m_bIsRowCountRequired = false;
        /****
                 m_bIsAllFetchedAtOnce = false;
         ****/

        m_aobjParams = null;
        m_anParamSqlTypes = null;

    } // release

} // class QueryInfo
