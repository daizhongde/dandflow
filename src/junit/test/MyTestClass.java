package junit.test;

import java.util.List;

public interface MyTestClass {
	public abstract List findByPage(final String hql, final int offset,
			final int pageSize);

	public abstract List findByPage(final String hql, final Object value,
			final int offset, final int pageSize);

	public abstract List findByPage(final String hql, final Object[] values,
			final int offset, final int pageSize);

	public abstract List sqlQueryfindByPage(final String sql, final int offset,
			final int pageSize);

	public abstract List sqlQueryfindByPage(final String sql,
			final Object value, final int offset, final int pageSize);

	public abstract List sqlQueryfindByPage(final String sql,
			final Object[] values, final int offset, final int pageSize);

	public abstract String sqlQueryfindAString(final String sql);

	public abstract String sqlQueryfindAString(final String sql,final Object value);

	public abstract String sqlQueryfindAString(final String sql,
			final Object[] values);

	public abstract Integer sqlQueryfindANumber(final String sql);

	public abstract Integer sqlQueryfindANumber(final String sql,final Object value);

	public abstract Integer sqlQueryfindANumber(final String sql,
			final Object[] values);
}
