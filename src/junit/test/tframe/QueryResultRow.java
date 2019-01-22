package junit.test.tframe;

/**
 * <p>Title: Smart Web Application FrameWork</p>
 * <p>Description: framework for J2EE applications</p>
 * <p>Copyright: Copyright (c) Reserved by copote 2008</p>
 * <p>Company: copote</p>
 * @author wg
 * @version 2.1
 */

public interface QueryResultRow
{
    public abstract Object getColumn(int nColumnIndex); // nColumnIndex starts from 0

    public abstract Object getColumn(String strColumnName);

    public abstract void release();

} // interface QueryResultRow
