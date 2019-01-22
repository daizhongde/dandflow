package person.daizhongde.migration.hibernate.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import person.daizhongde.virtue.dao.SpringHibernateDao;

import person.daizhongde.migration.hibernate.dto.MigJobParaDto;
import person.daizhongde.migration.hibernate.pojo.MigJobPara;

public interface MigJobParaDAO extends SpringHibernateDao{
	
		
	public abstract void newJobPara(MigJobPara mjp);
	public abstract void updateJobPara(String nodeId,Map<String,String> paras);
	public abstract void deleteJobPara(String nodeId,String para);
	
	/**
	 * 查询MigInsPara实体
	 * @param jobId
	 * @param taskId
	 * @param param
	 * @return
	 */
	public abstract List<MigJobPara> find(String jobId, String nodeId);
	
	public abstract List<MigJobPara> findAllParaByNodeId(String nodeId);
	/** 返回变量名组成的数组 **/
	public abstract Set<String> findAllParaByNodeId2(String nodeId);
	/** 返回变量名值对组成的集合 **/
	public abstract Map<String, String> findAllParaByNodeId3(String nodeId);
	
	/** 实例化叶子任务的变量 **/
	public abstract void instanceJobPara(String jobInsId, String nodeId );
	/** 实例化作业级的变量 **/
	public abstract void instanceJobPara(String jobInsId, String nodeId, List<MigJobParaDto> jobparamlist);
    public abstract void deleteByNodeId(String nodeId);

	public abstract void save(MigJobPara transientInstance);

	public abstract void delete(MigJobPara persistentInstance);

	/**
	 * 查询MigJobPara实体
	 * @param jobId
	 * @param taskId
	 * @param param
	 * @return
	 */
//	public abstract List<MigJobPara> find(String jobId, String taskId);
//	public abstract List<MigJobPara> find(String jobId, String taskId, String param);
//	
	public abstract MigJobPara findById(
			person.daizhongde.migration.hibernate.pojo.MigJobParaId id);

	public abstract List findByExample(MigJobPara instance);

	public abstract List findByProperty(String propertyName, Object value);

	public abstract List findAll();

	public abstract MigJobPara merge(MigJobPara detachedInstance);

	public abstract void attachDirty(MigJobPara instance);

	public abstract void attachClean(MigJobPara instance);

}