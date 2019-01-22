package person.daizhongde.migration.hibernate.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import person.daizhongde.virtue.dao.SpringHibernateDaoSupport;
import person.daizhongde.virtue.util.test.Printer;

import person.daizhongde.migration.exception.BusinessException;
import person.daizhongde.migration.hibernate.dao.MigComInfoDAO;
import person.daizhongde.migration.hibernate.pojo.MigComInfo;
import person.daizhongde.migration.hibernate.pojo.MigComIns;

/**
 * A data access object (DAO) providing persistence and search support for
 * MigComIns entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see person.daizhongde.migration.hibernate.dao.impl.MigComIns
 * @author MyEclipse Persistence Tools
 */
public class MigComInfoDAOImpl extends SpringHibernateDaoSupport implements MigComInfoDAO {
	private static final Logger log = LoggerFactory
			.getLogger(MigComInfoDAOImpl.class);

	// property constants

	protected void initDao() {
		// do nothing
	}
	
	public List<MigComInfo> getCominfoByComId(String comId){
		Map condition = new HashMap(1);
		condition.put("com_id", comId);
				
		String sql="from MigComInfo where id.comId=:com_id order by id.paraId";
		return this.listAllByMap(sql, condition);
	}
	
	public void deleteCominfoByComId(String comId){
		Map condition = new HashMap(1);
		condition.put("com_id", comId);
		String sql = "delete from tool.mig_com_info where com_id=:com_id";
		this.sqlQueryExeUByMap(sql, condition);	
	}

	public void updateCominfoById(String value, int Id, String comId){
		Map condition = new HashMap(3);
		condition.put("para_value", value);
		condition.put("com_id", comId);
		condition.put("para_id", Id);
		String sql="update mig_com_info set para_value=:para_value where com_id=:com_id and para_id=:para_id";
		this.sqlQueryExeUByMap(sql, condition);	
	}
	
	public void instanceComInfo(String jobInsId, String ComId){
		Map condition = new HashMap(2);
		condition.put("com_id", ComId);
		condition.put("job_ins_id", jobInsId);
		String sql="insert into mig_com_ins (job_ins_id,com_id,para_id,para_value) select :job_ins_id,com_id,para_id,para_value from tool.mig_com_info"
				+ " where com_id = :com_id";
//		Printer.printJSON( condition );
		this.sqlQueryExeUByMap(sql, condition);	
	}
	
	public void instanceComInfo(String jobInsId, String ComId, List<MigComInfo> rows ){
		Map condition = new HashMap(2);
		condition.put("com_id", ComId);
		condition.put("job_ins_id", jobInsId);
		for( MigComInfo row : rows){
			condition.put("paraValue", row.getParaValue());
			String sql="insert into mig_com_ins (job_ins_id,com_id,para_id,para_value) "
//					+ "VALUES ( :job_ins_id, :com_id, "+row.getId().getParaId()+",'"+row.getParaValue()+"')";
					+ "VALUES ( :job_ins_id, :com_id, "+row.getId().getParaId()+", :paraValue )";
			this.sqlQueryExeUByMap(sql, condition);	
		}
	}
	
	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigComInsDAO#save(person.daizhongde.migration.hibernate.pojo.MigComIns)
	 */
	@Override
	public void save(MigComInfo transientInstance) {
		log.debug("saving MigComIns instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigComInsDAO#delete(person.daizhongde.migration.hibernate.pojo.MigComIns)
	 */
	@Override
	public void delete(MigComInfo persistentInstance) {
		log.debug("deleting MigComIns instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigComInsDAO#findById(person.daizhongde.migration.hibernate.pojo.MigComInsId)
	 */
	@Override
	public MigComInfo findById(
			person.daizhongde.migration.hibernate.pojo.MigComInfoId id) {
		log.debug("getting MigComIns instance with id: " + id);
		try {
			MigComInfo instance = (MigComInfo) getHibernateTemplate().get(
					"person.daizhongde.migration.hibernate.pojo.MigComInfo", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigComInsDAO#findByExample(person.daizhongde.migration.hibernate.pojo.MigComIns)
	 */
	@Override
	public List findByExample(MigComInfo instance) {
		log.debug("finding MigComIns instance by example");
		try {
			List results = getHibernateTemplate().findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigComInsDAO#findByProperty(java.lang.String, java.lang.Object)
	 */
	@Override
	public List findByProperty(String propertyName, Object value) {
//		log.debug("finding MigComIns instance with property: " + propertyName
//				+ ", value: " + value);
		try {
			String queryString = "from MigComInfo as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigComInsDAO#findAll()
	 */
	@Override
	public List findAll() {
		log.debug("finding all MigComIns instances");
		try {
			String queryString = "from MigComIns";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigComInsDAO#merge(person.daizhongde.migration.hibernate.pojo.MigComIns)
	 */
	@Override
	public MigComInfo merge(MigComInfo detachedInstance) {
		log.debug("merging MigComIns instance");
		try {
			MigComInfo result = (MigComInfo) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigComInsDAO#attachDirty(person.daizhongde.migration.hibernate.pojo.MigComIns)
	 */
	@Override
	public void attachDirty(MigComInfo instance) {
		log.debug("attaching dirty MigComIns instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see person.daizhongde.migration.hibernate.dao.impl.MigComInsDAO#attachClean(person.daizhongde.migration.hibernate.pojo.MigComIns)
	 */
	@Override
	public void attachClean(MigComInfo instance) {
		log.debug("attaching clean MigComIns instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static MigComInfoDAO getFromApplicationContext(ApplicationContext ctx) {
		return (MigComInfoDAO) ctx.getBean("migComInsDAO");
	}
}