package junit.test.tframe;

/**
 * <p>Title: Smart Web Application FrameWork</p>
 * <p>Description: framework for J2EE applications</p>
 * <p>Copyright: Copyright (c) Reserved by copote 2008</p>
 * <p>Company: copote</p>
 * @author wg
 * @version 2.1
 */

import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * This is a utility class for obtaining JNDI objects.
 */
public class JndiUtil
{
    protected static InitialContext m_initialContext;

    public static Object getJndiObject (String strName) throws javax.naming.NamingException
    {
        if (m_initialContext == null)
        {
            m_initialContext = new InitialContext ();

        }
        return m_initialContext.lookup (strName);

    } // getJndiObject

} // class JndiUtil
