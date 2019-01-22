package junit.test.tframe;

/**
 * <p>Title: Smart Web Application FrameWork</p>
 * <p>Description: framework for J2EE applications</p>
 * <p>Copyright: Copyright (c) Reserved by copote 2008</p>
 * <p>Company: copote</p>
 * @author wg
 * @version 2.1
 */

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

public class Parameter
{
    /**
     *
     */
    private String m_strName;

    /**
     *
     */
    private Object m_objValue;

    /**
     *
     * @param strName
     */
    public Parameter (String strName)
    {
        m_strName = strName;
        m_objValue = "";
    }

    /**
     *
     * @return
     */
    public BigDecimal getBigDecimal ()
    {
        if (m_objValue == null)
        {
            return BigDecimal.valueOf (0L);
        }
        if (m_objValue instanceof BigDecimal)
        {
            return (BigDecimal) m_objValue;
        }
        else
        {
            return BigDecimal.valueOf ( ( (Number) m_objValue).longValue ());
        }
    }

    /**
     *
     * @return
     */
    public boolean getBoolean ()
    {
        if (m_objValue == null)
        {
            return false;
        }
        else
        {
            return ( (Boolean) m_objValue).booleanValue ();
        }
    }

    /**
     *
     * @return
     */
    public byte getByte ()
    {
        if (m_objValue == null)
        {
            return 0;
        }
        if (m_objValue instanceof Number)
        {
            return ( (Number) m_objValue).byteValue ();
        }
        else
        {
            return Byte.parseByte (m_objValue.toString ());
        }
    }

    /**
     *
     * @return
     */
    public Date getDate ()
    {
        if (m_objValue == null)
        {
            return null;
        }
        else
        {
            return new Date ( ( (java.util.Date) m_objValue).getTime ());
        }
    }

    /**
     *
     * @return
     */
    public double getDouble ()
    {
        if (m_objValue == null)
        {
            return 0.0D;
        }
        if (m_objValue instanceof Number)
        {
            return ( (Number) m_objValue).doubleValue ();
        }
        else
        {
            return Double.parseDouble (m_objValue.toString ());
        }
    }

    /**
     *
     * @return
     */
    public float getFloat ()
    {
        if (m_objValue == null)
        {
            return 0.0F;
        }
        if (m_objValue instanceof Number)
        {
            return ( (Number) m_objValue).floatValue ();
        }
        else
        {
            return Float.parseFloat (m_objValue.toString ());
        }
    }

    /**
     *
     * @return
     */
    public int getInt ()
    {
        if (m_objValue == null)
        {
            return 0;
        }
        if (m_objValue instanceof Number)
        {
            return ( (Number) m_objValue).intValue ();
        }
        else
        {
            return Integer.parseInt (m_objValue.toString ());
        }
    }

    /**
     *
     * @return
     */
    public long getLong ()
    {
        if (m_objValue == null)
        {
            return 0L;
        }
        if (m_objValue instanceof Number)
        {
            return ( (Number) m_objValue).longValue ();
        }
        else
        {
            return Long.parseLong (m_objValue.toString ());
        }
    }

    /**
     *
     * @return
     */
    public String getName ()
    {
        return m_strName;
    }

    /**
     *
     * @return
     */
    public short getShort ()
    {
        if (m_objValue == null)
        {
            return 0;
        }
        if (m_objValue instanceof Number)
        {
            return ( (Number) m_objValue).shortValue ();
        }
        else
        {
            return Short.parseShort (m_objValue.toString ());
        }
    }

    /**
     *
     * @return
     */
    public String getString ()
    {
        if (m_objValue == null)
        {
            return "";
        }
        else
        {
            return m_objValue.toString ();
        }
    }

    /**
     *
     * @return
     */
    public Time getTime ()
    {
        if (m_objValue == null)
        {
            return null;
        }
        else
        {
            return new Time ( ( (java.util.Date) m_objValue).getTime ());
        }
    }

    /**
     *
     * @return
     */
    public Timestamp getTimestamp ()
    {
        if (m_objValue == null)
        {
            return null;
        }
        else
        {
            return new Timestamp ( ( (java.util.Date) m_objValue).getTime ());
        }
    }

    /**
     *
     * @param nValue
     */
    public void setBigDecimal (BigDecimal nValue)
    {
        m_objValue = nValue;
    }

    /**
     *
     * @param bValue
     */
    public void setBoolean (boolean bValue)
    {
        m_objValue = new Boolean (bValue);
    }

    /**
     *
     * @param nValue
     */
    public void setByte (byte nValue)
    {
        m_objValue = new Byte (nValue);
    }

    /**
     *
     * @param date
     */
    public void setDate (Date date)
    {
        m_objValue = new java.util.Date (date.getTime ());
    }

    /**
     *
     * @param nValue
     */
    public void setDouble (double nValue)
    {
        m_objValue = new Double (nValue);
    }

    /**
     *
     * @param nValue
     */
    public void setFloat (float nValue)
    {
        m_objValue = new Float (nValue);
    }

    /**
     *
     * @param nValue
     */
    public void setInt (int nValue)
    {
        m_objValue = new Integer (nValue);
    }

    /**
     *
     * @param nValue
     */
    public void setLong (long nValue)
    {
        m_objValue = new Long (nValue);
    }

    /**
     *
     * @param nValue
     */
    public void setShort (short nValue)
    {
        m_objValue = new Short (nValue);
    }

    /**
     *
     * @param strValue
     */
    public void setString (String strValue)
    {
        m_objValue = strValue;
    }

    /**
     *
     * @param time
     */
    public void setTime (Time time)
    {
        m_objValue = new java.util.Date (time.getTime ());
    }

    /**
     *
     * @param time
     */
    public void setTimestamp (Timestamp time)
    {
        m_objValue = new java.util.Date (time.getTime ());
    }
}
