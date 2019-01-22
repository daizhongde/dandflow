package person.daizhongde.migration.struts2.action.busi;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import person.daizhongde.authority.struts2.action.BaseAction;
import person.daizhongde.migration.spring.service.MigJobLogService;

/**
 * 作业内容业务action
 * <br>only used by jquery-easy-ui
 * <br>this type Acitons whose name are match "*JEasyUIQUERYAction"
 * <br>are only do a work: query table data
 * @author dzd
 * @date 2015-01-07
 */
public class MigJobLogBUSIAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** jdata针对单个属性多的实体，目的将参数简单化，而对于具体业务中只需要传一两个参数建议使用传统的传参方式，否则会提高前台代码的复杂度   **/
	private String jobId;
	private String jobInsId;
	private String taskId;
	private String sResponse;
	
	private MigJobLogService dataService;

	public String fetchLog(){
		try{
			
			sResponse = dataService.fetchLog( jobId, jobInsId, taskId );
		}catch(Exception e){
			e.printStackTrace();
			
			Throwable e2 = e;
			while(e2.getCause() != null ){
				e2 = e2.getCause();
			}
			sResponse = e2.getLocalizedMessage();
		}
		this.setSResponse(sResponse);
		return "sResponse";
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public void setJobInsId(String jobInsId) {
		this.jobInsId = jobInsId;
	}

	/**
	 * 升级后的struts2  action属性没有放到request的Attribute中
	 * @param sResponse
	 */
	public void setSResponse(String sResponse) {
		this.sResponse = sResponse;
		HttpServletRequest request=ServletActionContext.getRequest();  
        ServletContext cxt=ServletActionContext.getServletContext();  
        request.setAttribute("sResponse", sResponse );
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getSResponse() {
		return sResponse;
	}
	public void setDataService(MigJobLogService dataService) {
		this.dataService = dataService;
	}
}