package junit.test.tframe;

import java.sql.*;
import java.util.Vector;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>Title: Smart Web Application FrameWork</p>
 * <p>Description: framework for J2EE applications</p>
 * <p>Copyright: Copyright (c) Reserved by copote 2008</p>
 * <p>Company: copote</p>
 * @author wg
 * @version 2.1
 */

public class DBStatement
{
    /**
     *
     */
    private String m_strDataSource;

    /**
     *
     */
    private Vector m_vecParameters;

    /**
     *
     */
    private String m_strSql;

    /**
     *
     */
    private String m_strTranslatedSql;

    /**
     *
     */
    public DBStatement ()
    {
        m_vecParameters = new Vector ();
        m_strSql = "";
        m_strDataSource = "";
        m_strTranslatedSql = "";
    }

    /**
     *
     * @param name
     * @return
     */
    private Parameter addParameter (String name)
    {
        Parameter p = new Parameter (name);
        m_vecParameters.add (p);
        return p;
    }

    /**
     *
     * @return
     */
    public String getDataSource ()
    {
        return m_strDataSource;
    }

    /**
     *
     * @param index
     * @return
     */
    public Parameter getParameter (int index)
    {
        return (Parameter) m_vecParameters.get (index);
    }

    /**
     *
     * @return
     */
    public int getParameterCount ()
    {
        return m_vecParameters.size ();
    }

    /**
     *
     * @param conn
     * @return
     * @throws SQLException
     */
    public PreparedStatement getPreparedStatement (Connection conn) throws SQLException
    {
        PreparedStatement ps = null;

        ps = conn.prepareStatement (m_strTranslatedSql ,
                                    ResultSet.TYPE_SCROLL_INSENSITIVE ,
                                    ResultSet.CONCUR_READ_ONLY);

//        Log.append ("INFO" , m_strTranslatedSql);
        for (int i = 0; i < getParameterCount (); i++)
        {
            Parameter param = getParameter (i);
            ps.setString (i + 1 , param != null ? param.getString () : "");
//            Log.append ("INFO" , param.getName () + ":" + param.getString ());
        }

        return ps;
    }

    /**
     *
     * @return
     */
    public String getSql ()
    {
        return m_strSql;
    }

    /**
     *
     * @return
     */
    public String getTranslatedSql ()
    {
        return m_strTranslatedSql;
    }

    /**
     *
     * @param dataSource
     */
    public void setDataSource (String dataSource)
    {
        m_strDataSource = dataSource;
    }

    /**
     *
     * @param strSql
     * @param request
     */
    public void setSql (String strSql , HttpServletRequest request)
    {
        if (m_vecParameters != null)
        {
            m_vecParameters.clear ();
        }

        m_strSql = strSql;
        m_strTranslatedSql = translateSqlClause (strSql , request);
    }

    /**
     *
     * @param strClause
     * @param request
     * @return
     */
    private String translateSqlClause (String strClause , HttpServletRequest request)
    {
        String strTranslated = "";

        boolean bInQuot = false;
        boolean bInParam = false;
        String strParam = "";
        char chIndicator = 0;
        int i = 0;
        int nLength = strClause.length ();

        while (i < nLength)
        {
            char ch = strClause.charAt (i);
            if (ch == '#' || ch == '$')
            {
                if (bInQuot && chIndicator == ch)
                {
                    bInQuot = false;
                }
                else if (!bInQuot)
                {
                    chIndicator = ch;
                    bInQuot = true;
                }
            }
            else if ( (ch == '.') && (i + 4 < nLength))
            {
                String strRel = strClause.substring (i + 1 , i + 4);
                if (strRel.compareTo ("EQ.") == 0)
                {
                    strTranslated += "=";
                    i += 4;
                    continue;
                }
                else if (strRel.compareTo ("NE.") == 0)
                {
                    strTranslated += "<>";
                    i += 4;
                    continue;
                }
                else if (strRel.compareTo ("GT.") == 0)
                {
                    strTranslated += ">";
                    i += 4;
                    continue;
                }
                else if (strRel.compareTo ("GE.") == 0)
                {
                    strTranslated += ">=";
                    i += 4;
                    continue;
                }
                else if (strRel.compareTo ("LT.") == 0)
                {
                    strTranslated += "<";
                    i += 4;
                    continue;
                }
                else if (strRel.compareTo ("LE.") == 0)
                {
                    strTranslated += "<=";
                    i += 4;
                    continue;
                }
            }

            if (bInQuot)
            {
                bInParam = true;
            }
            if (bInParam)
            {
                if (ch >= '0' && ch <= '9' || ch >= 'a' && ch <= 'z' || ch >= 'A' && ch <= 'Z' || ch == '_')
                {
                    strParam = strParam + ch;
                }
                bInParam = false;
            }
            else
            {
                if (strParam.length () > 0)
                {
                    if (chIndicator == '$')
                    {
//                        strTranslated += Code.decode (request.getParameter (strParam));
                    }
                    else
                    {
                        addParameter (strParam);
                        strTranslated += '?';
                    }

                    strParam = "";
                }

                if ( (ch != '#') && (ch != '$'))
                {
                    strTranslated += ch;
                }
            }
            i++;
        }

        if (strParam.length () > 0)
        {
            addParameter (strParam);
        }
        return strTranslated;

    } // translateSqlClause

}
