package person.daizhongde.migration.hibernate.dto;

//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * <p>EXELINUXBINCONFIG complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 */
//JsonIgnoreProperties(ignoreUnknown = true)
public class EXELINUXBINCONFIG {
    protected String taskId;
    protected String jobId;
    protected String jobInsId;
    protected int dryrunId;
    protected String hostIp;
    protected String hostConn;
    protected String command;
    protected String iswait;
    protected String isCheck;
    protected String comPara;

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public String getJobInsId() {
		return jobInsId;
	}

	public void setJobInsId(String jobInsId) {
		this.jobInsId = jobInsId;
	}

	public int getDryrunId() {
		return dryrunId;
	}

	public void setDryrunId(int dryrunId) {
		this.dryrunId = dryrunId;
	}

	public String getHostIp() {
		return hostIp;
	}

	public void setHostIp(String hostIp) {
		this.hostIp = hostIp;
	}

	public String getHostConn() {
		return hostConn;
	}

	public void setHostConn(String hostConn) {
		this.hostConn = hostConn;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public String getIswait() {
		return iswait;
	}

	public void setIswait(String iswait) {
		this.iswait = iswait;
	}

	public String getIsCheck() {
		return isCheck;
	}

	public void setIsCheck(String isCheck) {
		this.isCheck = isCheck;
	}

	public String getComPara() {
		return comPara;
	}

	public void setComPara(String comPara) {
		this.comPara = comPara;
	}
}
