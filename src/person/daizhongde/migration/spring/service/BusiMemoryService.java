package person.daizhongde.migration.spring.service;

import java.util.List;
import java.util.Map;

import person.daizhongde.migration.hibernate.pojo.MigControlTemplate;

public interface BusiMemoryService {
	/** {con001:'icon-split',con002:'icon-loaddata',...}  */
	public abstract Map<String, String> getControlIconMap();
	/** {con001:'split_file',con002:'load_data',...}  */
	public abstract Map<String, String> getControlNameMap();
	/**
	 * 获取控件树数据
	 * @return
	 */
	public abstract List getControlTreeData();
	
	public abstract Map<String, List<MigControlTemplate>> getControlMap();
	
	public abstract List<MigControlTemplate> getTemplateByControlId(String controlId);
	
	public abstract String getParamNameByControlIdandParamId( String controlId, int paraId );
}
