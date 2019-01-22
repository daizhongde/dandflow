package junit.test;

/**
 * TIsoftstoneEmployee entity. @author MyEclipse Persistence Tools
 */

public class TIsoftstoneEmployee implements java.io.Serializable {

	// Fields

	private String employeeNo;
	private String employeeName;
	private String enName;
	private String sex;
	private String tel;
	private String mobile;
	private String email;
	private String dept;
	private String costCenter;
	private String corpCode;
	private String corpName;
	private String position;
	private String rank;
	private String qualLevel;
	private String hwbgLevel;

	// Constructors

	/** default constructor */
	public TIsoftstoneEmployee() {
	}

	/** minimal constructor */
	public TIsoftstoneEmployee(String employeeNo) {
		this.employeeNo = employeeNo;
	}

	/** full constructor */
	public TIsoftstoneEmployee(String employeeNo, String employeeName,
			String enName, String sex, String tel, String mobile, String email,
			String dept, String costCenter, String corpCode, String corpName,
			String position, String rank, String qualLevel, String hwbgLevel) {
		this.employeeNo = employeeNo;
		this.employeeName = employeeName;
		this.enName = enName;
		this.sex = sex;
		this.tel = tel;
		this.mobile = mobile;
		this.email = email;
		this.dept = dept;
		this.costCenter = costCenter;
		this.corpCode = corpCode;
		this.corpName = corpName;
		this.position = position;
		this.rank = rank;
		this.qualLevel = qualLevel;
		this.hwbgLevel = hwbgLevel;
	}

	// Property accessors

	public String getEmployeeNo() {
		return this.employeeNo;
	}

	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}

	public String getEmployeeName() {
		return this.employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEnName() {
		return this.enName;
	}

	public void setEnName(String enName) {
		this.enName = enName;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDept() {
		return this.dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getCostCenter() {
		return this.costCenter;
	}

	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
	}

	public String getCorpCode() {
		return this.corpCode;
	}

	public void setCorpCode(String corpCode) {
		this.corpCode = corpCode;
	}

	public String getCorpName() {
		return this.corpName;
	}

	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}

	public String getPosition() {
		return this.position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getRank() {
		return this.rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public String getQualLevel() {
		return this.qualLevel;
	}

	public void setQualLevel(String qualLevel) {
		this.qualLevel = qualLevel;
	}

	public String getHwbgLevel() {
		return this.hwbgLevel;
	}

	public void setHwbgLevel(String hwbgLevel) {
		this.hwbgLevel = hwbgLevel;
	}

}