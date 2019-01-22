package person.daizhongde.migration.hibernate.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import person.daizhongde.virtue.dao.SpringHibernateDao;

import person.daizhongde.migration.hibernate.pojo.MigInsPara;
import person.daizhongde.migration.hibernate.pojo.MigInsPara;

public interface MigInsParaDAO extends SpringHibernateDao{

	// property constants
	public static final String JOB_INS_ID = "jobInsId";
	public static final String PARA_NAME = "paraName";
	public static final String PARA_TYPE = "paraType";
	public static final String PARA_VALUE = "paraValue";

	public abstract void newJobPara(MigInsPara mjp);
	public abstract void updateJobPara(String jobInsId, String nodeId,Map<String,String> paras);
	public abstract void deleteJobPara(String jobInsId, String nodeId,String para);
	public abstract void deleteJobPara(String jobInsId);
	public abstract void deleteJobPara(List<String> jobInsId);
	
	public abstract List<MigInsPara> findByNodeId(String jobInsId, String nodeId);
	public abstract HashMap<String, String> findByNodeId2(String jobInsId, String nodeId);
	
    public abstract void deleteJobParaByJobInsIdAndNodeId(String jobInsId, String nodeId);

	public abstract void save(MigInsPara transientInstance);

	public abstract void delete(MigInsPara persistentInstance);

	/**
	 * 查询MigInsPara实体
	 * @param jobId
	 * @param taskId
	 * @param param
	 * @return
	 */
	public abstract List<MigInsPara> find(String jobInsId, String jobId, String nodeId);
//	public abstract List<MigInsPara> find(String jobId, String taskId, String param);

	public abstract MigInsPara findById(
			person.daizhongde.migration.hibernate.pojo.MigInsParaId id);

	public abstract List findByExample(MigInsPara instance);

	public abstract List findByProperty(String propertyName, Object value);

	public abstract List findByJobInsId(Object jobInsId);

	public abstract List findByParaName(Object paraName);

	public abstract List findByParaType(Object paraType);

	public abstract List findByParaValue(Object paraValue);

	public abstract List findAll();

	public abstract MigInsPara merge(MigInsPara detachedInstance);

	public abstract void attachDirty(MigInsPara instance);

	public abstract void attachClean(MigInsPara instance);

}