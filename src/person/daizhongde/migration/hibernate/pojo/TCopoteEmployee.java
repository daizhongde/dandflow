package person.daizhongde.migration.hibernate.pojo;

/**
 * TCopoteEmployee entity. @author MyEclipse Persistence Tools
 */

public class TCopoteEmployee implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 734572070872391208L;
	private Long id;
	private Long uin;
	private Long pid;
	private String name;
	private String alias;
	private String sex;
	private String pos;
	private String tel;
	private String birth;
	private String slaveAlias;
	private String department;
	private String mobile;
	private String employeeNo;
	private String employeeCardno;
	private String employeeIdcard;
	private String logname;

	// Constructors

	/** default constructor */
	public TCopoteEmployee() {
	}

	/** minimal constructor */
	public TCopoteEmployee(Long uin, Long pid, String name, String alias,
			String sex, String department, String mobile) {
		this.uin = uin;
		this.pid = pid;
		this.name = name;
		this.alias = alias;
		this.sex = sex;
		this.department = department;
		this.mobile = mobile;
	}

	/** full constructor */
	public TCopoteEmployee(Long uin, Long pid, String name, String alias,
			String sex, String pos, String tel, String birth,
			String slaveAlias, String department, String mobile,
			String employeeNo, String employeeCardno, String employeeIdcard,
			String logname) {
		this.uin = uin;
		this.pid = pid;
		this.name = name;
		this.alias = alias;
		this.sex = sex;
		this.pos = pos;
		this.tel = tel;
		this.birth = birth;
		this.slaveAlias = slaveAlias;
		this.department = department;
		this.mobile = mobile;
		this.employeeNo = employeeNo;
		this.employeeCardno = employeeCardno;
		this.employeeIdcard = employeeIdcard;
		this.logname = logname;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUin() {
		return this.uin;
	}

	public void setUin(Long uin) {
		this.uin = uin;
	}

	public Long getPid() {
		return this.pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAlias() {
		return this.alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPos() {
		return this.pos;
	}

	public void setPos(String pos) {
		this.pos = pos;
	}

	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getBirth() {
		return this.birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getSlaveAlias() {
		return this.slaveAlias;
	}

	public void setSlaveAlias(String slaveAlias) {
		this.slaveAlias = slaveAlias;
	}

	public String getDepartment() {
		return this.department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmployeeNo() {
		return this.employeeNo;
	}

	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}

	public String getEmployeeCardno() {
		return this.employeeCardno;
	}

	public void setEmployeeCardno(String employeeCardno) {
		this.employeeCardno = employeeCardno;
	}

	public String getEmployeeIdcard() {
		return this.employeeIdcard;
	}

	public void setEmployeeIdcard(String employeeIdcard) {
		this.employeeIdcard = employeeIdcard;
	}

	public String getLogname() {
		return this.logname;
	}

	public void setLogname(String logname) {
		this.logname = logname;
	}

}