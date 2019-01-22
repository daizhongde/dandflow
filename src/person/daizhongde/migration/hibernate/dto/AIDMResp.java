package person.daizhongde.migration.hibernate.dto;

public class AIDMResp{
    protected boolean success;
    protected String msg;
    
    public AIDMResp(){
    }
    
    public AIDMResp(boolean success, String msg){
    	this.success = success;
    	this.msg = msg;
    }
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}