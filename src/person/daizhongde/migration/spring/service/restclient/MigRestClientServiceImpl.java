package person.daizhongde.migration.spring.service.restclient;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import person.daizhongde.migration.exception.BusinessException;
import person.daizhongde.migration.hibernate.dao.MigControlInfoDAO;
import person.daizhongde.migration.hibernate.dto.AIDMResp;
import person.daizhongde.migration.hibernate.dto.EXELINUXBINCONFIG;
import person.daizhongde.migration.hibernate.pojo.MigComIns;
import person.daizhongde.migration.hibernate.pojo.MigControlInfo;
/**
 * 与没有数字后缀的类区别只是方法返回值类型不一样
 * @author daizd
 *
 */
public class MigRestClientServiceImpl implements MigRestClientService {

	private MigControlInfoDAO migControlInfoDAO;
	
	private RestTemplate simpleRestTemplate;
	private String url;
	
	public AIDMResp invokeSingle( String jobId, String jobInsId, Integer dryrunid, String nodeId, int signal ){
//		return this.controller(jobId, jobInsId, dryrunid, nodeId, signal);
		return null;
	}
	
	public AIDMResp invoke( List<MigComIns> result, String jobId, String jobInsId, 
			 Integer dryrunid, String nodeId, String isCheck, String controlId ){
		
//		Printer.printJSON(result);

//		System.out.println("controlId:"+controlId);
		MigControlInfo ctrlInfo= migControlInfoDAO.findById( controlId );	
//		Printer.printJSON(ctrlInfo);
		
		switch(ctrlInfo.getControlName()){
//		case "split_file":    return this.splitFile(result, jobId, jobInsId, dryrunid, nodeId, isCheck); 
//		case "load_data":     return this.loadData(result, jobId, jobInsId, dryrunid, nodeId, isCheck); 
//		case "out_data_file": return this.outDataFile(result, jobId, jobInsId, dryrunid, nodeId, isCheck); 
//		case "out_data_base": return this.outDataBase(result, jobId, jobInsId, dryrunid, nodeId, isCheck); 
//		case "excute_sql":    return this.executeSql(result, jobId, jobInsId, dryrunid, nodeId, isCheck); 
		case "excute_bin":    return this.exeLinuxBin2(result, jobId, jobInsId, dryrunid, nodeId, isCheck); 
//		case "legality_audit":        return this.legalityAudit(result, jobId, jobInsId, dryrunid, nodeId, isCheck);
//		case "consistency_audit":     return this.consistencyAudit(result, jobId, jobInsId, dryrunid, nodeId, isCheck); 
//		case "quality_audit":         return this.qualityAudit(result, jobId, jobInsId, dryrunid, nodeId, isCheck); 
		
//		case "control":       return this.controller(result, jobId, taskId); 
		default : 
			AIDMResp dto = new AIDMResp(false, "程序内部错误!");
			return dto;
		}
	};
	
	public AIDMResp exeLinuxBin2( List<MigComIns> result, String jobId, String jobInsId, Integer dryrunid, String nodeId, String isCheck ){
		EXELINUXBINCONFIG dto = assembleEXELINUXBINCONFIG( result, jobId, jobInsId, dryrunid, nodeId, isCheck  );
		
//		  HttpHeaders headers = new HttpHeaders();
//		  MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
//		  headers.setContentType(type);
//		  headers.add("Accept", MediaType.APPLICATION_JSON.toString());
//		  HttpEntity<EXELINUXBINCONFIG> formEntity = new HttpEntity<EXELINUXBINCONFIG>( dto, headers);
		AIDMResp response = simpleRestTemplate.postForObject(url+"ComInterFace/exeLinuxBin", dto,  AIDMResp.class );
//		ResponseEntity<AIDMResp> response = simpleRestTemplate.postForEntity(url+"ComInterFace/exeLinuxBin", formEntity,  AIDMResp.class );
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+" exeLinuxBin2---------result :"+response.isSuccess()+"----------");
	
		return response;
	}
	public AIDMResp exeLinuxBin3( List<MigComIns> result, String jobId, String jobInsId, Integer dryrunid, String nodeId, String isCheck ){
		EXELINUXBINCONFIG dto = assembleEXELINUXBINCONFIG( result, jobId, jobInsId, dryrunid, nodeId, isCheck  );
		
		  HttpHeaders headers = new HttpHeaders();
		  MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
		  headers.setContentType(type);
		  headers.add("Accept", MediaType.APPLICATION_JSON.toString());
		  HttpEntity<EXELINUXBINCONFIG> formEntity = new HttpEntity<EXELINUXBINCONFIG>( dto, headers);
//		AIDMResp response = simpleRestTemplate.postForObject(url+"ComInterFace/exeLinuxBin", formEntity,  AIDMResp.class );
		ResponseEntity<AIDMResp> response = simpleRestTemplate.postForEntity(url+"ComInterFace/exeLinuxBin", formEntity,  AIDMResp.class );
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+" exeLinuxBin3---------result :"+response.getBody().isSuccess()+"----------");
		
		return response.getBody();
	}
	
    private EXELINUXBINCONFIG assembleEXELINUXBINCONFIG( List<MigComIns> result, String jobId, String jobInsId, Integer dryrunid, String nodeId, String isCheck ){
    	EXELINUXBINCONFIG param1 = new EXELINUXBINCONFIG();
        
        param1.setTaskId( nodeId );
        param1.setJobId(jobId );
        param1.setJobInsId( jobInsId );
        param1.setDryrunId( dryrunid );
        param1.setIsCheck( isCheck );
        
        for(MigComIns e : result){
          	String paramName = e.getParaName();//cfg-conn
          	String paramValue = e.getParaValue();
          	String methodName = getMethodName( paramName );//setCfgConn
          	
          	Object[] args = new Object[1];
   			args[0] = paramValue;
   			@SuppressWarnings("rawtypes")
   			Class[] parameterTypes = new Class[1];
   			parameterTypes[0] = String.class;
   			
   			System.out.println("paramName:"+paramName+", methodName:"+methodName+",value:"+e.getParaValue());
   		    try {
   		    	Method method = EXELINUXBINCONFIG.class.getMethod( methodName, parameterTypes );
   		    	method.invoke( param1 , args );
   			} catch (Exception ex) {
   				ex.printStackTrace();
   				throw new BusinessException("组装参数时出错！");
   			}
       	}
    	return param1;
	}

	public MigControlInfoDAO getMigControlInfoDAO() {
		return migControlInfoDAO;
	}

	public void setMigControlInfoDAO(MigControlInfoDAO migControlInfoDAO) {
		this.migControlInfoDAO = migControlInfoDAO;
	}

	public void setSimpleRestTemplate(RestTemplate simpleRestTemplate) {
		this.simpleRestTemplate = simpleRestTemplate;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 *  cfg-conn   ---->   setCfgConn
	 *  cfg_conn   ---->   setCfgConn
	 *  cfgconn    ---->   setCfgconn 
	 * @param label
	 * @return
	 */
	private String getMethodName( String label ){
		int i = label.indexOf("-");
		if(i == -1 ){
			i = label.indexOf("_");
		}
		if( i == -1 ){
			return "set"+Character.toUpperCase( label.charAt(0) ) + label.substring(1);
		}else{
			return "set"+Character.toUpperCase( label.charAt(0) ) + label.substring(1, i) + Character.toUpperCase( label.charAt(i+1) ) + label.substring( i+2 );
		}
	}
}
