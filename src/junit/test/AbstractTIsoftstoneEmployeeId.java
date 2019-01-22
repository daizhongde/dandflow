package junit.test;

/**
 * AbstractTIsoftstoneEmployeeId entity provides the base persistence definition
 * of the TIsoftstoneEmployeeId entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractTIsoftstoneEmployeeId implements
		java.io.Serializable {

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
	public AbstractTIsoftstoneEmployeeId() {
	}

	/** full constructor */
	public AbstractTIsoftstoneEmployeeId(String employeeNo,
			String employeeName, String enName, String sex, String tel,
			String mobile, String email, String dept, String costCenter,
			String corpCode, String corpName, String position, String rank,
			String qualLevel, String hwbgLevel) {
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

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AbstractTIsoftstoneEmployeeId))
			return false;
		AbstractTIsoftstoneEmployeeId castOther = (AbstractTIsoftstoneEmployeeId) other;

		return ((this.getEmployeeNo() == castOther.getEmployeeNo()) || (this
				.getEmployeeNo() != null && castOther.getEmployeeNo() != null && this
				.getEmployeeNo().equals(castOther.getEmployeeNo())))
				&& ((this.getEmployeeName() == castOther.getEmployeeName()) || (this
						.getEmployeeName() != null
						&& castOther.getEmployeeName() != null && this
						.getEmployeeName().equals(castOther.getEmployeeName())))
				&& ((this.getEnName() == castOther.getEnName()) || (this
						.getEnName() != null && castOther.getEnName() != null && this
						.getEnName().equals(castOther.getEnName())))
				&& ((this.getSex() == castOther.getSex()) || (this.getSex() != null
						&& castOther.getSex() != null && this.getSex().equals(
						castOther.getSex())))
				&& ((this.getTel() == castOther.getTel()) || (this.getTel() != null
						&& castOther.getTel() != null && this.getTel().equals(
						castOther.getTel())))
				&& ((this.getMobile() == castOther.getMobile()) || (this
						.getMobile() != null && castOther.getMobile() != null && this
						.getMobile().equals(castOther.getMobile())))
				&& ((this.getEmail() == castOther.getEmail()) || (this
						.getEmail() != null && castOther.getEmail() != null && this
						.getEmail().equals(castOther.getEmail())))
				&& ((this.getDept() == castOther.getDept()) || (this.getDept() != null
						&& castOther.getDept() != null && this.getDept()
						.equals(castOther.getDept())))
				&& ((this.getCostCenter() == castOther.getCostCenter()) || (this
						.getCostCenter() != null
						&& castOther.getCostCenter() != null && this
						.getCostCenter().equals(castOther.getCostCenter())))
				&& ((this.getCorpCode() == castOther.getCorpCode()) || (this
						.getCorpCode() != null
						&& castOther.getCorpCode() != null && this
						.getCorpCode().equals(castOther.getCorpCode())))
				&& ((this.getCorpName() == castOther.getCorpName()) || (this
						.getCorpName() != null
						&& castOther.getCorpName() != null && this
						.getCorpName().equals(castOther.getCorpName())))
				&& ((this.getPosition() == castOther.getPosition()) || (this
						.getPosition() != null
						&& castOther.getPosition() != null && this
						.getPosition().equals(castOther.getPosition())))
				&& ((this.getRank() == castOther.getRank()) || (this.getRank() != null
						&& castOther.getRank() != null && this.getRank()
						.equals(castOther.getRank())))
				&& ((this.getQualLevel() == castOther.getQualLevel()) || (this
						.getQualLevel() != null
						&& castOther.getQualLevel() != null && this
						.getQualLevel().equals(castOther.getQualLevel())))
				&& ((this.getHwbgLevel() == castOther.getHwbgLevel()) || (this
						.getHwbgLevel() != null
						&& castOther.getHwbgLevel() != null && this
						.getHwbgLevel().equals(castOther.getHwbgLevel())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getEmployeeNo() == null ? 0 : this.getEmployeeNo()
						.hashCode());
		result = 37
				* result
				+ (getEmployeeName() == null ? 0 : this.getEmployeeName()
						.hashCode());
		result = 37 * result
				+ (getEnName() == null ? 0 : this.getEnName().hashCode());
		result = 37 * result
				+ (getSex() == null ? 0 : this.getSex().hashCode());
		result = 37 * result
				+ (getTel() == null ? 0 : this.getTel().hashCode());
		result = 37 * result
				+ (getMobile() == null ? 0 : this.getMobile().hashCode());
		result = 37 * result
				+ (getEmail() == null ? 0 : this.getEmail().hashCode());
		result = 37 * result
				+ (getDept() == null ? 0 : this.getDept().hashCode());
		result = 37
				* result
				+ (getCostCenter() == null ? 0 : this.getCostCenter()
						.hashCode());
		result = 37 * result
				+ (getCorpCode() == null ? 0 : this.getCorpCode().hashCode());
		result = 37 * result
				+ (getCorpName() == null ? 0 : this.getCorpName().hashCode());
		result = 37 * result
				+ (getPosition() == null ? 0 : this.getPosition().hashCode());
		result = 37 * result
				+ (getRank() == null ? 0 : this.getRank().hashCode());
		result = 37 * result
				+ (getQualLevel() == null ? 0 : this.getQualLevel().hashCode());
		result = 37 * result
				+ (getHwbgLevel() == null ? 0 : this.getHwbgLevel().hashCode());
		return result;
	}

}