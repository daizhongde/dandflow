package person.daizhongde.migration.hibernate.dao;

import java.util.List;

import person.daizhongde.virtue.dao.SpringHibernateDao;

import person.daizhongde.migration.hibernate.pojo.MigAuditvConfigtree;

public interface MigAuditvConfigtreeDAO  extends SpringHibernateDao{

	//property constants
	public static final String PARENT = "parent";
	public static final String NAME = "name";
	public static final String CONTENT = "content";
	public static final String ISLEAF = "isleaf";
	public static final String STATUS = "status";
	public static final String REMARK = "remark";

//	public abstract List findAllWithOrder();
//	public abstract List findChildrenRecursive( Integer parentId );
//	public abstract List findChildrenRecursive( Integer parentId, Integer maxLevel );
	
	public abstract int updateParentWhenRemoveLink(int parent, int id );
	/**
	 * used
	 * @param parentId
	 * @return
	 */
	public abstract List<MigAuditvConfigtree> findChildrenNoRecursive( Integer parentId );
	
	public abstract void save(MigAuditvConfigtree transientInstance);

	public abstract void delete(MigAuditvConfigtree persistentInstance);

	public abstract MigAuditvConfigtree findById(java.lang.Integer id);

	public abstract List findByExample(MigAuditvConfigtree instance);

	public abstract List findByProperty(String propertyName, Object value);

	public abstract List findByParent(Object parent);
	public abstract List<MigAuditvConfigtree> findByParent2(Object parent);
	
	public abstract List findByName(Object name);

	public abstract List findByContent(Object content);
	public abstract List findByContent2(Object content);
	
	public abstract List findByIsleaf(Object isleaf);

	public abstract List findByStatus(Object status);

	public abstract List findByRemark(Object remark);

	public abstract List findAll();

	public abstract MigAuditvConfigtree merge(
			MigAuditvConfigtree detachedInstance);

	public abstract void attachDirty(MigAuditvConfigtree instance);

	public abstract void attachClean(MigAuditvConfigtree instance);

}