package person.daizhongde.migration.struts2.action.busi;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import net.sf.json.JSONObject;

import person.daizhongde.authority.struts2.action.BaseAction;
import person.daizhongde.migration.hibernate.dto.MigControlTemplateDto;
import person.daizhongde.migration.hibernate.pojo.MigComInfo;
import person.daizhongde.migration.hibernate.pojo.MigControlTemplate;
import person.daizhongde.migration.spring.service.MigComInfoService;

/**
 * 作业内容业务action
 * <br>only used by jquery-easy-ui
 * <br>this type Acitons whose name are match "*JEasyUIQUERYAction"
 * <br>are only do a work: query table data
 * @author dzd
 * @date 2015-01-07
 */
public class MigComInfoBUSIAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private MigComInfoService dataService;

	public String queryComInfos(){
		JSONObject json = StringUtils.isEmpty( super.getJdata() )?null:JSONObject.fromObject( super.getJdata() );
		super.setJsonObject(json);
		
		List<MigControlTemplateDto> list = dataService.queryComInfos( super.get( "controlId" ), super.get( "comId" ) );
		
		super.setJson( list );
		return SUCCESS;
	}

	public void setDataService(MigComInfoService dataService) {
		this.dataService = dataService;
	}
}