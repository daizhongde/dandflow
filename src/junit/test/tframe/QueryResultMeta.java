package junit.test.tframe;

/**
 * <p>Title: Smart Web Application FrameWork</p>
 * <p>Description: framework for J2EE applications</p>
 * <p>Copyright: Copyright (c) Reserved by copote 2008</p>
 * <p>Company: copote</p>
 * @author wg
 * @version 2.1
 */

public interface QueryResultMeta
{
    public abstract int getColumnCount();

    public abstract String getTableName(int nColumnIndex);

    public abstract String getColumnName(int nColumnIndex);

    public abstract String getColumnLabel(int nColumnIndex);

    public abstract int getColumnSqlType(int nColumnIndex);

    public abstract int getColumnDisplaySize(int nColumnIndex);

    public abstract int isNullable(int nColumnIndex);

    public abstract boolean isReadOnly(int nColumnIndex);

    public abstract boolean isWritable(int nColumnIndex);

    public abstract void release();

} // interface QueryResultMeta
