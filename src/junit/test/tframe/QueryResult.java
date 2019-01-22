package junit.test.tframe;

/**
 * <p>Title: Smart Web Application FrameWork</p>
 * <p>Description: framework for J2EE applications</p>
 * <p>Copyright: Copyright (c) Reserved by copote 2008</p>
 * <p>Company: copote</p>
 * @author wg
 * @version 2.1
 */

public interface QueryResult
{
    public abstract QueryResultMeta getQueryResultMeta();

    public abstract int getRowCount();

    public abstract Object fetchData(int nRowIndex, int nColIndex);

    public abstract void release();

} // interface QueryResult
